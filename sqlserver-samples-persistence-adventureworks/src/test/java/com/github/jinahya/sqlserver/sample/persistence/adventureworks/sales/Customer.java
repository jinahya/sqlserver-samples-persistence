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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedPerson;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedCustomer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTerritory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedStore;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.Customer} table.
 */
@Entity(name = "SalesCustomer")
@Table(schema = MappedCustomer.TABLE_SCHEMA,
       name = MappedCustomer.TABLE_NAME,
       comment = "Current customer information. Also see the Person and Store tables.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Customer_AccountNumber",
                       columnNames = {
                               Customer.COLUMN_NAME_ACCOUNT_NUMBER
                       }
               ),
               @UniqueConstraint(
                       name = "AK_Customer_rowguid",
                       columnNames = {
                               Customer.COLUMN_NAME_ROWGUID
                       }
               )
       }
)
public class Customer
        extends MappedCustomer {

    /**
     * The name of the attribute of the {@code FK_Customer_Person_PersonID} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERSON = "person";

    /**
     * The name of the attribute of the {@code FK_Customer_Store_StoreID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    /**
     * The name of the attribute of the {@code FK_Customer_SalesTerritory_TerritoryID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY = "territory";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Customer() {
        super();
    }

    // -------------------------------------------------------------------------------------- person

    /**
     * Returns the {@code Person} which the {@code FK_Customer_Person_PersonID} foreign key
     * references.
     *
     * @return the {@code Person} referenced; {@code null} when none is.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Replaces the {@code Person} which the {@code FK_Customer_Person_PersonID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param person the {@code Person} to reference; {@code null} for none.
     */
    public void setPerson(final Person person) {
        this.person = person;
        setPersonId(person == null ? null : person.getBusinessEntityId());
    }

    // --------------------------------------------------------------------------------------- store

    /**
     * Returns the {@code Store} which the {@code FK_Customer_Store_StoreID} foreign key
     * references.
     *
     * @return the {@code Store} referenced; {@code null} when none is.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Replaces the {@code Store} which the {@code FK_Customer_Store_StoreID} foreign key
     * references, and the value of the column it consists of.
     *
     * @param store the {@code Store} to reference; {@code null} for none.
     */
    public void setStore(final Store store) {
        this.store = store;
        setStoreId(store == null ? null : store.getBusinessEntityId());
    }

    // ----------------------------------------------------------------------------------- territory

    /**
     * Returns the {@code SalesTerritory} which the {@code FK_Customer_SalesTerritory_TerritoryID}
     * foreign key references.
     *
     * @return the {@code SalesTerritory} referenced; {@code null} when none is.
     */
    public SalesTerritory getTerritory() {
        return territory;
    }

    /**
     * Replaces the {@code SalesTerritory} which the {@code FK_Customer_SalesTerritory_TerritoryID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param territory the {@code SalesTerritory} to reference; {@code null} for none.
     */
    public void setTerritory(final SalesTerritory territory) {
        this.territory = territory;
        setTerritoryId(territory == null ? null : territory.getTerritoryId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PERSON_ID,
                referencedColumnName = MappedPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Person person;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STORE_ID,
                referencedColumnName = MappedStore.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private Store store;

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

