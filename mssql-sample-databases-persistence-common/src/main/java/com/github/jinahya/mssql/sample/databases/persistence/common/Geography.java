package com.github.jinahya.mssql.sample.databases.persistence.common;

/*-
 * #%L
 * mssql-sample-databases-persistence-common
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

import java.math.BigDecimal;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A value of SQL Server's {@code geography} type, in the binary form [MS-SSCLRT] section 2.1 describes.
 * <p>
 * The value keeps the structure as it is serialized -- the points, the figures, the shapes and the segments -- so that
 * {@link #getBytes()} returns what {@link #decode(byte[])} read. {@link #toString()} returns the Well-Known Text, as
 * {@code STAsText} does, longitude first.
 *
 * @see <a href="https://learn.microsoft.com/en-us/openspecs/sql_server_protocols/ms-ssclrt/">[MS-SSCLRT]</a>
 */
public final class Geography {

    /**
     * The default spatial reference identifier, WGS 84. The value is {@value}.
     */
    public static final int SRID_WGS84 = 4326;

    private static final int SRID_NULL = -1;

    private static final int PROPERTY_Z = 0x01;

    private static final int PROPERTY_M = 0x02;

    private static final int PROPERTY_V = 0x04;

    private static final int PROPERTY_P = 0x08;

    private static final int PROPERTY_L = 0x10;

    private static final int FIGURE_V2_ARC = 2;

    private static final int FIGURE_V2_COMPOSITE = 3;

    private static final int SEGMENT_ARC = 1;

    private static final int SEGMENT_FIRST_LINE = 2;

    private static final int SEGMENT_FIRST_ARC = 3;

    private static final String[] TYPE_NAMES = {
            null, "POINT", "LINESTRING", "POLYGON", "MULTIPOINT", "MULTILINESTRING", "MULTIPOLYGON",
            "GEOMETRYCOLLECTION", "CIRCULARSTRING", "COMPOUNDCURVE", "CURVEPOLYGON", "FULLGLOBE"
    };

    private static final int TYPE_POINT = 1;

    private static final int TYPE_LINE_STRING = 2;

    private static final int TYPE_POLYGON = 3;

    private static final int TYPE_MULTI_POINT = 4;

    private static final int TYPE_GEOMETRY_COLLECTION = 7;

    private static final int TYPE_CIRCULAR_STRING = 8;

    private static final int TYPE_COMPOUND_CURVE = 9;

    private static final int TYPE_CURVE_POLYGON = 10;

    private static final int TYPE_FULL_GLOBE = 11;

    // ---------------------------------------------------------------------------------------------------- NESTED TYPES

    private record Figure(int attribute, int pointOffset) {

    }

    private record Shape(int parentOffset, int figureOffset, int type) {

    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Returns a point of the specified spatial reference identifier, as {@code geography::Point} does.
     *
     * @param srid      the spatial reference identifier.
     * @param latitude  the latitude, in decimal degrees.
     * @param longitude the longitude, in decimal degrees.
     * @return a point of {@code latitude} and {@code longitude}.
     */
    public static Geography point(final int srid, final double latitude, final double longitude) {
        return new Geography(srid, 1, PROPERTY_V | PROPERTY_P, new double[] {latitude}, new double[] {longitude},
                             null, null, List.of(new Figure(1, 0)), List.of(new Shape(-1, 0, TYPE_POINT)),
                             new byte[0]);
    }

