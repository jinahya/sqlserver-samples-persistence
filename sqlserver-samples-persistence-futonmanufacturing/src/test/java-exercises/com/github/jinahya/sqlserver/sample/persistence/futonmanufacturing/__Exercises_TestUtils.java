package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.___PersistenceIT_Utils.applyEntityManagerFactory;

/**
 * Helpers for the {@code _<Schema>_Exercises_*_PersistenceIT} classes, which answer the exercises of
 * {@code src/test/sql-exercises} against the installed {@code FutonManufacturing} database.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class __Exercises_TestUtils {

    public static <R> R apply(final Function<? super EntityManager, ? extends R> function) {
        return applyEntityManagerFactory(
                emf -> applyEntityManagerInTransactionAndRollback(emf, function)
        );
    }

    public static BigDecimal scale4(final Object value) {
        return new BigDecimal(String.valueOf(value)).setScale(4, RoundingMode.HALF_UP);
    }

    // ---------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    private __Exercises_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
