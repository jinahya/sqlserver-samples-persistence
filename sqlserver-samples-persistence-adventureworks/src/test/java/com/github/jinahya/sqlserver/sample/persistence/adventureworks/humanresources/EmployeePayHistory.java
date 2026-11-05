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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.EmployeePayHistoryId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployeePayHistory;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

/**
 * An entity which maps the {@code HumanResources.EmployeePayHistory} table.
 * <p>
 * The application adds a row through
 * {@link Employee#NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_HIRE_INFO}.
 */
@Entity(name = "HumanResourcesEmployeePayHistory")
@Table(schema = MappedEmployeePayHistory.TABLE_SCHEMA,
       name = MappedEmployeePayHistory.TABLE_NAME,
       comment = "Employee pay history.",
       check = {
               @CheckConstraint(
                       name = "CK_EmployeePayHistory_PayFrequency",
                       constraint = "([PayFrequency]=(2) OR [PayFrequency]=(1))"
               ),
               @CheckConstraint(
                       name = "CK_EmployeePayHistory_Rate",
                       constraint = "([Rate]>=(6.50) AND [Rate]<=(200.00))"
               )
       }
)
@IdClass(EmployeePayHistoryId.class)
public class EmployeePayHistory
        extends MappedEmployeePayHistory {

    /**
     * The name of the attribute of the {@code FK_EmployeePayHistory_Employee_BusinessEntityID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected EmployeePayHistory() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Employee} which the
     * {@code FK_EmployeePayHistory_Employee_BusinessEntityID} foreign key references.
     *
     * @return the {@code Employee} referenced; {@code null} when none is.
     */
    public Employee getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Employee} which the
     * {@code FK_EmployeePayHistory_Employee_BusinessEntityID} foreign key references, and the value
     * of the column it consists of.
     *
     * @param businessEntity the {@code Employee} to reference; {@code null} for none.
     */
    protected void setBusinessEntity(final Employee businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(Optional.ofNullable(businessEntity).map(Employee::getBusinessEntityId)
                                    .orElse(null));
    }

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
}

