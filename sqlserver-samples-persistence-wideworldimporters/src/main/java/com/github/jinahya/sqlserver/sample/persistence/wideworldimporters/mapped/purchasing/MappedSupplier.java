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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsMasking;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.Suppliers} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Purchasing.Suppliers_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_SUPPLIER_CATEGORY_ID}, {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID},
 * {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID}, {@value #COLUMN_NAME_DELIVERY_METHOD_ID},
 * {@value #COLUMN_NAME_DELIVERY_CITY_ID}, {@value #COLUMN_NAME_POSTAL_CITY_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Purchasing.Suppliers}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SupplierID}</th>
 *     <td>Numeric ID used for reference to a supplier within the database</td></tr>
 * <tr><th scope="row">{@code SupplierName}</th>
 *     <td>Supplier's full name (usually a trading name)</td></tr>
 * <tr><th scope="row">{@code SupplierCategoryID}</th><td>Supplier's category</td></tr>
 * <tr><th scope="row">{@code PrimaryContactPersonID}</th><td>Primary contact</td></tr>
 * <tr><th scope="row">{@code AlternateContactPersonID}</th><td>Alternate contact</td></tr>
 * <tr><th scope="row">{@code DeliveryMethodID}</th>
 *     <td>Standard delivery method for stock items received from this supplier</td></tr>
 * <tr><th scope="row">{@code DeliveryCityID}</th>
 *     <td>ID of the delivery city for this address</td></tr>
 * <tr><th scope="row">{@code PostalCityID}</th>
 *     <td>ID of the mailing city for this address</td></tr>
 * <tr><th scope="row">{@code SupplierReference}</th>
 *     <td>Supplier reference for our organization (might be our account number at the
 *     supplier)</td></tr>
 * <tr><th scope="row">{@code BankAccountName}</th>
 *     <td>Supplier's bank account name (ie name on the account)</td></tr>
 * <tr><th scope="row">{@code BankAccountBranch}</th><td>Supplier's bank branch</td></tr>
 * <tr><th scope="row">{@code BankAccountCode}</th>
 *     <td>Supplier's bank account code (usually a numeric reference for the bank branch)</td></tr>
 * <tr><th scope="row">{@code BankAccountNumber}</th><td>Supplier's bank account number</td></tr>
 * <tr><th scope="row">{@code BankInternationalCode}</th>
 *     <td>Supplier's bank's international code (such as a SWIFT code)</td></tr>
 * <tr><th scope="row">{@code PaymentDays}</th>
 *     <td>Number of days for payment of an invoice (ie payment terms)</td></tr>
 * <tr><th scope="row">{@code InternalComments}</th>
 *     <td>Internal comments (not exposed outside organization)</td></tr>
 * <tr><th scope="row">{@code PhoneNumber}</th><td>Phone number</td></tr>
 * <tr><th scope="row">{@code FaxNumber}</th><td>Fax number</td></tr>
 * <tr><th scope="row">{@code WebsiteURL}</th><td>URL for the website for this supplier</td></tr>
 * <tr><th scope="row">{@code DeliveryAddressLine1}</th>
 *     <td>First delivery address line for the supplier</td></tr>
 * <tr><th scope="row">{@code DeliveryAddressLine2}</th>
 *     <td>Second delivery address line for the supplier</td></tr>
 * <tr><th scope="row">{@code DeliveryPostalCode}</th>
 *     <td>Delivery postal code for the supplier</td></tr>
 * <tr><th scope="row">{@code DeliveryLocation}</th>
 *     <td>Geographic location for the supplier's office/warehouse</td></tr>
 * <tr><th scope="row">{@code PostalAddressLine1}</th>
 *     <td>First postal address line for the supplier</td></tr>
 * <tr><th scope="row">{@code PostalAddressLine2}</th>
 *     <td>Second postal address line for the supplier</td></tr>
 * <tr><th scope="row">{@code PostalPostalCode}</th>
 *     <td>Postal code for the supplier when sending by mail</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Purchasing.Suppliers}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SupplierID}</th><td>{@code (NEXT VALUE FOR [Sequences].[SupplierID])}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Masked columns of {@code Purchasing.Suppliers}</caption>
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
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedSupplier.TABLE_SCHEMA,
        table = MappedSupplier.TABLE_NAME,
        value = "Main entity table for suppliers (organizations)"
)
@MsTemporal(
        historyTable = "Purchasing.Suppliers_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
public abstract class MappedSupplier {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Suppliers";

    // ---------------------------------------------------------------------------------- SupplierID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.SupplierID, NOT NULL} column. The value is {@value}.
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
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
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
    protected MappedSupplier() {
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
    public void setSupplierName(final String supplierName) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
     * The attribute is read-only; the extending entity calls this to keep it in step with the association which writes
     * the column.
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
     * value. The attribute is read-only; the extending entity calls this to keep it in step with the association which
     * writes the column.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
    public void setSupplierReference(final String supplierReference) {
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
    public void setBankAccountName(final String bankAccountName) {
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
    public void setBankAccountBranch(final String bankAccountBranch) {
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
    public void setBankAccountCode(final String bankAccountCode) {
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
    public void setBankAccountNumber(final String bankAccountNumber) {
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
    public void setBankInternationalCode(final String bankInternationalCode) {
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
    public void setPaymentDays(final Integer paymentDays) {
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
    public void setInternalComments(final String internalComments) {
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
    public void setPhoneNumber(final String phoneNumber) {
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
    public void setFaxNumber(final String faxNumber) {
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
    public void setWebsiteUrl(final String websiteUrl) {
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
    public void setDeliveryAddressLine1(final String deliveryAddressLine1) {
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
    public void setDeliveryAddressLine2(final String deliveryAddressLine2) {
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
    public void setDeliveryPostalCode(final String deliveryPostalCode) {
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
    public void setDeliveryLocation(final Geography deliveryLocation) {
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
    public void setPostalAddressLine1(final String postalAddressLine1) {
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
    public void setPostalAddressLine2(final String postalAddressLine2) {
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
    public void setPostalPostalCode(final String postalPostalCode) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.SupplierID", schema = "Sequences",
                       sequenceName = "SupplierID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.SupplierID")
    @Column(name = COLUMN_NAME_SUPPLIER_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_ID,
                          value = "Numeric ID used for reference to a supplier within the database")
    @MsDefault(name = "DF_Purchasing_Suppliers_SupplierID", value = "(NEXT VALUE FOR [Sequences].[SupplierID])")
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_SUPPLIER_NAME)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_NAME,
                          value = "Supplier's full name (usually a trading name)")
    private String supplierName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_CATEGORY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_CATEGORY_ID, value = "Supplier's category")
    private Integer supplierCategoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID, value = "Primary contact")
    private Integer primaryContactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID,
                          value = "Alternate contact")
    private Integer alternateContactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_METHOD_ID,
                          value = "Standard delivery method for stock items received from this "
                                  + "supplier")
    private Integer deliveryMethodId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_CITY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_CITY_ID,
                          value = "ID of the delivery city for this address")
    private Integer deliveryCityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_CITY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_CITY_ID,
                          value = "ID of the mailing city for this address")
    private Integer postalCityId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_REFERENCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_REFERENCE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_SUPPLIER_REFERENCE)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_REFERENCE,
                          value = "Supplier reference for our organization (might be our "
                                  + "account number at the supplier)")
    private String supplierReference;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_NAME, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BANK_ACCOUNT_NAME)
    @MsDescription.Column(name = COLUMN_NAME_BANK_ACCOUNT_NAME,
                          value = "Supplier's bank account name (ie name on the account)")
    @MsMasking("default()")
    private String bankAccountName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_BRANCH)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_BRANCH, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BANK_ACCOUNT_BRANCH)
    @MsDescription.Column(name = COLUMN_NAME_BANK_ACCOUNT_BRANCH, value = "Supplier's bank branch")
    @MsMasking("default()")
    private String bankAccountBranch;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_CODE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BANK_ACCOUNT_CODE)
    @MsDescription.Column(name = COLUMN_NAME_BANK_ACCOUNT_CODE,
                          value = "Supplier's bank account code (usually a numeric reference "
                                  + "for the bank branch)")
    @MsMasking("default()")
    private String bankAccountCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BANK_ACCOUNT_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_ACCOUNT_NUMBER, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BANK_ACCOUNT_NUMBER)
    @MsDescription.Column(name = COLUMN_NAME_BANK_ACCOUNT_NUMBER,
                          value = "Supplier's bank account number")
    @MsMasking("default()")
    private String bankAccountNumber;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BANK_INTERNATIONAL_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BANK_INTERNATIONAL_CODE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BANK_INTERNATIONAL_CODE)
    @MsDescription.Column(name = COLUMN_NAME_BANK_INTERNATIONAL_CODE,
                          value = "Supplier's bank's international code (such as a SWIFT code)")
    @MsMasking("default()")
    private String bankInternationalCode;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAYMENT_DAYS, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_PAYMENT_DAYS,
                          value = "Number of days for payment of an invoice (ie payment terms)")
    private Integer paymentDays;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
                          value = "Internal comments (not exposed outside organization)")
    private String internalComments;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PHONE_NUMBER, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_PHONE_NUMBER)
    @MsDescription.Column(name = COLUMN_NAME_PHONE_NUMBER, value = "Phone number")
    private String phoneNumber;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_FAX_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FAX_NUMBER, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_FAX_NUMBER)
    @MsDescription.Column(name = COLUMN_NAME_FAX_NUMBER, value = "Fax number")
    private String faxNumber;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WEBSITE_URL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WEBSITE_URL, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_WEBSITE_URL)
    @MsDescription.Column(name = COLUMN_NAME_WEBSITE_URL,
                          value = "URL for the website for this supplier")
    private String websiteUrl;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_1, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_1,
                          value = "First delivery address line for the supplier")
    private String deliveryAddressLine1;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_2, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_2,
                          value = "Second delivery address line for the supplier")
    private String deliveryAddressLine2;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_POSTAL_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_POSTAL_CODE)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_POSTAL_CODE,
                          value = "Delivery postal code for the supplier")
    private String deliveryPostalCode;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = GeographyAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_LOCATION, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_LOCATION,
                          value = "Geographic location for the supplier's office/warehouse")
    private Geography deliveryLocation;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_1, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_1,
                          value = "First postal address line for the supplier")
    private String postalAddressLine1;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_2, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_2,
                          value = "Second postal address line for the supplier")
    private String postalAddressLine2;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_POSTAL_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_POSTAL_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_POSTAL_POSTAL_CODE)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_POSTAL_CODE,
                          value = "Postal code for the supplier when sending by mail")
    private String postalPostalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

