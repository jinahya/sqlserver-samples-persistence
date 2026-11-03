package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing;

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
 * The identifier class of the {@code Purchasing.ProductVendor} table, whose rows are identified by {@code ProductID},
 * {@code BusinessEntityID}.
 * <p>
 * An entity which extends {@link MappedProductVendor} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class ProductVendorId {

    /**
     * The name of the attribute which maps the
     * {@value MappedProductVendor#COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductVendor#COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductVendorId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",businessEntityId=" + businessEntityId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductVendorId that)) {
            return false;
        }
        return Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getBusinessEntityId(), that.getBusinessEntityId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getProductId(), getBusinessEntityId());
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

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of the {@code businessEntityId} attribute.
     *
     * @return current value of the {@code businessEntityId} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of the {@code businessEntityId} attribute with the specified value.
     *
     * @param businessEntityId new value for the {@code businessEntityId} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ---------------------------------------------------------------------------------------------
    /**
     * The value of the {@code productId} attribute.
     */
    @NotNull
    private Integer productId;

    /**
     * The value of the {@code businessEntityId} attribute.
     */
    @NotNull
    private Integer businessEntityId;
}

