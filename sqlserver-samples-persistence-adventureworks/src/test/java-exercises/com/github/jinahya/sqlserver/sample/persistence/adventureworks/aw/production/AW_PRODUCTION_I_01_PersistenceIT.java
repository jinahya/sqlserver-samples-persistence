package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.production;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PRODUCTION_I_01} of
 * {@code src/test/sql-exercises/_Production/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName(
        "AW_PRODUCTION_I_01: The products whose stock is less than half their safety stock level")
class AW_PRODUCTION_I_01_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "853 | Women's Tights, M | 4 | 0",
                "859 | Half-Finger Gloves, M | 4 | 0",
                "876 | Hitch Rack - 4-Bike | 4 | 0",
                "882 | Short-Sleeve Classic Jersey, M | 4 | 0"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT p.productId, p.name, p.safetyStockLevel, SUM(i.quantity)
                        FROM ProductionProductInventory i
                                 JOIN i.product p
                        GROUP BY p.productId, p.name, p.safetyStockLevel
                        HAVING SUM(i.quantity) * 2 < p.safetyStockLevel
                        ORDER BY SUM(i.quantity), p.productId""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var i = query.from(ProductInventory.class);
            final var p = i.join(ProductInventory_.product);
            final var quantity = builder.sum(i.get(ProductInventory_.quantity));
            query.multiselect(p.get(Product_.productId), p.get(Product_.name),
                              p.get(Product_.safetyStockLevel),
                              quantity);
            query.groupBy(p.get(Product_.productId), p.get(Product_.name),
                          p.get(Product_.safetyStockLevel));
            query.having(
                    builder.lt(builder.prod(quantity, 2), p.get(Product_.safetyStockLevel)));
            query.orderBy(builder.asc(quantity), builder.asc(p.get(Product_.productId)));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
