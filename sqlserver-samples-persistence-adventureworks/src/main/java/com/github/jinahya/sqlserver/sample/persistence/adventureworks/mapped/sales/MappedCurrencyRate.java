package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.CurrencyRate} table.
 * <p>
 * The {@value #COLUMN_NAME_FROM_CURRENCY_CODE}, {@value #COLUMN_NAME_TO_CURRENCY_CODE} columns are
 * mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.CurrencyRate}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CurrencyRateID}</th>
 *     <td>Primary key for CurrencyRate records.</td></tr>
 * <tr><th scope="row">{@code CurrencyRateDate}</th>
 *     <td>Date and time the exchange rate was obtained.</td></tr>
 * <tr><th scope="row">{@code FromCurrencyCode}</th>
 *     <td>Exchange rate was converted from this currency code.</td></tr>
 * <tr><th scope="row">{@code ToCurrencyCode}</th>
 *     <td>Exchange rate was converted to this currency code.</td></tr>
 * <tr><th scope="row">{@code AverageRate}</th><td>Average exchange rate for the day.</td></tr>
 * <tr><th scope="row">{@code EndOfDayRate}</th><td>Final exchange rate for the day.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.CurrencyRate}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedCurrencyRate.TABLE_SCHEMA,
        table = MappedCurrencyRate.TABLE_NAME,
        value = "Currency exchange rates."
)
public abstract class MappedCurrencyRate {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CurrencyRate";

    // ------------------------------------------------------------------------------ CurrencyRateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENCY_RATE_ID = "CurrencyRateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENCY_RATE_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_RATE_ID = "currencyRateId";

    // ---------------------------------------------------------------------------- CurrencyRateDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CURRENCY_RATE_DATE = "CurrencyRateDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CURRENCY_RATE_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_RATE_DATE = "currencyRateDate";

    // ---------------------------------------------------------------------------- FromCurrencyCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FROM_CURRENCY_CODE = "FromCurrencyCode";

    /**
     * The length of the {@value #COLUMN_NAME_FROM_CURRENCY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FROM_CURRENCY_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FROM_CURRENCY_CODE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FROM_CURRENCY_CODE = "fromCurrencyCode";

    // ------------------------------------------------------------------------------ ToCurrencyCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TO_CURRENCY_CODE = "ToCurrencyCode";

    /**
     * The length of the {@value #COLUMN_NAME_TO_CURRENCY_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TO_CURRENCY_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TO_CURRENCY_CODE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TO_CURRENCY_CODE = "toCurrencyCode";

    // --------------------------------------------------------------------------------- AverageRate

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVERAGE_RATE = "AverageRate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVERAGE_RATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVERAGE_RATE = "averageRate";

    // -------------------------------------------------------------------------------- EndOfDayRate

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_OF_DAY_RATE = "EndOfDayRate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_OF_DAY_RATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_OF_DAY_RATE = "endOfDayRate";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCurrencyRate() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedCurrencyRate that)) {
            return false;
        }
        return getCurrencyRateId() != null
               && getCurrencyRateId().equals(that.getCurrencyRateId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedCurrencyRate.class.hashCode();
    }

    // ------------------------------------------------------------------------------ currencyRateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     */
    public Integer getCurrencyRateId() {
        return currencyRateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute with the
     * specified value.
     *
     * @param currencyRateId new value for {@value #ATTRIBUTE_NAME_CURRENCY_RATE_ID} attribute.
     */
    protected void setCurrencyRateId(final Integer currencyRateId) {
        this.currencyRateId = currencyRateId;
    }

    // ---------------------------------------------------------------------------- currencyRateDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute.
     */
    public LocalDateTime getCurrencyRateDate() {
        return currencyRateDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute with the
     * specified value.
     *
     * @param currencyRateDate new value for {@value #ATTRIBUTE_NAME_CURRENCY_RATE_DATE} attribute.
     */
    public void setCurrencyRateDate(final LocalDateTime currencyRateDate) {
        this.currencyRateDate = currencyRateDate;
    }

    // ---------------------------------------------------------------------------- fromCurrencyCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute.
     */
    protected String getFromCurrencyCode() {
        return fromCurrencyCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param fromCurrencyCode new value for {@value #ATTRIBUTE_NAME_FROM_CURRENCY_CODE} attribute.
     */
    protected void setFromCurrencyCode(final String fromCurrencyCode) {
        this.fromCurrencyCode = fromCurrencyCode;
    }

    // ------------------------------------------------------------------------------ toCurrencyCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute.
     */
    protected String getToCurrencyCode() {
        return toCurrencyCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param toCurrencyCode new value for {@value #ATTRIBUTE_NAME_TO_CURRENCY_CODE} attribute.
     */
    protected void setToCurrencyCode(final String toCurrencyCode) {
        this.toCurrencyCode = toCurrencyCode;
    }

    // --------------------------------------------------------------------------------- averageRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute.
     */
    public BigDecimal getAverageRate() {
        return averageRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute with the specified
     * value.
     *
     * @param averageRate new value for {@value #ATTRIBUTE_NAME_AVERAGE_RATE} attribute.
     */
    public void setAverageRate(final BigDecimal averageRate) {
        this.averageRate = averageRate;
    }

    // -------------------------------------------------------------------------------- endOfDayRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute.
     */
    public BigDecimal getEndOfDayRate() {
        return endOfDayRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute with the
     * specified value.
     *
     * @param endOfDayRate new value for {@value #ATTRIBUTE_NAME_END_OF_DAY_RATE} attribute.
     */
    public void setEndOfDayRate(final BigDecimal endOfDayRate) {
        this.endOfDayRate = endOfDayRate;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CURRENCY_RATE_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Primary key for CurrencyRate records.")
    private Integer currencyRateId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CURRENCY_RATE_DATE, nullable = false, insertable = true,
            updatable = true,
            comment = "Date and time the exchange rate was obtained.")
    private LocalDateTime currencyRateDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Size(max = COLUMN_LENGTH_FROM_CURRENCY_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FROM_CURRENCY_CODE, nullable = false, insertable = false,
            updatable = false, length = COLUMN_LENGTH_FROM_CURRENCY_CODE,
            comment = "Exchange rate was converted from this currency code.")
    private String fromCurrencyCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Size(max = COLUMN_LENGTH_TO_CURRENCY_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TO_CURRENCY_CODE, nullable = false, insertable = false,
            updatable = false, length = COLUMN_LENGTH_TO_CURRENCY_CODE,
            comment = "Exchange rate was converted to this currency code.")
    private String toCurrencyCode;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(SqlServerConstants.MONEY_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AVERAGE_RATE, nullable = false, insertable = true, updatable = true, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Average exchange rate for the day.")
    private BigDecimal averageRate;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(SqlServerConstants.MONEY_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_OF_DAY_RATE, nullable = false, insertable = true,
            updatable = true, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Final exchange rate for the day.")
    private BigDecimal endOfDayRate;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_CurrencyRate_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

