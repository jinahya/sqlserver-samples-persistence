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
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesChannel;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesQuote;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesRep;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.SalesQuote} table.
 */
@Entity(name = "DboSalesQuote")
@Table(schema = MappedSalesQuote.TABLE_SCHEMA,
       name = MappedSalesQuote.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               SalesQuote.COLUMN_NAME_QUOTE_NUMBER
                       }
               )
       }
)
public class SalesQuote extends MappedSalesQuote {

    /**
     * The name of the attribute of the {@code FK_Quote_Customer} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_Quote_SalesChannel} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL = "salesChannel";

    /**
     * The name of the attribute of the {@code FK_Quote_SalesRep} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP = "salesRep";

    /**
     * The name of the attribute of the {@code FK_Quote_ConvertedOrder} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONVERTED_TO_ORDER = "convertedToOrder";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesQuote() {
        super();
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Quote_Customer} foreign key references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Quote_Customer} foreign key references, and the value of the
     * column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // -------------------------------------------------------------------------------- salesChannel

    /**
     * Returns the {@code SalesChannel} which the {@code FK_Quote_SalesChannel} foreign key references.
     *
     * @return the {@code SalesChannel} referenced; {@code null} when none is.
     */
    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    /**
     * Replaces the {@code SalesChannel} which the {@code FK_Quote_SalesChannel} foreign key references, and the value
     * of the column it consists of.
     *
     * @param salesChannel the {@code SalesChannel} to reference; {@code null} for none.
     */
    public void setSalesChannel(final SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
        setSalesChannelId(salesChannel == null ? null : salesChannel.getSalesChannelId());
    }

    // ------------------------------------------------------------------------------------ salesRep

    /**
     * Returns the {@code SalesRep} which the {@code FK_Quote_SalesRep} foreign key references.
     *
     * @return the {@code SalesRep} referenced; {@code null} when none is.
     */
    public SalesRep getSalesRep() {
        return salesRep;
    }

    /**
     * Replaces the {@code SalesRep} which the {@code FK_Quote_SalesRep} foreign key references, and the value of the
     * column it consists of.
     *
     * @param salesRep the {@code SalesRep} to reference; {@code null} for none.
     */
    public void setSalesRep(final SalesRep salesRep) {
        this.salesRep = salesRep;
        setSalesRepId(salesRep == null ? null : salesRep.getSalesRepId());
    }

    // ---------------------------------------------------------------------------- convertedToOrder

    /**
     * Returns the {@code SalesOrder} which the {@code FK_Quote_ConvertedOrder} foreign key references.
     *
     * @return the {@code SalesOrder} referenced; {@code null} when none is.
     */
    public SalesOrder getConvertedToOrder() {
        return convertedToOrder;
    }

    /**
     * Replaces the {@code SalesOrder} which the {@code FK_Quote_ConvertedOrder} foreign key references, and the value
     * of the column it consists of.
     *
     * @param convertedToOrder the {@code SalesOrder} to reference; {@code null} for none.
     */
    public void setConvertedToOrder(final SalesOrder convertedToOrder) {
        this.convertedToOrder = convertedToOrder;
        setConvertedToOrderId(convertedToOrder == null ? null : convertedToOrder.getSalesOrderId());
    }

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
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_CHANNEL_ID,
                referencedColumnName = MappedSalesChannel.COLUMN_NAME_SALES_CHANNEL_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesChannel salesChannel;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_REP_ID,
                referencedColumnName = MappedSalesRep.COLUMN_NAME_SALES_REP_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesRep salesRep;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CONVERTED_TO_ORDER_ID,
                referencedColumnName = MappedSalesOrder.COLUMN_NAME_SALES_ORDER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesOrder convertedToOrder;
}

