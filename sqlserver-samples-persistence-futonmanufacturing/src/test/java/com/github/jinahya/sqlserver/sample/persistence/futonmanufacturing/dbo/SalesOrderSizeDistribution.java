package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesOrderSizeDistribution;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_OrderSizeDistribution} view, which no entity can map: the
 * view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesOrderSizeDistribution extends MappedSalesOrderSizeDistribution {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesOrderSizeDistribution() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param channelName            the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param customerType           the value of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column.
     * @param orderSizeBucket        the value of the {@value #COLUMN_NAME_ORDER_SIZE_BUCKET} column.
     * @param basketSize             the value of the {@value #COLUMN_NAME_BASKET_SIZE} column.
     * @param orderCount             the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param totalRevenue           the value of the {@value #COLUMN_NAME_TOTAL_REVENUE} column.
     * @param avgOrderValue          the value of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column.
     * @param avgUnitsPerOrder       the value of the {@value #COLUMN_NAME_AVG_UNITS_PER_ORDER} column.
     * @param avgLineItems           the value of the {@value #COLUMN_NAME_AVG_LINE_ITEMS} column.
     * @param percentOfChannelOrders the value of the {@value #COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesOrderSizeDistribution(final String channelName,
                                          final String customerType,
                                          final String orderSizeBucket,
                                          final String basketSize,
                                          final Integer orderCount,
                                          final BigDecimal totalRevenue,
                                          final BigDecimal avgOrderValue,
                                          final BigDecimal avgUnitsPerOrder,
                                          final Integer avgLineItems,
                                          final BigDecimal percentOfChannelOrders) {
        super();
        setChannelName(channelName);
        setCustomerType(customerType);
        setOrderSizeBucket(orderSizeBucket);
        setBasketSize(basketSize);
        setOrderCount(orderCount);
        setTotalRevenue(totalRevenue);
        setAvgOrderValue(avgOrderValue);
        setAvgUnitsPerOrder(avgUnitsPerOrder);
        setAvgLineItems(avgLineItems);
        setPercentOfChannelOrders(percentOfChannelOrders);
    }
}

