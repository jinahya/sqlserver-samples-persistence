package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.InventoryTransaction;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.InventoryTransaction_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_06} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_06: an item's inventory transactions in a month")
class FUTON_DBO_B_06_PersistenceIT {

    private static final String ITEM_CODE = "RM-FILL-001";

    private static final LocalDateTime FROM = LocalDate.of(2026, 11, 1).atStartOfDay();

    private static final LocalDateTime TO = LocalDate.of(2026, 12, 1).atStartOfDay();

    // the installed database has no inventory transaction at all
    private void verify(final List<InventoryTransaction> list) {
        assertThat(list).isEmpty();
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery(
                            """
                                    SELECT it
                                    FROM DboInventoryTransaction it
                                             JOIN it.item i
                                    WHERE i.itemCode = :itemCode
                                      AND it.transactionDate >= :from
                                      AND it.transactionDate < :to
                                    ORDER BY it.transactionDate, it.transactionId""",
                            InventoryTransaction.class)
                    .setParameter("itemCode", ITEM_CODE)
                    .setParameter("from", FROM)
                    .setParameter("to", TO)
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(InventoryTransaction.class);
            final var it = query.from(InventoryTransaction.class);
            final var i = it.join(InventoryTransaction_.item);
            final var transactionDate = it.get(InventoryTransaction_.transactionDate);
            query.select(it)
                    .where(builder.equal(i.get(Item_.itemCode), ITEM_CODE),
                           builder.greaterThanOrEqualTo(transactionDate, FROM),
                           builder.lessThan(transactionDate, TO))
                    .orderBy(builder.asc(transactionDate),
                             builder.asc(it.get(InventoryTransaction_.transactionId)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
