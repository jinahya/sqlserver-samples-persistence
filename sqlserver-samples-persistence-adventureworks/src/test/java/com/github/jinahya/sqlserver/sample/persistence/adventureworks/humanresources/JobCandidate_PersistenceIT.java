package com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources;

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
import io.github.jinahya.sqlserver.xml.schema.binding.b3da3e.ResumeType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;

class JobCandidate_PersistenceIT
        extends _Entity_PersistenceIT<JobCandidate, Long> {

    JobCandidate_PersistenceIT() {
        super(JobCandidate.class, Long.class);
    }

    /**
     * Verifies that every installed {@code Resume} is read, through the converter, as a {@code ResumeType}.
     */
    @DisplayName("every installed Resume is read as a ResumeType")
    @Test
    void resume_Read_Installed() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var count = em.createQuery("SELECT COUNT(e) FROM HumanResourcesJobCandidate e WHERE e.resume IS NOT NULL", Long.class)
                    .getSingleResult();
            final var values = em.createQuery("SELECT e.resume FROM HumanResourcesJobCandidate e WHERE e.resume IS NOT NULL", ResumeType.class)
                    .getResultList();
            assertThat(values).hasSize(count.intValue()).doesNotContainNull();
            return null;
        }));
    }

    /**
     * Verifies that a new {@code JobCandidate} with an installed {@code Resume} is persisted; the converter's text is what
     * the typed {@code xml} column takes.
     */
    @DisplayName("a new JobCandidate with an installed Resume is persisted")
    @Test
    void resume_Persisted() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var installed = em.createQuery("SELECT e.resume FROM HumanResourcesJobCandidate e WHERE e.resume IS NOT NULL", ResumeType.class)
                    .setMaxResults(1)
                    .getSingleResult();
            final var instance = ObjectRandomizerUtils.newRandomizedInstanceOf(JobCandidate.class).orElseThrow();
            instance.setResume(installed);
            EntityPersisterUtils.newPersisterInstanceOf(JobCandidate.class).orElseThrow().apply(em, instance);
            em.flush();
            em.clear();
            final var found = em.find(JobCandidate.class, instance.getJobCandidateId());
            assertThat(found.getResume()).isNotNull();
            return null;
        }));
    }
}

