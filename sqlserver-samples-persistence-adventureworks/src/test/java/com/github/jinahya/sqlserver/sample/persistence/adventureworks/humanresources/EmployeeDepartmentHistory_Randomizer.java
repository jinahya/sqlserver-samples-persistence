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

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link EmployeeDepartmentHistory}; the identifier and what it references, which the persister sets,
 * and what SQL Server fills are left out.
 */
class EmployeeDepartmentHistory_Randomizer
        extends PodamObjectRandomizer<EmployeeDepartmentHistory> {

    EmployeeDepartmentHistory_Randomizer() {
        super(EmployeeDepartmentHistory.class, List.of(
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_DEPARTMENT_ID,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_SHIFT_ID,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_DEPARTMENT,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_SHIFT,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has its dates in order.
     */
    @Override
    public EmployeeDepartmentHistory get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // CK_EmployeeDepartmentHistory_EndDate: EndDate, when any, >= StartDate
        instance.setStartDate(LocalDate.now().minusDays(random.nextLong(365L * 10)));
        instance.setEndDate(random.nextBoolean()
                            ? null
                            : instance.getStartDate().plusDays(random.nextLong(366L)));
        return instance;
    }
}
