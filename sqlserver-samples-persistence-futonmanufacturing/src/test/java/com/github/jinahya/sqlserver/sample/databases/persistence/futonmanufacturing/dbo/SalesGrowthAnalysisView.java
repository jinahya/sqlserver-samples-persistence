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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesGrowthAnalysisView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_GrowthAnalysis} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesGrowthAnalysisView extends MappedSalesGrowthAnalysisView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesGrowthAnalysisView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param year                the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month               the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param channelName         the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param orders              the value of the {@value #COLUMN_NAME_ORDERS} column.
     * @param customers           the value of the {@value #COLUMN_NAME_CUSTOMERS} column.
     * @param revenue             the value of the {@value #COLUMN_NAME_REVENUE} column.
     * @param priorMonthRevenue   the value of the {@value #COLUMN_NAME_PRIOR_MONTH_REVENUE} column.
     * @param revenueGrowth       the value of the {@value #COLUMN_NAME_REVENUE_GROWTH} column.
     * @param growthPercent       the value of the {@value #COLUMN_NAME_GROWTH_PERCENT} column.
     * @param priorMonthCustomers the value of the {@value #COLUMN_NAME_PRIOR_MONTH_CUSTOMERS} column.
     * @param customerGrowth      the value of the {@value #COLUMN_NAME_CUSTOMER_GROWTH} column.
     * @param cumulativeRevenue   the value of the {@value #COLUMN_NAME_CUMULATIVE_REVENUE} column.
     * @param threeMonthAvg       the value of the {@value #COLUMN_NAME_THREE_MONTH_AVG} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesGrowthAnalysisView(final Integer year,
                                   final Integer month,
                                   final String channelName,
                                   final Integer orders,
                                   final Integer customers,
                                   final BigDecimal revenue,
                                   final BigDecimal priorMonthRevenue,
                                   final BigDecimal revenueGrowth,
                                   final BigDecimal growthPercent,
                                   final Integer priorMonthCustomers,
                                   final Integer customerGrowth,
                                   final BigDecimal cumulativeRevenue,
                                   final BigDecimal threeMonthAvg) {
        super();
        setYear(year);
        setMonth(month);
        setChannelName(channelName);
        setOrders(orders);
        setCustomers(customers);
        setRevenue(revenue);
        setPriorMonthRevenue(priorMonthRevenue);
        setRevenueGrowth(revenueGrowth);
        setGrowthPercent(growthPercent);
        setPriorMonthCustomers(priorMonthCustomers);
        setCustomerGrowth(customerGrowth);
        setCumulativeRevenue(cumulativeRevenue);
        setThreeMonthAvg(threeMonthAvg);
    }
}

