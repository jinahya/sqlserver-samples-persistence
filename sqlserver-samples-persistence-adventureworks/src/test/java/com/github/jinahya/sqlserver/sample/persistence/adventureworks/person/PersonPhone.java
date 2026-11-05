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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPersonPhone;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPhoneNumberType;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.PersonPhoneId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Person.PersonPhone} table.
 */
@Entity(name = "PersonPersonPhone")
@Table(schema = MappedPersonPhone.TABLE_SCHEMA, name = MappedPersonPhone.TABLE_NAME,
       comment = "Telephone number and type of a person.")
@IdClass(PersonPhoneId.class)
public class PersonPhone
        extends MappedPersonPhone {

    /**
     * The name of the attribute of the {@code FK_PersonPhone_Person_BusinessEntityID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_PersonPhone_PhoneNumberType_PhoneNumberTypeID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER_TYPE = "phoneNumberType";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code businessEntity} and {@code phoneNumberType},
     * with {@code phoneNumber}.
     *
     * @param businessEntity  the {@code Person} to reference.
     * @param phoneNumber     the value of the {@value #COLUMN_NAME_PHONE_NUMBER} column.
     * @param phoneNumberType the {@code PhoneNumberType} to reference.
     * @return a new instance which references {@code businessEntity} and {@code phoneNumberType},
     *         with {@code phoneNumber}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static PersonPhone of(final Person businessEntity,
                                 final String phoneNumber,
                                 final PhoneNumberType phoneNumberType) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        Objects.requireNonNull(phoneNumber, "phoneNumber is null");
        Objects.requireNonNull(phoneNumberType, "phoneNumberType is null");
        final var instance = new PersonPhone();
        instance.setBusinessEntity(businessEntity);
        instance.setPhoneNumber(phoneNumber);
        instance.setPhoneNumberType(phoneNumberType);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PersonPhone() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Person} which the {@code FK_PersonPhone_Person_BusinessEntityID} foreign
     * key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_PersonPhone_Person_BusinessEntityID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code Person} to reference; {@code null} for none.
     */
    private void setBusinessEntity(final Person businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(
                Optional.ofNullable(businessEntity).map(Person::getBusinessEntityId).orElse(null));
    }

    // ----------------------------------------------------------------------------- phoneNumberType

    /**
     * Returns the {@code PhoneNumberType} which the
     * {@code FK_PersonPhone_PhoneNumberType_PhoneNumberTypeID} foreign key references.
     *
     * @return the {@code PhoneNumberType} referenced; {@code null} when none is.
     */
    public PhoneNumberType getPhoneNumberType() {
        return phoneNumberType;
    }

    /**
     * Replaces the {@code PhoneNumberType} which the
     * {@code FK_PersonPhone_PhoneNumberType_PhoneNumberTypeID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param phoneNumberType the {@code PhoneNumberType} to reference; {@code null} for none.
     */
    private void setPhoneNumberType(final PhoneNumberType phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
        setPhoneNumberTypeId(
                Optional.ofNullable(phoneNumberType).map(PhoneNumberType::getPhoneNumberTypeId)
                        .orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Person businessEntity;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PHONE_NUMBER_TYPE_ID,
                referencedColumnName = MappedPhoneNumberType.COLUMN_NAME_PHONE_NUMBER_TYPE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private PhoneNumberType phoneNumberType;
}

