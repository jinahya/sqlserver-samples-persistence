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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesTerritory} table.
 * <p>
 * The {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column is mapped read-only, as a foreign key; the extending entity owns
 * the writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesTerritory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesTerritory";

    // ----------------------------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

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

    // ----------------------------------------------------------------------------------------------- CountryRegionCode

    /**
     * The name of the {@code nvarchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION_CODE = "CountryRegionCode";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_REGION_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION_CODE = "countryRegionCode";

    // ----------------------------------------------------------------------------------------------------------- Group

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROUP = "\"Group\"";

    /**
     * The length of the {@value #COLUMN_NAME_GROUP} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_GROUP = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROUP} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROUP = "group";

    // -------------------------------------------------------------------------------------------------------- SalesYTD

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_YTD = "SalesYTD";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SALES_YTD} column, as the {@code CK_SalesTerritory_SalesYTD} check
     * constraint ({@code [SalesYTD]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_SALES_YTD = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_YTD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_YTD = "salesYtd";

    // --------------------------------------------------------------------------------------------------- SalesLastYear

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_LAST_YEAR = "SalesLastYear";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SALES_LAST_YEAR} column, as the
     * {@code CK_SalesTerritory_SalesLastYear} check constraint ({@code [SalesLastYear]>=(0.00)}) requires. The value is
     * {@value}.
     */
    public static final String COLUMN_MIN_SALES_LAST_YEAR = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_LAST_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_LAST_YEAR = "salesLastYear";

    // --------------------------------------------------------------------------------------------------------- CostYTD

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COST_YTD = "CostYTD";

    /**
     * The minimum value of the {@value #COLUMN_NAME_COST_YTD} column, as the {@code CK_SalesTerritory_CostYTD} check
     * constraint ({@code [CostYTD]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_COST_YTD = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COST_YTD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COST_YTD = "costYtd";

    // ---------------------------------------------------------------------------------------------------- CostLastYear

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COST_LAST_YEAR = "CostLastYear";

    /**
     * The minimum value of the {@value #COLUMN_NAME_COST_LAST_YEAR} column, as the
     * {@code CK_SalesTerritory_CostLastYear} check constraint ({@code [CostLastYear]>=(0.00)}) requires. The value is
     * {@value}.
     */
    public static final String COLUMN_MIN_COST_LAST_YEAR = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COST_LAST_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COST_LAST_YEAR = "costLastYear";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesTerritory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "territoryId=" + territoryId
               + ",name=" + name
               + ",countryRegionCode=" + countryRegionCode
               + ",group=" + group
               + ",salesYtd=" + salesYtd
               + ",salesLastYear=" + salesLastYear
               + ",costYtd=" + costYtd
               + ",costLastYear=" + costLastYear
               + ",rowguid=" + rowguid
               + ",modifiedDate=" + modifiedDate
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
        if (!(obj instanceof MappedSalesTerritory that)) {
            return false;
        }
        return Objects.equals(territoryId, that.territoryId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(territoryId);
    }

    // ----------------------------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
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
    public void setName(final String name) {
        this.name = name;
    }

    // ----------------------------------------------------------------------------------------------- countryRegionCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     */
    public String getCountryRegionCode() {
        return countryRegionCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param countryRegionCode new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     */
    protected void setCountryRegionCode(final String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
    }

    // ----------------------------------------------------------------------------------------------------------- group

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROUP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROUP} attribute.
     */
    public String getGroup() {
        return group;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROUP} attribute with the specified value.
     *
     * @param group new value for {@value #ATTRIBUTE_NAME_GROUP} attribute.
     */
    public void setGroup(final String group) {
        this.group = group;
    }

    // -------------------------------------------------------------------------------------------------------- salesYtd

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     */
    public BigDecimal getSalesYtd() {
        return salesYtd;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute with the specified value.
     *
     * @param salesYtd new value for {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     */
    public void setSalesYtd(final BigDecimal salesYtd) {
        this.salesYtd = salesYtd;
    }

    // --------------------------------------------------------------------------------------------------- salesLastYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     */
    public BigDecimal getSalesLastYear() {
        return salesLastYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute with the specified value.
     *
     * @param salesLastYear new value for {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     */
    public void setSalesLastYear(final BigDecimal salesLastYear) {
        this.salesLastYear = salesLastYear;
    }

    // --------------------------------------------------------------------------------------------------------- costYtd

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COST_YTD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COST_YTD} attribute.
     */
    public BigDecimal getCostYtd() {
        return costYtd;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COST_YTD} attribute with the specified value.
     *
     * @param costYtd new value for {@value #ATTRIBUTE_NAME_COST_YTD} attribute.
     */
    public void setCostYtd(final BigDecimal costYtd) {
        this.costYtd = costYtd;
    }

    // ---------------------------------------------------------------------------------------------------- costLastYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COST_LAST_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COST_LAST_YEAR} attribute.
     */
    public BigDecimal getCostLastYear() {
        return costLastYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COST_LAST_YEAR} attribute with the specified value.
     *
     * @param costLastYear new value for {@value #ATTRIBUTE_NAME_COST_LAST_YEAR} attribute.
     */
    public void setCostLastYear(final BigDecimal costLastYear) {
        this.costLastYear = costLastYear;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer territoryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_REGION_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COUNTRY_REGION_CODE
    )
    private String countryRegionCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_GROUP)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GROUP,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_GROUP
    )
    private String group;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesTerritory_SalesYTD: ([SalesYTD]>=(0.00))
    @DecimalMin(COLUMN_MIN_SALES_YTD)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_YTD,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesYtd;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesTerritory_SalesLastYear: ([SalesLastYear]>=(0.00))
    @DecimalMin(COLUMN_MIN_SALES_LAST_YEAR)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_LAST_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesLastYear;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesTerritory_CostYTD: ([CostYTD]>=(0.00))
    @DecimalMin(COLUMN_MIN_COST_YTD)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COST_YTD,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal costYtd;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesTerritory_CostLastYear: ([CostLastYear]>=(0.00))
    @DecimalMin(COLUMN_MIN_COST_LAST_YEAR)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COST_LAST_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal costLastYear;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

