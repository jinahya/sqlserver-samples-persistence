package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.vProductModelInstructions}
 * view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native
 * query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of
 * it is insertable or updatable; some of its columns are derived or constant (error 4406); one of
 * its columns is an identity column of its base table, which no UPDATE sets (error 8102).
 */
@MappedSuperclass
public abstract class MappedProductModelInstructionsView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vProductModelInstructions";

    // ------------------------------------------------------------------------------ ProductModelID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // -------------------------------------------------------------------------------- Instructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSTRUCTIONS = "Instructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSTRUCTIONS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSTRUCTIONS = "instructions";

    // ---------------------------------------------------------------------------------- LocationID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LocationID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // ---------------------------------------------------------------------------------- SetupHours

    /**
     * The name of the {@code decimal(9, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SETUP_HOURS = "SetupHours";

    /**
     * The precision of the {@value #COLUMN_NAME_SETUP_HOURS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SETUP_HOURS = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_SETUP_HOURS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SETUP_HOURS = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SETUP_HOURS} attribute, the minimum of the
     * {@code decimal(9, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SETUP_HOURS = "-99999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SETUP_HOURS} attribute, the maximum of the
     * {@code decimal(9, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SETUP_HOURS = "99999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SETUP_HOURS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SETUP_HOURS = "setupHours";

    // -------------------------------------------------------------------------------- MachineHours

    /**
     * The name of the {@code decimal(9, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MACHINE_HOURS = "MachineHours";

    /**
     * The precision of the {@value #COLUMN_NAME_MACHINE_HOURS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MACHINE_HOURS = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_MACHINE_HOURS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MACHINE_HOURS = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_MACHINE_HOURS} attribute, the minimum of the
     * {@code decimal(9, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_MACHINE_HOURS = "-99999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MACHINE_HOURS} attribute, the maximum of the
     * {@code decimal(9, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_MACHINE_HOURS = "99999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MACHINE_HOURS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MACHINE_HOURS = "machineHours";

    // ---------------------------------------------------------------------------------- LaborHours

    /**
     * The name of the {@code decimal(9, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LABOR_HOURS = "LaborHours";

    /**
     * The precision of the {@value #COLUMN_NAME_LABOR_HOURS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LABOR_HOURS = 9;

    /**
     * The scale of the {@value #COLUMN_NAME_LABOR_HOURS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LABOR_HOURS = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LABOR_HOURS} attribute, the minimum of the
     * {@code decimal(9, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LABOR_HOURS = "-99999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LABOR_HOURS} attribute, the maximum of the
     * {@code decimal(9, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LABOR_HOURS = "99999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LABOR_HOURS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LABOR_HOURS = "laborHours";

    // ------------------------------------------------------------------------------------- LotSize

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOT_SIZE = "LotSize";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOT_SIZE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOT_SIZE = "lotSize";

    // ---------------------------------------------------------------------------------------- Step

    /**
     * The name of the {@code nvarchar(1024)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STEP = "Step";

    /**
     * The length of the {@value #COLUMN_NAME_STEP} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STEP = 1024;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STEP} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STEP = "step";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductModelInstructionsView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",name=" + name
               + ",instructions=" + instructions
               + ",locationId=" + locationId
               + ",setupHours=" + setupHours
               + ",machineHours=" + machineHours
               + ",laborHours=" + laborHours
               + ",lotSize=" + lotSize
               + ",step=" + step
               + ",rowguid=" + rowguid
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    // ------------------------------------------------------------------------------ productModelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the
     * specified value.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    protected void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
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

    // -------------------------------------------------------------------------------- instructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute.
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute with the specified
     * value.
     *
     * @param instructions new value for {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute.
     */
    protected void setInstructions(final String instructions) {
        this.instructions = instructions;
    }

    // ---------------------------------------------------------------------------------- locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified
     * value.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    protected void setLocationId(final Integer locationId) {
        this.locationId = locationId;
    }

    // ---------------------------------------------------------------------------------- setupHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SETUP_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SETUP_HOURS} attribute.
     */
    public BigDecimal getSetupHours() {
        return setupHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SETUP_HOURS} attribute with the specified
     * value.
     *
     * @param setupHours new value for {@value #ATTRIBUTE_NAME_SETUP_HOURS} attribute.
     */
    protected void setSetupHours(final BigDecimal setupHours) {
        this.setupHours = setupHours;
    }

    // -------------------------------------------------------------------------------- machineHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MACHINE_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MACHINE_HOURS} attribute.
     */
    public BigDecimal getMachineHours() {
        return machineHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MACHINE_HOURS} attribute with the specified
     * value.
     *
     * @param machineHours new value for {@value #ATTRIBUTE_NAME_MACHINE_HOURS} attribute.
     */
    protected void setMachineHours(final BigDecimal machineHours) {
        this.machineHours = machineHours;
    }

    // ---------------------------------------------------------------------------------- laborHours

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LABOR_HOURS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LABOR_HOURS} attribute.
     */
    public BigDecimal getLaborHours() {
        return laborHours;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LABOR_HOURS} attribute with the specified
     * value.
     *
     * @param laborHours new value for {@value #ATTRIBUTE_NAME_LABOR_HOURS} attribute.
     */
    protected void setLaborHours(final BigDecimal laborHours) {
        this.laborHours = laborHours;
    }

    // ------------------------------------------------------------------------------------- lotSize

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOT_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOT_SIZE} attribute.
     */
    public Integer getLotSize() {
        return lotSize;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOT_SIZE} attribute with the specified
     * value.
     *
     * @param lotSize new value for {@value #ATTRIBUTE_NAME_LOT_SIZE} attribute.
     */
    protected void setLotSize(final Integer lotSize) {
        this.lotSize = lotSize;
    }

    // ---------------------------------------------------------------------------------------- step

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STEP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STEP} attribute.
     */
    public String getStep() {
        return step;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STEP} attribute with the specified value.
     *
     * @param step new value for {@value #ATTRIBUTE_NAME_STEP} attribute.
     */
    protected void setStep(final String step) {
        this.step = step;
    }

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified
     * value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    protected void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID, nullable = false, insertable = false,
            updatable = false)
    private Integer productModelId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = false, updatable = false,
            length = COLUMN_LENGTH_NAME)
    private String name;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 1076 bytes (538 characters)
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INSTRUCTIONS, nullable = true, insertable = false, updatable = false)
    private String instructions;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = true, insertable = false, updatable = false)
    private Integer locationId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SETUP_HOURS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SETUP_HOURS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SETUP_HOURS, nullable = true, insertable = false, updatable = false,
            precision = COLUMN_PRECISION_SETUP_HOURS, scale = COLUMN_SCALE_SETUP_HOURS)
    private BigDecimal setupHours;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_MACHINE_HOURS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_MACHINE_HOURS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MACHINE_HOURS, nullable = true, insertable = false,
            updatable = false, precision = COLUMN_PRECISION_MACHINE_HOURS,
            scale = COLUMN_SCALE_MACHINE_HOURS)
    private BigDecimal machineHours;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_LABOR_HOURS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_LABOR_HOURS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LABOR_HOURS, nullable = true, insertable = false, updatable = false,
            precision = COLUMN_PRECISION_LABOR_HOURS, scale = COLUMN_SCALE_LABOR_HOURS)
    private BigDecimal laborHours;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LOT_SIZE, nullable = true, insertable = false, updatable = false)
    private Integer lotSize;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STEP)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STEP, nullable = true, insertable = false, updatable = false,
            length = COLUMN_LENGTH_STEP)
    private String step;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = false)
    private LocalDateTime modifiedDate;
}

