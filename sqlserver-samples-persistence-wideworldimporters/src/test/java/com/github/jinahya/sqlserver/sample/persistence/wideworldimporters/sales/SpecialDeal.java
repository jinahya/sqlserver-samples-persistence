package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.application.Person;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.application.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedBuyingGroup;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedCustomerCategory;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales.MappedSpecialDeal;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockGroup;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse.MappedStockItem;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockGroup;
import com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.warehouse.StockItem;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.SpecialDeals} table.
 */
@Entity(name = "SalesSpecialDeal")
@Table(schema = MappedSpecialDeal.TABLE_SCHEMA,
       name = MappedSpecialDeal.TABLE_NAME,
       comment = "Special pricing (can include fixed prices, discount $ or discount %)",
       check = {
               @CheckConstraint(
                       name = "CK_Sales_SpecialDeals_Exactly_One_NOT_NULL_Pricing_Option_Is_Required",
                       constraint = "(((case when [DiscountAmount] IS NULL then (0) else (1) "
                               + "end+case when [DiscountPercentage] IS NULL then (0) else (1) "
                               + "end)+case when [UnitPrice] IS NULL then (0) else (1) end)=(1))"
               ),
               @CheckConstraint(
                       name = "CK_Sales_SpecialDeals_Unit_Price_Deal_Requires_Special_StockItem",
                       constraint = "([StockItemID] IS NOT NULL AND [UnitPrice] IS NOT NULL OR "
                               + "[UnitPrice] IS NULL)"
               )
       }
)
public class SpecialDeal extends MappedSpecialDeal {

    /**
     * The name of the attribute of the {@code FK_Sales_SpecialDeals_StockItemID_Warehouse_StockItems} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM = "stockItem";

    /**
     * The name of the attribute of the {@code FK_Sales_SpecialDeals_CustomerID_Sales_Customers} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    /**
     * The name of the attribute of the {@code FK_Sales_SpecialDeals_BuyingGroupID_Sales_BuyingGroups} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP = "buyingGroup";

    /**
     * The name of the attribute of the {@code FK_Sales_SpecialDeals_CustomerCategoryID_Sales_CustomerCategories}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY = "customerCategory";

    /**
     * The name of the attribute of the {@code FK_Sales_SpecialDeals_StockGroupID_Warehouse_StockGroups} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP = "stockGroup";

    /**
     * The name of the attribute of the {@code FK_Sales_SpecialDeals_Application_People} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY_PERSON = "lastEditedByPerson";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SpecialDeal() {
        super();
    }

    // ----------------------------------------------------------------------------------- stockItem

    /**
     * Returns the {@code StockItem} which the {@code FK_Sales_SpecialDeals_StockItemID_Warehouse_StockItems} foreign
     * key references.
     *
     * @return the {@code StockItem} referenced; {@code null} when none is.
     */
    public StockItem getStockItem() {
        return stockItem;
    }

    /**
     * Replaces the {@code StockItem} which the {@code FK_Sales_SpecialDeals_StockItemID_Warehouse_StockItems} foreign
     * key references, and the value of the column it consists of.
     *
     * @param stockItem the {@code StockItem} to reference; {@code null} for none.
     */
    public void setStockItem(final StockItem stockItem) {
        this.stockItem = stockItem;
        setStockItemId(stockItem == null ? null : stockItem.getStockItemId());
    }

    // ------------------------------------------------------------------------------------ customer

    /**
     * Returns the {@code Customer} which the {@code FK_Sales_SpecialDeals_CustomerID_Sales_Customers} foreign key
     * references.
     *
     * @return the {@code Customer} referenced; {@code null} when none is.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Replaces the {@code Customer} which the {@code FK_Sales_SpecialDeals_CustomerID_Sales_Customers} foreign key
     * references, and the value of the column it consists of.
     *
     * @param customer the {@code Customer} to reference; {@code null} for none.
     */
    public void setCustomer(final Customer customer) {
        this.customer = customer;
        setCustomerId(customer == null ? null : customer.getCustomerId());
    }

    // --------------------------------------------------------------------------------- buyingGroup

