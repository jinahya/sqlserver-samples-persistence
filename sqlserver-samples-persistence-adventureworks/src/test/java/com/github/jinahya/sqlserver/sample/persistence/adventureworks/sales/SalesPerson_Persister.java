package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;


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
 * Persists a new {@link SalesPerson}, who is a new employee, in a new territory.
 */
class SalesPerson_Persister
        extends AbstractEntityPersister<SalesPerson> {

    SalesPerson_Persister() {
        super(SalesPerson.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public SalesPerson apply(final EntityManager entityManager, final SalesPerson entityInstance) {
        final var employee = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Employee.class);
        final var territory = EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesTerritory.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setBusinessEntity(employee);
        entityInstance.setTerritory(territory);
        return super.apply(entityManager, entityInstance);
    }
}
