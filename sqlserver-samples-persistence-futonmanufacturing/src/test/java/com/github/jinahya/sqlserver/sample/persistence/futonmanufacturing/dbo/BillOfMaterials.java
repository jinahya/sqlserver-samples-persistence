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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedBillOfMaterials;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedUnitOfMeasure;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.BillOfMaterials} table.
 */
@Entity(name = "DboBillOfMaterials")
@Table(schema = MappedBillOfMaterials.TABLE_SCHEMA,
       name = MappedBillOfMaterials.TABLE_NAME,
       check = {
               @CheckConstraint(
                       name = "CHK_BOM_NotSelf",
                       constraint = "([ParentItemID]<>[ComponentItemID])"
               )
       }
)
public class BillOfMaterials extends MappedBillOfMaterials {

    /**
     * The name of the attribute of the {@code FK_BOM_ParentItem} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PARENT_ITEM = "parentItem";

    /**
     * The name of the attribute of the {@code FK_BOM_ComponentItem} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ITEM = "componentItem";

    /**
     * The name of the attribute of the {@code FK_BOM_Unit} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT = "unit";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected BillOfMaterials() {
        super();
    }

    // ---------------------------------------------------------------------------------- parentItem

    /**
     * Returns the {@code Item} which the {@code FK_BOM_ParentItem} foreign key references.
     *
     * @return the {@code Item} referenced; {@code null} when none is.
     */
    public Item getParentItem() {
        return parentItem;
    }

    /**
     * Replaces the {@code Item} which the {@code FK_BOM_ParentItem} foreign key references, and the value of the column
     * it consists of.
     *
     * @param parentItem the {@code Item} to reference; {@code null} for none.
     */
    public void setParentItem(final Item parentItem) {
        this.parentItem = parentItem;
        setParentItemId(parentItem == null ? null : parentItem.getItemId());
    }

    // ------------------------------------------------------------------------------- componentItem

    /**
     * Returns the {@code Item} which the {@code FK_BOM_ComponentItem} foreign key references.
     *
     * @return the {@code Item} referenced; {@code null} when none is.
     */
    public Item getComponentItem() {
        return componentItem;
    }

    /**
     * Replaces the {@code Item} which the {@code FK_BOM_ComponentItem} foreign key references, and the value of the
     * column it consists of.
     *
     * @param componentItem the {@code Item} to reference; {@code null} for none.
     */
    public void setComponentItem(final Item componentItem) {
        this.componentItem = componentItem;
        setComponentItemId(componentItem == null ? null : componentItem.getItemId());
    }

    // ---------------------------------------------------------------------------------------- unit

    /**
     * Returns the {@code UnitOfMeasure} which the {@code FK_BOM_Unit} foreign key references.
     *
     * @return the {@code UnitOfMeasure} referenced; {@code null} when none is.
     */
    public UnitOfMeasure getUnit() {
        return unit;
    }

    /**
     * Replaces the {@code UnitOfMeasure} which the {@code FK_BOM_Unit} foreign key references, and the value of the
     * column it consists of.
     *
     * @param unit the {@code UnitOfMeasure} to reference; {@code null} for none.
     */
    public void setUnit(final UnitOfMeasure unit) {
        this.unit = unit;
        setUnitId(unit == null ? null : unit.getUnitId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PARENT_ITEM_ID,
                referencedColumnName = MappedItem.COLUMN_NAME_ITEM_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Item parentItem;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COMPONENT_ITEM_ID,
                referencedColumnName = MappedItem.COLUMN_NAME_ITEM_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Item componentItem;

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

