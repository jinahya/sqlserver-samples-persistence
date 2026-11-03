package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.PackageType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link OrderLine}, with a new instance of each entity it references.
 */
class OrderLine_Persister
        extends AbstractEntityPersister<OrderLine> {

    OrderLine_Persister() {
        super(OrderLine.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public OrderLine apply(final EntityManager entityManager, final OrderLine entityInstance) {
        final var order = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Order.class);
        final var stockItem = EntityPersisterUtils.newPersistedInstanceOf(entityManager, StockItem.class);
        final var packageType = EntityPersisterUtils.newPersistedInstanceOf(entityManager, PackageType.class);
        final var lastEditedByPerson = EntityPersisterUtils.newPersistedInstanceOf(entityManager, Person.class);
        entityInstance.setOrder(order);
        entityInstance.setStockItem(stockItem);
        entityInstance.setPackageType(packageType);
        entityInstance.setLastEditedByPerson(lastEditedByPerson);
        return super.apply(entityManager, entityInstance);
    }
}

