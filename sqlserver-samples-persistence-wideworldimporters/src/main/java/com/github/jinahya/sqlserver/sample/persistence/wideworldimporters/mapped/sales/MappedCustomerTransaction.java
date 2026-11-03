package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.CustomerTransactions} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_TRANSACTION_TYPE_ID}, {@value #COLUMN_NAME_INVOICE_ID},
 * {@value #COLUMN_NAME_PAYMENT_METHOD_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.CustomerTransactions}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CustomerTransactionID}</th>
 *     <td>Numeric ID used to refer to a customer transaction within the database</td></tr>
 * <tr><th scope="row">{@code CustomerID}</th><td>Customer for this transaction</td></tr>
 * <tr><th scope="row">{@code TransactionTypeID}</th><td>Type of transaction</td></tr>
 * <tr><th scope="row">{@code InvoiceID}</th>
 *     <td>ID of an invoice (for transactions associated with an invoice)</td></tr>
 * <tr><th scope="row">{@code PaymentMethodID}</th>
 *     <td>ID of a payment method (for transactions involving payments)</td></tr>
 * <tr><th scope="row">{@code TransactionDate}</th><td>Date for the transaction</td></tr>
 * <tr><th scope="row">{@code AmountExcludingTax}</th>
 *     <td>Transaction amount (excluding tax)</td></tr>
 * <tr><th scope="row">{@code TaxAmount}</th><td>Tax amount calculated</td></tr>
 * <tr><th scope="row">{@code TransactionAmount}</th>
 *     <td>Transaction amount (including tax)</td></tr>
 * <tr><th scope="row">{@code OutstandingBalance}</th>
 *     <td>Amount still outstanding for this transaction</td></tr>
 * <tr><th scope="row">{@code FinalizationDate}</th>
 *     <td>Date that this transaction was finalized (if it has been)</td></tr>
 * <tr><th scope="row">{@code IsFinalized}</th>
 *     <td>Is this transaction finalized (invoices, credits and payments have been
 *     matched)</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.CustomerTransactions}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CustomerTransactionID}</th>
 *     <td>{@code (NEXT VALUE FOR [Sequences].[TransactionID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code Sales.CustomerTransactions}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsFinalized}</th>
 *     <td>{@code (case when [FinalizationDate] IS NULL then CONVERT([bit],(0)) else CONVERT([bit],(1)) end)}</td>
 *     <td>{@code true}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/cast-and-convert-transact-sql">
 *      CAST and CONVERT</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedCustomerTransaction.TABLE_SCHEMA,
        table = MappedCustomerTransaction.TABLE_NAME,
        value = "All financial transactions that are customer-related"
)
public abstract class MappedCustomerTransaction {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CustomerTransactions";

    // ----------------------------------------------------------------------- CustomerTransactionID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.TransactionID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_TRANSACTION_ID = "CustomerTransactionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TRANSACTION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TRANSACTION_ID = "customerTransactionId";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------- TransactionTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_TYPE_ID = "TransactionTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE_ID = "transactionTypeId";

    // ----------------------------------------------------------------------------------- InvoiceID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVOICE_ID = "InvoiceID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVOICE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE_ID = "invoiceId";

    // ----------------------------------------------------------------------------- PaymentMethodID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_METHOD_ID = "PaymentMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_METHOD_ID = "paymentMethodId";

    // ----------------------------------------------------------------------------- TransactionDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_DATE = "TransactionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_DATE = "transactionDate";

    // -------------------------------------------------------------------------- AmountExcludingTax

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AMOUNT_EXCLUDING_TAX = "AmountExcludingTax";

