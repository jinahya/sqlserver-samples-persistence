package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters;

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

import com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.MappedCityArchive;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code Application.Cities_Archive} table, which no entity can map: a history
 * table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class CityArchive extends MappedCityArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected CityArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param cityId the value of the {@value #COLUMN_NAME_CITY_ID} column.
     * @param cityName the value of the {@value #COLUMN_NAME_CITY_NAME} column.
     * @param stateProvinceId the value of the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column.
     * @param location the value of the {@value #COLUMN_NAME_LOCATION} column.
     * @param latestRecordedPopulation the value of the {@value #COLUMN_NAME_LATEST_RECORDED_POPULATION} column.
     * @param lastEditedBy the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public CityArchive(final Integer cityId,
                       final String cityName,
                       final Integer stateProvinceId,
                       final byte[] location,
                       final Long latestRecordedPopulation,
                       final Integer lastEditedBy,
                       final LocalDateTime validFrom,
                       final LocalDateTime validTo) {
        super();
        setCityId(cityId);
        setCityName(cityName);
        setStateProvinceId(stateProvinceId);
        setLocation(location);
        setLatestRecordedPopulation(latestRecordedPopulation);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

