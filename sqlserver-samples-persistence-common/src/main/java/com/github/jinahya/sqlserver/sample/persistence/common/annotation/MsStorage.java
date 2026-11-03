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
 * Documents where and how the database stores the table the annotated class maps: its filegroup or
 * partition scheme, and its data compression.
 * <p>
 * Jakarta Persistence has no annotation for storage; {@link jakarta.persistence.Table#options()
 * options} only carries DDL text.
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/databases/database-files-and-filegroups">
 *      Database files and filegroups</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/partitions/partitioned-tables-and-indexes">
 *      Partitioned tables and indexes</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/relational-databases/data-compression/data-compression">
 *      Data compression</a>
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface MsStorage {

    /**
     * The filegroup the table is on, {@code USERDATA} say; empty if the table is partitioned or on
     * the default filegroup.
     *
     * @return the filegroup the table is on, {@code USERDATA} say; empty if the table is
     *         partitioned or on the default filegroup.
     */
    String filegroup() default "";

    /**
     * The partition scheme the table is on, {@code PS_TransactionDate} say; empty if the table is
     * not partitioned.
     *
     * @return the partition scheme the table is on, {@code PS_TransactionDate} say; empty if the
     *         table is not partitioned.
     */
    String partitionScheme() default "";

    /**
     * The column the table is partitioned by; empty if the table is not partitioned.
     *
     * @return the column the table is partitioned by; empty if the table is not partitioned.
     */
    String partitionColumn() default "";

    /**
     * The data compression of the table, {@code PAGE} say; empty if none.
     *
     * @return the data compression of the table, {@code PAGE} say; empty if none.
     */
    String dataCompression() default "";
}
