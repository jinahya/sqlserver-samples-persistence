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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_CustomerLifetimeValue} view.
 * <p>
 * Identifier: the view declares no key; CustomerID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesCustomerLifetimeValueView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_CustomerLifetimeValue";

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

    // ----------------------------------------------------------------------------------------------- FirstPurchaseDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_PURCHASE_DATE = "FirstPurchaseDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_PURCHASE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_PURCHASE_DATE = "firstPurchaseDate";

    // ------------------------------------------------------------------------------------------------ LastPurchaseDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_PURCHASE_DATE = "LastPurchaseDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_PURCHASE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_PURCHASE_DATE = "lastPurchaseDate";

    // ------------------------------------------------------------------------------------------ CustomerLifetimeMonths

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_LIFETIME_MONTHS = "CustomerLifetimeMonths";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_LIFETIME_MONTHS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_LIFETIME_MONTHS = "customerLifetimeMonths";

    // ----------------------------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

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

    // ----------------------------------------------------------------------------------------------------- TotalProfit

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_PROFIT = "TotalProfit";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_PROFIT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_PROFIT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_PROFIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_PROFIT = "totalProfit";

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

    // ----------------------------------------------------------------------------------------------- AvgMonthlyRevenue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_MONTHLY_REVENUE = "AvgMonthlyRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_MONTHLY_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_MONTHLY_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_MONTHLY_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_MONTHLY_REVENUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_MONTHLY_REVENUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_MONTHLY_REVENUE = "avgMonthlyRevenue";

    // ---------------------------------------------------------------------------------------------------- CustomerTier

    /**
     * The name of the {@code varchar(8), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_TIER = "CustomerTier";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_TIER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_TIER = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TIER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TIER = "customerTier";

    // ------------------------------------------------------------------------------------------- DaysSinceLastPurchase

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_SINCE_LAST_PURCHASE = "DaysSinceLastPurchase";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_SINCE_LAST_PURCHASE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_SINCE_LAST_PURCHASE = "daysSinceLastPurchase";

    // -------------------------------------------------------------------------------------------------- ActivityStatus

    /**
     * The name of the {@code varchar(13), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTIVITY_STATUS = "ActivityStatus";

    /**
     * The length of the {@value #COLUMN_NAME_ACTIVITY_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ACTIVITY_STATUS = 13;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTIVITY_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTIVITY_STATUS = "activityStatus";

    // ------------------------------------------------------------------------------------------ ProjectedAnnualRevenue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROJECTED_ANNUAL_REVENUE = "ProjectedAnnualRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_PROJECTED_ANNUAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PROJECTED_ANNUAL_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PROJECTED_ANNUAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PROJECTED_ANNUAL_REVENUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROJECTED_ANNUAL_REVENUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROJECTED_ANNUAL_REVENUE = "projectedAnnualRevenue";

    // ------------------------------------------------------------------------------------------------------- ValueRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALUE_RANK = "ValueRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALUE_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALUE_RANK = "valueRank";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesCustomerLifetimeValueView() {
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
               + ",salesRep=" + salesRep
               + ",firstPurchaseDate=" + firstPurchaseDate
               + ",lastPurchaseDate=" + lastPurchaseDate
               + ",customerLifetimeMonths=" + customerLifetimeMonths
               + ",totalOrders=" + totalOrders
               + ",totalRevenue=" + totalRevenue
               + ",totalProfit=" + totalProfit
               + ",avgOrderValue=" + avgOrderValue
               + ",avgMonthlyRevenue=" + avgMonthlyRevenue
               + ",customerTier=" + customerTier
               + ",daysSinceLastPurchase=" + daysSinceLastPurchase
               + ",activityStatus=" + activityStatus
               + ",projectedAnnualRevenue=" + projectedAnnualRevenue
               + ",valueRank=" + valueRank
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
        if (!(obj instanceof MappedSalesCustomerLifetimeValueView that)) {
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

    // ----------------------------------------------------------------------------------------------- firstPurchaseDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_PURCHASE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_PURCHASE_DATE} attribute.
     */
    public LocalDate getFirstPurchaseDate() {
        return firstPurchaseDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_PURCHASE_DATE} attribute with the specified value.
     *
     * @param firstPurchaseDate new value for {@value #ATTRIBUTE_NAME_FIRST_PURCHASE_DATE} attribute.
     */
    protected void setFirstPurchaseDate(final LocalDate firstPurchaseDate) {
        this.firstPurchaseDate = firstPurchaseDate;
    }

    // ------------------------------------------------------------------------------------------------ lastPurchaseDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_PURCHASE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_PURCHASE_DATE} attribute.
     */
    public LocalDate getLastPurchaseDate() {
        return lastPurchaseDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_PURCHASE_DATE} attribute with the specified value.
     *
     * @param lastPurchaseDate new value for {@value #ATTRIBUTE_NAME_LAST_PURCHASE_DATE} attribute.
     */
    protected void setLastPurchaseDate(final LocalDate lastPurchaseDate) {
        this.lastPurchaseDate = lastPurchaseDate;
    }

    // ------------------------------------------------------------------------------------------ customerLifetimeMonths

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_LIFETIME_MONTHS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_LIFETIME_MONTHS} attribute.
     */
    public Integer getCustomerLifetimeMonths() {
        return customerLifetimeMonths;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_LIFETIME_MONTHS} attribute with the specified value.
     *
     * @param customerLifetimeMonths new value for {@value #ATTRIBUTE_NAME_CUSTOMER_LIFETIME_MONTHS} attribute.
     */
    protected void setCustomerLifetimeMonths(final Integer customerLifetimeMonths) {
        this.customerLifetimeMonths = customerLifetimeMonths;
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

    // ----------------------------------------------------------------------------------------------------- totalProfit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_PROFIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_PROFIT} attribute.
     */
    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_PROFIT} attribute with the specified value.
     *
     * @param totalProfit new value for {@value #ATTRIBUTE_NAME_TOTAL_PROFIT} attribute.
     */
    protected void setTotalProfit(final BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
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

    // ----------------------------------------------------------------------------------------------- avgMonthlyRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_MONTHLY_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_MONTHLY_REVENUE} attribute.
     */
    public BigDecimal getAvgMonthlyRevenue() {
        return avgMonthlyRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_MONTHLY_REVENUE} attribute with the specified value.
     *
     * @param avgMonthlyRevenue new value for {@value #ATTRIBUTE_NAME_AVG_MONTHLY_REVENUE} attribute.
     */
    protected void setAvgMonthlyRevenue(final BigDecimal avgMonthlyRevenue) {
        this.avgMonthlyRevenue = avgMonthlyRevenue;
    }

    // ---------------------------------------------------------------------------------------------------- customerTier

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TIER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TIER} attribute.
     */
    public String getCustomerTier() {
        return customerTier;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TIER} attribute with the specified value.
     *
     * @param customerTier new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TIER} attribute.
     */
    protected void setCustomerTier(final String customerTier) {
        this.customerTier = customerTier;
    }

    // ------------------------------------------------------------------------------------------- daysSinceLastPurchase

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_PURCHASE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_PURCHASE} attribute.
     */
    public Integer getDaysSinceLastPurchase() {
        return daysSinceLastPurchase;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_PURCHASE} attribute with the specified value.
     *
     * @param daysSinceLastPurchase new value for {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_PURCHASE} attribute.
     */
    protected void setDaysSinceLastPurchase(final Integer daysSinceLastPurchase) {
        this.daysSinceLastPurchase = daysSinceLastPurchase;
    }

    // -------------------------------------------------------------------------------------------------- activityStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTIVITY_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTIVITY_STATUS} attribute.
     */
    public String getActivityStatus() {
        return activityStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTIVITY_STATUS} attribute with the specified value.
     *
     * @param activityStatus new value for {@value #ATTRIBUTE_NAME_ACTIVITY_STATUS} attribute.
     */
    protected void setActivityStatus(final String activityStatus) {
        this.activityStatus = activityStatus;
    }

    // ------------------------------------------------------------------------------------------ projectedAnnualRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROJECTED_ANNUAL_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROJECTED_ANNUAL_REVENUE} attribute.
     */
    public BigDecimal getProjectedAnnualRevenue() {
        return projectedAnnualRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROJECTED_ANNUAL_REVENUE} attribute with the specified value.
     *
     * @param projectedAnnualRevenue new value for {@value #ATTRIBUTE_NAME_PROJECTED_ANNUAL_REVENUE} attribute.
     */
    protected void setProjectedAnnualRevenue(final BigDecimal projectedAnnualRevenue) {
        this.projectedAnnualRevenue = projectedAnnualRevenue;
    }

    // ------------------------------------------------------------------------------------------------------- valueRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALUE_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALUE_RANK} attribute.
     */
    public Long getValueRank() {
        return valueRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALUE_RANK} attribute with the specified value.
     *
     * @param valueRank new value for {@value #ATTRIBUTE_NAME_VALUE_RANK} attribute.
     */
    protected void setValueRank(final Long valueRank) {
        this.valueRank = valueRank;
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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FIRST_PURCHASE_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate firstPurchaseDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_PURCHASE_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate lastPurchaseDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_LIFETIME_MONTHS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer customerLifetimeMonths;

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
    @Digits(integer = COLUMN_PRECISION_TOTAL_PROFIT - COLUMN_SCALE_TOTAL_PROFIT, fraction = COLUMN_SCALE_TOTAL_PROFIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_PROFIT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_PROFIT,
            scale = COLUMN_SCALE_TOTAL_PROFIT
    )
    private BigDecimal totalProfit;

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
    @Digits(integer = COLUMN_PRECISION_AVG_MONTHLY_REVENUE - COLUMN_SCALE_AVG_MONTHLY_REVENUE,
            fraction = COLUMN_SCALE_AVG_MONTHLY_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_MONTHLY_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_MONTHLY_REVENUE,
            scale = COLUMN_SCALE_AVG_MONTHLY_REVENUE
    )
    private BigDecimal avgMonthlyRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_TIER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_TIER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_TIER
    )
    private String customerTier;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_SINCE_LAST_PURCHASE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer daysSinceLastPurchase;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ACTIVITY_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACTIVITY_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ACTIVITY_STATUS
    )
    private String activityStatus;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PROJECTED_ANNUAL_REVENUE - COLUMN_SCALE_PROJECTED_ANNUAL_REVENUE,
            fraction = COLUMN_SCALE_PROJECTED_ANNUAL_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PROJECTED_ANNUAL_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PROJECTED_ANNUAL_REVENUE,
            scale = COLUMN_SCALE_PROJECTED_ANNUAL_REVENUE
    )
    private BigDecimal projectedAnnualRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_VALUE_RANK,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Long valueRank;
}

