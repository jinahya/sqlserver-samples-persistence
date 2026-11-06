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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;

/**
 * Randomizes a new {@link PurchaseOrderDetail}; the generated identifier, what it references, which the persister sets,
 * and what SQL Server fills are left out.
 */
class PurchaseOrderDetail_Randomizer
        extends PodamObjectRandomizer<PurchaseOrderDetail> {

    PurchaseOrderDetail_Randomizer() {
        super(PurchaseOrderDetail.class, List.of(
                PurchaseOrderDetail.ATTRIBUTE_NAME_PO_DETAIL_ID,
                PurchaseOrderDetail.ATTRIBUTE_NAME_PURCHASE_ORDER_ID,
                PurchaseOrderDetail.ATTRIBUTE_NAME_ITEM_ID,
                PurchaseOrderDetail.ATTRIBUTE_NAME_LINE_TOTAL,
                PurchaseOrderDetail.ATTRIBUTE_NAME_PURCHASE_ORDER,
                PurchaseOrderDetail.ATTRIBUTE_NAME_ITEM
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has values small enough for the {@code LineTotal} column, which SQL Server
     *               computes by multiplying them.
     */
    @Override
    public PurchaseOrderDetail get() {
        final var instance = super.get();
        // Quantity, a decimal(18, 2), and UnitPrice, a decimal(18, 4), > 0
        instance.setQuantity(__Random_TestUtils.positive(5, 2));
        instance.setUnitPrice(__Random_TestUtils.positive(5, 4));
        return instance;
    }
}

