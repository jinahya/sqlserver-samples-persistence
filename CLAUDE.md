# CLAUDE.md

Guidance for working in this repository.

## What this project is

Jakarta Persistence mappings of three Microsoft SQL Server sample databases, one Maven module per database:
`-adventureworks`, `-futonmanufacturing`, `-wideworldimporters`. Each depends on `-common`, which holds what they
share, and on nothing of the others, tests included. See `README.md` for the type mappings and the identifiers chosen.

## The module, as it stands (absolute rules)

- **Hibernate ORM only.** The provider is Hibernate ORM, a test dependency; there is no provider profile. EclipseLink
  was dropped: what it does depends on the database, the driver and the provider together (here it binds a NULL with
  the driver's own `GEOGRAPHY` type, which the driver rejects), and the mysql and oracle projects keep both providers.
- **Main holds mapped superclasses; the entities are test code.** Main is a package per schema,
  `<database>.mapped.<schema>` (the schema in lower case): `Mapped<Entity>` per table and view and `Mapped<Entity>Id`
  per composite key (an `@IdClass`).
  No association, fetch type, cascade or named query in main. Each entity, its keyless class and its IT are in
  `<database>.<schema>`; the test bases and the exercises stay in `<database>`.
- **Every entity name carries its schema**, with no exception: `@Entity(name = "<Schema><Entity>")`, the schema as
  the database spells it with its first letter in upper case (`SalesCustomer`, `HumanResourcesEmployee`, `DboItem`).
  A class is named after its table within its schema, never with a schema prefix; where one class uses two of the
  same simple name, the second is qualified.
- **A foreign-key column is read-only in the mapped superclass** unless the column is part of the primary key or of
  another foreign key; then the basic attribute writes it and the association is read-only. A column has exactly one
  writable mapping.
- **No H2, ITs only.** Each module has one persistence unit, `<database>-it`, on the database `compose.yaml` brings
  up. A test that needs the database is a `*_PersistenceIT`, run by failsafe (`-Pfailsafe`). `-common` has no
  persistence unit; its `*Test`s, run by surefire, test what needs no database (the `hierarchyid` and `geography`
  codecs), and a database module's ITs test them against SQL Server.
- **Exercises are registered.** Each module's `EXERCISES.asciidoc` states every problem, and the `EXERCISES.yaml`
  beside it registers every id, `<DB>_<SCHEMA>_<TIER>_<NN>` (`AW`, `FUTON`, `WWI`), with its `sql` file and its
  `test`, `<Database>_Exercises_PersistenceIT.<ID>_Test`. Ids are permanent: a new exercise takes the next unused
  number of its schema and tier, and a removed one is marked `retired: true`, never reused. The id is the asciidoc
  anchor and heading prefix, and the marker above the statement in the `.sql` file.
- **Test bases, one copy per module**: `___Persistence_TestUtils`, `_Entity_PersistenceIT`, `_Keyless_PersistenceIT`.
  A fix to one is carried to the other two.
- Member order and section markers follow `oracle-sample-schemas-persistence`'s `CLAUDE.md`: constants, nested
  types, constructors, `java.lang.Object`, accessors, then the private fields; markers padded with dashes to column
  120.
- **Prefer `Optional` over the ternary operator** for a value that depends on whether another is `null`:
  `Optional.ofNullable(x).map(f).orElse(null)`, not `x == null ? null : f(x)`.

## Dependency convergence

`enforce-dependency-convergence` runs on every module and is not skipped. Jakarta EE 11 is the only platform; the
`jakarta.*` APIs come from `jakarta.jakartaee-bom` with no version of their own. Declare any other version in the root
`pom.xml`.
