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
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_StorePerformance} view.
 * <p>
 * Identifier: the view declares no key; StoreCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); it contains
 * aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesStorePerformanceView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_StorePerformance";

    // ----------------------------------------------------------------------------------- StoreCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_CODE = "StoreCode";

    /**
     * The length of the {@value #COLUMN_NAME_STORE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STORE_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_CODE = "storeCode";

    // ----------------------------------------------------------------------------------- StoreName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_NAME = "StoreName";

    /**
     * The length of the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STORE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_NAME = "storeName";

    // ---------------------------------------------------------------------------------------- City

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "City";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // --------------------------------------------------------------------------------------- State

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE = "State";

    /**
     * The length of the {@value #COLUMN_NAME_STATE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE = "state";

    // --------------------------------------------------------------------------------- ChannelName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
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

    // ------------------------------------------------------------------------------------- Manager

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MANAGER = "Manager";

    /**
     * The length of the {@value #COLUMN_NAME_MANAGER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MANAGER = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    // --------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

    // ----------------------------------------------------------------------------- UniqueCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_CUSTOMERS = "UniqueCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_CUSTOMERS = "uniqueCustomers";

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

    // ------------------------------------------------------------------------------ TotalDiscounts

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_DISCOUNTS = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_DISCOUNTS} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_DISCOUNTS = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DISCOUNTS = "totalDiscounts";

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

    // ----------------------------------------------------------------------------- CompletedOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETED_ORDERS = "CompletedOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETED_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETED_ORDERS = "completedOrders";

    // ------------------------------------------------------------------------------ CompletionRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETION_RATE = "CompletionRate";

    /**
     * The precision of the {@value #COLUMN_NAME_COMPLETION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_COMPLETION_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_COMPLETION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_COMPLETION_RATE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_COMPLETION_RATE} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_COMPLETION_RATE = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_COMPLETION_RATE} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_COMPLETION_RATE = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETION_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETION_RATE = "completionRate";

    // ----------------------------------------------------------------------------------- FirstSale

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_SALE = "FirstSale";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_SALE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_SALE = "firstSale";

    // ------------------------------------------------------------------------------------ LastSale

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_SALE = "LastSale";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_SALE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_SALE = "lastSale";

    // ---------------------------------------------------------------------------------- DaysActive

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_ACTIVE = "DaysActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_ACTIVE = "daysActive";

    // ------------------------------------------------------------------------------- AvgDailySales

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DAILY_SALES = "AvgDailySales";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_DAILY_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_DAILY_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_DAILY_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_DAILY_SALES = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_DAILY_SALES} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AVG_DAILY_SALES = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_DAILY_SALES} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AVG_DAILY_SALES = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DAILY_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DAILY_SALES = "avgDailySales";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesStorePerformanceView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "storeCode=" + storeCode
               + ",storeName=" + storeName
               + ",city=" + city
               + ",state=" + state
               + ",channelName=" + channelName
               + ",manager=" + manager
               + ",totalOrders=" + totalOrders
               + ",uniqueCustomers=" + uniqueCustomers
               + ",grossSales=" + grossSales
               + ",totalDiscounts=" + totalDiscounts
               + ",netSales=" + netSales
               + ",avgOrderValue=" + avgOrderValue
               + ",completedOrders=" + completedOrders
               + ",completionRate=" + completionRate
               + ",firstSale=" + firstSale
               + ",lastSale=" + lastSale
               + ",daysActive=" + daysActive
               + ",avgDailySales=" + avgDailySales
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
        if (!(obj instanceof MappedSalesStorePerformanceView that)) {
            return false;
        }
        return Objects.equals(getStoreCode(), that.getStoreCode());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getStoreCode());
    }

    // ----------------------------------------------------------------------------------- storeCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_CODE} attribute.
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_CODE} attribute with the specified value.
     *
     * @param storeCode new value for {@value #ATTRIBUTE_NAME_STORE_CODE} attribute.
     */
    protected void setStoreCode(final String storeCode) {
        this.storeCode = storeCode;
    }

    // ----------------------------------------------------------------------------------- storeName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute with the specified value.
     *
     * @param storeName new value for {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     */
    protected void setStoreName(final String storeName) {
        this.storeName = storeName;
    }

    // ---------------------------------------------------------------------------------------- city

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    public String getCity() {
        return city;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY} attribute with the specified value.
     *
     * @param city new value for {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    protected void setCity(final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------- state

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE} attribute.
     */
    public String getState() {
        return state;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE} attribute with the specified value.
     *
     * @param state new value for {@value #ATTRIBUTE_NAME_STATE} attribute.
     */
    protected void setState(final String state) {
        this.state = state;
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

    // ------------------------------------------------------------------------------------- manager

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public String getManager() {
        return manager;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute with the specified value.
     *
     * @param manager new value for {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    protected void setManager(final String manager) {
        this.manager = manager;
    }

    // --------------------------------------------------------------------------------- totalOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    public Integer getTotalOrders() {
        return totalOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute with the specified value.
     *
     * @param totalOrders new value for {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    protected void setTotalOrders(final Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    // ----------------------------------------------------------------------------- uniqueCustomers

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

    // ------------------------------------------------------------------------------ totalDiscounts

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

    // ----------------------------------------------------------------------------- completedOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETED_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETED_ORDERS} attribute.
     */
    public Integer getCompletedOrders() {
        return completedOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETED_ORDERS} attribute with the specified value.
     *
     * @param completedOrders new value for {@value #ATTRIBUTE_NAME_COMPLETED_ORDERS} attribute.
     */
    protected void setCompletedOrders(final Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    // ------------------------------------------------------------------------------ completionRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETION_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETION_RATE} attribute.
     */
    public BigDecimal getCompletionRate() {
        return completionRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETION_RATE} attribute with the specified value.
     *
     * @param completionRate new value for {@value #ATTRIBUTE_NAME_COMPLETION_RATE} attribute.
     */
    protected void setCompletionRate(final BigDecimal completionRate) {
        this.completionRate = completionRate;
    }

    // ----------------------------------------------------------------------------------- firstSale

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_SALE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_SALE} attribute.
     */
    public LocalDate getFirstSale() {
        return firstSale;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_SALE} attribute with the specified value.
     *
     * @param firstSale new value for {@value #ATTRIBUTE_NAME_FIRST_SALE} attribute.
     */
    protected void setFirstSale(final LocalDate firstSale) {
        this.firstSale = firstSale;
    }

    // ------------------------------------------------------------------------------------ lastSale

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_SALE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_SALE} attribute.
     */
    public LocalDate getLastSale() {
        return lastSale;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_SALE} attribute with the specified value.
     *
     * @param lastSale new value for {@value #ATTRIBUTE_NAME_LAST_SALE} attribute.
     */
    protected void setLastSale(final LocalDate lastSale) {
        this.lastSale = lastSale;
    }

    // ---------------------------------------------------------------------------------- daysActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_ACTIVE} attribute.
     */
    public Integer getDaysActive() {
        return daysActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_ACTIVE} attribute with the specified value.
     *
     * @param daysActive new value for {@value #ATTRIBUTE_NAME_DAYS_ACTIVE} attribute.
     */
    protected void setDaysActive(final Integer daysActive) {
        this.daysActive = daysActive;
    }

    // ------------------------------------------------------------------------------- avgDailySales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DAILY_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DAILY_SALES} attribute.
     */
    public BigDecimal getAvgDailySales() {
        return avgDailySales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DAILY_SALES} attribute with the specified value.
     *
     * @param avgDailySales new value for {@value #ATTRIBUTE_NAME_AVG_DAILY_SALES} attribute.
     */
    protected void setAvgDailySales(final BigDecimal avgDailySales) {
        this.avgDailySales = avgDailySales;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STORE_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_STORE_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_STORE_CODE)
    private String storeCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STORE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_STORE_NAME)
    private String storeName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CITY, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_CITY)
    private String city;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STATE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_STATE)
    private String state;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CHANNEL_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_CHANNEL_NAME)
    private String channelName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_MANAGER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_MANAGER)
    private String manager;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer totalOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS, nullable = true, insertable = false, updatable = false)
    private Integer uniqueCustomers;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_GROSS_SALES)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_GROSS_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_SALES, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_GROSS_SALES, scale = COLUMN_SCALE_GROSS_SALES)
    private BigDecimal grossSales;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_DISCOUNTS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_DISCOUNTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_DISCOUNTS, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_DISCOUNTS, scale = COLUMN_SCALE_TOTAL_DISCOUNTS)
    private BigDecimal totalDiscounts;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_NET_SALES)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_NET_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_SALES, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_NET_SALES, scale = COLUMN_SCALE_NET_SALES)
    private BigDecimal netSales;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_ORDER_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_ORDER_VALUE, scale = COLUMN_SCALE_AVG_ORDER_VALUE)
    private BigDecimal avgOrderValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETED_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer completedOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_COMPLETION_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_COMPLETION_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETION_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_COMPLETION_RATE, scale = COLUMN_SCALE_COMPLETION_RATE)
    private BigDecimal completionRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FIRST_SALE, nullable = true, insertable = false, updatable = false)
    private LocalDate firstSale;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_SALE, nullable = true, insertable = false, updatable = false)
    private LocalDate lastSale;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_ACTIVE, nullable = true, insertable = false, updatable = false)
    private Integer daysActive;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_DAILY_SALES)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_DAILY_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DAILY_SALES, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_DAILY_SALES, scale = COLUMN_SCALE_AVG_DAILY_SALES)
    private BigDecimal avgDailySales;
}

