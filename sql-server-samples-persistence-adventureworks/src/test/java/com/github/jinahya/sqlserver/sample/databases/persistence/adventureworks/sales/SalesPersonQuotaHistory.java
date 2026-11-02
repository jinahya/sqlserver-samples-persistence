package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.sales;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesPerson;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesPersonQuotaHistory;
import com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales.MappedSalesPersonQuotaHistoryId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.SalesPersonQuotaHistory} table.
 */
@Entity(name = "SalesSalesPersonQuotaHistory")
@Table(schema = MappedSalesPersonQuotaHistory.TABLE_SCHEMA, name = MappedSalesPersonQuotaHistory.TABLE_NAME)
@IdClass(MappedSalesPersonQuotaHistoryId.class)
public class SalesPersonQuotaHistory extends MappedSalesPersonQuotaHistory {

    /**
     * The name of the attribute of the {@code FK_SalesPersonQuotaHistory_SalesPerson_BusinessEntityID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesPersonQuotaHistory() {
        super();
    }

    // -------------------------------------------------------------------------------------------------- businessEntity

    /**
     * Returns the {@code SalesPerson} which the {@code FK_SalesPersonQuotaHistory_SalesPerson_BusinessEntityID} foreign
     * key references.
     *
     * @return the {@code SalesPerson} referenced; {@code null} when none is.
     */
    public SalesPerson getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code SalesPerson} which the {@code FK_SalesPersonQuotaHistory_SalesPerson_BusinessEntityID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param businessEntity the {@code SalesPerson} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final SalesPerson businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedSalesPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesPerson businessEntity;
}

