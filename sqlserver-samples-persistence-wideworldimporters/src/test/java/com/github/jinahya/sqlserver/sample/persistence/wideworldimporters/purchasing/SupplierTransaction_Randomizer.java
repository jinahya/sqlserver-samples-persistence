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

import com.github.jinahya.object.randomizer.PodamObjectRandomizer;

import java.util.List;

/**
 * Randomizes a new {@link SupplierTransaction}; the identifier, which a sequence generates, what it references, which
 * the persister sets, and what SQL Server fills are left out.
 */
class SupplierTransaction_Randomizer
        extends PodamObjectRandomizer<SupplierTransaction> {

    SupplierTransaction_Randomizer() {
        super(SupplierTransaction.class, List.of(
                SupplierTransaction.ATTRIBUTE_NAME_SUPPLIER_TRANSACTION_ID,
                SupplierTransaction.ATTRIBUTE_NAME_SUPPLIER_ID,
                SupplierTransaction.ATTRIBUTE_NAME_TRANSACTION_TYPE_ID,
                SupplierTransaction.ATTRIBUTE_NAME_PURCHASE_ORDER_ID,
                SupplierTransaction.ATTRIBUTE_NAME_PAYMENT_METHOD_ID,
                SupplierTransaction.ATTRIBUTE_NAME_IS_FINALIZED,
                SupplierTransaction.ATTRIBUTE_NAME_LAST_EDITED_BY,
                SupplierTransaction.ATTRIBUTE_NAME_SUPPLIER,
                SupplierTransaction.ATTRIBUTE_NAME_TRANSACTION_TYPE,
                SupplierTransaction.ATTRIBUTE_NAME_PURCHASE_ORDER,
                SupplierTransaction.ATTRIBUTE_NAME_PAYMENT_METHOD,
                SupplierTransaction.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }
}

