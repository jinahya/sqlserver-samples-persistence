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
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.SupplierItem} table.
 * <p>
 * The {@value #COLUMN_NAME_SUPPLIER_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are mapped read-only, as a foreign key;
 * the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSupplierItem {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SupplierItem";

    // -------------------------------------------------------------------------------------------------- SupplierItemID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ITEM_ID = "SupplierItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ITEM_ID = "supplierItemId";

    // ------------------------------------------------------------------------------------------------------ SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ---------------------------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ---------------------------------------------------------------------------------------------- SupplierPartNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_PART_NUMBER = "SupplierPartNumber";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_PART_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_PART_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_PART_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_PART_NUMBER = "supplierPartNumber";

    // ------------------------------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // -------------------------------------------------------------------------------------------- MinimumOrderQuantity

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((1))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MINIMUM_ORDER_QUANTITY = "MinimumOrderQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_MINIMUM_ORDER_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MINIMUM_ORDER_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_MINIMUM_ORDER_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MINIMUM_ORDER_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MINIMUM_ORDER_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MINIMUM_ORDER_QUANTITY = "minimumOrderQuantity";

    // ---------------------------------------------------------------------------------------------------- LeadTimeDays

    /**
     * The name of the {@code int, DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEAD_TIME_DAYS = "LeadTimeDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEAD_TIME_DAYS = "leadTimeDays";

    // ----------------------------------------------------------------------------------------------------- IsPreferred

    /**
     * The name of the {@code bit, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_PREFERRED = "IsPreferred";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_PREFERRED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_PREFERRED = "isPreferred";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplierItem() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "supplierItemId=" + supplierItemId
               + ",supplierId=" + supplierId
               + ",itemId=" + itemId
               + ",supplierPartNumber=" + supplierPartNumber
               + ",unitPrice=" + unitPrice
               + ",minimumOrderQuantity=" + minimumOrderQuantity
               + ",leadTimeDays=" + leadTimeDays
               + ",isPreferred=" + isPreferred
               + ",effectiveDate=" + effectiveDate
               + ",endDate=" + endDate
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
        if (!(obj instanceof MappedSupplierItem that)) {
            return false;
        }
        return Objects.equals(supplierItemId, that.supplierItemId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(supplierItemId);
    }

    // -------------------------------------------------------------------------------------------------- supplierItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ITEM_ID} attribute.
     */
    public Integer getSupplierItemId() {
        return supplierItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ITEM_ID} attribute with the specified value.
     *
     * @param supplierItemId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ITEM_ID} attribute.
     */
    protected void setSupplierItemId(final Integer supplierItemId) {
        this.supplierItemId = supplierItemId;
    }

    // ------------------------------------------------------------------------------------------------------ supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(final Integer supplierId) {
        this.supplierId = supplierId;
    }

    // ---------------------------------------------------------------------------------------------------------- itemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    public Integer getItemId() {
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

    // ---------------------------------------------------------------------------------------------- supplierPartNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_PART_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_PART_NUMBER} attribute.
     */
    public String getSupplierPartNumber() {
        return supplierPartNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_PART_NUMBER} attribute with the specified value.
     *
     * @param supplierPartNumber new value for {@value #ATTRIBUTE_NAME_SUPPLIER_PART_NUMBER} attribute.
     */
    public void setSupplierPartNumber(final String supplierPartNumber) {
        this.supplierPartNumber = supplierPartNumber;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------------------- minimumOrderQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MINIMUM_ORDER_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MINIMUM_ORDER_QUANTITY} attribute.
     */
    public BigDecimal getMinimumOrderQuantity() {
        return minimumOrderQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MINIMUM_ORDER_QUANTITY} attribute with the specified value.
     *
     * @param minimumOrderQuantity new value for {@value #ATTRIBUTE_NAME_MINIMUM_ORDER_QUANTITY} attribute.
     */
    public void setMinimumOrderQuantity(final BigDecimal minimumOrderQuantity) {
        this.minimumOrderQuantity = minimumOrderQuantity;
    }

    // ---------------------------------------------------------------------------------------------------- leadTimeDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute with the specified value.
     *
     * @param leadTimeDays new value for {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    public void setLeadTimeDays(final Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    // ----------------------------------------------------------------------------------------------------- isPreferred

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_PREFERRED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_PREFERRED} attribute.
     */
    public Boolean getIsPreferred() {
        return isPreferred;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_PREFERRED} attribute with the specified value.
     *
     * @param isPreferred new value for {@value #ATTRIBUTE_NAME_IS_PREFERRED} attribute.
     */
    public void setIsPreferred(final Boolean isPreferred) {
        this.isPreferred = isPreferred;
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

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SUPPLIER_ITEM_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer supplierItemId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer supplierId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer itemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_PART_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_PART_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_PART_NUMBER
    )
    private String supplierPartNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_MINIMUM_ORDER_QUANTITY - COLUMN_SCALE_MINIMUM_ORDER_QUANTITY,
            fraction = COLUMN_SCALE_MINIMUM_ORDER_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MINIMUM_ORDER_QUANTITY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_MINIMUM_ORDER_QUANTITY,
            scale = COLUMN_SCALE_MINIMUM_ORDER_QUANTITY
    )
    private BigDecimal minimumOrderQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer leadTimeDays;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_PREFERRED,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isPreferred;

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
}

