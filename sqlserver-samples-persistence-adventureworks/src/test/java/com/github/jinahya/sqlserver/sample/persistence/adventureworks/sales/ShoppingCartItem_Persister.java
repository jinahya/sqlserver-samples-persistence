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

import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link ShoppingCartItem}, of a new product.
 */
class ShoppingCartItem_Persister
        extends AbstractEntityPersister<ShoppingCartItem> {

    ShoppingCartItem_Persister() {
        super(ShoppingCartItem.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public ShoppingCartItem apply(final EntityManager entityManager, final ShoppingCartItem entityInstance) {
        final var product = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        // the product's identifier is the INSERT's; the flush is what assigns it
        entityManager.flush();
        entityInstance.setProduct(product);
        return super.apply(entityManager, entityInstance);
    }
}
