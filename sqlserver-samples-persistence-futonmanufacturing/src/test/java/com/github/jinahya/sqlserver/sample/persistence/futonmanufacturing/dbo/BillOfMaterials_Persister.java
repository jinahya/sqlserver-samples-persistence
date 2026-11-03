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
 * Persists a new {@link BillOfMaterials}, of a new parent item, a new component item and a new unit.
 */
class BillOfMaterials_Persister
        extends AbstractEntityPersister<BillOfMaterials> {

    BillOfMaterials_Persister() {
        super(BillOfMaterials.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public BillOfMaterials apply(final EntityManager entityManager, final BillOfMaterials entityInstance) {
        final var parentItem = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Item.class);
        final var componentItem = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Item.class);
        final var unit = EntityPersisterUtils.newPersistedInstanceOf(entityManager, UnitOfMeasure.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setParentItem(parentItem);
        entityInstance.setComponentItem(componentItem);
        entityInstance.setUnit(unit);
        return super.apply(entityManager, entityInstance);
    }
}

