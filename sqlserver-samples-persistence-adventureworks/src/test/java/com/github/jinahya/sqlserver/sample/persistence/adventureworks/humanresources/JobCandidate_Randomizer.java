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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link JobCandidate}; the generated identifier, what SQL Server fills, and the employee, which the
 * persister sets, are left out.
 */
class JobCandidate_Randomizer
        extends PodamObjectRandomizer<JobCandidate> {

    JobCandidate_Randomizer() {
        super(JobCandidate.class, List.of(
                JobCandidate.ATTRIBUTE_NAME_ID_,
                JobCandidate.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                JobCandidate.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                JobCandidate.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has no Resume, an xml column typed by a schema collection, which no random text
     *         is valid against.
     */
    @Override
    public JobCandidate get() {
        final var instance = super.get();
        instance.setResume(null);
        return instance;
    }
}
