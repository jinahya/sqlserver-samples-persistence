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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedPurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSupplier;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedWarehouse;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.PurchaseOrder} table.
 */
@Entity(name = "DboPurchaseOrder")
@Table(schema = MappedPurchaseOrder.TABLE_SCHEMA,
       name = MappedPurchaseOrder.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               PurchaseOrder.COLUMN_NAME_PO_NUMBER
                       }
               )
       }
)
public class PurchaseOrder extends MappedPurchaseOrder {

    /**
     * The name of the attribute of the {@code FK_PO_Supplier} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER = "supplier";

    /**
     * The name of the attribute of the {@code FK_PO_Warehouse} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE = "warehouse";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PurchaseOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------ supplier

    /**
     * Returns the {@code Supplier} which the {@code FK_PO_Supplier} foreign key references.
     *
     * @return the {@code Supplier} referenced; {@code null} when none is.
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Replaces the {@code Supplier} which the {@code FK_PO_Supplier} foreign key references, and the value of the
     * column it consists of.
     *
     * @param supplier the {@code Supplier} to reference; {@code null} for none.
     */
    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
        setSupplierId(supplier == null ? null : supplier.getSupplierId());
    }

    // ----------------------------------------------------------------------------------- warehouse

    /**
     * Returns the {@code Warehouse} which the {@code FK_PO_Warehouse} foreign key references.
     *
     * @return the {@code Warehouse} referenced; {@code null} when none is.
     */
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Replaces the {@code Warehouse} which the {@code FK_PO_Warehouse} foreign key references, and the value of the
     * column it consists of.
     *
     * @param warehouse the {@code Warehouse} to reference; {@code null} for none.
     */
    public void setWarehouse(final Warehouse warehouse) {
        this.warehouse = warehouse;
        setWarehouseId(warehouse == null ? null : warehouse.getWarehouseId());
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_WAREHOUSE_ID,
                referencedColumnName = MappedWarehouse.COLUMN_NAME_WAREHOUSE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Warehouse warehouse;
}

