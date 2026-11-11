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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedItemBelowReorderPoint;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code dbo.vw_ItemsBelowReorderPoint} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ItemBelowReorderPoint extends MappedItemBelowReorderPoint {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ItemBelowReorderPoint() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param warehouseCode     the value of the {@value #COLUMN_NAME_WAREHOUSE_CODE} column.
     * @param warehouseName     the value of the {@value #COLUMN_NAME_WAREHOUSE_NAME} column.
     * @param itemType          the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param itemCode          the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName          the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param quantityAvailable the value of the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column.
     * @param reorderPoint      the value of the {@value #COLUMN_NAME_REORDER_POINT} column.
     * @param safetyStock       the value of the {@value #COLUMN_NAME_SAFETY_STOCK} column.
     * @param shortageQuantity  the value of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column.
     * @param unitCode          the value of the {@value #COLUMN_NAME_UNIT_CODE} column.
     * @param leadTimeDays      the value of the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column.
     * @param preferredSupplier the value of the {@value #COLUMN_NAME_PREFERRED_SUPPLIER} column.
     * @param preferredPrice    the value of the {@value #COLUMN_NAME_PREFERRED_PRICE} column.
     * @param expectedArrival   the value of the {@value #COLUMN_NAME_EXPECTED_ARRIVAL} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ItemBelowReorderPoint(final String warehouseCode,
                                      final String warehouseName,
                                      final String itemType,
                                      final String itemCode,
                                      final String itemName,
                                      final BigDecimal quantityAvailable,
                                      final BigDecimal reorderPoint,
                                      final BigDecimal safetyStock,
                                      final BigDecimal shortageQuantity,
                                      final String unitCode,
                                      final Integer leadTimeDays,
                                      final String preferredSupplier,
                                      final BigDecimal preferredPrice,
                                      final LocalDateTime expectedArrival) {
        super();
        setWarehouseCode(warehouseCode);
        setWarehouseName(warehouseName);
        setItemType(itemType);
        setItemCode(itemCode);
        setItemName(itemName);
        setQuantityAvailable(quantityAvailable);
        setReorderPoint(reorderPoint);
        setSafetyStock(safetyStock);
        setShortageQuantity(shortageQuantity);
        setUnitCode(unitCode);
        setLeadTimeDays(leadTimeDays);
        setPreferredSupplier(preferredSupplier);
        setPreferredPrice(preferredPrice);
        setExpectedArrival(expectedArrival);
    }
}

