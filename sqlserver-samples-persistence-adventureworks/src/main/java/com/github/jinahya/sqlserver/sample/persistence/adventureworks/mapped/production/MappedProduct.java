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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
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
 * A mapped superclass which holds the mappings of the {@code Production.Product} table.
 * <p>
 * The {@value #COLUMN_NAME_SIZE_UNIT_MEASURE_CODE}, {@value #COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE},
 * {@value #COLUMN_NAME_PRODUCT_SUBCATEGORY_ID}, {@value #COLUMN_NAME_PRODUCT_MODEL_ID} columns are
 * mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.Product}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductID}</th><td>Primary key for Product records.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>Name of the product.</td></tr>
 * <tr><th scope="row">{@code ProductNumber}</th><td>Unique product identification number.</td></tr>
 * <tr><th scope="row">{@code MakeFlag}</th>
 *     <td>0 = Product is purchased, 1 = Product is manufactured in-house.</td></tr>
 * <tr><th scope="row">{@code FinishedGoodsFlag}</th>
 *     <td>0 = Product is not a salable item. 1 = Product is salable.</td></tr>
 * <tr><th scope="row">{@code Color}</th><td>Product color.</td></tr>
 * <tr><th scope="row">{@code SafetyStockLevel}</th><td>Minimum inventory quantity.</td></tr>
 * <tr><th scope="row">{@code ReorderPoint}</th>
 *     <td>Inventory level that triggers a purchase order or work order.</td></tr>
 * <tr><th scope="row">{@code StandardCost}</th><td>Standard cost of the product.</td></tr>
 * <tr><th scope="row">{@code ListPrice}</th><td>Selling price.</td></tr>
 * <tr><th scope="row">{@code Size}</th><td>Product size.</td></tr>
 * <tr><th scope="row">{@code SizeUnitMeasureCode}</th>
 *     <td>Unit of measure for Size column.</td></tr>
 * <tr><th scope="row">{@code WeightUnitMeasureCode}</th>
 *     <td>Unit of measure for Weight column.</td></tr>
 * <tr><th scope="row">{@code Weight}</th><td>Product weight.</td></tr>
 * <tr><th scope="row">{@code DaysToManufacture}</th>
 *     <td>Number of days required to manufacture the product.</td></tr>
 * <tr><th scope="row">{@code ProductLine}</th>
 *     <td>R = Road, M = Mountain, T = Touring, S = Standard</td></tr>
 * <tr><th scope="row">{@code Class}</th><td>H = High, M = Medium, L = Low</td></tr>
 * <tr><th scope="row">{@code Style}</th><td>W = Womens, M = Mens, U = Universal</td></tr>
 * <tr><th scope="row">{@code ProductSubcategoryID}</th>
 *     <td>Product is a member of this product subcategory. Foreign key to
 *     ProductSubCategory.ProductSubCategoryID.</td></tr>
 * <tr><th scope="row">{@code ProductModelID}</th>
 *     <td>Product is a member of this product model. Foreign key to
 *     ProductModel.ProductModelID.</td></tr>
 * <tr><th scope="row">{@code SellStartDate}</th>
 *     <td>Date the product was available for sale.</td></tr>
 * <tr><th scope="row">{@code SellEndDate}</th>
 *     <td>Date the product was no longer available for sale.</td></tr>
 * <tr><th scope="row">{@code DiscontinuedDate}</th><td>Date the product was discontinued.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.Product}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code MakeFlag}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code FinishedGoodsFlag}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedProduct.TABLE_SCHEMA,
        table = MappedProduct.TABLE_NAME,
        value = "Products sold or used in the manfacturing of sold products."
)
public abstract class MappedProduct {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Product";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

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

    // ------------------------------------------------------------------------------- ProductNumber

