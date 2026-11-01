package com.github.jinahya.mssql.sample.databases.persistence.adventureworks;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The integration tests of a class which maps a view, or a table, that no entity can map, against the
 * {@value ___Persistence_TestUtils#PERSISTENCE_UNIT_NAME} unit.
 * <p>
 * The class is no managed type, so what is checked is that every column its mapped superclass names exists, by a native
 * query of them all, and that the class takes every one of them in its public constructor.
 *
 * @param <T> the type of the class.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
public abstract class _Keyless_PersistenceIT<T> {

    protected _Keyless_PersistenceIT(final Class<T> targetClass) {
        super();
        this.targetClass = Objects.requireNonNull(targetClass, "targetClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String constant(final String name) {
        try {
            return (String) targetClass.getSuperclass().getField(name).get(null);
        } catch (final ReflectiveOperationException roe) {
            throw new AssertionError("failed to read " + name, roe);
        }
    }

    private String[] columnNames() {
        return Arrays.stream(targetClass.getSuperclass().getDeclaredFields())
                .filter(f -> Modifier.isStatic(f.getModifiers()))
                .filter(f -> f.getName().startsWith("COLUMN_NAME_"))
                .map(f -> constant(f.getName()))
                .toArray(String[]::new);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @DisplayName("every column the mapped superclass names is read by a native query")
    @Test
    void columns() {
        final var columns = columnNames();
        assertThat(columns).isNotEmpty();
        final var sql = "SELECT TOP (1) " + String.join(", ", columns)
                        + " FROM " + constant("TABLE_SCHEMA") + "." + constant("TABLE_NAME");
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var rows = em.createNativeQuery(sql).getResultList();
            for (final var row : rows) {
                assertThat(columns.length == 1 ? new Object[]{row} : (Object[]) row).hasSize(columns.length);
            }
            return null;
        });
    }

    @DisplayName("the public constructor takes every column")
    @Test
    void constructor() {
        final var columns = columnNames();
        final var constructors = Arrays.stream(targetClass.getConstructors()).toList();
        assertThat(constructors).hasSize(1);
        assertThat(constructors.getFirst().getParameterCount()).isEqualTo(columns.length);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<T> targetClass;
}
