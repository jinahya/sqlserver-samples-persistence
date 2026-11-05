package com.github.jinahya.sqlserver.sample.persistence.adventureworks.person;

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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedStateProvince;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales.SalesTerritory;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Person.StateProvince} table.
 */
@Entity(name = "PersonStateProvince")
@Table(schema = MappedStateProvince.TABLE_SCHEMA,
       name = MappedStateProvince.TABLE_NAME,
       comment = "State and province lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_StateProvince_Name",
                       columnNames = {
                               StateProvince.COLUMN_NAME_NAME
                       }
               ),
               @UniqueConstraint(
                       name = "AK_StateProvince_rowguid",
                       columnNames = {
                               StateProvince.COLUMN_NAME_ROWGUID
                       }
               ),
               @UniqueConstraint(
                       name = "AK_StateProvince_StateProvinceCode_CountryRegionCode",
                       columnNames = {
                               StateProvince.COLUMN_NAME_STATE_PROVINCE_CODE,
                               StateProvince.COLUMN_NAME_COUNTRY_REGION_CODE
                       }
               )
       }
)
public class StateProvince
        extends MappedStateProvince {

    /**
     * The name of the attribute of the {@code FK_StateProvince_CountryRegion_CountryRegionCode}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY_REGION = "countryRegion";

    /**
     * The name of the attribute of the {@code FK_StateProvince_SalesTerritory_TerritoryID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY = "territory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected StateProvince() {
        super();
    }

    // ------------------------------------------------------------------------------- countryRegion

    /**
     * Returns the {@code CountryRegion} which the
     * {@code FK_StateProvince_CountryRegion_CountryRegionCode} foreign key references.
     *
     * @return the {@code CountryRegion} referenced; {@code null} when none is.
     */
    public CountryRegion getCountryRegion() {
        return countryRegion;
    }

    /**
     * Replaces the {@code CountryRegion} which the
     * {@code FK_StateProvince_CountryRegion_CountryRegionCode} foreign key references, and the
     * value of the column it consists of.
     *
     * @param countryRegion the {@code CountryRegion} to reference; {@code null} for none.
     */
    public void setCountryRegion(final CountryRegion countryRegion) {
        this.countryRegion = countryRegion;
        setCountryRegionCode(countryRegion == null ? null : countryRegion.getCountryRegionCode());
    }

    // ----------------------------------------------------------------------------------- territory

    /**
     * Returns the {@code SalesTerritory} which the
     * {@code FK_StateProvince_SalesTerritory_TerritoryID} foreign key references.
     *
     * @return the {@code SalesTerritory} referenced; {@code null} when none is.
     */
    public SalesTerritory getTerritory() {
        return territory;
    }

    /**
     * Replaces the {@code SalesTerritory} which the
     * {@code FK_StateProvince_SalesTerritory_TerritoryID} foreign key references, and the value of
     * the column it consists of.
     *
     * @param territory the {@code SalesTerritory} to reference; {@code null} for none.
     */
    public void setTerritory(final SalesTerritory territory) {
        this.territory = territory;
        setTerritoryId(territory == null ? null : territory.getTerritoryId());
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

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TERRITORY_ID,
                referencedColumnName = MappedSalesTerritory.COLUMN_NAME_TERRITORY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private SalesTerritory territory;
}

