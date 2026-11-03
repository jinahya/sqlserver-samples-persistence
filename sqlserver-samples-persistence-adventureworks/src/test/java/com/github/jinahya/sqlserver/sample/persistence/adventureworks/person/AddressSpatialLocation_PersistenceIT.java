package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.___PersistenceIT_Utils;
import com.github.jinahya.sqlserver.sample.persistence.common.Geography;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.Assumptions.assumeThat;

/**
 * Checks {@link Geography} against what SQL Server does with {@code Person.Address.SpatialLocation}
 * and with {@code geography::STGeomFromText}.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
class AddressSpatialLocation_PersistenceIT {

    private static final Pattern NUMBER = Pattern.compile("-?\\d+(?:\\.\\d+)?(?:E[-+]?\\d+)?");

    private static double[] coordinates(final String text) {
        return NUMBER.matcher(text).results().mapToDouble(r -> Double.parseDouble(r.group()))
                .toArray();
    }

    /**
     * Asserts that the specified Well-Known Texts are the same but for how their coordinates are
     * written; SQL Server writes each with 15 significant digits, or 17, so a coordinate is the
     * same within 15 significant digits.
     */
    private static void assertSameText(final String actual, final String expected) {
        assertThat(NUMBER.matcher(actual).replaceAll("#")).as("the text of %s", expected)
                .isEqualTo(NUMBER.matcher(expected.toUpperCase(Locale.ROOT)).replaceAll("#"));
        final var actuals = coordinates(actual);
        final var expecteds = coordinates(expected);
        assertThat(actuals).as("the coordinates of %s", expected).hasSameSizeAs(expecteds);
        for (int i = 0; i < actuals.length; i++) {
            assertThat(actuals[i]).as("the coordinate %d of %s", i, expected)
                    .isCloseTo(expecteds[i], within(Math.abs(expecteds[i]) * 1.0E-14));
        }
    }

    @DisplayName(
            "every installed SpatialLocation maps to what STAsText, STSrid, Lat and Long return")
    @Test
    void spatialLocation() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            @SuppressWarnings("unchecked")
            final List<Object[]> rows = em.createNativeQuery("""
                                                                     SELECT a.AddressID,
                                                                            a.SpatialLocation.STAsText(),
                                                                            a.SpatialLocation.STSrid,
                                                                            a.SpatialLocation.Lat,
                                                                            a.SpatialLocation.Long
                                                                     FROM Person.Address AS a
                                                                     WHERE a.SpatialLocation IS NOT NULL""")
                    .getResultList();
            assumeThat(rows).isNotEmpty();
            final Map<Integer, Address> addresses =
                    em.createQuery("SELECT a FROM PersonAddress a", Address.class)
                            .getResultStream()
                            .collect(Collectors.toMap(Address::getAddressId, Function.identity()));
            for (final var row : rows) {
                final var location =
                        addresses.get(((Number) row[0]).intValue()).getSpatialLocation();
                assertSameText(location.toString(), (String) row[1]);
                assertThat(location.getSrid()).isEqualTo(((Number) row[2]).intValue());
                assertThat(location.getLatitude(0)).isEqualTo(((Number) row[3]).doubleValue());
                assertThat(location.getLongitude(0)).isEqualTo(((Number) row[4]).doubleValue());
            }
            return null;
        });
    }

    @DisplayName("a point is serialized as geography::Point serializes it")
    @Test
    void point() {
        final var expected = ___PersistenceIT_Utils.applyInTransactionAndRollback(
                em -> (byte[]) em.createNativeQuery(
                                "SELECT CAST(geography::Point(47.651, -122.349, 4326) AS " +
                                "varbinary(max))")
                        .getSingleResult()
        );
        assertThat(Geography.point(Geography.SRID_WGS84, 47.651, -122.349).getBytes()).isEqualTo(
                expected);
    }

    // a value of each type, and of each figure and segment kind of version 2
    @DisplayName(
            "a value decodes to what STAsText and AsTextZM return, and encodes back to its bytes")
    @ParameterizedTest
    @ValueSource(strings = {
            "POINT (-122.349 47.651)",
            "POINT EMPTY",
            "POINT (-122.349 47.651 10 20)",
            "POINT (-122.349 47.651 NULL 20)",
            "POINT (-122.349 47.651 10)",
            "LINESTRING (-122.36 47.656, -122.343 47.656)",
            "LINESTRING (-122.36 47.656, -122.343 47.656, -122.35 47.66)",
            "LINESTRING EMPTY",
            "POLYGON ((-122.358 47.653, -122.348 47.649, -122.348 47.658, -122.358 47.658, -122" +
            ".358 47.653))",
            "POLYGON ((-122.358 47.653, -122.348 47.649, -122.348 47.658, -122.358 47.658, -122" +
            ".358 47.653), "
            + "(-122.351 47.654, -122.354 47.656, -122.351 47.657, -122.351 47.654))",
            "MULTIPOINT ((-122.36 47.656), (-122.343 47.656))",
            "MULTILINESTRING ((-122.36 47.656, -122.343 47.656), (-122.341 47.656, -122.341 47" +
            ".661))",
            "MULTIPOLYGON (((-122.358 47.653, -122.348 47.649, -122.348 47.658, -122.358 47.658, " +
            "-122.358 47.653)), "
            + "((-122.341 47.656, -122.341 47.661, -122.351 47.661, -122.341 47.656)))",
            "GEOMETRYCOLLECTION (POINT (-122.349 47.651), LINESTRING (-122.36 47.656, -122.343 47" +
            ".656))",
            "GEOMETRYCOLLECTION (MULTIPOINT ((-122.36 47.656), (-122.343 47.656)), POINT EMPTY)",
            "GEOMETRYCOLLECTION EMPTY",
            "CIRCULARSTRING (-122.358 47.653, -122.348 47.649, -122.348 47.658)",
            "COMPOUNDCURVE (CIRCULARSTRING (-122.358 47.653, -122.348 47.649, -122.348 47.658), "
            + "(-122.348 47.658, -122.358 47.658, -122.358 47.653))",
            "CURVEPOLYGON (CIRCULARSTRING (-122.358 47.653, -122.348 47.649, -122.348 47.658, " +
            "-122.358 47.658, "
            + "-122.358 47.653))",
            "CURVEPOLYGON (COMPOUNDCURVE (CIRCULARSTRING (-122.358 47.653, -122.348 47.649, -122" +
            ".348 47.658), "
            + "(-122.348 47.658, -122.358 47.658, -122.358 47.653)))",
            "FULLGLOBE"
    })
    void wkt(final String wkt) {
        final var row = ___PersistenceIT_Utils.applyInTransactionAndRollback(
                em -> (Object[]) em.createNativeQuery("""
                                                              SELECT CAST(g.v AS varbinary(max)), g.v.STAsText(), g.v.AsTextZM()
                                                              FROM (SELECT geography::STGeomFromText(?1, 4326) AS v) AS g""")
                        .setParameter(1, wkt)
                        .getSingleResult()
        );
        final var bytes = (byte[]) row[0];
        final var geography = Geography.decode(bytes);
        assertSameText(geography.toString(), (String) row[1]);
        assertSameText(geography.asTextZM(), (String) row[2]);
        assertThat(geography.getBytes()).isEqualTo(bytes);
    }

    @DisplayName(
            "a SpatialLocation set through the entity is written as SQL Server reads it, and so " +
            "is a null")
    @Test
    void write() {
        ___PersistenceIT_Utils.applyInTransactionAndRollback(em -> {
            final var ids = em.createNativeQuery("""
                                                         SELECT TOP (3) a.AddressID
                                                         FROM Person.Address AS a
                                                         ORDER BY a.AddressID""")
                    .getResultStream()
                    .map(i -> ((Number) i).intValue())
                    .toList();
            assumeThat(ids).hasSize(3);
            final var point = Geography.point(Geography.SRID_WGS84, 47.651, -122.349);
            final var polygon = Geography.decode((byte[]) em.createNativeQuery(
                            "SELECT CAST(geography::STGeomFromText(?1, 4326) AS varbinary(max))")
                    .setParameter(1,
                                  "POLYGON ((-122.358 47.653, -122.348 47.649, -122.348 47.658, " +
                                  "-122.358 47.653))")
                    .getSingleResult());
            em.find(Address.class, ids.get(0)).setSpatialLocation(point);
            em.find(Address.class, ids.get(1)).setSpatialLocation(polygon);
            em.find(Address.class, ids.get(2)).setSpatialLocation(null);
            em.flush();
            final var read = em.createNativeQuery("""
                                                          SELECT a.SpatialLocation.STAsText()
                                                          FROM Person.Address AS a
                                                          WHERE a.AddressID = ?1""");
            assertSameText(point.toString(),
                           (String) read.setParameter(1, ids.get(0)).getSingleResult());
            assertSameText(polygon.toString(),
                           (String) read.setParameter(1, ids.get(1)).getSingleResult());
            assertThat(read.setParameter(1, ids.get(2)).getSingleResult()).isNull();
            em.clear();
            assertThat(em.find(Address.class, ids.get(0)).getSpatialLocation()).isEqualTo(point);
            assertThat(em.find(Address.class, ids.get(1)).getSpatialLocation()).isEqualTo(polygon);
            return null;
        });
    }
}
