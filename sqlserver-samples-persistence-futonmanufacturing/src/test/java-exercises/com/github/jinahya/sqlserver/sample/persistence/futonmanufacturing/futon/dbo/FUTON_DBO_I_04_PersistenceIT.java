package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.PurchaseOrder_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.QualityInspection;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.QualityInspection_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_04} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_04: the share rejected on receipt, for each supplier")
class FUTON_DBO_I_04_PersistenceIT {

    private void verify(final List<Object[]> list) {
        assertThat(list).extracting(r -> ((Supplier) r[0]).getSupplierCode())
                .containsExactly("SUP-001", "SUP-003", "SUP-005", "SUP-002", "SUP-004");
        assertThat(list).extracting(r -> new BigDecimal(String.valueOf(r[3])).setScale(2, RoundingMode.HALF_UP))
                .containsExactly(new BigDecimal("2.00"), new BigDecimal("2.00"), new BigDecimal("1.25"),
                                 new BigDecimal("1.00"), new BigDecimal("1.00"));
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT s,
                                                                      SUM(q.quantityInspected),
                                                                      SUM(q.quantityRejected),
                                                                      SUM(q.quantityRejected) * 100 / SUM(q.quantityInspected) AS rejectedPercent
                                                               FROM DboPurchaseOrder po
                                                                    JOIN po.supplier s
                                                                    JOIN DboQualityInspection q
                                                                         ON q.referenceType = 'PO' AND q.referenceNumber = po.poNumber
                                                               GROUP BY s
                                                               ORDER BY rejectedPercent DESC, s.supplierCode""",
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
            final var po = query.from(PurchaseOrder.class);
            final var s = po.join(PurchaseOrder_.supplier);
            final var q = po.join(QualityInspection.class);
            q.on(builder.equal(q.get(QualityInspection_.referenceType), "PO"),
                 builder.equal(q.get(QualityInspection_.referenceNumber), po.get(PurchaseOrder_.poNumber)));
            final var inspected = builder.sum(q.get(QualityInspection_.quantityInspected));
            final var rejected = builder.sum(q.get(QualityInspection_.quantityRejected));
            final var percent = builder.quot(builder.prod(rejected, BigDecimal.valueOf(100)), inspected);
            query.multiselect(s, inspected, rejected, percent)
                    .groupBy(s)
                    .orderBy(builder.desc(percent), builder.asc(s.get(Supplier_.supplierCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
