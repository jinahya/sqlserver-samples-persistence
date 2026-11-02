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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.vEmployeeDepartment} view.
 * <p>
 * Identifier: the view declares no key; BusinessEntityID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable,
 * and none but the identifier, of which EclipseLink requires a writable mapping, is insertable; an INSERT or an UPDATE
 * of a whole row affects more than one of its base tables (error 4405).
 */
@MappedSuperclass
public abstract class MappedEmployeeDepartmentView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vEmployeeDepartment";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ----------------------------------------------------------------------------------------------------------- Title

    /**
     * The name of the {@code nvarchar(8)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TITLE = "Title";

    /**
     * The length of the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TITLE = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // ------------------------------------------------------------------------------------------------------- FirstName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FirstName";

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    // ------------------------------------------------------------------------------------------------------ MiddleName

    /**
     * The name of the {@code Name (nvarchar)(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIDDLE_NAME = "MiddleName";

    /**
     * The length of the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MIDDLE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIDDLE_NAME = "middleName";

    // -------------------------------------------------------------------------------------------------------- LastName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LastName";

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    // ---------------------------------------------------------------------------------------------------------- Suffix

    /**
     * The name of the {@code nvarchar(10)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUFFIX = "Suffix";

    /**
     * The length of the {@value #COLUMN_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUFFIX = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUFFIX = "suffix";

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

    // ------------------------------------------------------------------------------------------------------ Department

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT = "Department";

    /**
     * The length of the {@value #COLUMN_NAME_DEPARTMENT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DEPARTMENT = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    // ------------------------------------------------------------------------------------------------------- GroupName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROUP_NAME = "GroupName";

    /**
     * The length of the {@value #COLUMN_NAME_GROUP_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_GROUP_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROUP_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROUP_NAME = "groupName";

    // ------------------------------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmployeeDepartmentView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",title=" + title
               + ",firstName=" + firstName
               + ",middleName=" + middleName
               + ",lastName=" + lastName
               + ",suffix=" + suffix
               + ",jobTitle=" + jobTitle
               + ",department=" + department
               + ",groupName=" + groupName
               + ",startDate=" + startDate
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
        if (!(obj instanceof MappedEmployeeDepartmentView that)) {
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
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ----------------------------------------------------------------------------------------------------------- title

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TITLE} attribute with the specified value.
     *
     * @param title new value for {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    protected void setTitle(final String title) {
        this.title = title;
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with the specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    protected void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    // ------------------------------------------------------------------------------------------------------ middleName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute with the specified value.
     *
     * @param middleName new value for {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    protected void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with the specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    protected void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    // ---------------------------------------------------------------------------------------------------------- suffix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute with the specified value.
     *
     * @param suffix new value for {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    protected void setSuffix(final String suffix) {
        this.suffix = suffix;
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
    protected void setJobTitle(final String jobTitle) {
        this.jobTitle = jobTitle;
    }

    // ------------------------------------------------------------------------------------------------------ department

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute with the specified value.
     *
     * @param department new value for {@value #ATTRIBUTE_NAME_DEPARTMENT} attribute.
     */
    protected void setDepartment(final String department) {
        this.department = department;
    }

    // ------------------------------------------------------------------------------------------------------- groupName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute with the specified value.
     *
     * @param groupName new value for {@value #ATTRIBUTE_NAME_GROUP_NAME} attribute.
     */
    protected void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    protected void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only but for an INSERT: SQL Server does not write this view, and EclipseLink requires a writable mapping
    // of every identifier column; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TITLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TITLE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_TITLE
    )
    private String title;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_FIRST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_MIDDLE_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MIDDLE_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_MIDDLE_NAME
    )
    private String middleName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_LAST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_LAST_NAME
    )
    private String lastName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SUFFIX)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUFFIX,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SUFFIX
    )
    private String suffix;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_JOB_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_JOB_TITLE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_JOB_TITLE
    )
    private String jobTitle;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DEPARTMENT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEPARTMENT,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DEPARTMENT
    )
    private String department;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_GROUP_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_GROUP_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_GROUP_NAME
    )
    private String groupName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate startDate;
}

