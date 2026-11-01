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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ByTerritory} view.
 * <p>
 * Identifier: the view declares no key; TerritoryCode is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedSalesByTerritoryView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ByTerritory";

    // --------------------------------------------------------------------------------------------------- TerritoryCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_CODE = "TerritoryCode";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_CODE = "territoryCode";

    // --------------------------------------------------------------------------------------------------- TerritoryName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_NAME = "TerritoryName";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ------------------------------------------------------------------------------------------------------- SalesReps

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REPS = "SalesReps";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REPS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REPS = "salesReps";

    // -------------------------------------------------------------------------------------------------- TotalCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_CUSTOMERS = "TotalCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_CUSTOMERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_CUSTOMERS = "totalCustomers";

    // ----------------------------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_SALES} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_SALES = "grossSales";

    // ------------------------------------------------------------------------------------------------------- Discounts

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNTS = "discounts";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_SALES} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ORDER_VALUE = "avgOrderValue";

    // ----------------------------------------------------------------------------------------------------- SalesPerRep

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_PER_REP} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PER_REP = "salesPerRep";

    // ------------------------------------------------------------------------------------------------ SalesPerCustomer

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_PER_CUSTOMER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_PER_CUSTOMER = "salesPerCustomer";

    // ------------------------------------------------------------------------------------------------------ TopProduct

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOP_PRODUCT = "TopProduct";

    /**
     * The length of the {@value #COLUMN_NAME_TOP_PRODUCT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TOP_PRODUCT = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOP_PRODUCT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOP_PRODUCT = "topProduct";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesByTerritoryView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
        if (!(obj instanceof MappedSalesByTerritoryView that)) {
            return false;
        }
        return Objects.equals(territoryCode, that.territoryCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(territoryCode);
    }

    // --------------------------------------------------------------------------------------------------- territoryCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     */
    @Nonnull
    public String getTerritoryCode() {
        return territoryCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute with the specified value.
     *
     * @param territoryCode new value for {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     */
    protected void setTerritoryCode(@Nonnull final String territoryCode) {
        this.territoryCode = territoryCode;
    }

    // --------------------------------------------------------------------------------------------------- territoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    @Nonnull
    public String getTerritoryName() {
        return territoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute with the specified value.
     *
     * @param territoryName new value for {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    protected void setTerritoryName(@Nonnull final String territoryName) {
        this.territoryName = territoryName;
    }

    // ---------------------------------------------------------------------------------------------------------- region

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    @Nullable
    public String getRegion() {
        return region;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION} attribute with the specified value.
     *
     * @param region new value for {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    protected void setRegion(@Nullable final String region) {
        this.region = region;
    }

    // ------------------------------------------------------------------------------------------------------- salesReps

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REPS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REPS} attribute.
     */
    @Nullable
    public Integer getSalesReps() {
        return salesReps;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REPS} attribute with the specified value.
     *
     * @param salesReps new value for {@value #ATTRIBUTE_NAME_SALES_REPS} attribute.
     */
    protected void setSalesReps(@Nullable final Integer salesReps) {
        this.salesReps = salesReps;
    }

    // -------------------------------------------------------------------------------------------------- totalCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute.
     */
    @Nullable
    public Integer getTotalCustomers() {
        return totalCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute with the specified value.
     *
     * @param totalCustomers new value for {@value #ATTRIBUTE_NAME_TOTAL_CUSTOMERS} attribute.
     */
    protected void setTotalCustomers(@Nullable final Integer totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    // ----------------------------------------------------------------------------------------------------- totalOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    @Nullable
    public Integer getTotalOrders() {
        return totalOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute with the specified value.
     *
     * @param totalOrders new value for {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    protected void setTotalOrders(@Nullable final Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    // ------------------------------------------------------------------------------------------------------ grossSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     */
    @Nullable
    public BigDecimal getGrossSales() {
        return grossSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute with the specified value.
     *
     * @param grossSales new value for {@value #ATTRIBUTE_NAME_GROSS_SALES} attribute.
     */
    protected void setGrossSales(@Nullable final BigDecimal grossSales) {
        this.grossSales = grossSales;
    }

    // ------------------------------------------------------------------------------------------------------- discounts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute.
     */
    @Nullable
    public BigDecimal getDiscounts() {
        return discounts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute with the specified value.
     *
     * @param discounts new value for {@value #ATTRIBUTE_NAME_DISCOUNTS} attribute.
     */
    protected void setDiscounts(@Nullable final BigDecimal discounts) {
        this.discounts = discounts;
    }

    // -------------------------------------------------------------------------------------------------------- netSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     */
    @Nullable
    public BigDecimal getNetSales() {
        return netSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NET_SALES} attribute with the specified value.
     *
     * @param netSales new value for {@value #ATTRIBUTE_NAME_NET_SALES} attribute.
     */
    protected void setNetSales(@Nullable final BigDecimal netSales) {
        this.netSales = netSales;
    }

    // --------------------------------------------------------------------------------------------------- avgOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    @Nullable
    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute with the specified value.
     *
     * @param avgOrderValue new value for {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    protected void setAvgOrderValue(@Nullable final BigDecimal avgOrderValue) {
        this.avgOrderValue = avgOrderValue;
    }

    // ----------------------------------------------------------------------------------------------------- salesPerRep

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute.
     */
    @Nullable
    public BigDecimal getSalesPerRep() {
        return salesPerRep;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute with the specified value.
     *
     * @param salesPerRep new value for {@value #ATTRIBUTE_NAME_SALES_PER_REP} attribute.
     */
    protected void setSalesPerRep(@Nullable final BigDecimal salesPerRep) {
        this.salesPerRep = salesPerRep;
    }

    // ------------------------------------------------------------------------------------------------ salesPerCustomer

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute.
     */
    @Nullable
    public BigDecimal getSalesPerCustomer() {
        return salesPerCustomer;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute with the specified value.
     *
     * @param salesPerCustomer new value for {@value #ATTRIBUTE_NAME_SALES_PER_CUSTOMER} attribute.
     */
    protected void setSalesPerCustomer(@Nullable final BigDecimal salesPerCustomer) {
        this.salesPerCustomer = salesPerCustomer;
    }

    // ------------------------------------------------------------------------------------------------------ topProduct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute.
     */
    @Nullable
    public String getTopProduct() {
        return topProduct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute with the specified value.
     *
     * @param topProduct new value for {@value #ATTRIBUTE_NAME_TOP_PRODUCT} attribute.
     */
    protected void setTopProduct(@Nullable final String topProduct) {
        this.topProduct = topProduct;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_TERRITORY_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_TERRITORY_CODE,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_TERRITORY_CODE
    )
    private String territoryCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_TERRITORY_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_TERRITORY_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TERRITORY_NAME
    )
    private String territoryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_REGION)
    @Column(name = COLUMN_NAME_REGION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REGION
    )
    private String region;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_SALES_REPS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer salesReps;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_TOTAL_CUSTOMERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer totalCustomers;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_TOTAL_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer totalOrders;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_GROSS_SALES - COLUMN_SCALE_GROSS_SALES, fraction = COLUMN_SCALE_GROSS_SALES)
    @Column(name = COLUMN_NAME_GROSS_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_SALES,
            scale = COLUMN_SCALE_GROSS_SALES
    )
    private BigDecimal grossSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DISCOUNTS - COLUMN_SCALE_DISCOUNTS, fraction = COLUMN_SCALE_DISCOUNTS)
    @Column(name = COLUMN_NAME_DISCOUNTS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNTS,
            scale = COLUMN_SCALE_DISCOUNTS
    )
    private BigDecimal discounts;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_NET_SALES - COLUMN_SCALE_NET_SALES, fraction = COLUMN_SCALE_NET_SALES)
    @Column(name = COLUMN_NAME_NET_SALES,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_NET_SALES,
            scale = COLUMN_SCALE_NET_SALES
    )
    private BigDecimal netSales;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_AVG_ORDER_VALUE - COLUMN_SCALE_AVG_ORDER_VALUE, fraction = COLUMN_SCALE_AVG_ORDER_VALUE)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_ORDER_VALUE,
            scale = COLUMN_SCALE_AVG_ORDER_VALUE
    )
    private BigDecimal avgOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_SALES_PER_REP - COLUMN_SCALE_SALES_PER_REP, fraction = COLUMN_SCALE_SALES_PER_REP)
    @Column(name = COLUMN_NAME_SALES_PER_REP,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SALES_PER_REP,
            scale = COLUMN_SCALE_SALES_PER_REP
    )
    private BigDecimal salesPerRep;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_SALES_PER_CUSTOMER - COLUMN_SCALE_SALES_PER_CUSTOMER, fraction = COLUMN_SCALE_SALES_PER_CUSTOMER)
    @Column(name = COLUMN_NAME_SALES_PER_CUSTOMER,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SALES_PER_CUSTOMER,
            scale = COLUMN_SCALE_SALES_PER_CUSTOMER
    )
    private BigDecimal salesPerCustomer;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_TOP_PRODUCT)
    @Column(name = COLUMN_NAME_TOP_PRODUCT,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TOP_PRODUCT
    )
    private String topProduct;
}

