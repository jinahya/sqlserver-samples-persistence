package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomerTransaction;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedInvoice;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.CustomerTransactions} table.
 */
@Entity(name = "SalesCustomerTransaction")
@Table(schema = MappedCustomerTransaction.TABLE_SCHEMA, name = MappedCustomerTransaction.TABLE_NAME,
       comment = "All financial transactions that are customer-related")
public class CustomerTransaction extends MappedCustomerTransaction {

    /**
     * The name of the attribute of the {@code FK_Sales_CustomerTransactions_CustomerID_Sales_Customers} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the
     * {@code FK_Sales_CustomerTransactions_TransactionTypeID_Application_TransactionTypes} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    /**
     * The name of the attribute of the {@code FK_Sales_CustomerTransactions_InvoiceID_Sales_Invoices} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE = "invoice";

    /**
     * The name of the attribute of the {@code FK_Sales_CustomerTransactions_PaymentMethodID_Application_PaymentMethods}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_METHOD = "paymentMethod";

    /**
     * The name of the attribute of the {@code FK_Sales_CustomerTransactions_Application_People} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CustomerTransaction() {
        super();
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Sales_CustomerTransactions_CustomerID_Sales_Customers} foreign
     * key references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Sales_CustomerTransactions_CustomerID_Sales_Customers} foreign
     * key references, and the value of the column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // ----------------------------------------------------------------------------- transactionType

    /**
     * Returns the {@code TransactionType} which the
     * {@code FK_Sales_CustomerTransactions_TransactionTypeID_Application_TransactionTypes} foreign key references.
     *
     * @return the {@code TransactionType} referenced; {@code null} when none is.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces the {@code TransactionType} which the
     * {@code FK_Sales_CustomerTransactions_TransactionTypeID_Application_TransactionTypes} foreign key references, and
     * the value of the column it consists of.
     *
     * @param transactionType the {@code TransactionType} to reference; {@code null} for none.
     */
    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
        setTransactionTypeId(transactionType == null ? null : transactionType.getTransactionTypeId());
    }

    // ------------------------------------------------------------------------------------- invoice

    /**
     * Returns the {@code Invoice} which the {@code FK_Sales_CustomerTransactions_InvoiceID_Sales_Invoices} foreign key
     * references.
     *
     * @return the {@code Invoice} referenced; {@code null} when none is.
     */
    public Invoice getInvoice() {
        return invoice;
    }

    /**
     * Replaces the {@code Invoice} which the {@code FK_Sales_CustomerTransactions_InvoiceID_Sales_Invoices} foreign key
     * references, and the value of the column it consists of.
     *
     * @param invoice the {@code Invoice} to reference; {@code null} for none.
     */
    public void setInvoice(final Invoice invoice) {
        this.invoice = invoice;
        setInvoiceId(invoice == null ? null : invoice.getInvoiceId());
    }

    // ------------------------------------------------------------------------------- paymentMethod

    /**
     * Returns the {@code PaymentMethod} which the
     * {@code FK_Sales_CustomerTransactions_PaymentMethodID_Application_PaymentMethods} foreign key references.
     *
     * @return the {@code PaymentMethod} referenced; {@code null} when none is.
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Replaces the {@code PaymentMethod} which the
     * {@code FK_Sales_CustomerTransactions_PaymentMethodID_Application_PaymentMethods} foreign key references, and the
     * value of the column it consists of.
     *
     * @param paymentMethod the {@code PaymentMethod} to reference; {@code null} for none.
     */
    public void setPaymentMethod(final PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        setPaymentMethodId(paymentMethod == null ? null : paymentMethod.getPaymentMethodId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_CustomerTransactions_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_CustomerTransactions_Application_People} foreign key
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
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID,
                referencedColumnName = MappedCustomer.COLUMN_NAME_CUSTOMER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Customer customer;

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
    @JoinColumn(name = COLUMN_NAME_INVOICE_ID,
                referencedColumnName = MappedInvoice.COLUMN_NAME_INVOICE_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Invoice invoice;

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

