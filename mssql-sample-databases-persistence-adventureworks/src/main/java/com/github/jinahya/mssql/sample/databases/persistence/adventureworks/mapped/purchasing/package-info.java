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
 * Mapped superclasses for the {@code Purchasing} schema of AdventureWorks, the classic OLTP sample database of SQL
 * Server, one per table and one per view, plus an identifier class per composite key.
 * <p>
 * Each holds what the database says about its table or view -- the names, the types, the lengths, the nullability, the
 * identity columns, the sequences and the columns the database computes -- and nothing an application decides: no
 * association, no fetch type, no cascade, no named query. A foreign-key column is mapped read-only here, as a basic
 * attribute; the extending entity owns its writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.purchasing;
