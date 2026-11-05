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
 * Documents the description, an extended property, of the table, or view, the annotated mapped
 * superclass maps, as the database holds it ({@code MS_Description} in AdventureWorks,
 * {@code Description} in WideWorldImporters).
 * <p>
 * A mapped superclass has no {@link jakarta.persistence.Table @Table}, so its table's description
 * is here; the entity which extends it repeats it in
 * {@link jakarta.persistence.Table#comment() @Table(comment = ...)}, and a column's is in
 * {@link jakarta.persistence.Column#comment() @Column(comment = ...)}, both of which a provider
 * writes to the DDL it generates. This annotation is for the reader only.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/system-stored-procedures/sp-addextendedproperty-transact-sql">
 *      sp_addextendedproperty</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/tables/view-the-table-definition">
 *      View the table definition</a>
 */
@Documented                         // appears in the class's Javadoc
@Target(ElementType.TYPE)           // class-only
@Retention(RetentionPolicy.SOURCE)  // documentation only; not in bytecode
public @interface MsDescription {

    /**
     * Schema name, e.g. "dbo".
     *
     * @return schema name, e.g. "dbo".
     */
    String schema() default "dbo";

    /**
     * Table name, e.g. "Orders".
     *
     * @return table name, e.g. "Orders".
     */
    String table();

    /**
     * Table description (MS_Description on the table).
     *
     * @return table description (MS_Description on the table).
     */
    String value();

    /**
     * Column descriptions (MS_Description on each column).
     *
     * @return column descriptions (MS_Description on each column).
     * @deprecated A column's description is in
     *         {@link jakarta.persistence.Column#comment() @Column(comment = ...)}.
     */
    @Deprecated(forRemoval = true)
    Column[] columns() default {};

    /**
     * Documents the {@code MS_Description} extended property of a column; on the attribute which
     * maps the column, or inside {@link MsDescription#columns()}.
     *
     * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/system-stored-procedures/sp-addextendedproperty-transact-sql">
     *      sp_addextendedproperty</a>
     * @deprecated A column's description is in
     *         {@link jakarta.persistence.Column#comment() @Column(comment = ...)}.
     */
    @Deprecated(forRemoval = true)
    @Documented
    @Target(ElementType.FIELD)          // on the attribute; or inside @MsDescription
    @Retention(RetentionPolicy.SOURCE)
    @interface Column {
        /**
         * Physical column name, e.g. "OrderId".
         *
         * @return physical column name, e.g. "OrderId".
         */
        String name();

        /**
         * Column description.
         *
         * @return column description.
         */
        String value();
    }
}