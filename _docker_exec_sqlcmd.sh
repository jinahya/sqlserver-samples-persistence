#!/bin/sh
# Opens sqlcmd, as sa, in the container from ./compose.sh, on one of its databases: adventureworks, wideworldimporters
# or futonmanufacturing. Further arguments go to sqlcmd, `-Q` say.
case "${1:-}" in
    adventureworks) database=AdventureWorks ;;
    wideworldimporters) database=WideWorldImporters ;;
    futonmanufacturing) database=FutonManufacturing ;;
    *) echo "usage: $0 adventureworks|wideworldimporters|futonmanufacturing [sqlcmd arguments]" >&2; exit 2 ;;
esac
shift
exec docker exec -it sqlserver-samples /opt/mssql-tools18/bin/sqlcmd -C -S localhost -U sa -P 'Passw0rd!' \
    -d "${database}" "$@"
