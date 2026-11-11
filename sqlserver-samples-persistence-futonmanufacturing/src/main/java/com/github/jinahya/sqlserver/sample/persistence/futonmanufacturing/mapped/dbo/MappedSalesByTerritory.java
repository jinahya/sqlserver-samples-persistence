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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ByTerritory} view.
 * <p>
 * Identifier: the view declares no key; TerritoryCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesByTerritory {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ByTerritory";

    // ------------------------------------------------------------------------------- TerritoryCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_CODE = "TerritoryCode";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_CODE = "territoryCode";

    // ------------------------------------------------------------------------------- TerritoryName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
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

    // -------------------------------------------------------------------------------------- Region

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

    // ----------------------------------------------------------------------------------- SalesReps

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REPS = "SalesReps";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REPS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REPS = "salesReps";

    // ------------------------------------------------------------------------------ TotalCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_CUSTOMERS = "TotalCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_CUSTOMERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_CUSTOMERS = "totalCustomers";

    // --------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

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

    // --------------------------------------------------------------------------------- SalesPerRep

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_PER_REP = "SalesPerRep";

    /**
     * The precision of the {@value #COLUMN_NAME_SALES_PER_REP} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SALES_PER_REP = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SALES_PER_REP} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SALES_PER_REP = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SALES_PER_REP = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SALES_PER_REP = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_PER_REP} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PER_REP = "salesPerRep";

    // ---------------------------------------------------------------------------- SalesPerCustomer

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_PER_CUSTOMER = "SalesPerCustomer";

    /**
     * The precision of the {@value #COLUMN_NAME_SALES_PER_CUSTOMER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SALES_PER_CUSTOMER = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SALES_PER_CUSTOMER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SALES_PER_CUSTOMER = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SALES_PER_CUSTOMER = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SALES_PER_CUSTOMER = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_PER_CUSTOMER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PER_CUSTOMER = "salesPerCustomer";

    // ---------------------------------------------------------------------------------- TopProduct

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOP_PRODUCT = "TopProduct";

    /**
     * The length of the {@value #COLUMN_NAME_TOP_PRODUCT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TOP_PRODUCT = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOP_PRODUCT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOP_PRODUCT = "topProduct";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesByTerritory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "territoryCode=" + territoryCode
               + ",territoryName=" + territoryName
               + ",region=" + region
               + ",salesReps=" + salesReps
               + ",totalCustomers=" + totalCustomers
               + ",totalOrders=" + totalOrders
               + ",grossSales=" + grossSales
               + ",discounts=" + discounts
               + ",netSales=" + netSales
               + ",avgOrderValue=" + avgOrderValue
               + ",salesPerRep=" + salesPerRep
               + ",salesPerCustomer=" + salesPerCustomer
               + ",topProduct=" + topProduct
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
        if (!(obj instanceof MappedSalesByTerritory that)) {
            return false;
        }
        return Objects.equals(getTerritoryCode(), that.getTerritoryCode());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getTerritoryCode());
    }

    // ------------------------------------------------------------------------------- territoryCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     */
    public String getTerritoryCode() {
        return territoryCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute with the specified value.
     *
     * @param territoryCode new value for {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     */
    protected void setTerritoryCode(final String territoryCode) {
        this.territoryCode = territoryCode;
    }

    // ------------------------------------------------------------------------------- territoryName

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

    // -------------------------------------------------------------------------------------- region

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

    // ----------------------------------------------------------------------------------- salesReps

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REPS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REPS} attribute.
     */
    public Integer getSalesReps() {
        return salesReps;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REPS} attribute with the specified value.
     *
     * @param salesReps new value for {@value #ATTRIBUTE_NAME_SALES_REPS} attribute.
     */
    protected void setSalesReps(final Integer salesReps) {
        this.salesReps = salesReps;
    }

    // ------------------------------------------------------------------------------ totalCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute.
     */
    public Integer getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute with the specified value.
     *
     * @param totalCustomers new value for {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute.
     */
    protected void setTotalCustomers(final Integer totalCustomers) {
        this.totalCustomers = totalCustomers;
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

    // --------------------------------------------------------------------------------- salesPerRep

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute.
     */
    public BigDecimal getSalesPerRep() {
        return salesPerRep;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute with the specified value.
     *
     * @param salesPerRep new value for {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute.
     */
    protected void setSalesPerRep(final BigDecimal salesPerRep) {
        this.salesPerRep = salesPerRep;
    }

    // ---------------------------------------------------------------------------- salesPerCustomer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute.
     */
    public BigDecimal getSalesPerCustomer() {
        return salesPerCustomer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute with the specified value.
     *
     * @param salesPerCustomer new value for {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute.
     */
    protected void setSalesPerCustomer(final BigDecimal salesPerCustomer) {
        this.salesPerCustomer = salesPerCustomer;
    }

    // ---------------------------------------------------------------------------------- topProduct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute.
     */
    public String getTopProduct() {
        return topProduct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute with the specified value.
     *
     * @param topProduct new value for {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute.
     */
    protected void setTopProduct(final String topProduct) {
        this.topProduct = topProduct;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TERRITORY_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_TERRITORY_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_TERRITORY_CODE)
    private String territoryCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TERRITORY_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TERRITORY_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_TERRITORY_NAME)
    private String territoryName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_REGION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REGION, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_REGION)
    private String region;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_REPS, nullable = true, insertable = false, updatable = false)
    private Integer salesReps;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_CUSTOMERS, nullable = true, insertable = false, updatable = false)
    private Integer totalCustomers;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer totalOrders;

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
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_ORDER_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_ORDER_VALUE, scale = COLUMN_SCALE_AVG_ORDER_VALUE)
    private BigDecimal avgOrderValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SALES_PER_REP)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SALES_PER_REP)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_PER_REP, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SALES_PER_REP, scale = COLUMN_SCALE_SALES_PER_REP)
    private BigDecimal salesPerRep;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SALES_PER_CUSTOMER)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SALES_PER_CUSTOMER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_PER_CUSTOMER, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SALES_PER_CUSTOMER, scale = COLUMN_SCALE_SALES_PER_CUSTOMER)
    private BigDecimal salesPerCustomer;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TOP_PRODUCT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOP_PRODUCT, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_TOP_PRODUCT)
    private String topProduct;
}

