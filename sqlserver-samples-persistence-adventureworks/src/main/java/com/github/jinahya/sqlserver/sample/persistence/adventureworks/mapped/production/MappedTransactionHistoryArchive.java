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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.TransactionHistoryArchive} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.TransactionHistoryArchive}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code TransactionID}</th>
 *     <td>Primary key for TransactionHistoryArchive records.</td></tr>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code ReferenceOrderID}</th>
 *     <td>Purchase order, sales order, or work order identification number.</td></tr>
 * <tr><th scope="row">{@code ReferenceOrderLineID}</th>
 *     <td>Line number associated with the purchase order, sales order, or work order.</td></tr>
 * <tr><th scope="row">{@code TransactionDate}</th><td>Date and time of the transaction.</td></tr>
 * <tr><th scope="row">{@code TransactionType}</th>
 *     <td>W = Work Order, S = Sales Order, P = Purchase Order</td></tr>
 * <tr><th scope="row">{@code Quantity}</th><td>Product quantity.</td></tr>
 * <tr><th scope="row">{@code ActualCost}</th><td>Product cost.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.TransactionHistoryArchive}</caption>
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
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedTransactionHistoryArchive.TABLE_SCHEMA,
        table = MappedTransactionHistoryArchive.TABLE_NAME,
        value = "Transactions for previous years."
)
public abstract class MappedTransactionHistoryArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "TransactionHistoryArchive";

    // ------------------------------------------------------------------------------- TransactionID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_ID = "TransactionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_ID = "transactionId";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ---------------------------------------------------------------------------- ReferenceOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_ORDER_ID = "ReferenceOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_ORDER_ID = "referenceOrderId";

    // ------------------------------------------------------------------------ ReferenceOrderLineID

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_ORDER_LINE_ID = "ReferenceOrderLineID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_ORDER_LINE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID = "referenceOrderLineId";

    // ----------------------------------------------------------------------------- TransactionDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_DATE = "TransactionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_DATE} column. The value is {@value}.
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
     * A value of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column, for a work order transaction. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_TRANSACTION_TYPE_WORK_ORDER =
            MappedTransactionHistory.COLUMN_VALUE_TRANSACTION_TYPE_WORK_ORDER;

    /**
     * A value of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column, for a sales order transaction. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_TRANSACTION_TYPE_SALES_ORDER =
            MappedTransactionHistory.COLUMN_VALUE_TRANSACTION_TYPE_SALES_ORDER;

    /**
     * A value of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column, for a purchase order transaction. The value is
     * {@value}.
     */
    public static final String COLUMN_VALUE_TRANSACTION_TYPE_PURCHASE_ORDER =
            MappedTransactionHistory.COLUMN_VALUE_TRANSACTION_TYPE_PURCHASE_ORDER;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ---------------------------------------------------------------------------------- ActualCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COST = "ActualCost";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COST = "actualCost";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedTransactionHistoryArchive() {
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
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedTransactionHistoryArchive that)) {
            return false;
        }
        return Objects.equals(transactionId, that.transactionId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(transactionId);
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute with the specified value.
     *
     * @param transactionId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    public void setTransactionId(final Integer transactionId) {
        this.transactionId = transactionId;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public void setProductId(final Integer productId) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute with the specified value.
     *
     * @param referenceOrderId new value for {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     */
    public void setReferenceOrderId(final Integer referenceOrderId) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute with the specified value.
     *
     * @param referenceOrderLineId new value for {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute.
     */
    public void setReferenceOrderLineId(final Integer referenceOrderLineId) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute with the specified value.
     *
     * @param transactionDate new value for {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public void setTransactionDate(final LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    // ----------------------------------------------------------------------------- transactionType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     */
    public MappedTransactionHistory.TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute with the specified value.
     *
     * @param transactionType new value for {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     */
    public void setTransactionType(final MappedTransactionHistory.TransactionType transactionType) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final Integer quantity) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute with the specified value.
     *
     * @param actualCost new value for {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    public void setActualCost(final BigDecimal actualCost) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_TRANSACTION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_ID,
                          value = "Primary key for TransactionHistoryArchive records.")
    private Integer transactionId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_ID,
                          value = "Product identification number. Foreign key to "
                                  + "Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REFERENCE_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_REFERENCE_ORDER_ID,
                          value = "Purchase order, sales order, or work order identification "
                                  + "number.")
    private Integer referenceOrderId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REFERENCE_ORDER_LINE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_REFERENCE_ORDER_LINE_ID,
                          value = "Line number associated with the purchase order, sales order, "
                                  + "or work order.")
    @MsDefault(name = "DF_TransactionHistoryArchive_ReferenceOrderLineID", value = "((0))")
    private Integer referenceOrderLineId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_DATE,
                          value = "Date and time of the transaction.")
    @MsDefault(name = "DF_TransactionHistoryArchive_TransactionDate", value = "(getdate())")
    private LocalDateTime transactionDate;

    // ---------------------------------------------------------------------------------------------
    // CK_TransactionHistoryArchive_TransactionType:
    // (upper([TransactionType])='P' OR upper([TransactionType])='S' OR upper([TransactionType])='W'), the same values
    // as MappedTransactionHistory.TransactionType
    @NotNull
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TRANSACTION_TYPE
    )
    @MsDescription.Column(name = COLUMN_NAME_TRANSACTION_TYPE,
                          value = "W = Work Order, S = Sales Order, P = Purchase Order")
    private MappedTransactionHistory.TransactionType transactionType;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_QUANTITY, value = "Product quantity.")
    private Integer quantity;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACTUAL_COST,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_ACTUAL_COST, value = "Product cost.")
    private BigDecimal actualCost;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_TransactionHistoryArchive_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

