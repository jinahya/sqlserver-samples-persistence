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
 * Randomizes a new {@link SalesOrderDetail}; the identifier, what it references, and what SQL Server fills are left out.
 * The persister takes the rest of the instance into one which {@link SalesOrderDetail#of} creates with the order it
 * references.
 */
class SalesOrderDetail_Randomizer
        extends PodamObjectRandomizer<SalesOrderDetail> {

    SalesOrderDetail_Randomizer() {
        super(SalesOrderDetail.class, List.of(
                SalesOrderDetail.ATTRIBUTE_NAME_SALES_ORDER_ID,
                SalesOrderDetail.ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID,
                SalesOrderDetail.ATTRIBUTE_NAME_PRODUCT_ID,
                SalesOrderDetail.ATTRIBUTE_NAME_SPECIAL_OFFER_ID,
                SalesOrderDetail.ATTRIBUTE_NAME_LINE_TOTAL,
                SalesOrderDetail.ATTRIBUTE_NAME_SALES_ORDER,
                SalesOrderDetail.ATTRIBUTE_NAME_SPECIAL_OFFER_PRODUCT,
                SalesOrderDetail.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK constraints, and a line total of its
     *         order's sub total's.
     */
    @Override
    public SalesOrderDetail get() {
        final var instance = super.get();
        // UnitPrice and UnitPriceDiscount, both money, >= 0; the discount is a fraction, less than 1, as
        // iduSalesOrderDetail sums the line totals, UnitPrice * (1 - UnitPriceDiscount) * OrderQty, into the order's
        // SubTotal, which CK_SalesOrderHeader_SubTotal takes only when >= 0
        instance.setUnitPrice(__Random_TestUtils.positive(5, 4));
        instance.setUnitPriceDiscount(__Random_TestUtils.positive(0, 4));
        return instance;
    }
}
