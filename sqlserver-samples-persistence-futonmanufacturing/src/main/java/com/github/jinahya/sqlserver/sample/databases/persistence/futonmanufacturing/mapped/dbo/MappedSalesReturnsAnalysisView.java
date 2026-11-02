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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_ReturnsAnalysis} view.
 * <p>
 * Identifier: the view declares no key; ReturnID is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; it contains aggregates, or a DISTINCT or GROUP BY clause (error 4403); some of its columns are derived or
 * constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesReturnsAnalysisView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_ReturnsAnalysis";

    // -------------------------------------------------------------------------------------------------------- ReturnID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_ID = "ReturnID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_ID = "returnId";

    // ------------------------------------------------------------------------------------------------------------ Year

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_YEAR = "Year";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_YEAR = "year";

    // ----------------------------------------------------------------------------------------------------------- Month

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MONTH = "Month";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MONTH = "month";

    // ------------------------------------------------------------------------------------------------------ ReasonCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REASON_CODE = "ReasonCode";

    /**
     * The length of the {@value #COLUMN_NAME_REASON_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REASON_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REASON_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REASON_CODE = "reasonCode";

    // ----------------------------------------------------------------------------------------------- ReasonDescription

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REASON_DESCRIPTION = "ReasonDescription";

    /**
     * The length of the {@value #COLUMN_NAME_REASON_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REASON_DESCRIPTION = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REASON_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REASON_DESCRIPTION = "reasonDescription";

    // ---------------------------------------------------------------------------------------------------- CustomerName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_NAME = "CustomerName";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_NAME = "customerName";

    // ---------------------------------------------------------------------------------------------------- CustomerType

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOMER_TYPE = "CustomerType";

    /**
     * The length of the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CUSTOMER_TYPE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOMER_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOMER_TYPE = "customerType";

    // ----------------------------------------------------------------------------------------------------- ChannelName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CHANNEL_NAME = "ChannelName";

    /**
     * The length of the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CHANNEL_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CHANNEL_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CHANNEL_NAME = "channelName";

    // ---------------------------------------------------------------------------------------------------- TotalRefunds

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_REFUNDS = "TotalRefunds";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_REFUNDS} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_REFUNDS = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_REFUNDS} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_REFUNDS = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_REFUNDS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_REFUNDS = "totalRefunds";

    // ------------------------------------------------------------------------------------------------- AvgRefundAmount

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVG_REFUND_AMOUNT = "AvgRefundAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_AVG_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVG_REFUND_AMOUNT = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVG_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVG_REFUND_AMOUNT = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVG_REFUND_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVG_REFUND_AMOUNT = "avgRefundAmount";

    // --------------------------------------------------------------------------------------------- TotalRestockingFees

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TOTAL_RESTOCKING_FEES = "TotalRestockingFees";

    /**
     * The precision of the {@value #COLUMN_NAME_TOTAL_RESTOCKING_FEES} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TOTAL_RESTOCKING_FEES = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_TOTAL_RESTOCKING_FEES} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TOTAL_RESTOCKING_FEES = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TOTAL_RESTOCKING_FEES} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TOTAL_RESTOCKING_FEES = "totalRestockingFees";

    // -------------------------------------------------------------------------------------------------------- ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // -------------------------------------------------------------------------------------------------------- ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // --------------------------------------------------------------------------------------------------- UnitsReturned

    /**
     * The name of the {@code decimal(38, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNITS_RETURNED = "UnitsReturned";

    /**
     * The precision of the {@value #COLUMN_NAME_UNITS_RETURNED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNITS_RETURNED = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_UNITS_RETURNED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNITS_RETURNED = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNITS_RETURNED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNITS_RETURNED = "unitsReturned";

    // ----------------------------------------------------------------------------------------------------- Disposition

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISPOSITION = "Disposition";

    /**
     * The length of the {@value #COLUMN_NAME_DISPOSITION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DISPOSITION = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISPOSITION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISPOSITION = "disposition";

    // ----------------------------------------------------------------------------------------------- ReturnRatePercent

    /**
     * The name of the {@code numeric(26, 12)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_RATE_PERCENT = "ReturnRatePercent";

    /**
     * The precision of the {@value #COLUMN_NAME_RETURN_RATE_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RETURN_RATE_PERCENT = 26;

    /**
     * The scale of the {@value #COLUMN_NAME_RETURN_RATE_PERCENT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RETURN_RATE_PERCENT = 12;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_RATE_PERCENT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_RATE_PERCENT = "returnRatePercent";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesReturnsAnalysisView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "returnId=" + returnId
               + ",year=" + year
               + ",month=" + month
               + ",reasonCode=" + reasonCode
               + ",reasonDescription=" + reasonDescription
               + ",customerName=" + customerName
               + ",customerType=" + customerType
               + ",channelName=" + channelName
               + ",totalRefunds=" + totalRefunds
               + ",avgRefundAmount=" + avgRefundAmount
               + ",totalRestockingFees=" + totalRestockingFees
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",unitsReturned=" + unitsReturned
               + ",disposition=" + disposition
               + ",returnRatePercent=" + returnRatePercent
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
        if (!(obj instanceof MappedSalesReturnsAnalysisView that)) {
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

    // ------------------------------------------------------------------------------------------------------------ year

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_YEAR} attribute with the specified value.
     *
     * @param year new value for {@value #ATTRIBUTE_NAME_YEAR} attribute.
     */
    protected void setYear(final Integer year) {
        this.year = year;
    }

    // ----------------------------------------------------------------------------------------------------------- month

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MONTH} attribute with the specified value.
     *
     * @param month new value for {@value #ATTRIBUTE_NAME_MONTH} attribute.
     */
    protected void setMonth(final Integer month) {
        this.month = month;
    }

    // ------------------------------------------------------------------------------------------------------ reasonCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REASON_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REASON_CODE} attribute.
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REASON_CODE} attribute with the specified value.
     *
     * @param reasonCode new value for {@value #ATTRIBUTE_NAME_REASON_CODE} attribute.
     */
    protected void setReasonCode(final String reasonCode) {
        this.reasonCode = reasonCode;
    }

    // ----------------------------------------------------------------------------------------------- reasonDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute.
     */
    public String getReasonDescription() {
        return reasonDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute with the specified value.
     *
     * @param reasonDescription new value for {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute.
     */
    protected void setReasonDescription(final String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    // ---------------------------------------------------------------------------------------------------- customerName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute with the specified value.
     *
     * @param customerName new value for {@value #ATTRIBUTE_NAME_CUSTOMER_NAME} attribute.
     */
    protected void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    // ---------------------------------------------------------------------------------------------------- customerType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    public String getCustomerType() {
        return customerType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute with the specified value.
     *
     * @param customerType new value for {@value #ATTRIBUTE_NAME_CUSTOMER_TYPE} attribute.
     */
    protected void setCustomerType(final String customerType) {
        this.customerType = customerType;
    }

    // ----------------------------------------------------------------------------------------------------- channelName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute with the specified value.
     *
     * @param channelName new value for {@value #ATTRIBUTE_NAME_CHANNEL_NAME} attribute.
     */
    protected void setChannelName(final String channelName) {
        this.channelName = channelName;
    }

    // ---------------------------------------------------------------------------------------------------- totalRefunds

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute.
     */
    public BigDecimal getTotalRefunds() {
        return totalRefunds;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute with the specified value.
     *
     * @param totalRefunds new value for {@value #ATTRIBUTE_NAME_TOTAL_REFUNDS} attribute.
     */
    protected void setTotalRefunds(final BigDecimal totalRefunds) {
        this.totalRefunds = totalRefunds;
    }

    // ------------------------------------------------------------------------------------------------- avgRefundAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVG_REFUND_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVG_REFUND_AMOUNT} attribute.
     */
    public BigDecimal getAvgRefundAmount() {
        return avgRefundAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVG_REFUND_AMOUNT} attribute with the specified value.
     *
     * @param avgRefundAmount new value for {@value #ATTRIBUTE_NAME_AVG_REFUND_AMOUNT} attribute.
     */
    protected void setAvgRefundAmount(final BigDecimal avgRefundAmount) {
        this.avgRefundAmount = avgRefundAmount;
    }

    // --------------------------------------------------------------------------------------------- totalRestockingFees

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TOTAL_RESTOCKING_FEES} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TOTAL_RESTOCKING_FEES} attribute.
     */
    public BigDecimal getTotalRestockingFees() {
        return totalRestockingFees;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TOTAL_RESTOCKING_FEES} attribute with the specified value.
     *
     * @param totalRestockingFees new value for {@value #ATTRIBUTE_NAME_TOTAL_RESTOCKING_FEES} attribute.
     */
    protected void setTotalRestockingFees(final BigDecimal totalRestockingFees) {
        this.totalRestockingFees = totalRestockingFees;
    }

    // -------------------------------------------------------------------------------------------------------- itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // -------------------------------------------------------------------------------------------------------- itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    // --------------------------------------------------------------------------------------------------- unitsReturned

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNITS_RETURNED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNITS_RETURNED} attribute.
     */
    public BigDecimal getUnitsReturned() {
        return unitsReturned;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNITS_RETURNED} attribute with the specified value.
     *
     * @param unitsReturned new value for {@value #ATTRIBUTE_NAME_UNITS_RETURNED} attribute.
     */
    protected void setUnitsReturned(final BigDecimal unitsReturned) {
        this.unitsReturned = unitsReturned;
    }

    // ----------------------------------------------------------------------------------------------------- disposition

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISPOSITION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISPOSITION} attribute.
     */
    public String getDisposition() {
        return disposition;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISPOSITION} attribute with the specified value.
     *
     * @param disposition new value for {@value #ATTRIBUTE_NAME_DISPOSITION} attribute.
     */
    protected void setDisposition(final String disposition) {
        this.disposition = disposition;
    }

    // ----------------------------------------------------------------------------------------------- returnRatePercent

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_RATE_PERCENT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_RATE_PERCENT} attribute.
     */
    public BigDecimal getReturnRatePercent() {
        return returnRatePercent;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_RATE_PERCENT} attribute with the specified value.
     *
     * @param returnRatePercent new value for {@value #ATTRIBUTE_NAME_RETURN_RATE_PERCENT} attribute.
     */
    protected void setReturnRatePercent(final BigDecimal returnRatePercent) {
        this.returnRatePercent = returnRatePercent;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_RETURN_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer returnId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_YEAR,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer year;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MONTH,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer month;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_REASON_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REASON_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_REASON_CODE
    )
    private String reasonCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_REASON_DESCRIPTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REASON_DESCRIPTION,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_REASON_DESCRIPTION
    )
    private String reasonDescription;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CUSTOMER_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_NAME
    )
    private String customerName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CUSTOMER_TYPE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOMER_TYPE,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CUSTOMER_TYPE
    )
    private String customerType;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CHANNEL_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_REFUNDS - COLUMN_SCALE_TOTAL_REFUNDS,
            fraction = COLUMN_SCALE_TOTAL_REFUNDS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_REFUNDS,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_REFUNDS,
            scale = COLUMN_SCALE_TOTAL_REFUNDS
    )
    private BigDecimal totalRefunds;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_AVG_REFUND_AMOUNT - COLUMN_SCALE_AVG_REFUND_AMOUNT,
            fraction = COLUMN_SCALE_AVG_REFUND_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVG_REFUND_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_AVG_REFUND_AMOUNT,
            scale = COLUMN_SCALE_AVG_REFUND_AMOUNT
    )
    private BigDecimal avgRefundAmount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_TOTAL_RESTOCKING_FEES - COLUMN_SCALE_TOTAL_RESTOCKING_FEES,
            fraction = COLUMN_SCALE_TOTAL_RESTOCKING_FEES)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TOTAL_RESTOCKING_FEES,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_TOTAL_RESTOCKING_FEES,
            scale = COLUMN_SCALE_TOTAL_RESTOCKING_FEES
    )
    private BigDecimal totalRestockingFees;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_CODE
    )
    private String itemCode;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_ITEM_NAME
    )
    private String itemName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_UNITS_RETURNED - COLUMN_SCALE_UNITS_RETURNED,
            fraction = COLUMN_SCALE_UNITS_RETURNED)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNITS_RETURNED,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_UNITS_RETURNED,
            scale = COLUMN_SCALE_UNITS_RETURNED
    )
    private BigDecimal unitsReturned;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_DISPOSITION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DISPOSITION,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_DISPOSITION
    )
    private String disposition;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_RETURN_RATE_PERCENT - COLUMN_SCALE_RETURN_RATE_PERCENT,
            fraction = COLUMN_SCALE_RETURN_RATE_PERCENT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RETURN_RATE_PERCENT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_RETURN_RATE_PERCENT,
            scale = COLUMN_SCALE_RETURN_RATE_PERCENT
    )
    private BigDecimal returnRatePercent;
}

