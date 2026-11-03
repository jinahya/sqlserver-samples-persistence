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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Sales.SalesOrderHeaderSalesReason} table, whose rows are
 * identified by {@code SalesOrderID}, {@code SalesReasonID}.
 * <p>
 * {@link MappedSalesOrderHeaderSalesReason} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class SalesOrderHeaderSalesReasonId {

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SALES_ORDER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SALES_ORDER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesOrderHeaderSalesReason#COLUMN_NAME_SALES_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SALES_REASON_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SALES_REASON_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SALES_REASON_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SALES_REASON_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the
     * {@value MappedSalesOrderHeaderSalesReason#COLUMN_NAME_SALES_REASON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REASON_ID = "salesReasonId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public SalesOrderHeaderSalesReasonId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesOrderId=" + salesOrderId
               + ",salesReasonId=" + salesReasonId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesOrderHeaderSalesReasonId that)) {
            return false;
        }
        return Objects.equals(getSalesOrderId(), that.getSalesOrderId()) &&
               Objects.equals(getSalesReasonId(), that.getSalesReasonId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getSalesOrderId(), getSalesReasonId());
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

    // ------------------------------------------------------------------------------- salesReasonId

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

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code salesOrderId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(ATTRIBUTE_MAX_SALES_REASON_ID)
    @Min(ATTRIBUTE_MIN_SALES_REASON_ID)
    @Max(ATTRIBUTE_MAX_SALES_ORDER_ID)
    @Min(ATTRIBUTE_MIN_SALES_ORDER_ID)
    @NotNull
    @Column(name = MappedSalesOrderHeaderSalesReason.COLUMN_NAME_SALES_ORDER_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedSalesOrderHeaderSalesReason.COLUMN_NAME_SALES_ORDER_ID,
                          value = "Primary key. Foreign key to SalesOrderHeader.SalesOrderID.")
    private Integer salesOrderId;

    /**
     * The value of the {@code salesReasonId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedSalesOrderHeaderSalesReason.COLUMN_NAME_SALES_REASON_ID, nullable = false,
            insertable = true, updatable = false)
    @MsDescription.Column(name = MappedSalesOrderHeaderSalesReason.COLUMN_NAME_SALES_REASON_ID,
                          value = "Primary key. Foreign key to SalesReason.SalesReasonID.")
    private Integer salesReasonId;
}

