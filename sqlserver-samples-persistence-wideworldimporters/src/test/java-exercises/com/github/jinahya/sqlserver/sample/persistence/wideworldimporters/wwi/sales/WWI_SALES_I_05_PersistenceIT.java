package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.SpecialDeal;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.SpecialDeal_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_I_05} of {@code src/test/sql-exercises/_Sales/02_intermediate.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_SALES_I_05: The special deals in force on a date")
class WWI_SALES_I_05_PersistenceIT {

    private static final LocalDate ON = LocalDate.of(2016, 1, 1);

    private static String row(final SpecialDeal d) {
        return String.join(
                ",",
                String.valueOf(d.getSpecialDealId()), d.getDealDescription(),
                String.valueOf(d.getStartDate()), String.valueOf(d.getEndDate()),
                Optional.ofNullable(d.getStockItem()).map(v -> v.getStockItemName()).orElse(null),
                Optional.ofNullable(d.getStockGroup()).map(v -> v.getStockGroupName()).orElse(null),
                Optional.ofNullable(d.getCustomer()).map(v -> v.getCustomerName()).orElse(null),
                Optional.ofNullable(d.getBuyingGroup()).map(v -> v.getBuyingGroupName())
                        .orElse(null),
                Optional.ofNullable(d.getCustomerCategory())
                        .map(v -> v.getCustomerCategoryName()).orElse(null),
                String.valueOf(d.getDiscountAmount()),
                Optional.ofNullable(d.getDiscountPercentage())
                        .map(v -> v.stripTrailingZeros().toPlainString()).orElse(null),
                String.valueOf(d.getUnitPrice())
        );
    }

    // reads the lazy associations, so it runs in the entity manager
    private void verify(final List<SpecialDeal> found) {
        assertThat(found).extracting(WWI_SALES_I_05_PersistenceIT::row).containsExactly(
                "1,10% 1st qtr USB Wingtip,2016-01-01,2016-03-31,null,USB Novelties,null,"
                + "Wingtip Toys,null,null,10,null"
        );
    }

    @Test
    void __QueryLanguage() {
        applyInTransactionAndRollback(entityManager -> {
            verify(entityManager.createQuery(
                            """
                                    SELECT d
                                    FROM SalesSpecialDeal d
                                    WHERE d.startDate <= :on
                                      AND d.endDate >= :on
                                    ORDER BY d.specialDealId""",
                            SpecialDeal.class)
                           .setParameter("on", ON)
                           .getResultList());
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(SpecialDeal.class);
            final var d = query.from(SpecialDeal.class);
            query.select(d)
                    .where(builder.lessThanOrEqualTo(d.get(SpecialDeal_.startDate), ON),
                           builder.greaterThanOrEqualTo(d.get(SpecialDeal_.endDate), ON))
                    .orderBy(builder.asc(d.get(SpecialDeal_.specialDealId)));
            verify(entityManager.createQuery(query).getResultList());
            return null;
        });
    }

}
