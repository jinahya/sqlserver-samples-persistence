package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.website;

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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters._Entity_PersistenceIT;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class VehicleTemperature_PersistenceIT extends _Entity_PersistenceIT<VehicleTemperature, Long> {

    VehicleTemperature_PersistenceIT() {
        super(VehicleTemperature.class, Long.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Does nothing; {@code Website.VehicleTemperatures} is a view, and the entity is read-only.
     */
    @Disabled("read-only: Website.VehicleTemperatures is a view")
    @Override
    @Test
    protected void persistRandomizedEntityInstance__() {
        // empty
    }
}
