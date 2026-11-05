package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse;

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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.TransactionType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedTransactionType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedPurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedInvoice;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItemTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.PurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Customer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales.Invoice;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Warehouse.StockItemTransactions} table.
 */
@Entity(name = "WarehouseStockItemTransaction")
@Table(schema = MappedStockItemTransaction.TABLE_SCHEMA, name = MappedStockItemTransaction.TABLE_NAME,
       comment = "Transactions covering all movements of all stock items")
public class StockItemTransaction extends MappedStockItemTransaction {

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemTransactions_StockItemID_Warehouse_StockItems}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM = "stockItem";

    /**
     * The name of the attribute of the
     * {@code FK_Warehouse_StockItemTransactions_TransactionTypeID_Application_TransactionTypes} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemTransactions_CustomerID_Sales_Customers} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemTransactions_InvoiceID_Sales_Invoices} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE = "invoice";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemTransactions_SupplierID_Purchasing_Suppliers}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER = "supplier";

    /**
     * The name of the attribute of the
     * {@code FK_Warehouse_StockItemTransactions_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER = "purchaseOrder";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemTransactions_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StockItemTransaction() {
        super();
    }

    // ----------------------------------------------------------------------------------- stockItem

    /**
     * Returns the {@code StockItem} which the
     * {@code FK_Warehouse_StockItemTransactions_StockItemID_Warehouse_StockItems} foreign key references.
     *
     * @return the {@code StockItem} referenced; {@code null} when none is.
     */
    public StockItem getStockItem() {
        return stockItem;
    }

    /**
     * Replaces the {@code StockItem} which the
     * {@code FK_Warehouse_StockItemTransactions_StockItemID_Warehouse_StockItems} foreign key references, and the value
     * of the column it consists of.
     *
     * @param stockItem the {@code StockItem} to reference; {@code null} for none.
     */
    public void setStockItem(final StockItem stockItem) {
        this.stockItem = stockItem;
        setStockItemId(stockItem == null ? null : stockItem.getStockItemId());
    }

    // ----------------------------------------------------------------------------- transactionType

    /**
     * Returns the {@code TransactionType} which the
     * {@code FK_Warehouse_StockItemTransactions_TransactionTypeID_Application_TransactionTypes} foreign key
     * references.
     *
     * @return the {@code TransactionType} referenced; {@code null} when none is.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces the {@code TransactionType} which the
     * {@code FK_Warehouse_StockItemTransactions_TransactionTypeID_Application_TransactionTypes} foreign key references,
     * and the value of the column it consists of.
     *
     * @param transactionType the {@code TransactionType} to reference; {@code null} for none.
     */
    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
        setTransactionTypeId(transactionType == null ? null : transactionType.getTransactionTypeId());
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Warehouse_StockItemTransactions_CustomerID_Sales_Customers}
     * foreign key references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Warehouse_StockItemTransactions_CustomerID_Sales_Customers}
     * foreign key references, and the value of the column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // ------------------------------------------------------------------------------------- invoice

    /**
     * Returns the {@code Invoice} which the {@code FK_Warehouse_StockItemTransactions_InvoiceID_Sales_Invoices} foreign
     * key references.
     *
     * @return the {@code Invoice} referenced; {@code null} when none is.
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Replaces the {@code Invoice} which the {@code FK_Warehouse_StockItemTransactions_InvoiceID_Sales_Invoices}
     * foreign key references, and the value of the column it consists of.
     *
     * @param invoice the {@code Invoice} to reference; {@code null} for none.
     */
    public void setInvoice(final Invoice invoice) {
        this.invoice = invoice;
        setInvoiceId(invoice == null ? null : invoice.getInvoiceId());
    }

    // ------------------------------------------------------------------------------------ supplier

    /**
     * Returns the {@code Supplier} which the {@code FK_Warehouse_StockItemTransactions_SupplierID_Purchasing_Suppliers}
     * foreign key references.
     *
     * @return the {@code Supplier} referenced; {@code null} when none is.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Replaces the {@code Supplier} which the
     * {@code FK_Warehouse_StockItemTransactions_SupplierID_Purchasing_Suppliers} foreign key references, and the value
     * of the column it consists of.
     *
     * @param supplier the {@code Supplier} to reference; {@code null} for none.
     */
    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
        setSupplierId(supplier == null ? null : supplier.getSupplierId());
    }

    // ------------------------------------------------------------------------------- purchaseOrder

    /**
     * Returns the {@code PurchaseOrder} which the
     * {@code FK_Warehouse_StockItemTransactions_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key references.
     *
     * @return the {@code PurchaseOrder} referenced; {@code null} when none is.
     */
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Replaces the {@code PurchaseOrder} which the
     * {@code FK_Warehouse_StockItemTransactions_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key references, and
     * the value of the column it consists of.
     *
     * @param purchaseOrder the {@code PurchaseOrder} to reference; {@code null} for none.
     */
    public void setPurchaseOrder(final PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        setPurchaseOrderId(purchaseOrder == null ? null : purchaseOrder.getPurchaseOrderId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Warehouse_StockItemTransactions_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Warehouse_StockItemTransactions_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STOCK_ITEM_ID,
                referencedColumnName = MappedStockItem.COLUMN_NAME_STOCK_ITEM_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private StockItem stockItem;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TRANSACTION_TYPE_ID,
                referencedColumnName = MappedTransactionType.COLUMN_NAME_TRANSACTION_TYPE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private TransactionType transactionType;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID,
                referencedColumnName = MappedCustomer.COLUMN_NAME_CUSTOMER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Customer customer;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_INVOICE_ID,
                referencedColumnName = MappedInvoice.COLUMN_NAME_INVOICE_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Invoice invoice;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SUPPLIER_ID,
                referencedColumnName = MappedSupplier.COLUMN_NAME_SUPPLIER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Supplier supplier;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                referencedColumnName = MappedPurchaseOrder.COLUMN_NAME_PURCHASE_ORDER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private PurchaseOrder purchaseOrder;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LAST_EDITED_BY,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person lastEditedByPerson;
}

