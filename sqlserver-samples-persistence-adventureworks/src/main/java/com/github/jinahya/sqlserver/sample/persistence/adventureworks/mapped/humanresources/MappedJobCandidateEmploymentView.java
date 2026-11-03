package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the
 * {@code HumanResources.vJobCandidateEmployment} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native
 * query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of
 * it is insertable or updatable; some of its columns are derived or constant (error 4406); one of
 * its columns is an identity column of its base table, which no UPDATE sets (error 8102).
 */
@MappedSuperclass
public abstract class MappedJobCandidateEmploymentView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vJobCandidateEmployment";

    // ------------------------------------------------------------------------------ JobCandidateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_CANDIDATE_ID = "JobCandidateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_CANDIDATE_ID = "jobCandidateId";

    // ------------------------------------------------------------------------------- Emp.StartDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_START_DATE = "\"Emp.StartDate\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_START_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_START_DATE = "empStartDate";

    // --------------------------------------------------------------------------------- Emp.EndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_END_DATE = "\"Emp.EndDate\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_END_DATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_END_DATE = "empEndDate";

    // --------------------------------------------------------------------------------- Emp.OrgName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_ORG_NAME = "\"Emp.OrgName\"";

    /**
     * The length of the {@value #COLUMN_NAME_EMP_ORG_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMP_ORG_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_ORG_NAME} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_ORG_NAME = "empOrgName";

    // -------------------------------------------------------------------------------- Emp.JobTitle

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_JOB_TITLE = "\"Emp.JobTitle\"";

    /**
     * The length of the {@value #COLUMN_NAME_EMP_JOB_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMP_JOB_TITLE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_JOB_TITLE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_JOB_TITLE = "empJobTitle";

    // -------------------------------------------------------------------------- Emp.Responsibility

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_RESPONSIBILITY = "\"Emp.Responsibility\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_RESPONSIBILITY} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_RESPONSIBILITY = "empResponsibility";

    // ------------------------------------------------------------------------ Emp.FunctionCategory

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_FUNCTION_CATEGORY = "\"Emp.FunctionCategory\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_FUNCTION_CATEGORY} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_FUNCTION_CATEGORY = "empFunctionCategory";

    // ------------------------------------------------------------------------ Emp.IndustryCategory

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_INDUSTRY_CATEGORY = "\"Emp.IndustryCategory\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_INDUSTRY_CATEGORY} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_INDUSTRY_CATEGORY = "empIndustryCategory";

    // ----------------------------------------------------------------------- Emp.Loc.CountryRegion

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_LOC_COUNTRY_REGION = "\"Emp.Loc.CountryRegion\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_LOC_COUNTRY_REGION} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_LOC_COUNTRY_REGION = "empLocCountryRegion";

    // ------------------------------------------------------------------------------- Emp.Loc.State

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_LOC_STATE = "\"Emp.Loc.State\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_LOC_STATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_LOC_STATE = "empLocState";

    // -------------------------------------------------------------------------------- Emp.Loc.City

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMP_LOC_CITY = "\"Emp.Loc.City\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMP_LOC_CITY} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMP_LOC_CITY = "empLocCity";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobCandidateEmploymentView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jobCandidateId=" + jobCandidateId
               + ",empStartDate=" + empStartDate
               + ",empEndDate=" + empEndDate
               + ",empOrgName=" + empOrgName
               + ",empJobTitle=" + empJobTitle
               + ",empResponsibility=" + empResponsibility
               + ",empFunctionCategory=" + empFunctionCategory
               + ",empIndustryCategory=" + empIndustryCategory
               + ",empLocCountryRegion=" + empLocCountryRegion
               + ",empLocState=" + empLocState
               + ",empLocCity=" + empLocCity
               + "}";
    }

    // ------------------------------------------------------------------------------ jobCandidateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    public Integer getJobCandidateId() {
        return jobCandidateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute with the
     * specified value.
     *
     * @param jobCandidateId new value for {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    protected void setJobCandidateId(final Integer jobCandidateId) {
        this.jobCandidateId = jobCandidateId;
    }

    // -------------------------------------------------------------------------------- empStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_START_DATE} attribute.
     */
    public LocalDateTime getEmpStartDate() {
        return empStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_START_DATE} attribute with the
     * specified value.
     *
     * @param empStartDate new value for {@value #ATTRIBUTE_NAME_EMP_START_DATE} attribute.
     */
    protected void setEmpStartDate(final LocalDateTime empStartDate) {
        this.empStartDate = empStartDate;
    }

    // ---------------------------------------------------------------------------------- empEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_END_DATE} attribute.
     */
    public LocalDateTime getEmpEndDate() {
        return empEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_END_DATE} attribute with the specified
     * value.
     *
     * @param empEndDate new value for {@value #ATTRIBUTE_NAME_EMP_END_DATE} attribute.
     */
    protected void setEmpEndDate(final LocalDateTime empEndDate) {
        this.empEndDate = empEndDate;
    }

    // ---------------------------------------------------------------------------------- empOrgName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_ORG_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_ORG_NAME} attribute.
     */
    public String getEmpOrgName() {
        return empOrgName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_ORG_NAME} attribute with the specified
     * value.
     *
     * @param empOrgName new value for {@value #ATTRIBUTE_NAME_EMP_ORG_NAME} attribute.
     */
    protected void setEmpOrgName(final String empOrgName) {
        this.empOrgName = empOrgName;
    }

    // --------------------------------------------------------------------------------- empJobTitle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_JOB_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_JOB_TITLE} attribute.
     */
    public String getEmpJobTitle() {
        return empJobTitle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_JOB_TITLE} attribute with the specified
     * value.
     *
     * @param empJobTitle new value for {@value #ATTRIBUTE_NAME_EMP_JOB_TITLE} attribute.
     */
    protected void setEmpJobTitle(final String empJobTitle) {
        this.empJobTitle = empJobTitle;
    }

    // --------------------------------------------------------------------------- empResponsibility

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_RESPONSIBILITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_RESPONSIBILITY} attribute.
     */
    public String getEmpResponsibility() {
        return empResponsibility;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_RESPONSIBILITY} attribute with the
     * specified value.
     *
     * @param empResponsibility new value for {@value #ATTRIBUTE_NAME_EMP_RESPONSIBILITY}
     *                          attribute.
     */
    protected void setEmpResponsibility(final String empResponsibility) {
        this.empResponsibility = empResponsibility;
    }

    // ------------------------------------------------------------------------- empFunctionCategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_FUNCTION_CATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_FUNCTION_CATEGORY} attribute.
     */
    public String getEmpFunctionCategory() {
        return empFunctionCategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_FUNCTION_CATEGORY} attribute with the
     * specified value.
     *
     * @param empFunctionCategory new value for {@value #ATTRIBUTE_NAME_EMP_FUNCTION_CATEGORY}
     *                            attribute.
     */
    protected void setEmpFunctionCategory(final String empFunctionCategory) {
        this.empFunctionCategory = empFunctionCategory;
    }

    // ------------------------------------------------------------------------- empIndustryCategory

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_INDUSTRY_CATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_INDUSTRY_CATEGORY} attribute.
     */
    public String getEmpIndustryCategory() {
        return empIndustryCategory;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_INDUSTRY_CATEGORY} attribute with the
     * specified value.
     *
     * @param empIndustryCategory new value for {@value #ATTRIBUTE_NAME_EMP_INDUSTRY_CATEGORY}
     *                            attribute.
     */
    protected void setEmpIndustryCategory(final String empIndustryCategory) {
        this.empIndustryCategory = empIndustryCategory;
    }

    // ------------------------------------------------------------------------- empLocCountryRegion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_LOC_COUNTRY_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_LOC_COUNTRY_REGION} attribute.
     */
    public String getEmpLocCountryRegion() {
        return empLocCountryRegion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_LOC_COUNTRY_REGION} attribute with the
     * specified value.
     *
     * @param empLocCountryRegion new value for {@value #ATTRIBUTE_NAME_EMP_LOC_COUNTRY_REGION}
     *                            attribute.
     */
    protected void setEmpLocCountryRegion(final String empLocCountryRegion) {
        this.empLocCountryRegion = empLocCountryRegion;
    }

    // --------------------------------------------------------------------------------- empLocState

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_LOC_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_LOC_STATE} attribute.
     */
    public String getEmpLocState() {
        return empLocState;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_LOC_STATE} attribute with the specified
     * value.
     *
     * @param empLocState new value for {@value #ATTRIBUTE_NAME_EMP_LOC_STATE} attribute.
     */
    protected void setEmpLocState(final String empLocState) {
        this.empLocState = empLocState;
    }

    // ---------------------------------------------------------------------------------- empLocCity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMP_LOC_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMP_LOC_CITY} attribute.
     */
    public String getEmpLocCity() {
        return empLocCity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMP_LOC_CITY} attribute with the specified
     * value.
     *
     * @param empLocCity new value for {@value #ATTRIBUTE_NAME_EMP_LOC_CITY} attribute.
     */
    protected void setEmpLocCity(final String empLocCity) {
        this.empLocCity = empLocCity;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_CANDIDATE_ID, nullable = false, insertable = false,
            updatable = false)
    private Integer jobCandidateId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_START_DATE, nullable = true, insertable = false,
            updatable = false)
    private LocalDateTime empStartDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_END_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDateTime empEndDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_EMP_ORG_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_ORG_NAME, nullable = true, insertable = false, updatable = false,
            length = COLUMN_LENGTH_EMP_ORG_NAME)
    private String empOrgName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_EMP_JOB_TITLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_JOB_TITLE, nullable = true, insertable = false,
            updatable = false, length = COLUMN_LENGTH_EMP_JOB_TITLE)
    private String empJobTitle;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_RESPONSIBILITY, nullable = true, insertable = false,
            updatable = false)
    private String empResponsibility;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_FUNCTION_CATEGORY, nullable = true, insertable = false,
            updatable = false)
    private String empFunctionCategory;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_INDUSTRY_CATEGORY, nullable = true, insertable = false,
            updatable = false)
    private String empIndustryCategory;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_LOC_COUNTRY_REGION, nullable = true, insertable = false,
            updatable = false)
    private String empLocCountryRegion;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_LOC_STATE, nullable = true, insertable = false,
            updatable = false)
    private String empLocState;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMP_LOC_CITY, nullable = true, insertable = false, updatable = false)
    private String empLocCity;
}

