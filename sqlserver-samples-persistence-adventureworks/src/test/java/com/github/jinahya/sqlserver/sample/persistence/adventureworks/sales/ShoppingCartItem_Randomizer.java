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
 * Randomizes a new {@link ShoppingCartItem}; the generated identifier, what SQL Server fills, and
 * what it references, which the persister sets, are left out.
 */
class ShoppingCartItem_Randomizer
        extends PodamObjectRandomizer<ShoppingCartItem> {

    ShoppingCartItem_Randomizer() {
        super(ShoppingCartItem.class, List.of(
                ShoppingCartItem.ATTRIBUTE_NAME_SHOPPING_CART_ITEM_ID,
                ShoppingCartItem.ATTRIBUTE_NAME_PRODUCT_ID,
                ShoppingCartItem.ATTRIBUTE_NAME_PRODUCT,
                ShoppingCartItem.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a date within its column.
     */
    @Override
    public ShoppingCartItem get() {
        final var instance = super.get();
        // DateCreated, a datetime
        instance.setDateCreated(__Random_TestUtils.dateTime());
        return instance;
    }
}
