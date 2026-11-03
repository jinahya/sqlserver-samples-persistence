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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * The identifier class of the {@code Sales.CountryRegionCurrency} table, whose rows are identified
 * by {@code CountryRegionCode}, {@code CurrencyCode}.
 * <p>
 * {@link MappedCountryRegionCurrency} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class CountryRegionCurrencyId {

    /**
     * The name of the attribute which maps the
     * {@value MappedCountryRegionCurrency#COLUMN_NAME_COUNTRY_REGION_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION_CODE = "countryRegionCode";

    /**
     * The name of the attribute which maps the
     * {@value MappedCountryRegionCurrency#COLUMN_NAME_CURRENCY_CODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY_CODE = "currencyCode";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public CountryRegionCurrencyId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "countryRegionCode=" + countryRegionCode
               + ",currencyCode=" + currencyCode
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CountryRegionCurrencyId that)) {
            return false;
        }
        return Objects.equals(getCountryRegionCode(), that.getCountryRegionCode()) &&
               Objects.equals(getCurrencyCode(), that.getCurrencyCode());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getCountryRegionCode(), getCurrencyCode());
    }

    // --------------------------------------------------------------------------- countryRegionCode

    /**
     * Returns current value of the {@code countryRegionCode} attribute.
     *
     * @return current value of the {@code countryRegionCode} attribute.
     */
    public String getCountryRegionCode() {
        return countryRegionCode;
    }

    /**
     * Replaces current value of the {@code countryRegionCode} attribute with the specified value.
     *
     * @param countryRegionCode new value for the {@code countryRegionCode} attribute.
     */
    public void setCountryRegionCode(final String countryRegionCode) {
        this.countryRegionCode = countryRegionCode;
    }

    // -------------------------------------------------------------------------------- currencyCode

    /**
     * Returns current value of the {@code currencyCode} attribute.
     *
     * @return current value of the {@code currencyCode} attribute.
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Replaces current value of the {@code currencyCode} attribute with the specified value.
     *
     * @param currencyCode new value for the {@code currencyCode} attribute.
     */
    public void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code countryRegionCode} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Size(max = MappedCountryRegionCurrency.COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @NotNull
    @Column(name = MappedCountryRegionCurrency.COLUMN_NAME_COUNTRY_REGION_CODE, nullable = false,
            insertable = true, updatable = false,
            length = MappedCountryRegionCurrency.COLUMN_LENGTH_COUNTRY_REGION_CODE)
    @MsDescription.Column(name = MappedCountryRegionCurrency.COLUMN_NAME_COUNTRY_REGION_CODE,
                          value = "ISO code for countries and regions. Foreign key to "
                                  + "CountryRegion.CountryRegionCode.")
    private String countryRegionCode;

    /**
     * The value of the {@code currencyCode} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Size(max = MappedCountryRegionCurrency.COLUMN_LENGTH_CURRENCY_CODE)
    @NotNull
    @Column(name = MappedCountryRegionCurrency.COLUMN_NAME_CURRENCY_CODE, nullable = false,
            insertable = true, updatable = false,
            length = MappedCountryRegionCurrency.COLUMN_LENGTH_CURRENCY_CODE)
    @MsDescription.Column(name = MappedCountryRegionCurrency.COLUMN_NAME_CURRENCY_CODE,
                          value = "ISO standard currency code. Foreign key to "
                                  + "Currency.CurrencyCode.")
    private String currencyCode;
}

