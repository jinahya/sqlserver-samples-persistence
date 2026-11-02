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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedDepartment;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployeeDepartmentHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployeeDepartmentHistoryId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedShift;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code HumanResources.EmployeeDepartmentHistory} table.
 */
@Entity(name = "HumanResourcesEmployeeDepartmentHistory")
@Table(schema = MappedEmployeeDepartmentHistory.TABLE_SCHEMA, name = MappedEmployeeDepartmentHistory.TABLE_NAME)
@IdClass(MappedEmployeeDepartmentHistoryId.class)
public class EmployeeDepartmentHistory extends MappedEmployeeDepartmentHistory {

    /**
     * The name of the attribute of the {@code FK_EmployeeDepartmentHistory_Department_DepartmentID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    /**
     * The name of the attribute of the {@code FK_EmployeeDepartmentHistory_Employee_BusinessEntityID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_EmployeeDepartmentHistory_Shift_ShiftID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIFT = "shift";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EmployeeDepartmentHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------ department

    /**
     * Returns the {@code Department} which the {@code FK_EmployeeDepartmentHistory_Department_DepartmentID} foreign key
     * references.
     *
     * @return the {@code Department} referenced; {@code null} when none is.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Replaces the {@code Department} which the {@code FK_EmployeeDepartmentHistory_Department_DepartmentID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param department the {@code Department} to reference; {@code null} for none.
     */
    public void setDepartment(final Department department) {
        this.department = department;
        setDepartmentId(department == null || department.getDepartmentId() == null
                        ? null : department.getDepartmentId().intValue());
    }

    // -------------------------------------------------------------------------------------------------- businessEntity

    /**
     * Returns the {@code Employee} which the {@code FK_EmployeeDepartmentHistory_Employee_BusinessEntityID} foreign key
     * references.
     *
     * @return the {@code Employee} referenced; {@code null} when none is.
     */
    public Employee getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Employee} which the {@code FK_EmployeeDepartmentHistory_Employee_BusinessEntityID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code Employee} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final Employee businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ----------------------------------------------------------------------------------------------------------- shift

    /**
     * Returns the {@code Shift} which the {@code FK_EmployeeDepartmentHistory_Shift_ShiftID} foreign key references.
     *
     * @return the {@code Shift} referenced; {@code null} when none is.
     */
    public Shift getShift() {
        return shift;
    }

    /**
     * Replaces the {@code Shift} which the {@code FK_EmployeeDepartmentHistory_Shift_ShiftID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param shift the {@code Shift} to reference; {@code null} for none.
     */
    public void setShift(final Shift shift) {
        this.shift = shift;
        setShiftId(shift == null || shift.getShiftId() == null ? null : shift.getShiftId().intValue());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                referencedColumnName = MappedDepartment.COLUMN_NAME_DEPARTMENT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Department department;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedEmployee.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Employee businessEntity;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIFT_ID,
                referencedColumnName = MappedShift.COLUMN_NAME_SHIFT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Shift shift;
}

