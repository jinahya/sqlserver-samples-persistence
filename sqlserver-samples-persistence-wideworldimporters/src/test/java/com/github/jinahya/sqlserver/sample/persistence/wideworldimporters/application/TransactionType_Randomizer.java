package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application;

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
 * Randomizes a new {@link TransactionType}; the identifier, which a sequence generates, what it references, which the
 * persister sets, and what SQL Server fills are left out.
 */
class TransactionType_Randomizer
        extends PodamObjectRandomizer<TransactionType> {

    TransactionType_Randomizer() {
        super(TransactionType.class, List.of(
                TransactionType.ATTRIBUTE_NAME_TRANSACTION_TYPE_ID,
                TransactionType.ATTRIBUTE_NAME_LAST_EDITED_BY,
                TransactionType.ATTRIBUTE_NAME_VALID_FROM,
                TransactionType.ATTRIBUTE_NAME_VALID_TO,
                TransactionType.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }
}

