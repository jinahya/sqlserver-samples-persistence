package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.person;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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
import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link Address}; the generated identifier, what SQL Server fills, and
 * the state or province, which the persister sets, are left out.
 */
class Address_Randomizer
        extends PodamObjectRandomizer<Address> {

    Address_Randomizer() {
        super(Address.class, List.of(
                Address.ATTRIBUTE_NAME_ADDRESS_ID,
                Address.ATTRIBUTE_NAME_STATE_PROVINCE_ID,
                Address.ATTRIBUTE_NAME_STATE_PROVINCE,
                Address.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a valid point as its spatial location, which Podam can not build.
     */
    @Override
    public Address get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        instance.setSpatialLocation(Geography.point(Geography.SRID_WGS84,
                                                    random.nextDouble(-90.0d, 90.0d),
                                                    random.nextDouble(-180.0d, 180.0d)));
        return instance;
    }
}
