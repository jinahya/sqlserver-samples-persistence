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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link Customer}, who is a new person, of a new store, in a new territory.
 */
class Customer_Persister
        extends AbstractEntityPersister<Customer> {

    Customer_Persister() {
        super(Customer.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public Customer apply(final EntityManager entityManager, final Customer entityInstance) {
        final var person = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        final var store = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Store.class);
        final var territory = EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesTerritory.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setPerson(person);
        entityInstance.setStore(store);
        entityInstance.setTerritory(territory);
        return super.apply(entityManager, entityInstance);
    }
}
