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
 * Randomizes a new {@link ProductProductPhoto}; the identifier, what it references, and what SQL
 * Server fills are left out. The persister takes the rest of the instance into one which
 * {@link ProductProductPhoto#of} creates with the instances it references.
 */
class ProductProductPhoto_Randomizer
        extends PodamObjectRandomizer<ProductProductPhoto> {

    ProductProductPhoto_Randomizer() {
        super(ProductProductPhoto.class, List.of(
                ProductProductPhoto.ATTRIBUTE_NAME_ID,
                ProductProductPhoto.ATTRIBUTE_NAME_PRODUCT,
                ProductProductPhoto.ATTRIBUTE_NAME_PRODUCT_PHOTO,
                ProductProductPhoto.ATTRIBUTE_NAME_MODIFIED_DATE
        ));
    }
}
