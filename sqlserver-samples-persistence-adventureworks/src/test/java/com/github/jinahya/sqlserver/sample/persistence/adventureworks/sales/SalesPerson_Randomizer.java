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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Random_TestUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link SalesPerson}; the identifier and what it references, which the persister
 * sets, and what SQL Server fills are left out.
 */
class SalesPerson_Randomizer
        extends PodamObjectRandomizer<SalesPerson> {

    SalesPerson_Randomizer() {
        super(SalesPerson.class, List.of(
                SalesPerson.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                SalesPerson.ATTRIBUTE_NAME_TERRITORY_ID,
                SalesPerson.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                SalesPerson.ATTRIBUTE_NAME_TERRITORY,
                SalesPerson.ATTRIBUTE_NAME_MODIFIED_DATE
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
    public SalesPerson get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // SalesQuota, when any, > 0; Bonus, SalesYTD and SalesLastYear >= 0, all money
        instance.setSalesQuota(random.nextBoolean() ? null : __Random_TestUtils.positive(5, 4));
        instance.setBonus(__Random_TestUtils.positive(5, 4));
        instance.setSalesYtd(__Random_TestUtils.positive(5, 4));
        instance.setSalesLastYear(__Random_TestUtils.positive(5, 4));
        // CommissionPct, a smallmoney, >= 0
        instance.setCommissionPct(__Random_TestUtils.positive(0, 4));
        return instance;
    }
}
