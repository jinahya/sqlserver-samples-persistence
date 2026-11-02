package com.github.jinahya.sqlserver.sample.persistence.futonmanufacturing.mapped.dbo;

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
import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_Sales_QuoteConversion} view.
 * <p>
 * Identifier: the view declares no key; QuoteNumber is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); some of its
 * columns are derived or constant (error 4406).
 */
@MappedSuperclass
public abstract class MappedSalesQuoteConversionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_Sales_QuoteConversion";

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

    // ----------------------------------------------------------------------------------------------------- QuoteStatus

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTE_STATUS = "QuoteStatus";

    /**
     * The length of the {@value #COLUMN_NAME_QUOTE_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_QUOTE_STATUS = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTE_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTE_STATUS = "quoteStatus";

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
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
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

    // -------------------------------------------------------------------------------------------------------- SalesRep

    /**
     * The name of the {@code nvarchar(101)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REP = "SalesRep";

    /**
     * The length of the {@value #COLUMN_NAME_SALES_REP} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SALES_REP = 101;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REP} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REP = "salesRep";

    // --------------------------------------------------------------------------------------------------- TerritoryName

    /**
     * The name of the {@code nvarchar(100)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_NAME = "TerritoryName";

    /**
     * The length of the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TERRITORY_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_NAME = "territoryName";

    // ------------------------------------------------------------------------------------------------------- QuoteDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
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

    // ---------------------------------------------------------------------------------------------------- DaysToExpire

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_TO_EXPIRE = "DaysToExpire";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_TO_EXPIRE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_TO_EXPIRE = "daysToExpire";

    // ----------------------------------------------------------------------------------------------------- QuoteAmount

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTE_AMOUNT = "QuoteAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_QUOTE_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUOTE_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUOTE_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUOTE_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTE_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTE_AMOUNT = "quoteAmount";

    // ------------------------------------------------------------------------------------------------ ConversionStatus

    /**
     * The name of the {@code varchar(18), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONVERSION_STATUS = "ConversionStatus";

    /**
     * The length of the {@value #COLUMN_NAME_CONVERSION_STATUS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONVERSION_STATUS = 18;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONVERSION_STATUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONVERSION_STATUS = "conversionStatus";

    // -------------------------------------------------------------------------------------------- ConvertedOrderNumber

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CONVERTED_ORDER_NUMBER = "ConvertedOrderNumber";

    /**
     * The length of the {@value #COLUMN_NAME_CONVERTED_ORDER_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CONVERTED_ORDER_NUMBER = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CONVERTED_ORDER_NUMBER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CONVERTED_ORDER_NUMBER = "convertedOrderNumber";

    // ----------------------------------------------------------------------------------------------------- OrderAmount

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_AMOUNT = "OrderAmount";

    /**
     * The precision of the {@value #COLUMN_NAME_ORDER_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_ORDER_AMOUNT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_ORDER_AMOUNT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_ORDER_AMOUNT = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_AMOUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_AMOUNT = "orderAmount";

    // --------------------------------------------------------------------------------------------------- ValueVariance

    /**
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALUE_VARIANCE = "ValueVariance";

    /**
     * The precision of the {@value #COLUMN_NAME_VALUE_VARIANCE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_VALUE_VARIANCE = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_VALUE_VARIANCE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_VALUE_VARIANCE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALUE_VARIANCE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALUE_VARIANCE = "valueVariance";

    // --------------------------------------------------------------------------------------------------- DaysToConvert

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_TO_CONVERT = "DaysToConvert";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_TO_CONVERT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_TO_CONVERT = "daysToConvert";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesQuoteConversionView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "quoteNumber=" + quoteNumber
               + ",quoteStatus=" + quoteStatus
               + ",customerName=" + customerName
               + ",customerType=" + customerType
               + ",channelName=" + channelName
               + ",salesRep=" + salesRep
               + ",territoryName=" + territoryName
               + ",quoteDate=" + quoteDate
               + ",expirationDate=" + expirationDate
               + ",daysToExpire=" + daysToExpire
               + ",quoteAmount=" + quoteAmount
               + ",conversionStatus=" + conversionStatus
               + ",convertedOrderNumber=" + convertedOrderNumber
               + ",orderAmount=" + orderAmount
               + ",valueVariance=" + valueVariance
               + ",daysToConvert=" + daysToConvert
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
        if (!(obj instanceof MappedSalesQuoteConversionView that)) {
            return false;
        }
        return Objects.equals(quoteNumber, that.quoteNumber);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(quoteNumber);
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
    protected void setQuoteNumber(final String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    // ----------------------------------------------------------------------------------------------------- quoteStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTE_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTE_STATUS} attribute.
     */
    public String getQuoteStatus() {
        return quoteStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTE_STATUS} attribute with the specified value.
     *
     * @param quoteStatus new value for {@value #ATTRIBUTE_NAME_QUOTE_STATUS} attribute.
     */
    protected void setQuoteStatus(final String quoteStatus) {
        this.quoteStatus = quoteStatus;
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

    // -------------------------------------------------------------------------------------------------------- salesRep

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REP} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REP} attribute.
     */
    public String getSalesRep() {
        return salesRep;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REP} attribute with the specified value.
     *
     * @param salesRep new value for {@value #ATTRIBUTE_NAME_SALES_REP} attribute.
     */
    protected void setSalesRep(final String salesRep) {
        this.salesRep = salesRep;
    }

    // --------------------------------------------------------------------------------------------------- territoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    public String getTerritoryName() {
        return territoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute with the specified value.
     *
     * @param territoryName new value for {@value #ATTRIBUTE_NAME_TERRITORY_NAME} attribute.
     */
    protected void setTerritoryName(final String territoryName) {
        this.territoryName = territoryName;
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
    protected void setQuoteDate(final LocalDate quoteDate) {
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
    protected void setExpirationDate(final LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    // ---------------------------------------------------------------------------------------------------- daysToExpire

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_TO_EXPIRE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_TO_EXPIRE} attribute.
     */
    public Integer getDaysToExpire() {
        return daysToExpire;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_TO_EXPIRE} attribute with the specified value.
     *
     * @param daysToExpire new value for {@value #ATTRIBUTE_NAME_DAYS_TO_EXPIRE} attribute.
     */
    protected void setDaysToExpire(final Integer daysToExpire) {
        this.daysToExpire = daysToExpire;
    }

    // ----------------------------------------------------------------------------------------------------- quoteAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTE_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTE_AMOUNT} attribute.
     */
    public BigDecimal getQuoteAmount() {
        return quoteAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTE_AMOUNT} attribute with the specified value.
     *
     * @param quoteAmount new value for {@value #ATTRIBUTE_NAME_QUOTE_AMOUNT} attribute.
     */
    protected void setQuoteAmount(final BigDecimal quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    // ------------------------------------------------------------------------------------------------ conversionStatus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONVERSION_STATUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONVERSION_STATUS} attribute.
     */
    public String getConversionStatus() {
        return conversionStatus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONVERSION_STATUS} attribute with the specified value.
     *
     * @param conversionStatus new value for {@value #ATTRIBUTE_NAME_CONVERSION_STATUS} attribute.
     */
    protected void setConversionStatus(final String conversionStatus) {
        this.conversionStatus = conversionStatus;
    }

    // -------------------------------------------------------------------------------------------- convertedOrderNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CONVERTED_ORDER_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CONVERTED_ORDER_NUMBER} attribute.
     */
    public String getConvertedOrderNumber() {
        return convertedOrderNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CONVERTED_ORDER_NUMBER} attribute with the specified value.
     *
     * @param convertedOrderNumber new value for {@value #ATTRIBUTE_NAME_CONVERTED_ORDER_NUMBER} attribute.
     */
    protected void setConvertedOrderNumber(final String convertedOrderNumber) {
        this.convertedOrderNumber = convertedOrderNumber;
    }

    // ----------------------------------------------------------------------------------------------------- orderAmount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_AMOUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_AMOUNT} attribute.
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_AMOUNT} attribute with the specified value.
     *
     * @param orderAmount new value for {@value #ATTRIBUTE_NAME_ORDER_AMOUNT} attribute.
     */
    protected void setOrderAmount(final BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    // --------------------------------------------------------------------------------------------------- valueVariance

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALUE_VARIANCE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALUE_VARIANCE} attribute.
     */
    public BigDecimal getValueVariance() {
        return valueVariance;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALUE_VARIANCE} attribute with the specified value.
     *
     * @param valueVariance new value for {@value #ATTRIBUTE_NAME_VALUE_VARIANCE} attribute.
     */
    protected void setValueVariance(final BigDecimal valueVariance) {
        this.valueVariance = valueVariance;
    }

    // --------------------------------------------------------------------------------------------------- daysToConvert

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_TO_CONVERT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_TO_CONVERT} attribute.
     */
    public Integer getDaysToConvert() {
        return daysToConvert;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_TO_CONVERT} attribute with the specified value.
     *
     * @param daysToConvert new value for {@value #ATTRIBUTE_NAME_DAYS_TO_CONVERT} attribute.
     */
    protected void setDaysToConvert(final Integer daysToConvert) {
        this.daysToConvert = daysToConvert;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_QUOTE_NUMBER)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_QUOTE_NUMBER,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_QUOTE_NUMBER
    )
    private String quoteNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_QUOTE_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUOTE_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_QUOTE_STATUS
    )
    private String quoteStatus;

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
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CHANNEL_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CHANNEL_NAME
    )
    private String channelName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_SALES_REP)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_REP,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_SALES_REP
    )
    private String salesRep;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_TERRITORY_NAME)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TERRITORY_NAME,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_TERRITORY_NAME
    )
    private String territoryName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUOTE_DATE,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDate quoteDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPIRATION_DATE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private LocalDate expirationDate;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_TO_EXPIRE,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer daysToExpire;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_QUOTE_AMOUNT - COLUMN_SCALE_QUOTE_AMOUNT, fraction = COLUMN_SCALE_QUOTE_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUOTE_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_QUOTE_AMOUNT,
            scale = COLUMN_SCALE_QUOTE_AMOUNT
    )
    private BigDecimal quoteAmount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CONVERSION_STATUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CONVERSION_STATUS,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CONVERSION_STATUS
    )
    private String conversionStatus;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_CONVERTED_ORDER_NUMBER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CONVERTED_ORDER_NUMBER,
            nullable = true,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_CONVERTED_ORDER_NUMBER
    )
    private String convertedOrderNumber;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_ORDER_AMOUNT - COLUMN_SCALE_ORDER_AMOUNT, fraction = COLUMN_SCALE_ORDER_AMOUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ORDER_AMOUNT,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_ORDER_AMOUNT,
            scale = COLUMN_SCALE_ORDER_AMOUNT
    )
    private BigDecimal orderAmount;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Digits(integer = COLUMN_PRECISION_VALUE_VARIANCE - COLUMN_SCALE_VALUE_VARIANCE,
            fraction = COLUMN_SCALE_VALUE_VARIANCE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_VALUE_VARIANCE,
            nullable = true,
            insertable = false,
            updatable = false,
            precision = COLUMN_PRECISION_VALUE_VARIANCE,
            scale = COLUMN_SCALE_VALUE_VARIANCE
    )
    private BigDecimal valueVariance;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_TO_CONVERT,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer daysToConvert;
}

