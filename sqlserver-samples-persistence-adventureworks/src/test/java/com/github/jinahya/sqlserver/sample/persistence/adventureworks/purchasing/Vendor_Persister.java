package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;


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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.BusinessEntity;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link Vendor}, of a new business entity.
 */
class Vendor_Persister
        extends AbstractEntityPersister<Vendor> {

    Vendor_Persister() {
        super(Vendor.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public Vendor apply(final EntityManager entityManager, final Vendor entityInstance) {
        final var businessEntity = EntityPersisterUtils.newPersistedInstanceOf(entityManager, BusinessEntity.class);
        // the business entity's identifier is the INSERT's; the flush is what assigns it
        entityManager.flush();
        entityInstance.setBusinessEntity(businessEntity);
        return super.apply(entityManager, entityInstance);
    }
}
