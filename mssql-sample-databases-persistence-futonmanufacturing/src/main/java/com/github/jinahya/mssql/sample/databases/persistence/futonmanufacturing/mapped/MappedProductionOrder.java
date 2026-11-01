package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.ProductionOrder} table.
 * <p>
 * The {@value #COLUMN_NAME_ITEM_ID}, {@value #COLUMN_NAME_WAREHOUSE_ID}, {@value #COLUMN_NAME_WORK_CENTER_ID} columns
 * are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedProductionOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductionOrder";

    // ----------------------------------------------------------------------------------------------- ProductionOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCTION_ORDER_ID = "ProductionOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCTION_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_ORDER_ID = "productionOrderId";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER_NUMBER = "workOrderNumber";

    // ---------------------------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ----------------------------------------------------------------------------------------------------- WarehouseID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_ID = "WarehouseID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_ID = "warehouseId";

    // ---------------------------------------------------------------------------------------------------- WorkCenterID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_ID = "WorkCenterID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_ID = "workCenterId";

    // --------------------------------------------------------------------------------------------------- OrderQuantity

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_QUANTITY = "orderQuantity";

    // ----------------------------------------------------------------------------------------------- QuantityCompleted

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_COMPLETED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_COMPLETED = "quantityCompleted";

    // ------------------------------------------------------------------------------------------------ QuantityScrapped

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SCRAPPED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SCRAPPED = "quantityScrapped";

    // ------------------------------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ------------------------------------------------------------------------------------------- PlannedCompletionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PLANNED_COMPLETION_DATE = "PlannedCompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PLANNED_COMPLETION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE = "plannedCompletionDate";

    // -------------------------------------------------------------------------------------------- ActualCompletionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COMPLETION_DATE = "ActualCompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COMPLETION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE = "actualCompletionDate";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), DEFAULT ('Planned'), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The length of the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // -------------------------------------------------------------------------------------------------------- Priority

    /**
     * The name of the {@code int, DEFAULT ((5))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIORITY = "Priority";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIORITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIORITY = "priority";

    // ----------------------------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // ------------------------------------------------------------------------------------------------------- CreatedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_BY = "CreatedBy";

    /**
     * The length of the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CREATED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_BY = "createdBy";

    // ----------------------------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
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
    protected MappedProductionOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productionOrderId=" + productionOrderId
               + ",workOrderNumber=" + workOrderNumber
               + ",itemId=" + itemId
               + ",warehouseId=" + warehouseId
               + ",workCenterId=" + workCenterId
               + ",orderQuantity=" + orderQuantity
               + ",quantityCompleted=" + quantityCompleted
               + ",quantityScrapped=" + quantityScrapped
               + ",startDate=" + startDate
               + ",plannedCompletionDate=" + plannedCompletionDate
               + ",actualCompletionDate=" + actualCompletionDate
               + ",status=" + status
               + ",priority=" + priority
               + ",notes=" + notes
               + ",createdBy=" + createdBy
               + ",createdDate=" + createdDate
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
        if (!(obj instanceof MappedProductionOrder that)) {
            return false;
        }
        return Objects.equals(productionOrderId, that.productionOrderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productionOrderId);
    }

    // ----------------------------------------------------------------------------------------------- productionOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     */
    @Nullable
    public Integer getProductionOrderId() {
        return productionOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute with the specified value.
     *
     * @param productionOrderId new value for {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     */
    protected void setProductionOrderId(@Nullable final Integer productionOrderId) {
        this.productionOrderId = productionOrderId;
    }

    // ------------------------------------------------------------------------------------------------- workOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     */
    @Nonnull
    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute with the specified value.
     *
     * @param workOrderNumber new value for {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     */
    public void setWorkOrderNumber(@Nonnull final String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    // ---------------------------------------------------------------------------------------------------------- itemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    @Nonnull
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param itemId new value for {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected void setItemId(@Nonnull final Integer itemId) {
        this.itemId = itemId;
    }

    // ----------------------------------------------------------------------------------------------------- warehouseId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    @Nonnull
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param warehouseId new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected void setWarehouseId(@Nonnull final Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    // ---------------------------------------------------------------------------------------------------- workCenterId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     */
    @Nullable
    public Integer getWorkCenterId() {
        return workCenterId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param workCenterId new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     */
    protected void setWorkCenterId(@Nullable final Integer workCenterId) {
        this.workCenterId = workCenterId;
    }

    // --------------------------------------------------------------------------------------------------- orderQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute.
     */
    @Nonnull
    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute with the specified value.
     *
     * @param orderQuantity new value for {@value #ATTRIBUTE_NAME_ORDER_QUANTITY} attribute.
     */
    public void setOrderQuantity(@Nonnull final BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    // ----------------------------------------------------------------------------------------------- quantityCompleted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute.
     */
    @Nullable
    public BigDecimal getQuantityCompleted() {
        return quantityCompleted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute with the specified value.
     *
     * @param quantityCompleted new value for {@value #ATTRIBUTE_NAME_QUANTITY_COMPLETED} attribute.
     */
    public void setQuantityCompleted(@Nullable final BigDecimal quantityCompleted) {
        this.quantityCompleted = quantityCompleted;
    }

    // ------------------------------------------------------------------------------------------------ quantityScrapped

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute.
     */
    @Nullable
    public BigDecimal getQuantityScrapped() {
        return quantityScrapped;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute with the specified value.
     *
     * @param quantityScrapped new value for {@value #ATTRIBUTE_NAME_QUANTITY_SCRAPPED} attribute.
     */
    public void setQuantityScrapped(@Nullable final BigDecimal quantityScrapped) {
        this.quantityScrapped = quantityScrapped;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nullable
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(@Nullable final LocalDate startDate) {
        this.startDate = startDate;
    }

    // ------------------------------------------------------------------------------------------- plannedCompletionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     */
    @Nullable
    public LocalDate getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute with the specified value.
     *
     * @param plannedCompletionDate new value for {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     */
    public void setPlannedCompletionDate(@Nullable final LocalDate plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    // -------------------------------------------------------------------------------------------- actualCompletionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute.
     */
    @Nullable
    public LocalDate getActualCompletionDate() {
        return actualCompletionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute with the specified value.
     *
     * @param actualCompletionDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_COMPLETION_DATE} attribute.
     */
    public void setActualCompletionDate(@Nullable final LocalDate actualCompletionDate) {
        this.actualCompletionDate = actualCompletionDate;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    @Nonnull
    public String getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(@Nonnull final String status) {
        this.status = status;
    }

    // -------------------------------------------------------------------------------------------------------- priority

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIORITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIORITY} attribute.
     */
    @Nullable
    public Integer getPriority() {
        return priority;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIORITY} attribute with the specified value.
     *
     * @param priority new value for {@value #ATTRIBUTE_NAME_PRIORITY} attribute.
     */
    public void setPriority(@Nullable final Integer priority) {
        this.priority = priority;
    }

    // ----------------------------------------------------------------------------------------------------------- notes

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    @Nullable
    public String getNotes() {
        return notes;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NOTES} attribute with the specified value.
     *
     * @param notes new value for {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public void setNotes(@Nullable final String notes) {
        this.notes = notes;
    }

    // ------------------------------------------------------------------------------------------------------- createdBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    @Nullable
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute with the specified value.
     *
     * @param createdBy new value for {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public void setCreatedBy(@Nullable final String createdBy) {
        this.createdBy = createdBy;
    }

    // ----------------------------------------------------------------------------------------------------- createdDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute with the specified value.
     *
     * @param createdDate new value for {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public void setCreatedDate(@Nullable final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(@Nullable final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCTION_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productionOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_WORK_ORDER_NUMBER)
    @NotNull
    @Column(name = COLUMN_NAME_WORK_ORDER_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_WORK_ORDER_NUMBER
    )
    private String workOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ITEM_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer itemId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_WAREHOUSE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer warehouseId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_WORK_CENTER_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer workCenterId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_ORDER_QUANTITY - COLUMN_SCALE_ORDER_QUANTITY, fraction = COLUMN_SCALE_ORDER_QUANTITY)
    @NotNull
    @Column(name = COLUMN_NAME_ORDER_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_ORDER_QUANTITY,
            scale = COLUMN_SCALE_ORDER_QUANTITY
    )
    private BigDecimal orderQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_QUANTITY_COMPLETED - COLUMN_SCALE_QUANTITY_COMPLETED, fraction = COLUMN_SCALE_QUANTITY_COMPLETED)
    @Column(name = COLUMN_NAME_QUANTITY_COMPLETED,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_COMPLETED,
            scale = COLUMN_SCALE_QUANTITY_COMPLETED
    )
    private BigDecimal quantityCompleted;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_QUANTITY_SCRAPPED - COLUMN_SCALE_QUANTITY_SCRAPPED, fraction = COLUMN_SCALE_QUANTITY_SCRAPPED)
    @Column(name = COLUMN_NAME_QUANTITY_SCRAPPED,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_SCRAPPED,
            scale = COLUMN_SCALE_QUANTITY_SCRAPPED
    )
    private BigDecimal quantityScrapped;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate startDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_PLANNED_COMPLETION_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate plannedCompletionDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ACTUAL_COMPLETION_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate actualCompletionDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATUS
    )
    private String status;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_PRIORITY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer priority;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_NOTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String notes;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_CREATED_BY)
    @Column(name = COLUMN_NAME_CREATED_BY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CREATED_BY
    )
    private String createdBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_CREATED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime createdDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

