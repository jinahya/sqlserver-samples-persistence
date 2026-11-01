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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedLocation;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedWorkOrder;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedWorkOrderRouting;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedWorkOrderRoutingId;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.WorkOrderRouting} table.
 */
@Entity
@Table(schema = MappedWorkOrderRouting.TABLE_SCHEMA, name = MappedWorkOrderRouting.TABLE_NAME)
@IdClass(MappedWorkOrderRoutingId.class)
public class WorkOrderRouting extends MappedWorkOrderRouting {

    /**
     * The name of the attribute of the {@code FK_WorkOrderRouting_Location_LocationID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    /**
     * The name of the attribute of the {@code FK_WorkOrderRouting_WorkOrder_WorkOrderID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER = "workOrder";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected WorkOrderRouting() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns the {@code Location} which the {@code FK_WorkOrderRouting_Location_LocationID} foreign key references.
     *
     * @return the {@code Location} referenced; {@code null} when none is.
     */
    @Nullable
    public Location getLocation() {
        return location;
    }

    /**
     * Replaces the {@code Location} which the {@code FK_WorkOrderRouting_Location_LocationID} foreign key references,
     * and the
     * value of the column it consists of.
     *
     * @param location the {@code Location} to reference; {@code null} for none.
     */
    public void setLocation(@Nullable final Location location) {
        this.location = location;
        setLocationId(location == null ? null : location.getLocationId());
    }

    // ------------------------------------------------------------------------------------------------------- workOrder

    /**
     * Returns the {@code WorkOrder} which the {@code FK_WorkOrderRouting_WorkOrder_WorkOrderID} foreign key references.
     *
     * @return the {@code WorkOrder} referenced; {@code null} when none is.
     */
    @Nullable
    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    /**
     * Replaces the {@code WorkOrder} which the {@code FK_WorkOrderRouting_WorkOrder_WorkOrderID} foreign key
     * references, and the
     * value of the column it consists of.
     *
     * @param workOrder the {@code WorkOrder} to reference; {@code null} for none.
     */
    public void setWorkOrder(@Nullable final WorkOrder workOrder) {
        this.workOrder = workOrder;
        setWorkOrderId(workOrder == null ? null : workOrder.getWorkOrderId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                referencedColumnName = MappedLocation.COLUMN_NAME_LOCATION_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_WORK_ORDER_ID,
                referencedColumnName = MappedWorkOrder.COLUMN_NAME_WORK_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private WorkOrder workOrder;
}

