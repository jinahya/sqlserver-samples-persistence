package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCreditCard;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedPersonCreditCard;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.PersonCreditCardId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity which maps the {@code Sales.PersonCreditCard} table.
 * <p>
 * Each attribute of the embedded {@link PersonCreditCardId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "SalesPersonCreditCard")
@Table(schema = MappedPersonCreditCard.TABLE_SCHEMA, name = MappedPersonCreditCard.TABLE_NAME,
       comment = "Cross-reference table mapping people to their credit card information in the "
                 + "CreditCard table. ")
public class PersonCreditCard
        extends MappedPersonCreditCard {

    /**
     * The name of the attribute of the {@code FK_PersonCreditCard_Person_BusinessEntityID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_PersonCreditCard_CreditCard_CreditCardID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD = "creditCard";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code businessEntity} and {@code creditCard}.
     *
     * @param businessEntity the {@code Person} to reference.
     * @param creditCard     the {@code CreditCard} to reference.
     * @return a new instance which references {@code businessEntity} and {@code creditCard}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static PersonCreditCard of(final Person businessEntity,
                                      final CreditCard creditCard) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        Objects.requireNonNull(creditCard, "creditCard is null");
        final var instance = new PersonCreditCard();
        instance.businessEntity = businessEntity;
        instance.creditCard = creditCard;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PersonCreditCard() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected PersonCreditCardId getId_() {
        return id_;
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Person} which the {@code FK_PersonCreditCard_Person_BusinessEntityID}
     * foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getBusinessEntity() {
        return businessEntity;
    }

    // ---------------------------------------------------------------------------------- creditCard

    /**
     * Returns the {@code CreditCard} which the {@code FK_PersonCreditCard_CreditCard_CreditCardID}
     * foreign key references.
     *
     * @return the {@code CreditCard} referenced; {@code null} when none is.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private PersonCreditCardId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(PersonCreditCardId.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Person businessEntity;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(PersonCreditCardId.ATTRIBUTE_NAME_CREDIT_CARD_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CREDIT_CARD_ID,
                referencedColumnName = MappedCreditCard.COLUMN_NAME_CREDIT_CARD_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private CreditCard creditCard;
}

