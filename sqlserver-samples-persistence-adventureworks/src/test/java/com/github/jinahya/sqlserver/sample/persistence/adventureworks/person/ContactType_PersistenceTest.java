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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceTest_Utils;
import com.github.jinahya.sqlserver.sample.persistence.shared._Entity_PersistenceTest;

/**
 * Verifies the mappings of {@link ContactType} against the schema generated into the in-memory
 * database.
 */
class ContactType_PersistenceTest
        extends _Entity_PersistenceTest<ContactType, Integer> {

    ContactType_PersistenceTest() {
        super(ContactType.class, Integer.class, ___PersistenceTest_Utils.PERSISTENCE_UNIT_NAME);
    }
}
