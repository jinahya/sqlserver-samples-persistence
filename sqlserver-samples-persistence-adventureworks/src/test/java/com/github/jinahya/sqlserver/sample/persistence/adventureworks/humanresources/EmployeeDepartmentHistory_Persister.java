package com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources;


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
 * Persists a new {@link EmployeeDepartmentHistory}, of a new employee, in a new department, on a new shift.
 */
class EmployeeDepartmentHistory_Persister
        extends AbstractEntityPersister<EmployeeDepartmentHistory> {

    EmployeeDepartmentHistory_Persister() {
        super(EmployeeDepartmentHistory.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public EmployeeDepartmentHistory apply(final EntityManager entityManager,
                                           final EmployeeDepartmentHistory entityInstance) {
        final var employee = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Employee.class);
        final var department = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Department.class);
        final var shift = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Shift.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setBusinessEntity(employee);
        entityInstance.setDepartment(department);
        entityInstance.setShift(shift);
        return super.apply(entityManager, entityInstance);
    }
}
