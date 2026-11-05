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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductProductPhotoId;
import com.github.jinahya.sqlserver.sample.persistence.common.mapped.MappedWithEmbeddedId;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

/**
 * A class for testing the {@link ProductProductPhoto} entity class.
 */
class ProductProductPhoto_Test
        extends _Entity_Test<ProductProductPhoto, ProductProductPhotoId> {

    private static ProductProductPhotoId id(final int productId, final int productPhotoId) {
        final var id = new ProductProductPhotoId();
        id.setProductId(productId);
        id.setProductPhotoId(productPhotoId);
        return id;
    }

    // ---------------------------------------------------------------------------------------------
    ProductProductPhoto_Test() {
        super(ProductProductPhoto.class, ProductProductPhotoId.class);
    }

    // ----------------------------------------------------------------------------- equals/hashCode

    /**
     * {@inheritDoc}
     *
     * @implNote {@link ProductProductPhoto#equals(Object) equals} compares only the
     *         {@code @EmbeddedId} {@code id_}, which persist assigns, because every other attribute
     *         is mutable state; {@link ProductProductPhoto#hashCode() hashCode} is the same for
     *         every instance, so that it does not change when persist assigns the key. The key is a
     *         type variable of {@link MappedWithEmbeddedId}, which EqualsVerifier fills with an
     *         {@link Object}, so the field is given values of its actual class.
     */
    @Override
    public SingleTypeEqualsVerifierApi<ProductProductPhoto> equals_verifier_() {
        return super.equals_verifier_()
                .withPrefabValuesForField(ProductProductPhoto.ATTRIBUTE_NAME_ID_, id(1, 1), id(2, 2))
                .suppress(Warning.SURROGATE_KEY, Warning.STRICT_HASHCODE);
    }
}
