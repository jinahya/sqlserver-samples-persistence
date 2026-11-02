package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.purchasing;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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
 * The identifier class of the {@code Purchasing.PurchaseOrderDetail} table, whose rows are identified by
 * {@code PurchaseOrderID}, {@code PurchaseOrderDetailID}.
 * <p>
 * An entity which extends {@link MappedPurchaseOrderDetail} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedPurchaseOrderDetailId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedPurchaseOrderDetailId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "purchaseOrderId=" + purchaseOrderId
               + ",purchaseOrderDetailId=" + purchaseOrderDetailId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedPurchaseOrderDetailId that)) {
            return false;
        }
        return Objects.equals(purchaseOrderId, that.purchaseOrderId) &&
               Objects.equals(purchaseOrderDetailId, that.purchaseOrderDetailId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseOrderId, purchaseOrderDetailId);
    }

    // ------------------------------------------------------------------------------------------------- purchaseOrderId

    /**
     * Returns current value of the {@code purchaseOrderId} attribute.
     *
     * @return current value of the {@code purchaseOrderId} attribute.
     */
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Replaces current value of the {@code purchaseOrderId} attribute with the specified value.
     *
     * @param purchaseOrderId new value for the {@code purchaseOrderId} attribute.
     */
    public void setPurchaseOrderId(final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // ------------------------------------------------------------------------------------------- purchaseOrderDetailId

    /**
     * Returns current value of the {@code purchaseOrderDetailId} attribute.
     *
     * @return current value of the {@code purchaseOrderDetailId} attribute.
     */
    public Integer getPurchaseOrderDetailId() {
        return purchaseOrderDetailId;
    }

    /**
     * Replaces current value of the {@code purchaseOrderDetailId} attribute with the specified value.
     *
     * @param purchaseOrderDetailId new value for the {@code purchaseOrderDetailId} attribute.
     */
    public void setPurchaseOrderDetailId(final Integer purchaseOrderDetailId) {
        this.purchaseOrderDetailId = purchaseOrderDetailId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer purchaseOrderId;

    private Integer purchaseOrderDetailId;
}

