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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Person.BusinessEntityContact} table, whose rows are identified
 * by {@code BusinessEntityID}, {@code PersonID}, {@code ContactTypeID}.
 * <p>
 * {@link MappedBusinessEntityContact} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class BusinessEntityContactId {

    /**
     * The name of the attribute which maps the
     * {@value MappedBusinessEntityContact#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ---------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the
     * {@value MappedBusinessEntityContact#COLUMN_NAME_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON_ID = "personId";

    // ---------------------------------------------------------------------------------------------

    /**
     * The name of the attribute which maps the
     * {@value MappedBusinessEntityContact#COLUMN_NAME_CONTACT_TYPE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_TYPE_ID = "contactTypeId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public BusinessEntityContactId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",personId=" + personId
               + ",contactTypeId=" + contactTypeId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BusinessEntityContactId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getPersonId(), that.getPersonId()) &&
               Objects.equals(getContactTypeId(), that.getContactTypeId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getPersonId(), getContactTypeId());
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

    // ------------------------------------------------------------------------------------ personId

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

    // ------------------------------------------------------------------------------- contactTypeId

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

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedBusinessEntityContact.COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedBusinessEntityContact.COLUMN_NAME_BUSINESS_ENTITY_ID,
                          value = "Primary key. Foreign key to BusinessEntity.BusinessEntityID.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedBusinessEntityContact.COLUMN_NAME_PERSON_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedBusinessEntityContact.COLUMN_NAME_PERSON_ID,
                          value = "Primary key. Foreign key to Person.BusinessEntityID.")
    private Integer personId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedBusinessEntityContact.COLUMN_NAME_CONTACT_TYPE_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedBusinessEntityContact.COLUMN_NAME_CONTACT_TYPE_ID,
                          value = "Primary key.  Foreign key to ContactType.ContactTypeID.")
    private Integer contactTypeId;
}

