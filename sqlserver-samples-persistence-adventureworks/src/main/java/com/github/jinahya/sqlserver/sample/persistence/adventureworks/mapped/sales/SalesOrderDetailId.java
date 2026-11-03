package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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
 * The identifier class of the {@code Sales.SalesOrderDetail} table, whose rows are identified by
 * {@code SalesOrderID}, {@code SalesOrderDetailID}.
 * <p>
 * An entity which extends {@link MappedSalesOrderDetail} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped
 * superclass's {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class SalesOrderDetailId {

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesOrderDetail#COLUMN_NAME_SALES_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesOrderDetail#COLUMN_NAME_SALES_ORDER_DETAIL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_DETAIL_ID = "salesOrderDetailId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public SalesOrderDetailId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesOrderId=" + salesOrderId
               + ",salesOrderDetailId=" + salesOrderDetailId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesOrderDetailId that)) {
            return false;
        }
        return Objects.equals(getSalesOrderId(), that.getSalesOrderId()) &&
               Objects.equals(getSalesOrderDetailId(), that.getSalesOrderDetailId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getSalesOrderId(), getSalesOrderDetailId());
    }

    // -------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of the {@code salesOrderId} attribute.
     *
     * @return current value of the {@code salesOrderId} attribute.
     */
    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of the {@code salesOrderId} attribute with the specified value.
     *
     * @param salesOrderId new value for the {@code salesOrderId} attribute.
     */
    public void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
    }

    // -------------------------------------------------------------------------- salesOrderDetailId

    /**
     * Returns current value of the {@code salesOrderDetailId} attribute.
     *
     * @return current value of the {@code salesOrderDetailId} attribute.
     */
    public Integer getSalesOrderDetailId() {
        return salesOrderDetailId;
    }

    /**
     * Replaces current value of the {@code salesOrderDetailId} attribute with the specified value.
     *
     * @param salesOrderDetailId new value for the {@code salesOrderDetailId} attribute.
     */
    public void setSalesOrderDetailId(final Integer salesOrderDetailId) {
        this.salesOrderDetailId = salesOrderDetailId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code salesOrderId} attribute.
     */
    @NotNull
    private Integer salesOrderId;

    /**
     * The value of the {@code salesOrderDetailId} attribute.
     */
    @NotNull
    private Integer salesOrderDetailId;
}

