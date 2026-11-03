package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.CreditCard} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.CreditCard}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code CreditCardID}</th><td>Primary key for CreditCard records.</td></tr>
 * <tr><th scope="row">{@code CardType}</th><td>Credit card name.</td></tr>
 * <tr><th scope="row">{@code CardNumber}</th><td>Credit card number.</td></tr>
 * <tr><th scope="row">{@code ExpMonth}</th><td>Credit card expiration month.</td></tr>
 * <tr><th scope="row">{@code ExpYear}</th><td>Credit card expiration year.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.CreditCard}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedCreditCard.TABLE_SCHEMA,
        table = MappedCreditCard.TABLE_NAME,
        value = "Customer credit card information."
)
public abstract class MappedCreditCard {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "CreditCard";

    // -------------------------------------------------------------------------------- CreditCardID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CREDIT_CARD_ID = "CreditCardID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CREDIT_CARD_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CREDIT_CARD_ID = "creditCardId";

    // ------------------------------------------------------------------------------------ CardType

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CARD_TYPE = "CardType";

    /**
     * The length of the {@value #COLUMN_NAME_CARD_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CARD_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CARD_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CARD_TYPE = "cardType";

    // ---------------------------------------------------------------------------------- CardNumber

    /**
     * The name of the {@code nvarchar(25), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CARD_NUMBER = "CardNumber";

    /**
     * The length of the {@value #COLUMN_NAME_CARD_NUMBER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CARD_NUMBER = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CARD_NUMBER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CARD_NUMBER = "cardNumber";

    // ------------------------------------------------------------------------------------ ExpMonth

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXP_MONTH = "ExpMonth";

    /**
     * The minimum value of the {@value #COLUMN_NAME_EXP_MONTH} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_EXP_MONTH = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_EXP_MONTH} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_EXP_MONTH = +255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXP_MONTH} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXP_MONTH = "expMonth";

    // ------------------------------------------------------------------------------------- ExpYear

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXP_YEAR = "ExpYear";

    /**
     * The minimum value of the {@value #COLUMN_NAME_EXP_YEAR} column. The value is {@value}.
     */
    public static final int COLUMN_MIN_EXP_YEAR = Short.MIN_VALUE;

    /**
     * The maximum value of the {@value #COLUMN_NAME_EXP_YEAR} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_EXP_YEAR = Short.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXP_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXP_YEAR = "expYear";

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
    protected MappedCreditCard() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "creditCardId=" + creditCardId
               + ",cardType=" + cardType
               + ",cardNumber=" + cardNumber
               + ",expMonth=" + expMonth
               + ",expYear=" + expYear
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
        if (!(obj instanceof MappedCreditCard that)) {
            return false;
        }
        return Objects.equals(creditCardId, that.creditCardId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(creditCardId);
    }

    // -------------------------------------------------------------------------------- creditCardId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute.
     */
    public Integer getCreditCardId() {
        return creditCardId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute with the specified value.
     *
     * @param creditCardId new value for {@value #ATTRIBUTE_NAME_CREDIT_CARD_ID} attribute.
     */
    protected void setCreditCardId(final Integer creditCardId) {
        this.creditCardId = creditCardId;
    }

    // ------------------------------------------------------------------------------------ cardType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CARD_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CARD_TYPE} attribute.
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CARD_TYPE} attribute with the specified value.
     *
     * @param cardType new value for {@value #ATTRIBUTE_NAME_CARD_TYPE} attribute.
     */
    public void setCardType(final String cardType) {
        this.cardType = cardType;
    }

    // ---------------------------------------------------------------------------------- cardNumber

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CARD_NUMBER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CARD_NUMBER} attribute.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CARD_NUMBER} attribute with the specified value.
     *
     * @param cardNumber new value for {@value #ATTRIBUTE_NAME_CARD_NUMBER} attribute.
     */
    public void setCardNumber(final String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // ------------------------------------------------------------------------------------ expMonth

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXP_MONTH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXP_MONTH} attribute.
     */
    public Integer getExpMonth() {
        return expMonth;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXP_MONTH} attribute with the specified value.
     *
     * @param expMonth new value for {@value #ATTRIBUTE_NAME_EXP_MONTH} attribute.
     */
    public void setExpMonth(final Integer expMonth) {
        this.expMonth = expMonth;
    }

    // ------------------------------------------------------------------------------------- expYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXP_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXP_YEAR} attribute.
     */
    public Integer getExpYear() {
        return expYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXP_YEAR} attribute with the specified value.
     *
     * @param expYear new value for {@value #ATTRIBUTE_NAME_EXP_YEAR} attribute.
     */
    public void setExpYear(final Integer expYear) {
        this.expYear = expYear;
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
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_CREDIT_CARD_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_CREDIT_CARD_ID,
                          value = "Primary key for CreditCard records.")
    private Integer creditCardId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CARD_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CARD_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CARD_TYPE
    )
    @MsDescription.Column(name = COLUMN_NAME_CARD_TYPE, value = "Credit card name.")
    private String cardType;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CARD_NUMBER)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CARD_NUMBER,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CARD_NUMBER
    )
    @MsDescription.Column(name = COLUMN_NAME_CARD_NUMBER, value = "Credit card number.")
    private String cardNumber;

    // ---------------------------------------------------------------------------------------------
    @Max(COLUMN_MAX_EXP_MONTH)
    @Min(COLUMN_MIN_EXP_MONTH)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EXP_MONTH,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_EXP_MONTH, value = "Credit card expiration month.")
    private Integer expMonth;

    // ---------------------------------------------------------------------------------------------
    @Max(COLUMN_MAX_EXP_YEAR)
    @Min(COLUMN_MIN_EXP_YEAR)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EXP_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_EXP_YEAR, value = "Credit card expiration year.")
    private Integer expYear;

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
    @MsDefault(name = "DF_CreditCard_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

