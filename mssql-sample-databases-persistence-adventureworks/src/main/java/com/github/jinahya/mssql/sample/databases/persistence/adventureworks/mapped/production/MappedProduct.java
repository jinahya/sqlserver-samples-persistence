package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.Product} table.
 * <p>
 * The {@value #COLUMN_NAME_SIZE_UNIT_MEASURE_CODE}, {@value #COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE},
 * {@value #COLUMN_NAME_PRODUCT_SUBCATEGORY_ID}, {@value #COLUMN_NAME_PRODUCT_MODEL_ID} columns are mapped read-only, as
 * a foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedProduct {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Product";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // --------------------------------------------------------------------------------------------------- ProductNumber

    /**
     * The name of the {@code nvarchar(25), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NUMBER = "ProductNumber";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NUMBER = "productNumber";

    // -------------------------------------------------------------------------------------------------------- MakeFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MAKE_FLAG = "MakeFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAKE_FLAG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAKE_FLAG = "makeFlag";

    // ----------------------------------------------------------------------------------------------- FinishedGoodsFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FINISHED_GOODS_FLAG = "FinishedGoodsFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FINISHED_GOODS_FLAG} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FINISHED_GOODS_FLAG = "finishedGoodsFlag";

    // ----------------------------------------------------------------------------------------------------------- Color

    /**
     * The name of the {@code nvarchar(15)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLOR = "Color";

    /**
     * The length of the {@value #COLUMN_NAME_COLOR} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COLOR = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLOR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLOR = "color";

    // ------------------------------------------------------------------------------------------------ SafetyStockLevel

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SAFETY_STOCK_LEVEL = "SafetyStockLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SAFETY_STOCK_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL = "safetyStockLevel";

    // ---------------------------------------------------------------------------------------------------- ReorderPoint

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REORDER_POINT = "ReorderPoint";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REORDER_POINT = "reorderPoint";

    // ---------------------------------------------------------------------------------------------------- StandardCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_COST = "StandardCost";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_COST = "standardCost";

    // ------------------------------------------------------------------------------------------------------- ListPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LIST_PRICE = "ListPrice";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LIST_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LIST_PRICE = "listPrice";

    // ------------------------------------------------------------------------------------------------------------ Size

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIZE = "Size";

    /**
     * The length of the {@value #COLUMN_NAME_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SIZE = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIZE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE = "size";

    // --------------------------------------------------------------------------------------------- SizeUnitMeasureCode

    /**
     * The name of the {@code nchar(3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIZE_UNIT_MEASURE_CODE = "SizeUnitMeasureCode";

    /**
     * The length of the {@value #COLUMN_NAME_SIZE_UNIT_MEASURE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SIZE_UNIT_MEASURE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIZE_UNIT_MEASURE_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE = "sizeUnitMeasureCode";

    // ------------------------------------------------------------------------------------------- WeightUnitMeasureCode

    /**
     * The name of the {@code nchar(3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE = "WeightUnitMeasureCode";

    /**
     * The length of the {@value #COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WEIGHT_UNIT_MEASURE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE = "weightUnitMeasureCode";

    // ---------------------------------------------------------------------------------------------------------- Weight

    /**
     * The name of the {@code decimal(8, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEIGHT = "Weight";

    /**
     * The precision of the {@value #COLUMN_NAME_WEIGHT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_WEIGHT = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_WEIGHT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_WEIGHT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEIGHT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEIGHT = "weight";

    // ----------------------------------------------------------------------------------------------- DaysToManufacture

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_TO_MANUFACTURE = "DaysToManufacture";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_TO_MANUFACTURE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE = "daysToManufacture";

    // ----------------------------------------------------------------------------------------------------- ProductLine

    /**
     * The name of the {@code nchar(2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_LINE = "ProductLine";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_LINE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_LINE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_LINE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_LINE = "productLine";

    // ----------------------------------------------------------------------------------------------------------- Class

    /**
     * The name of the {@code nchar(2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_CLASS = "Class";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_CLASS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_CLASS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_CLASS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_CLASS = "productClass";

    // ----------------------------------------------------------------------------------------------------------- Style

    /**
     * The name of the {@code nchar(2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STYLE = "Style";

    /**
     * The length of the {@value #COLUMN_NAME_STYLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STYLE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STYLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STYLE = "style";

    // -------------------------------------------------------------------------------------------- ProductSubcategoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_SUBCATEGORY_ID = "ProductSubcategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_SUBCATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID = "productSubcategoryId";

    // -------------------------------------------------------------------------------------------------- ProductModelID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    // --------------------------------------------------------------------------------------------------- SellStartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SELL_START_DATE = "SellStartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SELL_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SELL_START_DATE = "sellStartDate";

    // ----------------------------------------------------------------------------------------------------- SellEndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SELL_END_DATE = "SellEndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SELL_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SELL_END_DATE = "sellEndDate";

    // ------------------------------------------------------------------------------------------------ DiscontinuedDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCONTINUED_DATE = "DiscontinuedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCONTINUED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCONTINUED_DATE = "discontinuedDate";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProduct() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",name=" + name
               + ",productNumber=" + productNumber
               + ",makeFlag=" + makeFlag
               + ",finishedGoodsFlag=" + finishedGoodsFlag
               + ",color=" + color
               + ",safetyStockLevel=" + safetyStockLevel
               + ",reorderPoint=" + reorderPoint
               + ",standardCost=" + standardCost
               + ",listPrice=" + listPrice
               + ",size=" + size
               + ",sizeUnitMeasureCode=" + sizeUnitMeasureCode
               + ",weightUnitMeasureCode=" + weightUnitMeasureCode
               + ",weight=" + weight
               + ",daysToManufacture=" + daysToManufacture
               + ",productLine=" + productLine
               + ",productClass=" + productClass
               + ",style=" + style
               + ",productSubcategoryId=" + productSubcategoryId
               + ",productModelId=" + productModelId
               + ",sellStartDate=" + sellStartDate
               + ",sellEndDate=" + sellEndDate
               + ",discontinuedDate=" + discontinuedDate
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedProduct that)) {
            return false;
        }
        return Objects.equals(productId, that.productId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productId);
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------------------ name

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
    public void setName(final String name) {
        this.name = name;
    }

    // --------------------------------------------------------------------------------------------------- productNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute.
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute with the specified value.
     *
     * @param productNumber new value for {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute.
     */
    public void setProductNumber(final String productNumber) {
        this.productNumber = productNumber;
    }

    // -------------------------------------------------------------------------------------------------------- makeFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute.
     */
    public Boolean getMakeFlag() {
        return makeFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute with the specified value.
     *
     * @param makeFlag new value for {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute.
     */
    public void setMakeFlag(final Boolean makeFlag) {
        this.makeFlag = makeFlag;
    }

    // ----------------------------------------------------------------------------------------------- finishedGoodsFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute.
     */
    public Boolean getFinishedGoodsFlag() {
        return finishedGoodsFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute with the specified value.
     *
     * @param finishedGoodsFlag new value for {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute.
     */
    public void setFinishedGoodsFlag(final Boolean finishedGoodsFlag) {
        this.finishedGoodsFlag = finishedGoodsFlag;
    }

    // ----------------------------------------------------------------------------------------------------------- color

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COLOR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COLOR} attribute.
     */
    public String getColor() {
        return color;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLOR} attribute with the specified value.
     *
     * @param color new value for {@value #ATTRIBUTE_NAME_COLOR} attribute.
     */
    public void setColor(final String color) {
        this.color = color;
    }

    // ------------------------------------------------------------------------------------------------ safetyStockLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute.
     */
    public Short getSafetyStockLevel() {
        return safetyStockLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute with the specified value.
     *
     * @param safetyStockLevel new value for {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute.
     */
    public void setSafetyStockLevel(final Short safetyStockLevel) {
        this.safetyStockLevel = safetyStockLevel;
    }

    // ---------------------------------------------------------------------------------------------------- reorderPoint

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public Short getReorderPoint() {
        return reorderPoint;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute with the specified value.
     *
     * @param reorderPoint new value for {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public void setReorderPoint(final Short reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    // ---------------------------------------------------------------------------------------------------- standardCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute with the specified value.
     *
     * @param standardCost new value for {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public void setStandardCost(final BigDecimal standardCost) {
        this.standardCost = standardCost;
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
    public void setListPrice(final BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    // ------------------------------------------------------------------------------------------------------------ size

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SIZE} attribute.
     */
    public String getSize() {
        return size;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SIZE} attribute with the specified value.
     *
     * @param size new value for {@value #ATTRIBUTE_NAME_SIZE} attribute.
     */
    public void setSize(final String size) {
        this.size = size;
    }

    // --------------------------------------------------------------------------------------------- sizeUnitMeasureCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute.
     */
    public String getSizeUnitMeasureCode() {
        return sizeUnitMeasureCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param sizeUnitMeasureCode new value for {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute.
     */
    protected void setSizeUnitMeasureCode(final String sizeUnitMeasureCode) {
        this.sizeUnitMeasureCode = sizeUnitMeasureCode;
    }

    // ------------------------------------------------------------------------------------------- weightUnitMeasureCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute.
     */
    public String getWeightUnitMeasureCode() {
        return weightUnitMeasureCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute with the specified value.
     * The attribute is read-only; the extending entity calls this to keep it in step with the association which writes
     * the column.
     *
     * @param weightUnitMeasureCode new value for {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute.
     */
    protected void setWeightUnitMeasureCode(final String weightUnitMeasureCode) {
        this.weightUnitMeasureCode = weightUnitMeasureCode;
    }

    // ---------------------------------------------------------------------------------------------------------- weight

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEIGHT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEIGHT} attribute.
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEIGHT} attribute with the specified value.
     *
     * @param weight new value for {@value #ATTRIBUTE_NAME_WEIGHT} attribute.
     */
    public void setWeight(final BigDecimal weight) {
        this.weight = weight;
    }

    // ----------------------------------------------------------------------------------------------- daysToManufacture

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute.
     */
    public Integer getDaysToManufacture() {
        return daysToManufacture;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute with the specified value.
     *
     * @param daysToManufacture new value for {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute.
     */
    public void setDaysToManufacture(final Integer daysToManufacture) {
        this.daysToManufacture = daysToManufacture;
    }

    // ----------------------------------------------------------------------------------------------------- productLine

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     */
    public String getProductLine() {
        return productLine;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute with the specified value.
     *
     * @param productLine new value for {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     */
    public void setProductLine(final String productLine) {
        this.productLine = productLine;
    }

    // ---------------------------------------------------------------------------------------------------- productClass

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute.
     */
    public String getProductClass() {
        return productClass;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute with the specified value.
     *
     * @param productClass new value for {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute.
     */
    public void setProductClass(final String productClass) {
        this.productClass = productClass;
    }

    // ----------------------------------------------------------------------------------------------------------- style

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STYLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STYLE} attribute.
     */
    public String getStyle() {
        return style;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STYLE} attribute with the specified value.
     *
     * @param style new value for {@value #ATTRIBUTE_NAME_STYLE} attribute.
     */
    public void setStyle(final String style) {
        this.style = style;
    }

    // -------------------------------------------------------------------------------------------- productSubcategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     */
    public Integer getProductSubcategoryId() {
        return productSubcategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param productSubcategoryId new value for {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     */
    protected void setProductSubcategoryId(final Integer productSubcategoryId) {
        this.productSubcategoryId = productSubcategoryId;
    }

    // -------------------------------------------------------------------------------------------------- productModelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    protected void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // --------------------------------------------------------------------------------------------------- sellStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute.
     */
    public LocalDateTime getSellStartDate() {
        return sellStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute with the specified value.
     *
     * @param sellStartDate new value for {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute.
     */
    public void setSellStartDate(final LocalDateTime sellStartDate) {
        this.sellStartDate = sellStartDate;
    }

    // ----------------------------------------------------------------------------------------------------- sellEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute.
     */
    public LocalDateTime getSellEndDate() {
        return sellEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute with the specified value.
     *
     * @param sellEndDate new value for {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute.
     */
    public void setSellEndDate(final LocalDateTime sellEndDate) {
        this.sellEndDate = sellEndDate;
    }

    // ------------------------------------------------------------------------------------------------ discontinuedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute.
     */
    public LocalDateTime getDiscontinuedDate() {
        return discontinuedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute with the specified value.
     *
     * @param discontinuedDate new value for {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute.
     */
    public void setDiscontinuedDate(final LocalDateTime discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRODUCT_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRODUCT_NUMBER
    )
    private String productNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MAKE_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean makeFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FINISHED_GOODS_FLAG,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean finishedGoodsFlag;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COLOR)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COLOR,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_COLOR
    )
    private String color;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SAFETY_STOCK_LEVEL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short safetyStockLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REORDER_POINT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short reorderPoint;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STANDARD_COST,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal standardCost;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LIST_PRICE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal listPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SIZE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIZE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SIZE
    )
    private String size;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Size(max = COLUMN_LENGTH_SIZE_UNIT_MEASURE_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIZE_UNIT_MEASURE_CODE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SIZE_UNIT_MEASURE_CODE
    )
    private String sizeUnitMeasureCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Size(max = COLUMN_LENGTH_WEIGHT_UNIT_MEASURE_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WEIGHT_UNIT_MEASURE_CODE
    )
    private String weightUnitMeasureCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_WEIGHT - COLUMN_SCALE_WEIGHT, fraction = COLUMN_SCALE_WEIGHT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEIGHT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_WEIGHT,
            scale = COLUMN_SCALE_WEIGHT
    )
    private BigDecimal weight;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_TO_MANUFACTURE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer daysToManufacture;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRODUCT_LINE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_LINE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRODUCT_LINE
    )
    private String productLine;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRODUCT_CLASS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_CLASS,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRODUCT_CLASS
    )
    private String productClass;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STYLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STYLE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STYLE
    )
    private String style;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_SUBCATEGORY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer productSubcategoryId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer productModelId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SELL_START_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime sellStartDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SELL_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime sellEndDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCONTINUED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime discontinuedDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

