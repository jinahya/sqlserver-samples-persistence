package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SpecialDeals} table.
 * <p>
 * The {@value #COLUMN_NAME_STOCK_ITEM_ID}, {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_BUYING_GROUP_ID},
 * {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID}, {@value #COLUMN_NAME_STOCK_GROUP_ID},
 * {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.SpecialDeals}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SpecialDealID}</th>
 *     <td>ID (sequence based) for a special deal</td></tr>
 * <tr><th scope="row">{@code StockItemID}</th>
 *     <td>Stock item that the deal applies to (if NULL, then only discounts are permitted not unit
 *     prices)</td></tr>
 * <tr><th scope="row">{@code CustomerID}</th>
 *     <td>ID of the customer that the special pricing applies to (if NULL then all
 *     customers)</td></tr>
 * <tr><th scope="row">{@code BuyingGroupID}</th>
 *     <td>ID of the buying group that the special pricing applies to (optional)</td></tr>
 * <tr><th scope="row">{@code CustomerCategoryID}</th>
 *     <td>ID of the customer category that the special pricing applies to (optional)</td></tr>
 * <tr><th scope="row">{@code StockGroupID}</th>
 *     <td>ID of the stock group that the special pricing applies to (optional)</td></tr>
 * <tr><th scope="row">{@code DealDescription}</th><td>Description of the special deal</td></tr>
 * <tr><th scope="row">{@code StartDate}</th><td>Date that the special pricing starts from</td></tr>
 * <tr><th scope="row">{@code EndDate}</th><td>Date that the special pricing ends on</td></tr>
 * <tr><th scope="row">{@code DiscountAmount}</th>
 *     <td>Discount per unit to be applied to sale price (optional)</td></tr>
 * <tr><th scope="row">{@code DiscountPercentage}</th>
 *     <td>Discount percentage per unit to be applied to sale price (optional)</td></tr>
 * <tr><th scope="row">{@code UnitPrice}</th>
 *     <td>Special price per unit to be applied instead of sale price (optional)</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.SpecialDeals}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SpecialDealID}</th><td>{@code (NEXT VALUE FOR [Sequences].[SpecialDealID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedSpecialDeal.TABLE_SCHEMA,
        table = MappedSpecialDeal.TABLE_NAME,
        value = "Special pricing (can include fixed prices, discount $ or discount %)"
)
public abstract class MappedSpecialDeal {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SpecialDeals";

    // ------------------------------------------------------------------------------- SpecialDealID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.SpecialDealID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIAL_DEAL_ID = "SpecialDealID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIAL_DEAL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_DEAL_ID = "specialDealId";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ------------------------------------------------------------------------------- BuyingGroupID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUYING_GROUP_ID = "BuyingGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUYING_GROUP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP_ID = "buyingGroupId";

    // -------------------------------------------------------------------------- CustomerCategoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CATEGORY_ID = "CustomerCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID = "customerCategoryId";

    // -------------------------------------------------------------------------------- StockGroupID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_GROUP_ID = "StockGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_GROUP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP_ID = "stockGroupId";

    // ----------------------------------------------------------------------------- DealDescription

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEAL_DESCRIPTION = "DealDescription";

    /**
     * The length of the {@value #COLUMN_NAME_DEAL_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DEAL_DESCRIPTION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEAL_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEAL_DESCRIPTION = "dealDescription";

    // ----------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // ------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ------------------------------------------------------------------------------ DiscountAmount

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_AMOUNT = "DiscountAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_AMOUNT = "discountAmount";

    // -------------------------------------------------------------------------- DiscountPercentage

    /**
     * The name of the {@code decimal(18, 3)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_PERCENTAGE = "DiscountPercentage";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_PERCENTAGE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_PERCENTAGE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_PERCENTAGE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_PERCENTAGE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PERCENTAGE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE = "discountPercentage";

    // ----------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------ LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSpecialDeal() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "specialDealId=" + specialDealId
               + ",stockItemId=" + stockItemId
               + ",customerId=" + customerId
               + ",buyingGroupId=" + buyingGroupId
               + ",customerCategoryId=" + customerCategoryId
               + ",stockGroupId=" + stockGroupId
               + ",dealDescription=" + dealDescription
               + ",startDate=" + startDate
               + ",endDate=" + endDate
               + ",discountAmount=" + discountAmount
               + ",discountPercentage=" + discountPercentage
               + ",unitPrice=" + unitPrice
               + ",lastEditedBy=" + lastEditedBy
               + ",lastEditedWhen=" + lastEditedWhen
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedSpecialDeal that)) {
            return false;
        }
        return Objects.equals(specialDealId, that.specialDealId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(specialDealId);
    }

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether exactly one of the {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT},
     * {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} and {@value #ATTRIBUTE_NAME_UNIT_PRICE} attributes is not
     * {@code null}, as the table's {@code CK_Sales_SpecialDeals_Exactly_One_NOT_NULL_Pricing_Option_Is_Required}
     * constraint requires.
     * {@snippet lang = "sql":
     * (((case when [DiscountAmount] IS NULL then (0) else (1) end+case when [DiscountPercentage] IS NULL then (0) else (1) end)+case when [UnitPrice] IS NULL then (0) else (1) end)=(1))
     *}
     *
     * @return {@code true} if exactly one of the {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT},
     *         {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} and {@value #ATTRIBUTE_NAME_UNIT_PRICE} attributes is not
     *         {@code null}; {@code false} otherwise.
     */
    @AssertTrue(message = "exactly one of discountAmount, discountPercentage and unitPrice must not be null")
    protected boolean isPricingOptionValid() {
        return (discountAmount == null ? 0 : 1)
               + (discountPercentage == null ? 0 : 1)
               + (unitPrice == null ? 0 : 1)
               == 1;
    }

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute is {@code null}, or the
     * {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute is not {@code null}, as the table's
     * {@code CK_Sales_SpecialDeals_Unit_Price_Deal_Requires_Special_StockItem} constraint requires.
     * {@snippet lang = "sql":
     * ([StockItemID] IS NOT NULL AND [UnitPrice] IS NOT NULL OR [UnitPrice] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute is not {@code null}; {@code false} otherwise.
     */
    @AssertTrue(message = "unitPrice must be null unless stockItemId is not null")
    protected boolean isUnitPriceValid() {
        return unitPrice == null || stockItemId != null;
    }

    // ------------------------------------------------------------------------------- specialDealId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute.
     */
    public Integer getSpecialDealId() {
        return specialDealId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute with the specified value.
     *
     * @param specialDealId new value for {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute.
     */
    protected void setSpecialDealId(final Integer specialDealId) {
        this.specialDealId = specialDealId;
    }

    // --------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(final Integer stockItemId) {
        this.stockItemId = stockItemId;
    }

    // ---------------------------------------------------------------------------------- customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ------------------------------------------------------------------------------- buyingGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    protected Integer getBuyingGroupId() {
        return buyingGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param buyingGroupId new value for {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    protected void setBuyingGroupId(final Integer buyingGroupId) {
        this.buyingGroupId = buyingGroupId;
    }

    // -------------------------------------------------------------------------- customerCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    protected Integer getCustomerCategoryId() {
        return customerCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param customerCategoryId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    protected void setCustomerCategoryId(final Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    // -------------------------------------------------------------------------------- stockGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    protected Integer getStockGroupId() {
        return stockGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockGroupId new value for {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    protected void setStockGroupId(final Integer stockGroupId) {
        this.stockGroupId = stockGroupId;
    }

    // ----------------------------------------------------------------------------- dealDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute.
     */
    public String getDealDescription() {
        return dealDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute with the specified value.
     *
     * @param dealDescription new value for {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute.
     */
    public void setDealDescription(final String dealDescription) {
        this.dealDescription = dealDescription;
    }

    // ----------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    // ------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    // ------------------------------------------------------------------------------ discountAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute with the specified value.
     *
     * @param discountAmount new value for {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    public void setDiscountAmount(final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    // -------------------------------------------------------------------------- discountPercentage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute.
     */
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute with the specified value.
     *
     * @param discountPercentage new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute.
     */
    public void setDiscountPercentage(final BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    // ----------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------ lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.SpecialDealID", schema = "Sequences",
                       sequenceName = "SpecialDealID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.SpecialDealID")
    @Column(name = COLUMN_NAME_SPECIAL_DEAL_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SPECIAL_DEAL_ID,
                          value = "ID (sequence based) for a special deal")
    @MsDefault(name = "DF_Sales_SpecialDeals_SpecialDealID", value = "(NEXT VALUE FOR [Sequences].[SpecialDealID])")
    private Integer specialDealId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_ID,
                          value = "Stock item that the deal applies to (if NULL, then only "
                                  + "discounts are permitted not unit prices)")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_ID,
                          value = "ID of the customer that the special pricing applies to (if "
                                  + "NULL then all customers)")
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BUYING_GROUP_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_BUYING_GROUP_ID,
                          value = "ID of the buying group that the special pricing applies to "
                                  + "(optional)")
    private Integer buyingGroupId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID,
                          value = "ID of the customer category that the special pricing applies "
                                  + "to (optional)")
    private Integer customerCategoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STOCK_GROUP_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_GROUP_ID,
                          value = "ID of the stock group that the special pricing applies to "
                                  + "(optional)")
    private Integer stockGroupId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DEAL_DESCRIPTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DEAL_DESCRIPTION, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_DEAL_DESCRIPTION)
    @MsDescription.Column(name = COLUMN_NAME_DEAL_DESCRIPTION,
                          value = "Description of the special deal")
    private String dealDescription;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_START_DATE,
                          value = "Date that the special pricing starts from")
    private LocalDate startDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_END_DATE,
                          value = "Date that the special pricing ends on")
    private LocalDate endDate;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_AMOUNT - COLUMN_SCALE_DISCOUNT_AMOUNT,
            fraction = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_AMOUNT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_DISCOUNT_AMOUNT, scale = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @MsDescription.Column(name = COLUMN_NAME_DISCOUNT_AMOUNT,
                          value = "Discount per unit to be applied to sale price (optional)")
    private BigDecimal discountAmount;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_PERCENTAGE - COLUMN_SCALE_DISCOUNT_PERCENTAGE,
            fraction = COLUMN_SCALE_DISCOUNT_PERCENTAGE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENTAGE, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_DISCOUNT_PERCENTAGE, scale = COLUMN_SCALE_DISCOUNT_PERCENTAGE)
    @MsDescription.Column(name = COLUMN_NAME_DISCOUNT_PERCENTAGE,
                          value = "Discount percentage per unit to be applied to sale price "
                                  + "(optional)")
    private BigDecimal discountPercentage;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    @MsDescription.Column(name = COLUMN_NAME_UNIT_PRICE,
                          value = "Special price per unit to be applied instead of sale price "
                                  + "(optional)")
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = true, updatable = true)
    @MsDefault(name = "DF_Sales_SpecialDeals_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

