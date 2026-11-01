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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_TopProducts} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedSalesTopProductsView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_TopProducts";

    // -------------------------------------------------------------------------------------------------------- ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // -------------------------------------------------------------------------------------------------------- ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

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

    // ------------------------------------------------------------------------------------------------------- UnitsSold

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNITS_SOLD = "UnitsSold";

    /**
     * The precision of the {@value #COLUMN_NAME_UNITS_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNITS_SOLD = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_UNITS_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNITS_SOLD = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNITS_SOLD} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNITS_SOLD = "unitsSold";

    // --------------------------------------------------------------------------------------------------------- Revenue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVENUE = "Revenue";

    /**
     * The precision of the {@value #COLUMN_NAME_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REVENUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVENUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVENUE = "revenue";

    // ----------------------------------------------------------------------------------------------------- GrossProfit

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_PROFIT = "grossProfit";

    // ------------------------------------------------------------------------------------------------- UniqueCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_CUSTOMERS = "UniqueCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_CUSTOMERS = "uniqueCustomers";

    // -------------------------------------------------------------------------------------------------------- UnitRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_RANK = "UnitRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_RANK} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_RANK = "unitRank";

    // ----------------------------------------------------------------------------------------------------- RevenueRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVENUE_RANK = "RevenueRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVENUE_RANK} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVENUE_RANK = "revenueRank";

    // ------------------------------------------------------------------------------------------------------ ProfitRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROFIT_RANK = "ProfitRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROFIT_RANK} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROFIT_RANK = "profitRank";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesTopProductsView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",channelName=" + channelName
               + ",year=" + year
               + ",month=" + month
               + ",unitsSold=" + unitsSold
               + ",revenue=" + revenue
               + ",grossProfit=" + grossProfit
               + ",uniqueCustomers=" + uniqueCustomers
               + ",unitRank=" + unitRank
               + ",revenueRank=" + revenueRank
               + ",profitRank=" + profitRank
               + "}";
    }

    // -------------------------------------------------------------------------------------------------------- itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    @Nonnull
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(@Nonnull final String itemCode) {
        this.itemCode = itemCode;
    }

    // -------------------------------------------------------------------------------------------------------- itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    @Nonnull
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(@Nonnull final String itemName) {
        this.itemName = itemName;
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

    // ------------------------------------------------------------------------------------------------------- unitsSold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNITS_SOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNITS_SOLD} attribute.
     */
    @Nullable
    public BigDecimal getUnitsSold() {
        return unitsSold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNITS_SOLD} attribute with the specified value.
     *
     * @param unitsSold new value for {@value #ATTRIBUTE_NAME_UNITS_SOLD} attribute.
     */
    protected void setUnitsSold(@Nullable final BigDecimal unitsSold) {
        this.unitsSold = unitsSold;
    }

    // --------------------------------------------------------------------------------------------------------- revenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVENUE} attribute.
     */
    @Nullable
    public BigDecimal getRevenue() {
        return revenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVENUE} attribute with the specified value.
     *
     * @param revenue new value for {@value #ATTRIBUTE_NAME_REVENUE} attribute.
     */
    protected void setRevenue(@Nullable final BigDecimal revenue) {
        this.revenue = revenue;
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

    // ------------------------------------------------------------------------------------------------- uniqueCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    @Nullable
    public Integer getUniqueCustomers() {
        return uniqueCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute with the specified value.
     *
     * @param uniqueCustomers new value for {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    protected void setUniqueCustomers(@Nullable final Integer uniqueCustomers) {
        this.uniqueCustomers = uniqueCustomers;
    }

    // -------------------------------------------------------------------------------------------------------- unitRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_RANK} attribute.
     */
    @Nullable
    public Long getUnitRank() {
        return unitRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_RANK} attribute with the specified value.
     *
     * @param unitRank new value for {@value #ATTRIBUTE_NAME_UNIT_RANK} attribute.
     */
    protected void setUnitRank(@Nullable final Long unitRank) {
        this.unitRank = unitRank;
    }

    // ----------------------------------------------------------------------------------------------------- revenueRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute.
     */
    @Nullable
    public Long getRevenueRank() {
        return revenueRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute with the specified value.
     *
     * @param revenueRank new value for {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute.
     */
    protected void setRevenueRank(@Nullable final Long revenueRank) {
        this.revenueRank = revenueRank;
    }

    // ------------------------------------------------------------------------------------------------------ profitRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute.
     */
    @Nullable
    public Long getProfitRank() {
        return profitRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute with the specified value.
     *
     * @param profitRank new value for {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute.
     */
    protected void setProfitRank(@Nullable final Long profitRank) {
        this.profitRank = profitRank;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

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
    @Nullable
    @Digits(integer = COLUMN_PRECISION_UNITS_SOLD - COLUMN_SCALE_UNITS_SOLD, fraction = COLUMN_SCALE_UNITS_SOLD)
    @Column(name = COLUMN_NAME_UNITS_SOLD,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNITS_SOLD,
            scale = COLUMN_SCALE_UNITS_SOLD
    )
    private BigDecimal unitsSold;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_REVENUE - COLUMN_SCALE_REVENUE, fraction = COLUMN_SCALE_REVENUE)
    @Column(name = COLUMN_NAME_REVENUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_REVENUE,
            scale = COLUMN_SCALE_REVENUE
    )
    private BigDecimal revenue;

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
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer uniqueCustomers;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_UNIT_RANK,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long unitRank;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_REVENUE_RANK,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long revenueRank;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_PROFIT_RANK,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long profitRank;
}

