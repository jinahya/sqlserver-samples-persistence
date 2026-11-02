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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ByChannel} view.
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
public abstract class MappedSalesByChannelView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ByChannel";

    // ----------------------------------------------------------------------------------------------------- ChannelCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_CODE = "ChannelCode";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_CODE = "channelCode";

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

    // -------------------------------------------------------------------------------------------------- TotalDiscounts

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_DISCOUNTS = "TotalDiscounts";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_DISCOUNTS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_DISCOUNTS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DISCOUNTS = "totalDiscounts";

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

    // -------------------------------------------------------------------------------------------------- DeliveredSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERED_SALES = "DeliveredSales";

    /**
     * The precision of the {@value #COLUMN_NAME_DELIVERED_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DELIVERED_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_DELIVERED_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DELIVERED_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERED_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERED_SALES = "deliveredSales";

    // --------------------------------------------------------------------------------------------------- PipelineSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PIPELINE_SALES = "PipelineSales";

    /**
     * The precision of the {@value #COLUMN_NAME_PIPELINE_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PIPELINE_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PIPELINE_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PIPELINE_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PIPELINE_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PIPELINE_SALES = "pipelineSales";

    // ---------------------------------------------------------------------------------------------- AvgDiscountPercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DISCOUNT_PERCENT = "AvgDiscountPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_DISCOUNT_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_DISCOUNT_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DISCOUNT_PERCENT = "avgDiscountPercent";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesByChannelView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "channelCode=" + channelCode
               + ",channelName=" + channelName
               + ",year=" + year
               + ",month=" + month
               + ",quarter=" + quarter
               + ",orderCount=" + orderCount
               + ",uniqueCustomers=" + uniqueCustomers
               + ",grossSales=" + grossSales
               + ",totalDiscounts=" + totalDiscounts
               + ",netSales=" + netSales
               + ",avgOrderValue=" + avgOrderValue
               + ",deliveredSales=" + deliveredSales
               + ",pipelineSales=" + pipelineSales
               + ",avgDiscountPercent=" + avgDiscountPercent
               + "}";
    }

    // ----------------------------------------------------------------------------------------------------- channelCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute.
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute with the specified value.
     *
     * @param channelCode new value for {@value #ATTRIBUTE_NAME_CHANNEL_CODE} attribute.
     */
    protected void setChannelCode(final String channelCode) {
        this.channelCode = channelCode;
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

    // -------------------------------------------------------------------------------------------------- totalDiscounts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute.
     */
    public BigDecimal getTotalDiscounts() {
        return totalDiscounts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute with the specified value.
     *
     * @param totalDiscounts new value for {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute.
     */
    protected void setTotalDiscounts(final BigDecimal totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
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

    // -------------------------------------------------------------------------------------------------- deliveredSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERED_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERED_SALES} attribute.
     */
    public BigDecimal getDeliveredSales() {
        return deliveredSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERED_SALES} attribute with the specified value.
     *
     * @param deliveredSales new value for {@value #ATTRIBUTE_NAME_DELIVERED_SALES} attribute.
     */
    protected void setDeliveredSales(final BigDecimal deliveredSales) {
        this.deliveredSales = deliveredSales;
    }

    // --------------------------------------------------------------------------------------------------- pipelineSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PIPELINE_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PIPELINE_SALES} attribute.
     */
    public BigDecimal getPipelineSales() {
        return pipelineSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PIPELINE_SALES} attribute with the specified value.
     *
     * @param pipelineSales new value for {@value #ATTRIBUTE_NAME_PIPELINE_SALES} attribute.
     */
    protected void setPipelineSales(final BigDecimal pipelineSales) {
        this.pipelineSales = pipelineSales;
    }

    // ---------------------------------------------------------------------------------------------- avgDiscountPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PERCENT} attribute.
     */
    public BigDecimal getAvgDiscountPercent() {
        return avgDiscountPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PERCENT} attribute with the specified value.
     *
     * @param avgDiscountPercent new value for {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PERCENT} attribute.
     */
    protected void setAvgDiscountPercent(final BigDecimal avgDiscountPercent) {
        this.avgDiscountPercent = avgDiscountPercent;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CHANNEL_CODE
    )
    private String channelCode;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUARTER,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer quarter;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer orderCount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer uniqueCustomers;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_GROSS_SALES - COLUMN_SCALE_GROSS_SALES, fraction = COLUMN_SCALE_GROSS_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_GROSS_SALES,
            scale = COLUMN_SCALE_GROSS_SALES
    )
    private BigDecimal grossSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_DISCOUNTS - COLUMN_SCALE_TOTAL_DISCOUNTS,
            fraction = COLUMN_SCALE_TOTAL_DISCOUNTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_DISCOUNTS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_DISCOUNTS,
            scale = COLUMN_SCALE_TOTAL_DISCOUNTS
    )
    private BigDecimal totalDiscounts;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_NET_SALES - COLUMN_SCALE_NET_SALES, fraction = COLUMN_SCALE_NET_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_NET_SALES,
            scale = COLUMN_SCALE_NET_SALES
    )
    private BigDecimal netSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_ORDER_VALUE - COLUMN_SCALE_AVG_ORDER_VALUE,
            fraction = COLUMN_SCALE_AVG_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_ORDER_VALUE,
            scale = COLUMN_SCALE_AVG_ORDER_VALUE
    )
    private BigDecimal avgOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_DELIVERED_SALES - COLUMN_SCALE_DELIVERED_SALES,
            fraction = COLUMN_SCALE_DELIVERED_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERED_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_DELIVERED_SALES,
            scale = COLUMN_SCALE_DELIVERED_SALES
    )
    private BigDecimal deliveredSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PIPELINE_SALES - COLUMN_SCALE_PIPELINE_SALES,
            fraction = COLUMN_SCALE_PIPELINE_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PIPELINE_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PIPELINE_SALES,
            scale = COLUMN_SCALE_PIPELINE_SALES
    )
    private BigDecimal pipelineSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_DISCOUNT_PERCENT - COLUMN_SCALE_AVG_DISCOUNT_PERCENT,
            fraction = COLUMN_SCALE_AVG_DISCOUNT_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DISCOUNT_PERCENT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_DISCOUNT_PERCENT,
            scale = COLUMN_SCALE_AVG_DISCOUNT_PERCENT
    )
    private BigDecimal avgDiscountPercent;
}

