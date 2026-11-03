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
 * Documents that the table the annotated class maps is a system-versioned temporal table: the
 * database keeps each row's past versions in a history table, and sets the period columns itself.
 * <p>
 * Jakarta Persistence has no annotation for a temporal table.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/tables/temporal-tables">
 *      Temporal tables</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/tables/creating-a-system-versioned-temporal-table">
 *      Create a system-versioned temporal table</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MsTemporal {

    /**
     * The schema-qualified name of the history table, {@code Application.Cities_Archive} say.
     *
     * @return the schema-qualified name of the history table, {@code Application.Cities_Archive}
     *         say.
     */
    String historyTable();

    /**
     * The name of the {@code GENERATED ALWAYS AS ROW START} column, {@code ValidFrom} say.
     *
     * @return the name of the {@code GENERATED ALWAYS AS ROW START} column, {@code ValidFrom} say.
     */
    String periodStart();

    /**
     * The name of the {@code GENERATED ALWAYS AS ROW END} column, {@code ValidTo} say.
     *
     * @return the name of the {@code GENERATED ALWAYS AS ROW END} column, {@code ValidTo} say.
     */
    String periodEnd();
}
