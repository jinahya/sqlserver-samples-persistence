package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Sales.PersonCreditCard} table, whose rows are identified by
 * {@code BusinessEntityID}, {@code CreditCardID}.
 * <p>
 * {@link MappedPersonCreditCard} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class PersonCreditCardId {

    /**
     * The name of the attribute which maps the
     * {@value MappedPersonCreditCard#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    /**
     * The name of the attribute which maps the
     * {@value MappedPersonCreditCard#COLUMN_NAME_CREDIT_CARD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD_ID = "creditCardId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public PersonCreditCardId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",creditCardId=" + creditCardId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PersonCreditCardId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getCreditCardId(), that.getCreditCardId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getCreditCardId());
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

    // -------------------------------------------------------------------------------- creditCardId

    /**
     * Returns current value of the {@code creditCardId} attribute.
     *
     * @return current value of the {@code creditCardId} attribute.
     */
    public Integer getCreditCardId() {
        return creditCardId;
    }

    /**
     * Replaces current value of the {@code creditCardId} attribute with the specified value.
     *
     * @param creditCardId new value for the {@code creditCardId} attribute.
     */
    public void setCreditCardId(final Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code businessEntityId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedPersonCreditCard.COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false,
            insertable = true, updatable = false,
            comment = "Business entity identification number. Foreign key to "
                      + "Person.BusinessEntityID.")
    private Integer businessEntityId;

    /**
     * The value of the {@code creditCardId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedPersonCreditCard.COLUMN_NAME_CREDIT_CARD_ID, nullable = false,
            insertable = true, updatable = false,
            comment = "Credit card identification number. Foreign key to CreditCard.CreditCardID.")
    private Integer creditCardId;
}

