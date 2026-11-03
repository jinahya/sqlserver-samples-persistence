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
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_WhereUsed} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedWhereUsedView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_WhereUsed";

    // ----------------------------------------------------------------------------- ComponentItemID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_ITEM_ID = "ComponentItemID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_COMPONENT_ITEM_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_COMPONENT_ITEM_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ITEM_ID = "componentItemId";

    // --------------------------------------------------------------------------- ComponentItemCode

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_ITEM_CODE = "ComponentItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_COMPONENT_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMPONENT_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_ITEM_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ITEM_CODE = "componentItemCode";

    // --------------------------------------------------------------------------- ComponentItemName

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_ITEM_NAME = "ComponentItemName";

    /**
     * The length of the {@value #COLUMN_NAME_COMPONENT_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMPONENT_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_ITEM_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ITEM_NAME = "componentItemName";

    // -------------------------------------------------------------------------------- ParentItemID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_ID = "ParentItemID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PARENT_ITEM_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PARENT_ITEM_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_ID = "parentItemId";

    // ------------------------------------------------------------------------------ ParentItemCode

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_CODE = "ParentItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_PARENT_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PARENT_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_CODE = "parentItemCode";

    // ------------------------------------------------------------------------------ ParentItemName

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_NAME = "ParentItemName";

    /**
     * The length of the {@value #COLUMN_NAME_PARENT_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PARENT_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_NAME = "parentItemName";

    // ---------------------------------------------------------------------------------- ParentType

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_TYPE = "ParentType";

    /**
     * The length of the {@value #COLUMN_NAME_PARENT_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PARENT_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_TYPE = "parentType";

    // --------------------------------------------------------------------------------------- Level

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEVEL = "Level";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LEVEL} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LEVEL = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LEVEL} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LEVEL = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEVEL = "level";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code decimal(18, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the minimum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY = "-99999999999999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY} attribute, the maximum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY = "99999999999999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ------------------------------------------------------------------------------------ UnitCode

    /**
     * The name of the {@code nvarchar(10)} column. The value is {@value}.
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

    // ----------------------------------------------------------------------------------- UsagePath

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_USAGE_PATH = "UsagePath";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_USAGE_PATH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_USAGE_PATH = "usagePath";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWhereUsedView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "componentItemId=" + componentItemId
               + ",componentItemCode=" + componentItemCode
               + ",componentItemName=" + componentItemName
               + ",parentItemId=" + parentItemId
               + ",parentItemCode=" + parentItemCode
               + ",parentItemName=" + parentItemName
               + ",parentType=" + parentType
               + ",level=" + level
               + ",quantity=" + quantity
               + ",unitCode=" + unitCode
               + ",usagePath=" + usagePath
               + "}";
    }

    // ----------------------------------------------------------------------------- componentItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     */
    public Integer getComponentItemId() {
        return componentItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute with the specified value.
     *
     * @param componentItemId new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     */
    protected void setComponentItemId(final Integer componentItemId) {
        this.componentItemId = componentItemId;
    }

    // --------------------------------------------------------------------------- componentItemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute.
     */
    public String getComponentItemCode() {
        return componentItemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute with the specified value.
     *
     * @param componentItemCode new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute.
     */
    protected void setComponentItemCode(final String componentItemCode) {
        this.componentItemCode = componentItemCode;
    }

    // --------------------------------------------------------------------------- componentItemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute.
     */
    public String getComponentItemName() {
        return componentItemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute with the specified value.
     *
     * @param componentItemName new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute.
     */
    protected void setComponentItemName(final String componentItemName) {
        this.componentItemName = componentItemName;
    }

    // -------------------------------------------------------------------------------- parentItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     */
    public Integer getParentItemId() {
        return parentItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute with the specified value.
     *
     * @param parentItemId new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     */
    protected void setParentItemId(final Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    // ------------------------------------------------------------------------------ parentItemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute.
     */
    public String getParentItemCode() {
        return parentItemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute with the specified value.
     *
     * @param parentItemCode new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute.
     */
    protected void setParentItemCode(final String parentItemCode) {
        this.parentItemCode = parentItemCode;
    }

    // ------------------------------------------------------------------------------ parentItemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute.
     */
    public String getParentItemName() {
        return parentItemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute with the specified value.
     *
     * @param parentItemName new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute.
     */
    protected void setParentItemName(final String parentItemName) {
        this.parentItemName = parentItemName;
    }

    // ---------------------------------------------------------------------------------- parentType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute.
     */
    public String getParentType() {
        return parentType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute with the specified value.
     *
     * @param parentType new value for {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute.
     */
    protected void setParentType(final String parentType) {
        this.parentType = parentType;
    }

    // --------------------------------------------------------------------------------------- level

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEVEL} attribute.
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEVEL} attribute with the specified value.
     *
     * @param level new value for {@value #ATTRIBUTE_NAME_LEVEL} attribute.
     */
    protected void setLevel(final Integer level) {
        this.level = level;
    }

    // ------------------------------------------------------------------------------------ quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    protected void setQuantity(final BigDecimal quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------ unitCode

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
    protected void setUnitCode(final String unitCode) {
        this.unitCode = unitCode;
    }

    // ----------------------------------------------------------------------------------- usagePath

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute.
     */
    public String getUsagePath() {
        return usagePath;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute with the specified value.
     *
     * @param usagePath new value for {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute.
     */
    protected void setUsagePath(final String usagePath) {
        this.usagePath = usagePath;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_COMPONENT_ITEM_ID)
    @Min(ATTRIBUTE_MIN_COMPONENT_ITEM_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_ID, nullable = true, insertable = false, updatable = false)
    private Integer componentItemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_COMPONENT_ITEM_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_CODE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_COMPONENT_ITEM_CODE)
    private String componentItemCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_COMPONENT_ITEM_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_COMPONENT_ITEM_NAME)
    private String componentItemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_PARENT_ITEM_ID)
    @Min(ATTRIBUTE_MIN_PARENT_ITEM_ID)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PARENT_ITEM_ID, nullable = true, insertable = false, updatable = false)
    private Integer parentItemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PARENT_ITEM_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PARENT_ITEM_CODE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_PARENT_ITEM_CODE)
    private String parentItemCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PARENT_ITEM_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PARENT_ITEM_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_PARENT_ITEM_NAME)
    private String parentItemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PARENT_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PARENT_TYPE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_PARENT_TYPE)
    private String parentType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_LEVEL)
    @Min(ATTRIBUTE_MIN_LEVEL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LEVEL, nullable = true, insertable = false, updatable = false)
    private Integer level;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY, scale = COLUMN_SCALE_QUANTITY)
    private BigDecimal quantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_CODE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_UNIT_CODE)
    private String unitCode;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 92 bytes (46 characters)
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_USAGE_PATH, nullable = true, insertable = false, updatable = false)
    private String usagePath;
}

