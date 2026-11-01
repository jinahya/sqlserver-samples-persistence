package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.TransactionHistory} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedTransactionHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "TransactionHistory";

    // --------------------------------------------------------------------------------------------------- TransactionID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_ID = "TransactionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_ID = "transactionId";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ------------------------------------------------------------------------------------------------ ReferenceOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_ORDER_ID = "ReferenceOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_ORDER_ID = "referenceOrderId";

    // -------------------------------------------------------------------------------------------- ReferenceOrderLineID

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFERENCE_ORDER_LINE_ID = "ReferenceOrderLineID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFERENCE_ORDER_LINE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID = "referenceOrderLineId";

    // ------------------------------------------------------------------------------------------------- TransactionDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_DATE = "TransactionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_DATE = "transactionDate";

    // ------------------------------------------------------------------------------------------------- TransactionType

    /**
     * The name of the {@code nchar(1), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_TYPE = "TransactionType";

    /**
     * The length of the {@value #COLUMN_NAME_TRANSACTION_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TRANSACTION_TYPE = 1;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE = "transactionType";

    // -------------------------------------------------------------------------------------------------------- Quantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ------------------------------------------------------------------------------------------------------ ActualCost

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ACTUAL_COST = "ActualCost";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ACTUAL_COST} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ACTUAL_COST = "actualCost";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedTransactionHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
        if (!(obj instanceof MappedTransactionHistory that)) {
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

    // --------------------------------------------------------------------------------------------------- transactionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    @Nullable
    public Integer getTransactionId() {
        return transactionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute with the specified value.
     *
     * @param transactionId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_ID} attribute.
     */
    protected void setTransactionId(@Nullable final Integer transactionId) {
        this.transactionId = transactionId;
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    @Nonnull
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(@Nonnull final Integer productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------ referenceOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     */
    @Nonnull
    public Integer getReferenceOrderId() {
        return referenceOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute with the specified value.
     *
     * @param referenceOrderId new value for {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_ID} attribute.
     */
    public void setReferenceOrderId(@Nonnull final Integer referenceOrderId) {
        this.referenceOrderId = referenceOrderId;
    }

    // -------------------------------------------------------------------------------------------- referenceOrderLineId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute.
     */
    @Nonnull
    public Integer getReferenceOrderLineId() {
        return referenceOrderLineId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute with the specified value.
     *
     * @param referenceOrderLineId new value for {@value #ATTRIBUTE_NAME_REFERENCE_ORDER_LINE_ID} attribute.
     */
    public void setReferenceOrderLineId(@Nonnull final Integer referenceOrderLineId) {
        this.referenceOrderLineId = referenceOrderLineId;
    }

    // ------------------------------------------------------------------------------------------------- transactionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute with the specified value.
     *
     * @param transactionDate new value for {@value #ATTRIBUTE_NAME_TRANSACTION_DATE} attribute.
     */
    public void setTransactionDate(@Nonnull final LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    // ------------------------------------------------------------------------------------------------- transactionType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     */
    @Nonnull
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute with the specified value.
     *
     * @param transactionType new value for {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE} attribute.
     */
    public void setTransactionType(@Nonnull final String transactionType) {
        this.transactionType = transactionType;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    @Nonnull
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(@Nonnull final Integer quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------------------------ actualCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    @Nonnull
    public BigDecimal getActualCost() {
        return actualCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute with the specified value.
     *
     * @param actualCost new value for {@value #ATTRIBUTE_NAME_ACTUAL_COST} attribute.
     */
    public void setActualCost(@Nonnull final BigDecimal actualCost) {
        this.actualCost = actualCost;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_TRANSACTION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer transactionId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_REFERENCE_ORDER_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer referenceOrderId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_REFERENCE_ORDER_LINE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer referenceOrderLineId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_TRANSACTION_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime transactionDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_TRANSACTION_TYPE)
    @NotNull
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TRANSACTION_TYPE
    )
    private String transactionType;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_ACTUAL_COST,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal actualCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

