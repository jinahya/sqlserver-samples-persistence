package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person_;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.__Exercises_TestUtils.applyInTransactionAndRollback;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Application/01_basic.sql}, against the installed
 * {@code WideWorldImporters} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
class _Application_Exercises_01_Basic_PersistenceIT {

    // ------------------------------------------------------------------------ WWI_APPLICATION_B_01
    @DisplayName("WWI_APPLICATION_B_01: the salespeople, by name")
    @Nested
    class WWI_APPLICATION_B_01_Test {

        private static final List<Integer> EXPECTED = List.of(7, 8, 16, 13, 3, 20, 2, 14, 6, 15);

        @Test
        void __QueryLanguage() {
            final var found =
                    applyInTransactionAndRollback(entityManager -> entityManager.createQuery("""
                                                                                                     SELECT p
                                                                                                     FROM ApplicationPerson AS p
                                                                                                     WHERE p.isSalesperson = true
                                                                                                     ORDER BY p.fullName""",
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
                query.orderBy(builder.asc(root.get(Person_.fullName)));
                return entityManager.createQuery(query).getResultList()
                        .stream().map(Person::getPersonId).toList();
            });
            assertThat(found).isEqualTo(EXPECTED);
        }
    }

    @DisplayName("WWI_APPLICATION_B_02: The sales territories of a country")
    @Nested
    class WWI_APPLICATION_B_02_Test {
    }

    @DisplayName("WWI_APPLICATION_B_03: The cities of a country's states and provinces")
    @Nested
    class WWI_APPLICATION_B_03_Test {
    }
}
