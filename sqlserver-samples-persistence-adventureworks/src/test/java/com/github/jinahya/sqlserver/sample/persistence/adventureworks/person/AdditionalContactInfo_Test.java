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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_Test;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the {@link AdditionalContactInfo} entity class.
 */
class AdditionalContactInfo_Test
        extends _Entity_Test<AdditionalContactInfo, Integer> {

    AdditionalContactInfo_Test() {
        super(AdditionalContactInfo.class, Integer.class);
    }

    // ------------------------------------------------------------------------------------ toString

    @Disabled("a view: nothing is inserted into it; it has no randomizer")
    @Override
    @Test
    public void toString_NotBlank_NewRandomizedInstance() {
        // empty
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link AdditionalContactInfo#equals(Object) equals} compares only the {@code
     *         @Id}, because every other attribute is mutable state.
     */
    @Override
    public SingleTypeEqualsVerifierApi<AdditionalContactInfo> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY);
    }
}
