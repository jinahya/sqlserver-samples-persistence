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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_OrderSizeDistribution} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedSalesOrderSizeDistributionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_OrderSizeDistribution";

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

    // ------------------------------------------------------------------------------------------------- OrderSizeBucket

    /**
     * The name of the {@code varchar(22), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_SIZE_BUCKET = "OrderSizeBucket";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_SIZE_BUCKET} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_SIZE_BUCKET = 22;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_SIZE_BUCKET} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_SIZE_BUCKET = "orderSizeBucket";

    // ------------------------------------------------------------------------------------------------------ BasketSize

    /**
     * The name of the {@code varchar(13), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BASKET_SIZE = "BasketSize";

    /**
     * The length of the {@value #COLUMN_NAME_BASKET_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BASKET_SIZE = 13;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BASKET_SIZE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BASKET_SIZE = "basketSize";

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

    // ---------------------------------------------------------------------------------------------------- TotalRevenue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_REVENUE = "TotalRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_REVENUE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_REVENUE = "totalRevenue";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ORDER_VALUE = "avgOrderValue";

    // ------------------------------------------------------------------------------------------------ AvgUnitsPerOrder

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_UNITS_PER_ORDER = "AvgUnitsPerOrder";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_UNITS_PER_ORDER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_UNITS_PER_ORDER = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_UNITS_PER_ORDER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_UNITS_PER_ORDER = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_UNITS_PER_ORDER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER = "avgUnitsPerOrder";

    // ---------------------------------------------------------------------------------------------------- AvgLineItems

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_LINE_ITEMS = "AvgLineItems";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_LINE_ITEMS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_LINE_ITEMS = "avgLineItems";

    // ------------------------------------------------------------------------------------------ PercentOfChannelOrders

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS = "PercentOfChannelOrders";

    /**
     * The precision of the {@value #COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PERCENT_OF_CHANNEL_ORDERS = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS = "percentOfChannelOrders";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrderSizeDistributionView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "channelName=" + channelName
               + ",customerType=" + customerType
               + ",orderSizeBucket=" + orderSizeBucket
               + ",basketSize=" + basketSize
               + ",orderCount=" + orderCount
               + ",totalRevenue=" + totalRevenue
               + ",avgOrderValue=" + avgOrderValue
               + ",avgUnitsPerOrder=" + avgUnitsPerOrder
               + ",avgLineItems=" + avgLineItems
               + ",percentOfChannelOrders=" + percentOfChannelOrders
               + "}";
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

    // ------------------------------------------------------------------------------------------------- orderSizeBucket

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute.
     */
    @Nonnull
    public String getOrderSizeBucket() {
        return orderSizeBucket;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute with the specified value.
     *
     * @param orderSizeBucket new value for {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute.
     */
    protected void setOrderSizeBucket(@Nonnull final String orderSizeBucket) {
        this.orderSizeBucket = orderSizeBucket;
    }

    // ------------------------------------------------------------------------------------------------------ basketSize

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute.
     */
    @Nonnull
    public String getBasketSize() {
        return basketSize;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute with the specified value.
     *
     * @param basketSize new value for {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute.
     */
    protected void setBasketSize(@Nonnull final String basketSize) {
        this.basketSize = basketSize;
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

    // ---------------------------------------------------------------------------------------------------- totalRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    @Nullable
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute with the specified value.
     *
     * @param totalRevenue new value for {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    protected void setTotalRevenue(@Nullable final BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    // --------------------------------------------------------------------------------------------------- avgOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    @Nullable
    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute with the specified value.
     *
     * @param avgOrderValue new value for {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    protected void setAvgOrderValue(@Nullable final BigDecimal avgOrderValue) {
        this.avgOrderValue = avgOrderValue;
    }

    // ------------------------------------------------------------------------------------------------ avgUnitsPerOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute.
     */
    @Nullable
    public BigDecimal getAvgUnitsPerOrder() {
        return avgUnitsPerOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute with the specified value.
     *
     * @param avgUnitsPerOrder new value for {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute.
     */
    protected void setAvgUnitsPerOrder(@Nullable final BigDecimal avgUnitsPerOrder) {
        this.avgUnitsPerOrder = avgUnitsPerOrder;
    }

    // ---------------------------------------------------------------------------------------------------- avgLineItems

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute.
     */
    @Nullable
    public Integer getAvgLineItems() {
        return avgLineItems;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute with the specified value.
     *
     * @param avgLineItems new value for {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute.
     */
    protected void setAvgLineItems(@Nullable final Integer avgLineItems) {
        this.avgLineItems = avgLineItems;
    }

    // ------------------------------------------------------------------------------------------ percentOfChannelOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute.
     */
    @Nullable
    public BigDecimal getPercentOfChannelOrders() {
        return percentOfChannelOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute with the specified value.
     *
     * @param percentOfChannelOrders new value for {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute.
     */
    protected void setPercentOfChannelOrders(@Nullable final BigDecimal percentOfChannelOrders) {
        this.percentOfChannelOrders = percentOfChannelOrders;
    }

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
    @Nonnull
    @Size(max = COLUMN_LENGTH_ORDER_SIZE_BUCKET)
    @NotNull
    @Column(name = COLUMN_NAME_ORDER_SIZE_BUCKET,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ORDER_SIZE_BUCKET
    )
    private String orderSizeBucket;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_BASKET_SIZE)
    @NotNull
    @Column(name = COLUMN_NAME_BASKET_SIZE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_BASKET_SIZE
    )
    private String basketSize;

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
    @Digits(integer = COLUMN_PRECISION_TOTAL_REVENUE - COLUMN_SCALE_TOTAL_REVENUE, fraction = COLUMN_SCALE_TOTAL_REVENUE)
    @Column(name = COLUMN_NAME_TOTAL_REVENUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TOTAL_REVENUE,
            scale = COLUMN_SCALE_TOTAL_REVENUE
    )
    private BigDecimal totalRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_AVG_ORDER_VALUE - COLUMN_SCALE_AVG_ORDER_VALUE, fraction = COLUMN_SCALE_AVG_ORDER_VALUE)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_ORDER_VALUE,
            scale = COLUMN_SCALE_AVG_ORDER_VALUE
    )
    private BigDecimal avgOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_AVG_UNITS_PER_ORDER - COLUMN_SCALE_AVG_UNITS_PER_ORDER, fraction = COLUMN_SCALE_AVG_UNITS_PER_ORDER)
    @Column(name = COLUMN_NAME_AVG_UNITS_PER_ORDER,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_UNITS_PER_ORDER,
            scale = COLUMN_SCALE_AVG_UNITS_PER_ORDER
    )
    private BigDecimal avgUnitsPerOrder;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_AVG_LINE_ITEMS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer avgLineItems;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_PERCENT_OF_CHANNEL_ORDERS - COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS, fraction = COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS)
    @Column(name = COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PERCENT_OF_CHANNEL_ORDERS,
            scale = COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS
    )
    private BigDecimal percentOfChannelOrders;
}

