package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Warehouse_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_A_03} of {@code src/test/sql-exercises/_dbo/03_advanced.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_A_03: how many of each futon the components in stock can build")
class FUTON_DBO_A_03_PersistenceIT {

    private void verify(final List<Object[]> list) {
        assertThat(list).extracting(r -> ((Item) r[0]).getItemCode())
                .containsExactly("FG-FUT-001", "FG-FUT-002", "FG-FUT-005", "FG-FUT-003", "FG-FUT-004",
                                 "FG-FUT-006");
        assertThat(list).extracting(r -> ((Number) r[1]).intValue())
                .containsExactly(35, 30, 30, 25, 22, 22);
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT p, MIN(FLOOR(v.quantityAvailable / b.quantity)) AS buildable
                                                               FROM DboBillOfMaterials b
                                                                    JOIN b.parentItem p
                                                                    JOIN DboInventory v ON v.item = b.componentItem
                                                               WHERE p.itemType.typeCode = :typeCode
                                                                 AND b.isActive = TRUE
                                                                 AND v.warehouse.warehouseCode = :warehouseCode
                                                               GROUP BY p
                                                               ORDER BY buildable DESC, p.itemCode""",
                                                       Object[].class)
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
            final var query = builder.createQuery(Object[].class);
            final var b = query.from(BillOfMaterials.class);
            final var p = b.join(BillOfMaterials_.parentItem);
            final var v = b.join(Inventory.class);
            v.on(builder.equal(v.get(Inventory_.item), b.get(BillOfMaterials_.componentItem)));
            final var buildable = builder.min(builder.floor(
                    builder.quot(v.get(Inventory_.quantityAvailable), b.get(BillOfMaterials_.quantity))));
            query.select(builder.array(p, buildable))
                    .where(builder.equal(p.get(Item_.itemType).get(ItemType_.typeCode), "FG"),
                           builder.isTrue(b.get(BillOfMaterials_.isActive)),
                           builder.equal(v.get(Inventory_.warehouse).get(Warehouse_.warehouseCode), "WH-MAIN"))
                    .groupBy(p)
                    .orderBy(builder.desc(buildable), builder.asc(p.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
