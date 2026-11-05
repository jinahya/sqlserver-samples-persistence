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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Store} table.
 * <p>
 * The {@value #COLUMN_NAME_SALES_CHANNEL_ID} column is mapped read-only, as a foreign key; the extending entity owns
 * the writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.Store}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsActive}</th><td>{@code ((1))}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedStore {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Store";

    // ------------------------------------------------------------------------------------- StoreID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "StoreID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ----------------------------------------------------------------------------------- StoreCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_CODE = "StoreCode";

    /**
     * The length of the {@value #COLUMN_NAME_STORE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STORE_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_CODE = "storeCode";

    // ----------------------------------------------------------------------------------- StoreName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_NAME = "StoreName";

    /**
     * The length of the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STORE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_NAME = "storeName";

    // ------------------------------------------------------------------------------ SalesChannelID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_CHANNEL_ID = "SalesChannelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL_ID = "salesChannelId";

    // ------------------------------------------------------------------------------------- Manager

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MANAGER = "Manager";

    /**
     * The length of the {@value #COLUMN_NAME_MANAGER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MANAGER = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MANAGER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

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

    // ------------------------------------------------------------------------------------ OpenDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OPEN_DATE = "OpenDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OPEN_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OPEN_DATE = "openDate";

    // ------------------------------------------------------------------------------------ IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStore() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "storeId=" + storeId
               + ",storeCode=" + storeCode
               + ",storeName=" + storeName
               + ",salesChannelId=" + salesChannelId
               + ",manager=" + manager
               + ",phone=" + phone
               + ",email=" + email
               + ",address=" + address
               + ",city=" + city
               + ",state=" + state
               + ",zipCode=" + zipCode
               + ",openDate=" + openDate
               + ",isActive=" + isActive
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
        if (!(obj instanceof MappedStore that)) {
            return false;
        }
        return getStoreId() != null
               && getStoreId().equals(that.getStoreId());
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
        return MappedStore.class.hashCode();
    }

    // ------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified value.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(final Integer storeId) {
        this.storeId = storeId;
    }

    // ----------------------------------------------------------------------------------- storeCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_CODE} attribute.
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_CODE} attribute with the specified value.
     *
     * @param storeCode new value for {@value #ATTRIBUTE_NAME_STORE_CODE} attribute.
     */
    public void setStoreCode(final String storeCode) {
        this.storeCode = storeCode;
    }

    // ----------------------------------------------------------------------------------- storeName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_NAME} attribute with the specified value.
     *
     * @param storeName new value for {@value #ATTRIBUTE_NAME_STORE_NAME} attribute.
     */
    public void setStoreName(final String storeName) {
        this.storeName = storeName;
    }

    // ------------------------------------------------------------------------------ salesChannelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     */
    protected Integer getSalesChannelId() {
        return salesChannelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salesChannelId new value for {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     */
    protected void setSalesChannelId(final Integer salesChannelId) {
        this.salesChannelId = salesChannelId;
    }

    // ------------------------------------------------------------------------------------- manager

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public String getManager() {
        return manager;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute with the specified value.
     *
     * @param manager new value for {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public void setManager(final String manager) {
        this.manager = manager;
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

    // ------------------------------------------------------------------------------------ openDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OPEN_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OPEN_DATE} attribute.
     */
    public LocalDate getOpenDate() {
        return openDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OPEN_DATE} attribute with the specified value.
     *
     * @param openDate new value for {@value #ATTRIBUTE_NAME_OPEN_DATE} attribute.
     */
    public void setOpenDate(final LocalDate openDate) {
        this.openDate = openDate;
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

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_STORE_ID, nullable = false, insertable = false, updatable = false)
    private Integer storeId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STORE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STORE_CODE)
    private String storeCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STORE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STORE_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STORE_NAME)
    private String storeName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_CHANNEL_ID, nullable = false, insertable = false, updatable = false)
    private Integer salesChannelId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_MANAGER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MANAGER, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_MANAGER)
    private String manager;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PHONE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHONE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_PHONE)
    private String phone;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_EMAIL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EMAIL, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_EMAIL)
    private String email;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_OPEN_DATE, nullable = true, insertable = true, updatable = true)
    private LocalDate openDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE, nullable = false, insertable = true, updatable = true)
    @MsDefault("((1))")
    private Boolean isActive;
}

