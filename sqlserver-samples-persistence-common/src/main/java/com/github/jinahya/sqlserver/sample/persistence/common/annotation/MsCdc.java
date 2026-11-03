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
 * Documents that change data capture is enabled on the table the annotated class maps: the database
 * copies each change, with its values, to a change table.
 * <p>
 * Jakarta Persistence has no annotation for change data capture.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/track-changes/about-change-data-capture-sql-server">
 *      About change data capture</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/track-changes/enable-and-disable-change-data-capture-sql-server">
 *      Enable and disable change data capture</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MsCdc {

    /**
     * The name of the capture instance, {@code dbo_Customer} say; empty for the default, {@code
     * <schema>_<table>}.
     *
     * @return the name of the capture instance, {@code dbo_Customer} say; empty for the default,
     *         {@code <schema>_<table>}.
     */
    String captureInstance() default "";

    /**
     * Whether the capture instance supports querying net changes.
     *
     * @return whether the capture instance supports querying net changes.
     */
    boolean supportsNetChanges() default false;
}
