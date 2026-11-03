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

import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;
import com.github.jinahya.sqlserver.sample.persistence.common.HeirarchyidAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTrigger;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.Employee} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code HumanResources.Employee}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BusinessEntityID}</th>
 *     <td>Primary key for Employee records.  Foreign key to
 *     BusinessEntity.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code NationalIDNumber}</th>
 *     <td>Unique national identification number such as a social security number.</td></tr>
 * <tr><th scope="row">{@code LoginID}</th><td>Network login.</td></tr>
 * <tr><th scope="row">{@code OrganizationNode}</th>
 *     <td>Where the employee is located in corporate hierarchy.</td></tr>
 * <tr><th scope="row">{@code OrganizationLevel}</th>
 *     <td>The depth of the employee in the corporate hierarchy.</td></tr>
 * <tr><th scope="row">{@code JobTitle}</th>
 *     <td>Work title such as Buyer or Sales Representative.</td></tr>
 * <tr><th scope="row">{@code BirthDate}</th><td>Date of birth.</td></tr>
 * <tr><th scope="row">{@code MaritalStatus}</th><td>M = Married, S = Single</td></tr>
 * <tr><th scope="row">{@code Gender}</th><td>M = Male, F = Female</td></tr>
 * <tr><th scope="row">{@code HireDate}</th><td>Employee hired on this date.</td></tr>
 * <tr><th scope="row">{@code SalariedFlag}</th>
 *     <td>Job classification. 0 = Hourly, not exempt from collective bargaining. 1 = Salaried,
 *     exempt from collective bargaining.</td></tr>
 * <tr><th scope="row">{@code VacationHours}</th><td>Number of available vacation hours.</td></tr>
 * <tr><th scope="row">{@code SickLeaveHours}</th>
 *     <td>Number of available sick leave hours.</td></tr>
 * <tr><th scope="row">{@code CurrentFlag}</th><td>0 = Inactive, 1 = Active</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code HumanResources.Employee}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SalariedFlag}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code VacationHours}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code SickLeaveHours}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code CurrentFlag}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code HumanResources.Employee}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code OrganizationLevel}</th>
 *     <td>{@code ([OrganizationNode].[GetLevel]())}</td><td>{@code false}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Triggers on {@code HumanResources.Employee}</caption>
 * <thead>
 * <tr><th scope="col">Trigger</th><th scope="col">Timing</th><th scope="col">Events</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code dEmployee}</th><td>{@code INSTEAD OF}</td><td>{@code DELETE}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/getlevel-database-engine">
 *         GetLevel (Database Engine)</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedEmployee.TABLE_SCHEMA,
        table = MappedEmployee.TABLE_NAME,
        value = "Employee information such as salary, department, and title."
)
@MsTrigger(
        name = "dEmployee",
        timing = MsTrigger.Timing.INSTEAD_OF,
        events = MsTrigger.Event.DELETE
)
public abstract class MappedEmployee {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Employee";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ---------------------------------------------------------------------------- NationalIDNumber

