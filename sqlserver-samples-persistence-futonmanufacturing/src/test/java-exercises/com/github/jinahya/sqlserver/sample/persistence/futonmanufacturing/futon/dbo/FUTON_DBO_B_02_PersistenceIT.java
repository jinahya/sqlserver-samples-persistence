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
 * Answers {@code FUTON_DBO_B_02} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_02: the raw materials no item uses")
class FUTON_DBO_B_02_PersistenceIT {

    private void verify(final List<Item> list) {
        assertThat(list).extracting(Item::getItemCode)
                .containsExactly("RM-FAB-007", "RM-FAB-008", "RM-HARD-002");
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery("""
                                                               SELECT i
                                                               FROM DboItem i
                                                               WHERE i.itemType.typeCode = :typeCode
                                                                 AND NOT EXISTS (SELECT b
                                                                                 FROM DboBillOfMaterials b
                                                                                 WHERE b.componentItem = i
                                                                                   AND b.isActive = TRUE)
                                                               ORDER BY i.itemCode""", Item.class)
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
            final var query = builder.createQuery(Item.class);
            final var i = query.from(Item.class);
            final var subquery = query.subquery(BillOfMaterials.class);
            final var b = subquery.from(BillOfMaterials.class);
            subquery.select(b).where(
                    builder.equal(b.get(BillOfMaterials_.componentItem), i),
                    builder.isTrue(b.get(BillOfMaterials_.isActive))
            );
            query.select(i).where(
                    builder.equal(i.get(Item_.itemType).get(ItemType_.typeCode), "RAW"),
                    builder.not(builder.exists(subquery))
            );
            query.orderBy(builder.asc(i.get(Item_.itemCode)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
