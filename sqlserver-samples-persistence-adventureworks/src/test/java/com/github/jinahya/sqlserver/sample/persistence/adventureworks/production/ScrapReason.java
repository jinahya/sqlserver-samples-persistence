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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedScrapReason;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Production.ScrapReason} table.
 */
@Entity(name = "ProductionScrapReason")
@Table(schema = MappedScrapReason.TABLE_SCHEMA,
       name = MappedScrapReason.TABLE_NAME,
       comment = "Manufacturing failure reasons lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_ScrapReason_Name",
                       columnNames = {
                               ScrapReason.COLUMN_NAME_NAME
                       }
               )
       }
)
public class ScrapReason
        extends MappedScrapReason {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ScrapReason() {
        super();
    }
}

