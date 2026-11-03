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
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.WorkCenter} table.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.WorkCenter}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsActive}</th><td>{@code ((1))}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedWorkCenter {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "WorkCenter";

    // -------------------------------------------------------------------------------- WorkCenterID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_ID = "WorkCenterID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_ID = "workCenterId";

    // ------------------------------------------------------------------------------ WorkCenterCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_CODE = "WorkCenterCode";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_CENTER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_CENTER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_CODE = "workCenterCode";

    // ------------------------------------------------------------------------------ WorkCenterName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_NAME = "WorkCenterName";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_CENTER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_CENTER_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_NAME = "workCenterName";

    // --------------------------------------------------------------------------------- Description

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

    // ------------------------------------------------------------------------------------ Capacity

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CAPACITY = "Capacity";

    /**
     * The precision of the {@value #COLUMN_NAME_CAPACITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CAPACITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_CAPACITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CAPACITY = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_CAPACITY} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_CAPACITY = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_CAPACITY} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_CAPACITY = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CAPACITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CAPACITY = "capacity";

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
    protected MappedWorkCenter() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workCenterId=" + workCenterId
               + ",workCenterCode=" + workCenterCode
               + ",workCenterName=" + workCenterName
               + ",description=" + description
               + ",capacity=" + capacity
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
        if (!(obj instanceof MappedWorkCenter that)) {
            return false;
        }
        return Objects.equals(workCenterId, that.workCenterId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(workCenterId);
    }

    // -------------------------------------------------------------------------------- workCenterId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     */
    public Integer getWorkCenterId() {
        return workCenterId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute with the specified value.
     *
     * @param workCenterId new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_ID} attribute.
     */
    protected void setWorkCenterId(final Integer workCenterId) {
        this.workCenterId = workCenterId;
    }

    // ------------------------------------------------------------------------------ workCenterCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute.
     */
    public String getWorkCenterCode() {
        return workCenterCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute with the specified value.
     *
     * @param workCenterCode new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute.
     */
    public void setWorkCenterCode(final String workCenterCode) {
        this.workCenterCode = workCenterCode;
    }

    // ------------------------------------------------------------------------------ workCenterName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     */
    public String getWorkCenterName() {
        return workCenterName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute with the specified value.
     *
     * @param workCenterName new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     */
    public void setWorkCenterName(final String workCenterName) {
        this.workCenterName = workCenterName;
    }

    // --------------------------------------------------------------------------------- description

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

    // ------------------------------------------------------------------------------------ capacity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CAPACITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CAPACITY} attribute.
     */
    public BigDecimal getCapacity() {
        return capacity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CAPACITY} attribute with the specified value.
     *
     * @param capacity new value for {@value #ATTRIBUTE_NAME_CAPACITY} attribute.
     */
    public void setCapacity(final BigDecimal capacity) {
        this.capacity = capacity;
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
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_WORK_CENTER_ID, nullable = false, insertable = true, updatable = false)
    private Integer workCenterId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WORK_CENTER_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WORK_CENTER_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_WORK_CENTER_CODE)
    private String workCenterCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_WORK_CENTER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WORK_CENTER_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_WORK_CENTER_NAME)
    private String workCenterName;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_DESCRIPTION)
    private String description;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_CAPACITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_CAPACITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CAPACITY, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_CAPACITY, scale = COLUMN_SCALE_CAPACITY)
    private BigDecimal capacity;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE, nullable = false, insertable = true, updatable = true)
    @MsDefault("((1))")
    private Boolean isActive;
}

