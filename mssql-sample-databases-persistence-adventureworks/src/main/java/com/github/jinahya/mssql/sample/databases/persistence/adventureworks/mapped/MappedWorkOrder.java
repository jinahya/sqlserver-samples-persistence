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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.WorkOrder} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID}, {@value #COLUMN_NAME_SCRAP_REASON_ID} columns are mapped read-only, as a
 * foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedWorkOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "WorkOrder";

    // ----------------------------------------------------------------------------------------------------- WorkOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
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

    // -------------------------------------------------------------------------------------------------------- OrderQty

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_QTY = "OrderQty";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_QTY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_QTY = "orderQty";

    // ------------------------------------------------------------------------------------------------------ StockedQty

    /**
     * The name of the {@code int, computed as (isnull([OrderQty]-[ScrappedQty],(0))), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_STOCKED_QTY = "StockedQty";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCKED_QTY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCKED_QTY = "stockedQty";

    // ----------------------------------------------------------------------------------------------------- ScrappedQty

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCRAPPED_QTY = "ScrappedQty";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAPPED_QTY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAPPED_QTY = "scrappedQty";

    // ------------------------------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // --------------------------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // --------------------------------------------------------------------------------------------------------- DueDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DUE_DATE = "DueDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DUE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DUE_DATE = "dueDate";

    // --------------------------------------------------------------------------------------------------- ScrapReasonID

    /**
     * The name of the {@code smallint} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCRAP_REASON_ID = "ScrapReasonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAP_REASON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_REASON_ID = "scrapReasonId";

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
    protected MappedWorkOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workOrderId=" + workOrderId
               + ",productId=" + productId
               + ",orderQty=" + orderQty
               + ",stockedQty=" + stockedQty
               + ",scrappedQty=" + scrappedQty
               + ",startDate=" + startDate
               + ",endDate=" + endDate
               + ",dueDate=" + dueDate
               + ",scrapReasonId=" + scrapReasonId
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
        if (!(obj instanceof MappedWorkOrder that)) {
            return false;
        }
        return Objects.equals(workOrderId, that.workOrderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(workOrderId);
    }

    // ----------------------------------------------------------------------------------------------------- workOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     */
    @Nullable
    public Integer getWorkOrderId() {
        return workOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute with the specified value.
     *
     * @param workOrderId new value for {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     */
    protected void setWorkOrderId(@Nullable final Integer workOrderId) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(@Nonnull final Integer productId) {
        this.productId = productId;
    }

    // -------------------------------------------------------------------------------------------------------- orderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    @Nonnull
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute with the specified value.
     *
     * @param orderQty new value for {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public void setOrderQty(@Nonnull final Integer orderQty) {
        this.orderQty = orderQty;
    }

    // ------------------------------------------------------------------------------------------------------ stockedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     */
    @Nonnull
    public Integer getStockedQty() {
        return stockedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param stockedQty new value for {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     */
    protected void setStockedQty(@Nonnull final Integer stockedQty) {
        this.stockedQty = stockedQty;
    }

    // ----------------------------------------------------------------------------------------------------- scrappedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCRAPPED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCRAPPED_QTY} attribute.
     */
    @Nonnull
    public Short getScrappedQty() {
        return scrappedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCRAPPED_QTY} attribute with the specified value.
     *
     * @param scrappedQty new value for {@value #ATTRIBUTE_NAME_SCRAPPED_QTY} attribute.
     */
    public void setScrappedQty(@Nonnull final Short scrappedQty) {
        this.scrappedQty = scrappedQty;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(@Nonnull final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(@Nullable final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // --------------------------------------------------------------------------------------------------------- dueDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute with the specified value.
     *
     * @param dueDate new value for {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public void setDueDate(@Nonnull final LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    // --------------------------------------------------------------------------------------------------- scrapReasonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCRAP_REASON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCRAP_REASON_ID} attribute.
     */
    @Nullable
    public Short getScrapReasonId() {
        return scrapReasonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCRAP_REASON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param scrapReasonId new value for {@value #ATTRIBUTE_NAME_SCRAP_REASON_ID} attribute.
     */
    protected void setScrapReasonId(@Nullable final Short scrapReasonId) {
        this.scrapReasonId = scrapReasonId;
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
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_WORK_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer workOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ORDER_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer orderQty;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Column(name = COLUMN_NAME_STOCKED_QTY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer stockedQty;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SCRAPPED_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short scrappedQty;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime startDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_DUE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime dueDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_SCRAP_REASON_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Short scrapReasonId;

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

