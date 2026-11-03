package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.purchasing;

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
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.PurchaseOrders} table.
 * <p>
 * The {@value #COLUMN_NAME_SUPPLIER_ID}, {@value #COLUMN_NAME_DELIVERY_METHOD_ID},
 * {@value #COLUMN_NAME_CONTACT_PERSON_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Purchasing.PurchaseOrders}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PurchaseOrderID}</th>
 *     <td>Numeric ID used for reference to a purchase order within the database</td></tr>
 * <tr><th scope="row">{@code SupplierID}</th><td>Supplier for this purchase order</td></tr>
 * <tr><th scope="row">{@code OrderDate}</th><td>Date that this purchase order was raised</td></tr>
 * <tr><th scope="row">{@code DeliveryMethodID}</th>
 *     <td>How this purchase order should be delivered</td></tr>
 * <tr><th scope="row">{@code ContactPersonID}</th>
 *     <td>The person who is the primary contact for this purchase order</td></tr>
 * <tr><th scope="row">{@code ExpectedDeliveryDate}</th>
 *     <td>Expected delivery date for this purchase order</td></tr>
 * <tr><th scope="row">{@code SupplierReference}</th>
 *     <td>Supplier reference for our organization (might be our account number at the
 *     supplier)</td></tr>
 * <tr><th scope="row">{@code IsOrderFinalized}</th>
 *     <td>Is this purchase order now considered finalized?</td></tr>
 * <tr><th scope="row">{@code Comments}</th>
 *     <td>Any comments related this purchase order (comments sent to the supplier)</td></tr>
 * <tr><th scope="row">{@code InternalComments}</th>
 *     <td>Any internal comments related this purchase order (comments for internal reference only
 *     and not sent to the supplier)</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Purchasing.PurchaseOrders}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PurchaseOrderID}</th><td>{@code (NEXT VALUE FOR [Sequences].[PurchaseOrderID])}</td></tr>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPurchaseOrder.TABLE_SCHEMA,
        table = MappedPurchaseOrder.TABLE_NAME,
        value = "Details of supplier purchase orders"
)
public abstract class MappedPurchaseOrder {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PurchaseOrders";

    // ----------------------------------------------------------------------------- PurchaseOrderID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.PurchaseOrderID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PURCHASE_ORDER_ID = "PurchaseOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PURCHASE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PURCHASE_ORDER_ID = "purchaseOrderId";

    // ---------------------------------------------------------------------------------- SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ----------------------------------------------------------------------------------- OrderDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_DATE = "OrderDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_DATE = "orderDate";

    // ---------------------------------------------------------------------------- DeliveryMethodID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DELIVERY_METHOD_ID = "DeliveryMethodID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DELIVERY_METHOD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DELIVERY_METHOD_ID = "deliveryMethodId";

    // ----------------------------------------------------------------------------- ContactPersonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONTACT_PERSON_ID = "ContactPersonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONTACT_PERSON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONTACT_PERSON_ID = "contactPersonId";

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

    // --------------------------------------------------------------------------- SupplierReference

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_REFERENCE = "SupplierReference";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_REFERENCE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_REFERENCE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_REFERENCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_REFERENCE = "supplierReference";

    // ---------------------------------------------------------------------------- IsOrderFinalized

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ORDER_FINALIZED = "IsOrderFinalized";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ORDER_FINALIZED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ORDER_FINALIZED = "isOrderFinalized";

    // ------------------------------------------------------------------------------------ Comments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMENTS = "Comments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMENTS = "comments";

    // ---------------------------------------------------------------------------- InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

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
    protected MappedPurchaseOrder() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "purchaseOrderId=" + purchaseOrderId
               + ",supplierId=" + supplierId
               + ",orderDate=" + orderDate
               + ",deliveryMethodId=" + deliveryMethodId
               + ",contactPersonId=" + contactPersonId
               + ",expectedDeliveryDate=" + expectedDeliveryDate
               + ",supplierReference=" + supplierReference
               + ",isOrderFinalized=" + isOrderFinalized
               + ",comments=" + comments
               + ",internalComments=" + internalComments
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
        if (!(obj instanceof MappedPurchaseOrder that)) {
            return false;
        }
        return Objects.equals(purchaseOrderId, that.purchaseOrderId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(purchaseOrderId);
    }

