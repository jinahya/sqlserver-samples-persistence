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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedAddress;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedStateProvince;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Person.Address} table.
 */
@Entity(name = "PersonAddress")
@Table(schema = MappedAddress.TABLE_SCHEMA,
       name = MappedAddress.TABLE_NAME,
       comment = "Street address information for customers, employees, and vendors.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Address_rowguid",
                       columnNames = {
                               Address.COLUMN_NAME_ROWGUID
                       }
               ),
               @UniqueConstraint(
                       name = "IX_Address_AddressLine1_AddressLine2_City_StateProvinceID_PostalCode",
                       columnNames = {
                               Address.COLUMN_NAME_ADDRESS_LINE_1,
                               Address.COLUMN_NAME_ADDRESS_LINE_2,
                               Address.COLUMN_NAME_CITY,
                               Address.COLUMN_NAME_STATE_PROVINCE_ID,
                               Address.COLUMN_NAME_POSTAL_CODE
                       }
               )
       }
)
public class Address
        extends MappedAddress {

    /**
     * The name of the attribute of the {@code FK_Address_StateProvince_StateProvinceID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Address() {
        super();
    }

    // ------------------------------------------------------------------------------- stateProvince

    /**
     * Returns the {@code StateProvince} which the {@code FK_Address_StateProvince_StateProvinceID}
     * foreign key references.
     *
     * @return the {@code StateProvince} referenced; {@code null} when none is.
     */
    public StateProvince getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces the {@code StateProvince} which the {@code FK_Address_StateProvince_StateProvinceID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param stateProvince the {@code StateProvince} to reference; {@code null} for none.
     */
    public void setStateProvince(final StateProvince stateProvince) {
        this.stateProvince = stateProvince;
        setStateProvinceId(stateProvince == null ? null : stateProvince.getStateProvinceId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STATE_PROVINCE_ID,
                referencedColumnName = MappedStateProvince.COLUMN_NAME_STATE_PROVINCE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private StateProvince stateProvince;
}

