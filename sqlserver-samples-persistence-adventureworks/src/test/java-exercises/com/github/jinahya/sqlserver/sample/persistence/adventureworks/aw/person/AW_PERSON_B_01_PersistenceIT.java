package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.person;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PERSON_B_01} of {@code src/test/sql-exercises/_Person/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_PERSON_B_01: The persons of each type")
class AW_PERSON_B_01_PersistenceIT {

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
            query.select(builder.array(p.get(Person_.personType), persons));
            query.groupBy(p.get(Person_.personType));
            query.orderBy(builder.desc(persons));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
