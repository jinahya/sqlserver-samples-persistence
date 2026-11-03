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

import java.util.List;

/**
 * Randomizes a new {@link PriceList}; the generated identifier, what it may reference, which is left empty, and what
 * SQL Server fills are left out.
 */
class PriceList_Randomizer
        extends PodamObjectRandomizer<PriceList> {

    PriceList_Randomizer() {
        super(PriceList.class, List.of(
                PriceList.ATTRIBUTE_NAME_PRICE_LIST_ID,
                PriceList.ATTRIBUTE_NAME_SALES_CHANNEL_ID,
                PriceList.ATTRIBUTE_NAME_SALES_CHANNEL
        ));
    }
}

