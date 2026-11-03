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
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_CustomerFulfillmentRate} view.
 * <p>
 * Identifier: the view declares no key; CustomerCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedCustomerFulfillmentRateView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_CustomerFulfillmentRate";

    // -------------------------------------------------------------------------------- CustomerCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_CODE = "CustomerCode";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_CODE = "customerCode";

    // -------------------------------------------------------------------------------- CustomerName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_NAME = "CustomerName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

    // --------------------------------------------------------------------------------- TotalOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDERS = "TotalOrders";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_TOTAL_ORDERS = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_TOTAL_ORDERS = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDERS = "totalOrders";

    // ----------------------------------------------------------------------------- TotalOrderValue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_ORDER_VALUE = "TotalOrderValue";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_ORDER_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_ORDER_VALUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_ORDER_VALUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_ORDER_VALUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_ORDER_VALUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_ORDER_VALUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_ORDER_VALUE = "totalOrderValue";

    // ----------------------------------------------------------------------------- DeliveredOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERED_ORDERS = "DeliveredOrders";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DELIVERED_ORDERS} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_DELIVERED_ORDERS = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DELIVERED_ORDERS} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_DELIVERED_ORDERS = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERED_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERED_ORDERS = "deliveredOrders";

    // ---------------------------------------------------------------------------- OnTimeDeliveries

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ON_TIME_DELIVERIES = "OnTimeDeliveries";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_ON_TIME_DELIVERIES = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_ON_TIME_DELIVERIES = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ON_TIME_DELIVERIES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ON_TIME_DELIVERIES = "onTimeDeliveries";

    // ------------------------------------------------------------------------------ DeliveredValue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERED_VALUE = "DeliveredValue";

    /**
     * The precision of the {@value #COLUMN_NAME_DELIVERED_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DELIVERED_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_DELIVERED_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DELIVERED_VALUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DELIVERED_VALUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_DELIVERED_VALUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DELIVERED_VALUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_DELIVERED_VALUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERED_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERED_VALUE = "deliveredValue";

    // ------------------------------------------------------------------------------- PendingOrders

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PENDING_ORDERS = "PendingOrders";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PENDING_ORDERS} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PENDING_ORDERS = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PENDING_ORDERS} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PENDING_ORDERS = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PENDING_ORDERS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PENDING_ORDERS = "pendingOrders";

    // -------------------------------------------------------------------------------- PendingValue

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PENDING_VALUE = "PendingValue";

    /**
     * The precision of the {@value #COLUMN_NAME_PENDING_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PENDING_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_PENDING_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PENDING_VALUE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PENDING_VALUE} attribute, the minimum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PENDING_VALUE = "-999999999999999999999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PENDING_VALUE} attribute, the maximum of the
     * {@code decimal(38, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PENDING_VALUE = "999999999999999999999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PENDING_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PENDING_VALUE = "pendingValue";

    // ----------------------------------------------------------------------------- FulfillmentRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FULFILLMENT_RATE = "FulfillmentRate";

    /**
     * The precision of the {@value #COLUMN_NAME_FULFILLMENT_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_FULFILLMENT_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_FULFILLMENT_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_FULFILLMENT_RATE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_FULFILLMENT_RATE} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_FULFILLMENT_RATE = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_FULFILLMENT_RATE} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_FULFILLMENT_RATE = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULFILLMENT_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULFILLMENT_RATE = "fulfillmentRate";

    // -------------------------------------------------------------------------- OnTimeDeliveryRate

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ON_TIME_DELIVERY_RATE = "OnTimeDeliveryRate";

    /**
     * The precision of the {@value #COLUMN_NAME_ON_TIME_DELIVERY_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ON_TIME_DELIVERY_RATE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_ON_TIME_DELIVERY_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ON_TIME_DELIVERY_RATE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_ON_TIME_DELIVERY_RATE = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_ON_TIME_DELIVERY_RATE = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ON_TIME_DELIVERY_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE = "onTimeDeliveryRate";

    // ------------------------------------------------------------------------------- AvgDaysToShip

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DAYS_TO_SHIP = "AvgDaysToShip";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_AVG_DAYS_TO_SHIP = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_AVG_DAYS_TO_SHIP = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DAYS_TO_SHIP} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP = "avgDaysToShip";

    // ------------------------------------------------------------------------- AvgDeliveryVariance

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_DELIVERY_VARIANCE = "AvgDeliveryVariance";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_AVG_DELIVERY_VARIANCE = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_AVG_DELIVERY_VARIANCE = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_DELIVERY_VARIANCE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE = "avgDeliveryVariance";

    // -------------------------------------------------------------------------------- ServiceLevel

    /**
     * The name of the {@code varchar(9), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SERVICE_LEVEL = "ServiceLevel";

    /**
     * The length of the {@value #COLUMN_NAME_SERVICE_LEVEL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SERVICE_LEVEL = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SERVICE_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SERVICE_LEVEL = "serviceLevel";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedCustomerFulfillmentRateView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customerCode=" + customerCode
               + ",customerName=" + customerName
               + ",totalOrders=" + totalOrders
               + ",totalOrderValue=" + totalOrderValue
               + ",deliveredOrders=" + deliveredOrders
               + ",onTimeDeliveries=" + onTimeDeliveries
               + ",deliveredValue=" + deliveredValue
               + ",pendingOrders=" + pendingOrders
               + ",pendingValue=" + pendingValue
               + ",fulfillmentRate=" + fulfillmentRate
               + ",onTimeDeliveryRate=" + onTimeDeliveryRate
               + ",avgDaysToShip=" + avgDaysToShip
               + ",avgDeliveryVariance=" + avgDeliveryVariance
               + ",serviceLevel=" + serviceLevel
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
        if (!(obj instanceof MappedCustomerFulfillmentRateView that)) {
            return false;
        }
        return Objects.equals(customerCode, that.customerCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(customerCode);
    }

    // -------------------------------------------------------------------------------- customerCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    public String getCustomerCode() {
        return customerCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute with the specified value.
     *
     * @param customerCode new value for {@value #ATTRIBUTE_NAME_CUSTOMER_CODE} attribute.
     */
    protected void setCustomerCode(final String customerCode) {
        this.customerCode = customerCode;
    }

    // -------------------------------------------------------------------------------- customerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute with the specified value.
     *
     * @param customerName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    protected void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    // --------------------------------------------------------------------------------- totalOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    public Integer getTotalOrders() {
        return totalOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute with the specified value.
     *
     * @param totalOrders new value for {@value #ATTRIBUTE_NAME_TOTAL_ORDERS} attribute.
     */
    protected void setTotalOrders(final Integer totalOrders) {
        this.totalOrders = totalOrders;
    }

    // ----------------------------------------------------------------------------- totalOrderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDER_VALUE} attribute.
     */
    public BigDecimal getTotalOrderValue() {
        return totalOrderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_ORDER_VALUE} attribute with the specified value.
     *
     * @param totalOrderValue new value for {@value #ATTRIBUTE_NAME_TOTAL_ORDER_VALUE} attribute.
     */
    protected void setTotalOrderValue(final BigDecimal totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    // ----------------------------------------------------------------------------- deliveredOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERED_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERED_ORDERS} attribute.
     */
    public Integer getDeliveredOrders() {
        return deliveredOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERED_ORDERS} attribute with the specified value.
     *
     * @param deliveredOrders new value for {@value #ATTRIBUTE_NAME_DELIVERED_ORDERS} attribute.
     */
    protected void setDeliveredOrders(final Integer deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    // ---------------------------------------------------------------------------- onTimeDeliveries

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute.
     */
    public Integer getOnTimeDeliveries() {
        return onTimeDeliveries;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute with the specified value.
     *
     * @param onTimeDeliveries new value for {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERIES} attribute.
     */
    protected void setOnTimeDeliveries(final Integer onTimeDeliveries) {
        this.onTimeDeliveries = onTimeDeliveries;
    }

    // ------------------------------------------------------------------------------ deliveredValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERED_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERED_VALUE} attribute.
     */
    public BigDecimal getDeliveredValue() {
        return deliveredValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERED_VALUE} attribute with the specified value.
     *
     * @param deliveredValue new value for {@value #ATTRIBUTE_NAME_DELIVERED_VALUE} attribute.
     */
    protected void setDeliveredValue(final BigDecimal deliveredValue) {
        this.deliveredValue = deliveredValue;
    }

    // ------------------------------------------------------------------------------- pendingOrders

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PENDING_ORDERS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PENDING_ORDERS} attribute.
     */
    public Integer getPendingOrders() {
        return pendingOrders;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PENDING_ORDERS} attribute with the specified value.
     *
     * @param pendingOrders new value for {@value #ATTRIBUTE_NAME_PENDING_ORDERS} attribute.
     */
    protected void setPendingOrders(final Integer pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    // -------------------------------------------------------------------------------- pendingValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PENDING_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PENDING_VALUE} attribute.
     */
    public BigDecimal getPendingValue() {
        return pendingValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PENDING_VALUE} attribute with the specified value.
     *
     * @param pendingValue new value for {@value #ATTRIBUTE_NAME_PENDING_VALUE} attribute.
     */
    protected void setPendingValue(final BigDecimal pendingValue) {
        this.pendingValue = pendingValue;
    }

    // ----------------------------------------------------------------------------- fulfillmentRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULFILLMENT_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULFILLMENT_RATE} attribute.
     */
    public BigDecimal getFulfillmentRate() {
        return fulfillmentRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULFILLMENT_RATE} attribute with the specified value.
     *
     * @param fulfillmentRate new value for {@value #ATTRIBUTE_NAME_FULFILLMENT_RATE} attribute.
     */
    protected void setFulfillmentRate(final BigDecimal fulfillmentRate) {
        this.fulfillmentRate = fulfillmentRate;
    }

    // -------------------------------------------------------------------------- onTimeDeliveryRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute.
     */
    public BigDecimal getOnTimeDeliveryRate() {
        return onTimeDeliveryRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute with the specified value.
     *
     * @param onTimeDeliveryRate new value for {@value #ATTRIBUTE_NAME_ON_TIME_DELIVERY_RATE} attribute.
     */
    protected void setOnTimeDeliveryRate(final BigDecimal onTimeDeliveryRate) {
        this.onTimeDeliveryRate = onTimeDeliveryRate;
    }

    // ------------------------------------------------------------------------------- avgDaysToShip

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute.
     */
    public Integer getAvgDaysToShip() {
        return avgDaysToShip;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute with the specified value.
     *
     * @param avgDaysToShip new value for {@value #ATTRIBUTE_NAME_AVG_DAYS_TO_SHIP} attribute.
     */
    protected void setAvgDaysToShip(final Integer avgDaysToShip) {
        this.avgDaysToShip = avgDaysToShip;
    }

    // ------------------------------------------------------------------------- avgDeliveryVariance

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE} attribute.
     */
    public Integer getAvgDeliveryVariance() {
        return avgDeliveryVariance;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE} attribute with the specified value.
     *
     * @param avgDeliveryVariance new value for {@value #ATTRIBUTE_NAME_AVG_DELIVERY_VARIANCE} attribute.
     */
    protected void setAvgDeliveryVariance(final Integer avgDeliveryVariance) {
        this.avgDeliveryVariance = avgDeliveryVariance;
    }

    // -------------------------------------------------------------------------------- serviceLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SERVICE_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SERVICE_LEVEL} attribute.
     */
    public String getServiceLevel() {
        return serviceLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SERVICE_LEVEL} attribute with the specified value.
     *
     * @param serviceLevel new value for {@value #ATTRIBUTE_NAME_SERVICE_LEVEL} attribute.
     */
    protected void setServiceLevel(final String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CUSTOMER_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CUSTOMER_CODE)
    private String customerCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_CUSTOMER_NAME)
    private String customerName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_TOTAL_ORDERS)
    @Min(ATTRIBUTE_MIN_TOTAL_ORDERS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer totalOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_ORDER_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_ORDER_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_ORDER_VALUE, scale = COLUMN_SCALE_TOTAL_ORDER_VALUE)
    private BigDecimal totalOrderValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_DELIVERED_ORDERS)
    @Min(ATTRIBUTE_MIN_DELIVERED_ORDERS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERED_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer deliveredOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_ON_TIME_DELIVERIES)
    @Min(ATTRIBUTE_MIN_ON_TIME_DELIVERIES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ON_TIME_DELIVERIES, nullable = true, insertable = false, updatable = false)
    private Integer onTimeDeliveries;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_DELIVERED_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_DELIVERED_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERED_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_DELIVERED_VALUE, scale = COLUMN_SCALE_DELIVERED_VALUE)
    private BigDecimal deliveredValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_PENDING_ORDERS)
    @Min(ATTRIBUTE_MIN_PENDING_ORDERS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PENDING_ORDERS, nullable = true, insertable = false, updatable = false)
    private Integer pendingOrders;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PENDING_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PENDING_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PENDING_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PENDING_VALUE, scale = COLUMN_SCALE_PENDING_VALUE)
    private BigDecimal pendingValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_FULFILLMENT_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_FULFILLMENT_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_FULFILLMENT_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_FULFILLMENT_RATE, scale = COLUMN_SCALE_FULFILLMENT_RATE)
    private BigDecimal fulfillmentRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_ON_TIME_DELIVERY_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_ON_TIME_DELIVERY_RATE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ON_TIME_DELIVERY_RATE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_ON_TIME_DELIVERY_RATE, scale = COLUMN_SCALE_ON_TIME_DELIVERY_RATE)
    private BigDecimal onTimeDeliveryRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_AVG_DAYS_TO_SHIP)
    @Min(ATTRIBUTE_MIN_AVG_DAYS_TO_SHIP)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DAYS_TO_SHIP, nullable = true, insertable = false, updatable = false)
    private Integer avgDaysToShip;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_AVG_DELIVERY_VARIANCE)
    @Min(ATTRIBUTE_MIN_AVG_DELIVERY_VARIANCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_DELIVERY_VARIANCE, nullable = true, insertable = false, updatable = false)
    private Integer avgDeliveryVariance;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SERVICE_LEVEL)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SERVICE_LEVEL, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_SERVICE_LEVEL)
    private String serviceLevel;
}

