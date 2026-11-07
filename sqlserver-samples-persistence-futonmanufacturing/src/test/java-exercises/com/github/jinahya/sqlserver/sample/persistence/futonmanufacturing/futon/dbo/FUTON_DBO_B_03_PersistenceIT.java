package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Warehouse_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_03} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_03: the finished goods in the main warehouse, the fewest first")
class FUTON_DBO_B_03_PersistenceIT {

    private void verify(final List<Inventory> list) {
        assertThat(list).extracting(v -> v.getItem().getItemCode())
                .containsExactly("FG-FUT-006", "FG-FUT-004", "FG-FUT-003", "FG-FUT-005", "FG-FUT-002",
                                 "FG-FUT-001");
        assertThat(list).extracting(v -> v.getQuantityAvailable().intValue())
                .containsExactly(7, 8, 12, 14, 15, 18);
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT v
                                                               FROM DboInventory v
                                                               WHERE v.item.itemType.typeCode = :typeCode
                                                                 AND v.warehouse.warehouseCode = :warehouseCode
                                                               ORDER BY v.quantityAvailable, v.item.itemCode""",
                                                       Inventory.class)
                    .setParameter("typeCode", "FG")
                    .setParameter("warehouseCode", "WH-MAIN")
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Inventory.class);
            final var v = query.from(Inventory.class);
            final var item = v.join(Inventory_.item);
            query.select(v).where(
                    builder.equal(item.get(Item_.itemType).get(ItemType_.typeCode), "FG"),
                    builder.equal(v.get(Inventory_.warehouse).get(Warehouse_.warehouseCode), "WH-MAIN")
            );
            query.orderBy(builder.asc(v.get(Inventory_.quantityAvailable)),
                          builder.asc(item.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