    /**
     * The name of the {@code nvarchar(25), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_NUMBER = "ProductNumber";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_NUMBER} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_NUMBER = "productNumber";

    // ------------------------------------------------------------------------------------ MakeFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MAKE_FLAG = "MakeFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAKE_FLAG} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAKE_FLAG = "makeFlag";

    // --------------------------------------------------------------------------- FinishedGoodsFlag

    /**
     * The name of the {@code Flag (bit), DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FINISHED_GOODS_FLAG = "FinishedGoodsFlag";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FINISHED_GOODS_FLAG} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FINISHED_GOODS_FLAG = "finishedGoodsFlag";

    // --------------------------------------------------------------------------------------- Color

    /**
     * The name of the {@code nvarchar(15)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLOR = "Color";

    /**
     * The length of the {@value #COLUMN_NAME_COLOR} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_COLOR = 15;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLOR} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLOR = "color";

    // ---------------------------------------------------------------------------- SafetyStockLevel

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SAFETY_STOCK_LEVEL = "SafetyStockLevel";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute, as the
     * {@code CK_Product_SafetyStockLevel} check constraint ({@code [SafetyStockLevel]>(0)})
     * requires. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SAFETY_STOCK_LEVEL = +1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SAFETY_STOCK_LEVEL} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL = "safetyStockLevel";

    // -------------------------------------------------------------------------------- ReorderPoint

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REORDER_POINT = "ReorderPoint";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute, as the
     * {@code CK_Product_ReorderPoint} check constraint ({@code [ReorderPoint]>(0)}) requires. The
     * value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_REORDER_POINT = +1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REORDER_POINT} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REORDER_POINT = "reorderPoint";

    // -------------------------------------------------------------------------------- StandardCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_COST = "StandardCost";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute, as the
     * {@code CK_Product_StandardCost} check constraint ({@code [StandardCost]>=(0.00)}) requires.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_STANDARD_COST = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_COST} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_COST = "standardCost";

    // ----------------------------------------------------------------------------------- ListPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LIST_PRICE = "ListPrice";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute, as the
     * {@code CK_Product_ListPrice} check constraint ({@code [ListPrice]>=(0.00)}) requires. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LIST_PRICE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LIST_PRICE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LIST_PRICE = "listPrice";

    // ---------------------------------------------------------------------------------------- Size

    /**
     * The name of the {@code nvarchar(5)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIZE = "Size";

    /**
     * The length of the {@value #COLUMN_NAME_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SIZE = 5;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIZE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE = "size";

    // ------------------------------------------------------------------------- SizeUnitMeasureCode

    /**
     * The name of the {@code nchar(3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIZE_UNIT_MEASURE_CODE = "SizeUnitMeasureCode";

    /**
     * The length of the {@value #COLUMN_NAME_SIZE_UNIT_MEASURE_CODE} column. The value is
     * {@value}.
     */
    public static final int COLUMN_LENGTH_SIZE_UNIT_MEASURE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIZE_UNIT_MEASURE_CODE} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE = "sizeUnitMeasureCode";

    // ----------------------------------------------------------------------- WeightUnitMeasureCode

    /**
     * The name of the {@code nchar(3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE = "WeightUnitMeasureCode";

    /**
     * The length of the {@value #COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE} column. The value is
     * {@value}.
     */
    public static final int COLUMN_LENGTH_WEIGHT_UNIT_MEASURE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE = "weightUnitMeasureCode";

    // -------------------------------------------------------------------------------------- Weight

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
     * The exclusive minimum value of the {@value #ATTRIBUTE_NAME_WEIGHT} attribute, as the
     * {@code CK_Product_Weight} check constraint ({@code [Weight]>(0.00)}) requires. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_WEIGHT_EXCLUSIVE = "0.00";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_WEIGHT} attribute, the maximum of the
     * {@code decimal(8, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_WEIGHT = "999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEIGHT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEIGHT = "weight";

    // --------------------------------------------------------------------------- DaysToManufacture

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_TO_MANUFACTURE = "DaysToManufacture";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute, as the
     * {@code CK_Product_DaysToManufacture} check constraint ({@code [DaysToManufacture]>=(0)})
     * requires. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_DAYS_TO_MANUFACTURE = 0;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_TO_MANUFACTURE} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE = "daysToManufacture";

    // --------------------------------------------------------------------------------- ProductLine

    /**
     * The name of the {@code nchar(2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_LINE = "ProductLine";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_LINE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_LINE = 2;

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_LINE} column, for a road product. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_LINE_ROAD = "R";

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_LINE} column, for a mountain product. The value
     * is {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_LINE_MOUNTAIN = "M";

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_LINE} column, for a touring product. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_LINE_TOURING = "T";

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_LINE} column, for a standard product. The value
     * is {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_LINE_STANDARD = "S";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_LINE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_LINE = "productLine";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute, of the values the
     * {@code CK_Product_ProductLine} constraint allows.
     * {@snippet lang = "sql":
     * (upper([ProductLine])='R' OR upper([ProductLine])='M' OR upper([ProductLine])='T' OR upper
     * ([ProductLine])='S' OR [ProductLine] IS NULL)
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum ProductLine {

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_LINE_ROAD} value, for a
         * road product.
         */
        R,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_LINE_MOUNTAIN} value, for a
         * mountain product.
         */
        M,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_LINE_TOURING} value, for a
         * touring product.
         */
        T,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_LINE_STANDARD} value, for a
         * standard product.
         */
        S
    }

    /**
     * An attribute converter between the {@link ProductLine} constants and the values of the
     * {@value #COLUMN_NAME_PRODUCT_LINE} column. The column is an {@code nchar(2)} which holds a
     * single letter, padded with a space ({@code 'R '}), so
     * {@link jakarta.persistence.Enumerated @Enumerated} cannot read it; this converter strips the
     * padding off a column value, and writes a constant's {@link Enum#name() name}, which the
     * database pads.
     */
    @Converter(autoApply = false)
    public static class ProductLineConverter
            implements AttributeConverter<ProductLine, String> {

        /**
         * Creates a new instance.
         */
        public ProductLineConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final ProductLine attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.name();
        }

        /**
         * {@inheritDoc}
         *
         * @param dbData {@inheritDoc}
         * @return {@inheritDoc}
         * @throws IllegalArgumentException if {@code dbData}, stripped, is the name of no
         *                                  {@link ProductLine} constant.
         */
        @Override
        public ProductLine convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            return ProductLine.valueOf(dbData.strip());
        }
    }

    // --------------------------------------------------------------------------------------- Class

    /**
     * The name of the {@code nchar(2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_CLASS = "Class";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_CLASS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_CLASS = 2;

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_CLASS} column, for a high class product. The
     * value is {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_CLASS_HIGH = "H";

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_CLASS} column, for a medium class product. The
     * value is {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_CLASS_MEDIUM = "M";

    /**
     * A value of the {@value #COLUMN_NAME_PRODUCT_CLASS} column, for a low class product. The value
     * is {@value}.
     */
    public static final String COLUMN_VALUE_PRODUCT_CLASS_LOW = "L";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_CLASS} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_CLASS = "productClass";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute, of the values the
     * {@code CK_Product_Class} constraint allows.
     * {@snippet lang = "sql":
     * (upper([Class])='H' OR upper([Class])='M' OR upper([Class])='L' OR [Class] IS NULL)
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum ProductClass {

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_CLASS_HIGH} value, for a
         * high class product.
         */
        H,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_CLASS_MEDIUM} value, for a
         * medium class product.
         */
        M,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_PRODUCT_CLASS_LOW} value, for a low
         * class product.
         */
        L
    }

    /**
     * An attribute converter between the {@link ProductClass} constants and the values of the
     * {@value #COLUMN_NAME_PRODUCT_CLASS} column. The column is an {@code nchar(2)} which holds a
     * single letter, padded with a space ({@code 'H '}), so
     * {@link jakarta.persistence.Enumerated @Enumerated} cannot read it; this converter strips the
     * padding off a column value, and writes a constant's {@link Enum#name() name}, which the
     * database pads.
     */
    @Converter(autoApply = false)
    public static class ProductClassConverter
            implements AttributeConverter<ProductClass, String> {

        /**
         * Creates a new instance.
         */
        public ProductClassConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final ProductClass attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.name();
        }

        /**
         * {@inheritDoc}
         *
         * @param dbData {@inheritDoc}
         * @return {@inheritDoc}
         * @throws IllegalArgumentException if {@code dbData}, stripped, is the name of no
         *                                  {@link ProductClass} constant.
         */
        @Override
        public ProductClass convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            return ProductClass.valueOf(dbData.strip());
        }
    }

    // --------------------------------------------------------------------------------------- Style

    /**
     * The name of the {@code nchar(2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STYLE = "Style";

    /**
     * The length of the {@value #COLUMN_NAME_STYLE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STYLE = 2;

    /**
     * A value of the {@value #COLUMN_NAME_STYLE} column, for a women's product. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_STYLE_WOMENS = "W";

    /**
     * A value of the {@value #COLUMN_NAME_STYLE} column, for a men's product. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_STYLE_MENS = "M";

    /**
     * A value of the {@value #COLUMN_NAME_STYLE} column, for a universal product. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_STYLE_UNIVERSAL = "U";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STYLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STYLE = "style";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_STYLE} attribute, of the values the
     * {@code CK_Product_Style} constraint allows.
     * {@snippet lang = "sql":
     * (upper([Style])='U' OR upper([Style])='M' OR upper([Style])='W' OR [Style] IS NULL)
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum ProductStyle {

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_STYLE_WOMENS} value, for a women's
         * product.
         */
        W,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_STYLE_MENS} value, for a men's
         * product.
         */
        M,

        /**
         * A constant for the {@value MappedProduct#COLUMN_VALUE_STYLE_UNIVERSAL} value, for a
         * universal product.
         */
        U
    }

    /**
     * An attribute converter between the {@link ProductStyle} constants and the values of the
     * {@value #COLUMN_NAME_STYLE} column. The column is an {@code nchar(2)} which holds a single
     * letter, padded with a space ({@code 'U '}), so
     * {@link jakarta.persistence.Enumerated @Enumerated} cannot read it; this converter strips the
     * padding off a column value, and writes a constant's {@link Enum#name() name}, which the
     * database pads.
     */
    @Converter(autoApply = false)
    public static class ProductStyleConverter
            implements AttributeConverter<ProductStyle, String> {

        /**
         * Creates a new instance.
         */
        public ProductStyleConverter() {
            super();
        }

        @Override
        public String convertToDatabaseColumn(final ProductStyle attribute) {
            if (attribute == null) {
                return null;
            }
            return attribute.name();
        }

        /**
         * {@inheritDoc}
         *
         * @param dbData {@inheritDoc}
         * @return {@inheritDoc}
         * @throws IllegalArgumentException if {@code dbData}, stripped, is the name of no
         *                                  {@link ProductStyle} constant.
         */
        @Override
        public ProductStyle convertToEntityAttribute(final String dbData) {
            if (dbData == null) {
                return null;
            }
            return ProductStyle.valueOf(dbData.strip());
        }
    }

    // ------------------------------------------------------------------------ ProductSubcategoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_SUBCATEGORY_ID = "ProductSubcategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_SUBCATEGORY_ID} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID = "productSubcategoryId";

    // ------------------------------------------------------------------------------ ProductModelID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    // ------------------------------------------------------------------------------- SellStartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SELL_START_DATE = "SellStartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SELL_START_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SELL_START_DATE = "sellStartDate";

    // --------------------------------------------------------------------------------- SellEndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SELL_END_DATE = "SellEndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SELL_END_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SELL_END_DATE = "sellEndDate";

    // ---------------------------------------------------------------------------- DiscontinuedDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCONTINUED_DATE = "DiscontinuedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCONTINUED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCONTINUED_DATE = "discontinuedDate";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
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
    protected MappedProduct() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProduct that)) {
            return false;
        }
        return getProductId() != null
               && getProductId().equals(that.getProductId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedProduct.class.hashCode();
    }

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute is {@code null} or not
     * before the {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute, as the table's
     * {@code CK_Product_SellEndDate} constraint requires.
     * {@snippet lang = "sql":
     * ([SellEndDate]>=[SellStartDate] OR [SellEndDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute is {@code null},
     *         or the {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute is not before the
     *         {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute; {@code false} otherwise.
     */
    @AssertTrue(message = "sellEndDate must be null or not before sellStartDate")
    protected boolean isSellEndDateValid() {
        if (sellEndDate == null || sellStartDate == null) {
            return true;
        }
        return !sellEndDate.isBefore(sellStartDate);
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
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
    public void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------- productNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute.
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute with the
     * specified value.
     *
     * @param productNumber new value for {@value #ATTRIBUTE_NAME_PRODUCT_NUMBER} attribute.
     */
    public void setProductNumber(final String productNumber) {
        this.productNumber = productNumber;
    }

    // ------------------------------------------------------------------------------------ makeFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute.
     */
    public Boolean getMakeFlag() {
        return makeFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute with the specified
     * value.
     *
     * @param makeFlag new value for {@value #ATTRIBUTE_NAME_MAKE_FLAG} attribute.
     */
    public void setMakeFlag(final Boolean makeFlag) {
        this.makeFlag = makeFlag;
    }

    // --------------------------------------------------------------------------- finishedGoodsFlag

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute.
     */
    public Boolean getFinishedGoodsFlag() {
        return finishedGoodsFlag;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG} attribute with the
     * specified value.
     *
     * @param finishedGoodsFlag new value for {@value #ATTRIBUTE_NAME_FINISHED_GOODS_FLAG}
     *                          attribute.
     */
    public void setFinishedGoodsFlag(final Boolean finishedGoodsFlag) {
        this.finishedGoodsFlag = finishedGoodsFlag;
    }

    // --------------------------------------------------------------------------------------- color

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

    // ---------------------------------------------------------------------------- safetyStockLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute.
     */
    public Integer getSafetyStockLevel() {
        return safetyStockLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute with the
     * specified value.
     *
     * @param safetyStockLevel new value for {@value #ATTRIBUTE_NAME_SAFETY_STOCK_LEVEL} attribute.
     */
    public void setSafetyStockLevel(final Integer safetyStockLevel) {
        this.safetyStockLevel = safetyStockLevel;
    }

    // -------------------------------------------------------------------------------- reorderPoint

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public Integer getReorderPoint() {
        return reorderPoint;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute with the specified
     * value.
     *
     * @param reorderPoint new value for {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public void setReorderPoint(final Integer reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    // -------------------------------------------------------------------------------- standardCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute with the specified
     * value.
     *
     * @param standardCost new value for {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public void setStandardCost(final BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    // ----------------------------------------------------------------------------------- listPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     */
    public BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute with the specified
     * value.
     *
     * @param listPrice new value for {@value #ATTRIBUTE_NAME_LIST_PRICE} attribute.
     */
    public void setListPrice(final BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    // ---------------------------------------------------------------------------------------- size

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

    // ------------------------------------------------------------------------- sizeUnitMeasureCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute.
     */
    protected String getSizeUnitMeasureCode() {
        return sizeUnitMeasureCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param sizeUnitMeasureCode new value for {@value #ATTRIBUTE_NAME_SIZE_UNIT_MEASURE_CODE}
     *                            attribute.
     */
    protected void setSizeUnitMeasureCode(final String sizeUnitMeasureCode) {
        this.sizeUnitMeasureCode = sizeUnitMeasureCode;
    }

    // ----------------------------------------------------------------------- weightUnitMeasureCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute.
     */
    protected String getWeightUnitMeasureCode() {
        return weightUnitMeasureCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE} attribute with
     * the specified value. The attribute is read-only; the extending entity calls this to keep it
     * in step with the association which writes the column.
     *
     * @param weightUnitMeasureCode new value for {@value #ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE_CODE}
     *                              attribute.
     */
    protected void setWeightUnitMeasureCode(final String weightUnitMeasureCode) {
        this.weightUnitMeasureCode = weightUnitMeasureCode;
    }

    // -------------------------------------------------------------------------------------- weight

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEIGHT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEIGHT} attribute.
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEIGHT} attribute with the specified
     * value.
     *
     * @param weight new value for {@value #ATTRIBUTE_NAME_WEIGHT} attribute.
     */
    public void setWeight(final BigDecimal weight) {
        this.weight = weight;
    }

    // --------------------------------------------------------------------------- daysToManufacture

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute.
     */
    public Integer getDaysToManufacture() {
        return daysToManufacture;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE} attribute with the
     * specified value.
     *
     * @param daysToManufacture new value for {@value #ATTRIBUTE_NAME_DAYS_TO_MANUFACTURE}
     *                          attribute.
     */
    public void setDaysToManufacture(final Integer daysToManufacture) {
        this.daysToManufacture = daysToManufacture;
    }

    // --------------------------------------------------------------------------------- productLine

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     */
    public ProductLine getProductLine() {
        return productLine;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute with the specified
     * value.
     *
     * @param productLine new value for {@value #ATTRIBUTE_NAME_PRODUCT_LINE} attribute.
     */
    public void setProductLine(final ProductLine productLine) {
        this.productLine = productLine;
    }

    // -------------------------------------------------------------------------------- productClass

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute.
     */
    public ProductClass getProductClass() {
        return productClass;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute with the specified
     * value.
     *
     * @param productClass new value for {@value #ATTRIBUTE_NAME_PRODUCT_CLASS} attribute.
     */
    public void setProductClass(final ProductClass productClass) {
        this.productClass = productClass;
    }

    // --------------------------------------------------------------------------------------- style

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STYLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STYLE} attribute.
     */
    public ProductStyle getStyle() {
        return style;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STYLE} attribute with the specified value.
     *
     * @param style new value for {@value #ATTRIBUTE_NAME_STYLE} attribute.
     */
    public void setStyle(final ProductStyle style) {
        this.style = style;
    }

    // ------------------------------------------------------------------------ productSubcategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     */
    protected Integer getProductSubcategoryId() {
        return productSubcategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param productSubcategoryId new value for {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID}
     *                             attribute.
     */
    protected void setProductSubcategoryId(final Integer productSubcategoryId) {
        this.productSubcategoryId = productSubcategoryId;
    }

    // ------------------------------------------------------------------------------ productModelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    protected Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    protected void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // ------------------------------------------------------------------------------- sellStartDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute.
     */
    public LocalDateTime getSellStartDate() {
        return sellStartDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute with the
     * specified value.
     *
     * @param sellStartDate new value for {@value #ATTRIBUTE_NAME_SELL_START_DATE} attribute.
     */
    public void setSellStartDate(final LocalDateTime sellStartDate) {
        this.sellStartDate = sellStartDate;
    }

    // --------------------------------------------------------------------------------- sellEndDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute.
     */
    public LocalDateTime getSellEndDate() {
        return sellEndDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute with the specified
     * value.
     *
     * @param sellEndDate new value for {@value #ATTRIBUTE_NAME_SELL_END_DATE} attribute.
     */
    public void setSellEndDate(final LocalDateTime sellEndDate) {
        this.sellEndDate = sellEndDate;
    }

    // ---------------------------------------------------------------------------- discontinuedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute.
     */
    public LocalDateTime getDiscontinuedDate() {
        return discontinuedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute with the
     * specified value.
     *
     * @param discontinuedDate new value for {@value #ATTRIBUTE_NAME_DISCONTINUED_DATE} attribute.
     */
    public void setDiscontinuedDate(final LocalDateTime discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
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
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false,
            comment = "Primary key for Product records.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME,
            comment = "Name of the product.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRODUCT_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_NUMBER, nullable = false, insertable = true,
            updatable = true, length = COLUMN_LENGTH_PRODUCT_NUMBER,
            comment = "Unique product identification number.")
    private String productNumber;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MAKE_FLAG, nullable = false, insertable = true, updatable = true,
            comment = "0 = Product is purchased, 1 = Product is manufactured in-house.")
    @MsDefault(name = "DF_Product_MakeFlag", value = "((1))")
    private Boolean makeFlag;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FINISHED_GOODS_FLAG, nullable = false, insertable = true,
            updatable = true,
            comment = "0 = Product is not a salable item. 1 = Product is salable.")
    @MsDefault(name = "DF_Product_FinishedGoodsFlag", value = "((1))")
    private Boolean finishedGoodsFlag;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_COLOR)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COLOR, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_COLOR,
            comment = "Product color.")
    private String color;

    // ---------------------------------------------------------------------------------------------
    // CK_Product_SafetyStockLevel: ([SafetyStockLevel]>(0)); smallint's upper bound
    @Max(SqlServerConstants.SMALLINT_MAX)
    @Min(ATTRIBUTE_MIN_SAFETY_STOCK_LEVEL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SAFETY_STOCK_LEVEL, nullable = false, insertable = true,
            updatable = true,
            comment = "Minimum inventory quantity. ")
    private Integer safetyStockLevel;

    // ---------------------------------------------------------------------------------------------
    // CK_Product_ReorderPoint: ([ReorderPoint]>(0)); smallint's upper bound
    @Max(SqlServerConstants.SMALLINT_MAX)
    @Min(ATTRIBUTE_MIN_REORDER_POINT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REORDER_POINT, nullable = false, insertable = true, updatable = true,
            comment = "Inventory level that triggers a purchase order or work order. ")
    private Integer reorderPoint;

    // ---------------------------------------------------------------------------------------------
    // CK_Product_StandardCost: ([StandardCost]>=(0.00))
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_STANDARD_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STANDARD_COST, nullable = false, insertable = true, updatable = true,
            precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Standard cost of the product.")
    private BigDecimal standardCost;

    // ---------------------------------------------------------------------------------------------
    // CK_Product_ListPrice: ([ListPrice]>=(0.00))
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_LIST_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LIST_PRICE, nullable = false, insertable = true, updatable = true,
            precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Selling price.")
    private BigDecimal listPrice;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SIZE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIZE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_SIZE,
            comment = "Product size.")
    private String size;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Size(max = COLUMN_LENGTH_SIZE_UNIT_MEASURE_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIZE_UNIT_MEASURE_CODE, nullable = true, insertable = false,
            updatable = false, length = COLUMN_LENGTH_SIZE_UNIT_MEASURE_CODE,
            comment = "Unit of measure for Size column.")
    private String sizeUnitMeasureCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Size(max = COLUMN_LENGTH_WEIGHT_UNIT_MEASURE_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE, nullable = true, insertable = false,
            updatable = false, length = COLUMN_LENGTH_WEIGHT_UNIT_MEASURE_CODE,
            comment = "Unit of measure for Weight column.")
    private String weightUnitMeasureCode;

    // ---------------------------------------------------------------------------------------------
    // CK_Product_Weight: ([Weight]>(0.00))
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_WEIGHT)
    @DecimalMin(value = ATTRIBUTE_DECIMAL_MIN_WEIGHT_EXCLUSIVE, inclusive = false)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEIGHT, nullable = true, insertable = true, updatable = true,
            precision = COLUMN_PRECISION_WEIGHT, scale = COLUMN_SCALE_WEIGHT,
            comment = "Product weight.")
    private BigDecimal weight;

    // ---------------------------------------------------------------------------------------------
    // CK_Product_DaysToManufacture: ([DaysToManufacture]>=(0)); int's upper bound
    @Max(SqlServerConstants.INT_MAX)
    @Min(ATTRIBUTE_MIN_DAYS_TO_MANUFACTURE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DAYS_TO_MANUFACTURE, nullable = false, insertable = true,
            updatable = true,
            comment = "Number of days required to manufacture the product.")
    private Integer daysToManufacture;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = ProductLineConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_LINE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_PRODUCT_LINE,
            comment = "R = Road, M = Mountain, T = Touring, S = Standard")
    private ProductLine productLine;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = ProductClassConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_CLASS, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_PRODUCT_CLASS,
            comment = "H = High, M = Medium, L = Low")
    private ProductClass productClass;

    // ---------------------------------------------------------------------------------------------
    @Convert(converter = ProductStyleConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STYLE, nullable = true, insertable = true, updatable = true,
            length = COLUMN_LENGTH_STYLE,
            comment = "W = Womens, M = Mens, U = Universal")
    private ProductStyle style;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_SUBCATEGORY_ID, nullable = true, insertable = false,
            updatable = false,
            comment = "Product is a member of this product subcategory. Foreign key to "
                      + "ProductSubCategory.ProductSubCategoryID. ")
    private Integer productSubcategoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID, nullable = true, insertable = false,
            updatable = false,
            comment = "Product is a member of this product model. Foreign key to "
                      + "ProductModel.ProductModelID.")
    private Integer productModelId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SELL_START_DATE, nullable = false, insertable = true,
            updatable = true,
            comment = "Date the product was available for sale.")
    private LocalDateTime sellStartDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SELL_END_DATE, nullable = true, insertable = true, updatable = true,
            comment = "Date the product was no longer available for sale.")
    private LocalDateTime sellEndDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCONTINUED_DATE, nullable = true, insertable = true,
            updatable = true,
            comment = "Date the product was discontinued.")
    private LocalDateTime discontinuedDate;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_Product_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_Product_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

