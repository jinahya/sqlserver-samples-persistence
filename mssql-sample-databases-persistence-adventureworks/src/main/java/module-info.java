/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

/**
 * Jakarta Persistence mappings for AdventureWorks, the classic OLTP sample database of SQL Server.
 * <p>
 * The module holds mapped superclasses, one per table and view, and an identifier class per composite key, which an
 * application extends with its own entities. The persistence and validation APIs appear in their public signatures,
 * and so do the nullness annotations of {@code jakarta.annotation}, which are not needed at run time.
 * <p>
 * The package is opened, not only exported, because a persistence provider and a validation provider reach the mapped
 * superclasses' private fields reflectively.
 */
module com.github.jinahya.mssql.sample.databases.persistence.adventureworks {

    requires static transitive jakarta.annotation;
    requires transitive jakarta.persistence;
    requires transitive jakarta.validation;

    exports com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

    opens com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;
}
