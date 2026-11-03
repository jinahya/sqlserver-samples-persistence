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

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Objects;
import java.util.function.Function;

/**
 * Holds the factory of the {@value #PERSISTENCE_UNIT_NAME} unit, created once per JVM and closed
 * when it exits, and the helpers the integration tests share.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class ___PersistenceIT_Utils {

    static final String PERSISTENCE_UNIT_NAME = "adventureworks-it";

    private static final class Holder {

        private static final EntityManagerFactory ENTITY_MANAGER_FACTORY;

        static {
            ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            Runtime.getRuntime().addShutdownHook(new Thread(ENTITY_MANAGER_FACTORY::close));
        }

        private Holder() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static EntityManagerFactory entityManagerFactory() {
        return Holder.ENTITY_MANAGER_FACTORY;
    }

    /**
     * Applies the specified function to a new entity manager, inside a transaction which is rolled
     * back afterwards.
     */
    public static <R> R applyInTransactionAndRollback(
            final Function<? super EntityManager, ? extends R> function) {
        Objects.requireNonNull(function, "function is null");
        try (var entityManager = entityManagerFactory().createEntityManager()) {
            final var transaction = entityManager.getTransaction();
            transaction.begin();
            try {
                return function.apply(entityManager);
            } finally {
                transaction.rollback();
            }
        }
    }

    private ___PersistenceIT_Utils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
