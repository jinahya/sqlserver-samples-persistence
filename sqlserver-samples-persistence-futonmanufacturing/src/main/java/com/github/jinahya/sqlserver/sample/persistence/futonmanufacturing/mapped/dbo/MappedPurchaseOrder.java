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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.PurchaseOrder} table.
 * <p>
 * The {@value #COLUMN_NAME_SUPPLIER_ID}, {@value #COLUMN_NAME_WAREHOUSE_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.PurchaseOrder}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code OrderDate}</th><td>{@code (CONVERT([date],getdate()))}</td></tr>
 * <tr><th scope="row">{@code Status}</th><td>{@code ('Draft')}</td></tr>
 * <tr><th scope="row">{@code Subtotal}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code TaxAmount}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code ShippingAmount}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code TotalAmount}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code CreatedDate}</th><td>{@code (getdate())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/cast-and-convert-transact-sql">
 *      CAST and CONVERT</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedPurchaseOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrder";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PURCHASE_ORDER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PURCHASE_ORDER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // ------------------------------------------------------------------------------------ PONumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PO_NUMBER = "PONumber";

    /**
     * The length of the {@value #COLUMN_NAME_PO_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PO_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PO_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PO_NUMBER = "poNumber";

    // ---------------------------------------------------------------------------------- SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SUPPLIER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SUPPLIER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // --------------------------------------------------------------------------------- WarehouseID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_ID = "WarehouseID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_WAREHOUSE_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_WAREHOUSE_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_ID = "warehouseId";

    // ----------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, DEFAULT (CONVERT([date],getdate())), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // ------------------------------------------------------------------------ ExpectedDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_DELIVERY_DATE = "ExpectedDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE = "expectedDeliveryDate";

    // -------------------------------------------------------------------------- ActualDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_DELIVERY_DATE = "ActualDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE = "actualDeliveryDate";

    // -------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), DEFAULT ('Draft'), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The length of the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------ Subtotal

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SUBTOTAL = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SUBTOTAL = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUBTOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBTOTAL = "subtotal";

    // ----------------------------------------------------------------------------------- TaxAmount

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TAX_AMOUNT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TAX_AMOUNT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMOUNT = "taxAmount";

    // ------------------------------------------------------------------------------ ShippingAmount

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SHIPPING_AMOUNT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SHIPPING_AMOUNT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPPING_AMOUNT = "shippingAmount";

    // --------------------------------------------------------------------------------- TotalAmount

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_AMOUNT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_AMOUNT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_AMOUNT = "totalAmount";

    // --------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // ----------------------------------------------------------------------------------- CreatedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_BY = "CreatedBy";

    /**
     * The length of the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CREATED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_BY = "createdBy";

    // --------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPurchaseOrder() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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

    // ----------------------------------------------------------------------------- purchaseOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute with the specified value.
     *
     * @param purchaseOrderId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    protected void setPurchaseOrderId(final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // ------------------------------------------------------------------------------------ poNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute with the specified value.
     *
     * @param poNumber new value for {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     */
    public void setPoNumber(final String poNumber) {
        this.poNumber = poNumber;
    }

    // ---------------------------------------------------------------------------------- supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected Integer getSupplierId() {
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

    // --------------------------------------------------------------------------------- warehouseId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param warehouseId new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected void setWarehouseId(final Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    // ----------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public void setOrderDate(final LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    // ------------------------------------------------------------------------ expectedDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute with the specified value.
     *
     * @param expectedDeliveryDate new value for {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    public void setExpectedDeliveryDate(final LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    // -------------------------------------------------------------------------- actualDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     */
    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute with the specified value.
     *
     * @param actualDeliveryDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     */
    public void setActualDeliveryDate(final LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    // -------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------ subtotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute with the specified value.
     *
     * @param subtotal new value for {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     */
    public void setSubtotal(final BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    // ----------------------------------------------------------------------------------- taxAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute with the specified value.
     *
     * @param taxAmount new value for {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public void setTaxAmount(final BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    // ------------------------------------------------------------------------------ shippingAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     */
    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute with the specified value.
     *
     * @param shippingAmount new value for {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     */
    public void setShippingAmount(final BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
    }

    // --------------------------------------------------------------------------------- totalAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute with the specified value.
     *
     * @param totalAmount new value for {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     */
    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

    // ----------------------------------------------------------------------------------- createdBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute with the specified value.
     *
     * @param createdBy new value for {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    // --------------------------------------------------------------------------------- createdDate

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(ATTRIBUTE_MAX_PURCHASE_ORDER_ID)
    @Min(ATTRIBUTE_MIN_PURCHASE_ORDER_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, nullable = false, insertable = true, updatable = false)
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PO_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PO_NUMBER, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_PO_NUMBER)
    private String poNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_SUPPLIER_ID)
    @Min(ATTRIBUTE_MIN_SUPPLIER_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID, nullable = false, insertable = false, updatable = false)
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_WAREHOUSE_ID)
    @Min(ATTRIBUTE_MIN_WAREHOUSE_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_ID, nullable = false, insertable = false, updatable = false)
    private Integer warehouseId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE, nullable = false, insertable = true, updatable = true)
    @MsDefault("(CONVERT([date],getdate()))")
    private LocalDate orderDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE, nullable = true, insertable = true, updatable = true)
    private LocalDate expectedDeliveryDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_DELIVERY_DATE, nullable = true, insertable = true, updatable = true)
    private LocalDate actualDeliveryDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STATUS)
    @MsDefault("('Draft')")
    private String status;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SUBTOTAL)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SUBTOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUBTOTAL, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_SUBTOTAL, scale = COLUMN_SCALE_SUBTOTAL)
    @MsDefault("((0))")
    private BigDecimal subtotal;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TAX_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TAX_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TAX_AMOUNT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_TAX_AMOUNT, scale = COLUMN_SCALE_TAX_AMOUNT)
    @MsDefault("((0))")
    private BigDecimal taxAmount;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SHIPPING_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SHIPPING_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIPPING_AMOUNT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_SHIPPING_AMOUNT, scale = COLUMN_SCALE_SHIPPING_AMOUNT)
    @MsDefault("((0))")
    private BigDecimal shippingAmount;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_AMOUNT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_TOTAL_AMOUNT, scale = COLUMN_SCALE_TOTAL_AMOUNT)
    @MsDefault("((0))")
    private BigDecimal totalAmount;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 126 bytes (63 characters)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES, nullable = true, insertable = true, updatable = true)
    private String notes;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CREATED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_BY, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_CREATED_BY)
    private String createdBy;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE, nullable = true, insertable = true, updatable = true)
    @MsDefault("(getdate())")
    private LocalDateTime createdDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = true, insertable = true, updatable = true)
    @MsDefault("(getdate())")
    private LocalDateTime modifiedDate;
}

