package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
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
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesOrderDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID}, {@value #COLUMN_NAME_SPECIAL_OFFER_ID} columns are mapped
 * read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.SalesOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SalesOrderID}</th>
 *     <td>Primary key. Foreign key to SalesOrderHeader.SalesOrderID.</td></tr>
 * <tr><th scope="row">{@code SalesOrderDetailID}</th>
 *     <td>Primary key. One incremental unique number per product sold.</td></tr>
 * <tr><th scope="row">{@code CarrierTrackingNumber}</th>
 *     <td>Shipment tracking number supplied by the shipper.</td></tr>
 * <tr><th scope="row">{@code OrderQty}</th><td>Quantity ordered per product.</td></tr>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product sold to customer. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code SpecialOfferID}</th>
 *     <td>Promotional code. Foreign key to SpecialOffer.SpecialOfferID.</td></tr>
 * <tr><th scope="row">{@code UnitPrice}</th><td>Selling price of a single product.</td></tr>
 * <tr><th scope="row">{@code UnitPriceDiscount}</th><td>Discount amount.</td></tr>
 * <tr><th scope="row">{@code LineTotal}</th>
 *     <td>Per product subtotal. Computed as UnitPrice * (1 - UnitPriceDiscount) *
 *     OrderQty.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.SalesOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code UnitPriceDiscount}</th><td>{@code ((0.0))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code Sales.SalesOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code LineTotal}</th>
 *     <td>{@code (isnull(([UnitPrice]*((1.0)-[UnitPriceDiscount]))*[OrderQty],(0.0)))}</td><td
 *     >{@code false}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Triggers on {@code Sales.SalesOrderDetail}</caption>
 * <thead>
 * <tr><th scope="col">Trigger</th><th scope="col">Timing</th><th scope="col">Events</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code iduSalesOrderDetail}</th>
 *     <td>{@code AFTER}</td><td>{@code INSERT}, {@code UPDATE}, {@code DELETE}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/isnull-transact-sql">ISNULL</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedSalesOrderDetail.TABLE_SCHEMA,
        table = MappedSalesOrderDetail.TABLE_NAME,
        value = "Individual products associated with a specific sales order. See SalesOrderHeader."
)
@MsTrigger(
        name = "iduSalesOrderDetail",
        events = {MsTrigger.Event.INSERT, MsTrigger.Event.UPDATE, MsTrigger.Event.DELETE}
)
public abstract class MappedSalesOrderDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesOrderDetail";

    // -------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    // -------------------------------------------------------------------------- SalesOrderDetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_DETAIL_ID = "SalesOrderDetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_DETAIL_ID} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID = "salesOrderDetailId";

    // ----------------------------------------------------------------------- CarrierTrackingNumber

    /**
     * The name of the {@code nvarchar(25)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CARRIER_TRACKING_NUMBER = "CarrierTrackingNumber";

    /**
     * The length of the {@value #COLUMN_NAME_CARRIER_TRACKING_NUMBER} column. The value is
     * {@value}.
     */
    public static final int COLUMN_LENGTH_CARRIER_TRACKING_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CARRIER_TRACKING_NUMBER}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER = "carrierTrackingNumber";

    // ------------------------------------------------------------------------------------ OrderQty

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_QTY = "OrderQty";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ORDER_QTY} attribute, as the
     * {@code CK_SalesOrderDetail_OrderQty} check constraint ({@code [OrderQty]>(0)}) requires. The
     * value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_ORDER_QTY = +1;

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

    // ------------------------------------------------------------------------------ SpecialOfferID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIAL_OFFER_ID = "SpecialOfferID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIAL_OFFER_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER_ID = "specialOfferId";

    // ----------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, as the
     * {@code CK_SalesOrderDetail_UnitPrice} check constraint ({@code [UnitPrice]>=(0.00)})
     * requires. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // --------------------------------------------------------------------------- UnitPriceDiscount

    /**
     * The name of the {@code money, DEFAULT ((0.0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE_DISCOUNT = "UnitPriceDiscount";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute, as the
     * {@code CK_SalesOrderDetail_UnitPriceDiscount} check constraint
     * ({@code [UnitPriceDiscount]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE_DISCOUNT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE_DISCOUNT} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT = "unitPriceDiscount";

    // ----------------------------------------------------------------------------------- LineTotal

    /**
     * The name of the
     * {@code numeric(38, 6), computed as
     * (isnull(([UnitPrice]*((1.0)-[UnitPriceDiscount]))*[OrderQty],(0.0))), NOT NULL} column. The
     * value is {@value}.
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
     * The minimum value of the {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute, the minimum of the
     * {@code numeric(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LINE_TOTAL = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LINE_TOTAL} attribute, the maximum of the
     * {@code numeric(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LINE_TOTAL = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_TOTAL} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_TOTAL = "lineTotal";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedSalesOrderDetail() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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
     * @implSpec Equality is by the identifier, once each of its attributes is assigned; an
     *         instance whose identifier is not yet assigned, which the database does on insert,
     *         equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSalesOrderDetail that)) {
            return false;
        }
        return getSalesOrderId() != null
               && getSalesOrderDetailId() != null
               && getSalesOrderId().equals(that.getSalesOrderId())
               && getSalesOrderDetailId().equals(that.getSalesOrderDetailId());
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
        return MappedSalesOrderDetail.class.hashCode();
    }

    // -------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the
     * specified value.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    // -------------------------------------------------------------------------- salesOrderDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute.
     */
    public Integer getSalesOrderDetailId() {
        return salesOrderDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID} attribute with the
     * specified value.
     *
     * @param salesOrderDetailId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID}
     *                           attribute.
     */
    protected void setSalesOrderDetailId(final Integer salesOrderDetailId) {
        this.salesOrderDetailId = salesOrderDetailId;
    }

    // ----------------------------------------------------------------------- carrierTrackingNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute.
     */
    public String getCarrierTrackingNumber() {
        return carrierTrackingNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER} attribute with the
     * specified value.
     *
     * @param carrierTrackingNumber new value for {@value #ATTRIBUTE_NAME_CARRIER_TRACKING_NUMBER}
     *                              attribute.
     */
    public void setCarrierTrackingNumber(final String carrierTrackingNumber) {
        this.carrierTrackingNumber = carrierTrackingNumber;
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

    // ------------------------------------------------------------------------------ specialOfferId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    protected Integer getSpecialOfferId() {
        return specialOfferId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param specialOfferId new value for {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    protected void setSpecialOfferId(final Integer specialOfferId) {
        this.specialOfferId = specialOfferId;
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

    // --------------------------------------------------------------------------- unitPriceDiscount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute.
     */
    public BigDecimal getUnitPriceDiscount() {
        return unitPriceDiscount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT} attribute with the
     * specified value.
     *
     * @param unitPriceDiscount new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE_DISCOUNT}
     *                          attribute.
     */
    public void setUnitPriceDiscount(final BigDecimal unitPriceDiscount) {
        this.unitPriceDiscount = unitPriceDiscount;
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

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
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
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_SALES_ORDER_ID, nullable = false, insertable = true,
            updatable = false,
            comment = "Primary key. Foreign key to SalesOrderHeader.SalesOrderID.")
    private Integer salesOrderId;

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SALES_ORDER_DETAIL_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Primary key. One incremental unique number per product sold.")
    private Integer salesOrderDetailId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CARRIER_TRACKING_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CARRIER_TRACKING_NUMBER, nullable = true, insertable = true,
            updatable = true, length = COLUMN_LENGTH_CARRIER_TRACKING_NUMBER,
            comment = "Shipment tracking number supplied by the shipper.")
    private String carrierTrackingNumber;

    // ---------------------------------------------------------------------------------------------
    // CK_SalesOrderDetail_OrderQty: ([OrderQty]>(0)); smallint's upper bound
    @Max(SqlServerConstants.SMALLINT_MAX)
    @Min(ATTRIBUTE_MIN_ORDER_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_QTY, nullable = false, insertable = true, updatable = true,
            comment = "Quantity ordered per product.")
    private Integer orderQty;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false,
            comment = "Product sold to customer. Foreign key to Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SPECIAL_OFFER_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Promotional code. Foreign key to SpecialOffer.SpecialOfferID.")
    private Integer specialOfferId;

    // ---------------------------------------------------------------------------------------------
    // CK_SalesOrderDetail_UnitPrice: ([UnitPrice]>=(0.00))
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Selling price of a single product.")
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    // CK_SalesOrderDetail_UnitPriceDiscount: ([UnitPriceDiscount]>=(0.00))
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE_DISCOUNT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE_DISCOUNT, nullable = false, insertable = true,
            updatable = true, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Discount amount.")
    @MsDefault(name = "DF_SalesOrderDetail_UnitPriceDiscount", value = "((0.0))")
    private BigDecimal unitPriceDiscount;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_LINE_TOTAL)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_LINE_TOTAL)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LINE_TOTAL, nullable = false, insertable = false, updatable = false,
            precision = COLUMN_PRECISION_LINE_TOTAL, scale = COLUMN_SCALE_LINE_TOTAL,
            comment = "Per product subtotal. Computed as UnitPrice * (1 - UnitPriceDiscount) * "
                      + "OrderQty.")
    @MsComputedColumn("(isnull(([UnitPrice]*((1.0)-[UnitPriceDiscount]))*[OrderQty],(0.0)))")
    private BigDecimal lineTotal;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_SalesOrderDetail_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_SalesOrderDetail_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

