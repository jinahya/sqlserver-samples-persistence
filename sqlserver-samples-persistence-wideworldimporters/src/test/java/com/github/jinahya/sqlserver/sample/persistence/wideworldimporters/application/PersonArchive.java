package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application;

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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPersonArchive;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code Application.People_Archive} table, which no entity can map: a history
 * table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class PersonArchive extends MappedPersonArchive {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected PersonArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param personId                the value of the {@value #COLUMN_NAME_PERSON_ID} column.
     * @param fullName                the value of the {@value #COLUMN_NAME_FULL_NAME} column.
     * @param preferredName           the value of the {@value #COLUMN_NAME_PREFERRED_NAME} column.
     * @param searchName              the value of the {@value #COLUMN_NAME_SEARCH_NAME} column.
     * @param isPermittedToLogon      the value of the {@value #COLUMN_NAME_IS_PERMITTED_TO_LOGON} column.
     * @param logonName               the value of the {@value #COLUMN_NAME_LOGON_NAME} column.
     * @param isExternalLogonProvider the value of the {@value #COLUMN_NAME_IS_EXTERNAL_LOGON_PROVIDER} column.
     * @param hashedPassword          the value of the {@value #COLUMN_NAME_HASHED_PASSWORD} column.
     * @param isSystemUser            the value of the {@value #COLUMN_NAME_IS_SYSTEM_USER} column.
     * @param isEmployee              the value of the {@value #COLUMN_NAME_IS_EMPLOYEE} column.
     * @param isSalesperson           the value of the {@value #COLUMN_NAME_IS_SALESPERSON} column.
     * @param userPreferences         the value of the {@value #COLUMN_NAME_USER_PREFERENCES} column.
     * @param phoneNumber             the value of the {@value #COLUMN_NAME_PHONE_NUMBER} column.
     * @param faxNumber               the value of the {@value #COLUMN_NAME_FAX_NUMBER} column.
     * @param emailAddress            the value of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column.
     * @param photo                   the value of the {@value #COLUMN_NAME_PHOTO} column.
     * @param customFields            the value of the {@value #COLUMN_NAME_CUSTOM_FIELDS} column.
     * @param otherLanguages          the value of the {@value #COLUMN_NAME_OTHER_LANGUAGES} column.
     * @param lastEditedBy            the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom               the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo                 the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public PersonArchive(final Integer personId,
                         final String fullName,
                         final String preferredName,
                         final String searchName,
                         final Boolean isPermittedToLogon,
                         final String logonName,
                         final Boolean isExternalLogonProvider,
                         final byte[] hashedPassword,
                         final Boolean isSystemUser,
                         final Boolean isEmployee,
                         final Boolean isSalesperson,
                         final String userPreferences,
                         final String phoneNumber,
                         final String faxNumber,
                         final String emailAddress,
                         final byte[] photo,
                         final String customFields,
                         final String otherLanguages,
                         final Integer lastEditedBy,
                         final LocalDateTime validFrom,
                         final LocalDateTime validTo) {
        super();
        setPersonId(personId);
        setFullName(fullName);
        setPreferredName(preferredName);
        setSearchName(searchName);
        setIsPermittedToLogon(isPermittedToLogon);
        setLogonName(logonName);
        setIsExternalLogonProvider(isExternalLogonProvider);
        setHashedPassword(hashedPassword);
        setIsSystemUser(isSystemUser);
        setIsEmployee(isEmployee);
        setIsSalesperson(isSalesperson);
        setUserPreferences(userPreferences);
        setPhoneNumber(phoneNumber);
        setFaxNumber(faxNumber);
        setEmailAddress(emailAddress);
        setPhoto(photo);
        setCustomFields(customFields);
        setOtherLanguages(otherLanguages);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

