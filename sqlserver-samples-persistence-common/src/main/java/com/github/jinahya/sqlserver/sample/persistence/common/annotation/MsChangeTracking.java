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
 * Documents that change tracking is enabled on the table the annotated class maps: the database
 * records which rows changed, not their values.
 * <p>
 * Jakarta Persistence has no annotation for change tracking.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/track-changes/about-change-tracking-sql-server">
 *      About change tracking</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/track-changes/enable-and-disable-change-tracking-sql-server">
 *      Enable and disable change tracking</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MsChangeTracking {

    /**
     * Whether the table tracks which columns an {@code UPDATE} changed ({@code
     * TRACK_COLUMNS_UPDATED = ON}).
     *
     * @return whether the table tracks which columns an {@code UPDATE} changed ({@code
     *         TRACK_COLUMNS_UPDATED = ON}).
     */
    boolean trackColumnsUpdated() default false;
}
