package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales;

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
 * The identifier class of the {@code Sales.SpecialOfferProduct} table, whose rows are identified by
 * {@code SpecialOfferID}, {@code ProductID}.
 * <p>
 * An entity which extends {@link MappedSpecialOfferProduct} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedSpecialOfferProductId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedSpecialOfferProductId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "specialOfferId=" + specialOfferId
               + ",productId=" + productId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSpecialOfferProductId that)) {
            return false;
        }
        return Objects.equals(specialOfferId, that.specialOfferId) &&
               Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialOfferId, productId);
    }

    // -------------------------------------------------------------------------------------------------- specialOfferId

    /**
     * Returns current value of the {@code specialOfferId} attribute.
     *
     * @return current value of the {@code specialOfferId} attribute.
     */
    public Integer getSpecialOfferId() {
        return specialOfferId;
    }

    /**
     * Replaces current value of the {@code specialOfferId} attribute with the specified value.
     *
     * @param specialOfferId new value for the {@code specialOfferId} attribute.
     */
    public void setSpecialOfferId(final Integer specialOfferId) {
        this.specialOfferId = specialOfferId;
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

    // -----------------------------------------------------------------------------------------------------------------
    private Integer specialOfferId;

    private Integer productId;
}

