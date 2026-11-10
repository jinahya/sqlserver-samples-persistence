package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.sales;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesTerritory_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_SALES_I_01} of {@code src/test/sql-exercises/_Sales/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_SALES_I_01: The orders of each territory")
class AW_SALES_I_01_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "Southwest | North America | 6224 | 27150594.5893",
                "Canada | North America | 4067 | 18398929.188",
                "Northwest | North America | 4594 | 18061660.371",
                "Australia | Pacific | 6843 | 11814376.0952",
                "Central | North America | 385 | 8913299.2473",
                "Southeast | North America | 486 | 8884099.3669",
                "United Kingdom | Europe | 3219 | 8574048.7082",
                "France | Europe | 2672 | 8119749.346",
                "Northeast | North America | 352 | 7820209.6285",
                "Germany | Europe | 2623 | 5479819.5755"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT t.name, t.group, COUNT(h), SUM(h.totalDue)
                        FROM SalesSalesOrderHeader h
                                 JOIN h.territory t
                        GROUP BY t.territoryId, t.name, t.group
                        ORDER BY SUM(h.totalDue) DESC""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var h = query.from(SalesOrderHeader.class);
            final var t = h.join(SalesOrderHeader_.territory);
            final var totalDue = builder.sum(h.get(SalesOrderHeader_.totalDue));
            query.select(builder.array(t.get(SalesTerritory_.name), t.get(SalesTerritory_.group),
                                       builder.count(h),
                                       totalDue));
            query.groupBy(t.get(SalesTerritory_.territoryId), t.get(SalesTerritory_.name),
                          t.get(SalesTerritory_.group));
            query.orderBy(builder.desc(totalDue));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
