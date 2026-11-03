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
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Documents a security predicate of a row-level security policy on the table the annotated class
 * maps: the database filters, or blocks, the rows the current user may not see or write.
 * <p>
 * Jakarta Persistence has no annotation for row-level security.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/security/row-level-security">
 *      Row-level security</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/statements/create-security-policy-transact-sql">
 *      CREATE SECURITY POLICY</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Repeatable(MsRowLevelSecurity.List.class)
public @interface MsRowLevelSecurity {

    /**
     * The type of a security predicate.
     */
    enum PredicateType {

        /**
         * {@code FILTER}; the predicate silently filters the rows a read sees.
         */
        FILTER,

        /**
         * {@code BLOCK}; the predicate rejects a write which violates it.
         */
        BLOCK
    }

    /**
     * The operation a {@code BLOCK} predicate applies to.
     */
    enum BlockOperation {

        /**
         * No operation named; the predicate applies to every operation.
         */
        ALL,

        /**
         * {@code AFTER INSERT}.
         */
        AFTER_INSERT,

        /**
         * {@code AFTER UPDATE}.
         */
        AFTER_UPDATE,

        /**
         * {@code BEFORE UPDATE}.
         */
        BEFORE_UPDATE,

        /**
         * {@code BEFORE DELETE}.
         */
        BEFORE_DELETE
    }

    /**
     * The schema-qualified name of the security policy, {@code
     * Application.FilterCustomersBySalesTerritoryRole} say.
     *
     * @return the schema-qualified name of the security policy, {@code
     *         Application.FilterCustomersBySalesTerritoryRole} say.
     */
    String policy();

    /**
     * The type of the predicate.
     *
     * @return the type of the predicate.
     */
    PredicateType type() default PredicateType.FILTER;

    /**
     * The predicate function and its arguments, as the database defines it, {@code
     * ([Application].[DetermineCustomerAccess]([DeliveryCityID]))} say.
     *
     * @return the predicate function and its arguments, as the database defines it, {@code
     *         ([Application].[DetermineCustomerAccess]([DeliveryCityID]))} say.
     */
    String predicate();

    /**
     * The operation a {@code BLOCK} predicate applies to; {@link BlockOperation#ALL ALL} for a
     * {@code FILTER} predicate, or for a {@code BLOCK} predicate which names no operation.
     *
     * @return the operation a {@code BLOCK} predicate applies to.
     */
    BlockOperation operation() default BlockOperation.ALL;

    /**
     * Whether the policy is enabled ({@code STATE = ON}).
     *
     * @return whether the policy is enabled ({@code STATE = ON}).
     */
    boolean enabled() default true;

    /**
     * Holds the repeated {@link MsRowLevelSecurity @MsRowLevelSecurity} annotations of an element.
     */
    @Documented
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.SOURCE)
    @interface List {

        /**
         * Returns the repeated annotations.
         *
         * @return the repeated annotations.
         */
        MsRowLevelSecurity[] value();
    }
}
