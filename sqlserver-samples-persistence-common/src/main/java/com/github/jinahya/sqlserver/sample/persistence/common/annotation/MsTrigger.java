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
 * Documents a DML trigger on the table the annotated class maps.
 * <p>
 * Jakarta Persistence has no annotation for a trigger.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/triggers/dml-triggers">
 *      DML triggers</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/statements/create-trigger-transact-sql">
 *      CREATE TRIGGER</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Repeatable(MsTrigger.List.class)
public @interface MsTrigger {

    /**
     * When a trigger fires, relative to the statement which fires it.
     */
    enum Timing {

        /**
         * {@code AFTER} (or {@code FOR}); the trigger fires after the statement succeeds.
         */
        AFTER,

        /**
         * {@code INSTEAD OF}; the trigger runs in place of the statement.
         */
        INSTEAD_OF
    }

    /**
     * A data modification statement which fires a trigger.
     */
    enum Event {

        /**
         * {@code INSERT}.
         */
        INSERT,

        /**
         * {@code UPDATE}.
         */
        UPDATE,

        /**
         * {@code DELETE}.
         */
        DELETE
    }

    /**
     * The name of the trigger, {@code uSalesOrderHeader} say.
     *
     * @return the name of the trigger, {@code uSalesOrderHeader} say.
     */
    String name();

    /**
     * When the trigger fires.
     *
     * @return when the trigger fires.
     */
    Timing timing() default Timing.AFTER;

    /**
     * The statements which fire the trigger.
     *
     * @return the statements which fire the trigger.
     */
    Event[] events();

    /**
     * Holds the repeated {@link MsTrigger @MsTrigger} annotations of an element.
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
        MsTrigger[] value();
    }
}
