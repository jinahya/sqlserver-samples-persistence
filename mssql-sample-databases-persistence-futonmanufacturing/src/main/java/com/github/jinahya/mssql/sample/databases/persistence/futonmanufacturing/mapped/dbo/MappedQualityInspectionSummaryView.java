package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_QualityInspectionSummary} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedQualityInspectionSummaryView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_QualityInspectionSummary";

    // -------------------------------------------------------------------------------------------------- InspectionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_DATE = "InspectionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_DATE = "inspectionDate";

    // ------------------------------------------------------------------------------------------------------------ Year

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR = "Year";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR = "year";

    // ----------------------------------------------------------------------------------------------------------- Month

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONTH = "Month";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MONTH = "month";

    // -------------------------------------------------------------------------------------------------- InspectionType

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSPECTION_TYPE = "InspectionType";

    /**
     * The length of the {@value #COLUMN_NAME_INSPECTION_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_INSPECTION_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSPECTION_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSPECTION_TYPE = "inspectionType";

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

    // --------------------------------------------------------------------------------------------- NumberOfInspections

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_OF_INSPECTIONS = "NumberOfInspections";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_OF_INSPECTIONS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_OF_INSPECTIONS = "numberOfInspections";

    // -------------------------------------------------------------------------------------------------- TotalInspected

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_INSPECTED = "TotalInspected";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_INSPECTED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_INSPECTED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_INSPECTED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_INSPECTED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_INSPECTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_INSPECTED = "totalInspected";

    // --------------------------------------------------------------------------------------------------- TotalAccepted

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ACCEPTED = "TotalAccepted";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_ACCEPTED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_ACCEPTED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_ACCEPTED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_ACCEPTED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ACCEPTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ACCEPTED = "totalAccepted";

    // --------------------------------------------------------------------------------------------------- TotalRejected

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_REJECTED = "TotalRejected";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_REJECTED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_REJECTED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_REJECTED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_REJECTED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REJECTED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_REJECTED = "totalRejected";

    // -------------------------------------------------------------------------------------------------- AcceptanceRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACCEPTANCE_RATE = "AcceptanceRate";

    /**
     * The precision of the {@value #COLUMN_NAME_ACCEPTANCE_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ACCEPTANCE_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_ACCEPTANCE_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ACCEPTANCE_RATE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACCEPTANCE_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACCEPTANCE_RATE = "acceptanceRate";

    // --------------------------------------------------------------------------------------------------- RejectionRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REJECTION_RATE = "RejectionRate";

    /**
     * The precision of the {@value #COLUMN_NAME_REJECTION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REJECTION_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_REJECTION_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REJECTION_RATE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REJECTION_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REJECTION_RATE = "rejectionRate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedQualityInspectionSummaryView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "inspectionDate=" + inspectionDate
               + ",year=" + year
               + ",month=" + month
               + ",inspectionType=" + inspectionType
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",numberOfInspections=" + numberOfInspections
               + ",totalInspected=" + totalInspected
               + ",totalAccepted=" + totalAccepted
               + ",totalRejected=" + totalRejected
               + ",acceptanceRate=" + acceptanceRate
               + ",rejectionRate=" + rejectionRate
               + "}";
    }

    // -------------------------------------------------------------------------------------------------- inspectionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     */
    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute with the specified value.
     *
     * @param inspectionDate new value for {@value #ATTRIBUTE_NAME_INSPECTION_DATE} attribute.
     */
    protected void setInspectionDate(final LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    // ------------------------------------------------------------------------------------------------------------ year

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR} attribute with the specified value.
     *
     * @param year new value for {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    protected void setYear(final Integer year) {
        this.year = year;
    }

    // ----------------------------------------------------------------------------------------------------------- month

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MONTH} attribute with the specified value.
     *
     * @param month new value for {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    protected void setMonth(final Integer month) {
        this.month = month;
    }

    // -------------------------------------------------------------------------------------------------- inspectionType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     */
    public String getInspectionType() {
        return inspectionType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute with the specified value.
     *
     * @param inspectionType new value for {@value #ATTRIBUTE_NAME_INSPECTION_TYPE} attribute.
     */
    protected void setInspectionType(final String inspectionType) {
        this.inspectionType = inspectionType;
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

    // --------------------------------------------------------------------------------------------- numberOfInspections

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_INSPECTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_INSPECTIONS} attribute.
     */
    public Integer getNumberOfInspections() {
        return numberOfInspections;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_INSPECTIONS} attribute with the specified value.
     *
     * @param numberOfInspections new value for {@value #ATTRIBUTE_NAME_NUMBER_OF_INSPECTIONS} attribute.
     */
    protected void setNumberOfInspections(final Integer numberOfInspections) {
        this.numberOfInspections = numberOfInspections;
    }

    // -------------------------------------------------------------------------------------------------- totalInspected

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_INSPECTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_INSPECTED} attribute.
     */
    public BigDecimal getTotalInspected() {
        return totalInspected;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_INSPECTED} attribute with the specified value.
     *
     * @param totalInspected new value for {@value #ATTRIBUTE_NAME_TOTAL_INSPECTED} attribute.
     */
    protected void setTotalInspected(final BigDecimal totalInspected) {
        this.totalInspected = totalInspected;
    }

    // --------------------------------------------------------------------------------------------------- totalAccepted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_ACCEPTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_ACCEPTED} attribute.
     */
    public BigDecimal getTotalAccepted() {
        return totalAccepted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_ACCEPTED} attribute with the specified value.
     *
     * @param totalAccepted new value for {@value #ATTRIBUTE_NAME_TOTAL_ACCEPTED} attribute.
     */
    protected void setTotalAccepted(final BigDecimal totalAccepted) {
        this.totalAccepted = totalAccepted;
    }

    // --------------------------------------------------------------------------------------------------- totalRejected

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REJECTED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REJECTED} attribute.
     */
    public BigDecimal getTotalRejected() {
        return totalRejected;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REJECTED} attribute with the specified value.
     *
     * @param totalRejected new value for {@value #ATTRIBUTE_NAME_TOTAL_REJECTED} attribute.
     */
    protected void setTotalRejected(final BigDecimal totalRejected) {
        this.totalRejected = totalRejected;
    }

    // -------------------------------------------------------------------------------------------------- acceptanceRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACCEPTANCE_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACCEPTANCE_RATE} attribute.
     */
    public BigDecimal getAcceptanceRate() {
        return acceptanceRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACCEPTANCE_RATE} attribute with the specified value.
     *
     * @param acceptanceRate new value for {@value #ATTRIBUTE_NAME_ACCEPTANCE_RATE} attribute.
     */
    protected void setAcceptanceRate(final BigDecimal acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    // --------------------------------------------------------------------------------------------------- rejectionRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REJECTION_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REJECTION_RATE} attribute.
     */
    public BigDecimal getRejectionRate() {
        return rejectionRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REJECTION_RATE} attribute with the specified value.
     *
     * @param rejectionRate new value for {@value #ATTRIBUTE_NAME_REJECTION_RATE} attribute.
     */
    protected void setRejectionRate(final BigDecimal rejectionRate) {
        this.rejectionRate = rejectionRate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INSPECTION_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate inspectionDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer year;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer month;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_INSPECTION_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_INSPECTION_TYPE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_INSPECTION_TYPE
    )
    private String inspectionType;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_OF_INSPECTIONS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer numberOfInspections;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_INSPECTED - COLUMN_SCALE_TOTAL_INSPECTED,
            fraction = COLUMN_SCALE_TOTAL_INSPECTED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_INSPECTED,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_INSPECTED,
            scale = COLUMN_SCALE_TOTAL_INSPECTED
    )
    private BigDecimal totalInspected;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_ACCEPTED - COLUMN_SCALE_TOTAL_ACCEPTED,
            fraction = COLUMN_SCALE_TOTAL_ACCEPTED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ACCEPTED,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_ACCEPTED,
            scale = COLUMN_SCALE_TOTAL_ACCEPTED
    )
    private BigDecimal totalAccepted;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_REJECTED - COLUMN_SCALE_TOTAL_REJECTED,
            fraction = COLUMN_SCALE_TOTAL_REJECTED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_REJECTED,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_REJECTED,
            scale = COLUMN_SCALE_TOTAL_REJECTED
    )
    private BigDecimal totalRejected;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_ACCEPTANCE_RATE - COLUMN_SCALE_ACCEPTANCE_RATE,
            fraction = COLUMN_SCALE_ACCEPTANCE_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACCEPTANCE_RATE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_ACCEPTANCE_RATE,
            scale = COLUMN_SCALE_ACCEPTANCE_RATE
    )
    private BigDecimal acceptanceRate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_REJECTION_RATE - COLUMN_SCALE_REJECTION_RATE,
            fraction = COLUMN_SCALE_REJECTION_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REJECTION_RATE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_REJECTION_RATE,
            scale = COLUMN_SCALE_REJECTION_RATE
    )
    private BigDecimal rejectionRate;
}

