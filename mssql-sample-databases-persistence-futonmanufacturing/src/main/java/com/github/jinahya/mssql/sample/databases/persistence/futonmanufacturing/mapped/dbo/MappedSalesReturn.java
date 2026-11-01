package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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
 * A mapped superclass which holds the mappings of the {@code dbo.SalesReturn} table.
 * <p>
 * The {@value #COLUMN_NAME_SALES_ORDER_ID}, {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_RETURN_REASON_ID}
 * columns are mapped read-only, as a foreign key; the extending entity owns the writable mapping, through an
 * association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesReturn {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "dbo";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesReturn";

    // -------------------------------------------------------------------------------------------------------- ReturnID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_ID = "ReturnID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_ID = "returnId";

    // ---------------------------------------------------------------------------------------------------- ReturnNumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_NUMBER = "ReturnNumber";

    /**
     * The length of the {@value #COLUMN_NAME_RETURN_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_RETURN_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_NUMBER = "returnNumber";

    // ---------------------------------------------------------------------------------------------------- SalesOrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_ORDER_ID = "SalesOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_ORDER_ID = "salesOrderId";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // ------------------------------------------------------------------------------------------------------ ReturnDate

    /**
     * The name of the {@code date, DEFAULT (CONVERT([date],getdate())), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_DATE = "ReturnDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_DATE = "returnDate";

    // -------------------------------------------------------------------------------------------------- ReturnReasonID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_REASON_ID = "ReturnReasonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_REASON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_REASON_ID = "returnReasonId";

    // ---------------------------------------------------------------------------------------------------------- Status

    /**
     * The name of the {@code nvarchar(20), DEFAULT ('Pending'), NOT NULL} column. The value is {@value}.
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

    // ---------------------------------------------------------------------------------------------------- RefundAmount

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REFUND_AMOUNT = "RefundAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REFUND_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REFUND_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REFUND_AMOUNT = "refundAmount";

    // --------------------------------------------------------------------------------------------------- RestockingFee

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RESTOCKING_FEE = "RestockingFee";

    /**
     * The precision of the {@value #COLUMN_NAME_RESTOCKING_FEE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RESTOCKING_FEE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_RESTOCKING_FEE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RESTOCKING_FEE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RESTOCKING_FEE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RESTOCKING_FEE = "restockingFee";

    // ----------------------------------------------------------------------------------------------------------- Notes

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NOTES = "Notes";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NOTES} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NOTES = "notes";

    // ------------------------------------------------------------------------------------------------------ ApprovedBy

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_APPROVED_BY = "ApprovedBy";

    /**
     * The length of the {@value #COLUMN_NAME_APPROVED_BY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_APPROVED_BY = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_APPROVED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_APPROVED_BY = "approvedBy";

    // ---------------------------------------------------------------------------------------------------- ApprovedDate

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_APPROVED_DATE = "ApprovedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_APPROVED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_APPROVED_DATE = "approvedDate";

    // ----------------------------------------------------------------------------------------------------- CreatedDate

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREATED_DATE = "CreatedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREATED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREATED_DATE = "createdDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesReturn() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "returnId=" + returnId
               + ",returnNumber=" + returnNumber
               + ",salesOrderId=" + salesOrderId
               + ",customerId=" + customerId
               + ",returnDate=" + returnDate
               + ",returnReasonId=" + returnReasonId
               + ",status=" + status
               + ",refundAmount=" + refundAmount
               + ",restockingFee=" + restockingFee
               + ",notes=" + notes
               + ",approvedBy=" + approvedBy
               + ",approvedDate=" + approvedDate
               + ",createdDate=" + createdDate
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
        if (!(obj instanceof MappedSalesReturn that)) {
            return false;
        }
        return Objects.equals(returnId, that.returnId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(returnId);
    }

    // -------------------------------------------------------------------------------------------------------- returnId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_ID} attribute.
     */
    public Integer getReturnId() {
        return returnId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_ID} attribute with the specified value.
     *
     * @param returnId new value for {@value #ATTRIBUTE_NAME_RETURN_ID} attribute.
     */
    protected void setReturnId(final Integer returnId) {
        this.returnId = returnId;
    }

    // ---------------------------------------------------------------------------------------------------- returnNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_NUMBER} attribute.
     */
    public String getReturnNumber() {
        return returnNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_NUMBER} attribute with the specified value.
     *
     * @param returnNumber new value for {@value #ATTRIBUTE_NAME_RETURN_NUMBER} attribute.
     */
    public void setReturnNumber(final String returnNumber) {
        this.returnNumber = returnNumber;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param salesOrderId new value for {@value #ATTRIBUTE_NAME_SALES_ORDER_ID} attribute.
     */
    protected void setSalesOrderId(final Integer salesOrderId) {
        this.salesOrderId = salesOrderId;
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

    // ------------------------------------------------------------------------------------------------------ returnDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_DATE} attribute.
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_DATE} attribute with the specified value.
     *
     * @param returnDate new value for {@value #ATTRIBUTE_NAME_RETURN_DATE} attribute.
     */
    public void setReturnDate(final LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // -------------------------------------------------------------------------------------------------- returnReasonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute.
     */
    public Integer getReturnReasonId() {
        return returnReasonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param returnReasonId new value for {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute.
     */
    protected void setReturnReasonId(final Integer returnReasonId) {
        this.returnReasonId = returnReasonId;
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

    // ---------------------------------------------------------------------------------------------------- refundAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute.
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute with the specified value.
     *
     * @param refundAmount new value for {@value #ATTRIBUTE_NAME_REFUND_AMOUNT} attribute.
     */
    public void setRefundAmount(final BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    // --------------------------------------------------------------------------------------------------- restockingFee

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RESTOCKING_FEE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RESTOCKING_FEE} attribute.
     */
    public BigDecimal getRestockingFee() {
        return restockingFee;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RESTOCKING_FEE} attribute with the specified value.
     *
     * @param restockingFee new value for {@value #ATTRIBUTE_NAME_RESTOCKING_FEE} attribute.
     */
    public void setRestockingFee(final BigDecimal restockingFee) {
        this.restockingFee = restockingFee;
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

    // ------------------------------------------------------------------------------------------------------ approvedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_APPROVED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_APPROVED_BY} attribute.
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_APPROVED_BY} attribute with the specified value.
     *
     * @param approvedBy new value for {@value #ATTRIBUTE_NAME_APPROVED_BY} attribute.
     */
    public void setApprovedBy(final String approvedBy) {
        this.approvedBy = approvedBy;
    }

    // ---------------------------------------------------------------------------------------------------- approvedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_APPROVED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_APPROVED_DATE} attribute.
     */
    public LocalDateTime getApprovedDate() {
        return approvedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_APPROVED_DATE} attribute with the specified value.
     *
     * @param approvedDate new value for {@value #ATTRIBUTE_NAME_APPROVED_DATE} attribute.
     */
    public void setApprovedDate(final LocalDateTime approvedDate) {
        this.approvedDate = approvedDate;
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

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_RETURN_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer returnId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_RETURN_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RETURN_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_RETURN_NUMBER
    )
    private String returnNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_ORDER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer salesOrderId;

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
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RETURN_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate returnDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RETURN_REASON_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer returnReasonId;

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
    @Digits(integer = COLUMN_PRECISION_REFUND_AMOUNT - COLUMN_SCALE_REFUND_AMOUNT,
            fraction = COLUMN_SCALE_REFUND_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REFUND_AMOUNT,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_REFUND_AMOUNT,
            scale = COLUMN_SCALE_REFUND_AMOUNT
    )
    private BigDecimal refundAmount;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_RESTOCKING_FEE - COLUMN_SCALE_RESTOCKING_FEE,
            fraction = COLUMN_SCALE_RESTOCKING_FEE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RESTOCKING_FEE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_RESTOCKING_FEE,
            scale = COLUMN_SCALE_RESTOCKING_FEE
    )
    private BigDecimal restockingFee;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_NOTES,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String notes;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_APPROVED_BY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_APPROVED_BY,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_APPROVED_BY
    )
    private String approvedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_APPROVED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime approvedDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CREATED_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime createdDate;
}

