package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTrigger;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
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
 * are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Purchasing.PurchaseOrderHeader}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PurchaseOrderID}</th><td>Primary key.</td></tr>
 * <tr><th scope="row">{@code RevisionNumber}</th>
 *     <td>Incremental number to track changes to the purchase order over time.</td></tr>
 * <tr><th scope="row">{@code Status}</th>
 *     <td>Order current status. 1 = Pending; 2 = Approved; 3 = Rejected; 4 = Complete</td></tr>
 * <tr><th scope="row">{@code EmployeeID}</th>
 *     <td>Employee who created the purchase order. Foreign key to
 *     Employee.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code VendorID}</th>
 *     <td>Vendor with whom the purchase order is placed. Foreign key to
 *     Vendor.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code ShipMethodID}</th>
 *     <td>Shipping method. Foreign key to ShipMethod.ShipMethodID.</td></tr>
 * <tr><th scope="row">{@code OrderDate}</th><td>Purchase order creation date.</td></tr>
 * <tr><th scope="row">{@code ShipDate}</th><td>Estimated shipment date from the vendor.</td></tr>
 * <tr><th scope="row">{@code SubTotal}</th>
 *     <td>Purchase order subtotal. Computed as SUM(PurchaseOrderDetail.LineTotal)for the
 *     appropriate PurchaseOrderID.</td></tr>
 * <tr><th scope="row">{@code TaxAmt}</th><td>Tax amount.</td></tr>
 * <tr><th scope="row">{@code Freight}</th><td>Shipping cost.</td></tr>
 * <tr><th scope="row">{@code TotalDue}</th>
 *     <td>Total due to vendor. Computed as Subtotal + TaxAmt + Freight.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Purchasing.PurchaseOrderHeader}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code RevisionNumber}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code Status}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code OrderDate}</th><td>{@code (getdate())}</td></tr>
 * <tr><th scope="row">{@code SubTotal}</th><td>{@code ((0.00))}</td></tr>
 * <tr><th scope="row">{@code TaxAmt}</th><td>{@code ((0.00))}</td></tr>
 * <tr><th scope="row">{@code Freight}</th><td>{@code ((0.00))}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code Purchasing.PurchaseOrderHeader}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code TotalDue}</th>
 *     <td>{@code (isnull(([SubTotal]+[TaxAmt])+[Freight],(0)))}</td><td>{@code true}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Triggers on {@code Purchasing.PurchaseOrderHeader}</caption>
 * <thead>
 * <tr><th scope="col">Trigger</th><th scope="col">Timing</th><th scope="col">Events</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code uPurchaseOrderHeader}</th><td>{@code AFTER}</td><td>{@code UPDATE}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/isnull-transact-sql">ISNULL</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPurchaseOrderHeader.TABLE_SCHEMA,
        table = MappedPurchaseOrderHeader.TABLE_NAME,
        value = "General purchase order information. See PurchaseOrderDetail."
)
@MsTrigger(
        name = "uPurchaseOrderHeader",
        events = MsTrigger.Event.UPDATE
)
public abstract class MappedPurchaseOrderHeader {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrderHeader";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // ------------------------------------------------------------------------------ RevisionNumber

