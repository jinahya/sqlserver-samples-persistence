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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link City}; the identifier, which a sequence generates, what it references, which the persister
 * sets, and what SQL Server fills are left out.
 */
class City_Randomizer
        extends PodamObjectRandomizer<City> {

    City_Randomizer() {
        super(City.class, List.of(
                City.ATTRIBUTE_NAME_CITY_ID,
                City.ATTRIBUTE_NAME_STATE_PROVINCE_ID,
                City.ATTRIBUTE_NAME_LOCATION,
                City.ATTRIBUTE_NAME_LAST_EDITED_BY,
                City.ATTRIBUTE_NAME_VALID_FROM,
                City.ATTRIBUTE_NAME_VALID_TO,
                City.ATTRIBUTE_NAME_STATE_PROVINCE,
                City.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }
}

