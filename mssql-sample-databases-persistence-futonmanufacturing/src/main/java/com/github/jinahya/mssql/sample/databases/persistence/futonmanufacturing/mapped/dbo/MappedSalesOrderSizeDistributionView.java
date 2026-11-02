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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_OrderSizeDistribution} view.
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
public abstract class MappedSalesOrderSizeDistributionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_SIZE_BUCKET} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_BASKET_SIZE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BASKET_SIZE = "basketSize";

    // ------------------------------------------------------------------------------------------------------ OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_LINE_ITEMS} column. The value is {@value}.
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

    // ------------------------------------------------------------------------------------------------- orderSizeBucket

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute.
     */
    public String getOrderSizeBucket() {
        return orderSizeBucket;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute with the specified value.
     *
     * @param orderSizeBucket new value for {@value #ATTRIBUTE_NAME_ORDER_SIZE_BUCKET} attribute.
     */
    protected void setOrderSizeBucket(final String orderSizeBucket) {
        this.orderSizeBucket = orderSizeBucket;
    }

    // ------------------------------------------------------------------------------------------------------ basketSize

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute.
     */
    public String getBasketSize() {
        return basketSize;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute with the specified value.
     *
     * @param basketSize new value for {@value #ATTRIBUTE_NAME_BASKET_SIZE} attribute.
     */
    protected void setBasketSize(final String basketSize) {
        this.basketSize = basketSize;
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

    // ---------------------------------------------------------------------------------------------------- totalRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute with the specified value.
     *
     * @param totalRevenue new value for {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    protected void setTotalRevenue(final BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
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

    // ------------------------------------------------------------------------------------------------ avgUnitsPerOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute.
     */
    public BigDecimal getAvgUnitsPerOrder() {
        return avgUnitsPerOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute with the specified value.
     *
     * @param avgUnitsPerOrder new value for {@value #ATTRIBUTE_NAME_AVG_UNITS_PER_ORDER} attribute.
     */
    protected void setAvgUnitsPerOrder(final BigDecimal avgUnitsPerOrder) {
        this.avgUnitsPerOrder = avgUnitsPerOrder;
    }

    // ---------------------------------------------------------------------------------------------------- avgLineItems

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute.
     */
    public Integer getAvgLineItems() {
        return avgLineItems;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute with the specified value.
     *
     * @param avgLineItems new value for {@value #ATTRIBUTE_NAME_AVG_LINE_ITEMS} attribute.
     */
    protected void setAvgLineItems(final Integer avgLineItems) {
        this.avgLineItems = avgLineItems;
    }

    // ------------------------------------------------------------------------------------------ percentOfChannelOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute.
     */
    public BigDecimal getPercentOfChannelOrders() {
        return percentOfChannelOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute with the specified value.
     *
     * @param percentOfChannelOrders new value for {@value #ATTRIBUTE_NAME_PERCENT_OF_CHANNEL_ORDERS} attribute.
     */
    protected void setPercentOfChannelOrders(final BigDecimal percentOfChannelOrders) {
        this.percentOfChannelOrders = percentOfChannelOrders;
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
    @Size(max = COLUMN_LENGTH_ORDER_SIZE_BUCKET)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_SIZE_BUCKET,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ORDER_SIZE_BUCKET
    )
    private String orderSizeBucket;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_BASKET_SIZE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BASKET_SIZE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BASKET_SIZE
    )
    private String basketSize;

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
    @Digits(integer = COLUMN_PRECISION_TOTAL_REVENUE - COLUMN_SCALE_TOTAL_REVENUE,
            fraction = COLUMN_SCALE_TOTAL_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_REVENUE,
            scale = COLUMN_SCALE_TOTAL_REVENUE
    )
    private BigDecimal totalRevenue;

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
    @Digits(integer = COLUMN_PRECISION_AVG_UNITS_PER_ORDER - COLUMN_SCALE_AVG_UNITS_PER_ORDER,
            fraction = COLUMN_SCALE_AVG_UNITS_PER_ORDER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_UNITS_PER_ORDER,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_UNITS_PER_ORDER,
            scale = COLUMN_SCALE_AVG_UNITS_PER_ORDER
    )
    private BigDecimal avgUnitsPerOrder;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_LINE_ITEMS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer avgLineItems;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PERCENT_OF_CHANNEL_ORDERS - COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS,
            fraction = COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PERCENT_OF_CHANNEL_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PERCENT_OF_CHANNEL_ORDERS,
            scale = COLUMN_SCALE_PERCENT_OF_CHANNEL_ORDERS
    )
    private BigDecimal percentOfChannelOrders;
}

