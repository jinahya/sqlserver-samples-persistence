package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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
 * Persists a new {@link PriceListDetail}, of a new price list and a new item.
 */
class PriceListDetail_Persister
        extends AbstractEntityPersister<PriceListDetail> {

    PriceListDetail_Persister() {
        super(PriceListDetail.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public PriceListDetail apply(final EntityManager entityManager, final PriceListDetail entityInstance) {
        final var priceList = EntityPersisterUtils.newPersistedInstanceOf(entityManager, PriceList.class);
        final var item = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Item.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setPriceList(priceList);
        entityInstance.setItem(item);
        return super.apply(entityManager, entityInstance);
    }
}

