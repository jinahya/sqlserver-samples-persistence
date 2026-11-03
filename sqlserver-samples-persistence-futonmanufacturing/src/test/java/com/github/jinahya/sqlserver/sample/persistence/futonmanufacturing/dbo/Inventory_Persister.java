package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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
 * Persists a new {@link Inventory}, of a new item and a new warehouse.
 */
class Inventory_Persister
        extends AbstractEntityPersister<Inventory> {

    Inventory_Persister() {
        super(Inventory.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public Inventory apply(final EntityManager entityManager, final Inventory entityInstance) {
        final var item = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Item.class);
        final var warehouse = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Warehouse.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setItem(item);
        entityInstance.setWarehouse(warehouse);
        return super.apply(entityManager, entityInstance);
    }
}

