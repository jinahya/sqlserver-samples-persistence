package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.warehouse;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemHolding;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemHolding_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_WAREHOUSE_B_01} of {@code src/test/sql-exercises/_Warehouse/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_WAREHOUSE_B_01: The stock items below their reorder level")
class WWI_WAREHOUSE_B_01_PersistenceIT {

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
                        .stream().map(WWI_WAREHOUSE_B_01_PersistenceIT::row).toList());
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
                    .stream().map(WWI_WAREHOUSE_B_01_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
