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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_ProductionCompletionSummary} view.
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
public abstract class MappedProductionCompletionSummaryView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_ProductionCompletionSummary";

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

    // ---------------------------------------------------------------------------------------- Week

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WEEK = "Week";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WEEK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WEEK = "week";

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

    // ------------------------------------------------------------------------- NumberOfCompletions

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NUMBER_OF_COMPLETIONS = "NumberOfCompletions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NUMBER_OF_COMPLETIONS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NUMBER_OF_COMPLETIONS = "numberOfCompletions";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_SCRAPPED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_SCRAPPED = "totalScrapped";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_SCRAP_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SCRAP_RATE = "scrapRate";

    // ----------------------------------------------------------------------------- ProductionValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCTION_VALUE = "ProductionValue";

    /**
     * The precision of the {@value #COLUMN_NAME_PRODUCTION_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PRODUCTION_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PRODUCTION_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PRODUCTION_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCTION_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCTION_VALUE = "productionValue";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductionCompletionSummaryView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "completionDate=" + completionDate
               + ",year=" + year
               + ",month=" + month
               + ",week=" + week
               + ",workCenterName=" + workCenterName
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",itemType=" + itemType
               + ",numberOfCompletions=" + numberOfCompletions
               + ",totalCompleted=" + totalCompleted
               + ",totalScrapped=" + totalScrapped
               + ",scrapRate=" + scrapRate
               + ",productionValue=" + productionValue
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

    // ---------------------------------------------------------------------------------------- week

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WEEK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WEEK} attribute.
     */
    public Integer getWeek() {
        return week;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WEEK} attribute with the specified value.
     *
     * @param week new value for {@value #ATTRIBUTE_NAME_WEEK} attribute.
     */
    protected void setWeek(final Integer week) {
        this.week = week;
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

    // ------------------------------------------------------------------------- numberOfCompletions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_COMPLETIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_COMPLETIONS} attribute.
     */
    public Integer getNumberOfCompletions() {
        return numberOfCompletions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NUMBER_OF_COMPLETIONS} attribute with the specified value.
     *
     * @param numberOfCompletions new value for {@value #ATTRIBUTE_NAME_NUMBER_OF_COMPLETIONS} attribute.
     */
    protected void setNumberOfCompletions(final Integer numberOfCompletions) {
        this.numberOfCompletions = numberOfCompletions;
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

    // ----------------------------------------------------------------------------- productionValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute.
     */
    public BigDecimal getProductionValue() {
        return productionValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute with the specified value.
     *
     * @param productionValue new value for {@value #ATTRIBUTE_NAME_PRODUCTION_VALUE} attribute.
     */
    protected void setProductionValue(final BigDecimal productionValue) {
        this.productionValue = productionValue;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMPLETION_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate completionDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR, nullable = true, insertable = false, updatable = false)
    private Integer year;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH, nullable = true, insertable = false, updatable = false)
    private Integer month;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_WEEK, nullable = true, insertable = false, updatable = false)
    private Integer week;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NUMBER_OF_COMPLETIONS, nullable = true, insertable = false, updatable = false)
    private Integer numberOfCompletions;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_COMPLETED - COLUMN_SCALE_TOTAL_COMPLETED,
            fraction = COLUMN_SCALE_TOTAL_COMPLETED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_COMPLETED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_COMPLETED, scale = COLUMN_SCALE_TOTAL_COMPLETED)
    private BigDecimal totalCompleted;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_SCRAPPED - COLUMN_SCALE_TOTAL_SCRAPPED,
            fraction = COLUMN_SCALE_TOTAL_SCRAPPED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_SCRAPPED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_SCRAPPED, scale = COLUMN_SCALE_TOTAL_SCRAPPED)
    private BigDecimal totalScrapped;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_SCRAP_RATE - COLUMN_SCALE_SCRAP_RATE, fraction = COLUMN_SCALE_SCRAP_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SCRAP_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SCRAP_RATE, scale = COLUMN_SCALE_SCRAP_RATE)
    private BigDecimal scrapRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PRODUCTION_VALUE - COLUMN_SCALE_PRODUCTION_VALUE,
            fraction = COLUMN_SCALE_PRODUCTION_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PRODUCTION_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PRODUCTION_VALUE, scale = COLUMN_SCALE_PRODUCTION_VALUE)
    private BigDecimal productionValue;
}

