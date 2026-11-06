package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Warehouse_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.WhereUsedView;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.scale4;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_dbo/03_advanced.sql}, against the installed
 * {@code FutonManufacturing} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class _Dbo_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("FUTON_DBO_A_01: the raw materials which ten of FG-FUT-003 need, a query per level")
    @Nested
    class FUTON_DBO_A_01_Test {

        /**
         * Explodes the bill of materials of an item, a level at a time, with the specified function, which reads the
         * active rows whose parent is one of the specified items.
         */
        private Map<String, BigDecimal> explode(
                final EntityManager entityManager, final String itemCode, final BigDecimal quantity,
                final BiFunction<EntityManager, Collection<Integer>, List<BillOfMaterials>> children) {
            final var item = entityManager.createQuery(
                            "SELECT i FROM DboItem i WHERE i.itemCode = :itemCode", Item.class)
                    .setParameter("itemCode", itemCode)
                    .getSingleResult();
            final var leaves = new TreeMap<String, BigDecimal>();
            var level = Map.of(item.getItemId(), quantity);
            while (!level.isEmpty()) {
                final var rows = children.apply(entityManager, level.keySet());
                final var next = new HashMap<Integer, BigDecimal>();
                for (final var row : rows) {
                    // the quantity of the parent, times the quantity of this row and its scrap
                    final var required = level.get(row.getParentItem().getItemId())
                            .multiply(row.getQuantity())
                            .multiply(BigDecimal.ONE.add(row.getScrapRate().movePointLeft(2)));
                    next.merge(row.getComponentItem().getItemId(), required, BigDecimal::add);
                }
                // an item with no row of its own is a raw material
                for (final var entry : level.entrySet()) {
                    if (rows.stream().noneMatch(r -> r.getParentItem().getItemId().equals(entry.getKey()))) {
                        final var leaf = entityManager.find(Item.class, entry.getKey());
                        leaves.merge(leaf.getItemCode(), entry.getValue(), BigDecimal::add);
                    }
                }
                level = next;
            }
            return leaves;
        }

        private void verify(final Map<String, BigDecimal> leaves) {
            assertThat(leaves.keySet()).containsExactly(
                    "RM-FAB-004", "RM-FILL-002", "RM-FIN-001", "RM-FIN-003", "RM-HARD-001", "RM-METAL-001",
                    "RM-METAL-002", "RM-WOOD-001", "RM-WOOD-002", "RM-WOOD-003");
            assertThat(leaves.values()).extracting(__Exercises_TestUtils::scale4)
                    .containsExactly(new BigDecimal("130.9770"), new BigDecimal("351.6540"),
                                     new BigDecimal("8.2913"), new BigDecimal("8.2913"),
                                     new BigDecimal("10.0500"), new BigDecimal("81.2040"),
                                     new BigDecimal("20.3010"), new BigDecimal("21.1050"),
                                     new BigDecimal("21.1050"), new BigDecimal("105.5250"));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var leaves = explode(entityManager, "FG-FUT-003", BigDecimal.TEN, (em, parentItemIds) ->
                        em.createQuery("""
                                               SELECT b
                                               FROM DboBillOfMaterials b
                                               WHERE b.parentItemId IN :parentItemIds
                                                 AND b.isActive = TRUE""", BillOfMaterials.class)
                                .setParameter("parentItemIds", parentItemIds)
                                .getResultList());
                verify(leaves);
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var leaves = explode(entityManager, "FG-FUT-003", BigDecimal.TEN, (em, parentItemIds) -> {
                    final var builder = em.getCriteriaBuilder();
                    final var query = builder.createQuery(BillOfMaterials.class);
                    final var b = query.from(BillOfMaterials.class);
                    query.select(b).where(b.get(BillOfMaterials_.parentItemId).in(parentItemIds),
                                          builder.isTrue(b.get(BillOfMaterials_.isActive)));
                    return em.createQuery(query).getResultList();
                });
                verify(leaves);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_A_02: the finished goods which use RM-FILL-001, from vw_WhereUsed")
    @Nested
    class FUTON_DBO_A_02_Test {

        @Test
        void nativeQuery() {
            apply(entityManager -> {
                @SuppressWarnings("unchecked")
                final List<Object[]> rows = entityManager.createNativeQuery("""
                                                                                    SELECT ComponentItemID, ComponentItemCode, ComponentItemName,
                                                                                           ParentItemID, ParentItemCode, ParentItemName, ParentType,
                                                                                           Level, Quantity, UnitCode, UsagePath
                                                                                    FROM dbo.vw_WhereUsed
                                                                                    WHERE ComponentItemCode = ?1
                                                                                      AND ParentType = 'Finished Goods'
                                                                                    ORDER BY ParentItemCode, UsagePath""")
                        .setParameter(1, "RM-FILL-001")
                        .getResultList();
                // each row through the public constructor of the keyless class
                final var list = rows.stream()
                        .map(r -> new WhereUsedView(
                                ((Number) r[0]).intValue(), (String) r[1], (String) r[2],
                                ((Number) r[3]).intValue(), (String) r[4], (String) r[5], (String) r[6],
                                ((Number) r[7]).intValue(), (BigDecimal) r[8], (String) r[9], (String) r[10]))
                        .toList();
                assertThat(list).extracting(WhereUsedView::getParentItemCode)
                        .containsExactly("FG-FUT-001", "FG-FUT-001", "FG-FUT-002");
                assertThat(list).extracting(WhereUsedView::getLevel)
                        .containsOnly(2);
                assertThat(list).extracting(v -> scale4(v.getQuantity()))
                        .containsExactly(new BigDecimal("15.0000"), new BigDecimal("5.0000"),
                                         new BigDecimal("20.0000"));
                assertThat(list).extracting(WhereUsedView::getUsagePath)
                        .containsExactly("RM-FILL-001 used in COMP-MAT-001 > FG-FUT-001",
                                         "RM-FILL-001 used in COMP-PIL-001 > FG-FUT-001",
                                         "RM-FILL-001 used in COMP-MAT-002 > FG-FUT-002");
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_A_05: the items in a cycle of the bill of materials")
    @Nested
    class FUTON_DBO_A_05_Test {
    }

    @DisplayName("FUTON_DBO_A_03: how many of each finished futon the components in WH-MAIN can build")
    @Nested
    class FUTON_DBO_A_03_Test {

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
                query.multiselect(p, buildable)
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

    @DisplayName("FUTON_DBO_A_06: the last movement of each item in the main warehouse")
    @Nested
    class FUTON_DBO_A_06_Test {
    }

    @DisplayName("FUTON_DBO_A_04: the running total of each wholesale customer, a window function")
    @Nested
    class FUTON_DBO_A_04_Test {

        @Test
        void nativeQuery() {
            apply(entityManager -> {
                @SuppressWarnings("unchecked")
                final List<Object[]> rows = entityManager.createNativeQuery("""
                                                                                    SELECT c.CustomerCode,
                                                                                           so.OrderNumber,
                                                                                           so.OrderDate,
                                                                                           so.NetAmount,
                                                                                           SUM(so.NetAmount) OVER (PARTITION BY so.CustomerID
                                                                                                                   ORDER BY so.OrderDate, so.SalesOrderID
                                                                                                                   ROWS UNBOUNDED PRECEDING) AS RunningTotal
                                                                                    FROM SalesOrder so
                                                                                             JOIN Customer c ON c.CustomerID = so.CustomerID
                                                                                    WHERE c.CustomerType = ?1
                                                                                    ORDER BY c.CustomerCode, so.OrderDate, so.SalesOrderID""")
                        .setParameter(1, "Wholesale")
                        .getResultList();
                assertThat(rows).extracting(r -> r[1])
                        .containsExactly("SO-2024-1006", "SO-2024-1104", "SO-2024-1007", "SO-2024-1107",
                                         "SO-2024-1008");
                assertThat(rows).extracting(r -> new BigDecimal(String.valueOf(r[4])))
                        .usingElementComparator(BigDecimal::compareTo)
                        .containsExactly(new BigDecimal("8499.68"), new BigDecimal("18699.08"),
                                         new BigDecimal("6799.68"), new BigDecimal("14979.20"),
                                         new BigDecimal("5099.76"));
                return null;
            });
        }
    }
}