    /**
     * The name of the {@code tinyint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REVISION_NUMBER = "RevisionNumber";

    /**
     * The minimum value of the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_REVISION_NUMBER = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_REVISION_NUMBER = +255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REVISION_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REVISION_NUMBER = "revisionNumber";

    // -------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code tinyint, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The minimum value of the {@value #COLUMN_NAME_STATUS} column, as the {@code CK_PurchaseOrderHeader_Status} check
     * constraint ({@code [Status]>=(1)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_STATUS = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_STATUS} column, as the {@code CK_PurchaseOrderHeader_Status} check
     * constraint ({@code [Status]<=(4)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MAX_STATUS = +4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ---------------------------------------------------------------------------------- EmployeeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMPLOYEE_ID = "EmployeeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMPLOYEE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE_ID = "employeeId";

    // ------------------------------------------------------------------------------------ VendorID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VENDOR_ID = "VendorID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VENDOR_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VENDOR_ID = "vendorId";

    // -------------------------------------------------------------------------------- ShipMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_METHOD_ID = "ShipMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_METHOD_ID = "shipMethodId";

    // ----------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // ------------------------------------------------------------------------------------ ShipDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_DATE = "ShipDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_DATE = "shipDate";

    // ------------------------------------------------------------------------------------ SubTotal

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUB_TOTAL = "SubTotal";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SUB_TOTAL} column, as the {@code CK_PurchaseOrderHeader_SubTotal}
     * check constraint ({@code [SubTotal]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_SUB_TOTAL = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUB_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUB_TOTAL = "subTotal";

    // -------------------------------------------------------------------------------------- TaxAmt

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMT = "TaxAmt";

    /**
     * The minimum value of the {@value #COLUMN_NAME_TAX_AMT} column, as the {@code CK_PurchaseOrderHeader_TaxAmt} check
     * constraint ({@code [TaxAmt]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_TAX_AMT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMT = "taxAmt";

    // ------------------------------------------------------------------------------------- Freight

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FREIGHT = "Freight";

    /**
     * The minimum value of the {@value #COLUMN_NAME_FREIGHT} column, as the {@code CK_PurchaseOrderHeader_Freight}
     * check constraint ({@code [Freight]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_FREIGHT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FREIGHT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FREIGHT = "freight";

    // ------------------------------------------------------------------------------------ TotalDue

    /**
     * The name of the {@code money, computed as (isnull(([SubTotal]+[TaxAmt])+[Freight],(0))), NOT NULL} column. The
     * value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_DUE = "TotalDue";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DUE = "totalDue";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
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
    protected MappedPurchaseOrderHeader() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute is {@code null} or not before the
     * {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute, as the table's {@code CK_PurchaseOrderHeader_ShipDate} constraint
     * requires.
     * {@snippet lang = "sql":
     * ([ShipDate]>=[OrderDate] OR [ShipDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute is not before the {@value #ATTRIBUTE_NAME_ORDER_DATE}
     *         attribute; {@code false} otherwise.
     */
    @AssertTrue(message = "shipDate must be null or not before orderDate")
    protected boolean isShipDateValid() {
        if (shipDate == null || orderDate == null) {
            return true;
        }
        return !shipDate.isBefore(orderDate);
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

    // ------------------------------------------------------------------------------ revisionNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     */
    public Integer getRevisionNumber() {
        return revisionNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute with the specified value.
     *
     * @param revisionNumber new value for {@value #ATTRIBUTE_NAME_REVISION_NUMBER} attribute.
     */
    public void setRevisionNumber(final Integer revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    // -------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    // ---------------------------------------------------------------------------------- employeeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param employeeId new value for {@value #ATTRIBUTE_NAME_EMPLOYEE_ID} attribute.
     */
    protected void setEmployeeId(final Integer employeeId) {
        this.employeeId = employeeId;
    }

    // ------------------------------------------------------------------------------------ vendorId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute.
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param vendorId new value for {@value #ATTRIBUTE_NAME_VENDOR_ID} attribute.
     */
    protected void setVendorId(final Integer vendorId) {
        this.vendorId = vendorId;
    }

    // -------------------------------------------------------------------------------- shipMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_METHOD_ID} attribute.
     */
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
    protected void setShipMethodId(final Integer shipMethodId) {
        this.shipMethodId = shipMethodId;
    }

    // ----------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public void setOrderDate(final LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    // ------------------------------------------------------------------------------------ shipDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    public LocalDateTime getShipDate() {
        return shipDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute with the specified value.
     *
     * @param shipDate new value for {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    public void setShipDate(final LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    // ------------------------------------------------------------------------------------ subTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute with the specified value.
     *
     * @param subTotal new value for {@value #ATTRIBUTE_NAME_SUB_TOTAL} attribute.
     */
    public void setSubTotal(final BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    // -------------------------------------------------------------------------------------- taxAmt

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     */
    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMT} attribute with the specified value.
     *
     * @param taxAmt new value for {@value #ATTRIBUTE_NAME_TAX_AMT} attribute.
     */
    public void setTaxAmt(final BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    // ------------------------------------------------------------------------------------- freight

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     */
    public BigDecimal getFreight() {
        return freight;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FREIGHT} attribute with the specified value.
     *
     * @param freight new value for {@value #ATTRIBUTE_NAME_FREIGHT} attribute.
     */
    public void setFreight(final BigDecimal freight) {
        this.freight = freight;
    }

    // ------------------------------------------------------------------------------------ totalDue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     */
    public BigDecimal getTotalDue() {
        return totalDue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param totalDue new value for {@value #ATTRIBUTE_NAME_TOTAL_DUE} attribute.
     */
    protected void setTotalDue(final BigDecimal totalDue) {
        this.totalDue = totalDue;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, value = "Primary key.")
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    @Max(COLUMN_MAX_REVISION_NUMBER)
    @Min(COLUMN_MIN_REVISION_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REVISION_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_REVISION_NUMBER,
                          value = "Incremental number to track changes to the purchase order "
                                  + "over time.")
    @MsDefault(name = "DF_PurchaseOrderHeader_RevisionNumber", value = "((0))")
    private Integer revisionNumber;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderHeader_Status: ([Status]>=(1) AND [Status]<=(4))
    @Max(COLUMN_MAX_STATUS)
    @Min(COLUMN_MIN_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_STATUS,
                          value = "Order current status. 1 = Pending; 2 = Approved; 3 = "
                                  + "Rejected; 4 = Complete")
    @MsDefault(name = "DF_PurchaseOrderHeader_Status", value = "((1))")
    private Integer status;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EMPLOYEE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_EMPLOYEE_ID,
                          value = "Employee who created the purchase order. Foreign key to "
                                  + "Employee.BusinessEntityID.")
    private Integer employeeId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VENDOR_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_VENDOR_ID,
                          value = "Vendor with whom the purchase order is placed. Foreign key "
                                  + "to Vendor.BusinessEntityID.")
    private Integer vendorId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHIP_METHOD_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_SHIP_METHOD_ID,
                          value = "Shipping method. Foreign key to ShipMethod.ShipMethodID.")
    private Integer shipMethodId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_ORDER_DATE, value = "Purchase order creation date.")
    @MsDefault(name = "DF_PurchaseOrderHeader_OrderDate", value = "(getdate())")
    private LocalDateTime orderDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIP_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_SHIP_DATE,
                          value = "Estimated shipment date from the vendor.")
    private LocalDateTime shipDate;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderHeader_SubTotal: ([SubTotal]>=(0.00))
    @DecimalMin(COLUMN_MIN_SUB_TOTAL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUB_TOTAL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_SUB_TOTAL,
                          value = "Purchase order subtotal. Computed as "
                                  + "SUM(PurchaseOrderDetail.LineTotal)for the appropriate "
                                  + "PurchaseOrderID.")
    @MsDefault(name = "DF_PurchaseOrderHeader_SubTotal", value = "((0.00))")
    private BigDecimal subTotal;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderHeader_TaxAmt: ([TaxAmt]>=(0.00))
    @DecimalMin(COLUMN_MIN_TAX_AMT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_AMT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_TAX_AMT, value = "Tax amount.")
    @MsDefault(name = "DF_PurchaseOrderHeader_TaxAmt", value = "((0.00))")
    private BigDecimal taxAmt;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderHeader_Freight: ([Freight]>=(0.00))
    @DecimalMin(COLUMN_MIN_FREIGHT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FREIGHT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_FREIGHT, value = "Shipping cost.")
    @MsDefault(name = "DF_PurchaseOrderHeader_Freight", value = "((0.00))")
    private BigDecimal freight;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TOTAL_DUE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_TOTAL_DUE,
                          value = "Total due to vendor. Computed as Subtotal + TaxAmt + Freight.")
    @MsComputedColumn(value = "(isnull(([SubTotal]+[TaxAmt])+[Freight],(0)))", persisted = true)
    private BigDecimal totalDue;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_PurchaseOrderHeader_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

