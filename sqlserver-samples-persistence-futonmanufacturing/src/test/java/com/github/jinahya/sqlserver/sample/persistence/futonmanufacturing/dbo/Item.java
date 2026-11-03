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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedItemType;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedUnitOfMeasure;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.Items} table.
 */
@Entity(name = "DboItem")
@Table(schema = MappedItem.TABLE_SCHEMA,
       name = MappedItem.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               Item.COLUMN_NAME_ITEM_CODE
                       }
               )
       }
)
public class Item extends MappedItem {

    /**
     * The name of the attribute of the {@code FK_Items_ItemType} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE = "itemType";

    /**
     * The name of the attribute of the {@code FK_Items_UnitOfMeasure} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT = "unit";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Item() {
        super();
    }

    // ------------------------------------------------------------------------------------ itemType

    /**
     * Returns the {@code ItemType} which the {@code FK_Items_ItemType} foreign key references.
     *
     * @return the {@code ItemType} referenced; {@code null} when none is.
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * Replaces the {@code ItemType} which the {@code FK_Items_ItemType} foreign key references, and the value of the
     * column it consists of.
     *
     * @param itemType the {@code ItemType} to reference; {@code null} for none.
     */
    public void setItemType(final ItemType itemType) {
        this.itemType = itemType;
        setItemTypeId(itemType == null ? null : itemType.getItemTypeId());
    }

    // ---------------------------------------------------------------------------------------- unit

    /**
     * Returns the {@code UnitOfMeasure} which the {@code FK_Items_UnitOfMeasure} foreign key references.
     *
     * @return the {@code UnitOfMeasure} referenced; {@code null} when none is.
     */
    public UnitOfMeasure getUnit() {
        return unit;
    }

    /**
     * Replaces the {@code UnitOfMeasure} which the {@code FK_Items_UnitOfMeasure} foreign key references, and the value
     * of the column it consists of.
     *
     * @param unit the {@code UnitOfMeasure} to reference; {@code null} for none.
     */
    public void setUnit(final UnitOfMeasure unit) {
        this.unit = unit;
        setUnitId(unit == null ? null : unit.getUnitId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ITEM_TYPE_ID,
                referencedColumnName = MappedItemType.COLUMN_NAME_ITEM_TYPE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private ItemType itemType;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_UNIT_ID,
                referencedColumnName = MappedUnitOfMeasure.COLUMN_NAME_UNIT_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private UnitOfMeasure unit;
}

