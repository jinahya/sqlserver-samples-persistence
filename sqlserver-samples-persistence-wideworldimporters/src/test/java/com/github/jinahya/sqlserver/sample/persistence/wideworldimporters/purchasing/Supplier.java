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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.City;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.DeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedCity;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedDeliveryMethod;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplierCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Purchasing.Suppliers} table.
 */
@Entity(name = "PurchasingSupplier")
@Table(schema = MappedSupplier.TABLE_SCHEMA,
       name = MappedSupplier.TABLE_NAME,
       comment = "Main entity table for suppliers (organizations)",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "UQ_Purchasing_Suppliers_SupplierName",
                       columnNames = {
                               Supplier.COLUMN_NAME_SUPPLIER_NAME
                       }
               )
       }
)
public class Supplier extends MappedSupplier {

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_SupplierCategoryID_Purchasing_SupplierCategories}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CATEGORY = "supplierCategory";

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_PrimaryContactPersonID_Application_People}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON = "primaryContactPerson";

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_AlternateContactPersonID_Application_People}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON = "alternateContactPerson";

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_DeliveryMethodID_Application_DeliveryMethods}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD = "deliveryMethod";

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_DeliveryCityID_Application_Cities} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_CITY = "deliveryCity";

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_PostalCityID_Application_Cities} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CITY = "postalCity";

    /**
     * The name of the attribute of the {@code FK_Purchasing_Suppliers_Application_People} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Supplier() {
        super();
    }

    // ---------------------------------------------------------------------------- supplierCategory

    /**
     * Returns the {@code SupplierCategory} which the
     * {@code FK_Purchasing_Suppliers_SupplierCategoryID_Purchasing_SupplierCategories} foreign key references.
     *
     * @return the {@code SupplierCategory} referenced; {@code null} when none is.
     */
    public SupplierCategory getSupplierCategory() {
        return supplierCategory;
    }

    /**
     * Replaces the {@code SupplierCategory} which the
     * {@code FK_Purchasing_Suppliers_SupplierCategoryID_Purchasing_SupplierCategories} foreign key references, and the
     * value of the column it consists of.
     *
     * @param supplierCategory the {@code SupplierCategory} to reference; {@code null} for none.
     */
    public void setSupplierCategory(final SupplierCategory supplierCategory) {
        this.supplierCategory = supplierCategory;
        setSupplierCategoryId(supplierCategory == null ? null : supplierCategory.getSupplierCategoryId());
    }

    // ------------------------------------------------------------------------ primaryContactPerson

    /**
     * Returns the {@code Person} which the {@code FK_Purchasing_Suppliers_PrimaryContactPersonID_Application_People}
     * foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPrimaryContactPerson() {
        return primaryContactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_Suppliers_PrimaryContactPersonID_Application_People}
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
     * Returns the {@code Person} which the {@code FK_Purchasing_Suppliers_AlternateContactPersonID_Application_People}
     * foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getAlternateContactPerson() {
        return alternateContactPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_Suppliers_AlternateContactPersonID_Application_People}
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
     * {@code FK_Purchasing_Suppliers_DeliveryMethodID_Application_DeliveryMethods} foreign key references.
     *
     * @return the {@code DeliveryMethod} referenced; {@code null} when none is.
     */
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Replaces the {@code DeliveryMethod} which the
     * {@code FK_Purchasing_Suppliers_DeliveryMethodID_Application_DeliveryMethods} foreign key references, and the
     * value of the column it consists of.
     *
     * @param deliveryMethod the {@code DeliveryMethod} to reference; {@code null} for none.
     */
    public void setDeliveryMethod(final DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
        setDeliveryMethodId(deliveryMethod == null ? null : deliveryMethod.getDeliveryMethodId());
    }

    // -------------------------------------------------------------------------------- deliveryCity

    /**
     * Returns the {@code City} which the {@code FK_Purchasing_Suppliers_DeliveryCityID_Application_Cities} foreign key
     * references.
     *
     * @return the {@code City} referenced; {@code null} when none is.
     */
    public City getDeliveryCity() {
        return deliveryCity;
    }

    /**
     * Replaces the {@code City} which the {@code FK_Purchasing_Suppliers_DeliveryCityID_Application_Cities} foreign key
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
     * Returns the {@code City} which the {@code FK_Purchasing_Suppliers_PostalCityID_Application_Cities} foreign key
     * references.
     *
     * @return the {@code City} referenced; {@code null} when none is.
     */
    public City getPostalCity() {
        return postalCity;
    }

    /**
     * Replaces the {@code City} which the {@code FK_Purchasing_Suppliers_PostalCityID_Application_Cities} foreign key
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
     * Returns the {@code Person} which the {@code FK_Purchasing_Suppliers_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_Suppliers_Application_People} foreign key references,
     * and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SUPPLIER_CATEGORY_ID,
                referencedColumnName = MappedSupplierCategory.COLUMN_NAME_SUPPLIER_CATEGORY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private SupplierCategory supplierCategory;

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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person alternateContactPerson;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_DELIVERY_METHOD_ID,
                referencedColumnName = MappedDeliveryMethod.COLUMN_NAME_DELIVERY_METHOD_ID,
                nullable = true,
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

