package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse;

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

import java.util.List;

/**
 * Randomizes a new {@link ColdRoomTemperature}; the identifier, which a sequence generates, and what SQL Server fills
 * are left out.
 */
class ColdRoomTemperature_Randomizer
        extends PodamObjectRandomizer<ColdRoomTemperature> {

    ColdRoomTemperature_Randomizer() {
        super(ColdRoomTemperature.class, List.of(
                ColdRoomTemperature.ATTRIBUTE_NAME_COLD_ROOM_TEMPERATURE_ID,
                ColdRoomTemperature.ATTRIBUTE_NAME_VALID_FROM,
                ColdRoomTemperature.ATTRIBUTE_NAME_VALID_TO
        ));
    }
}

