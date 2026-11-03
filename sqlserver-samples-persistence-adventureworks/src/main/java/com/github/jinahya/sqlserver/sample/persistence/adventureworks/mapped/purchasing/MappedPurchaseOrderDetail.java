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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.PurchaseOrderDetail}
 * table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID} column is mapped read-only, as a foreign key; the extending
 * entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Purchasing.PurchaseOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PurchaseOrderID}</th>
 *     <td>Primary key. Foreign key to PurchaseOrderHeader.PurchaseOrderID.</td></tr>
 * <tr><th scope="row">{@code PurchaseOrderDetailID}</th>
 *     <td>Primary key. One line number per purchased product.</td></tr>
 * <tr><th scope="row">{@code DueDate}</th>
 *     <td>Date the product is expected to be received.</td></tr>
 * <tr><th scope="row">{@code OrderQty}</th><td>Quantity ordered.</td></tr>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code UnitPrice}</th>
 *     <td>Vendor's selling price of a single product.</td></tr>
 * <tr><th scope="row">{@code LineTotal}</th>
 *     <td>Per product subtotal. Computed as OrderQty * UnitPrice.</td></tr>
 * <tr><th scope="row">{@code ReceivedQty}</th>
 *     <td>Quantity actually received from the vendor.</td></tr>
 * <tr><th scope="row">{@code RejectedQty}</th><td>Quantity rejected during inspection.</td></tr>
 * <tr><th scope="row">{@code StockedQty}</th>
 *     <td>Quantity accepted into inventory. Computed as ReceivedQty - RejectedQty.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Purchasing.PurchaseOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code Purchasing.PurchaseOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code LineTotal}</th>
 *     <td>{@code (isnull([OrderQty]*[UnitPrice],(0.00)))}</td><td>{@code false}</td></tr>
 * <tr><th scope="row">{@code StockedQty}</th>
 *     <td>{@code (isnull([ReceivedQty]-[RejectedQty],(0.00)))}</td><td>{@code false}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Triggers on {@code Purchasing.PurchaseOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Trigger</th><th scope="col">Timing</th><th scope="col">Events</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code iPurchaseOrderDetail}</th><td>{@code AFTER}</td><td>{@code INSERT}
 * </td></tr>
 * <tr><th scope="row">{@code uPurchaseOrderDetail}</th><td>{@code AFTER}</td><td>{@code UPDATE}
 * </td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/isnull-transact-sql">ISNULL</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPurchaseOrderDetail.TABLE_SCHEMA,
        table = MappedPurchaseOrderDetail.TABLE_NAME,
        value = "Individual products associated with a specific purchase order. See "
                + "PurchaseOrderHeader."
)
@MsTrigger(
        name = "iPurchaseOrderDetail",
        events = MsTrigger.Event.INSERT
)
@MsTrigger(
        name = "uPurchaseOrderDetail",
        events = MsTrigger.Event.UPDATE
)
public abstract class MappedPurchaseOrderDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrderDetail";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // ----------------------------------------------------------------------- PurchaseOrderDetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID = "PurchaseOrderDetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID = "purchaseOrderDetailId";

    // ------------------------------------------------------------------------------------- DueDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DUE_DATE = "DueDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DUE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DUE_DATE = "dueDate";

    // ------------------------------------------------------------------------------------ OrderQty

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_QTY = "OrderQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_ORDER_QTY} column, as the
     * {@code CK_PurchaseOrderDetail_OrderQty} check constraint ({@code [OrderQty]>(0)}) requires.
     * The value is {@value}.
     */
    public static final int COLUMN_MIN_ORDER_QTY = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_ORDER_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_ORDER_QTY = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_QTY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_QTY = "orderQty";

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

    // ----------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The minimum value of the {@value #COLUMN_NAME_UNIT_PRICE} column, as the
     * {@code CK_PurchaseOrderDetail_UnitPrice} check constraint ({@code [UnitPrice]>=(0.00)})
     * requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_UNIT_PRICE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ----------------------------------------------------------------------------------- LineTotal

    /**
     * The name of the {@code money, computed as (isnull([OrderQty]*[UnitPrice],(0.00))), NOT NULL}
     * column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_TOTAL = "LineTotal";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

    // --------------------------------------------------------------------------------- ReceivedQty

    /**
     * The name of the {@code decimal(8, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECEIVED_QTY = "ReceivedQty";

    /**
     * The precision of the {@value #COLUMN_NAME_RECEIVED_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RECEIVED_QTY = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_RECEIVED_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RECEIVED_QTY = 2;

    /**
     * The minimum value of the {@value #COLUMN_NAME_RECEIVED_QTY} column, as the
     * {@code CK_PurchaseOrderDetail_ReceivedQty} check constraint ({@code [ReceivedQty]>=(0.00)})
     * requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_RECEIVED_QTY = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECEIVED_QTY} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECEIVED_QTY = "receivedQty";

    // --------------------------------------------------------------------------------- RejectedQty

    /**
     * The name of the {@code decimal(8, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REJECTED_QTY = "RejectedQty";

    /**
     * The precision of the {@value #COLUMN_NAME_REJECTED_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REJECTED_QTY = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_REJECTED_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REJECTED_QTY = 2;

    /**
     * The minimum value of the {@value #COLUMN_NAME_REJECTED_QTY} column, as the
     * {@code CK_PurchaseOrderDetail_RejectedQty} check constraint ({@code [RejectedQty]>=(0.00)})
     * requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_REJECTED_QTY = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REJECTED_QTY} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REJECTED_QTY = "rejectedQty";

    // ---------------------------------------------------------------------------------- StockedQty

    /**
     * The name of the
     * {@code decimal(9, 2), computed as (isnull([ReceivedQty]-[RejectedQty],(0.00))), NOT NULL}
     * column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCKED_QTY = "StockedQty";

    /**
     * The precision of the {@value #COLUMN_NAME_STOCKED_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_STOCKED_QTY = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_STOCKED_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_STOCKED_QTY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCKED_QTY} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCKED_QTY = "stockedQty";

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
    protected MappedPurchaseOrderDetail() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "purchaseOrderId=" + purchaseOrderId
               + ",purchaseOrderDetailId=" + purchaseOrderDetailId
               + ",dueDate=" + dueDate
               + ",orderQty=" + orderQty
               + ",productId=" + productId
               + ",unitPrice=" + unitPrice
               + ",lineTotal=" + lineTotal
               + ",receivedQty=" + receivedQty
               + ",rejectedQty=" + rejectedQty
               + ",stockedQty=" + stockedQty
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier, once each of its attributes is assigned; an
     *         instance whose identifier is not yet assigned, which the database does on insert,
     *         equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedPurchaseOrderDetail that)) {
            return false;
        }
        return getPurchaseOrderId() != null
               && getPurchaseOrderDetailId() != null
               && getPurchaseOrderId().equals(that.getPurchaseOrderId())
               && getPurchaseOrderDetailId().equals(that.getPurchaseOrderDetailId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         the identifier is assigned; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedPurchaseOrderDetail.class.hashCode();
    }

    // ----------------------------------------------------------------------------- purchaseOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute with the
     * specified value.
     *
     * @param purchaseOrderId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    protected void setPurchaseOrderId(final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // ----------------------------------------------------------------------- purchaseOrderDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute.
     */
    public Integer getPurchaseOrderDetailId() {
        return purchaseOrderDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute with
     * the specified value.
     *
     * @param purchaseOrderDetailId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID}
     *                              attribute.
     */
    protected void setPurchaseOrderDetailId(final Integer purchaseOrderDetailId) {
        this.purchaseOrderDetailId = purchaseOrderDetailId;
    }

    // ------------------------------------------------------------------------------------- dueDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute with the specified
     * value.
     *
     * @param dueDate new value for {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public void setDueDate(final LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    // ------------------------------------------------------------------------------------ orderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute with the specified
     * value.
     *
     * @param orderQty new value for {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public void setOrderQty(final Integer orderQty) {
        this.orderQty = orderQty;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ----------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified
     * value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ----------------------------------------------------------------------------------- lineTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute with the specified
     * value. The database writes the column; this is for the provider and the extending class
     * only.
     *
     * @param lineTotal new value for {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    protected void setLineTotal(final BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    // --------------------------------------------------------------------------------- receivedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute.
     */
    public BigDecimal getReceivedQty() {
        return receivedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute with the specified
     * value.
     *
     * @param receivedQty new value for {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute.
     */
    public void setReceivedQty(final BigDecimal receivedQty) {
        this.receivedQty = receivedQty;
    }

    // --------------------------------------------------------------------------------- rejectedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute.
     */
    public BigDecimal getRejectedQty() {
        return rejectedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute with the specified
     * value.
     *
     * @param rejectedQty new value for {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute.
     */
    public void setRejectedQty(final BigDecimal rejectedQty) {
        this.rejectedQty = rejectedQty;
    }

    // ---------------------------------------------------------------------------------- stockedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     */
    public BigDecimal getStockedQty() {
        return stockedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute with the specified
     * value. The database writes the column; this is for the provider and the extending class
     * only.
     *
     * @param stockedQty new value for {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     */
    protected void setStockedQty(final BigDecimal stockedQty) {
        this.stockedQty = stockedQty;
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
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                          value = "Primary key. Foreign key to "
                                  + "PurchaseOrderHeader.PurchaseOrderID.")
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the
    // identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID,
                          value = "Primary key. One line number per purchased product.")
    private Integer purchaseOrderDetailId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DUE_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_DUE_DATE,
                          value = "Date the product is expected to be received.")
    private LocalDateTime dueDate;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderDetail_OrderQty: ([OrderQty]>(0)); smallint's upper bound
    @Max(COLUMN_MAX_ORDER_QTY)
    @Min(COLUMN_MIN_ORDER_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_QTY, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_ORDER_QTY, value = "Quantity ordered.")
    private Integer orderQty;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_ID,
                          value = "Product identification number. Foreign key to "
                                  + "Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderDetail_UnitPrice: ([UnitPrice]>=(0.00))
    @DecimalMin(COLUMN_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_UNIT_PRICE,
                          value = "Vendor's selling price of a single product.")
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_TOTAL, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_LINE_TOTAL,
                          value = "Per product subtotal. Computed as OrderQty * UnitPrice.")
    @MsComputedColumn("(isnull([OrderQty]*[UnitPrice],(0.00)))")
    private BigDecimal lineTotal;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderDetail_ReceivedQty: ([ReceivedQty]>=(0.00))
    @DecimalMin(COLUMN_MIN_RECEIVED_QTY)
    @Digits(integer = COLUMN_PRECISION_RECEIVED_QTY - COLUMN_SCALE_RECEIVED_QTY,
            fraction = COLUMN_SCALE_RECEIVED_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RECEIVED_QTY, nullable = false, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_RECEIVED_QTY, scale = COLUMN_SCALE_RECEIVED_QTY)
    @MsDescription.Column(name = COLUMN_NAME_RECEIVED_QTY,
                          value = "Quantity actually received from the vendor.")
    private BigDecimal receivedQty;

    // ---------------------------------------------------------------------------------------------
    // CK_PurchaseOrderDetail_RejectedQty: ([RejectedQty]>=(0.00))
    @DecimalMin(COLUMN_MIN_REJECTED_QTY)
    @Digits(integer = COLUMN_PRECISION_REJECTED_QTY - COLUMN_SCALE_REJECTED_QTY,
            fraction = COLUMN_SCALE_REJECTED_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REJECTED_QTY, nullable = false, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_REJECTED_QTY, scale = COLUMN_SCALE_REJECTED_QTY)
    @MsDescription.Column(name = COLUMN_NAME_REJECTED_QTY,
                          value = "Quantity rejected during inspection.")
    private BigDecimal rejectedQty;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Digits(integer = COLUMN_PRECISION_STOCKED_QTY - COLUMN_SCALE_STOCKED_QTY,
            fraction = COLUMN_SCALE_STOCKED_QTY)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCKED_QTY, nullable = false, insertable = false, updatable = false,
            precision = COLUMN_PRECISION_STOCKED_QTY, scale = COLUMN_SCALE_STOCKED_QTY)
    @MsDescription.Column(name = COLUMN_NAME_STOCKED_QTY,
                          value = "Quantity accepted into inventory. Computed as ReceivedQty - "
                                  + "RejectedQty.")
    @MsComputedColumn("(isnull([ReceivedQty]-[RejectedQty],(0.00)))")
    private BigDecimal stockedQty;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (getdate()) fills it on an INSERT, and the
    // uPurchaseOrderDetail trigger
    // on every UPDATE
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_PurchaseOrderDetail_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

