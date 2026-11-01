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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ProductMixAnalysis} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 */
@MappedSuperclass
public abstract class MappedSalesProductMixAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ProductMixAnalysis";

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

    // ------------------------------------------------------------------------------------------------------ ProductMix

    /**
     * The name of the {@code nvarchar(4000)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MIX = "ProductMix";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_MIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_MIX = 4000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MIX} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MIX = "productMix";

    // -------------------------------------------------------------------------------------------------- UniqueProducts

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_PRODUCTS = "UniqueProducts";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_PRODUCTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_PRODUCTS = "uniqueProducts";

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
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_REVENUE = "TotalRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_REVENUE = 6;

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

    // -------------------------------------------------------------------------------------------------------- AvgUnits

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_UNITS = "AvgUnits";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_UNITS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_UNITS = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_UNITS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_UNITS = "avgUnits";

    // -------------------------------------------------------------------------------------------------- PopularityRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POPULARITY_RANK = "PopularityRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POPULARITY_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POPULARITY_RANK = "popularityRank";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesProductMixAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "channelName=" + channelName
               + ",customerType=" + customerType
               + ",productMix=" + productMix
               + ",uniqueProducts=" + uniqueProducts
               + ",orderCount=" + orderCount
               + ",totalRevenue=" + totalRevenue
               + ",avgOrderValue=" + avgOrderValue
               + ",avgUnits=" + avgUnits
               + ",popularityRank=" + popularityRank
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

    // ------------------------------------------------------------------------------------------------------ productMix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MIX} attribute.
     */
    public String getProductMix() {
        return productMix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MIX} attribute with the specified value.
     *
     * @param productMix new value for {@value #ATTRIBUTE_NAME_PRODUCT_MIX} attribute.
     */
    protected void setProductMix(final String productMix) {
        this.productMix = productMix;
    }

    // -------------------------------------------------------------------------------------------------- uniqueProducts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIQUE_PRODUCTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIQUE_PRODUCTS} attribute.
     */
    public Integer getUniqueProducts() {
        return uniqueProducts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIQUE_PRODUCTS} attribute with the specified value.
     *
     * @param uniqueProducts new value for {@value #ATTRIBUTE_NAME_UNIQUE_PRODUCTS} attribute.
     */
    protected void setUniqueProducts(final Integer uniqueProducts) {
        this.uniqueProducts = uniqueProducts;
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

    // -------------------------------------------------------------------------------------------------------- avgUnits

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_UNITS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_UNITS} attribute.
     */
    public BigDecimal getAvgUnits() {
        return avgUnits;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_UNITS} attribute with the specified value.
     *
     * @param avgUnits new value for {@value #ATTRIBUTE_NAME_AVG_UNITS} attribute.
     */
    protected void setAvgUnits(final BigDecimal avgUnits) {
        this.avgUnits = avgUnits;
    }

    // -------------------------------------------------------------------------------------------------- popularityRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POPULARITY_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POPULARITY_RANK} attribute.
     */
    public Long getPopularityRank() {
        return popularityRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POPULARITY_RANK} attribute with the specified value.
     *
     * @param popularityRank new value for {@value #ATTRIBUTE_NAME_POPULARITY_RANK} attribute.
     */
    protected void setPopularityRank(final Long popularityRank) {
        this.popularityRank = popularityRank;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_TYPE
    )
    private String customerType;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRODUCT_MIX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_MIX,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRODUCT_MIX
    )
    private String productMix;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIQUE_PRODUCTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer uniqueProducts;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer orderCount;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TOTAL_REVENUE - COLUMN_SCALE_TOTAL_REVENUE,
            fraction = COLUMN_SCALE_TOTAL_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_REVENUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TOTAL_REVENUE,
            scale = COLUMN_SCALE_TOTAL_REVENUE
    )
    private BigDecimal totalRevenue;

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
    @Digits(integer = COLUMN_PRECISION_AVG_UNITS - COLUMN_SCALE_AVG_UNITS, fraction = COLUMN_SCALE_AVG_UNITS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_UNITS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_UNITS,
            scale = COLUMN_SCALE_AVG_UNITS
    )
    private BigDecimal avgUnits;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_POPULARITY_RANK,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long popularityRank;
}

