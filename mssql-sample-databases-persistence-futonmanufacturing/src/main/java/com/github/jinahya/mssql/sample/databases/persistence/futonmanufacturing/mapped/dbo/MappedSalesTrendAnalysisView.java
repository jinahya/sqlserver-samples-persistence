package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_TrendAnalysis} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 */
@MappedSuperclass
public abstract class MappedSalesTrendAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_TrendAnalysis";

    // ------------------------------------------------------------------------------------------------------------ Year

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR = "Year";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR = "year";

    // ----------------------------------------------------------------------------------------------------------- Month

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONTH = "Month";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MONTH = "month";

    // --------------------------------------------------------------------------------------------------------- Quarter

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUARTER = "Quarter";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUARTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUARTER = "quarter";

    // ------------------------------------------------------------------------------------------------------ MonthStart

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONTH_START = "MonthStart";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONTH_START} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MONTH_START = "monthStart";

    // ------------------------------------------------------------------------------------------------------ OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // ------------------------------------------------------------------------------------------------- UniqueCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_CUSTOMERS = "UniqueCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_CUSTOMERS = "uniqueCustomers";

    // ------------------------------------------------------------------------------------------------------ GrossSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROSS_SALES = "GrossSales";

    /**
     * The precision of the {@value #COLUMN_NAME_GROSS_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROSS_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_GROSS_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROSS_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_SALES = "grossSales";

    // ------------------------------------------------------------------------------------------------------- Discounts

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNTS = "Discounts";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNTS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNTS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNTS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNTS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNTS = "discounts";

    // -------------------------------------------------------------------------------------------------------- NetSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NET_SALES = "NetSales";

    /**
     * The precision of the {@value #COLUMN_NAME_NET_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_NET_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_NET_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_NET_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_SALES = "netSales";

    // --------------------------------------------------------------------------------------------------- AvgOrderValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_ORDER_VALUE = "AvgOrderValue";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_ORDER_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_ORDER_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ORDER_VALUE = "avgOrderValue";

    // ------------------------------------------------------------------------------------------------- PriorMonthSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIOR_MONTH_SALES = "PriorMonthSales";

    /**
     * The precision of the {@value #COLUMN_NAME_PRIOR_MONTH_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PRIOR_MONTH_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PRIOR_MONTH_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PRIOR_MONTH_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIOR_MONTH_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIOR_MONTH_SALES = "priorMonthSales";

    // ------------------------------------------------------------------------------------------------------- MoMChange

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MO_M_CHANGE = "MoMChange";

    /**
     * The precision of the {@value #COLUMN_NAME_MO_M_CHANGE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MO_M_CHANGE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_MO_M_CHANGE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MO_M_CHANGE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MO_M_CHANGE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MO_M_CHANGE = "moMChange";

    // ------------------------------------------------------------------------------------------------ MoMChangePercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MO_M_CHANGE_PERCENT = "MoMChangePercent";

    /**
     * The precision of the {@value #COLUMN_NAME_MO_M_CHANGE_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MO_M_CHANGE_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_MO_M_CHANGE_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MO_M_CHANGE_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MO_M_CHANGE_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MO_M_CHANGE_PERCENT = "moMChangePercent";

    // -------------------------------------------------------------------------------------------------- PriorYearSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIOR_YEAR_SALES = "PriorYearSales";

    /**
     * The precision of the {@value #COLUMN_NAME_PRIOR_YEAR_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PRIOR_YEAR_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PRIOR_YEAR_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PRIOR_YEAR_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIOR_YEAR_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIOR_YEAR_SALES = "priorYearSales";

    // ------------------------------------------------------------------------------------------------------- YoYChange

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YO_Y_CHANGE = "YoYChange";

    /**
     * The precision of the {@value #COLUMN_NAME_YO_Y_CHANGE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_YO_Y_CHANGE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_YO_Y_CHANGE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_YO_Y_CHANGE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YO_Y_CHANGE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YO_Y_CHANGE = "yoYChange";

    // ------------------------------------------------------------------------------------------------ YoYChangePercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YO_Y_CHANGE_PERCENT = "YoYChangePercent";

    /**
     * The precision of the {@value #COLUMN_NAME_YO_Y_CHANGE_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_YO_Y_CHANGE_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_YO_Y_CHANGE_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_YO_Y_CHANGE_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YO_Y_CHANGE_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_YO_Y_CHANGE_PERCENT = "yoYChangePercent";

    // --------------------------------------------------------------------------------------------------- ThreeMonthAvg

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_THREE_MONTH_AVG = "ThreeMonthAvg";

    /**
     * The precision of the {@value #COLUMN_NAME_THREE_MONTH_AVG} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_THREE_MONTH_AVG = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_THREE_MONTH_AVG} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_THREE_MONTH_AVG = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_THREE_MONTH_AVG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_THREE_MONTH_AVG = "threeMonthAvg";

    // ----------------------------------------------------------------------------------------------------- SixMonthAvg

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIX_MONTH_AVG = "SixMonthAvg";

    /**
     * The precision of the {@value #COLUMN_NAME_SIX_MONTH_AVG} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SIX_MONTH_AVG = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SIX_MONTH_AVG} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SIX_MONTH_AVG = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIX_MONTH_AVG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIX_MONTH_AVG = "sixMonthAvg";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesTrendAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "year=" + year
               + ",month=" + month
               + ",quarter=" + quarter
               + ",monthStart=" + monthStart
               + ",orderCount=" + orderCount
               + ",uniqueCustomers=" + uniqueCustomers
               + ",grossSales=" + grossSales
               + ",discounts=" + discounts
               + ",netSales=" + netSales
               + ",avgOrderValue=" + avgOrderValue
               + ",priorMonthSales=" + priorMonthSales
               + ",moMChange=" + moMChange
               + ",moMChangePercent=" + moMChangePercent
               + ",priorYearSales=" + priorYearSales
               + ",yoYChange=" + yoYChange
               + ",yoYChangePercent=" + yoYChangePercent
               + ",threeMonthAvg=" + threeMonthAvg
               + ",sixMonthAvg=" + sixMonthAvg
               + "}";
    }

    // ------------------------------------------------------------------------------------------------------------ year

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR} attribute with the specified value.
     *
     * @param year new value for {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    protected void setYear(final Integer year) {
        this.year = year;
    }

    // ----------------------------------------------------------------------------------------------------------- month

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MONTH} attribute with the specified value.
     *
     * @param month new value for {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    protected void setMonth(final Integer month) {
        this.month = month;
    }

    // --------------------------------------------------------------------------------------------------------- quarter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUARTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUARTER} attribute.
     */
    public Integer getQuarter() {
        return quarter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUARTER} attribute with the specified value.
     *
     * @param quarter new value for {@value #ATTRIBUTE_NAME_QUARTER} attribute.
     */
    protected void setQuarter(final Integer quarter) {
        this.quarter = quarter;
    }

    // ------------------------------------------------------------------------------------------------------ monthStart

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MONTH_START} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MONTH_START} attribute.
     */
    public LocalDate getMonthStart() {
        return monthStart;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MONTH_START} attribute with the specified value.
     *
     * @param monthStart new value for {@value #ATTRIBUTE_NAME_MONTH_START} attribute.
     */
    protected void setMonthStart(final LocalDate monthStart) {
        this.monthStart = monthStart;
    }

    // ------------------------------------------------------------------------------------------------------ orderCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute with the specified value.
     *
     * @param orderCount new value for {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    protected void setOrderCount(final Integer orderCount) {
        this.orderCount = orderCount;
    }

    // ------------------------------------------------------------------------------------------------- uniqueCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    public Integer getUniqueCustomers() {
        return uniqueCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute with the specified value.
     *
     * @param uniqueCustomers new value for {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    protected void setUniqueCustomers(final Integer uniqueCustomers) {
        this.uniqueCustomers = uniqueCustomers;
    }

    // ------------------------------------------------------------------------------------------------------ grossSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     */
    public BigDecimal getGrossSales() {
        return grossSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute with the specified value.
     *
     * @param grossSales new value for {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     */
    protected void setGrossSales(final BigDecimal grossSales) {
        this.grossSales = grossSales;
    }

    // ------------------------------------------------------------------------------------------------------- discounts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute.
     */
    public BigDecimal getDiscounts() {
        return discounts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute with the specified value.
     *
     * @param discounts new value for {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute.
     */
    protected void setDiscounts(final BigDecimal discounts) {
        this.discounts = discounts;
    }

    // -------------------------------------------------------------------------------------------------------- netSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     */
    public BigDecimal getNetSales() {
        return netSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute with the specified value.
     *
     * @param netSales new value for {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     */
    protected void setNetSales(final BigDecimal netSales) {
        this.netSales = netSales;
    }

    // --------------------------------------------------------------------------------------------------- avgOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute with the specified value.
     *
     * @param avgOrderValue new value for {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    protected void setAvgOrderValue(final BigDecimal avgOrderValue) {
        this.avgOrderValue = avgOrderValue;
    }

    // ------------------------------------------------------------------------------------------------- priorMonthSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_SALES} attribute.
     */
    public BigDecimal getPriorMonthSales() {
        return priorMonthSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_SALES} attribute with the specified value.
     *
     * @param priorMonthSales new value for {@value #ATTRIBUTE_NAME_PRIOR_MONTH_SALES} attribute.
     */
    protected void setPriorMonthSales(final BigDecimal priorMonthSales) {
        this.priorMonthSales = priorMonthSales;
    }

    // ------------------------------------------------------------------------------------------------------- moMChange

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MO_M_CHANGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MO_M_CHANGE} attribute.
     */
    public BigDecimal getMoMChange() {
        return moMChange;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MO_M_CHANGE} attribute with the specified value.
     *
     * @param moMChange new value for {@value #ATTRIBUTE_NAME_MO_M_CHANGE} attribute.
     */
    protected void setMoMChange(final BigDecimal moMChange) {
        this.moMChange = moMChange;
    }

    // ------------------------------------------------------------------------------------------------ moMChangePercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MO_M_CHANGE_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MO_M_CHANGE_PERCENT} attribute.
     */
    public BigDecimal getMoMChangePercent() {
        return moMChangePercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MO_M_CHANGE_PERCENT} attribute with the specified value.
     *
     * @param moMChangePercent new value for {@value #ATTRIBUTE_NAME_MO_M_CHANGE_PERCENT} attribute.
     */
    protected void setMoMChangePercent(final BigDecimal moMChangePercent) {
        this.moMChangePercent = moMChangePercent;
    }

    // -------------------------------------------------------------------------------------------------- priorYearSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIOR_YEAR_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIOR_YEAR_SALES} attribute.
     */
    public BigDecimal getPriorYearSales() {
        return priorYearSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIOR_YEAR_SALES} attribute with the specified value.
     *
     * @param priorYearSales new value for {@value #ATTRIBUTE_NAME_PRIOR_YEAR_SALES} attribute.
     */
    protected void setPriorYearSales(final BigDecimal priorYearSales) {
        this.priorYearSales = priorYearSales;
    }

    // ------------------------------------------------------------------------------------------------------- yoYChange

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YO_Y_CHANGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YO_Y_CHANGE} attribute.
     */
    public BigDecimal getYoYChange() {
        return yoYChange;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YO_Y_CHANGE} attribute with the specified value.
     *
     * @param yoYChange new value for {@value #ATTRIBUTE_NAME_YO_Y_CHANGE} attribute.
     */
    protected void setYoYChange(final BigDecimal yoYChange) {
        this.yoYChange = yoYChange;
    }

    // ------------------------------------------------------------------------------------------------ yoYChangePercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YO_Y_CHANGE_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YO_Y_CHANGE_PERCENT} attribute.
     */
    public BigDecimal getYoYChangePercent() {
        return yoYChangePercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YO_Y_CHANGE_PERCENT} attribute with the specified value.
     *
     * @param yoYChangePercent new value for {@value #ATTRIBUTE_NAME_YO_Y_CHANGE_PERCENT} attribute.
     */
    protected void setYoYChangePercent(final BigDecimal yoYChangePercent) {
        this.yoYChangePercent = yoYChangePercent;
    }

    // --------------------------------------------------------------------------------------------------- threeMonthAvg

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_THREE_MONTH_AVG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_THREE_MONTH_AVG} attribute.
     */
    public BigDecimal getThreeMonthAvg() {
        return threeMonthAvg;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_THREE_MONTH_AVG} attribute with the specified value.
     *
     * @param threeMonthAvg new value for {@value #ATTRIBUTE_NAME_THREE_MONTH_AVG} attribute.
     */
    protected void setThreeMonthAvg(final BigDecimal threeMonthAvg) {
        this.threeMonthAvg = threeMonthAvg;
    }

    // ----------------------------------------------------------------------------------------------------- sixMonthAvg

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SIX_MONTH_AVG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SIX_MONTH_AVG} attribute.
     */
    public BigDecimal getSixMonthAvg() {
        return sixMonthAvg;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SIX_MONTH_AVG} attribute with the specified value.
     *
     * @param sixMonthAvg new value for {@value #ATTRIBUTE_NAME_SIX_MONTH_AVG} attribute.
     */
    protected void setSixMonthAvg(final BigDecimal sixMonthAvg) {
        this.sixMonthAvg = sixMonthAvg;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer year;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer month;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUARTER,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer quarter;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH_START,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate monthStart;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer orderCount;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer uniqueCustomers;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_GROSS_SALES - COLUMN_SCALE_GROSS_SALES, fraction = COLUMN_SCALE_GROSS_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_SALES,
            scale = COLUMN_SCALE_GROSS_SALES
    )
    private BigDecimal grossSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_DISCOUNTS - COLUMN_SCALE_DISCOUNTS, fraction = COLUMN_SCALE_DISCOUNTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNTS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNTS,
            scale = COLUMN_SCALE_DISCOUNTS
    )
    private BigDecimal discounts;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_NET_SALES - COLUMN_SCALE_NET_SALES, fraction = COLUMN_SCALE_NET_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_NET_SALES,
            scale = COLUMN_SCALE_NET_SALES
    )
    private BigDecimal netSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_AVG_ORDER_VALUE - COLUMN_SCALE_AVG_ORDER_VALUE,
            fraction = COLUMN_SCALE_AVG_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_ORDER_VALUE,
            scale = COLUMN_SCALE_AVG_ORDER_VALUE
    )
    private BigDecimal avgOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_PRIOR_MONTH_SALES - COLUMN_SCALE_PRIOR_MONTH_SALES,
            fraction = COLUMN_SCALE_PRIOR_MONTH_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIOR_MONTH_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PRIOR_MONTH_SALES,
            scale = COLUMN_SCALE_PRIOR_MONTH_SALES
    )
    private BigDecimal priorMonthSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_MO_M_CHANGE - COLUMN_SCALE_MO_M_CHANGE, fraction = COLUMN_SCALE_MO_M_CHANGE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MO_M_CHANGE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MO_M_CHANGE,
            scale = COLUMN_SCALE_MO_M_CHANGE
    )
    private BigDecimal moMChange;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_MO_M_CHANGE_PERCENT - COLUMN_SCALE_MO_M_CHANGE_PERCENT,
            fraction = COLUMN_SCALE_MO_M_CHANGE_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MO_M_CHANGE_PERCENT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MO_M_CHANGE_PERCENT,
            scale = COLUMN_SCALE_MO_M_CHANGE_PERCENT
    )
    private BigDecimal moMChangePercent;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_PRIOR_YEAR_SALES - COLUMN_SCALE_PRIOR_YEAR_SALES,
            fraction = COLUMN_SCALE_PRIOR_YEAR_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIOR_YEAR_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PRIOR_YEAR_SALES,
            scale = COLUMN_SCALE_PRIOR_YEAR_SALES
    )
    private BigDecimal priorYearSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_YO_Y_CHANGE - COLUMN_SCALE_YO_Y_CHANGE, fraction = COLUMN_SCALE_YO_Y_CHANGE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YO_Y_CHANGE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_YO_Y_CHANGE,
            scale = COLUMN_SCALE_YO_Y_CHANGE
    )
    private BigDecimal yoYChange;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_YO_Y_CHANGE_PERCENT - COLUMN_SCALE_YO_Y_CHANGE_PERCENT,
            fraction = COLUMN_SCALE_YO_Y_CHANGE_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YO_Y_CHANGE_PERCENT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_YO_Y_CHANGE_PERCENT,
            scale = COLUMN_SCALE_YO_Y_CHANGE_PERCENT
    )
    private BigDecimal yoYChangePercent;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_THREE_MONTH_AVG - COLUMN_SCALE_THREE_MONTH_AVG,
            fraction = COLUMN_SCALE_THREE_MONTH_AVG)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_THREE_MONTH_AVG,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_THREE_MONTH_AVG,
            scale = COLUMN_SCALE_THREE_MONTH_AVG
    )
    private BigDecimal threeMonthAvg;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_SIX_MONTH_AVG - COLUMN_SCALE_SIX_MONTH_AVG,
            fraction = COLUMN_SCALE_SIX_MONTH_AVG)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIX_MONTH_AVG,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SIX_MONTH_AVG,
            scale = COLUMN_SCALE_SIX_MONTH_AVG
    )
    private BigDecimal sixMonthAvg;
}

