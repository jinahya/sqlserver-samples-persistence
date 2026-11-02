package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.person;

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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link Address}, in a new state or province.
 */
class Address_Persister
        extends AbstractEntityPersister<Address> {

    Address_Persister() {
        super(Address.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Address apply(final EntityManager entityManager, final Address entityInstance) {
        final var stateProvince = EntityPersisterUtils.newPersistedInstanceOf(entityManager, StateProvince.class);
        // the state's identifier is the INSERT's; the flush is what assigns it
        entityManager.flush();
        entityInstance.setStateProvince(stateProvince);
        return super.apply(entityManager, entityInstance);
    }
}
