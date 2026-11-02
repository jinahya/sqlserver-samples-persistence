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
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_CustomerAnalysis} view.
 * <p>
 * Identifier: the view declares no key; CustomerID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesCustomerAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_CustomerAnalysis";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ---------------------------------------------------------------------------------------------------- CustomerCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CODE = "CustomerCode";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CODE = "customerCode";

    // ---------------------------------------------------------------------------------------------------- CustomerName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_NAME = "CustomerName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

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

    // ------------------------------------------------------------------------------------------------------------ City

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

    // ----------------------------------------------------------------------------------------------------------- State

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

    // -------------------------------------------------------------------------------------------------------- SalesRep

    /**
     * The name of the {@code nvarchar(101)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REP = "SalesRep";

    /**
     * The length of the {@value #COLUMN_NAME_SALES_REP} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SALES_REP = 101;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REP} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP = "salesRep";

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

    // ----------------------------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

    // ------------------------------------------------------------------------------------------------------ TotalSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_SALES = "TotalSales";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_SALES = "totalSales";

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

    // --------------------------------------------------------------------------------------------------- LastOrderDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_ORDER_DATE = "LastOrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_ORDER_DATE = "lastOrderDate";

    // -------------------------------------------------------------------------------------------------- FirstOrderDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_ORDER_DATE = "FirstOrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_ORDER_DATE = "firstOrderDate";

    // -------------------------------------------------------------------------------------------- CustomerLifespanDays

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_LIFESPAN_DAYS = "CustomerLifespanDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_LIFESPAN_DAYS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_LIFESPAN_DAYS = "customerLifespanDays";

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

    // ---------------------------------------------------------------------------------------------------- PendingSales

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PENDING_SALES = "PendingSales";

    /**
     * The precision of the {@value #COLUMN_NAME_PENDING_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PENDING_SALES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PENDING_SALES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PENDING_SALES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PENDING_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PENDING_SALES = "pendingSales";

    // ---------------------------------------------------------------------------------------------------- TotalReturns

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_RETURNS = "TotalReturns";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_RETURNS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_RETURNS = "totalReturns";

    // ---------------------------------------------------------------------------------------------------- TotalRefunds

    /**
     * The name of the {@code decimal(38, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_REFUNDS = "TotalRefunds";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_REFUNDS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_REFUNDS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_REFUNDS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_REFUNDS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REFUNDS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_REFUNDS = "totalRefunds";

    // ------------------------------------------------------------------------------------------------- CustomerSegment

    /**
     * The name of the {@code varchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_SEGMENT = "CustomerSegment";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_SEGMENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_SEGMENT = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_SEGMENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_SEGMENT = "customerSegment";

    // ------------------------------------------------------------------------------------------------------ ReturnRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_RATE = "ReturnRate";

    /**
     * The precision of the {@value #COLUMN_NAME_RETURN_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RETURN_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_RETURN_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RETURN_RATE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_RATE = "returnRate";

    // ---------------------------------------------------------------------------------------------- DaysSinceLastOrder

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_SINCE_LAST_ORDER = "DaysSinceLastOrder";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_SINCE_LAST_ORDER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_SINCE_LAST_ORDER = "daysSinceLastOrder";

    // -------------------------------------------------------------------------------------------------- CustomerStatus

    /**
     * The name of the {@code varchar(8), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_STATUS = "CustomerStatus";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_STATUS = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_STATUS = "customerStatus";

    // ------------------------------------------------------------------------------------------------- AvgMonthlyValue

    /**
     * The name of the {@code numeric(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_MONTHLY_VALUE = "AvgMonthlyValue";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_MONTHLY_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_MONTHLY_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_MONTHLY_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_MONTHLY_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_MONTHLY_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_MONTHLY_VALUE = "avgMonthlyValue";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesCustomerAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerId=" + customerId
               + ",customerCode=" + customerCode
               + ",customerName=" + customerName
               + ",customerType=" + customerType
               + ",city=" + city
               + ",state=" + state
               + ",salesRep=" + salesRep
               + ",territoryName=" + territoryName
               + ",totalOrders=" + totalOrders
               + ",totalSales=" + totalSales
               + ",avgOrderValue=" + avgOrderValue
               + ",lastOrderDate=" + lastOrderDate
               + ",firstOrderDate=" + firstOrderDate
               + ",customerLifespanDays=" + customerLifespanDays
               + ",deliveredSales=" + deliveredSales
               + ",pendingSales=" + pendingSales
               + ",totalReturns=" + totalReturns
               + ",totalRefunds=" + totalRefunds
               + ",customerSegment=" + customerSegment
               + ",returnRate=" + returnRate
               + ",daysSinceLastOrder=" + daysSinceLastOrder
               + ",customerStatus=" + customerStatus
               + ",avgMonthlyValue=" + avgMonthlyValue
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
        if (!(obj instanceof MappedSalesCustomerAnalysisView that)) {
            return false;
        }
        return Objects.equals(customerId, that.customerId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(customerId);
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- customerCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute with the specified value.
     *
     * @param customerCode new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    protected void setCustomerCode(final String customerCode) {
        this.customerCode = customerCode;
    }

    // ---------------------------------------------------------------------------------------------------- customerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute with the specified value.
     *
     * @param customerName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    protected void setCustomerName(final String customerName) {
        this.customerName = customerName;
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

    // ------------------------------------------------------------------------------------------------------------ city

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

    // ----------------------------------------------------------------------------------------------------------- state

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

    // -------------------------------------------------------------------------------------------------------- salesRep

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REP} attribute.
     */
    public String getSalesRep() {
        return salesRep;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REP} attribute with the specified value.
     *
     * @param salesRep new value for {@value #ATTRIBUTE_NAME_SALES_REP} attribute.
     */
    protected void setSalesRep(final String salesRep) {
        this.salesRep = salesRep;
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

    // ----------------------------------------------------------------------------------------------------- totalOrders

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

    // ------------------------------------------------------------------------------------------------------ totalSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    public BigDecimal getTotalSales() {
        return totalSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute with the specified value.
     *
     * @param totalSales new value for {@value #ATTRIBUTE_NAME_TOTAL_SALES} attribute.
     */
    protected void setTotalSales(final BigDecimal totalSales) {
        this.totalSales = totalSales;
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

    // --------------------------------------------------------------------------------------------------- lastOrderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_ORDER_DATE} attribute.
     */
    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_ORDER_DATE} attribute with the specified value.
     *
     * @param lastOrderDate new value for {@value #ATTRIBUTE_NAME_LAST_ORDER_DATE} attribute.
     */
    protected void setLastOrderDate(final LocalDate lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    // -------------------------------------------------------------------------------------------------- firstOrderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_ORDER_DATE} attribute.
     */
    public LocalDate getFirstOrderDate() {
        return firstOrderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_ORDER_DATE} attribute with the specified value.
     *
     * @param firstOrderDate new value for {@value #ATTRIBUTE_NAME_FIRST_ORDER_DATE} attribute.
     */
    protected void setFirstOrderDate(final LocalDate firstOrderDate) {
        this.firstOrderDate = firstOrderDate;
    }

    // -------------------------------------------------------------------------------------------- customerLifespanDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_LIFESPAN_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_LIFESPAN_DAYS} attribute.
     */
    public Integer getCustomerLifespanDays() {
        return customerLifespanDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_LIFESPAN_DAYS} attribute with the specified value.
     *
     * @param customerLifespanDays new value for {@value #ATTRIBUTE_NAME_CUSTOMER_LIFESPAN_DAYS} attribute.
     */
    protected void setCustomerLifespanDays(final Integer customerLifespanDays) {
        this.customerLifespanDays = customerLifespanDays;
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

    // ---------------------------------------------------------------------------------------------------- pendingSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PENDING_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PENDING_SALES} attribute.
     */
    public BigDecimal getPendingSales() {
        return pendingSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PENDING_SALES} attribute with the specified value.
     *
     * @param pendingSales new value for {@value #ATTRIBUTE_NAME_PENDING_SALES} attribute.
     */
    protected void setPendingSales(final BigDecimal pendingSales) {
        this.pendingSales = pendingSales;
    }

    // ---------------------------------------------------------------------------------------------------- totalReturns

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_RETURNS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_RETURNS} attribute.
     */
    public Integer getTotalReturns() {
        return totalReturns;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_RETURNS} attribute with the specified value.
     *
     * @param totalReturns new value for {@value #ATTRIBUTE_NAME_TOTAL_RETURNS} attribute.
     */
    protected void setTotalReturns(final Integer totalReturns) {
        this.totalReturns = totalReturns;
    }

    // ---------------------------------------------------------------------------------------------------- totalRefunds

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute.
     */
    public BigDecimal getTotalRefunds() {
        return totalRefunds;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute with the specified value.
     *
     * @param totalRefunds new value for {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute.
     */
    protected void setTotalRefunds(final BigDecimal totalRefunds) {
        this.totalRefunds = totalRefunds;
    }

    // ------------------------------------------------------------------------------------------------- customerSegment

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute.
     */
    public String getCustomerSegment() {
        return customerSegment;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute with the specified value.
     *
     * @param customerSegment new value for {@value #ATTRIBUTE_NAME_CUSTOMER_SEGMENT} attribute.
     */
    protected void setCustomerSegment(final String customerSegment) {
        this.customerSegment = customerSegment;
    }

    // ------------------------------------------------------------------------------------------------------ returnRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_RATE} attribute.
     */
    public BigDecimal getReturnRate() {
        return returnRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_RATE} attribute with the specified value.
     *
     * @param returnRate new value for {@value #ATTRIBUTE_NAME_RETURN_RATE} attribute.
     */
    protected void setReturnRate(final BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    // ---------------------------------------------------------------------------------------------- daysSinceLastOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ORDER} attribute.
     */
    public Integer getDaysSinceLastOrder() {
        return daysSinceLastOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ORDER} attribute with the specified value.
     *
     * @param daysSinceLastOrder new value for {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ORDER} attribute.
     */
    protected void setDaysSinceLastOrder(final Integer daysSinceLastOrder) {
        this.daysSinceLastOrder = daysSinceLastOrder;
    }

    // -------------------------------------------------------------------------------------------------- customerStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_STATUS} attribute.
     */
    public String getCustomerStatus() {
        return customerStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_STATUS} attribute with the specified value.
     *
     * @param customerStatus new value for {@value #ATTRIBUTE_NAME_CUSTOMER_STATUS} attribute.
     */
    protected void setCustomerStatus(final String customerStatus) {
        this.customerStatus = customerStatus;
    }

    // ------------------------------------------------------------------------------------------------- avgMonthlyValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_MONTHLY_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_MONTHLY_VALUE} attribute.
     */
    public BigDecimal getAvgMonthlyValue() {
        return avgMonthlyValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_MONTHLY_VALUE} attribute with the specified value.
     *
     * @param avgMonthlyValue new value for {@value #ATTRIBUTE_NAME_AVG_MONTHLY_VALUE} attribute.
     */
    protected void setAvgMonthlyValue(final BigDecimal avgMonthlyValue) {
        this.avgMonthlyValue = avgMonthlyValue;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_CODE
    )
    private String customerCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_NAME
    )
    private String customerName;

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
    @Size(max = COLUMN_LENGTH_CITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CITY,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STATE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STATE
    )
    private String state;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SALES_REP)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_REP,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SALES_REP
    )
    private String salesRep;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer totalOrders;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_SALES - COLUMN_SCALE_TOTAL_SALES, fraction = COLUMN_SCALE_TOTAL_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_SALES,
            scale = COLUMN_SCALE_TOTAL_SALES
    )
    private BigDecimal totalSales;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_ORDER_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate lastOrderDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FIRST_ORDER_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate firstOrderDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_LIFESPAN_DAYS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer customerLifespanDays;

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
    @Digits(integer = COLUMN_PRECISION_PENDING_SALES - COLUMN_SCALE_PENDING_SALES,
            fraction = COLUMN_SCALE_PENDING_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PENDING_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PENDING_SALES,
            scale = COLUMN_SCALE_PENDING_SALES
    )
    private BigDecimal pendingSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_RETURNS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer totalReturns;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_REFUNDS - COLUMN_SCALE_TOTAL_REFUNDS,
            fraction = COLUMN_SCALE_TOTAL_REFUNDS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TOTAL_REFUNDS,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_REFUNDS,
            scale = COLUMN_SCALE_TOTAL_REFUNDS
    )
    private BigDecimal totalRefunds;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_SEGMENT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_SEGMENT,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_SEGMENT
    )
    private String customerSegment;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_RETURN_RATE - COLUMN_SCALE_RETURN_RATE, fraction = COLUMN_SCALE_RETURN_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RETURN_RATE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_RETURN_RATE,
            scale = COLUMN_SCALE_RETURN_RATE
    )
    private BigDecimal returnRate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_SINCE_LAST_ORDER,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer daysSinceLastOrder;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_STATUS
    )
    private String customerStatus;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_MONTHLY_VALUE - COLUMN_SCALE_AVG_MONTHLY_VALUE,
            fraction = COLUMN_SCALE_AVG_MONTHLY_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_MONTHLY_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_MONTHLY_VALUE,
            scale = COLUMN_SCALE_AVG_MONTHLY_VALUE
    )
    private BigDecimal avgMonthlyValue;
}

