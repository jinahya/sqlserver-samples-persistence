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

/**
 * A mapped superclass which holds the mappings of the {@code dbo.SalesReturnDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_RETURN_ID}, {@value #COLUMN_NAME_SO_DETAIL_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are
 * mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesReturnDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesReturnDetail";

    // ------------------------------------------------------------------------------ ReturnDetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_DETAIL_ID = "ReturnDetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_DETAIL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_DETAIL_ID = "returnDetailId";

    // ------------------------------------------------------------------------------------ ReturnID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_ID = "ReturnID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_ID = "returnId";

    // ---------------------------------------------------------------------------------- SODetailID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SO_DETAIL_ID = "SODetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SO_DETAIL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SO_DETAIL_ID = "soDetailId";

    // -------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ---------------------------------------------------------------------------- QuantityReturned

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_RETURNED = "QuantityReturned";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_RETURNED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_RETURNED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_RETURNED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_RETURNED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_RETURNED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_RETURNED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_RETURNED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_RETURNED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_RETURNED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_RETURNED = "quantityReturned";

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

    // -------------------------------------------------------------------------------- RefundAmount

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFUND_AMOUNT = "RefundAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REFUND_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REFUND_AMOUNT = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_REFUND_AMOUNT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_REFUND_AMOUNT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFUND_AMOUNT = "refundAmount";

    // --------------------------------------------------------------------------------- Disposition

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISPOSITION = "Disposition";

    /**
     * The length of the {@value #COLUMN_NAME_DISPOSITION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DISPOSITION = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISPOSITION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISPOSITION = "disposition";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesReturnDetail() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "returnDetailId=" + returnDetailId
               + ",returnId=" + returnId
               + ",soDetailId=" + soDetailId
               + ",itemId=" + itemId
               + ",quantityReturned=" + quantityReturned
               + ",unitPrice=" + unitPrice
               + ",refundAmount=" + refundAmount
               + ",disposition=" + disposition
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSalesReturnDetail that)) {
            return false;
        }
        return getReturnDetailId() != null
               && getReturnDetailId().equals(that.getReturnDetailId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedSalesReturnDetail.class.hashCode();
    }

    // ------------------------------------------------------------------------------ returnDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_DETAIL_ID} attribute.
     */
    public Integer getReturnDetailId() {
        return returnDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_DETAIL_ID} attribute with the specified value.
     *
     * @param returnDetailId new value for {@value #ATTRIBUTE_NAME_RETURN_DETAIL_ID} attribute.
     */
    protected void setReturnDetailId(final Integer returnDetailId) {
        this.returnDetailId = returnDetailId;
    }

    // ------------------------------------------------------------------------------------ returnId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_ID} attribute.
     */
    protected Integer getReturnId() {
        return returnId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param returnId new value for {@value #ATTRIBUTE_NAME_RETURN_ID} attribute.
     */
    protected void setReturnId(final Integer returnId) {
        this.returnId = returnId;
    }

    // ---------------------------------------------------------------------------------- soDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     */
    protected Integer getSoDetailId() {
        return soDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param soDetailId new value for {@value #ATTRIBUTE_NAME_SO_DETAIL_ID} attribute.
     */
    protected void setSoDetailId(final Integer soDetailId) {
        this.soDetailId = soDetailId;
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

    // ---------------------------------------------------------------------------- quantityReturned

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_RETURNED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_RETURNED} attribute.
     */
    public BigDecimal getQuantityReturned() {
        return quantityReturned;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_RETURNED} attribute with the specified value.
     *
     * @param quantityReturned new value for {@value #ATTRIBUTE_NAME_QUANTITY_RETURNED} attribute.
     */
    public void setQuantityReturned(final BigDecimal quantityReturned) {
        this.quantityReturned = quantityReturned;
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

    // -------------------------------------------------------------------------------- refundAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute.
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute with the specified value.
     *
     * @param refundAmount new value for {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute.
     */
    public void setRefundAmount(final BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    // --------------------------------------------------------------------------------- disposition

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISPOSITION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISPOSITION} attribute.
     */
    public String getDisposition() {
        return disposition;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISPOSITION} attribute with the specified value.
     *
     * @param disposition new value for {@value #ATTRIBUTE_NAME_DISPOSITION} attribute.
     */
    public void setDisposition(final String disposition) {
        this.disposition = disposition;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_RETURN_DETAIL_ID, nullable = false, insertable = false, updatable = false)
    private Integer returnDetailId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RETURN_ID, nullable = false, insertable = false, updatable = false)
    private Integer returnId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SO_DETAIL_ID, nullable = false, insertable = false, updatable = false)
    private Integer soDetailId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_RETURNED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_RETURNED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_RETURNED, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_RETURNED, scale = COLUMN_SCALE_QUANTITY_RETURNED)
    private BigDecimal quantityReturned;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_REFUND_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_REFUND_AMOUNT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REFUND_AMOUNT, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_REFUND_AMOUNT, scale = COLUMN_SCALE_REFUND_AMOUNT)
    private BigDecimal refundAmount;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DISPOSITION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISPOSITION, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_DISPOSITION)
    private String disposition;
}

