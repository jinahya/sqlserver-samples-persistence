package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/**
 * A mapped superclass which holds the mappings of the {@code Sales.vIndividualCustomer} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedIndividualCustomerView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vIndividualCustomer";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ----------------------------------------------------------------------------------------------------------- Title

    /**
     * The name of the {@code nvarchar(8)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TITLE = "Title";

    /**
     * The length of the {@value #COLUMN_NAME_TITLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TITLE = 8;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TITLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // ------------------------------------------------------------------------------------------------------- FirstName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_NAME = "FirstName";

    /**
     * The length of the {@value #COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FIRST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    // ------------------------------------------------------------------------------------------------------ MiddleName

    /**
     * The name of the {@code Name (nvarchar)(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIDDLE_NAME = "MiddleName";

    /**
     * The length of the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MIDDLE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIDDLE_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIDDLE_NAME = "middleName";

    // -------------------------------------------------------------------------------------------------------- LastName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_NAME = "LastName";

    /**
     * The length of the {@value #COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_LAST_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    // ---------------------------------------------------------------------------------------------------------- Suffix

    /**
     * The name of the {@code nvarchar(10)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUFFIX = "Suffix";

    /**
     * The length of the {@value #COLUMN_NAME_SUFFIX} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUFFIX = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUFFIX} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUFFIX = "suffix";

    // ----------------------------------------------------------------------------------------------------- PhoneNumber

    /**
     * The name of the {@code Phone (nvarchar)(25)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE_NUMBER = "PhoneNumber";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER = "phoneNumber";

    // ------------------------------------------------------------------------------------------------- PhoneNumberType

    /**
     * The name of the {@code Name (nvarchar)(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE_NUMBER_TYPE = "PhoneNumberType";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE_NUMBER_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE_NUMBER_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE_NUMBER_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE_NUMBER_TYPE = "phoneNumberType";

    // ---------------------------------------------------------------------------------------------------- EmailAddress

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_ADDRESS = "EmailAddress";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL_ADDRESS = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_ADDRESS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_ADDRESS = "emailAddress";

    // -------------------------------------------------------------------------------------------------- EmailPromotion

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL_PROMOTION = "EmailPromotion";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL_PROMOTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL_PROMOTION = "emailPromotion";

    // ----------------------------------------------------------------------------------------------------- AddressType

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS_TYPE = "AddressType";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_TYPE = "addressType";

    // ---------------------------------------------------------------------------------------------------- AddressLine1

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS_LINE_1 = "AddressLine1";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS_LINE_1} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS_LINE_1 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS_LINE_1} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_LINE_1 = "addressLine1";

    // ---------------------------------------------------------------------------------------------------- AddressLine2

    /**
     * The name of the {@code nvarchar(60)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS_LINE_2 = "AddressLine2";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS_LINE_2} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS_LINE_2 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS_LINE_2} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_LINE_2 = "addressLine2";

    // ------------------------------------------------------------------------------------------------------------ City

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "City";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // ----------------------------------------------------------------------------------------------- StateProvinceName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_NAME = "StateProvinceName";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_NAME = "stateProvinceName";

    // ------------------------------------------------------------------------------------------------------ PostalCode

    /**
     * The name of the {@code nvarchar(15), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_CODE = "PostalCode";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_CODE = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CODE = "postalCode";

    // ----------------------------------------------------------------------------------------------- CountryRegionName

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION_NAME = "CountryRegionName";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_REGION_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_REGION_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION_NAME = "countryRegionName";

    // ---------------------------------------------------------------------------------------------------- Demographics

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEMOGRAPHICS = "Demographics";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEMOGRAPHICS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEMOGRAPHICS = "demographics";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedIndividualCustomerView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",title=" + title
               + ",firstName=" + firstName
               + ",middleName=" + middleName
               + ",lastName=" + lastName
               + ",suffix=" + suffix
               + ",phoneNumber=" + phoneNumber
               + ",phoneNumberType=" + phoneNumberType
               + ",emailAddress=" + emailAddress
               + ",emailPromotion=" + emailPromotion
               + ",addressType=" + addressType
               + ",addressLine1=" + addressLine1
               + ",addressLine2=" + addressLine2
               + ",city=" + city
               + ",stateProvinceName=" + stateProvinceName
               + ",postalCode=" + postalCode
               + ",countryRegionName=" + countryRegionName
               + ",demographics=" + demographics
               + "}";
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    @Nonnull
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(@Nonnull final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ----------------------------------------------------------------------------------------------------------- title

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    @Nullable
    public String getTitle() {
        return title;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TITLE} attribute with the specified value.
     *
     * @param title new value for {@value #ATTRIBUTE_NAME_TITLE} attribute.
     */
    protected void setTitle(@Nullable final String title) {
        this.title = title;
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    @Nonnull
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with the specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    protected void setFirstName(@Nonnull final String firstName) {
        this.firstName = firstName;
    }

    // ------------------------------------------------------------------------------------------------------ middleName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    @Nullable
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute with the specified value.
     *
     * @param middleName new value for {@value #ATTRIBUTE_NAME_MIDDLE_NAME} attribute.
     */
    protected void setMiddleName(@Nullable final String middleName) {
        this.middleName = middleName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    @Nonnull
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with the specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    protected void setLastName(@Nonnull final String lastName) {
        this.lastName = lastName;
    }

    // ---------------------------------------------------------------------------------------------------------- suffix

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    @Nullable
    public String getSuffix() {
        return suffix;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUFFIX} attribute with the specified value.
     *
     * @param suffix new value for {@value #ATTRIBUTE_NAME_SUFFIX} attribute.
     */
    protected void setSuffix(@Nullable final String suffix) {
        this.suffix = suffix;
    }

    // ----------------------------------------------------------------------------------------------------- phoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    @Nullable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute with the specified value.
     *
     * @param phoneNumber new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER} attribute.
     */
    protected void setPhoneNumber(@Nullable final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // ------------------------------------------------------------------------------------------------- phoneNumberType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE} attribute.
     */
    @Nullable
    public String getPhoneNumberType() {
        return phoneNumberType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE} attribute with the specified value.
     *
     * @param phoneNumberType new value for {@value #ATTRIBUTE_NAME_PHONE_NUMBER_TYPE} attribute.
     */
    protected void setPhoneNumberType(@Nullable final String phoneNumberType) {
        this.phoneNumberType = phoneNumberType;
    }

    // ---------------------------------------------------------------------------------------------------- emailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    @Nullable
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute with the specified value.
     *
     * @param emailAddress new value for {@value #ATTRIBUTE_NAME_EMAIL_ADDRESS} attribute.
     */
    protected void setEmailAddress(@Nullable final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    // -------------------------------------------------------------------------------------------------- emailPromotion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     */
    @Nonnull
    public Integer getEmailPromotion() {
        return emailPromotion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute with the specified value.
     *
     * @param emailPromotion new value for {@value #ATTRIBUTE_NAME_EMAIL_PROMOTION} attribute.
     */
    protected void setEmailPromotion(@Nonnull final Integer emailPromotion) {
        this.emailPromotion = emailPromotion;
    }

    // ----------------------------------------------------------------------------------------------------- addressType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute.
     */
    @Nonnull
    public String getAddressType() {
        return addressType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute with the specified value.
     *
     * @param addressType new value for {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute.
     */
    protected void setAddressType(@Nonnull final String addressType) {
        this.addressType = addressType;
    }

    // ---------------------------------------------------------------------------------------------------- addressLine1

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute.
     */
    @Nonnull
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute with the specified value.
     *
     * @param addressLine1 new value for {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute.
     */
    protected void setAddressLine1(@Nonnull final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    // ---------------------------------------------------------------------------------------------------- addressLine2

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute.
     */
    @Nullable
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute with the specified value.
     *
     * @param addressLine2 new value for {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute.
     */
    protected void setAddressLine2(@Nullable final String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    // ------------------------------------------------------------------------------------------------------------ city

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    @Nonnull
    public String getCity() {
        return city;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY} attribute with the specified value.
     *
     * @param city new value for {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    protected void setCity(@Nonnull final String city) {
        this.city = city;
    }

    // ----------------------------------------------------------------------------------------------- stateProvinceName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     */
    @Nonnull
    public String getStateProvinceName() {
        return stateProvinceName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute with the specified value.
     *
     * @param stateProvinceName new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     */
    protected void setStateProvinceName(@Nonnull final String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    // ------------------------------------------------------------------------------------------------------ postalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    @Nonnull
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute with the specified value.
     *
     * @param postalCode new value for {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    protected void setPostalCode(@Nonnull final String postalCode) {
        this.postalCode = postalCode;
    }

    // ----------------------------------------------------------------------------------------------- countryRegionName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute.
     */
    @Nonnull
    public String getCountryRegionName() {
        return countryRegionName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute with the specified value.
     *
     * @param countryRegionName new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute.
     */
    protected void setCountryRegionName(@Nonnull final String countryRegionName) {
        this.countryRegionName = countryRegionName;
    }

    // ---------------------------------------------------------------------------------------------------- demographics

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     */
    @Nullable
    public String getDemographics() {
        return demographics;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute with the specified value.
     *
     * @param demographics new value for {@value #ATTRIBUTE_NAME_DEMOGRAPHICS} attribute.
     */
    protected void setDemographics(@Nullable final String demographics) {
        this.demographics = demographics;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_TITLE)
    @Column(name = COLUMN_NAME_TITLE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TITLE
    )
    private String title;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_FIRST_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_FIRST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_FIRST_NAME
    )
    private String firstName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_MIDDLE_NAME)
    @Column(name = COLUMN_NAME_MIDDLE_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_MIDDLE_NAME
    )
    private String middleName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_LAST_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_LAST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_LAST_NAME
    )
    private String lastName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_SUFFIX)
    @Column(name = COLUMN_NAME_SUFFIX,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUFFIX
    )
    private String suffix;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER)
    @Column(name = COLUMN_NAME_PHONE_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE_NUMBER
    )
    private String phoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PHONE_NUMBER_TYPE)
    @Column(name = COLUMN_NAME_PHONE_NUMBER_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE_NUMBER_TYPE
    )
    private String phoneNumberType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EMAIL_ADDRESS)
    @Column(name = COLUMN_NAME_EMAIL_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EMAIL_ADDRESS
    )
    private String emailAddress;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_EMAIL_PROMOTION,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer emailPromotion;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ADDRESS_TYPE)
    @NotNull
    @Column(name = COLUMN_NAME_ADDRESS_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDRESS_TYPE
    )
    private String addressType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ADDRESS_LINE_1)
    @NotNull
    @Column(name = COLUMN_NAME_ADDRESS_LINE_1,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDRESS_LINE_1
    )
    private String addressLine1;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDRESS_LINE_2)
    @Column(name = COLUMN_NAME_ADDRESS_LINE_2,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDRESS_LINE_2
    )
    private String addressLine2;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_CITY)
    @NotNull
    @Column(name = COLUMN_NAME_CITY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_STATE_PROVINCE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATE_PROVINCE_NAME
    )
    private String stateProvinceName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_POSTAL_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_POSTAL_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_POSTAL_CODE
    )
    private String postalCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_REGION_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_REGION_NAME
    )
    private String countryRegionName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DEMOGRAPHICS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String demographics;
}

