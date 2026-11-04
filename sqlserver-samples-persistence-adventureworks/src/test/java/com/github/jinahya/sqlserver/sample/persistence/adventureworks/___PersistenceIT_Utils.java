package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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

import com.github.jinahya.persistence.test.util.JinahyaPersistenceTestUtils;
import jakarta.persistence.EntityManagerFactory;

import java.util.Objects;
import java.util.function.Function;

/**
 * The helpers the integration tests share, on the {@value #PERSISTENCE_UNIT_NAME} unit. The factory
 * is opened once for the whole run, through
 * {@link JinahyaPersistenceTestUtils#applyCachedEntityManagerFactory(String, Function)}, and closed
 * when the virtual machine shuts down.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___PersistenceIT_Utils {

    /**
     * The name of the persistence unit, which this module's {@code META-INF/persistence.xml}
     * declares. The value is {@value}.
     */
    static final String PERSISTENCE_UNIT_NAME = "adventureworks-it";

    // ---------------------------------------------------------------------------------------------

    /**
     * Applies the specified function to the factory of the {@value #PERSISTENCE_UNIT_NAME} unit,
     * opening it on the first call.
     *
     * @param function the function to apply; it must not close the factory.
     * @param <R>      the type of the result.
     * @return the result of the {@code function}.
     */
    public static <R> R applyEntityManagerFactory(
            final Function<? super EntityManagerFactory, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return JinahyaPersistenceTestUtils.applyCachedEntityManagerFactory(
                PERSISTENCE_UNIT_NAME,
                function
        );
    }

    // ---------------------------------------------------------------------------------------------

    private ___PersistenceIT_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
