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

import com.github.jinahya.persistence.more.test.AttributeEnum_Test;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct.ProductStyle;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class for testing the {@link ProductStyle} enum, whose attribute values are the padded values
 * the {@value MappedProduct#COLUMN_NAME_STYLE} column holds.
 */
class Product_ProductStyle_Test
        extends AttributeEnum_Test.OfStringTest<ProductStyle> {

    Product_ProductStyle_Test() {
        super(ProductStyle.class);
    }

    /**
     * {@inheritDoc}
     *
     * @return the column's values, padded to its length.
     */
    @Override
    protected Set<String> expectedAttributeValues() {
        return Stream.of(MappedProduct.COLUMN_VALUE_STYLE_WOMENS,
                         MappedProduct.COLUMN_VALUE_STYLE_MENS,
                         MappedProduct.COLUMN_VALUE_STYLE_UNIVERSAL)
                .map(v -> String.format("%-" + MappedProduct.COLUMN_LENGTH_STYLE + "s", v))
                .collect(Collectors.toSet());
    }
}
