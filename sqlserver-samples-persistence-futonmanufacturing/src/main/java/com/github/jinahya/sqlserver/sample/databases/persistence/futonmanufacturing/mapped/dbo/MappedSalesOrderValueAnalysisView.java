package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_OrderValueAnalysis} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); it contains
 * aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesOrderValueAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_OrderValueAnalysis";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TYPE = "customerType";

    // --------------------------------------------------------------------------------------------------- TerritoryName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_NAME = "TerritoryName";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_NAME = "territoryName";

    // ---------------------------------------------------------------------------------------------------------- Region

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REGION = "Region";

    /**
     * The length of the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ------------------------------------------------------------------------------------------------------ OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // ----------------------------------------------------------------------------------------------------- AvgSubtotal

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_SUBTOTAL = "AvgSubtotal";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_SUBTOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_SUBTOTAL = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_SUBTOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_SUBTOTAL = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_SUBTOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_SUBTOTAL = "avgSubtotal";

    // ----------------------------------------------------------------------------------------------------- AvgDiscount

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DISCOUNT = "AvgDiscount";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_DISCOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_DISCOUNT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_DISCOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_DISCOUNT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DISCOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DISCOUNT = "avgDiscount";

    // ---------------------------------------------------------------------------------------------------- AvgNetAmount

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_NET_AMOUNT = "AvgNetAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_NET_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_NET_AMOUNT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_NET_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_NET_AMOUNT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_NET_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_NET_AMOUNT = "avgNetAmount";

    // ---------------------------------------------------------------------------------------------------------- AvgTax

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_TAX = "AvgTax";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_TAX} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_TAX = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_TAX} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_TAX = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_TAX} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_TAX = "avgTax";

    // ----------------------------------------------------------------------------------------------------- AvgShipping

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_SHIPPING = "AvgShipping";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_SHIPPING} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_SHIPPING = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_SHIPPING} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_SHIPPING = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_SHIPPING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_SHIPPING = "avgShipping";

    // --------------------------------------------------------------------------------------------------- MinOrderValue

    /**
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIN_ORDER_VALUE = "MinOrderValue";

    /**
     * The precision of the {@value #COLUMN_NAME_MIN_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MIN_ORDER_VALUE = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_MIN_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MIN_ORDER_VALUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIN_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIN_ORDER_VALUE = "minOrderValue";

    // --------------------------------------------------------------------------------------------------- MaxOrderValue

    /**
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MAX_ORDER_VALUE = "MaxOrderValue";

    /**
     * The precision of the {@value #COLUMN_NAME_MAX_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MAX_ORDER_VALUE = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_MAX_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MAX_ORDER_VALUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAX_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAX_ORDER_VALUE = "maxOrderValue";

    // ------------------------------------------------------------------------------------------------ StdDevOrderValue

    /**
     * The name of the {@code float} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STD_DEV_ORDER_VALUE = "StdDevOrderValue";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STD_DEV_ORDER_VALUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STD_DEV_ORDER_VALUE = "stdDevOrderValue";

    // ----------------------------------------------------------------------------------------------------- SmallOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SMALL_ORDERS = "SmallOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SMALL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SMALL_ORDERS = "smallOrders";

    // ---------------------------------------------------------------------------------------------------- MediumOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MEDIUM_ORDERS = "MediumOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MEDIUM_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MEDIUM_ORDERS = "mediumOrders";

    // ----------------------------------------------------------------------------------------------------- LargeOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LARGE_ORDERS = "LargeOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LARGE_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LARGE_ORDERS = "largeOrders";

    // ------------------------------------------------------------------------------------------------ EnterpriseOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ENTERPRISE_ORDERS = "EnterpriseOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ENTERPRISE_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ENTERPRISE_ORDERS = "enterpriseOrders";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrderValueAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "channelName=" + channelName
               + ",customerType=" + customerType
               + ",territoryName=" + territoryName
               + ",region=" + region
               + ",orderCount=" + orderCount
               + ",avgSubtotal=" + avgSubtotal
               + ",avgDiscount=" + avgDiscount
               + ",avgNetAmount=" + avgNetAmount
               + ",avgTax=" + avgTax
               + ",avgShipping=" + avgShipping
               + ",minOrderValue=" + minOrderValue
               + ",maxOrderValue=" + maxOrderValue
               + ",stdDevOrderValue=" + stdDevOrderValue
               + ",smallOrders=" + smallOrders
               + ",mediumOrders=" + mediumOrders
               + ",largeOrders=" + largeOrders
               + ",enterpriseOrders=" + enterpriseOrders
               + "}";
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

    // ---------------------------------------------------------------------------------------------------- customerType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute with the specified value.
     *
     * @param customerType new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    protected void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    // --------------------------------------------------------------------------------------------------- territoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    public String getTerritoryName() {
        return territoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute with the specified value.
     *
     * @param territoryName new value for {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    protected void setTerritoryName(final String territoryName) {
        this.territoryName = territoryName;
    }

    // ---------------------------------------------------------------------------------------------------------- region

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION} attribute with the specified value.
     *
     * @param region new value for {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    protected void setRegion(final String region) {
        this.region = region;
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

    // ----------------------------------------------------------------------------------------------------- avgSubtotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_SUBTOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_SUBTOTAL} attribute.
     */
    public BigDecimal getAvgSubtotal() {
        return avgSubtotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_SUBTOTAL} attribute with the specified value.
     *
     * @param avgSubtotal new value for {@value #ATTRIBUTE_NAME_AVG_SUBTOTAL} attribute.
     */
    protected void setAvgSubtotal(final BigDecimal avgSubtotal) {
        this.avgSubtotal = avgSubtotal;
    }

    // ----------------------------------------------------------------------------------------------------- avgDiscount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT} attribute.
     */
    public BigDecimal getAvgDiscount() {
        return avgDiscount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DISCOUNT} attribute with the specified value.
     *
     * @param avgDiscount new value for {@value #ATTRIBUTE_NAME_AVG_DISCOUNT} attribute.
     */
    protected void setAvgDiscount(final BigDecimal avgDiscount) {
        this.avgDiscount = avgDiscount;
    }

    // ---------------------------------------------------------------------------------------------------- avgNetAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_NET_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_NET_AMOUNT} attribute.
     */
    public BigDecimal getAvgNetAmount() {
        return avgNetAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_NET_AMOUNT} attribute with the specified value.
     *
     * @param avgNetAmount new value for {@value #ATTRIBUTE_NAME_AVG_NET_AMOUNT} attribute.
     */
    protected void setAvgNetAmount(final BigDecimal avgNetAmount) {
        this.avgNetAmount = avgNetAmount;
    }

    // ---------------------------------------------------------------------------------------------------------- avgTax

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_TAX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_TAX} attribute.
     */
    public BigDecimal getAvgTax() {
        return avgTax;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_TAX} attribute with the specified value.
     *
     * @param avgTax new value for {@value #ATTRIBUTE_NAME_AVG_TAX} attribute.
     */
    protected void setAvgTax(final BigDecimal avgTax) {
        this.avgTax = avgTax;
    }

    // ----------------------------------------------------------------------------------------------------- avgShipping

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_SHIPPING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_SHIPPING} attribute.
     */
    public BigDecimal getAvgShipping() {
        return avgShipping;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_SHIPPING} attribute with the specified value.
     *
     * @param avgShipping new value for {@value #ATTRIBUTE_NAME_AVG_SHIPPING} attribute.
     */
    protected void setAvgShipping(final BigDecimal avgShipping) {
        this.avgShipping = avgShipping;
    }

    // --------------------------------------------------------------------------------------------------- minOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIN_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIN_ORDER_VALUE} attribute.
     */
    public BigDecimal getMinOrderValue() {
        return minOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_ORDER_VALUE} attribute with the specified value.
     *
     * @param minOrderValue new value for {@value #ATTRIBUTE_NAME_MIN_ORDER_VALUE} attribute.
     */
    protected void setMinOrderValue(final BigDecimal minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    // --------------------------------------------------------------------------------------------------- maxOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAX_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAX_ORDER_VALUE} attribute.
     */
    public BigDecimal getMaxOrderValue() {
        return maxOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_ORDER_VALUE} attribute with the specified value.
     *
     * @param maxOrderValue new value for {@value #ATTRIBUTE_NAME_MAX_ORDER_VALUE} attribute.
     */
    protected void setMaxOrderValue(final BigDecimal maxOrderValue) {
        this.maxOrderValue = maxOrderValue;
    }

    // ------------------------------------------------------------------------------------------------ stdDevOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STD_DEV_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STD_DEV_ORDER_VALUE} attribute.
     */
    public Double getStdDevOrderValue() {
        return stdDevOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STD_DEV_ORDER_VALUE} attribute with the specified value.
     *
     * @param stdDevOrderValue new value for {@value #ATTRIBUTE_NAME_STD_DEV_ORDER_VALUE} attribute.
     */
    protected void setStdDevOrderValue(final Double stdDevOrderValue) {
        this.stdDevOrderValue = stdDevOrderValue;
    }

    // ----------------------------------------------------------------------------------------------------- smallOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SMALL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SMALL_ORDERS} attribute.
     */
    public Integer getSmallOrders() {
        return smallOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SMALL_ORDERS} attribute with the specified value.
     *
     * @param smallOrders new value for {@value #ATTRIBUTE_NAME_SMALL_ORDERS} attribute.
     */
    protected void setSmallOrders(final Integer smallOrders) {
        this.smallOrders = smallOrders;
    }

    // ---------------------------------------------------------------------------------------------------- mediumOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MEDIUM_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MEDIUM_ORDERS} attribute.
     */
    public Integer getMediumOrders() {
        return mediumOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MEDIUM_ORDERS} attribute with the specified value.
     *
     * @param mediumOrders new value for {@value #ATTRIBUTE_NAME_MEDIUM_ORDERS} attribute.
     */
    protected void setMediumOrders(final Integer mediumOrders) {
        this.mediumOrders = mediumOrders;
    }

    // ----------------------------------------------------------------------------------------------------- largeOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LARGE_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LARGE_ORDERS} attribute.
     */
    public Integer getLargeOrders() {
        return largeOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LARGE_ORDERS} attribute with the specified value.
     *
     * @param largeOrders new value for {@value #ATTRIBUTE_NAME_LARGE_ORDERS} attribute.
     */
    protected void setLargeOrders(final Integer largeOrders) {
        this.largeOrders = largeOrders;
    }

    // ------------------------------------------------------------------------------------------------ enterpriseOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ENTERPRISE_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ENTERPRISE_ORDERS} attribute.
     */
    public Integer getEnterpriseOrders() {
        return enterpriseOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ENTERPRISE_ORDERS} attribute with the specified value.
     *
     * @param enterpriseOrders new value for {@value #ATTRIBUTE_NAME_ENTERPRISE_ORDERS} attribute.
     */
    protected void setEnterpriseOrders(final Integer enterpriseOrders) {
        this.enterpriseOrders = enterpriseOrders;
    }

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
    @Size(max = COLUMN_LENGTH_CUSTOMER_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_TYPE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_TYPE
    )
    private String customerType;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TERRITORY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TERRITORY_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_TERRITORY_NAME
    )
    private String territoryName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_REGION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REGION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_REGION
    )
    private String region;

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
    @Digits(integer = COLUMN_PRECISION_AVG_SUBTOTAL - COLUMN_SCALE_AVG_SUBTOTAL, fraction = COLUMN_SCALE_AVG_SUBTOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_SUBTOTAL,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_SUBTOTAL,
            scale = COLUMN_SCALE_AVG_SUBTOTAL
    )
    private BigDecimal avgSubtotal;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_DISCOUNT - COLUMN_SCALE_AVG_DISCOUNT, fraction = COLUMN_SCALE_AVG_DISCOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DISCOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_DISCOUNT,
            scale = COLUMN_SCALE_AVG_DISCOUNT
    )
    private BigDecimal avgDiscount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_NET_AMOUNT - COLUMN_SCALE_AVG_NET_AMOUNT,
            fraction = COLUMN_SCALE_AVG_NET_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_NET_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_NET_AMOUNT,
            scale = COLUMN_SCALE_AVG_NET_AMOUNT
    )
    private BigDecimal avgNetAmount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_TAX - COLUMN_SCALE_AVG_TAX, fraction = COLUMN_SCALE_AVG_TAX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_TAX,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_TAX,
            scale = COLUMN_SCALE_AVG_TAX
    )
    private BigDecimal avgTax;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_SHIPPING - COLUMN_SCALE_AVG_SHIPPING, fraction = COLUMN_SCALE_AVG_SHIPPING)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_SHIPPING,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_SHIPPING,
            scale = COLUMN_SCALE_AVG_SHIPPING
    )
    private BigDecimal avgShipping;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_MIN_ORDER_VALUE - COLUMN_SCALE_MIN_ORDER_VALUE,
            fraction = COLUMN_SCALE_MIN_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIN_ORDER_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_MIN_ORDER_VALUE,
            scale = COLUMN_SCALE_MIN_ORDER_VALUE
    )
    private BigDecimal minOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_MAX_ORDER_VALUE - COLUMN_SCALE_MAX_ORDER_VALUE,
            fraction = COLUMN_SCALE_MAX_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MAX_ORDER_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_MAX_ORDER_VALUE,
            scale = COLUMN_SCALE_MAX_ORDER_VALUE
    )
    private BigDecimal maxOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STD_DEV_ORDER_VALUE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Double stdDevOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SMALL_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer smallOrders;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MEDIUM_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer mediumOrders;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LARGE_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer largeOrders;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ENTERPRISE_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer enterpriseOrders;
}

