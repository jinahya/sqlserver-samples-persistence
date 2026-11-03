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
 * Persists a new {@link ProductInventory}, of a new product, in a new location.
 */
class ProductInventory_Persister
        extends AbstractEntityPersister<ProductInventory> {

    ProductInventory_Persister() {
        super(ProductInventory.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public ProductInventory apply(final EntityManager entityManager, final ProductInventory entityInstance) {
        final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        final var location = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Location.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setProduct(product);
        entityInstance.setLocation(location);
        return super.apply(entityManager, entityInstance);
    }
}
