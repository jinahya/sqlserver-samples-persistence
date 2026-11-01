# CLAUDE.md

Guidance for working in this repository.

## What this project is

Jakarta Persistence mappings of three Microsoft SQL Server sample databases, one Maven module per database:
`-adventureworks`, `-futonmanufacturing`, `-wideworldimporters`. The modules are independent of each other, tests
included. See `README.md` for the type mappings and the identifiers chosen.

## The module, as it stands (absolute rules)

- **Not Hibernate ORM only.** The provider is a property, `persistence-unit.provider`, set by the
  `jakarta-ee-11-hibernate-orm` (default) and `jakarta-ee-11-eclipselink` profiles. Every mapping is plain Jakarta
  Persistence and must work with both; check both before committing.
- **Main holds mapped superclasses; the entities are test code.** Main is the package `<database>.mapped` alone:
  `Mapped<Entity>` per table and view, `Mapped<Entity>Id` per composite key (an `@IdClass`), and
  `UuidStringAttributeConverter`. No association, fetch type, cascade or named query in main.
- **A foreign-key column is read-only in the mapped superclass** unless the column is part of the primary key or of
  another foreign key; then the basic attribute writes it and the association is read-only. A column has exactly one
  writable mapping.
- **No H2, ITs only.** Each module has one persistence unit, `<database>-it`, on the database `compose.yaml` brings
  up. A test that needs the database is a `*_PersistenceIT`, run by failsafe (`-Pfailsafe`).
- **Test bases, one copy per module**: `___Persistence_TestUtils`, `_Entity_PersistenceIT`, `_Keyless_PersistenceIT`.
  A fix to one is carried to the other two.
- Member order and section markers follow `oracle-sample-schemas-persistence`'s `CLAUDE.md`: constants, nested
  types, constructors, `java.lang.Object`, accessors, then the private fields; markers padded with dashes to column
  120.

## Dependency convergence

`enforce-dependency-convergence` runs on every module and is not skipped. Jakarta EE 11 is the only platform; the
`jakarta.*` APIs come from `jakarta.jakartaee-bom` with no version of their own. Declare any other version in the root
`pom.xml`.
