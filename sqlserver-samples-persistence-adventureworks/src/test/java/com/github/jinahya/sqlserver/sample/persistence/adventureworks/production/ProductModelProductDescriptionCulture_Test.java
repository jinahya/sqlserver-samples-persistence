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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductModelProductDescriptionCultureId;
import com.github.jinahya.sqlserver.sample.persistence.common.mapped.MappedWithEmbeddedId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link ProductModelProductDescriptionCulture} entity class.
 */
class ProductModelProductDescriptionCulture_Test
        extends _Entity_Test<ProductModelProductDescriptionCulture> {

    private static ProductModelProductDescriptionCultureId id(final int productModelId,
                                                              final int productDescriptionId,
                                                              final String cultureId) {
        final var id = new ProductModelProductDescriptionCultureId();
        id.setProductModelId(productModelId);
        id.setProductDescriptionId(productDescriptionId);
        id.setCultureId(cultureId);
        return id;
    }

    // ---------------------------------------------------------------------------------------------
    ProductModelProductDescriptionCulture_Test() {
        super(ProductModelProductDescriptionCulture.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link ProductModelProductDescriptionCulture#equals(Object) equals} compares only
     *         the {@code @EmbeddedId} {@code id}, which persist assigns, because every other
     *         attribute is mutable state; {@link ProductModelProductDescriptionCulture#hashCode()
     *         hashCode} is the same for every instance, so that it does not change when persist
     *         assigns the key. The key is a type variable of {@link MappedWithEmbeddedId}, which
     *         EqualsVerifier fills with an {@link Object}, so the field is given values of its
     *         actual class.
     */
    @Override
    public SingleTypeEqualsVerifierApi<ProductModelProductDescriptionCulture> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValuesForField(ProductModelProductDescriptionCulture.ATTRIBUTE_NAME_ID,
                        id(1, 1, "A"),
                        id(2, 2, "B"))
                .suppress(Warning.SURROGATE_KEY, Warning.STRICT_HASHCODE);
    }
}
