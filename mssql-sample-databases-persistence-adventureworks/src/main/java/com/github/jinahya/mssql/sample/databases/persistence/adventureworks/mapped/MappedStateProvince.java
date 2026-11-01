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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.StateProvince} table.
 * <p>
 * The {@value #COLUMN_NAME_COUNTRY_REGION_CODE}, {@value #COLUMN_NAME_TERRITORY_ID} columns are mapped read-only, as a
 * foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedStateProvince {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Person";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StateProvince";

    // ------------------------------------------------------------------------------------------------- StateProvinceID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_ID = "StateProvinceID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_ID = "stateProvinceId";

    // ----------------------------------------------------------------------------------------------- StateProvinceCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_CODE = "StateProvinceCode";

    /**
     * The length of the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATE_PROVINCE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_CODE = "stateProvinceCode";

    // ----------------------------------------------------------------------------------------------- CountryRegionCode

    /**
     * The name of the {@code nvarchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNTRY_REGION_CODE = "CountryRegionCode";

    /**
     * The length of the {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COUNTRY_REGION_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNTRY_REGION_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION_CODE = "countryRegionCode";

    // ----------------------------------------------------------------------------------------- IsOnlyStateProvinceFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG = "IsOnlyStateProvinceFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG = "isOnlyStateProvinceFlag";

    // ------------------------------------------------------------------------------------------------------------ Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ----------------------------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
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
    protected MappedStateProvince() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stateProvinceId=" + stateProvinceId
               + ",stateProvinceCode=" + stateProvinceCode
               + ",countryRegionCode=" + countryRegionCode
               + ",isOnlyStateProvinceFlag=" + isOnlyStateProvinceFlag
               + ",name=" + name
               + ",territoryId=" + territoryId
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
        if (!(obj instanceof MappedStateProvince that)) {
            return false;
        }
        return Objects.equals(stateProvinceId, that.stateProvinceId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(stateProvinceId);
    }

    // ------------------------------------------------------------------------------------------------- stateProvinceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    @Nullable
    public Integer getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute with the specified value.
     *
     * @param stateProvinceId new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    protected void setStateProvinceId(@Nullable final Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    // ----------------------------------------------------------------------------------------------- stateProvinceCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute.
     */
    @Nonnull
    public String getStateProvinceCode() {
        return stateProvinceCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute with the specified value.
     *
     * @param stateProvinceCode new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_CODE} attribute.
     */
    public void setStateProvinceCode(@Nonnull final String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }

    // ----------------------------------------------------------------------------------------------- countryRegionCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     */
    @Nonnull
    public String getCountryRegionCode() {
        return countryRegionCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param countryRegionCode new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     */
    protected void setCountryRegionCode(@Nonnull final String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
    }

    // ----------------------------------------------------------------------------------------- isOnlyStateProvinceFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute.
     */
    @Nonnull
    public Boolean getIsOnlyStateProvinceFlag() {
        return isOnlyStateProvinceFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute with the specified
     * value.
     *
     * @param isOnlyStateProvinceFlag new value for {@value #ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG} attribute.
     */
    public void setIsOnlyStateProvinceFlag(@Nonnull final Boolean isOnlyStateProvinceFlag) {
        this.isOnlyStateProvinceFlag = isOnlyStateProvinceFlag;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setName(@Nonnull final String name) {
        this.name = name;
    }

    // ----------------------------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    @Nonnull
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(@Nonnull final Integer territoryId) {
        this.territoryId = territoryId;
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
    public void setRowguid(@Nonnull final UUID rowguid) {
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
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer stateProvinceId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_STATE_PROVINCE_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_STATE_PROVINCE_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATE_PROVINCE_CODE
    )
    private String stateProvinceCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_REGION_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_COUNTRY_REGION_CODE
    )
    private String countryRegionCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isOnlyStateProvinceFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer territoryId;

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

