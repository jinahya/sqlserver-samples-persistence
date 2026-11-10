package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrderDetail_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.scale4;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_06} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_06: the three futons sold the most")
class FUTON_DBO_I_06_PersistenceIT {

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
            query.select(builder.array(i, quantity,
                                       builder.sum(d.get(SalesOrderDetail_.netAmount))))
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
