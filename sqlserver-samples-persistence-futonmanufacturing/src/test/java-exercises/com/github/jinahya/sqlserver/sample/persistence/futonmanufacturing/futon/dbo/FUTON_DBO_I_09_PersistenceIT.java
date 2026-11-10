package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ReturnReason;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ReturnReason_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesReturnDetail;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesReturnDetail_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesReturn_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_09} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_09: the quantities returned and refunded, by reason and item")
class FUTON_DBO_I_09_PersistenceIT {

    private void verify(final List<Object[]> list) {
        assertThat(list).extracting(r -> ((ReturnReason) r[0]).getReasonCode())
                .containsExactly("DEFECT", "CHANGE");
        assertThat(list).extracting(r -> ((Item) r[1]).getItemCode())
                .containsExactly("FG-FUT-006", "FG-FUT-002");
        assertThat(list).extracting(r -> r[2])
                .containsExactly(1L, 1L);
        assertThat(list).extracting(r -> (BigDecimal) r[3])
                .usingElementComparator(BigDecimal::compareTo)
                .containsExactly(new BigDecimal("1.00"), new BigDecimal("1.00"));
        assertThat(list).extracting(r -> (BigDecimal) r[4])
                .usingElementComparator(BigDecimal::compareTo)
                .containsExactly(new BigDecimal("899.99"), new BigDecimal("399.99"));
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery(
                            """
                                    SELECT rr, i,
                                           COUNT(DISTINCT d.soDetailId),
                                           SUM(d.quantityReturned),
                                           SUM(d.refundAmount) AS refundAmount
                                    FROM DboSalesReturnDetail d
                                             JOIN d.returnSalesReturn r
                                             JOIN r.returnReason rr
                                             JOIN d.item i
                                    WHERE r.status IN ('Received', 'Refunded')
                                    GROUP BY rr, i
                                    ORDER BY refundAmount DESC, rr.reasonCode, i.itemCode""",
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
            final var d = query.from(SalesReturnDetail.class);
            final var r = d.join(SalesReturnDetail_.returnSalesReturn);
            final var rr = r.join(SalesReturn_.returnReason);
            final var i = d.join(SalesReturnDetail_.item);
            final var refundAmount = builder.sum(d.get(SalesReturnDetail_.refundAmount));
            query.select(builder.array(rr, i,
                                       builder.countDistinct(d.get(SalesReturnDetail_.soDetailId)),
                                       builder.sum(d.get(SalesReturnDetail_.quantityReturned)),
                                       refundAmount))
                    .where(r.get(SalesReturn_.status).in("Received", "Refunded"))
                    .groupBy(rr, i)
                    .orderBy(builder.desc(refundAmount),
                             builder.asc(rr.get(ReturnReason_.reasonCode)),
                             builder.asc(i.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
