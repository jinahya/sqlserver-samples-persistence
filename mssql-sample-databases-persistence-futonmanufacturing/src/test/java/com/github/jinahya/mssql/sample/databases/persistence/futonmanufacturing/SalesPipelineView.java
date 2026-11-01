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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.MappedSalesPipelineView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_Pipeline} view, which no entity can map: the view
 * declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesPipelineView extends MappedSalesPipelineView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesPipelineView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param stage the value of the {@value #COLUMN_NAME_STAGE} column.
     * @param stageOrder the value of the {@value #COLUMN_NAME_STAGE_ORDER} column.
     * @param count the value of the {@value #COLUMN_NAME_COUNT} column.
     * @param value the value of the {@value #COLUMN_NAME_VALUE} column.
     * @param priorStageCount the value of the {@value #COLUMN_NAME_PRIOR_STAGE_COUNT} column.
     * @param priorStageValue the value of the {@value #COLUMN_NAME_PRIOR_STAGE_VALUE} column.
     * @param conversionRate the value of the {@value #COLUMN_NAME_CONVERSION_RATE} column.
     * @param valueRetentionRate the value of the {@value #COLUMN_NAME_VALUE_RETENTION_RATE} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesPipelineView(final String stage,
                             final Integer stageOrder,
                             final Integer count,
                             final BigDecimal value,
                             final Integer priorStageCount,
                             final BigDecimal priorStageValue,
                             final BigDecimal conversionRate,
                             final BigDecimal valueRetentionRate) {
        super();
        setStage(stage);
        setStageOrder(stageOrder);
        setCount(count);
        setValue(value);
        setPriorStageCount(priorStageCount);
        setPriorStageValue(priorStageValue);
        setConversionRate(conversionRate);
        setValueRetentionRate(valueRetentionRate);
    }
}

