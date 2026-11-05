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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.DeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedDeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedPurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplier;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Purchasing.PurchaseOrders} table.
 */
@Entity(name = "PurchasingPurchaseOrder")
@Table(schema = MappedPurchaseOrder.TABLE_SCHEMA, name = MappedPurchaseOrder.TABLE_NAME,
       comment = "Details of supplier purchase orders")
public class PurchaseOrder extends MappedPurchaseOrder {

    /**
     * The name of the attribute of the {@code FK_Purchasing_PurchaseOrders_SupplierID_Purchasing_Suppliers} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER = "supplier";

    /**
     * The name of the attribute of the
     * {@code FK_Purchasing_PurchaseOrders_DeliveryMethodID_Application_DeliveryMethods} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD = "deliveryMethod";

    /**
     * The name of the attribute of the {@code FK_Purchasing_PurchaseOrders_ContactPersonID_Application_People} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON = "contactPerson";

    /**
     * The name of the attribute of the {@code FK_Purchasing_PurchaseOrders_Application_People} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PurchaseOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------ supplier

    /**
     * Returns the {@code Supplier} which the {@code FK_Purchasing_PurchaseOrders_SupplierID_Purchasing_Suppliers}
     * foreign key references.
     *
     * @return the {@code Supplier} referenced; {@code null} when none is.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Replaces the {@code Supplier} which the {@code FK_Purchasing_PurchaseOrders_SupplierID_Purchasing_Suppliers}
     * foreign key references, and the value of the column it consists of.
     *
     * @param supplier the {@code Supplier} to reference; {@code null} for none.
     */
    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
        setSupplierId(supplier == null ? null : supplier.getSupplierId());
    }

    // ------------------------------------------------------------------------------ deliveryMethod

    /**
     * Returns the {@code DeliveryMethod} which the
     * {@code FK_Purchasing_PurchaseOrders_DeliveryMethodID_Application_DeliveryMethods} foreign key references.
     *
     * @return the {@code DeliveryMethod} referenced; {@code null} when none is.
     */
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Replaces the {@code DeliveryMethod} which the
     * {@code FK_Purchasing_PurchaseOrders_DeliveryMethodID_Application_DeliveryMethods} foreign key references, and the
     * value of the column it consists of.
     *
     * @param deliveryMethod the {@code DeliveryMethod} to reference; {@code null} for none.
     */
    public void setDeliveryMethod(final DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        setDeliveryMethodId(deliveryMethod == null ? null : deliveryMethod.getDeliveryMethodId());
    }

    // ------------------------------------------------------------------------------- contactPerson

    /**
     * Returns the {@code Person} which the {@code FK_Purchasing_PurchaseOrders_ContactPersonID_Application_People}
     * foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getContactPerson() {
        return contactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_PurchaseOrders_ContactPersonID_Application_People}
     * foreign key references, and the value of the column it consists of.
     *
     * @param contactPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setContactPerson(final Person contactPerson) {
        this.contactPerson = contactPerson;
        setContactPersonId(contactPerson == null ? null : contactPerson.getPersonId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Purchasing_PurchaseOrders_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_PurchaseOrders_Application_People} foreign key
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
    @JoinColumn(name = COLUMN_NAME_LAST_EDITED_BY,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person lastEditedByPerson;
}

