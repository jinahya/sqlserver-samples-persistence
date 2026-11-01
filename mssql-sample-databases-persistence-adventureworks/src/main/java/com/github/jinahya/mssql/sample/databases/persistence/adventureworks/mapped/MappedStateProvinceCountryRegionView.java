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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Person.vStateProvinceCountryRegion} view.
 * <p>
 * Identifier: the view declares no key; StateProvinceID is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedStateProvinceCountryRegionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Person";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vStateProvinceCountryRegion";

    // ------------------------------------------------------------------------------------------------- StateProvinceID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
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

    // ----------------------------------------------------------------------------------------- IsOnlyStateProvinceFlag

    /**
     * The name of the {@code Flag (bit), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG = "IsOnlyStateProvinceFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ONLY_STATE_PROVINCE_FLAG} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ONLY_STATE_PROVINCE_FLAG = "isOnlyStateProvinceFlag";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStateProvinceCountryRegionView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stateProvinceId=" + stateProvinceId
               + ",stateProvinceCode=" + stateProvinceCode
               + ",isOnlyStateProvinceFlag=" + isOnlyStateProvinceFlag
               + ",stateProvinceName=" + stateProvinceName
               + ",territoryId=" + territoryId
               + ",countryRegionCode=" + countryRegionCode
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
        if (!(obj instanceof MappedStateProvinceCountryRegionView that)) {
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
    @Nonnull
    public Integer getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute with the specified value.
     *
     * @param stateProvinceId new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    protected void setStateProvinceId(@Nonnull final Integer stateProvinceId) {
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
    protected void setStateProvinceCode(@Nonnull final String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
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
    protected void setIsOnlyStateProvinceFlag(@Nonnull final Boolean isOnlyStateProvinceFlag) {
        this.isOnlyStateProvinceFlag = isOnlyStateProvinceFlag;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(@Nonnull final Integer territoryId) {
        this.territoryId = territoryId;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute with the specified value.
     *
     * @param countryRegionCode new value for {@value #ATTRIBUTE_NAME_COUNTRY_REGION_CODE} attribute.
     */
    protected void setCountryRegionCode(@Nonnull final String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
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

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
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
    @NotNull
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer territoryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_COUNTRY_REGION_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COUNTRY_REGION_CODE
    )
    private String countryRegionCode;

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
}

