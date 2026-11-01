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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_ComponentShortage} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedComponentShortageView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_ComponentShortage";

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

    // ------------------------------------------------------------------------------------------------ RequiredQuantity

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REQUIRED_QUANTITY = "RequiredQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REQUIRED_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REQUIRED_QUANTITY = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REQUIRED_QUANTITY = "requiredQuantity";

    // ----------------------------------------------------------------------------------------------- AvailableQuantity

    /**
     * The name of the {@code decimal(38, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVAILABLE_QUANTITY = "AvailableQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVAILABLE_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVAILABLE_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVAILABLE_QUANTITY = "availableQuantity";

    // ------------------------------------------------------------------------------------------------ ShortageQuantity

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHORTAGE_QUANTITY = "ShortageQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SHORTAGE_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SHORTAGE_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHORTAGE_QUANTITY = "shortageQuantity";

    // ------------------------------------------------------------------------------------------------ EarliestNeedDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EARLIEST_NEED_DATE = "EarliestNeedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EARLIEST_NEED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EARLIEST_NEED_DATE = "earliestNeedDate";

    // ------------------------------------------------------------------------------------------------- DaysUntilNeeded

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_UNTIL_NEEDED = "DaysUntilNeeded";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_UNTIL_NEEDED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_UNTIL_NEEDED = "daysUntilNeeded";

    // -------------------------------------------------------------------------------------------------- AffectedOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AFFECTED_ORDERS = "AffectedOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AFFECTED_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AFFECTED_ORDERS = "affectedOrders";

    // ---------------------------------------------------------------------------------------------------- UrgencyLevel

    /**
     * The name of the {@code varchar(6), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_URGENCY_LEVEL = "UrgencyLevel";

    /**
     * The length of the {@value #COLUMN_NAME_URGENCY_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_URGENCY_LEVEL = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_URGENCY_LEVEL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_URGENCY_LEVEL = "urgencyLevel";

    // ----------------------------------------------------------------------------------------------- PreferredSupplier

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PREFERRED_SUPPLIER = "PreferredSupplier";

    /**
     * The length of the {@value #COLUMN_NAME_PREFERRED_SUPPLIER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PREFERRED_SUPPLIER = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PREFERRED_SUPPLIER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PREFERRED_SUPPLIER = "preferredSupplier";

    // ---------------------------------------------------------------------------------------------------- LeadTimeDays

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEAD_TIME_DAYS = "LeadTimeDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEAD_TIME_DAYS = "leadTimeDays";

    // ------------------------------------------------------------------------------------------------- PossibleArrival

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_POSSIBLE_ARRIVAL = "PossibleArrival";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_POSSIBLE_ARRIVAL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSSIBLE_ARRIVAL = "possibleArrival";

    // ---------------------------------------------------------------------------------------------------- SupplyStatus

    /**
     * The name of the {@code varchar(12), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLY_STATUS = "SupplyStatus";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLY_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLY_STATUS = 12;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLY_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLY_STATUS = "supplyStatus";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedComponentShortageView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",requiredQuantity=" + requiredQuantity
               + ",availableQuantity=" + availableQuantity
               + ",shortageQuantity=" + shortageQuantity
               + ",earliestNeedDate=" + earliestNeedDate
               + ",daysUntilNeeded=" + daysUntilNeeded
               + ",affectedOrders=" + affectedOrders
               + ",urgencyLevel=" + urgencyLevel
               + ",preferredSupplier=" + preferredSupplier
               + ",leadTimeDays=" + leadTimeDays
               + ",possibleArrival=" + possibleArrival
               + ",supplyStatus=" + supplyStatus
               + "}";
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

    // ------------------------------------------------------------------------------------------------ requiredQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     */
    @Nullable
    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute with the specified value.
     *
     * @param requiredQuantity new value for {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     */
    protected void setRequiredQuantity(@Nullable final BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    // ----------------------------------------------------------------------------------------------- availableQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute.
     */
    @Nonnull
    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute with the specified value.
     *
     * @param availableQuantity new value for {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute.
     */
    protected void setAvailableQuantity(@Nonnull final BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    // ------------------------------------------------------------------------------------------------ shortageQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     */
    @Nullable
    public BigDecimal getShortageQuantity() {
        return shortageQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute with the specified value.
     *
     * @param shortageQuantity new value for {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     */
    protected void setShortageQuantity(@Nullable final BigDecimal shortageQuantity) {
        this.shortageQuantity = shortageQuantity;
    }

    // ------------------------------------------------------------------------------------------------ earliestNeedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EARLIEST_NEED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EARLIEST_NEED_DATE} attribute.
     */
    @Nullable
    public LocalDate getEarliestNeedDate() {
        return earliestNeedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EARLIEST_NEED_DATE} attribute with the specified value.
     *
     * @param earliestNeedDate new value for {@value #ATTRIBUTE_NAME_EARLIEST_NEED_DATE} attribute.
     */
    protected void setEarliestNeedDate(@Nullable final LocalDate earliestNeedDate) {
        this.earliestNeedDate = earliestNeedDate;
    }

    // ------------------------------------------------------------------------------------------------- daysUntilNeeded

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_UNTIL_NEEDED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_UNTIL_NEEDED} attribute.
     */
    @Nullable
    public Integer getDaysUntilNeeded() {
        return daysUntilNeeded;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_UNTIL_NEEDED} attribute with the specified value.
     *
     * @param daysUntilNeeded new value for {@value #ATTRIBUTE_NAME_DAYS_UNTIL_NEEDED} attribute.
     */
    protected void setDaysUntilNeeded(@Nullable final Integer daysUntilNeeded) {
        this.daysUntilNeeded = daysUntilNeeded;
    }

    // -------------------------------------------------------------------------------------------------- affectedOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AFFECTED_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AFFECTED_ORDERS} attribute.
     */
    @Nullable
    public Integer getAffectedOrders() {
        return affectedOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AFFECTED_ORDERS} attribute with the specified value.
     *
     * @param affectedOrders new value for {@value #ATTRIBUTE_NAME_AFFECTED_ORDERS} attribute.
     */
    protected void setAffectedOrders(@Nullable final Integer affectedOrders) {
        this.affectedOrders = affectedOrders;
    }

    // ---------------------------------------------------------------------------------------------------- urgencyLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_URGENCY_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_URGENCY_LEVEL} attribute.
     */
    @Nonnull
    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_URGENCY_LEVEL} attribute with the specified value.
     *
     * @param urgencyLevel new value for {@value #ATTRIBUTE_NAME_URGENCY_LEVEL} attribute.
     */
    protected void setUrgencyLevel(@Nonnull final String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    // ----------------------------------------------------------------------------------------------- preferredSupplier

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute.
     */
    @Nullable
    public String getPreferredSupplier() {
        return preferredSupplier;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute with the specified value.
     *
     * @param preferredSupplier new value for {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute.
     */
    protected void setPreferredSupplier(@Nullable final String preferredSupplier) {
        this.preferredSupplier = preferredSupplier;
    }

    // ---------------------------------------------------------------------------------------------------- leadTimeDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    @Nullable
    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute with the specified value.
     *
     * @param leadTimeDays new value for {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    protected void setLeadTimeDays(@Nullable final Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    // ------------------------------------------------------------------------------------------------- possibleArrival

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_POSSIBLE_ARRIVAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_POSSIBLE_ARRIVAL} attribute.
     */
    @Nullable
    public LocalDateTime getPossibleArrival() {
        return possibleArrival;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_POSSIBLE_ARRIVAL} attribute with the specified value.
     *
     * @param possibleArrival new value for {@value #ATTRIBUTE_NAME_POSSIBLE_ARRIVAL} attribute.
     */
    protected void setPossibleArrival(@Nullable final LocalDateTime possibleArrival) {
        this.possibleArrival = possibleArrival;
    }

    // ---------------------------------------------------------------------------------------------------- supplyStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLY_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLY_STATUS} attribute.
     */
    @Nonnull
    public String getSupplyStatus() {
        return supplyStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLY_STATUS} attribute with the specified value.
     *
     * @param supplyStatus new value for {@value #ATTRIBUTE_NAME_SUPPLY_STATUS} attribute.
     */
    protected void setSupplyStatus(@Nonnull final String supplyStatus) {
        this.supplyStatus = supplyStatus;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
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
    @Nullable
    @Digits(integer = COLUMN_PRECISION_REQUIRED_QUANTITY - COLUMN_SCALE_REQUIRED_QUANTITY, fraction = COLUMN_SCALE_REQUIRED_QUANTITY)
    @Column(name = COLUMN_NAME_REQUIRED_QUANTITY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_REQUIRED_QUANTITY,
            scale = COLUMN_SCALE_REQUIRED_QUANTITY
    )
    private BigDecimal requiredQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_AVAILABLE_QUANTITY - COLUMN_SCALE_AVAILABLE_QUANTITY, fraction = COLUMN_SCALE_AVAILABLE_QUANTITY)
    @NotNull
    @Column(name = COLUMN_NAME_AVAILABLE_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVAILABLE_QUANTITY,
            scale = COLUMN_SCALE_AVAILABLE_QUANTITY
    )
    private BigDecimal availableQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_SHORTAGE_QUANTITY - COLUMN_SCALE_SHORTAGE_QUANTITY, fraction = COLUMN_SCALE_SHORTAGE_QUANTITY)
    @Column(name = COLUMN_NAME_SHORTAGE_QUANTITY,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SHORTAGE_QUANTITY,
            scale = COLUMN_SCALE_SHORTAGE_QUANTITY
    )
    private BigDecimal shortageQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_EARLIEST_NEED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate earliestNeedDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DAYS_UNTIL_NEEDED,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer daysUntilNeeded;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_AFFECTED_ORDERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer affectedOrders;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_URGENCY_LEVEL)
    @NotNull
    @Column(name = COLUMN_NAME_URGENCY_LEVEL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_URGENCY_LEVEL
    )
    private String urgencyLevel;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_PREFERRED_SUPPLIER)
    @Column(name = COLUMN_NAME_PREFERRED_SUPPLIER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PREFERRED_SUPPLIER
    )
    private String preferredSupplier;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer leadTimeDays;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_POSSIBLE_ARRIVAL,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime possibleArrival;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_SUPPLY_STATUS)
    @NotNull
    @Column(name = COLUMN_NAME_SUPPLY_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLY_STATUS
    )
    private String supplyStatus;
}

