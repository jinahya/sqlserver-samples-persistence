package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.application;

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

import com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.application.MappedCountryArchive;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code Application.Countries_Archive} table, which no entity can map: a
 * history table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class CountryArchive extends MappedCountryArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected CountryArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param countryId                the value of the {@value #COLUMN_NAME_COUNTRY_ID} column.
     * @param countryName              the value of the {@value #COLUMN_NAME_COUNTRY_NAME} column.
     * @param formalName               the value of the {@value #COLUMN_NAME_FORMAL_NAME} column.
     * @param isoAlpha3Code            the value of the {@value #COLUMN_NAME_ISO_ALPHA_3_CODE} column.
     * @param isoNumericCode           the value of the {@value #COLUMN_NAME_ISO_NUMERIC_CODE} column.
     * @param countryType              the value of the {@value #COLUMN_NAME_COUNTRY_TYPE} column.
     * @param latestRecordedPopulation the value of the {@value #COLUMN_NAME_LATEST_RECORDED_POPULATION} column.
     * @param continent                the value of the {@value #COLUMN_NAME_CONTINENT} column.
     * @param region                   the value of the {@value #COLUMN_NAME_REGION} column.
     * @param subregion                the value of the {@value #COLUMN_NAME_SUBREGION} column.
     * @param border                   the value of the {@value #COLUMN_NAME_BORDER} column.
     * @param lastEditedBy             the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom                the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo                  the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public CountryArchive(final Integer countryId,
                          final String countryName,
                          final String formalName,
                          final String isoAlpha3Code,
                          final Integer isoNumericCode,
                          final String countryType,
                          final Long latestRecordedPopulation,
                          final String continent,
                          final String region,
                          final String subregion,
                          final byte[] border,
                          final Integer lastEditedBy,
                          final LocalDateTime validFrom,
                          final LocalDateTime validTo) {
        super();
        setCountryId(countryId);
        setCountryName(countryName);
        setFormalName(formalName);
        setIsoAlpha3Code(isoAlpha3Code);
        setIsoNumericCode(isoNumericCode);
        setCountryType(countryType);
        setLatestRecordedPopulation(latestRecordedPopulation);
        setContinent(continent);
        setRegion(region);
        setSubregion(subregion);
        setBorder(border);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

