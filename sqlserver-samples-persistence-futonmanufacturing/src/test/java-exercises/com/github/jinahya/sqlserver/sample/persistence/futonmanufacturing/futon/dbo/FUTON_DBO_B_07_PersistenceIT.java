package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrder_;
import jakarta.persistence.criteria.Nulls;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_07} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_07: the purchase-order lines still to receive")
class FUTON_DBO_B_07_PersistenceIT {

    // every line of the installed database is received in full, or its order is cancelled
    private void verify(final List<Object[]> list) {
        assertThat(list).isEmpty();
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery(
                            """
                                    SELECT d, d.quantity - d.quantityReceived AS outstanding
                                    FROM DboPurchaseOrderDetail d
                                             JOIN d.purchaseOrder po
                                    WHERE po.status <> 'Cancelled'
                                      AND (d.quantityReceived < d.quantity OR d.quantityReceived IS NULL)
                                    ORDER BY po.expectedDeliveryDate NULLS LAST, po.poNumber, d.lineNumber,
                                             d.poDetailId""",
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
            final var d = query.from(PurchaseOrderDetail.class);
            final var po = d.join(PurchaseOrderDetail_.purchaseOrder);
            final var quantity = d.get(PurchaseOrderDetail_.quantity);
            final var quantityReceived = d.get(PurchaseOrderDetail_.quantityReceived);
            query.select(builder.array(d, builder.diff(quantity, quantityReceived)))
                    .where(builder.notEqual(po.get(PurchaseOrder_.status), "Cancelled"),
                           builder.or(builder.lessThan(quantityReceived, quantity),
                                      builder.isNull(quantityReceived)))
                    .orderBy(builder.asc(po.get(PurchaseOrder_.expectedDeliveryDate), Nulls.LAST),
                             builder.asc(po.get(PurchaseOrder_.poNumber)),
                             builder.asc(d.get(PurchaseOrderDetail_.lineNumber)),
                             builder.asc(d.get(PurchaseOrderDetail_.poDetailId)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
