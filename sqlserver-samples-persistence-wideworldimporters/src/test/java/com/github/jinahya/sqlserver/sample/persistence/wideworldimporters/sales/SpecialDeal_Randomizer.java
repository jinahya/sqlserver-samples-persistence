package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link SpecialDeal}; the identifier, which a sequence generates, what it references, which the
 * persister sets, and what SQL Server fills are left out.
 */
class SpecialDeal_Randomizer
        extends PodamObjectRandomizer<SpecialDeal> {

    SpecialDeal_Randomizer() {
        super(SpecialDeal.class, List.of(
                SpecialDeal.ATTRIBUTE_NAME_SPECIAL_DEAL_ID,
                SpecialDeal.ATTRIBUTE_NAME_STOCK_ITEM_ID,
                SpecialDeal.ATTRIBUTE_NAME_CUSTOMER_ID,
                SpecialDeal.ATTRIBUTE_NAME_BUYING_GROUP_ID,
                SpecialDeal.ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID,
                SpecialDeal.ATTRIBUTE_NAME_STOCK_GROUP_ID,
                SpecialDeal.ATTRIBUTE_NAME_LAST_EDITED_BY,
                SpecialDeal.ATTRIBUTE_NAME_STOCK_ITEM,
                SpecialDeal.ATTRIBUTE_NAME_CUSTOMER,
                SpecialDeal.ATTRIBUTE_NAME_BUYING_GROUP,
                SpecialDeal.ATTRIBUTE_NAME_CUSTOMER_CATEGORY,
                SpecialDeal.ATTRIBUTE_NAME_STOCK_GROUP,
                SpecialDeal.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has one pricing option, a discount percentage, as
     *         {@code CK_Sales_SpecialDeals_Exactly_One_NOT_NULL_Pricing_Option_Is_Required} requires; a unit price
     *         would need a stock item, which {@code CK_Sales_SpecialDeals_Unit_Price_Deal_Requires_Special_StockItem}
     *         requires.
     */
    @Override
    public SpecialDeal get() {
        final var instance = super.get();
        instance.setDiscountAmount(null);
        instance.setUnitPrice(null);
        // DiscountPercentage, a decimal(18, 3), > 0, less than a hundred
        instance.setDiscountPercentage(__Random_TestUtils.positive(2, 3));
        // EndDate, not before StartDate
        instance.setEndDate(instance.getStartDate().plusDays(ThreadLocalRandom.current().nextLong(366L)));
        return instance;
    }
}

