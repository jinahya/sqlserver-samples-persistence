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
 * A mapped superclass which holds the mappings of the {@code dbo.PurchaseOrder} table.
 * <p>
 * The {@value #COLUMN_NAME_SUPPLIER_ID}, {@value #COLUMN_NAME_WAREHOUSE_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedPurchaseOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrder";

    // ------------------------------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // -------------------------------------------------------------------------------------------------------- PONumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PO_NUMBER = "PONumber";

    /**
     * The length of the {@value #COLUMN_NAME_PO_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PO_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PO_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PO_NUMBER = "poNumber";

    // ------------------------------------------------------------------------------------------------------ SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

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

    // ------------------------------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, DEFAULT (CONVERT([date],getdate())), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // -------------------------------------------------------------------------------------------- ExpectedDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_DELIVERY_DATE = "ExpectedDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE = "expectedDeliveryDate";

    // ---------------------------------------------------------------------------------------------- ActualDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_DELIVERY_DATE = "ActualDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE = "actualDeliveryDate";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), DEFAULT ('Draft'), NOT NULL} column. The value is {@value}.
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

    // -------------------------------------------------------------------------------------------------------- Subtotal

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUBTOTAL = "Subtotal";

    /**
     * The precision of the {@value #COLUMN_NAME_SUBTOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SUBTOTAL = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_SUBTOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SUBTOTAL = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUBTOTAL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBTOTAL = "subtotal";

    // ------------------------------------------------------------------------------------------------------- TaxAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMOUNT = "TaxAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TAX_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TAX_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMOUNT = "taxAmount";

    // -------------------------------------------------------------------------------------------------- ShippingAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIPPING_AMOUNT = "ShippingAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SHIPPING_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SHIPPING_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPPING_AMOUNT = "shippingAmount";

    // ----------------------------------------------------------------------------------------------------- TotalAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_AMOUNT = "TotalAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_AMOUNT = "totalAmount";

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
    protected MappedPurchaseOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "purchaseOrderId=" + purchaseOrderId
               + ",poNumber=" + poNumber
               + ",supplierId=" + supplierId
               + ",warehouseId=" + warehouseId
               + ",orderDate=" + orderDate
               + ",expectedDeliveryDate=" + expectedDeliveryDate
               + ",actualDeliveryDate=" + actualDeliveryDate
               + ",status=" + status
               + ",subtotal=" + subtotal
               + ",taxAmount=" + taxAmount
               + ",shippingAmount=" + shippingAmount
               + ",totalAmount=" + totalAmount
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
        if (!(obj instanceof MappedPurchaseOrder that)) {
            return false;
        }
        return Objects.equals(purchaseOrderId, that.purchaseOrderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(purchaseOrderId);
    }

    // ------------------------------------------------------------------------------------------------- purchaseOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    @Nullable
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute with the specified value.
     *
     * @param purchaseOrderId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    protected void setPurchaseOrderId(@Nullable final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // -------------------------------------------------------------------------------------------------------- poNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     */
    @Nonnull
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute with the specified value.
     *
     * @param poNumber new value for {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     */
    public void setPoNumber(@Nonnull final String poNumber) {
        this.poNumber = poNumber;
    }

    // ------------------------------------------------------------------------------------------------------ supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    @Nonnull
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(@Nonnull final Integer supplierId) {
        this.supplierId = supplierId;
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

    // ------------------------------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    @Nonnull
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public void setOrderDate(@Nonnull final LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    // -------------------------------------------------------------------------------------------- expectedDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    @Nullable
    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute with the specified value.
     *
     * @param expectedDeliveryDate new value for {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    public void setExpectedDeliveryDate(@Nullable final LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    // ---------------------------------------------------------------------------------------------- actualDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     */
    @Nullable
    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute with the specified value.
     *
     * @param actualDeliveryDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     */
    public void setActualDeliveryDate(@Nullable final LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
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

    // -------------------------------------------------------------------------------------------------------- subtotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     */
    @Nullable
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute with the specified value.
     *
     * @param subtotal new value for {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     */
    public void setSubtotal(@Nullable final BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    // ------------------------------------------------------------------------------------------------------- taxAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    @Nullable
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute with the specified value.
     *
     * @param taxAmount new value for {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public void setTaxAmount(@Nullable final BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    // -------------------------------------------------------------------------------------------------- shippingAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     */
    @Nullable
    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute with the specified value.
     *
     * @param shippingAmount new value for {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     */
    public void setShippingAmount(@Nullable final BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    // ----------------------------------------------------------------------------------------------------- totalAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     */
    @Nullable
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute with the specified value.
     *
     * @param totalAmount new value for {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     */
    public void setTotalAmount(@Nullable final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer purchaseOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_PO_NUMBER)
    @NotNull
    @Column(name = COLUMN_NAME_PO_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PO_NUMBER
    )
    private String poNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer supplierId;

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
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate orderDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate expectedDeliveryDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ACTUAL_DELIVERY_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate actualDeliveryDate;

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
    @Digits(integer = COLUMN_PRECISION_SUBTOTAL - COLUMN_SCALE_SUBTOTAL, fraction = COLUMN_SCALE_SUBTOTAL)
    @Column(name = COLUMN_NAME_SUBTOTAL,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SUBTOTAL,
            scale = COLUMN_SCALE_SUBTOTAL
    )
    private BigDecimal subtotal;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_TAX_AMOUNT - COLUMN_SCALE_TAX_AMOUNT, fraction = COLUMN_SCALE_TAX_AMOUNT)
    @Column(name = COLUMN_NAME_TAX_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TAX_AMOUNT,
            scale = COLUMN_SCALE_TAX_AMOUNT
    )
    private BigDecimal taxAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_SHIPPING_AMOUNT - COLUMN_SCALE_SHIPPING_AMOUNT, fraction = COLUMN_SCALE_SHIPPING_AMOUNT)
    @Column(name = COLUMN_NAME_SHIPPING_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SHIPPING_AMOUNT,
            scale = COLUMN_SCALE_SHIPPING_AMOUNT
    )
    private BigDecimal shippingAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_TOTAL_AMOUNT - COLUMN_SCALE_TOTAL_AMOUNT, fraction = COLUMN_SCALE_TOTAL_AMOUNT)
    @Column(name = COLUMN_NAME_TOTAL_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TOTAL_AMOUNT,
            scale = COLUMN_SCALE_TOTAL_AMOUNT
    )
    private BigDecimal totalAmount;

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

