package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Inventory_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_03} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_03: the five stocks nearest to their reorder points")
class FUTON_DBO_I_03_PersistenceIT {

    private void verify(final List<Object[]> list) {
        assertThat(list).extracting(r -> ((Inventory) r[0]).getItem().getItemCode())
                .containsExactly("RM-METAL-002", "RM-FIN-001", "FG-FUT-004", "RM-HARD-002", "RM-FIN-002");
        assertThat(list).extracting(r -> new BigDecimal(String.valueOf(r[1])).setScale(2, RoundingMode.HALF_UP))
                .containsExactly(new BigDecimal("1.33"), new BigDecimal("1.50"), new BigDecimal("1.60"),
                                 new BigDecimal("1.60"), new BigDecimal("1.67"));
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT v, v.quantityAvailable / i.reorderPoint AS ratio
                                                               FROM DboInventory v
                                                                    JOIN v.item i
                                                               WHERE i.reorderPoint > 0
                                                               ORDER BY ratio, i.itemCode""", Object[].class)
                    .setMaxResults(5)
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
            final var v = query.from(Inventory.class);
            final var i = v.join(Inventory_.item);
            final var ratio = builder.quot(v.get(Inventory_.quantityAvailable), i.get(Item_.reorderPoint));
            query.select(builder.array(v, ratio))
                    .where(builder.gt(i.get(Item_.reorderPoint), BigDecimal.ZERO))
                    .orderBy(builder.asc(ratio), builder.asc(i.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query)
                    .setMaxResults(5)
                    .getResultList();
            verify(list);
            return null;
        });
    }

}
