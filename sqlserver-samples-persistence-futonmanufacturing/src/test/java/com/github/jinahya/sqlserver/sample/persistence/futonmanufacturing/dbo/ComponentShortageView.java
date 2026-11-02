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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedComponentShortageView;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code dbo.vw_ComponentShortage} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ComponentShortageView extends MappedComponentShortageView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ComponentShortageView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param itemCode          the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName          the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param itemType          the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param requiredQuantity  the value of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column.
     * @param availableQuantity the value of the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column.
     * @param shortageQuantity  the value of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column.
     * @param earliestNeedDate  the value of the {@value #COLUMN_NAME_EARLIEST_NEED_DATE} column.
     * @param daysUntilNeeded   the value of the {@value #COLUMN_NAME_DAYS_UNTIL_NEEDED} column.
     * @param affectedOrders    the value of the {@value #COLUMN_NAME_AFFECTED_ORDERS} column.
     * @param urgencyLevel      the value of the {@value #COLUMN_NAME_URGENCY_LEVEL} column.
     * @param preferredSupplier the value of the {@value #COLUMN_NAME_PREFERRED_SUPPLIER} column.
     * @param leadTimeDays      the value of the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column.
     * @param possibleArrival   the value of the {@value #COLUMN_NAME_POSSIBLE_ARRIVAL} column.
     * @param supplyStatus      the value of the {@value #COLUMN_NAME_SUPPLY_STATUS} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ComponentShortageView(final String itemCode,
                                 final String itemName,
                                 final String itemType,
                                 final BigDecimal requiredQuantity,
                                 final BigDecimal availableQuantity,
                                 final BigDecimal shortageQuantity,
                                 final LocalDate earliestNeedDate,
                                 final Integer daysUntilNeeded,
                                 final Integer affectedOrders,
                                 final String urgencyLevel,
                                 final String preferredSupplier,
                                 final Integer leadTimeDays,
                                 final LocalDateTime possibleArrival,
                                 final String supplyStatus) {
        super();
        setItemCode(itemCode);
        setItemName(itemName);
        setItemType(itemType);
        setRequiredQuantity(requiredQuantity);
        setAvailableQuantity(availableQuantity);
        setShortageQuantity(shortageQuantity);
        setEarliestNeedDate(earliestNeedDate);
        setDaysUntilNeeded(daysUntilNeeded);
        setAffectedOrders(affectedOrders);
        setUrgencyLevel(urgencyLevel);
        setPreferredSupplier(preferredSupplier);
        setLeadTimeDays(leadTimeDays);
        setPossibleArrival(possibleArrival);
        setSupplyStatus(supplyStatus);
    }
}

