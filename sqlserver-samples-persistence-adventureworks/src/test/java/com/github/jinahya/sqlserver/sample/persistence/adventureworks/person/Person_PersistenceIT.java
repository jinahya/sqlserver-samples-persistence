package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

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
import io.github.jinahya.sqlserver.xml.schema.binding.b9c1be.IndividualSurvey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class Person_PersistenceIT
        extends _Entity_PersistenceIT<Person, Integer> {

    Person_PersistenceIT() {
        super(Person.class, Integer.class, ___PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Verifies that every installed {@code AdditionalContactInfo} is read, through the converter,
     * as an {@code AdditionalContactInfo}.
     */
    @DisplayName("every installed AdditionalContactInfo is read as an AdditionalContactInfo")
    @Test
    void additionalContactInfo_Read_Installed() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var persons = em.createQuery(
                            """
                                    SELECT p
                                    FROM PersonPerson p
                                    WHERE p.additionalContactInfo IS NOT NULL""",
                            Person.class)
                    .getResultList();
            assertThat(persons).isNotEmpty().allSatisfy(p -> {
                assertThat(p.getAdditionalContactInfo()).as("the additional contact info of %s", p)
                        .isNotNull();
            });
            return null;
        }));
    }

    /**
     * Verifies that a new person with an installed {@code AdditionalContactInfo} is persisted; the
     * converter's text is what the typed {@code xml} column, of
     * {@code Person.AdditionalContactInfoSchemaCollection}, takes.
     */
    @DisplayName("a new person with an AdditionalContactInfo is persisted")
    @Test
    void additionalContactInfo_Persisted() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var installed = em.createQuery("""
                                                         SELECT p
                                                         FROM PersonPerson p
                                                         WHERE p.additionalContactInfo IS NOT NULL""",
                                                 Person.class)
                    .setMaxResults(1)
                    .getSingleResult()
                    .getAdditionalContactInfo();
            final var instance =
                    ObjectRandomizerUtils.newRandomizedInstanceOf(Person.class).orElseThrow();
            instance.setAdditionalContactInfo(installed);
            EntityPersisterUtils.newPersisterInstanceOf(Person.class).orElseThrow()
                    .apply(em, instance);
            em.flush();
            em.clear();
            final var found = em.find(Person.class, instance.getBusinessEntityId());
            assertThat(found.getAdditionalContactInfo()).isNotNull();
            return null;
        }));
    }

    /**
     * Verifies that every installed {@code Demographics} is read, through the converter, as an
     * {@code IndividualSurvey}.
     */
    @DisplayName("every installed Demographics is read as an IndividualSurvey")
    @Test
    void demographics_Read_Installed() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var count = em.createQuery("""
                                                     SELECT COUNT(p)
                                                     FROM PersonPerson p
                                                     WHERE p.demographics IS NOT NULL""",
                                             Long.class)
                    .getSingleResult();
            final var demographics = em.createQuery("""
                                                            SELECT p.demographics
                                                            FROM PersonPerson p
                                                            WHERE p.demographics IS NOT NULL""",
                                                    IndividualSurvey.class)
                    .getResultList();
            assertThat(demographics).hasSize(count.intValue()).doesNotContainNull();
            return null;
        }));
    }

    /**
     * Verifies that a new person with an installed {@code Demographics} is persisted; the
     * converter's text is what the typed {@code xml} column, of
     * {@code Person.IndividualSurveySchemaCollection}, takes.
     */
    @DisplayName("a new person with a Demographics is persisted")
    @Test
    void demographics_Persisted() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var installed = em.createQuery("""
                                                         SELECT p.demographics
                                                         FROM PersonPerson p
                                                         WHERE p.demographics IS NOT NULL""",
                                                 IndividualSurvey.class)
                    .setMaxResults(1)
                    .getSingleResult();
            final var instance =
                    ObjectRandomizerUtils.newRandomizedInstanceOf(Person.class).orElseThrow();
            instance.setDemographics(installed);
            EntityPersisterUtils.newPersisterInstanceOf(Person.class).orElseThrow()
                    .apply(em, instance);
            em.flush();
            em.clear();
            final var found = em.find(Person.class, instance.getBusinessEntityId());
            assertThat(found.getDemographics()).isNotNull();
            return null;
        }));
    }

    /**
     * Verifies that a new person persisted with no {@code Demographics} holds, right after the
     * flush and with no read of its own, the survey the {@code iuPerson} trigger wrote.
     */
    @DisplayName("the survey the iuPerson trigger writes is read back on persist")
    @Test
    void demographics_Generated_Persisted() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var instance =
                    ObjectRandomizerUtils.newRandomizedInstanceOf(Person.class).orElseThrow();
            instance.setDemographics(null);
            EntityPersisterUtils.newPersisterInstanceOf(Person.class).orElseThrow()
                    .apply(em, instance);
            em.flush();
            assertThat(instance.getDemographics()).isNotNull();
            return null;
        }));
    }
}

