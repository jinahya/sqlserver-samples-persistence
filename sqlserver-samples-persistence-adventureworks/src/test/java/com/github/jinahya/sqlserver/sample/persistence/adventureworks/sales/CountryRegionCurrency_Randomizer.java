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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link CountryRegionCurrency}; the identifier, what it references, and what SQL
 * Server fills are left out, which leaves nothing to randomize. The persister does not use the
 * instance; it persists one which {@link CountryRegionCurrency#of} creates with the instances it
 * references.
 */
class CountryRegionCurrency_Randomizer
        extends PodamObjectRandomizer<CountryRegionCurrency> {

    CountryRegionCurrency_Randomizer() {
        super(CountryRegionCurrency.class, List.of(
                CountryRegionCurrency.ATTRIBUTE_NAME_ID,
                CountryRegionCurrency.ATTRIBUTE_NAME_COUNTRY_REGION,
                CountryRegionCurrency.ATTRIBUTE_NAME_CURRENCY,
                CountryRegionCurrency.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }
}
