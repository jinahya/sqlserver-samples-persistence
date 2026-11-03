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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedPersonCreditCardId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.PersonCreditCard} table.
 */
@Entity(name = "SalesPersonCreditCard")
@Table(schema = MappedPersonCreditCard.TABLE_SCHEMA, name = MappedPersonCreditCard.TABLE_NAME)
@IdClass(MappedPersonCreditCardId.class)
public class PersonCreditCard extends MappedPersonCreditCard {

    /**
     * The name of the attribute of the {@code FK_PersonCreditCard_Person_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_PersonCreditCard_CreditCard_CreditCardID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD = "creditCard";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PersonCreditCard() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Person} which the {@code FK_PersonCreditCard_Person_BusinessEntityID} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_PersonCreditCard_Person_BusinessEntityID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param businessEntity the {@code Person} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final Person businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------- creditCard

    /**
     * Returns the {@code CreditCard} which the {@code FK_PersonCreditCard_CreditCard_CreditCardID} foreign key
     * references.
     *
     * @return the {@code CreditCard} referenced; {@code null} when none is.
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Replaces the {@code CreditCard} which the {@code FK_PersonCreditCard_CreditCard_CreditCardID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param creditCard the {@code CreditCard} to reference; {@code null} for none.
     */
    public void setCreditCard(final CreditCard creditCard) {
        this.creditCard = creditCard;
        setCreditCardId(creditCard == null ? null : creditCard.getCreditCardId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Person businessEntity;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CREDIT_CARD_ID,
                referencedColumnName = MappedCreditCard.COLUMN_NAME_CREDIT_CARD_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private CreditCard creditCard;
}

