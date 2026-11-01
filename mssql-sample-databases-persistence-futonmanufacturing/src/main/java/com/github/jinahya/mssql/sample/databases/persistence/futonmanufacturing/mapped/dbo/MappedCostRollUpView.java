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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_CostRollUp} view.
 * <p>
 * Identifier: the view declares no key; ItemCode is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedCostRollUpView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_CostRollUp";

    // -------------------------------------------------------------------------------------------------------- ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // -------------------------------------------------------------------------------------------------------- ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // -------------------------------------------------------------------------------------------------------- ItemType

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_TYPE = "ItemType";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE = "itemType";

    // --------------------------------------------------------------------------------------------- CurrentStandardCost

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENT_STANDARD_COST = "CurrentStandardCost";

    /**
     * The precision of the {@value #COLUMN_NAME_CURRENT_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CURRENT_STANDARD_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_CURRENT_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CURRENT_STANDARD_COST = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENT_STANDARD_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENT_STANDARD_COST = "currentStandardCost";

    // ------------------------------------------------------------------------------------------ CalculatedMaterialCost

    /**
     * The name of the {@code decimal(38, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CALCULATED_MATERIAL_COST = "CalculatedMaterialCost";

    /**
     * The precision of the {@value #COLUMN_NAME_CALCULATED_MATERIAL_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_CALCULATED_MATERIAL_COST = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_CALCULATED_MATERIAL_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_CALCULATED_MATERIAL_COST = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CALCULATED_MATERIAL_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CALCULATED_MATERIAL_COST = "calculatedMaterialCost";

    // ------------------------------------------------------------------------------------------------ LaborAndOverhead

    /**
     * The name of the {@code decimal(38, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LABOR_AND_OVERHEAD = "LaborAndOverhead";

    /**
     * The precision of the {@value #COLUMN_NAME_LABOR_AND_OVERHEAD} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LABOR_AND_OVERHEAD = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_LABOR_AND_OVERHEAD} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LABOR_AND_OVERHEAD = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LABOR_AND_OVERHEAD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LABOR_AND_OVERHEAD = "laborAndOverhead";

    // -------------------------------------------------------------------------------------------------- ComponentCount

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_COUNT = "ComponentCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_COUNT = "componentCount";

    // ------------------------------------------------------------------------------------------------------- ListPrice

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LIST_PRICE = "ListPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LIST_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LIST_PRICE = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LIST_PRICE = "listPrice";

    // ----------------------------------------------------------------------------------------------------- GrossProfit

    /**
     * The name of the {@code decimal(19, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROSS_PROFIT = "GrossProfit";

    /**
     * The precision of the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROSS_PROFIT = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROSS_PROFIT = 4;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_PROFIT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_PROFIT = "grossProfit";

    // ---------------------------------------------------------------------------------------------- GrossMarginPercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_GROSS_MARGIN_PERCENT = "GrossMarginPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_GROSS_MARGIN_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_GROSS_MARGIN_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_GROSS_MARGIN_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT = "grossMarginPercent";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCostRollUpView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",currentStandardCost=" + currentStandardCost
               + ",calculatedMaterialCost=" + calculatedMaterialCost
               + ",laborAndOverhead=" + laborAndOverhead
               + ",componentCount=" + componentCount
               + ",listPrice=" + listPrice
               + ",grossProfit=" + grossProfit
               + ",grossMarginPercent=" + grossMarginPercent
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
        if (!(obj instanceof MappedCostRollUpView that)) {
            return false;
        }
        return Objects.equals(itemCode, that.itemCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(itemCode);
    }

    // -------------------------------------------------------------------------------------------------------- itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // -------------------------------------------------------------------------------------------------------- itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    // -------------------------------------------------------------------------------------------------------- itemType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute with the specified value.
     *
     * @param itemType new value for {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    protected void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    // --------------------------------------------------------------------------------------------- currentStandardCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENT_STANDARD_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENT_STANDARD_COST} attribute.
     */
    public BigDecimal getCurrentStandardCost() {
        return currentStandardCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENT_STANDARD_COST} attribute with the specified value.
     *
     * @param currentStandardCost new value for {@value #ATTRIBUTE_NAME_CURRENT_STANDARD_COST} attribute.
     */
    protected void setCurrentStandardCost(final BigDecimal currentStandardCost) {
        this.currentStandardCost = currentStandardCost;
    }

    // ------------------------------------------------------------------------------------------ calculatedMaterialCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CALCULATED_MATERIAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CALCULATED_MATERIAL_COST} attribute.
     */
    public BigDecimal getCalculatedMaterialCost() {
        return calculatedMaterialCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CALCULATED_MATERIAL_COST} attribute with the specified value.
     *
     * @param calculatedMaterialCost new value for {@value #ATTRIBUTE_NAME_CALCULATED_MATERIAL_COST} attribute.
     */
    protected void setCalculatedMaterialCost(final BigDecimal calculatedMaterialCost) {
        this.calculatedMaterialCost = calculatedMaterialCost;
    }

    // ------------------------------------------------------------------------------------------------ laborAndOverhead

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LABOR_AND_OVERHEAD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LABOR_AND_OVERHEAD} attribute.
     */
    public BigDecimal getLaborAndOverhead() {
        return laborAndOverhead;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LABOR_AND_OVERHEAD} attribute with the specified value.
     *
     * @param laborAndOverhead new value for {@value #ATTRIBUTE_NAME_LABOR_AND_OVERHEAD} attribute.
     */
    protected void setLaborAndOverhead(final BigDecimal laborAndOverhead) {
        this.laborAndOverhead = laborAndOverhead;
    }

    // -------------------------------------------------------------------------------------------------- componentCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_COUNT} attribute.
     */
    public Integer getComponentCount() {
        return componentCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_COUNT} attribute with the specified value.
     *
     * @param componentCount new value for {@value #ATTRIBUTE_NAME_COMPONENT_COUNT} attribute.
     */
    protected void setComponentCount(final Integer componentCount) {
        this.componentCount = componentCount;
    }

    // ------------------------------------------------------------------------------------------------------- listPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     */
    public BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute with the specified value.
     *
     * @param listPrice new value for {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     */
    protected void setListPrice(final BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    // ----------------------------------------------------------------------------------------------------- grossProfit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     */
    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute with the specified value.
     *
     * @param grossProfit new value for {@value #ATTRIBUTE_NAME_GROSS_PROFIT} attribute.
     */
    protected void setGrossProfit(final BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
    }

    // ---------------------------------------------------------------------------------------------- grossMarginPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     */
    public BigDecimal getGrossMarginPercent() {
        return grossMarginPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute with the specified value.
     *
     * @param grossMarginPercent new value for {@value #ATTRIBUTE_NAME_GROSS_MARGIN_PERCENT} attribute.
     */
    protected void setGrossMarginPercent(final BigDecimal grossMarginPercent) {
        this.grossMarginPercent = grossMarginPercent;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_TYPE
    )
    private String itemType;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_CURRENT_STANDARD_COST - COLUMN_SCALE_CURRENT_STANDARD_COST,
            fraction = COLUMN_SCALE_CURRENT_STANDARD_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CURRENT_STANDARD_COST,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_CURRENT_STANDARD_COST,
            scale = COLUMN_SCALE_CURRENT_STANDARD_COST
    )
    private BigDecimal currentStandardCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_CALCULATED_MATERIAL_COST - COLUMN_SCALE_CALCULATED_MATERIAL_COST,
            fraction = COLUMN_SCALE_CALCULATED_MATERIAL_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CALCULATED_MATERIAL_COST,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_CALCULATED_MATERIAL_COST,
            scale = COLUMN_SCALE_CALCULATED_MATERIAL_COST
    )
    private BigDecimal calculatedMaterialCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_LABOR_AND_OVERHEAD - COLUMN_SCALE_LABOR_AND_OVERHEAD,
            fraction = COLUMN_SCALE_LABOR_AND_OVERHEAD)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LABOR_AND_OVERHEAD,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LABOR_AND_OVERHEAD,
            scale = COLUMN_SCALE_LABOR_AND_OVERHEAD
    )
    private BigDecimal laborAndOverhead;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COMPONENT_COUNT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer componentCount;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_LIST_PRICE - COLUMN_SCALE_LIST_PRICE, fraction = COLUMN_SCALE_LIST_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LIST_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_LIST_PRICE,
            scale = COLUMN_SCALE_LIST_PRICE
    )
    private BigDecimal listPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_GROSS_PROFIT - COLUMN_SCALE_GROSS_PROFIT, fraction = COLUMN_SCALE_GROSS_PROFIT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_PROFIT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_PROFIT,
            scale = COLUMN_SCALE_GROSS_PROFIT
    )
    private BigDecimal grossProfit;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_GROSS_MARGIN_PERCENT - COLUMN_SCALE_GROSS_MARGIN_PERCENT,
            fraction = COLUMN_SCALE_GROSS_MARGIN_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_GROSS_MARGIN_PERCENT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_GROSS_MARGIN_PERCENT,
            scale = COLUMN_SCALE_GROSS_MARGIN_PERCENT
    )
    private BigDecimal grossMarginPercent;
}

