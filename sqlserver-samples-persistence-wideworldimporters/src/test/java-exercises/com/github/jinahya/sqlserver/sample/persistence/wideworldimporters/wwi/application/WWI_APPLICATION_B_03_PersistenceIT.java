package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.application;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.City;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.City_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Country_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.StateProvince_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_APPLICATION_B_03} of
 * {@code src/test/sql-exercises/_Application/01_basic.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("WWI_APPLICATION_B_03: The cities of a country's states and provinces")
class WWI_APPLICATION_B_03_PersistenceIT {

    private static final int COUNTRY_ID = 230;

    private static String row(final City c) {
        final var sp = c.getStateProvince();
        return c.getCityId() + "," + c.getCityName() + "," + sp.getStateProvinceName() + ","
               + sp.getCountry().getCountryName() + "," + c.getLatestRecordedPopulation();
    }

    // reads the lazy state or province and its country, so it runs in the entity manager
    private void verify(final List<City> found) {
        assertThat(found).hasSize(37940);
        assertThat(found.subList(0, 5)).extracting(WWI_APPLICATION_B_03_PersistenceIT::row)
                .containsExactly(
                        "3,Abanda,Alabama,United States,192",
                        "6,Abbeville,Alabama,United States,2688",
                        "28,Aberfoil,Alabama,United States,null",
                        "29,Abernant,Alabama,United States,null",
                        "119,Adamsville,Alabama,United States,4522"
                );
        assertThat(found.subList(found.size() - 3, found.size()))
                .extracting(WWI_APPLICATION_B_03_PersistenceIT::row)
                .containsExactly(
                        "37948,Wyodak,Wyoming,United States,null",
                        "37959,Wyoming,Wyoming,United States,null",
                        "38046,Yoder,Wyoming,United States,151"
                );
    }

    @Test
    void __QueryLanguage() {
        applyInTransactionAndRollback(entityManager -> {
            verify(entityManager.createQuery(
                            """
                                    SELECT c
                                    FROM ApplicationCity c
                                             JOIN c.stateProvince sp
                                             JOIN sp.country co
                                    WHERE co.countryId = :countryId
                                    ORDER BY sp.stateProvinceName, c.cityName, c.cityId""",
                            City.class)
                           .setParameter("countryId", COUNTRY_ID)
                           .getResultList());
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(City.class);
            final var c = query.from(City.class);
            final var sp = c.join(City_.stateProvince);
            final var co = sp.join(StateProvince_.country);
            query.select(c)
                    .where(builder.equal(co.get(Country_.countryId), COUNTRY_ID))
                    .orderBy(builder.asc(sp.get(StateProvince_.stateProvinceName)),
                             builder.asc(c.get(City_.cityName)),
                             builder.asc(c.get(City_.cityId)));
            verify(entityManager.createQuery(query).getResultList());
            return null;
        });
    }

}
