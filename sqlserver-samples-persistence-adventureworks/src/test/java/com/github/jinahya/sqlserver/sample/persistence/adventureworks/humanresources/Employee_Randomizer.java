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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.common.Heirarchyid;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link Employee}; the identifier and the person, which the persister sets, the
 * organization node, which Podam can not build, and what SQL Server fills or derives are left out.
 */
class Employee_Randomizer
        extends PodamObjectRandomizer<Employee> {

    Employee_Randomizer() {
        super(Employee.class, List.of(
                Employee.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                Employee.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                Employee.ATTRIBUTE_NAME_ORGANIZATION_NODE,
                Employee.ATTRIBUTE_NAME_ORGANIZATION_LEVEL,
                Employee.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a node no installed employee has, and dates
     *         within the CHECK constraints.
     */
    @Override
    public Employee get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // no installed node is this deep
        instance.setOrganizationNode(Heirarchyid.parse(
                "/" + random.nextLong(1L, Integer.MAX_VALUE) + "/" +
                random.nextLong(1L, Integer.MAX_VALUE) + "/"));
        // CK_Employee_BirthDate: from 1930-01-01 to 18 years ago
        instance.setBirthDate(
                LocalDate.now().minusYears(18L).minusDays(random.nextLong(365L * 50)));
        // CK_Employee_HireDate: from 1996-07-01 to tomorrow
        instance.setHireDate(LocalDate.now().minusDays(random.nextLong(365L * 20)));
        return instance;
    }
}
