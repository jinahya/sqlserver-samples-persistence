package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.persistence.util.JinahyaEntityManagerUtils;
import com.github.jinahya.persistence.util.JinahyaPersistenceUtils;
import jakarta.persistence.EntityManager;

import java.util.Objects;
import java.util.function.Function;

/**
 * The helpers the integration tests share, on the {@value #PERSISTENCE_UNIT_NAME} unit. No factory is held:
 * each call creates one, through {@link JinahyaPersistenceUtils}, and closes it.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___PersistenceIT_Utils {

    static final String PERSISTENCE_UNIT_NAME = "futonmanufacturing-it";

    /**
     * Applies the specified function to a new entity manager, inside a transaction which is rolled back afterwards.
     */
    public static <R> R applyInTransactionAndRollback(final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        return JinahyaPersistenceUtils.applyEntityManager(
                PERSISTENCE_UNIT_NAME,
                em -> JinahyaEntityManagerUtils.applyInTransaction(em, function, true));
    }

    private ___PersistenceIT_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
