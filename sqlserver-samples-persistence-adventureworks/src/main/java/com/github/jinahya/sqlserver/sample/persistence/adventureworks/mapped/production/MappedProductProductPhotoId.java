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

import java.io.Serializable;
import java.util.Objects;

/**
 * The identifier class of the {@code Production.ProductProductPhoto} table, whose rows are identified by
 * {@code ProductID}, {@code ProductPhotoID}.
 * <p>
 * An entity which extends {@link MappedProductProductPhoto} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedProductProductPhotoId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedProductProductPhotoId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",productPhotoId=" + productPhotoId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductProductPhotoId that)) {
            return false;
        }
        return Objects.equals(productId, that.productId) &&
               Objects.equals(productPhotoId, that.productPhotoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productPhotoId);
    }

    // ------------------------------------------------------------------------------------------------------- productId

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

    // -------------------------------------------------------------------------------------------------- productPhotoId

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

    // -----------------------------------------------------------------------------------------------------------------
    private Integer productId;

    private Integer productPhotoId;
}

