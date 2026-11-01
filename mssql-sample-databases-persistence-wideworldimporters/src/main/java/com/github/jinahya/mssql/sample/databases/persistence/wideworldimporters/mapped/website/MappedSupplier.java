package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.website;

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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Website.Suppliers} view.
 * <p>
 * Identifier: the view declares no key; SupplierID is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedSupplier {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Website";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Suppliers";

    // ------------------------------------------------------------------------------------------------------ SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ---------------------------------------------------------------------------------------------------- SupplierName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_NAME = "SupplierName";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_NAME = "supplierName";

    // -------------------------------------------------------------------------------------------- SupplierCategoryName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_CATEGORY_NAME = "SupplierCategoryName";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_CATEGORY_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME = "supplierCategoryName";

    // -------------------------------------------------------------------------------------------------- PrimaryContact

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIMARY_CONTACT = "PrimaryContact";

    /**
     * The length of the {@value #COLUMN_NAME_PRIMARY_CONTACT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRIMARY_CONTACT = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIMARY_CONTACT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIMARY_CONTACT = "primaryContact";

    // ------------------------------------------------------------------------------------------------ AlternateContact

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ALTERNATE_CONTACT = "AlternateContact";

    /**
     * The length of the {@value #COLUMN_NAME_ALTERNATE_CONTACT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ALTERNATE_CONTACT = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ALTERNATE_CONTACT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ALTERNATE_CONTACT = "alternateContact";

    // ----------------------------------------------------------------------------------------------------- PhoneNumber

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE_NUMBER = "PhoneNumber";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE_NUMBER = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    // ------------------------------------------------------------------------------------------------------- FaxNumber

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FAX_NUMBER = "FaxNumber";

    /**
     * The length of the {@value #COLUMN_NAME_FAX_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FAX_NUMBER = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FAX_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FAX_NUMBER = "faxNumber";

    // ------------------------------------------------------------------------------------------------------ WebsiteURL

    /**
     * The name of the {@code nvarchar(256), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEBSITE_URL = "WebsiteURL";

    /**
     * The length of the {@value #COLUMN_NAME_WEBSITE_URL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WEBSITE_URL = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEBSITE_URL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEBSITE_URL = "websiteUrl";

    // -------------------------------------------------------------------------------------------------- DeliveryMethod

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_METHOD = "DeliveryMethod";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_METHOD} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_METHOD = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_METHOD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD = "deliveryMethod";

    // -------------------------------------------------------------------------------------------------------- CityName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY_NAME = "CityName";

    /**
     * The length of the {@value #COLUMN_NAME_CITY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY_NAME = "cityName";

    // ------------------------------------------------------------------------------------------------ DeliveryLocation

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_LOCATION = "DeliveryLocation";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_LOCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_LOCATION = "deliveryLocation";

    // ----------------------------------------------------------------------------------------------- SupplierReference

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_REFERENCE = "SupplierReference";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_REFERENCE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_REFERENCE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_REFERENCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_REFERENCE = "supplierReference";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplier() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "supplierId=" + supplierId
               + ",supplierName=" + supplierName
               + ",supplierCategoryName=" + supplierCategoryName
               + ",primaryContact=" + primaryContact
               + ",alternateContact=" + alternateContact
               + ",phoneNumber=" + phoneNumber
               + ",faxNumber=" + faxNumber
               + ",websiteUrl=" + websiteUrl
               + ",deliveryMethod=" + deliveryMethod
               + ",cityName=" + cityName
               + ",deliveryLocation=" + (this.deliveryLocation == null ? null : this.deliveryLocation.length + " bytes")
               + ",supplierReference=" + supplierReference
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
        if (!(obj instanceof MappedSupplier that)) {
            return false;
        }
        return Objects.equals(supplierId, that.supplierId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(supplierId);
    }

    // ------------------------------------------------------------------------------------------------------ supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(final Integer supplierId) {
        this.supplierId = supplierId;
    }

    // ---------------------------------------------------------------------------------------------------- supplierName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute with the specified value.
     *
     * @param supplierName new value for {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    protected void setSupplierName(final String supplierName) {
        this.supplierName = supplierName;
    }

    // -------------------------------------------------------------------------------------------- supplierCategoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute.
     */
    public String getSupplierCategoryName() {
        return supplierCategoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute with the specified value.
     *
     * @param supplierCategoryName new value for {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute.
     */
    protected void setSupplierCategoryName(final String supplierCategoryName) {
        this.supplierCategoryName = supplierCategoryName;
    }

    // -------------------------------------------------------------------------------------------------- primaryContact

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT} attribute.
     */
    public String getPrimaryContact() {
        return primaryContact;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT} attribute with the specified value.
     *
     * @param primaryContact new value for {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT} attribute.
     */
    protected void setPrimaryContact(final String primaryContact) {
        this.primaryContact = primaryContact;
    }

    // ------------------------------------------------------------------------------------------------ alternateContact

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT} attribute.
     */
    public String getAlternateContact() {
        return alternateContact;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT} attribute with the specified value.
     *
     * @param alternateContact new value for {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT} attribute.
     */
    protected void setAlternateContact(final String alternateContact) {
        this.alternateContact = alternateContact;
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute with the specified value.
     *
     * @param phoneNumber new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    protected void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ------------------------------------------------------------------------------------------------------- faxNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute.
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute with the specified value.
     *
     * @param faxNumber new value for {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute.
     */
    protected void setFaxNumber(final String faxNumber) {
        this.faxNumber = faxNumber;
    }

    // ------------------------------------------------------------------------------------------------------ websiteUrl

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute.
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute with the specified value.
     *
     * @param websiteUrl new value for {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute.
     */
    protected void setWebsiteUrl(final String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    // -------------------------------------------------------------------------------------------------- deliveryMethod

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD} attribute.
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD} attribute with the specified value.
     *
     * @param deliveryMethod new value for {@value #ATTRIBUTE_NAME_DELIVERY_METHOD} attribute.
     */
    protected void setDeliveryMethod(final String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    // -------------------------------------------------------------------------------------------------------- cityName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY_NAME} attribute.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY_NAME} attribute with the specified value.
     *
     * @param cityName new value for {@value #ATTRIBUTE_NAME_CITY_NAME} attribute.
     */
    protected void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    // ------------------------------------------------------------------------------------------------ deliveryLocation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     */
    public byte[] getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute with the specified value.
     *
     * @param deliveryLocation new value for {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     */
    protected void setDeliveryLocation(final byte[] deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    // ----------------------------------------------------------------------------------------------- supplierReference

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute.
     */
    public String getSupplierReference() {
        return supplierReference;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute with the specified value.
     *
     * @param supplierReference new value for {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute.
     */
    protected void setSupplierReference(final String supplierReference) {
        this.supplierReference = supplierReference;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer supplierId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_NAME
    )
    private String supplierName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_CATEGORY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_CATEGORY_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_CATEGORY_NAME
    )
    private String supplierCategoryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRIMARY_CONTACT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIMARY_CONTACT,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRIMARY_CONTACT
    )
    private String primaryContact;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ALTERNATE_CONTACT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ALTERNATE_CONTACT,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ALTERNATE_CONTACT
    )
    private String alternateContact;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FAX_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FAX_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FAX_NUMBER
    )
    private String faxNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WEBSITE_URL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WEBSITE_URL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_WEBSITE_URL
    )
    private String websiteUrl;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_METHOD)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_METHOD
    )
    private String deliveryMethod;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CITY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CITY_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CITY_NAME
    )
    private String cityName;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_LOCATION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] deliveryLocation;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_REFERENCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_REFERENCE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_REFERENCE
    )
    private String supplierReference;
}

