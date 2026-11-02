package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Random_TestUtils;
import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link Location}; the generated identifier and what SQL Server fills are left out.
 */
class Location_Randomizer
        extends PodamObjectRandomizer<Location> {

    Location_Randomizer() {
        super(Location.class, List.of(
                Location.ATTRIBUTE_NAME_LOCATION_ID,
                Location.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has rates within their columns and CHECK constraints.
     */
    @Override
    public Location get() {
        final var instance = super.get();
        // CostRate, a smallmoney, and Availability, a decimal(8, 2), are both >= 0
        instance.setCostRate(__Random_TestUtils.positive(5, 4));
        instance.setAvailability(__Random_TestUtils.positive(
                Location.COLUMN_PRECISION_AVAILABILITY - Location.COLUMN_SCALE_AVAILABILITY,
                Location.COLUMN_SCALE_AVAILABILITY));
        return instance;
    }
}
