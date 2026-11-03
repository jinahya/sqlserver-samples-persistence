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

/**
 * Randomizes a new {@link SalesTaxRate}; the generated identifier, what SQL Server fills, and the state or province,
 * which the persister sets, are left out.
 */
class SalesTaxRate_Randomizer
        extends PodamObjectRandomizer<SalesTaxRate> {

    SalesTaxRate_Randomizer() {
        super(SalesTaxRate.class, List.of(
                SalesTaxRate.ATTRIBUTE_NAME_SALES_TAX_RATE_ID,
                SalesTaxRate.ATTRIBUTE_NAME_STATE_PROVINCE_ID,
                SalesTaxRate.ATTRIBUTE_NAME_STATE_PROVINCE,
                SalesTaxRate.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a rate within its column.
     */
    @Override
    public SalesTaxRate get() {
        final var instance = super.get();
        // TaxRate, a smallmoney
        instance.setTaxRate(__Random_TestUtils.positive(2, 4));
        return instance;
    }
}
