package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.TransactionType;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link CustomerTransaction}, with a new instance of each entity it references.
 */
class CustomerTransaction_Persister
        extends AbstractEntityPersister<CustomerTransaction> {

    CustomerTransaction_Persister() {
        super(CustomerTransaction.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public CustomerTransaction apply(final EntityManager entityManager, final CustomerTransaction entityInstance) {
        final var customer = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Customer.class);
        final var transactionType = EntityPersisterUtils.newPersistedInstanceOf(entityManager, TransactionType.class);
        final var lastEditedByPerson = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        entityInstance.setCustomer(customer);
        entityInstance.setTransactionType(transactionType);
        entityInstance.setLastEditedByPerson(lastEditedByPerson);
        return super.apply(entityManager, entityInstance);
    }
}

