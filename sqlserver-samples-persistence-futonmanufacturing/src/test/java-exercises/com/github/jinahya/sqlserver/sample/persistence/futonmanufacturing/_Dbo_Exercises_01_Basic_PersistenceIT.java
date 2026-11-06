package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SupplierItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SupplierItem_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Warehouse_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_dbo/01_basic.sql}, against the installed
 * {@code FutonManufacturing} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class _Dbo_Exercises_01_Basic_PersistenceIT {

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

    @DisplayName("FUTON_DBO_B_06: an item's inventory transactions in a month")
    @Nested
    class FUTON_DBO_B_06_Test {
    }

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

    @DisplayName("FUTON_DBO_B_07: the purchase-order lines still to receive")
    @Nested
    class FUTON_DBO_B_07_Test {
    }

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

    @DisplayName("FUTON_DBO_B_08: the accepted quotes not turned into orders")
    @Nested
    class FUTON_DBO_B_08_Test {
    }

    @DisplayName("FUTON_DBO_B_09: the production orders completed late")
    @Nested
    class FUTON_DBO_B_09_Test {
    }

    @DisplayName("FUTON_DBO_B_10: the open production schedule")
    @Nested
    class FUTON_DBO_B_10_Test {
    }
}
