package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.person;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person.MappedPerson;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person.MappedPersonPhone;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person.MappedPersonPhoneId;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person.MappedPhoneNumberType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Person.PersonPhone} table.
 */
@Entity(name = "PersonPersonPhone")
@Table(schema = MappedPersonPhone.TABLE_SCHEMA, name = MappedPersonPhone.TABLE_NAME)
@IdClass(MappedPersonPhoneId.class)
public class PersonPhone extends MappedPersonPhone {

    /**
     * The name of the attribute of the {@code FK_PersonPhone_Person_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_PersonPhone_PhoneNumberType_PhoneNumberTypeID} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER_TYPE = "phoneNumberType";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PersonPhone() {
        super();
    }

    // -------------------------------------------------------------------------------------------------- businessEntity

    /**
     * Returns the {@code Person} which the {@code FK_PersonPhone_Person_BusinessEntityID} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_PersonPhone_Person_BusinessEntityID} foreign key references, and
     * the value of the column it consists of.
     *
     * @param businessEntity the {@code Person} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final Person businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ------------------------------------------------------------------------------------------------- phoneNumberType

    /**
     * Returns the {@code PhoneNumberType} which the {@code FK_PersonPhone_PhoneNumberType_PhoneNumberTypeID} foreign
     * key references.
     *
     * @return the {@code PhoneNumberType} referenced; {@code null} when none is.
     */
    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    /**
     * Replaces the {@code PhoneNumberType} which the {@code FK_PersonPhone_PhoneNumberType_PhoneNumberTypeID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param phoneNumberType the {@code PhoneNumberType} to reference; {@code null} for none.
     */
    public void setPhoneNumberType(final PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
        setPhoneNumberTypeId(phoneNumberType == null ? null : phoneNumberType.getPhoneNumberTypeId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Person businessEntity;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PHONE_NUMBER_TYPE_ID,
                referencedColumnName = MappedPhoneNumberType.COLUMN_NAME_PHONE_NUMBER_TYPE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private PhoneNumberType phoneNumberType;
}

