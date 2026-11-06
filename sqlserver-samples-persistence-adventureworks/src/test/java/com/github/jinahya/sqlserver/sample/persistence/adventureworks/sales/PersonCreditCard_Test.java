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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_Test;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.PersonCreditCardId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link PersonCreditCard} entity class.
 */
class PersonCreditCard_Test
        extends _Entity_Test<PersonCreditCard, PersonCreditCardId> {

    private static PersonCreditCardId id(final int businessEntityId, final int creditCardId) {
        final var id = new PersonCreditCardId();
        id.setBusinessEntityId(businessEntityId);
        id.setCreditCardId(creditCardId);
        return id;
    }

    // ---------------------------------------------------------------------------------------------
    PersonCreditCard_Test() {
        super(PersonCreditCard.class, PersonCreditCardId.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link PersonCreditCard#equals(Object) equals} compares only the
     *         {@code @EmbeddedId} {@code id_}, which persist assigns, because every other attribute
     *         is mutable state; {@link PersonCreditCard#hashCode() hashCode} is the same for every
     *         instance, so that it does not change when persist assigns the key. The field is given
     *         two keys of its class, which differ in every attribute.
     */
    @Override
    public SingleTypeEqualsVerifierApi<PersonCreditCard> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValuesForField(PersonCreditCard.ATTRIBUTE_NAME_ID_, id(1, 1), id(2, 2))
                .suppress(Warning.SURROGATE_KEY, Warning.STRICT_HASHCODE);
    }
}
