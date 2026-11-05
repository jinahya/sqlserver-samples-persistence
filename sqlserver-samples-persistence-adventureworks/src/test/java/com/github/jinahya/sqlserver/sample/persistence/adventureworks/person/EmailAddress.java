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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.EmailAddressId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedEmailAddress;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPerson;
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
 * An entity which maps the {@code Person.EmailAddress} table.
 */
@Entity(name = "PersonEmailAddress")
@Table(schema = MappedEmailAddress.TABLE_SCHEMA, name = MappedEmailAddress.TABLE_NAME,
       comment = "Where to send a person email.")
@IdClass(EmailAddressId.class)
public class EmailAddress
        extends MappedEmailAddress {

    /**
     * The name of the attribute of the {@code FK_EmailAddress_Person_BusinessEntityID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code businessEntity}.
     *
     * @param businessEntity the {@code Person} to reference.
     * @return a new instance which references {@code businessEntity}.
     * @throws NullPointerException if {@code businessEntity} is {@code null}.
     */
    public static EmailAddress of(final Person businessEntity) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        final var instance = new EmailAddress();
        instance.setBusinessEntity(businessEntity);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EmailAddress() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Person} which the {@code FK_EmailAddress_Person_BusinessEntityID} foreign
     * key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_EmailAddress_Person_BusinessEntityID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code Person} to reference; {@code null} for none.
     */
    private void setBusinessEntity(final Person businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(
                Optional.ofNullable(businessEntity).map(Person::getBusinessEntityId).orElse(null));
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
}

