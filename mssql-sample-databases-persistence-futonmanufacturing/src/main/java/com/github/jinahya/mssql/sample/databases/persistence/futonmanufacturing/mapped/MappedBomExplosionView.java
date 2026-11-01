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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_BOMExplosion} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedBomExplosionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_BOMExplosion";

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

    // --------------------------------------------------------------------------------------------------- ComponentType

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_TYPE = "ComponentType";

    /**
     * The length of the {@value #COLUMN_NAME_COMPONENT_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COMPONENT_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_TYPE = "componentType";

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

    // ----------------------------------------------------------------------------------------------- EffectiveQuantity

    /**
     * The name of the {@code decimal(18, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EFFECTIVE_QUANTITY = "EffectiveQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_EFFECTIVE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EFFECTIVE_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_EFFECTIVE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_EFFECTIVE_QUANTITY = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EFFECTIVE_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EFFECTIVE_QUANTITY = "effectiveQuantity";

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

    // ---------------------------------------------------------------------------------------------------- StandardCost

    /**
     * The name of the {@code decimal(18, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_COST = "StandardCost";

    /**
     * The precision of the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_STANDARD_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_STANDARD_COST = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_COST = "standardCost";

    // ---------------------------------------------------------------------------------------------------- ExtendedCost

    /**
     * The name of the {@code decimal(18, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXTENDED_COST = "ExtendedCost";

    /**
     * The precision of the {@value #COLUMN_NAME_EXTENDED_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_EXTENDED_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_EXTENDED_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_EXTENDED_COST = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXTENDED_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXTENDED_COST = "extendedCost";

    // --------------------------------------------------------------------------------------------------------- BOMPath

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BOM_PATH = "BOMPath";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BOM_PATH} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BOM_PATH = "bomPath";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedBomExplosionView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "parentItemId=" + parentItemId
               + ",parentItemCode=" + parentItemCode
               + ",parentItemName=" + parentItemName
               + ",componentItemId=" + componentItemId
               + ",componentItemCode=" + componentItemCode
               + ",componentItemName=" + componentItemName
               + ",componentType=" + componentType
               + ",level=" + level
               + ",effectiveQuantity=" + effectiveQuantity
               + ",unitCode=" + unitCode
               + ",standardCost=" + standardCost
               + ",extendedCost=" + extendedCost
               + ",bomPath=" + bomPath
               + "}";
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

    // --------------------------------------------------------------------------------------------------- componentType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_TYPE} attribute.
     */
    @Nullable
    public String getComponentType() {
        return componentType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_TYPE} attribute with the specified value.
     *
     * @param componentType new value for {@value #ATTRIBUTE_NAME_COMPONENT_TYPE} attribute.
     */
    protected void setComponentType(@Nullable final String componentType) {
        this.componentType = componentType;
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

    // ----------------------------------------------------------------------------------------------- effectiveQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_QUANTITY} attribute.
     */
    @Nullable
    public BigDecimal getEffectiveQuantity() {
        return effectiveQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_QUANTITY} attribute with the specified value.
     *
     * @param effectiveQuantity new value for {@value #ATTRIBUTE_NAME_EFFECTIVE_QUANTITY} attribute.
     */
    protected void setEffectiveQuantity(@Nullable final BigDecimal effectiveQuantity) {
        this.effectiveQuantity = effectiveQuantity;
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

    // ---------------------------------------------------------------------------------------------------- standardCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    @Nullable
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute with the specified value.
     *
     * @param standardCost new value for {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    protected void setStandardCost(@Nullable final BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    // ---------------------------------------------------------------------------------------------------- extendedCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXTENDED_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXTENDED_COST} attribute.
     */
    @Nullable
    public BigDecimal getExtendedCost() {
        return extendedCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXTENDED_COST} attribute with the specified value.
     *
     * @param extendedCost new value for {@value #ATTRIBUTE_NAME_EXTENDED_COST} attribute.
     */
    protected void setExtendedCost(@Nullable final BigDecimal extendedCost) {
        this.extendedCost = extendedCost;
    }

    // --------------------------------------------------------------------------------------------------------- bomPath

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BOM_PATH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BOM_PATH} attribute.
     */
    @Nullable
    public String getBomPath() {
        return bomPath;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BOM_PATH} attribute with the specified value.
     *
     * @param bomPath new value for {@value #ATTRIBUTE_NAME_BOM_PATH} attribute.
     */
    protected void setBomPath(@Nullable final String bomPath) {
        this.bomPath = bomPath;
    }

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
    @Size(max = COLUMN_LENGTH_COMPONENT_TYPE)
    @Column(name = COLUMN_NAME_COMPONENT_TYPE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COMPONENT_TYPE
    )
    private String componentType;

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
    @Digits(integer = COLUMN_PRECISION_EFFECTIVE_QUANTITY - COLUMN_SCALE_EFFECTIVE_QUANTITY, fraction = COLUMN_SCALE_EFFECTIVE_QUANTITY)
    @Column(name = COLUMN_NAME_EFFECTIVE_QUANTITY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_EFFECTIVE_QUANTITY,
            scale = COLUMN_SCALE_EFFECTIVE_QUANTITY
    )
    private BigDecimal effectiveQuantity;

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
    @Digits(integer = COLUMN_PRECISION_STANDARD_COST - COLUMN_SCALE_STANDARD_COST, fraction = COLUMN_SCALE_STANDARD_COST)
    @Column(name = COLUMN_NAME_STANDARD_COST,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_STANDARD_COST,
            scale = COLUMN_SCALE_STANDARD_COST
    )
    private BigDecimal standardCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_EXTENDED_COST - COLUMN_SCALE_EXTENDED_COST, fraction = COLUMN_SCALE_EXTENDED_COST)
    @Column(name = COLUMN_NAME_EXTENDED_COST,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_EXTENDED_COST,
            scale = COLUMN_SCALE_EXTENDED_COST
    )
    private BigDecimal extendedCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_BOM_PATH,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String bomPath;
}

