package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.ProductVendor;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.ProductVendor_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing.Vendor_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Purchasing/01_basic.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Purchasing_Exercises_01_Basic_PersistenceIT {

    @DisplayName("AW_PURCHASING_B_01: the vendors of a product, the cheapest first")
    @Nested
    class AW_PURCHASING_B_01_Test {

        private static final int PRODUCT_ID = 322;

        private void verify(final List<ProductVendor> found) {
            assertThat(found)
                    .extracting(pv -> row(pv.getBusinessEntity().getName(), pv.getStandardPrice(),
                                          pv.getAverageLeadTime(), pv.getMinOrderQty()))
                    .containsExactly(
                            "Beaumont Bikes | 24.21 | 17 | 20",
                            "Bike Satellite Inc. | 25.11 | 17 | 20",
                            "Training Systems | 27.33 | 19 | 20"
                    );
        }

        @Test
        void __QueryLanguage() {
            apply(entityManager -> {
                verify(entityManager.createQuery(
                        """
                                SELECT pv
                                FROM PurchasingProductVendor pv
                                         JOIN pv.businessEntity v
                                WHERE pv.productId = :productId
                                ORDER BY pv.standardPrice, v.name""",
                        ProductVendor.class
                ).setParameter("productId", PRODUCT_ID).getResultList());
                return null;
            });
        }

        @Test
        void __CriteriaApi() {
            apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(ProductVendor.class);
                final var pv = query.from(ProductVendor.class);
                final var v = pv.join(ProductVendor_.businessEntity);
                query.select(pv);
                query.where(builder.equal(pv.get(ProductVendor_.productId), PRODUCT_ID));
                query.orderBy(builder.asc(pv.get(ProductVendor_.standardPrice)),
                              builder.asc(v.get(Vendor_.name)));
                verify(entityManager.createQuery(query).getResultList());
                return null;
            });
        }
    }

    @DisplayName("AW_PURCHASING_B_02: The purchase orders of each status")
    @Nested
    class AW_PURCHASING_B_02_Test {
    }
}
