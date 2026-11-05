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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.CountryRegion;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.SalesTerritory} table.
 */
@Entity(name = "SalesSalesTerritory")
@Table(schema = MappedSalesTerritory.TABLE_SCHEMA,
       name = MappedSalesTerritory.TABLE_NAME,
       comment = "Sales territory lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SalesTerritory_Name",
                       columnNames = {
                               SalesTerritory.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_SalesTerritory_rowguid",
                       columnNames = {
                               SalesTerritory.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SalesTerritory_CostLastYear",
                       constraint = "([CostLastYear]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SalesTerritory_CostYTD",
                       constraint = "([CostYTD]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SalesTerritory_SalesLastYear",
                       constraint = "([SalesLastYear]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SalesTerritory_SalesYTD",
                       constraint = "([SalesYTD]>=(0.00))"
               )
       }
)
public class SalesTerritory
        extends MappedSalesTerritory {

    /**
     * The name of the attribute of the {@code FK_SalesTerritory_CountryRegion_CountryRegionCode}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION = "countryRegion";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesTerritory() {
        super();
    }

    // ------------------------------------------------------------------------------- countryRegion

    /**
     * Returns the {@code CountryRegion} which the
     * {@code FK_SalesTerritory_CountryRegion_CountryRegionCode} foreign key references.
     *
     * @return the {@code CountryRegion} referenced; {@code null} when none is.
     */
    public CountryRegion getCountryRegion() {
        return countryRegion;
    }

    /**
     * Replaces the {@code CountryRegion} which the
     * {@code FK_SalesTerritory_CountryRegion_CountryRegionCode} foreign key references, and the
     * value of the column it consists of.
     *
     * @param countryRegion the {@code CountryRegion} to reference; {@code null} for none.
     */
    public void setCountryRegion(final CountryRegion countryRegion) {
        this.countryRegion = countryRegion;
        setCountryRegionCode(countryRegion == null ? null : countryRegion.getCountryRegionCode());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_REGION_CODE,
                referencedColumnName = MappedCountryRegion.COLUMN_NAME_COUNTRY_REGION_CODE,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private CountryRegion countryRegion;
}

