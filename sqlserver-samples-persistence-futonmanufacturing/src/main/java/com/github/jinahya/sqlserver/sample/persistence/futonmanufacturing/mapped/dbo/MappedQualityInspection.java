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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.QualityInspection} table.
 * <p>
 * The {@value #COLUMN_NAME_ITEM_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.QualityInspection}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code InspectionDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedQualityInspection {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "QualityInspection";

    // -------------------------------------------------------------------------------- InspectionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_ID = "InspectionID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_INSPECTION_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_INSPECTION_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_ID = "inspectionId";

    // -------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ITEM_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_ITEM_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ITEM_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_ITEM_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ------------------------------------------------------------------------------ InspectionType

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_TYPE = "InspectionType";

    /**
     * The length of the {@value #COLUMN_NAME_INSPECTION_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_INSPECTION_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_TYPE = "inspectionType";

    // ------------------------------------------------------------------------------- ReferenceType

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_TYPE = "ReferenceType";

    /**
     * The length of the {@value #COLUMN_NAME_REFERENCE_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REFERENCE_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_TYPE = "referenceType";

    // ----------------------------------------------------------------------------- ReferenceNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_NUMBER = "ReferenceNumber";

    /**
     * The length of the {@value #COLUMN_NAME_REFERENCE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REFERENCE_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_NUMBER = "referenceNumber";

    // --------------------------------------------------------------------------- QuantityInspected

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_INSPECTED = "QuantityInspected";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_INSPECTED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_INSPECTED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_INSPECTED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_INSPECTED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_INSPECTED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_INSPECTED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_INSPECTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_INSPECTED = "quantityInspected";

    // ---------------------------------------------------------------------------- QuantityAccepted

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_ACCEPTED = "QuantityAccepted";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_ACCEPTED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_ACCEPTED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_ACCEPTED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_ACCEPTED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_ACCEPTED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_ACCEPTED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_ACCEPTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_ACCEPTED = "quantityAccepted";

    // ---------------------------------------------------------------------------- QuantityRejected

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_REJECTED = "QuantityRejected";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_REJECTED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_REJECTED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_REJECTED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_REJECTED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_REJECTED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_REJECTED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_REJECTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_REJECTED = "quantityRejected";

    // ------------------------------------------------------------------------------ InspectionDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_DATE = "InspectionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_DATE = "inspectionDate";

    // --------------------------------------------------------------------------------- InspectedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTED_BY = "InspectedBy";

    /**
     * The length of the {@value #COLUMN_NAME_INSPECTED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_INSPECTED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTED_BY = "inspectedBy";

    // --------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedQualityInspection() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "inspectionId=" + inspectionId
               + ",itemId=" + itemId
               + ",inspectionType=" + inspectionType
               + ",referenceType=" + referenceType
               + ",referenceNumber=" + referenceNumber
               + ",quantityInspected=" + quantityInspected
               + ",quantityAccepted=" + quantityAccepted
               + ",quantityRejected=" + quantityRejected
               + ",inspectionDate=" + inspectionDate
               + ",inspectedBy=" + inspectedBy
               + ",notes=" + notes
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
        if (!(obj instanceof MappedQualityInspection that)) {
            return false;
        }
        return Objects.equals(inspectionId, that.inspectionId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(inspectionId);
    }

    // -------------------------------------------------------------------------------- inspectionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute.
     */
    public Integer getInspectionId() {
        return inspectionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute with the specified value.
     *
     * @param inspectionId new value for {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute.
     */
    protected void setInspectionId(final Integer inspectionId) {
        this.inspectionId = inspectionId;
    }

    // -------------------------------------------------------------------------------------- itemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected Integer getItemId() {
        return itemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param itemId new value for {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    // ------------------------------------------------------------------------------ inspectionType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     */
    public String getInspectionType() {
        return inspectionType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute with the specified value.
     *
     * @param inspectionType new value for {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     */
    public void setInspectionType(final String inspectionType) {
        this.inspectionType = inspectionType;
    }

    // ------------------------------------------------------------------------------- referenceType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     */
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute with the specified value.
     *
     * @param referenceType new value for {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     */
    public void setReferenceType(final String referenceType) {
        this.referenceType = referenceType;
    }

    // ----------------------------------------------------------------------------- referenceNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute with the specified value.
     *
     * @param referenceNumber new value for {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     */
    public void setReferenceNumber(final String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    // --------------------------------------------------------------------------- quantityInspected

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute.
     */
    public BigDecimal getQuantityInspected() {
        return quantityInspected;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute with the specified value.
     *
     * @param quantityInspected new value for {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute.
     */
    public void setQuantityInspected(final BigDecimal quantityInspected) {
        this.quantityInspected = quantityInspected;
    }

    // ---------------------------------------------------------------------------- quantityAccepted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute.
     */
    public BigDecimal getQuantityAccepted() {
        return quantityAccepted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute with the specified value.
     *
     * @param quantityAccepted new value for {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute.
     */
    public void setQuantityAccepted(final BigDecimal quantityAccepted) {
        this.quantityAccepted = quantityAccepted;
    }

    // ---------------------------------------------------------------------------- quantityRejected

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute.
     */
    public BigDecimal getQuantityRejected() {
        return quantityRejected;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute with the specified value.
     *
     * @param quantityRejected new value for {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute.
     */
    public void setQuantityRejected(final BigDecimal quantityRejected) {
        this.quantityRejected = quantityRejected;
    }

    // ------------------------------------------------------------------------------ inspectionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     */
    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute with the specified value.
     *
     * @param inspectionDate new value for {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     */
    public void setInspectionDate(final LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    // --------------------------------------------------------------------------------- inspectedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute.
     */
    public String getInspectedBy() {
        return inspectedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute with the specified value.
     *
     * @param inspectedBy new value for {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute.
     */
    public void setInspectedBy(final String inspectedBy) {
        this.inspectedBy = inspectedBy;
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

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(ATTRIBUTE_MAX_INSPECTION_ID)
    @Min(ATTRIBUTE_MIN_INSPECTION_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_INSPECTION_ID, nullable = false, insertable = true, updatable = false)
    private Integer inspectionId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_ITEM_ID)
    @Min(ATTRIBUTE_MIN_ITEM_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_INSPECTION_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_INSPECTION_TYPE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_INSPECTION_TYPE)
    private String inspectionType;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REFERENCE_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REFERENCE_TYPE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_REFERENCE_TYPE)
    private String referenceType;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REFERENCE_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REFERENCE_NUMBER, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_REFERENCE_NUMBER)
    private String referenceNumber;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_INSPECTED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_INSPECTED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_INSPECTED, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_INSPECTED, scale = COLUMN_SCALE_QUANTITY_INSPECTED)
    private BigDecimal quantityInspected;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_ACCEPTED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_ACCEPTED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_ACCEPTED, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_ACCEPTED, scale = COLUMN_SCALE_QUANTITY_ACCEPTED)
    private BigDecimal quantityAccepted;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_REJECTED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_REJECTED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_REJECTED, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_REJECTED, scale = COLUMN_SCALE_QUANTITY_REJECTED)
    private BigDecimal quantityRejected;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INSPECTION_DATE, nullable = true, insertable = true, updatable = true)
    @MsDefault("(getdate())")
    private LocalDateTime inspectionDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_INSPECTED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INSPECTED_BY, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_INSPECTED_BY)
    private String inspectedBy;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 126 bytes (63 characters)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES, nullable = true, insertable = true, updatable = true)
    private String notes;
}

