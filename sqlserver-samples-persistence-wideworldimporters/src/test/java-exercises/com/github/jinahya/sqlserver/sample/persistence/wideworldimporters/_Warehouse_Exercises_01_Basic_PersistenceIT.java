package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemHolding_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemHolding;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Warehouse/01_basic.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Warehouse_Exercises_01_Basic_PersistenceIT {

    // -------------------------------------------------------------------------- WWI_WAREHOUSE_B_01
    @DisplayName("WWI_WAREHOUSE_B_01: the stock items below their reorder level")
    @Nested
    class WWI_WAREHOUSE_B_01_Test {

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
                            .stream().map(WWI_WAREHOUSE_B_01_Test::row).toList());
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
                        .stream().map(WWI_WAREHOUSE_B_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    // -------------------------------------------------------------------------- WWI_WAREHOUSE_B_02
    @DisplayName("WWI_WAREHOUSE_B_02: the chiller stock items, by name")
    @Nested
    class WWI_WAREHOUSE_B_02_Test {

        private static final List<Integer> EXPECTED =
                List.of(222, 223, 224, 225, 220, 221, 227, 226);

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT si
                                                                                                     FROM WarehouseStockItem AS si
                                                                                                     WHERE si.isChillerStock = true
                                                                                                     ORDER BY si.stockItemName""",
                                                                                             StockItem.class)
                            .getResultList()
                            .stream().map(StockItem::getStockItemId).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(StockItem.class);
                final var root = query.from(StockItem.class);
                query.select(root);
                query.where(builder.isTrue(root.get(StockItem_.isChillerStock)));
                query.orderBy(builder.asc(root.get(StockItem_.stockItemName)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(StockItem::getStockItemId).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    @DisplayName("WWI_WAREHOUSE_B_03: The latest vehicle temperature readings")
    @Nested
    class WWI_WAREHOUSE_B_03_Test {
    }
}
