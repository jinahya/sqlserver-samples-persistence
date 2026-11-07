package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.sales;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_SALES_B_01} of {@code src/test/sql-exercises/_Sales/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_SALES_B_01: An order, by its order number")
class AW_SALES_B_01_PersistenceIT {

    private static final String SALES_ORDER_NUMBER = "SO43659";

    private void verify(final SalesOrderHeader found) {
        assertThat(
                row(found.getSalesOrderId(), found.getSalesOrderNumber(), found.getSubTotal(),
                    found.getTaxAmt(), found.getFreight(), found.getTotalDue()))
                .isEqualTo("43659 | SO43659 | 20565.6206 | 1971.5149 | 616.0984 | 23153.2339");
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT h
                        FROM SalesSalesOrderHeader h
                        WHERE h.salesOrderNumber = :salesOrderNumber""",
                SalesOrderHeader.class
        ).setParameter("salesOrderNumber", SALES_ORDER_NUMBER).getSingleResult()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(SalesOrderHeader.class);
            final var h = query.from(SalesOrderHeader.class);
            query.select(h);
            query.where(builder.equal(h.get(SalesOrderHeader_.salesOrderNumber),
                                      SALES_ORDER_NUMBER));
            return entityManager.createQuery(query).getSingleResult();
        }));
    }

}
