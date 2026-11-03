package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.PurchaseOrderLines} table.
 * <p>
 * The {@value #COLUMN_NAME_PURCHASE_ORDER_ID}, {@value #COLUMN_NAME_STOCK_ITEM_ID},
 * {@value #COLUMN_NAME_PACKAGE_TYPE_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Purchasing.PurchaseOrderLines}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PurchaseOrderLineID}</th>
 *     <td>Numeric ID used for reference to a line on a purchase order within the database</td></tr>
 * <tr><th scope="row">{@code PurchaseOrderID}</th>
 *     <td>Purchase order that this line is associated with</td></tr>
 * <tr><th scope="row">{@code StockItemID}</th><td>Stock item for this purchase order line</td></tr>
 * <tr><th scope="row">{@code OrderedOuters}</th>
 *     <td>Quantity of the stock item that is ordered</td></tr>
 * <tr><th scope="row">{@code Description}</th>
 *     <td>Description of the item to be supplied (Often the stock item name but could be supplier
 *     description)</td></tr>
 * <tr><th scope="row">{@code ReceivedOuters}</th>
 *     <td>Total quantity of the stock item that has been received so far</td></tr>
 * <tr><th scope="row">{@code PackageTypeID}</th><td>Type of package received</td></tr>
 * <tr><th scope="row">{@code ExpectedUnitPricePerOuter}</th>
 *     <td>The unit price that we expect to be charged</td></tr>
 * <tr><th scope="row">{@code LastReceiptDate}</th>
 *     <td>The last date on which this stock item was received for this purchase order</td></tr>
 * <tr><th scope="row">{@code IsOrderLineFinalized}</th>
 *     <td>Is this purchase order line now considered finalized? (Receipted quantities and weights
 *     are often not precise)</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Purchasing.PurchaseOrderLines}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PurchaseOrderLineID}</th>
 *     <td>{@code (NEXT VALUE FOR [Sequences].[PurchaseOrderLineID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPurchaseOrderLine.TABLE_SCHEMA,
        table = MappedPurchaseOrderLine.TABLE_NAME,
        value = "Detail lines from supplier purchase orders"
)
public abstract class MappedPurchaseOrderLine {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrderLines";

    // ------------------------------------------------------------------------- PurchaseOrderLineID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.PurchaseOrderLineID, NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_LINE_ID = "PurchaseOrderLineID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_LINE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_LINE_ID = "purchaseOrderLineId";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // ------------------------------------------------------------------------------- OrderedOuters

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDERED_OUTERS = "OrderedOuters";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDERED_OUTERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDERED_OUTERS = "orderedOuters";

    // --------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ------------------------------------------------------------------------------ ReceivedOuters

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECEIVED_OUTERS = "ReceivedOuters";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECEIVED_OUTERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECEIVED_OUTERS = "receivedOuters";

    // ------------------------------------------------------------------------------- PackageTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_ID = "PackageTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_ID = "packageTypeId";

    // ------------------------------------------------------------------- ExpectedUnitPricePerOuter

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_UNIT_PRICE_PER_OUTER = "ExpectedUnitPricePerOuter";

    /**
     * The precision of the {@value #COLUMN_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EXPECTED_UNIT_PRICE_PER_OUTER = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_EXPECTED_UNIT_PRICE_PER_OUTER = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_UNIT_PRICE_PER_OUTER = "expectedUnitPricePerOuter";

    // ----------------------------------------------------------------------------- LastReceiptDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_RECEIPT_DATE = "LastReceiptDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_RECEIPT_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_RECEIPT_DATE = "lastReceiptDate";

    // ------------------------------------------------------------------------ IsOrderLineFinalized

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ORDER_LINE_FINALIZED = "IsOrderLineFinalized";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ORDER_LINE_FINALIZED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ORDER_LINE_FINALIZED = "isOrderLineFinalized";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------ LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPurchaseOrderLine() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "purchaseOrderLineId=" + purchaseOrderLineId
               + ",purchaseOrderId=" + purchaseOrderId
               + ",stockItemId=" + stockItemId
               + ",orderedOuters=" + orderedOuters
               + ",description=" + description
               + ",receivedOuters=" + receivedOuters
               + ",packageTypeId=" + packageTypeId
               + ",expectedUnitPricePerOuter=" + expectedUnitPricePerOuter
               + ",lastReceiptDate=" + lastReceiptDate
               + ",isOrderLineFinalized=" + isOrderLineFinalized
               + ",lastEditedBy=" + lastEditedBy
               + ",lastEditedWhen=" + lastEditedWhen
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
        if (!(obj instanceof MappedPurchaseOrderLine that)) {
            return false;
        }
        return Objects.equals(purchaseOrderLineId, that.purchaseOrderLineId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(purchaseOrderLineId);
    }

    // ------------------------------------------------------------------------- purchaseOrderLineId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_LINE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_LINE_ID} attribute.
     */
    public Integer getPurchaseOrderLineId() {
        return purchaseOrderLineId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_LINE_ID} attribute with the specified value.
     *
     * @param purchaseOrderLineId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_LINE_ID} attribute.
     */
    protected void setPurchaseOrderLineId(final Integer purchaseOrderLineId) {
        this.purchaseOrderLineId = purchaseOrderLineId;
    }

    // ----------------------------------------------------------------------------- purchaseOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    protected Integer getPurchaseOrderId() {
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

    // --------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(final Integer stockItemId) {
        this.stockItemId = stockItemId;
    }

    // ------------------------------------------------------------------------------- orderedOuters

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDERED_OUTERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDERED_OUTERS} attribute.
     */
    public Integer getOrderedOuters() {
        return orderedOuters;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDERED_OUTERS} attribute with the specified value.
     *
     * @param orderedOuters new value for {@value #ATTRIBUTE_NAME_ORDERED_OUTERS} attribute.
     */
    public void setOrderedOuters(final Integer orderedOuters) {
        this.orderedOuters = orderedOuters;
    }

    // --------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // ------------------------------------------------------------------------------ receivedOuters

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECEIVED_OUTERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECEIVED_OUTERS} attribute.
     */
    public Integer getReceivedOuters() {
        return receivedOuters;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECEIVED_OUTERS} attribute with the specified value.
     *
     * @param receivedOuters new value for {@value #ATTRIBUTE_NAME_RECEIVED_OUTERS} attribute.
     */
    public void setReceivedOuters(final Integer receivedOuters) {
        this.receivedOuters = receivedOuters;
    }

    // ------------------------------------------------------------------------------- packageTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected Integer getPackageTypeId() {
        return packageTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param packageTypeId new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected void setPackageTypeId(final Integer packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    // ------------------------------------------------------------------- expectedUnitPricePerOuter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} attribute.
     */
    public BigDecimal getExpectedUnitPricePerOuter() {
        return expectedUnitPricePerOuter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} attribute with the specified
     * value.
     *
     * @param expectedUnitPricePerOuter new value for {@value #ATTRIBUTE_NAME_EXPECTED_UNIT_PRICE_PER_OUTER} attribute.
     */
    public void setExpectedUnitPricePerOuter(final BigDecimal expectedUnitPricePerOuter) {
        this.expectedUnitPricePerOuter = expectedUnitPricePerOuter;
    }

    // ----------------------------------------------------------------------------- lastReceiptDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute.
     */
    public LocalDate getLastReceiptDate() {
        return lastReceiptDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute with the specified value.
     *
     * @param lastReceiptDate new value for {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute.
     */
    public void setLastReceiptDate(final LocalDate lastReceiptDate) {
        this.lastReceiptDate = lastReceiptDate;
    }

    // ------------------------------------------------------------------------ isOrderLineFinalized

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ORDER_LINE_FINALIZED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ORDER_LINE_FINALIZED} attribute.
     */
    public Boolean getIsOrderLineFinalized() {
        return isOrderLineFinalized;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ORDER_LINE_FINALIZED} attribute with the specified value.
     *
     * @param isOrderLineFinalized new value for {@value #ATTRIBUTE_NAME_IS_ORDER_LINE_FINALIZED} attribute.
     */
    public void setIsOrderLineFinalized(final Boolean isOrderLineFinalized) {
        this.isOrderLineFinalized = isOrderLineFinalized;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------ lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.PurchaseOrderLineID", schema = "Sequences",
                       sequenceName = "PurchaseOrderLineID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.PurchaseOrderLineID")
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_LINE_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_LINE_ID,
                          value = "Numeric ID used for reference to a line on a purchase order "
                                  + "within the database")
    @MsDefault(name = "DF_Purchasing_PurchaseOrderLines_PurchaseOrderLineID",
               value = "(NEXT VALUE FOR [Sequences].[PurchaseOrderLineID])")
    private Integer purchaseOrderLineId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                          value = "Purchase order that this line is associated with")
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_ID,
                          value = "Stock item for this purchase order line")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDERED_OUTERS, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_ORDERED_OUTERS,
                          value = "Quantity of the stock item that is ordered")
    private Integer orderedOuters;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DESCRIPTION, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DESCRIPTION)
    @MsDescription.Column(name = COLUMN_NAME_DESCRIPTION,
                          value = "Description of the item to be supplied (Often the stock item "
                                  + "name but could be supplier description)")
    private String description;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RECEIVED_OUTERS, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_RECEIVED_OUTERS,
                          value = "Total quantity of the stock item that has been received so far")
    private Integer receivedOuters;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PACKAGE_TYPE_ID, value = "Type of package received")
    private Integer packageTypeId;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_EXPECTED_UNIT_PRICE_PER_OUTER - COLUMN_SCALE_EXPECTED_UNIT_PRICE_PER_OUTER,
            fraction = COLUMN_SCALE_EXPECTED_UNIT_PRICE_PER_OUTER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPECTED_UNIT_PRICE_PER_OUTER, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_EXPECTED_UNIT_PRICE_PER_OUTER, scale = COLUMN_SCALE_EXPECTED_UNIT_PRICE_PER_OUTER)
    @MsDescription.Column(name = COLUMN_NAME_EXPECTED_UNIT_PRICE_PER_OUTER,
                          value = "The unit price that we expect to be charged")
    private BigDecimal expectedUnitPricePerOuter;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_RECEIPT_DATE, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_LAST_RECEIPT_DATE,
                          value = "The last date on which this stock item was received for this "
                                  + "purchase order")
    private LocalDate lastReceiptDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ORDER_LINE_FINALIZED, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_IS_ORDER_LINE_FINALIZED,
                          value = "Is this purchase order line now considered finalized? "
                                  + "(Receipted quantities and weights are often not precise)")
    private Boolean isOrderLineFinalized;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = true, updatable = true)
    @MsDefault(name = "DF_Purchasing_PurchaseOrderLines_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

