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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.UnitMeasure;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link ProductVendor}, of a new product, from a new vendor, measured in a new
 * unit.
 */
class ProductVendor_Persister
        extends AbstractEntityPersister<ProductVendor> {

    ProductVendor_Persister() {
        super(ProductVendor.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public ProductVendor apply(final EntityManager entityManager,
                               final ProductVendor entityInstance) {
        final var product =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        final var vendor = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Vendor.class);
        final var unitMeasure =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, UnitMeasure.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        final var instance = ProductVendor.of(product, vendor);
        instance.setUnitMeasure(unitMeasure);
        instance.setAverageLeadTime(entityInstance.getAverageLeadTime());
        instance.setStandardPrice(entityInstance.getStandardPrice());
        instance.setLastReceiptCost(entityInstance.getLastReceiptCost());
        instance.setLastReceiptDate(entityInstance.getLastReceiptDate());
        instance.setMinOrderQty(entityInstance.getMinOrderQty());
        instance.setMaxOrderQty(entityInstance.getMaxOrderQty());
        instance.setOnOrderQty(entityInstance.getOnOrderQty());
        return super.apply(entityManager, instance);
    }
}
