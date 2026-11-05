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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing._Entity_PersistenceTest;

/**
 * Verifies the mappings of {@link PriceListDetail} against the schema generated into the in-memory
 * database.
 */
class PriceListDetail_PersistenceTest
        extends _Entity_PersistenceTest<PriceListDetail, Integer> {

    PriceListDetail_PersistenceTest() {
        super(PriceListDetail.class, Integer.class);
    }
}
