package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.production;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductCategory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductSubcategory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PRODUCTION_B_01} of {@code src/test/sql-exercises/_Production/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_PRODUCTION_B_01: The products on sale of each category, and their list prices")
class AW_PRODUCTION_B_01_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "Accessories | 22 | 2.29 | 159",
                "Bikes | 60 | 539.99 | 2443.35",
                "Clothing | 20 | 8.99 | 69.99",
                "Components | 95 | 20.24 | 1431.5"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT c.name, COUNT(p), MIN(p.listPrice), MAX(p.listPrice)
                        FROM ProductionProduct p
                                 JOIN p.productSubcategory s
                                 JOIN s.productCategory c
                        WHERE p.sellEndDate IS NULL
                        GROUP BY c.productCategoryId, c.name
                        ORDER BY c.name""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var p = query.from(Product.class);
            final var c = p.join(Product_.productSubcategory)
                    .join(ProductSubcategory_.productCategory);
            query.select(builder.array(c.get(ProductCategory_.name), builder.count(p),
                                       builder.min(p.get(Product_.listPrice)),
                                       builder.max(p.get(Product_.listPrice))));
            query.where(builder.isNull(p.get(Product_.sellEndDate)));
            query.groupBy(c.get(ProductCategory_.productCategoryId),
                          c.get(ProductCategory_.name));
            query.orderBy(builder.asc(c.get(ProductCategory_.name)));
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
