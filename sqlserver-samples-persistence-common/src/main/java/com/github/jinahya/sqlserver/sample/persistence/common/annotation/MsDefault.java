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
 * Documents the {@code DEFAULT} constraint of the column the annotated attribute maps: the value
 * the database gives the column when an {@code INSERT} leaves it out.
 * <p>
 * Jakarta Persistence has no annotation for a column's default; {@link
 * jakarta.persistence.Column#columnDefinition() columnDefinition} only carries DDL text.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/tables/specify-default-values-for-columns">
 *      Specify default values for columns</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/statements/alter-table-column-constraint-transact-sql">
 *      ALTER TABLE column_constraint (DEFAULT)</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MsDefault {

    /**
     * The name of the default constraint, {@code DF_Address_rowguid} say; empty if the constraint
     * is unnamed or system-named.
     *
     * @return the name of the default constraint, {@code DF_Address_rowguid} say; empty if the
     *         constraint is unnamed or system-named.
     */
    String name() default "";

    /**
     * The expression of the default, as the database defines it, {@code (getdate())} say.
     *
     * @return the expression of the default, as the database defines it, {@code (getdate())} say.
     */
    String value();
}
