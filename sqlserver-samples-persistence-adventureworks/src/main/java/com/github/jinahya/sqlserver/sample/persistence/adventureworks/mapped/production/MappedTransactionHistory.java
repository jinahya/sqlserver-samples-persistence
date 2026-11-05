package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Production.TransactionHistory} table.
 * <p>
 * Read-only: the triggers iPurchaseOrderDetail, uPurchaseOrderDetail, Sales.iduSalesOrderDetail,
 * iWorkOrder and uWorkOrder insert every row of this table, a record of each purchase, sales and
 * work order transaction, and nothing in the database updates one; so no column of it is insertable
 * or updatable. The {@value #COLUMN_NAME_PRODUCT_ID} column, a foreign key, is read-only here and
 * in the association of the extending entity alike.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.TransactionHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code TransactionID}</th>
 *     <td>Primary key for TransactionHistory records.</td></tr>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code ReferenceOrderID}</th>
 *     <td>Purchase order, sales order, or work order identification number.</td></tr>
 * <tr><th scope="row">{@code ReferenceOrderLineID}</th>
 *     <td>Line number associated with the purchase order, sales order, or work order.</td></tr>
 * <tr><th scope="row">{@code TransactionDate}</th><td>Date and time of the transaction.</td></tr>
 * <tr><th scope="row">{@code TransactionType}</th>
 *     <td>W = WorkOrder, S = SalesOrder, P = PurchaseOrder</td></tr>
 * <tr><th scope="row">{@code Quantity}</th><td>Product quantity.</td></tr>
 * <tr><th scope="row">{@code ActualCost}</th><td>Product cost.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.TransactionHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ReferenceOrderLineID}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code TransactionDate}</th><td>{@code (getdate())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedTransactionHistory.TABLE_SCHEMA,
        table = MappedTransactionHistory.TABLE_NAME,
        value = "Record of each purchase order, sales order, or work order transaction year to "
                + "date."
)
public abstract class MappedTransactionHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "TransactionHistory";

    // ------------------------------------------------------------------------------- TransactionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_ID = "TransactionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_ID = "transactionId";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ---------------------------------------------------------------------------- ReferenceOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_ORDER_ID = "ReferenceOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_ORDER_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_ORDER_ID = "referenceOrderId";

    // ------------------------------------------------------------------------ ReferenceOrderLineID

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_ORDER_LINE_ID = "ReferenceOrderLineID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_ORDER_LINE_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID = "referenceOrderLineId";

    // ----------------------------------------------------------------------------- TransactionDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_DATE = "TransactionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_DATE = "transactionDate";

    // ----------------------------------------------------------------------------- TransactionType

    /**
     * The name of the {@code nchar(1), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_TYPE = "TransactionType";

    /**
     * The length of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TRANSACTION_TYPE = 1;

    /**
     * A value of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column, for a work order transaction.
     * The value is {@value}.
     */
    public static final String COLUMN_VALUE_TRANSACTION_TYPE_WORK_ORDER = "W";

    /**
     * A value of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column, for a sales order transaction.
     * The value is {@value}.
     */
    public static final String COLUMN_VALUE_TRANSACTION_TYPE_SALES_ORDER = "S";

    /**
     * A value of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column, for a purchase order
     * transaction. The value is {@value}.
     */
    public static final String COLUMN_VALUE_TRANSACTION_TYPE_PURCHASE_ORDER = "P";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    /**
     * An enum for the {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute, of the values the
     * {@code CK_TransactionHistory_TransactionType} constraint allows.
     * {@snippet lang = "sql":
     * (upper([TransactionType])='P' OR upper([TransactionType])='S' OR upper([TransactionType])
     * ='W')
     *} The constants are named after the column values, which are stored in upper case.
     */
    public enum TransactionType {

        /**
         * A constant for the
         * {@value MappedTransactionHistory#COLUMN_VALUE_TRANSACTION_TYPE_WORK_ORDER} value, for a
         * work order transaction.
         */
        W,

        /**
         * A constant for the
         * {@value MappedTransactionHistory#COLUMN_VALUE_TRANSACTION_TYPE_SALES_ORDER} value, for a
         * sales order transaction.
         */
        S,

        /**
         * A constant for the
         * {@value MappedTransactionHistory#COLUMN_VALUE_TRANSACTION_TYPE_PURCHASE_ORDER} value, for
         * a purchase order transaction.
         */
        P
    }

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ---------------------------------------------------------------------------------- ActualCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COST = "ActualCost";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COST} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COST = "actualCost";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedTransactionHistory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "transactionId=" + transactionId
               + ",productId=" + productId
               + ",referenceOrderId=" + referenceOrderId
               + ",referenceOrderLineId=" + referenceOrderLineId
               + ",transactionDate=" + transactionDate
               + ",transactionType=" + transactionType
               + ",quantity=" + quantity
               + ",actualCost=" + actualCost
               + ",modifiedDate=" + modifiedDate
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
        if (!(obj instanceof MappedTransactionHistory that)) {
            return false;
        }
        return getTransactionId() != null
               && getTransactionId().equals(that.getTransactionId());
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
        return MappedTransactionHistory.class.hashCode();
    }

    // ------------------------------------------------------------------------------- transactionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute with the
     * specified value.
     *
     * @param transactionId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    protected void setTransactionId(final Integer transactionId) {
        this.transactionId = transactionId;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value. The attribute is read-only; the extending entity calls this to keep it in step with
     * the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ---------------------------------------------------------------------------- referenceOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     */
    public Integer getReferenceOrderId() {
        return referenceOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute with the
     * specified value.
     *
     * @param referenceOrderId new value for {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     */
    protected void setReferenceOrderId(final Integer referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    // ------------------------------------------------------------------------ referenceOrderLineId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute.
     */
    public Integer getReferenceOrderLineId() {
        return referenceOrderLineId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute with the
     * specified value.
     *
     * @param referenceOrderLineId new value for {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID}
     *                             attribute.
     */
    protected void setReferenceOrderLineId(final Integer referenceOrderLineId) {
        this.referenceOrderLineId = referenceOrderLineId;
    }

    // ----------------------------------------------------------------------------- transactionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute with the
     * specified value.
     *
     * @param transactionDate new value for {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    protected void setTransactionDate(final LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    // ----------------------------------------------------------------------------- transactionType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute with the
     * specified value.
     *
     * @param transactionType new value for {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     */
    protected void setTransactionType(final TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    // ------------------------------------------------------------------------------------ quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified
     * value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    protected void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    // ---------------------------------------------------------------------------------- actualCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    public BigDecimal getActualCost() {
        return actualCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute with the specified
     * value.
     *
     * @param actualCost new value for {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    protected void setActualCost(final BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    protected void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_TRANSACTION_ID, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_ID,
                          value = "Primary key for TransactionHistory records.")
    private Integer transactionId;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_ID,
                          value = "Product identification number. Foreign key to "
                                  + "Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REFERENCE_ORDER_ID, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_REFERENCE_ORDER_ID,
                          value = "Purchase order, sales order, or work order identification "
                                  + "number.")
    private Integer referenceOrderId;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REFERENCE_ORDER_LINE_ID, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_REFERENCE_ORDER_LINE_ID,
                          value = "Line number associated with the purchase order, sales order, "
                                  + "or work order.")
    @MsDefault(name = "DF_TransactionHistory_ReferenceOrderLineID", value = "((0))")
    private Integer referenceOrderLineId;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_DATE, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_DATE,
                          value = "Date and time of the transaction.")
    @MsDefault(name = "DF_TransactionHistory_TransactionDate", value = "(getdate())")
    private LocalDateTime transactionDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE, nullable = false, insertable = false,
            updatable = false, length = COLUMN_LENGTH_TRANSACTION_TYPE)
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_TYPE,
                          value = "W = WorkOrder, S = SalesOrder, P = PurchaseOrder")
    private TransactionType transactionType;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_QUANTITY, value = "Product quantity.")
    private Integer quantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @DecimalMax(SqlServerConstants.MONEY_MAX)
    @DecimalMin(SqlServerConstants.MONEY_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACTUAL_COST, nullable = false, insertable = false, updatable = false, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE)
    @MsDescription.Column(name = COLUMN_NAME_ACTUAL_COST, value = "Product cost.")
    private BigDecimal actualCost;

    // ---------------------------------------------------------------------------------------------
    // read-only: only the order and work-order triggers insert a row of this table, and nothing
    // updates one; see the
    // class documentation
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_TransactionHistory_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

