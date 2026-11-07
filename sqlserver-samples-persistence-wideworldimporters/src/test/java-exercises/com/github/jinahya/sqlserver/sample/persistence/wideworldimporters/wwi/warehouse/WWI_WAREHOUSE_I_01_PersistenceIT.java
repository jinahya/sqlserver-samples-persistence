package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.warehouse;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockGroup_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemStockGroup;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItemStockGroup_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_WAREHOUSE_I_01} of
 * {@code src/test/sql-exercises/_Warehouse/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("WWI_WAREHOUSE_I_01: The stock items of each stock group")
class WWI_WAREHOUSE_I_01_PersistenceIT {

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
                        .stream().map(WWI_WAREHOUSE_I_01_PersistenceIT::row).toList());
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
                    .stream().map(WWI_WAREHOUSE_I_01_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
