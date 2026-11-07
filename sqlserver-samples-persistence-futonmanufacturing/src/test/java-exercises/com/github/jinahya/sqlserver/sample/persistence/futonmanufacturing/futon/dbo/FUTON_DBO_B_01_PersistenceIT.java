package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_01} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_01: the direct components of a finished futon")
class FUTON_DBO_B_01_PersistenceIT {

    private void verify(final List<BillOfMaterials> list) {
        assertThat(list).extracting(b -> b.getComponentItem().getItemCode())
                .containsExactly("COMP-FRM-004", "COMP-MAT-003", "COMP-PIL-002");
        assertThat(list).extracting(b -> b.getUnit().getUnitCode())
                .containsOnly("EA");
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT b
                                                               FROM DboBillOfMaterials b
                                                               WHERE b.parentItem.itemCode = :itemCode
                                                                 AND b.isActive = TRUE
                                                               ORDER BY b.componentItem.itemCode""",
                                                       BillOfMaterials.class)
                    .setParameter("itemCode", "FG-FUT-003")
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(BillOfMaterials.class);
            final var b = query.from(BillOfMaterials.class);
            query.select(b).where(
                    builder.equal(b.get(BillOfMaterials_.parentItem).get(Item_.itemCode), "FG-FUT-003"),
                    builder.isTrue(b.get(BillOfMaterials_.isActive))
            );
            query.orderBy(builder.asc(b.get(BillOfMaterials_.componentItem).get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
