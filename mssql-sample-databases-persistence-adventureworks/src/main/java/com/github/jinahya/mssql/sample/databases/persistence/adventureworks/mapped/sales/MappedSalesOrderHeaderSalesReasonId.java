package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales;

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
 * The identifier class of the {@code Sales.SalesOrderHeaderSalesReason} table, whose rows are identified by
 * {@code SalesOrderID}, {@code SalesReasonID}.
 * <p>
 * An entity which extends {@link MappedSalesOrderHeaderSalesReason} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedSalesOrderHeaderSalesReasonId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedSalesOrderHeaderSalesReasonId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesOrderId=" + salesOrderId
               + ",salesReasonId=" + salesReasonId
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSalesOrderHeaderSalesReasonId that)) {
            return false;
        }
        return Objects.equals(salesOrderId, that.salesOrderId) &&
               Objects.equals(salesReasonId, that.salesReasonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesOrderId, salesReasonId);
    }

    // ---------------------------------------------------------------------------------------------------- salesOrderId

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

    // --------------------------------------------------------------------------------------------------- salesReasonId

    /**
     * Returns current value of the {@code salesReasonId} attribute.
     *
     * @return current value of the {@code salesReasonId} attribute.
     */
    public Integer getSalesReasonId() {
        return salesReasonId;
    }

    /**
     * Replaces current value of the {@code salesReasonId} attribute with the specified value.
     *
     * @param salesReasonId new value for the {@code salesReasonId} attribute.
     */
    public void setSalesReasonId(final Integer salesReasonId) {
        this.salesReasonId = salesReasonId;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Integer salesOrderId;

    private Integer salesReasonId;
}

