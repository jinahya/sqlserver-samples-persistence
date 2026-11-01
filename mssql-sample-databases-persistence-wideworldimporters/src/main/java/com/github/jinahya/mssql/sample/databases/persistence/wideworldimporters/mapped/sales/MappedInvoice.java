package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.sales;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.Invoices} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID}, {@value #COLUMN_NAME_ORDER_ID},
 * {@value #COLUMN_NAME_DELIVERY_METHOD_ID}, {@value #COLUMN_NAME_CONTACT_PERSON_ID},
 * {@value #COLUMN_NAME_ACCOUNTS_PERSON_ID}, {@value #COLUMN_NAME_SALESPERSON_PERSON_ID},
 * {@value #COLUMN_NAME_PACKED_BY_PERSON_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedInvoice {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Invoices";

    // ------------------------------------------------------------------------------------------------------- InvoiceID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.InvoiceID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVOICE_ID = "InvoiceID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVOICE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE_ID = "invoiceId";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ------------------------------------------------------------------------------------------------ BillToCustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BILL_TO_CUSTOMER_ID = "BillToCustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID = "billToCustomerId";

    // --------------------------------------------------------------------------------------------------------- OrderID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "OrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ------------------------------------------------------------------------------------------------ DeliveryMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_METHOD_ID = "DeliveryMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD_ID = "deliveryMethodId";

    // ------------------------------------------------------------------------------------------------- ContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTACT_PERSON_ID = "ContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTACT_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON_ID = "contactPersonId";

    // ------------------------------------------------------------------------------------------------ AccountsPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCOUNTS_PERSON_ID = "AccountsPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCOUNTS_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNTS_PERSON_ID = "accountsPersonId";

    // --------------------------------------------------------------------------------------------- SalespersonPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALESPERSON_PERSON_ID = "SalespersonPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALESPERSON_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALESPERSON_PERSON_ID = "salespersonPersonId";

    // ------------------------------------------------------------------------------------------------ PackedByPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKED_BY_PERSON_ID = "PackedByPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKED_BY_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKED_BY_PERSON_ID = "packedByPersonId";

    // ----------------------------------------------------------------------------------------------------- InvoiceDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVOICE_DATE = "InvoiceDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVOICE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVOICE_DATE = "invoiceDate";

    // ------------------------------------------------------------------------------------- CustomerPurchaseOrderNumber

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER = "CustomerPurchaseOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER = "customerPurchaseOrderNumber";

    // ---------------------------------------------------------------------------------------------------- IsCreditNote

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_CREDIT_NOTE = "IsCreditNote";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_CREDIT_NOTE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_CREDIT_NOTE = "isCreditNote";

    // ------------------------------------------------------------------------------------------------ CreditNoteReason

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREDIT_NOTE_REASON = "CreditNoteReason";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_NOTE_REASON} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_NOTE_REASON = "creditNoteReason";

    // -------------------------------------------------------------------------------------------------------- Comments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMENTS = "Comments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENTS = "comments";

    // -------------------------------------------------------------------------------------------- DeliveryInstructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_INSTRUCTIONS = "DeliveryInstructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_INSTRUCTIONS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS = "deliveryInstructions";

    // ------------------------------------------------------------------------------------------------ InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

    // --------------------------------------------------------------------------------------------------- TotalDryItems

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_DRY_ITEMS = "TotalDryItems";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_DRY_ITEMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_DRY_ITEMS = "totalDryItems";

    // ----------------------------------------------------------------------------------------------- TotalChillerItems

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_CHILLER_ITEMS = "TotalChillerItems";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_CHILLER_ITEMS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_CHILLER_ITEMS = "totalChillerItems";

    // ----------------------------------------------------------------------------------------------------- DeliveryRun

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_RUN = "DeliveryRun";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_RUN} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_RUN = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_RUN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_RUN = "deliveryRun";

    // ----------------------------------------------------------------------------------------------------- RunPosition

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RUN_POSITION = "RunPosition";

    /**
     * The length of the {@value #COLUMN_NAME_RUN_POSITION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_RUN_POSITION = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RUN_POSITION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RUN_POSITION = "runPosition";

    // -------------------------------------------------------------------------------------------- ReturnedDeliveryData

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURNED_DELIVERY_DATA = "ReturnedDeliveryData";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURNED_DELIVERY_DATA} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURNED_DELIVERY_DATA = "returnedDeliveryData";

    // ------------------------------------------------------------------------------------------- ConfirmedDeliveryTime

    /**
     * The name of the
     * {@code datetime2, computed as
     * (TRY_CONVERT([datetime2](7),json_value([ReturnedDeliveryData],N'$.DeliveredWhen'),(126)))} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_CONFIRMED_DELIVERY_TIME = "ConfirmedDeliveryTime";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONFIRMED_DELIVERY_TIME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONFIRMED_DELIVERY_TIME = "confirmedDeliveryTime";

    // --------------------------------------------------------------------------------------------- ConfirmedReceivedBy

    /**
     * The name of the {@code nvarchar(4000), computed as (json_value([ReturnedDeliveryData],N'$.ReceivedBy'))} column.
     * The value is {@value}.
     */
    public static final String COLUMN_NAME_CONFIRMED_RECEIVED_BY = "ConfirmedReceivedBy";

    /**
     * The length of the {@value #COLUMN_NAME_CONFIRMED_RECEIVED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONFIRMED_RECEIVED_BY = 4000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONFIRMED_RECEIVED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONFIRMED_RECEIVED_BY = "confirmedReceivedBy";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // -------------------------------------------------------------------------------------------------- LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInvoice() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "invoiceId=" + invoiceId
               + ",customerId=" + customerId
               + ",billToCustomerId=" + billToCustomerId
               + ",orderId=" + orderId
               + ",deliveryMethodId=" + deliveryMethodId
               + ",contactPersonId=" + contactPersonId
               + ",accountsPersonId=" + accountsPersonId
               + ",salespersonPersonId=" + salespersonPersonId
               + ",packedByPersonId=" + packedByPersonId
               + ",invoiceDate=" + invoiceDate
               + ",customerPurchaseOrderNumber=" + customerPurchaseOrderNumber
               + ",isCreditNote=" + isCreditNote
               + ",creditNoteReason=" + creditNoteReason
               + ",comments=" + comments
               + ",deliveryInstructions=" + deliveryInstructions
               + ",internalComments=" + internalComments
               + ",totalDryItems=" + totalDryItems
               + ",totalChillerItems=" + totalChillerItems
               + ",deliveryRun=" + deliveryRun
               + ",runPosition=" + runPosition
               + ",returnedDeliveryData=" + returnedDeliveryData
               + ",confirmedDeliveryTime=" + confirmedDeliveryTime
               + ",confirmedReceivedBy=" + confirmedReceivedBy
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
        if (!(obj instanceof MappedInvoice that)) {
            return false;
        }
        return Objects.equals(invoiceId, that.invoiceId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(invoiceId);
    }

    // ------------------------------------------------------------------------------------------------------- invoiceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    public Integer getInvoiceId() {
        return invoiceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute with the specified value.
     *
     * @param invoiceId new value for {@value #ATTRIBUTE_NAME_INVOICE_ID} attribute.
     */
    protected void setInvoiceId(final Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

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

    // ------------------------------------------------------------------------------------------------ billToCustomerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     */
    public Integer getBillToCustomerId() {
        return billToCustomerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param billToCustomerId new value for {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     */
    protected void setBillToCustomerId(final Integer billToCustomerId) {
        this.billToCustomerId = billToCustomerId;
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    // ------------------------------------------------------------------------------------------------ deliveryMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    public Integer getDeliveryMethodId() {
        return deliveryMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param deliveryMethodId new value for {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    protected void setDeliveryMethodId(final Integer deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    // ------------------------------------------------------------------------------------------------- contactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     */
    public Integer getContactPersonId() {
        return contactPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param contactPersonId new value for {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     */
    protected void setContactPersonId(final Integer contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    // ------------------------------------------------------------------------------------------------ accountsPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCOUNTS_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCOUNTS_PERSON_ID} attribute.
     */
    public Integer getAccountsPersonId() {
        return accountsPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCOUNTS_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param accountsPersonId new value for {@value #ATTRIBUTE_NAME_ACCOUNTS_PERSON_ID} attribute.
     */
    protected void setAccountsPersonId(final Integer accountsPersonId) {
        this.accountsPersonId = accountsPersonId;
    }

    // --------------------------------------------------------------------------------------------- salespersonPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     */
    public Integer getSalespersonPersonId() {
        return salespersonPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salespersonPersonId new value for {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     */
    protected void setSalespersonPersonId(final Integer salespersonPersonId) {
        this.salespersonPersonId = salespersonPersonId;
    }

    // ------------------------------------------------------------------------------------------------ packedByPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKED_BY_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKED_BY_PERSON_ID} attribute.
     */
    public Integer getPackedByPersonId() {
        return packedByPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKED_BY_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param packedByPersonId new value for {@value #ATTRIBUTE_NAME_PACKED_BY_PERSON_ID} attribute.
     */
    protected void setPackedByPersonId(final Integer packedByPersonId) {
        this.packedByPersonId = packedByPersonId;
    }

    // ----------------------------------------------------------------------------------------------------- invoiceDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVOICE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVOICE_DATE} attribute.
     */
    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVOICE_DATE} attribute with the specified value.
     *
     * @param invoiceDate new value for {@value #ATTRIBUTE_NAME_INVOICE_DATE} attribute.
     */
    public void setInvoiceDate(final LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    // ------------------------------------------------------------------------------------- customerPurchaseOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     */
    public String getCustomerPurchaseOrderNumber() {
        return customerPurchaseOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute with the specified
     * value.
     *
     * @param customerPurchaseOrderNumber new value for {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER}
     *                                    attribute.
     */
    public void setCustomerPurchaseOrderNumber(final String customerPurchaseOrderNumber) {
        this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
    }

    // ---------------------------------------------------------------------------------------------------- isCreditNote

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_CREDIT_NOTE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_CREDIT_NOTE} attribute.
     */
    public Boolean getIsCreditNote() {
        return isCreditNote;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_CREDIT_NOTE} attribute with the specified value.
     *
     * @param isCreditNote new value for {@value #ATTRIBUTE_NAME_IS_CREDIT_NOTE} attribute.
     */
    public void setIsCreditNote(final Boolean isCreditNote) {
        this.isCreditNote = isCreditNote;
    }

    // ------------------------------------------------------------------------------------------------ creditNoteReason

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_NOTE_REASON} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_NOTE_REASON} attribute.
     */
    public String getCreditNoteReason() {
        return creditNoteReason;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_NOTE_REASON} attribute with the specified value.
     *
     * @param creditNoteReason new value for {@value #ATTRIBUTE_NAME_CREDIT_NOTE_REASON} attribute.
     */
    public void setCreditNoteReason(final String creditNoteReason) {
        this.creditNoteReason = creditNoteReason;
    }

    // -------------------------------------------------------------------------------------------------------- comments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute with the specified value.
     *
     * @param comments new value for {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public void setComments(final String comments) {
        this.comments = comments;
    }

    // -------------------------------------------------------------------------------------------- deliveryInstructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     */
    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute with the specified value.
     *
     * @param deliveryInstructions new value for {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     */
    public void setDeliveryInstructions(final String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    // ------------------------------------------------------------------------------------------------ internalComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    public String getInternalComments() {
        return internalComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute with the specified value.
     *
     * @param internalComments new value for {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    public void setInternalComments(final String internalComments) {
        this.internalComments = internalComments;
    }

    // --------------------------------------------------------------------------------------------------- totalDryItems

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_DRY_ITEMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_DRY_ITEMS} attribute.
     */
    public Integer getTotalDryItems() {
        return totalDryItems;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_DRY_ITEMS} attribute with the specified value.
     *
     * @param totalDryItems new value for {@value #ATTRIBUTE_NAME_TOTAL_DRY_ITEMS} attribute.
     */
    public void setTotalDryItems(final Integer totalDryItems) {
        this.totalDryItems = totalDryItems;
    }

    // ----------------------------------------------------------------------------------------------- totalChillerItems

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_CHILLER_ITEMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_CHILLER_ITEMS} attribute.
     */
    public Integer getTotalChillerItems() {
        return totalChillerItems;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_CHILLER_ITEMS} attribute with the specified value.
     *
     * @param totalChillerItems new value for {@value #ATTRIBUTE_NAME_TOTAL_CHILLER_ITEMS} attribute.
     */
    public void setTotalChillerItems(final Integer totalChillerItems) {
        this.totalChillerItems = totalChillerItems;
    }

    // ----------------------------------------------------------------------------------------------------- deliveryRun

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute.
     */
    public String getDeliveryRun() {
        return deliveryRun;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute with the specified value.
     *
     * @param deliveryRun new value for {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute.
     */
    public void setDeliveryRun(final String deliveryRun) {
        this.deliveryRun = deliveryRun;
    }

    // ----------------------------------------------------------------------------------------------------- runPosition

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute.
     */
    public String getRunPosition() {
        return runPosition;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute with the specified value.
     *
     * @param runPosition new value for {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute.
     */
    public void setRunPosition(final String runPosition) {
        this.runPosition = runPosition;
    }

    // -------------------------------------------------------------------------------------------- returnedDeliveryData

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURNED_DELIVERY_DATA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURNED_DELIVERY_DATA} attribute.
     */
    public String getReturnedDeliveryData() {
        return returnedDeliveryData;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURNED_DELIVERY_DATA} attribute with the specified value.
     *
     * @param returnedDeliveryData new value for {@value #ATTRIBUTE_NAME_RETURNED_DELIVERY_DATA} attribute.
     */
    public void setReturnedDeliveryData(final String returnedDeliveryData) {
        this.returnedDeliveryData = returnedDeliveryData;
    }

    // ------------------------------------------------------------------------------------------- confirmedDeliveryTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONFIRMED_DELIVERY_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONFIRMED_DELIVERY_TIME} attribute.
     */
    public LocalDateTime getConfirmedDeliveryTime() {
        return confirmedDeliveryTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONFIRMED_DELIVERY_TIME} attribute with the specified value.
     * The database writes the column; this is for the provider and the extending class only.
     *
     * @param confirmedDeliveryTime new value for {@value #ATTRIBUTE_NAME_CONFIRMED_DELIVERY_TIME} attribute.
     */
    protected void setConfirmedDeliveryTime(final LocalDateTime confirmedDeliveryTime) {
        this.confirmedDeliveryTime = confirmedDeliveryTime;
    }

    // --------------------------------------------------------------------------------------------- confirmedReceivedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONFIRMED_RECEIVED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONFIRMED_RECEIVED_BY} attribute.
     */
    public String getConfirmedReceivedBy() {
        return confirmedReceivedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONFIRMED_RECEIVED_BY} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param confirmedReceivedBy new value for {@value #ATTRIBUTE_NAME_CONFIRMED_RECEIVED_BY} attribute.
     */
    protected void setConfirmedReceivedBy(final String confirmedReceivedBy) {
        this.confirmedReceivedBy = confirmedReceivedBy;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

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

    // -------------------------------------------------------------------------------------------------- lastEditedWhen

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

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.InvoiceID", schema = "Sequences",
                       sequenceName = "InvoiceID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.InvoiceID")
    @Column(name = COLUMN_NAME_INVOICE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer invoiceId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BILL_TO_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer billToCustomerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer orderId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer deliveryMethodId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CONTACT_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer contactPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACCOUNTS_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer accountsPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALESPERSON_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer salespersonPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PACKED_BY_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer packedByPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_INVOICE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate invoiceDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER
    )
    private String customerPurchaseOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_CREDIT_NOTE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isCreditNote;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREDIT_NOTE_REASON,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String creditNoteReason;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String comments;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_INSTRUCTIONS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String deliveryInstructions;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String internalComments;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TOTAL_DRY_ITEMS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer totalDryItems;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TOTAL_CHILLER_ITEMS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer totalChillerItems;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_RUN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_RUN,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_RUN
    )
    private String deliveryRun;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_RUN_POSITION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RUN_POSITION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_RUN_POSITION
    )
    private String runPosition;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RETURNED_DELIVERY_DATA,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String returnedDeliveryData;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CONFIRMED_DELIVERY_TIME,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDateTime confirmedDeliveryTime;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CONFIRMED_RECEIVED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CONFIRMED_RECEIVED_BY,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CONFIRMED_RECEIVED_BY
    )
    private String confirmedReceivedBy;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime lastEditedWhen;
}

