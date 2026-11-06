package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;

/**
 * Helpers for the {@code _<Schema>_Exercises_*_PersistenceIT} classes, which answer the exercises of
 * {@code src/test/sql-exercises} against the installed {@code AdventureWorks} database.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
final class __Exercises_TestUtils {

    static <R> R apply(final Function<? super EntityManager, ? extends R> function) {
        return applyEntityManagerFactory(
                emf -> applyEntityManagerInTransactionAndRollback(emf, function)
        );
    }

    /**
     * Returns a row's values as the SQL's output shows them, separated by {@code " | "}; a decimal
     * without its trailing zeros, a {@code null} as {@code NULL}.
     */
    static String row(final Object... values) {
        return Arrays.stream(values)
                .map(v -> switch (v) {
                    case null -> "NULL";
                    case BigDecimal d -> d.stripTrailingZeros().toPlainString();
                    case byte[] b -> b.length + " bytes";
                    default -> String.valueOf(v);
                })
                .collect(Collectors.joining(" | "));
    }

    static List<String> rows(final List<Object[]> found) {
        return found.stream().map(r -> row((Object[]) r)).toList();
    }

    // ---------------------------------------------------------------------------------------------
    // CONSTRUCTORS
    private __Exercises_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
