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
 * A mapped superclass which holds the mappings of the {@code Website.Customers} view.
 * <p>
 * Identifier: the view declares no key; CustomerID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable,
 * and none but the identifier, of which EclipseLink requires a writable mapping, is insertable; an INSERT or an UPDATE
 * of a whole row affects more than one of its base tables (error 4405).
 */
@MappedSuperclass
public abstract class MappedCustomer {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Website";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Customers";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ---------------------------------------------------------------------------------------------------- CustomerName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_NAME = "CustomerName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

    // -------------------------------------------------------------------------------------------- CustomerCategoryName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CATEGORY_NAME = "CustomerCategoryName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_CATEGORY_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY_NAME = "customerCategoryName";

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

    // ------------------------------------------------------------------------------------------------- BuyingGroupName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUYING_GROUP_NAME = "BuyingGroupName";

    /**
     * The length of the {@value #COLUMN_NAME_BUYING_GROUP_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BUYING_GROUP_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUYING_GROUP_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP_NAME = "buyingGroupName";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCustomer() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerId=" + customerId
               + ",customerName=" + customerName
               + ",customerCategoryName=" + customerCategoryName
               + ",primaryContact=" + primaryContact
               + ",alternateContact=" + alternateContact
               + ",phoneNumber=" + phoneNumber
               + ",faxNumber=" + faxNumber
               + ",buyingGroupName=" + buyingGroupName
               + ",websiteUrl=" + websiteUrl
               + ",deliveryMethod=" + deliveryMethod
               + ",cityName=" + cityName
               + ",deliveryLocation=" + (this.deliveryLocation == null ? null : this.deliveryLocation.length + " bytes")
               + ",deliveryRun=" + deliveryRun
               + ",runPosition=" + runPosition
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
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return Objects.equals(customerId, that.customerId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(customerId);
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- customerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute with the specified value.
     *
     * @param customerName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    protected void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    // -------------------------------------------------------------------------------------------- customerCategoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_NAME} attribute.
     */
    public String getCustomerCategoryName() {
        return customerCategoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_NAME} attribute with the specified value.
     *
     * @param customerCategoryName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_NAME} attribute.
     */
    protected void setCustomerCategoryName(final String customerCategoryName) {
        this.customerCategoryName = customerCategoryName;
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

    // ------------------------------------------------------------------------------------------------- buyingGroupName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_NAME} attribute.
     */
    public String getBuyingGroupName() {
        return buyingGroupName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_NAME} attribute with the specified value.
     *
     * @param buyingGroupName new value for {@value #ATTRIBUTE_NAME_BUYING_GROUP_NAME} attribute.
     */
    protected void setBuyingGroupName(final String buyingGroupName) {
        this.buyingGroupName = buyingGroupName;
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
    protected void setDeliveryRun(final String deliveryRun) {
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
    protected void setRunPosition(final String runPosition) {
        this.runPosition = runPosition;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only but for an INSERT: SQL Server does not write this view, and EclipseLink requires a writable mapping
    // of every identifier column; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_NAME
    )
    private String customerName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_CATEGORY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_CATEGORY_NAME
    )
    private String customerCategoryName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PRIMARY_CONTACT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIMARY_CONTACT,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PRIMARY_CONTACT
    )
    private String primaryContact;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ALTERNATE_CONTACT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ALTERNATE_CONTACT,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ALTERNATE_CONTACT
    )
    private String alternateContact;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_FAX_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FAX_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FAX_NUMBER
    )
    private String faxNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_BUYING_GROUP_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BUYING_GROUP_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BUYING_GROUP_NAME
    )
    private String buyingGroupName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WEBSITE_URL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WEBSITE_URL,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WEBSITE_URL
    )
    private String websiteUrl;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DELIVERY_METHOD)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DELIVERY_METHOD
    )
    private String deliveryMethod;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CITY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CITY_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CITY_NAME
    )
    private String cityName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_LOCATION,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private byte[] deliveryLocation;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DELIVERY_RUN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_RUN,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DELIVERY_RUN
    )
    private String deliveryRun;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_RUN_POSITION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RUN_POSITION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_RUN_POSITION
    )
    private String runPosition;
}

