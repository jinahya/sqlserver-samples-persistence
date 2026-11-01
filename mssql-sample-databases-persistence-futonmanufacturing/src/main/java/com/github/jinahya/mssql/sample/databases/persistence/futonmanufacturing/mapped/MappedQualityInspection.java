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
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.QualityInspection} table.
 * <p>
 * The {@value #COLUMN_NAME_ITEM_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedQualityInspection {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "QualityInspection";

    // ---------------------------------------------------------------------------------------------------- InspectionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_ID = "InspectionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_ID = "inspectionId";

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

    // -------------------------------------------------------------------------------------------------- InspectionType

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_TYPE = "InspectionType";

    /**
     * The length of the {@value #COLUMN_NAME_INSPECTION_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_INSPECTION_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_TYPE = "inspectionType";

    // --------------------------------------------------------------------------------------------------- ReferenceType

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_TYPE = "ReferenceType";

    /**
     * The length of the {@value #COLUMN_NAME_REFERENCE_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REFERENCE_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_TYPE = "referenceType";

    // ------------------------------------------------------------------------------------------------- ReferenceNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_NUMBER = "ReferenceNumber";

    /**
     * The length of the {@value #COLUMN_NAME_REFERENCE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REFERENCE_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_NUMBER = "referenceNumber";

    // ----------------------------------------------------------------------------------------------- QuantityInspected

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_INSPECTED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_INSPECTED = "quantityInspected";

    // ------------------------------------------------------------------------------------------------ QuantityAccepted

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_ACCEPTED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_ACCEPTED = "quantityAccepted";

    // ------------------------------------------------------------------------------------------------ QuantityRejected

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_REJECTED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_REJECTED = "quantityRejected";

    // -------------------------------------------------------------------------------------------------- InspectionDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_DATE = "InspectionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_DATE = "inspectionDate";

    // ----------------------------------------------------------------------------------------------------- InspectedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTED_BY = "InspectedBy";

    /**
     * The length of the {@value #COLUMN_NAME_INSPECTED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_INSPECTED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTED_BY = "inspectedBy";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedQualityInspection() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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

    // ---------------------------------------------------------------------------------------------------- inspectionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute.
     */
    @Nullable
    public Integer getInspectionId() {
        return inspectionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute with the specified value.
     *
     * @param inspectionId new value for {@value #ATTRIBUTE_NAME_INSPECTION_ID} attribute.
     */
    protected void setInspectionId(@Nullable final Integer inspectionId) {
        this.inspectionId = inspectionId;
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

    // -------------------------------------------------------------------------------------------------- inspectionType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     */
    @Nonnull
    public String getInspectionType() {
        return inspectionType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute with the specified value.
     *
     * @param inspectionType new value for {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     */
    public void setInspectionType(@Nonnull final String inspectionType) {
        this.inspectionType = inspectionType;
    }

    // --------------------------------------------------------------------------------------------------- referenceType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     */
    @Nullable
    public String getReferenceType() {
        return referenceType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute with the specified value.
     *
     * @param referenceType new value for {@value #ATTRIBUTE_NAME_REFERENCE_TYPE} attribute.
     */
    public void setReferenceType(@Nullable final String referenceType) {
        this.referenceType = referenceType;
    }

    // ------------------------------------------------------------------------------------------------- referenceNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     */
    @Nullable
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute with the specified value.
     *
     * @param referenceNumber new value for {@value #ATTRIBUTE_NAME_REFERENCE_NUMBER} attribute.
     */
    public void setReferenceNumber(@Nullable final String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    // ----------------------------------------------------------------------------------------------- quantityInspected

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute.
     */
    @Nonnull
    public BigDecimal getQuantityInspected() {
        return quantityInspected;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute with the specified value.
     *
     * @param quantityInspected new value for {@value #ATTRIBUTE_NAME_QUANTITY_INSPECTED} attribute.
     */
    public void setQuantityInspected(@Nonnull final BigDecimal quantityInspected) {
        this.quantityInspected = quantityInspected;
    }

    // ------------------------------------------------------------------------------------------------ quantityAccepted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute.
     */
    @Nonnull
    public BigDecimal getQuantityAccepted() {
        return quantityAccepted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute with the specified value.
     *
     * @param quantityAccepted new value for {@value #ATTRIBUTE_NAME_QUANTITY_ACCEPTED} attribute.
     */
    public void setQuantityAccepted(@Nonnull final BigDecimal quantityAccepted) {
        this.quantityAccepted = quantityAccepted;
    }

    // ------------------------------------------------------------------------------------------------ quantityRejected

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute.
     */
    @Nonnull
    public BigDecimal getQuantityRejected() {
        return quantityRejected;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute with the specified value.
     *
     * @param quantityRejected new value for {@value #ATTRIBUTE_NAME_QUANTITY_REJECTED} attribute.
     */
    public void setQuantityRejected(@Nonnull final BigDecimal quantityRejected) {
        this.quantityRejected = quantityRejected;
    }

    // -------------------------------------------------------------------------------------------------- inspectionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getInspectionDate() {
        return inspectionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute with the specified value.
     *
     * @param inspectionDate new value for {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     */
    public void setInspectionDate(@Nullable final LocalDateTime inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    // ----------------------------------------------------------------------------------------------------- inspectedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute.
     */
    @Nullable
    public String getInspectedBy() {
        return inspectedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute with the specified value.
     *
     * @param inspectedBy new value for {@value #ATTRIBUTE_NAME_INSPECTED_BY} attribute.
     */
    public void setInspectedBy(@Nullable final String inspectedBy) {
        this.inspectedBy = inspectedBy;
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

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_INSPECTION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer inspectionId;

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
    @Nonnull
    @Size(max = COLUMN_LENGTH_INSPECTION_TYPE)
    @NotNull
    @Column(name = COLUMN_NAME_INSPECTION_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_INSPECTION_TYPE
    )
    private String inspectionType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_REFERENCE_TYPE)
    @Column(name = COLUMN_NAME_REFERENCE_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REFERENCE_TYPE
    )
    private String referenceType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_REFERENCE_NUMBER)
    @Column(name = COLUMN_NAME_REFERENCE_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REFERENCE_NUMBER
    )
    private String referenceNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_QUANTITY_INSPECTED - COLUMN_SCALE_QUANTITY_INSPECTED, fraction = COLUMN_SCALE_QUANTITY_INSPECTED)
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY_INSPECTED,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_INSPECTED,
            scale = COLUMN_SCALE_QUANTITY_INSPECTED
    )
    private BigDecimal quantityInspected;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_QUANTITY_ACCEPTED - COLUMN_SCALE_QUANTITY_ACCEPTED, fraction = COLUMN_SCALE_QUANTITY_ACCEPTED)
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY_ACCEPTED,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_ACCEPTED,
            scale = COLUMN_SCALE_QUANTITY_ACCEPTED
    )
    private BigDecimal quantityAccepted;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_QUANTITY_REJECTED - COLUMN_SCALE_QUANTITY_REJECTED, fraction = COLUMN_SCALE_QUANTITY_REJECTED)
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY_REJECTED,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_REJECTED,
            scale = COLUMN_SCALE_QUANTITY_REJECTED
    )
    private BigDecimal quantityRejected;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_INSPECTION_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime inspectionDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_INSPECTED_BY)
    @Column(name = COLUMN_NAME_INSPECTED_BY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_INSPECTED_BY
    )
    private String inspectedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_NOTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String notes;
}

