package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.vStoreWithDemographics} view.
 * <p>
 * Identifier: the view declares no key; BusinessEntityID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable,
 * and none but the identifier, of which EclipseLink requires a writable mapping, is insertable; some of its columns
 * are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedStoreWithDemographicsView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vStoreWithDemographics";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------------------------------------ Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ----------------------------------------------------------------------------------------------------- AnnualSales

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ANNUAL_SALES = "AnnualSales";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ANNUAL_SALES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ANNUAL_SALES = "annualSales";

    // --------------------------------------------------------------------------------------------------- AnnualRevenue

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ANNUAL_REVENUE = "AnnualRevenue";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ANNUAL_REVENUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ANNUAL_REVENUE = "annualRevenue";

    // -------------------------------------------------------------------------------------------------------- BankName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BANK_NAME = "BankName";

    /**
     * The length of the {@value #COLUMN_NAME_BANK_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BANK_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BANK_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BANK_NAME = "bankName";

    // ---------------------------------------------------------------------------------------------------- BusinessType

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_TYPE = "BusinessType";

    /**
     * The length of the {@value #COLUMN_NAME_BUSINESS_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BUSINESS_TYPE = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_TYPE = "businessType";

    // ------------------------------------------------------------------------------------------------------ YearOpened

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR_OPENED = "YearOpened";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR_OPENED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR_OPENED = "yearOpened";

    // ------------------------------------------------------------------------------------------------------- Specialty

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIALTY = "Specialty";

    /**
     * The length of the {@value #COLUMN_NAME_SPECIALTY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SPECIALTY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIALTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIALTY = "specialty";

    // ------------------------------------------------------------------------------------------------------ SquareFeet

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SQUARE_FEET = "SquareFeet";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SQUARE_FEET} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SQUARE_FEET = "squareFeet";

    // ---------------------------------------------------------------------------------------------------------- Brands

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BRANDS = "Brands";

    /**
     * The length of the {@value #COLUMN_NAME_BRANDS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BRANDS = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BRANDS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BRANDS = "brands";

    // -------------------------------------------------------------------------------------------------------- Internet

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNET = "Internet";

    /**
     * The length of the {@value #COLUMN_NAME_INTERNET} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_INTERNET = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNET} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNET = "internet";

    // ------------------------------------------------------------------------------------------------- NumberEmployees

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_EMPLOYEES = "NumberEmployees";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_EMPLOYEES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_EMPLOYEES = "numberEmployees";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStoreWithDemographicsView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",name=" + name
               + ",annualSales=" + annualSales
               + ",annualRevenue=" + annualRevenue
               + ",bankName=" + bankName
               + ",businessType=" + businessType
               + ",yearOpened=" + yearOpened
               + ",specialty=" + specialty
               + ",squareFeet=" + squareFeet
               + ",brands=" + brands
               + ",internet=" + internet
               + ",numberEmployees=" + numberEmployees
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
        if (!(obj instanceof MappedStoreWithDemographicsView that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    protected void setName(final String name) {
        this.name = name;
    }

    // ----------------------------------------------------------------------------------------------------- annualSales

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ANNUAL_SALES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ANNUAL_SALES} attribute.
     */
    public BigDecimal getAnnualSales() {
        return annualSales;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ANNUAL_SALES} attribute with the specified value.
     *
     * @param annualSales new value for {@value #ATTRIBUTE_NAME_ANNUAL_SALES} attribute.
     */
    protected void setAnnualSales(final BigDecimal annualSales) {
        this.annualSales = annualSales;
    }

    // --------------------------------------------------------------------------------------------------- annualRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ANNUAL_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ANNUAL_REVENUE} attribute.
     */
    public BigDecimal getAnnualRevenue() {
        return annualRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ANNUAL_REVENUE} attribute with the specified value.
     *
     * @param annualRevenue new value for {@value #ATTRIBUTE_NAME_ANNUAL_REVENUE} attribute.
     */
    protected void setAnnualRevenue(final BigDecimal annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    // -------------------------------------------------------------------------------------------------------- bankName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BANK_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BANK_NAME} attribute.
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BANK_NAME} attribute with the specified value.
     *
     * @param bankName new value for {@value #ATTRIBUTE_NAME_BANK_NAME} attribute.
     */
    protected void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    // ---------------------------------------------------------------------------------------------------- businessType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_TYPE} attribute.
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_TYPE} attribute with the specified value.
     *
     * @param businessType new value for {@value #ATTRIBUTE_NAME_BUSINESS_TYPE} attribute.
     */
    protected void setBusinessType(final String businessType) {
        this.businessType = businessType;
    }

    // ------------------------------------------------------------------------------------------------------ yearOpened

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR_OPENED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR_OPENED} attribute.
     */
    public Integer getYearOpened() {
        return yearOpened;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR_OPENED} attribute with the specified value.
     *
     * @param yearOpened new value for {@value #ATTRIBUTE_NAME_YEAR_OPENED} attribute.
     */
    protected void setYearOpened(final Integer yearOpened) {
        this.yearOpened = yearOpened;
    }

    // ------------------------------------------------------------------------------------------------------- specialty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIALTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIALTY} attribute.
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIALTY} attribute with the specified value.
     *
     * @param specialty new value for {@value #ATTRIBUTE_NAME_SPECIALTY} attribute.
     */
    protected void setSpecialty(final String specialty) {
        this.specialty = specialty;
    }

    // ------------------------------------------------------------------------------------------------------ squareFeet

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SQUARE_FEET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SQUARE_FEET} attribute.
     */
    public Integer getSquareFeet() {
        return squareFeet;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SQUARE_FEET} attribute with the specified value.
     *
     * @param squareFeet new value for {@value #ATTRIBUTE_NAME_SQUARE_FEET} attribute.
     */
    protected void setSquareFeet(final Integer squareFeet) {
        this.squareFeet = squareFeet;
    }

    // ---------------------------------------------------------------------------------------------------------- brands

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BRANDS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BRANDS} attribute.
     */
    public String getBrands() {
        return brands;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BRANDS} attribute with the specified value.
     *
     * @param brands new value for {@value #ATTRIBUTE_NAME_BRANDS} attribute.
     */
    protected void setBrands(final String brands) {
        this.brands = brands;
    }

    // -------------------------------------------------------------------------------------------------------- internet

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INTERNET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INTERNET} attribute.
     */
    public String getInternet() {
        return internet;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INTERNET} attribute with the specified value.
     *
     * @param internet new value for {@value #ATTRIBUTE_NAME_INTERNET} attribute.
     */
    protected void setInternet(final String internet) {
        this.internet = internet;
    }

    // ------------------------------------------------------------------------------------------------- numberEmployees

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_EMPLOYEES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_EMPLOYEES} attribute.
     */
    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_EMPLOYEES} attribute with the specified value.
     *
     * @param numberEmployees new value for {@value #ATTRIBUTE_NAME_NUMBER_EMPLOYEES} attribute.
     */
    protected void setNumberEmployees(final Integer numberEmployees) {
        this.numberEmployees = numberEmployees;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only but for an INSERT: SQL Server does not write this view, and EclipseLink requires a writable mapping
    // of every identifier column; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ANNUAL_SALES,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private BigDecimal annualSales;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ANNUAL_REVENUE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private BigDecimal annualRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_BANK_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BANK_NAME
    )
    private String bankName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_BUSINESS_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BUSINESS_TYPE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BUSINESS_TYPE
    )
    private String businessType;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR_OPENED,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer yearOpened;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SPECIALTY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SPECIALTY,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SPECIALTY
    )
    private String specialty;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SQUARE_FEET,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer squareFeet;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_BRANDS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BRANDS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BRANDS
    )
    private String brands;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_INTERNET)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNET,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_INTERNET
    )
    private String internet;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_EMPLOYEES,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer numberEmployees;
}

