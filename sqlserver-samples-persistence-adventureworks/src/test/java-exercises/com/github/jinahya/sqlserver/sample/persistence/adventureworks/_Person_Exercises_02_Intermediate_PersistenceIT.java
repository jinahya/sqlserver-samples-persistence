package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.CountryRegion_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.StateProvince_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Person/02_intermediate.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Person_Exercises_02_Intermediate_PersistenceIT {

    @DisplayName("AW_PERSON_I_01: the five countries with the most addresses")
    @Nested
    class AW_PERSON_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "US | United States | 8668",
                    "AU | Australia | 3632",
                    "GB | United Kingdom | 1954",
                    "FR | France | 1851",
                    "DE | Germany | 1821"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT c.countryRegionCode, c.name, COUNT(a)
                            FROM PersonAddress a
                                     JOIN a.stateProvince s
                                     JOIN s.countryRegion c
                            GROUP BY c.countryRegionCode, c.name
                            ORDER BY COUNT(a) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var a = query.from(Address.class);
                final var c = a.join(Address_.stateProvince).join(StateProvince_.countryRegion);
                final var addresses = builder.count(a);
                query.multiselect(c.get(CountryRegion_.countryRegionCode),
                                  c.get(CountryRegion_.name), addresses);
                query.groupBy(c.get(CountryRegion_.countryRegionCode), c.get(CountryRegion_.name));
                query.orderBy(builder.desc(addresses));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }
}
