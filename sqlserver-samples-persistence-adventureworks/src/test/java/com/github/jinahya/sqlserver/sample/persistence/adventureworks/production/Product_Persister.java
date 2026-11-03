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
 * Persists a new {@link Product}, in a new subcategory, of a new model, measured in new units.
 */
class Product_Persister
        extends AbstractEntityPersister<Product> {

    Product_Persister() {
        super(Product.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public Product apply(final EntityManager entityManager, final Product entityInstance) {
        final var productSubcategory =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, ProductSubcategory.class);
        final var productModel = EntityPersisterUtils.newPersistedInstanceOf(entityManager, ProductModel.class);
        final var sizeUnitMeasure = EntityPersisterUtils.newPersistedInstanceOf(entityManager, UnitMeasure.class);
        final var weightUnitMeasure = EntityPersisterUtils.newPersistedInstanceOf(entityManager, UnitMeasure.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setProductSubcategory(productSubcategory);
        entityInstance.setProductModel(productModel);
        entityInstance.setSizeUnitMeasure(sizeUnitMeasure);
        entityInstance.setWeightUnitMeasure(weightUnitMeasure);
        return super.apply(entityManager, entityInstance);
    }
}