    /**
     * The precision of the {@value #COLUMN_NAME_AMOUNT_EXCLUDING_TAX} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AMOUNT_EXCLUDING_TAX = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_AMOUNT_EXCLUDING_TAX} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AMOUNT_EXCLUDING_TAX = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AMOUNT_EXCLUDING_TAX} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AMOUNT_EXCLUDING_TAX = "amountExcludingTax";

    // ----------------------------------------------------------------------------------- TaxAmount

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMOUNT = "TaxAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TAX_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TAX_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMOUNT = "taxAmount";

    // --------------------------------------------------------------------------- TransactionAmount

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_AMOUNT = "TransactionAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TRANSACTION_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TRANSACTION_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TRANSACTION_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TRANSACTION_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_AMOUNT = "transactionAmount";

    // -------------------------------------------------------------------------- OutstandingBalance

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OUTSTANDING_BALANCE = "OutstandingBalance";

    /**
     * The precision of the {@value #COLUMN_NAME_OUTSTANDING_BALANCE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_OUTSTANDING_BALANCE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_OUTSTANDING_BALANCE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_OUTSTANDING_BALANCE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OUTSTANDING_BALANCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OUTSTANDING_BALANCE = "outstandingBalance";

    // ---------------------------------------------------------------------------- FinalizationDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FINALIZATION_DATE = "FinalizationDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FINALIZATION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FINALIZATION_DATE = "finalizationDate";

    // --------------------------------------------------------------------------------- IsFinalized

    /**
     * The name of the
     * {@code bit, computed as (case when [FinalizationDate] IS NULL then CONVERT([bit],(0))
     * else CONVERT([bit],(1)) end)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_FINALIZED = "IsFinalized";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_FINALIZED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_FINALIZED = "isFinalized";

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
    protected MappedCustomerTransaction() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerTransactionId=" + customerTransactionId
               + ",customerId=" + customerId
               + ",transactionTypeId=" + transactionTypeId
               + ",invoiceId=" + invoiceId
               + ",paymentMethodId=" + paymentMethodId
               + ",transactionDate=" + transactionDate
               + ",amountExcludingTax=" + amountExcludingTax
               + ",taxAmount=" + taxAmount
               + ",transactionAmount=" + transactionAmount
               + ",outstandingBalance=" + outstandingBalance
               + ",finalizationDate=" + finalizationDate
               + ",isFinalized=" + isFinalized
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
        if (!(obj instanceof MappedCustomerTransaction that)) {
            return false;
        }
        return Objects.equals(customerTransactionId, that.customerTransactionId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(customerTransactionId);
    }

    // ----------------------------------------------------------------------- customerTransactionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TRANSACTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TRANSACTION_ID} attribute.
     */
    public Integer getCustomerTransactionId() {
        return customerTransactionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TRANSACTION_ID} attribute with the specified value.
     *
     * @param customerTransactionId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TRANSACTION_ID} attribute.
     */
    protected void setCustomerTransactionId(final Integer customerTransactionId) {
        this.customerTransactionId = customerTransactionId;
    }

    // ---------------------------------------------------------------------------------- customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public Integer getCustomerId() {
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

    // --------------------------------------------------------------------------- transactionTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    public Integer getTransactionTypeId() {
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

    // ----------------------------------------------------------------------------------- invoiceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    public Integer getInvoiceId() {
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

    // ----------------------------------------------------------------------------- paymentMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute.
     */
    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param paymentMethodId new value for {@value #ATTRIBUTE_NAME_PAYMENT_METHOD_ID} attribute.
     */
    protected void setPaymentMethodId(final Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    // ----------------------------------------------------------------------------- transactionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute with the specified value.
     *
     * @param transactionDate new value for {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public void setTransactionDate(final LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    // -------------------------------------------------------------------------- amountExcludingTax

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AMOUNT_EXCLUDING_TAX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AMOUNT_EXCLUDING_TAX} attribute.
     */
    public BigDecimal getAmountExcludingTax() {
        return amountExcludingTax;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AMOUNT_EXCLUDING_TAX} attribute with the specified value.
     *
     * @param amountExcludingTax new value for {@value #ATTRIBUTE_NAME_AMOUNT_EXCLUDING_TAX} attribute.
     */
    public void setAmountExcludingTax(final BigDecimal amountExcludingTax) {
        this.amountExcludingTax = amountExcludingTax;
    }

    // ----------------------------------------------------------------------------------- taxAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute with the specified value.
     *
     * @param taxAmount new value for {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public void setTaxAmount(final BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    // --------------------------------------------------------------------------- transactionAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_AMOUNT} attribute.
     */
    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_AMOUNT} attribute with the specified value.
     *
     * @param transactionAmount new value for {@value #ATTRIBUTE_NAME_TRANSACTION_AMOUNT} attribute.
     */
    public void setTransactionAmount(final BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    // -------------------------------------------------------------------------- outstandingBalance

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OUTSTANDING_BALANCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OUTSTANDING_BALANCE} attribute.
     */
    public BigDecimal getOutstandingBalance() {
        return outstandingBalance;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OUTSTANDING_BALANCE} attribute with the specified value.
     *
     * @param outstandingBalance new value for {@value #ATTRIBUTE_NAME_OUTSTANDING_BALANCE} attribute.
     */
    public void setOutstandingBalance(final BigDecimal outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    // ---------------------------------------------------------------------------- finalizationDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FINALIZATION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FINALIZATION_DATE} attribute.
     */
    public LocalDate getFinalizationDate() {
        return finalizationDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FINALIZATION_DATE} attribute with the specified value.
     *
     * @param finalizationDate new value for {@value #ATTRIBUTE_NAME_FINALIZATION_DATE} attribute.
     */
    public void setFinalizationDate(final LocalDate finalizationDate) {
        this.finalizationDate = finalizationDate;
    }

    // --------------------------------------------------------------------------------- isFinalized

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_FINALIZED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_FINALIZED} attribute.
     */
    public Boolean getIsFinalized() {
        return isFinalized;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_FINALIZED} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param isFinalized new value for {@value #ATTRIBUTE_NAME_IS_FINALIZED} attribute.
     */
    protected void setIsFinalized(final Boolean isFinalized) {
        this.isFinalized = isFinalized;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    public Integer getLastEditedBy() {
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
    @SequenceGenerator(name = "WideWorldImporters.Sequences.TransactionID", schema = "Sequences",
                       sequenceName = "TransactionID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.TransactionID")
    @Column(name = COLUMN_NAME_CUSTOMER_TRANSACTION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_TRANSACTION_ID,
                          value = "Numeric ID used to refer to a customer transaction within "
                                  + "the database")
    @MsDefault(name = "DF_Sales_CustomerTransactions_CustomerTransactionID",
               value = "(NEXT VALUE FOR [Sequences].[TransactionID])")
    private Integer customerTransactionId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_ID, value = "Customer for this transaction")
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_TYPE_ID, value = "Type of transaction")
    private Integer transactionTypeId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INVOICE_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_INVOICE_ID,
                          value = "ID of an invoice (for transactions associated with an invoice)")
    private Integer invoiceId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PAYMENT_METHOD_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_PAYMENT_METHOD_ID,
                          value = "ID of a payment method (for transactions involving payments)")
    private Integer paymentMethodId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_DATE, value = "Date for the transaction")
    private LocalDate transactionDate;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_AMOUNT_EXCLUDING_TAX - COLUMN_SCALE_AMOUNT_EXCLUDING_TAX,
            fraction = COLUMN_SCALE_AMOUNT_EXCLUDING_TAX)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT_EXCLUDING_TAX,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AMOUNT_EXCLUDING_TAX,
            scale = COLUMN_SCALE_AMOUNT_EXCLUDING_TAX
    )
    @MsDescription.Column(name = COLUMN_NAME_AMOUNT_EXCLUDING_TAX,
                          value = "Transaction amount (excluding tax)")
    private BigDecimal amountExcludingTax;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TAX_AMOUNT - COLUMN_SCALE_TAX_AMOUNT, fraction = COLUMN_SCALE_TAX_AMOUNT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_AMOUNT,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TAX_AMOUNT,
            scale = COLUMN_SCALE_TAX_AMOUNT
    )
    @MsDescription.Column(name = COLUMN_NAME_TAX_AMOUNT, value = "Tax amount calculated")
    private BigDecimal taxAmount;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TRANSACTION_AMOUNT - COLUMN_SCALE_TRANSACTION_AMOUNT,
            fraction = COLUMN_SCALE_TRANSACTION_AMOUNT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_AMOUNT,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TRANSACTION_AMOUNT,
            scale = COLUMN_SCALE_TRANSACTION_AMOUNT
    )
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_AMOUNT,
                          value = "Transaction amount (including tax)")
    private BigDecimal transactionAmount;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_OUTSTANDING_BALANCE - COLUMN_SCALE_OUTSTANDING_BALANCE,
            fraction = COLUMN_SCALE_OUTSTANDING_BALANCE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_OUTSTANDING_BALANCE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_OUTSTANDING_BALANCE,
            scale = COLUMN_SCALE_OUTSTANDING_BALANCE
    )
    @MsDescription.Column(name = COLUMN_NAME_OUTSTANDING_BALANCE,
                          value = "Amount still outstanding for this transaction")
    private BigDecimal outstandingBalance;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FINALIZATION_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_FINALIZATION_DATE,
                          value = "Date that this transaction was finalized (if it has been)")
    private LocalDate finalizationDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_IS_FINALIZED,
            nullable = true,
            insertable = false,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_IS_FINALIZED,
                          value = "Is this transaction finalized (invoices, credits and "
                                  + "payments have been matched)")
    @MsComputedColumn(value = "(case when [FinalizationDate] IS NULL then CONVERT([bit],(0)) else CONVERT([bit],(1)) "
                              + "end)",
                      persisted = true)
    private Boolean isFinalized;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDefault(name = "DF_Sales_CustomerTransactions_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

