package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.purchasing;

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
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedPurchaseOrder;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing.MappedPurchaseOrderLine;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedPackageType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.PackageType;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Purchasing.PurchaseOrderLines} table.
 */
@Entity(name = "PurchasingPurchaseOrderLine")
@Table(schema = MappedPurchaseOrderLine.TABLE_SCHEMA, name = MappedPurchaseOrderLine.TABLE_NAME,
       comment = "Detail lines from supplier purchase orders")
public class PurchaseOrderLine extends MappedPurchaseOrderLine {

    /**
     * The name of the attribute of the
     * {@code FK_Purchasing_PurchaseOrderLines_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER = "purchaseOrder";

    /**
     * The name of the attribute of the {@code FK_Purchasing_PurchaseOrderLines_StockItemID_Warehouse_StockItems}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM = "stockItem";

    /**
     * The name of the attribute of the {@code FK_Purchasing_PurchaseOrderLines_PackageTypeID_Warehouse_PackageTypes}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE = "packageType";

    /**
     * The name of the attribute of the {@code FK_Purchasing_PurchaseOrderLines_Application_People} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PurchaseOrderLine() {
        super();
    }

    // ------------------------------------------------------------------------------- purchaseOrder

    /**
     * Returns the {@code PurchaseOrder} which the
     * {@code FK_Purchasing_PurchaseOrderLines_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key references.
     *
     * @return the {@code PurchaseOrder} referenced; {@code null} when none is.
     */
    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    /**
     * Replaces the {@code PurchaseOrder} which the
     * {@code FK_Purchasing_PurchaseOrderLines_PurchaseOrderID_Purchasing_PurchaseOrders} foreign key references, and
     * the value of the column it consists of.
     *
     * @param purchaseOrder the {@code PurchaseOrder} to reference; {@code null} for none.
     */
    public void setPurchaseOrder(final PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        setPurchaseOrderId(purchaseOrder == null ? null : purchaseOrder.getPurchaseOrderId());
    }

    // ----------------------------------------------------------------------------------- stockItem

    /**
     * Returns the {@code StockItem} which the {@code FK_Purchasing_PurchaseOrderLines_StockItemID_Warehouse_StockItems}
     * foreign key references.
     *
     * @return the {@code StockItem} referenced; {@code null} when none is.
     */
    public StockItem getStockItem() {
        return stockItem;
    }

    /**
     * Replaces the {@code StockItem} which the
     * {@code FK_Purchasing_PurchaseOrderLines_StockItemID_Warehouse_StockItems} foreign key references, and the value
     * of the column it consists of.
     *
     * @param stockItem the {@code StockItem} to reference; {@code null} for none.
     */
    public void setStockItem(final StockItem stockItem) {
        this.stockItem = stockItem;
        setStockItemId(stockItem == null ? null : stockItem.getStockItemId());
    }

    // --------------------------------------------------------------------------------- packageType

    /**
     * Returns the {@code PackageType} which the
     * {@code FK_Purchasing_PurchaseOrderLines_PackageTypeID_Warehouse_PackageTypes} foreign key references.
     *
     * @return the {@code PackageType} referenced; {@code null} when none is.
     */
    public PackageType getPackageType() {
        return packageType;
    }

    /**
     * Replaces the {@code PackageType} which the
     * {@code FK_Purchasing_PurchaseOrderLines_PackageTypeID_Warehouse_PackageTypes} foreign key references, and the
     * value of the column it consists of.
     *
     * @param packageType the {@code PackageType} to reference; {@code null} for none.
     */
    public void setPackageType(final PackageType packageType) {
        this.packageType = packageType;
        setPackageTypeId(packageType == null ? null : packageType.getPackageTypeId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Purchasing_PurchaseOrderLines_Application_People} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Purchasing_PurchaseOrderLines_Application_People} foreign key
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
    @JoinColumn(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                referencedColumnName = MappedPurchaseOrder.COLUMN_NAME_PURCHASE_ORDER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private PurchaseOrder purchaseOrder;

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
    @JoinColumn(name = COLUMN_NAME_PACKAGE_TYPE_ID,
                referencedColumnName = MappedPackageType.COLUMN_NAME_PACKAGE_TYPE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private PackageType packageType;

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

