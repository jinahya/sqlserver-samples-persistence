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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSpecialOffer;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.SpecialOffer} table.
 */
@Entity(name = "SalesSpecialOffer")
@Table(schema = MappedSpecialOffer.TABLE_SCHEMA,
       name = MappedSpecialOffer.TABLE_NAME,
       comment = "Sale discounts lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SpecialOffer_rowguid",
                       columnNames = {
                               SpecialOffer.COLUMN_NAME_ROWGUID
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SpecialOffer_DiscountPct",
                       constraint = "([DiscountPct]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_SpecialOffer_EndDate",
                       constraint = "([EndDate]>=[StartDate])"
               ),
               @CheckConstraint(
                       name = "CK_SpecialOffer_MaxQty",
                       constraint = "([MaxQty]>=(0))"
               ),
               @CheckConstraint(
                       name = "CK_SpecialOffer_MinQty",
                       constraint = "([MinQty]>=(0))"
               )
       }
)
public class SpecialOffer
        extends MappedSpecialOffer {

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SpecialOffer() {
        super();
    }
}

