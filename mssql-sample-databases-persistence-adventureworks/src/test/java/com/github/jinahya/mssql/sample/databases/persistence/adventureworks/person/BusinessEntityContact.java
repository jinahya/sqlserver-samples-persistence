package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.person;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person.MappedBusinessEntity;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person.MappedBusinessEntityContact;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person.MappedBusinessEntityContactId;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person.MappedContactType;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.person.MappedPerson;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Person.BusinessEntityContact} table.
 */
@Entity(name = "PersonBusinessEntityContact")
@Table(schema = MappedBusinessEntityContact.TABLE_SCHEMA, name = MappedBusinessEntityContact.TABLE_NAME)
@IdClass(MappedBusinessEntityContactId.class)
public class BusinessEntityContact extends MappedBusinessEntityContact {

    /**
     * The name of the attribute of the {@code FK_BusinessEntityContact_Person_PersonID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON = "person";

    /**
     * The name of the attribute of the {@code FK_BusinessEntityContact_ContactType_ContactTypeID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_TYPE = "contactType";

    /**
     * The name of the attribute of the {@code FK_BusinessEntityContact_BusinessEntity_BusinessEntityID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected BusinessEntityContact() {
        super();
    }

    // ---------------------------------------------------------------------------------------------------------- person

    /**
     * Returns the {@code Person} which the {@code FK_BusinessEntityContact_Person_PersonID} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_BusinessEntityContact_Person_PersonID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param person the {@code Person} to reference; {@code null} for none.
     */
    public void setPerson(final Person person) {
        this.person = person;
        setPersonId(person == null ? null : person.getBusinessEntityId());
    }

    // ----------------------------------------------------------------------------------------------------- contactType

    /**
     * Returns the {@code ContactType} which the {@code FK_BusinessEntityContact_ContactType_ContactTypeID} foreign key
     * references.
     *
     * @return the {@code ContactType} referenced; {@code null} when none is.
     */
    public ContactType getContactType() {
        return contactType;
    }

    /**
     * Replaces the {@code ContactType} which the {@code FK_BusinessEntityContact_ContactType_ContactTypeID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param contactType the {@code ContactType} to reference; {@code null} for none.
     */
    public void setContactType(final ContactType contactType) {
        this.contactType = contactType;
        setContactTypeId(contactType == null ? null : contactType.getContactTypeId());
    }

    // -------------------------------------------------------------------------------------------------- businessEntity

    /**
     * Returns the {@code BusinessEntity} which the {@code FK_BusinessEntityContact_BusinessEntity_BusinessEntityID}
     * foreign key references.
     *
     * @return the {@code BusinessEntity} referenced; {@code null} when none is.
     */
    public BusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code BusinessEntity} which the {@code FK_BusinessEntityContact_BusinessEntity_BusinessEntityID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code BusinessEntity} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final BusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Person person;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CONTACT_TYPE_ID,
                referencedColumnName = MappedContactType.COLUMN_NAME_CONTACT_TYPE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private ContactType contactType;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedBusinessEntity.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private BusinessEntity businessEntity;
}

