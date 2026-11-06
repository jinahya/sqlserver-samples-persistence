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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.sqlserver.sample.persistence.shared._Entity_PersistenceIT;
import io.github.jinahya.sqlserver.xml.schema.binding.b95f5e.StoreSurvey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class Store_PersistenceIT
        extends _Entity_PersistenceIT<Store, Integer> {

    Store_PersistenceIT() {
        super(Store.class, Integer.class, ___PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    /**
     * Verifies that every installed {@code Demographics} is read, through the converter, as a {@code StoreSurvey}.
     */
    @DisplayName("every installed Demographics is read as a StoreSurvey")
    @Test
    void demographics_Read_Installed() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var count = em.createQuery("SELECT COUNT(e) FROM SalesStore e WHERE e.demographics IS NOT NULL", Long.class)
                    .getSingleResult();
            final var values = em.createQuery("SELECT e.demographics FROM SalesStore e WHERE e.demographics IS NOT NULL", StoreSurvey.class)
                    .getResultList();
            assertThat(values).hasSize(count.intValue()).doesNotContainNull();
            return null;
        }));
    }

    /**
     * Verifies that a new {@code Store} with an installed {@code Demographics} is persisted; the converter's text is what
     * the typed {@code xml} column takes.
     */
    @DisplayName("a new Store with an installed Demographics is persisted")
    @Test
    void demographics_Persisted() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var installed = em.createQuery("SELECT e.demographics FROM SalesStore e WHERE e.demographics IS NOT NULL", StoreSurvey.class)
                    .setMaxResults(1)
                    .getSingleResult();
            final var instance = ObjectRandomizerUtils.newRandomizedInstanceOf(Store.class).orElseThrow();
            instance.setDemographics(installed);
            EntityPersisterUtils.newPersisterInstanceOf(Store.class).orElseThrow().apply(em, instance);
            em.flush();
            em.clear();
            final var found = em.find(Store.class, instance.getBusinessEntityId());
            assertThat(found.getDemographics()).isNotNull();
            return null;
        }));
    }
}

