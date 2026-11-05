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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.EmployeeDepartmentHistoryId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedDepartment;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployeeDepartmentHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedShift;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code HumanResources.EmployeeDepartmentHistory} table.
 */
@Entity(name = "HumanResourcesEmployeeDepartmentHistory")
@Table(schema = MappedEmployeeDepartmentHistory.TABLE_SCHEMA,
       name = MappedEmployeeDepartmentHistory.TABLE_NAME,
       comment = "Employee department transfers.",
       check = {
               @CheckConstraint(
                       name = "CK_EmployeeDepartmentHistory_EndDate",
                       constraint = "([EndDate]>=[StartDate] OR [EndDate] IS NULL)"
               )
       }
)
@IdClass(EmployeeDepartmentHistoryId.class)
public class EmployeeDepartmentHistory
        extends MappedEmployeeDepartmentHistory {

    /**
     * The name of the attribute of the {@code FK_EmployeeDepartmentHistory_Department_DepartmentID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEPARTMENT = "department";

    /**
     * The name of the attribute of the
     * {@code FK_EmployeeDepartmentHistory_Employee_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_EmployeeDepartmentHistory_Shift_ShiftID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIFT = "shift";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references the specified employee, department and shift, from
     * the specified date.
     *
     * @param businessEntity the {@code Employee} to reference.
     * @param startDate      the value of the {@value #COLUMN_NAME_START_DATE} column.
     * @param department     the {@code Department} to reference.
     * @param shift          the {@code Shift} to reference.
     * @return a new instance which references {@code businessEntity}, {@code department} and
     *         {@code shift}, from {@code startDate}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static EmployeeDepartmentHistory of(final Employee businessEntity,
                                               final LocalDate startDate,
                                               final Department department,
                                               final Shift shift) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        Objects.requireNonNull(startDate, "startDate is null");
        Objects.requireNonNull(department, "department is null");
        Objects.requireNonNull(shift, "shift is null");
        final var instance = new EmployeeDepartmentHistory();
        instance.setBusinessEntity(businessEntity);
        instance.setStartDate(startDate);
        instance.setDepartment(department);
        instance.setShift(shift);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EmployeeDepartmentHistory() {
        super();
    }

    // ---------------------------------------------------------------------------------- department

    /**
     * Returns the {@code Department} which the
     * {@code FK_EmployeeDepartmentHistory_Department_DepartmentID} foreign key references.
     *
     * @return the {@code Department} referenced; {@code null} when none is.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Replaces the {@code Department} which the
     * {@code FK_EmployeeDepartmentHistory_Department_DepartmentID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param department the {@code Department} to reference; {@code null} for none.
     */
    private void setDepartment(final Department department) {
        this.department = department;
        setDepartmentId(Optional.ofNullable(department)
                                .map(MappedDepartment::getDepartmentId)
                                .orElse(null));
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Employee} which the
     * {@code FK_EmployeeDepartmentHistory_Employee_BusinessEntityID} foreign key references.
     *
     * @return the {@code Employee} referenced; {@code null} when none is.
     */
    public Employee getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Employee} which the
     * {@code FK_EmployeeDepartmentHistory_Employee_BusinessEntityID} foreign key references, and
     * the value of the column it consists of.
     *
     * @param businessEntity the {@code Employee} to reference; {@code null} for none.
     */
    private void setBusinessEntity(final Employee businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(
                Optional.ofNullable(businessEntity).map(MappedEmployee::getBusinessEntityId)
                        .orElse(null));
    }

    // --------------------------------------------------------------------------------------- shift

    /**
     * Returns the {@code Shift} which the {@code FK_EmployeeDepartmentHistory_Shift_ShiftID}
     * foreign key references.
     *
     * @return the {@code Shift} referenced; {@code null} when none is.
     */
    public Shift getShift() {
        return shift;
    }

    /**
     * Replaces the {@code Shift} which the {@code FK_EmployeeDepartmentHistory_Shift_ShiftID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param shift the {@code Shift} to reference; {@code null} for none.
     */
    private void setShift(final Shift shift) {
        this.shift = shift;
        setShiftId(Optional.ofNullable(shift).map(MappedShift::getShiftId).orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DEPARTMENT_ID,
                referencedColumnName = MappedDepartment.COLUMN_NAME_DEPARTMENT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Department department;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedEmployee.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Employee businessEntity;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIFT_ID,
                referencedColumnName = MappedShift.COLUMN_NAME_SHIFT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Shift shift;
}

