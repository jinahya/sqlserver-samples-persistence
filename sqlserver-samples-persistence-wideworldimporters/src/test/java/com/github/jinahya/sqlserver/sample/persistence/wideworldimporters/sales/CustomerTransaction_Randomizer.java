package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

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
 * Randomizes a new {@link CustomerTransaction}; the identifier, which a sequence generates, what it references, which
 * the persister sets, and what SQL Server fills are left out.
 */
class CustomerTransaction_Randomizer
        extends PodamObjectRandomizer<CustomerTransaction> {

    CustomerTransaction_Randomizer() {
        super(CustomerTransaction.class, List.of(
                CustomerTransaction.ATTRIBUTE_NAME_CUSTOMER_TRANSACTION_ID,
                CustomerTransaction.ATTRIBUTE_NAME_CUSTOMER_ID,
                CustomerTransaction.ATTRIBUTE_NAME_TRANSACTION_TYPE_ID,
                CustomerTransaction.ATTRIBUTE_NAME_INVOICE_ID,
                CustomerTransaction.ATTRIBUTE_NAME_PAYMENT_METHOD_ID,
                CustomerTransaction.ATTRIBUTE_NAME_IS_FINALIZED,
                CustomerTransaction.ATTRIBUTE_NAME_LAST_EDITED_BY,
                CustomerTransaction.ATTRIBUTE_NAME_CUSTOMER,
                CustomerTransaction.ATTRIBUTE_NAME_TRANSACTION_TYPE,
                CustomerTransaction.ATTRIBUTE_NAME_INVOICE,
                CustomerTransaction.ATTRIBUTE_NAME_PAYMENT_METHOD,
                CustomerTransaction.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }
}

