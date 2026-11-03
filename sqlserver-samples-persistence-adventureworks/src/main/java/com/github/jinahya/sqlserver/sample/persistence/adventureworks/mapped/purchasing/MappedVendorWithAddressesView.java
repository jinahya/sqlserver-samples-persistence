package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.vVendorWithAddresses} view.
 * <p>
 * Identifier: the view declares no key; BusinessEntityID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405).
 */
@MappedSuperclass
public abstract class MappedVendorWithAddressesView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vVendorWithAddresses";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ---------------------------------------------------------------------------------------- Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // --------------------------------------------------------------------------------- AddressType

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS_TYPE = "AddressType";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_TYPE = "addressType";

    // -------------------------------------------------------------------------------- AddressLine1

    /**
     * The name of the {@code nvarchar(60), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS_LINE_1 = "AddressLine1";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS_LINE_1} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS_LINE_1 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS_LINE_1} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_LINE_1 = "addressLine1";

    // -------------------------------------------------------------------------------- AddressLine2

    /**
     * The name of the {@code nvarchar(60)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ADDRESS_LINE_2 = "AddressLine2";

    /**
     * The length of the {@value #COLUMN_NAME_ADDRESS_LINE_2} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS_LINE_2 = 60;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS_LINE_2} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS_LINE_2 = "addressLine2";

    // ---------------------------------------------------------------------------------------- City

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CITY = "City";

    /**
     * The length of the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CITY = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // --------------------------------------------------------------------------- StateProvinceName

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

    // ---------------------------------------------------------------------------------- PostalCode

    /**
     * The name of the {@code nvarchar(15), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSTAL_CODE = "PostalCode";

    /**
     * The length of the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_POSTAL_CODE = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CODE = "postalCode";

    // --------------------------------------------------------------------------- CountryRegionName

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

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedVendorWithAddressesView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",name=" + name
               + ",addressType=" + addressType
               + ",addressLine1=" + addressLine1
               + ",addressLine2=" + addressLine2
               + ",city=" + city
               + ",stateProvinceName=" + stateProvinceName
               + ",postalCode=" + postalCode
               + ",countryRegionName=" + countryRegionName
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
        if (!(obj instanceof MappedVendorWithAddressesView that)) {
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

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ---------------------------------------------------------------------------------------- name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    protected void setName(final String name) {
        this.name = name;
    }

    // --------------------------------------------------------------------------------- addressType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute.
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute with the specified value.
     *
     * @param addressType new value for {@value #ATTRIBUTE_NAME_ADDRESS_TYPE} attribute.
     */
    protected void setAddressType(final String addressType) {
        this.addressType = addressType;
    }

    // -------------------------------------------------------------------------------- addressLine1

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute with the specified value.
     *
     * @param addressLine1 new value for {@value #ATTRIBUTE_NAME_ADDRESS_LINE_1} attribute.
     */
    protected void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    // -------------------------------------------------------------------------------- addressLine2

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute.
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute with the specified value.
     *
     * @param addressLine2 new value for {@value #ATTRIBUTE_NAME_ADDRESS_LINE_2} attribute.
     */
    protected void setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
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
    protected void setCity(final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------- stateProvinceName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     */
    public String getStateProvinceName() {
        return stateProvinceName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute with the specified value.
     *
     * @param stateProvinceName new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_NAME} attribute.
     */
    protected void setStateProvinceName(final String stateProvinceName) {
        this.stateProvinceName = stateProvinceName;
    }

    // ---------------------------------------------------------------------------------- postalCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute with the specified value.
     *
     * @param postalCode new value for {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute.
     */
    protected void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    // --------------------------------------------------------------------------- countryRegionName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute.
     */
    public String getCountryRegionName() {
        return countryRegionName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute with the specified value.
     *
     * @param countryRegionName new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_NAME} attribute.
     */
    protected void setCountryRegionName(final String countryRegionName) {
        this.countryRegionName = countryRegionName;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDRESS_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ADDRESS_TYPE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ADDRESS_TYPE
    )
    private String addressType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDRESS_LINE_1)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ADDRESS_LINE_1,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ADDRESS_LINE_1
    )
    private String addressLine1;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ADDRESS_LINE_2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDRESS_LINE_2,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ADDRESS_LINE_2
    )
    private String addressLine2;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CITY,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STATE_PROVINCE_NAME
    )
    private String stateProvinceName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_POSTAL_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_POSTAL_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_POSTAL_CODE
    )
    private String postalCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COUNTRY_REGION_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COUNTRY_REGION_NAME
    )
    private String countryRegionName;
}

