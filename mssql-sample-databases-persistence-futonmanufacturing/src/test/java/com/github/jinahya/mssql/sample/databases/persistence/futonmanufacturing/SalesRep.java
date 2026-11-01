package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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

import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.MappedSalesRep;
import com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.MappedSalesTerritory;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.SalesRep} table.
 */
@Entity
@Table(schema = MappedSalesRep.TABLE_SCHEMA, name = MappedSalesRep.TABLE_NAME)
public class SalesRep extends MappedSalesRep {

    /**
     * The name of the attribute of the {@code FK_SalesRep_Territory} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY = "territory";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesRep() {
        super();
    }

    // ------------------------------------------------------------------------------------------------------- territory

    /**
     * Returns the {@code SalesTerritory} which the {@code FK_SalesRep_Territory} foreign key references.
     *
     * @return the {@code SalesTerritory} referenced; {@code null} when none is.
     */
    @Nullable
    public SalesTerritory getTerritory() {
        return territory;
    }

    /**
     * Replaces the {@code SalesTerritory} which the {@code FK_SalesRep_Territory} foreign key references, and the
     * value of the column it consists of.
     *
     * @param territory the {@code SalesTerritory} to reference; {@code null} for none.
     */
    public void setTerritory(@Nullable final SalesTerritory territory) {
        this.territory = territory;
        setTerritoryId(territory == null ? null : territory.getTerritoryId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TERRITORY_ID,
                referencedColumnName = MappedSalesTerritory.COLUMN_NAME_TERRITORY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesTerritory territory;
}

