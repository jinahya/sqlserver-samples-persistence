package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.person;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Address_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.CountryRegion_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.StateProvince_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PERSON_I_01} of {@code src/test/sql-exercises/_Person/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_PERSON_I_01: The five countries with the most addresses")
class AW_PERSON_I_01_PersistenceIT {

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
