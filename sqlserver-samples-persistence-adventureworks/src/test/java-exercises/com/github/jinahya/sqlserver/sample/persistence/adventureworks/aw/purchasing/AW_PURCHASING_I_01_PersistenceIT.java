package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.purchasing;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.PurchaseOrderHeader_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.Vendor_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PURCHASING_I_01} of
 * {@code src/test/sql-exercises/_Purchasing/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("AW_PURCHASING_I_01: The five vendors the company spent the most with")
class AW_PURCHASING_I_01_PersistenceIT {

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
