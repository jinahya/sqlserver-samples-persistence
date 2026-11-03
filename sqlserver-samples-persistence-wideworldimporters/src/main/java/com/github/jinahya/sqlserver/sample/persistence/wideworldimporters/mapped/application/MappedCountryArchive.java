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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A mapped superclass which holds the mappings of the {@code Application.Countries_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server rejects an INSERT (error 13559) and an UPDATE (error 13561) of a temporal history table; only
 * the system versioning of {@code Application.Countries} writes it.
 */
@MappedSuperclass
public abstract class MappedCountryArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Countries_Archive";

    // ----------------------------------------------------------------------------------- CountryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "CountryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // --------------------------------------------------------------------------------- CountryName

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "CountryName";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    // ---------------------------------------------------------------------------------- FormalName

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FORMAL_NAME = "FormalName";

    /**
     * The length of the {@value #COLUMN_NAME_FORMAL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FORMAL_NAME = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FORMAL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FORMAL_NAME = "formalName";

    // ------------------------------------------------------------------------------- IsoAlpha3Code

    /**
     * The name of the {@code nvarchar(3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ISO_ALPHA_3_CODE = "IsoAlpha3Code";

    /**
     * The length of the {@value #COLUMN_NAME_ISO_ALPHA_3_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ISO_ALPHA_3_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISO_ALPHA_3_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ISO_ALPHA_3_CODE = "isoAlpha3Code";

    // ------------------------------------------------------------------------------ IsoNumericCode

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ISO_NUMERIC_CODE = "IsoNumericCode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISO_NUMERIC_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ISO_NUMERIC_CODE = "isoNumericCode";

    // --------------------------------------------------------------------------------- CountryType

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_TYPE = "CountryType";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_TYPE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_TYPE = "countryType";

    // -------------------------------------------------------------------- LatestRecordedPopulation

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LATEST_RECORDED_POPULATION = "LatestRecordedPopulation";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATEST_RECORDED_POPULATION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION = "latestRecordedPopulation";

    // ----------------------------------------------------------------------------------- Continent

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTINENT = "Continent";

    /**
     * The length of the {@value #COLUMN_NAME_CONTINENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONTINENT = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTINENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTINENT = "continent";

    // -------------------------------------------------------------------------------------- Region

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REGION = "Region";

    /**
     * The length of the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ----------------------------------------------------------------------------------- Subregion

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUBREGION = "Subregion";

    /**
     * The length of the {@value #COLUMN_NAME_SUBREGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUBREGION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUBREGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBREGION = "subregion";

    // -------------------------------------------------------------------------------------- Border

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BORDER = "Border";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BORDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BORDER = "border";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ----------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
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
    protected MappedCountryArchive() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "countryId=" + countryId
               + ",countryName=" + countryName
               + ",formalName=" + formalName
               + ",isoAlpha3Code=" + isoAlpha3Code
               + ",isoNumericCode=" + isoNumericCode
               + ",countryType=" + countryType
               + ",latestRecordedPopulation=" + latestRecordedPopulation
               + ",continent=" + continent
               + ",region=" + region
               + ",subregion=" + subregion
               + ",border=" + Optional.ofNullable(border).map(b -> b.getNumPoints() + " points").orElse(null)
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // ----------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    protected void setCountryId(final Integer countryId) {
        this.countryId = countryId;
    }

    // --------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute with the specified value.
     *
     * @param countryName new value for {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    protected void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    // ---------------------------------------------------------------------------------- formalName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute.
     */
    public String getFormalName() {
        return formalName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute with the specified value.
     *
     * @param formalName new value for {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute.
     */
    protected void setFormalName(final String formalName) {
        this.formalName = formalName;
    }

    // ------------------------------------------------------------------------------- isoAlpha3Code

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute.
     */
    public String getIsoAlpha3Code() {
        return isoAlpha3Code;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute with the specified value.
     *
     * @param isoAlpha3Code new value for {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute.
     */
    protected void setIsoAlpha3Code(final String isoAlpha3Code) {
        this.isoAlpha3Code = isoAlpha3Code;
    }

    // ------------------------------------------------------------------------------ isoNumericCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute.
     */
    public Integer getIsoNumericCode() {
        return isoNumericCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute with the specified value.
     *
     * @param isoNumericCode new value for {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute.
     */
    protected void setIsoNumericCode(final Integer isoNumericCode) {
        this.isoNumericCode = isoNumericCode;
    }

    // --------------------------------------------------------------------------------- countryType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute.
     */
    public String getCountryType() {
        return countryType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute with the specified value.
     *
     * @param countryType new value for {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute.
     */
    protected void setCountryType(final String countryType) {
        this.countryType = countryType;
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
    protected void setLatestRecordedPopulation(final Long latestRecordedPopulation) {
        this.latestRecordedPopulation = latestRecordedPopulation;
    }

    // ----------------------------------------------------------------------------------- continent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTINENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTINENT} attribute.
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTINENT} attribute with the specified value.
     *
     * @param continent new value for {@value #ATTRIBUTE_NAME_CONTINENT} attribute.
     */
    protected void setContinent(final String continent) {
        this.continent = continent;
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

    // ----------------------------------------------------------------------------------- subregion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUBREGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUBREGION} attribute.
     */
    public String getSubregion() {
        return subregion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUBREGION} attribute with the specified value.
     *
     * @param subregion new value for {@value #ATTRIBUTE_NAME_SUBREGION} attribute.
     */
    protected void setSubregion(final String subregion) {
        this.subregion = subregion;
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
    protected void setBorder(final Geography border) {
        this.border = border;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_ID, nullable = false, insertable = false, updatable = false)
    private Integer countryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_COUNTRY_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_COUNTRY_NAME)
    private String countryName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_FORMAL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FORMAL_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_FORMAL_NAME)
    private String formalName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_ISO_ALPHA_3_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ISO_ALPHA_3_CODE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_ISO_ALPHA_3_CODE)
    private String isoAlpha3Code;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ISO_NUMERIC_CODE, nullable = true, insertable = false, updatable = false)
    private Integer isoNumericCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_COUNTRY_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_TYPE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_COUNTRY_TYPE)
    private String countryType;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATEST_RECORDED_POPULATION, nullable = true, insertable = false, updatable = false)
    private Long latestRecordedPopulation;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_CONTINENT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CONTINENT, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CONTINENT)
    private String continent;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_REGION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REGION, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_REGION)
    private String region;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_SUBREGION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUBREGION, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_SUBREGION)
    private String subregion;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Convert(converter = GeographyAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BORDER, nullable = true, insertable = false, updatable = false)
    private Geography border;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

