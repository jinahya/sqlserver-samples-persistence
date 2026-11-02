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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesTimeBasedAnalysisView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_TimeBasedAnalysis} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesTimeBasedAnalysisView extends MappedSalesTimeBasedAnalysisView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesTimeBasedAnalysisView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param dayOfWeek       the value of the {@value #COLUMN_NAME_DAY_OF_WEEK} column.
     * @param dayNumber       the value of the {@value #COLUMN_NAME_DAY_NUMBER} column.
     * @param hourOfDay       the value of the {@value #COLUMN_NAME_HOUR_OF_DAY} column.
     * @param timeOfDay       the value of the {@value #COLUMN_NAME_TIME_OF_DAY} column.
     * @param channelName     the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param orderCount      the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param totalRevenue    the value of the {@value #COLUMN_NAME_TOTAL_REVENUE} column.
     * @param avgOrderValue   the value of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column.
     * @param uniqueCustomers the value of the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesTimeBasedAnalysisView(final String dayOfWeek,
                                      final Integer dayNumber,
                                      final Integer hourOfDay,
                                      final String timeOfDay,
                                      final String channelName,
                                      final Integer orderCount,
                                      final BigDecimal totalRevenue,
                                      final BigDecimal avgOrderValue,
                                      final Integer uniqueCustomers) {
        super();
        setDayOfWeek(dayOfWeek);
        setDayNumber(dayNumber);
        setHourOfDay(hourOfDay);
        setTimeOfDay(timeOfDay);
        setChannelName(channelName);
        setOrderCount(orderCount);
        setTotalRevenue(totalRevenue);
        setAvgOrderValue(avgOrderValue);
        setUniqueCustomers(uniqueCustomers);
    }
}

