package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCurrency;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCurrencyRate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.CurrencyRate} table.
 */
@Entity(name = "SalesCurrencyRate")
@Table(schema = MappedCurrencyRate.TABLE_SCHEMA,
       name = MappedCurrencyRate.TABLE_NAME,
       comment = "Currency exchange rates.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_CurrencyRate_CurrencyRateDate_FromCurrencyCode_ToCurrencyCode",
                       columnNames = {
                               CurrencyRate.COLUMN_NAME_CURRENCY_RATE_DATE,
                               CurrencyRate.COLUMN_NAME_FROM_CURRENCY_CODE,
                               CurrencyRate.COLUMN_NAME_TO_CURRENCY_CODE
                       }
               )
       }
)
public class CurrencyRate
        extends MappedCurrencyRate {

    /**
     * The name of the attribute of the {@code FK_CurrencyRate_Currency_FromCurrencyCode} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FROM_CURRENCY = "fromCurrency";

    /**
     * The name of the attribute of the {@code FK_CurrencyRate_Currency_ToCurrencyCode} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TO_CURRENCY = "toCurrency";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CurrencyRate() {
        super();
    }

    // -------------------------------------------------------------------------------- fromCurrency

    /**
     * Returns the {@code Currency} which the {@code FK_CurrencyRate_Currency_FromCurrencyCode}
     * foreign key references.
     *
     * @return the {@code Currency} referenced; {@code null} when none is.
     */
    public Currency getFromCurrency() {
        return fromCurrency;
    }

    /**
     * Replaces the {@code Currency} which the {@code FK_CurrencyRate_Currency_FromCurrencyCode}
     * foreign key references, and the value of the column it consists of.
     *
     * @param fromCurrency the {@code Currency} to reference; {@code null} for none.
     */
    public void setFromCurrency(final Currency fromCurrency) {
        this.fromCurrency = fromCurrency;
        setFromCurrencyCode(fromCurrency == null ? null : fromCurrency.getCurrencyCode());
    }

    // ---------------------------------------------------------------------------------- toCurrency

    /**
     * Returns the {@code Currency} which the {@code FK_CurrencyRate_Currency_ToCurrencyCode}
     * foreign key references.
     *
     * @return the {@code Currency} referenced; {@code null} when none is.
     */
    public Currency getToCurrency() {
        return toCurrency;
    }

    /**
     * Replaces the {@code Currency} which the {@code FK_CurrencyRate_Currency_ToCurrencyCode}
     * foreign key references, and the value of the column it consists of.
     *
     * @param toCurrency the {@code Currency} to reference; {@code null} for none.
     */
    public void setToCurrency(final Currency toCurrency) {
        this.toCurrency = toCurrency;
        setToCurrencyCode(toCurrency == null ? null : toCurrency.getCurrencyCode());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_FROM_CURRENCY_CODE,
                referencedColumnName = MappedCurrency.COLUMN_NAME_CURRENCY_CODE,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Currency fromCurrency;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TO_CURRENCY_CODE,
                referencedColumnName = MappedCurrency.COLUMN_NAME_CURRENCY_CODE,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Currency toCurrency;
}

