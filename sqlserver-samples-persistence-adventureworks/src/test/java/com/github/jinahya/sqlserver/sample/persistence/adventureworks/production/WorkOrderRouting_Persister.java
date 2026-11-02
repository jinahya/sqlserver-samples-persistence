package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;


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
 * Persists a new {@link WorkOrderRouting}, of a new work order, at a new location.
 */
class WorkOrderRouting_Persister
        extends AbstractEntityPersister<WorkOrderRouting> {

    WorkOrderRouting_Persister() {
        super(WorkOrderRouting.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public WorkOrderRouting apply(final EntityManager entityManager, final WorkOrderRouting entityInstance) {
        final var workOrder = EntityPersisterUtils.newPersistedInstanceOf(entityManager, WorkOrder.class);
        final var location = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Location.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setWorkOrder(workOrder);
        // ProductID, of the key, has no foreign key of its own; it is the product of the work order
        entityInstance.setProductId(workOrder.getProductId());
        entityInstance.setLocation(location);
        return super.apply(entityManager, entityInstance);
    }
}
