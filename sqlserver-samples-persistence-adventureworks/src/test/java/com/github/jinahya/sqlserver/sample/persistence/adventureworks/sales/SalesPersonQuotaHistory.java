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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesPersonQuotaHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.SalesPersonQuotaHistoryId;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Sales.SalesPersonQuotaHistory} table.
 */
@Entity(name = "SalesSalesPersonQuotaHistory")
@Table(schema = MappedSalesPersonQuotaHistory.TABLE_SCHEMA,
       name = MappedSalesPersonQuotaHistory.TABLE_NAME,
       comment = "Sales performance tracking.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SalesPersonQuotaHistory_rowguid",
                       columnNames = {
                               SalesPersonQuotaHistory.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SalesPersonQuotaHistory_SalesQuota",
                       constraint = "([SalesQuota]>(0.00))"
               )
       }
)
@IdClass(SalesPersonQuotaHistoryId.class)
public class SalesPersonQuotaHistory
        extends MappedSalesPersonQuotaHistory {

    /**
     * The name of the attribute of the
     * {@code FK_SalesPersonQuotaHistory_SalesPerson_BusinessEntityID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code businessEntity}, with {@code quotaDate}.
     *
     * @param businessEntity the {@code SalesPerson} to reference.
     * @param quotaDate      the value of the {@value #COLUMN_NAME_QUOTA_DATE} column.
     * @return a new instance which references {@code businessEntity}, with {@code quotaDate}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static SalesPersonQuotaHistory of(final SalesPerson businessEntity,
                                             final LocalDateTime quotaDate) {
        Objects.requireNonNull(businessEntity, "businessEntity is null");
        Objects.requireNonNull(quotaDate, "quotaDate is null");
        final var instance = new SalesPersonQuotaHistory();
        instance.setBusinessEntity(businessEntity);
        instance.setQuotaDate(quotaDate);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesPersonQuotaHistory() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code SalesPerson} which the
     * {@code FK_SalesPersonQuotaHistory_SalesPerson_BusinessEntityID} foreign key references.
     *
     * @return the {@code SalesPerson} referenced; {@code null} when none is.
     */
    public SalesPerson getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code SalesPerson} which the
     * {@code FK_SalesPersonQuotaHistory_SalesPerson_BusinessEntityID} foreign key references, and
     * the value of the column it consists of.
     *
     * @param businessEntity the {@code SalesPerson} to reference; {@code null} for none.
     */
    private void setBusinessEntity(final SalesPerson businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(
                Optional.ofNullable(businessEntity).map(SalesPerson::getBusinessEntityId)
                        .orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedSalesPerson.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SalesPerson businessEntity;
}

