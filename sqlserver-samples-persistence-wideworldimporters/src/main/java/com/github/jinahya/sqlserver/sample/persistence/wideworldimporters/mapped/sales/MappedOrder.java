package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.Orders} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_SALESPERSON_PERSON_ID},
 * {@value #COLUMN_NAME_PICKED_BY_PERSON_ID}, {@value #COLUMN_NAME_CONTACT_PERSON_ID},
 * {@value #COLUMN_NAME_BACKORDER_ORDER_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.Orders}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code OrderID}</th>
 *     <td>Numeric ID used for reference to an order within the database</td></tr>
 * <tr><th scope="row">{@code CustomerID}</th><td>Customer for this order</td></tr>
 * <tr><th scope="row">{@code SalespersonPersonID}</th><td>Salesperson for this order</td></tr>
 * <tr><th scope="row">{@code PickedByPersonID}</th><td>Person who picked this shipment</td></tr>
 * <tr><th scope="row">{@code ContactPersonID}</th><td>Customer contact for this order</td></tr>
 * <tr><th scope="row">{@code BackorderOrderID}</th>
 *     <td>If this order is a backorder, this column holds the original order number</td></tr>
 * <tr><th scope="row">{@code OrderDate}</th><td>Date that this order was raised</td></tr>
 * <tr><th scope="row">{@code ExpectedDeliveryDate}</th><td>Expected delivery date</td></tr>
 * <tr><th scope="row">{@code CustomerPurchaseOrderNumber}</th>
 *     <td>Purchase Order Number received from customer</td></tr>
 * <tr><th scope="row">{@code IsUndersupplyBackordered}</th>
 *     <td>If items cannot be supplied are they backordered?</td></tr>
 * <tr><th scope="row">{@code Comments}</th>
 *     <td>Any comments related to this order (sent to customer)</td></tr>
 * <tr><th scope="row">{@code DeliveryInstructions}</th>
 *     <td>Any comments related to order delivery (sent to customer)</td></tr>
 * <tr><th scope="row">{@code InternalComments}</th>
 *     <td>Any internal comments related to this order (not sent to the customer)</td></tr>
 * <tr><th scope="row">{@code PickingCompletedWhen}</th>
 *     <td>When was picking of the entire order completed?</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.Orders}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code OrderID}</th><td>{@code (NEXT VALUE FOR [Sequences].[OrderID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedOrder.TABLE_SCHEMA,
        table = MappedOrder.TABLE_NAME,
        value = "Detail of customer orders"
)
public abstract class MappedOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Orders";

    // ------------------------------------------------------------------------------------- OrderID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.OrderID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "OrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ---------------------------------------------------------------------------------- CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ------------------------------------------------------------------------- SalespersonPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALESPERSON_PERSON_ID = "SalespersonPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALESPERSON_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALESPERSON_PERSON_ID = "salespersonPersonId";

    // ---------------------------------------------------------------------------- PickedByPersonID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICKED_BY_PERSON_ID = "PickedByPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICKED_BY_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKED_BY_PERSON_ID = "pickedByPersonId";

    // ----------------------------------------------------------------------------- ContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTACT_PERSON_ID = "ContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTACT_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON_ID = "contactPersonId";

    // ---------------------------------------------------------------------------- BackorderOrderID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BACKORDER_ORDER_ID = "BackorderOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BACKORDER_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BACKORDER_ORDER_ID = "backorderOrderId";

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
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_DELIVERY_DATE = "ExpectedDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE = "expectedDeliveryDate";

    // ----------------------------------------------------------------- CustomerPurchaseOrderNumber

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER = "CustomerPurchaseOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER = "customerPurchaseOrderNumber";

    // -------------------------------------------------------------------- IsUndersupplyBackordered

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_UNDERSUPPLY_BACKORDERED = "IsUndersupplyBackordered";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_UNDERSUPPLY_BACKORDERED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED = "isUndersupplyBackordered";

    // ------------------------------------------------------------------------------------ Comments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMENTS = "Comments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENTS = "comments";

    // ------------------------------------------------------------------------ DeliveryInstructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_INSTRUCTIONS = "DeliveryInstructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_INSTRUCTIONS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS = "deliveryInstructions";

    // ---------------------------------------------------------------------------- InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

    // ------------------------------------------------------------------------ PickingCompletedWhen

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICKING_COMPLETED_WHEN = "PickingCompletedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICKING_COMPLETED_WHEN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN = "pickingCompletedWhen";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------ LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrder() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "orderId=" + orderId
               + ",customerId=" + customerId
               + ",salespersonPersonId=" + salespersonPersonId
               + ",pickedByPersonId=" + pickedByPersonId
               + ",contactPersonId=" + contactPersonId
               + ",backorderOrderId=" + backorderOrderId
               + ",orderDate=" + orderDate
               + ",expectedDeliveryDate=" + expectedDeliveryDate
               + ",customerPurchaseOrderNumber=" + customerPurchaseOrderNumber
               + ",isUndersupplyBackordered=" + isUndersupplyBackordered
               + ",comments=" + comments
               + ",deliveryInstructions=" + deliveryInstructions
               + ",internalComments=" + internalComments
               + ",pickingCompletedWhen=" + pickingCompletedWhen
               + ",lastEditedBy=" + lastEditedBy
               + ",lastEditedWhen=" + lastEditedWhen
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedOrder that)) {
            return false;
        }
        return getOrderId() != null
               && getOrderId().equals(that.getOrderId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedOrder.class.hashCode();
    }

    // ------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    // ---------------------------------------------------------------------------------- customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected Integer getCustomerId() {
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

    // ------------------------------------------------------------------------- salespersonPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     */
    protected Integer getSalespersonPersonId() {
        return salespersonPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salespersonPersonId new value for {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     */
    protected void setSalespersonPersonId(final Integer salespersonPersonId) {
        this.salespersonPersonId = salespersonPersonId;
    }

    // ---------------------------------------------------------------------------- pickedByPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute.
     */
    protected Integer getPickedByPersonId() {
        return pickedByPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param pickedByPersonId new value for {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute.
     */
    protected void setPickedByPersonId(final Integer pickedByPersonId) {
        this.pickedByPersonId = pickedByPersonId;
    }

    // ----------------------------------------------------------------------------- contactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     */
    protected Integer getContactPersonId() {
        return contactPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param contactPersonId new value for {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     */
    protected void setContactPersonId(final Integer contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    // ---------------------------------------------------------------------------- backorderOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute.
     */
    protected Integer getBackorderOrderId() {
        return backorderOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param backorderOrderId new value for {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute.
     */
    protected void setBackorderOrderId(final Integer backorderOrderId) {
        this.backorderOrderId = backorderOrderId;
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
    public void setOrderDate(final LocalDate orderDate) {
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
    public void setExpectedDeliveryDate(final LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    // ----------------------------------------------------------------- customerPurchaseOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     */
    public String getCustomerPurchaseOrderNumber() {
        return customerPurchaseOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute with the specified
     * value.
     *
     * @param customerPurchaseOrderNumber new value for {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER}
     *                                    attribute.
     */
    public void setCustomerPurchaseOrderNumber(final String customerPurchaseOrderNumber) {
        this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
    }

    // -------------------------------------------------------------------- isUndersupplyBackordered

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute.
     */
    public Boolean getIsUndersupplyBackordered() {
        return isUndersupplyBackordered;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute with the specified
     * value.
     *
     * @param isUndersupplyBackordered new value for {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute.
     */
    public void setIsUndersupplyBackordered(final Boolean isUndersupplyBackordered) {
        this.isUndersupplyBackordered = isUndersupplyBackordered;
    }

    // ------------------------------------------------------------------------------------ comments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute with the specified value.
     *
     * @param comments new value for {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public void setComments(final String comments) {
        this.comments = comments;
    }

    // ------------------------------------------------------------------------ deliveryInstructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     */
    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute with the specified value.
     *
     * @param deliveryInstructions new value for {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     */
    public void setDeliveryInstructions(final String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    // ---------------------------------------------------------------------------- internalComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    public String getInternalComments() {
        return internalComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute with the specified value.
     *
     * @param internalComments new value for {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    public void setInternalComments(final String internalComments) {
        this.internalComments = internalComments;
    }

    // ------------------------------------------------------------------------ pickingCompletedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     */
    public LocalDateTime getPickingCompletedWhen() {
        return pickingCompletedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute with the specified value.
     *
     * @param pickingCompletedWhen new value for {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     */
    public void setPickingCompletedWhen(final LocalDateTime pickingCompletedWhen) {
        this.pickingCompletedWhen = pickingCompletedWhen;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------ lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.OrderID", schema = "Sequences", sequenceName = "OrderID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.OrderID")
    @Column(name = COLUMN_NAME_ORDER_ID, nullable = false, insertable = true, updatable = false,
            comment = "Numeric ID used for reference to an order within the database")
    @MsDefault(name = "DF_Sales_Orders_OrderID", value = "(NEXT VALUE FOR [Sequences].[OrderID])")
    private Integer orderId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, insertable = false, updatable = false,
            comment = "Customer for this order")
    private Integer customerId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALESPERSON_PERSON_ID, nullable = false, insertable = false, updatable = false,
            comment = "Salesperson for this order")
    private Integer salespersonPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PICKED_BY_PERSON_ID, nullable = true, insertable = false, updatable = false,
            comment = "Person who picked this shipment")
    private Integer pickedByPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CONTACT_PERSON_ID, nullable = false, insertable = false, updatable = false,
            comment = "Customer contact for this order")
    private Integer contactPersonId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BACKORDER_ORDER_ID, nullable = true, insertable = false, updatable = false,
            comment = "If this order is a backorder, this column holds the original order number")
    private Integer backorderOrderId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE, nullable = false, insertable = true, updatable = true,
            comment = "Date that this order was raised")
    private LocalDate orderDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE, nullable = false, insertable = true, updatable = true,
            comment = "Expected delivery date")
    private LocalDate expectedDeliveryDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER,
            comment = "Purchase Order Number received from customer")
    private String customerPurchaseOrderNumber;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_UNDERSUPPLY_BACKORDERED, nullable = false, insertable = true, updatable = true,
            comment = "If items cannot be supplied are they backordered?")
    private Boolean isUndersupplyBackordered;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMENTS, nullable = true, insertable = true, updatable = true,
            comment = "Any comments related to this order (sent to customer)")
    private String comments;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DELIVERY_INSTRUCTIONS, nullable = true, insertable = true, updatable = true,
            comment = "Any comments related to order delivery (sent to customer)")
    private String deliveryInstructions;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS, nullable = true, insertable = true, updatable = true,
            comment = "Any internal comments related to this order (not sent to the customer)")
    private String internalComments;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PICKING_COMPLETED_WHEN, nullable = true, insertable = true, updatable = true,
            comment = "When was picking of the entire order completed?")
    private LocalDateTime pickingCompletedWhen;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (sysdatetime()) fills it on an INSERT; the date and time the record
    // was last updated, which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = false, updatable = true)
    @MsDefault(name = "DF_Sales_Orders_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

