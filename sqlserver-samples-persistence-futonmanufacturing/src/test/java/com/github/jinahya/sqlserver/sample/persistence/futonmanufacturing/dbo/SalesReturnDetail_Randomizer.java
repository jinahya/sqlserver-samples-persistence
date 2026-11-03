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
 * Randomizes a new {@link SalesReturnDetail}; the generated identifier, what it references, which the persister sets,
 * and what SQL Server fills are left out.
 */
class SalesReturnDetail_Randomizer
        extends PodamObjectRandomizer<SalesReturnDetail> {

    SalesReturnDetail_Randomizer() {
        super(SalesReturnDetail.class, List.of(
                SalesReturnDetail.ATTRIBUTE_NAME_RETURN_DETAIL_ID,
                SalesReturnDetail.ATTRIBUTE_NAME_RETURN_ID,
                SalesReturnDetail.ATTRIBUTE_NAME_SO_DETAIL_ID,
                SalesReturnDetail.ATTRIBUTE_NAME_ITEM_ID,
                SalesReturnDetail.ATTRIBUTE_NAME_RETURN_SALES_RETURN,
                SalesReturnDetail.ATTRIBUTE_NAME_SO_DETAIL,
                SalesReturnDetail.ATTRIBUTE_NAME_ITEM
        ));
    }
}

