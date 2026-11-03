package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse;

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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link StockItem}, with a new instance of each entity it references.
 */
class StockItem_Persister
        extends AbstractEntityPersister<StockItem> {

    StockItem_Persister() {
        super(StockItem.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public StockItem apply(final EntityManager entityManager, final StockItem entityInstance) {
        final var supplier = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Supplier.class);
        final var unitPackage = EntityPersisterUtils.newPersistedInstanceOf(entityManager, PackageType.class);
        final var outerPackage = EntityPersisterUtils.newPersistedInstanceOf(entityManager, PackageType.class);
        final var lastEditedByPerson = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        entityInstance.setSupplier(supplier);
        entityInstance.setUnitPackage(unitPackage);
        entityInstance.setOuterPackage(outerPackage);
        entityInstance.setLastEditedByPerson(lastEditedByPerson);
        return super.apply(entityManager, entityInstance);
    }
}

