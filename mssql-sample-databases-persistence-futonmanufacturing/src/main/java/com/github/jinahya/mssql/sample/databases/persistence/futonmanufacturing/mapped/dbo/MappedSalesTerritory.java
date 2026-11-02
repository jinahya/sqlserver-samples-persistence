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
 * A mapped superclass which holds the mappings of the {@code dbo.SalesTerritory} table.
 */
@MappedSuperclass
public abstract class MappedSalesTerritory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesTerritory";

    // ----------------------------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // --------------------------------------------------------------------------------------------------- TerritoryCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_CODE = "TerritoryCode";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_CODE = "territoryCode";

    // --------------------------------------------------------------------------------------------------- TerritoryName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_NAME = "TerritoryName";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_NAME = "territoryName";

    // ---------------------------------------------------------------------------------------------------------- Region

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REGION = "Region";

    /**
     * The length of the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REGION = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REGION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REGION = "region";

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
    protected MappedSalesTerritory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "territoryId=" + territoryId
               + ",territoryCode=" + territoryCode
               + ",territoryName=" + territoryName
               + ",region=" + region
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
        if (!(obj instanceof MappedSalesTerritory that)) {
            return false;
        }
        return Objects.equals(territoryId, that.territoryId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(territoryId);
    }

    // ----------------------------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // --------------------------------------------------------------------------------------------------- territoryCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     */
    public String getTerritoryCode() {
        return territoryCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute with the specified value.
     *
     * @param territoryCode new value for {@value #ATTRIBUTE_NAME_TERRITORY_CODE} attribute.
     */
    public void setTerritoryCode(final String territoryCode) {
        this.territoryCode = territoryCode;
    }

    // --------------------------------------------------------------------------------------------------- territoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    public String getTerritoryName() {
        return territoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute with the specified value.
     *
     * @param territoryName new value for {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    public void setTerritoryName(final String territoryName) {
        this.territoryName = territoryName;
    }

    // ---------------------------------------------------------------------------------------------------------- region

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REGION} attribute with the specified value.
     *
     * @param region new value for {@value #ATTRIBUTE_NAME_REGION} attribute.
     */
    public void setRegion(final String region) {
        this.region = region;
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
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer territoryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TERRITORY_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TERRITORY_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TERRITORY_CODE
    )
    private String territoryCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TERRITORY_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TERRITORY_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TERRITORY_NAME
    )
    private String territoryName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REGION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REGION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REGION
    )
    private String region;

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

