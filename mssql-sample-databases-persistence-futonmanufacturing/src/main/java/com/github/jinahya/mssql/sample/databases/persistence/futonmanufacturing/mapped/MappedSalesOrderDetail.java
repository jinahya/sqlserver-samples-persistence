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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.SalesOrderDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_SALES_ORDER_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesOrderDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesOrderDetail";

    // ------------------------------------------------------------------------------------------------------ SODetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SO_DETAIL_ID = "SODetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SO_DETAIL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SO_DETAIL_ID = "soDetailId";

    // ---------------------------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    // ------------------------------------------------------------------------------------------------------ LineNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_NUMBER = "LineNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_NUMBER = "lineNumber";

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

    // -------------------------------------------------------------------------------------------------------- Quantity

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ------------------------------------------------------------------------------------------------- QuantityShipped

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_SHIPPED = "QuantityShipped";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_SHIPPED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_SHIPPED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_SHIPPED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_SHIPPED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SHIPPED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SHIPPED = "quantityShipped";

    // ------------------------------------------------------------------------------------------------------- LineTotal

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

    // ------------------------------------------------------------------------------------------------- DiscountPercent

    /**
     * The name of the {@code decimal(5, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_PERCENT = "DiscountPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PERCENT = "discountPercent";

    // -------------------------------------------------------------------------------------------------- DiscountAmount

    /**
     * The name of the {@code decimal(38, 6), computed as ((([Quantity]*[UnitPrice])*[DiscountPercent])/(100))} column.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_AMOUNT = "DiscountAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_AMOUNT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_AMOUNT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_AMOUNT = "discountAmount";

    // ------------------------------------------------------------------------------------------------------- NetAmount

    /**
     * The name of the {@code decimal(38, 6), computed as
     * ([Quantity]*[UnitPrice]-(([Quantity]*[UnitPrice])*[DiscountPercent])/(100))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NET_AMOUNT = "NetAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_NET_AMOUNT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_NET_AMOUNT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_AMOUNT = "netAmount";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrderDetail() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "soDetailId=" + soDetailId
               + ",salesOrderId=" + salesOrderId
               + ",lineNumber=" + lineNumber
               + ",itemId=" + itemId
               + ",quantity=" + quantity
               + ",unitPrice=" + unitPrice
               + ",quantityShipped=" + quantityShipped
               + ",lineTotal=" + lineTotal
               + ",discountPercent=" + discountPercent
               + ",discountAmount=" + discountAmount
               + ",netAmount=" + netAmount
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
        if (!(obj instanceof MappedSalesOrderDetail that)) {
            return false;
        }
        return Objects.equals(soDetailId, that.soDetailId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(soDetailId);
    }

    // ------------------------------------------------------------------------------------------------------ soDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     */
    @Nullable
    public Integer getSoDetailId() {
        return soDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute with the specified value.
     *
     * @param soDetailId new value for {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     */
    protected void setSoDetailId(@Nullable final Integer soDetailId) {
        this.soDetailId = soDetailId;
    }

    // ---------------------------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    @Nonnull
    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected void setSalesOrderId(@Nonnull final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    // ------------------------------------------------------------------------------------------------------ lineNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute.
     */
    @Nonnull
    public Integer getLineNumber() {
        return lineNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute with the specified value.
     *
     * @param lineNumber new value for {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute.
     */
    public void setLineNumber(@Nonnull final Integer lineNumber) {
        this.lineNumber = lineNumber;
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

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    @Nonnull
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(@Nonnull final BigDecimal quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    @Nonnull
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(@Nonnull final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ------------------------------------------------------------------------------------------------- quantityShipped

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute.
     */
    @Nullable
    public BigDecimal getQuantityShipped() {
        return quantityShipped;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute with the specified value.
     *
     * @param quantityShipped new value for {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute.
     */
    public void setQuantityShipped(@Nullable final BigDecimal quantityShipped) {
        this.quantityShipped = quantityShipped;
    }

    // ------------------------------------------------------------------------------------------------------- lineTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    @Nullable
    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param lineTotal new value for {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute.
     */
    protected void setLineTotal(@Nullable final BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    // ------------------------------------------------------------------------------------------------- discountPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    @Nullable
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute with the specified value.
     *
     * @param discountPercent new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    public void setDiscountPercent(@Nullable final BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    // -------------------------------------------------------------------------------------------------- discountAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    @Nullable
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param discountAmount new value for {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    protected void setDiscountAmount(@Nullable final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    // ------------------------------------------------------------------------------------------------------- netAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     */
    @Nullable
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param netAmount new value for {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     */
    protected void setNetAmount(@Nullable final BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SO_DETAIL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer soDetailId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SALES_ORDER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer salesOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LINE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer lineNumber;

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
    @Digits(integer = COLUMN_PRECISION_QUANTITY - COLUMN_SCALE_QUANTITY, fraction = COLUMN_SCALE_QUANTITY)
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY,
            scale = COLUMN_SCALE_QUANTITY
    )
    private BigDecimal quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @NotNull
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_QUANTITY_SHIPPED - COLUMN_SCALE_QUANTITY_SHIPPED, fraction = COLUMN_SCALE_QUANTITY_SHIPPED)
    @Column(name = COLUMN_NAME_QUANTITY_SHIPPED,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_SHIPPED,
            scale = COLUMN_SCALE_QUANTITY_SHIPPED
    )
    private BigDecimal quantityShipped;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_LINE_TOTAL - COLUMN_SCALE_LINE_TOTAL, fraction = COLUMN_SCALE_LINE_TOTAL)
    @Column(name = COLUMN_NAME_LINE_TOTAL,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_LINE_TOTAL,
            scale = COLUMN_SCALE_LINE_TOTAL
    )
    private BigDecimal lineTotal;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_PERCENT - COLUMN_SCALE_DISCOUNT_PERCENT, fraction = COLUMN_SCALE_DISCOUNT_PERCENT)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNT_PERCENT,
            scale = COLUMN_SCALE_DISCOUNT_PERCENT
    )
    private BigDecimal discountPercent;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_AMOUNT - COLUMN_SCALE_DISCOUNT_AMOUNT, fraction = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @Column(name = COLUMN_NAME_DISCOUNT_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_DISCOUNT_AMOUNT,
            scale = COLUMN_SCALE_DISCOUNT_AMOUNT
    )
    private BigDecimal discountAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_NET_AMOUNT - COLUMN_SCALE_NET_AMOUNT, fraction = COLUMN_SCALE_NET_AMOUNT)
    @Column(name = COLUMN_NAME_NET_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_NET_AMOUNT,
            scale = COLUMN_SCALE_NET_AMOUNT
    )
    private BigDecimal netAmount;
}

