package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ChannelProfitability} view.
 * <p>
 * Identifier: the view declares no key; ChannelCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesChannelProfitabilityView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ChannelProfitability";

    // --------------------------------------------------------------------------------- ChannelCode

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

    // --------------------------------------------------------------------------------- ChannelName

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

    // ---------------------------------------------------------------------------------- OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_ORDER_COUNT = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_ORDER_COUNT = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // ---------------------------------------------------------------------------------- TotalUnits

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_UNITS = "TotalUnits";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_UNITS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_UNITS = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_UNITS} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_UNITS = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_UNITS} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_UNITS = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_UNITS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_UNITS = "totalUnits";

    // ---------------------------------------------------------------------------------- GrossSales

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_GROSS_SALES = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_GROSS_SALES = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_SALES = "grossSales";

    // ----------------------------------------------------------------------------------- Discounts

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DISCOUNTS = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DISCOUNTS = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNTS = "discounts";

    // ------------------------------------------------------------------------------------ NetSales

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_NET_SALES} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_NET_SALES = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_NET_SALES} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_NET_SALES = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_SALES = "netSales";

    // ----------------------------------------------------------------------------- ShippingRevenue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIPPING_REVENUE = "ShippingRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_SHIPPING_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SHIPPING_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SHIPPING_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SHIPPING_REVENUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SHIPPING_REVENUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SHIPPING_REVENUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SHIPPING_REVENUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SHIPPING_REVENUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPPING_REVENUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPPING_REVENUE = "shippingRevenue";

    // ---------------------------------------------------------------------------------------- COGS

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COGS = "COGS";

    /**
     * The precision of the {@value #COLUMN_NAME_COGS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_COGS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_COGS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_COGS = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_COGS} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_COGS = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_COGS} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_COGS = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COGS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COGS = "cogs";

    // --------------------------------------------------------------------------------- GrossProfit

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROSS_PROFIT = "GrossProfit";

    /**
     * The precision of the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROSS_PROFIT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROSS_PROFIT = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_GROSS_PROFIT = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_GROSS_PROFIT = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_PROFIT = "grossProfit";

    // ------------------------------------------------------------------------------- AvgOrderValue

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AVG_ORDER_VALUE = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AVG_ORDER_VALUE = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ORDER_VALUE = "avgOrderValue";

    // -------------------------------------------------------------------------- GrossMarginPercent

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_GROSS_MARGIN_PERCENT = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_GROSS_MARGIN_PERCENT = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT = "grossMarginPercent";

    // ----------------------------------------------------------------------------- DiscountPercent

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DISCOUNT_PERCENT = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DISCOUNT_PERCENT = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PERCENT = "discountPercent";

    // ------------------------------------------------------------------------------ ProfitPerOrder

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROFIT_PER_ORDER = "ProfitPerOrder";

    /**
     * The precision of the {@value #COLUMN_NAME_PROFIT_PER_ORDER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROFIT_PER_ORDER = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PROFIT_PER_ORDER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROFIT_PER_ORDER = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PROFIT_PER_ORDER} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PROFIT_PER_ORDER = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PROFIT_PER_ORDER} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PROFIT_PER_ORDER = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROFIT_PER_ORDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROFIT_PER_ORDER = "profitPerOrder";

    // ------------------------------------------------------------------------------- ProfitPerUnit

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROFIT_PER_UNIT = "ProfitPerUnit";

    /**
     * The precision of the {@value #COLUMN_NAME_PROFIT_PER_UNIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROFIT_PER_UNIT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PROFIT_PER_UNIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROFIT_PER_UNIT = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PROFIT_PER_UNIT} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PROFIT_PER_UNIT = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PROFIT_PER_UNIT} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PROFIT_PER_UNIT = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROFIT_PER_UNIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROFIT_PER_UNIT = "profitPerUnit";

    // --------------------------------------------------------------------------- ProfitabilityRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROFITABILITY_RANK = "ProfitabilityRank";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PROFITABILITY_RANK} attribute, the minimum of the
     * {@code bigint} type. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_PROFITABILITY_RANK = SqlServerConstants.BIGINT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PROFITABILITY_RANK} attribute, the maximum of the
     * {@code bigint} type. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_PROFITABILITY_RANK = SqlServerConstants.BIGINT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROFITABILITY_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROFITABILITY_RANK = "profitabilityRank";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesChannelProfitabilityView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "channelCode=" + channelCode
               + ",channelName=" + channelName
               + ",orderCount=" + orderCount
               + ",totalUnits=" + totalUnits
               + ",grossSales=" + grossSales
               + ",discounts=" + discounts
               + ",netSales=" + netSales
               + ",shippingRevenue=" + shippingRevenue
               + ",cogs=" + cogs
               + ",grossProfit=" + grossProfit
               + ",avgOrderValue=" + avgOrderValue
               + ",grossMarginPercent=" + grossMarginPercent
               + ",discountPercent=" + discountPercent
               + ",profitPerOrder=" + profitPerOrder
               + ",profitPerUnit=" + profitPerUnit
               + ",profitabilityRank=" + profitabilityRank
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSalesChannelProfitabilityView that)) {
            return false;
        }
        return Objects.equals(channelCode, that.channelCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(channelCode);
    }

    // --------------------------------------------------------------------------------- channelCode

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

    // --------------------------------------------------------------------------------- channelName

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

    // ---------------------------------------------------------------------------------- orderCount

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

    // ---------------------------------------------------------------------------------- totalUnits

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_UNITS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_UNITS} attribute.
     */
    public BigDecimal getTotalUnits() {
        return totalUnits;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_UNITS} attribute with the specified value.
     *
     * @param totalUnits new value for {@value #ATTRIBUTE_NAME_TOTAL_UNITS} attribute.
     */
    protected void setTotalUnits(final BigDecimal totalUnits) {
        this.totalUnits = totalUnits;
    }

    // ---------------------------------------------------------------------------------- grossSales

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

    // ----------------------------------------------------------------------------------- discounts

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

    // ------------------------------------------------------------------------------------ netSales

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

    // ----------------------------------------------------------------------------- shippingRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPPING_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPPING_REVENUE} attribute.
     */
    public BigDecimal getShippingRevenue() {
        return shippingRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPPING_REVENUE} attribute with the specified value.
     *
     * @param shippingRevenue new value for {@value #ATTRIBUTE_NAME_SHIPPING_REVENUE} attribute.
     */
    protected void setShippingRevenue(final BigDecimal shippingRevenue) {
        this.shippingRevenue = shippingRevenue;
    }

    // ---------------------------------------------------------------------------------------- cogs

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COGS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COGS} attribute.
     */
    public BigDecimal getCogs() {
        return cogs;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COGS} attribute with the specified value.
     *
     * @param cogs new value for {@value #ATTRIBUTE_NAME_COGS} attribute.
     */
    protected void setCogs(final BigDecimal cogs) {
        this.cogs = cogs;
    }

    // --------------------------------------------------------------------------------- grossProfit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     */
    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute with the specified value.
     *
     * @param grossProfit new value for {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     */
    protected void setGrossProfit(final BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    // ------------------------------------------------------------------------------- avgOrderValue

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

    // -------------------------------------------------------------------------- grossMarginPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     */
    public BigDecimal getGrossMarginPercent() {
        return grossMarginPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute with the specified value.
     *
     * @param grossMarginPercent new value for {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     */
    protected void setGrossMarginPercent(final BigDecimal grossMarginPercent) {
        this.grossMarginPercent = grossMarginPercent;
    }

    // ----------------------------------------------------------------------------- discountPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute with the specified value.
     *
     * @param discountPercent new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    protected void setDiscountPercent(final BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    // ------------------------------------------------------------------------------ profitPerOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROFIT_PER_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROFIT_PER_ORDER} attribute.
     */
    public BigDecimal getProfitPerOrder() {
        return profitPerOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROFIT_PER_ORDER} attribute with the specified value.
     *
     * @param profitPerOrder new value for {@value #ATTRIBUTE_NAME_PROFIT_PER_ORDER} attribute.
     */
    protected void setProfitPerOrder(final BigDecimal profitPerOrder) {
        this.profitPerOrder = profitPerOrder;
    }

    // ------------------------------------------------------------------------------- profitPerUnit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROFIT_PER_UNIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROFIT_PER_UNIT} attribute.
     */
    public BigDecimal getProfitPerUnit() {
        return profitPerUnit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROFIT_PER_UNIT} attribute with the specified value.
     *
     * @param profitPerUnit new value for {@value #ATTRIBUTE_NAME_PROFIT_PER_UNIT} attribute.
     */
    protected void setProfitPerUnit(final BigDecimal profitPerUnit) {
        this.profitPerUnit = profitPerUnit;
    }

    // --------------------------------------------------------------------------- profitabilityRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROFITABILITY_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROFITABILITY_RANK} attribute.
     */
    public Long getProfitabilityRank() {
        return profitabilityRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROFITABILITY_RANK} attribute with the specified value.
     *
     * @param profitabilityRank new value for {@value #ATTRIBUTE_NAME_PROFITABILITY_RANK} attribute.
     */
    protected void setProfitabilityRank(final Long profitabilityRank) {
        this.profitabilityRank = profitabilityRank;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CHANNEL_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CHANNEL_CODE)
    private String channelCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CHANNEL_NAME)
    private String channelName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_ORDER_COUNT)
    @Min(ATTRIBUTE_MIN_ORDER_COUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT, nullable = true, insertable = false, updatable = false)
    private Integer orderCount;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_UNITS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_UNITS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_UNITS, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_UNITS, scale = COLUMN_SCALE_TOTAL_UNITS)
    private BigDecimal totalUnits;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_GROSS_SALES)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_GROSS_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_SALES, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_GROSS_SALES, scale = COLUMN_SCALE_GROSS_SALES)
    private BigDecimal grossSales;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DISCOUNTS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DISCOUNTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNTS, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_DISCOUNTS, scale = COLUMN_SCALE_DISCOUNTS)
    private BigDecimal discounts;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_NET_SALES)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_NET_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_SALES, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_NET_SALES, scale = COLUMN_SCALE_NET_SALES)
    private BigDecimal netSales;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SHIPPING_REVENUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SHIPPING_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIPPING_REVENUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SHIPPING_REVENUE, scale = COLUMN_SCALE_SHIPPING_REVENUE)
    private BigDecimal shippingRevenue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_COGS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_COGS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COGS, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_COGS, scale = COLUMN_SCALE_COGS)
    private BigDecimal cogs;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_GROSS_PROFIT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_GROSS_PROFIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_PROFIT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_GROSS_PROFIT, scale = COLUMN_SCALE_GROSS_PROFIT)
    private BigDecimal grossProfit;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_ORDER_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_ORDER_VALUE, scale = COLUMN_SCALE_AVG_ORDER_VALUE)
    private BigDecimal avgOrderValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_GROSS_MARGIN_PERCENT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_GROSS_MARGIN_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_MARGIN_PERCENT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_GROSS_MARGIN_PERCENT, scale = COLUMN_SCALE_GROSS_MARGIN_PERCENT)
    private BigDecimal grossMarginPercent;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DISCOUNT_PERCENT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DISCOUNT_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_DISCOUNT_PERCENT, scale = COLUMN_SCALE_DISCOUNT_PERCENT)
    private BigDecimal discountPercent;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PROFIT_PER_ORDER)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PROFIT_PER_ORDER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROFIT_PER_ORDER, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PROFIT_PER_ORDER, scale = COLUMN_SCALE_PROFIT_PER_ORDER)
    private BigDecimal profitPerOrder;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PROFIT_PER_UNIT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PROFIT_PER_UNIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROFIT_PER_UNIT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PROFIT_PER_UNIT, scale = COLUMN_SCALE_PROFIT_PER_UNIT)
    private BigDecimal profitPerUnit;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_PROFITABILITY_RANK)
    @Min(ATTRIBUTE_MIN_PROFITABILITY_RANK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROFITABILITY_RANK, nullable = true, insertable = false, updatable = false)
    private Long profitabilityRank;
}

