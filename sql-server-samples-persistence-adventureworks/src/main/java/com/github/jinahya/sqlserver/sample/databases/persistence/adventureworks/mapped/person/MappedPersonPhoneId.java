package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person;

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

import java.io.Serializable;
import java.util.Objects;

/**
 * The identifier class of the {@code Person.PersonPhone} table, whose rows are identified by {@code BusinessEntityID},
 * {@code PhoneNumber}, {@code PhoneNumberTypeID}.
 * <p>
 * An entity which extends {@link MappedPersonPhone} names this class with {@link jakarta.persistence.IdClass @IdClass};
 * its attributes are the same as the mapped superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by
 * type.
 */
public class MappedPersonPhoneId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedPersonPhoneId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",phoneNumber=" + phoneNumber
               + ",phoneNumberTypeId=" + phoneNumberTypeId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedPersonPhoneId that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(phoneNumber, that.phoneNumber) &&
               Objects.equals(phoneNumberTypeId, that.phoneNumberTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessEntityId, phoneNumber, phoneNumberTypeId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

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

    // ----------------------------------------------------------------------------------------------------- phoneNumber

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

    // ----------------------------------------------------------------------------------------------- phoneNumberTypeId

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

    // -----------------------------------------------------------------------------------------------------------------
    private Integer businessEntityId;

    private String phoneNumber;

    private Integer phoneNumberTypeId;
}

