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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedJobCandidateEducationView;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code HumanResources.vJobCandidateEducation} view, which no entity can map:
 * the view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class JobCandidateEducationView extends MappedJobCandidateEducationView {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected JobCandidateEducationView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param jobCandidateId      the value of the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column.
     * @param eduLevel            the value of the {@value #COLUMN_NAME_EDU_LEVEL} column.
     * @param eduStartDate        the value of the {@value #COLUMN_NAME_EDU_START_DATE} column.
     * @param eduEndDate          the value of the {@value #COLUMN_NAME_EDU_END_DATE} column.
     * @param eduDegree           the value of the {@value #COLUMN_NAME_EDU_DEGREE} column.
     * @param eduMajor            the value of the {@value #COLUMN_NAME_EDU_MAJOR} column.
     * @param eduMinor            the value of the {@value #COLUMN_NAME_EDU_MINOR} column.
     * @param eduGpa              the value of the {@value #COLUMN_NAME_EDU_GPA} column.
     * @param eduGpaScale         the value of the {@value #COLUMN_NAME_EDU_GPA_SCALE} column.
     * @param eduSchool           the value of the {@value #COLUMN_NAME_EDU_SCHOOL} column.
     * @param eduLocCountryRegion the value of the {@value #COLUMN_NAME_EDU_LOC_COUNTRY_REGION} column.
     * @param eduLocState         the value of the {@value #COLUMN_NAME_EDU_LOC_STATE} column.
     * @param eduLocCity          the value of the {@value #COLUMN_NAME_EDU_LOC_CITY} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public JobCandidateEducationView(final Integer jobCandidateId,
                                     final String eduLevel,
                                     final LocalDateTime eduStartDate,
                                     final LocalDateTime eduEndDate,
                                     final String eduDegree,
                                     final String eduMajor,
                                     final String eduMinor,
                                     final String eduGpa,
                                     final String eduGpaScale,
                                     final String eduSchool,
                                     final String eduLocCountryRegion,
                                     final String eduLocState,
                                     final String eduLocCity) {
        super();
        setJobCandidateId(jobCandidateId);
        setEduLevel(eduLevel);
        setEduStartDate(eduStartDate);
        setEduEndDate(eduEndDate);
        setEduDegree(eduDegree);
        setEduMajor(eduMajor);
        setEduMinor(eduMinor);
        setEduGpa(eduGpa);
        setEduGpaScale(eduGpaScale);
        setEduSchool(eduSchool);
        setEduLocCountryRegion(eduLocCountryRegion);
        setEduLocState(eduLocState);
        setEduLocCity(eduLocCity);
    }
}

