package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.function.Function;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.___PersistenceIT_Utils.applyEntityManagerFactory;

/**
 * Helpers for the {@code _<Schema>_Exercises_*_PersistenceIT} classes, which answer the exercises of
 * {@code src/test/sql-exercises} against the installed {@code WideWorldImporters} database.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class __Exercises_TestUtils {

    static final LocalDate FROM_2015 = LocalDate.of(2015, 1, 1);

    static final LocalDate TO_2015 = LocalDate.of(2016, 1, 1);

    static long longOf(final Object value) {
        return ((Number) value).longValue();
    }

    static BigDecimal decimalOf(final Object value) {
        return new BigDecimal(value.toString());
    }

    static <R> R applyInTransactionAndRollback(
            final Function<? super EntityManager, ? extends R> function) {
        return applyEntityManagerFactory(
                emf -> applyEntityManagerInTransactionAndRollback(emf, function)
        );
    }

    // ---------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    private __Exercises_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
