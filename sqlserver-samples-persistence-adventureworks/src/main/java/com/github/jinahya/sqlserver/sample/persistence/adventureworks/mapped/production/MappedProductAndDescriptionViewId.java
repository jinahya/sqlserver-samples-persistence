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
 * The identifier class of the {@code Production.vProductAndDescription} view, whose rows are identified by
 * {@code ProductID}, {@code CultureID}.
 * <p>
 * An entity which extends {@link MappedProductAndDescriptionView} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedProductAndDescriptionViewId implements Serializable {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedProductAndDescriptionViewId() {
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
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductAndDescriptionViewId that)) {
            return false;
        }
        return Objects.equals(productId, that.productId) &&
               Objects.equals(cultureId, that.cultureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cultureId);
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
    private Integer productId;

    /**
     * The value of the {@code cultureId} attribute.
     */
    private String cultureId;
}

