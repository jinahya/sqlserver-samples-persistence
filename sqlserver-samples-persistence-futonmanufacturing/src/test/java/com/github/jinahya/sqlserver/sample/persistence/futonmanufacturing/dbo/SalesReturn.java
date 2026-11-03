package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedReturnReason;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesReturn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.SalesReturn} table.
 */
@Entity(name = "DboSalesReturn")
@Table(schema = MappedSalesReturn.TABLE_SCHEMA,
       name = MappedSalesReturn.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               SalesReturn.COLUMN_NAME_RETURN_NUMBER
                       }
               )
       }
)
public class SalesReturn extends MappedSalesReturn {

    /**
     * The name of the attribute of the {@code FK_Return_SalesOrder} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER = "salesOrder";

    /**
     * The name of the attribute of the {@code FK_Return_Customer} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_Return_Reason} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_REASON = "returnReason";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesReturn() {
        super();
    }

    // ---------------------------------------------------------------------------------- salesOrder

    /**
     * Returns the {@code SalesOrder} which the {@code FK_Return_SalesOrder} foreign key references.
     *
     * @return the {@code SalesOrder} referenced; {@code null} when none is.
     */
    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    /**
     * Replaces the {@code SalesOrder} which the {@code FK_Return_SalesOrder} foreign key references, and the value of
     * the column it consists of.
     *
     * @param salesOrder the {@code SalesOrder} to reference; {@code null} for none.
     */
    public void setSalesOrder(final SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
        setSalesOrderId(salesOrder == null ? null : salesOrder.getSalesOrderId());
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Return_Customer} foreign key references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Return_Customer} foreign key references, and the value of the
     * column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // -------------------------------------------------------------------------------- returnReason

    /**
     * Returns the {@code ReturnReason} which the {@code FK_Return_Reason} foreign key references.
     *
     * @return the {@code ReturnReason} referenced; {@code null} when none is.
     */
    public ReturnReason getReturnReason() {
        return returnReason;
    }

    /**
     * Replaces the {@code ReturnReason} which the {@code FK_Return_Reason} foreign key references, and the value of the
     * column it consists of.
     *
     * @param returnReason the {@code ReturnReason} to reference; {@code null} for none.
     */
    public void setReturnReason(final ReturnReason returnReason) {
        this.returnReason = returnReason;
        setReturnReasonId(returnReason == null ? null : returnReason.getReturnReasonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_ORDER_ID,
                referencedColumnName = MappedSalesOrder.COLUMN_NAME_SALES_ORDER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private SalesOrder salesOrder;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID,
                referencedColumnName = MappedCustomer.COLUMN_NAME_CUSTOMER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Customer customer;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_RETURN_REASON_ID,
                referencedColumnName = MappedReturnReason.COLUMN_NAME_RETURN_REASON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private ReturnReason returnReason;
}

