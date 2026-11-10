package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.warehouse;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.TransactionType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.TransactionType_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemTransaction_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.FROM_2015;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.TO_2015;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_WAREHOUSE_I_03} of
 * {@code src/test/sql-exercises/_Warehouse/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("WWI_WAREHOUSE_I_03: The movements of a stock item in a year, by transaction type")
class WWI_WAREHOUSE_I_03_PersistenceIT {

    private static final int STOCK_ITEM_ID = 1;

    private static final LocalDateTime FROM = FROM_2015.atStartOfDay();

    private static final LocalDateTime TO = TO_2015.atStartOfDay();

    private static String row(final Object[] r) {
        return ((TransactionType) r[0]).getTransactionTypeName() + "," + longOf(r[1]) + ","
               + decimalOf(r[2]).stripTrailingZeros().toPlainString();
    }

    private void verify(final List<Object[]> found) {
        assertThat(found).extracting(WWI_WAREHOUSE_I_03_PersistenceIT::row).containsExactly(
                "Stock Issue,323,-1762"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(applyInTransactionAndRollback(entityManager -> entityManager.createQuery(
                        """
                                SELECT tt, COUNT(sit) AS transactions, SUM(sit.quantity) AS quantity
                                FROM WarehouseStockItemTransaction sit
                                         JOIN sit.transactionType tt
                                WHERE sit.stockItemId = :stockItemId
                                  AND sit.transactionOccurredWhen >= :from
                                  AND sit.transactionOccurredWhen < :to
                                GROUP BY tt
                                ORDER BY tt.transactionTypeName""",
                        Object[].class)
                .setParameter("stockItemId", STOCK_ITEM_ID)
                .setParameter("from", FROM)
                .setParameter("to", TO)
                .getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var sit = query.from(StockItemTransaction.class);
            final var tt = sit.join(StockItemTransaction_.transactionType);
            final var occurredWhen = sit.get(StockItemTransaction_.transactionOccurredWhen);
            query.select(builder.array(tt, builder.count(sit),
                                       builder.sum(sit.get(StockItemTransaction_.quantity))))
                    .where(builder.equal(sit.get(StockItemTransaction_.stockItemId), STOCK_ITEM_ID),
                           builder.greaterThanOrEqualTo(occurredWhen, FROM),
                           builder.lessThan(occurredWhen, TO))
                    .groupBy(tt)
                    .orderBy(builder.asc(tt.get(TransactionType_.transactionTypeName)));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
