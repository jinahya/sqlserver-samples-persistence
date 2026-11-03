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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritoryHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritoryHistoryId;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.SalesTerritoryHistory} table.
 */
@Entity(name = "SalesSalesTerritoryHistory")
@Table(schema = MappedSalesTerritoryHistory.TABLE_SCHEMA,
       name = MappedSalesTerritoryHistory.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SalesTerritoryHistory_rowguid",
                       columnNames = {
                               SalesTerritoryHistory.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SalesTerritoryHistory_EndDate",
                       constraint = "([EndDate]>=[StartDate] OR [EndDate] IS NULL)"
               )
       }
)
@IdClass(MappedSalesTerritoryHistoryId.class)
public class SalesTerritoryHistory extends MappedSalesTerritoryHistory {

    /**
     * The name of the attribute of the {@code FK_SalesTerritoryHistory_SalesPerson_BusinessEntityID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    /**
     * The name of the attribute of the {@code FK_SalesTerritoryHistory_SalesTerritory_TerritoryID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY = "territory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesTerritoryHistory() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code SalesPerson} which the {@code FK_SalesTerritoryHistory_SalesPerson_BusinessEntityID} foreign
     * key references.
     *
     * @return the {@code SalesPerson} referenced; {@code null} when none is.
     */
    public SalesPerson getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code SalesPerson} which the {@code FK_SalesTerritoryHistory_SalesPerson_BusinessEntityID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code SalesPerson} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final SalesPerson businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ----------------------------------------------------------------------------------- territory

    /**
     * Returns the {@code SalesTerritory} which the {@code FK_SalesTerritoryHistory_SalesTerritory_TerritoryID} foreign
     * key references.
     *
     * @return the {@code SalesTerritory} referenced; {@code null} when none is.
     */
    public SalesTerritory getTerritory() {
        return territory;
    }

    /**
     * Replaces the {@code SalesTerritory} which the {@code FK_SalesTerritoryHistory_SalesTerritory_TerritoryID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param territory the {@code SalesTerritory} to reference; {@code null} for none.
     */
    public void setTerritory(final SalesTerritory territory) {
        this.territory = territory;
        setTerritoryId(territory == null ? null : territory.getTerritoryId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedSalesPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesPerson businessEntity;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TERRITORY_ID,
                referencedColumnName = MappedSalesTerritory.COLUMN_NAME_TERRITORY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesTerritory territory;
}

