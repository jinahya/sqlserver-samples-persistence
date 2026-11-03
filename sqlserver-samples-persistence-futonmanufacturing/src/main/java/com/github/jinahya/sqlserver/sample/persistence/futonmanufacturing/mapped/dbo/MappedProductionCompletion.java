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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.ProductionCompletion} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCTION_ORDER_ID}, {@value #COLUMN_NAME_WORK_CENTER_ID} columns are mapped read-only, as
 * a foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.ProductionCompletion}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code QuantityScrapped}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code CompletionDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedProductionCompletion {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductionCompletion";

    // -------------------------------------------------------------------------------- CompletionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETION_ID = "CompletionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETION_ID = "completionId";

    // --------------------------------------------------------------------------- ProductionOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCTION_ORDER_ID = "ProductionOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCTION_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_ORDER_ID = "productionOrderId";

    // --------------------------------------------------------------------------- QuantityCompleted

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
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

    // ---------------------------------------------------------------------------- QuantityScrapped

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SCRAPPED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SCRAPPED = "quantityScrapped";

    // ------------------------------------------------------------------------------ CompletionDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETION_DATE = "CompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETION_DATE = "completionDate";

    // -------------------------------------------------------------------------------- WorkCenterID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_ID = "WorkCenterID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_ID = "workCenterId";

    // --------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // --------------------------------------------------------------------------------- CompletedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETED_BY = "CompletedBy";

    /**
     * The length of the {@value #COLUMN_NAME_COMPLETED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMPLETED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETED_BY = "completedBy";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductionCompletion() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "completionId=" + completionId
               + ",productionOrderId=" + productionOrderId
               + ",quantityCompleted=" + quantityCompleted
               + ",quantityScrapped=" + quantityScrapped
               + ",completionDate=" + completionDate
               + ",workCenterId=" + workCenterId
               + ",notes=" + notes
               + ",completedBy=" + completedBy
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
        if (!(obj instanceof MappedProductionCompletion that)) {
            return false;
        }
        return Objects.equals(completionId, that.completionId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(completionId);
    }

    // -------------------------------------------------------------------------------- completionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETION_ID} attribute.
     */
    public Integer getCompletionId() {
        return completionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETION_ID} attribute with the specified value.
     *
     * @param completionId new value for {@value #ATTRIBUTE_NAME_COMPLETION_ID} attribute.
     */
    protected void setCompletionId(final Integer completionId) {
        this.completionId = completionId;
    }

    // --------------------------------------------------------------------------- productionOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     */
    protected Integer getProductionOrderId() {
        return productionOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param productionOrderId new value for {@value #ATTRIBUTE_NAME_PRODUCTION_ORDER_ID} attribute.
     */
    protected void setProductionOrderId(final Integer productionOrderId) {
        this.productionOrderId = productionOrderId;
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
    public void setQuantityCompleted(final BigDecimal quantityCompleted) {
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
    public void setQuantityScrapped(final BigDecimal quantityScrapped) {
        this.quantityScrapped = quantityScrapped;
    }

    // ------------------------------------------------------------------------------ completionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute.
     */
    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute with the specified value.
     *
     * @param completionDate new value for {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute.
     */
    public void setCompletionDate(final LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    // -------------------------------------------------------------------------------- workCenterId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     */
    protected Integer getWorkCenterId() {
        return workCenterId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param workCenterId new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     */
    protected void setWorkCenterId(final Integer workCenterId) {
        this.workCenterId = workCenterId;
    }

    // --------------------------------------------------------------------------------------- notes

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NOTES} attribute with the specified value.
     *
     * @param notes new value for {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    // --------------------------------------------------------------------------------- completedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETED_BY} attribute.
     */
    public String getCompletedBy() {
        return completedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETED_BY} attribute with the specified value.
     *
     * @param completedBy new value for {@value #ATTRIBUTE_NAME_COMPLETED_BY} attribute.
     */
    public void setCompletedBy(final String completedBy) {
        this.completedBy = completedBy;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_COMPLETION_ID, nullable = false, insertable = true, updatable = false)
    private Integer completionId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCTION_ORDER_ID, nullable = false, insertable = false, updatable = false)
    private Integer productionOrderId;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_QUANTITY_COMPLETED - COLUMN_SCALE_QUANTITY_COMPLETED,
            fraction = COLUMN_SCALE_QUANTITY_COMPLETED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_COMPLETED, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_COMPLETED, scale = COLUMN_SCALE_QUANTITY_COMPLETED)
    private BigDecimal quantityCompleted;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_QUANTITY_SCRAPPED - COLUMN_SCALE_QUANTITY_SCRAPPED,
            fraction = COLUMN_SCALE_QUANTITY_SCRAPPED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_SCRAPPED, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_SCRAPPED, scale = COLUMN_SCALE_QUANTITY_SCRAPPED)
    @MsDefault("((0))")
    private BigDecimal quantityScrapped;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETION_DATE, nullable = true, insertable = true, updatable = true)
    @MsDefault("(getdate())")
    private LocalDateTime completionDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WORK_CENTER_ID, nullable = true, insertable = false, updatable = false)
    private Integer workCenterId;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES, nullable = true, insertable = true, updatable = true)
    private String notes;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COMPLETED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETED_BY, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_COMPLETED_BY)
    private String completedBy;
}

