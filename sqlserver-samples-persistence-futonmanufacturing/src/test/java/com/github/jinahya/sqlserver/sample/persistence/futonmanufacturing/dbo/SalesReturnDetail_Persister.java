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
 * Persists a new {@link SalesReturnDetail}, of a new return sales return, a new so detail and a new item.
 */
class SalesReturnDetail_Persister
        extends AbstractEntityPersister<SalesReturnDetail> {

    SalesReturnDetail_Persister() {
        super(SalesReturnDetail.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public SalesReturnDetail apply(final EntityManager entityManager, final SalesReturnDetail entityInstance) {
        final var returnSalesReturn = EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesReturn.class);
        final var soDetail = EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesOrderDetail.class);
        final var item = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Item.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        entityInstance.setReturnSalesReturn(returnSalesReturn);
        entityInstance.setSoDetail(soDetail);
        entityInstance.setItem(item);
        return super.apply(entityManager, entityInstance);
    }
}

