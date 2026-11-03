package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrder_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.QualityInspection;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.QualityInspection_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesChannel;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SupplierItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SupplierItem_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Warehouse_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.WhereUsedView;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code EXERCISES.asciidoc}, whose SQL is in {@code src/test/sql/exercises}, against the
 * installed {@code FutonManufacturing} database; a nested class per exercise, which answers it with the query language
 * and with the Criteria API, or with a native query where neither can, and checks the rows the SQL gives.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class FutonManufacturing_Exercises_PersistenceIT {

    private static <R> R apply(final Function<? super EntityManager, ? extends R> function) {
        return ___PersistenceIT_Utils.applyInTransactionAndRollback(function);
    }

    private static BigDecimal scale4(final Object value) {
        return new BigDecimal(String.valueOf(value)).setScale(4, RoundingMode.HALF_UP);
    }

    // ----------------------------------------------------------------------------------------- BOM

    @DisplayName("FUTON_DBO_B_01: the direct components of FG-FUT-003")
    @Nested
    class FUTON_DBO_B_01_Test {

        private void verify(final List<BillOfMaterials> list) {
            assertThat(list).extracting(b -> b.getComponentItem().getItemCode())
                    .containsExactly("COMP-FRM-004", "COMP-MAT-003", "COMP-PIL-002");
            assertThat(list).extracting(b -> b.getUnit().getUnitCode())
                    .containsOnly("EA");
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT b
                                                                   FROM DboBillOfMaterials b
                                                                   WHERE b.parentItem.itemCode = :itemCode
                                                                     AND b.isActive = TRUE
                                                                   ORDER BY b.componentItem.itemCode""",
                                                           BillOfMaterials.class)
                        .setParameter("itemCode", "FG-FUT-003")
                        .getResultList();
                verify(list);
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(BillOfMaterials.class);
                final var b = query.from(BillOfMaterials.class);
                query.select(b).where(
                        builder.equal(b.get(BillOfMaterials_.parentItem).get(Item_.itemCode), "FG-FUT-003"),
                        builder.isTrue(b.get(BillOfMaterials_.isActive))
                );
                query.orderBy(builder.asc(b.get(BillOfMaterials_.componentItem).get(Item_.itemCode)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_B_02: the raw materials which no item uses")
    @Nested
    class FUTON_DBO_B_02_Test {

        private void verify(final List<Item> list) {
            assertThat(list).extracting(Item::getItemCode)
                    .containsExactly("RM-FAB-007", "RM-FAB-008", "RM-HARD-002");
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT i
                                                                   FROM DboItem i
                                                                   WHERE i.itemType.typeCode = :typeCode
                                                                     AND NOT EXISTS (SELECT b
                                                                                     FROM DboBillOfMaterials b
                                                                                     WHERE b.componentItem = i
                                                                                       AND b.isActive = TRUE)
                                                                   ORDER BY i.itemCode""", Item.class)
                        .setParameter("typeCode", "RAW")
                        .getResultList();
                verify(list);
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Item.class);
                final var i = query.from(Item.class);
                final var subquery = query.subquery(BillOfMaterials.class);
                final var b = subquery.from(BillOfMaterials.class);
                subquery.select(b).where(
                        builder.equal(b.get(BillOfMaterials_.componentItem), i),
                        builder.isTrue(b.get(BillOfMaterials_.isActive))
                );
                query.select(i).where(
                        builder.equal(i.get(Item_.itemType).get(ItemType_.typeCode), "RAW"),
                        builder.not(builder.exists(subquery))
                );
                query.orderBy(builder.asc(i.get(Item_.itemCode)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_I_01: the raw materials which four or more items use directly")
    @Nested
    class FUTON_DBO_I_01_Test {

        private void verify(final List<Object[]> list) {
            assertThat(list).extracting(r -> ((Item) r[0]).getItemCode())
                    .containsExactly("RM-FIN-003", "RM-HARD-001", "RM-METAL-001", "RM-METAL-002", "RM-WOOD-001",
                                     "RM-WOOD-003");
            assertThat(list).extracting(r -> r[1])
                    .containsExactly(5L, 5L, 5L, 5L, 4L, 4L);
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT c, COUNT(b) AS parents
                                                                   FROM DboBillOfMaterials b
                                                                        JOIN b.componentItem c
                                                                   WHERE c.itemType.typeCode = :typeCode
                                                                     AND b.isActive = TRUE
                                                                   GROUP BY c
                                                                   HAVING COUNT(b) >= 4
                                                                   ORDER BY parents DESC, c.itemCode""", Object[].class)
                        .setParameter("typeCode", "RAW")
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
                final var c = b.join(BillOfMaterials_.componentItem);
                final var parents = builder.count(b);
                query.multiselect(c, parents)
                        .where(builder.equal(c.get(Item_.itemType).get(ItemType_.typeCode), "RAW"),
                               builder.isTrue(b.get(BillOfMaterials_.isActive)))
                        .groupBy(c)
                        .having(builder.ge(parents, 4L))
                        .orderBy(builder.desc(parents), builder.asc(c.get(Item_.itemCode)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_I_02: the material cost and the margin of each finished futon")
    @Nested
    class FUTON_DBO_I_02_Test {

        private void verify(final List<Object[]> list) {
            assertThat(list).extracting(r -> ((Item) r[0]).getItemCode())
                    .containsExactly("FG-FUT-001", "FG-FUT-002", "FG-FUT-003", "FG-FUT-004", "FG-FUT-005",
                                     "FG-FUT-006");
            assertThat(list).extracting(r -> scale4(r[1]))
                    .containsExactly(new BigDecimal("314.3468"), new BigDecimal("423.8052"),
                                     new BigDecimal("682.2813"), new BigDecimal("714.5420"),
                                     new BigDecimal("494.3444"), new BigDecimal("724.5918"));
            assertThat(list).extracting(r -> scale4(r[2]))
                    .containsExactly(new BigDecimal("-14.3568"), new BigDecimal("-23.8152"),
                                     new BigDecimal("-82.2913"), new BigDecimal("85.4480"),
                                     new BigDecimal("5.6456"), new BigDecimal("175.3982"));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT p,
                                                                          SUM(b.quantity * (1 + b.scrapRate / 100) * c.standardCost),
                                                                          p.listPrice - SUM(b.quantity * (1 + b.scrapRate / 100) * c.standardCost)
                                                                   FROM DboBillOfMaterials b
                                                                        JOIN b.parentItem p
                                                                        JOIN b.componentItem c
                                                                   WHERE p.itemType.typeCode = :typeCode
                                                                     AND b.isActive = TRUE
                                                                   GROUP BY p
                                                                   ORDER BY p.itemCode""", Object[].class)
                        .setParameter("typeCode", "FG")
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
                final var c = b.join(BillOfMaterials_.componentItem);
                final var cost = builder.sum(builder.prod(
                        builder.prod(b.get(BillOfMaterials_.quantity),
                                     builder.sum(builder.quot(b.get(BillOfMaterials_.scrapRate), 100), 1)),
                        c.get(Item_.standardCost)
                ));
                query.multiselect(p, cost, builder.diff(p.get(Item_.listPrice), cost))
                        .where(builder.equal(p.get(Item_.itemType).get(ItemType_.typeCode), "FG"),
                               builder.isTrue(b.get(BillOfMaterials_.isActive)))
                        .groupBy(p)
                        .orderBy(builder.asc(p.get(Item_.itemCode)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

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
                    final var required = level.get(row.getParentItemId())
                            .multiply(row.getQuantity())
                            .multiply(BigDecimal.ONE.add(row.getScrapRate().movePointLeft(2)));
                    next.merge(row.getComponentItemId(), required, BigDecimal::add);
                }
                // an item with no row of its own is a raw material
                for (final var entry : level.entrySet()) {
                    if (rows.stream().noneMatch(r -> r.getParentItemId().equals(entry.getKey()))) {
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
            assertThat(leaves.values()).extracting(FutonManufacturing_Exercises_PersistenceIT::scale4)
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

    // ----------------------------------------------------------------------------------- INVENTORY

    @DisplayName("FUTON_DBO_B_03: the finished goods in WH-MAIN, the fewest available first")
    @Nested
    class FUTON_DBO_B_03_Test {

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

    @DisplayName("FUTON_DBO_I_03: the five stocks nearest to their reorder points")
    @Nested
    class FUTON_DBO_I_03_Test {

        private void verify(final List<Object[]> list) {
            assertThat(list).extracting(r -> ((Inventory) r[0]).getItem().getItemCode())
                    .containsExactly("RM-METAL-002", "RM-FIN-001", "FG-FUT-004", "RM-HARD-002", "RM-FIN-002");
            assertThat(list).extracting(r -> new BigDecimal(String.valueOf(r[1])).setScale(2, RoundingMode.HALF_UP))
                    .containsExactly(new BigDecimal("1.33"), new BigDecimal("1.50"), new BigDecimal("1.60"),
                                     new BigDecimal("1.60"), new BigDecimal("1.67"));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT v, v.quantityAvailable / i.reorderPoint AS ratio
                                                                   FROM DboInventory v
                                                                        JOIN v.item i
                                                                   WHERE i.reorderPoint > 0
                                                                   ORDER BY ratio, i.itemCode""", Object[].class)
                        .setMaxResults(5)
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
                final var v = query.from(Inventory.class);
                final var i = v.join(Inventory_.item);
                final var ratio = builder.quot(v.get(Inventory_.quantityAvailable), i.get(Item_.reorderPoint));
                query.multiselect(v, ratio)
                        .where(builder.gt(i.get(Item_.reorderPoint), BigDecimal.ZERO))
                        .orderBy(builder.asc(ratio), builder.asc(i.get(Item_.itemCode)));
                final var list = entityManager.createQuery(query)
                        .setMaxResults(5)
                        .getResultList();
                verify(list);
                return null;
            });
        }
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

    // ---------------------------------------------------------------------------------- PURCHASING

    @DisplayName("FUTON_DBO_B_04: the items SUP-002 supplies, with its price and the standard cost")
    @Nested
    class FUTON_DBO_B_04_Test {

        private void verify(final List<SupplierItem> list) {
            assertThat(list).extracting(si -> si.getItem().getItemCode())
                    .containsExactly("RM-FILL-001", "RM-FILL-002", "RM-FILL-003", "RM-FILL-004", "RM-FILL-005");
            assertThat(list).allSatisfy(si -> assertThat(si.getUnitPrice())
                    .isLessThan(si.getItem().getStandardCost()));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT si
                                                                   FROM DboSupplierItem si
                                                                   WHERE si.supplier.supplierCode = :supplierCode
                                                                   ORDER BY si.item.itemCode""", SupplierItem.class)
                        .setParameter("supplierCode", "SUP-002")
                        .getResultList();
                verify(list);
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(SupplierItem.class);
                final var si = query.from(SupplierItem.class);
                query.select(si)
                        .where(builder.equal(si.get(SupplierItem_.supplier).get(Supplier_.supplierCode), "SUP-002"))
                        .orderBy(builder.asc(si.get(SupplierItem_.item).get(Item_.itemCode)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_I_04: the share rejected on receipt, for each supplier")
    @Nested
    class FUTON_DBO_I_04_Test {

        private void verify(final List<Object[]> list) {
            assertThat(list).extracting(r -> ((Supplier) r[0]).getSupplierCode())
                    .containsExactly("SUP-001", "SUP-003", "SUP-005", "SUP-002", "SUP-004");
            assertThat(list).extracting(r -> new BigDecimal(String.valueOf(r[3])).setScale(2, RoundingMode.HALF_UP))
                    .containsExactly(new BigDecimal("2.00"), new BigDecimal("2.00"), new BigDecimal("1.25"),
                                     new BigDecimal("1.00"), new BigDecimal("1.00"));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT s,
                                                                          SUM(q.quantityInspected),
                                                                          SUM(q.quantityRejected),
                                                                          SUM(q.quantityRejected) * 100 / SUM(q.quantityInspected) AS rejectedPercent
                                                                   FROM DboPurchaseOrder po
                                                                        JOIN po.supplier s
                                                                        JOIN DboQualityInspection q
                                                                             ON q.referenceType = 'PO' AND q.referenceNumber = po.poNumber
                                                                   GROUP BY s
                                                                   ORDER BY rejectedPercent DESC, s.supplierCode""",
                                                           Object[].class)
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
                final var po = query.from(PurchaseOrder.class);
                final var s = po.join(PurchaseOrder_.supplier);
                final var q = po.join(QualityInspection.class);
                q.on(builder.equal(q.get(QualityInspection_.referenceType), "PO"),
                     builder.equal(q.get(QualityInspection_.referenceNumber), po.get(PurchaseOrder_.poNumber)));
                final var inspected = builder.sum(q.get(QualityInspection_.quantityInspected));
                final var rejected = builder.sum(q.get(QualityInspection_.quantityRejected));
                final var percent = builder.quot(builder.prod(rejected, BigDecimal.valueOf(100)), inspected);
                query.multiselect(s, inspected, rejected, percent)
                        .groupBy(s)
                        .orderBy(builder.desc(percent), builder.asc(s.get(Supplier_.supplierCode)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    // --------------------------------------------------------------------------------------- SALES

    @DisplayName("FUTON_DBO_B_05: the orders not delivered yet")
    @Nested
    class FUTON_DBO_B_05_Test {

        private void verify(final List<SalesOrder> list) {
            assertThat(list).extracting(SalesOrder::getOrderNumber)
                    .containsExactly("SO-2024-1102", "SO-2024-1103", "SO-2024-1105", "SO-2024-1106",
                                     "SO-2024-1104", "SO-2024-1107");
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT so
                                                                   FROM DboSalesOrder so
                                                                   WHERE so.status <> :status
                                                                   ORDER BY so.requestedDeliveryDate, so.orderNumber""",
                                                           SalesOrder.class)
                        .setParameter("status", "Delivered")
                        .getResultList();
                verify(list);
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(SalesOrder.class);
                final var so = query.from(SalesOrder.class);
                query.select(so)
                        .where(builder.notEqual(so.get(SalesOrder_.status), "Delivered"))
                        .orderBy(builder.asc(so.get(SalesOrder_.requestedDeliveryDate)),
                                 builder.asc(so.get(SalesOrder_.orderNumber)));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_I_05: the number of orders and the net amount of each sales channel")
    @Nested
    class FUTON_DBO_I_05_Test {

        private void verify(final List<Object[]> list) {
            assertThat(list).extracting(r -> ((SalesChannel) r[0]).getChannelCode())
                    .containsExactly("WHOLESALE", "RETAIL", "ONLINE");
            assertThat(list).extracting(r -> r[1])
                    .containsExactly(5L, 6L, 4L);
            assertThat(list).extracting(r -> ((BigDecimal) r[2]).setScale(2, RoundingMode.HALF_UP))
                    .containsExactly(new BigDecimal("38778.04"), new BigDecimal("10219.85"),
                                     new BigDecimal("4913.93"));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT ch, COUNT(so), SUM(so.netAmount) AS netAmount
                                                                   FROM DboSalesOrder so
                                                                        JOIN so.salesChannel ch
                                                                   GROUP BY ch
                                                                   ORDER BY netAmount DESC""", Object[].class)
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
                final var so = query.from(SalesOrder.class);
                final var ch = so.join(SalesOrder_.salesChannel);
                final var netAmount = builder.sum(so.get(SalesOrder_.netAmount));
                query.multiselect(ch, builder.count(so), netAmount)
                        .groupBy(ch)
                        .orderBy(builder.desc(netAmount));
                final var list = entityManager.createQuery(query).getResultList();
                verify(list);
                return null;
            });
        }
    }

    @DisplayName("FUTON_DBO_I_06: the three futons sold the most")
    @Nested
    class FUTON_DBO_I_06_Test {

        private void verify(final List<Object[]> list) {
            assertThat(list).extracting(r -> ((Item) r[0]).getItemCode())
                    .containsExactly("FG-FUT-002", "FG-FUT-001", "FG-FUT-005");
            assertThat(list).extracting(r -> ((BigDecimal) r[1]).intValue())
                    .containsExactly(57, 49, 18);
            assertThat(list).extracting(r -> scale4(r[2]))
                    .containsExactly(new BigDecimal("20679.4830"), new BigDecimal("13259.5580"),
                                     new BigDecimal("8099.8380"));
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                final var list = entityManager.createQuery("""
                                                                   SELECT i, SUM(d.quantity) AS quantity, SUM(d.netAmount)
                                                                   FROM DboSalesOrderDetail d
                                                                        JOIN d.item i
                                                                   GROUP BY i
                                                                   ORDER BY quantity DESC, i.itemCode""",
                                                           Object[].class)
                        .setMaxResults(3)
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
                final var d = query.from(SalesOrderDetail.class);
                final var i = d.join(SalesOrderDetail_.item);
                final var quantity = builder.sum(d.get(SalesOrderDetail_.quantity));
                query.multiselect(i, quantity, builder.sum(d.get(SalesOrderDetail_.netAmount)))
                        .groupBy(i)
                        .orderBy(builder.desc(quantity), builder.asc(i.get(Item_.itemCode)));
                final var list = entityManager.createQuery(query)
                        .setMaxResults(3)
                        .getResultList();
                verify(list);
                return null;
            });
        }
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
