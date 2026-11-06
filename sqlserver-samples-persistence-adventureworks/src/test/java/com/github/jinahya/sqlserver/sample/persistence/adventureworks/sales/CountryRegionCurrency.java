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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedCountryRegion;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.CountryRegionCurrencyId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCountryRegionCurrency;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCurrency;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.CountryRegion;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity which maps the {@code Sales.CountryRegionCurrency} table.
 * <p>
 * Each attribute of the embedded {@link CountryRegionCurrencyId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "SalesCountryRegionCurrency")
@Table(schema = MappedCountryRegionCurrency.TABLE_SCHEMA,
       name = MappedCountryRegionCurrency.TABLE_NAME,
       comment = "Cross-reference table mapping ISO currency codes to a country or region.")
public class CountryRegionCurrency
        extends MappedCountryRegionCurrency {

    /**
     * The name of the attribute of the
     * {@code FK_CountryRegionCurrency_CountryRegion_CountryRegionCode} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION = "countryRegion";

    /**
     * The name of the attribute of the {@code FK_CountryRegionCurrency_Currency_CurrencyCode}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CURRENCY = "currency";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code countryRegion} and {@code currency}.
     *
     * @param countryRegion the {@code CountryRegion} to reference.
     * @param currency      the {@code Currency} to reference.
     * @return a new instance which references {@code countryRegion} and {@code currency}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static CountryRegionCurrency of(final CountryRegion countryRegion,
                                           final Currency currency) {
        Objects.requireNonNull(countryRegion, "countryRegion is null");
        Objects.requireNonNull(currency, "currency is null");
        final var instance = new CountryRegionCurrency();
        instance.countryRegion = countryRegion;
        instance.currency = currency;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected CountryRegionCurrency() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected CountryRegionCurrencyId getId_() {
        return id_;
    }

    // ------------------------------------------------------------------------------- countryRegion

    /**
     * Returns the {@code CountryRegion} which the
     * {@code FK_CountryRegionCurrency_CountryRegion_CountryRegionCode} foreign key references.
     *
     * @return the {@code CountryRegion} referenced; {@code null} when none is.
     */
    public CountryRegion getCountryRegion() {
        return countryRegion;
    }

    // ------------------------------------------------------------------------------------ currency

    /**
     * Returns the {@code Currency} which the {@code FK_CountryRegionCurrency_Currency_CurrencyCode}
     * foreign key references.
     *
     * @return the {@code Currency} referenced; {@code null} when none is.
     */
    public Currency getCurrency() {
        return currency;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private CountryRegionCurrencyId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(CountryRegionCurrencyId.ATTRIBUTE_NAME_COUNTRY_REGION_CODE)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_REGION_CODE,
                referencedColumnName = MappedCountryRegion.COLUMN_NAME_COUNTRY_REGION_CODE,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private CountryRegion countryRegion;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(CountryRegionCurrencyId.ATTRIBUTE_NAME_CURRENCY_CODE)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CURRENCY_CODE,
                referencedColumnName = MappedCurrency.COLUMN_NAME_CURRENCY_CODE,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Currency currency;
}

