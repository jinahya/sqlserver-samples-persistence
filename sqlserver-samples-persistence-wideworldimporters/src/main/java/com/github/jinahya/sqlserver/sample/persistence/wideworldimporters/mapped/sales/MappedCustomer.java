package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales;

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
import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsRowLevelSecurity;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.Customers} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Sales.Customers_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID}, {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID},
 * {@value #COLUMN_NAME_BUYING_GROUP_ID}, {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID},
 * {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID}, {@value #COLUMN_NAME_DELIVERY_METHOD_ID},
 * {@value #COLUMN_NAME_DELIVERY_CITY_ID}, {@value #COLUMN_NAME_POSTAL_CITY_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.Customers}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CustomerID}</th>
 *     <td>Numeric ID used for reference to a customer within the database</td></tr>
 * <tr><th scope="row">{@code CustomerName}</th>
 *     <td>Customer's full name (usually a trading name)</td></tr>
 * <tr><th scope="row">{@code BillToCustomerID}</th>
 *     <td>Customer that this is billed to (usually the same customer but can be another parent
 *     company)</td></tr>
 * <tr><th scope="row">{@code CustomerCategoryID}</th><td>Customer's category</td></tr>
 * <tr><th scope="row">{@code BuyingGroupID}</th><td>Customer's buying group (optional)</td></tr>
 * <tr><th scope="row">{@code PrimaryContactPersonID}</th><td>Primary contact</td></tr>
 * <tr><th scope="row">{@code AlternateContactPersonID}</th><td>Alternate contact</td></tr>
 * <tr><th scope="row">{@code DeliveryMethodID}</th>
 *     <td>Standard delivery method for stock items sent to this customer</td></tr>
 * <tr><th scope="row">{@code DeliveryCityID}</th>
 *     <td>ID of the delivery city for this address</td></tr>
 * <tr><th scope="row">{@code PostalCityID}</th><td>ID of the postal city for this address</td></tr>
 * <tr><th scope="row">{@code CreditLimit}</th>
 *     <td>Credit limit for this customer (NULL if unlimited)</td></tr>
 * <tr><th scope="row">{@code AccountOpenedDate}</th>
 *     <td>Date this customer account was opened</td></tr>
 * <tr><th scope="row">{@code StandardDiscountPercentage}</th>
 *     <td>Standard discount offered to this customer</td></tr>
 * <tr><th scope="row">{@code IsStatementSent}</th>
 *     <td>Is a statement sent to this customer? (Or do they just pay on each invoice?)</td></tr>
 * <tr><th scope="row">{@code IsOnCreditHold}</th>
 *     <td>Is this customer on credit hold? (Prevents further deliveries to this customer)</td></tr>
 * <tr><th scope="row">{@code PaymentDays}</th>
 *     <td>Number of days for payment of an invoice (ie payment terms)</td></tr>
 * <tr><th scope="row">{@code PhoneNumber}</th><td>Phone number</td></tr>
 * <tr><th scope="row">{@code FaxNumber}</th><td>Fax number</td></tr>
 * <tr><th scope="row">{@code DeliveryRun}</th><td>Normal delivery run for this customer</td></tr>
 * <tr><th scope="row">{@code RunPosition}</th>
 *     <td>Normal position in the delivery run for this customer</td></tr>
 * <tr><th scope="row">{@code WebsiteURL}</th><td>URL for the website for this customer</td></tr>
 * <tr><th scope="row">{@code DeliveryAddressLine1}</th>
 *     <td>First delivery address line for the customer</td></tr>
 * <tr><th scope="row">{@code DeliveryAddressLine2}</th>
 *     <td>Second delivery address line for the customer</td></tr>
 * <tr><th scope="row">{@code DeliveryPostalCode}</th>
 *     <td>Delivery postal code for the customer</td></tr>
 * <tr><th scope="row">{@code DeliveryLocation}</th>
 *     <td>Geographic location for the customer's office/warehouse</td></tr>
 * <tr><th scope="row">{@code PostalAddressLine1}</th>
 *     <td>First postal address line for the customer</td></tr>
 * <tr><th scope="row">{@code PostalAddressLine2}</th>
 *     <td>Second postal address line for the customer</td></tr>
 * <tr><th scope="row">{@code PostalPostalCode}</th>
 *     <td>Postal code for the customer when sending by mail</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.Customers}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CustomerID}</th><td>{@code (NEXT VALUE FOR [Sequences].[CustomerID])}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Security predicates on {@code Sales.Customers}</caption>
 * <thead>
 * <tr><th scope="col">Policy</th><th scope="col">Type</th><th scope="col">Predicate</th>
 *     <th scope="col">Operation</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code Application.FilterCustomersBySalesTerritoryRole}</th>
 *     <td>{@code FILTER}</td><td>{@code ([Application].[DetermineCustomerAccess]([DeliveryCityID]))}</td><td></td></tr>
 * <tr><th scope="row">{@code Application.FilterCustomersBySalesTerritoryRole}</th>
 *     <td>{@code BLOCK}</td>
 *     <td>{@code ([Application].[DetermineCustomerAccess]([DeliveryCityID]))}</td>
 *     <td>{@code AFTER UPDATE}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedCustomer.TABLE_SCHEMA,
        table = MappedCustomer.TABLE_NAME,
        value = "Main entity tables for customers (organizations or individuals)"
)
@MsTemporal(
        historyTable = "Sales.Customers_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
@MsRowLevelSecurity(
        policy = "Application.FilterCustomersBySalesTerritoryRole",
        predicate = "([Application].[DetermineCustomerAccess]([DeliveryCityID]))"
)
@MsRowLevelSecurity(
        policy = "Application.FilterCustomersBySalesTerritoryRole",
        type = MsRowLevelSecurity.PredicateType.BLOCK,
        predicate = "([Application].[DetermineCustomerAccess]([DeliveryCityID]))",
        operation = MsRowLevelSecurity.BlockOperation.AFTER_UPDATE
)
public abstract class MappedCustomer {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Customers";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.CustomerID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // -------------------------------------------------------------------------------- CustomerName

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

    // ---------------------------------------------------------------------------- BillToCustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BILL_TO_CUSTOMER_ID = "BillToCustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID = "billToCustomerId";

    // -------------------------------------------------------------------------- CustomerCategoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CATEGORY_ID = "CustomerCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID = "customerCategoryId";

    // ------------------------------------------------------------------------------- BuyingGroupID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUYING_GROUP_ID = "BuyingGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUYING_GROUP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP_ID = "buyingGroupId";

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
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID = "AlternateContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID = "alternateContactPersonId";

    // ---------------------------------------------------------------------------- DeliveryMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
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

    // --------------------------------------------------------------------------------- CreditLimit

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREDIT_LIMIT = "CreditLimit";

    /**
     * The precision of the {@value #COLUMN_NAME_CREDIT_LIMIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CREDIT_LIMIT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_CREDIT_LIMIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CREDIT_LIMIT = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_CREDIT_LIMIT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_CREDIT_LIMIT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_LIMIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_LIMIT = "creditLimit";

    // --------------------------------------------------------------------------- AccountOpenedDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCOUNT_OPENED_DATE = "AccountOpenedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCOUNT_OPENED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE = "accountOpenedDate";

    // ------------------------------------------------------------------ StandardDiscountPercentage

    /**
     * The name of the {@code decimal(18, 3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE = "StandardDiscountPercentage";

    /**
     * The precision of the {@value #COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_STANDARD_DISCOUNT_PERCENTAGE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_STANDARD_DISCOUNT_PERCENTAGE = 3;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute, the minimum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_STANDARD_DISCOUNT_PERCENTAGE = "-999999999999999.999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute, the maximum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_STANDARD_DISCOUNT_PERCENTAGE = "999999999999999.999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE = "standardDiscountPercentage";

    // ----------------------------------------------------------------------------- IsStatementSent

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_STATEMENT_SENT = "IsStatementSent";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_STATEMENT_SENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_STATEMENT_SENT = "isStatementSent";

    // ------------------------------------------------------------------------------ IsOnCreditHold

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ON_CREDIT_HOLD = "IsOnCreditHold";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ON_CREDIT_HOLD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD = "isOnCreditHold";

    // --------------------------------------------------------------------------------- PaymentDays

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_DAYS = "PaymentDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_DAYS = "paymentDays";

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

    // --------------------------------------------------------------------------------- DeliveryRun

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

    // --------------------------------------------------------------------------------- RunPosition

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
    protected MappedCustomer() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerId=" + customerId
               + ",customerName=" + customerName
               + ",billToCustomerId=" + billToCustomerId
               + ",customerCategoryId=" + customerCategoryId
               + ",buyingGroupId=" + buyingGroupId
               + ",primaryContactPersonId=" + primaryContactPersonId
               + ",alternateContactPersonId=" + alternateContactPersonId
               + ",deliveryMethodId=" + deliveryMethodId
               + ",deliveryCityId=" + deliveryCityId
               + ",postalCityId=" + postalCityId
               + ",creditLimit=" + creditLimit
               + ",accountOpenedDate=" + accountOpenedDate
               + ",standardDiscountPercentage=" + standardDiscountPercentage
               + ",isStatementSent=" + isStatementSent
               + ",isOnCreditHold=" + isOnCreditHold
               + ",paymentDays=" + paymentDays
               + ",phoneNumber=" + phoneNumber
               + ",faxNumber=" + faxNumber
               + ",deliveryRun=" + deliveryRun
               + ",runPosition=" + runPosition
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
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return Objects.equals(getCustomerId(), that.getCustomerId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getCustomerId());
    }

    // ---------------------------------------------------------------------------------- customerId

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

    // -------------------------------------------------------------------------------- customerName

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
    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    // ---------------------------------------------------------------------------- billToCustomerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     */
    protected Integer getBillToCustomerId() {
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

    // -------------------------------------------------------------------------- customerCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    protected Integer getCustomerCategoryId() {
        return customerCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param customerCategoryId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    protected void setCustomerCategoryId(final Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    // ------------------------------------------------------------------------------- buyingGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    protected Integer getBuyingGroupId() {
        return buyingGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param buyingGroupId new value for {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    protected void setBuyingGroupId(final Integer buyingGroupId) {
        this.buyingGroupId = buyingGroupId;
    }

    // ---------------------------------------------------------------------- primaryContactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     */
    protected Integer getPrimaryContactPersonId() {
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
    protected Integer getAlternateContactPersonId() {
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
    protected Integer getDeliveryMethodId() {
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
    protected Integer getDeliveryCityId() {
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
    protected Integer getPostalCityId() {
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

    // --------------------------------------------------------------------------------- creditLimit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute.
     */
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute with the specified value.
     *
     * @param creditLimit new value for {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute.
     */
    public void setCreditLimit(final BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    // --------------------------------------------------------------------------- accountOpenedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute.
     */
    public LocalDate getAccountOpenedDate() {
        return accountOpenedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute with the specified value.
     *
     * @param accountOpenedDate new value for {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute.
     */
    public void setAccountOpenedDate(final LocalDate accountOpenedDate) {
        this.accountOpenedDate = accountOpenedDate;
    }

    // ------------------------------------------------------------------ standardDiscountPercentage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute.
     */
    public BigDecimal getStandardDiscountPercentage() {
        return standardDiscountPercentage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute with the specified
     * value.
     *
     * @param standardDiscountPercentage new value for {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute.
     */
    public void setStandardDiscountPercentage(final BigDecimal standardDiscountPercentage) {
        this.standardDiscountPercentage = standardDiscountPercentage;
    }

    // ----------------------------------------------------------------------------- isStatementSent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute.
     */
    public Boolean getIsStatementSent() {
        return isStatementSent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute with the specified value.
     *
     * @param isStatementSent new value for {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute.
     */
    public void setIsStatementSent(final Boolean isStatementSent) {
        this.isStatementSent = isStatementSent;
    }

    // ------------------------------------------------------------------------------ isOnCreditHold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute.
     */
    public Boolean getIsOnCreditHold() {
        return isOnCreditHold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute with the specified value.
     *
     * @param isOnCreditHold new value for {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute.
     */
    public void setIsOnCreditHold(final Boolean isOnCreditHold) {
        this.isOnCreditHold = isOnCreditHold;
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

    // --------------------------------------------------------------------------------- deliveryRun

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

    // --------------------------------------------------------------------------------- runPosition

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
    protected Integer getLastEditedBy() {
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
    @SequenceGenerator(name = "WideWorldImporters.Sequences.CustomerID", schema = "Sequences",
                       sequenceName = "CustomerID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.CustomerID")
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_ID,
                          value = "Numeric ID used for reference to a customer within the database")
    @MsDefault(name = "DF_Sales_Customers_CustomerID", value = "(NEXT VALUE FOR [Sequences].[CustomerID])")
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_CUSTOMER_NAME)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_NAME,
                          value = "Customer's full name (usually a trading name)")
    private String customerName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BILL_TO_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BILL_TO_CUSTOMER_ID,
                          value = "Customer that this is billed to (usually the same customer "
                                  + "but can be another parent company)")
    private Integer billToCustomerId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID, value = "Customer's category")
    private Integer customerCategoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BUYING_GROUP_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUYING_GROUP_ID,
                          value = "Customer's buying group (optional)")
    private Integer buyingGroupId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID, value = "Primary contact")
    private Integer primaryContactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID,
                          value = "Alternate contact")
    private Integer alternateContactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_METHOD_ID,
                          value = "Standard delivery method for stock items sent to this customer")
    private Integer deliveryMethodId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_CITY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_CITY_ID,
                          value = "ID of the delivery city for this address")
    private Integer deliveryCityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_CITY_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_CITY_ID,
                          value = "ID of the postal city for this address")
    private Integer postalCityId;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_CREDIT_LIMIT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_CREDIT_LIMIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREDIT_LIMIT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_CREDIT_LIMIT, scale = COLUMN_SCALE_CREDIT_LIMIT)
    @MsDescription.Column(name = COLUMN_NAME_CREDIT_LIMIT,
                          value = "Credit limit for this customer (NULL if unlimited)")
    private BigDecimal creditLimit;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACCOUNT_OPENED_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_ACCOUNT_OPENED_DATE,
                          value = "Date this customer account was opened")
    private LocalDate accountOpenedDate;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_STANDARD_DISCOUNT_PERCENTAGE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_STANDARD_DISCOUNT_PERCENTAGE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_STANDARD_DISCOUNT_PERCENTAGE, scale = COLUMN_SCALE_STANDARD_DISCOUNT_PERCENTAGE)
    @MsDescription.Column(name = COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE,
                          value = "Standard discount offered to this customer")
    private BigDecimal standardDiscountPercentage;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_STATEMENT_SENT, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_IS_STATEMENT_SENT,
                          value = "Is a statement sent to this customer? (Or do they just pay "
                                  + "on each invoice?)")
    private Boolean isStatementSent;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ON_CREDIT_HOLD, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_IS_ON_CREDIT_HOLD,
                          value = "Is this customer on credit hold? (Prevents further "
                                  + "deliveries to this customer)")
    private Boolean isOnCreditHold;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAYMENT_DAYS, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_PAYMENT_DAYS,
                          value = "Number of days for payment of an invoice (ie payment terms)")
    private Integer paymentDays;

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
    @Size(max = COLUMN_LENGTH_DELIVERY_RUN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_RUN, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_RUN)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_RUN,
                          value = "Normal delivery run for this customer")
    private String deliveryRun;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_RUN_POSITION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RUN_POSITION, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_RUN_POSITION)
    @MsDescription.Column(name = COLUMN_NAME_RUN_POSITION,
                          value = "Normal position in the delivery run for this customer")
    private String runPosition;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WEBSITE_URL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WEBSITE_URL, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_WEBSITE_URL)
    @MsDescription.Column(name = COLUMN_NAME_WEBSITE_URL,
                          value = "URL for the website for this customer")
    private String websiteUrl;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_1, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_1,
                          value = "First delivery address line for the customer")
    private String deliveryAddressLine1;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_2, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_2,
                          value = "Second delivery address line for the customer")
    private String deliveryAddressLine2;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DELIVERY_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_POSTAL_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DELIVERY_POSTAL_CODE)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_POSTAL_CODE,
                          value = "Delivery postal code for the customer")
    private String deliveryPostalCode;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = GeographyAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_LOCATION, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_LOCATION,
                          value = "Geographic location for the customer's office/warehouse")
    private Geography deliveryLocation;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_1, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_1,
                          value = "First postal address line for the customer")
    private String postalAddressLine1;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_2, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_2,
                          value = "Second postal address line for the customer")
    private String postalAddressLine2;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_POSTAL_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_POSTAL_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_POSTAL_POSTAL_CODE)
    @MsDescription.Column(name = COLUMN_NAME_POSTAL_POSTAL_CODE,
                          value = "Postal code for the customer when sending by mail")
    private String postalPostalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
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

