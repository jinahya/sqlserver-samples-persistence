package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.production;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductListPriceHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductListPriceHistory_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.row;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_PRODUCTION_B_02} of {@code src/test/sql-exercises/_Production/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_PRODUCTION_B_02: The list price of a product on a date")
class AW_PRODUCTION_B_02_PersistenceIT {

    private static final int PRODUCT_ID = 707;

    private static final LocalDateTime ON_DATE = LocalDate.of(2024, 1, 1).atStartOfDay();

    private void verify(final List<ProductListPriceHistory> found) {
        assertThat(found).extracting(h -> row(h.getProduct().getProductId(), h.getListPrice(),
                                              h.getStartDate().toLocalDate(),
                                              h.getEndDate().toLocalDate()))
                .containsExactly(
                        "707 | 33.6442 | 2023-05-30 | 2024-05-28"
                );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                        """
                                SELECT h
                                FROM ProductionProductListPriceHistory h
                                WHERE h.productId = :productId
                                  AND h.startDate <= :onDate
                                  AND (h.endDate IS NULL OR h.endDate >= :onDate)""",
                        ProductListPriceHistory.class
                )
                .setParameter("productId", PRODUCT_ID)
                .setParameter("onDate", ON_DATE)
                .getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(ProductListPriceHistory.class);
            final var h = query.from(ProductListPriceHistory.class);
            final var endDate = h.get(ProductListPriceHistory_.endDate);
            query.where(
                    builder.equal(h.get(ProductListPriceHistory_.productId), PRODUCT_ID),
                    builder.lessThanOrEqualTo(h.get(ProductListPriceHistory_.startDate), ON_DATE),
                    builder.or(builder.isNull(endDate),
                               builder.greaterThanOrEqualTo(endDate, ON_DATE))
            );
            return entityManager.createQuery(query).getResultList();
        }));
    }

}
