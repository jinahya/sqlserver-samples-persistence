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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedDepartment;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code HumanResources.Department} table.
 */
@Entity(name = "HumanResourcesDepartment")
@Table(schema = MappedDepartment.TABLE_SCHEMA,
       name = MappedDepartment.TABLE_NAME,
       comment = "Lookup table containing the departments within the Adventure Works Cycles "
                 + "company.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Department_Name",
                       columnNames = {
                               Department.COLUMN_NAME_NAME
                       }
               )
       }
)
public class Department
        extends MappedDepartment {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Department() {
        super();
    }
}

