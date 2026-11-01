package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesTopProductsView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_TopProducts} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesTopProductsView extends MappedSalesTopProductsView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesTopProductsView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param itemCode        the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName        the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param channelName     the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param year            the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month           the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param unitsSold       the value of the {@value #COLUMN_NAME_UNITS_SOLD} column.
     * @param revenue         the value of the {@value #COLUMN_NAME_REVENUE} column.
     * @param grossProfit     the value of the {@value #COLUMN_NAME_GROSS_PROFIT} column.
     * @param uniqueCustomers the value of the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column.
     * @param unitRank        the value of the {@value #COLUMN_NAME_UNIT_RANK} column.
     * @param revenueRank     the value of the {@value #COLUMN_NAME_REVENUE_RANK} column.
     * @param profitRank      the value of the {@value #COLUMN_NAME_PROFIT_RANK} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesTopProductsView(final String itemCode,
                                final String itemName,
                                final String channelName,
                                final Integer year,
                                final Integer month,
                                final BigDecimal unitsSold,
                                final BigDecimal revenue,
                                final BigDecimal grossProfit,
                                final Integer uniqueCustomers,
                                final Long unitRank,
                                final Long revenueRank,
                                final Long profitRank) {
        super();
        setItemCode(itemCode);
        setItemName(itemName);
        setChannelName(channelName);
        setYear(year);
        setMonth(month);
        setUnitsSold(unitsSold);
        setRevenue(revenue);
        setGrossProfit(grossProfit);
        setUniqueCustomers(uniqueCustomers);
        setUnitRank(unitRank);
        setRevenueRank(revenueRank);
        setProfitRank(profitRank);
    }
}

