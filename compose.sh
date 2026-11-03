#!/bin/sh
#
# Cleans up any previous run, brings the three databases up, and follows their logs until you
# press Ctrl-C, which tears the stack back down again. Both ends use `down -v`.
#
# Each database runs in its own container: adventureworks on localhost:1433, wideworldimporters
# on 1434, futonmanufacturing on 1435. The data directories are not mounted, so they live in the
# containers' anonymous volumes, which `down -v` removes. Every run therefore starts from empty
# data directories, and each image's loader restores its backup, or runs its scripts, before its
# container reports healthy, which takes under a minute.
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
