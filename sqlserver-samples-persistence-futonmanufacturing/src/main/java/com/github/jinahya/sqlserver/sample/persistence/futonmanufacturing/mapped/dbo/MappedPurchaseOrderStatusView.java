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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_PurchaseOrderStatus} view.
 * <p>
 * Identifier: the view declares no key; PONumber is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); it contains
 * aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedPurchaseOrderStatusView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_PurchaseOrderStatus";

    // ------------------------------------------------------------------------------------ PONumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PO_NUMBER = "PONumber";

    /**
     * The length of the {@value #COLUMN_NAME_PO_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PO_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PO_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PO_NUMBER = "poNumber";

    // -------------------------------------------------------------------------------------- Status

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

    // -------------------------------------------------------------------------------- SupplierName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_NAME = "SupplierName";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_NAME = "supplierName";

    // ------------------------------------------------------------------------------- WarehouseName

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

    // ----------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // ------------------------------------------------------------------------ ExpectedDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_DELIVERY_DATE = "ExpectedDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE = "expectedDeliveryDate";

    // -------------------------------------------------------------------------- ActualDeliveryDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_DELIVERY_DATE = "ActualDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE = "actualDeliveryDate";

    // ------------------------------------------------------------------------------ DaysSinceOrder

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_SINCE_ORDER = "DaysSinceOrder";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_SINCE_ORDER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_SINCE_ORDER = "daysSinceOrder";

    // -------------------------------------------------------------------------------- DaysVariance

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_VARIANCE = "DaysVariance";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_VARIANCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_VARIANCE = "daysVariance";

    // ----------------------------------------------------------------------------------- LineItems

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINE_ITEMS = "LineItems";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINE_ITEMS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINE_ITEMS = "lineItems";

    // --------------------------------------------------------------------------------- TotalAmount

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TOTAL_AMOUNT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TOTAL_AMOUNT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TOTAL_AMOUNT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_AMOUNT = "totalAmount";

    // ---------------------------------------------------------------------------------- LinesTotal

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LINES_TOTAL = "LinesTotal";

    /**
     * The precision of the {@value #COLUMN_NAME_LINES_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LINES_TOTAL = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_LINES_TOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LINES_TOTAL = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LINES_TOTAL} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LINES_TOTAL = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LINES_TOTAL} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LINES_TOTAL = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LINES_TOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LINES_TOTAL = "linesTotal";

    // ------------------------------------------------------------------------------- ReceivedValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECEIVED_VALUE = "ReceivedValue";

    /**
     * The precision of the {@value #COLUMN_NAME_RECEIVED_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RECEIVED_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_RECEIVED_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RECEIVED_VALUE = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_RECEIVED_VALUE} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_RECEIVED_VALUE = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_RECEIVED_VALUE} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_RECEIVED_VALUE = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECEIVED_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECEIVED_VALUE = "receivedValue";

    // ----------------------------------------------------------------------------- PercentReceived

    /**
     * The name of the {@code decimal(5, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PERCENT_RECEIVED = "PercentReceived";

    /**
     * The precision of the {@value #COLUMN_NAME_PERCENT_RECEIVED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PERCENT_RECEIVED = 5;

    /**
     * The scale of the {@value #COLUMN_NAME_PERCENT_RECEIVED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PERCENT_RECEIVED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PERCENT_RECEIVED} attribute, the minimum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PERCENT_RECEIVED = "-999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PERCENT_RECEIVED} attribute, the maximum of the
     * {@code decimal(5, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PERCENT_RECEIVED = "999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PERCENT_RECEIVED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PERCENT_RECEIVED = "percentReceived";

    // ------------------------------------------------------------------------------ DeliveryStatus

    /**
     * The name of the {@code varchar(9), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_STATUS = "DeliveryStatus";

    /**
     * The length of the {@value #COLUMN_NAME_DELIVERY_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DELIVERY_STATUS = 9;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_STATUS = "deliveryStatus";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPurchaseOrderStatusView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "poNumber=" + poNumber
               + ",status=" + status
               + ",supplierName=" + supplierName
               + ",warehouseName=" + warehouseName
               + ",orderDate=" + orderDate
               + ",expectedDeliveryDate=" + expectedDeliveryDate
               + ",actualDeliveryDate=" + actualDeliveryDate
               + ",daysSinceOrder=" + daysSinceOrder
               + ",daysVariance=" + daysVariance
               + ",lineItems=" + lineItems
               + ",totalAmount=" + totalAmount
               + ",linesTotal=" + linesTotal
               + ",receivedValue=" + receivedValue
               + ",percentReceived=" + percentReceived
               + ",deliveryStatus=" + deliveryStatus
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
        if (!(obj instanceof MappedPurchaseOrderStatusView that)) {
            return false;
        }
        return Objects.equals(getPoNumber(), that.getPoNumber());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getPoNumber());
    }

    // ------------------------------------------------------------------------------------ poNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute with the specified value.
     *
     * @param poNumber new value for {@value #ATTRIBUTE_NAME_PO_NUMBER} attribute.
     */
    protected void setPoNumber(final String poNumber) {
        this.poNumber = poNumber;
    }

    // -------------------------------------------------------------------------------------- status

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

    // -------------------------------------------------------------------------------- supplierName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute with the specified value.
     *
     * @param supplierName new value for {@value #ATTRIBUTE_NAME_SUPPLIER_NAME} attribute.
     */
    protected void setSupplierName(final String supplierName) {
        this.supplierName = supplierName;
    }

    // ------------------------------------------------------------------------------- warehouseName

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

    // ----------------------------------------------------------------------------------- orderDate

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

    // ------------------------------------------------------------------------ expectedDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute with the specified value.
     *
     * @param expectedDeliveryDate new value for {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    protected void setExpectedDeliveryDate(final LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    // -------------------------------------------------------------------------- actualDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     */
    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute with the specified value.
     *
     * @param actualDeliveryDate new value for {@value #ATTRIBUTE_NAME_ACTUAL_DELIVERY_DATE} attribute.
     */
    protected void setActualDeliveryDate(final LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    // ------------------------------------------------------------------------------ daysSinceOrder

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_ORDER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_ORDER} attribute.
     */
    public Integer getDaysSinceOrder() {
        return daysSinceOrder;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_ORDER} attribute with the specified value.
     *
     * @param daysSinceOrder new value for {@value #ATTRIBUTE_NAME_DAYS_SINCE_ORDER} attribute.
     */
    protected void setDaysSinceOrder(final Integer daysSinceOrder) {
        this.daysSinceOrder = daysSinceOrder;
    }

    // -------------------------------------------------------------------------------- daysVariance

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute.
     */
    public Integer getDaysVariance() {
        return daysVariance;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute with the specified value.
     *
     * @param daysVariance new value for {@value #ATTRIBUTE_NAME_DAYS_VARIANCE} attribute.
     */
    protected void setDaysVariance(final Integer daysVariance) {
        this.daysVariance = daysVariance;
    }

    // ----------------------------------------------------------------------------------- lineItems

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

    // --------------------------------------------------------------------------------- totalAmount

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

    // ---------------------------------------------------------------------------------- linesTotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LINES_TOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LINES_TOTAL} attribute.
     */
    public BigDecimal getLinesTotal() {
        return linesTotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LINES_TOTAL} attribute with the specified value.
     *
     * @param linesTotal new value for {@value #ATTRIBUTE_NAME_LINES_TOTAL} attribute.
     */
    protected void setLinesTotal(final BigDecimal linesTotal) {
        this.linesTotal = linesTotal;
    }

    // ------------------------------------------------------------------------------- receivedValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECEIVED_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECEIVED_VALUE} attribute.
     */
    public BigDecimal getReceivedValue() {
        return receivedValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECEIVED_VALUE} attribute with the specified value.
     *
     * @param receivedValue new value for {@value #ATTRIBUTE_NAME_RECEIVED_VALUE} attribute.
     */
    protected void setReceivedValue(final BigDecimal receivedValue) {
        this.receivedValue = receivedValue;
    }

    // ----------------------------------------------------------------------------- percentReceived

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PERCENT_RECEIVED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PERCENT_RECEIVED} attribute.
     */
    public BigDecimal getPercentReceived() {
        return percentReceived;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PERCENT_RECEIVED} attribute with the specified value.
     *
     * @param percentReceived new value for {@value #ATTRIBUTE_NAME_PERCENT_RECEIVED} attribute.
     */
    protected void setPercentReceived(final BigDecimal percentReceived) {
        this.percentReceived = percentReceived;
    }

    // ------------------------------------------------------------------------------ deliveryStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_STATUS} attribute.
     */
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_STATUS} attribute with the specified value.
     *
     * @param deliveryStatus new value for {@value #ATTRIBUTE_NAME_DELIVERY_STATUS} attribute.
     */
    protected void setDeliveryStatus(final String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PO_NUMBER)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PO_NUMBER, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_PO_NUMBER)
    private String poNumber;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_STATUS)
    private String status;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SUPPLIER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_SUPPLIER_NAME)
    private String supplierName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WAREHOUSE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WAREHOUSE_NAME)
    private String warehouseName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE, nullable = false, insertable = false, updatable = false)
    private LocalDate orderDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate expectedDeliveryDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ACTUAL_DELIVERY_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDate actualDeliveryDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_SINCE_ORDER, nullable = true, insertable = false, updatable = false)
    private Integer daysSinceOrder;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_VARIANCE, nullable = true, insertable = false, updatable = false)
    private Integer daysVariance;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LINE_ITEMS, nullable = true, insertable = false, updatable = false)
    private Integer lineItems;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TOTAL_AMOUNT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TOTAL_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_AMOUNT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_TOTAL_AMOUNT, scale = COLUMN_SCALE_TOTAL_AMOUNT)
    private BigDecimal totalAmount;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_LINES_TOTAL)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_LINES_TOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LINES_TOTAL, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_LINES_TOTAL, scale = COLUMN_SCALE_LINES_TOTAL)
    private BigDecimal linesTotal;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_RECEIVED_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_RECEIVED_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RECEIVED_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_RECEIVED_VALUE, scale = COLUMN_SCALE_RECEIVED_VALUE)
    private BigDecimal receivedValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PERCENT_RECEIVED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PERCENT_RECEIVED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PERCENT_RECEIVED, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PERCENT_RECEIVED, scale = COLUMN_SCALE_PERCENT_RECEIVED)
    private BigDecimal percentReceived;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DELIVERY_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_STATUS, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_DELIVERY_STATUS)
    private String deliveryStatus;
}

