package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.BuyingGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer_;
import jakarta.persistence.criteria.JoinType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_B_01} of {@code src/test/sql-exercises/_Sales/01_basic.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_SALES_B_01: The customers of each buying group")
class WWI_SALES_B_01_PersistenceIT {

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
                        .stream().map(WWI_SALES_B_01_PersistenceIT::row).toList());
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
                    .stream().map(WWI_SALES_B_01_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
