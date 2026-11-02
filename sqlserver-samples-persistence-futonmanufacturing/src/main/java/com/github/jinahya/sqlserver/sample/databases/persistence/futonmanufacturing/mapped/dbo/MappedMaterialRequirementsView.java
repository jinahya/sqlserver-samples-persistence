package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_MaterialRequirements} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); it contains
 * aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedMaterialRequirementsView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_MaterialRequirements";

    // ------------------------------------------------------------------------------------------------- WorkOrderNumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_ORDER_NUMBER = "WorkOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_ORDER_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_ORDER_NUMBER = "workOrderNumber";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATUS = "Status";

    /**
     * The length of the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATUS = "status";

    // ------------------------------------------------------------------------------------------- PlannedCompletionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PLANNED_COMPLETION_DATE = "PlannedCompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PLANNED_COMPLETION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE = "plannedCompletionDate";

    // -------------------------------------------------------------------------------------------------------- ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // -------------------------------------------------------------------------------------------------------- ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // -------------------------------------------------------------------------------------------------------- ItemType

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_TYPE = "ItemType";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE = "itemType";

    // ------------------------------------------------------------------------------------------------ RequiredQuantity

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REQUIRED_QUANTITY = "RequiredQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REQUIRED_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REQUIRED_QUANTITY = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REQUIRED_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REQUIRED_QUANTITY = "requiredQuantity";

    // ----------------------------------------------------------------------------------------------- AvailableQuantity

    /**
     * The name of the {@code decimal(38, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVAILABLE_QUANTITY = "AvailableQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVAILABLE_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVAILABLE_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVAILABLE_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVAILABLE_QUANTITY = "availableQuantity";

    // ------------------------------------------------------------------------------------------------ ShortageQuantity

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHORTAGE_QUANTITY = "ShortageQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SHORTAGE_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SHORTAGE_QUANTITY = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHORTAGE_QUANTITY = "shortageQuantity";

    // ---------------------------------------------------------------------------------------------- AvailabilityStatus

    /**
     * The name of the {@code varchar(12), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVAILABILITY_STATUS = "AvailabilityStatus";

    /**
     * The length of the {@value #COLUMN_NAME_AVAILABILITY_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_AVAILABILITY_STATUS = 12;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVAILABILITY_STATUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVAILABILITY_STATUS = "availabilityStatus";

    // -------------------------------------------------------------------------------------------------------- UnitCode

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_CODE = "UnitCode";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_CODE = "unitCode";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedMaterialRequirementsView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workOrderNumber=" + workOrderNumber
               + ",status=" + status
               + ",plannedCompletionDate=" + plannedCompletionDate
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",requiredQuantity=" + requiredQuantity
               + ",availableQuantity=" + availableQuantity
               + ",shortageQuantity=" + shortageQuantity
               + ",availabilityStatus=" + availabilityStatus
               + ",unitCode=" + unitCode
               + "}";
    }

    // ------------------------------------------------------------------------------------------------- workOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     */
    public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute with the specified value.
     *
     * @param workOrderNumber new value for {@value #ATTRIBUTE_NAME_WORK_ORDER_NUMBER} attribute.
     */
    protected void setWorkOrderNumber(final String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    // ---------------------------------------------------------------------------------------------------------- status

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATUS} attribute with the specified value.
     *
     * @param status new value for {@value #ATTRIBUTE_NAME_STATUS} attribute.
     */
    protected void setStatus(final String status) {
        this.status = status;
    }

    // ------------------------------------------------------------------------------------------- plannedCompletionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     */
    public LocalDate getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute with the specified value.
     *
     * @param plannedCompletionDate new value for {@value #ATTRIBUTE_NAME_PLANNED_COMPLETION_DATE} attribute.
     */
    protected void setPlannedCompletionDate(final LocalDate plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    // -------------------------------------------------------------------------------------------------------- itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // -------------------------------------------------------------------------------------------------------- itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    // -------------------------------------------------------------------------------------------------------- itemType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute with the specified value.
     *
     * @param itemType new value for {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    protected void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    // ------------------------------------------------------------------------------------------------ requiredQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     */
    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute with the specified value.
     *
     * @param requiredQuantity new value for {@value #ATTRIBUTE_NAME_REQUIRED_QUANTITY} attribute.
     */
    protected void setRequiredQuantity(final BigDecimal requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    // ----------------------------------------------------------------------------------------------- availableQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute.
     */
    public BigDecimal getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute with the specified value.
     *
     * @param availableQuantity new value for {@value #ATTRIBUTE_NAME_AVAILABLE_QUANTITY} attribute.
     */
    protected void setAvailableQuantity(final BigDecimal availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    // ------------------------------------------------------------------------------------------------ shortageQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     */
    public BigDecimal getShortageQuantity() {
        return shortageQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute with the specified value.
     *
     * @param shortageQuantity new value for {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     */
    protected void setShortageQuantity(final BigDecimal shortageQuantity) {
        this.shortageQuantity = shortageQuantity;
    }

    // ---------------------------------------------------------------------------------------------- availabilityStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVAILABILITY_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVAILABILITY_STATUS} attribute.
     */
    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVAILABILITY_STATUS} attribute with the specified value.
     *
     * @param availabilityStatus new value for {@value #ATTRIBUTE_NAME_AVAILABILITY_STATUS} attribute.
     */
    protected void setAvailabilityStatus(final String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    // -------------------------------------------------------------------------------------------------------- unitCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute with the specified value.
     *
     * @param unitCode new value for {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    protected void setUnitCode(final String unitCode) {
        this.unitCode = unitCode;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_ORDER_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WORK_ORDER_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WORK_ORDER_NUMBER
    )
    private String workOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_STATUS
    )
    private String status;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PLANNED_COMPLETION_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate plannedCompletionDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_TYPE
    )
    private String itemType;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_REQUIRED_QUANTITY - COLUMN_SCALE_REQUIRED_QUANTITY,
            fraction = COLUMN_SCALE_REQUIRED_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REQUIRED_QUANTITY,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_REQUIRED_QUANTITY,
            scale = COLUMN_SCALE_REQUIRED_QUANTITY
    )
    private BigDecimal requiredQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVAILABLE_QUANTITY - COLUMN_SCALE_AVAILABLE_QUANTITY,
            fraction = COLUMN_SCALE_AVAILABLE_QUANTITY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AVAILABLE_QUANTITY,
            nullable = false,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVAILABLE_QUANTITY,
            scale = COLUMN_SCALE_AVAILABLE_QUANTITY
    )
    private BigDecimal availableQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_SHORTAGE_QUANTITY - COLUMN_SCALE_SHORTAGE_QUANTITY,
            fraction = COLUMN_SCALE_SHORTAGE_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHORTAGE_QUANTITY,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_SHORTAGE_QUANTITY,
            scale = COLUMN_SCALE_SHORTAGE_QUANTITY
    )
    private BigDecimal shortageQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_AVAILABILITY_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AVAILABILITY_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_AVAILABILITY_STATUS
    )
    private String availabilityStatus;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_UNIT_CODE
    )
    private String unitCode;
}

