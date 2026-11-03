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
 * The identifier class of the {@code Production.ProductModelProductDescriptionCulture} table, whose rows are identified
 * by {@code ProductModelID}, {@code ProductDescriptionID}, {@code CultureID}.
 * <p>
 * An entity which extends {@link MappedProductModelProductDescriptionCulture} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedProductModelProductDescriptionCultureId implements Serializable {

    private static final long serialVersionUID = 1L;

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedProductModelProductDescriptionCultureId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",productDescriptionId=" + productDescriptionId
               + ",cultureId=" + cultureId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductModelProductDescriptionCultureId that)) {
            return false;
        }
        return Objects.equals(productModelId, that.productModelId) &&
               Objects.equals(productDescriptionId, that.productDescriptionId) &&
               Objects.equals(cultureId, that.cultureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productModelId, productDescriptionId, cultureId);
    }

    // ------------------------------------------------------------------------------ productModelId

    /**
     * Returns current value of the {@code productModelId} attribute.
     *
     * @return current value of the {@code productModelId} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of the {@code productModelId} attribute with the specified value.
     *
     * @param productModelId new value for the {@code productModelId} attribute.
     */
    public void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // ------------------------------------------------------------------------ productDescriptionId

    /**
     * Returns current value of the {@code productDescriptionId} attribute.
     *
     * @return current value of the {@code productDescriptionId} attribute.
     */
    public Integer getProductDescriptionId() {
        return productDescriptionId;
    }

    /**
     * Replaces current value of the {@code productDescriptionId} attribute with the specified value.
     *
     * @param productDescriptionId new value for the {@code productDescriptionId} attribute.
     */
    public void setProductDescriptionId(final Integer productDescriptionId) {
        this.productDescriptionId = productDescriptionId;
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
     * The value of the {@code productModelId} attribute.
     */
    private Integer productModelId;

    /**
     * The value of the {@code productDescriptionId} attribute.
     */
    private Integer productDescriptionId;

    /**
     * The value of the {@code cultureId} attribute.
     */
    private String cultureId;
}

