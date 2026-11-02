package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;


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

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Randomizes a new {@link PurchaseOrderHeader}; the generated identifier, what SQL Server fills or derives, and what it
 * references, which the persister sets, are left out.
 */
class PurchaseOrderHeader_Randomizer
        extends PodamObjectRandomizer<PurchaseOrderHeader> {

    PurchaseOrderHeader_Randomizer() {
        super(PurchaseOrderHeader.class, List.of(
                PurchaseOrderHeader.ATTRIBUTE_NAME_PURCHASE_ORDER_ID,
                PurchaseOrderHeader.ATTRIBUTE_NAME_EMPLOYEE_ID,
                PurchaseOrderHeader.ATTRIBUTE_NAME_VENDOR_ID,
                PurchaseOrderHeader.ATTRIBUTE_NAME_SHIP_METHOD_ID,
                PurchaseOrderHeader.ATTRIBUTE_NAME_TOTAL_DUE,
                PurchaseOrderHeader.ATTRIBUTE_NAME_EMPLOYEE,
                PurchaseOrderHeader.ATTRIBUTE_NAME_VENDOR,
                PurchaseOrderHeader.ATTRIBUTE_NAME_SHIP_METHOD,
                PurchaseOrderHeader.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK constraints, its dates in order, and
     *         room in its revision number for the updates its details make.
     */
    @Override
    public PurchaseOrderHeader get() {
        final var instance = super.get();
        final var random = ThreadLocalRandom.current();
        // RevisionNumber, a tinyint, which uPurchaseOrderHeader increments on each update, as each detail inserted makes
        instance.setRevisionNumber(random.nextInt(100));
        // SubTotal, TaxAmt and Freight, all money, >= 0
        instance.setSubTotal(__Random_TestUtils.positive(5, 4));
        instance.setTaxAmt(__Random_TestUtils.positive(5, 4));
        instance.setFreight(__Random_TestUtils.positive(5, 4));
        // CK_PurchaseOrderHeader_ShipDate: ShipDate, when any, >= OrderDate
        instance.setOrderDate(__Random_TestUtils.dateTime());
        instance.setShipDate(random.nextBoolean() ? null : instance.getOrderDate().plusDays(random.nextLong(366L)));
        return instance;
    }
}
