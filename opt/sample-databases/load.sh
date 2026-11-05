#!/usr/bin/env bash
# Loads the three sample databases into the running server once, then creates the dmlonly login the integration-test
# persistence units connect as. Started in the background by entrypoint.sh. On success it writes the marker the health
# check looks for; on failure it stops the server, so the container exits instead of hanging.
#
# Each database comes from its jinahya/sql-server-samples image, whose /opt/sample-databases compose.yaml mounts at
# /sample-databases/<database>: a database.env, which names the database and its files, and files/. The loading is
# that of the images' own load.sh, which handles one database at /opt/sample-databases only, so it is repeated here
# for each: a backup is restored, scripts are run in order.
set -Eeuo pipefail

sources=/sample-databases
data=/var/opt/mssql/data
marker=/var/opt/mssql/.sample-databases-loaded
timeout=${SAMPLE_DATABASES_STARTUP_TIMEOUT:-300}
# in load order; the directories compose.yaml mounts
databases=(adventureworks wideworldimporters futonmanufacturing)

log() { echo "sample-databases: $*"; }

sqlcmd() { /opt/mssql-tools18/bin/sqlcmd -C -S localhost -U sa -P "$MSSQL_SA_PASSWORD" -b -I "$@"; }

fail() {
  log "load failed: $*" >&2
  pkill -TERM -x sqlservr 2>/dev/null || kill -TERM 1 || true
  exit 1
}
trap 'fail "line ${LINENO}"' ERR

if [ -f "$marker" ]; then
  log "already loaded"
  exit 0
fi

if [ -z "${MSSQL_SA_PASSWORD:-}" ]; then
  fail "MSSQL_SA_PASSWORD is not set"
fi

log "waiting for the server (up to ${timeout}s)"
start=$(date +%s)
until sqlcmd -Q "SELECT 1" >/dev/null 2>&1; do
  (( $(date +%s) - start < timeout )) || fail "the server did not accept connections"
  sleep 2
done

# Loads the database of one mounted image: SAMPLE_DATABASE_NAME, SAMPLE_DATABASE_FILES (in load order),
# SAMPLE_DATABASE_CREATE (1: the scripts expect the database to exist)
load() {
  local home=$1 SAMPLE_DATABASE_NAME SAMPLE_DATABASE_FILES SAMPLE_DATABASE_CREATE
  [ -f "${home}/database.env" ] || fail "${home}/database.env not found; is the image mounted?"
  . "${home}/database.env"
  local name=$SAMPLE_DATABASE_NAME files
  read -r -a files <<< "$SAMPLE_DATABASE_FILES"
  if [[ ${files[0]} == *.bak ]]; then
    local backup=${home}/files/${files[0]} moves
    # Moves every file of the backup into the data directory: the first data file to <name>.mdf, further data files
    # to <name>_<logical name>.ndf, the log to <name>_log.ldf
    moves=$(sqlcmd -h -1 -W -s '|' -Q "SET NOCOUNT ON; RESTORE FILELISTONLY FROM DISK = N'${backup}'" | tr -d '\r' \
      | awk -F '|' -v d="${data}/${name}" 'NF > 3 {
          if ($3 == "L") path = d "_log.ldf"; else if (++n == 1) path = d ".mdf"; else path = d "_" $1 ".ndf"
          printf ", MOVE N'\''%s'\'' TO N'\''%s'\''", $1, path }')
    [ -n "$moves" ] || fail "RESTORE FILELISTONLY listed no files of ${backup}"
    log "restoring ${name} from ${files[0]}"
    sqlcmd -Q "RESTORE DATABASE [${name}] FROM DISK = N'${backup}' WITH REPLACE${moves}" >/dev/null
  else
    local database=() f
    if [ "${SAMPLE_DATABASE_CREATE:-0}" = 1 ]; then
      log "creating ${name}"
      sqlcmd -Q "IF DB_ID(N'${name}') IS NULL CREATE DATABASE [${name}]"
      database=(-d "$name")
    fi
    for f in "${files[@]}"; do
      log "loading ${name}: ${f}"
      sqlcmd "${database[@]}" -i "${home}/files/${f}" >/dev/null
    done
  fi
  log "loaded ${name}"
  names+=("$name")
}

names=()
for d in "${databases[@]}"; do
  load "${sources}/${d}"
done

# The login the integration-test persistence units connect as, with sa's password: a user in each sample database,
# which may read and write its rows (sequences included, whose NEXT VALUE FOR needs UPDATE) and execute its objects (a
# typed xml column needs EXECUTE on its XML schema collection, and the ITs call stored procedures), and no DDL. VIEW
# SERVER PERFORMANCE STATE lets the ITs which count the log records of their own transaction read
# sys.dm_tran_database_transactions.
#
# WideWorldImporters filters and blocks the rows of Sales.Customers by a security policy, which lets a user see a
# customer only as db_owner or as a member of the role of its sales territory, `<territory> Sales`; sa is db_owner,
# dmlonly would see none. Those roles hold no permission of their own, so dmlonly is made a member of every one of them,
# which shows it every customer and grants it nothing else.
log "creating dmlonly"
grants=""
for name in "${names[@]}"; do
  grants+="
USE [${name}];
CREATE USER dmlonly FOR LOGIN dmlonly;
GRANT SELECT, INSERT, UPDATE, DELETE, EXECUTE ON DATABASE::[${name}] TO dmlonly;"
done
grants+="
USE [WideWorldImporters];
DECLARE @role sysname, @sql nvarchar(max);
DECLARE roles CURSOR LOCAL FAST_FORWARD FOR
    SELECT name FROM sys.database_principals WHERE type = 'R' AND is_fixed_role = 0 AND name LIKE N'% Sales';
OPEN roles;
FETCH NEXT FROM roles INTO @role;
WHILE @@FETCH_STATUS = 0
BEGIN
    SET @sql = N'ALTER ROLE ' + QUOTENAME(@role) + N' ADD MEMBER dmlonly';
    EXEC (@sql);
    FETCH NEXT FROM roles INTO @role;
END;"
out=$(sqlcmd -Q "CREATE LOGIN dmlonly WITH PASSWORD = N'${MSSQL_SA_PASSWORD}', CHECK_POLICY = OFF;
GRANT VIEW SERVER PERFORMANCE STATE TO dmlonly;${grants}" 2>&1) || fail "creating dmlonly: ${out}"

touch "$marker"
log "loaded ${names[*]}"
