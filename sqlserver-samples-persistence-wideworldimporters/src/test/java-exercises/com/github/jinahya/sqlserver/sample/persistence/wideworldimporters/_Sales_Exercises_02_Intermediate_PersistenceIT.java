package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.InvoiceLine_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.InvoiceLine;
import jakarta.persistence.criteria.LocalDateField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.FROM_2015;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.TO_2015;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Sales/02_intermediate.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Sales_Exercises_02_Intermediate_PersistenceIT {

    // ------------------------------------------------------------------------------ WWI_SALES_I_01
    @DisplayName("WWI_SALES_I_01: the five stock items which earned the most in a year")
    @Nested
    class WWI_SALES_I_01_Test {

        private static final List<String> EXPECTED = List.of(
                "215,1747,3815185.95",
                "161,19380,2406996.00",
                "173,19320,2332890.00",
                "170,19740,2315502.00",
                "167,20320,2313432.00"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[2]) + "," + decimalOf(row[3]).setScale(2);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT si.stockItemId, si.stockItemName, SUM(il.quantity), SUM(il.extendedPrice) AS revenue
                                                                                                     FROM SalesInvoiceLine AS il
                                                                                                          JOIN il.invoice AS i
                                                                                                          JOIN il.stockItem AS si
                                                                                                     WHERE i.invoiceDate >= :from
                                                                                                       AND i.invoiceDate < :to
                                                                                                     GROUP BY si.stockItemId, si.stockItemName
                                                                                                     ORDER BY revenue DESC""",
                                                                                             Object[].class)
                            .setParameter("from", FROM_2015)
                            .setParameter("to", TO_2015)
                            .setMaxResults(5)
                            .getResultList()
                            .stream().map(WWI_SALES_I_01_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(InvoiceLine.class);
                final var invoice = root.join(InvoiceLine_.invoice);
                final var item = root.join(InvoiceLine_.stockItem);
                final var revenue = builder.sum(root.get(InvoiceLine_.extendedPrice));
                query.multiselect(item.get(StockItem_.stockItemId),
                                  item.get(StockItem_.stockItemName),
                                  builder.sum(root.get(InvoiceLine_.quantity)), revenue);
                query.where(
                        builder.greaterThanOrEqualTo(invoice.get(Invoice_.invoiceDate), FROM_2015),
                        builder.lessThan(invoice.get(Invoice_.invoiceDate), TO_2015));
                query.groupBy(item.get(StockItem_.stockItemId), item.get(StockItem_.stockItemName));
                query.orderBy(builder.desc(revenue));
                return entityManager.createQuery(query).setMaxResults(5).getResultList()
                        .stream().map(WWI_SALES_I_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // ------------------------------------------------------------------------------ WWI_SALES_I_02
    @DisplayName("WWI_SALES_I_02: the sales of a year by month")
    @Nested
    class WWI_SALES_I_02_Test {

        private static final List<String> EXPECTED = List.of(
                "1,1749,5061954.63", "2,1661,4824617.67", "3,1821,5207351.93", "4,2020,5834255.09",
                "5,1857,5152840.72", "6,1878,5193217.15", "7,2166,5929023.38", "8,1642,4528888.44",
                "9,1970,5361990.68", "10,1880,5165857.34", "11,1743,4702590.28",
                "12,1863,5127633.50"
        );

        private static String row(final Object[] row) {
            return longOf(row[0]) + "," + longOf(row[1]) + "," + decimalOf(row[2]).setScale(2);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT EXTRACT(MONTH FROM i.invoiceDate) AS invoiceMonth,
                                                                                                            COUNT(DISTINCT i),
                                                                                                            SUM(il.extendedPrice)
                                                                                                     FROM SalesInvoiceLine AS il
                                                                                                          JOIN il.invoice AS i
                                                                                                     WHERE i.invoiceDate >= :from
                                                                                                       AND i.invoiceDate < :to
                                                                                                     GROUP BY EXTRACT(MONTH FROM i.invoiceDate)
                                                                                                     ORDER BY invoiceMonth""",
                                                                                             Object[].class)
                            .setParameter("from", FROM_2015)
                            .setParameter("to", TO_2015)
                            .getResultList()
                            .stream().map(WWI_SALES_I_02_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(InvoiceLine.class);
                final var invoice = root.join(InvoiceLine_.invoice);
                final var month =
                        builder.extract(LocalDateField.MONTH, invoice.get(Invoice_.invoiceDate));
                query.multiselect(month, builder.countDistinct(invoice),
                                  builder.sum(root.get(InvoiceLine_.extendedPrice)));
                query.where(
                        builder.greaterThanOrEqualTo(invoice.get(Invoice_.invoiceDate), FROM_2015),
                        builder.lessThan(invoice.get(Invoice_.invoiceDate), TO_2015));
                query.groupBy(month);
                query.orderBy(builder.asc(month));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_SALES_I_02_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // ------------------------------------------------------------------------------ WWI_SALES_I_03
    @DisplayName("WWI_SALES_I_03: the confirmed deliveries of a customer since a date")
    @Nested
    class WWI_SALES_I_03_Test {

        private static final int CUSTOMER_ID = 1;

        private static final LocalDate FROM = LocalDate.of(2016, 4, 1);

        private static final List<String> EXPECTED = List.of(
                "68177,2016-04-27T09:30,Waldemar Fisar",
                "68338,2016-04-29T08:45,Waldemar Fisar",
                "70232,2016-05-28T07:50,Waldemar Fisar"
        );

        private static String row(final Invoice invoice) {
            final LocalDateTime confirmed = invoice.getConfirmedDeliveryTime();
            return invoice.getInvoiceId() + "," + confirmed + "," +
                   invoice.getConfirmedReceivedBy();
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT i
                                                                                                     FROM SalesInvoice AS i
                                                                                                     WHERE i.customerId = :customerId
                                                                                                       AND i.invoiceDate >= :from
                                                                                                     ORDER BY i.invoiceId""",
                                                                                             Invoice.class)
                            .setParameter("customerId", CUSTOMER_ID)
                            .setParameter("from", FROM)
                            .getResultList()
                            .stream().map(WWI_SALES_I_03_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Invoice.class);
                final var root = query.from(Invoice.class);
                query.select(root);
                query.where(builder.equal(root.get(Invoice_.customerId), CUSTOMER_ID),
                            builder.greaterThanOrEqualTo(root.get(Invoice_.invoiceDate), FROM));
                query.orderBy(builder.asc(root.get(Invoice_.invoiceId)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_SALES_I_03_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    @DisplayName("WWI_SALES_I_04: The history of a customer's credit limit")
    @Nested
    class WWI_SALES_I_04_Test {
    }

    @DisplayName("WWI_SALES_I_05: The special deals in force on a date")
    @Nested
    class WWI_SALES_I_05_Test {
    }

    @DisplayName("WWI_SALES_I_06: The special deals that apply to a customer and an item")
    @Nested
    class WWI_SALES_I_06_Test {
    }

    @DisplayName("WWI_SALES_I_07: The ten customers using the most of their credit limit")
    @Nested
    class WWI_SALES_I_07_Test {
    }
}
