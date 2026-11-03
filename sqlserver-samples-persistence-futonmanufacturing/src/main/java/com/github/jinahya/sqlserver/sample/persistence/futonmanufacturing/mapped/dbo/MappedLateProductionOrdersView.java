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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_LateProductionOrders} view.
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
public abstract class MappedLateProductionOrdersView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_LateProductionOrders";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_COMPLETED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_COMPLETED = "quantityCompleted";

    // --------------------------------------------------------------------------- QuantityRemaining

    /**
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_REMAINING = "QuantityRemaining";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_REMAINING} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_REMAINING = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_REMAINING} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_REMAINING = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_REMAINING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_REMAINING = "quantityRemaining";

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

    // ------------------------------------------------------------------------------------ DaysLate

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_LATE = "DaysLate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_LATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_LATE = "daysLate";

    // ------------------------------------------------------------------------------------ Priority

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIORITY = "Priority";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIORITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIORITY = "priority";

    // ---------------------------------------------------------------------------- LatenessSeverity

    /**
     * The name of the {@code varchar(8), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LATENESS_SEVERITY = "LatenessSeverity";

    /**
     * The length of the {@value #COLUMN_NAME_LATENESS_SEVERITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LATENESS_SEVERITY = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATENESS_SEVERITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATENESS_SEVERITY = "latenessSeverity";

    // --------------------------------------------------------------------------------- ValueAtRisk

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALUE_AT_RISK = "ValueAtRisk";

    /**
     * The precision of the {@value #COLUMN_NAME_VALUE_AT_RISK} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_VALUE_AT_RISK = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_VALUE_AT_RISK} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_VALUE_AT_RISK = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALUE_AT_RISK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALUE_AT_RISK = "valueAtRisk";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedLateProductionOrdersView() {
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
               + ",quantityRemaining=" + quantityRemaining
               + ",workCenterName=" + workCenterName
               + ",startDate=" + startDate
               + ",plannedCompletionDate=" + plannedCompletionDate
               + ",daysLate=" + daysLate
               + ",priority=" + priority
               + ",latenessSeverity=" + latenessSeverity
               + ",valueAtRisk=" + valueAtRisk
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

    // ------------------------------------------------------------------------------------ daysLate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_LATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_LATE} attribute.
     */
    public Integer getDaysLate() {
        return daysLate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_LATE} attribute with the specified value.
     *
     * @param daysLate new value for {@value #ATTRIBUTE_NAME_DAYS_LATE} attribute.
     */
    protected void setDaysLate(final Integer daysLate) {
        this.daysLate = daysLate;
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

    // ---------------------------------------------------------------------------- latenessSeverity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LATENESS_SEVERITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LATENESS_SEVERITY} attribute.
     */
    public String getLatenessSeverity() {
        return latenessSeverity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATENESS_SEVERITY} attribute with the specified value.
     *
     * @param latenessSeverity new value for {@value #ATTRIBUTE_NAME_LATENESS_SEVERITY} attribute.
     */
    protected void setLatenessSeverity(final String latenessSeverity) {
        this.latenessSeverity = latenessSeverity;
    }

    // --------------------------------------------------------------------------------- valueAtRisk

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALUE_AT_RISK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALUE_AT_RISK} attribute.
     */
    public BigDecimal getValueAtRisk() {
        return valueAtRisk;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALUE_AT_RISK} attribute with the specified value.
     *
     * @param valueAtRisk new value for {@value #ATTRIBUTE_NAME_VALUE_AT_RISK} attribute.
     */
    protected void setValueAtRisk(final BigDecimal valueAtRisk) {
        this.valueAtRisk = valueAtRisk;
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
    @Digits(integer = COLUMN_PRECISION_ORDER_QUANTITY - COLUMN_SCALE_ORDER_QUANTITY,
            fraction = COLUMN_SCALE_ORDER_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_QUANTITY, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_ORDER_QUANTITY, scale = COLUMN_SCALE_ORDER_QUANTITY)
    private BigDecimal orderQuantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_QUANTITY_COMPLETED - COLUMN_SCALE_QUANTITY_COMPLETED,
            fraction = COLUMN_SCALE_QUANTITY_COMPLETED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_COMPLETED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_COMPLETED, scale = COLUMN_SCALE_QUANTITY_COMPLETED)
    private BigDecimal quantityCompleted;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_QUANTITY_REMAINING - COLUMN_SCALE_QUANTITY_REMAINING,
            fraction = COLUMN_SCALE_QUANTITY_REMAINING)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_REMAINING, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_REMAINING, scale = COLUMN_SCALE_QUANTITY_REMAINING)
    private BigDecimal quantityRemaining;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_CENTER_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WORK_CENTER_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_WORK_CENTER_NAME)
    private String workCenterName;

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
    @Column(name = COLUMN_NAME_DAYS_LATE, nullable = true, insertable = false, updatable = false)
    private Integer daysLate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIORITY, nullable = true, insertable = false, updatable = false)
    private Integer priority;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_LATENESS_SEVERITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LATENESS_SEVERITY, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_LATENESS_SEVERITY)
    private String latenessSeverity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_VALUE_AT_RISK - COLUMN_SCALE_VALUE_AT_RISK,
            fraction = COLUMN_SCALE_VALUE_AT_RISK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_VALUE_AT_RISK, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_VALUE_AT_RISK, scale = COLUMN_SCALE_VALUE_AT_RISK)
    private BigDecimal valueAtRisk;
}

