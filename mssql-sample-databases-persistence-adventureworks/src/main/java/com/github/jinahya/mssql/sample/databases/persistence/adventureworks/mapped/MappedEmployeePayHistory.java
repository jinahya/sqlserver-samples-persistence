package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.EmployeePayHistory} table.
 */
@MappedSuperclass
public abstract class MappedEmployeePayHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "HumanResources";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "EmployeePayHistory";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // -------------------------------------------------------------------------------------------------- RateChangeDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATE_CHANGE_DATE = "RateChangeDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATE_CHANGE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATE_CHANGE_DATE = "rateChangeDate";

    // ------------------------------------------------------------------------------------------------------------ Rate

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATE = "Rate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATE = "rate";

    // ---------------------------------------------------------------------------------------------------- PayFrequency

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAY_FREQUENCY = "PayFrequency";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAY_FREQUENCY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAY_FREQUENCY = "payFrequency";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedEmployeePayHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",rateChangeDate=" + rateChangeDate
               + ",rate=" + rate
               + ",payFrequency=" + payFrequency
               + ",modifiedDate=" + modifiedDate
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
        if (!(obj instanceof MappedEmployeePayHistory that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(rateChangeDate, that.rateChangeDate);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId, rateChangeDate);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    @Nonnull
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(@Nonnull final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // -------------------------------------------------------------------------------------------------- rateChangeDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getRateChangeDate() {
        return rateChangeDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute with the specified value.
     *
     * @param rateChangeDate new value for {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute.
     */
    public void setRateChangeDate(@Nonnull final LocalDateTime rateChangeDate) {
        this.rateChangeDate = rateChangeDate;
    }

    // ------------------------------------------------------------------------------------------------------------ rate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATE} attribute.
     */
    @Nonnull
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATE} attribute with the specified value.
     *
     * @param rate new value for {@value #ATTRIBUTE_NAME_RATE} attribute.
     */
    public void setRate(@Nonnull final BigDecimal rate) {
        this.rate = rate;
    }

    // ---------------------------------------------------------------------------------------------------- payFrequency

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute.
     */
    @Nonnull
    public Short getPayFrequency() {
        return payFrequency;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute with the specified value.
     *
     * @param payFrequency new value for {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute.
     */
    public void setPayFrequency(@Nonnull final Short payFrequency) {
        this.payFrequency = payFrequency;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_RATE_CHANGE_DATE,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private LocalDateTime rateChangeDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_RATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal rate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Max(255)
    @Min(0)
    @NotNull
    @Column(name = COLUMN_NAME_PAY_FREQUENCY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short payFrequency;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

