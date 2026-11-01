# sql-server-samples-persistence

Jakarta Persistence mappings of the Microsoft SQL Server
[sample databases](https://github.com/microsoft/sql-server-samples/tree/master/samples/databases), one Maven module
per database.

| Module | Database | Tables + views | Entities | Keyless classes |
|---|---|---|---|---|
| `mssql-sample-databases-persistence-adventureworks` | `AdventureWorks`, the classic OLTP sample | 71 + 20 | 82 | 9 |
| `mssql-sample-databases-persistence-futonmanufacturing` | `FutonManufacturing`, a bill of materials, inventory, production and sales | 32 + 40 | 50 | 22 |
| `mssql-sample-databases-persistence-wideworldimporters` | `WideWorldImporters`, the SQL Server 2016+ sample | 48 + 3 | 34 | 17 |

Each module's main sources are a package per schema, `<database>.mapped.<schema>`: one `@MappedSuperclass`
`Mapped<Entity>` per table and view, and one `Mapped<Entity>Id` per composite key. `UuidStringAttributeConverter`, which
the schemas share, is in `<database>.mapped`. The entities, which add `@Entity`, `@Table`, `@IdClass` and the
associations, live in `src/test/java`, in `<database>.<schema>`, with the persistence unit, `<database>-it`, and an
integration test per class. The layout follows
[oracle-sample-schemas-persistence](https://github.com/jinahya/oracle-sample-schemas-persistence).

A table or view is named within its schema, and so is its class: `Website.Customers` is `website.Customer`, next to
`sales.Customer`. An entity name is unique only across the persistence unit, so every entity takes its schema in its
name, `@Entity(name = "<Schema><Entity>")`: `SalesCustomer`, `WebsiteCustomer`, `DboItem`. Queries use these names.

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
$ ./_docker_exec_sqlcmd.sh -d AdventureWorks
```

## The mappings

A mapped superclass holds what the database says -- the names, the types, the lengths, the nullability, the identity
columns, the sequences, the computed and period columns -- and nothing an application decides. A foreign-key column
is mapped read-only there; the entity's `@ManyToOne` writes it, and the association's setter keeps the basic attribute
in step. Where a column cannot be written by one association alone -- it is part of the primary key, or of two
foreign keys -- the basic attribute writes it, and the association is the read-only one.

| SQL Server | Attribute | How |
|---|---|---|
| `int`, `smallint`, `tinyint`, `bigint` | `Integer`, `Short`, `Short`, `Long` | `tinyint` with `@Min(0)` and `@Max(255)` |
| `decimal`, `numeric`, `money`, `smallmoney` | `BigDecimal` | `precision` and `scale`, and `@Digits`, for `decimal` and `numeric` |
| `float`, `real`, `bit` | `Double`, `Float`, `Boolean` | |
| `char`, `nchar`, `varchar`, `nvarchar`, `sysname` | `String` | `length`, and `@Size`; `(max)` has no length |
| `xml` | `String` | the document's text; `dbo.DatabaseLog.XmlEvent` and `HumanResources.vEmployee.AdditionalContactInfo` are the exceptions, below |
| `date`, `time`, `datetime`, `datetime2` | `LocalDate`, `LocalTime`, `LocalDateTime` | |
| `uniqueidentifier` | `UUID` | `UuidStringAttributeConverter`, through the text; an identifier, to which no converter applies, is a `String` |
| `varbinary`, `hierarchyid`, `geography` | `byte[]` | the driver's bytes; the CLR types' serialized form |
| alias types (`Name`, `Flag`, `Phone`, ...) | as their base type | |

- An `IDENTITY` column is `@GeneratedValue(strategy = IDENTITY)`, and WideWorldImporters' keys, whose defaults are
  `NEXT VALUE FOR [Sequences].[...]`, `@GeneratedValue(strategy = SEQUENCE)` with a `@SequenceGenerator` per
  sequence.
- A computed column, and the `ValidFrom` / `ValidTo` period columns of a system-versioned table, are read-only.
- Every column of a view is read-only. SQL Server takes no whole row of any of the views in an INSERT or an UPDATE,
  which is how a provider writes one: the row spans more than one base table (error 4405), or has derived, constant
  or aggregated columns (errors 4406, 4403). Each class documents its view's reasons.
- Every column of a temporal history table, WideWorldImporters' `*_Archive`, is read-only; SQL Server rejects an
  INSERT (error 13559) and an UPDATE (error 13561) of one.
- Every column of a table only triggers write is read-only: AdventureWorks' `dbo.DatabaseLog`, which the database DDL
  trigger `ddlDatabaseTriggerLog` inserts, and `Production.TransactionHistory`, which the order and work-order
  triggers insert. Nothing updates a row of either. A column a trigger only partly maintains (`SubTotal`,
  `RevisionNumber`, `SalesYTD`) stays writable.
- A column name SQL Server reserves, or which is no plain identifier (`Group`, `Database Version`, `2002`), is
  quoted, `"\"Group\""`.
- `uniqueidentifier` needs the converter for EclipseLink, which reads the driver's text and parses it as hexadecimal
  bytes into a `UUID`; Hibernate ORM maps it either way.
- AdventureWorks' `dbo.DatabaseLog.XmlEvent`, one `EVENTDATA()` document a row, is an `EventInstance`: the class
  [`sqlserver-xml-schema-binding-b872ce`](https://central.sonatype.com/artifact/io.github.jinahya/sqlserver-xml-schema-binding-b872ce)
  generates for the row's event type, `EVENTINSTANCECREATETABLE` for a `CREATE_TABLE`, through
  `EventInstanceStringAttributeConverter`, a `JaxbAttributeConverter` of `jinahya-persistence-more`. It is read-only:
  the database DDL trigger `ddlDatabaseTriggerLog` inserts each row and nothing updates one, and the bound classes do
  not override `equals`, by which Hibernate ORM would find every loaded value changed.
- AdventureWorks' `HumanResources.vEmployee.AdditionalContactInfo`, typed by `Person.AdditionalContactInfoSchemaCollection`,
  is an `AdditionalContactInfo` of `sqlserver-xml-schema-binding-b7fdbe`, through
  `AdditionalContactInfoStringAttributeConverter`, whose context knows the classes of the collection's other two
  schemas, `b80e2e` (`ContactRecord`) and `b2e7ae` (`ContactTypes`).

What identifies a row:

| Object | Identifier |
|---|---|
| A table with a primary key | the primary key: `@Id`, or `@IdClass(Mapped<Entity>Id.class)` for the 23 composite ones of AdventureWorks |
| `Production.Document` | its unique `rowguid`: the primary key, `DocumentNode`, is a `hierarchyid`, which no identifier type holds |
| A view | the columns which are unique, and not null, in every row it returns, when they are identifiers (`...ID`, `...Code`, `...Number`); an entity with no association |
| A view with no such columns, `Production.ProductDocument` (whose key includes a `hierarchyid`) and the 17 `*_Archive` history tables of WideWorldImporters | none: a keyless class, a plain class over the mapped superclass with a public all-args constructor |

## Building

The persistence provider is the property `persistence-unit.provider`, which each module's `persistence.xml` reads; a
profile per provider sets it, `jakarta-ee-11-hibernate-orm` (the default) and `jakarta-ee-11-eclipselink`. Each
module's unit, `<database>-it`, reads the JDBC URL and the credentials from the root `pom.xml`'s `mssql.*`
properties.

| runs | Hibernate ORM | EclipseLink |
|---|---|---|
| unit tests | `_mvn_jakarta_ee_11_test_hibernate_orm.sh` | `_mvn_jakarta_ee_11_test_eclipselink.sh` |
| unit tests and ITs, the database up | `_mvn_jakarta_ee_11_verify_hibernate_orm.sh` | `_mvn_jakarta_ee_11_verify_eclipselink.sh` |
| no test, but still compiles them | `_mvn_jakarta_ee_11_verify_notests_hibernate_orm.sh` | `_mvn_jakarta_ee_11_verify_notests_eclipselink.sh` |

`-Dtests.skipAfterFailureCount=0` runs every test instead of stopping at the first failure. Building needs Java 25.

## The tests

Each entity's `*_PersistenceIT` reads up to eight installed rows, inside a transaction it rolls back, and

- finds each again by its identifier, from a cleared persistence context, equal to the one read;
- loads every association of each, and reads the identifier of the entity it references;
- validates each against the mapping's constraints.

Each keyless class's `*_PersistenceIT` reads every column its mapped superclass names by a native query, and checks
that its public constructor takes them all.

| Module | Entity and keyless ITs | Exercise ITs | Skipped |
|---|---|---|---|
| adventureworks | 264 | 42 | 39 |
| futonmanufacturing | 194 | 28 | 36 |
| wideworldimporters | 136 | 31 | 5 |

Each module's `<Database>_Exercises_PersistenceIT` answers the exercises of its `EXERCISES.asciidoc` with the Query
Language and the Criteria API, and asserts what the SQL in `src/test/sql/exercises` returns from the installed data.

All of them pass with both providers. A test is skipped when an entity maps no association, or a view returns no
row.

## The diagrams

Each module's `docs/erd` holds its logical ER diagrams, generated by `.claude/skills/erd` (see its `SKILL.md`):
`__<Database>.svg` for the whole database, `_<group>.svg` for each logical group of tables, and one per table and
view.
