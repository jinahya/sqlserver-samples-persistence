package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Warehouse} table.
 */
@MappedSuperclass
public abstract class MappedWarehouse {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Warehouse";

    // ----------------------------------------------------------------------------------------------------- WarehouseID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_ID = "WarehouseID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_ID = "warehouseId";

    // --------------------------------------------------------------------------------------------------- WarehouseCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_CODE = "WarehouseCode";

    /**
     * The length of the {@value #COLUMN_NAME_WAREHOUSE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WAREHOUSE_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_CODE = "warehouseCode";

    // --------------------------------------------------------------------------------------------------- WarehouseName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_NAME = "WarehouseName";

    /**
     * The length of the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WAREHOUSE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_NAME = "warehouseName";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_CITY} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE} column. The value is {@value}.
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
     * The name of the attribute which maps the {@value #COLUMN_NAME_ZIP_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ZIP_CODE = "zipCode";

    // -------------------------------------------------------------------------------------------------------- IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWarehouse() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "warehouseId=" + warehouseId
               + ",warehouseCode=" + warehouseCode
               + ",warehouseName=" + warehouseName
               + ",address=" + address
               + ",city=" + city
               + ",state=" + state
               + ",zipCode=" + zipCode
               + ",isActive=" + isActive
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
        if (!(obj instanceof MappedWarehouse that)) {
            return false;
        }
        return Objects.equals(warehouseId, that.warehouseId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(warehouseId);
    }

    // ----------------------------------------------------------------------------------------------------- warehouseId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute with the specified value.
     *
     * @param warehouseId new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected void setWarehouseId(final Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    // --------------------------------------------------------------------------------------------------- warehouseCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute.
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute with the specified value.
     *
     * @param warehouseCode new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute.
     */
    public void setWarehouseCode(final String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    // --------------------------------------------------------------------------------------------------- warehouseName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute with the specified value.
     *
     * @param warehouseName new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    public void setWarehouseName(final String warehouseName) {
        this.warehouseName = warehouseName;
    }

    // --------------------------------------------------------------------------------------------------------- address

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

    // ------------------------------------------------------------------------------------------------------------ city

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

    // ----------------------------------------------------------------------------------------------------------- state

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

    // --------------------------------------------------------------------------------------------------------- zipCode

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

    // -------------------------------------------------------------------------------------------------------- isActive

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

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_WAREHOUSE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer warehouseId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WAREHOUSE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_WAREHOUSE_CODE
    )
    private String warehouseCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WAREHOUSE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_WAREHOUSE_NAME
    )
    private String warehouseName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ADDRESS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDRESS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ADDRESS
    )
    private String address;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CITY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CITY
    )
    private String city;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STATE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATE
    )
    private String state;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ZIP_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ZIP_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ZIP_CODE
    )
    private String zipCode;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isActive;
}

