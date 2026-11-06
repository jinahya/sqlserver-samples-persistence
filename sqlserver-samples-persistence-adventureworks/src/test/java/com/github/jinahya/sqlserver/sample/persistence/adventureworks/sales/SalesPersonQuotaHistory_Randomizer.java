package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;


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
 * Randomizes a new {@link SalesPersonQuotaHistory}; the identifier, what it references, and what
 * SQL Server fills are left out. The persister takes the rest of the instance into one which
 * {@link SalesPersonQuotaHistory#of} creates with the sales person it references and a quota date
 * of its own.
 */
class SalesPersonQuotaHistory_Randomizer
        extends PodamObjectRandomizer<SalesPersonQuotaHistory> {

    SalesPersonQuotaHistory_Randomizer() {
        super(SalesPersonQuotaHistory.class, List.of(
                SalesPersonQuotaHistory.ATTRIBUTE_NAME_BUSINESS_ENTITY_ID,
                SalesPersonQuotaHistory.ATTRIBUTE_NAME_BUSINESS_ENTITY,
                SalesPersonQuotaHistory.ATTRIBUTE_NAME_QUOTA_DATE,
                SalesPersonQuotaHistory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote The returned instance has a date and an amount within their columns and
     *         CHECK constraint.
     */
    @Override
    public SalesPersonQuotaHistory get() {
        final var instance = super.get();
        // SalesQuota, a money, > 0
        instance.setSalesQuota(__Random_TestUtils.positive(5, 4));
        return instance;
    }
}
