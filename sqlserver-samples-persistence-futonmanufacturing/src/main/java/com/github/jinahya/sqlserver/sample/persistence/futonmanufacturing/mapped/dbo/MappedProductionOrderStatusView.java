package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_ProductionOrderStatus} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); some of its
 * columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedProductionOrderStatusView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_ProductionOrderStatus";

    // ----------------------------------------------------------------------------- WorkOrderNumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_ORDER_NUMBER = "WorkOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_ORDER_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER_NUMBER = "workOrderNumber";

    // -------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The length of the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------ ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // ------------------------------------------------------------------------------------ ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // ------------------------------------------------------------------------------------ ItemType

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_TYPE = "ItemType";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE = "itemType";

    // ------------------------------------------------------------------------------- OrderQuantity

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_QUANTITY = "OrderQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_ORDER_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ORDER_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_ORDER_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ORDER_QUANTITY = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_ORDER_QUANTITY = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_ORDER_QUANTITY = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_QUANTITY = "orderQuantity";

    // --------------------------------------------------------------------------- QuantityCompleted

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_COMPLETED = "QuantityCompleted";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_COMPLETED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_COMPLETED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_COMPLETED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_COMPLETED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_COMPLETED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_COMPLETED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_COMPLETED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_COMPLETED = "quantityCompleted";

    // ---------------------------------------------------------------------------- QuantityScrapped

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SCRAPPED = "QuantityScrapped";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_SCRAPPED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_SCRAPPED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_SCRAPPED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_SCRAPPED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_SCRAPPED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_SCRAPPED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SCRAPPED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SCRAPPED = "quantityScrapped";

    // --------------------------------------------------------------------------- QuantityRemaining

    /**
     * The name of the {@code decimal(20, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_REMAINING = "QuantityRemaining";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_REMAINING} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_REMAINING = 20;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_REMAINING} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_REMAINING = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_REMAINING} attribute, the minimum of the
     * {@code decimal(20, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_REMAINING = "-999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_REMAINING} attribute, the maximum of the
     * {@code decimal(20, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_REMAINING = "999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_REMAINING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_REMAINING = "quantityRemaining";

    // ----------------------------------------------------------------------------- PercentComplete

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERCENT_COMPLETE = "PercentComplete";

    /**
     * The precision of the {@value #COLUMN_NAME_PERCENT_COMPLETE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PERCENT_COMPLETE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_PERCENT_COMPLETE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PERCENT_COMPLETE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PERCENT_COMPLETE = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PERCENT_COMPLETE = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERCENT_COMPLETE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERCENT_COMPLETE = "percentComplete";

    // ------------------------------------------------------------------------------ WorkCenterName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_NAME = "WorkCenterName";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_CENTER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_CENTER_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_NAME = "workCenterName";

    // ------------------------------------------------------------------------------- WarehouseName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_NAME = "WarehouseName";

    /**
     * The length of the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WAREHOUSE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_NAME = "warehouseName";

    // ----------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ----------------------------------------------------------------------- PlannedCompletionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PLANNED_COMPLETION_DATE = "PlannedCompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PLANNED_COMPLETION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE = "plannedCompletionDate";

    // ------------------------------------------------------------------------ ActualCompletionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COMPLETION_DATE = "ActualCompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COMPLETION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE = "actualCompletionDate";

    // -------------------------------------------------------------------------------- DaysVariance

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_VARIANCE = "DaysVariance";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_DAYS_VARIANCE = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_DAYS_VARIANCE = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_VARIANCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_VARIANCE = "daysVariance";

    // ------------------------------------------------------------------------------ ScheduleStatus

    /**
     * The name of the {@code varchar(8), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEDULE_STATUS = "ScheduleStatus";

    /**
     * The length of the {@value #COLUMN_NAME_SCHEDULE_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SCHEDULE_STATUS = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEDULE_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEDULE_STATUS = "scheduleStatus";

    // ------------------------------------------------------------------------------------ Priority

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIORITY = "Priority";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PRIORITY} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PRIORITY = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PRIORITY} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PRIORITY = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIORITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIORITY = "priority";

    // ----------------------------------------------------------------------------------- CreatedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_BY = "CreatedBy";

    /**
     * The length of the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CREATED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_BY = "createdBy";

    // --------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductionOrderStatusView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workOrderNumber=" + workOrderNumber
               + ",status=" + status
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",orderQuantity=" + orderQuantity
               + ",quantityCompleted=" + quantityCompleted
               + ",quantityScrapped=" + quantityScrapped
               + ",quantityRemaining=" + quantityRemaining
               + ",percentComplete=" + percentComplete
               + ",workCenterName=" + workCenterName
               + ",warehouseName=" + warehouseName
               + ",startDate=" + startDate
               + ",plannedCompletionDate=" + plannedCompletionDate
               + ",actualCompletionDate=" + actualCompletionDate
               + ",daysVariance=" + daysVariance
               + ",scheduleStatus=" + scheduleStatus
               + ",priority=" + priority
               + ",createdBy=" + createdBy
               + ",createdDate=" + createdDate
               + "}";
    }

    // ----------------------------------------------------------------------------- workOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     */
    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute with the specified value.
     *
     * @param workOrderNumber new value for {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     */
    protected void setWorkOrderNumber(final String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    // -------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    protected void setStatus(final String status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------ itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // ------------------------------------------------------------------------------------ itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    // ------------------------------------------------------------------------------------ itemType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute with the specified value.
     *
     * @param itemType new value for {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    protected void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    // ------------------------------------------------------------------------------- orderQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute.
     */
    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute with the specified value.
     *
     * @param orderQuantity new value for {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute.
     */
    protected void setOrderQuantity(final BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    // --------------------------------------------------------------------------- quantityCompleted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute.
     */
    public BigDecimal getQuantityCompleted() {
        return quantityCompleted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute with the specified value.
     *
     * @param quantityCompleted new value for {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute.
     */
    protected void setQuantityCompleted(final BigDecimal quantityCompleted) {
        this.quantityCompleted = quantityCompleted;
    }

    // ---------------------------------------------------------------------------- quantityScrapped

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute.
     */
    public BigDecimal getQuantityScrapped() {
        return quantityScrapped;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute with the specified value.
     *
     * @param quantityScrapped new value for {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute.
     */
    protected void setQuantityScrapped(final BigDecimal quantityScrapped) {
        this.quantityScrapped = quantityScrapped;
    }

    // --------------------------------------------------------------------------- quantityRemaining

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_REMAINING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_REMAINING} attribute.
     */
    public BigDecimal getQuantityRemaining() {
        return quantityRemaining;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_REMAINING} attribute with the specified value.
     *
     * @param quantityRemaining new value for {@value #ATTRIBUTE_NAME_QUANTITY_REMAINING} attribute.
     */
    protected void setQuantityRemaining(final BigDecimal quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    // ----------------------------------------------------------------------------- percentComplete

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute.
     */
    public BigDecimal getPercentComplete() {
        return percentComplete;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute with the specified value.
     *
     * @param percentComplete new value for {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute.
     */
    protected void setPercentComplete(final BigDecimal percentComplete) {
        this.percentComplete = percentComplete;
    }

    // ------------------------------------------------------------------------------ workCenterName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     */
    public String getWorkCenterName() {
        return workCenterName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute with the specified value.
     *
     * @param workCenterName new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     */
    protected void setWorkCenterName(final String workCenterName) {
        this.workCenterName = workCenterName;
    }

    // ------------------------------------------------------------------------------- warehouseName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute with the specified value.
     *
     * @param warehouseName new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    protected void setWarehouseName(final String warehouseName) {
        this.warehouseName = warehouseName;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    protected void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // ----------------------------------------------------------------------- plannedCompletionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     */
    public LocalDate getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute with the specified value.
     *
     * @param plannedCompletionDate new value for {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     */
    protected void setPlannedCompletionDate(final LocalDate plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    // ------------------------------------------------------------------------ actualCompletionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute.
     */
    public LocalDate getActualCompletionDate() {
        return actualCompletionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute with the specified value.
     *
     * @param actualCompletionDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute.
     */
    protected void setActualCompletionDate(final LocalDate actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    // -------------------------------------------------------------------------------- daysVariance

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute.
     */
    public Integer getDaysVariance() {
        return daysVariance;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute with the specified value.
     *
     * @param daysVariance new value for {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute.
     */
    protected void setDaysVariance(final Integer daysVariance) {
        this.daysVariance = daysVariance;
    }

    // ------------------------------------------------------------------------------ scheduleStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEDULE_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEDULE_STATUS} attribute.
     */
    public String getScheduleStatus() {
        return scheduleStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEDULE_STATUS} attribute with the specified value.
     *
     * @param scheduleStatus new value for {@value #ATTRIBUTE_NAME_SCHEDULE_STATUS} attribute.
     */
    protected void setScheduleStatus(final String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }

    // ------------------------------------------------------------------------------------ priority

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIORITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIORITY} attribute.
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIORITY} attribute with the specified value.
     *
     * @param priority new value for {@value #ATTRIBUTE_NAME_PRIORITY} attribute.
     */
    protected void setPriority(final Integer priority) {
        this.priority = priority;
    }

    // ----------------------------------------------------------------------------------- createdBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute with the specified value.
     *
     * @param createdBy new value for {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    protected void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    // --------------------------------------------------------------------------------- createdDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute with the specified value.
     *
     * @param createdDate new value for {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    protected void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_ORDER_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WORK_ORDER_NUMBER, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WORK_ORDER_NUMBER)
    private String workOrderNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_STATUS)
    private String status;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_CODE)
    private String itemCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_NAME)
    private String itemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_TYPE)
    private String itemType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_ORDER_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_ORDER_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_QUANTITY, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_ORDER_QUANTITY, scale = COLUMN_SCALE_ORDER_QUANTITY)
    private BigDecimal orderQuantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_COMPLETED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_COMPLETED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_COMPLETED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_COMPLETED, scale = COLUMN_SCALE_QUANTITY_COMPLETED)
    private BigDecimal quantityCompleted;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_SCRAPPED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_SCRAPPED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_SCRAPPED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_SCRAPPED, scale = COLUMN_SCALE_QUANTITY_SCRAPPED)
    private BigDecimal quantityScrapped;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_REMAINING)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_REMAINING)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_REMAINING, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_REMAINING, scale = COLUMN_SCALE_QUANTITY_REMAINING)
    private BigDecimal quantityRemaining;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PERCENT_COMPLETE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PERCENT_COMPLETE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PERCENT_COMPLETE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PERCENT_COMPLETE, scale = COLUMN_SCALE_PERCENT_COMPLETE)
    private BigDecimal percentComplete;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_CENTER_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WORK_CENTER_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_WORK_CENTER_NAME)
    private String workCenterName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WAREHOUSE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WAREHOUSE_NAME)
    private String warehouseName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_START_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate startDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PLANNED_COMPLETION_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate plannedCompletionDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_COMPLETION_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate actualCompletionDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_DAYS_VARIANCE)
    @Min(ATTRIBUTE_MIN_DAYS_VARIANCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_VARIANCE, nullable = true, insertable = false, updatable = false)
    private Integer daysVariance;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SCHEDULE_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SCHEDULE_STATUS, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_SCHEDULE_STATUS)
    private String scheduleStatus;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_PRIORITY)
    @Min(ATTRIBUTE_MIN_PRIORITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIORITY, nullable = true, insertable = false, updatable = false)
    private Integer priority;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CREATED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_BY, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_CREATED_BY)
    private String createdBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDateTime createdDate;
}

