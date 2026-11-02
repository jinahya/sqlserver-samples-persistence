package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.person;

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
 * Randomizes a new {@link StateProvince}; the generated identifier, what SQL Server fills, and
 * the country and the territory, which the persister sets, are left out.
 */
class StateProvince_Randomizer
        extends PodamObjectRandomizer<StateProvince> {

    StateProvince_Randomizer() {
        super(StateProvince.class, List.of(
                StateProvince.ATTRIBUTE_NAME_STATE_PROVINCE_ID,
                StateProvince.ATTRIBUTE_NAME_COUNTRY_REGION_CODE,
                StateProvince.ATTRIBUTE_NAME_TERRITORY_ID,
                StateProvince.ATTRIBUTE_NAME_COUNTRY_REGION,
                StateProvince.ATTRIBUTE_NAME_TERRITORY,
                StateProvince.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }
}
