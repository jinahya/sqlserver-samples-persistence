package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.BillOfMaterials} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ASSEMBLY_ID}, {@value #COLUMN_NAME_COMPONENT_ID},
 * {@value #COLUMN_NAME_UNIT_MEASURE_CODE} columns are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedBillOfMaterials {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "BillOfMaterials";

    // ----------------------------------------------------------------------------------------------- BillOfMaterialsID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BILL_OF_MATERIALS_ID = "BillOfMaterialsID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BILL_OF_MATERIALS_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BILL_OF_MATERIALS_ID = "billOfMaterialsId";

    // ----------------------------------------------------------------------------------------------- ProductAssemblyID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ASSEMBLY_ID = "ProductAssemblyID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ASSEMBLY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID = "productAssemblyId";

    // ----------------------------------------------------------------------------------------------------- ComponentID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPONENT_ID = "ComponentID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPONENT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPONENT_ID = "componentId";

    // ------------------------------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // --------------------------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ------------------------------------------------------------------------------------------------- UnitMeasureCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_MEASURE_CODE = "UnitMeasureCode";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_MEASURE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_MEASURE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_MEASURE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_MEASURE_CODE = "unitMeasureCode";

    // -------------------------------------------------------------------------------------------------------- BOMLevel

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BOM_LEVEL = "BOMLevel";

    /**
     * The minimum value of the {@value #COLUMN_NAME_BOM_LEVEL} column, as the table's
     * {@code CK_BillOfMaterials_BOMLevel} check constraint ({@code [BOMLevel]=(0)} for an assembly with no
     * {@value #COLUMN_NAME_PRODUCT_ASSEMBLY_ID}, {@code [BOMLevel]>=(1)} for any other) requires. The value is
     * {@value}.
     */
    public static final int COLUMN_MIN_BOM_LEVEL = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_BOM_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_BOM_LEVEL = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BOM_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BOM_LEVEL = "bomLevel";

    // -------------------------------------------------------------------------------------------------- PerAssemblyQty

    /**
     * The name of the {@code decimal(8, 2), DEFAULT ((1.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PER_ASSEMBLY_QTY = "PerAssemblyQty";

    /**
     * The precision of the {@value #COLUMN_NAME_PER_ASSEMBLY_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PER_ASSEMBLY_QTY = 8;

    /**
     * The scale of the {@value #COLUMN_NAME_PER_ASSEMBLY_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PER_ASSEMBLY_QTY = 2;

    /**
     * The minimum value of the {@value #COLUMN_NAME_PER_ASSEMBLY_QTY} column, as the
     * {@code CK_BillOfMaterials_PerAssemblyQty} check constraint ({@code [PerAssemblyQty]>=(1.00)}) requires. The
     * value is {@value}.
     */
    public static final String COLUMN_MIN_PER_ASSEMBLY_QTY = "1.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PER_ASSEMBLY_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PER_ASSEMBLY_QTY = "perAssemblyQty";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedBillOfMaterials() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "billOfMaterialsId=" + billOfMaterialsId
               + ",productAssemblyId=" + productAssemblyId
               + ",componentId=" + componentId
               + ",startDate=" + startDate
               + ",endDate=" + endDate
               + ",unitMeasureCode=" + unitMeasureCode
               + ",bomLevel=" + bomLevel
               + ",perAssemblyQty=" + perAssemblyQty
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedBillOfMaterials that)) {
            return false;
        }
        return Objects.equals(billOfMaterialsId, that.billOfMaterialsId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(billOfMaterialsId);
    }

    // ------------------------------------------------------------------------------------------------------ VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute is {@code null} or not equal to the
     * {@value #ATTRIBUTE_NAME_COMPONENT_ID} attribute, as the table's {@code CK_BillOfMaterials_ProductAssemblyID}
     * constraint requires.
     * {@snippet lang = "sql":
     * ([ProductAssemblyID]<>[ComponentID])
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_COMPONENT_ID} attribute is {@code null}, or the two attributes are not equal;
     *         {@code false} otherwise.
     */
    @AssertTrue(message = "productAssemblyId must be null or not equal to componentId")
    protected boolean isProductAssemblyIdValid() {
        if (productAssemblyId == null || componentId == null) {
            return true;
        }
        return !productAssemblyId.equals(componentId);
    }

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_END_DATE} attribute is {@code null} or after the
     * {@value #ATTRIBUTE_NAME_START_DATE} attribute, as the table's {@code CK_BillOfMaterials_EndDate} constraint
     * requires.
     * {@snippet lang = "sql":
     * ([EndDate]>[StartDate] OR [EndDate] IS NULL)
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_END_DATE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_START_DATE} attribute is {@code null}, or the
     *         {@value #ATTRIBUTE_NAME_END_DATE} attribute is after the {@value #ATTRIBUTE_NAME_START_DATE} attribute;
     *         {@code false} otherwise.
     */
    @AssertTrue(message = "endDate must be null or after startDate")
    protected boolean isEndDateValid() {
        if (endDate == null || startDate == null) {
            return true;
        }
        return endDate.isAfter(startDate);
    }

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute agrees with the
     * {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} and {@value #ATTRIBUTE_NAME_PER_ASSEMBLY_QTY} attributes, as the
     * table's {@code CK_BillOfMaterials_BOMLevel} constraint requires: a top-level assembly, with no
     * {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID}, is at level {@code 0} with a quantity of {@code 1.00}; any other
     * component is at level {@code 1} or more.
     * {@snippet lang = "sql":
     * ([ProductAssemblyID] IS NULL AND [BOMLevel]=(0) AND [PerAssemblyQty]=(1.00) OR [ProductAssemblyID] IS NOT NULL AND [BOMLevel]>=(1))
     *}
     *
     * @return {@code false} if the {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute is {@code null} and the
     *         {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute is not {@code 0} or the
     *         {@value #ATTRIBUTE_NAME_PER_ASSEMBLY_QTY} attribute is not {@code 1.00}, or if the
     *         {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute is not {@code null} and the
     *         {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute is less than {@code 1}; {@code true} otherwise, including
     *         when an attribute the constraint compares is {@code null}.
     */
    @AssertTrue(message = "bomLevel must be 0, with perAssemblyQty 1.00, for no productAssemblyId, or 1 or more")
    protected boolean isBomLevelValid() {
        if (productAssemblyId == null) {
            if (bomLevel != null && bomLevel != 0) {
                return false;
            }
            return perAssemblyQty == null || perAssemblyQty.compareTo(BigDecimal.ONE) == 0;
        }
        return bomLevel == null || bomLevel >= 1;
    }

    // ----------------------------------------------------------------------------------------------- billOfMaterialsId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BILL_OF_MATERIALS_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BILL_OF_MATERIALS_ID} attribute.
     */
    public Integer getBillOfMaterialsId() {
        return billOfMaterialsId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BILL_OF_MATERIALS_ID} attribute with the specified value.
     *
     * @param billOfMaterialsId new value for {@value #ATTRIBUTE_NAME_BILL_OF_MATERIALS_ID} attribute.
     */
    protected void setBillOfMaterialsId(final Integer billOfMaterialsId) {
        this.billOfMaterialsId = billOfMaterialsId;
    }

    // ----------------------------------------------------------------------------------------------- productAssemblyId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute.
     */
    public Integer getProductAssemblyId() {
        return productAssemblyId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param productAssemblyId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ASSEMBLY_ID} attribute.
     */
    protected void setProductAssemblyId(final Integer productAssemblyId) {
        this.productAssemblyId = productAssemblyId;
    }

    // ----------------------------------------------------------------------------------------------------- componentId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPONENT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPONENT_ID} attribute.
     */
    public Integer getComponentId() {
        return componentId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPONENT_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param componentId new value for {@value #ATTRIBUTE_NAME_COMPONENT_ID} attribute.
     */
    protected void setComponentId(final Integer componentId) {
        this.componentId = componentId;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // ------------------------------------------------------------------------------------------------- unitMeasureCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute.
     */
    public String getUnitMeasureCode() {
        return unitMeasureCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param unitMeasureCode new value for {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute.
     */
    protected void setUnitMeasureCode(final String unitMeasureCode) {
        this.unitMeasureCode = unitMeasureCode;
    }

    // -------------------------------------------------------------------------------------------------------- bomLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute.
     */
    public Integer getBomLevel() {
        return bomLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute with the specified value.
     *
     * @param bomLevel new value for {@value #ATTRIBUTE_NAME_BOM_LEVEL} attribute.
     */
    public void setBomLevel(final Integer bomLevel) {
        this.bomLevel = bomLevel;
    }

    // -------------------------------------------------------------------------------------------------- perAssemblyQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PER_ASSEMBLY_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PER_ASSEMBLY_QTY} attribute.
     */
    public BigDecimal getPerAssemblyQty() {
        return perAssemblyQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PER_ASSEMBLY_QTY} attribute with the specified value.
     *
     * @param perAssemblyQty new value for {@value #ATTRIBUTE_NAME_PER_ASSEMBLY_QTY} attribute.
     */
    public void setPerAssemblyQty(final BigDecimal perAssemblyQty) {
        this.perAssemblyQty = perAssemblyQty;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_BILL_OF_MATERIALS_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer billOfMaterialsId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCT_ASSEMBLY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer productAssemblyId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COMPONENT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer componentId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime startDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_MEASURE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_MEASURE_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_UNIT_MEASURE_CODE
    )
    private String unitMeasureCode;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_BillOfMaterials_BOMLevel, of the table: 0 for an assembly with no ProductAssemblyID, 1 or more for any other;
    // smallint's upper bound
    @Max(COLUMN_MAX_BOM_LEVEL)
    @Min(COLUMN_MIN_BOM_LEVEL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BOM_LEVEL,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer bomLevel;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_BillOfMaterials_PerAssemblyQty: ([PerAssemblyQty]>=(1.00))
    @DecimalMin(COLUMN_MIN_PER_ASSEMBLY_QTY)
    @Digits(integer = COLUMN_PRECISION_PER_ASSEMBLY_QTY - COLUMN_SCALE_PER_ASSEMBLY_QTY,
            fraction = COLUMN_SCALE_PER_ASSEMBLY_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PER_ASSEMBLY_QTY,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_PER_ASSEMBLY_QTY,
            scale = COLUMN_SCALE_PER_ASSEMBLY_QTY
    )
    private BigDecimal perAssemblyQty;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

