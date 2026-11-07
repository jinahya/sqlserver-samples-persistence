package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.sales;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Order_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_SALES_A_03} of {@code src/test/sql-exercises/_Sales/03_advanced.sql} with JPQL
 * and with the Criteria API, or with a native query where neither can.
 */
@DisplayName(
        "WWI_SALES_A_03: The five oldest backorders never invoiced, and the orders which " +
        "backordered them")
class WWI_SALES_A_03_PersistenceIT {

    private static final List<String> EXPECTED = List.of(
            "694,Wingtip Toys (Lost River, ID),684",
            "858,Tailspin Toys (Magalia, CA),768",
            "863,Wingtip Toys (Deeson, MS),789",
            "865,Richard Friar,807",
            "1065,Tailspin Toys (New Baden, IL),1063"
    );

    private static String row(final Order original) {
        final var backorder = original.getBackorderOrder();
        return backorder.getOrderId() + "," + backorder.getCustomer().getCustomerName() + ","
               + original.getOrderId();
    }

    @Test
    void __QueryLanguage() {
        final var found =
                applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                 SELECT p
                                                                                                 FROM SalesOrder AS p
                                                                                                      JOIN p.backorderOrder AS o
                                                                                                 WHERE NOT EXISTS (SELECT i
                                                                                                                   FROM SalesInvoice AS i
                                                                                                                   WHERE i.order = o)
                                                                                                 ORDER BY o.orderDate, o.orderId""",
                                                                                         Order.class)
                        .setMaxResults(5)
                        .getResultList()
                        .stream().map(WWI_SALES_A_03_PersistenceIT::row).toList());
        assertThat(found).isEqualTo(EXPECTED);
    }

    @Test
    void __CriteriaApi() {
        final var found = applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Order.class);
            final var root = query.from(Order.class);
            final var backorder = root.join(Order_.backorderOrder);
            final var subquery = query.subquery(Invoice.class);
            final var invoice = subquery.from(Invoice.class);
            subquery.select(invoice)
                    .where(builder.equal(invoice.get(Invoice_.order), backorder));
            query.select(root);
            query.where(builder.not(builder.exists(subquery)));
            query.orderBy(builder.asc(backorder.get(Order_.orderDate)),
                          builder.asc(backorder.get(Order_.orderId)));
            return entityManager.createQuery(query).setMaxResults(5).getResultList()
                    .stream().map(WWI_SALES_A_03_PersistenceIT::row).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
