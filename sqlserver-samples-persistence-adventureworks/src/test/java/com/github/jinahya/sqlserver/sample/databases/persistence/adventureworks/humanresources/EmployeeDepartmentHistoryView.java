package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.humanresources;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.humanresources.MappedEmployeeDepartmentHistoryView;

import java.time.LocalDate;

/**
 * A plain class which holds a row of the {@code HumanResources.vEmployeeDepartmentHistory} view, which no entity can
 * map: the view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class EmployeeDepartmentHistoryView extends MappedEmployeeDepartmentHistoryView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected EmployeeDepartmentHistoryView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param businessEntityId the value of the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column.
     * @param title            the value of the {@value #COLUMN_NAME_TITLE} column.
     * @param firstName        the value of the {@value #COLUMN_NAME_FIRST_NAME} column.
     * @param middleName       the value of the {@value #COLUMN_NAME_MIDDLE_NAME} column.
     * @param lastName         the value of the {@value #COLUMN_NAME_LAST_NAME} column.
     * @param suffix           the value of the {@value #COLUMN_NAME_SUFFIX} column.
     * @param shift            the value of the {@value #COLUMN_NAME_SHIFT} column.
     * @param department       the value of the {@value #COLUMN_NAME_DEPARTMENT} column.
     * @param groupName        the value of the {@value #COLUMN_NAME_GROUP_NAME} column.
     * @param startDate        the value of the {@value #COLUMN_NAME_START_DATE} column.
     * @param endDate          the value of the {@value #COLUMN_NAME_END_DATE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public EmployeeDepartmentHistoryView(final Integer businessEntityId,
                                         final String title,
                                         final String firstName,
                                         final String middleName,
                                         final String lastName,
                                         final String suffix,
                                         final String shift,
                                         final String department,
                                         final String groupName,
                                         final LocalDate startDate,
                                         final LocalDate endDate) {
        super();
        setBusinessEntityId(businessEntityId);
        setTitle(title);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setSuffix(suffix);
        setShift(shift);
        setDepartment(department);
        setGroupName(groupName);
        setStartDate(startDate);
        setEndDate(endDate);
    }
}

