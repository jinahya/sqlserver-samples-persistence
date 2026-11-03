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

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_Pipeline} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesPipelineView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_Pipeline";

    // --------------------------------------------------------------------------------------- Stage

    /**
     * The name of the {@code varchar(22), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STAGE = "Stage";

    /**
     * The length of the {@value #COLUMN_NAME_STAGE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STAGE = 22;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STAGE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STAGE = "stage";

    // ---------------------------------------------------------------------------------- StageOrder

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STAGE_ORDER = "StageOrder";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STAGE_ORDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STAGE_ORDER = "stageOrder";

    // --------------------------------------------------------------------------------------- Count

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COUNT = "Count";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNT = "count";

    // --------------------------------------------------------------------------------------- Value

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALUE = "Value";

    /**
     * The precision of the {@value #COLUMN_NAME_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_VALUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_VALUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_VALUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_VALUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_VALUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALUE = "value";

    // ----------------------------------------------------------------------------- PriorStageCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIOR_STAGE_COUNT = "PriorStageCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIOR_STAGE_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIOR_STAGE_COUNT = "priorStageCount";

    // ----------------------------------------------------------------------------- PriorStageValue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIOR_STAGE_VALUE = "PriorStageValue";

    /**
     * The precision of the {@value #COLUMN_NAME_PRIOR_STAGE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PRIOR_STAGE_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PRIOR_STAGE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PRIOR_STAGE_VALUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PRIOR_STAGE_VALUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PRIOR_STAGE_VALUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PRIOR_STAGE_VALUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PRIOR_STAGE_VALUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIOR_STAGE_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIOR_STAGE_VALUE = "priorStageValue";

    // ------------------------------------------------------------------------------ ConversionRate

    /**
     * The name of the {@code decimal(10, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONVERSION_RATE = "ConversionRate";

    /**
     * The precision of the {@value #COLUMN_NAME_CONVERSION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CONVERSION_RATE = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_CONVERSION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CONVERSION_RATE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_CONVERSION_RATE} attribute, the minimum of the
     * {@code decimal(10, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_CONVERSION_RATE = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_CONVERSION_RATE} attribute, the maximum of the
     * {@code decimal(10, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_CONVERSION_RATE = "99999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONVERSION_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONVERSION_RATE = "conversionRate";

    // -------------------------------------------------------------------------- ValueRetentionRate

    /**
     * The name of the {@code decimal(10, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALUE_RETENTION_RATE = "ValueRetentionRate";

    /**
     * The precision of the {@value #COLUMN_NAME_VALUE_RETENTION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_VALUE_RETENTION_RATE = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_VALUE_RETENTION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_VALUE_RETENTION_RATE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_VALUE_RETENTION_RATE} attribute, the minimum of the
     * {@code decimal(10, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_VALUE_RETENTION_RATE = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_VALUE_RETENTION_RATE} attribute, the maximum of the
     * {@code decimal(10, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_VALUE_RETENTION_RATE = "99999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALUE_RETENTION_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALUE_RETENTION_RATE = "valueRetentionRate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesPipelineView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stage=" + stage
               + ",stageOrder=" + stageOrder
               + ",count=" + count
               + ",value=" + value
               + ",priorStageCount=" + priorStageCount
               + ",priorStageValue=" + priorStageValue
               + ",conversionRate=" + conversionRate
               + ",valueRetentionRate=" + valueRetentionRate
               + "}";
    }

    // --------------------------------------------------------------------------------------- stage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STAGE} attribute.
     */
    public String getStage() {
        return stage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STAGE} attribute with the specified value.
     *
     * @param stage new value for {@value #ATTRIBUTE_NAME_STAGE} attribute.
     */
    protected void setStage(final String stage) {
        this.stage = stage;
    }

    // ---------------------------------------------------------------------------------- stageOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STAGE_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STAGE_ORDER} attribute.
     */
    public Integer getStageOrder() {
        return stageOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STAGE_ORDER} attribute with the specified value.
     *
     * @param stageOrder new value for {@value #ATTRIBUTE_NAME_STAGE_ORDER} attribute.
     */
    protected void setStageOrder(final Integer stageOrder) {
        this.stageOrder = stageOrder;
    }

    // --------------------------------------------------------------------------------------- count

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COUNT} attribute.
     */
    public Integer getCount() {
        return count;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COUNT} attribute with the specified value.
     *
     * @param count new value for {@value #ATTRIBUTE_NAME_COUNT} attribute.
     */
    protected void setCount(final Integer count) {
        this.count = count;
    }

    // --------------------------------------------------------------------------------------- value

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALUE} attribute.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALUE} attribute with the specified value.
     *
     * @param value new value for {@value #ATTRIBUTE_NAME_VALUE} attribute.
     */
    protected void setValue(final BigDecimal value) {
        this.value = value;
    }

    // ----------------------------------------------------------------------------- priorStageCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIOR_STAGE_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIOR_STAGE_COUNT} attribute.
     */
    public Integer getPriorStageCount() {
        return priorStageCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIOR_STAGE_COUNT} attribute with the specified value.
     *
     * @param priorStageCount new value for {@value #ATTRIBUTE_NAME_PRIOR_STAGE_COUNT} attribute.
     */
    protected void setPriorStageCount(final Integer priorStageCount) {
        this.priorStageCount = priorStageCount;
    }

    // ----------------------------------------------------------------------------- priorStageValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIOR_STAGE_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIOR_STAGE_VALUE} attribute.
     */
    public BigDecimal getPriorStageValue() {
        return priorStageValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIOR_STAGE_VALUE} attribute with the specified value.
     *
     * @param priorStageValue new value for {@value #ATTRIBUTE_NAME_PRIOR_STAGE_VALUE} attribute.
     */
    protected void setPriorStageValue(final BigDecimal priorStageValue) {
        this.priorStageValue = priorStageValue;
    }

    // ------------------------------------------------------------------------------ conversionRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONVERSION_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONVERSION_RATE} attribute.
     */
    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONVERSION_RATE} attribute with the specified value.
     *
     * @param conversionRate new value for {@value #ATTRIBUTE_NAME_CONVERSION_RATE} attribute.
     */
    protected void setConversionRate(final BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }

    // -------------------------------------------------------------------------- valueRetentionRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALUE_RETENTION_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALUE_RETENTION_RATE} attribute.
     */
    public BigDecimal getValueRetentionRate() {
        return valueRetentionRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALUE_RETENTION_RATE} attribute with the specified value.
     *
     * @param valueRetentionRate new value for {@value #ATTRIBUTE_NAME_VALUE_RETENTION_RATE} attribute.
     */
    protected void setValueRetentionRate(final BigDecimal valueRetentionRate) {
        this.valueRetentionRate = valueRetentionRate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STAGE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STAGE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_STAGE)
    private String stage;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STAGE_ORDER, nullable = false, insertable = false, updatable = false)
    private Integer stageOrder;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COUNT, nullable = true, insertable = false, updatable = false)
    private Integer count;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_VALUE, scale = COLUMN_SCALE_VALUE)
    private BigDecimal value;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIOR_STAGE_COUNT, nullable = true, insertable = false, updatable = false)
    private Integer priorStageCount;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PRIOR_STAGE_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PRIOR_STAGE_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRIOR_STAGE_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PRIOR_STAGE_VALUE, scale = COLUMN_SCALE_PRIOR_STAGE_VALUE)
    private BigDecimal priorStageValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_CONVERSION_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_CONVERSION_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CONVERSION_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_CONVERSION_RATE, scale = COLUMN_SCALE_CONVERSION_RATE)
    private BigDecimal conversionRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_VALUE_RETENTION_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_VALUE_RETENTION_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_VALUE_RETENTION_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_VALUE_RETENTION_RATE, scale = COLUMN_SCALE_VALUE_RETENTION_RATE)
    private BigDecimal valueRetentionRate;
}

