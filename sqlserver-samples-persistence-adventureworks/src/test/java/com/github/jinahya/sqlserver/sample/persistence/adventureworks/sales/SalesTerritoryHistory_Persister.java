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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Random_TestUtils;
import jakarta.persistence.EntityManager;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Persists a new {@link SalesTerritoryHistory}, of a new sales person, in a new territory.
 */
class SalesTerritoryHistory_Persister
        extends AbstractEntityPersister<SalesTerritoryHistory> {

    SalesTerritoryHistory_Persister() {
        super(SalesTerritoryHistory.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public SalesTerritoryHistory apply(final EntityManager entityManager,
                                       final SalesTerritoryHistory entityInstance) {
        final var salesPerson =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesPerson.class);
        final var territory =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesTerritory.class);
        // the identifiers of the parents are the INSERTs'; the flush is what assigns them
        entityManager.flush();
        final var random = ThreadLocalRandom.current();
        // CK_SalesTerritoryHistory_EndDate: EndDate, when any, >= StartDate
        final var startDate = __Random_TestUtils.dateTime();
        final var instance = SalesTerritoryHistory.of(salesPerson, startDate, territory);
        instance.setEndDate(
                random.nextBoolean() ? null : startDate.plusDays(random.nextLong(366L)));
        return super.apply(entityManager, instance);
    }
}
