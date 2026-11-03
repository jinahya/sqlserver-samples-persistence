package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;


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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Random_TestUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link WorkOrderRouting}; the work order, its product, and the location, which the persister sets,
 * and what SQL Server fills are left out.
 */
class WorkOrderRouting_Randomizer
        extends PodamObjectRandomizer<WorkOrderRouting> {

    WorkOrderRouting_Randomizer() {
        super(WorkOrderRouting.class, List.of(
                WorkOrderRouting.ATTRIBUTE_NAME_WORK_ORDER_ID,
                WorkOrderRouting.ATTRIBUTE_NAME_PRODUCT_ID,
                WorkOrderRouting.ATTRIBUTE_NAME_LOCATION_ID,
                WorkOrderRouting.ATTRIBUTE_NAME_WORK_ORDER,
                WorkOrderRouting.ATTRIBUTE_NAME_LOCATION,
                WorkOrderRouting.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK constraints, and its dates in order.
     */
    @Override
    public WorkOrderRouting get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // CK_WorkOrderRouting_ScheduledEndDate: ScheduledEndDate >= ScheduledStartDate
        instance.setScheduledStartDate(__Random_TestUtils.dateTime());
        instance.setScheduledEndDate(instance.getScheduledStartDate().plusDays(random.nextLong(366L)));
        // CK_WorkOrderRouting_ActualEndDate: ActualEndDate, when both are, >= ActualStartDate; here, only with one
        instance.setActualStartDate(random.nextBoolean() ? null : __Random_TestUtils.dateTime());
        instance.setActualEndDate(Optional.ofNullable(instance.getActualStartDate())
                                          .filter(d -> random.nextBoolean())
                                          .map(d -> d.plusDays(random.nextLong(366L)))
                                          .orElse(null));
        // ActualResourceHrs, when any, a decimal(9, 4), >= 0; PlannedCost and ActualCost, when any, both money, > 0
        instance.setActualResourceHrs(random.nextBoolean() ? null : __Random_TestUtils.positive(4, 4));
        instance.setPlannedCost(__Random_TestUtils.positive(5, 4));
        instance.setActualCost(random.nextBoolean() ? null : __Random_TestUtils.positive(5, 4));
        return instance;
    }
}
