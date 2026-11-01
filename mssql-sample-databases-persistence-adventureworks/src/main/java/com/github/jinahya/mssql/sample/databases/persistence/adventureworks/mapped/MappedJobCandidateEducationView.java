package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.vJobCandidateEducation} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedJobCandidateEducationView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "HumanResources";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vJobCandidateEducation";

    // -------------------------------------------------------------------------------------------------- JobCandidateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_CANDIDATE_ID = "JobCandidateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_CANDIDATE_ID = "jobCandidateId";

    // ------------------------------------------------------------------------------------------------------- Edu.Level

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_LEVEL = "\"Edu.Level\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_LEVEL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_LEVEL = "eduLevel";

    // --------------------------------------------------------------------------------------------------- Edu.StartDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_START_DATE = "\"Edu.StartDate\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_START_DATE = "eduStartDate";

    // ----------------------------------------------------------------------------------------------------- Edu.EndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_END_DATE = "\"Edu.EndDate\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_END_DATE = "eduEndDate";

    // ------------------------------------------------------------------------------------------------------ Edu.Degree

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_DEGREE = "\"Edu.Degree\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_DEGREE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_DEGREE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_DEGREE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_DEGREE = "eduDegree";

    // ------------------------------------------------------------------------------------------------------- Edu.Major

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_MAJOR = "\"Edu.Major\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_MAJOR} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_MAJOR = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_MAJOR} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_MAJOR = "eduMajor";

    // ------------------------------------------------------------------------------------------------------- Edu.Minor

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_MINOR = "\"Edu.Minor\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_MINOR} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_MINOR = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_MINOR} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_MINOR = "eduMinor";

    // --------------------------------------------------------------------------------------------------------- Edu.GPA

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_GPA = "\"Edu.GPA\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_GPA} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_GPA = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_GPA} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_GPA = "eduGpa";

    // ---------------------------------------------------------------------------------------------------- Edu.GPAScale

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_GPA_SCALE = "\"Edu.GPAScale\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_GPA_SCALE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_GPA_SCALE = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_GPA_SCALE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_GPA_SCALE = "eduGpaScale";

    // ------------------------------------------------------------------------------------------------------ Edu.School

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_SCHOOL = "\"Edu.School\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_SCHOOL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_SCHOOL = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_SCHOOL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_SCHOOL = "eduSchool";

    // ------------------------------------------------------------------------------------------- Edu.Loc.CountryRegion

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_LOC_COUNTRY_REGION = "\"Edu.Loc.CountryRegion\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_LOC_COUNTRY_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_LOC_COUNTRY_REGION = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_LOC_COUNTRY_REGION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_LOC_COUNTRY_REGION = "eduLocCountryRegion";

    // --------------------------------------------------------------------------------------------------- Edu.Loc.State

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_LOC_STATE = "\"Edu.Loc.State\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_LOC_STATE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_LOC_STATE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_LOC_STATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_LOC_STATE = "eduLocState";

    // ---------------------------------------------------------------------------------------------------- Edu.Loc.City

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EDU_LOC_CITY = "\"Edu.Loc.City\"";

    /**
     * The length of the {@value #COLUMN_NAME_EDU_LOC_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EDU_LOC_CITY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EDU_LOC_CITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EDU_LOC_CITY = "eduLocCity";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobCandidateEducationView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jobCandidateId=" + jobCandidateId
               + ",eduLevel=" + eduLevel
               + ",eduStartDate=" + eduStartDate
               + ",eduEndDate=" + eduEndDate
               + ",eduDegree=" + eduDegree
               + ",eduMajor=" + eduMajor
               + ",eduMinor=" + eduMinor
               + ",eduGpa=" + eduGpa
               + ",eduGpaScale=" + eduGpaScale
               + ",eduSchool=" + eduSchool
               + ",eduLocCountryRegion=" + eduLocCountryRegion
               + ",eduLocState=" + eduLocState
               + ",eduLocCity=" + eduLocCity
               + "}";
    }

    // -------------------------------------------------------------------------------------------------- jobCandidateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    @Nullable
    public Integer getJobCandidateId() {
        return jobCandidateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute with the specified value.
     *
     * @param jobCandidateId new value for {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    protected void setJobCandidateId(@Nullable final Integer jobCandidateId) {
        this.jobCandidateId = jobCandidateId;
    }

    // -------------------------------------------------------------------------------------------------------- eduLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_LEVEL} attribute.
     */
    @Nullable
    public String getEduLevel() {
        return eduLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_LEVEL} attribute with the specified value.
     *
     * @param eduLevel new value for {@value #ATTRIBUTE_NAME_EDU_LEVEL} attribute.
     */
    protected void setEduLevel(@Nullable final String eduLevel) {
        this.eduLevel = eduLevel;
    }

    // ---------------------------------------------------------------------------------------------------- eduStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_START_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getEduStartDate() {
        return eduStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_START_DATE} attribute with the specified value.
     *
     * @param eduStartDate new value for {@value #ATTRIBUTE_NAME_EDU_START_DATE} attribute.
     */
    protected void setEduStartDate(@Nullable final LocalDateTime eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    // ------------------------------------------------------------------------------------------------------ eduEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_END_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getEduEndDate() {
        return eduEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_END_DATE} attribute with the specified value.
     *
     * @param eduEndDate new value for {@value #ATTRIBUTE_NAME_EDU_END_DATE} attribute.
     */
    protected void setEduEndDate(@Nullable final LocalDateTime eduEndDate) {
        this.eduEndDate = eduEndDate;
    }

    // ------------------------------------------------------------------------------------------------------- eduDegree

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_DEGREE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_DEGREE} attribute.
     */
    @Nullable
    public String getEduDegree() {
        return eduDegree;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_DEGREE} attribute with the specified value.
     *
     * @param eduDegree new value for {@value #ATTRIBUTE_NAME_EDU_DEGREE} attribute.
     */
    protected void setEduDegree(@Nullable final String eduDegree) {
        this.eduDegree = eduDegree;
    }

    // -------------------------------------------------------------------------------------------------------- eduMajor

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_MAJOR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_MAJOR} attribute.
     */
    @Nullable
    public String getEduMajor() {
        return eduMajor;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_MAJOR} attribute with the specified value.
     *
     * @param eduMajor new value for {@value #ATTRIBUTE_NAME_EDU_MAJOR} attribute.
     */
    protected void setEduMajor(@Nullable final String eduMajor) {
        this.eduMajor = eduMajor;
    }

    // -------------------------------------------------------------------------------------------------------- eduMinor

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_MINOR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_MINOR} attribute.
     */
    @Nullable
    public String getEduMinor() {
        return eduMinor;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_MINOR} attribute with the specified value.
     *
     * @param eduMinor new value for {@value #ATTRIBUTE_NAME_EDU_MINOR} attribute.
     */
    protected void setEduMinor(@Nullable final String eduMinor) {
        this.eduMinor = eduMinor;
    }

    // ---------------------------------------------------------------------------------------------------------- eduGpa

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_GPA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_GPA} attribute.
     */
    @Nullable
    public String getEduGpa() {
        return eduGpa;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_GPA} attribute with the specified value.
     *
     * @param eduGpa new value for {@value #ATTRIBUTE_NAME_EDU_GPA} attribute.
     */
    protected void setEduGpa(@Nullable final String eduGpa) {
        this.eduGpa = eduGpa;
    }

    // ----------------------------------------------------------------------------------------------------- eduGpaScale

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_GPA_SCALE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_GPA_SCALE} attribute.
     */
    @Nullable
    public String getEduGpaScale() {
        return eduGpaScale;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_GPA_SCALE} attribute with the specified value.
     *
     * @param eduGpaScale new value for {@value #ATTRIBUTE_NAME_EDU_GPA_SCALE} attribute.
     */
    protected void setEduGpaScale(@Nullable final String eduGpaScale) {
        this.eduGpaScale = eduGpaScale;
    }

    // ------------------------------------------------------------------------------------------------------- eduSchool

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_SCHOOL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_SCHOOL} attribute.
     */
    @Nullable
    public String getEduSchool() {
        return eduSchool;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_SCHOOL} attribute with the specified value.
     *
     * @param eduSchool new value for {@value #ATTRIBUTE_NAME_EDU_SCHOOL} attribute.
     */
    protected void setEduSchool(@Nullable final String eduSchool) {
        this.eduSchool = eduSchool;
    }

    // --------------------------------------------------------------------------------------------- eduLocCountryRegion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_LOC_COUNTRY_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_LOC_COUNTRY_REGION} attribute.
     */
    @Nullable
    public String getEduLocCountryRegion() {
        return eduLocCountryRegion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_LOC_COUNTRY_REGION} attribute with the specified value.
     *
     * @param eduLocCountryRegion new value for {@value #ATTRIBUTE_NAME_EDU_LOC_COUNTRY_REGION} attribute.
     */
    protected void setEduLocCountryRegion(@Nullable final String eduLocCountryRegion) {
        this.eduLocCountryRegion = eduLocCountryRegion;
    }

    // ----------------------------------------------------------------------------------------------------- eduLocState

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_LOC_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_LOC_STATE} attribute.
     */
    @Nullable
    public String getEduLocState() {
        return eduLocState;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_LOC_STATE} attribute with the specified value.
     *
     * @param eduLocState new value for {@value #ATTRIBUTE_NAME_EDU_LOC_STATE} attribute.
     */
    protected void setEduLocState(@Nullable final String eduLocState) {
        this.eduLocState = eduLocState;
    }

    // ------------------------------------------------------------------------------------------------------ eduLocCity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EDU_LOC_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EDU_LOC_CITY} attribute.
     */
    @Nullable
    public String getEduLocCity() {
        return eduLocCity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EDU_LOC_CITY} attribute with the specified value.
     *
     * @param eduLocCity new value for {@value #ATTRIBUTE_NAME_EDU_LOC_CITY} attribute.
     */
    protected void setEduLocCity(@Nullable final String eduLocCity) {
        this.eduLocCity = eduLocCity;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_JOB_CANDIDATE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer jobCandidateId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_EDU_LEVEL,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String eduLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_EDU_START_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime eduStartDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_EDU_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime eduEndDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_DEGREE)
    @Column(name = COLUMN_NAME_EDU_DEGREE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_DEGREE
    )
    private String eduDegree;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_MAJOR)
    @Column(name = COLUMN_NAME_EDU_MAJOR,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_MAJOR
    )
    private String eduMajor;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_MINOR)
    @Column(name = COLUMN_NAME_EDU_MINOR,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_MINOR
    )
    private String eduMinor;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_GPA)
    @Column(name = COLUMN_NAME_EDU_GPA,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_GPA
    )
    private String eduGpa;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_GPA_SCALE)
    @Column(name = COLUMN_NAME_EDU_GPA_SCALE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_GPA_SCALE
    )
    private String eduGpaScale;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_SCHOOL)
    @Column(name = COLUMN_NAME_EDU_SCHOOL,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_SCHOOL
    )
    private String eduSchool;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_LOC_COUNTRY_REGION)
    @Column(name = COLUMN_NAME_EDU_LOC_COUNTRY_REGION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_LOC_COUNTRY_REGION
    )
    private String eduLocCountryRegion;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_LOC_STATE)
    @Column(name = COLUMN_NAME_EDU_LOC_STATE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_LOC_STATE
    )
    private String eduLocState;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EDU_LOC_CITY)
    @Column(name = COLUMN_NAME_EDU_LOC_CITY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EDU_LOC_CITY
    )
    private String eduLocCity;
}

