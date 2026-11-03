package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application;

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
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link TransactionType}, with a new instance of each entity it references.
 */
class TransactionType_Persister
        extends AbstractEntityPersister<TransactionType> {

    TransactionType_Persister() {
        super(TransactionType.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public TransactionType apply(final EntityManager entityManager, final TransactionType entityInstance) {
        final var lastEditedByPerson = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        entityInstance.setLastEditedByPerson(lastEditedByPerson);
        return super.apply(entityManager, entityInstance);
    }
}

