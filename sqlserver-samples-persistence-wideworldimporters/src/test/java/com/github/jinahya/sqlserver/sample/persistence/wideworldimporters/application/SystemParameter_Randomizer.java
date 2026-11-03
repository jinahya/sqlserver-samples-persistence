package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application;

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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.common.Geography;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link SystemParameter}; the identifier, which a sequence generates, what it references, which the
 * persister sets, and what SQL Server fills are left out.
 */
class SystemParameter_Randomizer
        extends PodamObjectRandomizer<SystemParameter> {

    SystemParameter_Randomizer() {
        super(SystemParameter.class, List.of(
                SystemParameter.ATTRIBUTE_NAME_SYSTEM_PARAMETER_ID,
                SystemParameter.ATTRIBUTE_NAME_DELIVERY_CITY_ID,
                SystemParameter.ATTRIBUTE_NAME_DELIVERY_LOCATION,
                SystemParameter.ATTRIBUTE_NAME_POSTAL_CITY_ID,
                SystemParameter.ATTRIBUTE_NAME_LAST_EDITED_BY,
                SystemParameter.ATTRIBUTE_NAME_DELIVERY_CITY,
                SystemParameter.ATTRIBUTE_NAME_POSTAL_CITY,
                SystemParameter.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a point for each of its {@code geography} columns which takes no
     *         {@code NULL}.
     */
    @Override
    public SystemParameter get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        instance.setDeliveryLocation(Geography.point(Geography.SRID_WGS84, random.nextDouble(-90.0d, 90.0d),
                                         random.nextDouble(-180.0d, 180.0d)));
        return instance;
    }
}

