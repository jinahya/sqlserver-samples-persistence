package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.dbo;

/*-
 * #%L
 * sqlserver-samples-persistence-futonmanufacturing
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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesRep;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedSalesTerritory;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code dbo.Customer} table.
 */
@Entity(name = "DboCustomer")
@Table(schema = MappedCustomer.TABLE_SCHEMA,
       name = MappedCustomer.TABLE_NAME,
       uniqueConstraints = {
               @UniqueConstraint(
                       columnNames = {
                               Customer.COLUMN_NAME_CUSTOMER_CODE
                       }
               )
       }
)
public class Customer extends MappedCustomer {

    /**
     * The name of the attribute of the {@code FK_Customer_SalesRep} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP = "salesRep";

    /**
     * The name of the attribute of the {@code FK_Customer_Territory} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY = "territory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
        super();
    }

    // ------------------------------------------------------------------------------------ salesRep

    /**
     * Returns the {@code SalesRep} which the {@code FK_Customer_SalesRep} foreign key references.
     *
     * @return the {@code SalesRep} referenced; {@code null} when none is.
     */
    public SalesRep getSalesRep() {
        return salesRep;
    }

    /**
     * Replaces the {@code SalesRep} which the {@code FK_Customer_SalesRep} foreign key references, and the value of the
     * column it consists of.
     *
     * @param salesRep the {@code SalesRep} to reference; {@code null} for none.
     */
    public void setSalesRep(final SalesRep salesRep) {
        this.salesRep = salesRep;
        setSalesRepId(salesRep == null ? null : salesRep.getSalesRepId());
    }

    // ----------------------------------------------------------------------------------- territory

    /**
     * Returns the {@code SalesTerritory} which the {@code FK_Customer_Territory} foreign key references.
     *
     * @return the {@code SalesTerritory} referenced; {@code null} when none is.
     */
    public SalesTerritory getTerritory() {
        return territory;
    }

    /**
     * Replaces the {@code SalesTerritory} which the {@code FK_Customer_Territory} foreign key references, and the value
     * of the column it consists of.
     *
     * @param territory the {@code SalesTerritory} to reference; {@code null} for none.
     */
    public void setTerritory(final SalesTerritory territory) {
        this.territory = territory;
        setTerritoryId(territory == null ? null : territory.getTerritoryId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SALES_REP_ID,
                referencedColumnName = MappedSalesRep.COLUMN_NAME_SALES_REP_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesRep salesRep;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_TERRITORY_ID,
                referencedColumnName = MappedSalesTerritory.COLUMN_NAME_TERRITORY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private SalesTerritory territory;
}

