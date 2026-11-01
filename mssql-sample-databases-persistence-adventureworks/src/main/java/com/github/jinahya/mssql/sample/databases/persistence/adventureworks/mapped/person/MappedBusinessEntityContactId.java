package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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
 * The identifier class of the {@code Person.BusinessEntityContact} table, whose rows are identified by
 * {@code BusinessEntityID}, {@code PersonID}, {@code ContactTypeID}.
 * <p>
 * An entity which extends {@link MappedBusinessEntityContact} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedBusinessEntityContactId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedBusinessEntityContactId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",personId=" + personId
               + ",contactTypeId=" + contactTypeId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedBusinessEntityContactId that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(personId, that.personId) &&
               Objects.equals(contactTypeId, that.contactTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessEntityId, personId, contactTypeId);
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

    // -------------------------------------------------------------------------------------------------------- personId

    /**
     * Returns current value of the {@code personId} attribute.
     *
     * @return current value of the {@code personId} attribute.
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * Replaces current value of the {@code personId} attribute with the specified value.
     *
     * @param personId new value for the {@code personId} attribute.
     */
    public void setPersonId(final Integer personId) {
        this.personId = personId;
    }

    // --------------------------------------------------------------------------------------------------- contactTypeId

    /**
     * Returns current value of the {@code contactTypeId} attribute.
     *
     * @return current value of the {@code contactTypeId} attribute.
     */
    public Integer getContactTypeId() {
        return contactTypeId;
    }

    /**
     * Replaces current value of the {@code contactTypeId} attribute with the specified value.
     *
     * @param contactTypeId new value for the {@code contactTypeId} attribute.
     */
    public void setContactTypeId(final Integer contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer businessEntityId;

    private Integer personId;

    private Integer contactTypeId;
}

