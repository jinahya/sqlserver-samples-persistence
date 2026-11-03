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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_WorkCenterCapacity} view.
 * <p>
 * Identifier: the view declares no key; WorkCenterCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedWorkCenterCapacityView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_WorkCenterCapacity";

    // ------------------------------------------------------------------------------ WorkCenterCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WORK_CENTER_CODE = "WorkCenterCode";

    /**
     * The length of the {@value #COLUMN_NAME_WORK_CENTER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WORK_CENTER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WORK_CENTER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WORK_CENTER_CODE = "workCenterCode";

    // ------------------------------------------------------------------------------ WorkCenterName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
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

    // ------------------------------------------------------------------------------- DailyCapacity

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAILY_CAPACITY = "DailyCapacity";

    /**
     * The precision of the {@value #COLUMN_NAME_DAILY_CAPACITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DAILY_CAPACITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_DAILY_CAPACITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DAILY_CAPACITY = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DAILY_CAPACITY} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DAILY_CAPACITY = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DAILY_CAPACITY} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DAILY_CAPACITY = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAILY_CAPACITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAILY_CAPACITY = "dailyCapacity";

    // -------------------------------------------------------------------------------- ActiveOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTIVE_ORDERS = "ActiveOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTIVE_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTIVE_ORDERS = "activeOrders";

    // ---------------------------------------------------------------------------- InProgressOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IN_PROGRESS_ORDERS = "InProgressOrders";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IN_PROGRESS_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IN_PROGRESS_ORDERS = "inProgressOrders";

    // ------------------------------------------------------------------------ TotalQuantityPending

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_QUANTITY_PENDING = "TotalQuantityPending";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_QUANTITY_PENDING} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_QUANTITY_PENDING = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_QUANTITY_PENDING} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_QUANTITY_PENDING = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_QUANTITY_PENDING = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_QUANTITY_PENDING = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_QUANTITY_PENDING} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING = "totalQuantityPending";

    // ---------------------------------------------------------------------------------- DaysOfWork

    /**
     * The name of the {@code decimal(10, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_OF_WORK = "DaysOfWork";

    /**
     * The precision of the {@value #COLUMN_NAME_DAYS_OF_WORK} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DAYS_OF_WORK = 10;

    /**
     * The scale of the {@value #COLUMN_NAME_DAYS_OF_WORK} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DAYS_OF_WORK = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DAYS_OF_WORK} attribute, the minimum of the
     * {@code decimal(10, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DAYS_OF_WORK = "-99999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DAYS_OF_WORK} attribute, the maximum of the
     * {@code decimal(10, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DAYS_OF_WORK = "99999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_OF_WORK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_OF_WORK = "daysOfWork";

    // ------------------------------------------------------------------------ PercentOfTotalOrders

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERCENT_OF_TOTAL_ORDERS = "PercentOfTotalOrders";

    /**
     * The precision of the {@value #COLUMN_NAME_PERCENT_OF_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PERCENT_OF_TOTAL_ORDERS = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_PERCENT_OF_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PERCENT_OF_TOTAL_ORDERS = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PERCENT_OF_TOTAL_ORDERS = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PERCENT_OF_TOTAL_ORDERS = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERCENT_OF_TOTAL_ORDERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS = "percentOfTotalOrders";

    // ----------------------------------------------------------------------------- EarliestDueDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EARLIEST_DUE_DATE = "EarliestDueDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EARLIEST_DUE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EARLIEST_DUE_DATE = "earliestDueDate";

    // ------------------------------------------------------------------------------- LatestDueDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LATEST_DUE_DATE = "LatestDueDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LATEST_DUE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LATEST_DUE_DATE = "latestDueDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedWorkCenterCapacityView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "workCenterCode=" + workCenterCode
               + ",workCenterName=" + workCenterName
               + ",dailyCapacity=" + dailyCapacity
               + ",activeOrders=" + activeOrders
               + ",inProgressOrders=" + inProgressOrders
               + ",totalQuantityPending=" + totalQuantityPending
               + ",daysOfWork=" + daysOfWork
               + ",percentOfTotalOrders=" + percentOfTotalOrders
               + ",earliestDueDate=" + earliestDueDate
               + ",latestDueDate=" + latestDueDate
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
        if (!(obj instanceof MappedWorkCenterCapacityView that)) {
            return false;
        }
        return Objects.equals(workCenterCode, that.workCenterCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(workCenterCode);
    }

    // ------------------------------------------------------------------------------ workCenterCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute.
     */
    public String getWorkCenterCode() {
        return workCenterCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute with the specified value.
     *
     * @param workCenterCode new value for {@value #ATTRIBUTE_NAME_WORK_CENTER_CODE} attribute.
     */
    protected void setWorkCenterCode(final String workCenterCode) {
        this.workCenterCode = workCenterCode;
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

    // ------------------------------------------------------------------------------- dailyCapacity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAILY_CAPACITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAILY_CAPACITY} attribute.
     */
    public BigDecimal getDailyCapacity() {
        return dailyCapacity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAILY_CAPACITY} attribute with the specified value.
     *
     * @param dailyCapacity new value for {@value #ATTRIBUTE_NAME_DAILY_CAPACITY} attribute.
     */
    protected void setDailyCapacity(final BigDecimal dailyCapacity) {
        this.dailyCapacity = dailyCapacity;
    }

    // -------------------------------------------------------------------------------- activeOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTIVE_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTIVE_ORDERS} attribute.
     */
    public Integer getActiveOrders() {
        return activeOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTIVE_ORDERS} attribute with the specified value.
     *
     * @param activeOrders new value for {@value #ATTRIBUTE_NAME_ACTIVE_ORDERS} attribute.
     */
    protected void setActiveOrders(final Integer activeOrders) {
        this.activeOrders = activeOrders;
    }

    // ---------------------------------------------------------------------------- inProgressOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IN_PROGRESS_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IN_PROGRESS_ORDERS} attribute.
     */
    public Integer getInProgressOrders() {
        return inProgressOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IN_PROGRESS_ORDERS} attribute with the specified value.
     *
     * @param inProgressOrders new value for {@value #ATTRIBUTE_NAME_IN_PROGRESS_ORDERS} attribute.
     */
    protected void setInProgressOrders(final Integer inProgressOrders) {
        this.inProgressOrders = inProgressOrders;
    }

    // ------------------------------------------------------------------------ totalQuantityPending

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING} attribute.
     */
    public BigDecimal getTotalQuantityPending() {
        return totalQuantityPending;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING} attribute with the specified value.
     *
     * @param totalQuantityPending new value for {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_PENDING} attribute.
     */
    protected void setTotalQuantityPending(final BigDecimal totalQuantityPending) {
        this.totalQuantityPending = totalQuantityPending;
    }

    // ---------------------------------------------------------------------------------- daysOfWork

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_OF_WORK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_OF_WORK} attribute.
     */
    public BigDecimal getDaysOfWork() {
        return daysOfWork;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_OF_WORK} attribute with the specified value.
     *
     * @param daysOfWork new value for {@value #ATTRIBUTE_NAME_DAYS_OF_WORK} attribute.
     */
    protected void setDaysOfWork(final BigDecimal daysOfWork) {
        this.daysOfWork = daysOfWork;
    }

    // ------------------------------------------------------------------------ percentOfTotalOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS} attribute.
     */
    public BigDecimal getPercentOfTotalOrders() {
        return percentOfTotalOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS} attribute with the specified value.
     *
     * @param percentOfTotalOrders new value for {@value #ATTRIBUTE_NAME_PERCENT_OF_TOTAL_ORDERS} attribute.
     */
    protected void setPercentOfTotalOrders(final BigDecimal percentOfTotalOrders) {
        this.percentOfTotalOrders = percentOfTotalOrders;
    }

    // ----------------------------------------------------------------------------- earliestDueDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EARLIEST_DUE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EARLIEST_DUE_DATE} attribute.
     */
    public LocalDate getEarliestDueDate() {
        return earliestDueDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EARLIEST_DUE_DATE} attribute with the specified value.
     *
     * @param earliestDueDate new value for {@value #ATTRIBUTE_NAME_EARLIEST_DUE_DATE} attribute.
     */
    protected void setEarliestDueDate(final LocalDate earliestDueDate) {
        this.earliestDueDate = earliestDueDate;
    }

    // ------------------------------------------------------------------------------- latestDueDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LATEST_DUE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LATEST_DUE_DATE} attribute.
     */
    public LocalDate getLatestDueDate() {
        return latestDueDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LATEST_DUE_DATE} attribute with the specified value.
     *
     * @param latestDueDate new value for {@value #ATTRIBUTE_NAME_LATEST_DUE_DATE} attribute.
     */
    protected void setLatestDueDate(final LocalDate latestDueDate) {
        this.latestDueDate = latestDueDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_CENTER_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_WORK_CENTER_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WORK_CENTER_CODE)
    private String workCenterCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WORK_CENTER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WORK_CENTER_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WORK_CENTER_NAME)
    private String workCenterName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DAILY_CAPACITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DAILY_CAPACITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAILY_CAPACITY, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_DAILY_CAPACITY, scale = COLUMN_SCALE_DAILY_CAPACITY)
    private BigDecimal dailyCapacity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTIVE_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer activeOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_IN_PROGRESS_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer inProgressOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_QUANTITY_PENDING)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_QUANTITY_PENDING)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_QUANTITY_PENDING, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_QUANTITY_PENDING, scale = COLUMN_SCALE_TOTAL_QUANTITY_PENDING)
    private BigDecimal totalQuantityPending;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DAYS_OF_WORK)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DAYS_OF_WORK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_OF_WORK, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_DAYS_OF_WORK, scale = COLUMN_SCALE_DAYS_OF_WORK)
    private BigDecimal daysOfWork;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PERCENT_OF_TOTAL_ORDERS)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PERCENT_OF_TOTAL_ORDERS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PERCENT_OF_TOTAL_ORDERS, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PERCENT_OF_TOTAL_ORDERS, scale = COLUMN_SCALE_PERCENT_OF_TOTAL_ORDERS)
    private BigDecimal percentOfTotalOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EARLIEST_DUE_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate earliestDueDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LATEST_DUE_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate latestDueDate;
}

