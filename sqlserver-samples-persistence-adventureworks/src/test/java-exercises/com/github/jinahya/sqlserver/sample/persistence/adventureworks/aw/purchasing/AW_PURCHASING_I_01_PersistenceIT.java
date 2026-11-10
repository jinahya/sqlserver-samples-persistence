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
                "SUPERIOR0001 | Superior Bicycles | 45 | 4530840.066",
                "PROFESSI0001 | Professional Athletic Consultants | 47 | 3239313.5792",
                "CHICAGO0001 | Chicago City Saddles | 47 | 3128743.493",
                "JACKSON0001 | Jackson Authority | 47 | 2561247.815",
                "VISIONC0001 | Vision Cycles, Inc. | 46 | 2558969.6642"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT v.accountNumber, v.name, COUNT(h), SUM(h.totalDue)
                        FROM PurchasingPurchaseOrderHeader h
                                 JOIN h.vendor v
                        WHERE h.status IN (2, 4)
                        GROUP BY v.businessEntityId, v.accountNumber, v.name
                        ORDER BY SUM(h.totalDue) DESC, v.businessEntityId""",
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
            query.select(builder.array(v.get(Vendor_.accountNumber), v.get(Vendor_.name),
                                       builder.count(h), totalDue));
            query.where(h.get(PurchaseOrderHeader_.status).in(2, 4));
            query.groupBy(v.get(Vendor_.businessEntityId), v.get(Vendor_.accountNumber),
                          v.get(Vendor_.name));
            query.orderBy(builder.desc(totalDue), builder.asc(v.get(Vendor_.businessEntityId)));
            return entityManager.createQuery(query).setMaxResults(5).getResultList();
        }));
    }

}