    /**
     * The name of the {@code nvarchar(15), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NATIONAL_ID_NUMBER = "NationalIDNumber";

    /**
     * The length of the {@value #COLUMN_NAME_NATIONAL_ID_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NATIONAL_ID_NUMBER = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NATIONAL_ID_NUMBER} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NATIONAL_ID_NUMBER = "nationalIdNumber";

    // ------------------------------------------------------------------------------------- LoginID

    /**
     * The name of the {@code nvarchar(256), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOGIN_ID = "LoginID";

    /**
     * The length of the {@value #COLUMN_NAME_LOGIN_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LOGIN_ID = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGIN_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGIN_ID = "loginId";

    // ---------------------------------------------------------------------------- OrganizationNode

    /**
     * The name of the {@code hierarchyid} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORGANIZATION_NODE = "OrganizationNode";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORGANIZATION_NODE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORGANIZATION_NODE = "organizationNode";

    // --------------------------------------------------------------------------- OrganizationLevel

    /**
     * The name of the {@code smallint, computed as ([OrganizationNode].[GetLevel]())} column. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_ORGANIZATION_LEVEL = "OrganizationLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORGANIZATION_LEVEL} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORGANIZATION_LEVEL = "organizationLevel";

    // ------------------------------------------------------------------------------------ JobTitle

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_JOB_TITLE = "JobTitle";

    /**
     * The length of the {@value #COLUMN_NAME_JOB_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_JOB_TITLE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_JOB_TITLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_JOB_TITLE = "jobTitle";

    // ----------------------------------------------------------------------------------- BirthDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIRTH_DATE = "BirthDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIRTH_DATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIRTH_DATE = "birthDate";

    // ------------------------------------------------------------------------------- MaritalStatus

    /**
     * The name of the {@code nchar(1), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MARITAL_STATUS = "MaritalStatus";

    /**
     * The length of the {@value #COLUMN_NAME_MARITAL_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MARITAL_STATUS = 1;

    /**
     * A value of the {@value #COLUMN_NAME_MARITAL_STATUS} column, for an employee who is single.
     * The value is {@value}.
     */
    public static final String COLUMN_VALUE_MARITAL_STATUS_SINGLE = "S";

    /**
     * A value of the {@value #COLUMN_NAME_MARITAL_STATUS} column, for an employee who is married.
     * The value is {@value}.
     */
    public static final String COLUMN_VALUE_MARITAL_STATUS_MARRIED = "M";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MARITAL_STATUS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MARITAL_STATUS = "maritalStatus";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute, of the values the
     * {@code CK_Employee_MaritalStatus} constraint allows.
     * {@snippet lang = "sql":
     * (upper([MaritalStatus])='S' OR upper([MaritalStatus])='M')
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum MaritalStatus {

        /**
         * A constant for the {@value MappedEmployee#COLUMN_VALUE_MARITAL_STATUS_SINGLE} value, for
         * an employee who is single.
         */
        S,

        /**
         * A constant for the {@value MappedEmployee#COLUMN_VALUE_MARITAL_STATUS_MARRIED} value, for
         * an employee who is married.
         */
        M
    }

    // -------------------------------------------------------------------------------------- Gender

    /**
     * The name of the {@code nchar(1), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GENDER = "Gender";

    /**
     * The length of the {@value #COLUMN_NAME_GENDER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_GENDER = 1;

    /**
     * A value of the {@value #COLUMN_NAME_GENDER} column, for a female employee. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_GENDER_FEMALE = "F";

    /**
     * A value of the {@value #COLUMN_NAME_GENDER} column, for a male employee. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_GENDER_MALE = "M";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GENDER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GENDER = "gender";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_GENDER} attribute, of the values the
     * {@code CK_Employee_Gender} constraint allows.
     * {@snippet lang = "sql":
     * (upper([Gender])='F' OR upper([Gender])='M')
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum Gender {

        /**
         * A constant for the {@value MappedEmployee#COLUMN_VALUE_GENDER_FEMALE} value, for a female
         * employee.
         */
        F,

        /**
         * A constant for the {@value MappedEmployee#COLUMN_VALUE_GENDER_MALE} value, for a male
         * employee.
         */
        M
    }

    // ------------------------------------------------------------------------------------ HireDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HIRE_DATE = "HireDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HIRE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_HIRE_DATE = "hireDate";

    // -------------------------------------------------------------------------------- SalariedFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALARIED_FLAG = "SalariedFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALARIED_FLAG} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALARIED_FLAG = "salariedFlag";

    // ------------------------------------------------------------------------------- VacationHours

