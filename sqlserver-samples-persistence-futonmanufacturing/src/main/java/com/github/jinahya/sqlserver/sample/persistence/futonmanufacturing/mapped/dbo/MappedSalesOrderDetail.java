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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.SalesOrderDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_SALES_ORDER_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.SalesOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code QuantityShipped}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code DiscountPercent}</th><td>{@code ((0))}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code dbo.SalesOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code LineTotal}</th><td>{@code ([Quantity]*[UnitPrice])}</td><td>{@code true}</td></tr>
 * <tr><th scope="row">{@code DiscountAmount}</th>
 *     <td>{@code ((([Quantity]*[UnitPrice])*[DiscountPercent])/(100))}</td><td>{@code true}</td></tr>
 * <tr><th scope="row">{@code NetAmount}</th>
 *     <td>{@code ([Quantity]*[UnitPrice]-(([Quantity]*[UnitPrice])*[DiscountPercent])/(100))}</td>
 *     <td>{@code true}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedSalesOrderDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesOrderDetail";

    // ---------------------------------------------------------------------------------- SODetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SO_DETAIL_ID = "SODetailID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SO_DETAIL_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SO_DETAIL_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SO_DETAIL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SO_DETAIL_ID = "soDetailId";

    // -------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SALES_ORDER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SALES_ORDER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    // ---------------------------------------------------------------------------------- LineNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_NUMBER = "LineNumber";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LINE_NUMBER = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LINE_NUMBER} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LINE_NUMBER = SqlServerConstants.INT_MAX;

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_ITEM_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_ITEM_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ITEM_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_ITEM_ID = SqlServerConstants.INT_MAX;

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY = "9999999999999999.99";

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, the minimum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE = "-99999999999999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, the maximum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE = "99999999999999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ----------------------------------------------------------------------------- QuantityShipped

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_SHIPPED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_SHIPPED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_SHIPPED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_SHIPPED = "quantityShipped";

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute, the minimum of the
     * {@code decimal(37, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LINE_TOTAL = "-9999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute, the maximum of the
     * {@code decimal(37, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LINE_TOTAL = "9999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

    // ----------------------------------------------------------------------------- DiscountPercent

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DISCOUNT_PERCENT = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DISCOUNT_PERCENT = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PERCENT = "discountPercent";

    // ------------------------------------------------------------------------------ DiscountAmount

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DISCOUNT_AMOUNT = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DISCOUNT_AMOUNT = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_AMOUNT = "discountAmount";

    // ----------------------------------------------------------------------------------- NetAmount

    /**
     * The name of the
     * {@code decimal(38, 6), computed as ([Quantity]*[UnitPrice]-(([Quantity]*[UnitPrice])*[DiscountPercent])/(100))}
     * column. The value is {@value}.
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
     * The minimum value of the {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_NET_AMOUNT = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_NET_AMOUNT = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_AMOUNT = "netAmount";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrderDetail() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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

    // ---------------------------------------------------------------------------------- soDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     */
    public Integer getSoDetailId() {
        return soDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute with the specified value.
     *
     * @param soDetailId new value for {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     */
    protected void setSoDetailId(final Integer soDetailId) {
        this.soDetailId = soDetailId;
    }

    // -------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
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
    protected Integer getItemId() {
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

    // ----------------------------------------------------------------------------- quantityShipped

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute.
     */
    public BigDecimal getQuantityShipped() {
        return quantityShipped;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute with the specified value.
     *
     * @param quantityShipped new value for {@value #ATTRIBUTE_NAME_QUANTITY_SHIPPED} attribute.
     */
    public void setQuantityShipped(final BigDecimal quantityShipped) {
        this.quantityShipped = quantityShipped;
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

    // ----------------------------------------------------------------------------- discountPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute with the specified value.
     *
     * @param discountPercent new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    public void setDiscountPercent(final BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    // ------------------------------------------------------------------------------ discountAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param discountAmount new value for {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    protected void setDiscountAmount(final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    // ----------------------------------------------------------------------------------- netAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     */
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param netAmount new value for {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     */
    protected void setNetAmount(final BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(ATTRIBUTE_MAX_SO_DETAIL_ID)
    @Min(ATTRIBUTE_MIN_SO_DETAIL_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SO_DETAIL_ID, nullable = false, insertable = true, updatable = false)
    private Integer soDetailId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_SALES_ORDER_ID)
    @Min(ATTRIBUTE_MIN_SALES_ORDER_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_ORDER_ID, nullable = false, insertable = false, updatable = false)
    private Integer salesOrderId;

    // ---------------------------------------------------------------------------------------------
    @Max(ATTRIBUTE_MAX_LINE_NUMBER)
    @Min(ATTRIBUTE_MIN_LINE_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_NUMBER, nullable = false, insertable = true, updatable = true)
    private Integer lineNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_ITEM_ID)
    @Min(ATTRIBUTE_MIN_ITEM_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY, scale = COLUMN_SCALE_QUANTITY)
    private BigDecimal quantity;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_SHIPPED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_SHIPPED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_SHIPPED, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_SHIPPED, scale = COLUMN_SCALE_QUANTITY_SHIPPED)
    @MsDefault("((0))")
    private BigDecimal quantityShipped;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_LINE_TOTAL)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_LINE_TOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LINE_TOTAL, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_LINE_TOTAL, scale = COLUMN_SCALE_LINE_TOTAL)
    @MsComputedColumn(value = "([Quantity]*[UnitPrice])", persisted = true)
    private BigDecimal lineTotal;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DISCOUNT_PERCENT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DISCOUNT_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_DISCOUNT_PERCENT, scale = COLUMN_SCALE_DISCOUNT_PERCENT)
    @MsDefault("((0))")
    private BigDecimal discountPercent;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DISCOUNT_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DISCOUNT_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_AMOUNT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_DISCOUNT_AMOUNT, scale = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @MsComputedColumn(value = "((([Quantity]*[UnitPrice])*[DiscountPercent])/(100))", persisted = true)
    private BigDecimal discountAmount;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_NET_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_NET_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_AMOUNT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_NET_AMOUNT, scale = COLUMN_SCALE_NET_AMOUNT)
    @MsComputedColumn(value = "([Quantity]*[UnitPrice]-(([Quantity]*[UnitPrice])*[DiscountPercent])/(100))",
                      persisted = true)
    private BigDecimal netAmount;
}

