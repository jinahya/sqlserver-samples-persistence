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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the
 * {@code HumanResources.EmployeeDepartmentHistory} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code HumanResources.EmployeeDepartmentHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BusinessEntityID}</th>
 *     <td>Employee identification number. Foreign key to Employee.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code DepartmentID}</th>
 *     <td>Department in which the employee worked including currently. Foreign key to
 *     Department.DepartmentID.</td></tr>
 * <tr><th scope="row">{@code ShiftID}</th>
 *     <td>Identifies which 8-hour shift the employee works. Foreign key to
 *     Shift.Shift.ID.</td></tr>
 * <tr><th scope="row">{@code StartDate}</th>
 *     <td>Date the employee started work in the department.</td></tr>
 * <tr><th scope="row">{@code EndDate}</th>
 *     <td>Date the employee left the department. NULL = Current department.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code HumanResources.EmployeeDepartmentHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedEmployeeDepartmentHistory.TABLE_SCHEMA,
        table = MappedEmployeeDepartmentHistory.TABLE_NAME,
        value = "Employee department transfers."
)
public abstract class MappedEmployeeDepartmentHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "EmployeeDepartmentHistory";

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

    // -------------------------------------------------------------------------------- DepartmentID

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEPARTMENT_ID = "DepartmentID";

    /**
     * The minimum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_DEPARTMENT_ID = Short.MIN_VALUE;

    /**
     * The maximum value of the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_DEPARTMENT_ID = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEPARTMENT_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    // ------------------------------------------------------------------------------------- ShiftID

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIFT_ID = "ShiftID";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SHIFT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_SHIFT_ID = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_SHIFT_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_SHIFT_ID = +255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIFT_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIFT_ID = "shiftId";

    // ----------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

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
    protected MappedEmployeeDepartmentHistory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",departmentId=" + departmentId
               + ",shiftId=" + shiftId
               + ",startDate=" + startDate
               + ",endDate=" + endDate
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
        if (!(obj instanceof MappedEmployeeDepartmentHistory that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getStartDate(), that.getStartDate()) &&
               Objects.equals(getDepartmentId(), that.getDepartmentId()) &&
               Objects.equals(getShiftId(), that.getShiftId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getStartDate(), getDepartmentId(), getShiftId());
    }

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_END_DATE} attribute is {@code null} or not before
     * the {@value #ATTRIBUTE_NAME_START_DATE} attribute, as the table's
     * {@code CK_EmployeeDepartmentHistory_EndDate} constraint requires.
     * {@snippet lang = "sql":
     * ([EndDate]>=[StartDate] OR [EndDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_END_DATE} attribute is {@code null}, or
     *         the {@value #ATTRIBUTE_NAME_START_DATE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_END_DATE} attribute is not before the
     *         {@value #ATTRIBUTE_NAME_START_DATE} attribute; {@code false} otherwise.
     */
    @AssertTrue(message = "endDate must be null or not before startDate")
    protected boolean isEndDateValid() {
        if (endDate == null || startDate == null) {
            return true;
        }
        return !endDate.isBefore(startDate);
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getBusinessEntityId() {
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

    // -------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute with the specified
     * value.
     *
     * @param departmentId new value for {@value #ATTRIBUTE_NAME_DEPARTMENT_ID} attribute.
     */
    protected void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // ------------------------------------------------------------------------------------- shiftId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIFT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIFT_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getShiftId() {
        return shiftId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIFT_ID} attribute with the specified
     * value.
     *
     * @param shiftId new value for {@value #ATTRIBUTE_NAME_SHIFT_ID} attribute.
     */
    protected void setShiftId(final Integer shiftId) {
        this.shiftId = shiftId;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified
     * value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    protected void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // ------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified
     * value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
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
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                          value = "Employee identification number. Foreign key to "
                                  + "Employee.BusinessEntityID.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(COLUMN_MAX_DEPARTMENT_ID)
    @Min(COLUMN_MIN_DEPARTMENT_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_DEPARTMENT_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_DEPARTMENT_ID,
                          value = "Department in which the employee worked including currently. "
                                  + "Foreign key to Department.DepartmentID.")
    private Integer departmentId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(COLUMN_MAX_SHIFT_ID)
    @Min(COLUMN_MIN_SHIFT_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_SHIFT_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SHIFT_ID,
                          value = "Identifies which 8-hour shift the employee works. Foreign "
                                  + "key to Shift.Shift.ID.")
    private Integer shiftId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_START_DATE, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_START_DATE,
                          value = "Date the employee started work in the department.")
    private LocalDate startDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_END_DATE, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_END_DATE,
                          value = "Date the employee left the department. NULL = Current "
                                  + "department.")
    private LocalDate endDate;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_EmployeeDepartmentHistory_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

