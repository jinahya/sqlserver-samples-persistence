package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_DiscountAnalysis} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedSalesDiscountAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_DiscountAnalysis";

    // ------------------------------------------------------------------------------------------------------------ Year

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR = "Year";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR = "year";

    // ----------------------------------------------------------------------------------------------------------- Month

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONTH = "Month";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONTH} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_NAME = "channelName";

    // ---------------------------------------------------------------------------------------------------- CustomerType

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_TYPE = "CustomerType";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_TYPE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TYPE = "customerType";

    // ------------------------------------------------------------------------------------------------------ OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_SALES} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_SALES} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_SALES = "netSales";

    // ------------------------------------------------------------------------------------------------- DiscountPercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_PERCENT = "DiscountPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PERCENT = "discountPercent";

    // --------------------------------------------------------------------------------------------- AvgDiscountPerOrder

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DISCOUNT_PER_ORDER = "AvgDiscountPerOrder";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_DISCOUNT_PER_ORDER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_DISCOUNT_PER_ORDER = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_DISCOUNT_PER_ORDER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_DISCOUNT_PER_ORDER = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DISCOUNT_PER_ORDER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DISCOUNT_PER_ORDER = "avgDiscountPerOrder";

    // ------------------------------------------------------------------------------------------------ NoDiscountOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NO_DISCOUNT_ORDERS = "NoDiscountOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NO_DISCOUNT_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NO_DISCOUNT_ORDERS = "noDiscountOrders";

    // ----------------------------------------------------------------------------------------------- LowDiscountOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOW_DISCOUNT_ORDERS = "LowDiscountOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOW_DISCOUNT_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOW_DISCOUNT_ORDERS = "lowDiscountOrders";

    // -------------------------------------------------------------------------------------------- MediumDiscountOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MEDIUM_DISCOUNT_ORDERS = "MediumDiscountOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MEDIUM_DISCOUNT_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MEDIUM_DISCOUNT_ORDERS = "mediumDiscountOrders";

    // ---------------------------------------------------------------------------------------------- HighDiscountOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HIGH_DISCOUNT_ORDERS = "HighDiscountOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HIGH_DISCOUNT_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_HIGH_DISCOUNT_ORDERS = "highDiscountOrders";

    // ----------------------------------------------------------------------------------------------------- GrossProfit

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROSS_PROFIT = "GrossProfit";

    /**
     * The precision of the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROSS_PROFIT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROSS_PROFIT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_PROFIT = "grossProfit";

    // ---------------------------------------------------------------------------------------------- GrossMarginPercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROSS_MARGIN_PERCENT = "GrossMarginPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROSS_MARGIN_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROSS_MARGIN_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT = "grossMarginPercent";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesDiscountAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "year=" + year
               + ",month=" + month
               + ",channelName=" + channelName
               + ",customerType=" + customerType
               + ",orderCount=" + orderCount
               + ",grossSales=" + grossSales
               + ",totalDiscounts=" + totalDiscounts
               + ",netSales=" + netSales
               + ",discountPercent=" + discountPercent
               + ",avgDiscountPerOrder=" + avgDiscountPerOrder
               + ",noDiscountOrders=" + noDiscountOrders
               + ",lowDiscountOrders=" + lowDiscountOrders
               + ",mediumDiscountOrders=" + mediumDiscountOrders
               + ",highDiscountOrders=" + highDiscountOrders
               + ",grossProfit=" + grossProfit
               + ",grossMarginPercent=" + grossMarginPercent
               + "}";
    }

    // ------------------------------------------------------------------------------------------------------------ year

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    @Nullable
    public Integer getYear() {
        return year;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR} attribute with the specified value.
     *
     * @param year new value for {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    protected void setYear(@Nullable final Integer year) {
        this.year = year;
    }

    // ----------------------------------------------------------------------------------------------------------- month

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    @Nullable
    public Integer getMonth() {
        return month;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MONTH} attribute with the specified value.
     *
     * @param month new value for {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    protected void setMonth(@Nullable final Integer month) {
        this.month = month;
    }

    // ----------------------------------------------------------------------------------------------------- channelName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    @Nonnull
    public String getChannelName() {
        return channelName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute with the specified value.
     *
     * @param channelName new value for {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    protected void setChannelName(@Nonnull final String channelName) {
        this.channelName = channelName;
    }

    // ---------------------------------------------------------------------------------------------------- customerType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    @Nullable
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute with the specified value.
     *
     * @param customerType new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    protected void setCustomerType(@Nullable final String customerType) {
        this.customerType = customerType;
    }

    // ------------------------------------------------------------------------------------------------------ orderCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    @Nullable
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute with the specified value.
     *
     * @param orderCount new value for {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    protected void setOrderCount(@Nullable final Integer orderCount) {
        this.orderCount = orderCount;
    }

    // ------------------------------------------------------------------------------------------------------ grossSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     */
    @Nullable
    public BigDecimal getGrossSales() {
        return grossSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute with the specified value.
     *
     * @param grossSales new value for {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     */
    protected void setGrossSales(@Nullable final BigDecimal grossSales) {
        this.grossSales = grossSales;
    }

    // -------------------------------------------------------------------------------------------------- totalDiscounts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute.
     */
    @Nullable
    public BigDecimal getTotalDiscounts() {
        return totalDiscounts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute with the specified value.
     *
     * @param totalDiscounts new value for {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute.
     */
    protected void setTotalDiscounts(@Nullable final BigDecimal totalDiscounts) {
        this.totalDiscounts = totalDiscounts;
    }

    // -------------------------------------------------------------------------------------------------------- netSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     */
    @Nullable
    public BigDecimal getNetSales() {
        return netSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute with the specified value.
     *
     * @param netSales new value for {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     */
    protected void setNetSales(@Nullable final BigDecimal netSales) {
        this.netSales = netSales;
    }

    // ------------------------------------------------------------------------------------------------- discountPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    @Nullable
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute with the specified value.
     *
     * @param discountPercent new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    protected void setDiscountPercent(@Nullable final BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    // --------------------------------------------------------------------------------------------- avgDiscountPerOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PER_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PER_ORDER} attribute.
     */
    @Nullable
    public BigDecimal getAvgDiscountPerOrder() {
        return avgDiscountPerOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PER_ORDER} attribute with the specified value.
     *
     * @param avgDiscountPerOrder new value for {@value #ATTRIBUTE_NAME_AVG_DISCOUNT_PER_ORDER} attribute.
     */
    protected void setAvgDiscountPerOrder(@Nullable final BigDecimal avgDiscountPerOrder) {
        this.avgDiscountPerOrder = avgDiscountPerOrder;
    }

    // ------------------------------------------------------------------------------------------------ noDiscountOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NO_DISCOUNT_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NO_DISCOUNT_ORDERS} attribute.
     */
    @Nullable
    public Integer getNoDiscountOrders() {
        return noDiscountOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NO_DISCOUNT_ORDERS} attribute with the specified value.
     *
     * @param noDiscountOrders new value for {@value #ATTRIBUTE_NAME_NO_DISCOUNT_ORDERS} attribute.
     */
    protected void setNoDiscountOrders(@Nullable final Integer noDiscountOrders) {
        this.noDiscountOrders = noDiscountOrders;
    }

    // ----------------------------------------------------------------------------------------------- lowDiscountOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOW_DISCOUNT_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOW_DISCOUNT_ORDERS} attribute.
     */
    @Nullable
    public Integer getLowDiscountOrders() {
        return lowDiscountOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOW_DISCOUNT_ORDERS} attribute with the specified value.
     *
     * @param lowDiscountOrders new value for {@value #ATTRIBUTE_NAME_LOW_DISCOUNT_ORDERS} attribute.
     */
    protected void setLowDiscountOrders(@Nullable final Integer lowDiscountOrders) {
        this.lowDiscountOrders = lowDiscountOrders;
    }

    // -------------------------------------------------------------------------------------------- mediumDiscountOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MEDIUM_DISCOUNT_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MEDIUM_DISCOUNT_ORDERS} attribute.
     */
    @Nullable
    public Integer getMediumDiscountOrders() {
        return mediumDiscountOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MEDIUM_DISCOUNT_ORDERS} attribute with the specified value.
     *
     * @param mediumDiscountOrders new value for {@value #ATTRIBUTE_NAME_MEDIUM_DISCOUNT_ORDERS} attribute.
     */
    protected void setMediumDiscountOrders(@Nullable final Integer mediumDiscountOrders) {
        this.mediumDiscountOrders = mediumDiscountOrders;
    }

    // ---------------------------------------------------------------------------------------------- highDiscountOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HIGH_DISCOUNT_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HIGH_DISCOUNT_ORDERS} attribute.
     */
    @Nullable
    public Integer getHighDiscountOrders() {
        return highDiscountOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HIGH_DISCOUNT_ORDERS} attribute with the specified value.
     *
     * @param highDiscountOrders new value for {@value #ATTRIBUTE_NAME_HIGH_DISCOUNT_ORDERS} attribute.
     */
    protected void setHighDiscountOrders(@Nullable final Integer highDiscountOrders) {
        this.highDiscountOrders = highDiscountOrders;
    }

    // ----------------------------------------------------------------------------------------------------- grossProfit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     */
    @Nullable
    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute with the specified value.
     *
     * @param grossProfit new value for {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     */
    protected void setGrossProfit(@Nullable final BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    // ---------------------------------------------------------------------------------------------- grossMarginPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     */
    @Nullable
    public BigDecimal getGrossMarginPercent() {
        return grossMarginPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute with the specified value.
     *
     * @param grossMarginPercent new value for {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     */
    protected void setGrossMarginPercent(@Nullable final BigDecimal grossMarginPercent) {
        this.grossMarginPercent = grossMarginPercent;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_YEAR,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer year;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_MONTH,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer month;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_CUSTOMER_TYPE)
    @Column(name = COLUMN_NAME_CUSTOMER_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_TYPE
    )
    private String customerType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ORDER_COUNT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer orderCount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_GROSS_SALES - COLUMN_SCALE_GROSS_SALES, fraction = COLUMN_SCALE_GROSS_SALES)
    @Column(name = COLUMN_NAME_GROSS_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_SALES,
            scale = COLUMN_SCALE_GROSS_SALES
    )
    private BigDecimal grossSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_TOTAL_DISCOUNTS - COLUMN_SCALE_TOTAL_DISCOUNTS, fraction = COLUMN_SCALE_TOTAL_DISCOUNTS)
    @Column(name = COLUMN_NAME_TOTAL_DISCOUNTS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TOTAL_DISCOUNTS,
            scale = COLUMN_SCALE_TOTAL_DISCOUNTS
    )
    private BigDecimal totalDiscounts;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_NET_SALES - COLUMN_SCALE_NET_SALES, fraction = COLUMN_SCALE_NET_SALES)
    @Column(name = COLUMN_NAME_NET_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_NET_SALES,
            scale = COLUMN_SCALE_NET_SALES
    )
    private BigDecimal netSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_PERCENT - COLUMN_SCALE_DISCOUNT_PERCENT, fraction = COLUMN_SCALE_DISCOUNT_PERCENT)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNT_PERCENT,
            scale = COLUMN_SCALE_DISCOUNT_PERCENT
    )
    private BigDecimal discountPercent;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_AVG_DISCOUNT_PER_ORDER - COLUMN_SCALE_AVG_DISCOUNT_PER_ORDER, fraction = COLUMN_SCALE_AVG_DISCOUNT_PER_ORDER)
    @Column(name = COLUMN_NAME_AVG_DISCOUNT_PER_ORDER,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_DISCOUNT_PER_ORDER,
            scale = COLUMN_SCALE_AVG_DISCOUNT_PER_ORDER
    )
    private BigDecimal avgDiscountPerOrder;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_NO_DISCOUNT_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer noDiscountOrders;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_LOW_DISCOUNT_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer lowDiscountOrders;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_MEDIUM_DISCOUNT_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer mediumDiscountOrders;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_HIGH_DISCOUNT_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer highDiscountOrders;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_GROSS_PROFIT - COLUMN_SCALE_GROSS_PROFIT, fraction = COLUMN_SCALE_GROSS_PROFIT)
    @Column(name = COLUMN_NAME_GROSS_PROFIT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_PROFIT,
            scale = COLUMN_SCALE_GROSS_PROFIT
    )
    private BigDecimal grossProfit;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_GROSS_MARGIN_PERCENT - COLUMN_SCALE_GROSS_MARGIN_PERCENT, fraction = COLUMN_SCALE_GROSS_MARGIN_PERCENT)
    @Column(name = COLUMN_NAME_GROSS_MARGIN_PERCENT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_MARGIN_PERCENT,
            scale = COLUMN_SCALE_GROSS_MARGIN_PERCENT
    )
    private BigDecimal grossMarginPercent;
}

