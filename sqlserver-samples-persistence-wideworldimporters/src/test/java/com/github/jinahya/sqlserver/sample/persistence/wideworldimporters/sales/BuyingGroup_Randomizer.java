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
 * Randomizes a new {@link BuyingGroup}; the identifier, which a sequence generates, what it references, which the
 * persister sets, and what SQL Server fills are left out.
 */
class BuyingGroup_Randomizer
        extends PodamObjectRandomizer<BuyingGroup> {

    BuyingGroup_Randomizer() {
        super(BuyingGroup.class, List.of(
                BuyingGroup.ATTRIBUTE_NAME_BUYING_GROUP_ID,
                BuyingGroup.ATTRIBUTE_NAME_LAST_EDITED_BY,
                BuyingGroup.ATTRIBUTE_NAME_VALID_FROM,
                BuyingGroup.ATTRIBUTE_NAME_VALID_TO,
                BuyingGroup.ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON
        ));
    }
}

