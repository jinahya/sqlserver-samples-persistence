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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person.MappedStateProvince;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSalesTaxRate;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.person.StateProvince;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * An entity which maps the {@code Sales.SalesTaxRate} table.
 */
@Entity(name = "SalesSalesTaxRate")
@Table(schema = MappedSalesTaxRate.TABLE_SCHEMA,
       name = MappedSalesTaxRate.TABLE_NAME,
       comment = "Tax rate lookup table.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SalesTaxRate_rowguid",
                       columnNames = {
                               SalesTaxRate.COLUMN_NAME_ROWGUID
                       }
               ),
               @UniqueConstraint(
                       name = "AK_SalesTaxRate_StateProvinceID_TaxType",
                       columnNames = {
                               SalesTaxRate.COLUMN_NAME_STATE_PROVINCE_ID,
                               SalesTaxRate.COLUMN_NAME_TAX_TYPE
                       }
               )
       },
       check = {
               @CheckConstraint(
                       name = "CK_SalesTaxRate_TaxType",
                       constraint = "([TaxType]>=(1) AND [TaxType]<=(3))"
               )
       }
)
public class SalesTaxRate
        extends MappedSalesTaxRate {

    /**
     * The name of the attribute of the {@code FK_SalesTaxRate_StateProvince_StateProvinceID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesTaxRate() {
        super();
    }

    // ------------------------------------------------------------------------------- stateProvince

    /**
     * Returns the {@code StateProvince} which the
     * {@code FK_SalesTaxRate_StateProvince_StateProvinceID} foreign key references.
     *
     * @return the {@code StateProvince} referenced; {@code null} when none is.
     */
    public StateProvince getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces the {@code StateProvince} which the
     * {@code FK_SalesTaxRate_StateProvince_StateProvinceID} foreign key references, and the value
     * of the column it consists of.
     *
     * @param stateProvince the {@code StateProvince} to reference; {@code null} for none.
     */
    public void setStateProvince(final StateProvince stateProvince) {
        this.stateProvince = stateProvince;
        setStateProvinceId(stateProvince == null ? null : stateProvince.getStateProvinceId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STATE_PROVINCE_ID,
                referencedColumnName = MappedStateProvince.COLUMN_NAME_STATE_PROVINCE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private StateProvince stateProvince;
}

