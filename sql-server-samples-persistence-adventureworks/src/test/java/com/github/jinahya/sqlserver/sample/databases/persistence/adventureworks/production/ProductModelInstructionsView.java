package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.production;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.production.MappedProductModelInstructionsView;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A plain class which holds a row of the {@code Production.vProductModelInstructions} view, which no entity can map:
 * the view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ProductModelInstructionsView extends MappedProductModelInstructionsView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ProductModelInstructionsView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param productModelId the value of the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column.
     * @param name           the value of the {@value #COLUMN_NAME_NAME} column.
     * @param instructions   the value of the {@value #COLUMN_NAME_INSTRUCTIONS} column.
     * @param locationId     the value of the {@value #COLUMN_NAME_LOCATION_ID} column.
     * @param setupHours     the value of the {@value #COLUMN_NAME_SETUP_HOURS} column.
     * @param machineHours   the value of the {@value #COLUMN_NAME_MACHINE_HOURS} column.
     * @param laborHours     the value of the {@value #COLUMN_NAME_LABOR_HOURS} column.
     * @param lotSize        the value of the {@value #COLUMN_NAME_LOT_SIZE} column.
     * @param step           the value of the {@value #COLUMN_NAME_STEP} column.
     * @param rowguid        the value of the {@value #COLUMN_NAME_ROWGUID} column.
     * @param modifiedDate   the value of the {@value #COLUMN_NAME_MODIFIED_DATE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ProductModelInstructionsView(final Integer productModelId,
                                        final String name,
                                        final String instructions,
                                        final Integer locationId,
                                        final BigDecimal setupHours,
                                        final BigDecimal machineHours,
                                        final BigDecimal laborHours,
                                        final Integer lotSize,
                                        final String step,
                                        final UUID rowguid,
                                        final LocalDateTime modifiedDate) {
        super();
        setProductModelId(productModelId);
        setName(name);
        setInstructions(instructions);
        setLocationId(locationId);
        setSetupHours(setupHours);
        setMachineHours(machineHours);
        setLaborHours(laborHours);
        setLotSize(lotSize);
        setStep(step);
        setRowguid(rowguid);
        setModifiedDate(modifiedDate);
    }
}

