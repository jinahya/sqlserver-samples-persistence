package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.purchasing;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PURCHASING_B_02} of {@code src/test/sql-exercises/_Purchasing/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_PURCHASING_B_02: The purchase orders of each status")
class AW_PURCHASING_B_02_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "1 | Pending | 225 | 3873879.6844",
                "2 | Approved | 12 | 2380772.8065",
                "3 | Rejected | 86 | 1438517.6426",
                "4 | Complete | 3689 | 62786162.5048"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT h.status,
                               CASE h.status
                                   WHEN 1 THEN 'Pending'
                                   WHEN 2 THEN 'Approved'
                                   WHEN 3 THEN 'Rejected'
                                   WHEN 4 THEN 'Complete'
                               END,
                               COUNT(h),
                               SUM(h.totalDue)
                        FROM PurchasingPurchaseOrderHeader h
                        GROUP BY h.status
                        ORDER BY h.status""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var h = query.from(PurchaseOrderHeader.class);
            final var status = h.get(PurchaseOrderHeader_.status);
            final var statusName = builder.<Integer, String>selectCase(status)
                    .when(1, "Pending")
                    .when(2, "Approved")
                    .when(3, "Rejected")
                    .when(4, "Complete");
            query.select(builder.array(status, statusName, builder.count(h),
                                       builder.sum(h.get(PurchaseOrderHeader_.totalDue))));
            query.groupBy(status);
            query.orderBy(builder.asc(status));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
