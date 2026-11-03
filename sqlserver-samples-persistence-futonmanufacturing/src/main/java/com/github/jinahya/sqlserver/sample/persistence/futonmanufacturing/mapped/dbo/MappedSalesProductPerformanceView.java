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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ProductPerformance} view.
 * <p>
 * Identifier: the view declares no key; ItemCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesProductPerformanceView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ProductPerformance";

    // ------------------------------------------------------------------------------------ ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // ------------------------------------------------------------------------------------ ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // ---------------------------------------------------------------------------------- OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // ------------------------------------------------------------------------------ TotalUnitsSold

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_UNITS_SOLD = "TotalUnitsSold";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_UNITS_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_UNITS_SOLD = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_UNITS_SOLD} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_UNITS_SOLD = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_UNITS_SOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_UNITS_SOLD = "totalUnitsSold";

    // -------------------------------------------------------------------------------- TotalRevenue

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

    // ----------------------------------------------------------------------------- AvgSellingPrice

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_SELLING_PRICE = "AvgSellingPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_SELLING_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_SELLING_PRICE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_SELLING_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_SELLING_PRICE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_SELLING_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_SELLING_PRICE = "avgSellingPrice";

    // ------------------------------------------------------------------------------------ UnitCost

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_COST = "UnitCost";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_COST = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_COST = "unitCost";

    // ----------------------------------------------------------------------- AvgGrossProfitPerUnit

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_GROSS_PROFIT_PER_UNIT = "AvgGrossProfitPerUnit";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_GROSS_PROFIT_PER_UNIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_GROSS_PROFIT_PER_UNIT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_GROSS_PROFIT_PER_UNIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_GROSS_PROFIT_PER_UNIT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_GROSS_PROFIT_PER_UNIT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_GROSS_PROFIT_PER_UNIT = "avgGrossProfitPerUnit";

    // ---------------------------------------------------------------------------- TotalGrossProfit

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_GROSS_PROFIT = "TotalGrossProfit";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_GROSS_PROFIT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_GROSS_PROFIT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_GROSS_PROFIT = "totalGrossProfit";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT = "grossMarginPercent";

    // -------------------------------------------------------------------------- AvgDiscountPercent

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DISCOUNT_PERCENT = "AvgDiscountPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_DISCOUNT_PERCENT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_DISCOUNT_PERCENT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DISCOUNT_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DISCOUNT_PERCENT = "avgDiscountPercent";

    // --------------------------------------------------------------------------------- RetailUnits

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETAIL_UNITS = "RetailUnits";

    /**
     * The precision of the {@value #COLUMN_NAME_RETAIL_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RETAIL_UNITS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_RETAIL_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RETAIL_UNITS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETAIL_UNITS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETAIL_UNITS = "retailUnits";

    // --------------------------------------------------------------------------------- OnlineUnits

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ONLINE_UNITS = "OnlineUnits";

    /**
     * The precision of the {@value #COLUMN_NAME_ONLINE_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ONLINE_UNITS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_ONLINE_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ONLINE_UNITS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ONLINE_UNITS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ONLINE_UNITS = "onlineUnits";

    // ------------------------------------------------------------------------------ WholesaleUnits

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WHOLESALE_UNITS = "WholesaleUnits";

    /**
     * The precision of the {@value #COLUMN_NAME_WHOLESALE_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_WHOLESALE_UNITS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_WHOLESALE_UNITS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_WHOLESALE_UNITS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WHOLESALE_UNITS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WHOLESALE_UNITS = "wholesaleUnits";

    // ------------------------------------------------------------------------------- UnitSalesRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_SALES_RANK = "UnitSalesRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_SALES_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_SALES_RANK = "unitSalesRank";

    // --------------------------------------------------------------------------------- RevenueRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVENUE_RANK = "RevenueRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVENUE_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVENUE_RANK = "revenueRank";

    // ---------------------------------------------------------------------------------- ProfitRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROFIT_RANK = "ProfitRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROFIT_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROFIT_RANK = "profitRank";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesProductPerformanceView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",orderCount=" + orderCount
               + ",totalUnitsSold=" + totalUnitsSold
               + ",totalRevenue=" + totalRevenue
               + ",avgSellingPrice=" + avgSellingPrice
               + ",unitCost=" + unitCost
               + ",avgGrossProfitPerUnit=" + avgGrossProfitPerUnit
               + ",totalGrossProfit=" + totalGrossProfit
               + ",grossMarginPercent=" + grossMarginPercent
               + ",avgDiscountPercent=" + avgDiscountPercent
               + ",retailUnits=" + retailUnits
               + ",onlineUnits=" + onlineUnits
               + ",wholesaleUnits=" + wholesaleUnits
               + ",unitSalesRank=" + unitSalesRank
               + ",revenueRank=" + revenueRank
               + ",profitRank=" + profitRank
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
        if (!(obj instanceof MappedSalesProductPerformanceView that)) {
            return false;
        }
        return Objects.equals(itemCode, that.itemCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(itemCode);
    }

    // ------------------------------------------------------------------------------------ itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // ------------------------------------------------------------------------------------ itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(final String itemName) {
        this.itemName = itemName;
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

    // ------------------------------------------------------------------------------ totalUnitsSold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_UNITS_SOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_UNITS_SOLD} attribute.
     */
    public BigDecimal getTotalUnitsSold() {
        return totalUnitsSold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_UNITS_SOLD} attribute with the specified value.
     *
     * @param totalUnitsSold new value for {@value #ATTRIBUTE_NAME_TOTAL_UNITS_SOLD} attribute.
     */
    protected void setTotalUnitsSold(final BigDecimal totalUnitsSold) {
        this.totalUnitsSold = totalUnitsSold;
    }

    // -------------------------------------------------------------------------------- totalRevenue

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

    // ----------------------------------------------------------------------------- avgSellingPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_SELLING_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_SELLING_PRICE} attribute.
     */
    public BigDecimal getAvgSellingPrice() {
        return avgSellingPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_SELLING_PRICE} attribute with the specified value.
     *
     * @param avgSellingPrice new value for {@value #ATTRIBUTE_NAME_AVG_SELLING_PRICE} attribute.
     */
    protected void setAvgSellingPrice(final BigDecimal avgSellingPrice) {
        this.avgSellingPrice = avgSellingPrice;
    }

    // ------------------------------------------------------------------------------------ unitCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    public BigDecimal getUnitCost() {
        return unitCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_COST} attribute with the specified value.
     *
     * @param unitCost new value for {@value #ATTRIBUTE_NAME_UNIT_COST} attribute.
     */
    protected void setUnitCost(final BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    // ----------------------------------------------------------------------- avgGrossProfitPerUnit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_GROSS_PROFIT_PER_UNIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_GROSS_PROFIT_PER_UNIT} attribute.
     */
    public BigDecimal getAvgGrossProfitPerUnit() {
        return avgGrossProfitPerUnit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_GROSS_PROFIT_PER_UNIT} attribute with the specified value.
     *
     * @param avgGrossProfitPerUnit new value for {@value #ATTRIBUTE_NAME_AVG_GROSS_PROFIT_PER_UNIT} attribute.
     */
    protected void setAvgGrossProfitPerUnit(final BigDecimal avgGrossProfitPerUnit) {
        this.avgGrossProfitPerUnit = avgGrossProfitPerUnit;
    }

    // ---------------------------------------------------------------------------- totalGrossProfit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_GROSS_PROFIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_GROSS_PROFIT} attribute.
     */
    public BigDecimal getTotalGrossProfit() {
        return totalGrossProfit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_GROSS_PROFIT} attribute with the specified value.
     *
     * @param totalGrossProfit new value for {@value #ATTRIBUTE_NAME_TOTAL_GROSS_PROFIT} attribute.
     */
    protected void setTotalGrossProfit(final BigDecimal totalGrossProfit) {
        this.totalGrossProfit = totalGrossProfit;
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

    // -------------------------------------------------------------------------- avgDiscountPercent

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

    // --------------------------------------------------------------------------------- retailUnits

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETAIL_UNITS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETAIL_UNITS} attribute.
     */
    public BigDecimal getRetailUnits() {
        return retailUnits;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETAIL_UNITS} attribute with the specified value.
     *
     * @param retailUnits new value for {@value #ATTRIBUTE_NAME_RETAIL_UNITS} attribute.
     */
    protected void setRetailUnits(final BigDecimal retailUnits) {
        this.retailUnits = retailUnits;
    }

    // --------------------------------------------------------------------------------- onlineUnits

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ONLINE_UNITS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ONLINE_UNITS} attribute.
     */
    public BigDecimal getOnlineUnits() {
        return onlineUnits;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ONLINE_UNITS} attribute with the specified value.
     *
     * @param onlineUnits new value for {@value #ATTRIBUTE_NAME_ONLINE_UNITS} attribute.
     */
    protected void setOnlineUnits(final BigDecimal onlineUnits) {
        this.onlineUnits = onlineUnits;
    }

    // ------------------------------------------------------------------------------ wholesaleUnits

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WHOLESALE_UNITS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WHOLESALE_UNITS} attribute.
     */
    public BigDecimal getWholesaleUnits() {
        return wholesaleUnits;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WHOLESALE_UNITS} attribute with the specified value.
     *
     * @param wholesaleUnits new value for {@value #ATTRIBUTE_NAME_WHOLESALE_UNITS} attribute.
     */
    protected void setWholesaleUnits(final BigDecimal wholesaleUnits) {
        this.wholesaleUnits = wholesaleUnits;
    }

    // ------------------------------------------------------------------------------- unitSalesRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_SALES_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_SALES_RANK} attribute.
     */
    public Long getUnitSalesRank() {
        return unitSalesRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_SALES_RANK} attribute with the specified value.
     *
     * @param unitSalesRank new value for {@value #ATTRIBUTE_NAME_UNIT_SALES_RANK} attribute.
     */
    protected void setUnitSalesRank(final Long unitSalesRank) {
        this.unitSalesRank = unitSalesRank;
    }

    // --------------------------------------------------------------------------------- revenueRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute.
     */
    public Long getRevenueRank() {
        return revenueRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute with the specified value.
     *
     * @param revenueRank new value for {@value #ATTRIBUTE_NAME_REVENUE_RANK} attribute.
     */
    protected void setRevenueRank(final Long revenueRank) {
        this.revenueRank = revenueRank;
    }

    // ---------------------------------------------------------------------------------- profitRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute.
     */
    public Long getProfitRank() {
        return profitRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute with the specified value.
     *
     * @param profitRank new value for {@value #ATTRIBUTE_NAME_PROFIT_RANK} attribute.
     */
    protected void setProfitRank(final Long profitRank) {
        this.profitRank = profitRank;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer orderCount;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_UNITS_SOLD - COLUMN_SCALE_TOTAL_UNITS_SOLD,
            fraction = COLUMN_SCALE_TOTAL_UNITS_SOLD)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_UNITS_SOLD,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_UNITS_SOLD,
            scale = COLUMN_SCALE_TOTAL_UNITS_SOLD
    )
    private BigDecimal totalUnitsSold;

    // ---------------------------------------------------------------------------------------------
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

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_SELLING_PRICE - COLUMN_SCALE_AVG_SELLING_PRICE,
            fraction = COLUMN_SCALE_AVG_SELLING_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_SELLING_PRICE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_SELLING_PRICE,
            scale = COLUMN_SCALE_AVG_SELLING_PRICE
    )
    private BigDecimal avgSellingPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_UNIT_COST - COLUMN_SCALE_UNIT_COST, fraction = COLUMN_SCALE_UNIT_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_COST,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_UNIT_COST,
            scale = COLUMN_SCALE_UNIT_COST
    )
    private BigDecimal unitCost;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_GROSS_PROFIT_PER_UNIT - COLUMN_SCALE_AVG_GROSS_PROFIT_PER_UNIT,
            fraction = COLUMN_SCALE_AVG_GROSS_PROFIT_PER_UNIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_GROSS_PROFIT_PER_UNIT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_GROSS_PROFIT_PER_UNIT,
            scale = COLUMN_SCALE_AVG_GROSS_PROFIT_PER_UNIT
    )
    private BigDecimal avgGrossProfitPerUnit;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_GROSS_PROFIT - COLUMN_SCALE_TOTAL_GROSS_PROFIT,
            fraction = COLUMN_SCALE_TOTAL_GROSS_PROFIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_GROSS_PROFIT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_GROSS_PROFIT,
            scale = COLUMN_SCALE_TOTAL_GROSS_PROFIT
    )
    private BigDecimal totalGrossProfit;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_GROSS_MARGIN_PERCENT - COLUMN_SCALE_GROSS_MARGIN_PERCENT,
            fraction = COLUMN_SCALE_GROSS_MARGIN_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_MARGIN_PERCENT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_GROSS_MARGIN_PERCENT,
            scale = COLUMN_SCALE_GROSS_MARGIN_PERCENT
    )
    private BigDecimal grossMarginPercent;

    // ---------------------------------------------------------------------------------------------
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

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_RETAIL_UNITS - COLUMN_SCALE_RETAIL_UNITS, fraction = COLUMN_SCALE_RETAIL_UNITS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RETAIL_UNITS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_RETAIL_UNITS,
            scale = COLUMN_SCALE_RETAIL_UNITS
    )
    private BigDecimal retailUnits;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_ONLINE_UNITS - COLUMN_SCALE_ONLINE_UNITS, fraction = COLUMN_SCALE_ONLINE_UNITS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ONLINE_UNITS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_ONLINE_UNITS,
            scale = COLUMN_SCALE_ONLINE_UNITS
    )
    private BigDecimal onlineUnits;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_WHOLESALE_UNITS - COLUMN_SCALE_WHOLESALE_UNITS,
            fraction = COLUMN_SCALE_WHOLESALE_UNITS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WHOLESALE_UNITS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_WHOLESALE_UNITS,
            scale = COLUMN_SCALE_WHOLESALE_UNITS
    )
    private BigDecimal wholesaleUnits;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_SALES_RANK,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Long unitSalesRank;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REVENUE_RANK,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Long revenueRank;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROFIT_RANK,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Long profitRank;
}

