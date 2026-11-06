package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

/**
 * Answers the exercises of {@code src/test/sql-exercises/_Purchasing/03_advanced.sql}, against the installed
 * {@code AdventureWorks} database; a nested class per exercise, which answers it with the query language and with the
 * Criteria API, or with a native query where neither can.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class _Purchasing_Exercises_03_Advanced_PersistenceIT {

    @DisplayName("AW_PURCHASING_A_01: The yearly spend with each vendor, against the year before")
    @Nested
    class AW_PURCHASING_A_01_Test {
    }
}
