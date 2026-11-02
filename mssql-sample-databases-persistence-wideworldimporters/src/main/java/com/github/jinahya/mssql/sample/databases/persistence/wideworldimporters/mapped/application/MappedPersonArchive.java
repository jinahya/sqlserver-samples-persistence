package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.application;

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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Application.People_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server rejects an INSERT (error 13559) and an UPDATE (error 13561) of a temporal history table; only
 * the system versioning of {@code Application.People} writes it.
 */
@MappedSuperclass
public abstract class MappedPersonArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "People_Archive";

    // -------------------------------------------------------------------------------------------------------- PersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERSON_ID = "PersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON_ID = "personId";

    // -------------------------------------------------------------------------------------------------------- FullName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FULL_NAME = "FullName";

    /**
     * The length of the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FULL_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    // --------------------------------------------------------------------------------------------------- PreferredName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PREFERRED_NAME = "PreferredName";

    /**
     * The length of the {@value #COLUMN_NAME_PREFERRED_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PREFERRED_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PREFERRED_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PREFERRED_NAME = "preferredName";

    // ------------------------------------------------------------------------------------------------------ SearchName

    /**
     * The name of the {@code nvarchar(101), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SEARCH_NAME = "SearchName";

    /**
     * The length of the {@value #COLUMN_NAME_SEARCH_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SEARCH_NAME = 101;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SEARCH_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SEARCH_NAME = "searchName";

    // ---------------------------------------------------------------------------------------------- IsPermittedToLogon

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_PERMITTED_TO_LOGON = "IsPermittedToLogon";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_PERMITTED_TO_LOGON} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_PERMITTED_TO_LOGON = "isPermittedToLogon";

    // ------------------------------------------------------------------------------------------------------- LogonName

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOGON_NAME = "LogonName";

    /**
     * The length of the {@value #COLUMN_NAME_LOGON_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LOGON_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOGON_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOGON_NAME = "logonName";

    // ----------------------------------------------------------------------------------------- IsExternalLogonProvider

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_EXTERNAL_LOGON_PROVIDER = "IsExternalLogonProvider";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_EXTERNAL_LOGON_PROVIDER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_EXTERNAL_LOGON_PROVIDER = "isExternalLogonProvider";

    // -------------------------------------------------------------------------------------------------- HashedPassword

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HASHED_PASSWORD = "HashedPassword";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HASHED_PASSWORD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_HASHED_PASSWORD = "hashedPassword";

    // ---------------------------------------------------------------------------------------------------- IsSystemUser

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_SYSTEM_USER = "IsSystemUser";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_SYSTEM_USER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_SYSTEM_USER = "isSystemUser";

    // ------------------------------------------------------------------------------------------------------ IsEmployee

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_EMPLOYEE = "IsEmployee";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_EMPLOYEE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_EMPLOYEE = "isEmployee";

    // --------------------------------------------------------------------------------------------------- IsSalesperson

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_SALESPERSON = "IsSalesperson";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_SALESPERSON} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_SALESPERSON = "isSalesperson";

    // ------------------------------------------------------------------------------------------------- UserPreferences

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_USER_PREFERENCES = "UserPreferences";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_USER_PREFERENCES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_USER_PREFERENCES = "userPreferences";

    // ----------------------------------------------------------------------------------------------------- PhoneNumber

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
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
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
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

    // ---------------------------------------------------------------------------------------------------- EmailAddress

    /**
     * The name of the {@code nvarchar(256)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EmailAddress";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL_ADDRESS = 256;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    // ----------------------------------------------------------------------------------------------------------- Photo

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHOTO = "Photo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHOTO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHOTO = "photo";

    // ---------------------------------------------------------------------------------------------------- CustomFields

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOM_FIELDS = "CustomFields";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOM_FIELDS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOM_FIELDS = "customFields";

    // -------------------------------------------------------------------------------------------------- OtherLanguages

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OTHER_LANGUAGES = "OtherLanguages";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OTHER_LANGUAGES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OTHER_LANGUAGES = "otherLanguages";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPersonArchive() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "personId=" + personId
               + ",fullName=" + fullName
               + ",preferredName=" + preferredName
               + ",searchName=" + searchName
               + ",isPermittedToLogon=" + isPermittedToLogon
               + ",logonName=" + logonName
               + ",isExternalLogonProvider=" + isExternalLogonProvider
               + ",hashedPassword=" + (this.hashedPassword == null ? null : this.hashedPassword.length + " bytes")
               + ",isSystemUser=" + isSystemUser
               + ",isEmployee=" + isEmployee
               + ",isSalesperson=" + isSalesperson
               + ",userPreferences=" + userPreferences
               + ",phoneNumber=" + phoneNumber
               + ",faxNumber=" + faxNumber
               + ",emailAddress=" + emailAddress
               + ",photo=" + (this.photo == null ? null : this.photo.length + " bytes")
               + ",customFields=" + customFields
               + ",otherLanguages=" + otherLanguages
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // -------------------------------------------------------------------------------------------------------- personId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERSON_ID} attribute.
     */
    public Integer getPersonId() {
        return personId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERSON_ID} attribute with the specified value.
     *
     * @param personId new value for {@value #ATTRIBUTE_NAME_PERSON_ID} attribute.
     */
    protected void setPersonId(final Integer personId) {
        this.personId = personId;
    }

    // -------------------------------------------------------------------------------------------------------- fullName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULL_NAME} attribute with the specified value.
     *
     * @param fullName new value for {@value #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    protected void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    // --------------------------------------------------------------------------------------------------- preferredName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PREFERRED_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PREFERRED_NAME} attribute.
     */
    public String getPreferredName() {
        return preferredName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PREFERRED_NAME} attribute with the specified value.
     *
     * @param preferredName new value for {@value #ATTRIBUTE_NAME_PREFERRED_NAME} attribute.
     */
    protected void setPreferredName(final String preferredName) {
        this.preferredName = preferredName;
    }

    // ------------------------------------------------------------------------------------------------------ searchName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SEARCH_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SEARCH_NAME} attribute.
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SEARCH_NAME} attribute with the specified value.
     *
     * @param searchName new value for {@value #ATTRIBUTE_NAME_SEARCH_NAME} attribute.
     */
    protected void setSearchName(final String searchName) {
        this.searchName = searchName;
    }

    // ---------------------------------------------------------------------------------------------- isPermittedToLogon

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_PERMITTED_TO_LOGON} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_PERMITTED_TO_LOGON} attribute.
     */
    public Boolean getIsPermittedToLogon() {
        return isPermittedToLogon;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_PERMITTED_TO_LOGON} attribute with the specified value.
     *
     * @param isPermittedToLogon new value for {@value #ATTRIBUTE_NAME_IS_PERMITTED_TO_LOGON} attribute.
     */
    protected void setIsPermittedToLogon(final Boolean isPermittedToLogon) {
        this.isPermittedToLogon = isPermittedToLogon;
    }

    // ------------------------------------------------------------------------------------------------------- logonName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOGON_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOGON_NAME} attribute.
     */
    public String getLogonName() {
        return logonName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOGON_NAME} attribute with the specified value.
     *
     * @param logonName new value for {@value #ATTRIBUTE_NAME_LOGON_NAME} attribute.
     */
    protected void setLogonName(final String logonName) {
        this.logonName = logonName;
    }

    // ----------------------------------------------------------------------------------------- isExternalLogonProvider

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_EXTERNAL_LOGON_PROVIDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_EXTERNAL_LOGON_PROVIDER} attribute.
     */
    public Boolean getIsExternalLogonProvider() {
        return isExternalLogonProvider;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_EXTERNAL_LOGON_PROVIDER} attribute with the specified
     * value.
     *
     * @param isExternalLogonProvider new value for {@value #ATTRIBUTE_NAME_IS_EXTERNAL_LOGON_PROVIDER} attribute.
     */
    protected void setIsExternalLogonProvider(final Boolean isExternalLogonProvider) {
        this.isExternalLogonProvider = isExternalLogonProvider;
    }

    // -------------------------------------------------------------------------------------------------- hashedPassword

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HASHED_PASSWORD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HASHED_PASSWORD} attribute.
     */
    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HASHED_PASSWORD} attribute with the specified value.
     *
     * @param hashedPassword new value for {@value #ATTRIBUTE_NAME_HASHED_PASSWORD} attribute.
     */
    protected void setHashedPassword(final byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    // ---------------------------------------------------------------------------------------------------- isSystemUser

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_SYSTEM_USER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_SYSTEM_USER} attribute.
     */
    public Boolean getIsSystemUser() {
        return isSystemUser;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_SYSTEM_USER} attribute with the specified value.
     *
     * @param isSystemUser new value for {@value #ATTRIBUTE_NAME_IS_SYSTEM_USER} attribute.
     */
    protected void setIsSystemUser(final Boolean isSystemUser) {
        this.isSystemUser = isSystemUser;
    }

    // ------------------------------------------------------------------------------------------------------ isEmployee

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_EMPLOYEE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_EMPLOYEE} attribute.
     */
    public Boolean getIsEmployee() {
        return isEmployee;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_EMPLOYEE} attribute with the specified value.
     *
     * @param isEmployee new value for {@value #ATTRIBUTE_NAME_IS_EMPLOYEE} attribute.
     */
    protected void setIsEmployee(final Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    // --------------------------------------------------------------------------------------------------- isSalesperson

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_SALESPERSON} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_SALESPERSON} attribute.
     */
    public Boolean getIsSalesperson() {
        return isSalesperson;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_SALESPERSON} attribute with the specified value.
     *
     * @param isSalesperson new value for {@value #ATTRIBUTE_NAME_IS_SALESPERSON} attribute.
     */
    protected void setIsSalesperson(final Boolean isSalesperson) {
        this.isSalesperson = isSalesperson;
    }

    // ------------------------------------------------------------------------------------------------- userPreferences

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_USER_PREFERENCES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_USER_PREFERENCES} attribute.
     */
    public String getUserPreferences() {
        return userPreferences;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_USER_PREFERENCES} attribute with the specified value.
     *
     * @param userPreferences new value for {@value #ATTRIBUTE_NAME_USER_PREFERENCES} attribute.
     */
    protected void setUserPreferences(final String userPreferences) {
        this.userPreferences = userPreferences;
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

    // ---------------------------------------------------------------------------------------------------- emailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute with the specified value.
     *
     * @param emailAddress new value for {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    protected void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // ----------------------------------------------------------------------------------------------------------- photo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute with the specified value.
     *
     * @param photo new value for {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     */
    protected void setPhoto(final byte[] photo) {
        this.photo = photo;
    }

    // ---------------------------------------------------------------------------------------------------- customFields

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     */
    public String getCustomFields() {
        return customFields;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute with the specified value.
     *
     * @param customFields new value for {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     */
    protected void setCustomFields(final String customFields) {
        this.customFields = customFields;
    }

    // -------------------------------------------------------------------------------------------------- otherLanguages

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OTHER_LANGUAGES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OTHER_LANGUAGES} attribute.
     */
    public String getOtherLanguages() {
        return otherLanguages;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OTHER_LANGUAGES} attribute with the specified value.
     *
     * @param otherLanguages new value for {@value #ATTRIBUTE_NAME_OTHER_LANGUAGES} attribute.
     */
    protected void setOtherLanguages(final String otherLanguages) {
        this.otherLanguages = otherLanguages;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

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

    // ------------------------------------------------------------------------------------------------------- validFrom

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

    // --------------------------------------------------------------------------------------------------------- validTo

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

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer personId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_FULL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FULL_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FULL_NAME
    )
    private String fullName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_PREFERRED_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PREFERRED_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PREFERRED_NAME
    )
    private String preferredName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_SEARCH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SEARCH_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SEARCH_NAME
    )
    private String searchName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_PERMITTED_TO_LOGON,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Boolean isPermittedToLogon;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_LOGON_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOGON_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_LOGON_NAME
    )
    private String logonName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_EXTERNAL_LOGON_PROVIDER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Boolean isExternalLogonProvider;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_HASHED_PASSWORD,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private byte[] hashedPassword;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_SYSTEM_USER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Boolean isSystemUser;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_EMPLOYEE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Boolean isEmployee;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_SALESPERSON,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Boolean isSalesperson;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_USER_PREFERENCES,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String userPreferences;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_FAX_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FAX_NUMBER,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FAX_NUMBER
    )
    private String faxNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_EMAIL_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_EMAIL_ADDRESS
    )
    private String emailAddress;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHOTO,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private byte[] photo;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOM_FIELDS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String customFields;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_OTHER_LANGUAGES,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private String otherLanguages;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
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

