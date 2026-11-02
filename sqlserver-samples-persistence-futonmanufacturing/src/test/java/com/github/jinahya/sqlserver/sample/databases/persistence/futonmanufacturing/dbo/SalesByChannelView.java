package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesByChannelView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_ByChannel} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesByChannelView extends MappedSalesByChannelView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesByChannelView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param channelCode        the value of the {@value #COLUMN_NAME_CHANNEL_CODE} column.
     * @param channelName        the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param year               the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month              the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param quarter            the value of the {@value #COLUMN_NAME_QUARTER} column.
     * @param orderCount         the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param uniqueCustomers    the value of the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column.
     * @param grossSales         the value of the {@value #COLUMN_NAME_GROSS_SALES} column.
     * @param totalDiscounts     the value of the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column.
     * @param netSales           the value of the {@value #COLUMN_NAME_NET_SALES} column.
     * @param avgOrderValue      the value of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column.
     * @param deliveredSales     the value of the {@value #COLUMN_NAME_DELIVERED_SALES} column.
     * @param pipelineSales      the value of the {@value #COLUMN_NAME_PIPELINE_SALES} column.
     * @param avgDiscountPercent the value of the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesByChannelView(final String channelCode,
                              final String channelName,
                              final Integer year,
                              final Integer month,
                              final Integer quarter,
                              final Integer orderCount,
                              final Integer uniqueCustomers,
                              final BigDecimal grossSales,
                              final BigDecimal totalDiscounts,
                              final BigDecimal netSales,
                              final BigDecimal avgOrderValue,
                              final BigDecimal deliveredSales,
                              final BigDecimal pipelineSales,
                              final BigDecimal avgDiscountPercent) {
        super();
        setChannelCode(channelCode);
        setChannelName(channelName);
        setYear(year);
        setMonth(month);
        setQuarter(quarter);
        setOrderCount(orderCount);
        setUniqueCustomers(uniqueCustomers);
        setGrossSales(grossSales);
        setTotalDiscounts(totalDiscounts);
        setNetSales(netSales);
        setAvgOrderValue(avgOrderValue);
        setDeliveredSales(deliveredSales);
        setPipelineSales(pipelineSales);
        setAvgDiscountPercent(avgDiscountPercent);
    }
}

