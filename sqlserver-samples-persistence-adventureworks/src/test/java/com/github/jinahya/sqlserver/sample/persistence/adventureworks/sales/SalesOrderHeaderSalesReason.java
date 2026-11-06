package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesOrderHeaderSalesReason;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesReason;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.SalesOrderHeaderSalesReasonId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity which maps the {@code Sales.SalesOrderHeaderSalesReason} table.
 * <p>
 * Each attribute of the embedded {@link SalesOrderHeaderSalesReasonId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "SalesSalesOrderHeaderSalesReason")
@Table(schema = MappedSalesOrderHeaderSalesReason.TABLE_SCHEMA,
       name = MappedSalesOrderHeaderSalesReason.TABLE_NAME,
       comment = "Cross-reference table mapping sales orders to sales reason codes.")
public class SalesOrderHeaderSalesReason
        extends MappedSalesOrderHeaderSalesReason {

    /**
     * The name of the attribute of the
     * {@code FK_SalesOrderHeaderSalesReason_SalesReason_SalesReasonID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REASON = "salesReason";

    /**
     * The name of the attribute of the
     * {@code FK_SalesOrderHeaderSalesReason_SalesOrderHeader_SalesOrderID} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER = "salesOrder";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code salesOrder} and {@code salesReason}.
     *
     * @param salesOrder  the {@code SalesOrderHeader} to reference.
     * @param salesReason the {@code SalesReason} to reference.
     * @return a new instance which references {@code salesOrder} and {@code salesReason}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static SalesOrderHeaderSalesReason of(final SalesOrderHeader salesOrder,
                                                 final SalesReason salesReason) {
        Objects.requireNonNull(salesOrder, "salesOrder is null");
        Objects.requireNonNull(salesReason, "salesReason is null");
        final var instance = new SalesOrderHeaderSalesReason();
        instance.salesOrder = salesOrder;
        instance.salesReason = salesReason;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesOrderHeaderSalesReason() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected SalesOrderHeaderSalesReasonId getId_() {
        return id_;
    }

    // --------------------------------------------------------------------------------- salesReason

    /**
     * Returns the {@code SalesReason} which the
     * {@code FK_SalesOrderHeaderSalesReason_SalesReason_SalesReasonID} foreign key references.
     *
     * @return the {@code SalesReason} referenced; {@code null} when none is.
     */
    public SalesReason getSalesReason() {
        return salesReason;
    }

    // ---------------------------------------------------------------------------------- salesOrder

    /**
     * Returns the {@code SalesOrderHeader} which the
     * {@code FK_SalesOrderHeaderSalesReason_SalesOrderHeader_SalesOrderID} foreign key references.
     *
     * @return the {@code SalesOrderHeader} referenced; {@code null} when none is.
     */
    public SalesOrderHeader getSalesOrder() {
        return salesOrder;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private SalesOrderHeaderSalesReasonId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesOrderHeaderSalesReasonId.ATTRIBUTE_NAME_SALES_REASON_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_REASON_ID,
                referencedColumnName = MappedSalesReason.COLUMN_NAME_SALES_REASON_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private SalesReason salesReason;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(SalesOrderHeaderSalesReasonId.ATTRIBUTE_NAME_SALES_ORDER_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_ORDER_ID,
                referencedColumnName = MappedSalesOrderHeader.COLUMN_NAME_SALES_ORDER_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private SalesOrderHeader salesOrder;
}

