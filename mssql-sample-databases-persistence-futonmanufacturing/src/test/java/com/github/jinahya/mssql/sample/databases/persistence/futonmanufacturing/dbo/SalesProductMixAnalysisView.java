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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo.MappedSalesProductMixAnalysisView;

import java.math.BigDecimal;

/**
 * A plain class which holds a row of the {@code dbo.vw_Sales_ProductMixAnalysis} view, which no entity can map: the
 * view declares no key, and no identifier column of it is unique.
 * <p>
 * The public constructor takes every column, in the order the view declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class SalesProductMixAnalysisView extends MappedSalesProductMixAnalysisView {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected SalesProductMixAnalysisView() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param channelName    the value of the {@value #COLUMN_NAME_CHANNEL_NAME} column.
     * @param customerType   the value of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column.
     * @param productMix     the value of the {@value #COLUMN_NAME_PRODUCT_MIX} column.
     * @param uniqueProducts the value of the {@value #COLUMN_NAME_UNIQUE_PRODUCTS} column.
     * @param orderCount     the value of the {@value #COLUMN_NAME_ORDER_COUNT} column.
     * @param totalRevenue   the value of the {@value #COLUMN_NAME_TOTAL_REVENUE} column.
     * @param avgOrderValue  the value of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column.
     * @param avgUnits       the value of the {@value #COLUMN_NAME_AVG_UNITS} column.
     * @param popularityRank the value of the {@value #COLUMN_NAME_POPULARITY_RANK} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public SalesProductMixAnalysisView(final String channelName,
                                       final String customerType,
                                       final String productMix,
                                       final Integer uniqueProducts,
                                       final Integer orderCount,
                                       final BigDecimal totalRevenue,
                                       final BigDecimal avgOrderValue,
                                       final BigDecimal avgUnits,
                                       final Long popularityRank) {
        super();
        setChannelName(channelName);
        setCustomerType(customerType);
        setProductMix(productMix);
        setUniqueProducts(uniqueProducts);
        setOrderCount(orderCount);
        setTotalRevenue(totalRevenue);
        setAvgOrderValue(avgOrderValue);
        setAvgUnits(avgUnits);
        setPopularityRank(popularityRank);
    }
}

