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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedInventoryTransaction;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedTransactionType;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedWarehouse;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.InventoryTransaction} table.
 */
@Entity(name = "DboInventoryTransaction")
@Table(schema = MappedInventoryTransaction.TABLE_SCHEMA, name = MappedInventoryTransaction.TABLE_NAME)
public class InventoryTransaction extends MappedInventoryTransaction {

    /**
     * The name of the attribute of the {@code FK_InvTrans_Item} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM = "item";

    /**
     * The name of the attribute of the {@code FK_InvTrans_Warehouse} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE = "warehouse";

    /**
     * The name of the attribute of the {@code FK_InvTrans_Type} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected InventoryTransaction() {
        super();
    }

    // ---------------------------------------------------------------------------------------- item

    /**
     * Returns the {@code Item} which the {@code FK_InvTrans_Item} foreign key references.
     *
     * @return the {@code Item} referenced; {@code null} when none is.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Replaces the {@code Item} which the {@code FK_InvTrans_Item} foreign key references, and the value of the column
     * it consists of.
     *
     * @param item the {@code Item} to reference; {@code null} for none.
     */
    public void setItem(final Item item) {
        this.item = item;
        setItemId(item == null ? null : item.getItemId());
    }

    // ----------------------------------------------------------------------------------- warehouse

    /**
     * Returns the {@code Warehouse} which the {@code FK_InvTrans_Warehouse} foreign key references.
     *
     * @return the {@code Warehouse} referenced; {@code null} when none is.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Replaces the {@code Warehouse} which the {@code FK_InvTrans_Warehouse} foreign key references, and the value of
     * the column it consists of.
     *
     * @param warehouse the {@code Warehouse} to reference; {@code null} for none.
     */
    public void setWarehouse(final Warehouse warehouse) {
        this.warehouse = warehouse;
        setWarehouseId(warehouse == null ? null : warehouse.getWarehouseId());
    }

    // ----------------------------------------------------------------------------- transactionType

    /**
     * Returns the {@code TransactionType} which the {@code FK_InvTrans_Type} foreign key references.
     *
     * @return the {@code TransactionType} referenced; {@code null} when none is.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces the {@code TransactionType} which the {@code FK_InvTrans_Type} foreign key references, and the value of
     * the column it consists of.
     *
     * @param transactionType the {@code TransactionType} to reference; {@code null} for none.
     */
    public void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
        setTransactionTypeId(transactionType == null ? null : transactionType.getTransactionTypeId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ITEM_ID,
                referencedColumnName = MappedItem.COLUMN_NAME_ITEM_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Item item;

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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TRANSACTION_TYPE_ID,
                referencedColumnName = MappedTransactionType.COLUMN_NAME_TRANSACTION_TYPE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private TransactionType transactionType;
}

