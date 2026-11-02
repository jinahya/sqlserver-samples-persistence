package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.sales;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.__Random_TestUtils;
import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link SpecialOffer}; the generated identifier and what SQL Server fills are left out.
 */
class SpecialOffer_Randomizer
        extends PodamObjectRandomizer<SpecialOffer> {

    SpecialOffer_Randomizer() {
        super(SpecialOffer.class, List.of(
                SpecialOffer.ATTRIBUTE_NAME_SPECIAL_OFFER_ID,
                SpecialOffer.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has values within their CHECK constraints.
     */
    @Override
    public SpecialOffer get() {
        final var instance = super.get();
        // DiscountPct, a smallmoney, is >= 0; EndDate >= StartDate; MaxQty, when any, >= MinQty
        instance.setDiscountPct(__Random_TestUtils.positive(0, 4));
        instance.setStartDate(__Random_TestUtils.dateTime());
        instance.setEndDate(instance.getStartDate().plusDays(ThreadLocalRandom.current().nextLong(366L)));
        instance.setMinQty(ThreadLocalRandom.current().nextInt(100));
        instance.setMaxQty(ThreadLocalRandom.current().nextBoolean()
                           ? null
                           : instance.getMinQty() + ThreadLocalRandom.current().nextInt(100));
        return instance;
    }
}
