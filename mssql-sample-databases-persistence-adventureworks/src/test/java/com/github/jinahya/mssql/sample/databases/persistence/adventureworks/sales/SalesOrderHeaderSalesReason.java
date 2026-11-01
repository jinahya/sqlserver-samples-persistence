package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.sales;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesOrderHeader;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesOrderHeaderSalesReason;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesOrderHeaderSalesReasonId;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesReason;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.SalesOrderHeaderSalesReason} table.
 */
@Entity(name = "SalesSalesOrderHeaderSalesReason")
@Table(schema = MappedSalesOrderHeaderSalesReason.TABLE_SCHEMA, name = MappedSalesOrderHeaderSalesReason.TABLE_NAME)
@IdClass(MappedSalesOrderHeaderSalesReasonId.class)
public class SalesOrderHeaderSalesReason extends MappedSalesOrderHeaderSalesReason {

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeaderSalesReason_SalesReason_SalesReasonID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REASON = "salesReason";

    /**
     * The name of the attribute of the {@code FK_SalesOrderHeaderSalesReason_SalesOrderHeader_SalesOrderID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER = "salesOrder";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesOrderHeaderSalesReason() {
        super();
    }

    // ----------------------------------------------------------------------------------------------------- salesReason

    /**
     * Returns the {@code SalesReason} which the {@code FK_SalesOrderHeaderSalesReason_SalesReason_SalesReasonID}
     * foreign key references.
     *
     * @return the {@code SalesReason} referenced; {@code null} when none is.
     */
    public SalesReason getSalesReason() {
        return salesReason;
    }

    /**
     * Replaces the {@code SalesReason} which the {@code FK_SalesOrderHeaderSalesReason_SalesReason_SalesReasonID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param salesReason the {@code SalesReason} to reference; {@code null} for none.
     */
    public void setSalesReason(final SalesReason salesReason) {
        this.salesReason = salesReason;
        setSalesReasonId(salesReason == null ? null : salesReason.getSalesReasonId());
    }

    // ------------------------------------------------------------------------------------------------------ salesOrder

    /**
     * Returns the {@code SalesOrderHeader} which the
     * {@code FK_SalesOrderHeaderSalesReason_SalesOrderHeader_SalesOrderID} foreign key references.
     *
     * @return the {@code SalesOrderHeader} referenced; {@code null} when none is.
     */
    public SalesOrderHeader getSalesOrder() {
        return salesOrder;
    }

    /**
     * Replaces the {@code SalesOrderHeader} which the
     * {@code FK_SalesOrderHeaderSalesReason_SalesOrderHeader_SalesOrderID} foreign key references, and the value of the
     * column it consists of.
     *
     * @param salesOrder the {@code SalesOrderHeader} to reference; {@code null} for none.
     */
    public void setSalesOrder(final SalesOrderHeader salesOrder) {
        this.salesOrder = salesOrder;
        setSalesOrderId(salesOrder == null ? null : salesOrder.getSalesOrderId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_REASON_ID,
                referencedColumnName = MappedSalesReason.COLUMN_NAME_SALES_REASON_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesReason salesReason;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_ORDER_ID,
                referencedColumnName = MappedSalesOrderHeader.COLUMN_NAME_SALES_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesOrderHeader salesOrder;
}

