package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedProductionCompletionSummary;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A plain class which holds a row of the {@code dbo.vw_ProductionCompletionSummary} view, which no entity can map: the
 * view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class ProductionCompletionSummary extends MappedProductionCompletionSummary {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected ProductionCompletionSummary() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param completionDate      the value of the {@value #COLUMN_NAME_COMPLETION_DATE} column.
     * @param year                the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month               the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param week                the value of the {@value #COLUMN_NAME_WEEK} column.
     * @param workCenterName      the value of the {@value #COLUMN_NAME_WORK_CENTER_NAME} column.
     * @param itemCode            the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName            the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param itemType            the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param numberOfCompletions the value of the {@value #COLUMN_NAME_NUMBER_OF_COMPLETIONS} column.
     * @param totalCompleted      the value of the {@value #COLUMN_NAME_TOTAL_COMPLETED} column.
     * @param totalScrapped       the value of the {@value #COLUMN_NAME_TOTAL_SCRAPPED} column.
     * @param scrapRate           the value of the {@value #COLUMN_NAME_SCRAP_RATE} column.
     * @param productionValue     the value of the {@value #COLUMN_NAME_PRODUCTION_VALUE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public ProductionCompletionSummary(final LocalDate completionDate,
                                           final Integer year,
                                           final Integer month,
                                           final Integer week,
                                           final String workCenterName,
                                           final String itemCode,
                                           final String itemName,
                                           final String itemType,
                                           final Integer numberOfCompletions,
                                           final BigDecimal totalCompleted,
                                           final BigDecimal totalScrapped,
                                           final BigDecimal scrapRate,
                                           final BigDecimal productionValue) {
        super();
        setCompletionDate(completionDate);
        setYear(year);
        setMonth(month);
        setWeek(week);
        setWorkCenterName(workCenterName);
        setItemCode(itemCode);
        setItemName(itemName);
        setItemType(itemType);
        setNumberOfCompletions(numberOfCompletions);
        setTotalCompleted(totalCompleted);
        setTotalScrapped(totalScrapped);
        setScrapRate(scrapRate);
        setProductionValue(productionValue);
    }
}

