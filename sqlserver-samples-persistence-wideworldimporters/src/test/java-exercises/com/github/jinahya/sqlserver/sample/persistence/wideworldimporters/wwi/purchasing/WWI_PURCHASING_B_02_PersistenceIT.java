package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.purchasing;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.SupplierTransaction_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_PURCHASING_B_02} of {@code src/test/sql-exercises/_Purchasing/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_PURCHASING_B_02: What is still owed to each supplier")
class WWI_PURCHASING_B_02_PersistenceIT {

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
                                                                                                 ORDER BY outstanding DESC, s.supplierName""",
                                                                                         Object[].class)
                        .getResultList()
                        .stream().map(WWI_PURCHASING_B_02_PersistenceIT::row).toList());
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
            query.select(builder.array(supplier.get(Supplier_.supplierName), builder.count(root),
                                       outstanding));
            query.where(builder.isFalse(root.get(SupplierTransaction_.isFinalized)));
            query.groupBy(supplier.get(Supplier_.supplierId),
                          supplier.get(Supplier_.supplierName));
            query.orderBy(builder.desc(outstanding),
                          builder.asc(supplier.get(Supplier_.supplierName)));
            return entityManager.createQuery(query).getResultList()
                    .stream().map(WWI_PURCHASING_B_02_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
