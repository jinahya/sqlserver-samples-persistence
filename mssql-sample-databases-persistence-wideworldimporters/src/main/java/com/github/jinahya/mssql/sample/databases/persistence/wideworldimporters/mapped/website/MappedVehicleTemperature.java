package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.website;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Website.VehicleTemperatures} view.
 * <p>
 * Identifier: the view declares no key; VehicleTemperatureID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable,
 * and none but the identifier, of which EclipseLink requires a writable mapping, is insertable; some of its columns
 * are derived or constant (error 4406); one of its columns is an identity column of its base table, which no UPDATE
 * sets (error 8102).
 */
@MappedSuperclass
public abstract class MappedVehicleTemperature {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Website";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "VehicleTemperatures";

    // -------------------------------------------------------------------------------------------- VehicleTemperatureID

    /**
     * The name of the {@code bigint, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VEHICLE_TEMPERATURE_ID = "VehicleTemperatureID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VEHICLE_TEMPERATURE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VEHICLE_TEMPERATURE_ID = "vehicleTemperatureId";

    // --------------------------------------------------------------------------------------------- VehicleRegistration

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VEHICLE_REGISTRATION = "VehicleRegistration";

    /**
     * The length of the {@value #COLUMN_NAME_VEHICLE_REGISTRATION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_VEHICLE_REGISTRATION = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VEHICLE_REGISTRATION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VEHICLE_REGISTRATION = "vehicleRegistration";

    // --------------------------------------------------------------------------------------------- ChillerSensorNumber

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHILLER_SENSOR_NUMBER = "ChillerSensorNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHILLER_SENSOR_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHILLER_SENSOR_NUMBER = "chillerSensorNumber";

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

    // -------------------------------------------------------------------------------------------------- FullSensorData

    /**
     * The name of the {@code nvarchar(1000)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FULL_SENSOR_DATA = "FullSensorData";

    /**
     * The length of the {@value #COLUMN_NAME_FULL_SENSOR_DATA} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FULL_SENSOR_DATA = 1000;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULL_SENSOR_DATA} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULL_SENSOR_DATA = "fullSensorData";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedVehicleTemperature() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "vehicleTemperatureId=" + vehicleTemperatureId
               + ",vehicleRegistration=" + vehicleRegistration
               + ",chillerSensorNumber=" + chillerSensorNumber
               + ",recordedWhen=" + recordedWhen
               + ",temperature=" + temperature
               + ",fullSensorData=" + fullSensorData
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
        if (!(obj instanceof MappedVehicleTemperature that)) {
            return false;
        }
        return Objects.equals(vehicleTemperatureId, that.vehicleTemperatureId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(vehicleTemperatureId);
    }

    // -------------------------------------------------------------------------------------------- vehicleTemperatureId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VEHICLE_TEMPERATURE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VEHICLE_TEMPERATURE_ID} attribute.
     */
    public Long getVehicleTemperatureId() {
        return vehicleTemperatureId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VEHICLE_TEMPERATURE_ID} attribute with the specified value.
     *
     * @param vehicleTemperatureId new value for {@value #ATTRIBUTE_NAME_VEHICLE_TEMPERATURE_ID} attribute.
     */
    protected void setVehicleTemperatureId(final Long vehicleTemperatureId) {
        this.vehicleTemperatureId = vehicleTemperatureId;
    }

    // --------------------------------------------------------------------------------------------- vehicleRegistration

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VEHICLE_REGISTRATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VEHICLE_REGISTRATION} attribute.
     */
    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VEHICLE_REGISTRATION} attribute with the specified value.
     *
     * @param vehicleRegistration new value for {@value #ATTRIBUTE_NAME_VEHICLE_REGISTRATION} attribute.
     */
    protected void setVehicleRegistration(final String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    // --------------------------------------------------------------------------------------------- chillerSensorNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHILLER_SENSOR_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHILLER_SENSOR_NUMBER} attribute.
     */
    public Integer getChillerSensorNumber() {
        return chillerSensorNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHILLER_SENSOR_NUMBER} attribute with the specified value.
     *
     * @param chillerSensorNumber new value for {@value #ATTRIBUTE_NAME_CHILLER_SENSOR_NUMBER} attribute.
     */
    protected void setChillerSensorNumber(final Integer chillerSensorNumber) {
        this.chillerSensorNumber = chillerSensorNumber;
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
    protected void setRecordedWhen(final LocalDateTime recordedWhen) {
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
    protected void setTemperature(final BigDecimal temperature) {
        this.temperature = temperature;
    }

    // -------------------------------------------------------------------------------------------------- fullSensorData

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULL_SENSOR_DATA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULL_SENSOR_DATA} attribute.
     */
    public String getFullSensorData() {
        return fullSensorData;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULL_SENSOR_DATA} attribute with the specified value.
     *
     * @param fullSensorData new value for {@value #ATTRIBUTE_NAME_FULL_SENSOR_DATA} attribute.
     */
    protected void setFullSensorData(final String fullSensorData) {
        this.fullSensorData = fullSensorData;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only but for an INSERT: SQL Server does not write this view, and EclipseLink requires a writable mapping
    // of every identifier column; see the class documentation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_VEHICLE_TEMPERATURE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Long vehicleTemperatureId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_VEHICLE_REGISTRATION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VEHICLE_REGISTRATION,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_VEHICLE_REGISTRATION
    )
    private String vehicleRegistration;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHILLER_SENSOR_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer chillerSensorNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RECORDED_WHEN,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime recordedWhen;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TEMPERATURE - COLUMN_SCALE_TEMPERATURE, fraction = COLUMN_SCALE_TEMPERATURE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TEMPERATURE,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TEMPERATURE,
            scale = COLUMN_SCALE_TEMPERATURE
    )
    private BigDecimal temperature;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_FULL_SENSOR_DATA)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FULL_SENSOR_DATA,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FULL_SENSOR_DATA
    )
    private String fullSensorData;
}

