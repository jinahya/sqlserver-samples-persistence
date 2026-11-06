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
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;

/**
 * Randomizes a new {@link PurchaseOrderDetail}; the identifier, what it references, and what SQL
 * Server fills are left out. The persister takes the rest of the instance into one which
 * {@link PurchaseOrderDetail#of} creates with the order it references.
 */
class PurchaseOrderDetail_Randomizer
        extends PodamObjectRandomizer<PurchaseOrderDetail> {

    PurchaseOrderDetail_Randomizer() {
        super(PurchaseOrderDetail.class, List.of(
                PurchaseOrderDetail.ATTRIBUTE_NAME_PURCHASE_ORDER_ID,
                PurchaseOrderDetail.ATTRIBUTE_NAME_PURCHASE_ORDER_DETAIL_ID,
                PurchaseOrderDetail.ATTRIBUTE_NAME_PRODUCT_ID,
                PurchaseOrderDetail.ATTRIBUTE_NAME_LINE_TOTAL,
                PurchaseOrderDetail.ATTRIBUTE_NAME_STOCKED_QTY,
                PurchaseOrderDetail.ATTRIBUTE_NAME_PURCHASE_ORDER,
                PurchaseOrderDetail.ATTRIBUTE_NAME_PRODUCT,
                PurchaseOrderDetail.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has amounts within their columns and CHECK
     *         constraints.
     */
    @Override
    public PurchaseOrderDetail get() {
        final var instance = super.get();
        // DueDate, a datetime; UnitPrice, a money, >= 0; ReceivedQty and RejectedQty, both
        // decimal(8, 2), >= 0
        instance.setDueDate(__Random_TestUtils.dateTime());
        instance.setUnitPrice(__Random_TestUtils.positive(5, 4));
        instance.setReceivedQty(__Random_TestUtils.positive(5, 2));
        instance.setRejectedQty(__Random_TestUtils.positive(5, 2));
        return instance;
    }
}
