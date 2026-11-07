package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SupplierItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SupplierItem_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Supplier_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_04} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_04: the items a supplier supplies, its price beside the standard cost")
class FUTON_DBO_B_04_PersistenceIT {

    private void verify(final List<SupplierItem> list) {
        assertThat(list).extracting(si -> si.getItem().getItemCode())
                .containsExactly("RM-FILL-001", "RM-FILL-002", "RM-FILL-003", "RM-FILL-004", "RM-FILL-005");
        assertThat(list).allSatisfy(si -> assertThat(si.getUnitPrice())
                .isLessThan(si.getItem().getStandardCost()));
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT si
                                                               FROM DboSupplierItem si
                                                               WHERE si.supplier.supplierCode = :supplierCode
                                                               ORDER BY si.item.itemCode""", SupplierItem.class)
                    .setParameter("supplierCode", "SUP-002")
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(SupplierItem.class);
            final var si = query.from(SupplierItem.class);
            query.select(si)
                    .where(builder.equal(si.get(SupplierItem_.supplier).get(Supplier_.supplierCode), "SUP-002"))
                    .orderBy(builder.asc(si.get(SupplierItem_.item).get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
