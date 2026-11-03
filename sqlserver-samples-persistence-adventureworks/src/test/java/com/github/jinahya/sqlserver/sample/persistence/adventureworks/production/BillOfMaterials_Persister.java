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
 * Persists a new {@link BillOfMaterials}, of a new component in a new assembly, measured in a new unit.
 */
class BillOfMaterials_Persister
        extends AbstractEntityPersister<BillOfMaterials> {

    BillOfMaterials_Persister() {
        super(BillOfMaterials.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public BillOfMaterials apply(final EntityManager entityManager, final BillOfMaterials entityInstance) {
        final var productAssembly = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        final var component = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Product.class);
        final var unitMeasure = EntityPersisterUtils.newPersistedInstanceOf(entityManager, UnitMeasure.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setProductAssembly(productAssembly);
        entityInstance.setComponent(component);
        entityInstance.setUnitMeasure(unitMeasure);
        return super.apply(entityManager, entityInstance);
    }
}
