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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedCity;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedStateProvince;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Application.Cities} table.
 */
@Entity(name = "ApplicationCity")
@Table(schema = MappedCity.TABLE_SCHEMA, name = MappedCity.TABLE_NAME,
       comment = "Cities that are part of any address (including geographic location)")
public class City extends MappedCity {

    /**
     * The name of the attribute of the {@code FK_Application_Cities_StateProvinceID_Application_StateProvinces} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    /**
     * The name of the attribute of the {@code FK_Application_Cities_Application_People} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected City() {
        super();
    }

    // ------------------------------------------------------------------------------- stateProvince

    /**
     * Returns the {@code StateProvince} which the
     * {@code FK_Application_Cities_StateProvinceID_Application_StateProvinces} foreign key references.
     *
     * @return the {@code StateProvince} referenced; {@code null} when none is.
     */
    public StateProvince getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces the {@code StateProvince} which the
     * {@code FK_Application_Cities_StateProvinceID_Application_StateProvinces} foreign key references, and the value of
     * the column it consists of.
     *
     * @param stateProvince the {@code StateProvince} to reference; {@code null} for none.
     */
    public void setStateProvince(final StateProvince stateProvince) {
        this.stateProvince = stateProvince;
        setStateProvinceId(stateProvince == null ? null : stateProvince.getStateProvinceId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Application_Cities_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Application_Cities_Application_People} foreign key references,
     * and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
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

