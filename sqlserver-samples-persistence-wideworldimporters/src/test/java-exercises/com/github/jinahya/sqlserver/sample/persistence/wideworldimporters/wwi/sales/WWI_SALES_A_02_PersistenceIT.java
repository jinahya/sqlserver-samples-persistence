package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_A_02} of {@code src/test/sql-exercises/_Sales/03_advanced.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName(
        "WWI_SALES_A_02: The customers whose credit limit changed since a moment, by a native " +
        "query")
class WWI_SALES_A_02_PersistenceIT {

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
                                                                         WHERE NOT EXISTS (SELECT h.CreditLimit
                                                                                           INTERSECT
                                                                                           SELECT c.CreditLimit)
                                                                         ORDER BY c.CustomerID""")
                    .setParameter(1, LocalDateTime.of(2015, 1, 1, 0, 0))
                    .getResultList();
            return rows.stream()
                    .map(r -> (Object[]) r)
                    .map(r -> longOf(r[0]) + "," + creditLimitOf(r[2]) + "," + creditLimitOf(r[3]))
                    .toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

    // a limit set or removed since then is NULL on one side
    private static String creditLimitOf(final Object value) {
        return Optional.ofNullable(value).map(v -> decimalOf(v).toPlainString()).orElse("NULL");
    }

}
