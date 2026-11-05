#!/bin/sh
#
# Cleans up any previous run, brings the database up, and follows its log until you press
# Ctrl-C, which tears the stack back down again. Both ends use `down -v`.
#
# One SQL Server, on localhost:1433, holds the three databases, AdventureWorks,
# WideWorldImporters and FutonManufacturing. Its data directory, /var/opt/mssql, is not bound to
# the host (its line in compose.yaml is commented out), so it lives in the container, which
# `down -v` removes. Every run therefore starts from an empty data directory, and
# opt/sample-databases/load.sh restores the backups, runs the scripts and creates the dmlonly
# login before the container reports healthy, which takes under a minute.
#
set -eu

cd "$(dirname "$0")"

if docker compose version >/dev/null 2>&1; then
    compose() { docker compose "$@"; }
else
    compose() { docker-compose "$@"; }
fi

logs_pid=""

on_interrupt() {
    # stop trapping, so a second Ctrl-C can still kill a slow teardown
    trap - INT TERM
    # guard, not `&&`: under `set -e` an empty logs_pid (Ctrl-C during startup, before the
    # log tail exists) would make the handler exit before it ever ran `down -v`
    if [ -n "${logs_pid}" ]; then
        kill "${logs_pid}" 2>/dev/null || true
    fi
    echo
    echo "==> down -v ..."
    compose down -v
    exit 0
}

trap on_interrupt INT TERM

# clear out whatever a previous run left behind -- a container killed without the trap
# firing would otherwise be reused by `up -d`
echo "==> down -v (cleaning up any previous run) ..."
compose down -v

echo "==> up -d ..."
compose up -d

echo "==> following the log; Ctrl-C to bring it down"
echo
# `logs -f` runs in the background and the script waits on it: a foreground child would
# make the shell defer the trap until it exited, and `logs -f` never exits on its own.
compose logs -f &
logs_pid=$!
wait "${logs_pid}"
