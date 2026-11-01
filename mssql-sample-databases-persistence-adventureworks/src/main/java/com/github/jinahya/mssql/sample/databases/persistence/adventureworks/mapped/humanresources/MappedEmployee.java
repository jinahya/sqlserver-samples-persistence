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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.Employee} table.
 */
@MappedSuperclass
public abstract class MappedEmployee {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "HumanResources";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Employee";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------------------------ NationalIDNumber

    /**
     * The name of the {@code nvarchar(15), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NATIONAL_ID_NUMBER = "NationalIDNumber";

    /**
     * The length of the {@value #COLUMN_NAME_NATIONAL_ID_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NATIONAL_ID_NUMBER = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NATIONAL_ID_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NATIONAL_ID_NUMBER = "nationalIdNumber";

    // --------------------------------------------------------------------------------------------------------- LoginID

    /**
     * The name of the {@code nvarchar(256), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOGIN_ID = "LoginID";

    /**
     * The length of the {@value #COLUMN_NAME_LOGIN_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LOGIN_ID = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGIN_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGIN_ID = "loginId";

    // ------------------------------------------------------------------------------------------------ OrganizationNode

    /**
     * The name of the {@code hierarchyid} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORGANIZATION_NODE = "OrganizationNode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORGANIZATION_NODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORGANIZATION_NODE = "organizationNode";

    // ----------------------------------------------------------------------------------------------- OrganizationLevel

    /**
     * The name of the {@code smallint, computed as ([OrganizationNode].[GetLevel]())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORGANIZATION_LEVEL = "OrganizationLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORGANIZATION_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORGANIZATION_LEVEL = "organizationLevel";

    // -------------------------------------------------------------------------------------------------------- JobTitle

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_TITLE = "JobTitle";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_TITLE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    // ------------------------------------------------------------------------------------------------------- BirthDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIRTH_DATE = "BirthDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIRTH_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIRTH_DATE = "birthDate";

    // --------------------------------------------------------------------------------------------------- MaritalStatus

    /**
     * The name of the {@code nchar(1), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MARITAL_STATUS = "MaritalStatus";

    /**
     * The length of the {@value #COLUMN_NAME_MARITAL_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MARITAL_STATUS = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MARITAL_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MARITAL_STATUS = "maritalStatus";

    // ---------------------------------------------------------------------------------------------------------- Gender

    /**
     * The name of the {@code nchar(1), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GENDER = "Gender";

    /**
     * The length of the {@value #COLUMN_NAME_GENDER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_GENDER = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GENDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GENDER = "gender";

    // -------------------------------------------------------------------------------------------------------- HireDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HIRE_DATE = "HireDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HIRE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_HIRE_DATE = "hireDate";

    // ---------------------------------------------------------------------------------------------------- SalariedFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALARIED_FLAG = "SalariedFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALARIED_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALARIED_FLAG = "salariedFlag";

    // --------------------------------------------------------------------------------------------------- VacationHours

    /**
     * The name of the {@code smallint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VACATION_HOURS = "VacationHours";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VACATION_HOURS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VACATION_HOURS = "vacationHours";

    // -------------------------------------------------------------------------------------------------- SickLeaveHours

    /**
     * The name of the {@code smallint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SICK_LEAVE_HOURS = "SickLeaveHours";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SICK_LEAVE_HOURS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SICK_LEAVE_HOURS = "sickLeaveHours";

    // ----------------------------------------------------------------------------------------------------- CurrentFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENT_FLAG = "CurrentFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENT_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENT_FLAG = "currentFlag";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedEmployee() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",nationalIdNumber=" + nationalIdNumber
               + ",loginId=" + loginId
               + ",organizationNode=" + (this.organizationNode == null ? null : this.organizationNode.length + " bytes")
               + ",organizationLevel=" + organizationLevel
               + ",jobTitle=" + jobTitle
               + ",birthDate=" + birthDate
               + ",maritalStatus=" + maritalStatus
               + ",gender=" + gender
               + ",hireDate=" + hireDate
               + ",salariedFlag=" + salariedFlag
               + ",vacationHours=" + vacationHours
               + ",sickLeaveHours=" + sickLeaveHours
               + ",currentFlag=" + currentFlag
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedEmployee that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ------------------------------------------------------------------------------------------------ nationalIdNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute.
     */
    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute with the specified value.
     *
     * @param nationalIdNumber new value for {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute.
     */
    public void setNationalIdNumber(final String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    // --------------------------------------------------------------------------------------------------------- loginId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute.
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute with the specified value.
     *
     * @param loginId new value for {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute.
     */
    public void setLoginId(final String loginId) {
        this.loginId = loginId;
    }

    // ------------------------------------------------------------------------------------------------ organizationNode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute.
     */
    public byte[] getOrganizationNode() {
        return organizationNode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute with the specified value.
     *
     * @param organizationNode new value for {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute.
     */
    public void setOrganizationNode(final byte[] organizationNode) {
        this.organizationNode = organizationNode;
    }

    // ----------------------------------------------------------------------------------------------- organizationLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute.
     */
    public Short getOrganizationLevel() {
        return organizationLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param organizationLevel new value for {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute.
     */
    protected void setOrganizationLevel(final Short organizationLevel) {
        this.organizationLevel = organizationLevel;
    }

    // -------------------------------------------------------------------------------------------------------- jobTitle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute with the specified value.
     *
     * @param jobTitle new value for {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------- birthDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute with the specified value.
     *
     * @param birthDate new value for {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     */
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // --------------------------------------------------------------------------------------------------- maritalStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute with the specified value.
     *
     * @param maritalStatus new value for {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     */
    public void setMaritalStatus(final String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // ---------------------------------------------------------------------------------------------------------- gender

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GENDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GENDER} attribute.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GENDER} attribute with the specified value.
     *
     * @param gender new value for {@value #ATTRIBUTE_NAME_GENDER} attribute.
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    // -------------------------------------------------------------------------------------------------------- hireDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute with the specified value.
     *
     * @param hireDate new value for {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    public void setHireDate(final LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // ---------------------------------------------------------------------------------------------------- salariedFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute.
     */
    public Boolean getSalariedFlag() {
        return salariedFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute with the specified value.
     *
     * @param salariedFlag new value for {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute.
     */
    public void setSalariedFlag(final Boolean salariedFlag) {
        this.salariedFlag = salariedFlag;
    }

    // --------------------------------------------------------------------------------------------------- vacationHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute.
     */
    public Short getVacationHours() {
        return vacationHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute with the specified value.
     *
     * @param vacationHours new value for {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute.
     */
    public void setVacationHours(final Short vacationHours) {
        this.vacationHours = vacationHours;
    }

    // -------------------------------------------------------------------------------------------------- sickLeaveHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute.
     */
    public Short getSickLeaveHours() {
        return sickLeaveHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute with the specified value.
     *
     * @param sickLeaveHours new value for {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute.
     */
    public void setSickLeaveHours(final Short sickLeaveHours) {
        this.sickLeaveHours = sickLeaveHours;
    }

    // ----------------------------------------------------------------------------------------------------- currentFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute.
     */
    public Boolean getCurrentFlag() {
        return currentFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute with the specified value.
     *
     * @param currentFlag new value for {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute.
     */
    public void setCurrentFlag(final Boolean currentFlag) {
        this.currentFlag = currentFlag;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
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
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NATIONAL_ID_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NATIONAL_ID_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NATIONAL_ID_NUMBER
    )
    private String nationalIdNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_LOGIN_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOGIN_ID,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LOGIN_ID
    )
    private String loginId;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORGANIZATION_NODE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] organizationNode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORGANIZATION_LEVEL,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Short organizationLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_JOB_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_TITLE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_JOB_TITLE
    )
    private String jobTitle;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BIRTH_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate birthDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_MARITAL_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MARITAL_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_MARITAL_STATUS
    )
    private String maritalStatus;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_GENDER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GENDER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_GENDER
    )
    private String gender;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_HIRE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate hireDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALARIED_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean salariedFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VACATION_HOURS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short vacationHours;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SICK_LEAVE_HOURS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short sickLeaveHours;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CURRENT_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean currentFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

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