    // ----------------------------------------------------------------------------- purchaseOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    public Integer getPurchaseOrderId() {
        return purchaseOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute with the specified value.
     *
     * @param purchaseOrderId new value for {@value #ATTRIBUTE_NAME_PURCHASE_ORDER_ID} attribute.
     */
    protected void setPurchaseOrderId(final Integer purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    // ---------------------------------------------------------------------------------- supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(final Integer supplierId) {
        this.supplierId = supplierId;
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

    // ---------------------------------------------------------------------------- deliveryMethodId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    protected Integer getDeliveryMethodId() {
        return deliveryMethodId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param deliveryMethodId new value for {@value #ATTRIBUTE_NAME_DELIVERY_METHOD_ID} attribute.
     */
    protected void setDeliveryMethodId(final Integer deliveryMethodId) {
        this.deliveryMethodId = deliveryMethodId;
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

    // --------------------------------------------------------------------------- supplierReference

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute.
     */
    public String getSupplierReference() {
        return supplierReference;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute with the specified value.
     *
     * @param supplierReference new value for {@value #ATTRIBUTE_NAME_SUPPLIER_REFERENCE} attribute.
     */
    public void setSupplierReference(final String supplierReference) {
        this.supplierReference = supplierReference;
    }

    // ---------------------------------------------------------------------------- isOrderFinalized

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ORDER_FINALIZED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ORDER_FINALIZED} attribute.
     */
    public Boolean getIsOrderFinalized() {
        return isOrderFinalized;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ORDER_FINALIZED} attribute with the specified value.
     *
     * @param isOrderFinalized new value for {@value #ATTRIBUTE_NAME_IS_ORDER_FINALIZED} attribute.
     */
    public void setIsOrderFinalized(final Boolean isOrderFinalized) {
        this.isOrderFinalized = isOrderFinalized;
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
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.PurchaseOrderID", schema = "Sequences",
                       sequenceName = "PurchaseOrderID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.PurchaseOrderID")
    @Column(name = COLUMN_NAME_PURCHASE_ORDER_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PURCHASE_ORDER_ID,
                          value = "Numeric ID used for reference to a purchase order within the "
                                  + "database")
    @MsDefault(name = "DF_Purchasing_PurchaseOrders_PurchaseOrderID",
               value = "(NEXT VALUE FOR [Sequences].[PurchaseOrderID])")
    private Integer purchaseOrderId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_ID,
                          value = "Supplier for this purchase order")
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_DATE, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_ORDER_DATE,
                          value = "Date that this purchase order was raised")
    private LocalDate orderDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DELIVERY_METHOD_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_DELIVERY_METHOD_ID,
                          value = "How this purchase order should be delivered")
    private Integer deliveryMethodId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CONTACT_PERSON_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_CONTACT_PERSON_ID,
                          value = "The person who is the primary contact for this purchase order")
    private Integer contactPersonId;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_EXPECTED_DELIVERY_DATE,
                          value = "Expected delivery date for this purchase order")
    private LocalDate expectedDeliveryDate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SUPPLIER_REFERENCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SUPPLIER_REFERENCE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_SUPPLIER_REFERENCE)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_REFERENCE,
                          value = "Supplier reference for our organization (might be our "
                                  + "account number at the supplier)")
    private String supplierReference;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ORDER_FINALIZED, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_IS_ORDER_FINALIZED,
                          value = "Is this purchase order now considered finalized?")
    private Boolean isOrderFinalized;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COMMENTS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_COMMENTS,
                          value = "Any comments related this purchase order (comments sent to "
                                  + "the supplier)")
    private String comments;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
                          value = "Any internal comments related this purchase order (comments "
                                  + "for internal reference only and not sent to the supplier)")
    private String internalComments;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = true, updatable = true)
    @MsDefault(name = "DF_Purchasing_PurchaseOrders_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

