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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___Persistence_TestUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_PersistenceIT;
import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Address_PersistenceIT extends _Entity_PersistenceIT<Address> {

    Address_PersistenceIT() {
        super(Address.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Object storedSpatialLocation(final EntityManager entityManager, final Address address) {
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
     * @implNote The spatial location SQL Server stored is the randomized point, byte for byte.
     */
    @Override
    protected void persisted(final EntityManager entityManager, final Address instance) {
        assertThat(instance.getSpatialLocation()).isNotNull();
        assertThat((byte[]) storedSpatialLocation(entityManager, instance))
                .as("the SpatialLocation stored for %s", instance.getSpatialLocation())
                .isEqualTo(instance.getSpatialLocation().getBytes());
    }

    @DisplayName("a new instance with no spatial location is persisted, and SQL Server stores a NULL")
    @Test
    void persist_SpatialLocationNull() {
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var instance = ObjectRandomizerUtils.newRandomizedInstanceOf(Address.class).orElseThrow();
            instance.setSpatialLocation(null);
            EntityPersisterUtils.newPersisterInstanceOf(Address.class).orElseThrow().apply(em, instance);
            em.flush();
            assertThat(storedSpatialLocation(em, instance)).isNull();
            return null;
        });
    }
}
