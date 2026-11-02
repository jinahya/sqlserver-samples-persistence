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

import com.github.jinahya.sqlserver.sample.persistence.common.Geography;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomerArchive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A plain class which holds a row of the {@code Sales.Customers_Archive} table, which no entity can map: a history
 * table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class CustomerArchive extends MappedCustomerArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected CustomerArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param customerId                 the value of the {@value #COLUMN_NAME_CUSTOMER_ID} column.
     * @param customerName               the value of the {@value #COLUMN_NAME_CUSTOMER_NAME} column.
     * @param billToCustomerId           the value of the {@value #COLUMN_NAME_BILL_TO_CUSTOMER_ID} column.
     * @param customerCategoryId         the value of the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID} column.
     * @param buyingGroupId              the value of the {@value #COLUMN_NAME_BUYING_GROUP_ID} column.
     * @param primaryContactPersonId     the value of the {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID} column.
     * @param alternateContactPersonId   the value of the {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID} column.
     * @param deliveryMethodId           the value of the {@value #COLUMN_NAME_DELIVERY_METHOD_ID} column.
     * @param deliveryCityId             the value of the {@value #COLUMN_NAME_DELIVERY_CITY_ID} column.
     * @param postalCityId               the value of the {@value #COLUMN_NAME_POSTAL_CITY_ID} column.
     * @param creditLimit                the value of the {@value #COLUMN_NAME_CREDIT_LIMIT} column.
     * @param accountOpenedDate          the value of the {@value #COLUMN_NAME_ACCOUNT_OPENED_DATE} column.
     * @param standardDiscountPercentage the value of the {@value #COLUMN_NAME_STANDARD_DISCOUNT_PERCENTAGE} column.
     * @param isStatementSent            the value of the {@value #COLUMN_NAME_IS_STATEMENT_SENT} column.
     * @param isOnCreditHold             the value of the {@value #COLUMN_NAME_IS_ON_CREDIT_HOLD} column.
     * @param paymentDays                the value of the {@value #COLUMN_NAME_PAYMENT_DAYS} column.
     * @param phoneNumber                the value of the {@value #COLUMN_NAME_PHONE_NUMBER} column.
     * @param faxNumber                  the value of the {@value #COLUMN_NAME_FAX_NUMBER} column.
     * @param deliveryRun                the value of the {@value #COLUMN_NAME_DELIVERY_RUN} column.
     * @param runPosition                the value of the {@value #COLUMN_NAME_RUN_POSITION} column.
     * @param websiteUrl                 the value of the {@value #COLUMN_NAME_WEBSITE_URL} column.
     * @param deliveryAddressLine1       the value of the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_1} column.
     * @param deliveryAddressLine2       the value of the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_2} column.
     * @param deliveryPostalCode         the value of the {@value #COLUMN_NAME_DELIVERY_POSTAL_CODE} column.
     * @param deliveryLocation           the value of the {@value #COLUMN_NAME_DELIVERY_LOCATION} column, in its binary form.
     * @param postalAddressLine1         the value of the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_1} column.
     * @param postalAddressLine2         the value of the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_2} column.
     * @param postalPostalCode           the value of the {@value #COLUMN_NAME_POSTAL_POSTAL_CODE} column.
     * @param lastEditedBy               the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom                  the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo                    the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public CustomerArchive(final Integer customerId,
                           final String customerName,
                           final Integer billToCustomerId,
                           final Integer customerCategoryId,
                           final Integer buyingGroupId,
                           final Integer primaryContactPersonId,
                           final Integer alternateContactPersonId,
                           final Integer deliveryMethodId,
                           final Integer deliveryCityId,
                           final Integer postalCityId,
                           final BigDecimal creditLimit,
                           final LocalDate accountOpenedDate,
                           final BigDecimal standardDiscountPercentage,
                           final Boolean isStatementSent,
                           final Boolean isOnCreditHold,
                           final Integer paymentDays,
                           final String phoneNumber,
                           final String faxNumber,
                           final String deliveryRun,
                           final String runPosition,
                           final String websiteUrl,
                           final String deliveryAddressLine1,
                           final String deliveryAddressLine2,
                           final String deliveryPostalCode,
                           final byte[] deliveryLocation,
                           final String postalAddressLine1,
                           final String postalAddressLine2,
                           final String postalPostalCode,
                           final Integer lastEditedBy,
                           final LocalDateTime validFrom,
                           final LocalDateTime validTo) {
        super();
        setCustomerId(customerId);
        setCustomerName(customerName);
        setBillToCustomerId(billToCustomerId);
        setCustomerCategoryId(customerCategoryId);
        setBuyingGroupId(buyingGroupId);
        setPrimaryContactPersonId(primaryContactPersonId);
        setAlternateContactPersonId(alternateContactPersonId);
        setDeliveryMethodId(deliveryMethodId);
        setDeliveryCityId(deliveryCityId);
        setPostalCityId(postalCityId);
        setCreditLimit(creditLimit);
        setAccountOpenedDate(accountOpenedDate);
        setStandardDiscountPercentage(standardDiscountPercentage);
        setIsStatementSent(isStatementSent);
        setIsOnCreditHold(isOnCreditHold);
        setPaymentDays(paymentDays);
        setPhoneNumber(phoneNumber);
        setFaxNumber(faxNumber);
        setDeliveryRun(deliveryRun);
        setRunPosition(runPosition);
        setWebsiteUrl(websiteUrl);
        setDeliveryAddressLine1(deliveryAddressLine1);
        setDeliveryAddressLine2(deliveryAddressLine2);
        setDeliveryPostalCode(deliveryPostalCode);
        setDeliveryLocation(Optional.ofNullable(deliveryLocation).map(Geography::decode).orElse(null));
        setPostalAddressLine1(postalAddressLine1);
        setPostalAddressLine2(postalAddressLine2);
        setPostalPostalCode(postalPostalCode);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

