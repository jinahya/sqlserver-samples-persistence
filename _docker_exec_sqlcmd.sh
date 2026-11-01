#!/bin/sh
# Opens sqlcmd in the container from ./compose.sh, as sa; arguments go to sqlcmd, `-d AdventureWorks` say.
exec docker exec -it mssql-sample-databases /opt/mssql-tools18/bin/sqlcmd -C -S localhost -U sa -P 'Passw0rd!' "$@"
