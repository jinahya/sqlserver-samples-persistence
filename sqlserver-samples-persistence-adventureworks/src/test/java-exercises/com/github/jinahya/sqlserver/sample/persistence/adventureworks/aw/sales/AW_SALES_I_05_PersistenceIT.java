package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.sales;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.CurrencyRate_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader_;
import jakarta.persistence.criteria.JoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_SALES_I_05} of {@code src/test/sql-exercises/_Sales/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_SALES_I_05: The orders and their amounts in each currency")
class AW_SALES_I_05_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "USD | 17489 | 81335163.3658 | 81335163.3658",
                "AUD | 6675 | 11804417.2776 | 21589723.3101",
                "CAD | 3839 | 18209612.3849 | 28060019.0616",
                "GBP | 3155 | 8570333.1218 | 5796020.349",
                "EUR | 172 | 2834975.8244 | 3090158.8861",
                "DEM | 76 | 262752.4184 | 527335.5275",
                "FRF | 59 | 199531.723 | 1340725.1818"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT COALESCE(r.toCurrencyCode, 'USD') AS currencyCode,
                               COUNT(h) AS orders,
                               SUM(h.totalDue),
                               SUM(h.totalDue * COALESCE(r.averageRate, 1))
                        FROM SalesSalesOrderHeader h
                                 LEFT JOIN h.currencyRate r
                        GROUP BY COALESCE(r.toCurrencyCode, 'USD')
                        ORDER BY orders DESC, currencyCode""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var h = query.from(SalesOrderHeader.class);
            final var r = h.join(SalesOrderHeader_.currencyRate, JoinType.LEFT);
            final var currencyCode = builder.coalesce(r.get(CurrencyRate_.toCurrencyCode),
                                                      builder.literal("USD"));
            final var orders = builder.count(h);
            final var totalDue = h.get(SalesOrderHeader_.totalDue);
            query.select(builder.array(currencyCode, orders, builder.sum(totalDue),
                                       builder.sum(builder.prod(totalDue, builder.coalesce(
                                               r.get(CurrencyRate_.averageRate),
                                               builder.literal(BigDecimal.ONE))))));
            query.groupBy(currencyCode);
            query.orderBy(builder.desc(orders), builder.asc(currencyCode));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
