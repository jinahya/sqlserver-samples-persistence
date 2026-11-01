package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.production;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production.MappedProductPhoto;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production.MappedProductProductPhoto;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production.MappedProductProductPhotoId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.ProductProductPhoto} table.
 */
@Entity(name = "ProductionProductProductPhoto")
@Table(schema = MappedProductProductPhoto.TABLE_SCHEMA, name = MappedProductProductPhoto.TABLE_NAME)
@IdClass(MappedProductProductPhotoId.class)
public class ProductProductPhoto extends MappedProductProductPhoto {

    /**
     * The name of the attribute of the {@code FK_ProductProductPhoto_Product_ProductID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_ProductProductPhoto_ProductPhoto_ProductPhotoID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_PHOTO = "productPhoto";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductProductPhoto() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductProductPhoto_Product_ProductID} foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ProductProductPhoto_Product_ProductID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // ---------------------------------------------------------------------------------------------------- productPhoto

    /**
     * Returns the {@code ProductPhoto} which the {@code FK_ProductProductPhoto_ProductPhoto_ProductPhotoID} foreign key
     * references.
     *
     * @return the {@code ProductPhoto} referenced; {@code null} when none is.
     */
    public ProductPhoto getProductPhoto() {
        return productPhoto;
    }

    /**
     * Replaces the {@code ProductPhoto} which the {@code FK_ProductProductPhoto_ProductPhoto_ProductPhotoID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param productPhoto the {@code ProductPhoto} to reference; {@code null} for none.
     */
    public void setProductPhoto(final ProductPhoto productPhoto) {
        this.productPhoto = productPhoto;
        setProductPhotoId(productPhoto == null ? null : productPhoto.getProductPhotoId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_PHOTO_ID,
                referencedColumnName = MappedProductPhoto.COLUMN_NAME_PRODUCT_PHOTO_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private ProductPhoto productPhoto;
}

