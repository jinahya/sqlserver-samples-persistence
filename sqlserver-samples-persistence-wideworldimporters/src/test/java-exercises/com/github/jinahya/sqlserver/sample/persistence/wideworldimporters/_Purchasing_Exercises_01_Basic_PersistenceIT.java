package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierCategory_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierTransaction_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierTransaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Purchasing/01_basic.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Purchasing_Exercises_01_Basic_PersistenceIT {

    // ------------------------------------------------------------------------- WWI_PURCHASING_B_01
    @DisplayName("WWI_PURCHASING_B_01: the suppliers, by category and then by name")
    @Nested
    class WWI_PURCHASING_B_01_Test {

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

    // ------------------------------------------------------------------------- WWI_PURCHASING_B_02
    @DisplayName("WWI_PURCHASING_B_02: what is still owed to each supplier")
    @Nested
    class WWI_PURCHASING_B_02_Test {

        private static final List<String> EXPECTED = List.of(
                "Fabrikam, Inc.,1,1871894.10",
                "Litware, Inc.,1,849784.11"
        );

        private static String row(final Object[] row) {
            return row[0] + "," + longOf(row[1]) + "," + decimalOf(row[2]).setScale(2);
        }

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT s.supplierName, COUNT(st) AS transactions, SUM(st.outstandingBalance) AS outstanding
                                                                                                     FROM PurchasingSupplierTransaction AS st
                                                                                                          JOIN st.supplier AS s
                                                                                                     WHERE st.isFinalized = false
                                                                                                     GROUP BY s.supplierId, s.supplierName
                                                                                                     ORDER BY outstanding DESC""",
                                                                                             Object[].class)
                            .getResultList()
                            .stream().map(WWI_PURCHASING_B_02_Test::row).toList());
            assertThat(found).isEqualTo(EXPECTED);
        }

        @Test
        void __CriteriaApi() {
            final var found = applyInTransactionAndRollback(entityManager -> {
                final var builder = entityManager.getCriteriaBuilder();
                final var query = builder.createQuery(Object[].class);
                final var root = query.from(SupplierTransaction.class);
                final var supplier = root.join(SupplierTransaction_.supplier);
                final var outstanding =
                        builder.sum(root.get(SupplierTransaction_.outstandingBalance));
                query.multiselect(supplier.get(Supplier_.supplierName), builder.count(root),
                                  outstanding);
                query.where(builder.isFalse(root.get(SupplierTransaction_.isFinalized)));
                query.groupBy(supplier.get(Supplier_.supplierId),
                              supplier.get(Supplier_.supplierName));
                query.orderBy(builder.desc(outstanding));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(WWI_PURCHASING_B_02_Test::row).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }
}
