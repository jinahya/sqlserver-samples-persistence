package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ScrapReason_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.WorkOrder;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.WorkOrder_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Production/02_intermediate.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S100", // Method names should comply with a naming convention
        "java:S101"  // Class names should comply with a naming convention
})
class _Production_Exercises_02_Intermediate_PersistenceIT {

    @Nested
    class AW_PRODUCTION_I_01_Test {

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

    @DisplayName("AW_PRODUCTION_I_02: the five scrap reasons with the most scrapped quantity")
    @Nested
    class AW_PRODUCTION_I_02_Test {

        private void verify(final List<Object[]> found) {
            assertThat(rows(found)).containsExactly(
                    "Paint process failed | 37 | 1271",
                    "Trim length too long | 52 | 981",
                    "Thermoform temperature too low | 63 | 876",
                    "Drill size too small | 44 | 826",
                    "Wheel misaligned | 51 | 804"
            );
        }

        @Test
        void __QueryLanguage() {
            verify(apply(entityManager -> entityManager.createQuery(
                    """
                            SELECT r.name, COUNT(w), SUM(w.scrappedQty)
                            FROM ProductionWorkOrder w
                                     JOIN w.scrapReason r
                            GROUP BY r.scrapReasonId, r.name
                            ORDER BY SUM(w.scrappedQty) DESC""",
                    Object[].class
            ).setMaxResults(5).getResultList()));
        }

        @Test
        void __CriteriaApi() {
            verify(apply(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var w = query.from(WorkOrder.class);
                final var r = w.join(WorkOrder_.scrapReason);
                final var scrappedQty = builder.sum(w.get(WorkOrder_.scrappedQty));
                query.multiselect(r.get(ScrapReason_.name), builder.count(w), scrappedQty);
                query.groupBy(r.get(ScrapReason_.scrapReasonId), r.get(ScrapReason_.name));
                query.orderBy(builder.desc(scrappedQty));
                return entityManager.createQuery(query).setMaxResults(5).getResultList();
            }));
        }
    }

    @DisplayName("AW_PRODUCTION_I_03: The ten products whose work orders finish late the most")
    @Nested
    class AW_PRODUCTION_I_03_Test {
    }
}
