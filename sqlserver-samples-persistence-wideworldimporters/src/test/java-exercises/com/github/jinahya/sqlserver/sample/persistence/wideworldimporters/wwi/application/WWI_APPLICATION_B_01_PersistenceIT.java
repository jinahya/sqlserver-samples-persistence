package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.wwi.application;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person_;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers {@code WWI_APPLICATION_B_01} of {@code src/test/sql-exercises/_Application/01_basic.sql}
 * with JPQL and with the Criteria API, or with a native query where neither can.
 */
@DisplayName("WWI_APPLICATION_B_01: The salespeople, by name")
class WWI_APPLICATION_B_01_PersistenceIT {

    private static final List<Integer> EXPECTED = List.of(7, 8, 16, 13, 3, 20, 2, 14, 6, 15);

    @Test
    void __QueryLanguage() {
        final var found =
                applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                 SELECT p
                                                                                                 FROM ApplicationPerson AS p
                                                                                                 WHERE p.isSalesperson = true
                                                                                                 ORDER BY p.fullName, p.personId""",
                                                                                         Person.class)
                        .getResultList()
                        .stream().map(Person::getPersonId).toList());
        assertThat(found).isEqualTo(EXPECTED);
    }

    @Test
    void __CriteriaApi() {
        final var found = applyInTransactionAndRollback(entityManager -> {
            final var builder = entityManager.getCriteriaBuilder();
            final var query = builder.createQuery(Person.class);
            final var root = query.from(Person.class);
            query.select(root);
            query.where(builder.isTrue(root.get(Person_.isSalesperson)));
            query.orderBy(builder.asc(root.get(Person_.fullName)),
                          builder.asc(root.get(Person_.personId)));
            return entityManager.createQuery(query).getResultList()
                    .stream().map(Person::getPersonId).toList();
        });
        assertThat(found).isEqualTo(EXPECTED);
    }

}
