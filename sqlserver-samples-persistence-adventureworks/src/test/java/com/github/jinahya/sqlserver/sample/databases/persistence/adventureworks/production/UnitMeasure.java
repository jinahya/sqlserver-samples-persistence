package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.production;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production.MappedUnitMeasure;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.UnitMeasure} table.
 */
@Entity(name = "ProductionUnitMeasure")
@Table(schema = MappedUnitMeasure.TABLE_SCHEMA, name = MappedUnitMeasure.TABLE_NAME)
public class UnitMeasure extends MappedUnitMeasure {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected UnitMeasure() {
        super();
    }
}

