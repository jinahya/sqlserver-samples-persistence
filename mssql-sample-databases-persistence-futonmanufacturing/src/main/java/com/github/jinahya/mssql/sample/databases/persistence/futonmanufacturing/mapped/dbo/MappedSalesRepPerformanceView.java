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
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_RepPerformance} view.
 * <p>
 * Identifier: the view declares no key; EmployeeCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesRepPerformanceView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_RepPerformance";

    // ---------------------------------------------------------------------------------------------------- EmployeeCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_CODE = "EmployeeCode";

    /**
     * The length of the {@value #COLUMN_NAME_EMPLOYEE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMPLOYEE_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_CODE = "employeeCode";

    // ---------------------------------------------------------------------------------------------------- SalesRepName

    /**
     * The name of the {@code nvarchar(101), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REP_NAME = "SalesRepName";

    /**
     * The length of the {@value #COLUMN_NAME_SALES_REP_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SALES_REP_NAME = 101;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REP_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP_NAME = "salesRepName";

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

    // ----------------------------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

    // ------------------------------------------------------------------------------------------------- UniqueCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_CUSTOMERS = "UniqueCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_CUSTOMERS = "uniqueCustomers";

    // ------------------------------------------------------------------------------------------------------ GrossSales

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_SALES = "grossSales";

    // -------------------------------------------------------------------------------------------------- TotalDiscounts

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DISCOUNTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DISCOUNTS = "totalDiscounts";

    // -------------------------------------------------------------------------------------------------------- NetSales

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_SALES = "netSales";

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

    // --------------------------------------------------------------------------------------------------- AvgDaysToShip

    /**
     * The name of the {@code decimal(10, 1)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DAYS_TO_SHIP = "AvgDaysToShip";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_DAYS_TO_SHIP} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_DAYS_TO_SHIP = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_DAYS_TO_SHIP} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_DAYS_TO_SHIP = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DAYS_TO_SHIP} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP = "avgDaysToShip";

    // --------------------------------------------------------------------------------------------------- QuotesCreated

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTES_CREATED = "QuotesCreated";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTES_CREATED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTES_CREATED = "quotesCreated";

    // -------------------------------------------------------------------------------------------------- QuotesAccepted

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTES_ACCEPTED = "QuotesAccepted";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTES_ACCEPTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTES_ACCEPTED = "quotesAccepted";

    // ---------------------------------------------------------------------------------------------------- QuoteWinRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTE_WIN_RATE = "QuoteWinRate";

    /**
     * The precision of the {@value #COLUMN_NAME_QUOTE_WIN_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUOTE_WIN_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_QUOTE_WIN_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUOTE_WIN_RATE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTE_WIN_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTE_WIN_RATE = "quoteWinRate";

    // ------------------------------------------------------------------------------------------------------- SalesRank

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_RANK = "SalesRank";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_RANK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_RANK = "salesRank";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesRepPerformanceView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "employeeCode=" + employeeCode
               + ",salesRepName=" + salesRepName
               + ",territoryName=" + territoryName
               + ",region=" + region
               + ",totalOrders=" + totalOrders
               + ",uniqueCustomers=" + uniqueCustomers
               + ",grossSales=" + grossSales
               + ",totalDiscounts=" + totalDiscounts
               + ",netSales=" + netSales
               + ",avgOrderValue=" + avgOrderValue
               + ",deliveredSales=" + deliveredSales
               + ",avgDaysToShip=" + avgDaysToShip
               + ",quotesCreated=" + quotesCreated
               + ",quotesAccepted=" + quotesAccepted
               + ",quoteWinRate=" + quoteWinRate
               + ",salesRank=" + salesRank
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
        if (!(obj instanceof MappedSalesRepPerformanceView that)) {
            return false;
        }
        return Objects.equals(employeeCode, that.employeeCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(employeeCode);
    }

    // ---------------------------------------------------------------------------------------------------- employeeCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_CODE} attribute.
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_CODE} attribute with the specified value.
     *
     * @param employeeCode new value for {@value #ATTRIBUTE_NAME_EMPLOYEE_CODE} attribute.
     */
    protected void setEmployeeCode(final String employeeCode) {
        this.employeeCode = employeeCode;
    }

    // ---------------------------------------------------------------------------------------------------- salesRepName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REP_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REP_NAME} attribute.
     */
    public String getSalesRepName() {
        return salesRepName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REP_NAME} attribute with the specified value.
     *
     * @param salesRepName new value for {@value #ATTRIBUTE_NAME_SALES_REP_NAME} attribute.
     */
    protected void setSalesRepName(final String salesRepName) {
        this.salesRepName = salesRepName;
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

    // ------------------------------------------------------------------------------------------------- uniqueCustomers

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

    // ------------------------------------------------------------------------------------------------------ grossSales

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

    // -------------------------------------------------------------------------------------------------- totalDiscounts

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

    // -------------------------------------------------------------------------------------------------------- netSales

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

    // --------------------------------------------------------------------------------------------------- avgDaysToShip

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute.
     */
    public BigDecimal getAvgDaysToShip() {
        return avgDaysToShip;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute with the specified value.
     *
     * @param avgDaysToShip new value for {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute.
     */
    protected void setAvgDaysToShip(final BigDecimal avgDaysToShip) {
        this.avgDaysToShip = avgDaysToShip;
    }

    // --------------------------------------------------------------------------------------------------- quotesCreated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTES_CREATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTES_CREATED} attribute.
     */
    public Integer getQuotesCreated() {
        return quotesCreated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTES_CREATED} attribute with the specified value.
     *
     * @param quotesCreated new value for {@value #ATTRIBUTE_NAME_QUOTES_CREATED} attribute.
     */
    protected void setQuotesCreated(final Integer quotesCreated) {
        this.quotesCreated = quotesCreated;
    }

    // -------------------------------------------------------------------------------------------------- quotesAccepted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTES_ACCEPTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTES_ACCEPTED} attribute.
     */
    public Integer getQuotesAccepted() {
        return quotesAccepted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTES_ACCEPTED} attribute with the specified value.
     *
     * @param quotesAccepted new value for {@value #ATTRIBUTE_NAME_QUOTES_ACCEPTED} attribute.
     */
    protected void setQuotesAccepted(final Integer quotesAccepted) {
        this.quotesAccepted = quotesAccepted;
    }

    // ---------------------------------------------------------------------------------------------------- quoteWinRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTE_WIN_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTE_WIN_RATE} attribute.
     */
    public BigDecimal getQuoteWinRate() {
        return quoteWinRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTE_WIN_RATE} attribute with the specified value.
     *
     * @param quoteWinRate new value for {@value #ATTRIBUTE_NAME_QUOTE_WIN_RATE} attribute.
     */
    protected void setQuoteWinRate(final BigDecimal quoteWinRate) {
        this.quoteWinRate = quoteWinRate;
    }

    // ------------------------------------------------------------------------------------------------------- salesRank

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_RANK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_RANK} attribute.
     */
    public Long getSalesRank() {
        return salesRank;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_RANK} attribute with the specified value.
     *
     * @param salesRank new value for {@value #ATTRIBUTE_NAME_SALES_RANK} attribute.
     */
    protected void setSalesRank(final Long salesRank) {
        this.salesRank = salesRank;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_EMPLOYEE_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_EMPLOYEE_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_EMPLOYEE_CODE
    )
    private String employeeCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SALES_REP_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_REP_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SALES_REP_NAME
    )
    private String salesRepName;

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
    @Column(name = COLUMN_NAME_TOTAL_ORDERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer totalOrders;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer uniqueCustomers;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_GROSS_SALES - COLUMN_SCALE_GROSS_SALES, fraction = COLUMN_SCALE_GROSS_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_GROSS_SALES,
            scale = COLUMN_SCALE_GROSS_SALES
    )
    private BigDecimal grossSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_DISCOUNTS - COLUMN_SCALE_TOTAL_DISCOUNTS,
            fraction = COLUMN_SCALE_TOTAL_DISCOUNTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_DISCOUNTS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_DISCOUNTS,
            scale = COLUMN_SCALE_TOTAL_DISCOUNTS
    )
    private BigDecimal totalDiscounts;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_NET_SALES - COLUMN_SCALE_NET_SALES, fraction = COLUMN_SCALE_NET_SALES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_SALES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_NET_SALES,
            scale = COLUMN_SCALE_NET_SALES
    )
    private BigDecimal netSales;

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
    @Digits(integer = COLUMN_PRECISION_AVG_DAYS_TO_SHIP - COLUMN_SCALE_AVG_DAYS_TO_SHIP,
            fraction = COLUMN_SCALE_AVG_DAYS_TO_SHIP)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DAYS_TO_SHIP,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_DAYS_TO_SHIP,
            scale = COLUMN_SCALE_AVG_DAYS_TO_SHIP
    )
    private BigDecimal avgDaysToShip;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUOTES_CREATED,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer quotesCreated;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUOTES_ACCEPTED,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer quotesAccepted;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_QUOTE_WIN_RATE - COLUMN_SCALE_QUOTE_WIN_RATE,
            fraction = COLUMN_SCALE_QUOTE_WIN_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUOTE_WIN_RATE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_QUOTE_WIN_RATE,
            scale = COLUMN_SCALE_QUOTE_WIN_RATE
    )
    private BigDecimal quoteWinRate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_RANK,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Long salesRank;
}

