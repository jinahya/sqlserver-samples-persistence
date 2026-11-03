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
 * Persists a new {@link JobCandidate}, who is a new employee.
 */
class JobCandidate_Persister
        extends AbstractEntityPersister<JobCandidate> {

    JobCandidate_Persister() {
        super(JobCandidate.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public JobCandidate apply(final EntityManager entityManager,
                              final JobCandidate entityInstance) {
        final var employee =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Employee.class);
        entityInstance.setBusinessEntity(employee);
        return super.apply(entityManager, entityInstance);
    }
}
