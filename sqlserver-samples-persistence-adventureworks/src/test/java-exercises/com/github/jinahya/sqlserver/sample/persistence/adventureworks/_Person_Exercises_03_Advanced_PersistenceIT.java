package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.PersonDemographicsView;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.PersonDemographicsView_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Person/03_advanced.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Person_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("AW_PERSON_A_01: the individual customers of each yearly income band")
    @Nested
    class AW_PERSON_A_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "0-25000 | 2922",
                    "25001-50000 | 5704",
                    "50001-75000 | 5476",
                    "75001-100000 | 2755",
                    "greater than 100000 | 1627"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT v.yearlyIncome, COUNT(v)
                            FROM SalesPersonDemographicsView v
                                     JOIN PersonPerson p ON p.businessEntityId = v.businessEntityId
                            WHERE p.personType = :personType
                            GROUP BY v.yearlyIncome
                            ORDER BY v.yearlyIncome""",
                    Object[].class
            ).setParameter("personType", Person.PersonType.IN).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var v = query.from(PersonDemographicsView.class);
                final var p = v.join(Person.class);
                p.on(builder.equal(p.get(Person_.businessEntityId),
                                   v.get(PersonDemographicsView_.businessEntityId)));
                final var yearlyIncome = v.get(PersonDemographicsView_.yearlyIncome);
                query.multiselect(yearlyIncome, builder.count(v));
                query.where(builder.equal(p.get(Person_.personType), Person.PersonType.IN));
                query.groupBy(yearlyIncome);
                query.orderBy(builder.asc(yearlyIncome));
                return entityManager.createQuery(query).getResultList();
            }));
        }
    }
}
