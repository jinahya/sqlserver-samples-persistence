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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_GrowthAnalysis} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesGrowthAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_GrowthAnalysis";

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

    // ----------------------------------------------------------------------------------------------------- ChannelName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_NAME = "ChannelName";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_NAME = "channelName";

    // ---------------------------------------------------------------------------------------------------------- Orders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDERS = "Orders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDERS = "orders";

    // ------------------------------------------------------------------------------------------------------- Customers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMERS = "Customers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMERS = "customers";

    // --------------------------------------------------------------------------------------------------------- Revenue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVENUE = "Revenue";

    /**
     * The precision of the {@value #COLUMN_NAME_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REVENUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVENUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVENUE = "revenue";

    // ----------------------------------------------------------------------------------------------- PriorMonthRevenue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIOR_MONTH_REVENUE = "PriorMonthRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_PRIOR_MONTH_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PRIOR_MONTH_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PRIOR_MONTH_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PRIOR_MONTH_REVENUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIOR_MONTH_REVENUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIOR_MONTH_REVENUE = "priorMonthRevenue";

    // --------------------------------------------------------------------------------------------------- RevenueGrowth

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVENUE_GROWTH = "RevenueGrowth";

    /**
     * The precision of the {@value #COLUMN_NAME_REVENUE_GROWTH} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REVENUE_GROWTH = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_REVENUE_GROWTH} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REVENUE_GROWTH = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVENUE_GROWTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVENUE_GROWTH = "revenueGrowth";

    // --------------------------------------------------------------------------------------------------- GrowthPercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROWTH_PERCENT = "GrowthPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_GROWTH_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROWTH_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_GROWTH_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROWTH_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROWTH_PERCENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROWTH_PERCENT = "growthPercent";

    // --------------------------------------------------------------------------------------------- PriorMonthCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIOR_MONTH_CUSTOMERS = "PriorMonthCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIOR_MONTH_CUSTOMERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIOR_MONTH_CUSTOMERS = "priorMonthCustomers";

    // -------------------------------------------------------------------------------------------------- CustomerGrowth

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_GROWTH = "CustomerGrowth";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_GROWTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_GROWTH = "customerGrowth";

    // ----------------------------------------------------------------------------------------------- CumulativeRevenue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUMULATIVE_REVENUE = "CumulativeRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_CUMULATIVE_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CUMULATIVE_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_CUMULATIVE_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CUMULATIVE_REVENUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUMULATIVE_REVENUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUMULATIVE_REVENUE = "cumulativeRevenue";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesGrowthAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "year=" + year
               + ",month=" + month
               + ",channelName=" + channelName
               + ",orders=" + orders
               + ",customers=" + customers
               + ",revenue=" + revenue
               + ",priorMonthRevenue=" + priorMonthRevenue
               + ",revenueGrowth=" + revenueGrowth
               + ",growthPercent=" + growthPercent
               + ",priorMonthCustomers=" + priorMonthCustomers
               + ",customerGrowth=" + customerGrowth
               + ",cumulativeRevenue=" + cumulativeRevenue
               + ",threeMonthAvg=" + threeMonthAvg
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

    // ----------------------------------------------------------------------------------------------------- channelName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute with the specified value.
     *
     * @param channelName new value for {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    protected void setChannelName(final String channelName) {
        this.channelName = channelName;
    }

    // ---------------------------------------------------------------------------------------------------------- orders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDERS} attribute.
     */
    public Integer getOrders() {
        return orders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDERS} attribute with the specified value.
     *
     * @param orders new value for {@value #ATTRIBUTE_NAME_ORDERS} attribute.
     */
    protected void setOrders(final Integer orders) {
        this.orders = orders;
    }

    // ------------------------------------------------------------------------------------------------------- customers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMERS} attribute.
     */
    public Integer getCustomers() {
        return customers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMERS} attribute with the specified value.
     *
     * @param customers new value for {@value #ATTRIBUTE_NAME_CUSTOMERS} attribute.
     */
    protected void setCustomers(final Integer customers) {
        this.customers = customers;
    }

    // --------------------------------------------------------------------------------------------------------- revenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVENUE} attribute.
     */
    public BigDecimal getRevenue() {
        return revenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVENUE} attribute with the specified value.
     *
     * @param revenue new value for {@value #ATTRIBUTE_NAME_REVENUE} attribute.
     */
    protected void setRevenue(final BigDecimal revenue) {
        this.revenue = revenue;
    }

    // ----------------------------------------------------------------------------------------------- priorMonthRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_REVENUE} attribute.
     */
    public BigDecimal getPriorMonthRevenue() {
        return priorMonthRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_REVENUE} attribute with the specified value.
     *
     * @param priorMonthRevenue new value for {@value #ATTRIBUTE_NAME_PRIOR_MONTH_REVENUE} attribute.
     */
    protected void setPriorMonthRevenue(final BigDecimal priorMonthRevenue) {
        this.priorMonthRevenue = priorMonthRevenue;
    }

    // --------------------------------------------------------------------------------------------------- revenueGrowth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVENUE_GROWTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVENUE_GROWTH} attribute.
     */
    public BigDecimal getRevenueGrowth() {
        return revenueGrowth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVENUE_GROWTH} attribute with the specified value.
     *
     * @param revenueGrowth new value for {@value #ATTRIBUTE_NAME_REVENUE_GROWTH} attribute.
     */
    protected void setRevenueGrowth(final BigDecimal revenueGrowth) {
        this.revenueGrowth = revenueGrowth;
    }

    // --------------------------------------------------------------------------------------------------- growthPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROWTH_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROWTH_PERCENT} attribute.
     */
    public BigDecimal getGrowthPercent() {
        return growthPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROWTH_PERCENT} attribute with the specified value.
     *
     * @param growthPercent new value for {@value #ATTRIBUTE_NAME_GROWTH_PERCENT} attribute.
     */
    protected void setGrowthPercent(final BigDecimal growthPercent) {
        this.growthPercent = growthPercent;
    }

    // --------------------------------------------------------------------------------------------- priorMonthCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_CUSTOMERS} attribute.
     */
    public Integer getPriorMonthCustomers() {
        return priorMonthCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIOR_MONTH_CUSTOMERS} attribute with the specified value.
     *
     * @param priorMonthCustomers new value for {@value #ATTRIBUTE_NAME_PRIOR_MONTH_CUSTOMERS} attribute.
     */
    protected void setPriorMonthCustomers(final Integer priorMonthCustomers) {
        this.priorMonthCustomers = priorMonthCustomers;
    }

    // -------------------------------------------------------------------------------------------------- customerGrowth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_GROWTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_GROWTH} attribute.
     */
    public Integer getCustomerGrowth() {
        return customerGrowth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_GROWTH} attribute with the specified value.
     *
     * @param customerGrowth new value for {@value #ATTRIBUTE_NAME_CUSTOMER_GROWTH} attribute.
     */
    protected void setCustomerGrowth(final Integer customerGrowth) {
        this.customerGrowth = customerGrowth;
    }

    // ----------------------------------------------------------------------------------------------- cumulativeRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUMULATIVE_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUMULATIVE_REVENUE} attribute.
     */
    public BigDecimal getCumulativeRevenue() {
        return cumulativeRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUMULATIVE_REVENUE} attribute with the specified value.
     *
     * @param cumulativeRevenue new value for {@value #ATTRIBUTE_NAME_CUMULATIVE_REVENUE} attribute.
     */
    protected void setCumulativeRevenue(final BigDecimal cumulativeRevenue) {
        this.cumulativeRevenue = cumulativeRevenue;
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

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer year;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer month;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer orders;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer customers;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_REVENUE - COLUMN_SCALE_REVENUE, fraction = COLUMN_SCALE_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_REVENUE,
            scale = COLUMN_SCALE_REVENUE
    )
    private BigDecimal revenue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PRIOR_MONTH_REVENUE - COLUMN_SCALE_PRIOR_MONTH_REVENUE,
            fraction = COLUMN_SCALE_PRIOR_MONTH_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIOR_MONTH_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PRIOR_MONTH_REVENUE,
            scale = COLUMN_SCALE_PRIOR_MONTH_REVENUE
    )
    private BigDecimal priorMonthRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_REVENUE_GROWTH - COLUMN_SCALE_REVENUE_GROWTH,
            fraction = COLUMN_SCALE_REVENUE_GROWTH)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REVENUE_GROWTH,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_REVENUE_GROWTH,
            scale = COLUMN_SCALE_REVENUE_GROWTH
    )
    private BigDecimal revenueGrowth;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_GROWTH_PERCENT - COLUMN_SCALE_GROWTH_PERCENT,
            fraction = COLUMN_SCALE_GROWTH_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROWTH_PERCENT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_GROWTH_PERCENT,
            scale = COLUMN_SCALE_GROWTH_PERCENT
    )
    private BigDecimal growthPercent;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIOR_MONTH_CUSTOMERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer priorMonthCustomers;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_GROWTH,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer customerGrowth;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_CUMULATIVE_REVENUE - COLUMN_SCALE_CUMULATIVE_REVENUE,
            fraction = COLUMN_SCALE_CUMULATIVE_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUMULATIVE_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_CUMULATIVE_REVENUE,
            scale = COLUMN_SCALE_CUMULATIVE_REVENUE
    )
    private BigDecimal cumulativeRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_THREE_MONTH_AVG - COLUMN_SCALE_THREE_MONTH_AVG,
            fraction = COLUMN_SCALE_THREE_MONTH_AVG)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_THREE_MONTH_AVG,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_THREE_MONTH_AVG,
            scale = COLUMN_SCALE_THREE_MONTH_AVG
    )
    private BigDecimal threeMonthAvg;
}