    /**
     * Decodes the specified binary form, as SQL Server stores and sends it.
     *
     * @param bytes the binary form to decode.
     * @return the value of {@code bytes}; {@code null} when {@code bytes} is a null geography, whose SRID is -1.
     * @throws IllegalArgumentException when {@code bytes} is not a valid binary form.
     */
    public static Geography decode(final byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes is null");
        final var buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        try {
            final var srid = buffer.getInt();
            if (srid == SRID_NULL) {
                return null;
            }
            final var version = Byte.toUnsignedInt(buffer.get());
            if (version != 1 && version != 2) {
                throw new IllegalArgumentException("unknown version: " + version);
            }
            final var properties = Byte.toUnsignedInt(buffer.get());
            final var single = (properties & PROPERTY_P) != 0 ? 1 : (properties & PROPERTY_L) != 0 ? 2 : 0;
            final var count = single > 0 ? single : count(buffer);
            final var latitudes = new double[count];
            final var longitudes = new double[count];
            for (int i = 0; i < count; i++) {
                latitudes[i] = buffer.getDouble();
                longitudes[i] = buffer.getDouble();
            }
            final var zs = (properties & PROPERTY_Z) != 0 ? doubles(buffer, count) : null;
            final var ms = (properties & PROPERTY_M) != 0 ? doubles(buffer, count) : null;
            final List<Figure> figures;
            final List<Shape> shapes;
            if (single > 0) {
                figures = List.of(new Figure(1, 0));
                shapes = List.of(new Shape(-1, 0, single == 1 ? TYPE_POINT : TYPE_LINE_STRING));
            } else {
                figures = new ArrayList<>();
                for (int i = count(buffer); i > 0; i--) {
                    figures.add(new Figure(Byte.toUnsignedInt(buffer.get()), buffer.getInt()));
                }
                shapes = new ArrayList<>();
                for (int i = count(buffer); i > 0; i--) {
                    shapes.add(new Shape(buffer.getInt(), buffer.getInt(), Byte.toUnsignedInt(buffer.get())));
                }
            }
            if (shapes.isEmpty()) {
                throw new IllegalArgumentException("no shape");
            }
            // SQL Server writes the segments, and their count, only when a figure is a composite curve
            final var composite = version == 2 && figures.stream().anyMatch(f -> f.attribute() == FIGURE_V2_COMPOSITE);
            final var segments = new byte[composite ? count(buffer) : 0];
            buffer.get(segments);
            if (buffer.hasRemaining()) {
                throw new IllegalArgumentException(buffer.remaining() + " bytes left");
            }
            final var geography = new Geography(srid, version, properties, latitudes, longitudes, zs, ms,
                                                List.copyOf(figures), List.copyOf(shapes), segments);
            if (!Arrays.equals(geography.bytes, bytes)) {
                throw new IllegalArgumentException("not in the canonical form");
            }
            return geography;
        } catch (final BufferUnderflowException bue) {
            throw new IllegalArgumentException("truncated", bue);
        }
    }

    private static int count(final ByteBuffer buffer) {
        final var count = buffer.getInt();
        if (count < 0 || count > buffer.remaining()) {
            throw new IllegalArgumentException("wrong count: " + count);
        }
        return count;
    }

    private static double[] doubles(final ByteBuffer buffer, final int count) {
        final var values = new double[count];
        for (int i = 0; i < count; i++) {
            values[i] = buffer.getDouble();
        }
        return values;
    }

    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    private Geography(final int srid, final int version, final int properties, final double[] latitudes,
                      final double[] longitudes, final double[] zs, final double[] ms, final List<Figure> figures,
                      final List<Shape> shapes, final byte[] segments) {
        super();
        this.srid = srid;
        this.version = version;
        this.properties = properties;
        this.latitudes = latitudes;
        this.longitudes = longitudes;
        this.zs = zs;
        this.ms = ms;
        this.figures = figures;
        this.shapes = shapes;
        this.segments = segments;
        this.bytes = encode();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    /**
     * Returns the Well-Known Text of this value, as {@code STAsText} does: longitude first, without Z and M values.
     * <p>
     * Each coordinate is written in the shortest form which reads back as the same {@code double}. SQL Server writes
     * each with 15 significant digits, or 17, so the text may differ from {@code STAsText} in the last digits of a
     * coordinate.
     *
     * @return the Well-Known Text of this value.
     * @see #asTextZM()
     */
    @Override
    public String toString() {
        final var builder = new StringBuilder();
        appendShape(builder, 0, true, false);
        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Geography that)) {
            return false;
        }
        return Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private byte[] encode() {
        final var single = (properties & (PROPERTY_P | PROPERTY_L)) != 0;
        final var count = latitudes.length;
        final var size = 6 + (single ? 0 : 4) + count * 16 + (zs == null ? 0 : count * 8)
                         + (ms == null ? 0 : count * 8)
                         + (single ? 0 : 4 + figures.size() * 5 + 4 + shapes.size() * 9)
                         + (segments.length > 0 ? 4 + segments.length : 0);
        final var buffer = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(srid).put((byte) version).put((byte) properties);
        if (!single) {
            buffer.putInt(count);
        }
        for (int i = 0; i < count; i++) {
            buffer.putDouble(latitudes[i]).putDouble(longitudes[i]);
        }
        for (final var values : new double[][] {zs, ms}) {
            if (values != null) {
                Arrays.stream(values).forEach(buffer::putDouble);
            }
        }
        if (!single) {
            buffer.putInt(figures.size());
            figures.forEach(f -> buffer.put((byte) f.attribute()).putInt(f.pointOffset()));
            buffer.putInt(shapes.size());
            shapes.forEach(s -> buffer.putInt(s.parentOffset()).putInt(s.figureOffset()).put((byte) s.type()));
            if (segments.length > 0) {
                buffer.putInt(segments.length).put(segments);
            }
        }
        return buffer.array();
    }

