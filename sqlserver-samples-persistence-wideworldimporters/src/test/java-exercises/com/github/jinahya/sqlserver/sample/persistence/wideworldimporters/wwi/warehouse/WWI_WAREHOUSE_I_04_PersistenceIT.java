package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.warehouse;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemTransaction_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_WAREHOUSE_I_04} of
 * {@code src/test/sql-exercises/_Warehouse/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("WWI_WAREHOUSE_I_04: Incoming and outgoing stock movements")
class WWI_WAREHOUSE_I_04_PersistenceIT {

    private static final LocalDateTime FROM = LocalDate.of(2016, 5, 1).atStartOfDay();

    private static final LocalDateTime TO = LocalDate.of(2016, 6, 1).atStartOfDay();

    private static String row(final Object[] r) {
        final var si = (StockItem) r[0];
        return si.getStockItemId() + "," + si.getStockItemName() + ","
               + decimalOf(r[1]).stripTrailingZeros().toPlainString() + ","
               + decimalOf(r[2]).stripTrailingZeros().toPlainString() + ","
               + decimalOf(r[3]).stripTrailingZeros().toPlainString();
    }

    private void verify(final List<Object[]> found) {
        assertThat(found).hasSize(227);
        assertThat(found.subList(0, 3)).extracting(WWI_WAREHOUSE_I_04_PersistenceIT::row)
                .containsExactly(
                        "97,\"The Gu\" red shirt XML tag t-shirt (Black) 3XL,0,1584,-1584",
                        "89,\"The Gu\" red shirt XML tag t-shirt (Black) 3XS,0,1656,-1656",
                        "98,\"The Gu\" red shirt XML tag t-shirt (Black) 4XL,969240,588,968652"
                );
        assertThat(found.subList(found.size() - 3, found.size()))
                .extracting(WWI_WAREHOUSE_I_04_PersistenceIT::row)
                .containsExactly(
                        "219,Void fill 400 L bag (White) 400L,0,2070,-2070",
                        "227,White chocolate moon rocks 250g,0,3576,-3576",
                        "226,White chocolate snow balls 250g,0,3408,-3408"
                );
    }

    @Test
    void __QueryLanguage() {
        verify(applyInTransactionAndRollback(entityManager -> entityManager.createQuery(
                        """
                                SELECT si,
                                       SUM(CASE WHEN st.quantity > 0 THEN st.quantity ELSE 0 END)
                                           AS incoming,
                                       SUM(CASE WHEN st.quantity < 0 THEN -st.quantity ELSE 0 END)
                                           AS outgoing,
                                       SUM(st.quantity) AS netMovement
                                FROM WarehouseStockItemTransaction st
                                         JOIN st.stockItem si
                                WHERE st.transactionOccurredWhen >= :from
                                  AND st.transactionOccurredWhen < :to
                                GROUP BY si
                                ORDER BY si.stockItemName""",
                        Object[].class)
                .setParameter("from", FROM)
                .setParameter("to", TO)
                .getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var st = query.from(StockItemTransaction.class);
            final var si = st.join(StockItemTransaction_.stockItem);
            final var quantity = st.get(StockItemTransaction_.quantity);
            final var occurredWhen = st.get(StockItemTransaction_.transactionOccurredWhen);
            final var incoming = builder.<BigDecimal>selectCase()
                    .when(builder.gt(quantity, BigDecimal.ZERO), quantity)
                    .otherwise(BigDecimal.ZERO);
            final var outgoing = builder.<BigDecimal>selectCase()
                    .when(builder.lt(quantity, BigDecimal.ZERO), builder.neg(quantity))
                    .otherwise(BigDecimal.ZERO);
            query.multiselect(si, builder.sum(incoming), builder.sum(outgoing),
                              builder.sum(quantity))
                    .where(builder.greaterThanOrEqualTo(occurredWhen, FROM),
                           builder.lessThan(occurredWhen, TO))
                    .groupBy(si)
                    .orderBy(builder.asc(si.get(StockItem_.stockItemName)));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
