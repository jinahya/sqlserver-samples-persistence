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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Production.ProductProductPhoto} table, whose rows are
 * identified by {@code ProductID}, {@code ProductPhotoID}.
 * <p>
 * {@link MappedProductProductPhoto} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class ProductProductPhotoId {

    /**
     * The name of the attribute which maps the
     * {@value MappedProductProductPhoto#COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductProductPhoto#COLUMN_NAME_PRODUCT_PHOTO_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_PHOTO_ID = "productPhotoId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductProductPhotoId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",productPhotoId=" + productPhotoId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductProductPhotoId that)) {
            return false;
        }
        return Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getProductPhotoId(), that.getProductPhotoId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getProductId(), getProductPhotoId());
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of the {@code productId} attribute.
     *
     * @return current value of the {@code productId} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of the {@code productId} attribute with the specified value.
     *
     * @param productId new value for the {@code productId} attribute.
     */
    public void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------ productPhotoId

    /**
     * Returns current value of the {@code productPhotoId} attribute.
     *
     * @return current value of the {@code productPhotoId} attribute.
     */
    public Integer getProductPhotoId() {
        return productPhotoId;
    }

    /**
     * Replaces current value of the {@code productPhotoId} attribute with the specified value.
     *
     * @param productPhotoId new value for the {@code productPhotoId} attribute.
     */
    public void setProductPhotoId(final Integer productPhotoId) {
        this.productPhotoId = productPhotoId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code productId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedProductProductPhoto.COLUMN_NAME_PRODUCT_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedProductProductPhoto.COLUMN_NAME_PRODUCT_ID,
                          value = "Product identification number. Foreign key to "
                                  + "Product.ProductID.")
    private Integer productId;

    /**
     * The value of the {@code productPhotoId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedProductProductPhoto.COLUMN_NAME_PRODUCT_PHOTO_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedProductProductPhoto.COLUMN_NAME_PRODUCT_PHOTO_ID,
                          value = "Product photo identification number. Foreign key to "
                                  + "ProductPhoto.ProductPhotoID.")
    private Integer productPhotoId;
}

