package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedReturnReason;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.ReturnReason} table.
 */
@Entity(name = "DboReturnReason")
@Table(schema = MappedReturnReason.TABLE_SCHEMA, name = MappedReturnReason.TABLE_NAME)
public class ReturnReason extends MappedReturnReason {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ReturnReason() {
        super();
    }
}

