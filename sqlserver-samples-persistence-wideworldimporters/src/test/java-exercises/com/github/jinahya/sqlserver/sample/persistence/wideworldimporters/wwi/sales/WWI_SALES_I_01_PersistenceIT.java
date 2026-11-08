package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.InvoiceLine;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.InvoiceLine_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem_;
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
 * Answers {@code WWI_SALES_I_01} of {@code src/test/sql-exercises/_Sales/02_intermediate.sql} with
 * JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_SALES_I_01: The five stock items which earned the most in a year")
class WWI_SALES_I_01_PersistenceIT {

    private static final List<String> EXPECTED = List.of(
            "215,1747,3815185.95",
            "161,19380,2406996.00",
            "173,19320,2332890.00",
            "170,19740,2315502.00",
            "167,20320,2313432.00"
    );

    private static String row(final Object[] row) {
        return row[0] + "," + longOf(row[2]) + "," + decimalOf(row[3]).setScale(2);
    }

    @Test
    void __QueryLanguage() {
        final var found =
                applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                 SELECT si.stockItemId, si.stockItemName, SUM(il.quantity), SUM(il.extendedPrice) AS revenue
                                                                                                 FROM SalesInvoiceLine AS il
                                                                                                      JOIN il.invoice AS i
                                                                                                      JOIN il.stockItem AS si
                                                                                                 WHERE i.invoiceDate >= :from
                                                                                                   AND i.invoiceDate < :to
                                                                                                 GROUP BY si.stockItemId, si.stockItemName
                                                                                                 ORDER BY revenue DESC, si.stockItemId""",
                                                                                         Object[].class)
                        .setParameter("from", FROM_2015)
                        .setParameter("to", TO_2015)
                        .setMaxResults(5)
                        .getResultList()
                        .stream().map(WWI_SALES_I_01_PersistenceIT::row).toList());
        assertThat(found).isEqualTo(EXPECTED);
    }

    @Test
    void __CriteriaApi() {
        final var found = applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Object[].class);
            final var root = query.from(InvoiceLine.class);
            final var invoice = root.join(InvoiceLine_.invoice);
            final var item = root.join(InvoiceLine_.stockItem);
            final var revenue = builder.sum(root.get(InvoiceLine_.extendedPrice));
            query.multiselect(item.get(StockItem_.stockItemId),
                              item.get(StockItem_.stockItemName),
                              builder.sum(root.get(InvoiceLine_.quantity)), revenue);
            query.where(
                    builder.greaterThanOrEqualTo(invoice.get(Invoice_.invoiceDate), FROM_2015),
                    builder.lessThan(invoice.get(Invoice_.invoiceDate), TO_2015));
            query.groupBy(item.get(StockItem_.stockItemId), item.get(StockItem_.stockItemName));
            query.orderBy(builder.desc(revenue), builder.asc(item.get(StockItem_.stockItemId)));
            return entityManager.createQuery(query).setMaxResults(5).getResultList()
                    .stream().map(WWI_SALES_I_01_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
