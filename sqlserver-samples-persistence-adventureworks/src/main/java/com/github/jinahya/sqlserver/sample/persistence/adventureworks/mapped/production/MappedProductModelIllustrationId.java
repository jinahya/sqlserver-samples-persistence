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
 * The identifier class of the {@code Production.ProductModelIllustration} table, whose rows are identified by
 * {@code ProductModelID}, {@code IllustrationID}.
 * <p>
 * An entity which extends {@link MappedProductModelIllustration} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedProductModelIllustrationId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedProductModelIllustrationId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",illustrationId=" + illustrationId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductModelIllustrationId that)) {
            return false;
        }
        return Objects.equals(productModelId, that.productModelId) &&
               Objects.equals(illustrationId, that.illustrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productModelId, illustrationId);
    }

    // -------------------------------------------------------------------------------------------------- productModelId

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

    // -------------------------------------------------------------------------------------------------- illustrationId

    /**
     * Returns current value of the {@code illustrationId} attribute.
     *
     * @return current value of the {@code illustrationId} attribute.
     */
    public Integer getIllustrationId() {
        return illustrationId;
    }

    /**
     * Replaces current value of the {@code illustrationId} attribute with the specified value.
     *
     * @param illustrationId new value for the {@code illustrationId} attribute.
     */
    public void setIllustrationId(final Integer illustrationId) {
        this.illustrationId = illustrationId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer productModelId;

    private Integer illustrationId;
}

