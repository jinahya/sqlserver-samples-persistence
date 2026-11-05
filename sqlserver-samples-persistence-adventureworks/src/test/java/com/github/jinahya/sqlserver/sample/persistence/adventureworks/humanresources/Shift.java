package com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedShift;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code HumanResources.Shift} table.
 */
@Entity(name = "HumanResourcesShift")
@Table(schema = MappedShift.TABLE_SCHEMA,
       name = MappedShift.TABLE_NAME,
       comment = "Work shift lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Shift_Name",
                       columnNames = {
                               Shift.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Shift_StartTime_EndTime",
                       columnNames = {
                               Shift.COLUMN_NAME_START_TIME,
                               Shift.COLUMN_NAME_END_TIME
                       }
               )
       }
)
public class Shift
        extends MappedShift {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Shift() {
        super();
    }
}

