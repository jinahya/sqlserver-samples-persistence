package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.application;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Country;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Country_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_APPLICATION_I_01} of
 * {@code src/test/sql-exercises/_Application/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("WWI_APPLICATION_I_01: The countries and the population of each continent")
class WWI_APPLICATION_I_01_PersistenceIT {

    private static final List<String> EXPECTED = List.of(
            "Asia,46,4119330174",
            "Africa,50,983682747",
            "Europe,43,732605961",
            "North America,23,538347913",
            "South America,12,395988881",
            "Oceania,13,35907679",
            "Seven seas (open ocean),3,1768074"
    );

    private static String row(final Object[] row) {
        return row[0] + "," + longOf(row[1]) + "," + longOf(row[2]);
    }

    @Test
    void __QueryLanguage() {
        final var found =
                applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                 SELECT c.continent, COUNT(c) AS countries, SUM(c.latestRecordedPopulation) AS population
                                                                                                 FROM ApplicationCountry AS c
                                                                                                 GROUP BY c.continent
                                                                                                 ORDER BY population DESC""",
                                                                                         Object[].class)
                        .getResultList()
                        .stream().map(WWI_APPLICATION_I_01_PersistenceIT::row).toList());
        assertThat(found).isEqualTo(EXPECTED);
    }

    @Test
    void __CriteriaApi() {
        final var found = applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var root = query.from(Country.class);
            final var continent = root.get(Country_.continent);
            final var population = builder.sum(root.get(Country_.latestRecordedPopulation));
            query.multiselect(continent, builder.count(root), population);
            query.groupBy(continent);
            query.orderBy(builder.desc(population));
            return entityManager.createQuery(query).getResultList()
                    .stream().map(WWI_APPLICATION_I_01_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
