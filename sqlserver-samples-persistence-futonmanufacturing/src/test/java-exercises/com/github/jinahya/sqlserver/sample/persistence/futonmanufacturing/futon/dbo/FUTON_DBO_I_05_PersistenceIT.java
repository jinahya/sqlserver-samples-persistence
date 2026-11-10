package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesChannel;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_05} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_05: the orders and the net amount of each sales channel")
class FUTON_DBO_I_05_PersistenceIT {

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
            query.select(builder.array(ch, builder.count(so), netAmount))
                    .groupBy(ch)
                    .orderBy(builder.desc(netAmount));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