    /**
     * The name of the {@code smallint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VACATION_HOURS = "VacationHours";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute, as the
     * {@code CK_Employee_VacationHours} check constraint ({@code [VacationHours]>=(-40)}) requires.
     * The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_VACATION_HOURS = -40;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute, as the
     * {@code CK_Employee_VacationHours} check constraint ({@code [VacationHours]<=(240)}) requires.
     * The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_VACATION_HOURS = +240;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VACATION_HOURS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VACATION_HOURS = "vacationHours";

    // ------------------------------------------------------------------------------ SickLeaveHours

    /**
     * The name of the {@code smallint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SICK_LEAVE_HOURS = "SickLeaveHours";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute, as the
     * {@code CK_Employee_SickLeaveHours} check constraint ({@code [SickLeaveHours]>=(0)}) requires.
     * The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SICK_LEAVE_HOURS = 0;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute, as the
     * {@code CK_Employee_SickLeaveHours} check constraint ({@code [SickLeaveHours]<=(120)})
     * requires. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SICK_LEAVE_HOURS = +120;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SICK_LEAVE_HOURS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SICK_LEAVE_HOURS = "sickLeaveHours";

    // --------------------------------------------------------------------------------- CurrentFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENT_FLAG = "CurrentFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENT_FLAG} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENT_FLAG = "currentFlag";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmployee() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",nationalIdNumber=" + nationalIdNumber
               + ",loginId=" + loginId
               + ",organizationNode=" + organizationNode
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

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute is {@code null} or within
     * the range of the table's {@code CK_Employee_BirthDate} constraint.
     * {@snippet lang = "sql":
     * ([BirthDate]>='1930-01-01' AND [BirthDate]<=dateadd(year,(-18),getdate()))
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute is {@code null} or
     *         within the range; {@code false} otherwise.
     * @implNote This method compares with the current date of this JVM, not with the
     *         {@code getdate()} of the database.
     */
    @AssertTrue(message = "birthDate must be between 1930-01-01 and 18 years ago, inclusive")
    protected boolean isBirthDateValid() {
        if (birthDate == null) {
            return true;
        }
        return !birthDate.isBefore(LocalDate.of(1930, 1, 1))
               && !birthDate.isAfter(LocalDate.now().minusYears(18L));
    }

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute is {@code null} or within the
     * range of the table's {@code CK_Employee_HireDate} constraint.
     * {@snippet lang = "sql":
     * ([HireDate]>='1996-07-01' AND [HireDate]<=dateadd(day,(1),getdate()))
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute is {@code null} or
     *         within the range; {@code false} otherwise.
     * @implNote This method compares with the current date of this JVM, not with the
     *         {@code getdate()} of the database.
     */
    @AssertTrue(message = "hireDate must be between 1996-07-01 and tomorrow, inclusive")
    protected boolean isHireDateValid() {
        if (hireDate == null) {
            return true;
        }
        return !hireDate.isBefore(LocalDate.of(1996, 7, 1))
               && !hireDate.isAfter(LocalDate.now().plusDays(1L));
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the
     * specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ---------------------------------------------------------------------------- nationalIdNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute.
     */
    public String getNationalIdNumber() {
        return nationalIdNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute with the
     * specified value.
     *
     * @param nationalIdNumber new value for {@value #ATTRIBUTE_NAME_NATIONAL_ID_NUMBER} attribute.
     */
    public void setNationalIdNumber(final String nationalIdNumber) {
        this.nationalIdNumber = nationalIdNumber;
    }

    // ------------------------------------------------------------------------------------- loginId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute.
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute with the specified
     * value.
     *
     * @param loginId new value for {@value #ATTRIBUTE_NAME_LOGIN_ID} attribute.
     */
    public void setLoginId(final String loginId) {
        this.loginId = loginId;
    }

    // ---------------------------------------------------------------------------- organizationNode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute.
     */
    public Heirarchyid getOrganizationNode() {
        return organizationNode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute with the
     * specified value.
     *
     * @param organizationNode new value for {@value #ATTRIBUTE_NAME_ORGANIZATION_NODE} attribute.
     */
    public void setOrganizationNode(final Heirarchyid organizationNode) {
        this.organizationNode = organizationNode;
    }

    // --------------------------------------------------------------------------- organizationLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute.
     */
    public Integer getOrganizationLevel() {
        return organizationLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL} attribute with the
     * specified value. The database writes the column; this is for the provider and the extending
     * class only.
     *
     * @param organizationLevel new value for {@value #ATTRIBUTE_NAME_ORGANIZATION_LEVEL}
     *                          attribute.
     */
    protected void setOrganizationLevel(final Integer organizationLevel) {
        this.organizationLevel = organizationLevel;
    }

    // ------------------------------------------------------------------------------------ jobTitle

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute with the specified
     * value.
     *
     * @param jobTitle new value for {@value #ATTRIBUTE_NAME_JOB_TITLE} attribute.
     */
    public void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ----------------------------------------------------------------------------------- birthDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute with the specified
     * value.
     *
     * @param birthDate new value for {@value #ATTRIBUTE_NAME_BIRTH_DATE} attribute.
     */
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // ------------------------------------------------------------------------------- maritalStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     */
    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute with the
     * specified value.
     *
     * @param maritalStatus new value for {@value #ATTRIBUTE_NAME_MARITAL_STATUS} attribute.
     */
    public void setMaritalStatus(final MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    // -------------------------------------------------------------------------------------- gender

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GENDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GENDER} attribute.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GENDER} attribute with the specified
     * value.
     *
     * @param gender new value for {@value #ATTRIBUTE_NAME_GENDER} attribute.
     */
    public void setGender(final Gender gender) {
        this.gender = gender;
    }

    // ------------------------------------------------------------------------------------ hireDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute with the specified
     * value.
     *
     * @param hireDate new value for {@value #ATTRIBUTE_NAME_HIRE_DATE} attribute.
     */
    public void setHireDate(final LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    // -------------------------------------------------------------------------------- salariedFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute.
     */
    public Boolean getSalariedFlag() {
        return salariedFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute with the specified
     * value.
     *
     * @param salariedFlag new value for {@value #ATTRIBUTE_NAME_SALARIED_FLAG} attribute.
     */
    public void setSalariedFlag(final Boolean salariedFlag) {
        this.salariedFlag = salariedFlag;
    }

    // ------------------------------------------------------------------------------- vacationHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute.
     */
    public Integer getVacationHours() {
        return vacationHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute with the
     * specified value.
     *
     * @param vacationHours new value for {@value #ATTRIBUTE_NAME_VACATION_HOURS} attribute.
     */
    public void setVacationHours(final Integer vacationHours) {
        this.vacationHours = vacationHours;
    }

    // ------------------------------------------------------------------------------ sickLeaveHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute.
     */
    public Integer getSickLeaveHours() {
        return sickLeaveHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute with the
     * specified value.
     *
     * @param sickLeaveHours new value for {@value #ATTRIBUTE_NAME_SICK_LEAVE_HOURS} attribute.
     */
    public void setSickLeaveHours(final Integer sickLeaveHours) {
        this.sickLeaveHours = sickLeaveHours;
    }

    // --------------------------------------------------------------------------------- currentFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute.
     */
    public Boolean getCurrentFlag() {
        return currentFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute with the specified
     * value.
     *
     * @param currentFlag new value for {@value #ATTRIBUTE_NAME_CURRENT_FLAG} attribute.
     */
    public void setCurrentFlag(final Boolean currentFlag) {
        this.currentFlag = currentFlag;
    }

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                          value = "Primary key for Employee records.  Foreign key to "
                                  + "BusinessEntity.BusinessEntityID.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NATIONAL_ID_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NATIONAL_ID_NUMBER, nullable = false, insertable = true,
            updatable = true, length = COLUMN_LENGTH_NATIONAL_ID_NUMBER)
    @MsDescription.Column(name = COLUMN_NAME_NATIONAL_ID_NUMBER,
                          value = "Unique national identification number such as a social "
                                  + "security number.")
    private String nationalIdNumber;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_LOGIN_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOGIN_ID, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_LOGIN_ID)
    @MsDescription.Column(name = COLUMN_NAME_LOGIN_ID, value = "Network login.")
    private String loginId;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = HeirarchyidAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORGANIZATION_NODE, nullable = true, insertable = true,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_ORGANIZATION_NODE,
                          value = "Where the employee is located in corporate hierarchy.")
    private Heirarchyid organizationNode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Max(SqlServerConstants.SMALLINT_MAX)
    @Min(SqlServerConstants.SMALLINT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORGANIZATION_LEVEL, nullable = true, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ORGANIZATION_LEVEL,
                          value = "The depth of the employee in the corporate hierarchy.")
    @MsComputedColumn("([OrganizationNode].[GetLevel]())")
    private Integer organizationLevel;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_JOB_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_TITLE, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_JOB_TITLE)
    @MsDescription.Column(name = COLUMN_NAME_JOB_TITLE,
                          value = "Work title such as Buyer or Sales Representative.")
    private String jobTitle;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BIRTH_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_BIRTH_DATE, value = "Date of birth.")
    private LocalDate birthDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MARITAL_STATUS, nullable = false, insertable = true,
            updatable = true, length = COLUMN_LENGTH_MARITAL_STATUS)
    @MsDescription.Column(name = COLUMN_NAME_MARITAL_STATUS, value = "M = Married, S = Single")
    private MaritalStatus maritalStatus;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GENDER, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_GENDER)
    @MsDescription.Column(name = COLUMN_NAME_GENDER, value = "M = Male, F = Female")
    private Gender gender;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_HIRE_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_HIRE_DATE, value = "Employee hired on this date.")
    private LocalDate hireDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALARIED_FLAG, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_SALARIED_FLAG,
                          value = "Job classification. 0 = Hourly, not exempt from collective "
                                  + "bargaining. 1 = Salaried, exempt from collective bargaining.")
    @MsDefault(name = "DF_Employee_SalariedFlag", value = "((1))")
    private Boolean salariedFlag;

    // ---------------------------------------------------------------------------------------------
    // CK_Employee_VacationHours: ([VacationHours]>=(-40) AND [VacationHours]<=(240))
    @Max(ATTRIBUTE_MAX_VACATION_HOURS)
    @Min(ATTRIBUTE_MIN_VACATION_HOURS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VACATION_HOURS, nullable = false, insertable = true,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_VACATION_HOURS,
                          value = "Number of available vacation hours.")
    @MsDefault(name = "DF_Employee_VacationHours", value = "((0))")
    private Integer vacationHours;

    // ---------------------------------------------------------------------------------------------
    // CK_Employee_SickLeaveHours: ([SickLeaveHours]>=(0) AND [SickLeaveHours]<=(120))
    @Max(ATTRIBUTE_MAX_SICK_LEAVE_HOURS)
    @Min(ATTRIBUTE_MIN_SICK_LEAVE_HOURS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SICK_LEAVE_HOURS, nullable = false, insertable = true,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_SICK_LEAVE_HOURS,
                          value = "Number of available sick leave hours.")
    @MsDefault(name = "DF_Employee_SickLeaveHours", value = "((0))")
    private Integer sickLeaveHours;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CURRENT_FLAG, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_CURRENT_FLAG, value = "0 = Inactive, 1 = Active")
    @MsDefault(name = "DF_Employee_CurrentFlag", value = "((1))")
    private Boolean currentFlag;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ROWGUID,
                          value = "ROWGUIDCOL number uniquely identifying the record. Used to "
                                  + "support a merge replication sample.")
    @MsDefault(name = "DF_Employee_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_Employee_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

