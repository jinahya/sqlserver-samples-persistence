package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockGroup;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItemStockGroup;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Warehouse.StockItemStockGroups} table.
 */
@Entity(name = "WarehouseStockItemStockGroup")
@Table(schema = MappedStockItemStockGroup.TABLE_SCHEMA,
       name = MappedStockItemStockGroup.TABLE_NAME,
       comment = "Which stock items are in which stock groups",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "UQ_StockItemStockGroups_StockGroupID_Lookup",
                       columnNames = {
                               StockItemStockGroup.COLUMN_NAME_STOCK_GROUP_ID,
                               StockItemStockGroup.COLUMN_NAME_STOCK_ITEM_ID
                       }
               ),
               @UniqueConstraint(
                       name = "UQ_StockItemStockGroups_StockItemID_Lookup",
                       columnNames = {
                               StockItemStockGroup.COLUMN_NAME_STOCK_ITEM_ID,
                               StockItemStockGroup.COLUMN_NAME_STOCK_GROUP_ID
                       }
               )
       }
)
public class StockItemStockGroup extends MappedStockItemStockGroup {

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemStockGroups_StockItemID_Warehouse_StockItems}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM = "stockItem";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemStockGroups_StockGroupID_Warehouse_StockGroups}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP = "stockGroup";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItemStockGroups_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StockItemStockGroup() {
        super();
    }

    // ----------------------------------------------------------------------------------- stockItem

    /**
     * Returns the {@code StockItem} which the
     * {@code FK_Warehouse_StockItemStockGroups_StockItemID_Warehouse_StockItems} foreign key references.
     *
     * @return the {@code StockItem} referenced; {@code null} when none is.
     */
    public StockItem getStockItem() {
        return stockItem;
    }

    /**
     * Replaces the {@code StockItem} which the
     * {@code FK_Warehouse_StockItemStockGroups_StockItemID_Warehouse_StockItems} foreign key references, and the value
     * of the column it consists of.
     *
     * @param stockItem the {@code StockItem} to reference; {@code null} for none.
     */
    public void setStockItem(final StockItem stockItem) {
        this.stockItem = stockItem;
        setStockItemId(stockItem == null ? null : stockItem.getStockItemId());
    }

    // ---------------------------------------------------------------------------------- stockGroup

    /**
     * Returns the {@code StockGroup} which the
     * {@code FK_Warehouse_StockItemStockGroups_StockGroupID_Warehouse_StockGroups} foreign key references.
     *
     * @return the {@code StockGroup} referenced; {@code null} when none is.
     */
    public StockGroup getStockGroup() {
        return stockGroup;
    }

    /**
     * Replaces the {@code StockGroup} which the
     * {@code FK_Warehouse_StockItemStockGroups_StockGroupID_Warehouse_StockGroups} foreign key references, and the
     * value of the column it consists of.
     *
     * @param stockGroup the {@code StockGroup} to reference; {@code null} for none.
     */
    public void setStockGroup(final StockGroup stockGroup) {
        this.stockGroup = stockGroup;
        setStockGroupId(stockGroup == null ? null : stockGroup.getStockGroupId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Warehouse_StockItemStockGroups_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Warehouse_StockItemStockGroups_Application_People} foreign key
     * references, and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STOCK_ITEM_ID,
                referencedColumnName = MappedStockItem.COLUMN_NAME_STOCK_ITEM_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private StockItem stockItem;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STOCK_GROUP_ID,
                referencedColumnName = MappedStockGroup.COLUMN_NAME_STOCK_GROUP_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private StockGroup stockGroup;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LAST_EDITED_BY,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person lastEditedByPerson;
}

