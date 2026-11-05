package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing;

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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.PaymentMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.TransactionType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPaymentMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedTransactionType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedPurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplierTransaction;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Purchasing.SupplierTransactions} table.
 */
@Entity(name = "PurchasingSupplierTransaction")
@Table(schema = MappedSupplierTransaction.TABLE_SCHEMA, name = MappedSupplierTransaction.TABLE_NAME,
       comment = "All financial transactions that are supplier-related")
public class SupplierTransaction extends MappedSupplierTransaction {

    /**
     * The name of the attribute of the {@code FK_Purchasing_SupplierTransactions_SupplierID_Purchasing_Suppliers}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER = "supplier";

    /**
     * The name of the attribute of the
     * {@code FK_Purchasing_SupplierTransactions_TransactionTypeID_Application_TransactionTypes} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    /**
     * The name of the attribute of the
     * {@code FK_Purchasing_SupplierTransactions_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER = "purchaseOrder";

    /**
     * The name of the attribute of the
     * {@code FK_Purchasing_SupplierTransactions_PaymentMethodID_Application_PaymentMethods} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_METHOD = "paymentMethod";

    /**
     * The name of the attribute of the {@code FK_Purchasing_SupplierTransactions_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SupplierTransaction() {
        super();
    }

    // ------------------------------------------------------------------------------------ supplier

    /**
     * Returns the {@code Supplier} which the {@code FK_Purchasing_SupplierTransactions_SupplierID_Purchasing_Suppliers}
     * foreign key references.
     *
     * @return the {@code Supplier} referenced; {@code null} when none is.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Replaces the {@code Supplier} which the
     * {@code FK_Purchasing_SupplierTransactions_SupplierID_Purchasing_Suppliers} foreign key references, and the value
     * of the column it consists of.
     *
     * @param supplier the {@code Supplier} to reference; {@code null} for none.
     */
    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
        setSupplierId(supplier == null ? null : supplier.getSupplierId());
    }

    // ----------------------------------------------------------------------------- transactionType

    /**
     * Returns the {@code TransactionType} which the
     * {@code FK_Purchasing_SupplierTransactions_TransactionTypeID_Application_TransactionTypes} foreign key
     * references.
     *
     * @return the {@code TransactionType} referenced; {@code null} when none is.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces the {@code TransactionType} which the
     * {@code FK_Purchasing_SupplierTransactions_TransactionTypeID_Application_TransactionTypes} foreign key references,
     * and the value of the column it consists of.
     *
     * @param transactionType the {@code TransactionType} to reference; {@code null} for none.
     */
    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
        setTransactionTypeId(transactionType == null ? null : transactionType.getTransactionTypeId());
    }

    // ------------------------------------------------------------------------------- purchaseOrder

    /**
     * Returns the {@code PurchaseOrder} which the
     * {@code FK_Purchasing_SupplierTransactions_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key references.
     *
     * @return the {@code PurchaseOrder} referenced; {@code null} when none is.
     */
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Replaces the {@code PurchaseOrder} which the
     * {@code FK_Purchasing_SupplierTransactions_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key references, and
     * the value of the column it consists of.
     *
     * @param purchaseOrder the {@code PurchaseOrder} to reference; {@code null} for none.
     */
    public void setPurchaseOrder(final PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        setPurchaseOrderId(purchaseOrder == null ? null : purchaseOrder.getPurchaseOrderId());
    }

    // ------------------------------------------------------------------------------- paymentMethod

    /**
     * Returns the {@code PaymentMethod} which the
     * {@code FK_Purchasing_SupplierTransactions_PaymentMethodID_Application_PaymentMethods} foreign key references.
     *
     * @return the {@code PaymentMethod} referenced; {@code null} when none is.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Replaces the {@code PaymentMethod} which the
     * {@code FK_Purchasing_SupplierTransactions_PaymentMethodID_Application_PaymentMethods} foreign key references, and
     * the value of the column it consists of.
     *
     * @param paymentMethod the {@code PaymentMethod} to reference; {@code null} for none.
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        setPaymentMethodId(paymentMethod == null ? null : paymentMethod.getPaymentMethodId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Purchasing_SupplierTransactions_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_SupplierTransactions_Application_People} foreign key
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
    @JoinColumn(name = COLUMN_NAME_SUPPLIER_ID,
                referencedColumnName = MappedSupplier.COLUMN_NAME_SUPPLIER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Supplier supplier;

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
    @JoinColumn(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                referencedColumnName = MappedPurchaseOrder.COLUMN_NAME_PURCHASE_ORDER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private PurchaseOrder purchaseOrder;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PAYMENT_METHOD_ID,
                referencedColumnName = MappedPaymentMethod.COLUMN_NAME_PAYMENT_METHOD_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private PaymentMethod paymentMethod;

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

