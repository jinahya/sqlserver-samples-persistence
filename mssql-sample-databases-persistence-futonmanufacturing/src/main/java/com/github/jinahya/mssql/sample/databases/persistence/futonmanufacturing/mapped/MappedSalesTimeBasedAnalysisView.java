package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_TimeBasedAnalysis} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedSalesTimeBasedAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_TimeBasedAnalysis";

    // ------------------------------------------------------------------------------------------------------- DayOfWeek

    /**
     * The name of the {@code nvarchar(30)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAY_OF_WEEK = "DayOfWeek";

    /**
     * The length of the {@value #COLUMN_NAME_DAY_OF_WEEK} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DAY_OF_WEEK = 30;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_OF_WEEK} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_OF_WEEK = "dayOfWeek";

    // ------------------------------------------------------------------------------------------------------- DayNumber

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAY_NUMBER = "DayNumber";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAY_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAY_NUMBER = "dayNumber";

    // ------------------------------------------------------------------------------------------------------- HourOfDay

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_HOUR_OF_DAY = "HourOfDay";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_HOUR_OF_DAY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_HOUR_OF_DAY = "hourOfDay";

    // ------------------------------------------------------------------------------------------------------- TimeOfDay

    /**
     * The name of the {@code varchar(17), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TIME_OF_DAY = "TimeOfDay";

    /**
     * The length of the {@value #COLUMN_NAME_TIME_OF_DAY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TIME_OF_DAY = 17;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TIME_OF_DAY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TIME_OF_DAY = "timeOfDay";

    // ----------------------------------------------------------------------------------------------------- ChannelName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_NAME = "ChannelName";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_NAME = "channelName";

    // ------------------------------------------------------------------------------------------------------ OrderCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_COUNT = "OrderCount";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_COUNT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_COUNT = "orderCount";

    // ---------------------------------------------------------------------------------------------------- TotalRevenue

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REVENUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_REVENUE = "totalRevenue";

    // --------------------------------------------------------------------------------------------------- AvgOrderValue

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_ORDER_VALUE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_ORDER_VALUE = "avgOrderValue";

    // ------------------------------------------------------------------------------------------------- UniqueCustomers

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIQUE_CUSTOMERS = "UniqueCustomers";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIQUE_CUSTOMERS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIQUE_CUSTOMERS = "uniqueCustomers";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesTimeBasedAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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

    // ------------------------------------------------------------------------------------------------------- dayOfWeek

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     */
    @Nullable
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute with the specified value.
     *
     * @param dayOfWeek new value for {@value #ATTRIBUTE_NAME_DAY_OF_WEEK} attribute.
     */
    protected void setDayOfWeek(@Nullable final String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    // ------------------------------------------------------------------------------------------------------- dayNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute.
     */
    @Nullable
    public Integer getDayNumber() {
        return dayNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute with the specified value.
     *
     * @param dayNumber new value for {@value #ATTRIBUTE_NAME_DAY_NUMBER} attribute.
     */
    protected void setDayNumber(@Nullable final Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    // ------------------------------------------------------------------------------------------------------- hourOfDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute.
     */
    @Nullable
    public Integer getHourOfDay() {
        return hourOfDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute with the specified value.
     *
     * @param hourOfDay new value for {@value #ATTRIBUTE_NAME_HOUR_OF_DAY} attribute.
     */
    protected void setHourOfDay(@Nullable final Integer hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    // ------------------------------------------------------------------------------------------------------- timeOfDay

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute.
     */
    @Nonnull
    public String getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute with the specified value.
     *
     * @param timeOfDay new value for {@value #ATTRIBUTE_NAME_TIME_OF_DAY} attribute.
     */
    protected void setTimeOfDay(@Nonnull final String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    // ----------------------------------------------------------------------------------------------------- channelName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    @Nonnull
    public String getChannelName() {
        return channelName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute with the specified value.
     *
     * @param channelName new value for {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    protected void setChannelName(@Nonnull final String channelName) {
        this.channelName = channelName;
    }

    // ------------------------------------------------------------------------------------------------------ orderCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    @Nullable
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute with the specified value.
     *
     * @param orderCount new value for {@value #ATTRIBUTE_NAME_ORDER_COUNT} attribute.
     */
    protected void setOrderCount(@Nullable final Integer orderCount) {
        this.orderCount = orderCount;
    }

    // ---------------------------------------------------------------------------------------------------- totalRevenue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    @Nullable
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute with the specified value.
     *
     * @param totalRevenue new value for {@value #ATTRIBUTE_NAME_TOTAL_REVENUE} attribute.
     */
    protected void setTotalRevenue(@Nullable final BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    // --------------------------------------------------------------------------------------------------- avgOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    @Nullable
    public BigDecimal getAvgOrderValue() {
        return avgOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute with the specified value.
     *
     * @param avgOrderValue new value for {@value #ATTRIBUTE_NAME_AVG_ORDER_VALUE} attribute.
     */
    protected void setAvgOrderValue(@Nullable final BigDecimal avgOrderValue) {
        this.avgOrderValue = avgOrderValue;
    }

    // ------------------------------------------------------------------------------------------------- uniqueCustomers

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    @Nullable
    public Integer getUniqueCustomers() {
        return uniqueCustomers;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute with the specified value.
     *
     * @param uniqueCustomers new value for {@value #ATTRIBUTE_NAME_UNIQUE_CUSTOMERS} attribute.
     */
    protected void setUniqueCustomers(@Nullable final Integer uniqueCustomers) {
        this.uniqueCustomers = uniqueCustomers;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_DAY_OF_WEEK)
    @Column(name = COLUMN_NAME_DAY_OF_WEEK,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DAY_OF_WEEK
    )
    private String dayOfWeek;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DAY_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer dayNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_HOUR_OF_DAY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer hourOfDay;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_TIME_OF_DAY)
    @NotNull
    @Column(name = COLUMN_NAME_TIME_OF_DAY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TIME_OF_DAY
    )
    private String timeOfDay;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_ORDER_COUNT,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer orderCount;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_TOTAL_REVENUE - COLUMN_SCALE_TOTAL_REVENUE, fraction = COLUMN_SCALE_TOTAL_REVENUE)
    @Column(name = COLUMN_NAME_TOTAL_REVENUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TOTAL_REVENUE,
            scale = COLUMN_SCALE_TOTAL_REVENUE
    )
    private BigDecimal totalRevenue;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_AVG_ORDER_VALUE - COLUMN_SCALE_AVG_ORDER_VALUE, fraction = COLUMN_SCALE_AVG_ORDER_VALUE)
    @Column(name = COLUMN_NAME_AVG_ORDER_VALUE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_AVG_ORDER_VALUE,
            scale = COLUMN_SCALE_AVG_ORDER_VALUE
    )
    private BigDecimal avgOrderValue;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_UNIQUE_CUSTOMERS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer uniqueCustomers;
}

