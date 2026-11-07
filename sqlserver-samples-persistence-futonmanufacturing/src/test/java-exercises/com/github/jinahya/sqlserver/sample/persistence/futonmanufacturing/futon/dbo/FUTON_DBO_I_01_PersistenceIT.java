package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.BillOfMaterials_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.ItemType_;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.Item_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_I_01} of {@code src/test/sql-exercises/_dbo/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_I_01: the raw materials which four or more items use")
class FUTON_DBO_I_01_PersistenceIT {

    private void verify(final List<Object[]> list) {
        assertThat(list).extracting(r -> ((Item) r[0]).getItemCode())
                .containsExactly("RM-FIN-003", "RM-HARD-001", "RM-METAL-001", "RM-METAL-002", "RM-WOOD-001",
                                 "RM-WOOD-003");
        assertThat(list).extracting(r -> r[1])
                .containsExactly(5L, 5L, 5L, 5L, 4L, 4L);
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT c, COUNT(b) AS parents
                                                               FROM DboBillOfMaterials b
                                                                    JOIN b.componentItem c
                                                               WHERE c.itemType.typeCode = :typeCode
                                                                 AND b.isActive = TRUE
                                                               GROUP BY c
                                                               HAVING COUNT(b) >= 4
                                                               ORDER BY parents DESC, c.itemCode""", Object[].class)
                    .setParameter("typeCode", "RAW")
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
            final var c = b.join(BillOfMaterials_.componentItem);
            final var parents = builder.count(b);
            query.multiselect(c, parents)
                    .where(builder.equal(c.get(Item_.itemType).get(ItemType_.typeCode), "RAW"),
                           builder.isTrue(b.get(BillOfMaterials_.isActive)))
                    .groupBy(c)
                    .having(builder.ge(parents, 4L))
                    .orderBy(builder.desc(parents), builder.asc(c.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
