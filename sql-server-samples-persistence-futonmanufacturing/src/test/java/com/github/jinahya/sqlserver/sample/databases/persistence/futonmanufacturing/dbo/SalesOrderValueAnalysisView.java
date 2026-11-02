package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sql-server-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesOrderValueAnalysisView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_OrderValueAnalysis} view, which no entity can map: the
 * view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesOrderValueAnalysisView extends MappedSalesOrderValueAnalysisView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesOrderValueAnalysisView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param channelName      the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param customerType     the value of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column.
     * @param territoryName    the value of the {@value #COLUMN_NAME_TERRITORY_NAME} column.
     * @param region           the value of the {@value #COLUMN_NAME_REGION} column.
     * @param orderCount       the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param avgSubtotal      the value of the {@value #COLUMN_NAME_AVG_SUBTOTAL} column.
     * @param avgDiscount      the value of the {@value #COLUMN_NAME_AVG_DISCOUNT} column.
     * @param avgNetAmount     the value of the {@value #COLUMN_NAME_AVG_NET_AMOUNT} column.
     * @param avgTax           the value of the {@value #COLUMN_NAME_AVG_TAX} column.
     * @param avgShipping      the value of the {@value #COLUMN_NAME_AVG_SHIPPING} column.
     * @param minOrderValue    the value of the {@value #COLUMN_NAME_MIN_ORDER_VALUE} column.
     * @param maxOrderValue    the value of the {@value #COLUMN_NAME_MAX_ORDER_VALUE} column.
     * @param stdDevOrderValue the value of the {@value #COLUMN_NAME_STD_DEV_ORDER_VALUE} column.
     * @param smallOrders      the value of the {@value #COLUMN_NAME_SMALL_ORDERS} column.
     * @param mediumOrders     the value of the {@value #COLUMN_NAME_MEDIUM_ORDERS} column.
     * @param largeOrders      the value of the {@value #COLUMN_NAME_LARGE_ORDERS} column.
     * @param enterpriseOrders the value of the {@value #COLUMN_NAME_ENTERPRISE_ORDERS} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesOrderValueAnalysisView(final String channelName,
                                       final String customerType,
                                       final String territoryName,
                                       final String region,
                                       final Integer orderCount,
                                       final BigDecimal avgSubtotal,
                                       final BigDecimal avgDiscount,
                                       final BigDecimal avgNetAmount,
                                       final BigDecimal avgTax,
                                       final BigDecimal avgShipping,
                                       final BigDecimal minOrderValue,
                                       final BigDecimal maxOrderValue,
                                       final Double stdDevOrderValue,
                                       final Integer smallOrders,
                                       final Integer mediumOrders,
                                       final Integer largeOrders,
                                       final Integer enterpriseOrders) {
        super();
        setChannelName(channelName);
        setCustomerType(customerType);
        setTerritoryName(territoryName);
        setRegion(region);
        setOrderCount(orderCount);
        setAvgSubtotal(avgSubtotal);
        setAvgDiscount(avgDiscount);
        setAvgNetAmount(avgNetAmount);
        setAvgTax(avgTax);
        setAvgShipping(avgShipping);
        setMinOrderValue(minOrderValue);
        setMaxOrderValue(maxOrderValue);
        setStdDevOrderValue(stdDevOrderValue);
        setSmallOrders(smallOrders);
        setMediumOrders(mediumOrders);
        setLargeOrders(largeOrders);
        setEnterpriseOrders(enterpriseOrders);
    }
}

