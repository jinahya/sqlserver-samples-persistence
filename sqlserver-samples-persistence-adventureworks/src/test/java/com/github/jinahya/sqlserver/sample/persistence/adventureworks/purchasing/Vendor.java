package com.github.jinahya.sqlserver.sample.persistence.adventureworks.purchasing;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedBusinessEntity;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedVendor;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.BusinessEntity;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Purchasing.Vendor} table.
 */
@Entity(name = "PurchasingVendor")
@Table(schema = MappedVendor.TABLE_SCHEMA,
       name = MappedVendor.TABLE_NAME,
       comment = "Companies from whom Adventure Works Cycles purchases parts or other goods.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_Vendor_AccountNumber",
                       columnNames = {
                               Vendor.COLUMN_NAME_ACCOUNT_NUMBER
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_Vendor_CreditRating",
                       constraint = "([CreditRating]>=(1) AND [CreditRating]<=(5))"
               )
       }
)
public class Vendor
        extends MappedVendor {

    /**
     * The name of the attribute of the {@code FK_Vendor_BusinessEntity_BusinessEntityID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY = "businessEntity";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected Vendor() {
        super();
    }

    // ------------------------------------------------------------------------------ businessEntity

    /**
     * Returns the {@code BusinessEntity} which the
     * {@code FK_Vendor_BusinessEntity_BusinessEntityID} foreign key references.
     *
     * @return the {@code BusinessEntity} referenced; {@code null} when none is.
     */
    public BusinessEntity getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Replaces the {@code BusinessEntity} which the
     * {@code FK_Vendor_BusinessEntity_BusinessEntityID} foreign key references, and the value of
     * the column it consists of.
     *
     * @param businessEntity the {@code BusinessEntity} to reference; {@code null} for none.
     */
    public void setBusinessEntity(final BusinessEntity businessEntity) {
        this.businessEntity = businessEntity;
        setBusinessEntityId(businessEntity == null ? null : businessEntity.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
                referencedColumnName = MappedBusinessEntity.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private BusinessEntity businessEntity;
}

