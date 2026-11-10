package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.sales;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesOrderDetail_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SpecialOfferProduct_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.apply;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Exercises_TestUtils.rows;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code AW_SALES_I_02} of {@code src/test/sql-exercises/_Sales/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("AW_SALES_I_02: The five products which brought in the most")
class AW_SALES_I_02_PersistenceIT {

    private void verify(final List<Object[]> found) {
        assertThat(rows(found)).containsExactly(
                "BK-M68B-38 | Mountain-200 Black, 38 | 2977 | 4400592.8004",
                "BK-M68B-42 | Mountain-200 Black, 42 | 2664 | 4009494.761841",
                "BK-M68S-38 | Mountain-200 Silver, 38 | 2394 | 3693678.025272",
                "BK-M68S-42 | Mountain-200 Silver, 42 | 2234 | 3438478.860423",
                "BK-M68S-46 | Mountain-200 Silver, 46 | 2216 | 3434256.941928"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT p.productNumber, p.name, SUM(d.orderQty), SUM(d.lineTotal)
                        FROM SalesSalesOrderDetail d
                                 JOIN d.specialOfferProduct sop
                                 JOIN sop.product p
                        GROUP BY p.productId, p.productNumber, p.name
                        ORDER BY SUM(d.lineTotal) DESC, p.productId""",
                Object[].class
        ).setMaxResults(5).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var d = query.from(SalesOrderDetail.class);
            final var p = d.join(SalesOrderDetail_.specialOfferProduct)
                    .join(SpecialOfferProduct_.product);
            final var lineTotal = builder.sum(d.get(SalesOrderDetail_.lineTotal));
            query.select(builder.array(p.get(Product_.productNumber), p.get(Product_.name),
                                       builder.sum(d.get(SalesOrderDetail_.orderQty)), lineTotal));
            query.groupBy(p.get(Product_.productId), p.get(Product_.productNumber),
                          p.get(Product_.name));
            query.orderBy(builder.desc(lineTotal), builder.asc(p.get(Product_.productId)));
            return entityManager.createQuery(query).setMaxResults(5).getResultList();
        }));
    }

}
