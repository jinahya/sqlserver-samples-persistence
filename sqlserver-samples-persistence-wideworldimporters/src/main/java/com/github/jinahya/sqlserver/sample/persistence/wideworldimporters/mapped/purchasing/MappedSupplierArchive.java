package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing;

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

import com.github.jinahya.sqlserver.sample.persistence.common.Geography;
import com.github.jinahya.sqlserver.sample.persistence.common.GeographyAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsMasking;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.Suppliers_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server rejects an INSERT (error 13559) and an UPDATE (error 13561) of a temporal history table; only
 * the system versioning of {@code Purchasing.Suppliers} writes it.
 *
 * <table class="striped">
 * <caption>Masked columns of {@code Purchasing.Suppliers_Archive}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Masking function</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BankAccountName}</th><td>{@code default()}</td></tr>
 * <tr><th scope="row">{@code BankAccountBranch}</th><td>{@code default()}</td></tr>
 * <tr><th scope="row">{@code BankAccountCode}</th><td>{@code default()}</td></tr>
 * <tr><th scope="row">{@code BankAccountNumber}</th><td>{@code default()}</td></tr>
 * <tr><th scope="row">{@code BankInternationalCode}</th><td>{@code default()}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedSupplierArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Suppliers_Archive";

    // ---------------------------------------------------------------------------------- SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // -------------------------------------------------------------------------------- SupplierName

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

    // -------------------------------------------------------------------------- SupplierCategoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_CATEGORY_ID = "SupplierCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID = "supplierCategoryId";

    // ---------------------------------------------------------------------- PrimaryContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID = "PrimaryContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID = "primaryContactPersonId";

    // -------------------------------------------------------------------- AlternateContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID = "AlternateContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID = "alternateContactPersonId";

    // ---------------------------------------------------------------------------- DeliveryMethodID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_METHOD_ID = "DeliveryMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD_ID = "deliveryMethodId";

    // ------------------------------------------------------------------------------ DeliveryCityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_CITY_ID = "DeliveryCityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_CITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_CITY_ID = "deliveryCityId";

    // -------------------------------------------------------------------------------- PostalCityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_CITY_ID = "PostalCityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_CITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CITY_ID = "postalCityId";

    // --------------------------------------------------------------------------- SupplierReference

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

    // ----------------------------------------------------------------------------- BankAccountName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BANK_ACCOUNT_NAME = "BankAccountName";

    /**
     * The length of the {@value #COLUMN_NAME_BANK_ACCOUNT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BANK_ACCOUNT_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BANK_ACCOUNT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BANK_ACCOUNT_NAME = "bankAccountName";

    // --------------------------------------------------------------------------- BankAccountBranch

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BANK_ACCOUNT_BRANCH = "BankAccountBranch";

    /**
     * The length of the {@value #COLUMN_NAME_BANK_ACCOUNT_BRANCH} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BANK_ACCOUNT_BRANCH = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BANK_ACCOUNT_BRANCH} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BANK_ACCOUNT_BRANCH = "bankAccountBranch";

    // ----------------------------------------------------------------------------- BankAccountCode

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BANK_ACCOUNT_CODE = "BankAccountCode";

    /**
     * The length of the {@value #COLUMN_NAME_BANK_ACCOUNT_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BANK_ACCOUNT_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BANK_ACCOUNT_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BANK_ACCOUNT_CODE = "bankAccountCode";

    // --------------------------------------------------------------------------- BankAccountNumber

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BANK_ACCOUNT_NUMBER = "BankAccountNumber";

    /**
     * The length of the {@value #COLUMN_NAME_BANK_ACCOUNT_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BANK_ACCOUNT_NUMBER = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BANK_ACCOUNT_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BANK_ACCOUNT_NUMBER = "bankAccountNumber";

    // ----------------------------------------------------------------------- BankInternationalCode

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BANK_INTERNATIONAL_CODE = "BankInternationalCode";

    /**
     * The length of the {@value #COLUMN_NAME_BANK_INTERNATIONAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BANK_INTERNATIONAL_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BANK_INTERNATIONAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BANK_INTERNATIONAL_CODE = "bankInternationalCode";

    // --------------------------------------------------------------------------------- PaymentDays

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_DAYS = "PaymentDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_DAYS = "paymentDays";

    // ---------------------------------------------------------------------------- InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

    // --------------------------------------------------------------------------------- PhoneNumber

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

    // ----------------------------------------------------------------------------------- FaxNumber

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

    // ---------------------------------------------------------------------------------- WebsiteURL

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

    // ------------------------------------------------------------------------ DeliveryAddressLine1

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_ADDRESS_LINE_1 = "DeliveryAddressLine1";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_1} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_1} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1 = "deliveryAddressLine1";

    // ------------------------------------------------------------------------ DeliveryAddressLine2

    /**
     * The name of the {@code nvarchar(60)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_ADDRESS_LINE_2 = "DeliveryAddressLine2";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_2} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_2} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2 = "deliveryAddressLine2";

    // -------------------------------------------------------------------------- DeliveryPostalCode

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_POSTAL_CODE = "DeliveryPostalCode";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_POSTAL_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_POSTAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE = "deliveryPostalCode";

    // ---------------------------------------------------------------------------- DeliveryLocation

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_LOCATION = "DeliveryLocation";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_LOCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_LOCATION = "deliveryLocation";

    // -------------------------------------------------------------------------- PostalAddressLine1

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_ADDRESS_LINE_1 = "PostalAddressLine1";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_1} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_1} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1 = "postalAddressLine1";

    // -------------------------------------------------------------------------- PostalAddressLine2

    /**
     * The name of the {@code nvarchar(60)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_ADDRESS_LINE_2 = "PostalAddressLine2";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_2} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_2} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2 = "postalAddressLine2";

    // ---------------------------------------------------------------------------- PostalPostalCode

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_POSTAL_CODE = "PostalPostalCode";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_POSTAL_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_POSTAL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_POSTAL_CODE = "postalPostalCode";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ----------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplierArchive() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "supplierId=" + supplierId
               + ",supplierName=" + supplierName
               + ",supplierCategoryId=" + supplierCategoryId
               + ",primaryContactPersonId=" + primaryContactPersonId
               + ",alternateContactPersonId=" + alternateContactPersonId
               + ",deliveryMethodId=" + deliveryMethodId
               + ",deliveryCityId=" + deliveryCityId
               + ",postalCityId=" + postalCityId
               + ",supplierReference=" + supplierReference
               + ",bankAccountName=" + bankAccountName
               + ",bankAccountBranch=" + bankAccountBranch
               + ",bankAccountCode=" + bankAccountCode
               + ",bankAccountNumber=" + bankAccountNumber
               + ",bankInternationalCode=" + bankInternationalCode
               + ",paymentDays=" + paymentDays
               + ",internalComments=" + internalComments
               + ",phoneNumber=" + phoneNumber
               + ",faxNumber=" + faxNumber
               + ",websiteUrl=" + websiteUrl
               + ",deliveryAddressLine1=" + deliveryAddressLine1
               + ",deliveryAddressLine2=" + deliveryAddressLine2
               + ",deliveryPostalCode=" + deliveryPostalCode
               + ",deliveryLocation=" + deliveryLocation
               + ",postalAddressLine1=" + postalAddressLine1
               + ",postalAddressLine2=" + postalAddressLine2
               + ",postalPostalCode=" + postalPostalCode
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // ---------------------------------------------------------------------------------- supplierId

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

    // -------------------------------------------------------------------------------- supplierName

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

    // -------------------------------------------------------------------------- supplierCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute.
     */
    public Integer getSupplierCategoryId() {
        return supplierCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute with the specified value.
     *
     * @param supplierCategoryId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute.
     */
    protected void setSupplierCategoryId(final Integer supplierCategoryId) {
        this.supplierCategoryId = supplierCategoryId;
    }

    // ---------------------------------------------------------------------- primaryContactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     */
    public Integer getPrimaryContactPersonId() {
        return primaryContactPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute with the specified value.
     *
     * @param primaryContactPersonId new value for {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     */
    protected void setPrimaryContactPersonId(final Integer primaryContactPersonId) {
        this.primaryContactPersonId = primaryContactPersonId;
    }

    // -------------------------------------------------------------------- alternateContactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID} attribute.
     */
    public Integer getAlternateContactPersonId() {
        return alternateContactPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID} attribute with the specified
     * value.
     *
     * @param alternateContactPersonId new value for {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID} attribute.
     */
    protected void setAlternateContactPersonId(final Integer alternateContactPersonId) {
        this.alternateContactPersonId = alternateContactPersonId;
    }

    // ---------------------------------------------------------------------------- deliveryMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    public Integer getDeliveryMethodId() {
        return deliveryMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute with the specified value.
     *
     * @param deliveryMethodId new value for {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    protected void setDeliveryMethodId(final Integer deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    // ------------------------------------------------------------------------------ deliveryCityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute.
     */
    public Integer getDeliveryCityId() {
        return deliveryCityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute with the specified value.
     *
     * @param deliveryCityId new value for {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute.
     */
    protected void setDeliveryCityId(final Integer deliveryCityId) {
        this.deliveryCityId = deliveryCityId;
    }

    // -------------------------------------------------------------------------------- postalCityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute.
     */
    public Integer getPostalCityId() {
        return postalCityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute with the specified value.
     *
     * @param postalCityId new value for {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute.
     */
    protected void setPostalCityId(final Integer postalCityId) {
        this.postalCityId = postalCityId;
    }

    // --------------------------------------------------------------------------- supplierReference

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

    // ----------------------------------------------------------------------------- bankAccountName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NAME} attribute.
     */
    public String getBankAccountName() {
        return bankAccountName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NAME} attribute with the specified value.
     *
     * @param bankAccountName new value for {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NAME} attribute.
     */
    protected void setBankAccountName(final String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    // --------------------------------------------------------------------------- bankAccountBranch

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_BRANCH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_BRANCH} attribute.
     */
    public String getBankAccountBranch() {
        return bankAccountBranch;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_BRANCH} attribute with the specified value.
     *
     * @param bankAccountBranch new value for {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_BRANCH} attribute.
     */
    protected void setBankAccountBranch(final String bankAccountBranch) {
        this.bankAccountBranch = bankAccountBranch;
    }

    // ----------------------------------------------------------------------------- bankAccountCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_CODE} attribute.
     */
    public String getBankAccountCode() {
        return bankAccountCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_CODE} attribute with the specified value.
     *
     * @param bankAccountCode new value for {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_CODE} attribute.
     */
    protected void setBankAccountCode(final String bankAccountCode) {
        this.bankAccountCode = bankAccountCode;
    }

    // --------------------------------------------------------------------------- bankAccountNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NUMBER} attribute.
     */
    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NUMBER} attribute with the specified value.
     *
     * @param bankAccountNumber new value for {@value #ATTRIBUTE_NAME_BANK_ACCOUNT_NUMBER} attribute.
     */
    protected void setBankAccountNumber(final String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    // ----------------------------------------------------------------------- bankInternationalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BANK_INTERNATIONAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BANK_INTERNATIONAL_CODE} attribute.
     */
    public String getBankInternationalCode() {
        return bankInternationalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BANK_INTERNATIONAL_CODE} attribute with the specified value.
     *
     * @param bankInternationalCode new value for {@value #ATTRIBUTE_NAME_BANK_INTERNATIONAL_CODE} attribute.
     */
    protected void setBankInternationalCode(final String bankInternationalCode) {
        this.bankInternationalCode = bankInternationalCode;
    }

    // --------------------------------------------------------------------------------- paymentDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute.
     */
    public Integer getPaymentDays() {
        return paymentDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute with the specified value.
     *
     * @param paymentDays new value for {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute.
     */
    protected void setPaymentDays(final Integer paymentDays) {
        this.paymentDays = paymentDays;
    }

    // ---------------------------------------------------------------------------- internalComments

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
    protected void setInternalComments(final String internalComments) {
        this.internalComments = internalComments;
    }

    // --------------------------------------------------------------------------------- phoneNumber

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

    // ----------------------------------------------------------------------------------- faxNumber

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

    // ---------------------------------------------------------------------------------- websiteUrl

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

    // ------------------------------------------------------------------------ deliveryAddressLine1

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute.
     */
    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute with the specified value.
     *
     * @param deliveryAddressLine1 new value for {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute.
     */
    protected void setDeliveryAddressLine1(final String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    // ------------------------------------------------------------------------ deliveryAddressLine2

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute.
     */
    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute with the specified value.
     *
     * @param deliveryAddressLine2 new value for {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute.
     */
    protected void setDeliveryAddressLine2(final String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    // -------------------------------------------------------------------------- deliveryPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute.
     */
    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute with the specified value.
     *
     * @param deliveryPostalCode new value for {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute.
     */
    protected void setDeliveryPostalCode(final String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    // ---------------------------------------------------------------------------- deliveryLocation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     */
    public Geography getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute with the specified value.
     *
     * @param deliveryLocation new value for {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     */
    protected void setDeliveryLocation(final Geography deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    // -------------------------------------------------------------------------- postalAddressLine1

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute.
     */
    public String getPostalAddressLine1() {
        return postalAddressLine1;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute with the specified value.
     *
     * @param postalAddressLine1 new value for {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute.
     */
    protected void setPostalAddressLine1(final String postalAddressLine1) {
        this.postalAddressLine1 = postalAddressLine1;
    }

    // -------------------------------------------------------------------------- postalAddressLine2

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute.
     */
    public String getPostalAddressLine2() {
        return postalAddressLine2;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute with the specified value.
     *
     * @param postalAddressLine2 new value for {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute.
     */
    protected void setPostalAddressLine2(final String postalAddressLine2) {
        this.postalAddressLine2 = postalAddressLine2;
    }

    // ---------------------------------------------------------------------------- postalPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute.
     */
    public String getPostalPostalCode() {
        return postalPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute with the specified value.
     *
     * @param postalPostalCode new value for {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute.
     */
    protected void setPostalPostalCode(final String postalPostalCode) {
        this.postalPostalCode = postalPostalCode;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ----------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // ------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_SUPPLIER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SUPPLIER_NAME
    )
    private String supplierName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_CATEGORY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer supplierCategoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer primaryContactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer alternateContactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer deliveryMethodId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_CITY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer deliveryCityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_CITY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer postalCityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_SUPPLIER_REFERENCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_REFERENCE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SUPPLIER_REFERENCE
    )
    private String supplierReference;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BANK_ACCOUNT_NAME
    )
    @MsMasking("default()")
    private String bankAccountName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_BRANCH)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_BRANCH,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BANK_ACCOUNT_BRANCH
    )
    @MsMasking("default()")
    private String bankAccountBranch;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_CODE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BANK_ACCOUNT_CODE
    )
    @MsMasking("default()")
    private String bankAccountCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_NUMBER,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BANK_ACCOUNT_NUMBER
    )
    @MsMasking("default()")
    private String bankAccountNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BANK_INTERNATIONAL_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_INTERNATIONAL_CODE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_BANK_INTERNATIONAL_CODE
    )
    @MsMasking("default()")
    private String bankInternationalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAYMENT_DAYS,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer paymentDays;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String internalComments;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
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

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
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

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
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

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_1,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1
    )
    private String deliveryAddressLine1;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_2,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2
    )
    private String deliveryAddressLine2;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_DELIVERY_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_POSTAL_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DELIVERY_POSTAL_CODE
    )
    private String deliveryPostalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Convert(converter = GeographyAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_LOCATION,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Geography deliveryLocation;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_1,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1
    )
    private String postalAddressLine1;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_2,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2
    )
    private String postalAddressLine2;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_POSTAL_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_POSTAL_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_POSTAL_POSTAL_CODE
    )
    private String postalPostalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validTo;
}

