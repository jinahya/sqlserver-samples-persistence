package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.application;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Application.Countries} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Application.Countries_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_LAST_EDITED_BY} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedCountry {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Application";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Countries";

    // ------------------------------------------------------------------------------------------------------- CountryID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.CountryID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "CountryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_NAME} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_FORMAL_NAME} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISO_ALPHA_3_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ISO_ALPHA_3_CODE = "isoAlpha3Code";

    // -------------------------------------------------------------------------------------------------- IsoNumericCode

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ISO_NUMERIC_CODE = "IsoNumericCode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ISO_NUMERIC_CODE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_TYPE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTINENT} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUBREGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBREGION = "subregion";

    // ---------------------------------------------------------------------------------------------------------- Border

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BORDER = "Border";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BORDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BORDER = "border";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCountry() {
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
        if (!(obj instanceof MappedCountry that)) {
            return false;
        }
        return Objects.equals(countryId, that.countryId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(countryId);
    }

    // ------------------------------------------------------------------------------------------------------- countryId

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

    // ----------------------------------------------------------------------------------------------------- countryName

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
    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    // ------------------------------------------------------------------------------------------------------ formalName

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
    public void setFormalName(final String formalName) {
        this.formalName = formalName;
    }

    // --------------------------------------------------------------------------------------------------- isoAlpha3Code

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
    public void setIsoAlpha3Code(final String isoAlpha3Code) {
        this.isoAlpha3Code = isoAlpha3Code;
    }

    // -------------------------------------------------------------------------------------------------- isoNumericCode

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
    public void setIsoNumericCode(final Integer isoNumericCode) {
        this.isoNumericCode = isoNumericCode;
    }

    // ----------------------------------------------------------------------------------------------------- countryType

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
    public void setCountryType(final String countryType) {
        this.countryType = countryType;
    }

    // ---------------------------------------------------------------------------------------- latestRecordedPopulation

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

    // ------------------------------------------------------------------------------------------------------- continent

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
    public void setContinent(final String continent) {
        this.continent = continent;
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
    public void setRegion(final String region) {
        this.region = region;
    }

    // ------------------------------------------------------------------------------------------------------- subregion

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
    public void setSubregion(final String subregion) {
        this.subregion = subregion;
    }

    // ---------------------------------------------------------------------------------------------------------- border

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BORDER} attribute.
     */
    public byte[] getBorder() {
        return border;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BORDER} attribute with the specified value.
     *
     * @param border new value for {@value #ATTRIBUTE_NAME_BORDER} attribute.
     */
    public void setBorder(final byte[] border) {
        this.border = border;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    public Integer getLastEditedBy() {
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

    // ------------------------------------------------------------------------------------------------------- validFrom

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

    // --------------------------------------------------------------------------------------------------------- validTo

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

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.CountryID", schema = "Sequences",
                       sequenceName = "CountryID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.CountryID")
    @Column(name = COLUMN_NAME_COUNTRY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer countryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COUNTRY_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_NAME
    )
    private String countryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FORMAL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FORMAL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FORMAL_NAME
    )
    private String formalName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ISO_ALPHA_3_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ISO_ALPHA_3_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ISO_ALPHA_3_CODE
    )
    private String isoAlpha3Code;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ISO_NUMERIC_CODE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer isoNumericCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COUNTRY_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_TYPE
    )
    private String countryType;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATEST_RECORDED_POPULATION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Long latestRecordedPopulation;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CONTINENT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CONTINENT,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CONTINENT
    )
    private String continent;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REGION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REGION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REGION
    )
    private String region;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUBREGION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUBREGION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUBREGION
    )
    private String subregion;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BORDER,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] border;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validTo;
}

