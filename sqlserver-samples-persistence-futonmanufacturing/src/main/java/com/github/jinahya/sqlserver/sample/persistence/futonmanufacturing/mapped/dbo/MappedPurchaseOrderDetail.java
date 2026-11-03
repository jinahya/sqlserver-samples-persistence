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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.PurchaseOrderDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_PURCHASE_ORDER_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.PurchaseOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code QuantityReceived}</th><td>{@code ((0))}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code dbo.PurchaseOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code LineTotal}</th><td>{@code ([Quantity]*[UnitPrice])}</td><td>{@code true}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedPurchaseOrderDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrderDetail";

    // ---------------------------------------------------------------------------------- PODetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PO_DETAIL_ID = "PODetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PO_DETAIL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PO_DETAIL_ID = "poDetailId";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // ---------------------------------------------------------------------------------- LineNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_NUMBER = "LineNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_NUMBER = "lineNumber";

    // -------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ----------------------------------------------------------------------------------- UnitPrice

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

    // ---------------------------------------------------------------------------- QuantityReceived

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_RECEIVED = "QuantityReceived";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_RECEIVED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_RECEIVED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_RECEIVED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_RECEIVED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_RECEIVED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_RECEIVED = "quantityReceived";

    // ----------------------------------------------------------------------------------- LineTotal

    /**
     * The name of the {@code decimal(37, 6), computed as ([Quantity]*[UnitPrice])} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_TOTAL = "LineTotal";

    /**
     * The precision of the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LINE_TOTAL = 37;

    /**
     * The scale of the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LINE_TOTAL = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

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
               + "poDetailId=" + poDetailId
               + ",purchaseOrderId=" + purchaseOrderId
               + ",lineNumber=" + lineNumber
               + ",itemId=" + itemId
               + ",quantity=" + quantity
               + ",unitPrice=" + unitPrice
               + ",quantityReceived=" + quantityReceived
               + ",lineTotal=" + lineTotal
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
        return Objects.equals(poDetailId, that.poDetailId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(poDetailId);
    }

    // ---------------------------------------------------------------------------------- poDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PO_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PO_DETAIL_ID} attribute.
     */
    public Integer getPoDetailId() {
        return poDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PO_DETAIL_ID} attribute with the specified value.
     *
     * @param poDetailId new value for {@value #ATTRIBUTE_NAME_PO_DETAIL_ID} attribute.
     */
    protected void setPoDetailId(final Integer poDetailId) {
        this.poDetailId = poDetailId;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param purchaseOrderId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    protected void setPurchaseOrderId(final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // ---------------------------------------------------------------------------------- lineNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute.
     */
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute with the specified value.
     *
     * @param lineNumber new value for {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute.
     */
    public void setLineNumber(final Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    // -------------------------------------------------------------------------------------- itemId

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

    // ------------------------------------------------------------------------------------ quantity

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
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ---------------------------------------------------------------------------- quantityReceived

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_RECEIVED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_RECEIVED} attribute.
     */
    public BigDecimal getQuantityReceived() {
        return quantityReceived;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_RECEIVED} attribute with the specified value.
     *
     * @param quantityReceived new value for {@value #ATTRIBUTE_NAME_QUANTITY_RECEIVED} attribute.
     */
    public void setQuantityReceived(final BigDecimal quantityReceived) {
        this.quantityReceived = quantityReceived;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param lineTotal new value for {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    protected void setLineTotal(final BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PO_DETAIL_ID, nullable = false, insertable = true, updatable = false)
    private Integer poDetailId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, nullable = false, insertable = false, updatable = false)
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_NUMBER, nullable = false, insertable = true, updatable = true)
    private Integer lineNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_QUANTITY - COLUMN_SCALE_QUANTITY, fraction = COLUMN_SCALE_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY, scale = COLUMN_SCALE_QUANTITY)
    private BigDecimal quantity;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_QUANTITY_RECEIVED - COLUMN_SCALE_QUANTITY_RECEIVED,
            fraction = COLUMN_SCALE_QUANTITY_RECEIVED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_RECEIVED, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_RECEIVED, scale = COLUMN_SCALE_QUANTITY_RECEIVED)
    @MsDefault("((0))")
    private BigDecimal quantityReceived;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Digits(integer = COLUMN_PRECISION_LINE_TOTAL - COLUMN_SCALE_LINE_TOTAL, fraction = COLUMN_SCALE_LINE_TOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LINE_TOTAL, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_LINE_TOTAL, scale = COLUMN_SCALE_LINE_TOTAL)
    @MsComputedColumn(value = "([Quantity]*[UnitPrice])", persisted = true)
    private BigDecimal lineTotal;
}

