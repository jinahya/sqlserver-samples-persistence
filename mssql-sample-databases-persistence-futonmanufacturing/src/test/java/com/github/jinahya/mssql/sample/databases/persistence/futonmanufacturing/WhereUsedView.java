package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.MappedWhereUsedView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_WhereUsed} view, which no entity can map: the view declares no
 * key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class WhereUsedView extends MappedWhereUsedView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected WhereUsedView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param componentItemId the value of the {@value #COLUMN_NAME_COMPONENT_ITEM_ID} column.
     * @param componentItemCode the value of the {@value #COLUMN_NAME_COMPONENT_ITEM_CODE} column.
     * @param componentItemName the value of the {@value #COLUMN_NAME_COMPONENT_ITEM_NAME} column.
     * @param parentItemId the value of the {@value #COLUMN_NAME_PARENT_ITEM_ID} column.
     * @param parentItemCode the value of the {@value #COLUMN_NAME_PARENT_ITEM_CODE} column.
     * @param parentItemName the value of the {@value #COLUMN_NAME_PARENT_ITEM_NAME} column.
     * @param parentType the value of the {@value #COLUMN_NAME_PARENT_TYPE} column.
     * @param level the value of the {@value #COLUMN_NAME_LEVEL} column.
     * @param quantity the value of the {@value #COLUMN_NAME_QUANTITY} column.
     * @param unitCode the value of the {@value #COLUMN_NAME_UNIT_CODE} column.
     * @param usagePath the value of the {@value #COLUMN_NAME_USAGE_PATH} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public WhereUsedView(final Integer componentItemId,
                         final String componentItemCode,
                         final String componentItemName,
                         final Integer parentItemId,
                         final String parentItemCode,
                         final String parentItemName,
                         final String parentType,
                         final Integer level,
                         final BigDecimal quantity,
                         final String unitCode,
                         final String usagePath) {
        super();
        setComponentItemId(componentItemId);
        setComponentItemCode(componentItemCode);
        setComponentItemName(componentItemName);
        setParentItemId(parentItemId);
        setParentItemCode(parentItemCode);
        setParentItemName(parentItemName);
        setParentType(parentType);
        setLevel(level);
        setQuantity(quantity);
        setUnitCode(unitCode);
        setUsagePath(usagePath);
    }
}

