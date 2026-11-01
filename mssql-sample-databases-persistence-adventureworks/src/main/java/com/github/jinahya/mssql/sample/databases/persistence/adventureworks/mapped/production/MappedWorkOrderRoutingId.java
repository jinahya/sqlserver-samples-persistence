package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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
 * The identifier class of the {@code Production.WorkOrderRouting} table, whose rows are identified by
 * {@code WorkOrderID}, {@code ProductID}, {@code OperationSequence}.
 * <p>
 * An entity which extends {@link MappedWorkOrderRouting} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedWorkOrderRoutingId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedWorkOrderRoutingId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workOrderId=" + workOrderId
               + ",productId=" + productId
               + ",operationSequence=" + operationSequence
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedWorkOrderRoutingId that)) {
            return false;
        }
        return Objects.equals(workOrderId, that.workOrderId) &&
               Objects.equals(productId, that.productId) &&
               Objects.equals(operationSequence, that.operationSequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workOrderId, productId, operationSequence);
    }

    // ----------------------------------------------------------------------------------------------------- workOrderId

    /**
     * Returns current value of the {@code workOrderId} attribute.
     *
     * @return current value of the {@code workOrderId} attribute.
     */
    public Integer getWorkOrderId() {
        return workOrderId;
    }

    /**
     * Replaces current value of the {@code workOrderId} attribute with the specified value.
     *
     * @param workOrderId new value for the {@code workOrderId} attribute.
     */
    public void setWorkOrderId(final Integer workOrderId) {
        this.workOrderId = workOrderId;
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

    // ----------------------------------------------------------------------------------------------- operationSequence

    /**
     * Returns current value of the {@code operationSequence} attribute.
     *
     * @return current value of the {@code operationSequence} attribute.
     */
    public Short getOperationSequence() {
        return operationSequence;
    }

    /**
     * Replaces current value of the {@code operationSequence} attribute with the specified value.
     *
     * @param operationSequence new value for the {@code operationSequence} attribute.
     */
    public void setOperationSequence(final Short operationSequence) {
        this.operationSequence = operationSequence;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer workOrderId;

    private Integer productId;

    private Short operationSequence;
}

