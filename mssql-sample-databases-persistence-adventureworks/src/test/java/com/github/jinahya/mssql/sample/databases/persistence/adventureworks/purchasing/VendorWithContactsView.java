package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.purchasing;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing.MappedVendorWithContactsView;

/**
 * A plain class which holds a row of the {@code Purchasing.vVendorWithContacts} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class VendorWithContactsView extends MappedVendorWithContactsView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected VendorWithContactsView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param businessEntityId the value of the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column.
     * @param name             the value of the {@value #COLUMN_NAME_NAME} column.
     * @param contactType      the value of the {@value #COLUMN_NAME_CONTACT_TYPE} column.
     * @param title            the value of the {@value #COLUMN_NAME_TITLE} column.
     * @param firstName        the value of the {@value #COLUMN_NAME_FIRST_NAME} column.
     * @param middleName       the value of the {@value #COLUMN_NAME_MIDDLE_NAME} column.
     * @param lastName         the value of the {@value #COLUMN_NAME_LAST_NAME} column.
     * @param suffix           the value of the {@value #COLUMN_NAME_SUFFIX} column.
     * @param phoneNumber      the value of the {@value #COLUMN_NAME_PHONE_NUMBER} column.
     * @param phoneNumberType  the value of the {@value #COLUMN_NAME_PHONE_NUMBER_TYPE} column.
     * @param emailAddress     the value of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column.
     * @param emailPromotion   the value of the {@value #COLUMN_NAME_EMAIL_PROMOTION} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public VendorWithContactsView(final Integer businessEntityId,
                                  final String name,
                                  final String contactType,
                                  final String title,
                                  final String firstName,
                                  final String middleName,
                                  final String lastName,
                                  final String suffix,
                                  final String phoneNumber,
                                  final String phoneNumberType,
                                  final String emailAddress,
                                  final Integer emailPromotion) {
        super();
        setBusinessEntityId(businessEntityId);
        setName(name);
        setContactType(contactType);
        setTitle(title);
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setSuffix(suffix);
        setPhoneNumber(phoneNumber);
        setPhoneNumberType(phoneNumberType);
        setEmailAddress(emailAddress);
        setEmailPromotion(emailPromotion);
    }
}

