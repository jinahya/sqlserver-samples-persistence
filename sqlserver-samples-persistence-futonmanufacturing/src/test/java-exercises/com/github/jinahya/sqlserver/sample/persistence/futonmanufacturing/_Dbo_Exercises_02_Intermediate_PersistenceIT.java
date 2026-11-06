package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing;

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
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.scale4;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_dbo/02_intermediate.sql}, against the installed
 * {@code FutonManufacturing} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S3577" // Test classes should comply with a naming convention
})
class _Dbo_Exercises_02_Intermediate_PersistenceIT {

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

    @DisplayName("FUTON_DBO_I_07: the BOM links that break the planning assumptions")
    @Nested
    class FUTON_DBO_I_07_Test {
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

    @DisplayName("FUTON_DBO_I_08: the share of each item returned")
    @Nested
    class FUTON_DBO_I_08_Test {
    }

    @DisplayName("FUTON_DBO_I_09: the quantities returned and refunded, by reason and item")
    @Nested
    class FUTON_DBO_I_09_Test {
    }

    @DisplayName("FUTON_DBO_I_10: the scrap rate of each work center")
    @Nested
    class FUTON_DBO_I_10_Test {
    }

    @DisplayName("FUTON_DBO_I_11: the production materials still to issue")
    @Nested
    class FUTON_DBO_I_11_Test {
    }
}
