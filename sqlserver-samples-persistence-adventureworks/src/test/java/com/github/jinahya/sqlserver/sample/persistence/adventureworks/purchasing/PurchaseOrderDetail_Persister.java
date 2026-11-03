package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;


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
 * Persists a new {@link PurchaseOrderDetail}, of a new purchase order, for a new product.
 */
class PurchaseOrderDetail_Persister
        extends AbstractEntityPersister<PurchaseOrderDetail> {

    PurchaseOrderDetail_Persister() {
        super(PurchaseOrderDetail.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public PurchaseOrderDetail apply(final EntityManager entityManager,
                                     final PurchaseOrderDetail entityInstance) {
        final var purchaseOrder = EntityPersisterUtils.newPersistedInstanceOf(entityManager,
                                                                              PurchaseOrderHeader.class);
        final var product =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        final var instance = PurchaseOrderDetail.of(purchaseOrder);
        instance.setProduct(product);
        instance.setDueDate(entityInstance.getDueDate());
        instance.setOrderQty(entityInstance.getOrderQty());
        instance.setUnitPrice(entityInstance.getUnitPrice());
        instance.setReceivedQty(entityInstance.getReceivedQty());
        instance.setRejectedQty(entityInstance.getRejectedQty());
        return super.apply(entityManager, instance);
    }
}
