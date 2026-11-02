package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedRawMaterialUsageView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_RawMaterialUsage} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class RawMaterialUsageView extends MappedRawMaterialUsageView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected RawMaterialUsageView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param year             the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month            the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param quarter          the value of the {@value #COLUMN_NAME_QUARTER} column.
     * @param itemType         the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param itemCode         the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName         the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param unitCode         the value of the {@value #COLUMN_NAME_UNIT_CODE} column.
     * @param totalUsage       the value of the {@value #COLUMN_NAME_TOTAL_USAGE} column.
     * @param totalReceipts    the value of the {@value #COLUMN_NAME_TOTAL_RECEIPTS} column.
     * @param numberOfIssues   the value of the {@value #COLUMN_NAME_NUMBER_OF_ISSUES} column.
     * @param avgIssueQuantity the value of the {@value #COLUMN_NAME_AVG_ISSUE_QUANTITY} column.
     * @param totalUsageValue  the value of the {@value #COLUMN_NAME_TOTAL_USAGE_VALUE} column.
     * @param avgUnitCost      the value of the {@value #COLUMN_NAME_AVG_UNIT_COST} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public RawMaterialUsageView(final Integer year,
                                final Integer month,
                                final Integer quarter,
                                final String itemType,
                                final String itemCode,
                                final String itemName,
                                final String unitCode,
                                final BigDecimal totalUsage,
                                final BigDecimal totalReceipts,
                                final Integer numberOfIssues,
                                final BigDecimal avgIssueQuantity,
                                final BigDecimal totalUsageValue,
                                final BigDecimal avgUnitCost) {
        super();
        setYear(year);
        setMonth(month);
        setQuarter(quarter);
        setItemType(itemType);
        setItemCode(itemCode);
        setItemName(itemName);
        setUnitCode(unitCode);
        setTotalUsage(totalUsage);
        setTotalReceipts(totalReceipts);
        setNumberOfIssues(numberOfIssues);
        setAvgIssueQuantity(avgIssueQuantity);
        setTotalUsageValue(totalUsageValue);
        setAvgUnitCost(avgUnitCost);
    }
}

