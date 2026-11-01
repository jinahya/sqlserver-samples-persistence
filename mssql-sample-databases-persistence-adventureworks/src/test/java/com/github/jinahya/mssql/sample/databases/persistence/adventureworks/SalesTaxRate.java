package com.github.jinahya.mssql.sample.databases.persistence.adventureworks;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedSalesTaxRate;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedStateProvince;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.SalesTaxRate} table.
 */
@Entity
@Table(schema = MappedSalesTaxRate.TABLE_SCHEMA, name = MappedSalesTaxRate.TABLE_NAME)
public class SalesTaxRate extends MappedSalesTaxRate {

    /**
     * The name of the attribute of the {@code FK_SalesTaxRate_StateProvince_StateProvinceID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE = "stateProvince";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SalesTaxRate() {
        super();
    }

    // --------------------------------------------------------------------------------------------------- stateProvince

    /**
     * Returns the {@code StateProvince} which the {@code FK_SalesTaxRate_StateProvince_StateProvinceID} foreign key
     * references.
     *
     * @return the {@code StateProvince} referenced; {@code null} when none is.
     */
    @Nullable
    public StateProvince getStateProvince() {
        return stateProvince;
    }

    /**
     * Replaces the {@code StateProvince} which the {@code FK_SalesTaxRate_StateProvince_StateProvinceID} foreign key
     * references, and the
     * value of the column it consists of.
     *
     * @param stateProvince the {@code StateProvince} to reference; {@code null} for none.
     */
    public void setStateProvince(@Nullable final StateProvince stateProvince) {
        this.stateProvince = stateProvince;
        setStateProvinceId(stateProvince == null ? null : stateProvince.getStateProvinceId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_STATE_PROVINCE_ID,
                referencedColumnName = MappedStateProvince.COLUMN_NAME_STATE_PROVINCE_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private StateProvince stateProvince;
}

