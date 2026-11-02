package com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.warehouse;

/*-
 * #%L
 * sql-server-samples-persistence-wideworldimporters
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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.ColdRoomTemperatures} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Warehouse.ColdRoomTemperatures_Archive}.
 */
@MappedSuperclass
public abstract class MappedColdRoomTemperature {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ColdRoomTemperatures";

    // ------------------------------------------------------------------------------------------- ColdRoomTemperatureID

    /**
     * The name of the {@code bigint, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLD_ROOM_TEMPERATURE_ID = "ColdRoomTemperatureID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLD_ROOM_TEMPERATURE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLD_ROOM_TEMPERATURE_ID = "coldRoomTemperatureId";

    // -------------------------------------------------------------------------------------------- ColdRoomSensorNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLD_ROOM_SENSOR_NUMBER = "ColdRoomSensorNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLD_ROOM_SENSOR_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLD_ROOM_SENSOR_NUMBER = "coldRoomSensorNumber";

    // ---------------------------------------------------------------------------------------------------- RecordedWhen

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECORDED_WHEN = "RecordedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECORDED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECORDED_WHEN = "recordedWhen";

    // ----------------------------------------------------------------------------------------------------- Temperature

    /**
     * The name of the {@code decimal(10, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TEMPERATURE = "Temperature";

    /**
     * The precision of the {@value #COLUMN_NAME_TEMPERATURE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TEMPERATURE = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_TEMPERATURE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TEMPERATURE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TEMPERATURE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TEMPERATURE = "temperature";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedColdRoomTemperature() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "coldRoomTemperatureId=" + coldRoomTemperatureId
               + ",coldRoomSensorNumber=" + coldRoomSensorNumber
               + ",recordedWhen=" + recordedWhen
               + ",temperature=" + temperature
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
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
        if (!(obj instanceof MappedColdRoomTemperature that)) {
            return false;
        }
        return Objects.equals(coldRoomTemperatureId, that.coldRoomTemperatureId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(coldRoomTemperatureId);
    }

    // ------------------------------------------------------------------------------------------- coldRoomTemperatureId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COLD_ROOM_TEMPERATURE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COLD_ROOM_TEMPERATURE_ID} attribute.
     */
    public Long getColdRoomTemperatureId() {
        return coldRoomTemperatureId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLD_ROOM_TEMPERATURE_ID} attribute with the specified value.
     *
     * @param coldRoomTemperatureId new value for {@value #ATTRIBUTE_NAME_COLD_ROOM_TEMPERATURE_ID} attribute.
     */
    protected void setColdRoomTemperatureId(final Long coldRoomTemperatureId) {
        this.coldRoomTemperatureId = coldRoomTemperatureId;
    }

    // -------------------------------------------------------------------------------------------- coldRoomSensorNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COLD_ROOM_SENSOR_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COLD_ROOM_SENSOR_NUMBER} attribute.
     */
    public Integer getColdRoomSensorNumber() {
        return coldRoomSensorNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLD_ROOM_SENSOR_NUMBER} attribute with the specified value.
     *
     * @param coldRoomSensorNumber new value for {@value #ATTRIBUTE_NAME_COLD_ROOM_SENSOR_NUMBER} attribute.
     */
    public void setColdRoomSensorNumber(final Integer coldRoomSensorNumber) {
        this.coldRoomSensorNumber = coldRoomSensorNumber;
    }

    // ---------------------------------------------------------------------------------------------------- recordedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECORDED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECORDED_WHEN} attribute.
     */
    public LocalDateTime getRecordedWhen() {
        return recordedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECORDED_WHEN} attribute with the specified value.
     *
     * @param recordedWhen new value for {@value #ATTRIBUTE_NAME_RECORDED_WHEN} attribute.
     */
    public void setRecordedWhen(final LocalDateTime recordedWhen) {
        this.recordedWhen = recordedWhen;
    }

    // ----------------------------------------------------------------------------------------------------- temperature

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TEMPERATURE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TEMPERATURE} attribute.
     */
    public BigDecimal getTemperature() {
        return temperature;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TEMPERATURE} attribute with the specified value.
     *
     * @param temperature new value for {@value #ATTRIBUTE_NAME_TEMPERATURE} attribute.
     */
    public void setTemperature(final BigDecimal temperature) {
        this.temperature = temperature;
    }

    // ------------------------------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // --------------------------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_COLD_ROOM_TEMPERATURE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Long coldRoomTemperatureId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COLD_ROOM_SENSOR_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer coldRoomSensorNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RECORDED_WHEN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime recordedWhen;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TEMPERATURE - COLUMN_SCALE_TEMPERATURE, fraction = COLUMN_SCALE_TEMPERATURE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TEMPERATURE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TEMPERATURE,
            scale = COLUMN_SCALE_TEMPERATURE
    )
    private BigDecimal temperature;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validTo;
}

