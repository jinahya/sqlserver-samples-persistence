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
 * Randomizes a new {@link CurrencyRate}; the generated identifier, what SQL Server fills, and the currencies, which
 * the persister sets, are left out.
 */
class CurrencyRate_Randomizer
        extends PodamObjectRandomizer<CurrencyRate> {

    CurrencyRate_Randomizer() {
        super(CurrencyRate.class, List.of(
                CurrencyRate.ATTRIBUTE_NAME_CURRENCY_RATE_ID,
                CurrencyRate.ATTRIBUTE_NAME_FROM_CURRENCY_CODE,
                CurrencyRate.ATTRIBUTE_NAME_TO_CURRENCY_CODE,
                CurrencyRate.ATTRIBUTE_NAME_FROM_CURRENCY,
                CurrencyRate.ATTRIBUTE_NAME_TO_CURRENCY,
                CurrencyRate.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a date and rates within their columns.
     */
    @Override
    public CurrencyRate get() {
        final var instance = super.get();
        // CurrencyRateDate, a datetime; AverageRate and EndOfDayRate, both money
        instance.setCurrencyRateDate(__Random_TestUtils.dateTime());
        instance.setAverageRate(__Random_TestUtils.positive(5, 4));
        instance.setEndOfDayRate(__Random_TestUtils.positive(5, 4));
        return instance;
    }
}
