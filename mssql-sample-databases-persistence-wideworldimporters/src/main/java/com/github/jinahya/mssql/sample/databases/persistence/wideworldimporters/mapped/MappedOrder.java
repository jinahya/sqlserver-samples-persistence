package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.Orders} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_SALESPERSON_PERSON_ID}, {@value
 * #COLUMN_NAME_PICKED_BY_PERSON_ID}, {@value #COLUMN_NAME_CONTACT_PERSON_ID}, {@value
 * #COLUMN_NAME_BACKORDER_ORDER_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a foreign
 * key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Orders";

    // --------------------------------------------------------------------------------------------------------- OrderID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.OrderID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "OrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // --------------------------------------------------------------------------------------------- SalespersonPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALESPERSON_PERSON_ID = "SalespersonPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALESPERSON_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALESPERSON_PERSON_ID = "salespersonPersonId";

    // ------------------------------------------------------------------------------------------------ PickedByPersonID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICKED_BY_PERSON_ID = "PickedByPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICKED_BY_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKED_BY_PERSON_ID = "pickedByPersonId";

    // ------------------------------------------------------------------------------------------------- ContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTACT_PERSON_ID = "ContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTACT_PERSON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON_ID = "contactPersonId";

    // ------------------------------------------------------------------------------------------------ BackorderOrderID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BACKORDER_ORDER_ID = "BackorderOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BACKORDER_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BACKORDER_ORDER_ID = "backorderOrderId";

    // ------------------------------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // -------------------------------------------------------------------------------------------- ExpectedDeliveryDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_DELIVERY_DATE = "ExpectedDeliveryDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_DELIVERY_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE = "expectedDeliveryDate";

    // ------------------------------------------------------------------------------------- CustomerPurchaseOrderNumber

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
     * is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER = "customerPurchaseOrderNumber";

    // ---------------------------------------------------------------------------------------- IsUndersupplyBackordered

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_UNDERSUPPLY_BACKORDERED = "IsUndersupplyBackordered";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_UNDERSUPPLY_BACKORDERED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED = "isUndersupplyBackordered";

    // -------------------------------------------------------------------------------------------------------- Comments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMENTS = "Comments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENTS = "comments";

    // -------------------------------------------------------------------------------------------- DeliveryInstructions

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_INSTRUCTIONS = "DeliveryInstructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_INSTRUCTIONS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS = "deliveryInstructions";

    // ------------------------------------------------------------------------------------------------ InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

    // -------------------------------------------------------------------------------------------- PickingCompletedWhen

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICKING_COMPLETED_WHEN = "PickingCompletedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICKING_COMPLETED_WHEN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN = "pickingCompletedWhen";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // -------------------------------------------------------------------------------------------------- LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrder() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedOrder that)) {
            return false;
        }
        return Objects.equals(orderId, that.orderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(orderId);
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    @Nullable
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(@Nullable final Integer orderId) {
        this.orderId = orderId;
    }

    // ------------------------------------------------------------------------------------------------------ customerId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    @Nonnull
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param customerId new value for {@value #ATTRIBUTE_NAME_CUSTOMER_ID} attribute.
     */
    protected void setCustomerId(@Nonnull final Integer customerId) {
        this.customerId = customerId;
    }

    // --------------------------------------------------------------------------------------------- salespersonPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     */
    @Nonnull
    public Integer getSalespersonPersonId() {
        return salespersonPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salespersonPersonId new value for {@value #ATTRIBUTE_NAME_SALESPERSON_PERSON_ID} attribute.
     */
    protected void setSalespersonPersonId(@Nonnull final Integer salespersonPersonId) {
        this.salespersonPersonId = salespersonPersonId;
    }

    // ------------------------------------------------------------------------------------------------ pickedByPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute.
     */
    @Nullable
    public Integer getPickedByPersonId() {
        return pickedByPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param pickedByPersonId new value for {@value #ATTRIBUTE_NAME_PICKED_BY_PERSON_ID} attribute.
     */
    protected void setPickedByPersonId(@Nullable final Integer pickedByPersonId) {
        this.pickedByPersonId = pickedByPersonId;
    }

    // ------------------------------------------------------------------------------------------------- contactPersonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     */
    @Nonnull
    public Integer getContactPersonId() {
        return contactPersonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param contactPersonId new value for {@value #ATTRIBUTE_NAME_CONTACT_PERSON_ID} attribute.
     */
    protected void setContactPersonId(@Nonnull final Integer contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    // ------------------------------------------------------------------------------------------------ backorderOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute.
     */
    @Nullable
    public Integer getBackorderOrderId() {
        return backorderOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param backorderOrderId new value for {@value #ATTRIBUTE_NAME_BACKORDER_ORDER_ID} attribute.
     */
    protected void setBackorderOrderId(@Nullable final Integer backorderOrderId) {
        this.backorderOrderId = backorderOrderId;
    }

    // ------------------------------------------------------------------------------------------------------- orderDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    @Nonnull
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute with the specified value.
     *
     * @param orderDate new value for {@value #ATTRIBUTE_NAME_ORDER_DATE} attribute.
     */
    public void setOrderDate(@Nonnull final LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    // -------------------------------------------------------------------------------------------- expectedDeliveryDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    @Nonnull
    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute with the specified value.
     *
     * @param expectedDeliveryDate new value for {@value #ATTRIBUTE_NAME_EXPECTED_DELIVERY_DATE} attribute.
     */
    public void setExpectedDeliveryDate(@Nonnull final LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    // ------------------------------------------------------------------------------------- customerPurchaseOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     */
    @Nullable
    public String getCustomerPurchaseOrderNumber() {
        return customerPurchaseOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute with the specified
     * value.
     *
     * @param customerPurchaseOrderNumber new value for {@value #ATTRIBUTE_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER} attribute.
     */
    public void setCustomerPurchaseOrderNumber(@Nullable final String customerPurchaseOrderNumber) {
        this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
    }

    // ---------------------------------------------------------------------------------------- isUndersupplyBackordered

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute.
     */
    @Nonnull
    public Boolean getIsUndersupplyBackordered() {
        return isUndersupplyBackordered;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute with the specified value.
     *
     * @param isUndersupplyBackordered new value for {@value #ATTRIBUTE_NAME_IS_UNDERSUPPLY_BACKORDERED} attribute.
     */
    public void setIsUndersupplyBackordered(@Nonnull final Boolean isUndersupplyBackordered) {
        this.isUndersupplyBackordered = isUndersupplyBackordered;
    }

    // -------------------------------------------------------------------------------------------------------- comments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    @Nullable
    public String getComments() {
        return comments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMENTS} attribute with the specified value.
     *
     * @param comments new value for {@value #ATTRIBUTE_NAME_COMMENTS} attribute.
     */
    public void setComments(@Nullable final String comments) {
        this.comments = comments;
    }

    // -------------------------------------------------------------------------------------------- deliveryInstructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     */
    @Nullable
    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute with the specified value.
     *
     * @param deliveryInstructions new value for {@value #ATTRIBUTE_NAME_DELIVERY_INSTRUCTIONS} attribute.
     */
    public void setDeliveryInstructions(@Nullable final String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    // ------------------------------------------------------------------------------------------------ internalComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    @Nullable
    public String getInternalComments() {
        return internalComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute with the specified value.
     *
     * @param internalComments new value for {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    public void setInternalComments(@Nullable final String internalComments) {
        this.internalComments = internalComments;
    }

    // -------------------------------------------------------------------------------------------- pickingCompletedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     */
    @Nullable
    public LocalDateTime getPickingCompletedWhen() {
        return pickingCompletedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute with the specified value.
     *
     * @param pickingCompletedWhen new value for {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     */
    public void setPickingCompletedWhen(@Nullable final LocalDateTime pickingCompletedWhen) {
        this.pickingCompletedWhen = pickingCompletedWhen;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    @Nonnull
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(@Nonnull final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // -------------------------------------------------------------------------------------------------- lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    @Nonnull
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(@Nonnull final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.OrderID", schema = "Sequences", sequenceName = "OrderID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.OrderID")
    @Column(name = COLUMN_NAME_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer orderId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_CUSTOMER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer customerId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SALESPERSON_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer salespersonPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_PICKED_BY_PERSON_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer pickedByPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_CONTACT_PERSON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer contactPersonId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nullable
    @Column(name = COLUMN_NAME_BACKORDER_ORDER_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer backorderOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ORDER_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate orderDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate expectedDeliveryDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER)
    @Column(name = COLUMN_NAME_CUSTOMER_PURCHASE_ORDER_NUMBER,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CUSTOMER_PURCHASE_ORDER_NUMBER
    )
    private String customerPurchaseOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_IS_UNDERSUPPLY_BACKORDERED,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isUndersupplyBackordered;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String comments;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_DELIVERY_INSTRUCTIONS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String deliveryInstructions;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String internalComments;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_PICKING_COMPLETED_WHEN,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime pickingCompletedWhen;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime lastEditedWhen;
}

