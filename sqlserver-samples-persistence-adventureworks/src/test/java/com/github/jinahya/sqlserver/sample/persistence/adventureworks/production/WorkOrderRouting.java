package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedLocation;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedWorkOrder;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedWorkOrderRouting;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.WorkOrderRoutingId;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Production.WorkOrderRouting} table.
 */
@Entity(name = "ProductionWorkOrderRouting")
@Table(schema = MappedWorkOrderRouting.TABLE_SCHEMA,
       name = MappedWorkOrderRouting.TABLE_NAME,
       check = {
               @CheckConstraint(
                       name = "CK_WorkOrderRouting_ActualCost",
                       constraint = "([ActualCost]>(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_WorkOrderRouting_ActualEndDate",
                       constraint = "([ActualEndDate]>=[ActualStartDate] OR [ActualEndDate] IS "
                                    + "NULL OR [ActualStartDate] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_WorkOrderRouting_ActualResourceHrs",
                       constraint = "([ActualResourceHrs]>=(0.0000))"
               ),
               @CheckConstraint(
                       name = "CK_WorkOrderRouting_PlannedCost",
                       constraint = "([PlannedCost]>(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_WorkOrderRouting_ScheduledEndDate",
                       constraint = "([ScheduledEndDate]>=[ScheduledStartDate])"
               )
       }
)
@IdClass(WorkOrderRoutingId.class)
public class WorkOrderRouting
        extends MappedWorkOrderRouting {

    /**
     * The name of the attribute of the {@code FK_WorkOrderRouting_Location_LocationID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    /**
     * The name of the attribute of the {@code FK_WorkOrderRouting_WorkOrder_WorkOrderID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER = "workOrder";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code workOrder}, with {@code operationSequence}.
     *
     * @param workOrder         the {@code WorkOrder} to reference.
     * @param operationSequence the value of the {@value #COLUMN_NAME_OPERATION_SEQUENCE} column.
     * @return a new instance which references {@code workOrder}, with {@code operationSequence}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static WorkOrderRouting of(final WorkOrder workOrder,
                                      final Integer operationSequence) {
        Objects.requireNonNull(workOrder, "workOrder is null");
        Objects.requireNonNull(operationSequence, "operationSequence is null");
        final var instance = new WorkOrderRouting();
        instance.setWorkOrder(workOrder);
        instance.setOperationSequence(operationSequence);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected WorkOrderRouting() {
        super();
    }

    // ------------------------------------------------------------------------------------ location

    /**
     * Returns the {@code Location} which the {@code FK_WorkOrderRouting_Location_LocationID}
     * foreign key references.
     *
     * @return the {@code Location} referenced; {@code null} when none is.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Replaces the {@code Location} which the {@code FK_WorkOrderRouting_Location_LocationID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param location the {@code Location} to reference; {@code null} for none.
     */
    public void setLocation(final Location location) {
        this.location = location;
        setLocationId(
                Optional.ofNullable(location).map(MappedLocation::getLocationId).orElse(null));
    }

    // ----------------------------------------------------------------------------------- workOrder

    /**
     * Returns the {@code WorkOrder} which the {@code FK_WorkOrderRouting_WorkOrder_WorkOrderID}
     * foreign key references.
     *
     * @return the {@code WorkOrder} referenced; {@code null} when none is.
     */
    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    /**
     * Replaces the {@code WorkOrder} which the {@code FK_WorkOrderRouting_WorkOrder_WorkOrderID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param workOrder the {@code WorkOrder} to reference; {@code null} for none.
     */
    private void setWorkOrder(final WorkOrder workOrder) {
        this.workOrder = workOrder;
        setWorkOrderId(Optional.ofNullable(workOrder).map(WorkOrder::getWorkOrderId).orElse(null));
        // ProductID, of the key, has no foreign key of its own; it is the product of the work order
        setProductId(Optional.ofNullable(workOrder).map(WorkOrder::getProduct).map(Product::getProductId)
                              .orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                referencedColumnName = MappedLocation.COLUMN_NAME_LOCATION_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Location location;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_WORK_ORDER_ID,
                referencedColumnName = MappedWorkOrder.COLUMN_NAME_WORK_ORDER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private WorkOrder workOrder;
}

