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
 * Documents that the column the annotated attribute maps is a {@code rowversion} column: the
 * database sets a new value on each {@code INSERT} and {@code UPDATE} of the row.
 *
 * @deprecated Jakarta Persistence covers it; map the column with {@link jakarta.persistence.Version
 *             @Version}.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/data-types/rowversion-transact-sql">
 *      rowversion</a>
 * @see <a href="https://jakarta.ee/specifications/persistence/3.2/apidocs/jakarta.persistence/jakarta/persistence/Version.html">
 *      jakarta.persistence.Version</a>
 */
@Deprecated(forRemoval = true)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MsRowVersion {
}
