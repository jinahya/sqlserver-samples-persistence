package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemStockGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemStockGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Warehouse/02_intermediate.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Warehouse_Exercises_02_Intermediate_PersistenceIT {

    // -------------------------------------------------------------------------- WWI_WAREHOUSE_I_01
    @DisplayName("WWI_WAREHOUSE_I_01: the stock items of each stock group")
    @Nested
    class WWI_WAREHOUSE_I_01_Test {

        private static final List<String> EXPECTED = List.of(
                "Novelty Items,91", "Computing Novelties,83", "Clothing,74",
                "Packaging Materials,67", "Mugs,42",
                "T-Shirts,26", "Furry Footwear,24", "Toys,21", "USB Novelties,14"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[1]);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT sg.stockGroupName, COUNT(sisg) AS stockItems
                                                                                                     FROM WarehouseStockItemStockGroup AS sisg
                                                                                                          JOIN sisg.stockGroup AS sg
                                                                                                     GROUP BY sg.stockGroupId, sg.stockGroupName
                                                                                                     ORDER BY stockItems DESC, sg.stockGroupName""",
                                                                                             Object[].class)
                            .getResultList()
                            .stream().map(WWI_WAREHOUSE_I_01_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(StockItemStockGroup.class);
                final var group = root.join(StockItemStockGroup_.stockGroup);
                final var name = group.get(StockGroup_.stockGroupName);
                final var stockItems = builder.count(root);
                query.multiselect(name, stockItems);
                query.groupBy(group.get(StockGroup_.stockGroupId), name);
                query.orderBy(builder.desc(stockItems), builder.asc(name));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_WAREHOUSE_I_01_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    @DisplayName("WWI_WAREHOUSE_I_02: The stock items by country of manufacture, from the CustomFields JSON")
    @Nested
    class WWI_WAREHOUSE_I_02_Test {
    }

    @DisplayName("WWI_WAREHOUSE_I_03: The movements of a stock item in a year, by transaction type")
    @Nested
    class WWI_WAREHOUSE_I_03_Test {
    }

    @DisplayName("WWI_WAREHOUSE_I_04: Incoming and outgoing stock movements")
    @Nested
    class WWI_WAREHOUSE_I_04_Test {
    }
}
