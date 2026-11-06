package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.Customer_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderHeader_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Sales/01_basic.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Sales_Exercises_01_Basic_PersistenceIT {

    @DisplayName("AW_SALES_B_01: an order, by its order number")
    @Nested
    class AW_SALES_B_01_Test {

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

    @DisplayName("AW_SALES_B_02: the customers, the persons and the stores among them")
    @Nested
    class AW_SALES_B_02_Test {

        private void verify(final Object[] found) {
            assertThat(row(found)).isEqualTo("19820 | 19119 | 1336");
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT COUNT(c), COUNT(c.personId), COUNT(c.storeId)
                            FROM SalesCustomer c""",
                    Object[].class
            ).getSingleResult()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var c = query.from(Customer.class);
                query.multiselect(builder.count(c), builder.count(c.get(Customer_.personId)),
                                  builder.count(c.get(Customer_.storeId)));
                return entityManager.createQuery(query).getSingleResult();
            }));
        }
    }

    @DisplayName("AW_SALES_B_03: The orders that gave each sales reason")
    @Nested
    class AW_SALES_B_03_Test {
    }
}
