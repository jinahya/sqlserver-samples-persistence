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
 * The identifier class of the {@code Production.ProductInventory} table, whose rows are identified
 * by {@code ProductID}, {@code LocationID}.
 * <p>
 * An entity which extends {@link MappedProductInventory} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped
 * superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class ProductInventoryId {

    /**
     * The name of the attribute which maps the
     * {@value MappedProductInventory#COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductInventory#COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductInventoryId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",locationId=" + locationId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductInventoryId that)) {
            return false;
        }
        return Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getLocationId(), that.getLocationId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getProductId(), getLocationId());
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

    // ---------------------------------------------------------------------------------- locationId

    /**
     * Returns current value of the {@code locationId} attribute.
     *
     * @return current value of the {@code locationId} attribute.
     */
    public Long getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of the {@code locationId} attribute with the specified value.
     *
     * @param locationId new value for the {@code locationId} attribute.
     */
    public void setLocationId(final Long locationId) {
        this.locationId = locationId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code productId} attribute.
     */
    @NotNull
    private Integer productId;

    /**
     * The value of the {@code locationId} attribute.
     */
    @NotNull
    private Long locationId;
}

