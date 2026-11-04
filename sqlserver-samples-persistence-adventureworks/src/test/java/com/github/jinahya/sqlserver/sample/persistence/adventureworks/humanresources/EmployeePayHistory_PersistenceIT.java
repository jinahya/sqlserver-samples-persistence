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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks._Entity_PersistenceIT;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.EmployeePayHistoryId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.github.jinahya.persistence.util.JinahyaEntityManagerFactoryUtils.applyEntityManagerInTransactionAndRollback;
import static com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils.applyEntityManagerFactory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

class EmployeePayHistory_PersistenceIT
        extends _Entity_PersistenceIT<EmployeePayHistory> {

    EmployeePayHistory_PersistenceIT() {
        super(EmployeePayHistory.class);
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * Does nothing; the application inserts into {@code HumanResources.EmployeePayHistory} only
     * through {@code HumanResources.uspUpdateEmployeeHireInfo}, and the entity is read-only.
     */
    @Disabled("read-only: HumanResources.uspUpdateEmployeeHireInfo inserts into it")
    @Override
    @Test
    protected void persistRandomizedEntityInstance__() {
        // empty
    }

    // the employee's own JobTitle, HireDate and CurrentFlag go back in, so only the new pay row
    // changes anything
    @DisplayName("a row HumanResources.uspUpdateEmployeeHireInfo inserts is found as an entity")
    @Test
    void uspUpdateEmployeeHireInfo() {
        applyEntityManagerFactory(emf -> applyEntityManagerInTransactionAndRollback(emf, em -> {
            final var employee = em.createQuery("""
                                                        SELECT e
                                                        FROM HumanResourcesEmployee e
                                                        ORDER BY e.businessEntityId""",
                                                Employee.class)
                    .setMaxResults(1)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);
            assumeThat(employee).as("an installed employee").isNotNull();
            // datetime keeps no more than a 300th of a second; a whole second comes back as it
            // went in
            final var rateChangeDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            final var rate = new BigDecimal("12.3400");
            final var payFrequency = 2;
            em.createNamedStoredProcedureQuery(
                            Employee.NAMED_STORED_PROCEDURE_QUERY_USP_UPDATE_EMPLOYEE_HIRE_INFO)
                    .setParameter(1, employee.getBusinessEntityId())
                    .setParameter(2, employee.getJobTitle())
                    .setParameter(3, employee.getHireDate().atStartOfDay())
                    .setParameter(4, rateChangeDate)
                    .setParameter(5, rate)
                    .setParameter(6, payFrequency)
                    .setParameter(7, employee.getCurrentFlag())
                    .execute();
            // the procedure logs a failure to dbo.ErrorLog and raises nothing, so the row is
            // what tells
            final var id = new EmployeePayHistoryId();
            id.setBusinessEntityId(employee.getBusinessEntityId());
            id.setRateChangeDate(rateChangeDate);
            em.clear();
            final var found = em.find(EmployeePayHistory.class, id);
            assertThat(found).as("the %s inserted, by %s", EmployeePayHistory.class.getSimpleName(),
                                 id).isNotNull();
            assertThat(found.getRate()).isEqualByComparingTo(rate);
            assertThat(found.getPayFrequency()).isEqualTo(payFrequency);
            assertThat(found.getModifiedDate()).isNotNull();
            return null;
        }));
    }
}
