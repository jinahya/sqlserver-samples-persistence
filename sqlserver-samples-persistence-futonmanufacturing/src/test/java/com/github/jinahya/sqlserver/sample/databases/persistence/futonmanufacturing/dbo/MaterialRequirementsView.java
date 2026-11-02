package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.dbo;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedMaterialRequirementsView;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A plain class which holds a row of the {@code dbo.vw_MaterialRequirements} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class MaterialRequirementsView extends MappedMaterialRequirementsView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected MaterialRequirementsView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param workOrderNumber       the value of the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column.
     * @param status                the value of the {@value #COLUMN_NAME_STATUS} column.
     * @param plannedCompletionDate the value of the {@value #COLUMN_NAME_PLANNED_COMPLETION_DATE} column.
     * @param itemCode              the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName              the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param itemType              the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param requiredQuantity      the value of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column.
     * @param availableQuantity     the value of the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column.
     * @param shortageQuantity      the value of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column.
     * @param availabilityStatus    the value of the {@value #COLUMN_NAME_AVAILABILITY_STATUS} column.
     * @param unitCode              the value of the {@value #COLUMN_NAME_UNIT_CODE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public MaterialRequirementsView(final String workOrderNumber,
                                    final String status,
                                    final LocalDate plannedCompletionDate,
                                    final String itemCode,
                                    final String itemName,
                                    final String itemType,
                                    final BigDecimal requiredQuantity,
                                    final BigDecimal availableQuantity,
                                    final BigDecimal shortageQuantity,
                                    final String availabilityStatus,
                                    final String unitCode) {
        super();
        setWorkOrderNumber(workOrderNumber);
        setStatus(status);
        setPlannedCompletionDate(plannedCompletionDate);
        setItemCode(itemCode);
        setItemName(itemName);
        setItemType(itemType);
        setRequiredQuantity(requiredQuantity);
        setAvailableQuantity(availableQuantity);
        setShortageQuantity(shortageQuantity);
        setAvailabilityStatus(availabilityStatus);
        setUnitCode(unitCode);
    }
}