    /**
     * Returns the {@code BuyingGroup} which the {@code FK_Sales_SpecialDeals_BuyingGroupID_Sales_BuyingGroups} foreign
     * key references.
     *
     * @return the {@code BuyingGroup} referenced; {@code null} when none is.
     */
    public BuyingGroup getBuyingGroup() {
        return buyingGroup;
    }

    /**
     * Replaces the {@code BuyingGroup} which the {@code FK_Sales_SpecialDeals_BuyingGroupID_Sales_BuyingGroups} foreign
     * key references, and the value of the column it consists of.
     *
     * @param buyingGroup the {@code BuyingGroup} to reference; {@code null} for none.
     */
    public void setBuyingGroup(final BuyingGroup buyingGroup) {
        this.buyingGroup = buyingGroup;
        setBuyingGroupId(buyingGroup == null ? null : buyingGroup.getBuyingGroupId());
    }

    // ---------------------------------------------------------------------------- customerCategory

    /**
     * Returns the {@code CustomerCategory} which the
     * {@code FK_Sales_SpecialDeals_CustomerCategoryID_Sales_CustomerCategories} foreign key references.
     *
     * @return the {@code CustomerCategory} referenced; {@code null} when none is.
     */
    public CustomerCategory getCustomerCategory() {
        return customerCategory;
    }

    /**
     * Replaces the {@code CustomerCategory} which the
     * {@code FK_Sales_SpecialDeals_CustomerCategoryID_Sales_CustomerCategories} foreign key references, and the value
     * of the column it consists of.
     *
     * @param customerCategory the {@code CustomerCategory} to reference; {@code null} for none.
     */
    public void setCustomerCategory(final CustomerCategory customerCategory) {
        this.customerCategory = customerCategory;
        setCustomerCategoryId(customerCategory == null ? null : customerCategory.getCustomerCategoryId());
    }

    // ---------------------------------------------------------------------------------- stockGroup

    /**
     * Returns the {@code StockGroup} which the {@code FK_Sales_SpecialDeals_StockGroupID_Warehouse_StockGroups} foreign
     * key references.
     *
     * @return the {@code StockGroup} referenced; {@code null} when none is.
     */
    public StockGroup getStockGroup() {
        return stockGroup;
    }

    /**
     * Replaces the {@code StockGroup} which the {@code FK_Sales_SpecialDeals_StockGroupID_Warehouse_StockGroups}
     * foreign key references, and the value of the column it consists of.
     *
     * @param stockGroup the {@code StockGroup} to reference; {@code null} for none.
     */
    public void setStockGroup(final StockGroup stockGroup) {
        this.stockGroup = stockGroup;
        setStockGroupId(stockGroup == null ? null : stockGroup.getStockGroupId());
    }

    // -------------------------------------------------------------------------- lastEditedByPerson

    /**
     * Returns the {@code Person} which the {@code FK_Sales_SpecialDeals_Application_People} foreign key references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getLastEditedByPerson() {
        return lastEditedByPerson;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Sales_SpecialDeals_Application_People} foreign key references,
     * and the value of the column it consists of.
     *
     * @param lastEditedByPerson the {@code Person} to reference; {@code null} for none.
     */
    public void setLastEditedByPerson(final Person lastEditedByPerson) {
        this.lastEditedByPerson = lastEditedByPerson;
        setLastEditedBy(lastEditedByPerson == null ? null : lastEditedByPerson.getPersonId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STOCK_ITEM_ID,
                referencedColumnName = MappedStockItem.COLUMN_NAME_STOCK_ITEM_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private StockItem stockItem;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID,
                referencedColumnName = MappedCustomer.COLUMN_NAME_CUSTOMER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Customer customer;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUYING_GROUP_ID,
                referencedColumnName = MappedBuyingGroup.COLUMN_NAME_BUYING_GROUP_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private BuyingGroup buyingGroup;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID,
                referencedColumnName = MappedCustomerCategory.COLUMN_NAME_CUSTOMER_CATEGORY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private CustomerCategory customerCategory;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STOCK_GROUP_ID,
                referencedColumnName = MappedStockGroup.COLUMN_NAME_STOCK_GROUP_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private StockGroup stockGroup;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LAST_EDITED_BY,
                referencedColumnName = MappedPerson.COLUMN_NAME_PERSON_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Person lastEditedByPerson;
}

