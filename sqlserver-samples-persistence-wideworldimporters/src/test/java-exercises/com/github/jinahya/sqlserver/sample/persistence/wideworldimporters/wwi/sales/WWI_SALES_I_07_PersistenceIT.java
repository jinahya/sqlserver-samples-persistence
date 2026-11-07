package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.CustomerTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.CustomerTransaction_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer_;
import jakarta.persistence.criteria.Nulls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_I_07} of {@code src/test/sql-exercises/_Sales/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 *
 * <p>The customer is selected, not grouped by: {@code GROUP BY c} lists every column of
 * {@code Sales.Customers}, and SQL Server does not group by its {@code geography} column. A
 * subquery sums the outstanding balance instead; a customer with no transaction not finalized has
 * none, and its {@code NULL} share sorts last. The share is not cast to {@code decimal(10, 1)},
 * which JPQL cannot write; it orders the rows the same.
 */
@DisplayName("WWI_SALES_I_07: The ten customers using the most of their credit limit")
class WWI_SALES_I_07_PersistenceIT {

    private static String row(final Object[] r) {
        final var c = (Customer) r[0];
        return c.getCustomerId() + "," + c.getCustomerName() + "," + c.getCreditLimit() + ","
               + decimalOf(r[1]).setScale(2, RoundingMode.HALF_UP) + ","
               + decimalOf(r[2]).setScale(1, RoundingMode.HALF_UP);
    }

    private void verify(final List<Object[]> found) {
        assertThat(found).extracting(WWI_SALES_I_07_PersistenceIT::row).containsExactly(
                "995,Kumar Naicker,1100.00,13212.12,1201.1",
                "1015,Isa Hulsegge,1500.00,4713.85,314.3",
                "840,Rodrigo Figueiredo,2000.00,5975.40,298.8",
                "915,Ana Florea,1984.50,5511.15,277.7",
                "822,Suparna Bhattacharya,1500.00,4127.93,275.2",
                "900,Lilli Sokk,2300.00,5689.31,247.4",
                "813,Shyam Poddar,2600.00,5767.83,221.8",
                "856,Satish Mittal,2400.00,5157.75,214.9",
                "953,Linh Dao,3200.00,6728.65,210.3",
                "937,Manca Hrastovsek,1155.00,2217.33,192.0"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(applyInTransactionAndRollback(entityManager -> entityManager.createQuery(
                        """
                                SELECT c,
                                       (SELECT SUM(ct.outstandingBalance)
                                        FROM SalesCustomerTransaction ct
                                        WHERE ct.customer = c
                                          AND ct.isFinalized = false) AS outstanding,
                                       (SELECT SUM(ct.outstandingBalance) * 100 / c.creditLimit
                                        FROM SalesCustomerTransaction ct
                                        WHERE ct.customer = c
                                          AND ct.isFinalized = false) AS percentUsed
                                FROM SalesCustomer c
                                WHERE c.creditLimit > 0
                                ORDER BY percentUsed DESC NULLS LAST, c.customerId""",
                        Object[].class)
                .setMaxResults(10)
                .getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var c = query.from(Customer.class);
            final var outstanding = query.subquery(BigDecimal.class);
            final var ct = outstanding.from(CustomerTransaction.class);
            outstanding.select(builder.sum(ct.get(CustomerTransaction_.outstandingBalance)))
                    .where(builder.equal(ct.get(CustomerTransaction_.customer), c),
                           builder.isFalse(ct.get(CustomerTransaction_.isFinalized)));
            final var creditLimit = c.get(Customer_.creditLimit);
            final var percentUsed = builder.quot(builder.prod(outstanding, BigDecimal.valueOf(100)),
                                                 creditLimit);
            query.multiselect(c, outstanding, percentUsed)
                    .where(builder.gt(creditLimit, BigDecimal.ZERO))
                    .orderBy(builder.desc(percentUsed, Nulls.LAST),
                             builder.asc(c.get(Customer_.customerId)));
            return entityManager.createQuery(query).setMaxResults(10).getResultList();
        }));
    }

}
