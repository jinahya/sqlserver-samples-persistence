package com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.warehouse;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.warehouse.MappedColorArchive;

import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code Warehouse.Colors_Archive} table, which no entity can map: a history
 * table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ColorArchive extends MappedColorArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ColorArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param colorId      the value of the {@value #COLUMN_NAME_COLOR_ID} column.
     * @param colorName    the value of the {@value #COLUMN_NAME_COLOR_NAME} column.
     * @param lastEditedBy the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom    the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo      the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ColorArchive(final Integer colorId,
                        final String colorName,
                        final Integer lastEditedBy,
                        final LocalDateTime validFrom,
                        final LocalDateTime validTo) {
        super();
        setColorId(colorId);
        setColorName(colorName);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

