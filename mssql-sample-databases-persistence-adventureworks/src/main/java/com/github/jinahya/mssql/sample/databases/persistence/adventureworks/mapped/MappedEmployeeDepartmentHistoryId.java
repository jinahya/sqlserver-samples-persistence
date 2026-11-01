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

import java.io.Serializable;
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
public class MappedEmployeeDepartmentHistoryId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedEmployeeDepartmentHistoryId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedEmployeeDepartmentHistoryId that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(startDate, that.startDate) &&
               Objects.equals(departmentId, that.departmentId) &&
               Objects.equals(shiftId, that.shiftId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessEntityId, startDate, departmentId, shiftId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

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

    // ------------------------------------------------------------------------------------------------------- startDate

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

    // ---------------------------------------------------------------------------------------------------- departmentId

    /**
     * Returns current value of the {@code departmentId} attribute.
     *
     * @return current value of the {@code departmentId} attribute.
     */
    public Short getDepartmentId() {
        return departmentId;
    }

    /**
     * Replaces current value of the {@code departmentId} attribute with the specified value.
     *
     * @param departmentId new value for the {@code departmentId} attribute.
     */
    public void setDepartmentId(final Short departmentId) {
        this.departmentId = departmentId;
    }

    // --------------------------------------------------------------------------------------------------------- shiftId

    /**
     * Returns current value of the {@code shiftId} attribute.
     *
     * @return current value of the {@code shiftId} attribute.
     */
    public Short getShiftId() {
        return shiftId;
    }

    /**
     * Replaces current value of the {@code shiftId} attribute with the specified value.
     *
     * @param shiftId new value for the {@code shiftId} attribute.
     */
    public void setShiftId(final Short shiftId) {
        this.shiftId = shiftId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer businessEntityId;

    private LocalDate startDate;

    private Short departmentId;

    private Short shiftId;
}

