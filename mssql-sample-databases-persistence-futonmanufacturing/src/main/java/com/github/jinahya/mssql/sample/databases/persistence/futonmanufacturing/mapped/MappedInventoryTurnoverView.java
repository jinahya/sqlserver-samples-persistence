package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_InventoryTurnover} view.
 * <p>
 * Identifier: the view declares no key; ItemCode is unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedInventoryTurnoverView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_InventoryTurnover";

    // -------------------------------------------------------------------------------------------------------- ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // -------------------------------------------------------------------------------------------------------- ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // -------------------------------------------------------------------------------------------------------- ItemType

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_TYPE = "ItemType";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE = "itemType";

    // -------------------------------------------------------------------------------------------------- QuantityOnHand

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_ON_HAND = "QuantityOnHand";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_ON_HAND = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_ON_HAND = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_ON_HAND = "quantityOnHand";

    // ----------------------------------------------------------------------------------------------- QuantityAvailable

    /**
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_AVAILABLE = "QuantityAvailable";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_AVAILABLE = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_AVAILABLE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_AVAILABLE = "quantityAvailable";

    // ---------------------------------------------------------------------------------------------------- Annual Usage

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ANNUAL_USAGE = "\"Annual Usage\"";

    /**
     * The precision of the {@value #COLUMN_NAME_ANNUAL_USAGE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ANNUAL_USAGE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_ANNUAL_USAGE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ANNUAL_USAGE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ANNUAL_USAGE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ANNUAL_USAGE = "annualUsage";

    // -------------------------------------------------------------------------------------------------- AnnualReceipts

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ANNUAL_RECEIPTS = "AnnualReceipts";

    /**
     * The precision of the {@value #COLUMN_NAME_ANNUAL_RECEIPTS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ANNUAL_RECEIPTS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_ANNUAL_RECEIPTS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ANNUAL_RECEIPTS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ANNUAL_RECEIPTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ANNUAL_RECEIPTS = "annualReceipts";

    // ------------------------------------------------------------------------------------------------ TransactionCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_COUNT = "TransactionCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_COUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_COUNT = "transactionCount";

    // --------------------------------------------------------------------------------------------------- TurnoverRatio

    /**
     * The name of the {@code decimal(10, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TURNOVER_RATIO = "TurnoverRatio";

    /**
     * The precision of the {@value #COLUMN_NAME_TURNOVER_RATIO} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TURNOVER_RATIO = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_TURNOVER_RATIO} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TURNOVER_RATIO = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TURNOVER_RATIO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TURNOVER_RATIO = "turnoverRatio";

    // ------------------------------------------------------------------------------------------------------ DaysOnHand

    /**
     * The name of the {@code decimal(10, 1)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_ON_HAND = "DaysOnHand";

    /**
     * The precision of the {@value #COLUMN_NAME_DAYS_ON_HAND} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DAYS_ON_HAND = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_DAYS_ON_HAND} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DAYS_ON_HAND = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_ON_HAND} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_ON_HAND = "daysOnHand";

    // -------------------------------------------------------------------------------------------------- InventoryValue

    /**
     * The name of the {@code decimal(37, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_VALUE = "InventoryValue";

    /**
     * The precision of the {@value #COLUMN_NAME_INVENTORY_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_INVENTORY_VALUE = 37;

    /**
     * The scale of the {@value #COLUMN_NAME_INVENTORY_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_INVENTORY_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVENTORY_VALUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVENTORY_VALUE = "inventoryValue";

    // ------------------------------------------------------------------------------------------------ FirstTransaction

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FIRST_TRANSACTION = "FirstTransaction";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FIRST_TRANSACTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FIRST_TRANSACTION = "firstTransaction";

    // ------------------------------------------------------------------------------------------------- LastTransaction

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_TRANSACTION = "LastTransaction";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_TRANSACTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_TRANSACTION = "lastTransaction";

    // ------------------------------------------------------------------------------------------- DaysSinceLastActivity

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_SINCE_LAST_ACTIVITY = "DaysSinceLastActivity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_SINCE_LAST_ACTIVITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_SINCE_LAST_ACTIVITY = "daysSinceLastActivity";

    // --------------------------------------------------------------------------------------------------- MovementClass

    /**
     * The name of the {@code varchar(11), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MOVEMENT_CLASS = "MovementClass";

    /**
     * The length of the {@value #COLUMN_NAME_MOVEMENT_CLASS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_MOVEMENT_CLASS = 11;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MOVEMENT_CLASS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MOVEMENT_CLASS = "movementClass";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventoryTurnoverView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",quantityOnHand=" + quantityOnHand
               + ",quantityAvailable=" + quantityAvailable
               + ",annualUsage=" + annualUsage
               + ",annualReceipts=" + annualReceipts
               + ",transactionCount=" + transactionCount
               + ",turnoverRatio=" + turnoverRatio
               + ",daysOnHand=" + daysOnHand
               + ",inventoryValue=" + inventoryValue
               + ",firstTransaction=" + firstTransaction
               + ",lastTransaction=" + lastTransaction
               + ",daysSinceLastActivity=" + daysSinceLastActivity
               + ",movementClass=" + movementClass
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
        if (!(obj instanceof MappedInventoryTurnoverView that)) {
            return false;
        }
        return Objects.equals(itemCode, that.itemCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(itemCode);
    }

    // -------------------------------------------------------------------------------------------------------- itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    @Nonnull
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(@Nonnull final String itemCode) {
        this.itemCode = itemCode;
    }

    // -------------------------------------------------------------------------------------------------------- itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    @Nonnull
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(@Nonnull final String itemName) {
        this.itemName = itemName;
    }

    // -------------------------------------------------------------------------------------------------------- itemType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    @Nonnull
    public String getItemType() {
        return itemType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute with the specified value.
     *
     * @param itemType new value for {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    protected void setItemType(@Nonnull final String itemType) {
        this.itemType = itemType;
    }

    // -------------------------------------------------------------------------------------------------- quantityOnHand

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     */
    @Nonnull
    public BigDecimal getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute with the specified value.
     *
     * @param quantityOnHand new value for {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     */
    protected void setQuantityOnHand(@Nonnull final BigDecimal quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    // ----------------------------------------------------------------------------------------------- quantityAvailable

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     */
    @Nullable
    public BigDecimal getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute with the specified value.
     *
     * @param quantityAvailable new value for {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     */
    protected void setQuantityAvailable(@Nullable final BigDecimal quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    // ----------------------------------------------------------------------------------------------------- annualUsage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ANNUAL_USAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ANNUAL_USAGE} attribute.
     */
    @Nullable
    public BigDecimal getAnnualUsage() {
        return annualUsage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ANNUAL_USAGE} attribute with the specified value.
     *
     * @param annualUsage new value for {@value #ATTRIBUTE_NAME_ANNUAL_USAGE} attribute.
     */
    protected void setAnnualUsage(@Nullable final BigDecimal annualUsage) {
        this.annualUsage = annualUsage;
    }

    // -------------------------------------------------------------------------------------------------- annualReceipts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ANNUAL_RECEIPTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ANNUAL_RECEIPTS} attribute.
     */
    @Nullable
    public BigDecimal getAnnualReceipts() {
        return annualReceipts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ANNUAL_RECEIPTS} attribute with the specified value.
     *
     * @param annualReceipts new value for {@value #ATTRIBUTE_NAME_ANNUAL_RECEIPTS} attribute.
     */
    protected void setAnnualReceipts(@Nullable final BigDecimal annualReceipts) {
        this.annualReceipts = annualReceipts;
    }

    // ------------------------------------------------------------------------------------------------ transactionCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_COUNT} attribute.
     */
    @Nullable
    public Integer getTransactionCount() {
        return transactionCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_COUNT} attribute with the specified value.
     *
     * @param transactionCount new value for {@value #ATTRIBUTE_NAME_TRANSACTION_COUNT} attribute.
     */
    protected void setTransactionCount(@Nullable final Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    // --------------------------------------------------------------------------------------------------- turnoverRatio

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TURNOVER_RATIO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TURNOVER_RATIO} attribute.
     */
    @Nullable
    public BigDecimal getTurnoverRatio() {
        return turnoverRatio;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TURNOVER_RATIO} attribute with the specified value.
     *
     * @param turnoverRatio new value for {@value #ATTRIBUTE_NAME_TURNOVER_RATIO} attribute.
     */
    protected void setTurnoverRatio(@Nullable final BigDecimal turnoverRatio) {
        this.turnoverRatio = turnoverRatio;
    }

    // ------------------------------------------------------------------------------------------------------ daysOnHand

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_ON_HAND} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_ON_HAND} attribute.
     */
    @Nullable
    public BigDecimal getDaysOnHand() {
        return daysOnHand;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_ON_HAND} attribute with the specified value.
     *
     * @param daysOnHand new value for {@value #ATTRIBUTE_NAME_DAYS_ON_HAND} attribute.
     */
    protected void setDaysOnHand(@Nullable final BigDecimal daysOnHand) {
        this.daysOnHand = daysOnHand;
    }

    // -------------------------------------------------------------------------------------------------- inventoryValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute.
     */
    @Nullable
    public BigDecimal getInventoryValue() {
        return inventoryValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute with the specified value.
     *
     * @param inventoryValue new value for {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute.
     */
    protected void setInventoryValue(@Nullable final BigDecimal inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    // ------------------------------------------------------------------------------------------------ firstTransaction

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FIRST_TRANSACTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FIRST_TRANSACTION} attribute.
     */
    @Nullable
    public LocalDateTime getFirstTransaction() {
        return firstTransaction;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FIRST_TRANSACTION} attribute with the specified value.
     *
     * @param firstTransaction new value for {@value #ATTRIBUTE_NAME_FIRST_TRANSACTION} attribute.
     */
    protected void setFirstTransaction(@Nullable final LocalDateTime firstTransaction) {
        this.firstTransaction = firstTransaction;
    }

    // ------------------------------------------------------------------------------------------------- lastTransaction

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_TRANSACTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_TRANSACTION} attribute.
     */
    @Nullable
    public LocalDateTime getLastTransaction() {
        return lastTransaction;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_TRANSACTION} attribute with the specified value.
     *
     * @param lastTransaction new value for {@value #ATTRIBUTE_NAME_LAST_TRANSACTION} attribute.
     */
    protected void setLastTransaction(@Nullable final LocalDateTime lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

    // ------------------------------------------------------------------------------------------- daysSinceLastActivity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ACTIVITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ACTIVITY} attribute.
     */
    @Nullable
    public Integer getDaysSinceLastActivity() {
        return daysSinceLastActivity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ACTIVITY} attribute with the specified value.
     *
     * @param daysSinceLastActivity new value for {@value #ATTRIBUTE_NAME_DAYS_SINCE_LAST_ACTIVITY} attribute.
     */
    protected void setDaysSinceLastActivity(@Nullable final Integer daysSinceLastActivity) {
        this.daysSinceLastActivity = daysSinceLastActivity;
    }

    // --------------------------------------------------------------------------------------------------- movementClass

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MOVEMENT_CLASS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MOVEMENT_CLASS} attribute.
     */
    @Nonnull
    public String getMovementClass() {
        return movementClass;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MOVEMENT_CLASS} attribute with the specified value.
     *
     * @param movementClass new value for {@value #ATTRIBUTE_NAME_MOVEMENT_CLASS} attribute.
     */
    protected void setMovementClass(@Nonnull final String movementClass) {
        this.movementClass = movementClass;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Column(name = COLUMN_NAME_ITEM_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ITEM_TYPE
    )
    private String itemType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_QUANTITY_ON_HAND - COLUMN_SCALE_QUANTITY_ON_HAND, fraction = COLUMN_SCALE_QUANTITY_ON_HAND)
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY_ON_HAND,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_ON_HAND,
            scale = COLUMN_SCALE_QUANTITY_ON_HAND
    )
    private BigDecimal quantityOnHand;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_QUANTITY_AVAILABLE - COLUMN_SCALE_QUANTITY_AVAILABLE, fraction = COLUMN_SCALE_QUANTITY_AVAILABLE)
    @Column(name = COLUMN_NAME_QUANTITY_AVAILABLE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_QUANTITY_AVAILABLE,
            scale = COLUMN_SCALE_QUANTITY_AVAILABLE
    )
    private BigDecimal quantityAvailable;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_ANNUAL_USAGE - COLUMN_SCALE_ANNUAL_USAGE, fraction = COLUMN_SCALE_ANNUAL_USAGE)
    @Column(name = COLUMN_NAME_ANNUAL_USAGE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_ANNUAL_USAGE,
            scale = COLUMN_SCALE_ANNUAL_USAGE
    )
    private BigDecimal annualUsage;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_ANNUAL_RECEIPTS - COLUMN_SCALE_ANNUAL_RECEIPTS, fraction = COLUMN_SCALE_ANNUAL_RECEIPTS)
    @Column(name = COLUMN_NAME_ANNUAL_RECEIPTS,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_ANNUAL_RECEIPTS,
            scale = COLUMN_SCALE_ANNUAL_RECEIPTS
    )
    private BigDecimal annualReceipts;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_TRANSACTION_COUNT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer transactionCount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_TURNOVER_RATIO - COLUMN_SCALE_TURNOVER_RATIO, fraction = COLUMN_SCALE_TURNOVER_RATIO)
    @Column(name = COLUMN_NAME_TURNOVER_RATIO,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TURNOVER_RATIO,
            scale = COLUMN_SCALE_TURNOVER_RATIO
    )
    private BigDecimal turnoverRatio;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_DAYS_ON_HAND - COLUMN_SCALE_DAYS_ON_HAND, fraction = COLUMN_SCALE_DAYS_ON_HAND)
    @Column(name = COLUMN_NAME_DAYS_ON_HAND,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DAYS_ON_HAND,
            scale = COLUMN_SCALE_DAYS_ON_HAND
    )
    private BigDecimal daysOnHand;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_INVENTORY_VALUE - COLUMN_SCALE_INVENTORY_VALUE, fraction = COLUMN_SCALE_INVENTORY_VALUE)
    @Column(name = COLUMN_NAME_INVENTORY_VALUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_INVENTORY_VALUE,
            scale = COLUMN_SCALE_INVENTORY_VALUE
    )
    private BigDecimal inventoryValue;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_FIRST_TRANSACTION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime firstTransaction;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_LAST_TRANSACTION,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime lastTransaction;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DAYS_SINCE_LAST_ACTIVITY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer daysSinceLastActivity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_MOVEMENT_CLASS)
    @NotNull
    @Column(name = COLUMN_NAME_MOVEMENT_CLASS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_MOVEMENT_CLASS
    )
    private String movementClass;
}

