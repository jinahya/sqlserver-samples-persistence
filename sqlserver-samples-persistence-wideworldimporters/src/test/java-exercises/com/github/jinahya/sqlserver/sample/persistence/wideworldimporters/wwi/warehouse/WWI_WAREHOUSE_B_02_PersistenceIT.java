package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.warehouse;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_WAREHOUSE_B_02} of {@code src/test/sql-exercises/_Warehouse/01_basic.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_WAREHOUSE_B_02: The chiller stock items, by name")
class WWI_WAREHOUSE_B_02_PersistenceIT {

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
