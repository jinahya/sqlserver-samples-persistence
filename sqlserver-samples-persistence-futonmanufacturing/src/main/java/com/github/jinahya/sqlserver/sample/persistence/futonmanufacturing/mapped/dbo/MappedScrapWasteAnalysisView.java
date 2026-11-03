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
import java.time.LocalDate;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_ScrapWasteAnalysis} view.
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
public abstract class MappedScrapWasteAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_ScrapWasteAnalysis";

    // ------------------------------------------------------------------------------ CompletionDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMPLETION_DATE = "CompletionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMPLETION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMPLETION_DATE = "completionDate";

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

    // ------------------------------------------------------------------------------ WorkCenterName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_NAME = "WorkCenterName";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_CENTER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_CENTER_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_NAME = "workCenterName";

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

    // ------------------------------------------------------------------------------ TotalCompleted

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_COMPLETED = "TotalCompleted";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_COMPLETED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_COMPLETED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_COMPLETED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_COMPLETED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_COMPLETED} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_COMPLETED = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_COMPLETED} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_COMPLETED = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_COMPLETED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_COMPLETED = "totalCompleted";

    // ------------------------------------------------------------------------------- TotalScrapped

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_SCRAPPED = "TotalScrapped";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_SCRAPPED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_SCRAPPED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_SCRAPPED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_SCRAPPED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_SCRAPPED} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_SCRAPPED = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_SCRAPPED} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_SCRAPPED = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_SCRAPPED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_SCRAPPED = "totalScrapped";

    // ------------------------------------------------------------------------------- TotalProduced

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_PRODUCED = "TotalProduced";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_PRODUCED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_PRODUCED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_PRODUCED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_PRODUCED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_PRODUCED} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_PRODUCED = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_PRODUCED} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_PRODUCED = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_PRODUCED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_PRODUCED = "totalProduced";

    // ----------------------------------------------------------------------------------- ScrapRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCRAP_RATE = "ScrapRate";

    /**
     * The precision of the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SCRAP_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SCRAP_RATE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SCRAP_RATE = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SCRAP_RATE = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_RATE = "scrapRate";

    // ---------------------------------------------------------------------------------- ScrapValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCRAP_VALUE = "ScrapValue";

    /**
     * The precision of the {@value #COLUMN_NAME_SCRAP_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SCRAP_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_SCRAP_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SCRAP_VALUE = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SCRAP_VALUE} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SCRAP_VALUE = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SCRAP_VALUE} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SCRAP_VALUE = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAP_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_VALUE = "scrapValue";

    // ------------------------------------------------------------------------------ NumberOfOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_OF_ORDERS = "NumberOfOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_OF_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_OF_ORDERS = "numberOfOrders";

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

    // ---------------------------------------------------------------------------------- ScrapLevel

    /**
     * The name of the {@code varchar(6), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SCRAP_LEVEL = "ScrapLevel";

    /**
     * The length of the {@value #COLUMN_NAME_SCRAP_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SCRAP_LEVEL = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAP_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_LEVEL = "scrapLevel";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedScrapWasteAnalysisView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "completionDate=" + completionDate
               + ",year=" + year
               + ",month=" + month
               + ",workCenterName=" + workCenterName
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",totalCompleted=" + totalCompleted
               + ",totalScrapped=" + totalScrapped
               + ",totalProduced=" + totalProduced
               + ",scrapRate=" + scrapRate
               + ",scrapValue=" + scrapValue
               + ",numberOfOrders=" + numberOfOrders
               + ",avgUnitCost=" + avgUnitCost
               + ",scrapLevel=" + scrapLevel
               + "}";
    }

    // ------------------------------------------------------------------------------ completionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute.
     */
    public LocalDate getCompletionDate() {
        return completionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute with the specified value.
     *
     * @param completionDate new value for {@value #ATTRIBUTE_NAME_COMPLETION_DATE} attribute.
     */
    protected void setCompletionDate(final LocalDate completionDate) {
        this.completionDate = completionDate;
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

    // ------------------------------------------------------------------------------ workCenterName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     */
    public String getWorkCenterName() {
        return workCenterName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute with the specified value.
     *
     * @param workCenterName new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_NAME} attribute.
     */
    protected void setWorkCenterName(final String workCenterName) {
        this.workCenterName = workCenterName;
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

    // ------------------------------------------------------------------------------ totalCompleted

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_COMPLETED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_COMPLETED} attribute.
     */
    public BigDecimal getTotalCompleted() {
        return totalCompleted;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_COMPLETED} attribute with the specified value.
     *
     * @param totalCompleted new value for {@value #ATTRIBUTE_NAME_TOTAL_COMPLETED} attribute.
     */
    protected void setTotalCompleted(final BigDecimal totalCompleted) {
        this.totalCompleted = totalCompleted;
    }

    // ------------------------------------------------------------------------------- totalScrapped

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_SCRAPPED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_SCRAPPED} attribute.
     */
    public BigDecimal getTotalScrapped() {
        return totalScrapped;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_SCRAPPED} attribute with the specified value.
     *
     * @param totalScrapped new value for {@value #ATTRIBUTE_NAME_TOTAL_SCRAPPED} attribute.
     */
    protected void setTotalScrapped(final BigDecimal totalScrapped) {
        this.totalScrapped = totalScrapped;
    }

    // ------------------------------------------------------------------------------- totalProduced

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_PRODUCED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_PRODUCED} attribute.
     */
    public BigDecimal getTotalProduced() {
        return totalProduced;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_PRODUCED} attribute with the specified value.
     *
     * @param totalProduced new value for {@value #ATTRIBUTE_NAME_TOTAL_PRODUCED} attribute.
     */
    protected void setTotalProduced(final BigDecimal totalProduced) {
        this.totalProduced = totalProduced;
    }

    // ----------------------------------------------------------------------------------- scrapRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute.
     */
    public BigDecimal getScrapRate() {
        return scrapRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute with the specified value.
     *
     * @param scrapRate new value for {@value #ATTRIBUTE_NAME_SCRAP_RATE} attribute.
     */
    protected void setScrapRate(final BigDecimal scrapRate) {
        this.scrapRate = scrapRate;
    }

    // ---------------------------------------------------------------------------------- scrapValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCRAP_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCRAP_VALUE} attribute.
     */
    public BigDecimal getScrapValue() {
        return scrapValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCRAP_VALUE} attribute with the specified value.
     *
     * @param scrapValue new value for {@value #ATTRIBUTE_NAME_SCRAP_VALUE} attribute.
     */
    protected void setScrapValue(final BigDecimal scrapValue) {
        this.scrapValue = scrapValue;
    }

    // ------------------------------------------------------------------------------ numberOfOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_ORDERS} attribute.
     */
    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_ORDERS} attribute with the specified value.
     *
     * @param numberOfOrders new value for {@value #ATTRIBUTE_NAME_NUMBER_OF_ORDERS} attribute.
     */
    protected void setNumberOfOrders(final Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
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

    // ---------------------------------------------------------------------------------- scrapLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SCRAP_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SCRAP_LEVEL} attribute.
     */
    public String getScrapLevel() {
        return scrapLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SCRAP_LEVEL} attribute with the specified value.
     *
     * @param scrapLevel new value for {@value #ATTRIBUTE_NAME_SCRAP_LEVEL} attribute.
     */
    protected void setScrapLevel(final String scrapLevel) {
        this.scrapLevel = scrapLevel;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETION_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate completionDate;

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
    @Size(max = COLUMN_LENGTH_WORK_CENTER_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WORK_CENTER_NAME, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_WORK_CENTER_NAME)
    private String workCenterName;

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
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_TYPE)
    private String itemType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_COMPLETED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_COMPLETED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_COMPLETED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_COMPLETED, scale = COLUMN_SCALE_TOTAL_COMPLETED)
    private BigDecimal totalCompleted;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_SCRAPPED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_SCRAPPED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SCRAPPED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_SCRAPPED, scale = COLUMN_SCALE_TOTAL_SCRAPPED)
    private BigDecimal totalScrapped;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_PRODUCED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_PRODUCED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_PRODUCED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_PRODUCED, scale = COLUMN_SCALE_TOTAL_PRODUCED)
    private BigDecimal totalProduced;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SCRAP_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SCRAP_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SCRAP_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SCRAP_RATE, scale = COLUMN_SCALE_SCRAP_RATE)
    private BigDecimal scrapRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SCRAP_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SCRAP_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SCRAP_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SCRAP_VALUE, scale = COLUMN_SCALE_SCRAP_VALUE)
    private BigDecimal scrapValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_OF_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer numberOfOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_UNIT_COST)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_UNIT_COST)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_UNIT_COST, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_UNIT_COST, scale = COLUMN_SCALE_AVG_UNIT_COST)
    private BigDecimal avgUnitCost;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SCRAP_LEVEL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SCRAP_LEVEL, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_SCRAP_LEVEL)
    private String scrapLevel;
}

