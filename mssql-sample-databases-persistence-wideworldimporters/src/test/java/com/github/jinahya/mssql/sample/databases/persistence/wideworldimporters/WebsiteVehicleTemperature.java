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

import com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.MappedWebsiteVehicleTemperature;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Website.VehicleTemperatures} view.
 * <p>
 * A view is read, not written; the entity maps no association.
 */
@Entity
@Table(schema = MappedWebsiteVehicleTemperature.TABLE_SCHEMA, name = MappedWebsiteVehicleTemperature.TABLE_NAME)
public class WebsiteVehicleTemperature extends MappedWebsiteVehicleTemperature {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected WebsiteVehicleTemperature() {
        super();
    }
}

