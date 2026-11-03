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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.CountryRegion;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link SalesTerritory}, in a new country.
 */
class SalesTerritory_Persister
        extends AbstractEntityPersister<SalesTerritory> {

    SalesTerritory_Persister() {
        super(SalesTerritory.class);
    }

    // ---------------------------------------------------------------------------------------------
    @Override
    public SalesTerritory apply(final EntityManager entityManager,
                                final SalesTerritory entityInstance) {
        entityInstance.setCountryRegion(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, CountryRegion.class));
        return super.apply(entityManager, entityInstance);
    }
}
