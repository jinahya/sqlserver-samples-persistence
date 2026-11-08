package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.purchasing;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrderLine;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrderLine_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrder_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.FROM_2015;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.TO_2015;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.decimalOf;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.longOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_PURCHASING_I_01} of
 * {@code src/test/sql-exercises/_Purchasing/02_intermediate.sql} with JPQL and with the Criteria
 * API, or with a native query where neither can.
 */
@DisplayName(
        "WWI_PURCHASING_I_01: The purchase orders and the amount ordered from each supplier in a " +
        "year")
class WWI_PURCHASING_I_01_PersistenceIT {

    private static final List<String> EXPECTED = List.of(
            "Fabrikam, Inc.,309,295332354.00",
            "Litware, Inc.,301,127101914.10"
    );

    private static String row(final Object[] row) {
        return row[0] + "," + longOf(row[1]) + "," + decimalOf(row[2]).setScale(2);
    }

    @Test
    void __QueryLanguage() {
        final var found =
                applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                 SELECT s.supplierName,
                                                                                                        COUNT(DISTINCT po) AS orders,
                                                                                                        SUM(pol.orderedOuters * pol.expectedUnitPricePerOuter) AS amount
                                                                                                 FROM PurchasingPurchaseOrderLine AS pol
                                                                                                      JOIN pol.purchaseOrder AS po
                                                                                                      JOIN po.supplier AS s
                                                                                                 WHERE po.orderDate >= :from
                                                                                                   AND po.orderDate < :to
                                                                                                 GROUP BY s.supplierId, s.supplierName
                                                                                                 ORDER BY amount DESC, s.supplierId""",
                                                                                         Object[].class)
                        .setParameter("from", FROM_2015)
                        .setParameter("to", TO_2015)
                        .getResultList()
                        .stream().map(WWI_PURCHASING_I_01_PersistenceIT::row).toList());
        assertThat(found).isEqualTo(EXPECTED);
    }

    @Test
    void __CriteriaApi() {
        final var found = applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var root = query.from(PurchaseOrderLine.class);
            final var order = root.join(PurchaseOrderLine_.purchaseOrder);
            final var supplier = order.join(PurchaseOrder_.supplier);
            final var amount =
                    builder.sum(builder.prod(root.get(PurchaseOrderLine_.orderedOuters),
                                             root.get(
                                                     PurchaseOrderLine_.expectedUnitPricePerOuter)));
            query.multiselect(supplier.get(Supplier_.supplierName),
                              builder.countDistinct(order), amount);
            query.where(builder.greaterThanOrEqualTo(order.get(PurchaseOrder_.orderDate),
                                                     FROM_2015),
                        builder.lessThan(order.get(PurchaseOrder_.orderDate), TO_2015));
            query.groupBy(supplier.get(Supplier_.supplierId),
                          supplier.get(Supplier_.supplierName));
            query.orderBy(builder.desc(amount),
                          builder.asc(supplier.get(Supplier_.supplierId)));
            return entityManager.createQuery(query).getResultList()
                    .stream().map(WWI_PURCHASING_I_01_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
