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
 * A mapped superclass which holds the mappings of the {@code Production.Location} table.
 */
@MappedSuperclass
public abstract class MappedLocation {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Location";

    // ------------------------------------------------------------------------------------------------------ LocationID

    /**
     * The name of the {@code smallint, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LocationID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

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

    // -------------------------------------------------------------------------------------------------------- CostRate

    /**
     * The name of the {@code smallmoney, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COST_RATE = "CostRate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COST_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COST_RATE = "costRate";

    // ---------------------------------------------------------------------------------------------------- Availability

    /**
     * The name of the {@code decimal(8, 2), DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVAILABILITY = "Availability";

    /**
     * The precision of the {@value #COLUMN_NAME_AVAILABILITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVAILABILITY = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_AVAILABILITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVAILABILITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVAILABILITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVAILABILITY = "availability";

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
    protected MappedLocation() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "locationId=" + locationId
               + ",name=" + name
               + ",costRate=" + costRate
               + ",availability=" + availability
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
        if (!(obj instanceof MappedLocation that)) {
            return false;
        }
        return Objects.equals(locationId, that.locationId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(locationId);
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    @Nullable
    public Short getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified value.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    protected void setLocationId(@Nullable final Short locationId) {
        this.locationId = locationId;
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

    // -------------------------------------------------------------------------------------------------------- costRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COST_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COST_RATE} attribute.
     */
    @Nonnull
    public BigDecimal getCostRate() {
        return costRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COST_RATE} attribute with the specified value.
     *
     * @param costRate new value for {@value #ATTRIBUTE_NAME_COST_RATE} attribute.
     */
    public void setCostRate(@Nonnull final BigDecimal costRate) {
        this.costRate = costRate;
    }

    // ---------------------------------------------------------------------------------------------------- availability

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVAILABILITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVAILABILITY} attribute.
     */
    @Nonnull
    public BigDecimal getAvailability() {
        return availability;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVAILABILITY} attribute with the specified value.
     *
     * @param availability new value for {@value #ATTRIBUTE_NAME_AVAILABILITY} attribute.
     */
    public void setAvailability(@Nonnull final BigDecimal availability) {
        this.availability = availability;
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
    @Column(name = COLUMN_NAME_LOCATION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Short locationId;

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
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_COST_RATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal costRate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_AVAILABILITY - COLUMN_SCALE_AVAILABILITY, fraction = COLUMN_SCALE_AVAILABILITY)
    @NotNull
    @Column(name = COLUMN_NAME_AVAILABILITY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVAILABILITY,
            scale = COLUMN_SCALE_AVAILABILITY
    )
    private BigDecimal availability;

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

