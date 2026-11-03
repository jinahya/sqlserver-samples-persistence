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

/**
 * A mapped superclass which holds the mappings of the {@code Application.Cities_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server rejects an INSERT (error 13559) and an UPDATE (error 13561) of a temporal history table; only
 * the system versioning of {@code Application.Cities} writes it.
 */
@MappedSuperclass
public abstract class MappedCityArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Cities_Archive";

    // -------------------------------------------------------------------------------------- CityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY_ID = "CityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY_ID = "cityId";

    // ------------------------------------------------------------------------------------ CityName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY_NAME = "CityName";

    /**
     * The length of the {@value #COLUMN_NAME_CITY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY_NAME = "cityName";

    // ----------------------------------------------------------------------------- StateProvinceID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_ID = "StateProvinceID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_ID = "stateProvinceId";

    // ------------------------------------------------------------------------------------ Location

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION = "Location";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

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
    protected MappedCityArchive() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "cityId=" + cityId
               + ",cityName=" + cityName
               + ",stateProvinceId=" + stateProvinceId
               + ",location=" + location
               + ",latestRecordedPopulation=" + latestRecordedPopulation
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // -------------------------------------------------------------------------------------- cityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY_ID} attribute.
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY_ID} attribute with the specified value.
     *
     * @param cityId new value for {@value #ATTRIBUTE_NAME_CITY_ID} attribute.
     */
    protected void setCityId(final Integer cityId) {
        this.cityId = cityId;
    }

    // ------------------------------------------------------------------------------------ cityName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY_NAME} attribute.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY_NAME} attribute with the specified value.
     *
     * @param cityName new value for {@value #ATTRIBUTE_NAME_CITY_NAME} attribute.
     */
    protected void setCityName(final String cityName) {
        this.cityName = cityName;
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

    // ------------------------------------------------------------------------------------ location

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute.
     */
    public Geography getLocation() {
        return location;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute with the specified value.
     *
     * @param location new value for {@value #ATTRIBUTE_NAME_LOCATION} attribute.
     */
    protected void setLocation(final Geography location) {
        this.location = location;
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
    @Column(name = COLUMN_NAME_CITY_ID, nullable = false, insertable = false, updatable = false)
    private Integer cityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_CITY_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CITY_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CITY_NAME)
    private String cityName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_ID, nullable = false, insertable = false, updatable = false)
    private Integer stateProvinceId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Convert(converter = GeographyAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOCATION, nullable = true, insertable = false, updatable = false)
    private Geography location;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATEST_RECORDED_POPULATION, nullable = true, insertable = false, updatable = false)
    private Long latestRecordedPopulation;

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

