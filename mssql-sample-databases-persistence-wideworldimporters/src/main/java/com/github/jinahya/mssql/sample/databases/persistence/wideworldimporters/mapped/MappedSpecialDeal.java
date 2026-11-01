package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
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
 * {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID}, {@value #COLUMN_NAME_STOCK_GROUP_ID}, {@value
 * #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSpecialDeal {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SpecialDeals";

    // --------------------------------------------------------------------------------------------------- SpecialDealID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.SpecialDealID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIAL_DEAL_ID = "SpecialDealID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIAL_DEAL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_DEAL_ID = "specialDealId";

    // ----------------------------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------------------------------- BuyingGroupID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUYING_GROUP_ID = "BuyingGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUYING_GROUP_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUYING_GROUP_ID = "buyingGroupId";

    // ---------------------------------------------------------------------------------------------- CustomerCategoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CATEGORY_ID = "CustomerCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID = "customerCategoryId";

    // ---------------------------------------------------------------------------------------------------- StockGroupID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_GROUP_ID = "StockGroupID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_GROUP_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_GROUP_ID = "stockGroupId";

    // ------------------------------------------------------------------------------------------------- DealDescription

    /**
     * The name of the {@code nvarchar(30), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DEAL_DESCRIPTION = "DealDescription";

    /**
     * The length of the {@value #COLUMN_NAME_DEAL_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DEAL_DESCRIPTION = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DEAL_DESCRIPTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DEAL_DESCRIPTION = "dealDescription";

    // ------------------------------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // --------------------------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // -------------------------------------------------------------------------------------------------- DiscountAmount

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_AMOUNT = "discountAmount";

    // ---------------------------------------------------------------------------------------------- DiscountPercentage

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

    // ------------------------------------------------------------------------------------------------------- UnitPrice

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // -------------------------------------------------------------------------------------------------- LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSpecialDeal() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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

    // --------------------------------------------------------------------------------------------------- specialDealId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute.
     */
    @Nullable
    public Integer getSpecialDealId() {
        return specialDealId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute with the specified value.
     *
     * @param specialDealId new value for {@value #ATTRIBUTE_NAME_SPECIAL_DEAL_ID} attribute.
     */
    protected void setSpecialDealId(@Nullable final Integer specialDealId) {
        this.specialDealId = specialDealId;
    }

    // ----------------------------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    @Nullable
    public Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(@Nullable final Integer stockItemId) {
        this.stockItemId = stockItemId;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    @Nullable
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(@Nullable final Integer customerId) {
        this.customerId = customerId;
    }

    // --------------------------------------------------------------------------------------------------- buyingGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    @Nullable
    public Integer getBuyingGroupId() {
        return buyingGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param buyingGroupId new value for {@value #ATTRIBUTE_NAME_BUYING_GROUP_ID} attribute.
     */
    protected void setBuyingGroupId(@Nullable final Integer buyingGroupId) {
        this.buyingGroupId = buyingGroupId;
    }

    // ---------------------------------------------------------------------------------------------- customerCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    @Nullable
    public Integer getCustomerCategoryId() {
        return customerCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param customerCategoryId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CATEGORY_ID} attribute.
     */
    protected void setCustomerCategoryId(@Nullable final Integer customerCategoryId) {
        this.customerCategoryId = customerCategoryId;
    }

    // ---------------------------------------------------------------------------------------------------- stockGroupId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    @Nullable
    public Integer getStockGroupId() {
        return stockGroupId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockGroupId new value for {@value #ATTRIBUTE_NAME_STOCK_GROUP_ID} attribute.
     */
    protected void setStockGroupId(@Nullable final Integer stockGroupId) {
        this.stockGroupId = stockGroupId;
    }

    // ------------------------------------------------------------------------------------------------- dealDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute.
     */
    @Nonnull
    public String getDealDescription() {
        return dealDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute with the specified value.
     *
     * @param dealDescription new value for {@value #ATTRIBUTE_NAME_DEAL_DESCRIPTION} attribute.
     */
    public void setDealDescription(@Nonnull final String dealDescription) {
        this.dealDescription = dealDescription;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    @Nonnull
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(@Nonnull final LocalDate startDate) {
        this.startDate = startDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    @Nonnull
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(@Nonnull final LocalDate endDate) {
        this.endDate = endDate;
    }

    // -------------------------------------------------------------------------------------------------- discountAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    @Nullable
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute with the specified value.
     *
     * @param discountAmount new value for {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    public void setDiscountAmount(@Nullable final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    // ---------------------------------------------------------------------------------------------- discountPercentage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute.
     */
    @Nullable
    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute with the specified value.
     *
     * @param discountPercentage new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENTAGE} attribute.
     */
    public void setDiscountPercentage(@Nullable final BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    @Nullable
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(@Nullable final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    @Nonnull
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(@Nonnull final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // -------------------------------------------------------------------------------------------------- lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    @Nonnull
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(@Nonnull final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.SpecialDealID", schema = "Sequences", sequenceName = "SpecialDealID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.SpecialDealID")
    @Column(name = COLUMN_NAME_SPECIAL_DEAL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer specialDealId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer stockItemId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_BUYING_GROUP_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer buyingGroupId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_CUSTOMER_CATEGORY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer customerCategoryId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_STOCK_GROUP_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer stockGroupId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_DEAL_DESCRIPTION)
    @NotNull
    @Column(name = COLUMN_NAME_DEAL_DESCRIPTION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DEAL_DESCRIPTION
    )
    private String dealDescription;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate startDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate endDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_AMOUNT - COLUMN_SCALE_DISCOUNT_AMOUNT, fraction = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @Column(name = COLUMN_NAME_DISCOUNT_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNT_AMOUNT,
            scale = COLUMN_SCALE_DISCOUNT_AMOUNT
    )
    private BigDecimal discountAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_PERCENTAGE - COLUMN_SCALE_DISCOUNT_PERCENTAGE, fraction = COLUMN_SCALE_DISCOUNT_PERCENTAGE)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENTAGE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNT_PERCENTAGE,
            scale = COLUMN_SCALE_DISCOUNT_PERCENTAGE
    )
    private BigDecimal discountPercentage;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime lastEditedWhen;
}

