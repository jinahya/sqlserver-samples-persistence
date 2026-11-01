package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_WhereUsed} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedWhereUsedView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_WhereUsed";

    // ------------------------------------------------------------------------------------------------- ComponentItemID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_ITEM_ID = "ComponentItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_ITEM_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ITEM_ID = "componentItemId";

    // ----------------------------------------------------------------------------------------------- ComponentItemCode

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

    // ----------------------------------------------------------------------------------------------- ComponentItemName

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

    // ---------------------------------------------------------------------------------------------------- ParentItemID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_ID = "ParentItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_ID = "parentItemId";

    // -------------------------------------------------------------------------------------------------- ParentItemCode

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_CODE = "ParentItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_PARENT_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PARENT_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_CODE = "parentItemCode";

    // -------------------------------------------------------------------------------------------------- ParentItemName

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_ITEM_NAME = "ParentItemName";

    /**
     * The length of the {@value #COLUMN_NAME_PARENT_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PARENT_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_ITEM_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM_NAME = "parentItemName";

    // ------------------------------------------------------------------------------------------------------ ParentType

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PARENT_TYPE = "ParentType";

    /**
     * The length of the {@value #COLUMN_NAME_PARENT_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PARENT_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PARENT_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_TYPE = "parentType";

    // ----------------------------------------------------------------------------------------------------------- Level

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEVEL = "Level";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEVEL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEVEL = "level";

    // -------------------------------------------------------------------------------------------------------- Quantity

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // -------------------------------------------------------------------------------------------------------- UnitCode

    /**
     * The name of the {@code nvarchar(10)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_CODE = "UnitCode";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_CODE = "unitCode";

    // ------------------------------------------------------------------------------------------------------- UsagePath

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_USAGE_PATH = "UsagePath";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_USAGE_PATH} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_USAGE_PATH = "usagePath";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWhereUsedView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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

    // ------------------------------------------------------------------------------------------------- componentItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     */
    @Nullable
    public Integer getComponentItemId() {
        return componentItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute with the specified value.
     *
     * @param componentItemId new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_ID} attribute.
     */
    protected void setComponentItemId(@Nullable final Integer componentItemId) {
        this.componentItemId = componentItemId;
    }

    // ----------------------------------------------------------------------------------------------- componentItemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute.
     */
    @Nullable
    public String getComponentItemCode() {
        return componentItemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute with the specified value.
     *
     * @param componentItemCode new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_CODE} attribute.
     */
    protected void setComponentItemCode(@Nullable final String componentItemCode) {
        this.componentItemCode = componentItemCode;
    }

    // ----------------------------------------------------------------------------------------------- componentItemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute.
     */
    @Nullable
    public String getComponentItemName() {
        return componentItemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute with the specified value.
     *
     * @param componentItemName new value for {@value #ATTRIBUTE_NAME_COMPONENT_ITEM_NAME} attribute.
     */
    protected void setComponentItemName(@Nullable final String componentItemName) {
        this.componentItemName = componentItemName;
    }

    // ---------------------------------------------------------------------------------------------------- parentItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     */
    @Nullable
    public Integer getParentItemId() {
        return parentItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute with the specified value.
     *
     * @param parentItemId new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_ID} attribute.
     */
    protected void setParentItemId(@Nullable final Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    // -------------------------------------------------------------------------------------------------- parentItemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute.
     */
    @Nullable
    public String getParentItemCode() {
        return parentItemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute with the specified value.
     *
     * @param parentItemCode new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_CODE} attribute.
     */
    protected void setParentItemCode(@Nullable final String parentItemCode) {
        this.parentItemCode = parentItemCode;
    }

    // -------------------------------------------------------------------------------------------------- parentItemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute.
     */
    @Nullable
    public String getParentItemName() {
        return parentItemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute with the specified value.
     *
     * @param parentItemName new value for {@value #ATTRIBUTE_NAME_PARENT_ITEM_NAME} attribute.
     */
    protected void setParentItemName(@Nullable final String parentItemName) {
        this.parentItemName = parentItemName;
    }

    // ------------------------------------------------------------------------------------------------------ parentType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute.
     */
    @Nullable
    public String getParentType() {
        return parentType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute with the specified value.
     *
     * @param parentType new value for {@value #ATTRIBUTE_NAME_PARENT_TYPE} attribute.
     */
    protected void setParentType(@Nullable final String parentType) {
        this.parentType = parentType;
    }

    // ----------------------------------------------------------------------------------------------------------- level

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEVEL} attribute.
     */
    @Nullable
    public Integer getLevel() {
        return level;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEVEL} attribute with the specified value.
     *
     * @param level new value for {@value #ATTRIBUTE_NAME_LEVEL} attribute.
     */
    protected void setLevel(@Nullable final Integer level) {
        this.level = level;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    @Nullable
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    protected void setQuantity(@Nullable final BigDecimal quantity) {
        this.quantity = quantity;
    }

    // -------------------------------------------------------------------------------------------------------- unitCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    @Nullable
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute with the specified value.
     *
     * @param unitCode new value for {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    protected void setUnitCode(@Nullable final String unitCode) {
        this.unitCode = unitCode;
    }

    // ------------------------------------------------------------------------------------------------------- usagePath

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute.
     */
    @Nullable
    public String getUsagePath() {
        return usagePath;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute with the specified value.
     *
     * @param usagePath new value for {@value #ATTRIBUTE_NAME_USAGE_PATH} attribute.
     */
    protected void setUsagePath(@Nullable final String usagePath) {
        this.usagePath = usagePath;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_ID,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer componentItemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_COMPONENT_ITEM_CODE)
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COMPONENT_ITEM_CODE
    )
    private String componentItemCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_COMPONENT_ITEM_NAME)
    @Column(name = COLUMN_NAME_COMPONENT_ITEM_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COMPONENT_ITEM_NAME
    )
    private String componentItemName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_PARENT_ITEM_ID,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer parentItemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PARENT_ITEM_CODE)
    @Column(name = COLUMN_NAME_PARENT_ITEM_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PARENT_ITEM_CODE
    )
    private String parentItemCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PARENT_ITEM_NAME)
    @Column(name = COLUMN_NAME_PARENT_ITEM_NAME,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PARENT_ITEM_NAME
    )
    private String parentItemName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PARENT_TYPE)
    @Column(name = COLUMN_NAME_PARENT_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PARENT_TYPE
    )
    private String parentType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_LEVEL,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer level;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_QUANTITY - COLUMN_SCALE_QUANTITY, fraction = COLUMN_SCALE_QUANTITY)
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY,
            scale = COLUMN_SCALE_QUANTITY
    )
    private BigDecimal quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @Column(name = COLUMN_NAME_UNIT_CODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_UNIT_CODE
    )
    private String unitCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_USAGE_PATH,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String usagePath;
}

