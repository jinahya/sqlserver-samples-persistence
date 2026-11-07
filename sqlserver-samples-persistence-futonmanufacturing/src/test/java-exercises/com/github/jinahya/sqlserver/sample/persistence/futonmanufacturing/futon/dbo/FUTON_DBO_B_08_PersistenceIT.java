package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.futon.dbo;

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesQuote;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo.SalesQuote_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.__Exercises_TestUtils.apply;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code FUTON_DBO_B_08} of {@code src/test/sql-exercises/_dbo/01_basic.sql} with JPQL and
 * with the Criteria API, or with a native query where neither can.
 */
@DisplayName("FUTON_DBO_B_08: the accepted quotes not turned into orders")
class FUTON_DBO_B_08_PersistenceIT {

    // no quote of the installed database is accepted and left without an order
    private void verify(final List<SalesQuote> list) {
        assertThat(list).isEmpty();
    }

    @Test
    void __QueryLanguage() {
        apply(entityManager -> {
            final var list = entityManager.createQuery(
                            """
                                    SELECT q
                                    FROM DboSalesQuote q
                                             JOIN q.customer c
                                    WHERE q.status = 'Accepted'
                                      AND q.convertedToOrder IS NULL
                                    ORDER BY q.quoteDate, q.quoteNumber""",
                            SalesQuote.class)
                    .getResultList();
            verify(list);
            return null;
        });
    }

    @Test
    void __CriteriaApi() {
        apply(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(SalesQuote.class);
            final var q = query.from(SalesQuote.class);
            q.join(SalesQuote_.customer);
            query.select(q)
                    .where(builder.equal(q.get(SalesQuote_.status), "Accepted"),
                           builder.isNull(q.get(SalesQuote_.convertedToOrder)))
                    .orderBy(builder.asc(q.get(SalesQuote_.quoteDate)),
                             builder.asc(q.get(SalesQuote_.quoteNumber)));
            final var list = entityManager.createQuery(query).getResultList();
            verify(list);
            return null;
        });
    }

}
