package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.BillOfMaterials} table.
 * <p>
 * The {@value #COLUMN_NAME_PARENT_ITEM_ID}, {@value #COLUMN_NAME_COMPONENT_ITEM_ID}, {@value #COLUMN_NAME_UNIT_ID}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedBillOfMaterials {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "BillOfMaterials";

    // ----------------------------------------------------------------------------------------------------------- BOMID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BOM_ID = "BOMID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BOM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BOM_ID = "bomId";

    // ---------------------------------------------------------------------------------------------------- ParentItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_ID = "ParentItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_ID = "parentItemId";

    // ------------------------------------------------------------------------------------------------- ComponentItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_ITEM_ID = "ComponentItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ITEM_ID = "componentItemId";

    // -------------------------------------------------------------------------------------------------------- Quantity

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ---------------------------------------------------------------------------------------------------------- UnitID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_ID = "UnitID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_ID = "unitId";

    // ------------------------------------------------------------------------------------------------------- ScrapRate

    /**
     * The name of the {@code decimal(5, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCRAP_RATE = "ScrapRate";

    /**
     * The precision of the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SCRAP_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SCRAP_RATE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_RATE = "scrapRate";

    // --------------------------------------------------------------------------------------------------- EffectiveDate

    /**
     * The name of the {@code date, DEFAULT (CONVERT([date],getdate()))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EFFECTIVE_DATE = "EffectiveDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EFFECTIVE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EFFECTIVE_DATE = "effectiveDate";

    // --------------------------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // -------------------------------------------------------------------------------------------------------- BOMLevel

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BOM_LEVEL = "BOMLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BOM_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BOM_LEVEL = "bomLevel";

    // -------------------------------------------------------------------------------------------------------- IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // ----------------------------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // ----------------------------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedBillOfMaterials() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "bomId=" + bomId
               + ",parentItemId=" + parentItemId
               + ",componentItemId=" + componentItemId
               + ",quantity=" + quantity
               + ",unitId=" + unitId
               + ",scrapRate=" + scrapRate
               + ",effectiveDate=" + effectiveDate
               + ",endDate=" + endDate
               + ",bomLevel=" + bomLevel
               + ",isActive=" + isActive
               + ",notes=" + notes
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
        if (!(obj instanceof MappedBillOfMaterials that)) {
            return false;
        }
        return Objects.equals(bomId, that.bomId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(bomId);
    }

    // ----------------------------------------------------------------------------------------------------------- bomId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BOM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BOM_ID} attribute.
     */
    public Integer getBomId() {
        return bomId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BOM_ID} attribute with the specified value.
     *
     * @param bomId new value for {@value #ATTRIBUTE_NAME_BOM_ID} attribute.
     */
    protected void setBomId(final Integer bomId) {
        this.bomId = bomId;
    }

    // ---------------------------------------------------------------------------------------------------- parentItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     */
    public Integer getParentItemId() {
        return parentItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param parentItemId new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     */
    protected void setParentItemId(final Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    // ------------------------------------------------------------------------------------------------- componentItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     */
    public Integer getComponentItemId() {
        return componentItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param componentItemId new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     */
    protected void setComponentItemId(final Integer componentItemId) {
        this.componentItemId = componentItemId;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final BigDecimal quantity) {
        this.quantity = quantity;
    }

    // ---------------------------------------------------------------------------------------------------------- unitId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param unitId new value for {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     */
    protected void setUnitId(final Integer unitId) {
        this.unitId = unitId;
    }

    // ------------------------------------------------------------------------------------------------------- scrapRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute.
     */
    public BigDecimal getScrapRate() {
        return scrapRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute with the specified value.
     *
     * @param scrapRate new value for {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute.
     */
    public void setScrapRate(final BigDecimal scrapRate) {
        this.scrapRate = scrapRate;
    }

    // --------------------------------------------------------------------------------------------------- effectiveDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute.
     */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute with the specified value.
     *
     * @param effectiveDate new value for {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute.
     */
    public void setEffectiveDate(final LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    // -------------------------------------------------------------------------------------------------------- bomLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute.
     */
    public Integer getBomLevel() {
        return bomLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute with the specified value.
     *
     * @param bomLevel new value for {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute.
     */
    public void setBomLevel(final Integer bomLevel) {
        this.bomLevel = bomLevel;
    }

    // -------------------------------------------------------------------------------------------------------- isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // ----------------------------------------------------------------------------------------------------------- notes

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

    // ----------------------------------------------------------------------------------------------------- createdDate

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
    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_BOM_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer bomId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PARENT_ITEM_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer parentItemId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer componentItemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_QUANTITY - COLUMN_SCALE_QUANTITY, fraction = COLUMN_SCALE_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY,
            scale = COLUMN_SCALE_QUANTITY
    )
    private BigDecimal quantity;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer unitId;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_SCRAP_RATE - COLUMN_SCALE_SCRAP_RATE, fraction = COLUMN_SCALE_SCRAP_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SCRAP_RATE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SCRAP_RATE,
            scale = COLUMN_SCALE_SCRAP_RATE
    )
    private BigDecimal scrapRate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EFFECTIVE_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate effectiveDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BOM_LEVEL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer bomLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isActive;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String notes;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime createdDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

