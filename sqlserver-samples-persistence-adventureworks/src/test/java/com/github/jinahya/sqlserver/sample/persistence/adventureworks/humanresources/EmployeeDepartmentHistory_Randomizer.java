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

import java.util.List;

/**
 * Randomizes a new {@link EmployeeDepartmentHistory}; the identifier, what it references, the dates, which the
 * {@code CK_EmployeeDepartmentHistory_EndDate} constraint relates, and what SQL Server fills are left out, which
 * leaves nothing to randomize. The persister does not use the instance; it persists one which
 * {@link EmployeeDepartmentHistory#of} creates with the instances it references, and dates of its own.
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
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_START_DATE,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_END_DATE,
                EmployeeDepartmentHistory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }
}