    // ------------------------------------------------------------------------------------------------------------- WKT

    private void appendShape(final StringBuilder builder, final int index, final boolean tagged, final boolean zm) {
        final var shape = shapes.get(index);
        final var type = shape.type();
        if (type < TYPE_POINT || type > TYPE_FULL_GLOBE) {
            throw new IllegalStateException("unknown type: " + type);
        }
        if (tagged) {
            builder.append(TYPE_NAMES[type]);
            if (type == TYPE_FULL_GLOBE) {
                return;
            }
            builder.append(' ');
        }
        final var children = IntStream.range(index + 1, shapes.size())
                .filter(i -> shapes.get(i).parentOffset() == index)
                .toArray();
        final var figureIndices = figureIndices(index);
        final var container = type >= TYPE_MULTI_POINT && type <= TYPE_GEOMETRY_COLLECTION;
        if (container ? children.length == 0 : figureIndices.length == 0) {
            builder.append("EMPTY");
            return;
        }
        builder.append('(');
        switch (type) {
            case TYPE_POINT, TYPE_LINE_STRING, TYPE_CIRCULAR_STRING -> builder.append(points(figureIndices[0], zm));
            case TYPE_POLYGON -> builder.append(Arrays.stream(figureIndices)
                                                        .mapToObj(f -> '(' + points(f, zm) + ')')
                                                        .collect(Collectors.joining(", ")));
            case TYPE_COMPOUND_CURVE -> appendCompound(builder, figureIndices[0], zm);
            case TYPE_CURVE_POLYGON -> {
                for (int i = 0; i < figureIndices.length; i++) {
                    if (i > 0) {
                        builder.append(", ");
                    }
                    final var figure = figureIndices[i];
                    switch (figures.get(figure).attribute()) {
                        case FIGURE_V2_ARC -> builder.append("CIRCULARSTRING (").append(points(figure, zm)).append(')');
                        case FIGURE_V2_COMPOSITE -> {
                            builder.append("COMPOUNDCURVE (");
                            appendCompound(builder, figure, zm);
                            builder.append(')');
                        }
                        default -> builder.append('(').append(points(figure, zm)).append(')');
                    }
                }
            }
            default -> {
                // MULTIPOINT, MULTILINESTRING and MULTIPOLYGON leave their members untagged
                final var memberTagged = type == TYPE_GEOMETRY_COLLECTION;
                for (int i = 0; i < children.length; i++) {
                    if (i > 0) {
                        builder.append(", ");
                    }
                    appendShape(builder, children[i], memberTagged, zm);
                }
            }
        }
        builder.append(')');
    }

    /**
     * Returns the indices of the figures of the specified shape, which run up to the figures of the next shape which
     * has any.
     */
    private int[] figureIndices(final int index) {
        final var start = shapes.get(index).figureOffset();
        if (start < 0) {
            return new int[0];
        }
        final var end = IntStream.range(index + 1, shapes.size())
                .map(i -> shapes.get(i).figureOffset())
                .filter(o -> o >= 0)
                .findFirst()
                .orElse(figures.size());
        return IntStream.range(start, end).toArray();
    }

    private int pointEnd(final int figure) {
        return figure + 1 < figures.size() ? figures.get(figure + 1).pointOffset() : latitudes.length;
    }

    private String points(final int figure, final boolean zm) {
        return points(figures.get(figure).pointOffset(), pointEnd(figure), zm);
    }

