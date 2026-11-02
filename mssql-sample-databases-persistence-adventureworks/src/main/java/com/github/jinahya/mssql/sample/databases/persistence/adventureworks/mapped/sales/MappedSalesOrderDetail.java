package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesOrderDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID}, {@value #COLUMN_NAME_SPECIAL_OFFER_ID} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
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

    // ---------------------------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    // ---------------------------------------------------------------------------------------------- SalesOrderDetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_DETAIL_ID = "SalesOrderDetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_DETAIL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID = "salesOrderDetailId";

    // ------------------------------------------------------------------------------------------- CarrierTrackingNumber

    /**
     * The name of the {@code nvarchar(25)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CARRIER_TRACKING_NUMBER = "CarrierTrackingNumber";

    /**
     * The length of the {@value #COLUMN_NAME_CARRIER_TRACKING_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CARRIER_TRACKING_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CARRIER_TRACKING_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER = "carrierTrackingNumber";

    // -------------------------------------------------------------------------------------------------------- OrderQty

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_QTY = "OrderQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_ORDER_QTY} column, as the {@code CK_SalesOrderDetail_OrderQty}
     * check constraint ({@code [OrderQty]>(0)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_ORDER_QTY = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_ORDER_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_ORDER_QTY = Short.MAX_VALUE;

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

    // -------------------------------------------------------------------------------------------------- SpecialOfferID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIAL_OFFER_ID = "SpecialOfferID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIAL_OFFER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER_ID = "specialOfferId";

    // ------------------------------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The minimum value of the {@value #COLUMN_NAME_UNIT_PRICE} column, as the {@code CK_SalesOrderDetail_UnitPrice}
     * check constraint ({@code [UnitPrice]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_UNIT_PRICE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ----------------------------------------------------------------------------------------------- UnitPriceDiscount

    /**
     * The name of the {@code money, DEFAULT ((0.0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE_DISCOUNT = "UnitPriceDiscount";

    /**
     * The minimum value of the {@value #COLUMN_NAME_UNIT_PRICE_DISCOUNT} column, as the
     * {@code CK_SalesOrderDetail_UnitPriceDiscount} check constraint ({@code [UnitPriceDiscount]>=(0.00)}) requires.
     * The value is {@value}.
     */
    public static final String COLUMN_MIN_UNIT_PRICE_DISCOUNT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE_DISCOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT = "unitPriceDiscount";

    // ------------------------------------------------------------------------------------------------------- LineTotal

    /**
     * The name of the
     * {@code numeric(38, 6), computed as (isnull(([UnitPrice]*((1.0)-[UnitPriceDiscount]))*[OrderQty],(0.0))), NOT
     * NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_TOTAL = "LineTotal";

    /**
     * The precision of the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LINE_TOTAL = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LINE_TOTAL = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedSalesOrderDetail() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesOrderId=" + salesOrderId
               + ",salesOrderDetailId=" + salesOrderDetailId
               + ",carrierTrackingNumber=" + carrierTrackingNumber
               + ",orderQty=" + orderQty
               + ",productId=" + productId
               + ",specialOfferId=" + specialOfferId
               + ",unitPrice=" + unitPrice
               + ",unitPriceDiscount=" + unitPriceDiscount
               + ",lineTotal=" + lineTotal
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedSalesOrderDetail that)) {
            return false;
        }
        return Objects.equals(salesOrderId, that.salesOrderId) &&
               Objects.equals(salesOrderDetailId, that.salesOrderDetailId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(salesOrderId, salesOrderDetailId);
    }

    // ---------------------------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the specified value.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    public void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    // ---------------------------------------------------------------------------------------------- salesOrderDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute.
     */
    public Integer getSalesOrderDetailId() {
        return salesOrderDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute with the specified value.
     *
     * @param salesOrderDetailId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute.
     */
    protected void setSalesOrderDetailId(final Integer salesOrderDetailId) {
        this.salesOrderDetailId = salesOrderDetailId;
    }

    // ------------------------------------------------------------------------------------------- carrierTrackingNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute.
     */
    public String getCarrierTrackingNumber() {
        return carrierTrackingNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute with the specified value.
     *
     * @param carrierTrackingNumber new value for {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute.
     */
    public void setCarrierTrackingNumber(final String carrierTrackingNumber) {
        this.carrierTrackingNumber = carrierTrackingNumber;
    }

    // -------------------------------------------------------------------------------------------------------- orderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute with the specified value.
     *
     * @param orderQty new value for {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute.
     */
    public void setOrderQty(final Integer orderQty) {
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

    // -------------------------------------------------------------------------------------------------- specialOfferId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    public Integer getSpecialOfferId() {
        return specialOfferId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param specialOfferId new value for {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    protected void setSpecialOfferId(final Integer specialOfferId) {
        this.specialOfferId = specialOfferId;
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

    // ----------------------------------------------------------------------------------------------- unitPriceDiscount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute.
     */
    public BigDecimal getUnitPriceDiscount() {
        return unitPriceDiscount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute with the specified value.
     *
     * @param unitPriceDiscount new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute.
     */
    public void setUnitPriceDiscount(final BigDecimal unitPriceDiscount) {
        this.unitPriceDiscount = unitPriceDiscount;
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

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
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
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_SALES_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer salesOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SALES_ORDER_DETAIL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer salesOrderDetailId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CARRIER_TRACKING_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CARRIER_TRACKING_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CARRIER_TRACKING_NUMBER
    )
    private String carrierTrackingNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderDetail_OrderQty: ([OrderQty]>(0)); smallint's upper bound
    @Max(COLUMN_MAX_ORDER_QTY)
    @Min(COLUMN_MIN_ORDER_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer orderQty;

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
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIAL_OFFER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer specialOfferId;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderDetail_UnitPrice: ([UnitPrice]>=(0.00))
    @DecimalMin(COLUMN_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesOrderDetail_UnitPriceDiscount: ([UnitPriceDiscount]>=(0.00))
    @DecimalMin(COLUMN_MIN_UNIT_PRICE_DISCOUNT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE_DISCOUNT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal unitPriceDiscount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Digits(integer = COLUMN_PRECISION_LINE_TOTAL - COLUMN_SCALE_LINE_TOTAL, fraction = COLUMN_SCALE_LINE_TOTAL)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_TOTAL,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_LINE_TOTAL,
            scale = COLUMN_SCALE_LINE_TOTAL
    )
    private BigDecimal lineTotal;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

