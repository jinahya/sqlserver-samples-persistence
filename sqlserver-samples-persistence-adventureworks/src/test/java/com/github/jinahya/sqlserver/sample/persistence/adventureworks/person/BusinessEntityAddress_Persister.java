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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link BusinessEntityAddress}, of a new business entity, a new address, and a new address type.
 */
class BusinessEntityAddress_Persister
        extends AbstractEntityPersister<BusinessEntityAddress> {

    BusinessEntityAddress_Persister() {
        super(BusinessEntityAddress.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public BusinessEntityAddress apply(final EntityManager entityManager, final BusinessEntityAddress entityInstance) {
        final var businessEntity = EntityPersisterUtils.newPersistedInstanceOf(entityManager, BusinessEntity.class);
        final var address = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Address.class);
        final var addressType = EntityPersisterUtils.newPersistedInstanceOf(entityManager, AddressType.class);
        // the identifiers of the three are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setBusinessEntity(businessEntity);
        entityInstance.setAddress(address);
        entityInstance.setAddressType(addressType);
        return super.apply(entityManager, entityInstance);
    }
}
