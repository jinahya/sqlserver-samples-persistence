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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedPriceList;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesChannel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.PriceList} table.
 */
@Entity(name = "DboPriceList")
@Table(schema = MappedPriceList.TABLE_SCHEMA,
       name = MappedPriceList.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               PriceList.COLUMN_NAME_PRICE_LIST_CODE
                       }
               )
       }
)
public class PriceList extends MappedPriceList {

    /**
     * The name of the attribute of the {@code FK_PriceList_SalesChannel} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL = "salesChannel";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PriceList() {
        super();
    }

    // -------------------------------------------------------------------------------- salesChannel

    /**
     * Returns the {@code SalesChannel} which the {@code FK_PriceList_SalesChannel} foreign key references.
     *
     * @return the {@code SalesChannel} referenced; {@code null} when none is.
     */
    public SalesChannel getSalesChannel() {
        return salesChannel;
    }

    /**
     * Replaces the {@code SalesChannel} which the {@code FK_PriceList_SalesChannel} foreign key references, and the
     * value of the column it consists of.
     *
     * @param salesChannel the {@code SalesChannel} to reference; {@code null} for none.
     */
    public void setSalesChannel(final SalesChannel salesChannel) {
        this.salesChannel = salesChannel;
        setSalesChannelId(salesChannel == null ? null : salesChannel.getSalesChannelId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_CHANNEL_ID,
                referencedColumnName = MappedSalesChannel.COLUMN_NAME_SALES_CHANNEL_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesChannel salesChannel;
}

