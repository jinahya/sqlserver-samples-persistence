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
 * Persists a new {@link ProductProductPhoto}, of a new product and a new photo.
 */
class ProductProductPhoto_Persister
        extends AbstractEntityPersister<ProductProductPhoto> {

    ProductProductPhoto_Persister() {
        super(ProductProductPhoto.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public ProductProductPhoto apply(final EntityManager entityManager,
                                     final ProductProductPhoto entityInstance) {
        final var product =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        final var productPhoto =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, ProductPhoto.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        final var instance = ProductProductPhoto.of(product, productPhoto);
        instance.setPrimary(entityInstance.getPrimary());
        return super.apply(entityManager, instance);
    }
}
