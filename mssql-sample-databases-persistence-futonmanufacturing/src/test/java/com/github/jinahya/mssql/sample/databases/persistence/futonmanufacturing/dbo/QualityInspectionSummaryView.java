package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.dbo;

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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedQualityInspectionSummaryView;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A plain class which holds a row of the {@code dbo.vw_QualityInspectionSummary} view, which no entity can map: the
 * view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class QualityInspectionSummaryView extends MappedQualityInspectionSummaryView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected QualityInspectionSummaryView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param inspectionDate      the value of the {@value #COLUMN_NAME_INSPECTION_DATE} column.
     * @param year                the value of the {@value #COLUMN_NAME_YEAR} column.
     * @param month               the value of the {@value #COLUMN_NAME_MONTH} column.
     * @param inspectionType      the value of the {@value #COLUMN_NAME_INSPECTION_TYPE} column.
     * @param itemCode            the value of the {@value #COLUMN_NAME_ITEM_CODE} column.
     * @param itemName            the value of the {@value #COLUMN_NAME_ITEM_NAME} column.
     * @param itemType            the value of the {@value #COLUMN_NAME_ITEM_TYPE} column.
     * @param numberOfInspections the value of the {@value #COLUMN_NAME_NUMBER_OF_INSPECTIONS} column.
     * @param totalInspected      the value of the {@value #COLUMN_NAME_TOTAL_INSPECTED} column.
     * @param totalAccepted       the value of the {@value #COLUMN_NAME_TOTAL_ACCEPTED} column.
     * @param totalRejected       the value of the {@value #COLUMN_NAME_TOTAL_REJECTED} column.
     * @param acceptanceRate      the value of the {@value #COLUMN_NAME_ACCEPTANCE_RATE} column.
     * @param rejectionRate       the value of the {@value #COLUMN_NAME_REJECTION_RATE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public QualityInspectionSummaryView(final LocalDate inspectionDate,
                                        final Integer year,
                                        final Integer month,
                                        final String inspectionType,
                                        final String itemCode,
                                        final String itemName,
                                        final String itemType,
                                        final Integer numberOfInspections,
                                        final BigDecimal totalInspected,
                                        final BigDecimal totalAccepted,
                                        final BigDecimal totalRejected,
                                        final BigDecimal acceptanceRate,
                                        final BigDecimal rejectionRate) {
        super();
        setInspectionDate(inspectionDate);
        setYear(year);
        setMonth(month);
        setInspectionType(inspectionType);
        setItemCode(itemCode);
        setItemName(itemName);
        setItemType(itemType);
        setNumberOfInspections(numberOfInspections);
        setTotalInspected(totalInspected);
        setTotalAccepted(totalAccepted);
        setTotalRejected(totalRejected);
        setAcceptanceRate(acceptanceRate);
        setRejectionRate(rejectionRate);
    }
}

