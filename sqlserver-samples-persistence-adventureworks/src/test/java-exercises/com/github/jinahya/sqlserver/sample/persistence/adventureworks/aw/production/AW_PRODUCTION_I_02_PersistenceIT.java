package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.production;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ScrapReason_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.WorkOrder;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.WorkOrder_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PRODUCTION_I_02} of
 * {@code src/test/sql-exercises/_Production/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName("AW_PRODUCTION_I_02: The five scrap reasons with the most scrapped quantity")
class AW_PRODUCTION_I_02_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "Paint process failed | 37 | 1271",
                "Trim length too long | 52 | 981",
                "Thermoform temperature too low | 63 | 876",
                "Drill size too small | 44 | 826",
                "Wheel misaligned | 51 | 804"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT r.name, COUNT(w), SUM(w.scrappedQty)
                        FROM ProductionWorkOrder w
                                 JOIN w.scrapReason r
                        GROUP BY r.scrapReasonId, r.name
                        ORDER BY SUM(w.scrappedQty) DESC, r.scrapReasonId""",
                Object[].class
        ).setMaxResults(5).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var w = query.from(WorkOrder.class);
            final var r = w.join(WorkOrder_.scrapReason);
            final var scrappedQty = builder.sum(w.get(WorkOrder_.scrappedQty));
            query.select(builder.array(r.get(ScrapReason_.name), builder.count(w), scrappedQty));
            query.groupBy(r.get(ScrapReason_.scrapReasonId), r.get(ScrapReason_.name));
            query.orderBy(builder.desc(scrappedQty),
                          builder.asc(r.get(ScrapReason_.scrapReasonId)));
            return entityManager.createQuery(query).setMaxResults(5).getResultList();
        }));
    }

}
