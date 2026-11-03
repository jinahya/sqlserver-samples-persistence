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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link Person}; the identifier and the business entity, which the persister sets, and what SQL
 * Server fills are left out.
 */
class Person_Randomizer
        extends PodamObjectRandomizer<Person> {

    Person_Randomizer() {
        super(Person.class, List.of(
                Person.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                Person.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                Person.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has no AdditionalContactInfo and no Demographics; each is an xml column typed by
     *         a schema collection, which no random text is valid against.
     */
    @Override
    public Person get() {
        final var instance = super.get();
        instance.setAdditionalContactInfo(null);
        instance.setDemographics(null);
        return instance;
    }
}
