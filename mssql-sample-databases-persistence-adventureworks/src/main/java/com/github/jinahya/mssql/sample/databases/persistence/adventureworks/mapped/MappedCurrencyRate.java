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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.CurrencyRate} table.
 * <p>
 * The {@value #COLUMN_NAME_FROM_CURRENCY_CODE}, {@value #COLUMN_NAME_TO_CURRENCY_CODE} columns are mapped read-only,
 * as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedCurrencyRate {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CurrencyRate";

    // -------------------------------------------------------------------------------------------------- CurrencyRateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENCY_RATE_ID = "CurrencyRateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENCY_RATE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_RATE_ID = "currencyRateId";

    // ------------------------------------------------------------------------------------------------ CurrencyRateDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENCY_RATE_DATE = "CurrencyRateDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENCY_RATE_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_RATE_DATE = "currencyRateDate";

    // ------------------------------------------------------------------------------------------------ FromCurrencyCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FROM_CURRENCY_CODE = "FromCurrencyCode";

    /**
     * The length of the {@value #COLUMN_NAME_FROM_CURRENCY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FROM_CURRENCY_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FROM_CURRENCY_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_FROM_CURRENCY_CODE = "fromCurrencyCode";

    // -------------------------------------------------------------------------------------------------- ToCurrencyCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TO_CURRENCY_CODE = "ToCurrencyCode";

    /**
     * The length of the {@value #COLUMN_NAME_TO_CURRENCY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TO_CURRENCY_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TO_CURRENCY_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TO_CURRENCY_CODE = "toCurrencyCode";

    // ----------------------------------------------------------------------------------------------------- AverageRate

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVERAGE_RATE = "AverageRate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVERAGE_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVERAGE_RATE = "averageRate";

    // ---------------------------------------------------------------------------------------------------- EndOfDayRate

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_OF_DAY_RATE = "EndOfDayRate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_DAY_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_DAY_RATE = "endOfDayRate";

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
    protected MappedCurrencyRate() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "currencyRateId=" + currencyRateId
               + ",currencyRateDate=" + currencyRateDate
               + ",fromCurrencyCode=" + fromCurrencyCode
               + ",toCurrencyCode=" + toCurrencyCode
               + ",averageRate=" + averageRate
               + ",endOfDayRate=" + endOfDayRate
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
        if (!(obj instanceof MappedCurrencyRate that)) {
            return false;
        }
        return Objects.equals(currencyRateId, that.currencyRateId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(currencyRateId);
    }

    // -------------------------------------------------------------------------------------------------- currencyRateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     */
    @Nullable
    public Integer getCurrencyRateId() {
        return currencyRateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute with the specified value.
     *
     * @param currencyRateId new value for {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     */
    protected void setCurrencyRateId(@Nullable final Integer currencyRateId) {
        this.currencyRateId = currencyRateId;
    }

    // ------------------------------------------------------------------------------------------------ currencyRateDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getCurrencyRateDate() {
        return currencyRateDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute with the specified value.
     *
     * @param currencyRateDate new value for {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute.
     */
    public void setCurrencyRateDate(@Nonnull final LocalDateTime currencyRateDate) {
        this.currencyRateDate = currencyRateDate;
    }

    // ------------------------------------------------------------------------------------------------ fromCurrencyCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute.
     */
    @Nonnull
    public String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param fromCurrencyCode new value for {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute.
     */
    protected void setFromCurrencyCode(@Nonnull final String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    // -------------------------------------------------------------------------------------------------- toCurrencyCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute.
     */
    @Nonnull
    public String getToCurrencyCode() {
        return toCurrencyCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param toCurrencyCode new value for {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute.
     */
    protected void setToCurrencyCode(@Nonnull final String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    // ----------------------------------------------------------------------------------------------------- averageRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute.
     */
    @Nonnull
    public BigDecimal getAverageRate() {
        return averageRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute with the specified value.
     *
     * @param averageRate new value for {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute.
     */
    public void setAverageRate(@Nonnull final BigDecimal averageRate) {
        this.averageRate = averageRate;
    }

    // ---------------------------------------------------------------------------------------------------- endOfDayRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute.
     */
    @Nonnull
    public BigDecimal getEndOfDayRate() {
        return endOfDayRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute with the specified value.
     *
     * @param endOfDayRate new value for {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute.
     */
    public void setEndOfDayRate(@Nonnull final BigDecimal endOfDayRate) {
        this.endOfDayRate = endOfDayRate;
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
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CURRENCY_RATE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer currencyRateId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_CURRENCY_RATE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime currencyRateDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @Size(max = COLUMN_LENGTH_FROM_CURRENCY_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_FROM_CURRENCY_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FROM_CURRENCY_CODE
    )
    private String fromCurrencyCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @Size(max = COLUMN_LENGTH_TO_CURRENCY_CODE)
    @NotNull
    @Column(name = COLUMN_NAME_TO_CURRENCY_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_TO_CURRENCY_CODE
    )
    private String toCurrencyCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_AVERAGE_RATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal averageRate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_END_OF_DAY_RATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal endOfDayRate;

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

