package com.github.jinahya.sqlserver.sample.persistence.adventureworks.aw.production;

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.ProductInventory_;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product_;
import jakarta.persistence.criteria.JoinType;
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
                "680 | HL Road Frame - Black, 58 | 500 | 0",
                "706 | HL Road Frame - Red, 58 | 500 | 0",
                "717 | HL Road Frame - Red, 62 | 500 | 0",
                "718 | HL Road Frame - Red, 44 | 500 | 0",
                "719 | HL Road Frame - Red, 48 | 500 | 0",
                "720 | HL Road Frame - Red, 52 | 500 | 0",
                "721 | HL Road Frame - Red, 56 | 500 | 0",
                "722 | LL Road Frame - Black, 58 | 500 | 0",
                "723 | LL Road Frame - Black, 60 | 500 | 0",
                "724 | LL Road Frame - Black, 62 | 500 | 0",
                "725 | LL Road Frame - Red, 44 | 500 | 0",
                "726 | LL Road Frame - Red, 48 | 500 | 0",
                "727 | LL Road Frame - Red, 52 | 500 | 0",
                "728 | LL Road Frame - Red, 58 | 500 | 0",
                "729 | LL Road Frame - Red, 60 | 500 | 0",
                "730 | LL Road Frame - Red, 62 | 500 | 0",
                "731 | ML Road Frame - Red, 44 | 500 | 0",
                "732 | ML Road Frame - Red, 48 | 500 | 0",
                "733 | ML Road Frame - Red, 52 | 500 | 0",
                "734 | ML Road Frame - Red, 58 | 500 | 0",
                "735 | ML Road Frame - Red, 60 | 500 | 0",
                "736 | LL Road Frame - Black, 44 | 500 | 0",
                "737 | LL Road Frame - Black, 48 | 500 | 0",
                "738 | LL Road Frame - Black, 52 | 500 | 0",
                "739 | HL Mountain Frame - Silver, 42 | 500 | 0",
                "740 | HL Mountain Frame - Silver, 44 | 500 | 0",
                "741 | HL Mountain Frame - Silver, 48 | 500 | 0",
                "742 | HL Mountain Frame - Silver, 46 | 500 | 0",
                "743 | HL Mountain Frame - Black, 42 | 500 | 0",
                "744 | HL Mountain Frame - Black, 44 | 500 | 0",
                "745 | HL Mountain Frame - Black, 48 | 500 | 0",
                "746 | HL Mountain Frame - Black, 46 | 500 | 0",
                "830 | ML Mountain Frame - Black, 40 | 500 | 0",
                "831 | ML Mountain Frame - Black, 44 | 500 | 0",
                "832 | ML Mountain Frame - Black, 48 | 500 | 0",
                "833 | ML Road Frame-W - Yellow, 40 | 500 | 0",
                "834 | ML Road Frame-W - Yellow, 42 | 500 | 0",
                "835 | ML Road Frame-W - Yellow, 44 | 500 | 0",
                "836 | ML Road Frame-W - Yellow, 48 | 500 | 0",
                "837 | HL Road Frame - Black, 62 | 500 | 0",
                "838 | HL Road Frame - Black, 44 | 500 | 0",
                "839 | HL Road Frame - Black, 48 | 500 | 0",
                "840 | HL Road Frame - Black, 52 | 500 | 0",
                "853 | Women's Tights, M | 4 | 0",
                "859 | Half-Finger Gloves, M | 4 | 0",
                "876 | Hitch Rack - 4-Bike | 4 | 0",
                "882 | Short-Sleeve Classic Jersey, M | 4 | 0",
                "885 | HL Touring Frame - Yellow, 60 | 500 | 0",
                "886 | LL Touring Frame - Yellow, 62 | 500 | 0",
                "887 | HL Touring Frame - Yellow, 46 | 500 | 0",
                "888 | HL Touring Frame - Yellow, 50 | 500 | 0",
                "889 | HL Touring Frame - Yellow, 54 | 500 | 0",
                "890 | HL Touring Frame - Blue, 46 | 500 | 0",
                "891 | HL Touring Frame - Blue, 50 | 500 | 0",
                "892 | HL Touring Frame - Blue, 54 | 500 | 0",
                "893 | HL Touring Frame - Blue, 60 | 500 | 0",
                "895 | LL Touring Frame - Blue, 50 | 500 | 0",
                "896 | LL Touring Frame - Blue, 54 | 500 | 0",
                "897 | LL Touring Frame - Blue, 58 | 500 | 0",
                "898 | LL Touring Frame - Blue, 62 | 500 | 0",
                "899 | LL Touring Frame - Yellow, 44 | 500 | 0",
                "900 | LL Touring Frame - Yellow, 50 | 500 | 0",
                "901 | LL Touring Frame - Yellow, 54 | 500 | 0",
                "902 | LL Touring Frame - Yellow, 58 | 500 | 0",
                "903 | LL Touring Frame - Blue, 44 | 500 | 0",
                "904 | ML Mountain Frame-W - Silver, 40 | 500 | 0",
                "905 | ML Mountain Frame-W - Silver, 42 | 500 | 0",
                "906 | ML Mountain Frame-W - Silver, 46 | 500 | 0",
                "917 | LL Mountain Frame - Silver, 42 | 500 | 0",
                "918 | LL Mountain Frame - Silver, 44 | 500 | 0",
                "919 | LL Mountain Frame - Silver, 48 | 500 | 0",
                "920 | LL Mountain Frame - Silver, 52 | 500 | 0",
                "924 | LL Mountain Frame - Black, 42 | 500 | 0",
                "925 | LL Mountain Frame - Black, 44 | 500 | 0",
                "926 | LL Mountain Frame - Black, 48 | 500 | 0",
                "927 | LL Mountain Frame - Black, 52 | 500 | 0"
        );
    }

    @Test
    void __QueryLanguage() {
        verify(apply(entityManager -> entityManager.createQuery(
                """
                        SELECT p.productId, p.name, p.safetyStockLevel, COALESCE(SUM(i.quantity), 0)
                        FROM ProductionProduct p
                                 LEFT JOIN ProductionProductInventory i ON i.product = p
                        GROUP BY p.productId, p.name, p.safetyStockLevel
                        HAVING COALESCE(SUM(i.quantity), 0) * 2 < p.safetyStockLevel
                        ORDER BY COALESCE(SUM(i.quantity), 0), p.productId""",
                Object[].class
        ).getResultList()));
    }

    @Test
    void __CriteriaApi() {
        verify(apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var p = query.from(Product.class);
            // Product has no association to ProductInventory; an entity join with ON
            final var i = p.join(ProductInventory.class, JoinType.LEFT);
            i.on(builder.equal(i.get(ProductInventory_.product), p));
            final var quantity =
                    builder.coalesce(builder.sum(i.get(ProductInventory_.quantity)), 0);
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
