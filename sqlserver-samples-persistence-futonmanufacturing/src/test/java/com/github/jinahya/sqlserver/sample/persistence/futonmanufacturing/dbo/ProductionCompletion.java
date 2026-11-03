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

import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedProductionCompletion;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedProductionOrder;
import com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo.MappedWorkCenter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code dbo.ProductionCompletion} table.
 */
@Entity(name = "DboProductionCompletion")
@Table(schema = MappedProductionCompletion.TABLE_SCHEMA, name = MappedProductionCompletion.TABLE_NAME)
public class ProductionCompletion extends MappedProductionCompletion {

    /**
     * The name of the attribute of the {@code FK_Completion_ProdOrder} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_ORDER = "productionOrder";

    /**
     * The name of the attribute of the {@code FK_Completion_WorkCenter} foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER = "workCenter";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductionCompletion() {
        super();
    }

    // ----------------------------------------------------------------------------- productionOrder

    /**
     * Returns the {@code ProductionOrder} which the {@code FK_Completion_ProdOrder} foreign key references.
     *
     * @return the {@code ProductionOrder} referenced; {@code null} when none is.
     */
    public ProductionOrder getProductionOrder() {
        return productionOrder;
    }

    /**
     * Replaces the {@code ProductionOrder} which the {@code FK_Completion_ProdOrder} foreign key references, and the
     * value of the column it consists of.
     *
     * @param productionOrder the {@code ProductionOrder} to reference; {@code null} for none.
     */
    public void setProductionOrder(final ProductionOrder productionOrder) {
        this.productionOrder = productionOrder;
        setProductionOrderId(productionOrder == null ? null : productionOrder.getProductionOrderId());
    }

    // ---------------------------------------------------------------------------------- workCenter

    /**
     * Returns the {@code WorkCenter} which the {@code FK_Completion_WorkCenter} foreign key references.
     *
     * @return the {@code WorkCenter} referenced; {@code null} when none is.
     */
    public WorkCenter getWorkCenter() {
        return workCenter;
    }

    /**
     * Replaces the {@code WorkCenter} which the {@code FK_Completion_WorkCenter} foreign key references, and the value
     * of the column it consists of.
     *
     * @param workCenter the {@code WorkCenter} to reference; {@code null} for none.
     */
    public void setWorkCenter(final WorkCenter workCenter) {
        this.workCenter = workCenter;
        setWorkCenterId(workCenter == null ? null : workCenter.getWorkCenterId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCTION_ORDER_ID,
                referencedColumnName = MappedProductionOrder.COLUMN_NAME_PRODUCTION_ORDER_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private ProductionOrder productionOrder;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_WORK_CENTER_ID,
                referencedColumnName = MappedWorkCenter.COLUMN_NAME_WORK_CENTER_ID,
                nullable = true,
                insertable = true,
                updatable = true
    )
    private WorkCenter workCenter;
}

