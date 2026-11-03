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

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The identifier class of the {@code HumanResources.EmployeeDepartmentHistory} table, whose rows are identified by
 * {@code BusinessEntityID}, {@code StartDate}, {@code DepartmentID}, {@code ShiftID}.
 * <p>
 * An entity which extends {@link MappedEmployeeDepartmentHistory} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class EmployeeDepartmentHistoryId {

    /**
     * The name of the attribute which maps the
     * {@value MappedEmployeeDepartmentHistory#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    /**
     * The name of the attribute which maps the
     * {@value MappedEmployeeDepartmentHistory#COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    /**
     * The name of the attribute which maps the
     * {@value MappedEmployeeDepartmentHistory#COLUMN_NAME_DEPARTMENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT_ID = "departmentId";

    /**
     * The name of the attribute which maps the
     * {@value MappedEmployeeDepartmentHistory#COLUMN_NAME_SHIFT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIFT_ID = "shiftId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public EmployeeDepartmentHistoryId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",startDate=" + startDate
               + ",departmentId=" + departmentId
               + ",shiftId=" + shiftId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EmployeeDepartmentHistoryId that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getStartDate(), that.getStartDate()) &&
               Objects.equals(getDepartmentId(), that.getDepartmentId()) &&
               Objects.equals(getShiftId(), that.getShiftId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getStartDate(), getDepartmentId(), getShiftId());
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of the {@code businessEntityId} attribute.
     *
     * @return current value of the {@code businessEntityId} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of the {@code businessEntityId} attribute with the specified value.
     *
     * @param businessEntityId new value for the {@code businessEntityId} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of the {@code startDate} attribute.
     *
     * @return current value of the {@code startDate} attribute.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of the {@code startDate} attribute with the specified value.
     *
     * @param startDate new value for the {@code startDate} attribute.
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // -------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of the {@code departmentId} attribute.
     *
     * @return current value of the {@code departmentId} attribute.
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of the {@code departmentId} attribute with the specified value.
     *
     * @param departmentId new value for the {@code departmentId} attribute.
     */
    public void setDepartmentId(final Integer departmentId) {
        this.departmentId = departmentId;
    }

    // ------------------------------------------------------------------------------------- shiftId

    /**
     * Returns current value of the {@code shiftId} attribute.
     *
     * @return current value of the {@code shiftId} attribute.
     */
    public Integer getShiftId() {
        return shiftId;
    }

    /**
     * Replaces current value of the {@code shiftId} attribute with the specified value.
     *
     * @param shiftId new value for the {@code shiftId} attribute.
     */
    public void setShiftId(final Integer shiftId) {
        this.shiftId = shiftId;
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * The value of the {@code businessEntityId} attribute.
     */
    @NotNull
    private Integer businessEntityId;

    /**
     * The value of the {@code startDate} attribute.
     */
    @NotNull
    private LocalDate startDate;

    /**
     * The value of the {@code departmentId} attribute.
     */
    @NotNull
    private Integer departmentId;

    /**
     * The value of the {@code shiftId} attribute.
     */
    @NotNull
    private Integer shiftId;
}

