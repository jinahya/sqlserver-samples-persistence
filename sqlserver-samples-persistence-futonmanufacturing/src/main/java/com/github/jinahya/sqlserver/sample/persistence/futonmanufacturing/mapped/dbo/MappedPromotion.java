package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Promotion} table.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.Promotion}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsActive}</th><td>{@code ((1))}</td></tr>
 * <tr><th scope="row">{@code MinimumPurchase}</th><td>{@code ((0))}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedPromotion {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Promotion";

    // --------------------------------------------------------------------------------- PromotionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMOTION_ID = "PromotionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMOTION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION_ID = "promotionId";

    // ------------------------------------------------------------------------------- PromotionCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMOTION_CODE = "PromotionCode";

    /**
     * The length of the {@value #COLUMN_NAME_PROMOTION_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROMOTION_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMOTION_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION_CODE = "promotionCode";

    // ------------------------------------------------------------------------------- PromotionName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PROMOTION_NAME = "PromotionName";

    /**
     * The length of the {@value #COLUMN_NAME_PROMOTION_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PROMOTION_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PROMOTION_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PROMOTION_NAME = "promotionName";

    // --------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ----------------------------------------------------------------------------- DiscountPercent

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_PERCENT = "DiscountPercent";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_PERCENT = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_PERCENT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PERCENT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PERCENT = "discountPercent";

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

    // ------------------------------------------------------------------------------------ IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // ----------------------------------------------------------------------------- MinimumPurchase

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MINIMUM_PURCHASE = "MinimumPurchase";

    /**
     * The precision of the {@value #COLUMN_NAME_MINIMUM_PURCHASE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MINIMUM_PURCHASE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_MINIMUM_PURCHASE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MINIMUM_PURCHASE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MINIMUM_PURCHASE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MINIMUM_PURCHASE = "minimumPurchase";

    // -------------------------------------------------------------------------- ApplicableChannels

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_APPLICABLE_CHANNELS = "ApplicableChannels";

    /**
     * The length of the {@value #COLUMN_NAME_APPLICABLE_CHANNELS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_APPLICABLE_CHANNELS = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_APPLICABLE_CHANNELS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_APPLICABLE_CHANNELS = "applicableChannels";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPromotion() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "promotionId=" + promotionId
               + ",promotionCode=" + promotionCode
               + ",promotionName=" + promotionName
               + ",description=" + description
               + ",discountPercent=" + discountPercent
               + ",discountAmount=" + discountAmount
               + ",startDate=" + startDate
               + ",endDate=" + endDate
               + ",isActive=" + isActive
               + ",minimumPurchase=" + minimumPurchase
               + ",applicableChannels=" + applicableChannels
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
        if (!(obj instanceof MappedPromotion that)) {
            return false;
        }
        return Objects.equals(promotionId, that.promotionId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(promotionId);
    }

    // --------------------------------------------------------------------------------- promotionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMOTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMOTION_ID} attribute.
     */
    public Integer getPromotionId() {
        return promotionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMOTION_ID} attribute with the specified value.
     *
     * @param promotionId new value for {@value #ATTRIBUTE_NAME_PROMOTION_ID} attribute.
     */
    protected void setPromotionId(final Integer promotionId) {
        this.promotionId = promotionId;
    }

    // ------------------------------------------------------------------------------- promotionCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMOTION_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMOTION_CODE} attribute.
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMOTION_CODE} attribute with the specified value.
     *
     * @param promotionCode new value for {@value #ATTRIBUTE_NAME_PROMOTION_CODE} attribute.
     */
    public void setPromotionCode(final String promotionCode) {
        this.promotionCode = promotionCode;
    }

    // ------------------------------------------------------------------------------- promotionName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PROMOTION_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PROMOTION_NAME} attribute.
     */
    public String getPromotionName() {
        return promotionName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PROMOTION_NAME} attribute with the specified value.
     *
     * @param promotionName new value for {@value #ATTRIBUTE_NAME_PROMOTION_NAME} attribute.
     */
    public void setPromotionName(final String promotionName) {
        this.promotionName = promotionName;
    }

    // --------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // ----------------------------------------------------------------------------- discountPercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute with the specified value.
     *
     * @param discountPercent new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PERCENT} attribute.
     */
    public void setDiscountPercent(final BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
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

    // ------------------------------------------------------------------------------------ isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // ----------------------------------------------------------------------------- minimumPurchase

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MINIMUM_PURCHASE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MINIMUM_PURCHASE} attribute.
     */
    public BigDecimal getMinimumPurchase() {
        return minimumPurchase;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MINIMUM_PURCHASE} attribute with the specified value.
     *
     * @param minimumPurchase new value for {@value #ATTRIBUTE_NAME_MINIMUM_PURCHASE} attribute.
     */
    public void setMinimumPurchase(final BigDecimal minimumPurchase) {
        this.minimumPurchase = minimumPurchase;
    }

    // -------------------------------------------------------------------------- applicableChannels

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_APPLICABLE_CHANNELS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_APPLICABLE_CHANNELS} attribute.
     */
    public String getApplicableChannels() {
        return applicableChannels;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_APPLICABLE_CHANNELS} attribute with the specified value.
     *
     * @param applicableChannels new value for {@value #ATTRIBUTE_NAME_APPLICABLE_CHANNELS} attribute.
     */
    public void setApplicableChannels(final String applicableChannels) {
        this.applicableChannels = applicableChannels;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PROMOTION_ID, nullable = false, insertable = true, updatable = false)
    private Integer promotionId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PROMOTION_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMOTION_CODE, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_PROMOTION_CODE)
    private String promotionCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PROMOTION_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PROMOTION_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_PROMOTION_NAME)
    private String promotionName;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION, nullable = true, insertable = true, updatable = true)
    private String description;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_PERCENT - COLUMN_SCALE_DISCOUNT_PERCENT,
            fraction = COLUMN_SCALE_DISCOUNT_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_PERCENT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_DISCOUNT_PERCENT, scale = COLUMN_SCALE_DISCOUNT_PERCENT)
    private BigDecimal discountPercent;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_AMOUNT - COLUMN_SCALE_DISCOUNT_AMOUNT,
            fraction = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_AMOUNT, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_DISCOUNT_AMOUNT, scale = COLUMN_SCALE_DISCOUNT_AMOUNT)
    private BigDecimal discountAmount;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_DATE, nullable = false, insertable = true, updatable = true)
    private LocalDate startDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_DATE, nullable = false, insertable = true, updatable = true)
    private LocalDate endDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE, nullable = false, insertable = true, updatable = true)
    @MsDefault("((1))")
    private Boolean isActive;

    // ---------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_MINIMUM_PURCHASE - COLUMN_SCALE_MINIMUM_PURCHASE,
            fraction = COLUMN_SCALE_MINIMUM_PURCHASE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MINIMUM_PURCHASE, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_MINIMUM_PURCHASE, scale = COLUMN_SCALE_MINIMUM_PURCHASE)
    @MsDefault("((0))")
    private BigDecimal minimumPurchase;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_APPLICABLE_CHANNELS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_APPLICABLE_CHANNELS, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_APPLICABLE_CHANNELS)
    private String applicableChannels;
}

