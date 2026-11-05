package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application;

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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedCountry;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedStateProvince;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Application.StateProvinces} table.
 */
@Entity(name = "ApplicationStateProvince")
@Table(schema = MappedStateProvince.TABLE_SCHEMA,
       name = MappedStateProvince.TABLE_NAME,
       comment = "States or provinces that contain cities (including geographic location)",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "UQ_Application_StateProvinces_StateProvinceName",
                       columnNames = {
                               StateProvince.COLUMN_NAME_STATE_PROVINCE_NAME
                       }
               )
       }
)
public class StateProvince extends MappedStateProvince {

    /**
     * The name of the attribute of the {@code FK_Application_StateProvinces_CountryID_Application_Countries} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    /**
     * The name of the attribute of the {@code FK_Application_StateProvinces_Application_People} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StateProvince() {
        super();
    }

    // ------------------------------------------------------------------------------------- country

    /**
     * Returns the {@code Country} which the {@code FK_Application_StateProvinces_CountryID_Application_Countries}
     * foreign key references.
     *
     * @return the {@code Country} referenced; {@code null} when none is.
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Replaces the {@code Country} which the {@code FK_Application_StateProvinces_CountryID_Application_Countries}
     * foreign key references, and the value of the column it consists of.
     *
     * @param country the {@code Country} to reference; {@code null} for none.
     */
    public void setCountry(final Country country) {
        this.country = country;
        setCountryId(country == null ? null : country.getCountryId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Application_StateProvinces_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Application_StateProvinces_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_ID,
                referencedColumnName = MappedCountry.COLUMN_NAME_COUNTRY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Country country;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LAST_EDITED_BY,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person lastEditedByPerson;
}

