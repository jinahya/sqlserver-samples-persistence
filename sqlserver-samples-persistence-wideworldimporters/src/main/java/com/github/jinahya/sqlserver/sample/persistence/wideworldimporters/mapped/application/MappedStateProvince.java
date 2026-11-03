package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.persistence.common.Geography;
import com.github.jinahya.sqlserver.sample.persistence.common.GeographyAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * A mapped superclass which holds the mappings of the {@code Application.StateProvinces} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Application.StateProvinces_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_COUNTRY_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a foreign
 * key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Application.StateProvinces}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StateProvinceID}</th>
 *     <td>Numeric ID used for reference to a state or province within the database</td></tr>
 * <tr><th scope="row">{@code StateProvinceCode}</th>
 *     <td>Common code for this state or province (such as WA - Washington for the USA)</td></tr>
 * <tr><th scope="row">{@code StateProvinceName}</th>
 *     <td>Formal name of the state or province</td></tr>
 * <tr><th scope="row">{@code CountryID}</th><td>Country for this StateProvince</td></tr>
 * <tr><th scope="row">{@code SalesTerritory}</th>
 *     <td>Sales territory for this StateProvince</td></tr>
 * <tr><th scope="row">{@code Border}</th><td>Geographic boundary of the state or province</td></tr>
 * <tr><th scope="row">{@code LatestRecordedPopulation}</th>
 *     <td>Latest available population for the StateProvince</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Application.StateProvinces}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StateProvinceID}</th><td>{@code (NEXT VALUE FOR [Sequences].[StateProvinceID])}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStateProvince.TABLE_SCHEMA,
        table = MappedStateProvince.TABLE_NAME,
        value = "States or provinces that contain cities (including geographic location)"
)
@MsTemporal(
        historyTable = "Application.StateProvinces_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
public abstract class MappedStateProvince {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StateProvinces";

    // ----------------------------------------------------------------------------- StateProvinceID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.StateProvinceID, NOT NULL} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_ID = "stateProvinceId";

    // --------------------------------------------------------------------------- StateProvinceCode

    /**
     * The name of the {@code nvarchar(5), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_CODE = "StateProvinceCode";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE_CODE = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_CODE = "stateProvinceCode";

    // --------------------------------------------------------------------------- StateProvinceName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_NAME = "StateProvinceName";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_NAME = "stateProvinceName";

    // ----------------------------------------------------------------------------------- CountryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "CountryID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_COUNTRY_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_COUNTRY_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // ------------------------------------------------------------------------------ SalesTerritory

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_TERRITORY = "SalesTerritory";

    /**
     * The length of the {@value #COLUMN_NAME_SALES_TERRITORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SALES_TERRITORY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_TERRITORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_TERRITORY = "salesTerritory";

    // -------------------------------------------------------------------------------------- Border

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BORDER = "Border";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BORDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BORDER = "border";

    // -------------------------------------------------------------------- LatestRecordedPopulation

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LATEST_RECORDED_POPULATION = "LatestRecordedPopulation";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute, the minimum of the
     * {@code bigint} type. The value is {@value}.
     */
    public static final long ATTRIBUTE_MIN_LATEST_RECORDED_POPULATION = SqlServerConstants.BIGINT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute, the maximum of the
     * {@code bigint} type. The value is {@value}.
     */
    public static final long ATTRIBUTE_MAX_LATEST_RECORDED_POPULATION = SqlServerConstants.BIGINT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATEST_RECORDED_POPULATION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION = "latestRecordedPopulation";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LAST_EDITED_BY = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LAST_EDITED_BY = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ----------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

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
               + ",stateProvinceName=" + stateProvinceName
               + ",countryId=" + countryId
               + ",salesTerritory=" + salesTerritory
               + ",border=" + Optional.ofNullable(border).map(b -> b.getNumPoints() + " points").orElse(null)
               + ",latestRecordedPopulation=" + latestRecordedPopulation
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute with the specified value.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute with the specified value.
     *
     * @param stateProvinceCode new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute.
     */
    public void setStateProvinceCode(final String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }

    // --------------------------------------------------------------------------- stateProvinceName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     */
    public String getStateProvinceName() {
        return stateProvinceName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute with the specified value.
     *
     * @param stateProvinceName new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     */
    public void setStateProvinceName(final String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    // ----------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    protected Integer getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    protected void setCountryId(final Integer countryId) {
        this.countryId = countryId;
    }

    // ------------------------------------------------------------------------------ salesTerritory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute.
     */
    public String getSalesTerritory() {
        return salesTerritory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute with the specified value.
     *
     * @param salesTerritory new value for {@value #ATTRIBUTE_NAME_SALES_TERRITORY} attribute.
     */
    public void setSalesTerritory(final String salesTerritory) {
        this.salesTerritory = salesTerritory;
    }

    // -------------------------------------------------------------------------------------- border

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BORDER} attribute.
     */
    public Geography getBorder() {
        return border;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BORDER} attribute with the specified value.
     *
     * @param border new value for {@value #ATTRIBUTE_NAME_BORDER} attribute.
     */
    public void setBorder(final Geography border) {
        this.border = border;
    }

    // -------------------------------------------------------------------- latestRecordedPopulation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute.
     */
    public Long getLatestRecordedPopulation() {
        return latestRecordedPopulation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute with the specified
     * value.
     *
     * @param latestRecordedPopulation new value for {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute.
     */
    public void setLatestRecordedPopulation(final Long latestRecordedPopulation) {
        this.latestRecordedPopulation = latestRecordedPopulation;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ----------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // ------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Max(ATTRIBUTE_MAX_STATE_PROVINCE_ID)
    @Min(ATTRIBUTE_MIN_STATE_PROVINCE_ID)
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.StateProvinceID", schema = "Sequences",
                       sequenceName = "StateProvinceID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.StateProvinceID")
    @Column(name = COLUMN_NAME_STATE_PROVINCE_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STATE_PROVINCE_ID,
                          value = "Numeric ID used for reference to a state or province within "
                                  + "the database")
    @MsDefault(name = "DF_Application_StateProvinces_StateProvinceID",
               value = "(NEXT VALUE FOR [Sequences].[StateProvinceID])")
    private Integer stateProvinceId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STATE_PROVINCE_CODE)
    @MsDescription.Column(name = COLUMN_NAME_STATE_PROVINCE_CODE,
                          value = "Common code for this state or province (such as WA - "
                                  + "Washington for the USA)")
    private String stateProvinceCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STATE_PROVINCE_NAME)
    @MsDescription.Column(name = COLUMN_NAME_STATE_PROVINCE_NAME,
                          value = "Formal name of the state or province")
    private String stateProvinceName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_COUNTRY_ID)
    @Min(ATTRIBUTE_MIN_COUNTRY_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_COUNTRY_ID, value = "Country for this StateProvince")
    private Integer countryId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SALES_TERRITORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_TERRITORY, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_SALES_TERRITORY)
    @MsDescription.Column(name = COLUMN_NAME_SALES_TERRITORY,
                          value = "Sales territory for this StateProvince")
    private String salesTerritory;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = GeographyAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BORDER, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_BORDER,
                          value = "Geographic boundary of the state or province")
    private Geography border;

    // ---------------------------------------------------------------------------------------------
    @Max(ATTRIBUTE_MAX_LATEST_RECORDED_POPULATION)
    @Min(ATTRIBUTE_MIN_LATEST_RECORDED_POPULATION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATEST_RECORDED_POPULATION, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_LATEST_RECORDED_POPULATION,
                          value = "Latest available population for the StateProvince")
    private Long latestRecordedPopulation;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_LAST_EDITED_BY)
    @Min(ATTRIBUTE_MIN_LAST_EDITED_BY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

