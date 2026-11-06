package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;

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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;

/**
 * Randomizes a new {@link ShipMethod}; the generated identifier and what SQL Server fills are left
 * out.
 */
class ShipMethod_Randomizer
        extends PodamObjectRandomizer<ShipMethod> {

    ShipMethod_Randomizer() {
        super(ShipMethod.class, List.of(
                ShipMethod.ATTRIBUTE_NAME_SHIP_METHOD_ID,
                ShipMethod.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has rates within their columns and CHECK
     *         constraints.
     */
    @Override
    public ShipMethod get() {
        final var instance = super.get();
        // ShipBase and ShipRate, both money, are both > 0
        instance.setShipBase(__Random_TestUtils.positive(5, 4));
        instance.setShipRate(__Random_TestUtils.positive(5, 4));
        return instance;
    }
}
