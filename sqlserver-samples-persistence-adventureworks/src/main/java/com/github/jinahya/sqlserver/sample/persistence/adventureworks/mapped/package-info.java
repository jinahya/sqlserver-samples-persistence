/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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
 * What the mapped superclasses of AdventureWorks, the classic OLTP sample database of SQL Server,
 * share across its schemas.
 * <p>
 * The mapped superclasses themselves are in a package per schema, {@code mapped.<schema>}, so that
 * each table and view is named within its schema, as the database names it.
 * <p>
 * No attribute is {@code @Column(unique = true)}. A unique key or index is declared by the entity
 * which extends a mapped superclass, as a
 * {@link jakarta.persistence.UniqueConstraint @UniqueConstraint} of its
 * {@link jakarta.persistence.Table @Table}, under the name the database gives it and with all its
 * columns; a mapped superclass has no {@code @Table}, and {@code unique = true} names no constraint
 * and takes one column only.
 */
package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped;
