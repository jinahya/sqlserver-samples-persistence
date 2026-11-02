package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo;

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
 * A mapped superclass which holds the mappings of the {@code dbo.SalesQuote} table.
 * <p>
 * The {@value #COLUMN_NAME_CUSTOMER_ID}, {@value #COLUMN_NAME_SALES_CHANNEL_ID}, {@value #COLUMN_NAME_SALES_REP_ID},
 * {@value #COLUMN_NAME_CONVERTED_TO_ORDER_ID} columns are mapped read-only, as a foreign key; the extending entity owns
 * the writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesQuote {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesQuote";

    // --------------------------------------------------------------------------------------------------------- QuoteID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTE_ID = "QuoteID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTE_ID = "quoteId";

    // ----------------------------------------------------------------------------------------------------- QuoteNumber

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTE_NUMBER = "QuoteNumber";

    /**
     * The length of the {@value #COLUMN_NAME_QUOTE_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_QUOTE_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTE_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTE_NUMBER = "quoteNumber";

    // ------------------------------------------------------------------------------------------------------ CustomerID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "CustomerID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_ID = "customerId";

    // -------------------------------------------------------------------------------------------------- SalesChannelID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_CHANNEL_ID = "SalesChannelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL_ID = "salesChannelId";

    // ------------------------------------------------------------------------------------------------------ SalesRepID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REP_ID = "SalesRepID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REP_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP_ID = "salesRepId";

    // ------------------------------------------------------------------------------------------------------- QuoteDate

    /**
     * The name of the {@code date, DEFAULT (CONVERT([date],getdate())), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTE_DATE = "QuoteDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTE_DATE = "quoteDate";

    // -------------------------------------------------------------------------------------------------- ExpirationDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPIRATION_DATE = "ExpirationDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPIRATION_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPIRATION_DATE = "expirationDate";

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

    // ---------------------------------------------------------------------------------------------- ConvertedToOrderID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONVERTED_TO_ORDER_ID = "ConvertedToOrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONVERTED_TO_ORDER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONVERTED_TO_ORDER_ID = "convertedToOrderId";

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

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesQuote() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "quoteId=" + quoteId
               + ",quoteNumber=" + quoteNumber
               + ",customerId=" + customerId
               + ",salesChannelId=" + salesChannelId
               + ",salesRepId=" + salesRepId
               + ",quoteDate=" + quoteDate
               + ",expirationDate=" + expirationDate
               + ",status=" + status
               + ",subtotal=" + subtotal
               + ",discountAmount=" + discountAmount
               + ",taxAmount=" + taxAmount
               + ",totalAmount=" + totalAmount
               + ",convertedToOrderId=" + convertedToOrderId
               + ",notes=" + notes
               + ",createdBy=" + createdBy
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
        if (!(obj instanceof MappedSalesQuote that)) {
            return false;
        }
        return Objects.equals(quoteId, that.quoteId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(quoteId);
    }

    // --------------------------------------------------------------------------------------------------------- quoteId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTE_ID} attribute.
     */
    public Integer getQuoteId() {
        return quoteId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTE_ID} attribute with the specified value.
     *
     * @param quoteId new value for {@value #ATTRIBUTE_NAME_QUOTE_ID} attribute.
     */
    protected void setQuoteId(final Integer quoteId) {
        this.quoteId = quoteId;
    }

    // ----------------------------------------------------------------------------------------------------- quoteNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTE_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTE_NUMBER} attribute.
     */
    public String getQuoteNumber() {
        return quoteNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTE_NUMBER} attribute with the specified value.
     *
     * @param quoteNumber new value for {@value #ATTRIBUTE_NAME_QUOTE_NUMBER} attribute.
     */
    public void setQuoteNumber(final String quoteNumber) {
        this.quoteNumber = quoteNumber;
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

    // ------------------------------------------------------------------------------------------------------- quoteDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTE_DATE} attribute.
     */
    public LocalDate getQuoteDate() {
        return quoteDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTE_DATE} attribute with the specified value.
     *
     * @param quoteDate new value for {@value #ATTRIBUTE_NAME_QUOTE_DATE} attribute.
     */
    public void setQuoteDate(final LocalDate quoteDate) {
        this.quoteDate = quoteDate;
    }

    // -------------------------------------------------------------------------------------------------- expirationDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPIRATION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPIRATION_DATE} attribute.
     */
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPIRATION_DATE} attribute with the specified value.
     *
     * @param expirationDate new value for {@value #ATTRIBUTE_NAME_EXPIRATION_DATE} attribute.
     */
    public void setExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
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

    // ---------------------------------------------------------------------------------------------- convertedToOrderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONVERTED_TO_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONVERTED_TO_ORDER_ID} attribute.
     */
    public Integer getConvertedToOrderId() {
        return convertedToOrderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONVERTED_TO_ORDER_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param convertedToOrderId new value for {@value #ATTRIBUTE_NAME_CONVERTED_TO_ORDER_ID} attribute.
     */
    protected void setConvertedToOrderId(final Integer convertedToOrderId) {
        this.convertedToOrderId = convertedToOrderId;
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

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_QUOTE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer quoteId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_QUOTE_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUOTE_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_QUOTE_NUMBER
    )
    private String quoteNumber;

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
    @Column(name = COLUMN_NAME_SALES_REP_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer salesRepId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUOTE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate quoteDate;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPIRATION_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate expirationDate;

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
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CONVERTED_TO_ORDER_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer convertedToOrderId;

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
}

