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

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Production.vProductAndDescription} view, whose rows are
 * identified by {@code ProductID}, {@code CultureID}.
 * <p>
 * An entity which extends {@link MappedProductAndDescription} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped
 * superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class ProductAndDescriptionId {

    /**
     * The name of the attribute which maps the
     * {@value MappedProductAndDescription#COLUMN_NAME_PRODUCT_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductAndDescription#COLUMN_NAME_CULTURE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CULTURE_ID = "cultureId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductAndDescriptionId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",cultureId=" + cultureId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductAndDescriptionId that)) {
            return false;
        }
        return Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getCultureId(), that.getCultureId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getProductId(), getCultureId());
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

    // ----------------------------------------------------------------------------------- cultureId

    /**
     * Returns current value of the {@code cultureId} attribute.
     *
     * @return current value of the {@code cultureId} attribute.
     */
    public String getCultureId() {
        return cultureId;
    }

    /**
     * Replaces current value of the {@code cultureId} attribute with the specified value.
     *
     * @param cultureId new value for the {@code cultureId} attribute.
     */
    public void setCultureId(final String cultureId) {
        this.cultureId = cultureId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code productId} attribute.
     */
    @NotNull
    private Integer productId;

    /**
     * The value of the {@code cultureId} attribute.
     */
    @NotNull
    private String cultureId;
}

