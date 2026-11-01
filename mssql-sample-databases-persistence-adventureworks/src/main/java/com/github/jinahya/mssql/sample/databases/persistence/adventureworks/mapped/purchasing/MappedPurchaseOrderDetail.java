package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.PurchaseOrderDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedPurchaseOrderDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Purchasing";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrderDetail";

    // ------------------------------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // ------------------------------------------------------------------------------------------- PurchaseOrderDetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID = "PurchaseOrderDetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID = "purchaseOrderDetailId";

    // --------------------------------------------------------------------------------------------------------- DueDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DUE_DATE = "DueDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DUE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DUE_DATE = "dueDate";

    // -------------------------------------------------------------------------------------------------------- OrderQty

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_QTY = "OrderQty";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_QTY = "orderQty";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ------------------------------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ------------------------------------------------------------------------------------------------------- LineTotal

    /**
     * The name of the {@code money, computed as (isnull([OrderQty]*[UnitPrice],(0.00))), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_LINE_TOTAL = "LineTotal";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

    // ----------------------------------------------------------------------------------------------------- ReceivedQty

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECEIVED_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECEIVED_QTY = "receivedQty";

    // ----------------------------------------------------------------------------------------------------- RejectedQty

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_REJECTED_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REJECTED_QTY = "rejectedQty";

    // ------------------------------------------------------------------------------------------------------ StockedQty

    /**
     * The name of the {@code decimal(9, 2), computed as (isnull([ReceivedQty]-[RejectedQty],(0.00))), NOT NULL} column.
     * The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCKED_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCKED_QTY = "stockedQty";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
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
    protected MappedPurchaseOrderDetail() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedPurchaseOrderDetail that)) {
            return false;
        }
        return Objects.equals(purchaseOrderId, that.purchaseOrderId) &&
               Objects.equals(purchaseOrderDetailId, that.purchaseOrderDetailId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(purchaseOrderId, purchaseOrderDetailId);
    }

    // ------------------------------------------------------------------------------------------------- purchaseOrderId

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
    public void setPurchaseOrderId(final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // ------------------------------------------------------------------------------------------- purchaseOrderDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute.
     */
    public Integer getPurchaseOrderDetailId() {
        return purchaseOrderDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute with the specified value.
     *
     * @param purchaseOrderDetailId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID} attribute.
     */
    protected void setPurchaseOrderDetailId(final Integer purchaseOrderDetailId) {
        this.purchaseOrderDetailId = purchaseOrderDetailId;
    }

    // --------------------------------------------------------------------------------------------------------- dueDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public LocalDateTime getDueDate() {
        return dueDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DUE_DATE} attribute with the specified value.
     *
     * @param dueDate new value for {@value #ATTRIBUTE_NAME_DUE_DATE} attribute.
     */
    public void setDueDate(final LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    // -------------------------------------------------------------------------------------------------------- orderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public Short getOrderQty() {
        return orderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute with the specified value.
     *
     * @param orderQty new value for {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public void setOrderQty(final Short orderQty) {
        this.orderQty = orderQty;
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
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

    // ------------------------------------------------------------------------------------------------------- lineTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param lineTotal new value for {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    protected void setLineTotal(final BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    // ----------------------------------------------------------------------------------------------------- receivedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute.
     */
    public BigDecimal getReceivedQty() {
        return receivedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute with the specified value.
     *
     * @param receivedQty new value for {@value #ATTRIBUTE_NAME_RECEIVED_QTY} attribute.
     */
    public void setReceivedQty(final BigDecimal receivedQty) {
        this.receivedQty = receivedQty;
    }

    // ----------------------------------------------------------------------------------------------------- rejectedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute.
     */
    public BigDecimal getRejectedQty() {
        return rejectedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute with the specified value.
     *
     * @param rejectedQty new value for {@value #ATTRIBUTE_NAME_REJECTED_QTY} attribute.
     */
    public void setRejectedQty(final BigDecimal rejectedQty) {
        this.rejectedQty = rejectedQty;
    }

    // ------------------------------------------------------------------------------------------------------ stockedQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     */
    public BigDecimal getStockedQty() {
        return stockedQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param stockedQty new value for {@value #ATTRIBUTE_NAME_STOCKED_QTY} attribute.
     */
    protected void setStockedQty(final BigDecimal stockedQty) {
        this.stockedQty = stockedQty;
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
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer purchaseOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_DETAIL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer purchaseOrderDetailId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DUE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime dueDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short orderQty;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_TOTAL,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private BigDecimal lineTotal;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_RECEIVED_QTY - COLUMN_SCALE_RECEIVED_QTY, fraction = COLUMN_SCALE_RECEIVED_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RECEIVED_QTY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_RECEIVED_QTY,
            scale = COLUMN_SCALE_RECEIVED_QTY
    )
    private BigDecimal receivedQty;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_REJECTED_QTY - COLUMN_SCALE_REJECTED_QTY, fraction = COLUMN_SCALE_REJECTED_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REJECTED_QTY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_REJECTED_QTY,
            scale = COLUMN_SCALE_REJECTED_QTY
    )
    private BigDecimal rejectedQty;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_STOCKED_QTY - COLUMN_SCALE_STOCKED_QTY, fraction = COLUMN_SCALE_STOCKED_QTY)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCKED_QTY,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_STOCKED_QTY,
            scale = COLUMN_SCALE_STOCKED_QTY
    )
    private BigDecimal stockedQty;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (getdate()) fills it on an INSERT, and the uPurchaseOrderDetail trigger
    // on every UPDATE
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime modifiedDate;
}

