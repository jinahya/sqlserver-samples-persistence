package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.sqlserver.sample.persistence.shared._Entity_PersistenceIT;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class Address_PersistenceIT
        extends _Entity_PersistenceIT<Address, Integer> {

    Address_PersistenceIT() {
        super(Address.class, Integer.class, ___PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    // ---------------------------------------------------------------------------------------------
    private static Object storedSpatialLocation(final EntityManager entityManager,
                                                final Address address) {
        return entityManager.createNativeQuery("""
                                                       SELECT CAST(a.SpatialLocation AS varbinary(max))
                                                       FROM Person.Address AS a
                                                       WHERE a.AddressID = ?1""")
                .setParameter(1, address.getAddressId())
                .getSingleResult();
    }

    /**
     * {@inheritDoc}
     *
     * @implNote The spatial location SQL Server stored is the randomized point, byte for
     *         byte.
     */
    @Override
    protected void randomizedEntityInstancePersisted__(
            final EntityManagerFactory entityManagerFactory, final EntityManager entityManager,
            final Address entityInstance) {
        assertThat(entityInstance.getSpatialLocation()).isNotNull();
        assertThat((byte[]) storedSpatialLocation(entityManager, entityInstance))
                .as("the SpatialLocation stored for %s", entityInstance.getSpatialLocation())
                .isEqualTo(entityInstance.getSpatialLocation().getBytes());
    }

    @DisplayName(
            "a new instance with no spatial location is persisted, and SQL Server stores a NULL")
    @Test
    void persistRandomEntityInstance_SpatialLocationNull() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var instance =
                    ObjectRandomizerUtils.newRandomizedInstanceOf(Address.class)
                            .orElseThrow();
            instance.setSpatialLocation(null);
            EntityPersisterUtils.newPersisterInstanceOf(Address.class).orElseThrow()
                    .apply(em, instance);
            em.flush();
            assertThat(storedSpatialLocation(em, instance)).isNull();
            return null;
        }));
    }
}
