package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * sql-server-samples-persistence-futonmanufacturing
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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_SalesOrderBacklog} view.
 * <p>
 * Identifier: the view declares no key; OrderNumber is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); it contains
 * aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesOrderBacklogView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_SalesOrderBacklog";

    // ----------------------------------------------------------------------------------------------------- OrderNumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_NUMBER = "OrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ORDER_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_NUMBER = "orderNumber";

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

    // ---------------------------------------------------------------------------------------------------- CustomerName

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

    // ---------------------------------------------------------------------------------------------------- CustomerCode

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

    // --------------------------------------------------------------------------------------------------- WarehouseName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_NAME = "WarehouseName";

    /**
     * The length of the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WAREHOUSE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_NAME = "warehouseName";

    // ------------------------------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // ------------------------------------------------------------------------------------------- RequestedDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REQUESTED_DELIVERY_DATE = "RequestedDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REQUESTED_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REQUESTED_DELIVERY_DATE = "requestedDeliveryDate";

    // -------------------------------------------------------------------------------------------------------- ShipDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIP_DATE = "ShipDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIP_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIP_DATE = "shipDate";

    // -------------------------------------------------------------------------------------------------------- DaysOpen

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_OPEN = "DaysOpen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_OPEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_OPEN = "daysOpen";

    // ---------------------------------------------------------------------------------------------------- DaysUntilDue

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_UNTIL_DUE = "DaysUntilDue";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_UNTIL_DUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_UNTIL_DUE = "daysUntilDue";

    // ------------------------------------------------------------------------------------------------------- LineItems

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEMS = "LineItems";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_ITEMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_ITEMS = "lineItems";

    // -------------------------------------------------------------------------------------------- TotalQuantityOrdered

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_QUANTITY_ORDERED = "TotalQuantityOrdered";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_QUANTITY_ORDERED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_QUANTITY_ORDERED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_QUANTITY_ORDERED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_QUANTITY_ORDERED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_QUANTITY_ORDERED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_QUANTITY_ORDERED = "totalQuantityOrdered";

    // -------------------------------------------------------------------------------------------- TotalQuantityShipped

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_QUANTITY_SHIPPED = "TotalQuantityShipped";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_QUANTITY_SHIPPED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_QUANTITY_SHIPPED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_QUANTITY_SHIPPED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_QUANTITY_SHIPPED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_QUANTITY_SHIPPED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_QUANTITY_SHIPPED = "totalQuantityShipped";

    // ------------------------------------------------------------------------------------------------- QuantityBacklog

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_BACKLOG = "QuantityBacklog";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_BACKLOG} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_BACKLOG = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_BACKLOG} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_BACKLOG = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_BACKLOG} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_BACKLOG = "quantityBacklog";

    // ----------------------------------------------------------------------------------------------------- TotalAmount

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_AMOUNT = "TotalAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_AMOUNT = "totalAmount";

    // ------------------------------------------------------------------------------------------------------ OrderValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_VALUE = "OrderValue";

    /**
     * The precision of the {@value #COLUMN_NAME_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ORDER_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_ORDER_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ORDER_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_VALUE = "orderValue";

    // ---------------------------------------------------------------------------------------------------- BacklogValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BACKLOG_VALUE = "BacklogValue";

    /**
     * The precision of the {@value #COLUMN_NAME_BACKLOG_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_BACKLOG_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_BACKLOG_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_BACKLOG_VALUE = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BACKLOG_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BACKLOG_VALUE = "backlogValue";

    // ------------------------------------------------------------------------------------------------- PercentComplete

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERCENT_COMPLETE = "PercentComplete";

    /**
     * The precision of the {@value #COLUMN_NAME_PERCENT_COMPLETE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PERCENT_COMPLETE = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_PERCENT_COMPLETE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PERCENT_COMPLETE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERCENT_COMPLETE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERCENT_COMPLETE = "percentComplete";

    // ----------------------------------------------------------------------------------------------- FulfillmentStatus

    /**
     * The name of the {@code varchar(9), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_FULFILLMENT_STATUS = "FulfillmentStatus";

    /**
     * The length of the {@value #COLUMN_NAME_FULFILLMENT_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_FULFILLMENT_STATUS = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_FULFILLMENT_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FULFILLMENT_STATUS = "fulfillmentStatus";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrderBacklogView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "orderNumber=" + orderNumber
               + ",status=" + status
               + ",customerName=" + customerName
               + ",customerCode=" + customerCode
               + ",warehouseName=" + warehouseName
               + ",orderDate=" + orderDate
               + ",requestedDeliveryDate=" + requestedDeliveryDate
               + ",shipDate=" + shipDate
               + ",daysOpen=" + daysOpen
               + ",daysUntilDue=" + daysUntilDue
               + ",lineItems=" + lineItems
               + ",totalQuantityOrdered=" + totalQuantityOrdered
               + ",totalQuantityShipped=" + totalQuantityShipped
               + ",quantityBacklog=" + quantityBacklog
               + ",totalAmount=" + totalAmount
               + ",orderValue=" + orderValue
               + ",backlogValue=" + backlogValue
               + ",percentComplete=" + percentComplete
               + ",fulfillmentStatus=" + fulfillmentStatus
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
        if (!(obj instanceof MappedSalesOrderBacklogView that)) {
            return false;
        }
        return Objects.equals(orderNumber, that.orderNumber);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(orderNumber);
    }

    // ----------------------------------------------------------------------------------------------------- orderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_NUMBER} attribute.
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_NUMBER} attribute with the specified value.
     *
     * @param orderNumber new value for {@value #ATTRIBUTE_NAME_ORDER_NUMBER} attribute.
     */
    protected void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
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

    // ---------------------------------------------------------------------------------------------------- customerName

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

    // ---------------------------------------------------------------------------------------------------- customerCode

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

    // --------------------------------------------------------------------------------------------------- warehouseName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute with the specified value.
     *
     * @param warehouseName new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    protected void setWarehouseName(final String warehouseName) {
        this.warehouseName = warehouseName;
    }

    // ------------------------------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    protected void setOrderDate(final LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    // ------------------------------------------------------------------------------------------- requestedDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REQUESTED_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REQUESTED_DELIVERY_DATE} attribute.
     */
    public LocalDate getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REQUESTED_DELIVERY_DATE} attribute with the specified value.
     *
     * @param requestedDeliveryDate new value for {@value #ATTRIBUTE_NAME_REQUESTED_DELIVERY_DATE} attribute.
     */
    protected void setRequestedDeliveryDate(final LocalDate requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
    }

    // -------------------------------------------------------------------------------------------------------- shipDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    public LocalDate getShipDate() {
        return shipDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute with the specified value.
     *
     * @param shipDate new value for {@value #ATTRIBUTE_NAME_SHIP_DATE} attribute.
     */
    protected void setShipDate(final LocalDate shipDate) {
        this.shipDate = shipDate;
    }

    // -------------------------------------------------------------------------------------------------------- daysOpen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_OPEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_OPEN} attribute.
     */
    public Integer getDaysOpen() {
        return daysOpen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_OPEN} attribute with the specified value.
     *
     * @param daysOpen new value for {@value #ATTRIBUTE_NAME_DAYS_OPEN} attribute.
     */
    protected void setDaysOpen(final Integer daysOpen) {
        this.daysOpen = daysOpen;
    }

    // ---------------------------------------------------------------------------------------------------- daysUntilDue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_UNTIL_DUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_UNTIL_DUE} attribute.
     */
    public Integer getDaysUntilDue() {
        return daysUntilDue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_UNTIL_DUE} attribute with the specified value.
     *
     * @param daysUntilDue new value for {@value #ATTRIBUTE_NAME_DAYS_UNTIL_DUE} attribute.
     */
    protected void setDaysUntilDue(final Integer daysUntilDue) {
        this.daysUntilDue = daysUntilDue;
    }

    // ------------------------------------------------------------------------------------------------------- lineItems

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINE_ITEMS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINE_ITEMS} attribute.
     */
    public Integer getLineItems() {
        return lineItems;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINE_ITEMS} attribute with the specified value.
     *
     * @param lineItems new value for {@value #ATTRIBUTE_NAME_LINE_ITEMS} attribute.
     */
    protected void setLineItems(final Integer lineItems) {
        this.lineItems = lineItems;
    }

    // -------------------------------------------------------------------------------------------- totalQuantityOrdered

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_ORDERED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_ORDERED} attribute.
     */
    public BigDecimal getTotalQuantityOrdered() {
        return totalQuantityOrdered;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_ORDERED} attribute with the specified value.
     *
     * @param totalQuantityOrdered new value for {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_ORDERED} attribute.
     */
    protected void setTotalQuantityOrdered(final BigDecimal totalQuantityOrdered) {
        this.totalQuantityOrdered = totalQuantityOrdered;
    }

    // -------------------------------------------------------------------------------------------- totalQuantityShipped

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_SHIPPED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_SHIPPED} attribute.
     */
    public BigDecimal getTotalQuantityShipped() {
        return totalQuantityShipped;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_SHIPPED} attribute with the specified value.
     *
     * @param totalQuantityShipped new value for {@value #ATTRIBUTE_NAME_TOTAL_QUANTITY_SHIPPED} attribute.
     */
    protected void setTotalQuantityShipped(final BigDecimal totalQuantityShipped) {
        this.totalQuantityShipped = totalQuantityShipped;
    }

    // ------------------------------------------------------------------------------------------------- quantityBacklog

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_BACKLOG} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_BACKLOG} attribute.
     */
    public BigDecimal getQuantityBacklog() {
        return quantityBacklog;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_BACKLOG} attribute with the specified value.
     *
     * @param quantityBacklog new value for {@value #ATTRIBUTE_NAME_QUANTITY_BACKLOG} attribute.
     */
    protected void setQuantityBacklog(final BigDecimal quantityBacklog) {
        this.quantityBacklog = quantityBacklog;
    }

    // ----------------------------------------------------------------------------------------------------- totalAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute with the specified value.
     *
     * @param totalAmount new value for {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute.
     */
    protected void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // ------------------------------------------------------------------------------------------------------ orderValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_VALUE} attribute.
     */
    public BigDecimal getOrderValue() {
        return orderValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_VALUE} attribute with the specified value.
     *
     * @param orderValue new value for {@value #ATTRIBUTE_NAME_ORDER_VALUE} attribute.
     */
    protected void setOrderValue(final BigDecimal orderValue) {
        this.orderValue = orderValue;
    }

    // ---------------------------------------------------------------------------------------------------- backlogValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BACKLOG_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BACKLOG_VALUE} attribute.
     */
    public BigDecimal getBacklogValue() {
        return backlogValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BACKLOG_VALUE} attribute with the specified value.
     *
     * @param backlogValue new value for {@value #ATTRIBUTE_NAME_BACKLOG_VALUE} attribute.
     */
    protected void setBacklogValue(final BigDecimal backlogValue) {
        this.backlogValue = backlogValue;
    }

    // ------------------------------------------------------------------------------------------------- percentComplete

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute.
     */
    public BigDecimal getPercentComplete() {
        return percentComplete;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute with the specified value.
     *
     * @param percentComplete new value for {@value #ATTRIBUTE_NAME_PERCENT_COMPLETE} attribute.
     */
    protected void setPercentComplete(final BigDecimal percentComplete) {
        this.percentComplete = percentComplete;
    }

    // ----------------------------------------------------------------------------------------------- fulfillmentStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_FULFILLMENT_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_FULFILLMENT_STATUS} attribute.
     */
    public String getFulfillmentStatus() {
        return fulfillmentStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_FULFILLMENT_STATUS} attribute with the specified value.
     *
     * @param fulfillmentStatus new value for {@value #ATTRIBUTE_NAME_FULFILLMENT_STATUS} attribute.
     */
    protected void setFulfillmentStatus(final String fulfillmentStatus) {
        this.fulfillmentStatus = fulfillmentStatus;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ORDER_NUMBER)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ORDER_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ORDER_NUMBER
    )
    private String orderNumber;

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
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_NAME
    )
    private String customerName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_CODE
    )
    private String customerCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WAREHOUSE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_WAREHOUSE_NAME
    )
    private String warehouseName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate orderDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REQUESTED_DELIVERY_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate requestedDeliveryDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIP_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate shipDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_OPEN,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer daysOpen;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_UNTIL_DUE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer daysUntilDue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LINE_ITEMS,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer lineItems;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_QUANTITY_ORDERED - COLUMN_SCALE_TOTAL_QUANTITY_ORDERED,
            fraction = COLUMN_SCALE_TOTAL_QUANTITY_ORDERED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_QUANTITY_ORDERED,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_QUANTITY_ORDERED,
            scale = COLUMN_SCALE_TOTAL_QUANTITY_ORDERED
    )
    private BigDecimal totalQuantityOrdered;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_QUANTITY_SHIPPED - COLUMN_SCALE_TOTAL_QUANTITY_SHIPPED,
            fraction = COLUMN_SCALE_TOTAL_QUANTITY_SHIPPED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_QUANTITY_SHIPPED,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_QUANTITY_SHIPPED,
            scale = COLUMN_SCALE_TOTAL_QUANTITY_SHIPPED
    )
    private BigDecimal totalQuantityShipped;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_QUANTITY_BACKLOG - COLUMN_SCALE_QUANTITY_BACKLOG,
            fraction = COLUMN_SCALE_QUANTITY_BACKLOG)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_BACKLOG,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_QUANTITY_BACKLOG,
            scale = COLUMN_SCALE_QUANTITY_BACKLOG
    )
    private BigDecimal quantityBacklog;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_AMOUNT - COLUMN_SCALE_TOTAL_AMOUNT, fraction = COLUMN_SCALE_TOTAL_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_AMOUNT,
            scale = COLUMN_SCALE_TOTAL_AMOUNT
    )
    private BigDecimal totalAmount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_ORDER_VALUE - COLUMN_SCALE_ORDER_VALUE, fraction = COLUMN_SCALE_ORDER_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_ORDER_VALUE,
            scale = COLUMN_SCALE_ORDER_VALUE
    )
    private BigDecimal orderValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_BACKLOG_VALUE - COLUMN_SCALE_BACKLOG_VALUE,
            fraction = COLUMN_SCALE_BACKLOG_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BACKLOG_VALUE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_BACKLOG_VALUE,
            scale = COLUMN_SCALE_BACKLOG_VALUE
    )
    private BigDecimal backlogValue;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_PERCENT_COMPLETE - COLUMN_SCALE_PERCENT_COMPLETE,
            fraction = COLUMN_SCALE_PERCENT_COMPLETE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PERCENT_COMPLETE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_PERCENT_COMPLETE,
            scale = COLUMN_SCALE_PERCENT_COMPLETE
    )
    private BigDecimal percentComplete;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_FULFILLMENT_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FULFILLMENT_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_FULFILLMENT_STATUS
    )
    private String fulfillmentStatus;
}

