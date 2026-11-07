package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_B_02} of {@code src/test/sql-exercises/_Sales/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_SALES_B_02: The customers billed for others")
class WWI_SALES_B_02_PersistenceIT {

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
                        .stream().map(WWI_SALES_B_02_PersistenceIT::row).toList());
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
                    .stream().map(WWI_SALES_B_02_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
