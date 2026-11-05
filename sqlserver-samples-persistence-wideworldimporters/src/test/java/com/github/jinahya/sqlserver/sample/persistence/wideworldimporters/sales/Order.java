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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.Orders} table.
 */
@Entity(name = "SalesOrder")
@Table(schema = MappedOrder.TABLE_SCHEMA, name = MappedOrder.TABLE_NAME,
       comment = "Detail of customer orders")
public class Order extends MappedOrder {

    /**
     * The name of the attribute of the {@code FK_Sales_Orders_CustomerID_Sales_Customers} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_Sales_Orders_SalespersonPersonID_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALESPERSON_PERSON = "salespersonPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Orders_PickedByPersonID_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKED_BY_PERSON = "pickedByPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Orders_ContactPersonID_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON = "contactPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Orders_BackorderOrderID_Sales_Orders} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BACKORDER_ORDER = "backorderOrder";

    /**
     * The name of the attribute of the {@code FK_Sales_Orders_Application_People} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Order() {
        super();
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Sales_Orders_CustomerID_Sales_Customers} foreign key
     * references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Sales_Orders_CustomerID_Sales_Customers} foreign key
     * references, and the value of the column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // --------------------------------------------------------------------------- salespersonPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Orders_SalespersonPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getSalespersonPerson() {
        return salespersonPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Orders_SalespersonPersonID_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param salespersonPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setSalespersonPerson(final Person salespersonPerson) {
        this.salespersonPerson = salespersonPerson;
        setSalespersonPersonId(salespersonPerson == null ? null : salespersonPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------ pickedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Orders_PickedByPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPickedByPerson() {
        return pickedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Orders_PickedByPersonID_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param pickedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setPickedByPerson(final Person pickedByPerson) {
        this.pickedByPerson = pickedByPerson;
        setPickedByPersonId(pickedByPerson == null ? null : pickedByPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------- contactPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Orders_ContactPersonID_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getContactPerson() {
        return contactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Orders_ContactPersonID_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param contactPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setContactPerson(final Person contactPerson) {
        this.contactPerson = contactPerson;
        setContactPersonId(contactPerson == null ? null : contactPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------ backorderOrder

    /**
     * Returns the {@code Order} which the {@code FK_Sales_Orders_BackorderOrderID_Sales_Orders} foreign key
     * references.
     *
     * @return the {@code Order} referenced; {@code null} when none is.
     */
    public Order getBackorderOrder() {
        return backorderOrder;
    }

    /**
     * Replaces the {@code Order} which the {@code FK_Sales_Orders_BackorderOrderID_Sales_Orders} foreign key
     * references, and the value of the column it consists of.
     *
     * @param backorderOrder the {@code Order} to reference; {@code null} for none.
     */
    public void setBackorderOrder(final Order backorderOrder) {
        this.backorderOrder = backorderOrder;
        setBackorderOrderId(backorderOrder == null ? null : backorderOrder.getOrderId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Orders_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Orders_Application_People} foreign key references, and the
     * value of the column it consists of.
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
    @JoinColumn(name = COLUMN_NAME_SALESPERSON_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person salespersonPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PICKED_BY_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Person pickedByPerson;

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
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BACKORDER_ORDER_ID,
                referencedColumnName = MappedOrder.COLUMN_NAME_ORDER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Order backorderOrder;

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

