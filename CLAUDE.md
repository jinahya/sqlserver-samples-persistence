# CLAUDE.md

Guidance for working in this repository.

## What this project is

Jakarta Persistence mappings of three Microsoft SQL Server sample databases, one Maven module per database:
`-adventureworks`, `-futonmanufacturing`, `-wideworldimporters`. Each depends on `-common`, which holds what they
share, and on nothing of the others, tests included. See `README.md` for the type mappings and the identifiers chosen.

## The module, as it stands (absolute rules)

- **Hibernate ORM only.** The provider is Hibernate ORM, a provided dependency; there is no provider profile.
  EclipseLink was dropped: what it does depends on the database, the driver and the provider together (here it binds a
  NULL with the driver's own `GEOGRAPHY` type, which the driver rejects), and the mysql and oracle projects keep both
  providers.
- **Main holds mapped superclasses; the entities are test code.** Main is a package per schema,
  `<database>.mapped.<schema>` (the schema in lower case): `Mapped<Entity>` per table and view and `<Entity>Id`
  per composite key (an `@IdClass` or an `@Embeddable`, which no class extends, so it carries no `Mapped` prefix).
  No association, fetch type, cascade or named query in main. Each entity, its keyless class and its IT are in
  `<database>.<schema>`; the test bases and the exercises stay in `<database>`.
- **A composite key is an `@IdClass` or an `@EmbeddedId`, by what it is made of**, and the mapped superclass fixes
  which; the entity does not choose.
  - `@EmbeddedId` with `@MapsId`, when every key column is a foreign key to another entity's `@Id` of the same Java
    type (a link table): `ProductModelIllustration`, `ProductModelProductDescriptionCulture`, `ProductProductPhoto`,
    `BusinessEntityContact`, `CountryRegionCurrency`, `PersonCreditCard`, `SalesOrderHeaderSalesReason`. The id class
    is an `@Embeddable` which maps the key columns; the mapped superclass extends `-common`'s
    `MappedWithEmbeddedId<<Entity>Id>`, which holds `@Valid @NotNull @EmbeddedId id_`, `null` until persist creates
    it, a `protected getId_()` and no setter, `ATTRIBUTE_NAME_ID_` and a `toString` of the id, and itself holds an
    `ATTRIBUTE_PATH_ID_<COLUMN>` per key column, its own `equals` and `hashCode`, and a `toString` of its other
    attributes. The entity puts `@MapsId(<Entity>Id.ATTRIBUTE_NAME_<COLUMN>)` on each association of the key, which
    writes its column, and has no setter for them. Its `<Entity>_Test` gives EqualsVerifier two ids for the `id_`
    field, whose type is a type variable.
  - `@IdClass` otherwise: a key with a value of the row's own (a date, a number, an `IDENTITY` column), a view, and
    `BusinessEntityAddress`, `ProductInventory`, `ProductVendor` and `SpecialOfferProduct` (the book quotes both as
    `@IdClass`). The `@Id` attributes stay in the mapped superclass: a key column which is a foreign key has a
    `protected` getter and setter; a value of the row's own a `public` getter and a `protected` setter.
    `SpecialOfferProduct`'s key getters are `public`, since `SalesOrderDetail` references the key with a composite
    foreign key.
  - Either way, `equals` and `hashCode` are `final` and read the key through its getters, never the fields, so that a
    Hibernate proxy, whose own fields are unset, answers; the getters are `protected` or `public`, never
    package-private, since a proxy is a subclass in the entity's package. Where the key is assigned only on persist
    (`@MapsId`, an `IDENTITY` column), `equals` holds only once every key attribute is assigned, and `hashCode` is the
    same for every instance.
  - The entity's `public static of(...)` takes the associations and values of the key, in key order, and sets them;
    nothing else changes them. Its persister persists `of(...)`, and copies into it what the randomizer fills.
  - The id class has `ATTRIBUTE_NAME_*` constants, `@NotNull` attributes and `final` `equals` and `hashCode` over
    its getters, and a `<Entity>Id_Test` (a `_NonEntity_Test`: `toString`, EqualsVerifier and the accessors) with its
    `<Entity>Id_Randomizer`, in `<database>.mapped.<schema>` of the tests.
- **Every `@Column` and `@JoinColumn` names `nullable`, `insertable` and `updatable`**, even where they take their
  default values.
- **Descriptions come from the database**, through the queries of each module's `src/test/sql/__.sql`, verbatim: a
  column's is its `@Column(comment = ...)`, a table's the entity's `@Table(comment = ...)` and, since a mapped
  superclass has no `@Table`, the mapped superclass's `@MsDescription`, as `oracle-sample-schemas-persistence` does
  with `@OraComment`.
- **A read-only `rowguid` has no setter.** Every table's `rowguid` is filled by the database's `DEFAULT (newid())` and
  never written; `Production.Document`'s, its `@Id`, and the views' keep theirs.
- **Every entity name carries its schema**, with no exception: `@Entity(name = "<Schema><Entity>")`, the schema as
  the database spells it with its first letter in upper case (`SalesCustomer`, `HumanResourcesEmployee`, `DboItem`).
  A class is named after its table within its schema, never with a schema prefix; where one class uses two of the
  same simple name, the second is qualified.
- **A foreign-key column is read-only in the mapped superclass** unless the column is part of the primary key or of
  another foreign key; then the basic attribute writes it and the association is read-only, except under an
  `@EmbeddedId`, where the association writes it through `@MapsId`. A column has exactly one writable mapping.
- **No H2, ITs only.** Each module has one persistence unit, `<database>-it`, on the database `compose.yaml` brings
  up. A test that needs the database is a `*_PersistenceIT`, run by failsafe (`-Pfailsafe`). `-common` has no
  persistence unit; its `*Test`s, run by surefire, test what needs no database (the `hierarchyid` and `geography`
  codecs), and a database module's ITs test them against SQL Server. The other surefire tests, which need no database
  either, are each module's `<Entity>_Test`s and AdventureWorks' `<Entity>Id_Test`s, of the id classes.
- **Exercises are registered.** Each module's `EXERCISES.asciidoc` states every problem, and the `EXERCISES.yaml`
  beside it registers every id, `<DB>_<SCHEMA>_<TIER>_<NN>` (`AW`, `FUTON`, `WWI`), with its `sql` file and its
  `test`, `<Database>_Exercises_PersistenceIT.<ID>_Test`. Ids are permanent: a new exercise takes the next unused
  number of its schema and tier, and a removed one is marked `retired: true`, never reused. The id is the asciidoc
  anchor and heading prefix, and the marker above the statement in the `.sql` file.
- **Test bases, one copy per module**: `___PersistenceIT_Utils`, `___Persistence_TestUtils`,
  `___Validation_TestUtils`, `_Entity_PersistenceIT`, `_Keyless_PersistenceIT`. A fix to one is carried to the other
  two. `___PersistenceIT_Utils` holds the entity manager factory, as `applyEntityManagerFactory(Function)`, and nothing
  of validation or of entity managers; as in `oracle-sample-schemas-persistence`, the bases hold no entity manager and
  no wrapper of their own, and a test goes through the jinahya library directly, one entity manager per call:
  `applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> ...))`, or
  `applyEntityManager` alone for a test which only reads. `___Persistence_TestUtils` holds only what the library has no
  single call for, `selectRandomEntityInstance(EntityManagerFactory, Class, BiFunction)`. `_Entity_PersistenceIT` runs
  `persistRandomizedEntityInstance__` and `selectRandomEntityInstance__`, and hands the instance to the hooks
  `randomizedEntityInstancePersisted__` and `randomEntityInstanceSelected__(EntityManager, T)`; an entity which is not
  persisted overrides `persistRandomizedEntityInstance__` with a `@Disabled` one. `___Validation_TestUtils` holds the
  validator factory, and `selectRandomEntityInstance__` validates the row it loads with it, since Jakarta Persistence
  validates only on persist, update and remove. `_Entity_PersistenceIT.attributePaths` resolves every `ATTRIBUTE_PATH_*`
  constant of an entity through the metamodel. The unit-test bases (from `oracle-sample-schemas-persistence`), run by
  surefire, are `__Test`, `__Object_Test` and `_Entity_Test` in every module, and `_NonEntity_Test`, for the id
  classes, in AdventureWorks only, the one module with composite keys. Every entity has an `<Entity>_Test`, which
  suppresses EqualsVerifier's `SURROGATE_KEY`, since its `equals` is by its key, and `STRICT_HASHCODE` where its
  `hashCode` is a constant, and, where the key is generated (`IDENTITY`, `SEQUENCE`, `MappedWithIdentityId`), also
  `IDENTICAL_COPY_FOR_VERSIONED_ENTITY`, since an instance whose key is not yet assigned equals only itself; as
  [EqualsVerifier's JPA guide](https://jqno.nl/equalsverifier/manual/jpa-entities/) has it, which the tests follow. An
  entity with no randomizer, a view or a table only the database writes, disables
  `toString_NotBlank_NewRandomizedInstance`, as its IT disables persisting. AdventureWorks' `_Entity_Test` gives
  EqualsVerifier two `XMLGregorianCalendar`s, which the classes binding the typed `xml` columns hold, and
  WideWorldImporters' two of `Person`, `Customer` and `Order`, whose associations are cyclic. The tests are compiled
  patched into the module, which, in FutonManufacturing and WideWorldImporters, is given `java.desktop` for
  `__Object_Test`'s `java.beans`.
- **`equals` and `hashCode` read the key through its getters, never the fields**, in every mapped superclass, so that a
  Hibernate proxy, whose own fields are unset, answers; EqualsVerifier's JPA check fails a field read.
- Member order and section markers follow `oracle-sample-schemas-persistence`'s `CLAUDE.md`: constants, nested
  types, constructors, `java.lang.Object`, accessors, then the private fields; markers padded with dashes to column
  100.
- **Prefer `Optional` over the ternary operator** for a value that depends on whether another is `null`:
  `Optional.ofNullable(x).map(f).orElse(null)`, not `x == null ? null : f(x)`.

## Dependency convergence

`enforce-dependency-convergence` runs on every module and is not skipped. Jakarta EE 11 is the only platform; the
`jakarta.*` APIs come from `jakarta.jakartaee-bom` with no version of their own. Declare any other version in the root
`pom.xml`.

**Every `<dependency>` names its `<scope>`**, `compile` included, though it is the default; a module's dependency
never leaves it out.
