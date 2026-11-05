#!/usr/bin/env bash
# Starts the loader in the background, then hands the process over to the official image's own entrypoint, so SQL
# Server receives the container's signals as it does in the official image. As the jinahya/sql-server-samples images'
# own entrypoint.sh does.
set -Eeuo pipefail

/opt/sample-databases/load.sh &

exec /opt/mssql/bin/launch_sqlservr.sh "$@"
