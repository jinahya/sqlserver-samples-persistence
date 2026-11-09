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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct.ProductLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing the {@link ProductLine} enum, whose column values are the padded values
 * the {@value MappedProduct#COLUMN_NAME_PRODUCT_LINE} column holds.
 */
class Product_ProductLine_Test {

    /**
     * Verifies that the column values are the column's values, padded to its length.
     */
    @Test
    void columnValues_ColumnValues() {
        final var expected = Stream.of(MappedProduct.COLUMN_VALUE_PRODUCT_LINE_ROAD,
                                       MappedProduct.COLUMN_VALUE_PRODUCT_LINE_MOUNTAIN,
                                       MappedProduct.COLUMN_VALUE_PRODUCT_LINE_TOURING,
                                       MappedProduct.COLUMN_VALUE_PRODUCT_LINE_STANDARD)
                .map(v -> String.format("%-" + MappedProduct.COLUMN_LENGTH_PRODUCT_LINE + "s", v))
                .toArray(String[]::new);
        assertThat(Arrays.stream(ProductLine.values()).map(ProductLine::columnValue))
                .containsExactlyInAnyOrder(expected);
    }

    @ParameterizedTest
    @EnumSource(ProductLine.class)
    void valueOfColumnValue_Same_ColumnValue(final ProductLine constant) {
        assertThat(ProductLine.valueOfColumnValue(constant.columnValue())).isSameAs(constant);
    }

    /**
     * Verifies that an unpadded value, which the column never holds, is rejected.
     */
    @ParameterizedTest
    @EnumSource(ProductLine.class)
    void valueOfColumnValue_IllegalArgumentException_Unpadded(final ProductLine constant) {
        assertThatThrownBy(() -> ProductLine.valueOfColumnValue(constant.name()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
