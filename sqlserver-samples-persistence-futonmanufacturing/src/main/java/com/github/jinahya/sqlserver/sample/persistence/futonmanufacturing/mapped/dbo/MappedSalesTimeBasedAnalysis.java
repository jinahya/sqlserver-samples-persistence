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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_TimeBasedAnalysis} view.
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
public abstract class MappedSalesTimeBasedAnalysis {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_TimeBasedAnalysis";

    // ----------------------------------------------------------------------------------- DayOfWeek

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAY_OF_WEEK = "DayOfWeek";

    /**
     * The length of the {@value #COLUMN_NAME_DAY_OF_WEEK} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DAY_OF_WEEK = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_OF_WEEK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_OF_WEEK = "dayOfWeek";

    // ----------------------------------------------------------------------------------- DayNumber

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAY_NUMBER = "DayNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_NUMBER = "dayNumber";

    // ----------------------------------------------------------------------------------- HourOfDay

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HOUR_OF_DAY = "HourOfDay";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HOUR_OF_DAY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_HOUR_OF_DAY = "hourOfDay";

    // ----------------------------------------------------------------------------------- TimeOfDay

    /**
     * The name of the {@code varchar(17), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_OF_DAY = "TimeOfDay";

    /**
     * The length of the {@value #COLUMN_NAME_TIME_OF_DAY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TIME_OF_DAY = 17;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_OF_DAY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_OF_DAY = "timeOfDay";

    // --------------------------------------------------------------------------------- ChannelName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_NAME = "ChannelName";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_NAME = "channelName";

    // ---------------------------------------------------------------------------------- OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // -------------------------------------------------------------------------------- TotalRevenue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_REVENUE = "TotalRevenue";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_REVENUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_REVENUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_REVENUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_REVENUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_REVENUE = "totalRevenue";

    // ------------------------------------------------------------------------------- AvgOrderValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_ORDER_VALUE = "AvgOrderValue";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_ORDER_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_ORDER_VALUE = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AVG_ORDER_VALUE = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AVG_ORDER_VALUE = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ORDER_VALUE = "avgOrderValue";

    // ----------------------------------------------------------------------------- UniqueCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_CUSTOMERS = "UniqueCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_CUSTOMERS = "uniqueCustomers";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesTimeBasedAnalysis() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "dayOfWeek=" + dayOfWeek
               + ",dayNumber=" + dayNumber
               + ",hourOfDay=" + hourOfDay
               + ",timeOfDay=" + timeOfDay
               + ",channelName=" + channelName
               + ",orderCount=" + orderCount
               + ",totalRevenue=" + totalRevenue
               + ",avgOrderValue=" + avgOrderValue
               + ",uniqueCustomers=" + uniqueCustomers
               + "}";
    }

    // ----------------------------------------------------------------------------------- dayOfWeek

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute with the specified value.
     *
     * @param dayOfWeek new value for {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     */
    protected void setDayOfWeek(final String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // ----------------------------------------------------------------------------------- dayNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute.
     */
    public Integer getDayNumber() {
        return dayNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute with the specified value.
     *
     * @param dayNumber new value for {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute.
     */
    protected void setDayNumber(final Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    // ----------------------------------------------------------------------------------- hourOfDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute.
     */
    public Integer getHourOfDay() {
        return hourOfDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute with the specified value.
     *
     * @param hourOfDay new value for {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute.
     */
    protected void setHourOfDay(final Integer hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    // ----------------------------------------------------------------------------------- timeOfDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute.
     */
    public String getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute with the specified value.
     *
     * @param timeOfDay new value for {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute.
     */
    protected void setTimeOfDay(final String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    // --------------------------------------------------------------------------------- channelName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute with the specified value.
     *
     * @param channelName new value for {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    protected void setChannelName(final String channelName) {
        this.channelName = channelName;
    }

    // ---------------------------------------------------------------------------------- orderCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute with the specified value.
     *
     * @param orderCount new value for {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    protected void setOrderCount(final Integer orderCount) {
        this.orderCount = orderCount;
    }

    // -------------------------------------------------------------------------------- totalRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute with the specified value.
     *
     * @param totalRevenue new value for {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    protected void setTotalRevenue(final BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    // ------------------------------------------------------------------------------- avgOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute with the specified value.
     *
     * @param avgOrderValue new value for {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    protected void setAvgOrderValue(final BigDecimal avgOrderValue) {
        this.avgOrderValue = avgOrderValue;
    }

    // ----------------------------------------------------------------------------- uniqueCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    public Integer getUniqueCustomers() {
        return uniqueCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute with the specified value.
     *
     * @param uniqueCustomers new value for {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    protected void setUniqueCustomers(final Integer uniqueCustomers) {
        this.uniqueCustomers = uniqueCustomers;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DAY_OF_WEEK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAY_OF_WEEK, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_DAY_OF_WEEK)
    private String dayOfWeek;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAY_NUMBER, nullable = true, insertable = false, updatable = false)
    private Integer dayNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_HOUR_OF_DAY, nullable = true, insertable = false, updatable = false)
    private Integer hourOfDay;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TIME_OF_DAY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TIME_OF_DAY, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_TIME_OF_DAY)
    private String timeOfDay;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CHANNEL_NAME)
    private String channelName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_COUNT, nullable = true, insertable = false, updatable = false)
    private Integer orderCount;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_REVENUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_REVENUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_REVENUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_REVENUE, scale = COLUMN_SCALE_TOTAL_REVENUE)
    private BigDecimal totalRevenue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVG_ORDER_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVG_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVG_ORDER_VALUE, scale = COLUMN_SCALE_AVG_ORDER_VALUE)
    private BigDecimal avgOrderValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS, nullable = true, insertable = false, updatable = false)
    private Integer uniqueCustomers;
}

