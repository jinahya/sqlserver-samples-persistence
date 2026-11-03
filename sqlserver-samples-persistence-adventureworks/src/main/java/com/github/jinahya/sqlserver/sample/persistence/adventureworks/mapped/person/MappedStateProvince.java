package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.StateProvince} table.
 * <p>
 * The {@value #COLUMN_NAME_COUNTRY_REGION_CODE}, {@value #COLUMN_NAME_TERRITORY_ID} columns are
 * mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Person.StateProvince}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StateProvinceID}</th>
 *     <td>Primary key for StateProvince records.</td></tr>
 * <tr><th scope="row">{@code StateProvinceCode}</th>
 *     <td>ISO standard state or province code.</td></tr>
 * <tr><th scope="row">{@code CountryRegionCode}</th>
 *     <td>ISO standard country or region code. Foreign key to
 *     CountryRegion.CountryRegionCode.</td></tr>
 * <tr><th scope="row">{@code IsOnlyStateProvinceFlag}</th>
 *     <td>0 = StateProvinceCode exists. 1 = StateProvinceCode unavailable, using
 *     CountryRegionCode.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>State or province description.</td></tr>
 * <tr><th scope="row">{@code TerritoryID}</th>
 *     <td>ID of the territory in which the state or province is located. Foreign key to
 *     SalesTerritory.SalesTerritoryID.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Person.StateProvince}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsOnlyStateProvinceFlag}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStateProvince.TABLE_SCHEMA,
        table = MappedStateProvince.TABLE_NAME,
        value = "State and province lookup table."
)
public abstract class MappedStateProvince {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StateProvince";

    // ----------------------------------------------------------------------------- StateProvinceID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_ID = "StateProvinceID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_STATE_PROVINCE_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_STATE_PROVINCE_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_ID = "stateProvinceId";

    // --------------------------------------------------------------------------- StateProvinceCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_CODE = "StateProvinceCode";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_CODE = "stateProvinceCode";

    // --------------------------------------------------------------------------- CountryRegionCode

    /**
     * The name of the {@code nvarchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION_CODE = "CountryRegionCode";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_REGION_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION_CODE = "countryRegionCode";

    // --------------------------------------------------------------------- IsOnlyStateProvinceFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG = "IsOnlyStateProvinceFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG =
            "isOnlyStateProvinceFlag";

    // ---------------------------------------------------------------------------------------- Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // --------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_TERRITORY_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_TERRITORY_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStateProvince() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stateProvinceId=" + stateProvinceId
               + ",stateProvinceCode=" + stateProvinceCode
               + ",countryRegionCode=" + countryRegionCode
               + ",isOnlyStateProvinceFlag=" + isOnlyStateProvinceFlag
               + ",name=" + name
               + ",territoryId=" + territoryId
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
        if (!(obj instanceof MappedStateProvince that)) {
            return false;
        }
        return Objects.equals(stateProvinceId, that.stateProvinceId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(stateProvinceId);
    }

    // ----------------------------------------------------------------------------- stateProvinceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    public Integer getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute with the
     * specified value.
     *
     * @param stateProvinceId new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    protected void setStateProvinceId(final Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    // --------------------------------------------------------------------------- stateProvinceCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute.
     */
    public String getStateProvinceCode() {
        return stateProvinceCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute with the
     * specified value.
     *
     * @param stateProvinceCode new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE}
     *                          attribute.
     */
    public void setStateProvinceCode(final String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }

    // --------------------------------------------------------------------------- countryRegionCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     */
    protected String getCountryRegionCode() {
        return countryRegionCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param countryRegionCode new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE}
     *                          attribute.
     */
    protected void setCountryRegionCode(final String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
    }

    // --------------------------------------------------------------------- isOnlyStateProvinceFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute.
     */
    public Boolean getIsOnlyStateProvinceFlag() {
        return isOnlyStateProvinceFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute with
     * the specified value.
     *
     * @param isOnlyStateProvinceFlag new value for
     *                                {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG}
     *                                attribute.
     */
    public void setIsOnlyStateProvinceFlag(final Boolean isOnlyStateProvinceFlag) {
        this.isOnlyStateProvinceFlag = isOnlyStateProvinceFlag;
    }

    // ---------------------------------------------------------------------------------------- name

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

    // --------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the
    // identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(ATTRIBUTE_MAX_STATE_PROVINCE_ID)
    @Min(ATTRIBUTE_MIN_STATE_PROVINCE_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STATE_PROVINCE_ID,
                          value = "Primary key for StateProvince records.")
    private Integer stateProvinceId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_CODE, nullable = false, insertable = true,
            updatable = true, length = COLUMN_LENGTH_STATE_PROVINCE_CODE)
    @MsDescription.Column(name = COLUMN_NAME_STATE_PROVINCE_CODE,
                          value = "ISO standard state or province code.")
    private String stateProvinceCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_REGION_CODE, nullable = false, insertable = false,
            updatable = false, length = COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @MsDescription.Column(name = COLUMN_NAME_COUNTRY_REGION_CODE,
                          value = "ISO standard country or region code. Foreign key to "
                                  + "CountryRegion.CountryRegionCode.")
    private String countryRegionCode;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG, nullable = false, insertable = true,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG,
                          value = "0 = StateProvinceCode exists. 1 = StateProvinceCode "
                                  + "unavailable, using CountryRegionCode.")
    @MsDefault(name = "DF_StateProvince_IsOnlyStateProvinceFlag", value = "((1))")
    private Boolean isOnlyStateProvinceFlag;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME)
    @MsDescription.Column(name = COLUMN_NAME_NAME, value = "State or province description.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(ATTRIBUTE_MAX_TERRITORY_ID)
    @Min(ATTRIBUTE_MIN_TERRITORY_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TERRITORY_ID, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_TERRITORY_ID,
                          value = "ID of the territory in which the state or province is "
                                  + "located. Foreign key to SalesTerritory.SalesTerritoryID.")
    private Integer territoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ROWGUID,
                          value = "ROWGUIDCOL number uniquely identifying the record. Used to "
                                  + "support a merge replication sample.")
    @MsDefault(name = "DF_StateProvince_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_StateProvince_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

