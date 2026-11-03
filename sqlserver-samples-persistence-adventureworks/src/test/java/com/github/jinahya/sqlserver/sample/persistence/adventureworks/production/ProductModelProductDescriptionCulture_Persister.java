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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link ProductModelProductDescriptionCulture}, of a new product model, a new product description,
 * and a new culture.
 */
class ProductModelProductDescriptionCulture_Persister
        extends AbstractEntityPersister<ProductModelProductDescriptionCulture> {

    ProductModelProductDescriptionCulture_Persister() {
        super(ProductModelProductDescriptionCulture.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public ProductModelProductDescriptionCulture apply(final EntityManager entityManager,
                                                       final ProductModelProductDescriptionCulture entityInstance) {
        final var productModel = EntityPersisterUtils.newPersistedInstanceOf(entityManager, ProductModel.class);
        final var productDescription =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, ProductDescription.class);
        final var culture = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Culture.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setProductModel(productModel);
        entityInstance.setProductDescription(productDescription);
        entityInstance.setCulture(culture);
        return super.apply(entityManager, entityInstance);
    }
}
