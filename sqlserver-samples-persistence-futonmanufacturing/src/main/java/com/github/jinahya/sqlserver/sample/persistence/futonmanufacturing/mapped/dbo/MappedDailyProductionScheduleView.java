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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_DailyProductionSchedule} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedDailyProductionScheduleView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_DailyProductionSchedule";

    // --------------------------------------------------------------------------------------------------- ScheduledDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEDULED_DATE = "ScheduledDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEDULED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEDULED_DATE = "scheduledDate";

    // ------------------------------------------------------------------------------------------------------- DayOfWeek

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAY_OF_WEEK = "DayOfWeek";

    /**
     * The length of the {@value #COLUMN_NAME_DAY_OF_WEEK} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DAY_OF_WEEK = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_OF_WEEK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_OF_WEEK = "dayOfWeek";

    // -------------------------------------------------------------------------------------------------- WorkCenterName

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

    // ------------------------------------------------------------------------------------------------- WorkOrderNumber

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

    // ---------------------------------------------------------------------------------------------------------- Status

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

    // -------------------------------------------------------------------------------------------------------- ItemCode

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

    // -------------------------------------------------------------------------------------------------------- ItemName

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

    // ----------------------------------------------------------------------------------------------- QuantityToProduce

    /**
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_TO_PRODUCE = "QuantityToProduce";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_TO_PRODUCE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_TO_PRODUCE = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_TO_PRODUCE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_TO_PRODUCE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_TO_PRODUCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_TO_PRODUCE = "quantityToProduce";

    // -------------------------------------------------------------------------------------------------------- Priority

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIORITY = "Priority";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIORITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIORITY = "priority";

    // --------------------------------------------------------------------------------------------------- EstimatedDays

    /**
     * The name of the {@code decimal(10, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ESTIMATED_DAYS = "EstimatedDays";

    /**
     * The precision of the {@value #COLUMN_NAME_ESTIMATED_DAYS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ESTIMATED_DAYS = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_ESTIMATED_DAYS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ESTIMATED_DAYS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ESTIMATED_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ESTIMATED_DAYS = "estimatedDays";

    // ------------------------------------------------------------------------------------------------- ProductionValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCTION_VALUE = "ProductionValue";

    /**
     * The precision of the {@value #COLUMN_NAME_PRODUCTION_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PRODUCTION_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PRODUCTION_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PRODUCTION_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCTION_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_VALUE = "productionValue";

    // ------------------------------------------------------------------------------------------------ ProductionStatus

    /**
     * The name of the {@code varchar(17), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCTION_STATUS = "ProductionStatus";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCTION_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCTION_STATUS = 17;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCTION_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_STATUS = "productionStatus";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedDailyProductionScheduleView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "scheduledDate=" + scheduledDate
               + ",dayOfWeek=" + dayOfWeek
               + ",workCenterName=" + workCenterName
               + ",workOrderNumber=" + workOrderNumber
               + ",status=" + status
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",quantityToProduce=" + quantityToProduce
               + ",priority=" + priority
               + ",estimatedDays=" + estimatedDays
               + ",productionValue=" + productionValue
               + ",productionStatus=" + productionStatus
               + "}";
    }

    // --------------------------------------------------------------------------------------------------- scheduledDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEDULED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEDULED_DATE} attribute.
     */
    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEDULED_DATE} attribute with the specified value.
     *
     * @param scheduledDate new value for {@value #ATTRIBUTE_NAME_SCHEDULED_DATE} attribute.
     */
    protected void setScheduledDate(final LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    // ------------------------------------------------------------------------------------------------------- dayOfWeek

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute with the specified value.
     *
     * @param dayOfWeek new value for {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     */
    protected void setDayOfWeek(final String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // -------------------------------------------------------------------------------------------------- workCenterName

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

    // ------------------------------------------------------------------------------------------------- workOrderNumber

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

    // ---------------------------------------------------------------------------------------------------------- status

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

    // -------------------------------------------------------------------------------------------------------- itemCode

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

    // -------------------------------------------------------------------------------------------------------- itemName

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

    // ----------------------------------------------------------------------------------------------- quantityToProduce

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_TO_PRODUCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_TO_PRODUCE} attribute.
     */
    public BigDecimal getQuantityToProduce() {
        return quantityToProduce;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_TO_PRODUCE} attribute with the specified value.
     *
     * @param quantityToProduce new value for {@value #ATTRIBUTE_NAME_QUANTITY_TO_PRODUCE} attribute.
     */
    protected void setQuantityToProduce(final BigDecimal quantityToProduce) {
        this.quantityToProduce = quantityToProduce;
    }

    // -------------------------------------------------------------------------------------------------------- priority

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

    // --------------------------------------------------------------------------------------------------- estimatedDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ESTIMATED_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ESTIMATED_DAYS} attribute.
     */
    public BigDecimal getEstimatedDays() {
        return estimatedDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ESTIMATED_DAYS} attribute with the specified value.
     *
     * @param estimatedDays new value for {@value #ATTRIBUTE_NAME_ESTIMATED_DAYS} attribute.
     */
    protected void setEstimatedDays(final BigDecimal estimatedDays) {
        this.estimatedDays = estimatedDays;
    }

    // ------------------------------------------------------------------------------------------------- productionValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute.
     */
    public BigDecimal getProductionValue() {
        return productionValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute with the specified value.
     *
     * @param productionValue new value for {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute.
     */
    protected void setProductionValue(final BigDecimal productionValue) {
        this.productionValue = productionValue;
    }

    // ------------------------------------------------------------------------------------------------ productionStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCTION_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCTION_STATUS} attribute.
     */
    public String getProductionStatus() {
        return productionStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCTION_STATUS} attribute with the specified value.
     *
     * @param productionStatus new value for {@value #ATTRIBUTE_NAME_PRODUCTION_STATUS} attribute.
     */
    protected void setProductionStatus(final String productionStatus) {
        this.productionStatus = productionStatus;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SCHEDULED_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate scheduledDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DAY_OF_WEEK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAY_OF_WEEK,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DAY_OF_WEEK
    )
    private String dayOfWeek;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_CENTER_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WORK_CENTER_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WORK_CENTER_NAME
    )
    private String workCenterName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_ORDER_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WORK_ORDER_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WORK_ORDER_NUMBER
    )
    private String workOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STATUS
    )
    private String status;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_QUANTITY_TO_PRODUCE - COLUMN_SCALE_QUANTITY_TO_PRODUCE,
            fraction = COLUMN_SCALE_QUANTITY_TO_PRODUCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_TO_PRODUCE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_QUANTITY_TO_PRODUCE,
            scale = COLUMN_SCALE_QUANTITY_TO_PRODUCE
    )
    private BigDecimal quantityToProduce;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIORITY,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer priority;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_ESTIMATED_DAYS - COLUMN_SCALE_ESTIMATED_DAYS,
            fraction = COLUMN_SCALE_ESTIMATED_DAYS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ESTIMATED_DAYS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_ESTIMATED_DAYS,
            scale = COLUMN_SCALE_ESTIMATED_DAYS
    )
    private BigDecimal estimatedDays;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PRODUCTION_VALUE - COLUMN_SCALE_PRODUCTION_VALUE,
            fraction = COLUMN_SCALE_PRODUCTION_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCTION_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PRODUCTION_VALUE,
            scale = COLUMN_SCALE_PRODUCTION_VALUE
    )
    private BigDecimal productionValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PRODUCTION_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCTION_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PRODUCTION_STATUS
    )
    private String productionStatus;
}

