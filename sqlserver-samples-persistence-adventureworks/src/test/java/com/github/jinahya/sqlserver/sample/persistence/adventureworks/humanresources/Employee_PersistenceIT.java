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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import com.github.jinahya.sqlserver.sample.persistence.shared._Entity_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

class Employee_PersistenceIT
        extends _Entity_PersistenceIT<Employee, Integer> {

    Employee_PersistenceIT() {
        super(Employee.class, Integer.class, ___PersistenceIT_Utils.PERSISTENCE_UNIT_NAME);
    }

    // ---------------------------------------------------------------------------------------------
    private static Employee first(final EntityManager entityManager) {
        return entityManager.createQuery(
                        """
                                SELECT e
                                FROM HumanResourcesEmployee e
                                ORDER BY e.businessEntityId""",
                        Employee.class
                )
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    // ---------------------------------------------------------------------------------------------
    // the employee's own values go back in, but for LoginID, which the unique index on the
    // column takes as it is new
    @DisplayName("HumanResources.uspUpdateEmployeeLogin updates an employee")
    @Test
    void uspUpdateEmployeeLogin() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var employee = first(em);
            assumeThat(employee).as("an installed employee").isNotNull();
            final var loginId = employee.getLoginId() + "-updated";
            em.createNamedStoredProcedureQuery(
                            Employee.NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_LOGIN)
                    .setParameter(1, employee.getBusinessEntityId())
                    .setParameter(2, Optional.ofNullable(employee.getOrganizationNode())
                            .map(Heirarchyid::getBytes)
                            .orElse(null))
                    .setParameter(3, loginId)
                    .setParameter(4, employee.getJobTitle())
                    .setParameter(5, employee.getHireDate().atStartOfDay())
                    .setParameter(6, employee.getCurrentFlag())
                    .execute();
            // the procedure logs a failure to dbo.ErrorLog and raises nothing, so the row is
            // what tells
            final var organizationNode = employee.getOrganizationNode();
            em.clear();
            final var found = em.find(Employee.class, employee.getBusinessEntityId());
            assertThat(found.getLoginId()).isEqualTo(loginId);
            assertThat(found.getOrganizationNode()).isEqualTo(organizationNode);
            return null;
        }));
    }

    // the employee's own values go back in, but for MaritalStatus, which
    // CK_Employee_MaritalStatus takes either way
    @DisplayName("HumanResources.uspUpdateEmployeePersonalInfo updates an employee")
    @Test
    void uspUpdateEmployeePersonalInfo() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var employee = first(em);
            assumeThat(employee).as("an installed employee").isNotNull();
            final var maritalStatus = employee.getMaritalStatus() == MappedEmployee.MaritalStatus.M
                    ? MappedEmployee.MaritalStatus.S
                    : MappedEmployee.MaritalStatus.M;
            em.createNamedStoredProcedureQuery(
                            Employee.NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_PERSONAL_INFO)
                    .setParameter(1, employee.getBusinessEntityId())
                    .setParameter(2, employee.getNationalIdNumber())
                    .setParameter(3, employee.getBirthDate().atStartOfDay())
                    .setParameter(4, maritalStatus.name())
                    .setParameter(5, employee.getGender().name())
                    .execute();
            // the procedure logs a failure to dbo.ErrorLog and raises nothing, so the row is
            // what tells
            em.clear();
            final var found = em.find(Employee.class, employee.getBusinessEntityId());
            assertThat(found.getMaritalStatus()).isEqualTo(maritalStatus);
            return null;
        }));
    }
}
