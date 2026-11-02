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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link PurchaseOrderHeader}, by a new employee, from a new vendor, shipped by a new method.
 */
class PurchaseOrderHeader_Persister
        extends AbstractEntityPersister<PurchaseOrderHeader> {

    PurchaseOrderHeader_Persister() {
        super(PurchaseOrderHeader.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public PurchaseOrderHeader apply(final EntityManager entityManager, final PurchaseOrderHeader entityInstance) {
        final var employee = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Employee.class);
        final var vendor = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Vendor.class);
        final var shipMethod = EntityPersisterUtils.newPersistedInstanceOf(entityManager, ShipMethod.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setEmployee(employee);
        entityInstance.setVendor(vendor);
        entityInstance.setShipMethod(shipMethod);
        return super.apply(entityManager, entityInstance);
    }
}
