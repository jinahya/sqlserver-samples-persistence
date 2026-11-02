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

import com.github.jinahya.bit.io.BitInput;
import com.github.jinahya.bit.io.BitInputs;
import com.github.jinahya.bit.io.BitOutput;
import com.github.jinahya.bit.io.BitOutputs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A value of SQL Server's {@code hierarchyid} type: a path of node labels from the root, each label a sequence of
 * integers.
 * <p>
 * The binary form is the one [MS-SSCLRT] section 2.2 describes. Each integer of a label is one level,
 * {@code Li Oi Fi}: a prefix selecting a range, the offset from the range's lower limit with its anti-ambiguity bits,
 * and a bit which is {@code 1} when the integer ends its label and {@code 0} when a dot follows it, in which case the
 * level holds the integer plus one. Zero bits pad the levels to a whole byte. The root is the empty binary string.
 * <p>
 * Instances compare as SQL Server compares the values, in depth-first order, by their binary forms.
 *
 * @see <a href="https://learn.microsoft.com/en-us/openspecs/sql_server_protocols/ms-ssclrt/">[MS-SSCLRT]</a>
 */
public final class Heirarchyid
        implements Comparable<Heirarchyid> {

    /**
     * The maximum length of the binary form, in bytes. The value is {@value}.
     */
    public static final int MAX_BYTES = 892;

    /**
     * The root, {@code /}.
     */
    public static final Heirarchyid ROOT = new Heirarchyid(List.of(), new byte[0]);

    // ---------------------------------------------------------------------------------------------------- NESTED TYPES

    /**
     * The ranges of section 2.2, each with its {@code Li} prefix and the pattern of its {@code Oi} field: a {@code .}
     * is an offset bit, from the most significant, and a {@code 0} or a {@code 1} is a fixed anti-ambiguity bit.
     */
    private enum Range {

        N48("000100", "..............0.....................0......0...0.1...", -281479271682120L),

        N32("000101", "...................0......0...0.1...", -4294971464L),

        N12("000110", ".....0...0.1...", -4168L),

        N6("0010", "..0.1...", -72L),

        N3("00111", "...", -8L),

        P2A("01", "..", 0L),

        P2B("100", "..", 4L),

        P3("101", "...", 8L),

        P6("110", "..0.1...", 16L),

        P10("1110", "...0...0.1...", 80L),

        P12("11110", ".....0...0.1...", 1104L),

        P32("111110", "...................0......0...0.1...", 5200L),

        P48("111111", "..............0.....................0......0...0.1...", 4294972496L);

        static Range of(final long value) {
            for (final var range : values()) {
                if (value >= range.min && value <= range.max) {
                    return range;
                }
            }
            throw new IllegalArgumentException("out of range: " + value);
        }

        Range(final String prefix, final String pattern, final long min) {
            this.prefix = prefix;
            this.pattern = pattern;
            this.min = min;
            this.max = min + (1L << pattern.replaceAll("[01]", "").length()) - 1L;
        }

        void write(final BitOutput output, final long value) throws IOException {
            for (final var c : prefix.toCharArray()) {
                output.writeBoolean(c == '1');
            }
            final var offset = value - min;
            var bits = pattern.replaceAll("[01]", "").length();
            for (int i = 0; i < pattern.length(); ) {
                final var c = pattern.charAt(i);
                if (c != '.') {
                    output.writeBoolean(c == '1');
                    i++;
                    continue;
                }
                var run = 0;
                while (i < pattern.length() && pattern.charAt(i) == '.') {
                    run++;
                    i++;
                }
                bits -= run;
                output.writeUnsignedLong(run, (offset >>> bits) & ((1L << run) - 1L));
            }
        }

        long read(final BitInput input) throws IOException {
            var offset = 0L;
            for (int i = 0; i < pattern.length(); ) {
                final var c = pattern.charAt(i);
                if (c != '.') {
                    if (input.readBoolean() != (c == '1')) {
                        throw new IllegalArgumentException("wrong anti-ambiguity bit in " + this);
                    }
                    i++;
                    continue;
                }
                var run = 0;
                while (i < pattern.length() && pattern.charAt(i) == '.') {
                    run++;
                    i++;
                }
                offset = (offset << run) | input.readUnsignedLong(run);
            }
            return min + offset;
        }

        int size() {
            return prefix.length() + pattern.length() + 1;
        }

        private final String prefix;

        private final String pattern;

        private final long min;

        private final long max;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Returns the value of the specified node labels.
     *
     * @param labels the node labels, from the root; each a non-empty array of integers.
     * @return the value of {@code labels}.
     * @throws IllegalArgumentException when a label is empty, an integer is out of range, or the binary form is
     *                                  longer than {@value #MAX_BYTES} bytes.
     */
    public static Heirarchyid of(final List<long[]> labels) {
        Objects.requireNonNull(labels, "labels is null");
        final var copy = labels.stream().map(long[]::clone).toList();
        return new Heirarchyid(copy, encode(copy));
    }

    /**
     * Parses the specified string representation, as {@code hierarchyid::Parse} does; {@code /}, {@code /1/},
     * {@code /0.3.-7/}, {@code /1/3/}.
     *
     * @param string the string representation to parse.
     * @return the value of {@code string}.
     * @throws IllegalArgumentException when {@code string} is not a valid representation.
     */
    public static Heirarchyid parse(final String string) {
        Objects.requireNonNull(string, "string is null");
        if (!string.startsWith("/") || !string.endsWith("/")) {
            throw new IllegalArgumentException("not a hierarchyid: " + string);
        }
        if (string.length() == 1) {
            return ROOT;
        }
        final var labels = new ArrayList<long[]>();
        for (final var label : string.substring(1, string.length() - 1).split("/", -1)) {
            try {
                labels.add(Arrays.stream(label.split("\\.", -1)).mapToLong(Long::parseLong).toArray());
            } catch (final NumberFormatException nfe) {
                throw new IllegalArgumentException("not a hierarchyid: " + string, nfe);
            }
        }
        return of(labels);
    }

    /**
     * Decodes the specified binary form, as SQL Server stores and sends it.
     *
     * @param bytes the binary form to decode.
     * @return the value of {@code bytes}.
     * @throws IllegalArgumentException when {@code bytes} is not a valid binary form.
     */
    public static Heirarchyid decode(final byte[] bytes) {
        Objects.requireNonNull(bytes, "bytes is null");
        if (bytes.length > MAX_BYTES) {
            throw new IllegalArgumentException("longer than " + MAX_BYTES + " bytes: " + bytes.length);
        }
        // the last level is a real one, so the levels end with the last one bit; the rest is padding
        var end = bytes.length * Byte.SIZE;
        while (end > 0 && (bytes[(end - 1) / Byte.SIZE] & (0x80 >>> ((end - 1) % Byte.SIZE))) == 0) {
            end--;
        }
        if (bytes.length != (end + Byte.SIZE - 1) / Byte.SIZE) {
            throw new IllegalArgumentException("padded with more than 7 bits");
        }
        final var input = BitInputs.from(bytes);
        final var labels = new ArrayList<long[]>();
        final var label = new ArrayList<Long>();
        try {
            for (var position = 0; position < end; ) {
                final var range = readPrefix(input);
                final var value = range.read(input);
                final var real = input.readBoolean();
                position += range.size();
                if (position > end) {
                    throw new IllegalArgumentException("truncated level");
                }
                if (real) {
                    label.add(value);
                    labels.add(label.stream().mapToLong(Long::longValue).toArray());
                    label.clear();
                } else {
                    label.add(value - 1L);
                }
            }
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        return labels.isEmpty() ? ROOT : new Heirarchyid(labels, bytes.clone());
    }

    private static Range readPrefix(final BitInput input) throws IOException {
        final var prefix = new StringBuilder();
        while (prefix.length() < 6) {
            prefix.append(input.readBoolean() ? '1' : '0');
            for (final var range : Range.values()) {
                if (range.prefix.contentEquals(prefix)) {
                    return range;
                }
            }
        }
        throw new IllegalArgumentException("unknown prefix: " + prefix);
    }

    private static byte[] encode(final List<long[]> labels) {
        final var stream = new ByteArrayOutputStream();
        final var output = BitOutputs.from(stream);
        try {
            for (final var label : labels) {
                if (label.length == 0) {
                    throw new IllegalArgumentException("empty label");
                }
                for (int i = 0; i < label.length; i++) {
                    final var real = i == label.length - 1;
                    if (!real && label[i] == Long.MAX_VALUE) {
                        throw new IllegalArgumentException("out of range: " + label[i]);
                    }
                    final var value = real ? label[i] : label[i] + 1L;
                    Range.of(value).write(output, value);
                    output.writeBoolean(real);
                }
            }
            output.align(1);
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
        final var bytes = stream.toByteArray();
        if (bytes.length > MAX_BYTES) {
            throw new IllegalArgumentException("longer than " + MAX_BYTES + " bytes: " + bytes.length);
        }
        return bytes;
    }

    // ---------------------------------------------------------------------------------------------
    private Heirarchyid(final List<long[]> labels, final byte[] bytes) {
        super();
        this.labels = labels;
        this.bytes = bytes;
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    /**
     * Returns the string representation of this value, as {@code hierarchyid::ToString} does.
     *
     * @return the string representation of this value.
     */
    @Override
    public String toString() {
        final var builder = new StringBuilder("/");
        for (final var label : labels) {
            for (int i = 0; i < label.length; i++) {
                if (i > 0) {
                    builder.append('.');
                }
                builder.append(label[i]);
            }
            builder.append('/');
        }
        return builder.toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Heirarchyid that)) {
            return false;
        }
        return Arrays.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    // ------------------------------------------------------------------------ java.lang.Comparable

    @Override
    public int compareTo(final Heirarchyid o) {
        return Arrays.compareUnsigned(bytes, o.bytes);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the binary form of this value.
     *
     * @return the binary form of this value.
     */
    public byte[] getBytes() {
        return bytes.clone();
    }

    /**
     * Returns the node labels of this value, from the root.
     *
     * @return the node labels of this value.
     */
    public List<long[]> getLabels() {
        return labels.stream().map(long[]::clone).toList();
    }

    /**
     * Returns the level of this value, as {@code hierarchyid::GetLevel} does; {@code 0} for the root.
     *
     * @return the level of this value.
     */
    public int getLevel() {
        return labels.size();
    }

    /**
     * Returns the ancestor of this value the specified levels up, as {@code hierarchyid::GetAncestor} does.
     *
     * @param n the number of levels to go up.
     * @return the ancestor {@code n} levels up; {@code null} when {@code n} is greater than the level of this value.
     */
    public Heirarchyid getAncestor(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("negative n: " + n);
        }
        if (n > labels.size()) {
            return null;
        }
        return n == 0 ? this : of(labels.subList(0, labels.size() - n));
    }

    /**
     * Checks whether this value is a descendant of the specified value, as {@code hierarchyid::IsDescendantOf} does; a
     * value is a descendant of itself.
     *
     * @param parent the value to check against.
     * @return {@code true} when this value is a descendant of {@code parent}; {@code false} otherwise.
     */
    public boolean isDescendantOf(final Heirarchyid parent) {
        Objects.requireNonNull(parent, "parent is null");
        if (parent.labels.size() > labels.size()) {
            return false;
        }
        for (int i = 0; i < parent.labels.size(); i++) {
            if (!Arrays.equals(parent.labels.get(i), labels.get(i))) {
                return false;
            }
        }
        return true;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private final List<long[]> labels;

    private final byte[] bytes;
}
