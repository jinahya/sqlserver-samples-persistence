package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.WorkOrderRouting} table.
 * <p>
 * The {@value #COLUMN_NAME_LOCATION_ID} column is mapped read-only, as a foreign key; the extending
 * entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.WorkOrderRouting}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedWorkOrderRouting {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "WorkOrderRouting";

    // --------------------------------------------------------------------------------- WorkOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_ORDER_ID = "WorkOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_ORDER_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER_ID = "workOrderId";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // --------------------------------------------------------------------------- OperationSequence

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OPERATION_SEQUENCE = "OperationSequence";

    /**
     * The minimum value of the {@value #COLUMN_NAME_OPERATION_SEQUENCE} column. The value is
     * {@value}.
     */
    public static final int COLUMN_MIN_OPERATION_SEQUENCE = Short.MIN_VALUE;

    /**
     * The maximum value of the {@value #COLUMN_NAME_OPERATION_SEQUENCE} column. The value is
     * {@value}.
     */
    public static final int COLUMN_MAX_OPERATION_SEQUENCE = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OPERATION_SEQUENCE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OPERATION_SEQUENCE = "operationSequence";

    // ---------------------------------------------------------------------------------- LocationID

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LocationID";

    /**
     * The minimum value of the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_LOCATION_ID = Short.MIN_VALUE;

    /**
     * The maximum value of the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_LOCATION_ID = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // -------------------------------------------------------------------------- ScheduledStartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEDULED_START_DATE = "ScheduledStartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEDULED_START_DATE} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEDULED_START_DATE = "scheduledStartDate";

    // ---------------------------------------------------------------------------- ScheduledEndDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCHEDULED_END_DATE = "ScheduledEndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCHEDULED_END_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCHEDULED_END_DATE = "scheduledEndDate";

    // ----------------------------------------------------------------------------- ActualStartDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_START_DATE = "ActualStartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_START_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_START_DATE = "actualStartDate";

    // ------------------------------------------------------------------------------- ActualEndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_END_DATE = "ActualEndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_END_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_END_DATE = "actualEndDate";

    // --------------------------------------------------------------------------- ActualResourceHrs

    /**
     * The name of the {@code decimal(9, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_RESOURCE_HRS = "ActualResourceHrs";

    /**
     * The precision of the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column. The value is
     * {@value}.
     */
    public static final int COLUMN_PRECISION_ACTUAL_RESOURCE_HRS = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ACTUAL_RESOURCE_HRS = 4;

    /**
     * The minimum value of the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column, as the
     * {@code CK_WorkOrderRouting_ActualResourceHrs} check constraint
     * ({@code [ActualResourceHrs]>=(0.0000)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_ACTUAL_RESOURCE_HRS = "0.0000";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_RESOURCE_HRS} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS = "actualResourceHrs";

    // --------------------------------------------------------------------------------- PlannedCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PLANNED_COST = "PlannedCost";

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_PLANNED_COST} column, as the
     * {@code CK_WorkOrderRouting_PlannedCost} check constraint ({@code [PlannedCost]>(0.00)})
     * requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_PLANNED_COST_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PLANNED_COST} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PLANNED_COST = "plannedCost";

    // ---------------------------------------------------------------------------------- ActualCost

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COST = "ActualCost";

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_ACTUAL_COST} column, as the
     * {@code CK_WorkOrderRouting_ActualCost} check constraint ({@code [ActualCost]>(0.00)})
     * requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_ACTUAL_COST_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COST} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COST = "actualCost";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWorkOrderRouting() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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
        return Objects.equals(getWorkOrderId(), that.getWorkOrderId()) &&
               Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getOperationSequence(), that.getOperationSequence());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getWorkOrderId(), getProductId(), getOperationSequence());
    }

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute is not before the
     * {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute, as the table's
     * {@code CK_WorkOrderRouting_ScheduledEndDate} constraint requires.
     * {@snippet lang = "sql":
     * ([ScheduledEndDate]>=[ScheduledStartDate])
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute is
     *         {@code null}, or the {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute is
     *         {@code null}, or the {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute is not
     *         before the {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute; {@code false}
     *         otherwise.
     */
    @AssertTrue(message = "scheduledEndDate must not be before scheduledStartDate")
    protected boolean isScheduledEndDateValid() {
        if (scheduledEndDate == null || scheduledStartDate == null) {
            return true;
        }
        return !scheduledEndDate.isBefore(scheduledStartDate);
    }

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute is {@code null} or not
     * before the {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute, as the table's
     * {@code CK_WorkOrderRouting_ActualEndDate} constraint requires.
     * {@snippet lang = "sql":
     * ([ActualEndDate]>=[ActualStartDate] OR [ActualEndDate] IS NULL OR [ActualStartDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute is
     *         {@code null}, or the {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute is
     *         {@code null}, or the {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute is not before
     *         the {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute; {@code false} otherwise.
     */
    @AssertTrue(message = "actualEndDate must be null or not before actualStartDate")
    protected boolean isActualEndDateValid() {
        if (actualEndDate == null || actualStartDate == null) {
            return true;
        }
        return !actualEndDate.isBefore(actualStartDate);
    }

    // --------------------------------------------------------------------------------- workOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getWorkOrderId() {
        return workOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute with the specified
     * value.
     *
     * @param workOrderId new value for {@value #ATTRIBUTE_NAME_WORK_ORDER_ID} attribute.
     */
    protected void setWorkOrderId(final Integer workOrderId) {
        this.workOrderId = workOrderId;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // --------------------------------------------------------------------------- operationSequence

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute.
     */
    public Integer getOperationSequence() {
        return operationSequence;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE} attribute with the
     * specified value.
     *
     * @param operationSequence new value for {@value #ATTRIBUTE_NAME_OPERATION_SEQUENCE}
     *                          attribute.
     */
    protected void setOperationSequence(final Integer operationSequence) {
        this.operationSequence = operationSequence;
    }

    // ---------------------------------------------------------------------------------- locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    protected void setLocationId(final Integer locationId) {
        this.locationId = locationId;
    }

    // -------------------------------------------------------------------------- scheduledStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute.
     */
    public LocalDateTime getScheduledStartDate() {
        return scheduledStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE} attribute with the
     * specified value.
     *
     * @param scheduledStartDate new value for {@value #ATTRIBUTE_NAME_SCHEDULED_START_DATE}
     *                           attribute.
     */
    public void setScheduledStartDate(final LocalDateTime scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    // ---------------------------------------------------------------------------- scheduledEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute.
     */
    public LocalDateTime getScheduledEndDate() {
        return scheduledEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute with the
     * specified value.
     *
     * @param scheduledEndDate new value for {@value #ATTRIBUTE_NAME_SCHEDULED_END_DATE} attribute.
     */
    public void setScheduledEndDate(final LocalDateTime scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    // ----------------------------------------------------------------------------- actualStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute.
     */
    public LocalDateTime getActualStartDate() {
        return actualStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute with the
     * specified value.
     *
     * @param actualStartDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_START_DATE} attribute.
     */
    public void setActualStartDate(final LocalDateTime actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    // ------------------------------------------------------------------------------- actualEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute.
     */
    public LocalDateTime getActualEndDate() {
        return actualEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute with the
     * specified value.
     *
     * @param actualEndDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_END_DATE} attribute.
     */
    public void setActualEndDate(final LocalDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    // --------------------------------------------------------------------------- actualResourceHrs

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute.
     */
    public BigDecimal getActualResourceHrs() {
        return actualResourceHrs;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS} attribute with the
     * specified value.
     *
     * @param actualResourceHrs new value for {@value #ATTRIBUTE_NAME_ACTUAL_RESOURCE_HRS}
     *                          attribute.
     */
    public void setActualResourceHrs(final BigDecimal actualResourceHrs) {
        this.actualResourceHrs = actualResourceHrs;
    }

    // --------------------------------------------------------------------------------- plannedCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute.
     */
    public BigDecimal getPlannedCost() {
        return plannedCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute with the specified
     * value.
     *
     * @param plannedCost new value for {@value #ATTRIBUTE_NAME_PLANNED_COST} attribute.
     */
    public void setPlannedCost(final BigDecimal plannedCost) {
        this.plannedCost = plannedCost;
    }

    // ---------------------------------------------------------------------------------- actualCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    public BigDecimal getActualCost() {
        return actualCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute with the specified
     * value.
     *
     * @param actualCost new value for {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    public void setActualCost(final BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_WORK_ORDER_ID, nullable = false, insertable = true,
            updatable = false)
    private Integer workOrderId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(COLUMN_MAX_OPERATION_SEQUENCE)
    @Min(COLUMN_MIN_OPERATION_SEQUENCE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_OPERATION_SEQUENCE, nullable = false, insertable = true,
            updatable = false)
    private Integer operationSequence;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(COLUMN_MAX_LOCATION_ID)
    @Min(COLUMN_MIN_LOCATION_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = false, updatable = false)
    private Integer locationId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SCHEDULED_START_DATE, nullable = false, insertable = true,
            updatable = true)
    private LocalDateTime scheduledStartDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SCHEDULED_END_DATE, nullable = false, insertable = true,
            updatable = true)
    private LocalDateTime scheduledEndDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_START_DATE, nullable = true, insertable = true,
            updatable = true)
    private LocalDateTime actualStartDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_END_DATE, nullable = true, insertable = true,
            updatable = true)
    private LocalDateTime actualEndDate;

    // ---------------------------------------------------------------------------------------------
    // CK_WorkOrderRouting_ActualResourceHrs: ([ActualResourceHrs]>=(0.0000))
    @DecimalMin(COLUMN_MIN_ACTUAL_RESOURCE_HRS)
    @Digits(integer = COLUMN_PRECISION_ACTUAL_RESOURCE_HRS - COLUMN_SCALE_ACTUAL_RESOURCE_HRS,
            fraction = COLUMN_SCALE_ACTUAL_RESOURCE_HRS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_RESOURCE_HRS, nullable = true, insertable = true,
            updatable = true, precision = COLUMN_PRECISION_ACTUAL_RESOURCE_HRS,
            scale = COLUMN_SCALE_ACTUAL_RESOURCE_HRS)
    private BigDecimal actualResourceHrs;

    // ---------------------------------------------------------------------------------------------
    // CK_WorkOrderRouting_PlannedCost: ([PlannedCost]>(0.00))
    @DecimalMin(value = COLUMN_MIN_PLANNED_COST_EXCLUSIVE, inclusive = false)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PLANNED_COST, nullable = false, insertable = true, updatable = true)
    private BigDecimal plannedCost;

    // ---------------------------------------------------------------------------------------------
    // CK_WorkOrderRouting_ActualCost: ([ActualCost]>(0.00))
    @DecimalMin(value = COLUMN_MIN_ACTUAL_COST_EXCLUSIVE, inclusive = false)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_COST, nullable = true, insertable = true, updatable = true)
    private BigDecimal actualCost;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (getdate()) fills it on an INSERT, and nothing documents
    // it as changing
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = false)
    @MsDefault(name = "DF_WorkOrderRouting_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

