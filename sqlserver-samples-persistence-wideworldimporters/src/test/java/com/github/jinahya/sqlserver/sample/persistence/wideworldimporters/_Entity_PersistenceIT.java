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

import com.github.jinahya.persistence.test.util.JinahyaEntityManagerTestUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * The integration tests of an entity, against the {@value ___Persistence_TestUtils#PERSISTENCE_UNIT_NAME} unit on the
 * database {@code compose.yaml} brings up. Each reads installed rows only, and rolls back.
 *
 * @param <T> the type of the entity.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
public abstract class _Entity_PersistenceIT<T> {

    /**
     * The number of installed rows each test reads.
     */
    static final int MAX_RESULTS = 8;

    protected _Entity_PersistenceIT(final Class<T> entityClass) {
        super();
        this.entityClass = Objects.requireNonNull(entityClass, "entityClass is null");
    }

    // ---------------------------------------------------------------------------------------------
    private List<T> select(final EntityManager entityManager) {
        final var builder = entityManager.getCriteriaBuilder();
        final var query = builder.createQuery(entityClass);
        query.select(query.from(entityClass));
        return entityManager.createQuery(query).setMaxResults(MAX_RESULTS).getResultList();
    }

    private EntityType<T> entityType() {
        return ___Persistence_TestUtils.entityManagerFactory().getMetamodel().entity(entityClass);
    }

    // ---------------------------------------------------------------------------------------------
    @DisplayName("the installed rows are read, and each is found again by its identifier")
    @Test
    void find() {
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var selected = select(em);
            assumeFalse(selected.isEmpty(), () -> "no row of " + entityClass.getSimpleName() + " is installed");
            final var util = em.getEntityManagerFactory().getPersistenceUnitUtil();
            for (final var instance : selected) {
                final var identifier = util.getIdentifier(instance);
                assertThat(identifier).as("the identifier of %s", instance).isNotNull();
                em.clear();
                final var found = em.find(entityClass, identifier);
                assertThat(found).as("the %s found by %s", entityClass.getSimpleName(), identifier)
                        .isNotNull()
                        .isNotSameAs(instance)
                        .isEqualTo(instance);
                assertThat(util.getIdentifier(found)).isEqualTo(identifier);
            }
            return null;
        });
    }

    @DisplayName("every association of the installed rows is loaded")
    @Test
    void associations() {
        final var associations = entityType().getSingularAttributes().stream()
                .filter(Attribute::isAssociation)
                .map(a -> (Field) a.getJavaMember())
                .toList();
        assumeFalse(associations.isEmpty(), () -> entityClass.getSimpleName() + " maps no association");
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var util = em.getEntityManagerFactory().getPersistenceUnitUtil();
            var referenced = 0;
            for (final var instance : select(em)) {
                for (final var association : associations) {
                    final Object value;
                    try {
                        association.setAccessible(true);
                        value = association.get(instance);
                    } catch (final IllegalAccessException iae) {
                        throw new AssertionError("failed to read " + association, iae);
                    }
                    if (value == null) {
                        continue;
                    }
                    util.load(value);
                    assertThat(util.isLoaded(value))
                            .as("%s of %s loaded", association.getName(), instance)
                            .isTrue();
                    assertThat(util.getIdentifier(value)).isNotNull();
                    referenced++;
                }
            }
            assumeFalse(referenced == 0, () -> "no installed row of " + entityClass.getSimpleName() + " references");
            return null;
        });
    }

    @DisplayName("the installed rows satisfy the validation constraints of the mapping")
    @Test
    void validate() {
        final var validator = ___Persistence_TestUtils.validatorFactory().getValidator();
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            for (final var instance : select(em)) {
                assertThat(validator.validate(instance)).as("the violations of %s", instance).isEmpty();
            }
            return null;
        });
    }

    /**
     * Selects a random instance of the entity, from the installed rows, and hands it to
     * {@link #randomTargetInstanceSelected(Object)}.
     *
     * @implNote The selection and the callback both run inside a transaction which is rolled back, so the instance
     *         handed over is still managed, and nothing the callback does reaches the sample data.
     * @implSpec An empty table is not a failure; the test aborts, since the table's content is the installer's.
     */
    @DisplayName("a randomly selected installed row is handed to randomTargetInstanceSelected")
    @Test
    void selectRandomTargetInstance() {
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var selected = JinahyaEntityManagerTestUtils.selectRandomEntity(em, entityClass);
            assumeTrue(selected.isPresent(), () -> "no row of " + entityClass.getSimpleName() + " is installed");
            randomTargetInstanceSelected(selected.get());
            return null;
        });
    }

    /**
     * Called with the instance {@link #selectRandomTargetInstance()} selected, while it is still managed.
     *
     * @param instance the randomly selected instance.
     * @implSpec This class does nothing; a subclass overrides it to assert what its entity promises of an installed
     *         row.
     */
    protected void randomTargetInstanceSelected(final T instance) {
        // empty
    }

    // ---------------------------------------------------------------------------------------------
    protected final Class<T> entityClass;
}
