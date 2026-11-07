package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ProductionOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ProductionOrder_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_09} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_09: the production orders completed late")
class FUTON_DBO_B_09_PersistenceIT {

    // the installed database has no production order at all
    private void verify(final List<ProductionOrder> list) {
        assertThat(list).isEmpty();
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery(
                            """
                                    SELECT po
                                    FROM DboProductionOrder po
                                             JOIN po.item i
                                    WHERE po.status = 'Completed'
                                      AND po.actualCompletionDate > po.plannedCompletionDate
                                    ORDER BY po.plannedCompletionDate, po.workOrderNumber""",
                            ProductionOrder.class)
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(ProductionOrder.class);
            final var po = query.from(ProductionOrder.class);
            po.join(ProductionOrder_.item);
            final var plannedCompletionDate = po.get(ProductionOrder_.plannedCompletionDate);
            query.select(po)
                    .where(builder.equal(po.get(ProductionOrder_.status), "Completed"),
                           builder.greaterThan(po.get(ProductionOrder_.actualCompletionDate),
                                               plannedCompletionDate))
                    .orderBy(builder.asc(plannedCompletionDate),
                             builder.asc(po.get(ProductionOrder_.workOrderNumber)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
