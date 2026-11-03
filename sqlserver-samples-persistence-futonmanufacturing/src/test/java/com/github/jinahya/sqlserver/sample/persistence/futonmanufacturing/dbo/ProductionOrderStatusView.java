package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedProductionOrderStatusView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code dbo.vw_ProductionOrderStatus} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ProductionOrderStatusView extends MappedProductionOrderStatusView {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ProductionOrderStatusView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param workOrderNumber       the value of the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column.
     * @param status                the value of the {@value #COLUMN_NAME_STATUS} column.
     * @param itemCode              the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName              the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param itemType              the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param orderQuantity         the value of the {@value #COLUMN_NAME_ORDER_QUANTITY} column.
     * @param quantityCompleted     the value of the {@value #COLUMN_NAME_QUANTITY_COMPLETED} column.
     * @param quantityScrapped      the value of the {@value #COLUMN_NAME_QUANTITY_SCRAPPED} column.
     * @param quantityRemaining     the value of the {@value #COLUMN_NAME_QUANTITY_REMAINING} column.
     * @param percentComplete       the value of the {@value #COLUMN_NAME_PERCENT_COMPLETE} column.
     * @param workCenterName        the value of the {@value #COLUMN_NAME_WORK_CENTER_NAME} column.
     * @param warehouseName         the value of the {@value #COLUMN_NAME_WAREHOUSE_NAME} column.
     * @param startDate             the value of the {@value #COLUMN_NAME_START_DATE} column.
     * @param plannedCompletionDate the value of the {@value #COLUMN_NAME_PLANNED_COMPLETION_DATE} column.
     * @param actualCompletionDate  the value of the {@value #COLUMN_NAME_ACTUAL_COMPLETION_DATE} column.
     * @param daysVariance          the value of the {@value #COLUMN_NAME_DAYS_VARIANCE} column.
     * @param scheduleStatus        the value of the {@value #COLUMN_NAME_SCHEDULE_STATUS} column.
     * @param priority              the value of the {@value #COLUMN_NAME_PRIORITY} column.
     * @param createdBy             the value of the {@value #COLUMN_NAME_CREATED_BY} column.
     * @param createdDate           the value of the {@value #COLUMN_NAME_CREATED_DATE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ProductionOrderStatusView(final String workOrderNumber,
                                     final String status,
                                     final String itemCode,
                                     final String itemName,
                                     final String itemType,
                                     final BigDecimal orderQuantity,
                                     final BigDecimal quantityCompleted,
                                     final BigDecimal quantityScrapped,
                                     final BigDecimal quantityRemaining,
                                     final BigDecimal percentComplete,
                                     final String workCenterName,
                                     final String warehouseName,
                                     final LocalDate startDate,
                                     final LocalDate plannedCompletionDate,
                                     final LocalDate actualCompletionDate,
                                     final Integer daysVariance,
                                     final String scheduleStatus,
                                     final Integer priority,
                                     final String createdBy,
                                     final LocalDateTime createdDate) {
        super();
        setWorkOrderNumber(workOrderNumber);
        setStatus(status);
        setItemCode(itemCode);
        setItemName(itemName);
        setItemType(itemType);
        setOrderQuantity(orderQuantity);
        setQuantityCompleted(quantityCompleted);
        setQuantityScrapped(quantityScrapped);
        setQuantityRemaining(quantityRemaining);
        setPercentComplete(percentComplete);
        setWorkCenterName(workCenterName);
        setWarehouseName(warehouseName);
        setStartDate(startDate);
        setPlannedCompletionDate(plannedCompletionDate);
        setActualCompletionDate(actualCompletionDate);
        setDaysVariance(daysVariance);
        setScheduleStatus(scheduleStatus);
        setPriority(priority);
        setCreatedBy(createdBy);
        setCreatedDate(createdDate);
    }
}

