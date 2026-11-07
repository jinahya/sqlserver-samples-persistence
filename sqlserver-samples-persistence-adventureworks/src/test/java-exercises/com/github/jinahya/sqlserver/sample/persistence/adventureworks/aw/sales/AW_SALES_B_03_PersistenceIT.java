package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.sales;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.SalesOrderHeaderSalesReasonId_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeaderSalesReason;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeaderSalesReason_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesReason;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesReason_;
import jakarta.persistence.criteria.JoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_SALES_B_03} of {@code src/test/sql-exercises/_Sales/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_SALES_B_03: The orders that gave each sales reason")
class AW_SALES_B_03_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(found).extracting(r -> {
            final var reason = (SalesReason) r[0];
            return row(reason.getReasonType(), reason.getName(), r[1]);
        }).containsExactly(
                "Other | Price | 17473",
                "Promotion | On Promotion | 3515",
                "Other | Manufacturer | 1746",
                "Other | Quality | 1551",
                "Other | Other | 1395",
                "Other | Review | 1245",
                "Marketing | Television  Advertisement | 722",
                "Marketing | Demo Event | 0",
                "Marketing | Magazine Advertisement | 0",
                "Marketing | Sponsorship | 0"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT r, COUNT(hr.id_.salesOrderId) AS orders
                        FROM SalesSalesReason r
                                 LEFT JOIN SalesSalesOrderHeaderSalesReason hr ON hr.salesReason = r
                        GROUP BY r
                        ORDER BY orders DESC, r.name""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var r = query.from(SalesReason.class);
            final var hr = r.join(SalesOrderHeaderSalesReason.class, JoinType.LEFT);
            hr.on(builder.equal(hr.get(SalesOrderHeaderSalesReason_.salesReason), r));
            final var orders = builder.count(
                    hr.get(SalesOrderHeaderSalesReason_.id_)
                            .get(SalesOrderHeaderSalesReasonId_.salesOrderId));
            query.multiselect(r, orders);
            query.groupBy(r);
            query.orderBy(builder.desc(orders), builder.asc(r.get(SalesReason_.name)));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
