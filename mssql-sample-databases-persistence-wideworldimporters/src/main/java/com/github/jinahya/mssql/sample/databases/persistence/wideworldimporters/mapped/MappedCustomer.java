package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.Customers} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and
 * keeps every former version of a row in {@code Sales.Customers_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID}, {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID}, {@value
 * #COLUMN_NAME_BUYING_GROUP_ID}, {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID}, {@value
 * #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID}, {@value #COLUMN_NAME_DELIVERY_METHOD_ID}, {@value
 * #COLUMN_NAME_DELIVERY_CITY_ID}, {@value #COLUMN_NAME_POSTAL_CITY_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns
 * are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedCustomer {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Customers";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.CustomerID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

    // ------------------------------------------------------------------------------------------------ BillToCustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BILL_TO_CUSTOMER_ID = "BillToCustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID = "billToCustomerId";

    // ---------------------------------------------------------------------------------------------- CustomerCategoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CATEGORY_ID = "CustomerCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID = "customerCategoryId";

    // --------------------------------------------------------------------------------------------------- BuyingGroupID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUYING_GROUP_ID = "BuyingGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUYING_GROUP_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP_ID = "buyingGroupId";

    // ------------------------------------------------------------------------------------------ PrimaryContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID = "PrimaryContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID = "primaryContactPersonId";

    // ---------------------------------------------------------------------------------------- AlternateContactPersonID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID = "AlternateContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID = "alternateContactPersonId";

    // ------------------------------------------------------------------------------------------------ DeliveryMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_METHOD_ID = "DeliveryMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_METHOD_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD_ID = "deliveryMethodId";

    // -------------------------------------------------------------------------------------------------- DeliveryCityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_CITY_ID = "DeliveryCityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_CITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_CITY_ID = "deliveryCityId";

    // ---------------------------------------------------------------------------------------------------- PostalCityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_CITY_ID = "PostalCityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_CITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CITY_ID = "postalCityId";

    // ----------------------------------------------------------------------------------------------------- CreditLimit

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_LIMIT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_LIMIT = "creditLimit";

    // ----------------------------------------------------------------------------------------------- AccountOpenedDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCOUNT_OPENED_DATE = "AccountOpenedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCOUNT_OPENED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE = "accountOpenedDate";

    // -------------------------------------------------------------------------------------- StandardDiscountPercentage

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE = "standardDiscountPercentage";

    // ------------------------------------------------------------------------------------------------- IsStatementSent

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_STATEMENT_SENT = "IsStatementSent";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_STATEMENT_SENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_STATEMENT_SENT = "isStatementSent";

    // -------------------------------------------------------------------------------------------------- IsOnCreditHold

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ON_CREDIT_HOLD = "IsOnCreditHold";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ON_CREDIT_HOLD} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD = "isOnCreditHold";

    // ----------------------------------------------------------------------------------------------------- PaymentDays

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_DAYS = "PaymentDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_DAYS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_DAYS = "paymentDays";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_FAX_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FAX_NUMBER = "faxNumber";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_RUN} column. The value is
     * {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_RUN_POSITION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RUN_POSITION = "runPosition";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEBSITE_URL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEBSITE_URL = "websiteUrl";

    // -------------------------------------------------------------------------------------------- DeliveryAddressLine1

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

    // -------------------------------------------------------------------------------------------- DeliveryAddressLine2

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

    // ---------------------------------------------------------------------------------------------- DeliveryPostalCode

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

    // ------------------------------------------------------------------------------------------------ DeliveryLocation

    /**
     * The name of the {@code geography} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_LOCATION = "DeliveryLocation";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_LOCATION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_LOCATION = "deliveryLocation";

    // ---------------------------------------------------------------------------------------------- PostalAddressLine1

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

    // ---------------------------------------------------------------------------------------------- PostalAddressLine2

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

    // ------------------------------------------------------------------------------------------------ PostalPostalCode

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_POSTAL_CODE = "PostalPostalCode";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_POSTAL_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_POSTAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_POSTAL_CODE = "postalPostalCode";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

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
               + ",deliveryLocation=" + (this.deliveryLocation == null ? null : this.deliveryLocation.length + " bytes")
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
    @Nullable
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(@Nullable final Integer customerId) {
        this.customerId = customerId;
    }

    // ---------------------------------------------------------------------------------------------------- customerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    @Nonnull
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute with the specified value.
     *
     * @param customerName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    public void setCustomerName(@Nonnull final String customerName) {
        this.customerName = customerName;
    }

    // ------------------------------------------------------------------------------------------------ billToCustomerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     */
    @Nonnull
    public Integer getBillToCustomerId() {
        return billToCustomerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param billToCustomerId new value for {@value #ATTRIBUTE_NAME_BILL_TO_CUSTOMER_ID} attribute.
     */
    protected void setBillToCustomerId(@Nonnull final Integer billToCustomerId) {
        this.billToCustomerId = billToCustomerId;
    }

    // ---------------------------------------------------------------------------------------------- customerCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    @Nonnull
    public Integer getCustomerCategoryId() {
        return customerCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param customerCategoryId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    protected void setCustomerCategoryId(@Nonnull final Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    // --------------------------------------------------------------------------------------------------- buyingGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    @Nullable
    public Integer getBuyingGroupId() {
        return buyingGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param buyingGroupId new value for {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    protected void setBuyingGroupId(@Nullable final Integer buyingGroupId) {
        this.buyingGroupId = buyingGroupId;
    }

    // ------------------------------------------------------------------------------------------ primaryContactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIMARY_CONTACT_PERSON_ID} attribute.
     */
    @Nonnull
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
    protected void setPrimaryContactPersonId(@Nonnull final Integer primaryContactPersonId) {
        this.primaryContactPersonId = primaryContactPersonId;
    }

    // ---------------------------------------------------------------------------------------- alternateContactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ALTERNATE_CONTACT_PERSON_ID} attribute.
     */
    @Nullable
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
    protected void setAlternateContactPersonId(@Nullable final Integer alternateContactPersonId) {
        this.alternateContactPersonId = alternateContactPersonId;
    }

    // ------------------------------------------------------------------------------------------------ deliveryMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    @Nonnull
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
    protected void setDeliveryMethodId(@Nonnull final Integer deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
    }

    // -------------------------------------------------------------------------------------------------- deliveryCityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_CITY_ID} attribute.
     */
    @Nonnull
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
    protected void setDeliveryCityId(@Nonnull final Integer deliveryCityId) {
        this.deliveryCityId = deliveryCityId;
    }

    // ---------------------------------------------------------------------------------------------------- postalCityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_CITY_ID} attribute.
     */
    @Nonnull
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
    protected void setPostalCityId(@Nonnull final Integer postalCityId) {
        this.postalCityId = postalCityId;
    }

    // ----------------------------------------------------------------------------------------------------- creditLimit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute.
     */
    @Nullable
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute with the specified value.
     *
     * @param creditLimit new value for {@value #ATTRIBUTE_NAME_CREDIT_LIMIT} attribute.
     */
    public void setCreditLimit(@Nullable final BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    // ----------------------------------------------------------------------------------------------- accountOpenedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute.
     */
    @Nonnull
    public LocalDate getAccountOpenedDate() {
        return accountOpenedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute with the specified value.
     *
     * @param accountOpenedDate new value for {@value #ATTRIBUTE_NAME_ACCOUNT_OPENED_DATE} attribute.
     */
    public void setAccountOpenedDate(@Nonnull final LocalDate accountOpenedDate) {
        this.accountOpenedDate = accountOpenedDate;
    }

    // -------------------------------------------------------------------------------------- standardDiscountPercentage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute.
     */
    @Nonnull
    public BigDecimal getStandardDiscountPercentage() {
        return standardDiscountPercentage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute with the specified
     * value.
     *
     * @param standardDiscountPercentage new value for {@value #ATTRIBUTE_NAME_STANDARD_DISCOUNT_PERCENTAGE} attribute.
     */
    public void setStandardDiscountPercentage(@Nonnull final BigDecimal standardDiscountPercentage) {
        this.standardDiscountPercentage = standardDiscountPercentage;
    }

    // ------------------------------------------------------------------------------------------------- isStatementSent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute.
     */
    @Nonnull
    public Boolean getIsStatementSent() {
        return isStatementSent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute with the specified value.
     *
     * @param isStatementSent new value for {@value #ATTRIBUTE_NAME_IS_STATEMENT_SENT} attribute.
     */
    public void setIsStatementSent(@Nonnull final Boolean isStatementSent) {
        this.isStatementSent = isStatementSent;
    }

    // -------------------------------------------------------------------------------------------------- isOnCreditHold

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute.
     */
    @Nonnull
    public Boolean getIsOnCreditHold() {
        return isOnCreditHold;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute with the specified value.
     *
     * @param isOnCreditHold new value for {@value #ATTRIBUTE_NAME_IS_ON_CREDIT_HOLD} attribute.
     */
    public void setIsOnCreditHold(@Nonnull final Boolean isOnCreditHold) {
        this.isOnCreditHold = isOnCreditHold;
    }

    // ----------------------------------------------------------------------------------------------------- paymentDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute.
     */
    @Nonnull
    public Integer getPaymentDays() {
        return paymentDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute with the specified value.
     *
     * @param paymentDays new value for {@value #ATTRIBUTE_NAME_PAYMENT_DAYS} attribute.
     */
    public void setPaymentDays(@Nonnull final Integer paymentDays) {
        this.paymentDays = paymentDays;
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    @Nonnull
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute with the specified value.
     *
     * @param phoneNumber new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    public void setPhoneNumber(@Nonnull final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ------------------------------------------------------------------------------------------------------- faxNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute.
     */
    @Nonnull
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute with the specified value.
     *
     * @param faxNumber new value for {@value #ATTRIBUTE_NAME_FAX_NUMBER} attribute.
     */
    public void setFaxNumber(@Nonnull final String faxNumber) {
        this.faxNumber = faxNumber;
    }

    // ----------------------------------------------------------------------------------------------------- deliveryRun

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute.
     */
    @Nullable
    public String getDeliveryRun() {
        return deliveryRun;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute with the specified value.
     *
     * @param deliveryRun new value for {@value #ATTRIBUTE_NAME_DELIVERY_RUN} attribute.
     */
    public void setDeliveryRun(@Nullable final String deliveryRun) {
        this.deliveryRun = deliveryRun;
    }

    // ----------------------------------------------------------------------------------------------------- runPosition

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute.
     */
    @Nullable
    public String getRunPosition() {
        return runPosition;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute with the specified value.
     *
     * @param runPosition new value for {@value #ATTRIBUTE_NAME_RUN_POSITION} attribute.
     */
    public void setRunPosition(@Nullable final String runPosition) {
        this.runPosition = runPosition;
    }

    // ------------------------------------------------------------------------------------------------------ websiteUrl

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute.
     */
    @Nonnull
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute with the specified value.
     *
     * @param websiteUrl new value for {@value #ATTRIBUTE_NAME_WEBSITE_URL} attribute.
     */
    public void setWebsiteUrl(@Nonnull final String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    // -------------------------------------------------------------------------------------------- deliveryAddressLine1

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute.
     */
    @Nonnull
    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute with the specified value.
     *
     * @param deliveryAddressLine1 new value for {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_1} attribute.
     */
    public void setDeliveryAddressLine1(@Nonnull final String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    // -------------------------------------------------------------------------------------------- deliveryAddressLine2

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute.
     */
    @Nullable
    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute with the specified value.
     *
     * @param deliveryAddressLine2 new value for {@value #ATTRIBUTE_NAME_DELIVERY_ADDRESS_LINE_2} attribute.
     */
    public void setDeliveryAddressLine2(@Nullable final String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    // ---------------------------------------------------------------------------------------------- deliveryPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute.
     */
    @Nonnull
    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute with the specified value.
     *
     * @param deliveryPostalCode new value for {@value #ATTRIBUTE_NAME_DELIVERY_POSTAL_CODE} attribute.
     */
    public void setDeliveryPostalCode(@Nonnull final String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    // ------------------------------------------------------------------------------------------------ deliveryLocation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     */
    @Nullable
    public byte[] getDeliveryLocation() {
        return deliveryLocation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute with the specified value.
     *
     * @param deliveryLocation new value for {@value #ATTRIBUTE_NAME_DELIVERY_LOCATION} attribute.
     */
    public void setDeliveryLocation(@Nullable final byte[] deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    // ---------------------------------------------------------------------------------------------- postalAddressLine1

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute.
     */
    @Nonnull
    public String getPostalAddressLine1() {
        return postalAddressLine1;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute with the specified value.
     *
     * @param postalAddressLine1 new value for {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_1} attribute.
     */
    public void setPostalAddressLine1(@Nonnull final String postalAddressLine1) {
        this.postalAddressLine1 = postalAddressLine1;
    }

    // ---------------------------------------------------------------------------------------------- postalAddressLine2

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute.
     */
    @Nullable
    public String getPostalAddressLine2() {
        return postalAddressLine2;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute with the specified value.
     *
     * @param postalAddressLine2 new value for {@value #ATTRIBUTE_NAME_POSTAL_ADDRESS_LINE_2} attribute.
     */
    public void setPostalAddressLine2(@Nullable final String postalAddressLine2) {
        this.postalAddressLine2 = postalAddressLine2;
    }

    // ------------------------------------------------------------------------------------------------ postalPostalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute.
     */
    @Nonnull
    public String getPostalPostalCode() {
        return postalPostalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute with the specified value.
     *
     * @param postalPostalCode new value for {@value #ATTRIBUTE_NAME_POSTAL_POSTAL_CODE} attribute.
     */
    public void setPostalPostalCode(@Nonnull final String postalPostalCode) {
        this.postalPostalCode = postalPostalCode;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    @Nonnull
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
    protected void setLastEditedBy(@Nonnull final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    @Nonnull
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(@Nonnull final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // --------------------------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    @Nonnull
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(@Nonnull final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.CustomerID", schema = "Sequences", sequenceName = "CustomerID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.CustomerID")
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_CUSTOMER_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_NAME
    )
    private String customerName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_BILL_TO_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer billToCustomerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer customerCategoryId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_BUYING_GROUP_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer buyingGroupId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer primaryContactPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer alternateContactPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_DELIVERY_METHOD_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer deliveryMethodId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_DELIVERY_CITY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer deliveryCityId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_POSTAL_CITY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer postalCityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_CREDIT_LIMIT - COLUMN_SCALE_CREDIT_LIMIT, fraction = COLUMN_SCALE_CREDIT_LIMIT)
    @Column(name = COLUMN_NAME_CREDIT_LIMIT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_CREDIT_LIMIT,
            scale = COLUMN_SCALE_CREDIT_LIMIT
    )
    private BigDecimal creditLimit;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ACCOUNT_OPENED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate accountOpenedDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_STANDARD_DISCOUNT_PERCENTAGE - COLUMN_SCALE_STANDARD_DISCOUNT_PERCENTAGE, fraction = COLUMN_SCALE_STANDARD_DISCOUNT_PERCENTAGE)
    @NotNull
    @Column(name = COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_STANDARD_DISCOUNT_PERCENTAGE,
            scale = COLUMN_SCALE_STANDARD_DISCOUNT_PERCENTAGE
    )
    private BigDecimal standardDiscountPercentage;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_IS_STATEMENT_SENT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isStatementSent;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_IS_ON_CREDIT_HOLD,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isOnCreditHold;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PAYMENT_DAYS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer paymentDays;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @NotNull
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_FAX_NUMBER)
    @NotNull
    @Column(name = COLUMN_NAME_FAX_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FAX_NUMBER
    )
    private String faxNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_DELIVERY_RUN)
    @Column(name = COLUMN_NAME_DELIVERY_RUN,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_RUN
    )
    private String deliveryRun;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_RUN_POSITION)
    @Column(name = COLUMN_NAME_RUN_POSITION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_RUN_POSITION
    )
    private String runPosition;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_WEBSITE_URL)
    @NotNull
    @Column(name = COLUMN_NAME_WEBSITE_URL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_WEBSITE_URL
    )
    private String websiteUrl;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1)
    @NotNull
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_1,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_1
    )
    private String deliveryAddressLine1;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2)
    @Column(name = COLUMN_NAME_DELIVERY_ADDRESS_LINE_2,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_ADDRESS_LINE_2
    )
    private String deliveryAddressLine2;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_DELIVERY_POSTAL_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_DELIVERY_POSTAL_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DELIVERY_POSTAL_CODE
    )
    private String deliveryPostalCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DELIVERY_LOCATION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] deliveryLocation;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1)
    @NotNull
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_1,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_1
    )
    private String postalAddressLine1;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2)
    @Column(name = COLUMN_NAME_POSTAL_ADDRESS_LINE_2,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_POSTAL_ADDRESS_LINE_2
    )
    private String postalAddressLine2;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_POSTAL_POSTAL_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_POSTAL_POSTAL_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_POSTAL_POSTAL_CODE
    )
    private String postalPostalCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validTo;
}

