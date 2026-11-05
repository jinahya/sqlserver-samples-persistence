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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.humanresources.Employee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources.MappedEmployee;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedPurchaseOrderHeader;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedShipMethod;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.purchasing.MappedVendor;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Purchasing.PurchaseOrderHeader} table.
 */
@Entity(name = "PurchasingPurchaseOrderHeader")
@Table(schema = MappedPurchaseOrderHeader.TABLE_SCHEMA,
       name = MappedPurchaseOrderHeader.TABLE_NAME,
       comment = "General purchase order information. See PurchaseOrderDetail.",
       check = {
               @CheckConstraint(
                       name = "CK_PurchaseOrderHeader_Freight",
                       constraint = "([Freight]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderHeader_ShipDate",
                       constraint = "([ShipDate]>=[OrderDate] OR [ShipDate] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderHeader_Status",
                       constraint = "([Status]>=(1) AND [Status]<=(4))"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderHeader_SubTotal",
                       constraint = "([SubTotal]>=(0.00))"
               ),
               @CheckConstraint(
                       name = "CK_PurchaseOrderHeader_TaxAmt",
                       constraint = "([TaxAmt]>=(0.00))"
               )
       }
)
public class PurchaseOrderHeader
        extends MappedPurchaseOrderHeader {

    /**
     * The name of the attribute of the {@code FK_PurchaseOrderHeader_Employee_EmployeeID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EMPLOYEE = "employee";

    /**
     * The name of the attribute of the {@code FK_PurchaseOrderHeader_Vendor_VendorID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VENDOR = "vendor";

    /**
     * The name of the attribute of the {@code FK_PurchaseOrderHeader_ShipMethod_ShipMethodID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_METHOD = "shipMethod";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected PurchaseOrderHeader() {
        super();
    }

    // ------------------------------------------------------------------------------------ employee

    /**
     * Returns the {@code Employee} which the {@code FK_PurchaseOrderHeader_Employee_EmployeeID}
     * foreign key references.
     *
     * @return the {@code Employee} referenced; {@code null} when none is.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Replaces the {@code Employee} which the {@code FK_PurchaseOrderHeader_Employee_EmployeeID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param employee the {@code Employee} to reference; {@code null} for none.
     */
    public void setEmployee(final Employee employee) {
        this.employee = employee;
        setEmployeeId(employee == null ? null : employee.getBusinessEntityId());
    }

    // -------------------------------------------------------------------------------------- vendor

    /**
     * Returns the {@code Vendor} which the {@code FK_PurchaseOrderHeader_Vendor_VendorID} foreign
     * key references.
     *
     * @return the {@code Vendor} referenced; {@code null} when none is.
     */
    public Vendor getVendor() {
        return vendor;
    }

    /**
     * Replaces the {@code Vendor} which the {@code FK_PurchaseOrderHeader_Vendor_VendorID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param vendor the {@code Vendor} to reference; {@code null} for none.
     */
    public void setVendor(final Vendor vendor) {
        this.vendor = vendor;
        setVendorId(vendor == null ? null : vendor.getBusinessEntityId());
    }

    // ---------------------------------------------------------------------------------- shipMethod

    /**
     * Returns the {@code ShipMethod} which the
     * {@code FK_PurchaseOrderHeader_ShipMethod_ShipMethodID} foreign key references.
     *
     * @return the {@code ShipMethod} referenced; {@code null} when none is.
     */
    public ShipMethod getShipMethod() {
        return shipMethod;
    }

    /**
     * Replaces the {@code ShipMethod} which the
     * {@code FK_PurchaseOrderHeader_ShipMethod_ShipMethodID} foreign key references, and the value
     * of the column it consists of.
     *
     * @param shipMethod the {@code ShipMethod} to reference; {@code null} for none.
     */
    public void setShipMethod(final ShipMethod shipMethod) {
        this.shipMethod = shipMethod;
        setShipMethodId(shipMethod == null ? null : shipMethod.getShipMethodId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_EMPLOYEE_ID,
                referencedColumnName = MappedEmployee.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Employee employee;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_VENDOR_ID,
                referencedColumnName = MappedVendor.COLUMN_NAME_BUSINESS_ENTITY_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Vendor vendor;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SHIP_METHOD_ID,
                referencedColumnName = MappedShipMethod.COLUMN_NAME_SHIP_METHOD_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private ShipMethod shipMethod;
}

