# sqlserver-samples-persistence

Jakarta Persistence mappings of the Microsoft SQL Server
[sample databases](https://github.com/microsoft/sql-server-samples/tree/master/samples/databases), one Maven module
per database.

| Module | Database | Tables + views | Entities | Keyless classes |
|---|---|---|---|---|
| `sqlserver-samples-persistence-adventureworks` | `AdventureWorks`, the classic OLTP sample | 71 + 20 | 82 | 9 |
| `sqlserver-samples-persistence-futonmanufacturing` | `FutonManufacturing`, a bill of materials, inventory, production and sales | 32 + 40 | 50 | 22 |
| `sqlserver-samples-persistence-wideworldimporters` | `WideWorldImporters`, the SQL Server 2016+ sample | 48 + 3 | 34 | 17 |

The three depend on `sqlserver-samples-persistence-common`, which holds what they share: `MappedWithIdentityId`,
and the `hierarchyid` and `geography` types with their converters.

Each module's main sources are a package per schema, `<database>.mapped.<schema>`: one `@MappedSuperclass`
`Mapped<Entity>` per table and view, and one `<Entity>Id` per composite key. The entities, which add `@Entity`,
`@Table`, the `@IdClass` of a composite key and the associations, live in `src/test/java`, in `<database>.<schema>`,
with the persistence unit, `<database>-it`, and an integration test per class. The layout follows
[oracle-sample-schemas-persistence](https://github.com/jinahya/oracle-sample-schemas-persistence).

A table or view is named within its schema, and so is its class: `Website.Customers` is `website.Customer`, next to
`sales.Customer`. An entity name is unique only across the persistence unit, so every entity takes its schema in its
name, `@Entity(name = "<Schema><Entity>")`: `SalesCustomer`, `WebsiteCustomer`, `DboItem`. Queries use these names.

## The database

```shell
$ ./compose.sh
```

Brings up one container per database, from
[`jinahya/sql-server-samples`](https://hub.docker.com/r/jinahya/sql-server-samples) (see
[sql-server-samples-docker](https://github.com/jinahya/sql-server-samples-docker)), as `sa` / `Passw0rd!`, and follows
their logs; Ctrl-C takes them down again.

| Container | Image | Port | JDBC URL |
|---|---|---|---|
| `adventureworks` | `jinahya/sql-server-samples:adventureworks-2025` | 1433 | `jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=AdventureWorks` |
| `wideworldimporters` | `jinahya/sql-server-samples:wideworldimporters-2025` | 1434 | `jdbc:sqlserver://localhost:1434;encrypt=true;trustServerCertificate=true;databaseName=WideWorldImporters` |
| `futonmanufacturing` | `jinahya/sql-server-samples:futon-manufacturing-2025` | 1435 | `jdbc:sqlserver://localhost:1435;encrypt=true;trustServerCertificate=true;databaseName=FutonManufacturing` |

Each is the official `mcr.microsoft.com/mssql/server:2025-latest` image with its database loaded on first start. Every
run starts from an empty data directory, so loading happens every time, under a minute; a container is `healthy` once
its database is there. The three SQL Servers want about 6 GB of Docker's memory together. The images are `linux/amd64`
only; on Apple silicon, turn on Rosetta in Docker Desktop.

```shell
$ ./_docker_exec_sqlcmd.sh adventureworks
```

## The mappings

A mapped superclass holds what the database says -- the names, the types, the lengths, the nullability, the identity
columns, the sequences, the computed and period columns -- and nothing an application decides. The one exception is
`@Email` on `Person.EmailAddress.EmailAddress`, which the table does not check, but which every installed row holds.
A foreign-key column is mapped read-only there; the entity's `@ManyToOne` writes it, and the association's setter
keeps the basic attribute in step. Where a column cannot be written by one association alone -- it is part of the
primary key, or of two foreign keys -- the basic attribute writes it, and the association is the read-only one; under
an `@EmbeddedId`, the association writes it, and `@MapsId` derives the attribute of the key from it.

| SQL Server | Attribute | How |
|---|---|---|
| `int`, `smallint`, `tinyint`, `bigint` | `Integer`, `Integer`, `Integer`, `Long` | `smallint` with `@Min(-32768)` and `@Max(32767)`, `tinyint` with `@Min(0)` and `@Max(255)`, narrowed to a `CHECK` constraint's bounds, which a comment quotes; an `IDENTITY` key, below |
| `decimal`, `numeric`, `money`, `smallmoney` | `BigDecimal` | `precision` and `scale`, for `decimal` and `numeric`; `@DecimalMin` and `@DecimalMax`, the type's range or a check constraint's bound (`ATTRIBUTE_DECIMAL_MIN_*`, `ATTRIBUTE_DECIMAL_MAX_*`), and no `@Digits` |
| `float`, `real`, `bit` | `Double`, `Float`, `Boolean` | |
| `char`, `nchar`, `varchar`, `nvarchar`, `sysname` | `String` | `length`, and `@Size`; `(max)` has no length |
| `xml` | a class of a `sqlserver-xml-schema-binding-*` artifact, or `String` | the bound class, through a converter, where a binding exists (below); otherwise the document's text |
| `date`, `time`, `datetime`, `datetime2` | `LocalDate`, `LocalTime`, `LocalDateTime` | |
| `uniqueidentifier` | `UUID` | Hibernate ORM maps it, with no converter; an identifier is a `String` |
| `varbinary` | `byte[]` | the driver's bytes |
| `hierarchyid` | `-common`'s `Heirarchyid` | `HeirarchyidAttributeConverter`, through the serialized form of [MS-SSCLRT] section 2.2; its `toString()` is [`ToString()`](https://learn.microsoft.com/en-us/sql/t-sql/data-types/tostring-database-engine)'s |
| `geography` | `-common`'s `Geography` | `GeographyAttributeConverter`, through the serialized form of [MS-SSCLRT] section 2.1; its `toString()` is [`STAsText()`](https://learn.microsoft.com/en-us/sql/t-sql/spatial-geography/stastext-geography-data-type)'s, but for the digits of a coordinate |
| alias types (`Name`, `Flag`, `Phone`, ...) | as their base type | |

- An `IDENTITY` column is `@GeneratedValue(strategy = IDENTITY)`, and WideWorldImporters' keys, whose defaults are
  [`NEXT VALUE FOR`](https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql) `[Sequences].[...]`, `@GeneratedValue(strategy = SEQUENCE)` with a `@SequenceGenerator` per
  sequence. An `IDENTITY` column has no `@Min` or `@Max`: the database assigns it, and the attribute is never set.
- An AdventureWorks `dbo` or `HumanResources` table keyed by a single `IDENTITY` column extends `-common`'s
  `MappedWithIdentityId`, whose `Long id_` maps the column through an `@AttributeOverride`, whatever its width.
  `Production.Location` and `Production.ScrapReason`, keyed by `smallint` `IDENTITY` columns too, map them as `Long`s
  of their own, and a foreign key to any of these keys is a `Long` as the key is, whatever it declares, so that the
  two compare.
- A keyless class, which no converter reaches, takes a `hierarchyid` or a `geography` column's bytes in its
  constructor and decodes them.
- No attribute is `@Column(unique = true)`. A unique key or index is a `@UniqueConstraint` of the entity's `@Table`,
  under the name the database gives it (`AK_AddressType_rowguid`), with all its columns; a mapped superclass has no
  `@Table`, and `unique = true` names no constraint and takes one column only. The two together would generate one
  constraint twice, the second under a name the provider makes up.
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
- A column a `DEFAULT` stamps is not inserted, so that the default fills it: a `rowguid` (`newid()`) and a creation
  date (FutonManufacturing's `CreatedDate`) are read-only, and a last-modified date (AdventureWorks' `ModifiedDate`,
  FutonManufacturing's `ModifiedDate`, WideWorldImporters' `LastEditedWhen`) is written by an UPDATE, since nothing in
  the database restamps it; `Purchasing.PurchaseOrderDetail.ModifiedDate`, which its `uPurchaseOrderDetail` trigger
  restamps, is read-only. Such an attribute carries no `@NotNull`, for it is null until the INSERT.
- A primary-key column is not updatable, and an `IDENTITY` column, which SQL Server assigns, is not insertable either.
- A description the database holds, as an extended property (`MS_Description` in AdventureWorks, `Description` in
  WideWorldImporters; FutonManufacturing has none), is the `comment` of the column's `@Column` and of the entity's
  `@Table`, verbatim; a mapped superclass, which has no `@Table`, keeps its table's in `-common`'s `@MsDescription`.
  Each module's `src/test/sql/__.sql` selects them.
- A read-only attribute has a `protected` setter, and a read-only `rowguid` none. A foreign-key attribute has
  `protected` accessors, for the association is what the entity exposes; a single-column primary key which is a foreign
  key (`Person.Person.BusinessEntityID`) has a `public` getter and a `protected` setter, which its association's setter
  calls.
- A column name SQL Server reserves, or which is no plain identifier (`Group`, `Database Version`, `2002`), is
  quoted, `"\"Group\""`.
- AdventureWorks' `dbo.DatabaseLog.XmlEvent`, one [`EVENTDATA()`](https://learn.microsoft.com/en-us/sql/t-sql/functions/eventdata-transact-sql) document a row, is an `EventInstance`: the class
  [`sqlserver-xml-schema-binding-b872ce`](https://central.sonatype.com/artifact/io.github.jinahya/sqlserver-xml-schema-binding-b872ce)
  generates for the row's event type, `EVENTINSTANCECREATETABLE` for a `CREATE_TABLE`, through
  `EventInstanceStringAttributeConverter`, a `JaxbAttributeConverter` of `jinahya-persistence-more`. It is read-only:
  the database DDL trigger `ddlDatabaseTriggerLog` inserts each row and nothing updates one, and the bound classes do
  not override `equals`, by which Hibernate ORM would find every loaded value changed.
- AdventureWorks' typed `xml` columns are the classes of the `sqlserver-xml-schema-binding-*` artifacts which bind
  their collections' schemas, each through a `JaxbAttributeConverter` whose context knows the classes of every schema
  of the collection:

  | Column | Collection | Class (artifact) | Converter |
  |---|---|---|---|
  | `HumanResources.JobCandidate.Resume` | `HRResumeSchemaCollection` | `ResumeType` (`b3da3e`) | `ResumeTypeStringAttributeConverter` |
  | `Person.Person.AdditionalContactInfo`, `HumanResources.vEmployee.AdditionalContactInfo` | `AdditionalContactInfoSchemaCollection` | `AdditionalContactInfo` (`b7fdbe`; `b80e2e`, `b2e7ae`) | `AdditionalContactInfoStringAttributeConverter` |
  | `Person.Person.Demographics`, `Sales.vIndividualCustomer.Demographics` | `IndividualSurveySchemaCollection` | `IndividualSurvey` (`b9c1be`) | `IndividualSurveyStringAttributeConverter` |
  | `Production.ProductModel.CatalogDescription` | `ProductDescriptionSchemaCollection` | `ProductDescription` (`b0fd0e`; `b4347e`) | `ProductDescriptionStringAttributeConverter` |
  | `Production.ProductModel.Instructions` | `ManuInstructionsSchemaCollection` | `Root` (`b73cae`) | `ManuInstructionsStringAttributeConverter` |
  | `Sales.Store.Demographics` | `StoreSurveySchemaCollection` | `StoreSurvey` (`b95f5e`) | `StoreSurveyStringAttributeConverter` |

  `Resume` and `CatalogDescription` hold DOM content, which compares by identity, so Hibernate ORM treats their values
  as immutable (`@Mutability(Immutability.class)`): a change is written when a new value is set, not when the value is
  changed in place. `Sales.vIndividualCustomer` is keyless and read by a native query, which returns the column's
  text; the converter turns it into the `IndividualSurvey` the row's constructor takes. `Production.Illustration.Diagram`
  is untyped XAML, which no artifact binds, and stays a `String`.

What identifies a row:

| Object | Identifier |
|---|---|
| A table with a primary key | the primary key: `@Id`; for the 23 composite ones of AdventureWorks, `@IdClass(<Entity>Id.class)`, or an `@EmbeddedId` `<Entity>Id` which the associations derive with `@MapsId` (below) |
| `Production.Document` | its unique `rowguid`: the primary key, `DocumentNode`, is a `hierarchyid`, which no identifier type holds |
| A view | the columns which are unique, and not null, in every row it returns, when they are identifiers (`...ID`, `...Code`, `...Number`); an entity with no association |
| A view with no such columns, `Production.ProductDocument` (whose key includes a `hierarchyid`) and the 17 `*_Archive` history tables of WideWorldImporters | none: a keyless class, a plain class over the mapped superclass with a public all-args constructor |

A composite key is mapped by what it is made of, and the mapped superclass fixes how:

| Key | Mapping | Tables |
|---|---|---|
| foreign keys only, each to another entity's `@Id` of the same type | `@EmbeddedId`; each association of the key carries `@MapsId` and writes its column | `BusinessEntityContact`, `ProductModelIllustration`, `ProductModelProductDescriptionCulture`, `ProductProductPhoto`, `CountryRegionCurrency`, `PersonCreditCard`, `SalesOrderHeaderSalesReason` |
| foreign keys and a value of the row's own, an `IDENTITY` column, a view, or one the book shows as an `@IdClass` | `@IdClass`; the `@Id` attributes write the columns, and the associations are read-only | the other 15 tables, and `Production.vProductAndDescription` |

`BusinessEntityAddress`, whose key is three foreign keys like `BusinessEntityContact`'s, stays an `@IdClass`, so that
the two show the two mappings side by side; `ProductInventory`, whose key is two foreign keys as well, stays one
too.

Either way, an entity of a composite key is created by its `of(...)`, which takes what makes up the key and is the
only thing which sets it. `equals` and `hashCode` read the key through its getters, so that a Hibernate proxy answers
too; where the key is assigned only on persist, by `@MapsId` or an `IDENTITY` column, an instance equals only itself
until it is, and the hash is the same for every instance.

## Hibernate Spatial and `geography`

Hibernate Spatial and geolatte-geom are provided dependencies, but no mapping uses them: a `geography` column stays
`-common`'s `Geography`. Hibernate Spatial reads and writes a SQL Server `geography` with its latitude and longitude
swapped. Checked against `Person.Address.SpatialLocation`, with Hibernate ORM 7.4.12, geolatte-geom 1.11, and
mssql-jdbc 13.4.0:

| What | Result |
|---|---|
| `Point<G2D>` (geolatte) or `Point` (JTS), `@JdbcType(SqlServerGeographyType.class)` or `@JdbcTypeCode(SqlTypes.GEOGRAPHY)` | read with the axes swapped: `getLat()`, or JTS's `getY()`, is the column's `Long`, and the other its `Lat` |
| A native query with no declared type | the dialect knows the driver's `GEOGRAPHY` (-158), and returns a geolatte `Point`, swapped too |
| `st_srid`, `st_astext`, and `st_distance` of two columns, in the Query Language | right; SQL Server computes them, on the stored bytes |
| A point bound as a parameter, `st_distance(a.spatialLocation, :p)` | the axes swapped: a latitude out of range is rejected (`System.FormatException`); one within it would be another, wrong, point |
| A curve, or `FULLGLOBE` | not decoded: geolatte reads version 1 of the serialized form only |

The swap is geolatte's. Its SQL Server codec, which `SqlServerGeographyType` uses for both its binder and its
extractor, reads every value as a `geometry`, x before y, while a `geography` point is serialized latitude first
([MS-SSCLRT](https://learn.microsoft.com/en-us/openspecs/sql_server_protocols/ms-ssclrt/), section 2.1). A round trip
through Hibernate alone hides it: what it writes swapped, it reads back swapped.

- [geolatte-geom #58](https://github.com/GeoLatte/geolatte-geom/issues/58), "SQL Server: Lat/Lon ordering is wrong
  when doing encoding/decoding", open since 2018: the maintainer answers that the codec is for `geometry`, not for
  `geography`.
- [geolatte-geom #50](https://github.com/GeoLatte/geolatte-geom/issues/50), version 2 of the serialized form, open
  since 2017.
- [mssql-jdbc #851](https://github.com/microsoft/mssql-jdbc/issues/851), the same swap in the driver's own
  `microsoft.sql.Geography`, fixed in 7.1.3 ([#853](https://github.com/microsoft/mssql-jdbc/pull/853)); Hibernate
  Spatial does not use that class.
- [HHH-21058](https://hibernate.atlassian.net/browse/HHH-21058), "SqlServerGeographyType reads and binds SQL Server
  geography values with latitude and longitude swapped", this project's report to Hibernate. The type came with
  [HHH-15165](https://hibernate.atlassian.net/browse/HHH-15165)
  ([#4938](https://github.com/hibernate/hibernate-orm/pull/4938)); Hibernate's package documentation still says that
  only `geometry` is supported on SQL Server.

`SqlServerGeographyType` is Hibernate's, so it is Hibernate the swap is to be reported to, and fixed by; a `JdbcType`
of this project's own, which would swap the axes itself, is the last resort.

## Building

The persistence provider is Hibernate ORM, a provided dependency; the property `persistence-unit.provider` names it, and
each module's `persistence.xml` reads it. EclipseLink is not supported: how a provider meets SQL Server's driver differs
by database, driver and provider (EclipseLink binds a NULL `geography` with the driver's own `GEOGRAPHY` type, which the
driver rejects), and the MySQL and Oracle projects keep both providers. Each module's unit, `<database>-it`, reads the
JDBC URL and the credentials from the root `pom.xml`'s `mssql.*` properties.

| runs | script |
|---|---|
| unit tests | `_mvn_jakarta_ee_11_test_hibernate_orm.sh` |
| unit tests and ITs, the database up | `_mvn_jakarta_ee_11_verify_hibernate_orm.sh` |
| no test, but still compiles them | `_mvn_jakarta_ee_11_verify_notests_hibernate_orm.sh` |

`-Dtests.skipAfterFailureCount=0` runs every test instead of stopping at the first failure. Building needs Java 25.

## The tests

Each entity's `*_PersistenceIT`, each test inside a transaction it rolls back,

- selects a random installed row, and validates it against the mapping's constraints, which Jakarta Persistence does
  only on persist, update and remove;
- resolves each of its `ATTRIBUTE_PATH_*` constants, such as `id.personId`, through the metamodel;
- persists a new instance, which its `<Entity>_Randomizer` fills and its `<Entity>_Persister` persists with
  the rows it references, and flushes it.

An entity which is not persisted -- a view, or a table which only SQL Server,
the installer or a procedure writes -- has the test `@Disabled`, with the reason.

Each id class of a composite key has a unit test, `<Entity>Id_Test`, run by surefire: its `toString`, its `equals` and
`hashCode`, with EqualsVerifier, and its accessors.

Each keyless class's `*_PersistenceIT` reads every column its mapped superclass names by a native query, and checks
that its public constructor takes them all.

| Module | Entity and keyless ITs | Exercise ITs | Skipped |
|---|---|---|---|
| adventureworks | 348 | 42 | 18 |
| futonmanufacturing | 194 | 28 | 22 |
| wideworldimporters | 150 | 31 | 4 |

Each module's `<Database>_Exercises_PersistenceIT` answers the exercises of its `EXERCISES.asciidoc` with the Query
Language and the Criteria API, and asserts what the SQL in `src/test/sql/exercises` returns from the installed data.

All of them pass. A test is skipped when a table or a view returns no row, or an entity is not persisted: a view, or
a table which only SQL Server, the installer or a procedure writes.

## The diagrams

Each module's `docs/erd` holds its logical ER diagrams, generated by `.claude/skills/erd` (see its `SKILL.md`):
`__<Database>.svg` for the whole database, `_<group>.svg` for each logical group of tables, and one per table and
view.
