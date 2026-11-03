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
 * Documents that the column the annotated attribute maps is a computed column: the database derives
 * its value from an expression of other columns, and no {@code INSERT} or {@code UPDATE} writes it.
 * <p>
 * Jakarta Persistence has no annotation for a computed column; the mapping of such a column is
 * read-only.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/tables/specify-computed-columns-in-a-table">
 *      Specify computed columns in a table</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/statements/create-table-transact-sql">
 *      CREATE TABLE (computed_column_definition)</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/isnull-transact-sql">ISNULL</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/cast-and-convert-transact-sql">
 *      CAST and CONVERT</a>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MsComputedColumn {

    /**
     * The expression of the column, as the database defines it, {@code
     * (isnull(N'SO'+CONVERT([nvarchar](23),[SalesOrderID]),N'*** ERROR ***'))} say.
     *
     * @return the expression of the column, as the database defines it, {@code
     *         (isnull(N'SO'+CONVERT([nvarchar](23),[SalesOrderID]),N'*** ERROR ***'))} say.
     */
    String value();

    /**
     * Whether the column is {@code PERSISTED}: stored, and updated when a column it depends on
     * changes, rather than computed on each read.
     *
     * @return whether the column is {@code PERSISTED}: stored, and updated when a column it depends
     *         on changes, rather than computed on each read.
     */
    boolean persisted() default false;
}
