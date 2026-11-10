package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.scale4;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_02} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_02: the material cost and the margin of each finished futon")
class FUTON_DBO_I_02_PersistenceIT {

    private void verify(final List<Object[]> list) {
        assertThat(list).extracting(r -> ((Item) r[0]).getItemCode())
                .containsExactly("FG-FUT-001", "FG-FUT-002", "FG-FUT-003", "FG-FUT-004", "FG-FUT-005",
                                 "FG-FUT-006");
        assertThat(list).extracting(r -> scale4(r[1]))
                .containsExactly(new BigDecimal("314.3468"), new BigDecimal("423.8052"),
                                 new BigDecimal("682.2813"), new BigDecimal("714.5420"),
                                 new BigDecimal("494.3444"), new BigDecimal("724.5918"));
        assertThat(list).extracting(r -> scale4(r[2]))
                .containsExactly(new BigDecimal("-14.3568"), new BigDecimal("-23.8152"),
                                 new BigDecimal("-82.2913"), new BigDecimal("85.4480"),
                                 new BigDecimal("5.6456"), new BigDecimal("175.3982"));
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT p,
                                                                      SUM(b.quantity * (1 + b.scrapRate / 100) * c.standardCost),
                                                                      p.listPrice - SUM(b.quantity * (1 + b.scrapRate / 100) * c.standardCost)
                                                               FROM DboBillOfMaterials b
                                                                    JOIN b.parentItem p
                                                                    JOIN b.componentItem c
                                                               WHERE p.itemType.typeCode = :typeCode
                                                                 AND b.isActive = TRUE
                                                               GROUP BY p
                                                               ORDER BY p.itemCode""", Object[].class)
                    .setParameter("typeCode", "FG")
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
            final var b = query.from(BillOfMaterials.class);
            final var p = b.join(BillOfMaterials_.parentItem);
            final var c = b.join(BillOfMaterials_.componentItem);
            final var cost = builder.sum(builder.prod(
                    builder.prod(b.get(BillOfMaterials_.quantity),
                                 builder.sum(builder.quot(b.get(BillOfMaterials_.scrapRate), 100), 1)),
                    c.get(Item_.standardCost)
            ));
            query.select(builder.array(p, cost, builder.diff(p.get(Item_.listPrice), cost)))
                    .where(builder.equal(p.get(Item_.itemType).get(ItemType_.typeCode), "FG"),
                           builder.isTrue(b.get(BillOfMaterials_.isActive)))
                    .groupBy(p)
                    .orderBy(builder.asc(p.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
