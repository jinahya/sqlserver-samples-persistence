package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Application.Countries_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedCountryArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Application";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Countries_Archive";

    // ------------------------------------------------------------------------------------------------------- CountryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "CountryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_ID = "countryId";

    // ----------------------------------------------------------------------------------------------------- CountryName

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_NAME = "CountryName";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_NAME = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_NAME = "countryName";

    // ------------------------------------------------------------------------------------------------------ FormalName

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FORMAL_NAME = "FormalName";

    /**
     * The length of the {@value #COLUMN_NAME_FORMAL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FORMAL_NAME = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FORMAL_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FORMAL_NAME = "formalName";

    // --------------------------------------------------------------------------------------------------- IsoAlpha3Code

    /**
     * The name of the {@code nvarchar(3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ISO_ALPHA_3_CODE = "IsoAlpha3Code";

    /**
     * The length of the {@value #COLUMN_NAME_ISO_ALPHA_3_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ISO_ALPHA_3_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISO_ALPHA_3_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ISO_ALPHA_3_CODE = "isoAlpha3Code";

    // -------------------------------------------------------------------------------------------------- IsoNumericCode

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ISO_NUMERIC_CODE = "IsoNumericCode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISO_NUMERIC_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ISO_NUMERIC_CODE = "isoNumericCode";

    // ----------------------------------------------------------------------------------------------------- CountryType

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_TYPE = "CountryType";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_TYPE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_TYPE = "countryType";

    // ---------------------------------------------------------------------------------------- LatestRecordedPopulation

    /**
     * The name of the {@code bigint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LATEST_RECORDED_POPULATION = "LatestRecordedPopulation";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATEST_RECORDED_POPULATION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION = "latestRecordedPopulation";

    // ------------------------------------------------------------------------------------------------------- Continent

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTINENT = "Continent";

    /**
     * The length of the {@value #COLUMN_NAME_CONTINENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONTINENT = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTINENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTINENT = "continent";

    // ---------------------------------------------------------------------------------------------------------- Region

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REGION = "Region";

    /**
     * The length of the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

    // ------------------------------------------------------------------------------------------------------- Subregion

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUBREGION = "Subregion";

    /**
     * The length of the {@value #COLUMN_NAME_SUBREGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUBREGION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUBREGION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBREGION = "subregion";

    // ---------------------------------------------------------------------------------------------------------- Border

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BORDER = "Border";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BORDER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BORDER = "border";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountryArchive() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
               + ",border=" + (this.border == null ? null : this.border.length + " bytes")
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // ------------------------------------------------------------------------------------------------------- countryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    @Nonnull
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute with the specified value.
     *
     * @param countryId new value for {@value #ATTRIBUTE_NAME_COUNTRY_ID} attribute.
     */
    protected void setCountryId(@Nonnull final Integer countryId) {
        this.countryId = countryId;
    }

    // ----------------------------------------------------------------------------------------------------- countryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    @Nonnull
    public String getCountryName() {
        return countryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute with the specified value.
     *
     * @param countryName new value for {@value #ATTRIBUTE_NAME_COUNTRY_NAME} attribute.
     */
    protected void setCountryName(@Nonnull final String countryName) {
        this.countryName = countryName;
    }

    // ------------------------------------------------------------------------------------------------------ formalName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute.
     */
    @Nonnull
    public String getFormalName() {
        return formalName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute with the specified value.
     *
     * @param formalName new value for {@value #ATTRIBUTE_NAME_FORMAL_NAME} attribute.
     */
    protected void setFormalName(@Nonnull final String formalName) {
        this.formalName = formalName;
    }

    // --------------------------------------------------------------------------------------------------- isoAlpha3Code

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute.
     */
    @Nullable
    public String getIsoAlpha3Code() {
        return isoAlpha3Code;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute with the specified value.
     *
     * @param isoAlpha3Code new value for {@value #ATTRIBUTE_NAME_ISO_ALPHA_3_CODE} attribute.
     */
    protected void setIsoAlpha3Code(@Nullable final String isoAlpha3Code) {
        this.isoAlpha3Code = isoAlpha3Code;
    }

    // -------------------------------------------------------------------------------------------------- isoNumericCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute.
     */
    @Nullable
    public Integer getIsoNumericCode() {
        return isoNumericCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute with the specified value.
     *
     * @param isoNumericCode new value for {@value #ATTRIBUTE_NAME_ISO_NUMERIC_CODE} attribute.
     */
    protected void setIsoNumericCode(@Nullable final Integer isoNumericCode) {
        this.isoNumericCode = isoNumericCode;
    }

    // ----------------------------------------------------------------------------------------------------- countryType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute.
     */
    @Nullable
    public String getCountryType() {
        return countryType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute with the specified value.
     *
     * @param countryType new value for {@value #ATTRIBUTE_NAME_COUNTRY_TYPE} attribute.
     */
    protected void setCountryType(@Nullable final String countryType) {
        this.countryType = countryType;
    }

    // ---------------------------------------------------------------------------------------- latestRecordedPopulation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute.
     */
    @Nullable
    public Long getLatestRecordedPopulation() {
        return latestRecordedPopulation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute with the specified value.
     *
     * @param latestRecordedPopulation new value for {@value #ATTRIBUTE_NAME_LATEST_RECORDED_POPULATION} attribute.
     */
    protected void setLatestRecordedPopulation(@Nullable final Long latestRecordedPopulation) {
        this.latestRecordedPopulation = latestRecordedPopulation;
    }

    // ------------------------------------------------------------------------------------------------------- continent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTINENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTINENT} attribute.
     */
    @Nonnull
    public String getContinent() {
        return continent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTINENT} attribute with the specified value.
     *
     * @param continent new value for {@value #ATTRIBUTE_NAME_CONTINENT} attribute.
     */
    protected void setContinent(@Nonnull final String continent) {
        this.continent = continent;
    }

    // ---------------------------------------------------------------------------------------------------------- region

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    @Nonnull
    public String getRegion() {
        return region;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION} attribute with the specified value.
     *
     * @param region new value for {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    protected void setRegion(@Nonnull final String region) {
        this.region = region;
    }

    // ------------------------------------------------------------------------------------------------------- subregion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUBREGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUBREGION} attribute.
     */
    @Nonnull
    public String getSubregion() {
        return subregion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUBREGION} attribute with the specified value.
     *
     * @param subregion new value for {@value #ATTRIBUTE_NAME_SUBREGION} attribute.
     */
    protected void setSubregion(@Nonnull final String subregion) {
        this.subregion = subregion;
    }

    // ---------------------------------------------------------------------------------------------------------- border

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BORDER} attribute.
     */
    @Nullable
    public byte[] getBorder() {
        return border;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BORDER} attribute with the specified value.
     *
     * @param border new value for {@value #ATTRIBUTE_NAME_BORDER} attribute.
     */
    protected void setBorder(@Nullable final byte[] border) {
        this.border = border;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    @Nonnull
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(@Nonnull final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    @Nonnull
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(@Nonnull final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // --------------------------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    @Nonnull
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(@Nonnull final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_COUNTRY_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_NAME
    )
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_FORMAL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FORMAL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FORMAL_NAME
    )
    private String formalName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ISO_ALPHA_3_CODE)
    @Column(name = COLUMN_NAME_ISO_ALPHA_3_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ISO_ALPHA_3_CODE
    )
    private String isoAlpha3Code;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ISO_NUMERIC_CODE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer isoNumericCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_COUNTRY_TYPE)
    @Column(name = COLUMN_NAME_COUNTRY_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_TYPE
    )
    private String countryType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_LATEST_RECORDED_POPULATION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long latestRecordedPopulation;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_CONTINENT)
    @NotNull
    @Column(name = COLUMN_NAME_CONTINENT,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CONTINENT
    )
    private String continent;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_REGION)
    @NotNull
    @Column(name = COLUMN_NAME_REGION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REGION
    )
    private String region;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_SUBREGION)
    @NotNull
    @Column(name = COLUMN_NAME_SUBREGION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUBREGION
    )
    private String subregion;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_BORDER,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] border;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime validTo;
}

