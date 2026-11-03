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
 * Randomizes a new {@link SalesQuote}; the generated identifier, what it references, which the persister sets, what it
 * may reference, which is left empty, and what SQL Server fills are left out.
 */
class SalesQuote_Randomizer
        extends PodamObjectRandomizer<SalesQuote> {

    SalesQuote_Randomizer() {
        super(SalesQuote.class, List.of(
                SalesQuote.ATTRIBUTE_NAME_QUOTE_ID,
                SalesQuote.ATTRIBUTE_NAME_CUSTOMER_ID,
                SalesQuote.ATTRIBUTE_NAME_SALES_CHANNEL_ID,
                SalesQuote.ATTRIBUTE_NAME_SALES_REP_ID,
                SalesQuote.ATTRIBUTE_NAME_CONVERTED_TO_ORDER_ID,
                SalesQuote.ATTRIBUTE_NAME_CUSTOMER,
                SalesQuote.ATTRIBUTE_NAME_SALES_CHANNEL,
                SalesQuote.ATTRIBUTE_NAME_SALES_REP,
                SalesQuote.ATTRIBUTE_NAME_CONVERTED_TO_ORDER
        ));
    }
}

