package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Supplier} table.
 */
@MappedSuperclass
public abstract class MappedSupplier {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Supplier";

    // ------------------------------------------------------------------------------------------------------ SupplierID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ---------------------------------------------------------------------------------------------------- SupplierCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_CODE = "SupplierCode";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CODE = "supplierCode";

    // ---------------------------------------------------------------------------------------------------- SupplierName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_NAME = "SupplierName";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_NAME = "supplierName";

    // ----------------------------------------------------------------------------------------------------- ContactName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTACT_NAME = "ContactName";

    /**
     * The length of the {@value #COLUMN_NAME_CONTACT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONTACT_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTACT_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_NAME = "contactName";

    // ----------------------------------------------------------------------------------------------------------- Email

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL = "Email";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    // ----------------------------------------------------------------------------------------------------------- Phone

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE = "Phone";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE = "phone";

    // --------------------------------------------------------------------------------------------------------- Address

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS = "Address";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    // ------------------------------------------------------------------------------------------------------------ City

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "City";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // ----------------------------------------------------------------------------------------------------------- State

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE = "State";

    /**
     * The length of the {@value #COLUMN_NAME_STATE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE = "state";

    // --------------------------------------------------------------------------------------------------------- ZipCode

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ZIP_CODE = "ZipCode";

    /**
     * The length of the {@value #COLUMN_NAME_ZIP_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ZIP_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ZIP_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ZIP_CODE = "zipCode";

    // --------------------------------------------------------------------------------------------------------- Country

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY = "Country";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    // ---------------------------------------------------------------------------------------------------- PaymentTerms

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAYMENT_TERMS = "PaymentTerms";

    /**
     * The length of the {@value #COLUMN_NAME_PAYMENT_TERMS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PAYMENT_TERMS = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAYMENT_TERMS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAYMENT_TERMS = "paymentTerms";

    // ---------------------------------------------------------------------------------------------------------- Rating

    /**
     * The name of the {@code decimal(3, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATING = "Rating";

    /**
     * The precision of the {@value #COLUMN_NAME_RATING} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RATING = 3;

    /**
     * The scale of the {@value #COLUMN_NAME_RATING} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RATING = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATING} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATING = "rating";

    // -------------------------------------------------------------------------------------------------------- IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // ----------------------------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplier() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "supplierId=" + supplierId
               + ",supplierCode=" + supplierCode
               + ",supplierName=" + supplierName
               + ",contactName=" + contactName
               + ",email=" + email
               + ",phone=" + phone
               + ",address=" + address
               + ",city=" + city
               + ",state=" + state
               + ",zipCode=" + zipCode
               + ",country=" + country
               + ",paymentTerms=" + paymentTerms
               + ",rating=" + rating
               + ",isActive=" + isActive
               + ",createdDate=" + createdDate
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

    // ------------------------------------------------------------------------------------------------------ supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    @Nullable
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(@Nullable final Integer supplierId) {
        this.supplierId = supplierId;
    }

    // ---------------------------------------------------------------------------------------------------- supplierCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute.
     */
    @Nonnull
    public String getSupplierCode() {
        return supplierCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute with the specified value.
     *
     * @param supplierCode new value for {@value #ATTRIBUTE_NAME_SUPPLIER_CODE} attribute.
     */
    public void setSupplierCode(@Nonnull final String supplierCode) {
        this.supplierCode = supplierCode;
    }

    // ---------------------------------------------------------------------------------------------------- supplierName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    @Nonnull
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute with the specified value.
     *
     * @param supplierName new value for {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    public void setSupplierName(@Nonnull final String supplierName) {
        this.supplierName = supplierName;
    }

    // ----------------------------------------------------------------------------------------------------- contactName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute.
     */
    @Nullable
    public String getContactName() {
        return contactName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute with the specified value.
     *
     * @param contactName new value for {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute.
     */
    public void setContactName(@Nullable final String contactName) {
        this.contactName = contactName;
    }

    // ----------------------------------------------------------------------------------------------------------- email

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     */
    @Nullable
    public String getEmail() {
        return email;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute with the specified value.
     *
     * @param email new value for {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     */
    public void setEmail(@Nullable final String email) {
        this.email = email;
    }

    // ----------------------------------------------------------------------------------------------------------- phone

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE} attribute.
     */
    @Nullable
    public String getPhone() {
        return phone;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE} attribute with the specified value.
     *
     * @param phone new value for {@value #ATTRIBUTE_NAME_PHONE} attribute.
     */
    public void setPhone(@Nullable final String phone) {
        this.phone = phone;
    }

    // --------------------------------------------------------------------------------------------------------- address

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    @Nullable
    public String getAddress() {
        return address;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute with the specified value.
     *
     * @param address new value for {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public void setAddress(@Nullable final String address) {
        this.address = address;
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
    public void setCity(@Nullable final String city) {
        this.city = city;
    }

    // ----------------------------------------------------------------------------------------------------------- state

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE} attribute.
     */
    @Nullable
    public String getState() {
        return state;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE} attribute with the specified value.
     *
     * @param state new value for {@value #ATTRIBUTE_NAME_STATE} attribute.
     */
    public void setState(@Nullable final String state) {
        this.state = state;
    }

    // --------------------------------------------------------------------------------------------------------- zipCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute.
     */
    @Nullable
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute with the specified value.
     *
     * @param zipCode new value for {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute.
     */
    public void setZipCode(@Nullable final String zipCode) {
        this.zipCode = zipCode;
    }

    // --------------------------------------------------------------------------------------------------------- country

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     */
    @Nullable
    public String getCountry() {
        return country;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute with the specified value.
     *
     * @param country new value for {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     */
    public void setCountry(@Nullable final String country) {
        this.country = country;
    }

    // ---------------------------------------------------------------------------------------------------- paymentTerms

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAYMENT_TERMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAYMENT_TERMS} attribute.
     */
    @Nullable
    public String getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAYMENT_TERMS} attribute with the specified value.
     *
     * @param paymentTerms new value for {@value #ATTRIBUTE_NAME_PAYMENT_TERMS} attribute.
     */
    public void setPaymentTerms(@Nullable final String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    // ---------------------------------------------------------------------------------------------------------- rating

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATING} attribute.
     */
    @Nullable
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATING} attribute with the specified value.
     *
     * @param rating new value for {@value #ATTRIBUTE_NAME_RATING} attribute.
     */
    public void setRating(@Nullable final BigDecimal rating) {
        this.rating = rating;
    }

    // -------------------------------------------------------------------------------------------------------- isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    @Nonnull
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(@Nonnull final Boolean isActive) {
        this.isActive = isActive;
    }

    // ----------------------------------------------------------------------------------------------------- createdDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    @Nullable
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute with the specified value.
     *
     * @param createdDate new value for {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public void setCreatedDate(@Nullable final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer supplierId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_SUPPLIER_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_SUPPLIER_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_CODE
    )
    private String supplierCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_SUPPLIER_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_SUPPLIER_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_NAME
    )
    private String supplierName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_CONTACT_NAME)
    @Column(name = COLUMN_NAME_CONTACT_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CONTACT_NAME
    )
    private String contactName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_EMAIL)
    @Column(name = COLUMN_NAME_EMAIL,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_EMAIL
    )
    private String email;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PHONE)
    @Column(name = COLUMN_NAME_PHONE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PHONE
    )
    private String phone;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ADDRESS)
    @Column(name = COLUMN_NAME_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDRESS
    )
    private String address;

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
    @Size(max = COLUMN_LENGTH_STATE)
    @Column(name = COLUMN_NAME_STATE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATE
    )
    private String state;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_ZIP_CODE)
    @Column(name = COLUMN_NAME_ZIP_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ZIP_CODE
    )
    private String zipCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_COUNTRY)
    @Column(name = COLUMN_NAME_COUNTRY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY
    )
    private String country;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PAYMENT_TERMS)
    @Column(name = COLUMN_NAME_PAYMENT_TERMS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PAYMENT_TERMS
    )
    private String paymentTerms;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_RATING - COLUMN_SCALE_RATING, fraction = COLUMN_SCALE_RATING)
    @Column(name = COLUMN_NAME_RATING,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_RATING,
            scale = COLUMN_SCALE_RATING
    )
    private BigDecimal rating;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isActive;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_CREATED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime createdDate;
}

