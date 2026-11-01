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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.MappedBomExplosionView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_BOMExplosion} view, which no entity can map: the view declares
 * no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class BomExplosionView extends MappedBomExplosionView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected BomExplosionView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param parentItemId the value of the {@value #COLUMN_NAME_PARENT_ITEM_ID} column.
     * @param parentItemCode the value of the {@value #COLUMN_NAME_PARENT_ITEM_CODE} column.
     * @param parentItemName the value of the {@value #COLUMN_NAME_PARENT_ITEM_NAME} column.
     * @param componentItemId the value of the {@value #COLUMN_NAME_COMPONENT_ITEM_ID} column.
     * @param componentItemCode the value of the {@value #COLUMN_NAME_COMPONENT_ITEM_CODE} column.
     * @param componentItemName the value of the {@value #COLUMN_NAME_COMPONENT_ITEM_NAME} column.
     * @param componentType the value of the {@value #COLUMN_NAME_COMPONENT_TYPE} column.
     * @param level the value of the {@value #COLUMN_NAME_LEVEL} column.
     * @param effectiveQuantity the value of the {@value #COLUMN_NAME_EFFECTIVE_QUANTITY} column.
     * @param unitCode the value of the {@value #COLUMN_NAME_UNIT_CODE} column.
     * @param standardCost the value of the {@value #COLUMN_NAME_STANDARD_COST} column.
     * @param extendedCost the value of the {@value #COLUMN_NAME_EXTENDED_COST} column.
     * @param bomPath the value of the {@value #COLUMN_NAME_BOM_PATH} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public BomExplosionView(final Integer parentItemId,
                            final String parentItemCode,
                            final String parentItemName,
                            final Integer componentItemId,
                            final String componentItemCode,
                            final String componentItemName,
                            final String componentType,
                            final Integer level,
                            final BigDecimal effectiveQuantity,
                            final String unitCode,
                            final BigDecimal standardCost,
                            final BigDecimal extendedCost,
                            final String bomPath) {
        super();
        setParentItemId(parentItemId);
        setParentItemCode(parentItemCode);
        setParentItemName(parentItemName);
        setComponentItemId(componentItemId);
        setComponentItemCode(componentItemCode);
        setComponentItemName(componentItemName);
        setComponentType(componentType);
        setLevel(level);
        setEffectiveQuantity(effectiveQuantity);
        setUnitCode(unitCode);
        setStandardCost(standardCost);
        setExtendedCost(extendedCost);
        setBomPath(bomPath);
    }
}

