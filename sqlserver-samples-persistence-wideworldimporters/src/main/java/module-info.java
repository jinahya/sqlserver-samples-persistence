/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
 * Jakarta Persistence mappings for WideWorldImporters, the sample database of SQL Server 2016 and later.
 * <p>
 * The module holds mapped superclasses, one per table and view, and an identifier class per composite key, which an
 * application extends with its own entities. The persistence and validation APIs appear in their public signatures.
 * <p>
 * The packages are opened, not only exported, because a persistence provider and a validation provider reach the
 * mapped superclasses' private fields reflectively.
 */
module com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters {

    // the geography columns; the mapped superclasses return a Geography
    requires transitive com.github.jinahya.sqlserver.sample.databases.persistence.common;
    requires transitive jakarta.persistence;
    requires transitive jakarta.validation;

    exports com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.application;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.purchasing;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.sales;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.warehouse;
    exports com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.website;

    opens com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.application;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.purchasing;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.sales;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.warehouse;
    opens com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.website;
}
