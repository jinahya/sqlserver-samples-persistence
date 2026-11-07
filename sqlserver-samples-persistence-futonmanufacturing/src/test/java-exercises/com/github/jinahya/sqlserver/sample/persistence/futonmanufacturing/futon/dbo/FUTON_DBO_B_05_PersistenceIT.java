package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesOrder_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_05} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_05: the orders not delivered yet")
class FUTON_DBO_B_05_PersistenceIT {

    private void verify(final List<SalesOrder> list) {
        assertThat(list).extracting(SalesOrder::getOrderNumber)
                .containsExactly("SO-2024-1102", "SO-2024-1103", "SO-2024-1105", "SO-2024-1106",
                                 "SO-2024-1104", "SO-2024-1107");
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT so
                                                               FROM DboSalesOrder so
                                                               WHERE so.status <> :status
                                                               ORDER BY so.requestedDeliveryDate, so.orderNumber""",
                                                       SalesOrder.class)
                    .setParameter("status", "Delivered")
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(SalesOrder.class);
            final var so = query.from(SalesOrder.class);
            query.select(so)
                    .where(builder.notEqual(so.get(SalesOrder_.status), "Delivered"))
                    .orderBy(builder.asc(so.get(SalesOrder_.requestedDeliveryDate)),
                             builder.asc(so.get(SalesOrder_.orderNumber)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
