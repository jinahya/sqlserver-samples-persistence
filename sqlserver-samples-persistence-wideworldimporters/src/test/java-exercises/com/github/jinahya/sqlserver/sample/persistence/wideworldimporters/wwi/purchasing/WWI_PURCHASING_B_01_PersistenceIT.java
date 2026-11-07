package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.purchasing;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierCategory_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_PURCHASING_B_01} of {@code src/test/sql-exercises/_Purchasing/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_PURCHASING_B_01: The suppliers, by category and then by name")
class WWI_PURCHASING_B_01_PersistenceIT {

    private static final List<Integer> EXPECTED =
            List.of(4, 3, 13, 6, 11, 1, 2, 5, 8, 9, 12, 7, 10);

    @Test
    void __QueryLanguage() {
        final var found =
                applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                 SELECT s
                                                                                                 FROM PurchasingSupplier AS s
                                                                                                      JOIN s.supplierCategory AS sc
                                                                                                 ORDER BY sc.supplierCategoryName, s.supplierName""",
                                                                                         Supplier.class)
                        .getResultList()
                        .stream().map(Supplier::getSupplierId).toList());
        assertThat(found).isEqualTo(EXPECTED);
    }

    @Test
    void __CriteriaApi() {
        final var found = applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Supplier.class);
            final var root = query.from(Supplier.class);
            final var category = root.join(Supplier_.supplierCategory);
            query.select(root);
            query.orderBy(builder.asc(category.get(SupplierCategory_.supplierCategoryName)),
                          builder.asc(root.get(Supplier_.supplierName)));
            return entityManager.createQuery(query).getResultList()
                    .stream().map(Supplier::getSupplierId).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
