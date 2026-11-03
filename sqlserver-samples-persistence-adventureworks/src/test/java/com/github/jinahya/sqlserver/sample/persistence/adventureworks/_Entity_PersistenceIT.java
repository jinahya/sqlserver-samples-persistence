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

import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.persistence.test.util.JinahyaEntityManagerTestUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * The integration tests of an entity, against the
 * {@value ___PersistenceIT_Utils#PERSISTENCE_UNIT_NAME} unit on the database {@code compose.yaml}
 * brings up. Each reads installed rows, or persists a new randomized instance, and rolls back.
 *
 * @param <T> the type of the entity.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
public abstract class _Entity_PersistenceIT<T> {

    protected _Entity_PersistenceIT(final Class<T> entityClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Verifies that each {@code ATTRIBUTE_PATH_*} constant of the entity, a dotted path such as
     * {@code id.productId}, resolves through the metamodel.
     */
    @DisplayName("each ATTRIBUTE_PATH_* constant resolves through the metamodel")
    @Test
    void attributePaths() throws IllegalAccessException {
        final var paths = new ArrayList<String>();
        for (final Field field : entityClass.getFields()) {
            if (Modifier.isStatic(field.getModifiers()) && field.getType() == String.class
                && field.getName().startsWith("ATTRIBUTE_PATH_")) {
                paths.add((String) field.get(null));
            }
        }
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            for (final var attributePath : paths) {
                Path<?> path = em.getCriteriaBuilder().createQuery().from(entityClass);
                for (final var name : attributePath.split("\\.")) {
                    path = path.get(name);
                }
                assertThat(path.getJavaType()).as(attributePath).isNotNull();
            }
            return null;
        });
    }

    /**
     * Persists a new, randomized instance of the entity, through its persister, and flushes it.
     * <p>
     * The randomizer, {@code <Entity>_Randomizer}, and the persister, {@code <Entity>_Persister},
     * are found by name, beside the entity. An entity which is not persisted -- a view, or a table
     * which only SQL Server, the installer, or a procedure writes -- overrides this method with an
     * empty one, {@link org.junit.jupiter.api.Disabled @Disabled} with the reason.
     *
     * @implNote The flush is what makes the {@code INSERT} reach the database, with its
     *         constraints and triggers; the transaction is rolled back, so without it nothing would
     *         be written, and nothing tested.
     */
    @DisplayName("a new randomized instance is persisted")
    @Test
    protected void persistRandomEntityInstance() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var persisted = EntityPersisterUtils.newPersistedInstanceOf(em, entityClass);
            em.flush();
            assertThat(
                    em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(persisted))
                    .as("the identifier of the persisted %s", persisted)
                    .isNotNull();
            randomEntityInstancePersisted(em, persisted);
            return null;
        });
    }

    /**
     * Called with the instance {@link #persistRandomEntityInstance()} persisted and flushed, while
     * it is still managed.
     *
     * @param entityManager  the entity manager which persisted the instance.
     * @param entityInstance the persisted instance.
     * @implSpec This class does nothing; a subclass overrides it to assert what its table
     *         does with a new row.
     */
    protected void randomEntityInstancePersisted(final EntityManager entityManager,
                                                 final T entityInstance) {
        // empty
    }

    /**
     * Selects a random instance of the entity, from the installed rows, validates it against the
     * constraints of the mapping, and hands it to {@link #randomEntityInstanceSelected(Object)}.
     * <p>
     * The validation is here, since Jakarta Persistence validates an entity only when it persists,
     * updates, or removes it, never when it loads it.
     *
     * @implNote The selection and the callback both run inside a transaction which is
     *         rolled back, so the instance handed over is still managed, and nothing the callback
     *         does reaches the sample data.
     * @implSpec An empty table is not a failure; the test aborts, since the table's content
     *         is the installer's.
     */
    @DisplayName(
            "a randomly selected installed row is valid, and is handed to " +
            "randomEntityInstanceSelected")
    @Test
    void selectRandomEntityInstance() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var selected = JinahyaEntityManagerTestUtils.selectRandomEntity(em, entityClass);
            assumeTrue(selected.isPresent(),
                       () -> "no row of " + entityClass.getSimpleName() + " is installed");
            ___Validation_TestUtils.assertValid(selected.get());
            randomEntityInstanceSelected(selected.get());
            return null;
        });
    }

    /**
     * Called with the instance {@link #selectRandomEntityInstance()} selected, while it is still
     * managed.
     *
     * @param entityInstance the randomly selected instance.
     * @implSpec This class does nothing; a subclass overrides it to assert what its entity
     *         promises of an installed row.
     */
    protected void randomEntityInstanceSelected(final T entityInstance) {
        // empty
    }

    // ---------------------------------------------------------------------------------------------
    protected final Class<T> entityClass;
}
