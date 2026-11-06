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
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link SalesPersonQuotaHistory}, of a new sales person.
 */
class SalesPersonQuotaHistory_Persister
        extends AbstractEntityPersister<SalesPersonQuotaHistory> {

    SalesPersonQuotaHistory_Persister() {
        super(SalesPersonQuotaHistory.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public SalesPersonQuotaHistory apply(final EntityManager entityManager,
                                         final SalesPersonQuotaHistory entityInstance) {
        final var salesPerson =
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesPerson.class);
        final var instance = SalesPersonQuotaHistory.of(salesPerson, __Random_TestUtils.dateTime());
        instance.setSalesQuota(entityInstance.getSalesQuota());
        return super.apply(entityManager, instance);
    }
}
