package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Person/01_basic.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Person_Exercises_01_Basic_PersistenceIT {

    @DisplayName("AW_PERSON_B_01: the persons of each type")
    @Nested
    class AW_PERSON_B_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "IN | 18484",
                    "SC | 753",
                    "GC | 289",
                    "EM | 273",
                    "VC | 156",
                    "SP | 17"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT p.personType, COUNT(p)
                            FROM PersonPerson p
                            GROUP BY p.personType
                            ORDER BY COUNT(p) DESC""",
                    Object[].class
            ).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var p = query.from(Person.class);
                final var persons = builder.count(p);
                query.multiselect(p.get(Person_.personType), persons);
                query.groupBy(p.get(Person_.personType));
                query.orderBy(builder.desc(persons));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }

    @DisplayName("AW_PERSON_B_02: The addresses of each address type")
    @Nested
    class AW_PERSON_B_02_Test {
    }

    @DisplayName("AW_PERSON_B_03: An organization's contacts and their roles")
    @Nested
    class AW_PERSON_B_03_Test {
    }
}
