package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedDailyProductionScheduleView;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A plain class which holds a row of the {@code dbo.vw_DailyProductionSchedule} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class DailyProductionScheduleView extends MappedDailyProductionScheduleView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected DailyProductionScheduleView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param scheduledDate     the value of the {@value #COLUMN_NAME_SCHEDULED_DATE} column.
     * @param dayOfWeek         the value of the {@value #COLUMN_NAME_DAY_OF_WEEK} column.
     * @param workCenterName    the value of the {@value #COLUMN_NAME_WORK_CENTER_NAME} column.
     * @param workOrderNumber   the value of the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column.
     * @param status            the value of the {@value #COLUMN_NAME_STATUS} column.
     * @param itemCode          the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName          the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param quantityToProduce the value of the {@value #COLUMN_NAME_QUANTITY_TO_PRODUCE} column.
     * @param priority          the value of the {@value #COLUMN_NAME_PRIORITY} column.
     * @param estimatedDays     the value of the {@value #COLUMN_NAME_ESTIMATED_DAYS} column.
     * @param productionValue   the value of the {@value #COLUMN_NAME_PRODUCTION_VALUE} column.
     * @param productionStatus  the value of the {@value #COLUMN_NAME_PRODUCTION_STATUS} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public DailyProductionScheduleView(final LocalDate scheduledDate,
                                       final String dayOfWeek,
                                       final String workCenterName,
                                       final String workOrderNumber,
                                       final String status,
                                       final String itemCode,
                                       final String itemName,
                                       final BigDecimal quantityToProduce,
                                       final Integer priority,
                                       final BigDecimal estimatedDays,
                                       final BigDecimal productionValue,
                                       final String productionStatus) {
        super();
        setScheduledDate(scheduledDate);
        setDayOfWeek(dayOfWeek);
        setWorkCenterName(workCenterName);
        setWorkOrderNumber(workOrderNumber);
        setStatus(status);
        setItemCode(itemCode);
        setItemName(itemName);
        setQuantityToProduce(quantityToProduce);
        setPriority(priority);
        setEstimatedDays(estimatedDays);
        setProductionValue(productionValue);
        setProductionStatus(productionStatus);
    }
}

