package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;


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
 * Persists a new {@link PersonPhone}, of a new person, with a new phone number type.
 */
class PersonPhone_Persister
        extends AbstractEntityPersister<PersonPhone> {

    PersonPhone_Persister() {
        super(PersonPhone.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public PersonPhone apply(final EntityManager entityManager, final PersonPhone entityInstance) {
        final var person = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        final var phoneNumberType = EntityPersisterUtils.newPersistedInstanceOf(entityManager, PhoneNumberType.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setBusinessEntity(person);
        entityInstance.setPhoneNumberType(phoneNumberType);
        return super.apply(entityManager, entityInstance);
    }
}
