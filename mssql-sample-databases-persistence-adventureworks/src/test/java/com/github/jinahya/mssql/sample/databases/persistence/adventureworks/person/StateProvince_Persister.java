package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.person;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.sales.SalesTerritory;
import com.github.jinahya.persistence.test.util.AbstractEntityPersister;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import jakarta.persistence.EntityManager;

/**
 * Persists a new {@link StateProvince}, in a new country and a new sales territory.
 */
class StateProvince_Persister
        extends AbstractEntityPersister<StateProvince> {

    StateProvince_Persister() {
        super(StateProvince.class);
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public StateProvince apply(final EntityManager entityManager, final StateProvince entityInstance) {
        entityInstance.setCountryRegion(
                EntityPersisterUtils.newPersistedInstanceOf(entityManager, CountryRegion.class));
        final var territory = EntityPersisterUtils.newPersistedInstanceOf(entityManager, SalesTerritory.class);
        // the territory's identifier is the INSERT's; the flush is what assigns it
        entityManager.flush();
        entityInstance.setTerritory(territory);
        return super.apply(entityManager, entityInstance);
    }
}
