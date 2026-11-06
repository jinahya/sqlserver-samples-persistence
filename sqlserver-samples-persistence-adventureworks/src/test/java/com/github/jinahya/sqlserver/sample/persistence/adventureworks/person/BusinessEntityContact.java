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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.BusinessEntityContactId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedBusinessEntity;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedBusinessEntityContact;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedContactType;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPerson;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity which maps the {@code Person.BusinessEntityContact} table.
 * <p>
 * Each attribute of the embedded {@link BusinessEntityContactId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "PersonBusinessEntityContact")
@Table(schema = MappedBusinessEntityContact.TABLE_SCHEMA,
       name = MappedBusinessEntityContact.TABLE_NAME,
       comment = "Cross-reference table mapping stores, vendors, and employees to people",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_BusinessEntityContact_rowguid",
                       columnNames = {
                               BusinessEntityContact.COLUMN_NAME_ROWGUID
                       }
               )
       }
)
public class BusinessEntityContact
        extends MappedBusinessEntityContact {

    /**
     * The name of the attribute of the {@code FK_BusinessEntityContact_Person_PersonID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON = "person";

    /**
     * The name of the attribute of the {@code FK_BusinessEntityContact_ContactType_ContactTypeID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_TYPE = "contactType";

    /**
     * The name of the attribute of the
     * {@code FK_BusinessEntityContact_BusinessEntity_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references the specified business entity, person and contact
     * type.
     *
     * @param businessEntity the {@code BusinessEntity} to reference.
     * @param person         the {@code Person} to reference.
     * @param contactType    the {@code ContactType} to reference.
     * @return a new instance which references {@code businessEntity}, {@code person} and
     *         {@code contactType}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static BusinessEntityContact of(final BusinessEntity businessEntity, final Person person,
                                           final ContactType contactType) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        Objects.requireNonNull(person, "person is null");
        Objects.requireNonNull(contactType, "contactType is null");
        final var instance = new BusinessEntityContact();
        instance.businessEntity = businessEntity;
        instance.person = person;
        instance.contactType = contactType;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected BusinessEntityContact() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected BusinessEntityContactId getId_() {
        return id_;
    }

    // -------------------------------------------------------------------------------------- person

    /**
     * Returns the {@code Person} which the {@code FK_BusinessEntityContact_Person_PersonID} foreign
     * key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPerson() {
        return person;
    }

    // --------------------------------------------------------------------------------- contactType

    /**
     * Returns the {@code ContactType} which the
     * {@code FK_BusinessEntityContact_ContactType_ContactTypeID} foreign key references.
     *
     * @return the {@code ContactType} referenced; {@code null} when none is.
     */
    public ContactType getContactType() {
        return contactType;
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code BusinessEntity} which the
     * {@code FK_BusinessEntityContact_BusinessEntity_BusinessEntityID} foreign key references.
     *
     * @return the {@code BusinessEntity} referenced; {@code null} when none is.
     */
    public BusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private BusinessEntityContactId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(BusinessEntityContactId.ATTRIBUTE_NAME_PERSON_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false
    )
    private Person person;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(BusinessEntityContactId.ATTRIBUTE_NAME_CONTACT_TYPE_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CONTACT_TYPE_ID,
                referencedColumnName = MappedContactType.COLUMN_NAME_CONTACT_TYPE_ID,
                nullable = false
    )
    private ContactType contactType;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(BusinessEntityContactId.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedBusinessEntity.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false
    )
    private BusinessEntity businessEntity;
}

