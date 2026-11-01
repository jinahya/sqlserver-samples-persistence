package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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

import com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped.MappedSupplierCategoryArchive;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code Purchasing.SupplierCategories_Archive} table, which no entity can map:
 * a history table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SupplierCategoryArchive extends MappedSupplierCategoryArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SupplierCategoryArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param supplierCategoryId the value of the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_ID} column.
     * @param supplierCategoryName the value of the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_NAME} column.
     * @param lastEditedBy the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SupplierCategoryArchive(final Integer supplierCategoryId,
                                   final String supplierCategoryName,
                                   final Integer lastEditedBy,
                                   final LocalDateTime validFrom,
                                   final LocalDateTime validTo) {
        super();
        setSupplierCategoryId(supplierCategoryId);
        setSupplierCategoryName(supplierCategoryName);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

