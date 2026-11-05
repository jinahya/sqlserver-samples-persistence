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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.DeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedDeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedInvoice;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedOrder;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.Invoices} table.
 */
@Entity(name = "SalesInvoice")
@Table(schema = MappedInvoice.TABLE_SCHEMA,
       name = MappedInvoice.TABLE_NAME,
       comment = "Details of customer invoices",
       check = {
               @CheckConstraint(
                       name = "CK_Sales_Invoices_ReturnedDeliveryData_Must_Be_Valid_JSON",
                       constraint = "([ReturnedDeliveryData] IS NULL OR "
                               + "isjson([ReturnedDeliveryData])<>(0))"
               )
       }
)
public class Invoice extends MappedInvoice {

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_SalespersonPersonID_Application_People} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALESPERSON_PERSON = "salespersonPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_PackedByPersonID_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKED_BY_PERSON = "packedByPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_Application_People} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_CustomerID_Sales_Customers} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_BillToCustomerID_Sales_Customers} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_CUSTOMER = "billToCustomer";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_OrderID_Sales_Orders} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER = "order";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_DeliveryMethodID_Application_DeliveryMethods} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD = "deliveryMethod";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_ContactPersonID_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON = "contactPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Invoices_AccountsPersonID_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNTS_PERSON = "accountsPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Invoice() {
        super();
    }

    // --------------------------------------------------------------------------- salespersonPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Invoices_SalespersonPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getSalespersonPerson() {
        return salespersonPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Invoices_SalespersonPersonID_Application_People} foreign
     * key references, and the value of the column it consists of.
     *
     * @param salespersonPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setSalespersonPerson(final Person salespersonPerson) {
        this.salespersonPerson = salespersonPerson;
        setSalespersonPersonId(salespersonPerson == null ? null : salespersonPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------ packedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Invoices_PackedByPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPackedByPerson() {
        return packedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Invoices_PackedByPersonID_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param packedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setPackedByPerson(final Person packedByPerson) {
        this.packedByPerson = packedByPerson;
        setPackedByPersonId(packedByPerson == null ? null : packedByPerson.getPersonId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Invoices_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Invoices_Application_People} foreign key references, and
     * the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Sales_Invoices_CustomerID_Sales_Customers} foreign key
     * references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Sales_Invoices_CustomerID_Sales_Customers} foreign key
     * references, and the value of the column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // ------------------------------------------------------------------------------ billToCustomer

    /**
     * Returns the {@code Customer} which the {@code FK_Sales_Invoices_BillToCustomerID_Sales_Customers} foreign key
     * references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getBillToCustomer() {
        return billToCustomer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Sales_Invoices_BillToCustomerID_Sales_Customers} foreign key
     * references, and the value of the column it consists of.
     *
     * @param billToCustomer the {@code Customer} to reference; {@code null} for none.
     */
    public void setBillToCustomer(final Customer billToCustomer) {
        this.billToCustomer = billToCustomer;
        setBillToCustomerId(billToCustomer == null ? null : billToCustomer.getCustomerId());
    }

    // --------------------------------------------------------------------------------------- order

    /**
     * Returns the {@code Order} which the {@code FK_Sales_Invoices_OrderID_Sales_Orders} foreign key references.
     *
     * @return the {@code Order} referenced; {@code null} when none is.
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Replaces the {@code Order} which the {@code FK_Sales_Invoices_OrderID_Sales_Orders} foreign key references, and
     * the value of the column it consists of.
     *
     * @param order the {@code Order} to reference; {@code null} for none.
     */
    public void setOrder(final Order order) {
        this.order = order;
        setOrderId(order == null ? null : order.getOrderId());
    }

    // ------------------------------------------------------------------------------ deliveryMethod

    /**
     * Returns the {@code DeliveryMethod} which the
     * {@code FK_Sales_Invoices_DeliveryMethodID_Application_DeliveryMethods} foreign key references.
     *
     * @return the {@code DeliveryMethod} referenced; {@code null} when none is.
     */
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Replaces the {@code DeliveryMethod} which the
     * {@code FK_Sales_Invoices_DeliveryMethodID_Application_DeliveryMethods} foreign key references, and the value of
     * the column it consists of.
     *
     * @param deliveryMethod the {@code DeliveryMethod} to reference; {@code null} for none.
     */
    public void setDeliveryMethod(final DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        setDeliveryMethodId(deliveryMethod == null ? null : deliveryMethod.getDeliveryMethodId());
    }

    // ------------------------------------------------------------------------------- contactPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Invoices_ContactPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getContactPerson() {
        return contactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Invoices_ContactPersonID_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param contactPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setContactPerson(final Person contactPerson) {
        this.contactPerson = contactPerson;
        setContactPersonId(contactPerson == null ? null : contactPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------ accountsPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Invoices_AccountsPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getAccountsPerson() {
        return accountsPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Invoices_AccountsPersonID_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param accountsPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setAccountsPerson(final Person accountsPerson) {
        this.accountsPerson = accountsPerson;
        setAccountsPersonId(accountsPerson == null ? null : accountsPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALESPERSON_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person salespersonPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PACKED_BY_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person packedByPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LAST_EDITED_BY,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person lastEditedByPerson;

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
    @JoinColumn(name = COLUMN_NAME_BILL_TO_CUSTOMER_ID,
                referencedColumnName = MappedCustomer.COLUMN_NAME_CUSTOMER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Customer billToCustomer;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ORDER_ID,
                referencedColumnName = MappedOrder.COLUMN_NAME_ORDER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Order order;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DELIVERY_METHOD_ID,
                referencedColumnName = MappedDeliveryMethod.COLUMN_NAME_DELIVERY_METHOD_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private DeliveryMethod deliveryMethod;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CONTACT_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person contactPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ACCOUNTS_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person accountsPerson;
}

