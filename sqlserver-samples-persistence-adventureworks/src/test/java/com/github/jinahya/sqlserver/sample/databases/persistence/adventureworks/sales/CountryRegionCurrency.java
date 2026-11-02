package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.sales;

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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.person.MappedCountryRegion;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales.MappedCountryRegionCurrency;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales.MappedCountryRegionCurrencyId;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales.MappedCurrency;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.person.CountryRegion;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.CountryRegionCurrency} table.
 */
@Entity(name = "SalesCountryRegionCurrency")
@Table(schema = MappedCountryRegionCurrency.TABLE_SCHEMA, name = MappedCountryRegionCurrency.TABLE_NAME)
@IdClass(MappedCountryRegionCurrencyId.class)
public class CountryRegionCurrency extends MappedCountryRegionCurrency {

    /**
     * The name of the attribute of the {@code FK_CountryRegionCurrency_CountryRegion_CountryRegionCode} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION = "countryRegion";

    /**
     * The name of the attribute of the {@code FK_CountryRegionCurrency_Currency_CurrencyCode} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY = "currency";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CountryRegionCurrency() {
        super();
    }

    // --------------------------------------------------------------------------------------------------- countryRegion

    /**
     * Returns the {@code CountryRegion} which the {@code FK_CountryRegionCurrency_CountryRegion_CountryRegionCode}
     * foreign key references.
     *
     * @return the {@code CountryRegion} referenced; {@code null} when none is.
     */
    public CountryRegion getCountryRegion() {
        return countryRegion;
    }

    /**
     * Replaces the {@code CountryRegion} which the {@code FK_CountryRegionCurrency_CountryRegion_CountryRegionCode}
     * foreign key references, and the value of the column it consists of.
     *
     * @param countryRegion the {@code CountryRegion} to reference; {@code null} for none.
     */
    public void setCountryRegion(final CountryRegion countryRegion) {
        this.countryRegion = countryRegion;
        setCountryRegionCode(countryRegion == null ? null : countryRegion.getCountryRegionCode());
    }

    // -------------------------------------------------------------------------------------------------------- currency

    /**
     * Returns the {@code Currency} which the {@code FK_CountryRegionCurrency_Currency_CurrencyCode} foreign key
     * references.
     *
     * @return the {@code Currency} referenced; {@code null} when none is.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Replaces the {@code Currency} which the {@code FK_CountryRegionCurrency_Currency_CurrencyCode} foreign key
     * references, and the value of the column it consists of.
     *
     * @param currency the {@code Currency} to reference; {@code null} for none.
     */
    public void setCurrency(final Currency currency) {
        this.currency = currency;
        setCurrencyCode(currency == null ? null : currency.getCurrencyCode());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_REGION_CODE,
                referencedColumnName = MappedCountryRegion.COLUMN_NAME_COUNTRY_REGION_CODE,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private CountryRegion countryRegion;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CURRENCY_CODE,
                referencedColumnName = MappedCurrency.COLUMN_NAME_CURRENCY_CODE,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Currency currency;
}

