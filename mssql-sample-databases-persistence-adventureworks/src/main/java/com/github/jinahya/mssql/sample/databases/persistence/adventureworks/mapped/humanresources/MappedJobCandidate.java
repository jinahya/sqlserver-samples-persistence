package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.humanresources;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.JobCandidate} table.
 * <p>
 * The {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column is mapped read-only, as a foreign key; the extending entity owns
 * the writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedJobCandidate {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "HumanResources";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "JobCandidate";

    // -------------------------------------------------------------------------------------------------- JobCandidateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_CANDIDATE_ID = "JobCandidateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_CANDIDATE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_CANDIDATE_ID = "jobCandidateId";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ---------------------------------------------------------------------------------------------------------- Resume

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RESUME = "Resume";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RESUME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RESUME = "resume";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedJobCandidate() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "jobCandidateId=" + jobCandidateId
               + ",businessEntityId=" + businessEntityId
               + ",resume=" + resume
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedJobCandidate that)) {
            return false;
        }
        return Objects.equals(jobCandidateId, that.jobCandidateId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(jobCandidateId);
    }

    // -------------------------------------------------------------------------------------------------- jobCandidateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    public Integer getJobCandidateId() {
        return jobCandidateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute with the specified value.
     *
     * @param jobCandidateId new value for {@value #ATTRIBUTE_NAME_JOB_CANDIDATE_ID} attribute.
     */
    protected void setJobCandidateId(final Integer jobCandidateId) {
        this.jobCandidateId = jobCandidateId;
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ---------------------------------------------------------------------------------------------------------- resume

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RESUME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RESUME} attribute.
     */
    public String getResume() {
        return resume;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RESUME} attribute with the specified value.
     *
     * @param resume new value for {@value #ATTRIBUTE_NAME_RESUME} attribute.
     */
    public void setResume(final String resume) {
        this.resume = resume;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_JOB_CANDIDATE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer jobCandidateId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RESUME,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String resume;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

