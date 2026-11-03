package com.github.jinahya.sqlserver.sample.persistence.common.annotation;

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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documents a sequence object of the database, which the annotated class or attribute uses.
 *
 * @deprecated Jakarta Persistence covers it; use a {@link jakarta.persistence.SequenceGenerator
 *             @SequenceGenerator} for a sequence which generates an identifier. A sequence which a
 *             column's default reads, {@code NEXT VALUE FOR Sequences.CustomerID} say, is
 *             documented with {@link MsDefault @MsDefault}.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/sequence-numbers/sequence-numbers">
 *      Sequence numbers</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/statements/create-sequence-transact-sql">
 *      CREATE SEQUENCE</a>
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/SequenceGenerator.html">
 *      jakarta.persistence.SequenceGenerator</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@Deprecated(forRemoval = true)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface MsSequence {

    /**
     * The schema-qualified name of the sequence, {@code Sequences.CustomerID} say.
     *
     * @return the schema-qualified name of the sequence, {@code Sequences.CustomerID} say.
     */
    String name();

    /**
     * The value the sequence starts with.
     *
     * @return the value the sequence starts with.
     */
    long startWith() default 1L;

    /**
     * The value the sequence is incremented by.
     *
     * @return the value the sequence is incremented by.
     */
    long incrementBy() default 1L;
}
