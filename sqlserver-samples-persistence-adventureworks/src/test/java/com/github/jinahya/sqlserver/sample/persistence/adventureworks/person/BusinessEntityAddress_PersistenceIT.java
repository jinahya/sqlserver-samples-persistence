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
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___Persistence_TestUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_PersistenceIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessEntityAddress_PersistenceIT extends _Entity_PersistenceIT<BusinessEntityAddress> {

    BusinessEntityAddress_PersistenceIT() {
        super(BusinessEntityAddress.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Verifies that two new instances which reference the same persisted instances are equal, and that the hash of
     * an instance does not change when it is persisted.
     */
    @DisplayName("instances with the same assigned identifier are equal, and keep their hash through persist")
    @Test
    void equals_hashCode_AcrossPersist() {
        ___Persistence_TestUtils.applyInTransactionAndRollback(em -> {
            final var businessEntity = EntityPersisterUtils.newPersistedInstanceOf(em, BusinessEntity.class);
            final var address = EntityPersisterUtils.newPersistedInstanceOf(em, Address.class);
            final var addressType = EntityPersisterUtils.newPersistedInstanceOf(em, AddressType.class);
            em.flush();
            final var instance = BusinessEntityAddress.of(businessEntity, address, addressType);
            final var other = BusinessEntityAddress.of(businessEntity, address, addressType);
            assertThat(instance).isEqualTo(other).hasSameHashCodeAs(other);
            final var hash = instance.hashCode();
            em.persist(instance);
            em.flush();
            assertThat(instance.hashCode()).isEqualTo(hash);
            assertThat(instance).isEqualTo(other);
            return null;
        });
    }
}

