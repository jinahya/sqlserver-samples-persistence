package com.github.jinahya.sqlserver.sample.persistence.adventureworks;

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

import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * This module's {@link com.github.jinahya.sqlserver.sample.persistence.shared._Entity_Test}, which
 * gives EqualsVerifier two {@code XMLGregorianCalendar}s, which the classes binding the typed
 * {@code xml} columns hold.
 *
 * @param <T> the type of the entity.
 * @param <U> the type of the identifier of the entity: its id class, the type of its
 *            {@code @EmbeddedId}, or the type of its {@code @Id}.
 */
@SuppressWarnings({
        "java:S101", // Class names should comply with a naming convention
        "java:S118"  // Abstract class names should comply with a naming convention
})
public abstract class _Entity_Test<T, U>
        extends com.github.jinahya.sqlserver.sample.persistence.shared._Entity_Test<T, U> {

    /**
     * Creates a new instance for the specified entity class.
     *
     * @param targetClass the entity class to test.
     * @param idClass     the class of the identifier of the entity: its id class, the type of its
     *                    {@code @EmbeddedId}, or the type of its {@code @Id}.
     */
    protected _Entity_Test(final Class<T> targetClass, final Class<U> idClass) {
        super(targetClass, idClass);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    private static XMLGregorianCalendar calendar(final String lexicalRepresentation) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(lexicalRepresentation);
        } catch (final DatatypeConfigurationException dce) {
            throw new AssertionError("failed to create a datatype factory", dce);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @implNote The classes which bind the typed {@code xml} columns hold
     *         {@link XMLGregorianCalendar}s, an abstract class which EqualsVerifier cannot create,
     *         so it is given two.
     */
    @Override
    public SingleTypeEqualsVerifierApi<T> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValues(XMLGregorianCalendar.class,
                                  calendar("2001-01-01T00:00:00Z"),
                                  calendar("2002-02-02T00:00:00Z"));
    }
}
