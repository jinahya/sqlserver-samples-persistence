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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedSystemParameter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Application.SystemParameters} table.
 */
@Entity(name = "ApplicationSystemParameter")
@Table(schema = MappedSystemParameter.TABLE_SCHEMA, name = MappedSystemParameter.TABLE_NAME,
       comment = "Any configurable parameters for the whole system")
public class SystemParameter extends MappedSystemParameter {

    /**
     * The name of the attribute of the {@code FK_Application_SystemParameters_DeliveryCityID_Application_Cities}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_CITY = "deliveryCity";

    /**
     * The name of the attribute of the {@code FK_Application_SystemParameters_PostalCityID_Application_Cities} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CITY = "postalCity";

    /**
     * The name of the attribute of the {@code FK_Application_SystemParameters_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SystemParameter() {
        super();
    }

    // -------------------------------------------------------------------------------- deliveryCity

    /**
     * Returns the {@code City} which the {@code FK_Application_SystemParameters_DeliveryCityID_Application_Cities}
     * foreign key references.
     *
     * @return the {@code City} referenced; {@code null} when none is.
     */
    public City getDeliveryCity() {
        return deliveryCity;
    }

    /**
     * Replaces the {@code City} which the {@code FK_Application_SystemParameters_DeliveryCityID_Application_Cities}
     * foreign key references, and the value of the column it consists of.
     *
     * @param deliveryCity the {@code City} to reference; {@code null} for none.
     */
    public void setDeliveryCity(final City deliveryCity) {
        this.deliveryCity = deliveryCity;
        setDeliveryCityId(deliveryCity == null ? null : deliveryCity.getCityId());
    }

    // ---------------------------------------------------------------------------------- postalCity

    /**
     * Returns the {@code City} which the {@code FK_Application_SystemParameters_PostalCityID_Application_Cities}
     * foreign key references.
     *
     * @return the {@code City} referenced; {@code null} when none is.
     */
    public City getPostalCity() {
        return postalCity;
    }

    /**
     * Replaces the {@code City} which the {@code FK_Application_SystemParameters_PostalCityID_Application_Cities}
     * foreign key references, and the value of the column it consists of.
     *
     * @param postalCity the {@code City} to reference; {@code null} for none.
     */
    public void setPostalCity(final City postalCity) {
        this.postalCity = postalCity;
        setPostalCityId(postalCity == null ? null : postalCity.getCityId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Application_SystemParameters_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Application_SystemParameters_Application_People} foreign key
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
    @JoinColumn(name = COLUMN_NAME_DELIVERY_CITY_ID,
                referencedColumnName = MappedCity.COLUMN_NAME_CITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private City deliveryCity;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_POSTAL_CITY_ID,
                referencedColumnName = MappedCity.COLUMN_NAME_CITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private City postalCity;

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

