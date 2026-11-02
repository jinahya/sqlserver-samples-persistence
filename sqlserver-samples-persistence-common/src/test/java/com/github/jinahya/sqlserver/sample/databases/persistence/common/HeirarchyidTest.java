package com.github.jinahya.sqlserver.sample.databases.persistence.common;

/*-
 * #%L
 * sqlserver-samples-persistence-common
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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HexFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HeirarchyidTest {

    // the examples of [MS-SSCLRT] section 3.2, and values of AdventureWorks' HumanResources.Employee
    @CsvSource({
            "/,",
            "/1/,58",
            "/1/-2.18/,59FB0540",
            "/1.1/,62C0",
            "/1/1/,5AC0",
            "/1/2/,5B40",
            "/2/,68",
            "/3/,78"
    })
    @ParameterizedTest
    void __(final String string, final String hex) {
        final var bytes = HexFormat.of().parseHex(Optional.ofNullable(hex).orElse(""));
        final var parsed = Heirarchyid.parse(string);
        assertThat(parsed.getBytes()).isEqualTo(bytes);
        final var decoded = Heirarchyid.decode(bytes);
        assertThat(decoded).hasToString(string).isEqualTo(parsed);
    }

    // the bounds of every range, real and fake
    @ValueSource(longs = {
            -281479271682120L, -4294971465L, -4294971464L, -4169L, -4168L, -73L, -72L, -9L, -8L, -1L,
            0L, 3L, 4L, 7L, 8L, 15L, 16L, 79L, 80L, 1103L, 1104L, 5199L, 5200L, 4294972495L, 4294972496L,
            281479271683151L
    })
    @ParameterizedTest
    void __roundTrip(final long value) {
        for (final var string : new String[] {"/" + value + "/", "/1/" + value + "/2/", "/" + (value - 1) + ".5/"}) {
            if (string.equals("/-281479271682121.5/")) {
                continue; // below the lowest range
            }
            final var expected = Heirarchyid.parse(string);
            assertThat(Heirarchyid.decode(expected.getBytes())).hasToString(string).isEqualTo(expected);
        }
    }

    @Test
    void __outOfRange() {
        assertThatThrownBy(() -> Heirarchyid.parse("/281479271683152/")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Heirarchyid.parse("/-281479271682121/")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Heirarchyid.parse("/1//")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Heirarchyid.decode(new byte[] {0x58, 0x00}))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void __order() {
        final var values = new String[] {"/", "/-1/", "/0/", "/0.1/", "/1/", "/1/1/", "/1/1.0/", "/1/2/", "/2/"};
        for (int i = 1; i < values.length; i++) {
            assertThat(Heirarchyid.parse(values[i - 1])).isLessThan(Heirarchyid.parse(values[i]));
        }
    }

    @Test
    void __ancestor() {
        final var node = Heirarchyid.parse("/1/2.3/4/");
        assertThat(node.getLevel()).isEqualTo(3);
        assertThat(node.getAncestor(1)).hasToString("/1/2.3/");
        assertThat(node.getAncestor(3)).isEqualTo(Heirarchyid.ROOT);
        assertThat(node.getAncestor(4)).isNull();
        assertThat(node.isDescendantOf(Heirarchyid.parse("/1/2.3/"))).isTrue();
        assertThat(node.isDescendantOf(Heirarchyid.parse("/1/2/"))).isFalse();
        assertThat(node.isDescendantOf(Heirarchyid.ROOT)).isTrue();
    }
}
