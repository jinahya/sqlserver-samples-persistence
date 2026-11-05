package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedAddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Person.AddressType} table.
 */
@Entity(name = "PersonAddressType")
@Table(schema = MappedAddressType.TABLE_SCHEMA,
       name = MappedAddressType.TABLE_NAME,
       comment = "Types of addresses stored in the Address table. ",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_AddressType_Name",
                       columnNames = {
                               AddressType.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_AddressType_rowguid",
                       columnNames = {
                               AddressType.COLUMN_NAME_ROWGUID
                       }
               )
       }
)
public class AddressType
        extends MappedAddressType {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected AddressType() {
        super();
    }
}

