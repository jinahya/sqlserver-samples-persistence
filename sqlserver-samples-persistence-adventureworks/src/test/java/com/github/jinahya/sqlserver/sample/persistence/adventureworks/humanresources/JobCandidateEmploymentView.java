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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedJobCandidateEmploymentView;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code HumanResources.vJobCandidateEmployment} view, which
 * no entity can map: the view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class JobCandidateEmploymentView
        extends MappedJobCandidateEmploymentView {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected JobCandidateEmploymentView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param jobCandidateId      the value of the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column.
     * @param empStartDate        the value of the {@value #COLUMN_NAME_EMP_START_DATE} column.
     * @param empEndDate          the value of the {@value #COLUMN_NAME_EMP_END_DATE} column.
     * @param empOrgName          the value of the {@value #COLUMN_NAME_EMP_ORG_NAME} column.
     * @param empJobTitle         the value of the {@value #COLUMN_NAME_EMP_JOB_TITLE} column.
     * @param empResponsibility   the value of the {@value #COLUMN_NAME_EMP_RESPONSIBILITY} column.
     * @param empFunctionCategory the value of the {@value #COLUMN_NAME_EMP_FUNCTION_CATEGORY}
     *                            column.
     * @param empIndustryCategory the value of the {@value #COLUMN_NAME_EMP_INDUSTRY_CATEGORY}
     *                            column.
     * @param empLocCountryRegion the value of the {@value #COLUMN_NAME_EMP_LOC_COUNTRY_REGION}
     *                            column.
     * @param empLocState         the value of the {@value #COLUMN_NAME_EMP_LOC_STATE} column.
     * @param empLocCity          the value of the {@value #COLUMN_NAME_EMP_LOC_CITY} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public JobCandidateEmploymentView(final Integer jobCandidateId,
                                      final LocalDateTime empStartDate,
                                      final LocalDateTime empEndDate,
                                      final String empOrgName,
                                      final String empJobTitle,
                                      final String empResponsibility,
                                      final String empFunctionCategory,
                                      final String empIndustryCategory,
                                      final String empLocCountryRegion,
                                      final String empLocState,
                                      final String empLocCity) {
        super();
        setJobCandidateId(jobCandidateId);
        setEmpStartDate(empStartDate);
        setEmpEndDate(empEndDate);
        setEmpOrgName(empOrgName);
        setEmpJobTitle(empJobTitle);
        setEmpResponsibility(empResponsibility);
        setEmpFunctionCategory(empFunctionCategory);
        setEmpIndustryCategory(empIndustryCategory);
        setEmpLocCountryRegion(empLocCountryRegion);
        setEmpLocState(empLocState);
        setEmpLocCity(empLocCity);
    }
}

