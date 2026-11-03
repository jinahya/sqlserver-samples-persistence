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
 * Documents the sensitivity classification of the column the annotated attribute maps.
 * <p>
 * Jakarta Persistence has no annotation for data classification.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/security/sql-data-discovery-and-classification">
 *      SQL data discovery and classification</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/statements/add-sensitivity-classification-transact-sql">
 *      ADD SENSITIVITY CLASSIFICATION</a>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MsSensitivity {

    /**
     * The rank of a sensitivity classification.
     */
    enum Rank {

        /**
         * {@code NONE}.
         */
        NONE,

        /**
         * {@code LOW}.
         */
        LOW,

        /**
         * {@code MEDIUM}.
         */
        MEDIUM,

        /**
         * {@code HIGH}.
         */
        HIGH,

        /**
         * {@code CRITICAL}.
         */
        CRITICAL
    }

    /**
     * The sensitivity label, {@code Confidential - GDPR} say.
     *
     * @return the sensitivity label, {@code Confidential - GDPR} say.
     */
    String label();

    /**
     * The information type, {@code Contact Info} say; empty if none.
     *
     * @return the information type, {@code Contact Info} say; empty if none.
     */
    String informationType() default "";

    /**
     * The rank of the classification.
     *
     * @return the rank of the classification.
     */
    Rank rank() default Rank.NONE;
}
