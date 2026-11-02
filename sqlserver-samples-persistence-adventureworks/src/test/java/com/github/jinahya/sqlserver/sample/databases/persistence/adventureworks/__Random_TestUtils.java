package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ThreadLocalRandom;

/**
 * What the randomizers draw the values from which their tables constrain.
 */
@SuppressWarnings({
        "java:S101" // Class names should comply with a naming convention
})
public final class __Random_TestUtils {

    /**
     * Returns a code of the specified length, of digits only; the installed codes are of letters, so it takes no key
     * of theirs, which a case-insensitive collation compares.
     *
     * @param length the length of the code.
     * @return a code of {@code length} digits.
     */
    public static String code(final int length) {
        final var random = ThreadLocalRandom.current();
        final var builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append((char) ('0' + random.nextInt(10)));
        }
        return builder.toString();
    }

    /**
     * Returns a positive decimal of the specified scale, less than ten to the power of the specified number of integer
     * digits.
     *
     * @param integerDigits the number of integer digits.
     * @param scale         the scale.
     * @return a positive decimal.
     */
    public static BigDecimal positive(final int integerDigits, final int scale) {
        final var bound = BigDecimal.TEN.pow(integerDigits + scale).longValueExact();
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextLong(1L, bound), scale);
    }

    /**
     * Returns a date and time within the last ten years, to the second.
     *
     * @return a date and time within the last ten years.
     */
    public static LocalDateTime dateTime() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
                .minusSeconds(ThreadLocalRandom.current().nextLong(ChronoUnit.YEARS.getDuration().getSeconds() * 10));
    }

    private __Random_TestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
