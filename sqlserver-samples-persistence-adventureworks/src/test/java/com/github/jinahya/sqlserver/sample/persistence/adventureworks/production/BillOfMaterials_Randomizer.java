package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;


/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.__Random_TestUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link BillOfMaterials}; the generated identifier, what SQL Server fills, and what it references,
 * which the persister sets, are left out.
 */
class BillOfMaterials_Randomizer
        extends PodamObjectRandomizer<BillOfMaterials> {

    BillOfMaterials_Randomizer() {
        super(BillOfMaterials.class, List.of(
                BillOfMaterials.ATTRIBUTE_NAME_BILL_OF_MATERIALS_ID,
                BillOfMaterials.ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID,
                BillOfMaterials.ATTRIBUTE_NAME_COMPONENT_ID,
                BillOfMaterials.ATTRIBUTE_NAME_UNIT_MEASURE_CODE,
                BillOfMaterials.ATTRIBUTE_NAME_PRODUCT_ASSEMBLY,
                BillOfMaterials.ATTRIBUTE_NAME_COMPONENT,
                BillOfMaterials.ATTRIBUTE_NAME_UNIT_MEASURE,
                BillOfMaterials.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance is of a component in an assembly, within the CHECK constraints, and has its dates
     *         in order.
     */
    @Override
    public BillOfMaterials get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // CK_BillOfMaterials_BOMLevel: 1 or more, as the persister sets an assembly
        instance.setBomLevel(random.nextInt(1, 5));
        // CK_BillOfMaterials_PerAssemblyQty: >= 1.00, a decimal(8, 2)
        instance.setPerAssemblyQty(BigDecimal.ONE.add(__Random_TestUtils.positive(5, 2)));
        // CK_BillOfMaterials_EndDate: EndDate, when any, > StartDate
        instance.setStartDate(__Random_TestUtils.dateTime());
        instance.setEndDate(random.nextBoolean()
                            ? null
                            : instance.getStartDate().plusDays(random.nextLong(1L, 366L)));
        return instance;
    }
}
