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
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;

/**
 * Randomizes a new {@link SalesTerritory}; the generated identifier, what SQL Server fills, and the
 * country, which the persister sets, are left out.
 */
class SalesTerritory_Randomizer
        extends PodamObjectRandomizer<SalesTerritory> {

    SalesTerritory_Randomizer() {
        super(SalesTerritory.class, List.of(
                SalesTerritory.ATTRIBUTE_NAME_TERRITORY_ID,
                SalesTerritory.ATTRIBUTE_NAME_COUNTRY_REGION_CODE,
                SalesTerritory.ATTRIBUTE_NAME_COUNTRY_REGION,
                SalesTerritory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK
     *         constraints.
     */
    @Override
    public SalesTerritory get() {
        final var instance = super.get();
        // SalesYTD, SalesLastYear, CostYTD and CostLastYear, all money, are all >= 0
        instance.setSalesYtd(__Random_TestUtils.positive(5, 4));
        instance.setSalesLastYear(__Random_TestUtils.positive(5, 4));
        instance.setCostYtd(__Random_TestUtils.positive(5, 4));
        instance.setCostLastYear(__Random_TestUtils.positive(5, 4));
        return instance;
    }
}
