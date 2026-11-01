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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.PurchaseOrderHeader} table.
 * <p>
 * The {@value #COLUMN_NAME_EMPLOYEE_ID}, {@value #COLUMN_NAME_VENDOR_ID}, {@value #COLUMN_NAME_SHIP_METHOD_ID} columns
 * are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedPurchaseOrderHeader {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Purchasing";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrderHeader";

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

    // -------------------------------------------------------------------------------------------------- RevisionNumber

    /**
     * The name of the {@code tinyint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVISION_NUMBER = "RevisionNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVISION_NUMBER = "revisionNumber";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code tinyint, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------------------------ EmployeeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EmployeeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    // -------------------------------------------------------------------------------------------------------- VendorID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VENDOR_ID = "VendorID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VENDOR_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VENDOR_ID = "vendorId";

    // ---------------------------------------------------------------------------------------------------- ShipMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_METHOD_ID = "ShipMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_METHOD_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_METHOD_ID = "shipMethodId";

    // ------------------------------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // -------------------------------------------------------------------------------------------------------- ShipDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_DATE = "ShipDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_DATE = "shipDate";

    // -------------------------------------------------------------------------------------------------------- SubTotal

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUB_TOTAL = "SubTotal";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUB_TOTAL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUB_TOTAL = "subTotal";

    // ---------------------------------------------------------------------------------------------------------- TaxAmt

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMT = "TaxAmt";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMT = "taxAmt";

    // --------------------------------------------------------------------------------------------------------- Freight

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FREIGHT = "Freight";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FREIGHT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FREIGHT = "freight";

    // -------------------------------------------------------------------------------------------------------- TotalDue

    /**
     * The name of the {@code money, computed as (isnull(([SubTotal]+[TaxAmt])+[Freight],(0))), NOT NULL} column. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_DUE = "TotalDue";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DUE = "totalDue";

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
    protected MappedPurchaseOrderHeader() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "purchaseOrderId=" + purchaseOrderId
               + ",revisionNumber=" + revisionNumber
               + ",status=" + status
               + ",employeeId=" + employeeId
               + ",vendorId=" + vendorId
               + ",shipMethodId=" + shipMethodId
               + ",orderDate=" + orderDate
               + ",shipDate=" + shipDate
               + ",subTotal=" + subTotal
               + ",taxAmt=" + taxAmt
               + ",freight=" + freight
               + ",totalDue=" + totalDue
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
        if (!(obj instanceof MappedPurchaseOrderHeader that)) {
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

    // -------------------------------------------------------------------------------------------------- revisionNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     */
    @Nonnull
    public Short getRevisionNumber() {
        return revisionNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute with the specified value.
     *
     * @param revisionNumber new value for {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     */
    public void setRevisionNumber(@Nonnull final Short revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    @Nonnull
    public Short getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(@Nonnull final Short status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------------------------ employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    @Nonnull
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param employeeId new value for {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    protected void setEmployeeId(@Nonnull final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // -------------------------------------------------------------------------------------------------------- vendorId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute.
     */
    @Nonnull
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param vendorId new value for {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute.
     */
    protected void setVendorId(@Nonnull final Integer vendorId) {
        this.vendorId = vendorId;
    }

    // ---------------------------------------------------------------------------------------------------- shipMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
    @Nonnull
    public Integer getShipMethodId() {
        return shipMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param shipMethodId new value for {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
    protected void setShipMethodId(@Nonnull final Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    // ------------------------------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public void setOrderDate(@Nonnull final LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // -------------------------------------------------------------------------------------------------------- shipDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getShipDate() {
        return shipDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute with the specified value.
     *
     * @param shipDate new value for {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    public void setShipDate(@Nullable final LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    // -------------------------------------------------------------------------------------------------------- subTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     */
    @Nonnull
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute with the specified value.
     *
     * @param subTotal new value for {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     */
    public void setSubTotal(@Nonnull final BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    // ---------------------------------------------------------------------------------------------------------- taxAmt

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     */
    @Nonnull
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute with the specified value.
     *
     * @param taxAmt new value for {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     */
    public void setTaxAmt(@Nonnull final BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    // --------------------------------------------------------------------------------------------------------- freight

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     */
    @Nonnull
    public BigDecimal getFreight() {
        return freight;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute with the specified value.
     *
     * @param freight new value for {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     */
    public void setFreight(@Nonnull final BigDecimal freight) {
        this.freight = freight;
    }

    // -------------------------------------------------------------------------------------------------------- totalDue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     */
    @Nonnull
    public BigDecimal getTotalDue() {
        return totalDue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param totalDue new value for {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     */
    protected void setTotalDue(@Nonnull final BigDecimal totalDue) {
        this.totalDue = totalDue;
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
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer purchaseOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(255)
    @Min(0)
    @NotNull
    @Column(name = COLUMN_NAME_REVISION_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short revisionNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(255)
    @Min(0)
    @NotNull
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short status;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer employeeId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VENDOR_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer vendorId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SHIP_METHOD_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer shipMethodId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime orderDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_SHIP_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime shipDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SUB_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal subTotal;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_TAX_AMT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal taxAmt;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_FREIGHT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal freight;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Column(name = COLUMN_NAME_TOTAL_DUE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private BigDecimal totalDue;

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

