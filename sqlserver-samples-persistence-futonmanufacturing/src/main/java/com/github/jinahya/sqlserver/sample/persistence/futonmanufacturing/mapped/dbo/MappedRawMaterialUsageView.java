package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_RawMaterialUsage} view.
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
public abstract class MappedRawMaterialUsageView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_RawMaterialUsage";

    // ---------------------------------------------------------------------------------------- Year

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR = "Year";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR = "year";

    // --------------------------------------------------------------------------------------- Month

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONTH = "Month";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MONTH = "month";

    // ------------------------------------------------------------------------------------- Quarter

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUARTER = "Quarter";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUARTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUARTER = "quarter";

    // ------------------------------------------------------------------------------------ ItemType

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

    // ------------------------------------------------------------------------------------ ItemCode

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

    // ------------------------------------------------------------------------------------ ItemName

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

    // ------------------------------------------------------------------------------------ UnitCode

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

    // ---------------------------------------------------------------------------------- TotalUsage

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_USAGE = "TotalUsage";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_USAGE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_USAGE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_USAGE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_USAGE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_USAGE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_USAGE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_USAGE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_USAGE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_USAGE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_USAGE = "totalUsage";

    // ------------------------------------------------------------------------------- TotalReceipts

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_RECEIPTS = "TotalReceipts";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_RECEIPTS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_RECEIPTS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_RECEIPTS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_RECEIPTS = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_RECEIPTS} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_RECEIPTS = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_RECEIPTS} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_RECEIPTS = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_RECEIPTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_RECEIPTS = "totalReceipts";

    // ------------------------------------------------------------------------------ NumberOfIssues

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_OF_ISSUES = "NumberOfIssues";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_OF_ISSUES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_OF_ISSUES = "numberOfIssues";

    // ---------------------------------------------------------------------------- AvgIssueQuantity

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_ISSUE_QUANTITY = "AvgIssueQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_ISSUE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_ISSUE_QUANTITY = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_ISSUE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_ISSUE_QUANTITY = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AVG_ISSUE_QUANTITY = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AVG_ISSUE_QUANTITY = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ISSUE_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY = "avgIssueQuantity";

    // ----------------------------------------------------------------------------- TotalUsageValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_USAGE_VALUE = "TotalUsageValue";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_USAGE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_USAGE_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_USAGE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_USAGE_VALUE = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_USAGE_VALUE} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_USAGE_VALUE = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_USAGE_VALUE} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_USAGE_VALUE = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_USAGE_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_USAGE_VALUE = "totalUsageValue";

    // --------------------------------------------------------------------------------- AvgUnitCost

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_UNIT_COST = "AvgUnitCost";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_UNIT_COST = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_UNIT_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_UNIT_COST = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_UNIT_COST} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AVG_UNIT_COST = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_UNIT_COST} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AVG_UNIT_COST = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_UNIT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_UNIT_COST = "avgUnitCost";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedRawMaterialUsageView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "year=" + year
               + ",month=" + month
               + ",quarter=" + quarter
               + ",itemType=" + itemType
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",unitCode=" + unitCode
               + ",totalUsage=" + totalUsage
               + ",totalReceipts=" + totalReceipts
               + ",numberOfIssues=" + numberOfIssues
               + ",avgIssueQuantity=" + avgIssueQuantity
               + ",totalUsageValue=" + totalUsageValue
               + ",avgUnitCost=" + avgUnitCost
               + "}";
    }

    // ---------------------------------------------------------------------------------------- year

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

    // --------------------------------------------------------------------------------------- month

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

    // ------------------------------------------------------------------------------------- quarter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUARTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUARTER} attribute.
     */
    public Integer getQuarter() {
        return quarter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUARTER} attribute with the specified value.
     *
     * @param quarter new value for {@value #ATTRIBUTE_NAME_QUARTER} attribute.
     */
    protected void setQuarter(final Integer quarter) {
        this.quarter = quarter;
    }

    // ------------------------------------------------------------------------------------ itemType

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

    // ------------------------------------------------------------------------------------ itemCode

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

    // ------------------------------------------------------------------------------------ itemName

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

    // ------------------------------------------------------------------------------------ unitCode

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

    // ---------------------------------------------------------------------------------- totalUsage

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_USAGE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_USAGE} attribute.
     */
    public BigDecimal getTotalUsage() {
        return totalUsage;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_USAGE} attribute with the specified value.
     *
     * @param totalUsage new value for {@value #ATTRIBUTE_NAME_TOTAL_USAGE} attribute.
     */
    protected void setTotalUsage(final BigDecimal totalUsage) {
        this.totalUsage = totalUsage;
    }

    // ------------------------------------------------------------------------------- totalReceipts

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_RECEIPTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_RECEIPTS} attribute.
     */
    public BigDecimal getTotalReceipts() {
        return totalReceipts;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_RECEIPTS} attribute with the specified value.
     *
     * @param totalReceipts new value for {@value #ATTRIBUTE_NAME_TOTAL_RECEIPTS} attribute.
     */
    protected void setTotalReceipts(final BigDecimal totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    // ------------------------------------------------------------------------------ numberOfIssues

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_ISSUES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_ISSUES} attribute.
     */
    public Integer getNumberOfIssues() {
        return numberOfIssues;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_ISSUES} attribute with the specified value.
     *
     * @param numberOfIssues new value for {@value #ATTRIBUTE_NAME_NUMBER_OF_ISSUES} attribute.
     */
    protected void setNumberOfIssues(final Integer numberOfIssues) {
        this.numberOfIssues = numberOfIssues;
    }

    // ---------------------------------------------------------------------------- avgIssueQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY} attribute.
     */
    public BigDecimal getAvgIssueQuantity() {
        return avgIssueQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY} attribute with the specified value.
     *
     * @param avgIssueQuantity new value for {@value #ATTRIBUTE_NAME_AVG_ISSUE_QUANTITY} attribute.
     */
    protected void setAvgIssueQuantity(final BigDecimal avgIssueQuantity) {
        this.avgIssueQuantity = avgIssueQuantity;
    }

    // ----------------------------------------------------------------------------- totalUsageValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_USAGE_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_USAGE_VALUE} attribute.
     */
    public BigDecimal getTotalUsageValue() {
        return totalUsageValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_USAGE_VALUE} attribute with the specified value.
     *
     * @param totalUsageValue new value for {@value #ATTRIBUTE_NAME_TOTAL_USAGE_VALUE} attribute.
     */
    protected void setTotalUsageValue(final BigDecimal totalUsageValue) {
        this.totalUsageValue = totalUsageValue;
    }

    // --------------------------------------------------------------------------------- avgUnitCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_UNIT_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_UNIT_COST} attribute.
     */
    public BigDecimal getAvgUnitCost() {
        return avgUnitCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_UNIT_COST} attribute with the specified value.
     *
     * @param avgUnitCost new value for {@value #ATTRIBUTE_NAME_AVG_UNIT_COST} attribute.
     */
    protected void setAvgUnitCost(final BigDecimal avgUnitCost) {
        this.avgUnitCost = avgUnitCost;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR, nullable = true, insertable = false, updatable = false)
    private Integer year;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH, nullable = true, insertable = false, updatable = false)
    private Integer month;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUARTER, nullable = true, insertable = false, updatable = false)
    private Integer quarter;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_TYPE)
    private String itemType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_CODE)
    private String itemCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_NAME)
    private String itemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_UNIT_CODE)
    private String unitCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_USAGE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_USAGE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_USAGE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_USAGE, scale = COLUMN_SCALE_TOTAL_USAGE)
    private BigDecimal totalUsage;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_RECEIPTS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_RECEIPTS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_RECEIPTS, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_RECEIPTS, scale = COLUMN_SCALE_TOTAL_RECEIPTS)
    private BigDecimal totalReceipts;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_OF_ISSUES, nullable = true, insertable = false, updatable = false)
    private Integer numberOfIssues;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_ISSUE_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_ISSUE_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ISSUE_QUANTITY, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_ISSUE_QUANTITY, scale = COLUMN_SCALE_AVG_ISSUE_QUANTITY)
    private BigDecimal avgIssueQuantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_USAGE_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_USAGE_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_USAGE_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_USAGE_VALUE, scale = COLUMN_SCALE_TOTAL_USAGE_VALUE)
    private BigDecimal totalUsageValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_UNIT_COST)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_UNIT_COST)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_UNIT_COST, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_UNIT_COST, scale = COLUMN_SCALE_AVG_UNIT_COST)
    private BigDecimal avgUnitCost;
}