    private String points(final int start, final int end, final boolean zm) {
        return IntStream.range(start, end).mapToObj(i -> point(i, zm)).collect(Collectors.joining(", "));
    }

    private String point(final int i, final boolean zm) {
        final var builder = new StringBuilder(number(longitudes[i])).append(' ').append(number(latitudes[i]));
        if (!zm) {
            return builder.toString();
        }
        final var hasZ = zs != null && !Double.isNaN(zs[i]);
        final var hasM = ms != null && !Double.isNaN(ms[i]);
        if (hasZ || hasM) {
            builder.append(' ').append(hasZ ? number(zs[i]) : "NULL");
        }
        if (hasM) {
            builder.append(' ').append(number(ms[i]));
        }
        return builder.toString();
    }

    private static String number(final double value) {
        return BigDecimal.valueOf(value).stripTrailingZeros().toPlainString();
    }

    /**
     * Appends the parts of the specified composite curve figure, each a run of lines or of arcs which a first-line or
     * a first-arc segment starts.
     */
    private void appendCompound(final StringBuilder builder, final int figure, final boolean zm) {
        var segment = segmentStart(figure);
        var point = figures.get(figure).pointOffset();
        final var end = pointEnd(figure);
        final var parts = new ArrayList<String>();
        while (point + 1 < end) {
            final var arc = isArc(segments[segment]);
            final var start = point;
            do {
                point += isArc(segments[segment++]) ? 2 : 1;
            } while (point + 1 < end && !isFirst(segments[segment]));
            parts.add((arc ? "CIRCULARSTRING (" : "(") + points(start, point + 1, zm) + ")");
        }
        builder.append(String.join(", ", parts));
    }

    /**
     * Returns the index of the first segment of the specified composite curve figure; the composite figures before it
     * consume the segments in order.
     */
    private int segmentStart(final int figure) {
        var segment = 0;
        for (int f = 0; f < figure; f++) {
            if (figures.get(f).attribute() != FIGURE_V2_COMPOSITE) {
                continue;
            }
            for (var point = figures.get(f).pointOffset() + 1; point < pointEnd(f); ) {
                point += isArc(segments[segment++]) ? 2 : 1;
            }
        }
        return segment;
    }

    private static boolean isArc(final byte segment) {
        return segment == SEGMENT_ARC || segment == SEGMENT_FIRST_ARC;
    }

    private static boolean isFirst(final byte segment) {
        return segment == SEGMENT_FIRST_LINE || segment == SEGMENT_FIRST_ARC;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the Well-Known Text of this value with its Z and M values, as {@code AsTextZM} does; a missing Z value
     * before an M value is {@code NULL}. The coordinates are written as {@link #toString()} writes them.
     *
     * @return the Well-Known Text of this value with its Z and M values.
     */
    public String asTextZM() {
        final var builder = new StringBuilder();
        appendShape(builder, 0, true, true);
        return builder.toString();
    }

    /**
     * Returns the binary form of this value.
     *
     * @return the binary form of this value.
     */
    public byte[] getBytes() {
        return bytes.clone();
    }

    /**
     * Returns the spatial reference identifier of this value.
     *
     * @return the spatial reference identifier of this value.
     */
    public int getSrid() {
        return srid;
    }

    /**
     * Returns the number of points of this value, as {@code STNumPoints} does.
     *
     * @return the number of points of this value.
     */
    public int getNumPoints() {
        return latitudes.length;
    }

    /**
     * Returns the latitude of the point at the specified index.
     *
     * @param index the index of the point.
     * @return the latitude of the point at {@code index}.
     */
    public double getLatitude(final int index) {
        return latitudes[index];
    }

    /**
     * Returns the longitude of the point at the specified index.
     *
     * @param index the index of the point.
     * @return the longitude of the point at {@code index}.
     */
    public double getLongitude(final int index) {
        return longitudes[index];
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final int srid;

    private final int version;

    private final int properties;

    private final double[] latitudes;

    private final double[] longitudes;

    private final double[] zs;

    private final double[] ms;

    private final List<Figure> figures;

    private final List<Shape> shapes;

    private final byte[] segments;

    private final byte[] bytes;
}
