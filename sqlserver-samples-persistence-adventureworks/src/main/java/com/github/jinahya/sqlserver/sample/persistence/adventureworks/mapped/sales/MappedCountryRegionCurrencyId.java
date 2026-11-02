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

import java.io.Serializable;
import java.util.Objects;

/**
 * The identifier class of the {@code Sales.CountryRegionCurrency} table, whose rows are identified by
 * {@code CountryRegionCode}, {@code CurrencyCode}.
 * <p>
 * An entity which extends {@link MappedCountryRegionCurrency} names this class with
 * {@link jakarta.persistence.IdClass @IdClass}; its attributes are the same as the mapped superclass's
 * {@link jakarta.persistence.Id @Id} attributes, by name and by type.
 */
public class MappedCountryRegionCurrencyId implements Serializable {

    private static final long serialVersionUID = 1L;

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public MappedCountryRegionCurrencyId() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "countryRegionCode=" + countryRegionCode
               + ",currencyCode=" + currencyCode
               + "}";
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedCountryRegionCurrencyId that)) {
            return false;
        }
        return Objects.equals(countryRegionCode, that.countryRegionCode) &&
               Objects.equals(currencyCode, that.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryRegionCode, currencyCode);
    }

    // ----------------------------------------------------------------------------------------------- countryRegionCode

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

    // ---------------------------------------------------------------------------------------------------- currencyCode

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

    // -----------------------------------------------------------------------------------------------------------------
    private String countryRegionCode;

    private String currencyCode;
}

