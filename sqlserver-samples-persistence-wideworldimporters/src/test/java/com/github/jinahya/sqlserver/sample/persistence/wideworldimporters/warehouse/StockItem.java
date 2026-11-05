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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedSupplier;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedColor;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedPackageType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing.Supplier;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Warehouse.StockItems} table.
 */
@Entity(name = "WarehouseStockItem")
@Table(schema = MappedStockItem.TABLE_SCHEMA,
       name = MappedStockItem.TABLE_NAME,
       comment = "Main entity table for stock items",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "UQ_Warehouse_StockItems_StockItemName",
                       columnNames = {
                               StockItem.COLUMN_NAME_STOCK_ITEM_NAME
                       }
               )
       }
)
public class StockItem extends MappedStockItem {

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItems_SupplierID_Purchasing_Suppliers} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER = "supplier";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItems_ColorID_Warehouse_Colors} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLOR = "color";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItems_UnitPackageID_Warehouse_PackageTypes} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PACKAGE = "unitPackage";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItems_OuterPackageID_Warehouse_PackageTypes} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OUTER_PACKAGE = "outerPackage";

    /**
     * The name of the attribute of the {@code FK_Warehouse_StockItems_Application_People} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StockItem() {
        super();
    }

    // ------------------------------------------------------------------------------------ supplier

    /**
     * Returns the {@code Supplier} which the {@code FK_Warehouse_StockItems_SupplierID_Purchasing_Suppliers} foreign
     * key references.
     *
     * @return the {@code Supplier} referenced; {@code null} when none is.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Replaces the {@code Supplier} which the {@code FK_Warehouse_StockItems_SupplierID_Purchasing_Suppliers} foreign
     * key references, and the value of the column it consists of.
     *
     * @param supplier the {@code Supplier} to reference; {@code null} for none.
     */
    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
        setSupplierId(supplier == null ? null : supplier.getSupplierId());
    }

    // --------------------------------------------------------------------------------------- color

    /**
     * Returns the {@code Color} which the {@code FK_Warehouse_StockItems_ColorID_Warehouse_Colors} foreign key
     * references.
     *
     * @return the {@code Color} referenced; {@code null} when none is.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Replaces the {@code Color} which the {@code FK_Warehouse_StockItems_ColorID_Warehouse_Colors} foreign key
     * references, and the value of the column it consists of.
     *
     * @param color the {@code Color} to reference; {@code null} for none.
     */
    public void setColor(final Color color) {
        this.color = color;
        setColorId(color == null ? null : color.getColorId());
    }

    // --------------------------------------------------------------------------------- unitPackage

    /**
     * Returns the {@code PackageType} which the {@code FK_Warehouse_StockItems_UnitPackageID_Warehouse_PackageTypes}
     * foreign key references.
     *
     * @return the {@code PackageType} referenced; {@code null} when none is.
     */
    public PackageType getUnitPackage() {
        return unitPackage;
    }

    /**
     * Replaces the {@code PackageType} which the {@code FK_Warehouse_StockItems_UnitPackageID_Warehouse_PackageTypes}
     * foreign key references, and the value of the column it consists of.
     *
     * @param unitPackage the {@code PackageType} to reference; {@code null} for none.
     */
    public void setUnitPackage(final PackageType unitPackage) {
        this.unitPackage = unitPackage;
        setUnitPackageId(unitPackage == null ? null : unitPackage.getPackageTypeId());
    }

    // -------------------------------------------------------------------------------- outerPackage

    /**
     * Returns the {@code PackageType} which the {@code FK_Warehouse_StockItems_OuterPackageID_Warehouse_PackageTypes}
     * foreign key references.
     *
     * @return the {@code PackageType} referenced; {@code null} when none is.
     */
    public PackageType getOuterPackage() {
        return outerPackage;
    }

    /**
     * Replaces the {@code PackageType} which the {@code FK_Warehouse_StockItems_OuterPackageID_Warehouse_PackageTypes}
     * foreign key references, and the value of the column it consists of.
     *
     * @param outerPackage the {@code PackageType} to reference; {@code null} for none.
     */
    public void setOuterPackage(final PackageType outerPackage) {
        this.outerPackage = outerPackage;
        setOuterPackageId(outerPackage == null ? null : outerPackage.getPackageTypeId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Warehouse_StockItems_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Warehouse_StockItems_Application_People} foreign key references,
     * and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SUPPLIER_ID,
                referencedColumnName = MappedSupplier.COLUMN_NAME_SUPPLIER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Supplier supplier;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COLOR_ID,
                referencedColumnName = MappedColor.COLUMN_NAME_COLOR_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Color color;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_UNIT_PACKAGE_ID,
                referencedColumnName = MappedPackageType.COLUMN_NAME_PACKAGE_TYPE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private PackageType unitPackage;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_OUTER_PACKAGE_ID,
                referencedColumnName = MappedPackageType.COLUMN_NAME_PACKAGE_TYPE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private PackageType outerPackage;

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

