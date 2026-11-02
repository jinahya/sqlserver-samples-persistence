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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link ProductInventory}; the identifier and what it references, which the persister sets, and what
 * SQL Server fills are left out.
 */
class ProductInventory_Randomizer
        extends PodamObjectRandomizer<ProductInventory> {

    ProductInventory_Randomizer() {
        super(ProductInventory.class, List.of(
                ProductInventory.ATTRIBUTE_NAME_PRODUCT_ID,
                ProductInventory.ATTRIBUTE_NAME_LOCATION_ID,
                ProductInventory.ATTRIBUTE_NAME_PRODUCT,
                ProductInventory.ATTRIBUTE_NAME_LOCATION,
                ProductInventory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a shelf the CHECK constraint takes.
     */
    @Override
    public ProductInventory get() {
        final var instance = super.get();
        // CK_ProductInventory_Shelf: a letter, or N/A
        instance.setShelf(String.valueOf((char) ('A' + ThreadLocalRandom.current().nextInt(26))));
        return instance;
    }
}
