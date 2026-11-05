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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * An entity which maps the {@code HumanResources.Employee} table.
 * <p>
 * Three named stored procedure queries call the procedures of {@code HumanResources}, each of which
 * updates an employee: {@value #NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_HIRE_INFO}, which
 * also adds a row to {@code HumanResources.EmployeePayHistory}, in one transaction;
 * {@value #NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_LOGIN}; and
 * {@value #NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_PERSONAL_INFO}. Their parameters are
 * positional, in the order each procedure declares them; every one is required. A procedure logs a
 * failure to {@code dbo.ErrorLog} and raises nothing.
 */
@NamedStoredProcedureQuery(
        name = Employee.NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_HIRE_INFO,
        procedureName = "HumanResources.uspUpdateEmployeeHireInfo",
        parameters = {
                @StoredProcedureParameter(type = Integer.class),       // @BusinessEntityID int
                @StoredProcedureParameter(type = String.class),        // @JobTitle nvarchar(50)
                @StoredProcedureParameter(type = LocalDateTime.class), // @HireDate datetime
                @StoredProcedureParameter(type = LocalDateTime.class), // @RateChangeDate datetime
                @StoredProcedureParameter(type = BigDecimal.class),    // @Rate money
                @StoredProcedureParameter(type = Integer.class),       // @PayFrequency tinyint
                @StoredProcedureParameter(type = Boolean.class)        // @CurrentFlag dbo.Flag
        }
)
@NamedStoredProcedureQuery(
        name = Employee.NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_LOGIN,
        procedureName = "HumanResources.uspUpdateEmployeeLogin",
        parameters = {
                @StoredProcedureParameter(type = Integer.class),       // @BusinessEntityID int
                @StoredProcedureParameter(
                        type = byte[].class),        // @OrganizationNode hierarchyid
                @StoredProcedureParameter(type = String.class),        // @LoginID nvarchar(256)
                @StoredProcedureParameter(type = String.class),        // @JobTitle nvarchar(50)
                @StoredProcedureParameter(type = LocalDateTime.class), // @HireDate datetime
                @StoredProcedureParameter(type = Boolean.class)        // @CurrentFlag dbo.Flag
        }
)
@NamedStoredProcedureQuery(
        name = Employee.NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_PERSONAL_INFO,
        procedureName = "HumanResources.uspUpdateEmployeePersonalInfo",
        parameters = {
                @StoredProcedureParameter(type = Integer.class),       // @BusinessEntityID int
                @StoredProcedureParameter(
                        type = String.class),        // @NationalIDNumber nvarchar(15)
                @StoredProcedureParameter(type = LocalDateTime.class), // @BirthDate datetime
                @StoredProcedureParameter(type = String.class),        // @MaritalStatus nchar(1)
                @StoredProcedureParameter(type = String.class)         // @Gender nchar(1)
        }
)
@Entity(name = "HumanResourcesEmployee")
@Table(schema = MappedEmployee.TABLE_SCHEMA,
       name = MappedEmployee.TABLE_NAME,
       comment = "Employee information such as salary, department, and title.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Employee_LoginID",
                       columnNames = {
                               Employee.COLUMN_NAME_LOGIN_ID
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Employee_NationalIDNumber",
                       columnNames = {
                               Employee.COLUMN_NAME_NATIONAL_ID_NUMBER
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Employee_rowguid",
                       columnNames = {
                               Employee.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_Employee_BirthDate",
                       constraint = "([BirthDate]>='1930-01-01' AND "
                                    + "[BirthDate]<=dateadd(year,(-18),getdate()))"
               ),
               @CheckConstraint(
                       name = "CK_Employee_Gender",
                       constraint = "(upper([Gender])='F' OR upper([Gender])='M')"
               ),
               @CheckConstraint(
                       name = "CK_Employee_HireDate",
                       constraint = "([HireDate]>='1996-07-01' AND "
                                    + "[HireDate]<=dateadd(day,(1),getdate()))"
               ),
               @CheckConstraint(
                       name = "CK_Employee_MaritalStatus",
                       constraint = "(upper([MaritalStatus])='S' OR upper([MaritalStatus])='M')"
               ),
               @CheckConstraint(
                       name = "CK_Employee_SickLeaveHours",
                       constraint = "([SickLeaveHours]>=(0) AND [SickLeaveHours]<=(120))"
               ),
               @CheckConstraint(
                       name = "CK_Employee_VacationHours",
                       constraint = "([VacationHours]>=(-40) AND [VacationHours]<=(240))"
               )
       }
)
public class Employee
        extends MappedEmployee {

    /**
     * The name of the attribute of the {@code FK_Employee_Person_BusinessEntityID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the named stored procedure query which calls
     * {@code HumanResources.uspUpdateEmployeeHireInfo}. The value is {@value}.
     */
    public static final String NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_HIRE_INFO =
            "HumanResourcesEmployee.uspUpdateEmployeeHireInfo";

    /**
     * The name of the named stored procedure query which calls
     * {@code HumanResources.uspUpdateEmployeeLogin}; its {@code @OrganizationNode} takes the bytes
     * of a {@code hierarchyid}, which SQL Server converts from {@code varbinary}. The value is
     * {@value}.
     */
    public static final String NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_LOGIN =
            "HumanResourcesEmployee.uspUpdateEmployeeLogin";

    /**
     * The name of the named stored procedure query which calls
     * {@code HumanResources.uspUpdateEmployeePersonalInfo}; its {@code @MaritalStatus} and
     * {@code @Gender} take the names of the constants. The value is {@value}.
     */
    public static final String NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_PERSONAL_INFO =
            "HumanResourcesEmployee.uspUpdateEmployeePersonalInfo";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Employee() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code Person} which the {@code FK_Employee_Person_BusinessEntityID} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Employee_Person_BusinessEntityID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code Person} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final Person businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Person businessEntity;
}

