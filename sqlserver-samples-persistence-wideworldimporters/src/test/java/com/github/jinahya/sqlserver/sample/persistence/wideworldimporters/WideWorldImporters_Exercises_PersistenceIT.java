package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Country_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrderLine_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrder_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierCategory_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierTransaction_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.BuyingGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.InvoiceLine_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemHolding_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemStockGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Country;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrderLine;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.InvoiceLine;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemHolding;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemStockGroup;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.LocalDateField;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.___Persistence_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * The exercises of {@code EXERCISES.asciidoc} which the entities can answer, each in the Jakarta
 * Persistence query language and with the Criteria API, against the
 * {@value ___Persistence_TestUtils#PERSISTENCE_UNIT_NAME} unit.
 * <p>
 * Each nested class is an exercise, named after its identifier; each asserts the rows the
 * exercise's SQL, in {@code src/test/sql/exercises}, returns from the installed database. The
 * exercises which only SQL can answer are not here, but one: {@code WWI_SALES_A_02}, read by a
 * native query.
 */
@DisplayName("WideWorldImporters exercises")
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class WideWorldImporters_Exercises_PersistenceIT {

    private static final LocalDate FROM_2015 = LocalDate.of(2015, 1, 1);

    private static final LocalDate TO_2015 = LocalDate.of(2016, 1, 1);

    private static long longOf(final Object value) {
        return ((Number) value).longValue();
    }

    private static BigDecimal decimalOf(final Object value) {
        return new BigDecimal(value.toString());
    }

    // -------------------------------------------------------------------------------------------- WWI_APPLICATION_B_01
    @DisplayName("WWI_APPLICATION_B_01: the salespeople, by name")
    @Nested
    class WWI_APPLICATION_B_01_Test {

        private static final List<Integer> EXPECTED = List.of(7, 8, 16, 13, 3, 20, 2, 14, 6, 15);

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT p
                                                                                                     FROM ApplicationPerson AS p
                                                                                                     WHERE p.isSalesperson = true
                                                                                                     ORDER BY p.fullName""",
                                                                                             Person.class)
                            .getResultList()
                            .stream().map(Person::getPersonId).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Person.class);
                final var root = query.from(Person.class);
                query.select(root);
                query.where(builder.isTrue(root.get(Person_.isSalesperson)));
                query.orderBy(builder.asc(root.get(Person_.fullName)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(Person::getPersonId).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // -------------------------------------------------------------------------------------------- WWI_APPLICATION_I_01
    @DisplayName("WWI_APPLICATION_I_01: the countries and the population of each continent")
    @Nested
    class WWI_APPLICATION_I_01_Test {

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
                            .stream().map(WWI_APPLICATION_I_01_Test::row).toList());
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
                        .stream().map(WWI_APPLICATION_I_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // --------------------------------------------------------------------------------------------- WWI_PURCHASING_B_01
    @DisplayName("WWI_PURCHASING_B_01: the suppliers, by category and then by name")
    @Nested
    class WWI_PURCHASING_B_01_Test {

        private static final List<Integer> EXPECTED =
                List.of(4, 3, 13, 6, 11, 1, 2, 5, 8, 9, 12, 7, 10);

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT s
                                                                                                     FROM PurchasingSupplier AS s
                                                                                                          JOIN s.supplierCategory AS sc
                                                                                                     ORDER BY sc.supplierCategoryName, s.supplierName""",
                                                                                             Supplier.class)
                            .getResultList()
                            .stream().map(Supplier::getSupplierId).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Supplier.class);
                final var root = query.from(Supplier.class);
                final var category = root.join(Supplier_.supplierCategory);
                query.select(root);
                query.orderBy(builder.asc(category.get(SupplierCategory_.supplierCategoryName)),
                              builder.asc(root.get(Supplier_.supplierName)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(Supplier::getSupplierId).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // --------------------------------------------------------------------------------------------- WWI_PURCHASING_B_02
    @DisplayName("WWI_PURCHASING_B_02: what is still owed to each supplier")
    @Nested
    class WWI_PURCHASING_B_02_Test {

        private static final List<String> EXPECTED = List.of(
                "Fabrikam, Inc.,1,1871894.10",
                "Litware, Inc.,1,849784.11"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[1]) + "," + decimalOf(row[2]).setScale(2);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT s.supplierName, COUNT(st) AS transactions, SUM(st.outstandingBalance) AS outstanding
                                                                                                     FROM PurchasingSupplierTransaction AS st
                                                                                                          JOIN st.supplier AS s
                                                                                                     WHERE st.isFinalized = false
                                                                                                     GROUP BY s.supplierId, s.supplierName
                                                                                                     ORDER BY outstanding DESC""",
                                                                                             Object[].class)
                            .getResultList()
                            .stream().map(WWI_PURCHASING_B_02_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(SupplierTransaction.class);
                final var supplier = root.join(SupplierTransaction_.supplier);
                final var outstanding =
                        builder.sum(root.get(SupplierTransaction_.outstandingBalance));
                query.multiselect(supplier.get(Supplier_.supplierName), builder.count(root),
                                  outstanding);
                query.where(builder.isFalse(root.get(SupplierTransaction_.isFinalized)));
                query.groupBy(supplier.get(Supplier_.supplierId),
                              supplier.get(Supplier_.supplierName));
                query.orderBy(builder.desc(outstanding));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_PURCHASING_B_02_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // --------------------------------------------------------------------------------------------- WWI_PURCHASING_I_01
    @DisplayName(
            "WWI_PURCHASING_I_01: the purchase orders and the amount ordered from each supplier " +
            "in a year")
    @Nested
    class WWI_PURCHASING_I_01_Test {

        private static final List<String> EXPECTED = List.of(
                "Fabrikam, Inc.,309,295332354.00",
                "Litware, Inc.,301,127101914.10"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[1]) + "," + decimalOf(row[2]).setScale(2);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT s.supplierName,
                                                                                                            COUNT(DISTINCT po) AS orders,
                                                                                                            SUM(pol.orderedOuters * pol.expectedUnitPricePerOuter) AS amount
                                                                                                     FROM PurchasingPurchaseOrderLine AS pol
                                                                                                          JOIN pol.purchaseOrder AS po
                                                                                                          JOIN po.supplier AS s
                                                                                                     WHERE po.orderDate >= :from
                                                                                                       AND po.orderDate < :to
                                                                                                     GROUP BY s.supplierId, s.supplierName
                                                                                                     ORDER BY amount DESC""",
                                                                                             Object[].class)
                            .setParameter("from", FROM_2015)
                            .setParameter("to", TO_2015)
                            .getResultList()
                            .stream().map(WWI_PURCHASING_I_01_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(PurchaseOrderLine.class);
                final var order = root.join(PurchaseOrderLine_.purchaseOrder);
                final var supplier = order.join(PurchaseOrder_.supplier);
                final var amount =
                        builder.sum(builder.prod(root.get(PurchaseOrderLine_.orderedOuters),
                                                 root.get(
                                                         PurchaseOrderLine_.expectedUnitPricePerOuter)));
                query.multiselect(supplier.get(Supplier_.supplierName),
                                  builder.countDistinct(order), amount);
                query.where(builder.greaterThanOrEqualTo(order.get(PurchaseOrder_.orderDate),
                                                         FROM_2015),
                            builder.lessThan(order.get(PurchaseOrder_.orderDate), TO_2015));
                query.groupBy(supplier.get(Supplier_.supplierId),
                              supplier.get(Supplier_.supplierName));
                query.orderBy(builder.desc(amount));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_PURCHASING_I_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // -------------------------------------------------------------------------------------------------- WWI_SALES_B_01
    @DisplayName("WWI_SALES_B_01: the customers of each buying group")
    @Nested
    class WWI_SALES_B_01_Test {

        private static final List<String> EXPECTED = List.of(
                "null,261",
                "Tailspin Toys,201",
                "Wingtip Toys,201"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[1]);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT bg.buyingGroupName, COUNT(c) AS customers
                                                                                                     FROM SalesCustomer AS c
                                                                                                          LEFT JOIN c.buyingGroup AS bg
                                                                                                     GROUP BY bg.buyingGroupName
                                                                                                     ORDER BY customers DESC, bg.buyingGroupName""",
                                                                                             Object[].class)
                            .getResultList()
                            .stream().map(WWI_SALES_B_01_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(Customer.class);
                final var group = root.join(Customer_.buyingGroup, JoinType.LEFT);
                final var name = group.get(BuyingGroup_.buyingGroupName);
                final var customers = builder.count(root);
                query.multiselect(name, customers);
                query.groupBy(name);
                query.orderBy(builder.desc(customers), builder.asc(name));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_SALES_B_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // -------------------------------------------------------------------------------------------------- WWI_SALES_B_02
    @DisplayName("WWI_SALES_B_02: the customers billed for others")
    @Nested
    class WWI_SALES_B_02_Test {

        private static final List<String> EXPECTED = List.of(
                "1,Tailspin Toys (Head Office),200",
                "401,Wingtip Toys (Head Office),200"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + row[1] + "," + longOf(row[2]);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT b.customerId, b.customerName, COUNT(c) AS billed
                                                                                                     FROM SalesCustomer AS c
                                                                                                          JOIN c.billToCustomer AS b
                                                                                                     WHERE b <> c
                                                                                                     GROUP BY b.customerId, b.customerName
                                                                                                     ORDER BY billed DESC, b.customerId""",
                                                                                             Object[].class)
                            .getResultList()
                            .stream().map(WWI_SALES_B_02_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(Customer.class);
                final var billTo = root.join(Customer_.billToCustomer);
                final var billed = builder.count(root);
                query.multiselect(billTo.get(Customer_.customerId),
                                  billTo.get(Customer_.customerName), billed);
                query.where(builder.notEqual(billTo, root));
                query.groupBy(billTo.get(Customer_.customerId), billTo.get(Customer_.customerName));
                query.orderBy(builder.desc(billed), builder.asc(billTo.get(Customer_.customerId)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_SALES_B_02_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // -------------------------------------------------------------------------------------------------- WWI_SALES_B_03
    @DisplayName(
            "WWI_SALES_B_03: the first five customers of no buying group, from the Website" +
            ".Customers view")
    @Nested
    class WWI_SALES_B_03_Test {

        // Website.Customers is website.Customer, qualified here, for this class imports sales
        // .Customer
        private static final List<Integer> EXPECTED = List.of(832, 836, 869, 1048, 901);

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery(
                                    """
                                            SELECT c
                                            FROM WebsiteCustomer AS c
                                            WHERE c.buyingGroupName IS NULL
                                            ORDER BY c.customerName""",
                                    com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website.Customer.class)
                            .setMaxResults(5)
                            .getResultList()
                            .stream()
                            .map(com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website.Customer::getCustomerId)
                            .toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(
                        com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website.Customer.class);
                final var root = query.from(query.getResultType());
                query.select(root);
                query.where(builder.isNull(root.get(
                        com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website
                                .Customer_.buyingGroupName)));
                query.orderBy(builder.asc(root.get(
                        com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website
                                .Customer_.customerName)));
                return entityManager.createQuery(query).setMaxResults(5).getResultList()
                        .stream()
                        .map(com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website.Customer::getCustomerId)
                        .toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // -------------------------------------------------------------------------------------------------- WWI_SALES_I_01
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

    // -------------------------------------------------------------------------------------------------- WWI_SALES_I_02
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

    // -------------------------------------------------------------------------------------------------- WWI_SALES_I_03
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

    // -------------------------------------------------------------------------------------------------- WWI_SALES_A_02
    @DisplayName(
            "WWI_SALES_A_02: the customers whose credit limit changed since a moment, by a native" +
            " query")
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

    // -------------------------------------------------------------------------------------------------- WWI_SALES_A_03
    @DisplayName(
            "WWI_SALES_A_03: the five oldest backorders never invoiced, and the orders which " +
            "backordered them")
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

    // ---------------------------------------------------------------------------------------------- WWI_WAREHOUSE_B_01
    @DisplayName("WWI_WAREHOUSE_B_01: the stock items below their reorder level")
    @Nested
    class WWI_WAREHOUSE_B_01_Test {

        private static final List<String> EXPECTED = List.of("86,3,5", "184,38,50");

        private static String row(final StockItemHolding holding) {
            return holding.getStockItemId() + "," + holding.getQuantityOnHand() + "," +
                   holding.getReorderLevel();
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery(
                                    """
                                            SELECT h
                                            FROM WarehouseStockItemHolding AS h
                                                 JOIN h.stockItem AS si
                                            WHERE h.quantityOnHand < h.reorderLevel
                                            ORDER BY si.stockItemName""",
                                    StockItemHolding.class
                            )
                            .getResultList()
                            .stream().map(WWI_WAREHOUSE_B_01_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(StockItemHolding.class);
                final var root = query.from(StockItemHolding.class);
                final var item = root.join(StockItemHolding_.stockItem);
                query.select(root);
                query.where(builder.lessThan(root.get(StockItemHolding_.quantityOnHand),
                                             root.get(StockItemHolding_.reorderLevel)));
                query.orderBy(builder.asc(item.get(StockItem_.stockItemName)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_WAREHOUSE_B_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // ---------------------------------------------------------------------------------------------- WWI_WAREHOUSE_B_02
    @DisplayName("WWI_WAREHOUSE_B_02: the chiller stock items, by name")
    @Nested
    class WWI_WAREHOUSE_B_02_Test {

        private static final List<Integer> EXPECTED =
                List.of(222, 223, 224, 225, 220, 221, 227, 226);

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT si
                                                                                                     FROM WarehouseStockItem AS si
                                                                                                     WHERE si.isChillerStock = true
                                                                                                     ORDER BY si.stockItemName""",
                                                                                             StockItem.class)
                            .getResultList()
                            .stream().map(StockItem::getStockItemId).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(StockItem.class);
                final var root = query.from(StockItem.class);
                query.select(root);
                query.where(builder.isTrue(root.get(StockItem_.isChillerStock)));
                query.orderBy(builder.asc(root.get(StockItem_.stockItemName)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(StockItem::getStockItemId).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // ---------------------------------------------------------------------------------------------- WWI_WAREHOUSE_I_01
    @DisplayName("WWI_WAREHOUSE_I_01: the stock items of each stock group")
    @Nested
    class WWI_WAREHOUSE_I_01_Test {

        private static final List<String> EXPECTED = List.of(
                "Novelty Items,91", "Computing Novelties,83", "Clothing,74",
                "Packaging Materials,67", "Mugs,42",
                "T-Shirts,26", "Furry Footwear,24", "Toys,21", "USB Novelties,14"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[1]);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT sg.stockGroupName, COUNT(sisg) AS stockItems
                                                                                                     FROM WarehouseStockItemStockGroup AS sisg
                                                                                                          JOIN sisg.stockGroup AS sg
                                                                                                     GROUP BY sg.stockGroupId, sg.stockGroupName
                                                                                                     ORDER BY stockItems DESC, sg.stockGroupName""",
                                                                                             Object[].class)
                            .getResultList()
                            .stream().map(WWI_WAREHOUSE_I_01_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(StockItemStockGroup.class);
                final var group = root.join(StockItemStockGroup_.stockGroup);
                final var name = group.get(StockGroup_.stockGroupName);
                final var stockItems = builder.count(root);
                query.multiselect(name, stockItems);
                query.groupBy(group.get(StockGroup_.stockGroupId), name);
                query.orderBy(builder.desc(stockItems), builder.asc(name));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_WAREHOUSE_I_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }
}
