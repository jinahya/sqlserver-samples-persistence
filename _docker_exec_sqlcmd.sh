#!/bin/sh
# Opens sqlcmd, as sa, in one of the containers from ./compose.sh: adventureworks, wideworldimporters or
# futonmanufacturing, the container also naming the database it holds. Further arguments go to sqlcmd, `-Q` say.
case "${1:-}" in
    adventureworks) database=AdventureWorks ;;
    wideworldimporters) database=WideWorldImporters ;;
    futonmanufacturing) database=FutonManufacturing ;;
    *) echo "usage: $0 adventureworks|wideworldimporters|futonmanufacturing [sqlcmd arguments]" >&2; exit 2 ;;
esac
container=$1
shift
exec docker exec -it "${container}" /opt/mssql-tools18/bin/sqlcmd -C -S localhost -U sa -P 'Passw0rd!' \
    -d "${database}" "$@"
