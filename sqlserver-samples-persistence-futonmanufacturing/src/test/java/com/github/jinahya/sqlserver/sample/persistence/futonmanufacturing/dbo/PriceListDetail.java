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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedItem;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedPriceList;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedPriceListDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.PriceListDetail} table.
 */
@Entity(name = "DboPriceListDetail")
@Table(schema = MappedPriceListDetail.TABLE_SCHEMA, name = MappedPriceListDetail.TABLE_NAME)
public class PriceListDetail extends MappedPriceListDetail {

    /**
     * The name of the attribute of the {@code FK_PriceListDetail_PriceList} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRICE_LIST = "priceList";

    /**
     * The name of the attribute of the {@code FK_PriceListDetail_Item} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM = "item";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PriceListDetail() {
        super();
    }

    // ----------------------------------------------------------------------------------- priceList

    /**
     * Returns the {@code PriceList} which the {@code FK_PriceListDetail_PriceList} foreign key references.
     *
     * @return the {@code PriceList} referenced; {@code null} when none is.
     */
    public PriceList getPriceList() {
        return priceList;
    }

    /**
     * Replaces the {@code PriceList} which the {@code FK_PriceListDetail_PriceList} foreign key references, and the
     * value of the column it consists of.
     *
     * @param priceList the {@code PriceList} to reference; {@code null} for none.
     */
    public void setPriceList(final PriceList priceList) {
        this.priceList = priceList;
        setPriceListId(priceList == null ? null : priceList.getPriceListId());
    }

    // ---------------------------------------------------------------------------------------- item

    /**
     * Returns the {@code Item} which the {@code FK_PriceListDetail_Item} foreign key references.
     *
     * @return the {@code Item} referenced; {@code null} when none is.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Replaces the {@code Item} which the {@code FK_PriceListDetail_Item} foreign key references, and the value of the
     * column it consists of.
     *
     * @param item the {@code Item} to reference; {@code null} for none.
     */
    public void setItem(final Item item) {
        this.item = item;
        setItemId(item == null ? null : item.getItemId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRICE_LIST_ID,
                referencedColumnName = MappedPriceList.COLUMN_NAME_PRICE_LIST_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private PriceList priceList;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ITEM_ID,
                referencedColumnName = MappedItem.COLUMN_NAME_ITEM_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Item item;
}

