package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.BuyingGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import jakarta.persistence.criteria.JoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Sales/01_basic.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Sales_Exercises_01_Basic_PersistenceIT {

    // ------------------------------------------------------------------------------ WWI_SALES_B_01
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

    // ------------------------------------------------------------------------------ WWI_SALES_B_02
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
}
