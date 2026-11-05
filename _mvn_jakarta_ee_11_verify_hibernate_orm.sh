#!/bin/sh
# Runs `verify`, Hibernate ORM the only provider: the whole lifecycle -- compile, package, javadoc,
# enforcer -- with both the unit tests and the integration tests executed.
#
# The ITs talk to a real SQL Server at localhost:1433, so bring it up with ./_compose.sh first;
# otherwise they fail on the connection rather than on a mapping.
#
# `failsafe` is the profile that binds maven-failsafe-plugin.
#
# As of 3.6.0 failsafe no longer binds its own skipTests parameter to the skipTests user property,
# so the two halves are selected separately -- and that is a property of ${version.maven-failsafe};
# under 3.5.x and earlier -DskipTests reached both:
#
#     ./_mvn_jakarta_ee_11_verify_hibernate_orm.sh -DskipTests   # the ITs alone
#     ./_mvn_jakarta_ee_11_verify_hibernate_orm.sh -DskipITs     # the unit tests alone
#     ./_mvn_jakarta_ee_11_verify_notests_hibernate_orm.sh       # neither
#
# `clean` keeps a run from compiling against the metamodel a previous run generated.
cd "$(dirname "$0")" || exit 1

exec ./mvnw -Pfailsafe clean verify "$@"
