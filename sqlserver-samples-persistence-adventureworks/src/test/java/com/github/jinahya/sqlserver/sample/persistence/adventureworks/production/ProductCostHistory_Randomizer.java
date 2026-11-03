package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;


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
 * Randomizes a new {@link ProductCostHistory}; the product, which the persister sets, and what SQL Server fills are
 * left out.
 */
class ProductCostHistory_Randomizer
        extends PodamObjectRandomizer<ProductCostHistory> {

    ProductCostHistory_Randomizer() {
        super(ProductCostHistory.class, List.of(
                ProductCostHistory.ATTRIBUTE_NAME_PRODUCT_ID,
                ProductCostHistory.ATTRIBUTE_NAME_PRODUCT,
                ProductCostHistory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has an amount within its column and CHECK constraint, and its dates in order.
     */
    @Override
    public ProductCostHistory get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // StandardCost, a money, >= 0
        instance.setStandardCost(__Random_TestUtils.positive(5, 4));
        // EndDate, when any, >= StartDate
        instance.setStartDate(__Random_TestUtils.dateTime());
        instance.setEndDate(random.nextBoolean()
                            ? null
                            : instance.getStartDate().plusDays(random.nextLong(366L)));
        return instance;
    }
}
