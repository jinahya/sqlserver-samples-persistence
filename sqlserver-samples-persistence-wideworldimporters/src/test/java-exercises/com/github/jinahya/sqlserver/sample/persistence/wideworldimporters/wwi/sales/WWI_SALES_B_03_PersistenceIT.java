package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_B_03} of {@code src/test/sql-exercises/_Sales/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName(
        "WWI_SALES_B_03: The first five customers of no buying group, from the Website.Customers " +
        "view")
class WWI_SALES_B_03_PersistenceIT {

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
