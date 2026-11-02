package com.github.jinahya.sqlserver.sample.persistence.common;

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

import com.github.jinahya.sqlserver.sample.persistence.common.Geography;
import org.junit.jupiter.api.Test;

import java.util.HexFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GeographyTest {

    @Test
    void __pointEmpty() {
        // [MS-SSCLRT] section 3.1, POINT EMPTY with SRID 0
        final var bytes = HexFormat.of().parseHex("000000000104" + "00000000" + "00000000" + "01000000" + "FFFFFFFF" + "FFFFFFFF" + "01");
        final var geography = Geography.decode(bytes);
        assertThat(geography).hasToString("POINT EMPTY");
        assertThat(geography.getSrid()).isZero();
        assertThat(geography.getBytes()).isEqualTo(bytes);
    }

    @Test
    void __point() {
        final var point = Geography.point(Geography.SRID_WGS84, 47.65100, -122.34900);
        assertThat(point).hasToString("POINT (-122.349 47.651)");
        assertThat(Geography.decode(point.getBytes())).isEqualTo(point);
        assertThat(point.getLatitude(0)).isEqualTo(47.651);
        assertThat(point.getLongitude(0)).isEqualTo(-122.349);
    }

    @Test
    void __null() {
        assertThat(Geography.decode(HexFormat.of().parseHex("FFFFFFFF"))).isNull();
    }

    @Test
    void __invalid() {
        assertThatThrownBy(() -> Geography.decode(new byte[] {0x00})).isInstanceOf(IllegalArgumentException.class);
        final var point = Geography.point(Geography.SRID_WGS84, 1.0, 2.0).getBytes();
        final var longer = java.util.Arrays.copyOf(point, point.length + 1);
        assertThatThrownBy(() -> Geography.decode(longer)).isInstanceOf(IllegalArgumentException.class);
    }
}
