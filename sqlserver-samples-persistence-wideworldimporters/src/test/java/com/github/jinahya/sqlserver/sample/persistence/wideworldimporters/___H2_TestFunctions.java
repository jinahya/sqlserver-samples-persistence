package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
 * %%
 * Copyright (C) 2026 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.time.LocalDateTime;

/**
 * The SQL Server functions WideWorldImporters' mappings and defaults call, which H2 does not have,
 * for the in-memory database of the {@value ___PersistenceTest_Utils#PERSISTENCE_UNIT_NAME} unit.
 * <p>
 * The unit's URL creates an alias of each, {@code CREATE ALIAS ... FOR}, when the database opens,
 * before the provider generates the schema, whose check constraints call {@code isjson}, and
 * before the defaults, some of which call {@code sysdatetime}.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S100"  // Method names should comply with a naming convention
})
public final class ___H2_TestFunctions {

    /**
     * Stands for SQL Server's {@code ISJSON}.
     *
     * @param expression the text to check.
     * @return {@code 1}, for any text.
     * @implNote H2 does not check the text is JSON; a check constraint which calls
     *         {@code isjson} takes any value here, and only SQL Server, in an IT, holds a column to
     *         it.
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/isjson-transact-sql">ISJSON
     *         (Transact-SQL)</a>
     */
    public static int isjson(final String expression) {
        return 1;
    }

    /**
     * Stands for SQL Server's {@code SYSDATETIME}.
     *
     * @return the current date and time.
     * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME
     *         (Transact-SQL)</a>
     */
    public static LocalDateTime sysdatetime() {
        return LocalDateTime.now();
    }

    // ---------------------------------------------------------------------------------------------

    private ___H2_TestFunctions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
