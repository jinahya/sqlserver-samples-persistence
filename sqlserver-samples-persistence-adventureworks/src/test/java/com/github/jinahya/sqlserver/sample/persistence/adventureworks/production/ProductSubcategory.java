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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductCategory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductSubcategory;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Production.ProductSubcategory} table.
 */
@Entity(name = "ProductionProductSubcategory")
@Table(schema = MappedProductSubcategory.TABLE_SCHEMA,
       name = MappedProductSubcategory.TABLE_NAME,
       comment = "Product subcategories. See ProductCategory table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_ProductSubcategory_Name",
                       columnNames = {
                               ProductSubcategory.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_ProductSubcategory_rowguid",
                       columnNames = {
                               ProductSubcategory.COLUMN_NAME_ROWGUID
                       }
               )
       }
)
public class ProductSubcategory
        extends MappedProductSubcategory {

    /**
     * The name of the attribute of the
     * {@code FK_ProductSubcategory_ProductCategory_ProductCategoryID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_CATEGORY = "productCategory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductSubcategory() {
        super();
    }

    // ----------------------------------------------------------------------------- productCategory

    /**
     * Returns the {@code ProductCategory} which the
     * {@code FK_ProductSubcategory_ProductCategory_ProductCategoryID} foreign key references.
     *
     * @return the {@code ProductCategory} referenced; {@code null} when none is.
     */
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    /**
     * Replaces the {@code ProductCategory} which the
     * {@code FK_ProductSubcategory_ProductCategory_ProductCategoryID} foreign key references, and
     * the value of the column it consists of.
     *
     * @param productCategory the {@code ProductCategory} to reference; {@code null} for none.
     */
    public void setProductCategory(final ProductCategory productCategory) {
        this.productCategory = productCategory;
        setProductCategoryId(
                productCategory == null ? null : productCategory.getProductCategoryId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_CATEGORY_ID,
                referencedColumnName = MappedProductCategory.COLUMN_NAME_PRODUCT_CATEGORY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private ProductCategory productCategory;
}

