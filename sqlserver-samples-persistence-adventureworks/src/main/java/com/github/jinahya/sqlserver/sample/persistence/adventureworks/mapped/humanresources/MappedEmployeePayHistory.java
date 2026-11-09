package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.humanresources;

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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code HumanResources.EmployeePayHistory}
 * table.
 * <p>
 * Read-only: the application writes this table only through
 * {@code HumanResources.uspUpdateEmployeeHireInfo}, which inserts a row, and nothing updates or
 * deletes one; so no column of it is insertable, nor updatable.
 *
 * <table class="striped">
 * <caption>Columns of {@code HumanResources.EmployeePayHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code BusinessEntityID}</th>
 *     <td>Employee identification number. Foreign key to Employee.BusinessEntityID.</td></tr>
 * <tr><th scope="row">{@code RateChangeDate}</th><td>Date the change in pay is effective</td></tr>
 * <tr><th scope="row">{@code Rate}</th><td>Salary hourly rate.</td></tr>
 * <tr><th scope="row">{@code PayFrequency}</th>
 *     <td>1 = Salary received monthly, 2 = Salary received biweekly</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code HumanResources.EmployeePayHistory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedEmployeePayHistory.TABLE_SCHEMA,
        table = MappedEmployeePayHistory.TABLE_NAME,
        value = "Employee pay history."
)
public abstract class MappedEmployeePayHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "EmployeePayHistory";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------ RateChangeDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATE_CHANGE_DATE = "RateChangeDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATE_CHANGE_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATE_CHANGE_DATE = "rateChangeDate";

    // ---------------------------------------------------------------------------------------- Rate

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RATE = "Rate";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_RATE} attribute, as the
     * {@code CK_EmployeePayHistory_Rate} check constraint ({@code [Rate]>=(6.50)}) requires. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_RATE = "6.50";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_RATE} attribute, as the
     * {@code CK_EmployeePayHistory_Rate} check constraint ({@code [Rate]<=(200.00)}) requires. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_RATE = "200.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATE = "rate";

    // -------------------------------------------------------------------------------- PayFrequency

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PAY_FREQUENCY = "PayFrequency";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute, as the
     * {@code CK_EmployeePayHistory_PayFrequency} check constraint ({@code [PayFrequency]=(1)})
     * requires. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PAY_FREQUENCY = +1;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute, as the
     * {@code CK_EmployeePayHistory_PayFrequency} check constraint ({@code [PayFrequency]=(2)})
     * requires. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PAY_FREQUENCY = +2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PAY_FREQUENCY} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PAY_FREQUENCY = "payFrequency";

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
    protected MappedEmployeePayHistory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",rateChangeDate=" + rateChangeDate
               + ",rate=" + rate
               + ",payFrequency=" + payFrequency
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
        if (!(obj instanceof MappedEmployeePayHistory that)) {
            return false;
        }
        return Objects.equals(getBusinessEntityId(), that.getBusinessEntityId()) &&
               Objects.equals(getRateChangeDate(), that.getRateChangeDate());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getBusinessEntityId(), getRateChangeDate());
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the
     * specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ------------------------------------------------------------------------------ rateChangeDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute.
     */
    public LocalDateTime getRateChangeDate() {
        return rateChangeDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute with the
     * specified value.
     *
     * @param rateChangeDate new value for {@value #ATTRIBUTE_NAME_RATE_CHANGE_DATE} attribute.
     */
    protected void setRateChangeDate(final LocalDateTime rateChangeDate) {
        this.rateChangeDate = rateChangeDate;
    }

    // ---------------------------------------------------------------------------------------- rate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RATE} attribute.
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RATE} attribute with the specified value.
     *
     * @param rate new value for {@value #ATTRIBUTE_NAME_RATE} attribute.
     */
    protected void setRate(final BigDecimal rate) {
        this.rate = rate;
    }

    // -------------------------------------------------------------------------------- payFrequency

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute.
     */
    public Integer getPayFrequency() {
        return payFrequency;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute with the specified
     * value.
     *
     * @param payFrequency new value for {@value #ATTRIBUTE_NAME_PAY_FREQUENCY} attribute.
     */
    protected void setPayFrequency(final Integer payFrequency) {
        this.payFrequency = payFrequency;
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
    // read-only: HumanResources.uspUpdateEmployeeHireInfo writes this table; see the class
    // documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Employee identification number. Foreign key to Employee.BusinessEntityID.")
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // read-only: HumanResources.uspUpdateEmployeeHireInfo writes this table; see the class
    // documentation
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_RATE_CHANGE_DATE, nullable = false, insertable = false,
            updatable = false,
            comment = "Date the change in pay is effective")
    private LocalDateTime rateChangeDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: HumanResources.uspUpdateEmployeeHireInfo writes this table; see the class
    // documentation
    // CK_EmployeePayHistory_Rate: ([Rate]>=(6.50) AND [Rate]<=(200.00))
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_RATE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RATE, nullable = false, insertable = false, updatable = false, precision = SqlServerConstants.MONEY_PRECISION, scale = SqlServerConstants.MONEY_SCALE,
            comment = "Salary hourly rate.")
    private BigDecimal rate;

    // ---------------------------------------------------------------------------------------------
    // read-only: HumanResources.uspUpdateEmployeeHireInfo writes this table; see the class
    // documentation
    // CK_EmployeePayHistory_PayFrequency: ([PayFrequency]=(2) OR [PayFrequency]=(1))
    @Max(ATTRIBUTE_MAX_PAY_FREQUENCY)
    @Min(ATTRIBUTE_MIN_PAY_FREQUENCY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PAY_FREQUENCY, nullable = false, insertable = false,
            updatable = false,
            comment = "1 = Salary received monthly, 2 = Salary received biweekly")
    private Integer payFrequency;

    // ---------------------------------------------------------------------------------------------
    // read-only: HumanResources.uspUpdateEmployeeHireInfo writes this table; see the class
    // documentation
    // the database's DEFAULT (getdate()) fills it on the INSERT of the procedure
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = false,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_EmployeePayHistory_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

