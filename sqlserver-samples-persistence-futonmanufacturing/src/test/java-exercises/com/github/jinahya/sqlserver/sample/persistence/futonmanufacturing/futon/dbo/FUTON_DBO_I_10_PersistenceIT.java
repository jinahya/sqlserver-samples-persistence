package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ProductionCompletion;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ProductionCompletion_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ProductionOrder_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.WorkCenter;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.WorkCenter_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_10} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 *
 * <p>The share scrapped is not cast to {@code DECIMAL(9, 2)}, which JPQL cannot write; it orders
 * the rows the same.
 */
@DisplayName("FUTON_DBO_I_10: the scrap rate of each work center")
class FUTON_DBO_I_10_PersistenceIT {

    // the installed database has no production completion at all
    private void verify(final List<Object[]> list) {
        assertThat(list).isEmpty();
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery(
                            """
                                    SELECT wc,
                                           SUM(pc.quantityCompleted),
                                           SUM(COALESCE(pc.quantityScrapped, 0)),
                                           SUM(COALESCE(pc.quantityScrapped, 0)) * 100
                                               / NULLIF(SUM(pc.quantityCompleted
                                                            + COALESCE(pc.quantityScrapped, 0)), 0)
                                               AS scrapPercent
                                    FROM DboProductionCompletion pc
                                             JOIN pc.productionOrder po
                                             JOIN DboWorkCenter wc
                                               ON wc.workCenterId = COALESCE(pc.workCenterId, po.workCenterId)
                                    GROUP BY wc
                                    ORDER BY scrapPercent DESC, wc.workCenterCode""",
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
            final var pc = query.from(ProductionCompletion.class);
            final var po = pc.join(ProductionCompletion_.productionOrder);
            final var wc = pc.join(WorkCenter.class);
            wc.on(builder.equal(wc.get(WorkCenter_.workCenterId),
                                builder.coalesce(pc.get(ProductionCompletion_.workCenterId),
                                                 po.get(ProductionOrder_.workCenterId))));
            final var completed = pc.get(ProductionCompletion_.quantityCompleted);
            final var scrapped = builder.coalesce(pc.get(ProductionCompletion_.quantityScrapped),
                                                  BigDecimal.ZERO);
            final var scrappedSum = builder.sum(scrapped);
            final var scrapPercent = builder.quot(
                    builder.prod(scrappedSum, BigDecimal.valueOf(100)),
                    builder.nullif(builder.sum(builder.sum(completed, scrapped)), BigDecimal.ZERO));
            query.select(builder.array(wc, builder.sum(completed), scrappedSum, scrapPercent))
                    .groupBy(wc)
                    .orderBy(builder.desc(scrapPercent),
                             builder.asc(wc.get(WorkCenter_.workCenterCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
