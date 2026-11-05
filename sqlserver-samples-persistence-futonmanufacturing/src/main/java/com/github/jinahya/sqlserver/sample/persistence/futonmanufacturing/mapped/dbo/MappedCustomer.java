package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Customer} table.
 * <p>
 * The {@value #COLUMN_NAME_SALES_REP_ID}, {@value #COLUMN_NAME_TERRITORY_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.Customer}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsActive}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code CreatedDate}</th><td>{@code (getdate())}</td></tr>
 * <tr><th scope="row">{@code CustomerType}</th><td>{@code ('Retail')}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedCustomer {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Customer";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // -------------------------------------------------------------------------------- CustomerCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CODE = "CustomerCode";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CODE = "customerCode";

    // -------------------------------------------------------------------------------- CustomerName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_NAME = "CustomerName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

    // --------------------------------------------------------------------------------- ContactName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTACT_NAME = "ContactName";

    /**
     * The length of the {@value #COLUMN_NAME_CONTACT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONTACT_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTACT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_NAME = "contactName";

    // --------------------------------------------------------------------------------------- Email

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EMAIL = "Email";

    /**
     * The length of the {@value #COLUMN_NAME_EMAIL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_EMAIL = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EMAIL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    // --------------------------------------------------------------------------------------- Phone

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHONE = "Phone";

    /**
     * The length of the {@value #COLUMN_NAME_PHONE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PHONE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHONE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE = "phone";

    // ------------------------------------------------------------------------------------- Address

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS = "Address";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    // ---------------------------------------------------------------------------------------- City

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "City";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // --------------------------------------------------------------------------------------- State

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE = "State";

    /**
     * The length of the {@value #COLUMN_NAME_STATE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE = "state";

    // ------------------------------------------------------------------------------------- ZipCode

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ZIP_CODE = "ZipCode";

    /**
     * The length of the {@value #COLUMN_NAME_ZIP_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ZIP_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ZIP_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ZIP_CODE = "zipCode";

    // ------------------------------------------------------------------------------------- Country

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY = "Country";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

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

    // ------------------------------------------------------------------------------------ IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // --------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // -------------------------------------------------------------------------------- CustomerType

    /**
     * The name of the {@code nvarchar(20), DEFAULT ('Retail')} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_TYPE = "CustomerType";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_TYPE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TYPE = "customerType";

    // ---------------------------------------------------------------------------------- SalesRepID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REP_ID = "SalesRepID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP_ID = "salesRepId";

    // --------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

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
               + ",customerCode=" + customerCode
               + ",customerName=" + customerName
               + ",contactName=" + contactName
               + ",email=" + email
               + ",phone=" + phone
               + ",address=" + address
               + ",city=" + city
               + ",state=" + state
               + ",zipCode=" + zipCode
               + ",country=" + country
               + ",creditLimit=" + creditLimit
               + ",isActive=" + isActive
               + ",createdDate=" + createdDate
               + ",customerType=" + customerType
               + ",salesRepId=" + salesRepId
               + ",territoryId=" + territoryId
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedCustomer that)) {
            return false;
        }
        return getCustomerId() != null
               && getCustomerId().equals(that.getCustomerId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedCustomer.class.hashCode();
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

    // -------------------------------------------------------------------------------- customerCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute with the specified value.
     *
     * @param customerCode new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    public void setCustomerCode(final String customerCode) {
        this.customerCode = customerCode;
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

    // --------------------------------------------------------------------------------- contactName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute.
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute with the specified value.
     *
     * @param contactName new value for {@value #ATTRIBUTE_NAME_CONTACT_NAME} attribute.
     */
    public void setContactName(final String contactName) {
        this.contactName = contactName;
    }

    // --------------------------------------------------------------------------------------- email

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EMAIL} attribute with the specified value.
     *
     * @param email new value for {@value #ATTRIBUTE_NAME_EMAIL} attribute.
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    // --------------------------------------------------------------------------------------- phone

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHONE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHONE} attribute.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHONE} attribute with the specified value.
     *
     * @param phone new value for {@value #ATTRIBUTE_NAME_PHONE} attribute.
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    // ------------------------------------------------------------------------------------- address

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute with the specified value.
     *
     * @param address new value for {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    // ---------------------------------------------------------------------------------------- city

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    public String getCity() {
        return city;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CITY} attribute with the specified value.
     *
     * @param city new value for {@value #ATTRIBUTE_NAME_CITY} attribute.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------- state

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE} attribute.
     */
    public String getState() {
        return state;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE} attribute with the specified value.
     *
     * @param state new value for {@value #ATTRIBUTE_NAME_STATE} attribute.
     */
    public void setState(final String state) {
        this.state = state;
    }

    // ------------------------------------------------------------------------------------- zipCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute with the specified value.
     *
     * @param zipCode new value for {@value #ATTRIBUTE_NAME_ZIP_CODE} attribute.
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    // ------------------------------------------------------------------------------------- country

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY} attribute with the specified value.
     *
     * @param country new value for {@value #ATTRIBUTE_NAME_COUNTRY} attribute.
     */
    public void setCountry(final String country) {
        this.country = country;
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

    // ------------------------------------------------------------------------------------ isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // --------------------------------------------------------------------------------- createdDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute with the specified value.
     *
     * @param createdDate new value for {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    protected void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // -------------------------------------------------------------------------------- customerType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute with the specified value.
     *
     * @param customerType new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    public void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    // ---------------------------------------------------------------------------------- salesRepId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute.
     */
    protected Integer getSalesRepId() {
        return salesRepId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param salesRepId new value for {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute.
     */
    protected void setSalesRepId(final Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    // --------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false)
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_CUSTOMER_CODE)
    private String customerCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_CUSTOMER_NAME)
    private String customerName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CONTACT_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CONTACT_NAME, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_CONTACT_NAME)
    private String contactName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_EMAIL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMAIL, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_EMAIL)
    private String email;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PHONE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHONE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_PHONE)
    private String phone;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDRESS, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_ADDRESS)
    private String address;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CITY, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_CITY)
    private String city;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STATE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_STATE)
    private String state;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ZIP_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ZIP_CODE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_ZIP_CODE)
    private String zipCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COUNTRY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNTRY, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_COUNTRY)
    private String country;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_CREDIT_LIMIT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_CREDIT_LIMIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREDIT_LIMIT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_CREDIT_LIMIT, scale = COLUMN_SCALE_CREDIT_LIMIT)
    private BigDecimal creditLimit;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE, nullable = false, insertable = true, updatable = true)
    @MsDefault("((1))")
    private Boolean isActive;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (getdate()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE, nullable = true, insertable = false, updatable = false)
    @MsDefault("(getdate())")
    private LocalDateTime createdDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_TYPE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_CUSTOMER_TYPE)
    @MsDefault("('Retail')")
    private String customerType;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_REP_ID, nullable = true, insertable = false, updatable = false)
    private Integer salesRepId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TERRITORY_ID, nullable = true, insertable = false, updatable = false)
    private Integer territoryId;
}

