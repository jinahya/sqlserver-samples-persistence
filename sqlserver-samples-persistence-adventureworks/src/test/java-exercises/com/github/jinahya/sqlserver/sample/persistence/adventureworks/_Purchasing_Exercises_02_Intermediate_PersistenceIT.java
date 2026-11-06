package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.Vendor_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Purchasing/02_intermediate.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Purchasing_Exercises_02_Intermediate_PersistenceIT {

    @DisplayName("AW_PURCHASING_I_01: the five vendors the company spent the most with")
    @Nested
    class AW_PURCHASING_I_01_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "SUPERIOR0001 | Superior Bicycles | 50 | 5034266.74",
                    "PROFESSI0001 | Professional Athletic Consultants | 50 | 3379946.3215",
                    "CHICAGO0001 | Chicago City Saddles | 51 | 3347165.1965",
                    "JACKSON0001 | Jackson Authority | 51 | 2821333.516",
                    "VISIONC0001 | Vision Cycles, Inc. | 50 | 2777684.911"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT v.accountNumber, v.name, COUNT(h), SUM(h.totalDue)
                            FROM PurchasingPurchaseOrderHeader h
                                     JOIN h.vendor v
                            GROUP BY v.businessEntityId, v.accountNumber, v.name
                            ORDER BY SUM(h.totalDue) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var h = query.from(PurchaseOrderHeader.class);
                final var v = h.join(PurchaseOrderHeader_.vendor);
                final var totalDue = builder.sum(h.get(PurchaseOrderHeader_.totalDue));
                query.multiselect(v.get(Vendor_.accountNumber), v.get(Vendor_.name),
                                  builder.count(h), totalDue);
                query.groupBy(v.get(Vendor_.businessEntityId), v.get(Vendor_.accountNumber),
                              v.get(Vendor_.name));
                query.orderBy(builder.desc(totalDue));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_PURCHASING_I_02: the five vendors whose goods were rejected the most")
    @Nested
    class AW_PURCHASING_I_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found.stream().map(r -> Arrays.copyOf(r, 3)).toList())).containsExactly(
                    "International | 153 | 9",
                    "Anderson's Custom Bikes | 27558 | 1510",
                    "Signature Cycles | 54455 | 2940",
                    "Crowley Sport | 55637 | 2967",
                    "Capital Road Cycles | 55664 | 2967"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT v.name, SUM(d.receivedQty), SUM(d.rejectedQty),
                                   SUM(d.rejectedQty) / SUM(d.receivedQty) AS rejected
                            FROM PurchasingPurchaseOrderDetail d
                                     JOIN d.purchaseOrder h
                                     JOIN h.vendor v
                            GROUP BY v.businessEntityId, v.name
                            ORDER BY rejected DESC, v.name""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var d = query.from(PurchaseOrderDetail.class);
                final var v = d.join(PurchaseOrderDetail_.purchaseOrder)
                        .join(PurchaseOrderHeader_.vendor);
                final var receivedQty = builder.sum(d.get(PurchaseOrderDetail_.receivedQty));
                final var rejectedQty = builder.sum(d.get(PurchaseOrderDetail_.rejectedQty));
                final var rejected = builder.quot(rejectedQty, receivedQty);
                query.multiselect(v.get(Vendor_.name), receivedQty, rejectedQty, rejected);
                query.groupBy(v.get(Vendor_.businessEntityId), v.get(Vendor_.name));
                query.orderBy(builder.desc(rejected), builder.asc(v.get(Vendor_.name)));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }
}
