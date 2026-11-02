package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.purchasing;

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

import com.github.jinahya.mssql.sample.databases.persistence.common.Geography;
import com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.purchasing.MappedSupplierArchive;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A plain class which holds a row of the {@code Purchasing.Suppliers_Archive} table, which no entity can map: a history
 * table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SupplierArchive extends MappedSupplierArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SupplierArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param supplierId               the value of the {@value #COLUMN_NAME_SUPPLIER_ID} column.
     * @param supplierName             the value of the {@value #COLUMN_NAME_SUPPLIER_NAME} column.
     * @param supplierCategoryId       the value of the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_ID} column.
     * @param primaryContactPersonId   the value of the {@value #COLUMN_NAME_PRIMARY_CONTACT_PERSON_ID} column.
     * @param alternateContactPersonId the value of the {@value #COLUMN_NAME_ALTERNATE_CONTACT_PERSON_ID} column.
     * @param deliveryMethodId         the value of the {@value #COLUMN_NAME_DELIVERY_METHOD_ID} column.
     * @param deliveryCityId           the value of the {@value #COLUMN_NAME_DELIVERY_CITY_ID} column.
     * @param postalCityId             the value of the {@value #COLUMN_NAME_POSTAL_CITY_ID} column.
     * @param supplierReference        the value of the {@value #COLUMN_NAME_SUPPLIER_REFERENCE} column.
     * @param bankAccountName          the value of the {@value #COLUMN_NAME_BANK_ACCOUNT_NAME} column.
     * @param bankAccountBranch        the value of the {@value #COLUMN_NAME_BANK_ACCOUNT_BRANCH} column.
     * @param bankAccountCode          the value of the {@value #COLUMN_NAME_BANK_ACCOUNT_CODE} column.
     * @param bankAccountNumber        the value of the {@value #COLUMN_NAME_BANK_ACCOUNT_NUMBER} column.
     * @param bankInternationalCode    the value of the {@value #COLUMN_NAME_BANK_INTERNATIONAL_CODE} column.
     * @param paymentDays              the value of the {@value #COLUMN_NAME_PAYMENT_DAYS} column.
     * @param internalComments         the value of the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column.
     * @param phoneNumber              the value of the {@value #COLUMN_NAME_PHONE_NUMBER} column.
     * @param faxNumber                the value of the {@value #COLUMN_NAME_FAX_NUMBER} column.
     * @param websiteUrl               the value of the {@value #COLUMN_NAME_WEBSITE_URL} column.
     * @param deliveryAddressLine1     the value of the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_1} column.
     * @param deliveryAddressLine2     the value of the {@value #COLUMN_NAME_DELIVERY_ADDRESS_LINE_2} column.
     * @param deliveryPostalCode       the value of the {@value #COLUMN_NAME_DELIVERY_POSTAL_CODE} column.
     * @param deliveryLocation         the value of the {@value #COLUMN_NAME_DELIVERY_LOCATION} column, in its binary form.
     * @param postalAddressLine1       the value of the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_1} column.
     * @param postalAddressLine2       the value of the {@value #COLUMN_NAME_POSTAL_ADDRESS_LINE_2} column.
     * @param postalPostalCode         the value of the {@value #COLUMN_NAME_POSTAL_POSTAL_CODE} column.
     * @param lastEditedBy             the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom                the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo                  the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SupplierArchive(final Integer supplierId,
                           final String supplierName,
                           final Integer supplierCategoryId,
                           final Integer primaryContactPersonId,
                           final Integer alternateContactPersonId,
                           final Integer deliveryMethodId,
                           final Integer deliveryCityId,
                           final Integer postalCityId,
                           final String supplierReference,
                           final String bankAccountName,
                           final String bankAccountBranch,
                           final String bankAccountCode,
                           final String bankAccountNumber,
                           final String bankInternationalCode,
                           final Integer paymentDays,
                           final String internalComments,
                           final String phoneNumber,
                           final String faxNumber,
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
        setSupplierId(supplierId);
        setSupplierName(supplierName);
        setSupplierCategoryId(supplierCategoryId);
        setPrimaryContactPersonId(primaryContactPersonId);
        setAlternateContactPersonId(alternateContactPersonId);
        setDeliveryMethodId(deliveryMethodId);
        setDeliveryCityId(deliveryCityId);
        setPostalCityId(postalCityId);
        setSupplierReference(supplierReference);
        setBankAccountName(bankAccountName);
        setBankAccountBranch(bankAccountBranch);
        setBankAccountCode(bankAccountCode);
        setBankAccountNumber(bankAccountNumber);
        setBankInternationalCode(bankInternationalCode);
        setPaymentDays(paymentDays);
        setInternalComments(internalComments);
        setPhoneNumber(phoneNumber);
        setFaxNumber(faxNumber);
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

