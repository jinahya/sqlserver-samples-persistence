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
 * A mapped superclass which holds the mappings of the {@code dbo.UnitOfMeasure} table.
 */
@MappedSuperclass
public abstract class MappedUnitOfMeasure {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "UnitOfMeasure";

    // ---------------------------------------------------------------------------------------------------------- UnitID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_ID = "UnitID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_ID = "unitId";

    // -------------------------------------------------------------------------------------------------------- UnitCode

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_CODE = "UnitCode";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_CODE = "unitCode";

    // -------------------------------------------------------------------------------------------------------- UnitName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_NAME = "UnitName";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_NAME = "unitName";

    // ----------------------------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedUnitOfMeasure() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "unitId=" + unitId
               + ",unitCode=" + unitCode
               + ",unitName=" + unitName
               + ",description=" + description
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
        if (!(obj instanceof MappedUnitOfMeasure that)) {
            return false;
        }
        return Objects.equals(unitId, that.unitId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(unitId);
    }

    // ---------------------------------------------------------------------------------------------------------- unitId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     */
    public Integer getUnitId() {
        return unitId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_ID} attribute with the specified value.
     *
     * @param unitId new value for {@value #ATTRIBUTE_NAME_UNIT_ID} attribute.
     */
    protected void setUnitId(final Integer unitId) {
        this.unitId = unitId;
    }

    // -------------------------------------------------------------------------------------------------------- unitCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute with the specified value.
     *
     * @param unitCode new value for {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    public void setUnitCode(final String unitCode) {
        this.unitCode = unitCode;
    }

    // -------------------------------------------------------------------------------------------------------- unitName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_NAME} attribute.
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_NAME} attribute with the specified value.
     *
     * @param unitName new value for {@value #ATTRIBUTE_NAME_UNIT_NAME} attribute.
     */
    public void setUnitName(final String unitName) {
        this.unitName = unitName;
    }

    // ----------------------------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_UNIT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer unitId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_UNIT_CODE
    )
    private String unitCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_UNIT_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_UNIT_NAME
    )
    private String unitName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DESCRIPTION
    )
    private String description;
}

