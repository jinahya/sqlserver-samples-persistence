package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.WorkOrderRouting} table.
 * <p>
 * The {@value #COLUMN_NAME_LOCATION_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedWorkOrderRouting {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "WorkOrderRouting";

    // ----------------------------------------------------------------------------------------------------- WorkOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_ORDER_ID = "WorkOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER_ID = "workOrderId";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ----------------------------------------------------------------------------------------------- OperationSequence

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OPERATION_SEQUENCE = "OperationSequence";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OPERATION_SEQUENCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OPERATION_SEQUENCE = "operationSequence";

    // ------------------------------------------------------------------------------------------------------ LocationID

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LocationID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // ---------------------------------------------------------------------------------------------- ScheduledStartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEDULED_START_DATE = "ScheduledStartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEDULED_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEDULED_START_DATE = "scheduledStartDate";

    // ------------------------------------------------------------------------------------------------ ScheduledEndDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEDULED_END_DATE = "ScheduledEndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEDULED_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEDULED_END_DATE = "scheduledEndDate";

    // ------------------------------------------------------------------------------------------------- ActualStartDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_START_DATE = "ActualStartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_START_DATE = "actualStartDate";

    // --------------------------------------------------------------------------------------------------- ActualEndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_END_DATE = "ActualEndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_END_DATE = "actualEndDate";

    // ----------------------------------------------------------------------------------------------- ActualResourceHrs

    /**
     * The name of the {@code decimal(9, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_RESOURCE_HRS = "ActualResourceHrs";

    /**
     * The precision of the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ACTUAL_RESOURCE_HRS = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ACTUAL_RESOURCE_HRS = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS = "actualResourceHrs";

    // ----------------------------------------------------------------------------------------------------- PlannedCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PLANNED_COST = "PlannedCost";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PLANNED_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PLANNED_COST = "plannedCost";

    // ------------------------------------------------------------------------------------------------------ ActualCost

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COST = "ActualCost";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COST = "actualCost";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWorkOrderRouting() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workOrderId=" + workOrderId
               + ",productId=" + productId
               + ",operationSequence=" + operationSequence
               + ",locationId=" + locationId
               + ",scheduledStartDate=" + scheduledStartDate
               + ",scheduledEndDate=" + scheduledEndDate
               + ",actualStartDate=" + actualStartDate
               + ",actualEndDate=" + actualEndDate
               + ",actualResourceHrs=" + actualResourceHrs
               + ",plannedCost=" + plannedCost
               + ",actualCost=" + actualCost
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedWorkOrderRouting that)) {
            return false;
        }
        return Objects.equals(workOrderId, that.workOrderId) &&
               Objects.equals(productId, that.productId) &&
               Objects.equals(operationSequence, that.operationSequence);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(workOrderId, productId, operationSequence);
    }

    // ----------------------------------------------------------------------------------------------------- workOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     */
    @Nonnull
    public Integer getWorkOrderId() {
        return workOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute with the specified value.
     *
     * @param workOrderId new value for {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     */
    public void setWorkOrderId(@Nonnull final Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    @Nonnull
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public void setProductId(@Nonnull final Integer productId) {
        this.productId = productId;
    }

    // ----------------------------------------------------------------------------------------------- operationSequence

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute.
     */
    @Nonnull
    public Short getOperationSequence() {
        return operationSequence;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute with the specified value.
     *
     * @param operationSequence new value for {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute.
     */
    public void setOperationSequence(@Nonnull final Short operationSequence) {
        this.operationSequence = operationSequence;
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    @Nonnull
    public Short getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    protected void setLocationId(@Nonnull final Short locationId) {
        this.locationId = locationId;
    }

    // ---------------------------------------------------------------------------------------------- scheduledStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getScheduledStartDate() {
        return scheduledStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute with the specified value.
     *
     * @param scheduledStartDate new value for {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute.
     */
    public void setScheduledStartDate(@Nonnull final LocalDateTime scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    // ------------------------------------------------------------------------------------------------ scheduledEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getScheduledEndDate() {
        return scheduledEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute with the specified value.
     *
     * @param scheduledEndDate new value for {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute.
     */
    public void setScheduledEndDate(@Nonnull final LocalDateTime scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    // ------------------------------------------------------------------------------------------------- actualStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getActualStartDate() {
        return actualStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute with the specified value.
     *
     * @param actualStartDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute.
     */
    public void setActualStartDate(@Nullable final LocalDateTime actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    // --------------------------------------------------------------------------------------------------- actualEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getActualEndDate() {
        return actualEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute with the specified value.
     *
     * @param actualEndDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute.
     */
    public void setActualEndDate(@Nullable final LocalDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    // ----------------------------------------------------------------------------------------------- actualResourceHrs

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute.
     */
    @Nullable
    public BigDecimal getActualResourceHrs() {
        return actualResourceHrs;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute with the specified value.
     *
     * @param actualResourceHrs new value for {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute.
     */
    public void setActualResourceHrs(@Nullable final BigDecimal actualResourceHrs) {
        this.actualResourceHrs = actualResourceHrs;
    }

    // ----------------------------------------------------------------------------------------------------- plannedCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute.
     */
    @Nonnull
    public BigDecimal getPlannedCost() {
        return plannedCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute with the specified value.
     *
     * @param plannedCost new value for {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute.
     */
    public void setPlannedCost(@Nonnull final BigDecimal plannedCost) {
        this.plannedCost = plannedCost;
    }

    // ------------------------------------------------------------------------------------------------------ actualCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    @Nullable
    public BigDecimal getActualCost() {
        return actualCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute with the specified value.
     *
     * @param actualCost new value for {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    public void setActualCost(@Nullable final BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_WORK_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer workOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_OPERATION_SEQUENCE,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Short operationSequence;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LOCATION_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Short locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SCHEDULED_START_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime scheduledStartDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SCHEDULED_END_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime scheduledEndDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ACTUAL_START_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime actualStartDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ACTUAL_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime actualEndDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_ACTUAL_RESOURCE_HRS - COLUMN_SCALE_ACTUAL_RESOURCE_HRS, fraction = COLUMN_SCALE_ACTUAL_RESOURCE_HRS)
    @Column(name = COLUMN_NAME_ACTUAL_RESOURCE_HRS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_ACTUAL_RESOURCE_HRS,
            scale = COLUMN_SCALE_ACTUAL_RESOURCE_HRS
    )
    private BigDecimal actualResourceHrs;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PLANNED_COST,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal plannedCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ACTUAL_COST,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private BigDecimal actualCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

