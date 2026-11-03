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
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link StockItemStockGroup}, with a new instance of each entity it references.
 */
class StockItemStockGroup_Persister
        extends AbstractEntityPersister<StockItemStockGroup> {

    StockItemStockGroup_Persister() {
        super(StockItemStockGroup.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public StockItemStockGroup apply(final EntityManager entityManager, final StockItemStockGroup entityInstance) {
        final var stockItem = EntityPersisterUtils.newPersistedInstanceOf(entityManager, StockItem.class);
        final var stockGroup = EntityPersisterUtils.newPersistedInstanceOf(entityManager, StockGroup.class);
        final var lastEditedByPerson = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        entityInstance.setStockItem(stockItem);
        entityInstance.setStockGroup(stockGroup);
        entityInstance.setLastEditedByPerson(lastEditedByPerson);
        return super.apply(entityManager, entityInstance);
    }
}

