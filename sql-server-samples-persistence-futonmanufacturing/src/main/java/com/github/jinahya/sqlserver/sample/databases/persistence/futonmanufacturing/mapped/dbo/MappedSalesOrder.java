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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.SalesOrder} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_WAREHOUSE_ID}, {@value #COLUMN_NAME_SALES_CHANNEL_ID},
 * {@value #COLUMN_NAME_STORE_ID}, {@value #COLUMN_NAME_SALES_REP_ID} columns are mapped read-only, as a foreign key;
 * the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesOrder";

    // ---------------------------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

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

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ----------------------------------------------------------------------------------------------------- WarehouseID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_ID = "WarehouseID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_ID = "warehouseId";

    // ------------------------------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, DEFAULT (CONVERT([date],getdate())), NOT NULL} column. The value is {@value}.
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

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), DEFAULT ('Draft'), NOT NULL} column. The value is {@value}.
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

    // -------------------------------------------------------------------------------------------------------- Subtotal

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUBTOTAL = "Subtotal";

    /**
     * The precision of the {@value #COLUMN_NAME_SUBTOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SUBTOTAL = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_SUBTOTAL} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SUBTOTAL = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUBTOTAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUBTOTAL = "subtotal";

    // ------------------------------------------------------------------------------------------------------- TaxAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_AMOUNT = "TaxAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TAX_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TAX_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_AMOUNT = "taxAmount";

    // -------------------------------------------------------------------------------------------------- ShippingAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHIPPING_AMOUNT = "ShippingAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SHIPPING_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SHIPPING_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHIPPING_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHIPPING_AMOUNT = "shippingAmount";

    // ----------------------------------------------------------------------------------------------------- TotalAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
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

    // ----------------------------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // ------------------------------------------------------------------------------------------------------- CreatedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_BY = "CreatedBy";

    /**
     * The length of the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CREATED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_BY = "createdBy";

    // ----------------------------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------------------------- SalesChannelID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_CHANNEL_ID = "SalesChannelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL_ID = "salesChannelId";

    // --------------------------------------------------------------------------------------------------------- StoreID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STORE_ID = "StoreID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE_ID = "storeId";

    // ------------------------------------------------------------------------------------------------------ SalesRepID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REP_ID = "SalesRepID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP_ID = "salesRepId";

    // -------------------------------------------------------------------------------------------------- DiscountAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_AMOUNT = "DiscountAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_DISCOUNT_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_DISCOUNT_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_AMOUNT = "discountAmount";

    // ------------------------------------------------------------------------------------------------------- NetAmount

    /**
     * The name of the {@code decimal(19, 2), computed as ([TotalAmount]-[DiscountAmount])} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_NET_AMOUNT = "NetAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_NET_AMOUNT = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_NET_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NET_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NET_AMOUNT = "netAmount";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesOrderId=" + salesOrderId
               + ",orderNumber=" + orderNumber
               + ",customerId=" + customerId
               + ",warehouseId=" + warehouseId
               + ",orderDate=" + orderDate
               + ",requestedDeliveryDate=" + requestedDeliveryDate
               + ",shipDate=" + shipDate
               + ",status=" + status
               + ",subtotal=" + subtotal
               + ",taxAmount=" + taxAmount
               + ",shippingAmount=" + shippingAmount
               + ",totalAmount=" + totalAmount
               + ",notes=" + notes
               + ",createdBy=" + createdBy
               + ",createdDate=" + createdDate
               + ",modifiedDate=" + modifiedDate
               + ",salesChannelId=" + salesChannelId
               + ",storeId=" + storeId
               + ",salesRepId=" + salesRepId
               + ",discountAmount=" + discountAmount
               + ",netAmount=" + netAmount
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
        if (!(obj instanceof MappedSalesOrder that)) {
            return false;
        }
        return Objects.equals(salesOrderId, that.salesOrderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(salesOrderId);
    }

    // ---------------------------------------------------------------------------------------------------- salesOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    public Integer getSalesOrderId() {
        return salesOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the specified value.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
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
    public void setOrderNumber(final String orderNumber) {
        this.orderNumber = orderNumber;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    // ----------------------------------------------------------------------------------------------------- warehouseId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    public Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param warehouseId new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected void setWarehouseId(final Integer warehouseId) {
        this.warehouseId = warehouseId;
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
    public void setOrderDate(final LocalDate orderDate) {
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
    public void setRequestedDeliveryDate(final LocalDate requestedDeliveryDate) {
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
    public void setShipDate(final LocalDate shipDate) {
        this.shipDate = shipDate;
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
    public void setStatus(final String status) {
        this.status = status;
    }

    // -------------------------------------------------------------------------------------------------------- subtotal

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute with the specified value.
     *
     * @param subtotal new value for {@value #ATTRIBUTE_NAME_SUBTOTAL} attribute.
     */
    public void setSubtotal(final BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    // ------------------------------------------------------------------------------------------------------- taxAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute with the specified value.
     *
     * @param taxAmount new value for {@value #ATTRIBUTE_NAME_TAX_AMOUNT} attribute.
     */
    public void setTaxAmount(final BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    // -------------------------------------------------------------------------------------------------- shippingAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     */
    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute with the specified value.
     *
     * @param shippingAmount new value for {@value #ATTRIBUTE_NAME_SHIPPING_AMOUNT} attribute.
     */
    public void setShippingAmount(final BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
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
    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    // ----------------------------------------------------------------------------------------------------------- notes

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NOTES} attribute with the specified value.
     *
     * @param notes new value for {@value #ATTRIBUTE_NAME_NOTES} attribute.
     */
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    // ------------------------------------------------------------------------------------------------------- createdBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_BY} attribute with the specified value.
     *
     * @param createdBy new value for {@value #ATTRIBUTE_NAME_CREATED_BY} attribute.
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    // ----------------------------------------------------------------------------------------------------- createdDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute with the specified value.
     *
     * @param createdDate new value for {@value #ATTRIBUTE_NAME_CREATED_DATE} attribute.
     */
    public void setCreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -------------------------------------------------------------------------------------------------- salesChannelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     */
    public Integer getSalesChannelId() {
        return salesChannelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salesChannelId new value for {@value #ATTRIBUTE_NAME_SALES_CHANNEL_ID} attribute.
     */
    protected void setSalesChannelId(final Integer salesChannelId) {
        this.salesChannelId = salesChannelId;
    }

    // --------------------------------------------------------------------------------------------------------- storeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STORE_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param storeId new value for {@value #ATTRIBUTE_NAME_STORE_ID} attribute.
     */
    protected void setStoreId(final Integer storeId) {
        this.storeId = storeId;
    }

    // ------------------------------------------------------------------------------------------------------ salesRepId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute.
     */
    public Integer getSalesRepId() {
        return salesRepId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param salesRepId new value for {@value #ATTRIBUTE_NAME_SALES_REP_ID} attribute.
     */
    protected void setSalesRepId(final Integer salesRepId) {
        this.salesRepId = salesRepId;
    }

    // -------------------------------------------------------------------------------------------------- discountAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute with the specified value.
     *
     * @param discountAmount new value for {@value #ATTRIBUTE_NAME_DISCOUNT_AMOUNT} attribute.
     */
    public void setDiscountAmount(final BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    // ------------------------------------------------------------------------------------------------------- netAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     */
    public BigDecimal getNetAmount() {
        return netAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param netAmount new value for {@value #ATTRIBUTE_NAME_NET_AMOUNT} attribute.
     */
    protected void setNetAmount(final BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SALES_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer salesOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_ORDER_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_ORDER_NUMBER
    )
    private String orderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer warehouseId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate orderDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REQUESTED_DELIVERY_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate requestedDeliveryDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIP_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate shipDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATUS,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STATUS
    )
    private String status;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_SUBTOTAL - COLUMN_SCALE_SUBTOTAL, fraction = COLUMN_SCALE_SUBTOTAL)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUBTOTAL,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SUBTOTAL,
            scale = COLUMN_SCALE_SUBTOTAL
    )
    private BigDecimal subtotal;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TAX_AMOUNT - COLUMN_SCALE_TAX_AMOUNT, fraction = COLUMN_SCALE_TAX_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TAX_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TAX_AMOUNT,
            scale = COLUMN_SCALE_TAX_AMOUNT
    )
    private BigDecimal taxAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_SHIPPING_AMOUNT - COLUMN_SCALE_SHIPPING_AMOUNT,
            fraction = COLUMN_SCALE_SHIPPING_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHIPPING_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_SHIPPING_AMOUNT,
            scale = COLUMN_SCALE_SHIPPING_AMOUNT
    )
    private BigDecimal shippingAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TOTAL_AMOUNT - COLUMN_SCALE_TOTAL_AMOUNT, fraction = COLUMN_SCALE_TOTAL_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TOTAL_AMOUNT,
            scale = COLUMN_SCALE_TOTAL_AMOUNT
    )
    private BigDecimal totalAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String notes;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CREATED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_BY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CREATED_BY
    )
    private String createdBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime createdDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_CHANNEL_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer salesChannelId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_STORE_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer storeId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_REP_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer salesRepId;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_DISCOUNT_AMOUNT - COLUMN_SCALE_DISCOUNT_AMOUNT,
            fraction = COLUMN_SCALE_DISCOUNT_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISCOUNT_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_DISCOUNT_AMOUNT,
            scale = COLUMN_SCALE_DISCOUNT_AMOUNT
    )
    private BigDecimal discountAmount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Digits(integer = COLUMN_PRECISION_NET_AMOUNT - COLUMN_SCALE_NET_AMOUNT, fraction = COLUMN_SCALE_NET_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NET_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_NET_AMOUNT,
            scale = COLUMN_SCALE_NET_AMOUNT
    )
    private BigDecimal netAmount;
}

