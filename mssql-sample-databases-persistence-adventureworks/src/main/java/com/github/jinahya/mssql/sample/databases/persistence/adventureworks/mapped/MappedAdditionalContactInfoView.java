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
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.vAdditionalContactInfo} view.
 * <p>
 * Identifier: the view declares no key; BusinessEntityID is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedAdditionalContactInfoView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Person";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vAdditionalContactInfo";

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

    // ------------------------------------------------------------------------------------------------- TelephoneNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TELEPHONE_NUMBER = "TelephoneNumber";

    /**
     * The length of the {@value #COLUMN_NAME_TELEPHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TELEPHONE_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TELEPHONE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TELEPHONE_NUMBER = "telephoneNumber";

    // ------------------------------------------------------------------------------------ TelephoneSpecialInstructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS = "TelephoneSpecialInstructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS} column. The value
     * is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS = "telephoneSpecialInstructions";

    // ---------------------------------------------------------------------------------------------------------- Street

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STREET = "Street";

    /**
     * The length of the {@value #COLUMN_NAME_STREET} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STREET = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STREET} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STREET = "street";

    // ------------------------------------------------------------------------------------------------------------ City

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "City";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // --------------------------------------------------------------------------------------------------- StateProvince

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE = "StateProvince";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    // ------------------------------------------------------------------------------------------------------ PostalCode

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_CODE = "PostalCode";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CODE = "postalCode";

    // --------------------------------------------------------------------------------------------------- CountryRegion

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION = "CountryRegion";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_REGION = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION = "countryRegion";

    // ---------------------------------------------------------------------------------- HomeAddressSpecialInstructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS = "HomeAddressSpecialInstructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS} column. The
     * value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS = "homeAddressSpecialInstructions";

    // ---------------------------------------------------------------------------------------------------- EMailAddress

    /**
     * The name of the {@code nvarchar(128)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_E_MAIL_ADDRESS = "EMailAddress";

    /**
     * The length of the {@value #COLUMN_NAME_E_MAIL_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_E_MAIL_ADDRESS = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_E_MAIL_ADDRESS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_E_MAIL_ADDRESS = "eMailAddress";

    // ---------------------------------------------------------------------------------------- EMailSpecialInstructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_E_MAIL_SPECIAL_INSTRUCTIONS = "EMailSpecialInstructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_E_MAIL_SPECIAL_INSTRUCTIONS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_E_MAIL_SPECIAL_INSTRUCTIONS = "eMailSpecialInstructions";

    // -------------------------------------------------------------------------------------------- EMailTelephoneNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_E_MAIL_TELEPHONE_NUMBER = "EMailTelephoneNumber";

    /**
     * The length of the {@value #COLUMN_NAME_E_MAIL_TELEPHONE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_E_MAIL_TELEPHONE_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_E_MAIL_TELEPHONE_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_E_MAIL_TELEPHONE_NUMBER = "eMailTelephoneNumber";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedAdditionalContactInfoView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",firstName=" + firstName
               + ",middleName=" + middleName
               + ",lastName=" + lastName
               + ",telephoneNumber=" + telephoneNumber
               + ",telephoneSpecialInstructions=" + telephoneSpecialInstructions
               + ",street=" + street
               + ",city=" + city
               + ",stateProvince=" + stateProvince
               + ",postalCode=" + postalCode
               + ",countryRegion=" + countryRegion
               + ",homeAddressSpecialInstructions=" + homeAddressSpecialInstructions
               + ",eMailAddress=" + eMailAddress
               + ",eMailSpecialInstructions=" + eMailSpecialInstructions
               + ",eMailTelephoneNumber=" + eMailTelephoneNumber
               + ",rowguid=" + rowguid
               + ",modifiedDate=" + modifiedDate
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
        if (!(obj instanceof MappedAdditionalContactInfoView that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
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

    // ------------------------------------------------------------------------------------------------- telephoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TELEPHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TELEPHONE_NUMBER} attribute.
     */
    @Nullable
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TELEPHONE_NUMBER} attribute with the specified value.
     *
     * @param telephoneNumber new value for {@value #ATTRIBUTE_NAME_TELEPHONE_NUMBER} attribute.
     */
    protected void setTelephoneNumber(@Nullable final String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    // ------------------------------------------------------------------------------------ telephoneSpecialInstructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS} attribute.
     */
    @Nullable
    public String getTelephoneSpecialInstructions() {
        return telephoneSpecialInstructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS} attribute with the specified
     * value.
     *
     * @param telephoneSpecialInstructions new value for {@value #ATTRIBUTE_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS} attribute.
     */
    protected void setTelephoneSpecialInstructions(@Nullable final String telephoneSpecialInstructions) {
        this.telephoneSpecialInstructions = telephoneSpecialInstructions;
    }

    // ---------------------------------------------------------------------------------------------------------- street

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STREET} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STREET} attribute.
     */
    @Nullable
    public String getStreet() {
        return street;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STREET} attribute with the specified value.
     *
     * @param street new value for {@value #ATTRIBUTE_NAME_STREET} attribute.
     */
    protected void setStreet(@Nullable final String street) {
        this.street = street;
    }

    // ------------------------------------------------------------------------------------------------------------ city

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    @Nullable
    public String getCity() {
        return city;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY} attribute with the specified value.
     *
     * @param city new value for {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    protected void setCity(@Nullable final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------------------- stateProvince

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     */
    @Nullable
    public String getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute with the specified value.
     *
     * @param stateProvince new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE} attribute.
     */
    protected void setStateProvince(@Nullable final String stateProvince) {
        this.stateProvince = stateProvince;
    }

    // ------------------------------------------------------------------------------------------------------ postalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    @Nullable
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute with the specified value.
     *
     * @param postalCode new value for {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    protected void setPostalCode(@Nullable final String postalCode) {
        this.postalCode = postalCode;
    }

    // --------------------------------------------------------------------------------------------------- countryRegion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute.
     */
    @Nullable
    public String getCountryRegion() {
        return countryRegion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute with the specified value.
     *
     * @param countryRegion new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION} attribute.
     */
    protected void setCountryRegion(@Nullable final String countryRegion) {
        this.countryRegion = countryRegion;
    }

    // ---------------------------------------------------------------------------------- homeAddressSpecialInstructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS} attribute.
     */
    @Nullable
    public String getHomeAddressSpecialInstructions() {
        return homeAddressSpecialInstructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS} attribute with the
     * specified value.
     *
     * @param homeAddressSpecialInstructions new value for {@value #ATTRIBUTE_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS} attribute.
     */
    protected void setHomeAddressSpecialInstructions(@Nullable final String homeAddressSpecialInstructions) {
        this.homeAddressSpecialInstructions = homeAddressSpecialInstructions;
    }

    // ---------------------------------------------------------------------------------------------------- eMailAddress

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_E_MAIL_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_E_MAIL_ADDRESS} attribute.
     */
    @Nullable
    public String getEMailAddress() {
        return eMailAddress;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_E_MAIL_ADDRESS} attribute with the specified value.
     *
     * @param eMailAddress new value for {@value #ATTRIBUTE_NAME_E_MAIL_ADDRESS} attribute.
     */
    protected void setEMailAddress(@Nullable final String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    // ---------------------------------------------------------------------------------------- eMailSpecialInstructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_E_MAIL_SPECIAL_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_E_MAIL_SPECIAL_INSTRUCTIONS} attribute.
     */
    @Nullable
    public String getEMailSpecialInstructions() {
        return eMailSpecialInstructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_E_MAIL_SPECIAL_INSTRUCTIONS} attribute with the specified
     * value.
     *
     * @param eMailSpecialInstructions new value for {@value #ATTRIBUTE_NAME_E_MAIL_SPECIAL_INSTRUCTIONS} attribute.
     */
    protected void setEMailSpecialInstructions(@Nullable final String eMailSpecialInstructions) {
        this.eMailSpecialInstructions = eMailSpecialInstructions;
    }

    // -------------------------------------------------------------------------------------------- eMailTelephoneNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_E_MAIL_TELEPHONE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_E_MAIL_TELEPHONE_NUMBER} attribute.
     */
    @Nullable
    public String getEMailTelephoneNumber() {
        return eMailTelephoneNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_E_MAIL_TELEPHONE_NUMBER} attribute with the specified value.
     *
     * @param eMailTelephoneNumber new value for {@value #ATTRIBUTE_NAME_E_MAIL_TELEPHONE_NUMBER} attribute.
     */
    protected void setEMailTelephoneNumber(@Nullable final String eMailTelephoneNumber) {
        this.eMailTelephoneNumber = eMailTelephoneNumber;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    @Nonnull
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    protected void setRowguid(@Nonnull final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    protected void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

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
    @Size(max = COLUMN_LENGTH_TELEPHONE_NUMBER)
    @Column(name = COLUMN_NAME_TELEPHONE_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TELEPHONE_NUMBER
    )
    private String telephoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_TELEPHONE_SPECIAL_INSTRUCTIONS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String telephoneSpecialInstructions;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_STREET)
    @Column(name = COLUMN_NAME_STREET,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STREET
    )
    private String street;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_CITY)
    @Column(name = COLUMN_NAME_CITY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE)
    @Column(name = COLUMN_NAME_STATE_PROVINCE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATE_PROVINCE
    )
    private String stateProvince;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_POSTAL_CODE)
    @Column(name = COLUMN_NAME_POSTAL_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_POSTAL_CODE
    )
    private String postalCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION)
    @Column(name = COLUMN_NAME_COUNTRY_REGION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_REGION
    )
    private String countryRegion;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_HOME_ADDRESS_SPECIAL_INSTRUCTIONS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String homeAddressSpecialInstructions;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_E_MAIL_ADDRESS)
    @Column(name = COLUMN_NAME_E_MAIL_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_E_MAIL_ADDRESS
    )
    private String eMailAddress;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_E_MAIL_SPECIAL_INSTRUCTIONS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String eMailSpecialInstructions;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_E_MAIL_TELEPHONE_NUMBER)
    @Column(name = COLUMN_NAME_E_MAIL_TELEPHONE_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_E_MAIL_TELEPHONE_NUMBER
    )
    private String eMailTelephoneNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

