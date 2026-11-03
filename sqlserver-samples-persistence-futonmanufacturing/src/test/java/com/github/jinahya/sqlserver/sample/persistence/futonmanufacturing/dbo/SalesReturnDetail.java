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
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesOrderDetail;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesReturn;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesReturnDetail;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.SalesReturnDetail} table.
 */
@Entity(name = "DboSalesReturnDetail")
@Table(schema = MappedSalesReturnDetail.TABLE_SCHEMA, name = MappedSalesReturnDetail.TABLE_NAME)
public class SalesReturnDetail extends MappedSalesReturnDetail {

    /**
     * The name of the attribute of the {@code FK_ReturnDetail_Return} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_SALES_RETURN = "returnSalesReturn";

    /**
     * The name of the attribute of the {@code FK_ReturnDetail_SODetail} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SO_DETAIL = "soDetail";

    /**
     * The name of the attribute of the {@code FK_ReturnDetail_Item} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM = "item";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesReturnDetail() {
        super();
    }

    // --------------------------------------------------------------------------- returnSalesReturn

    /**
     * Returns the {@code SalesReturn} which the {@code FK_ReturnDetail_Return} foreign key references.
     *
     * @return the {@code SalesReturn} referenced; {@code null} when none is.
     */
    public SalesReturn getReturnSalesReturn() {
        return returnSalesReturn;
    }

    /**
     * Replaces the {@code SalesReturn} which the {@code FK_ReturnDetail_Return} foreign key references, and the value
     * of the column it consists of.
     *
     * @param returnSalesReturn the {@code SalesReturn} to reference; {@code null} for none.
     */
    public void setReturnSalesReturn(final SalesReturn returnSalesReturn) {
        this.returnSalesReturn = returnSalesReturn;
        setReturnId(returnSalesReturn == null ? null : returnSalesReturn.getReturnId());
    }

    // ------------------------------------------------------------------------------------ soDetail

    /**
     * Returns the {@code SalesOrderDetail} which the {@code FK_ReturnDetail_SODetail} foreign key references.
     *
     * @return the {@code SalesOrderDetail} referenced; {@code null} when none is.
     */
    public SalesOrderDetail getSoDetail() {
        return soDetail;
    }

    /**
     * Replaces the {@code SalesOrderDetail} which the {@code FK_ReturnDetail_SODetail} foreign key references, and the
     * value of the column it consists of.
     *
     * @param soDetail the {@code SalesOrderDetail} to reference; {@code null} for none.
     */
    public void setSoDetail(final SalesOrderDetail soDetail) {
        this.soDetail = soDetail;
        setSoDetailId(soDetail == null ? null : soDetail.getSoDetailId());
    }

    // ---------------------------------------------------------------------------------------- item

    /**
     * Returns the {@code Item} which the {@code FK_ReturnDetail_Item} foreign key references.
     *
     * @return the {@code Item} referenced; {@code null} when none is.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Replaces the {@code Item} which the {@code FK_ReturnDetail_Item} foreign key references, and the value of the
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
    @JoinColumn(name = COLUMN_NAME_RETURN_ID,
                referencedColumnName = MappedSalesReturn.COLUMN_NAME_RETURN_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private SalesReturn returnSalesReturn;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SO_DETAIL_ID,
                referencedColumnName = MappedSalesOrderDetail.COLUMN_NAME_SO_DETAIL_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private SalesOrderDetail soDetail;

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

