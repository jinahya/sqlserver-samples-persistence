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
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesRep;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedStore;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedWarehouse;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.SalesOrder} table.
 */
@Entity(name = "DboSalesOrder")
@Table(schema = MappedSalesOrder.TABLE_SCHEMA,
       name = MappedSalesOrder.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               SalesOrder.COLUMN_NAME_ORDER_NUMBER
                       }
               )
       }
)
public class SalesOrder extends MappedSalesOrder {

    /**
     * The name of the attribute of the {@code FK_SO_Customer} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_SO_Warehouse} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE = "warehouse";

    /**
     * The name of the attribute of the {@code FK_SalesOrder_SalesChannel} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL = "salesChannel";

    /**
     * The name of the attribute of the {@code FK_SalesOrder_Store} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    /**
     * The name of the attribute of the {@code FK_SalesOrder_SalesRep} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP = "salesRep";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_SO_Customer} foreign key references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_SO_Customer} foreign key references, and the value of the
     * column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // ----------------------------------------------------------------------------------- warehouse

    /**
     * Returns the {@code Warehouse} which the {@code FK_SO_Warehouse} foreign key references.
     *
     * @return the {@code Warehouse} referenced; {@code null} when none is.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Replaces the {@code Warehouse} which the {@code FK_SO_Warehouse} foreign key references, and the value of the
     * column it consists of.
     *
     * @param warehouse the {@code Warehouse} to reference; {@code null} for none.
     */
    public void setWarehouse(final Warehouse warehouse) {
        this.warehouse = warehouse;
        setWarehouseId(warehouse == null ? null : warehouse.getWarehouseId());
    }

    // -------------------------------------------------------------------------------- salesChannel

    /**
     * Returns the {@code SalesChannel} which the {@code FK_SalesOrder_SalesChannel} foreign key references.
     *
     * @return the {@code SalesChannel} referenced; {@code null} when none is.
     */
    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    /**
     * Replaces the {@code SalesChannel} which the {@code FK_SalesOrder_SalesChannel} foreign key references, and the
     * value of the column it consists of.
     *
     * @param salesChannel the {@code SalesChannel} to reference; {@code null} for none.
     */
    public void setSalesChannel(final SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
        setSalesChannelId(salesChannel == null ? null : salesChannel.getSalesChannelId());
    }

    // --------------------------------------------------------------------------------------- store

    /**
     * Returns the {@code Store} which the {@code FK_SalesOrder_Store} foreign key references.
     *
     * @return the {@code Store} referenced; {@code null} when none is.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Replaces the {@code Store} which the {@code FK_SalesOrder_Store} foreign key references, and the value of the
     * column it consists of.
     *
     * @param store the {@code Store} to reference; {@code null} for none.
     */
    public void setStore(final Store store) {
        this.store = store;
        setStoreId(store == null ? null : store.getStoreId());
    }

    // ------------------------------------------------------------------------------------ salesRep

    /**
     * Returns the {@code SalesRep} which the {@code FK_SalesOrder_SalesRep} foreign key references.
     *
     * @return the {@code SalesRep} referenced; {@code null} when none is.
     */
    public SalesRep getSalesRep() {
        return salesRep;
    }

    /**
     * Replaces the {@code SalesRep} which the {@code FK_SalesOrder_SalesRep} foreign key references, and the value of
     * the column it consists of.
     *
     * @param salesRep the {@code SalesRep} to reference; {@code null} for none.
     */
    public void setSalesRep(final SalesRep salesRep) {
        this.salesRep = salesRep;
        setSalesRepId(salesRep == null ? null : salesRep.getSalesRepId());
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_WAREHOUSE_ID,
                referencedColumnName = MappedWarehouse.COLUMN_NAME_WAREHOUSE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Warehouse warehouse;

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
    @JoinColumn(name = COLUMN_NAME_STORE_ID,
                referencedColumnName = MappedStore.COLUMN_NAME_STORE_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Store store;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_REP_ID,
                referencedColumnName = MappedSalesRep.COLUMN_NAME_SALES_REP_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesRep salesRep;
}

