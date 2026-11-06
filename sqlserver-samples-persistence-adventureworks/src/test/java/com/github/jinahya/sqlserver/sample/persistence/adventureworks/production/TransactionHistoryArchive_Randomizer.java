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
import com.github.jinahya.sqlserver.sample.persistence.shared.__Random_TestUtils;

import java.util.List;

/**
 * Randomizes a new {@link TransactionHistoryArchive}; the identifier, which the persister sets, and
 * what SQL Server fills are left out.
 */
class TransactionHistoryArchive_Randomizer
        extends PodamObjectRandomizer<TransactionHistoryArchive> {

    TransactionHistoryArchive_Randomizer() {
        super(TransactionHistoryArchive.class, List.of(
                TransactionHistoryArchive.ATTRIBUTE_NAME_TRANSACTION_ID,
                TransactionHistoryArchive.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a date and a cost within their columns.
     */
    @Override
    public TransactionHistoryArchive get() {
        final var instance = super.get();
        // TransactionDate, a datetime; ActualCost, a money
        instance.setTransactionDate(__Random_TestUtils.dateTime());
        instance.setActualCost(__Random_TestUtils.positive(5, 4));
        return instance;
    }
}
