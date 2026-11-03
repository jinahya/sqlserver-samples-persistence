package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockItemTransactions} table.
 * <p>
 * The {@value #COLUMN_NAME_STOCK_ITEM_ID}, {@value #COLUMN_NAME_TRANSACTION_TYPE_ID},
 * {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_INVOICE_ID}, {@value #COLUMN_NAME_SUPPLIER_ID},
 * {@value #COLUMN_NAME_PURCHASE_ORDER_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Warehouse.StockItemTransactions}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemTransactionID}</th>
 *     <td>Numeric ID used to refer to a stock item transaction within the database</td></tr>
 * <tr><th scope="row">{@code StockItemID}</th><td>StockItem for this transaction</td></tr>
 * <tr><th scope="row">{@code TransactionTypeID}</th><td>Type of transaction</td></tr>
 * <tr><th scope="row">{@code CustomerID}</th>
 *     <td>Customer for this transaction (if applicable)</td></tr>
 * <tr><th scope="row">{@code InvoiceID}</th>
 *     <td>ID of an invoice (for transactions associated with an invoice)</td></tr>
 * <tr><th scope="row">{@code SupplierID}</th>
 *     <td>Supplier for this stock transaction (if applicable)</td></tr>
 * <tr><th scope="row">{@code PurchaseOrderID}</th>
 *     <td>ID of an purchase order (for transactions associated with a purchase order)</td></tr>
 * <tr><th scope="row">{@code TransactionOccurredWhen}</th>
 *     <td>Date and time when the transaction occurred</td></tr>
 * <tr><th scope="row">{@code Quantity}</th>
 *     <td>Quantity of stock movement (positive is incoming stock, negative is outgoing)</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Warehouse.StockItemTransactions}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemTransactionID}</th>
 *     <td>{@code (NEXT VALUE FOR [Sequences].[TransactionID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStockItemTransaction.TABLE_SCHEMA,
        table = MappedStockItemTransaction.TABLE_NAME,
        value = "Transactions covering all movements of all stock items"
)
public abstract class MappedStockItemTransaction {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockItemTransactions";

    // ---------------------------------------------------------------------- StockItemTransactionID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.TransactionID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_TRANSACTION_ID = "StockItemTransactionID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_STOCK_ITEM_TRANSACTION_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_STOCK_ITEM_TRANSACTION_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_TRANSACTION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID = "stockItemTransactionId";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_STOCK_ITEM_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_STOCK_ITEM_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // --------------------------------------------------------------------------- TransactionTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_TYPE_ID = "TransactionTypeID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_TRANSACTION_TYPE_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_TRANSACTION_TYPE_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE_ID = "transactionTypeId";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_CUSTOMER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_CUSTOMER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ----------------------------------------------------------------------------------- InvoiceID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVOICE_ID = "InvoiceID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_INVOICE_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_INVOICE_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVOICE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE_ID = "invoiceId";

    // ---------------------------------------------------------------------------------- SupplierID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SUPPLIER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SUPPLIER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PURCHASE_ORDER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PURCHASE_ORDER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // --------------------------------------------------------------------- TransactionOccurredWhen

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_OCCURRED_WHEN = "TransactionOccurredWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_OCCURRED_WHEN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_OCCURRED_WHEN = "transactionOccurredWhen";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code decimal(18, 3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY = 3;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the minimum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY = "-999999999999999.999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the maximum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY = "999999999999999.999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LAST_EDITED_BY = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LAST_EDITED_BY = SqlServerConstants.INT_MAX;

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
    protected MappedStockItemTransaction() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stockItemTransactionId=" + stockItemTransactionId
               + ",stockItemId=" + stockItemId
               + ",transactionTypeId=" + transactionTypeId
               + ",customerId=" + customerId
               + ",invoiceId=" + invoiceId
               + ",supplierId=" + supplierId
               + ",purchaseOrderId=" + purchaseOrderId
               + ",transactionOccurredWhen=" + transactionOccurredWhen
               + ",quantity=" + quantity
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
        if (!(obj instanceof MappedStockItemTransaction that)) {
            return false;
        }
        return Objects.equals(stockItemTransactionId, that.stockItemTransactionId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(stockItemTransactionId);
    }

    // ---------------------------------------------------------------------- stockItemTransactionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID} attribute.
     */
    public Integer getStockItemTransactionId() {
        return stockItemTransactionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID} attribute with the specified value.
     *
     * @param stockItemTransactionId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_TRANSACTION_ID} attribute.
     */
    protected void setStockItemTransactionId(final Integer stockItemTransactionId) {
        this.stockItemTransactionId = stockItemTransactionId;
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

    // --------------------------------------------------------------------------- transactionTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    protected Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param transactionTypeId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    protected void setTransactionTypeId(final Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    // ---------------------------------------------------------------------------------- customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ----------------------------------------------------------------------------------- invoiceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    protected Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param invoiceId new value for {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    protected void setInvoiceId(final Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    // ---------------------------------------------------------------------------------- supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(final Integer supplierId) {
        this.supplierId = supplierId;
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

    // --------------------------------------------------------------------- transactionOccurredWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_OCCURRED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_OCCURRED_WHEN} attribute.
     */
    public LocalDateTime getTransactionOccurredWhen() {
        return transactionOccurredWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_OCCURRED_WHEN} attribute with the specified value.
     *
     * @param transactionOccurredWhen new value for {@value #ATTRIBUTE_NAME_TRANSACTION_OCCURRED_WHEN} attribute.
     */
    public void setTransactionOccurredWhen(final LocalDateTime transactionOccurredWhen) {
        this.transactionOccurredWhen = transactionOccurredWhen;
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
    @Max(ATTRIBUTE_MAX_STOCK_ITEM_TRANSACTION_ID)
    @Min(ATTRIBUTE_MIN_STOCK_ITEM_TRANSACTION_ID)
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.TransactionID", schema = "Sequences",
                       sequenceName = "TransactionID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.TransactionID")
    @Column(name = COLUMN_NAME_STOCK_ITEM_TRANSACTION_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_TRANSACTION_ID,
                          value = "Numeric ID used to refer to a stock item transaction within "
                                  + "the database")
    @MsDefault(name = "DF_Warehouse_StockItemTransactions_StockItemTransactionID",
               value = "(NEXT VALUE FOR [Sequences].[TransactionID])")
    private Integer stockItemTransactionId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_STOCK_ITEM_ID)
    @Min(ATTRIBUTE_MIN_STOCK_ITEM_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_ID,
                          value = "StockItem for this transaction")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_TRANSACTION_TYPE_ID)
    @Min(ATTRIBUTE_MIN_TRANSACTION_TYPE_ID)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_TYPE_ID, value = "Type of transaction")
    private Integer transactionTypeId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_CUSTOMER_ID)
    @Min(ATTRIBUTE_MIN_CUSTOMER_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_ID,
                          value = "Customer for this transaction (if applicable)")
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_INVOICE_ID)
    @Min(ATTRIBUTE_MIN_INVOICE_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INVOICE_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_INVOICE_ID,
                          value = "ID of an invoice (for transactions associated with an invoice)")
    private Integer invoiceId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_SUPPLIER_ID)
    @Min(ATTRIBUTE_MIN_SUPPLIER_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_ID,
                          value = "Supplier for this stock transaction (if applicable)")
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_PURCHASE_ORDER_ID)
    @Min(ATTRIBUTE_MIN_PURCHASE_ORDER_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                          value = "ID of an purchase order (for transactions associated with a "
                                  + "purchase order)")
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_OCCURRED_WHEN, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_OCCURRED_WHEN,
                          value = "Date and time when the transaction occurred")
    private LocalDateTime transactionOccurredWhen;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY, scale = COLUMN_SCALE_QUANTITY)
    @MsDescription.Column(name = COLUMN_NAME_QUANTITY,
                          value = "Quantity of stock movement (positive is incoming stock, "
                                  + "negative is outgoing)")
    private BigDecimal quantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(ATTRIBUTE_MAX_LAST_EDITED_BY)
    @Min(ATTRIBUTE_MIN_LAST_EDITED_BY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = true, updatable = true)
    @MsDefault(name = "DF_Warehouse_StockItemTransactions_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

