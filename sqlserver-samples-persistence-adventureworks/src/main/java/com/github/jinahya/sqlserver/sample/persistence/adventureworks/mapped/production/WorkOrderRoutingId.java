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
 * The identifier class of the {@code Production.WorkOrderRouting} table, whose rows are identified
 * by {@code WorkOrderID}, {@code ProductID}, {@code OperationSequence}.
 * <p>
 * An entity which extends {@link MappedWorkOrderRouting} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped
 * superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class WorkOrderRoutingId {

    /**
     * The name of the attribute which maps the
     * {@value MappedWorkOrderRouting#COLUMN_NAME_WORK_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER_ID = "workOrderId";

    /**
     * The name of the attribute which maps the
     * {@value MappedWorkOrderRouting#COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    /**
     * The name of the attribute which maps the
     * {@value MappedWorkOrderRouting#COLUMN_NAME_OPERATION_SEQUENCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OPERATION_SEQUENCE = "operationSequence";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public WorkOrderRoutingId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workOrderId=" + workOrderId
               + ",productId=" + productId
               + ",operationSequence=" + operationSequence
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkOrderRoutingId that)) {
            return false;
        }
        return Objects.equals(getWorkOrderId(), that.getWorkOrderId()) &&
               Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getOperationSequence(), that.getOperationSequence());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getWorkOrderId(), getProductId(), getOperationSequence());
    }

    // --------------------------------------------------------------------------------- workOrderId

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

    // --------------------------------------------------------------------------- operationSequence

    /**
     * Returns current value of the {@code operationSequence} attribute.
     *
     * @return current value of the {@code operationSequence} attribute.
     */
    public Integer getOperationSequence() {
        return operationSequence;
    }

    /**
     * Replaces current value of the {@code operationSequence} attribute with the specified value.
     *
     * @param operationSequence new value for the {@code operationSequence} attribute.
     */
    public void setOperationSequence(final Integer operationSequence) {
        this.operationSequence = operationSequence;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code workOrderId} attribute.
     */
    @NotNull
    private Integer workOrderId;

    /**
     * The value of the {@code productId} attribute.
     */
    @NotNull
    private Integer productId;

    /**
     * The value of the {@code operationSequence} attribute.
     */
    @NotNull
    private Integer operationSequence;
}

