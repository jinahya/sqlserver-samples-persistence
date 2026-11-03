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

import com.github.jinahya.object.randomizer.ObjectRandomizerUtils;
import com.github.jinahya.persistence.test.util.EntityPersisterUtils;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import io.github.jinahya.sqlserver.xml.schema.binding.b0fd0e.ProductDescription;
import io.github.jinahya.sqlserver.xml.schema.binding.b73cae.Root;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProductModel_PersistenceIT
        extends _Entity_PersistenceIT<ProductModel> {

    ProductModel_PersistenceIT() {
        super(ProductModel.class);
    }

    /**
     * Verifies that every installed {@code CatalogDescription} is read, through the converter, as a {@code ProductDescription}.
     */
    @DisplayName("every installed CatalogDescription is read as a ProductDescription")
    @Test
    void catalogDescription_Read_Installed() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var count = em.createQuery("SELECT COUNT(e) FROM ProductionProductModel e WHERE e.catalogDescription IS NOT NULL", Long.class)
                    .getSingleResult();
            final var values = em.createQuery("SELECT e.catalogDescription FROM ProductionProductModel e WHERE e.catalogDescription IS NOT NULL", ProductDescription.class)
                    .getResultList();
            assertThat(values).hasSize(count.intValue()).doesNotContainNull();
            return null;
        });
    }

    /**
     * Verifies that a new {@code ProductModel} with an installed {@code CatalogDescription} is persisted; the converter's text is what
     * the typed {@code xml} column takes.
     */
    @DisplayName("a new ProductModel with an installed CatalogDescription is persisted")
    @Test
    void catalogDescription_Persisted() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var installed = em.createQuery("SELECT e.catalogDescription FROM ProductionProductModel e WHERE e.catalogDescription IS NOT NULL", ProductDescription.class)
                    .setMaxResults(1)
                    .getSingleResult();
            final var instance = ObjectRandomizerUtils.newRandomizedInstanceOf(ProductModel.class).orElseThrow();
            instance.setCatalogDescription(installed);
            EntityPersisterUtils.newPersisterInstanceOf(ProductModel.class).orElseThrow().apply(em, instance);
            em.flush();
            em.clear();
            final var found = em.find(ProductModel.class, instance.getProductModelId());
            assertThat(found.getCatalogDescription()).isNotNull();
            return null;
        });
    }

    /**
     * Verifies that every installed {@code Instructions} is read, through the converter, as a {@code Root}.
     */
    @DisplayName("every installed Instructions is read as a Root")
    @Test
    void instructions_Read_Installed() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var count = em.createQuery("SELECT COUNT(e) FROM ProductionProductModel e WHERE e.instructions IS NOT NULL", Long.class)
                    .getSingleResult();
            final var values = em.createQuery("SELECT e.instructions FROM ProductionProductModel e WHERE e.instructions IS NOT NULL", Root.class)
                    .getResultList();
            assertThat(values).hasSize(count.intValue()).doesNotContainNull();
            return null;
        });
    }

    /**
     * Verifies that a new {@code ProductModel} with an installed {@code Instructions} is persisted; the converter's text is what the
     * typed {@code xml} column takes.
     */
    @DisplayName("a new ProductModel with an installed Instructions is persisted")
    @Test
    void instructions_Persisted() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var installed = em.createQuery("SELECT e.instructions FROM ProductionProductModel e WHERE e.instructions IS NOT NULL", Root.class)
                    .setMaxResults(1)
                    .getSingleResult();
            final var instance = ObjectRandomizerUtils.newRandomizedInstanceOf(ProductModel.class).orElseThrow();
            instance.setInstructions(installed);
            EntityPersisterUtils.newPersisterInstanceOf(ProductModel.class).orElseThrow().apply(em, instance);
            em.flush();
            em.clear();
            final var found = em.find(ProductModel.class, instance.getProductModelId());
            assertThat(found.getInstructions()).isEqualTo(installed);
            return null;
        });
    }
}
