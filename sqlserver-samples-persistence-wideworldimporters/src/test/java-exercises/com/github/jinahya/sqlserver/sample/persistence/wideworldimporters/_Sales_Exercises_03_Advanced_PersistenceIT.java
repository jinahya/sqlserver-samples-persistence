package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Sales/03_advanced.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Sales_Exercises_03_Advanced_PersistenceIT {

    @Nested
    class WWI_SALES_A_02_Test {

        private static final List<String> EXPECTED = List.of(
                "802,2800.00,2940.00", "818,1100.00,1155.00", "835,3000.00,3150.00",
                "838,1800.00,1890.00",
                "898,3900.00,4095.00", "911,3200.00,3360.00", "913,2900.00,3045.00",
                "915,1890.00,1984.50",
                "944,3800.00,3990.00", "971,3000.00,3150.00", "976,3675.00,3858.75",
                "978,3300.00,3465.00",
                "1017,2600.00,2730.00", "1020,2500.00,2625.00", "1034,3700.00,3885.00"
        );

        @Test
        void __NativeQuery() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final List<?> rows = entityManager.createNativeQuery("""
                                                                             SELECT c.CustomerID, c.CustomerName, h.CreditLimit, c.CreditLimit
                                                                             FROM Sales.Customers c
                                                                                      JOIN Sales.Customers FOR SYSTEM_TIME AS OF ?1 h ON h.CustomerID = c.CustomerID
                                                                             WHERE h.CreditLimit <> c.CreditLimit
                                                                             ORDER BY c.CustomerID""")
                        .setParameter(1, LocalDateTime.of(2015, 1, 1, 0, 0))
                        .getResultList();
                return rows.stream()
                        .map(r -> (Object[]) r)
                        .map(r -> longOf(r[0]) + "," + decimalOf(r[2]) + "," + decimalOf(r[3]))
                        .toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    @Nested
    class WWI_SALES_A_03_Test {

        private static final List<String> EXPECTED = List.of(
                "694,Wingtip Toys (Lost River, ID),684",
                "858,Tailspin Toys (Magalia, CA),768",
                "863,Wingtip Toys (Deeson, MS),789",
                "865,Richard Friar,807",
                "1065,Tailspin Toys (New Baden, IL),1063"
        );

        private static String row(final Order original) {
            final var backorder = original.getBackorderOrder();
            return backorder.getOrderId() + "," + backorder.getCustomer().getCustomerName() + ","
                   + original.getOrderId();
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT p
                                                                                                     FROM SalesOrder AS p
                                                                                                          JOIN p.backorderOrder AS o
                                                                                                     WHERE NOT EXISTS (SELECT i
                                                                                                                       FROM SalesInvoice AS i
                                                                                                                       WHERE i.order = o)
                                                                                                     ORDER BY o.orderDate, o.orderId""",
                                                                                             Order.class)
                            .setMaxResults(5)
                            .getResultList()
                            .stream().map(WWI_SALES_A_03_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Order.class);
                final var root = query.from(Order.class);
                final var backorder = root.join(Order_.backorderOrder);
                final var subquery = query.subquery(Invoice.class);
                final var invoice = subquery.from(Invoice.class);
                subquery.select(invoice)
                        .where(builder.equal(invoice.get(Invoice_.order), backorder));
                query.select(root);
                query.where(builder.not(builder.exists(subquery)));
                query.orderBy(builder.asc(backorder.get(Order_.orderDate)),
                              builder.asc(backorder.get(Order_.orderId)));
                return entityManager.createQuery(query).setMaxResults(5).getResultList()
                        .stream().map(WWI_SALES_A_03_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    @DisplayName("WWI_SALES_A_01: The top three salespeople of each year")
    @Nested
    class WWI_SALES_A_01_Test {
    }
}
