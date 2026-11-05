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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.BusinessEntityContactId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class BusinessEntityContact_PersistenceIT
        extends _Entity_PersistenceIT<BusinessEntityContact, BusinessEntityContactId> {

    BusinessEntityContact_PersistenceIT() {
        super(BusinessEntityContact.class, BusinessEntityContactId.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Verifies that two new instances which reference the same rows are not equal while their
     * identifiers are not assigned, and that the hash of an instance does not change when persist
     * assigns its identifier.
     */
    @DisplayName("an unpersisted instance equals only itself, and keeps its hash through persist")
    @Test
    void equals_hashCode_AcrossPersist() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var businessEntity =
                    EntityPersisterUtils.newPersistedInstanceOf(em, BusinessEntity.class);
            final var person = EntityPersisterUtils.newPersistedInstanceOf(em, Person.class);
            final var contactType =
                    EntityPersisterUtils.newPersistedInstanceOf(em, ContactType.class);
            em.flush();
            final var instance = BusinessEntityContact.of(businessEntity, person, contactType);
            final var other = BusinessEntityContact.of(businessEntity, person, contactType);
            assertThat(instance).isEqualTo(instance).isNotEqualTo(other);
            final var hash = instance.hashCode();
            em.persist(instance);
            em.flush();
            assertThat(instance.hashCode()).isEqualTo(hash);
            assertThat(instance).isNotEqualTo(other);
            return null;
        }));
    }
}

