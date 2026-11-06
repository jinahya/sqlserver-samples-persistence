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
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link WorkOrder}; the generated identifier, what SQL Server fills or derives,
 * and what it references, which the persister sets, are left out.
 */
class WorkOrder_Randomizer
        extends PodamObjectRandomizer<WorkOrder> {

    WorkOrder_Randomizer() {
        super(WorkOrder.class, List.of(
                WorkOrder.ATTRIBUTE_NAME_WORK_ORDER_ID,
                WorkOrder.ATTRIBUTE_NAME_PRODUCT_ID,
                WorkOrder.ATTRIBUTE_NAME_STOCKED_QTY,
                WorkOrder.ATTRIBUTE_NAME_SCRAP_REASON_ID,
                WorkOrder.ATTRIBUTE_NAME_PRODUCT,
                WorkOrder.ATTRIBUTE_NAME_SCRAP_REASON,
                WorkOrder.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has its dates within their columns, and in order.
     */
    @Override
    public WorkOrder get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // CK_WorkOrder_EndDate: EndDate, when any, >= StartDate
        instance.setStartDate(__Random_TestUtils.dateTime());
        instance.setEndDate(random.nextBoolean() ? null : instance.getStartDate()
                .plusDays(random.nextLong(366L)));
        instance.setDueDate(instance.getStartDate().plusDays(random.nextLong(366L)));
        return instance;
    }
}
