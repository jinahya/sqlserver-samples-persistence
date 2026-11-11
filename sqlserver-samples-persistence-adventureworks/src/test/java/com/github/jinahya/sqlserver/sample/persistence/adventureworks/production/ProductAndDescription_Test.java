package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductAndDescriptionId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * A class for testing the {@link ProductAndDescription} entity class.
 */
class ProductAndDescription_Test
        extends _Entity_Test<ProductAndDescription, ProductAndDescriptionId> {

    ProductAndDescription_Test() {
        super(ProductAndDescription.class, ProductAndDescriptionId.class);
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
     * @implNote {@link ProductAndDescription#equals(Object) equals} compares only the {@code
     *         @Id} attributes, because every other attribute is mutable state.
     */
    @Override
    public SingleTypeEqualsVerifierApi<ProductAndDescription> equals_verifier_() {
        return super.equals_verifier_()
                .suppress(Warning.SURROGATE_KEY);
    }
}
