package com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.application;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.common.Geography;
import com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.application.MappedStateProvinceArchive;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A plain class which holds a row of the {@code Application.StateProvinces_Archive} table, which no entity can map: a
 * history table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class StateProvinceArchive extends MappedStateProvinceArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected StateProvinceArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param stateProvinceId          the value of the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column.
     * @param stateProvinceCode        the value of the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column.
     * @param stateProvinceName        the value of the {@value #COLUMN_NAME_STATE_PROVINCE_NAME} column.
     * @param countryId                the value of the {@value #COLUMN_NAME_COUNTRY_ID} column.
     * @param salesTerritory           the value of the {@value #COLUMN_NAME_SALES_TERRITORY} column.
     * @param border                   the value of the {@value #COLUMN_NAME_BORDER} column, in its binary form.
     * @param latestRecordedPopulation the value of the {@value #COLUMN_NAME_LATEST_RECORDED_POPULATION} column.
     * @param lastEditedBy             the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom                the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo                  the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public StateProvinceArchive(final Integer stateProvinceId,
                                final String stateProvinceCode,
                                final String stateProvinceName,
                                final Integer countryId,
                                final String salesTerritory,
                                final byte[] border,
                                final Long latestRecordedPopulation,
                                final Integer lastEditedBy,
                                final LocalDateTime validFrom,
                                final LocalDateTime validTo) {
        super();
        setStateProvinceId(stateProvinceId);
        setStateProvinceCode(stateProvinceCode);
        setStateProvinceName(stateProvinceName);
        setCountryId(countryId);
        setSalesTerritory(salesTerritory);
        setBorder(Optional.ofNullable(border).map(Geography::decode).orElse(null));
        setLatestRecordedPopulation(latestRecordedPopulation);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

