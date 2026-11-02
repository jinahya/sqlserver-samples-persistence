package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;


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
 * Randomizes a new {@link ProductVendor}; the identifier and what it references, which the persister sets, and what SQL
 * Server fills are left out.
 */
class ProductVendor_Randomizer
        extends PodamObjectRandomizer<ProductVendor> {

    ProductVendor_Randomizer() {
        super(ProductVendor.class, List.of(
                ProductVendor.ATTRIBUTE_NAME_PRODUCT_ID,
                ProductVendor.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                ProductVendor.ATTRIBUTE_NAME_UNIT_MEASURE_CODE,
                ProductVendor.ATTRIBUTE_NAME_PRODUCT,
                ProductVendor.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                ProductVendor.ATTRIBUTE_NAME_UNIT_MEASURE,
                ProductVendor.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK constraints.
     */
    @Override
    public ProductVendor get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // StandardPrice and LastReceiptCost, when any, both money, > 0; LastReceiptDate, a datetime
        instance.setStandardPrice(__Random_TestUtils.positive(5, 4));
        instance.setLastReceiptCost(random.nextBoolean() ? null : __Random_TestUtils.positive(5, 4));
        instance.setLastReceiptDate(random.nextBoolean() ? null : __Random_TestUtils.dateTime());
        return instance;
    }
}
