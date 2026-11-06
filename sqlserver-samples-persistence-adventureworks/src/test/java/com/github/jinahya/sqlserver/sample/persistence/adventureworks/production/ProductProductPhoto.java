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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductPhoto;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductProductPhoto;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductProductPhotoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity which maps the {@code Production.ProductProductPhoto} table.
 * <p>
 * Each attribute of the embedded {@link ProductProductPhotoId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "ProductionProductProductPhoto")
@Table(schema = MappedProductProductPhoto.TABLE_SCHEMA, name = MappedProductProductPhoto.TABLE_NAME,
       comment = "Cross-reference table mapping products and product photos.")
public class ProductProductPhoto
        extends MappedProductProductPhoto {

    /**
     * The name of the attribute of the {@code FK_ProductProductPhoto_Product_ProductID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_ProductProductPhoto_ProductPhoto_ProductPhotoID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_PHOTO = "productPhoto";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code product} and {@code productPhoto}.
     *
     * @param product      the {@code Product} to reference.
     * @param productPhoto the {@code ProductPhoto} to reference.
     * @return a new instance which references {@code product} and {@code productPhoto}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static ProductProductPhoto of(final Product product,
                                         final ProductPhoto productPhoto) {
        Objects.requireNonNull(product, "product is null");
        Objects.requireNonNull(productPhoto, "productPhoto is null");
        final var instance = new ProductProductPhoto();
        instance.product = product;
        instance.productPhoto = productPhoto;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductProductPhoto() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected ProductProductPhotoId getId_() {
        return id_;
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductProductPhoto_Product_ProductID}
     * foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    // -------------------------------------------------------------------------------- productPhoto

    /**
     * Returns the {@code ProductPhoto} which the
     * {@code FK_ProductProductPhoto_ProductPhoto_ProductPhotoID} foreign key references.
     *
     * @return the {@code ProductPhoto} referenced; {@code null} when none is.
     */
    public ProductPhoto getProductPhoto() {
        return productPhoto;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private ProductProductPhotoId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductProductPhotoId.ATTRIBUTE_NAME_PRODUCT_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Product product;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductProductPhotoId.ATTRIBUTE_NAME_PRODUCT_PHOTO_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_PHOTO_ID,
                referencedColumnName = MappedProductPhoto.COLUMN_NAME_PRODUCT_PHOTO_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private ProductPhoto productPhoto;
}

