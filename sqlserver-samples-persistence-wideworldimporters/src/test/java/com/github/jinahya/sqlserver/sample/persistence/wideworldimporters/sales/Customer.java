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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.City;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.DeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedCity;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedDeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedBuyingGroup;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomerCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.Customers} table.
 */
@Entity(name = "SalesCustomer")
@Table(schema = MappedCustomer.TABLE_SCHEMA,
       name = MappedCustomer.TABLE_NAME,
       comment = "Main entity tables for customers (organizations or individuals)",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "UQ_Sales_Customers_CustomerName",
                       columnNames = {
                               Customer.COLUMN_NAME_CUSTOMER_NAME
                       }
               )
       }
)
public class Customer extends MappedCustomer {

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_BillToCustomerID_Sales_Customers} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_CUSTOMER = "billToCustomer";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_CustomerCategoryID_Sales_CustomerCategories} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY = "customerCategory";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_BuyingGroupID_Sales_BuyingGroups} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP = "buyingGroup";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_PrimaryContactPersonID_Application_People} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON = "primaryContactPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_AlternateContactPersonID_Application_People} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON = "alternateContactPerson";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_DeliveryMethodID_Application_DeliveryMethods} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD = "deliveryMethod";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_DeliveryCityID_Application_Cities} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_CITY = "deliveryCity";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_PostalCityID_Application_Cities} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CITY = "postalCity";

    /**
     * The name of the attribute of the {@code FK_Sales_Customers_Application_People} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
        super();
    }

    // ------------------------------------------------------------------------------ billToCustomer

    /**
     * Returns the {@code Customer} which the {@code FK_Sales_Customers_BillToCustomerID_Sales_Customers} foreign key
     * references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getBillToCustomer() {
        return billToCustomer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Sales_Customers_BillToCustomerID_Sales_Customers} foreign key
     * references, and the value of the column it consists of.
     *
     * @param billToCustomer the {@code Customer} to reference; {@code null} for none.
     */
    public void setBillToCustomer(final Customer billToCustomer) {
        this.billToCustomer = billToCustomer;
        setBillToCustomerId(billToCustomer == null ? null : billToCustomer.getCustomerId());
    }

    // ---------------------------------------------------------------------------- customerCategory

    /**
     * Returns the {@code CustomerCategory} which the
     * {@code FK_Sales_Customers_CustomerCategoryID_Sales_CustomerCategories} foreign key references.
     *
     * @return the {@code CustomerCategory} referenced; {@code null} when none is.
     */
    public CustomerCategory getCustomerCategory() {
        return customerCategory;
    }

    /**
     * Replaces the {@code CustomerCategory} which the
     * {@code FK_Sales_Customers_CustomerCategoryID_Sales_CustomerCategories} foreign key references, and the value of
     * the column it consists of.
     *
     * @param customerCategory the {@code CustomerCategory} to reference; {@code null} for none.
     */
    public void setCustomerCategory(final CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
        setCustomerCategoryId(customerCategory == null ? null : customerCategory.getCustomerCategoryId());
    }

    // --------------------------------------------------------------------------------- buyingGroup

    /**
     * Returns the {@code BuyingGroup} which the {@code FK_Sales_Customers_BuyingGroupID_Sales_BuyingGroups} foreign key
     * references.
     *
     * @return the {@code BuyingGroup} referenced; {@code null} when none is.
     */
    public BuyingGroup getBuyingGroup() {
        return buyingGroup;
    }

    /**
     * Replaces the {@code BuyingGroup} which the {@code FK_Sales_Customers_BuyingGroupID_Sales_BuyingGroups} foreign
     * key references, and the value of the column it consists of.
     *
     * @param buyingGroup the {@code BuyingGroup} to reference; {@code null} for none.
     */
    public void setBuyingGroup(final BuyingGroup buyingGroup) {
        this.buyingGroup = buyingGroup;
        setBuyingGroupId(buyingGroup == null ? null : buyingGroup.getBuyingGroupId());
    }

    // ------------------------------------------------------------------------ primaryContactPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Customers_PrimaryContactPersonID_Application_People} foreign
     * key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPrimaryContactPerson() {
        return primaryContactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Customers_PrimaryContactPersonID_Application_People}
     * foreign key references, and the value of the column it consists of.
     *
     * @param primaryContactPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setPrimaryContactPerson(final Person primaryContactPerson) {
        this.primaryContactPerson = primaryContactPerson;
        setPrimaryContactPersonId(primaryContactPerson == null ? null : primaryContactPerson.getPersonId());
    }

    // ---------------------------------------------------------------------- alternateContactPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Customers_AlternateContactPersonID_Application_People}
     * foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getAlternateContactPerson() {
        return alternateContactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Customers_AlternateContactPersonID_Application_People}
     * foreign key references, and the value of the column it consists of.
     *
     * @param alternateContactPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setAlternateContactPerson(final Person alternateContactPerson) {
        this.alternateContactPerson = alternateContactPerson;
        setAlternateContactPersonId(alternateContactPerson == null ? null : alternateContactPerson.getPersonId());
    }

    // ------------------------------------------------------------------------------ deliveryMethod

    /**
     * Returns the {@code DeliveryMethod} which the
     * {@code FK_Sales_Customers_DeliveryMethodID_Application_DeliveryMethods} foreign key references.
     *
     * @return the {@code DeliveryMethod} referenced; {@code null} when none is.
     */
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Replaces the {@code DeliveryMethod} which the
     * {@code FK_Sales_Customers_DeliveryMethodID_Application_DeliveryMethods} foreign key references, and the value of
     * the column it consists of.
     *
     * @param deliveryMethod the {@code DeliveryMethod} to reference; {@code null} for none.
     */
    public void setDeliveryMethod(final DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        setDeliveryMethodId(deliveryMethod == null ? null : deliveryMethod.getDeliveryMethodId());
    }

    // -------------------------------------------------------------------------------- deliveryCity

    /**
     * Returns the {@code City} which the {@code FK_Sales_Customers_DeliveryCityID_Application_Cities} foreign key
     * references.
     *
     * @return the {@code City} referenced; {@code null} when none is.
     */
    public City getDeliveryCity() {
        return deliveryCity;
    }

    /**
     * Replaces the {@code City} which the {@code FK_Sales_Customers_DeliveryCityID_Application_Cities} foreign key
     * references, and the value of the column it consists of.
     *
     * @param deliveryCity the {@code City} to reference; {@code null} for none.
     */
    public void setDeliveryCity(final City deliveryCity) {
        this.deliveryCity = deliveryCity;
        setDeliveryCityId(deliveryCity == null ? null : deliveryCity.getCityId());
    }

    // ---------------------------------------------------------------------------------- postalCity

    /**
     * Returns the {@code City} which the {@code FK_Sales_Customers_PostalCityID_Application_Cities} foreign key
     * references.
     *
     * @return the {@code City} referenced; {@code null} when none is.
     */
    public City getPostalCity() {
        return postalCity;
    }

    /**
     * Replaces the {@code City} which the {@code FK_Sales_Customers_PostalCityID_Application_Cities} foreign key
     * references, and the value of the column it consists of.
     *
     * @param postalCity the {@code City} to reference; {@code null} for none.
     */
    public void setPostalCity(final City postalCity) {
        this.postalCity = postalCity;
        setPostalCityId(postalCity == null ? null : postalCity.getCityId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_Customers_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_Customers_Application_People} foreign key references, and
     * the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID,
                referencedColumnName = MappedCustomerCategory.COLUMN_NAME_CUSTOMER_CATEGORY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private CustomerCategory customerCategory;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUYING_GROUP_ID,
                referencedColumnName = MappedBuyingGroup.COLUMN_NAME_BUYING_GROUP_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private BuyingGroup buyingGroup;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person primaryContactPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Person alternateContactPerson;

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
    @JoinColumn(name = COLUMN_NAME_DELIVERY_CITY_ID,
                referencedColumnName = MappedCity.COLUMN_NAME_CITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private City deliveryCity;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_POSTAL_CITY_ID,
                referencedColumnName = MappedCity.COLUMN_NAME_CITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private City postalCity;

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

