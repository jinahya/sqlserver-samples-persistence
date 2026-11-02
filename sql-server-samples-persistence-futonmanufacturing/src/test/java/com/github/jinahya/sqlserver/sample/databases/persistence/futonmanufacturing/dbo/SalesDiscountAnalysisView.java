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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesDiscountAnalysisView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_DiscountAnalysis} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesDiscountAnalysisView extends MappedSalesDiscountAnalysisView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesDiscountAnalysisView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param year                 the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month                the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param channelName          the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param customerType         the value of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column.
     * @param orderCount           the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param grossSales           the value of the {@value #COLUMN_NAME_GROSS_SALES} column.
     * @param totalDiscounts       the value of the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column.
     * @param netSales             the value of the {@value #COLUMN_NAME_NET_SALES} column.
     * @param discountPercent      the value of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column.
     * @param avgDiscountPerOrder  the value of the {@value #COLUMN_NAME_AVG_DISCOUNT_PER_ORDER} column.
     * @param noDiscountOrders     the value of the {@value #COLUMN_NAME_NO_DISCOUNT_ORDERS} column.
     * @param lowDiscountOrders    the value of the {@value #COLUMN_NAME_LOW_DISCOUNT_ORDERS} column.
     * @param mediumDiscountOrders the value of the {@value #COLUMN_NAME_MEDIUM_DISCOUNT_ORDERS} column.
     * @param highDiscountOrders   the value of the {@value #COLUMN_NAME_HIGH_DISCOUNT_ORDERS} column.
     * @param grossProfit          the value of the {@value #COLUMN_NAME_GROSS_PROFIT} column.
     * @param grossMarginPercent   the value of the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesDiscountAnalysisView(final Integer year,
                                     final Integer month,
                                     final String channelName,
                                     final String customerType,
                                     final Integer orderCount,
                                     final BigDecimal grossSales,
                                     final BigDecimal totalDiscounts,
                                     final BigDecimal netSales,
                                     final BigDecimal discountPercent,
                                     final BigDecimal avgDiscountPerOrder,
                                     final Integer noDiscountOrders,
                                     final Integer lowDiscountOrders,
                                     final Integer mediumDiscountOrders,
                                     final Integer highDiscountOrders,
                                     final BigDecimal grossProfit,
                                     final BigDecimal grossMarginPercent) {
        super();
        setYear(year);
        setMonth(month);
        setChannelName(channelName);
        setCustomerType(customerType);
        setOrderCount(orderCount);
        setGrossSales(grossSales);
        setTotalDiscounts(totalDiscounts);
        setNetSales(netSales);
        setDiscountPercent(discountPercent);
        setAvgDiscountPerOrder(avgDiscountPerOrder);
        setNoDiscountOrders(noDiscountOrders);
        setLowDiscountOrders(lowDiscountOrders);
        setMediumDiscountOrders(mediumDiscountOrders);
        setHighDiscountOrders(highDiscountOrders);
        setGrossProfit(grossProfit);
        setGrossMarginPercent(grossMarginPercent);
    }
}

