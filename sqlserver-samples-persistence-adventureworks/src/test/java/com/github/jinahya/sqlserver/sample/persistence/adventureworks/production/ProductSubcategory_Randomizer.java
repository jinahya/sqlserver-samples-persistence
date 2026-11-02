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

import java.util.List;

/**
 * Randomizes a new {@link ProductSubcategory}; the generated identifier, what SQL Server fills, and the category,
 * which the persister sets, are left out.
 */
class ProductSubcategory_Randomizer
        extends PodamObjectRandomizer<ProductSubcategory> {

    ProductSubcategory_Randomizer() {
        super(ProductSubcategory.class, List.of(
                ProductSubcategory.ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID,
                ProductSubcategory.ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID,
                ProductSubcategory.ATTRIBUTE_NAME_PRODUCT_CATEGORY,
                ProductSubcategory.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }
}
