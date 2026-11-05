package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters._Entity_Test;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link Country} entity class.
 */
class Country_Test
        extends _Entity_Test<Country, Integer> {

    Country_Test() {
        super(Country.class, Integer.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link Country#equals(Object) equals} compares only the {@code @Id}, because every
     *         other attribute is mutable state, and only once it is assigned, which persist does;
     *         until then an instance equals only itself, and {@link Country#hashCode() hashCode} is
     *         the same for every instance.
     */
    @Override
    public SingleTypeEqualsVerifierApi<Country> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY, Warning.STRICT_HASHCODE,
                          Warning.IDENTICAL_COPY_FOR_VERSIONED_ENTITY);
    }
}
