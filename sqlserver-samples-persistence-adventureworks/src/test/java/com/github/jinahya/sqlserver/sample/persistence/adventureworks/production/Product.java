package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModel;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductSubcategory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedUnitMeasure;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Production.Product} table.
 */
@Entity(name = "ProductionProduct")
@Table(schema = MappedProduct.TABLE_SCHEMA,
       name = MappedProduct.TABLE_NAME,
       comment = "Products sold or used in the manfacturing of sold products.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Product_Name",
                       columnNames = {
                               Product.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Product_ProductNumber",
                       columnNames = {
                               Product.COLUMN_NAME_PRODUCT_NUMBER
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Product_rowguid",
                       columnNames = {
                               Product.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_Product_Class",
                       constraint = "(upper([Class])='H' OR upper([Class])='M' OR "
                                    + "upper([Class])='L' OR [Class] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_Product_DaysToManufacture",
                       constraint = "([DaysToManufacture]>=(0))"
               ),
               @CheckConstraint(
                       name = "CK_Product_ListPrice",
                       constraint = "([ListPrice]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_Product_ProductLine",
                       constraint = "(upper([ProductLine])='R' OR upper([ProductLine])='M' OR "
                                    + "upper([ProductLine])='T' OR upper([ProductLine])='S' OR "
                                    + "[ProductLine] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_Product_ReorderPoint",
                       constraint = "([ReorderPoint]>(0))"
               ),
               @CheckConstraint(
                       name = "CK_Product_SafetyStockLevel",
                       constraint = "([SafetyStockLevel]>(0))"
               ),
               @CheckConstraint(
                       name = "CK_Product_SellEndDate",
                       constraint = "([SellEndDate]>=[SellStartDate] OR [SellEndDate] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_Product_StandardCost",
                       constraint = "([StandardCost]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_Product_Style",
                       constraint = "(upper([Style])='U' OR upper([Style])='M' OR "
                                    + "upper([Style])='W' OR [Style] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_Product_Weight",
                       constraint = "([Weight]>(0.00))"
               )
       }
)
public class Product
        extends MappedProduct {

    /**
     * The name of the attribute of the {@code FK_Product_UnitMeasure_SizeUnitMeasureCode} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE_UNIT_MEASURE = "sizeUnitMeasure";

    /**
     * The name of the attribute of the {@code FK_Product_UnitMeasure_WeightUnitMeasureCode} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEIGHT_UNIT_MEASURE = "weightUnitMeasure";

    /**
     * The name of the attribute of the {@code FK_Product_ProductModel_ProductModelID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL = "productModel";

    /**
     * The name of the attribute of the {@code FK_Product_ProductSubcategory_ProductSubcategoryID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY = "productSubcategory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Product() {
        super();
    }

    // ----------------------------------------------------------------------------- sizeUnitMeasure

    /**
     * Returns the {@code UnitMeasure} which the {@code FK_Product_UnitMeasure_SizeUnitMeasureCode}
     * foreign key references.
     *
     * @return the {@code UnitMeasure} referenced; {@code null} when none is.
     */
    public UnitMeasure getSizeUnitMeasure() {
        return sizeUnitMeasure;
    }

    /**
     * Replaces the {@code UnitMeasure} which the {@code FK_Product_UnitMeasure_SizeUnitMeasureCode}
     * foreign key references, and the value of the column it consists of.
     *
     * @param sizeUnitMeasure the {@code UnitMeasure} to reference; {@code null} for none.
     */
    public void setSizeUnitMeasure(final UnitMeasure sizeUnitMeasure) {
        this.sizeUnitMeasure = sizeUnitMeasure;
        setSizeUnitMeasureCode(
                sizeUnitMeasure == null ? null : sizeUnitMeasure.getUnitMeasureCode());
    }

    // --------------------------------------------------------------------------- weightUnitMeasure

    /**
     * Returns the {@code UnitMeasure} which the
     * {@code FK_Product_UnitMeasure_WeightUnitMeasureCode} foreign key references.
     *
     * @return the {@code UnitMeasure} referenced; {@code null} when none is.
     */
    public UnitMeasure getWeightUnitMeasure() {
        return weightUnitMeasure;
    }

    /**
     * Replaces the {@code UnitMeasure} which the
     * {@code FK_Product_UnitMeasure_WeightUnitMeasureCode} foreign key references, and the value of
     * the column it consists of.
     *
     * @param weightUnitMeasure the {@code UnitMeasure} to reference; {@code null} for none.
     */
    public void setWeightUnitMeasure(final UnitMeasure weightUnitMeasure) {
        this.weightUnitMeasure = weightUnitMeasure;
        setWeightUnitMeasureCode(
                weightUnitMeasure == null ? null : weightUnitMeasure.getUnitMeasureCode());
    }

    // -------------------------------------------------------------------------------- productModel

    /**
     * Returns the {@code ProductModel} which the {@code FK_Product_ProductModel_ProductModelID}
     * foreign key references.
     *
     * @return the {@code ProductModel} referenced; {@code null} when none is.
     */
    public ProductModel getProductModel() {
        return productModel;
    }

    /**
     * Replaces the {@code ProductModel} which the {@code FK_Product_ProductModel_ProductModelID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param productModel the {@code ProductModel} to reference; {@code null} for none.
     */
    public void setProductModel(final ProductModel productModel) {
        this.productModel = productModel;
        setProductModelId(productModel == null ? null : productModel.getProductModelId());
    }

    // -------------------------------------------------------------------------- productSubcategory

    /**
     * Returns the {@code ProductSubcategory} which the
     * {@code FK_Product_ProductSubcategory_ProductSubcategoryID} foreign key references.
     *
     * @return the {@code ProductSubcategory} referenced; {@code null} when none is.
     */
    public ProductSubcategory getProductSubcategory() {
        return productSubcategory;
    }

    /**
     * Replaces the {@code ProductSubcategory} which the
     * {@code FK_Product_ProductSubcategory_ProductSubcategoryID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param productSubcategory the {@code ProductSubcategory} to reference; {@code null} for
     *                           none.
     */
    public void setProductSubcategory(final ProductSubcategory productSubcategory) {
        this.productSubcategory = productSubcategory;
        setProductSubcategoryId(
                productSubcategory == null ? null : productSubcategory.getProductSubcategoryId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SIZE_UNIT_MEASURE_CODE,
                referencedColumnName = MappedUnitMeasure.COLUMN_NAME_UNIT_MEASURE_CODE,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private UnitMeasure sizeUnitMeasure;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_WEIGHT_UNIT_MEASURE_CODE,
                referencedColumnName = MappedUnitMeasure.COLUMN_NAME_UNIT_MEASURE_CODE,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private UnitMeasure weightUnitMeasure;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_MODEL_ID,
                referencedColumnName = MappedProductModel.COLUMN_NAME_PRODUCT_MODEL_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private ProductModel productModel;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_SUBCATEGORY_ID,
                referencedColumnName = MappedProductSubcategory.COLUMN_NAME_PRODUCT_SUBCATEGORY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private ProductSubcategory productSubcategory;
}

