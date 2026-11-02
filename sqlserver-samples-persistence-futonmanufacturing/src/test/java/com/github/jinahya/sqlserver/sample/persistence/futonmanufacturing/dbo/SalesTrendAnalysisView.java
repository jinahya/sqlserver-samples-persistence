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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesTrendAnalysisView;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_TrendAnalysis} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesTrendAnalysisView extends MappedSalesTrendAnalysisView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesTrendAnalysisView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param year             the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month            the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param quarter          the value of the {@value #COLUMN_NAME_QUARTER} column.
     * @param monthStart       the value of the {@value #COLUMN_NAME_MONTH_START} column.
     * @param orderCount       the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param uniqueCustomers  the value of the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column.
     * @param grossSales       the value of the {@value #COLUMN_NAME_GROSS_SALES} column.
     * @param discounts        the value of the {@value #COLUMN_NAME_DISCOUNTS} column.
     * @param netSales         the value of the {@value #COLUMN_NAME_NET_SALES} column.
     * @param avgOrderValue    the value of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column.
     * @param priorMonthSales  the value of the {@value #COLUMN_NAME_PRIOR_MONTH_SALES} column.
     * @param moMChange        the value of the {@value #COLUMN_NAME_MO_M_CHANGE} column.
     * @param moMChangePercent the value of the {@value #COLUMN_NAME_MO_M_CHANGE_PERCENT} column.
     * @param priorYearSales   the value of the {@value #COLUMN_NAME_PRIOR_YEAR_SALES} column.
     * @param yoYChange        the value of the {@value #COLUMN_NAME_YO_Y_CHANGE} column.
     * @param yoYChangePercent the value of the {@value #COLUMN_NAME_YO_Y_CHANGE_PERCENT} column.
     * @param threeMonthAvg    the value of the {@value #COLUMN_NAME_THREE_MONTH_AVG} column.
     * @param sixMonthAvg      the value of the {@value #COLUMN_NAME_SIX_MONTH_AVG} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesTrendAnalysisView(final Integer year,
                                  final Integer month,
                                  final Integer quarter,
                                  final LocalDate monthStart,
                                  final Integer orderCount,
                                  final Integer uniqueCustomers,
                                  final BigDecimal grossSales,
                                  final BigDecimal discounts,
                                  final BigDecimal netSales,
                                  final BigDecimal avgOrderValue,
                                  final BigDecimal priorMonthSales,
                                  final BigDecimal moMChange,
                                  final BigDecimal moMChangePercent,
                                  final BigDecimal priorYearSales,
                                  final BigDecimal yoYChange,
                                  final BigDecimal yoYChangePercent,
                                  final BigDecimal threeMonthAvg,
                                  final BigDecimal sixMonthAvg) {
        super();
        setYear(year);
        setMonth(month);
        setQuarter(quarter);
        setMonthStart(monthStart);
        setOrderCount(orderCount);
        setUniqueCustomers(uniqueCustomers);
        setGrossSales(grossSales);
        setDiscounts(discounts);
        setNetSales(netSales);
        setAvgOrderValue(avgOrderValue);
        setPriorMonthSales(priorMonthSales);
        setMoMChange(moMChange);
        setMoMChangePercent(moMChangePercent);
        setPriorYearSales(priorYearSales);
        setYoYChange(yoYChange);
        setYoYChangePercent(yoYChangePercent);
        setThreeMonthAvg(threeMonthAvg);
        setSixMonthAvg(sixMonthAvg);
    }
}

