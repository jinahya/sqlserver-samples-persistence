package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;

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

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Person.PersonPhone} table, whose rows are identified by {@code BusinessEntityID},
 * {@code PhoneNumber}, {@code PhoneNumberTypeID}.
 * <p>
 * An entity which extends {@link MappedPersonPhone} names this class with {@link jakarta.persistence.IdClass @IdClass};
 * its attributes are the same as the mapped superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by
 * type.
 */
public class PersonPhoneId {

    /**
     * The name of the attribute which maps the
     * {@value MappedPersonPhone#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    /**
     * The name of the attribute which maps the
     * {@value MappedPersonPhone#COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    /**
     * The name of the attribute which maps the
     * {@value MappedPersonPhone#COLUMN_NAME_PHONE_NUMBER_TYPE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER_TYPE_ID = "phoneNumberTypeId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public PersonPhoneId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",phoneNumber=" + phoneNumber
               + ",phoneNumberTypeId=" + phoneNumberTypeId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonPhoneId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getPhoneNumber(), that.getPhoneNumber()) &&
               Objects.equals(getPhoneNumberTypeId(), that.getPhoneNumberTypeId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getPhoneNumber(), getPhoneNumberTypeId());
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of the {@code businessEntityId} attribute.
     *
     * @return current value of the {@code businessEntityId} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of the {@code businessEntityId} attribute with the specified value.
     *
     * @param businessEntityId new value for the {@code businessEntityId} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // --------------------------------------------------------------------------------- phoneNumber

    /**
     * Returns current value of the {@code phoneNumber} attribute.
     *
     * @return current value of the {@code phoneNumber} attribute.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Replaces current value of the {@code phoneNumber} attribute with the specified value.
     *
     * @param phoneNumber new value for the {@code phoneNumber} attribute.
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // --------------------------------------------------------------------------- phoneNumberTypeId

    /**
     * Returns current value of the {@code phoneNumberTypeId} attribute.
     *
     * @return current value of the {@code phoneNumberTypeId} attribute.
     */
    public Integer getPhoneNumberTypeId() {
        return phoneNumberTypeId;
    }

    /**
     * Replaces current value of the {@code phoneNumberTypeId} attribute with the specified value.
     *
     * @param phoneNumberTypeId new value for the {@code phoneNumberTypeId} attribute.
     */
    public void setPhoneNumberTypeId(final Integer phoneNumberTypeId) {
        this.phoneNumberTypeId = phoneNumberTypeId;
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * The value of the {@code businessEntityId} attribute.
     */
    @NotNull
    private Integer businessEntityId;

    /**
     * The value of the {@code phoneNumber} attribute.
     */
    @NotNull
    private String phoneNumber;

    /**
     * The value of the {@code phoneNumberTypeId} attribute.
     */
    @NotNull
    private Integer phoneNumberTypeId;
}

