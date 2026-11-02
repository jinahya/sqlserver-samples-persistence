#!/bin/sh
# Runs the unit tests; Hibernate ORM is the only provider.
#
# `clean` keeps a run from compiling against the metamodel a previous run generated.
cd "$(dirname "$0")" || exit 1

exec ./mvnw clean test "$@"
