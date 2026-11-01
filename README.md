# mssql-sample-databases-persistence

Jakarta Persistence mappings of the Microsoft SQL Server
[sample databases](https://github.com/microsoft/sql-server-samples/tree/master/samples/databases), one Maven module
per database.

| Module | Database |
|---|---|
| `mssql-sample-databases-persistence-adventureworks` | `AdventureWorks`, the classic OLTP sample |
| `mssql-sample-databases-persistence-futonmanufacturing` | `FutonManufacturing`, a multi-level bill of materials, inventory, production and sales |
| `mssql-sample-databases-persistence-wideworldimporters` | `WideWorldImporters`, the SQL Server 2016+ sample |

The modules hold no mapping yet.

## The database

```shell
$ ./compose.sh
```

Brings up [`onacit/mssql-sample-databases:2025-latest`](https://hub.docker.com/r/onacit/mssql-sample-databases) on
`localhost:1433`, as `sa` / `Passw0rd!`, and follows its log; Ctrl-C takes it down again. That is the official
`mcr.microsoft.com/mssql/server:2025-latest` image with the three databases loaded on first start (see
[mssql-sample-databases-docker](https://github.com/jinahya/mssql-sample-databases-docker)). Every run starts from an
empty data directory, so loading happens every time, under a minute; the container is `healthy` once the three are
there. The image is `linux/amd64` only; on Apple silicon, turn on Rosetta in Docker Desktop.

```shell
$ ./_docker-exec-sqlcmd.sh -d AdventureWorks
```

## Building

The persistence provider is the property `persistence-unit.provider`, which each module's `persistence.xml` reads; a
profile per provider sets it, `jakarta-ee-11-hibernate-orm` (the default) and `jakarta-ee-11-eclipselink`. Each
module's unit, `<database>-it`, reads the JDBC URL and the credentials from the root `pom.xml`'s `mssql.*`
properties.

| runs | Hibernate ORM | EclipseLink |
|---|---|---|
| unit tests | `_mvn_jakarta_ee_11_test_hibernate-orm.sh` | `_mvn_jakarta_ee_11_test_eclipselink.sh` |
| unit tests and ITs, the database up | `_mvn_jakarta_ee_11_verify_hibernate-orm.sh` | `_mvn_jakarta_ee_11_verify_eclipselink.sh` |
| no test, but still compiles them | `_mvn_jakarta_ee_11_verify_notests_hibernate-orm.sh` | `_mvn_jakarta_ee_11_verify_notests_eclipselink.sh` |

Building needs Java 25.
