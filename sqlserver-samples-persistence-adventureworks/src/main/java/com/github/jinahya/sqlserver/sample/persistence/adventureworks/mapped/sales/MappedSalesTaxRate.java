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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesTaxRate} table.
 * <p>
 * The {@value #COLUMN_NAME_STATE_PROVINCE_ID} column is mapped read-only, as a foreign key; the
 * extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.SalesTaxRate}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SalesTaxRateID}</th>
 *     <td>Primary key for SalesTaxRate records.</td></tr>
 * <tr><th scope="row">{@code StateProvinceID}</th>
 *     <td>State, province, or country/region the sales tax applies to.</td></tr>
 * <tr><th scope="row">{@code TaxType}</th>
 *     <td>1 = Tax applied to retail transactions, 2 = Tax applied to wholesale transactions, 3 =
 *     Tax applied to all sales (retail and wholesale) transactions.</td></tr>
 * <tr><th scope="row">{@code TaxRate}</th><td>Tax rate amount.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>Tax rate description.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.SalesTaxRate}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code TaxRate}</th><td>{@code ((0.00))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedSalesTaxRate.TABLE_SCHEMA,
        table = MappedSalesTaxRate.TABLE_NAME,
        value = "Tax rate lookup table."
)
public abstract class MappedSalesTaxRate {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesTaxRate";

    // ------------------------------------------------------------------------------ SalesTaxRateID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_TAX_RATE_ID = "SalesTaxRateID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_TAX_RATE_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_TAX_RATE_ID = "salesTaxRateId";

    // ----------------------------------------------------------------------------- StateProvinceID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STATE_PROVINCE_ID = "StateProvinceID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STATE_PROVINCE_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STATE_PROVINCE_ID = "stateProvinceId";

    // ------------------------------------------------------------------------------------- TaxType

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_TYPE = "TaxType";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TAX_TYPE} attribute, as the
     * {@code CK_SalesTaxRate_TaxType} check constraint ({@code [TaxType]>=(1)}) requires. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MIN_TAX_TYPE = +1;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TAX_TYPE} attribute, as the
     * {@code CK_SalesTaxRate_TaxType} check constraint ({@code [TaxType]<=(3)}) requires. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MAX_TAX_TYPE = +3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_TYPE = "taxType";

    // ------------------------------------------------------------------------------------- TaxRate

    /**
     * The name of the {@code smallmoney, DEFAULT ((0.00)), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TAX_RATE = "TaxRate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_RATE = "taxRate";

    // ---------------------------------------------------------------------------------------- Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedSalesTaxRate() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesTaxRateId=" + salesTaxRateId
               + ",stateProvinceId=" + stateProvinceId
               + ",taxType=" + taxType
               + ",taxRate=" + taxRate
               + ",name=" + name
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedSalesTaxRate that)) {
            return false;
        }
        return getSalesTaxRateId() != null
               && getSalesTaxRateId().equals(that.getSalesTaxRateId());
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
        return MappedSalesTaxRate.class.hashCode();
    }

    // ------------------------------------------------------------------------------ salesTaxRateId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_TAX_RATE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_TAX_RATE_ID} attribute.
     */
    public Integer getSalesTaxRateId() {
        return salesTaxRateId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_TAX_RATE_ID} attribute with the
     * specified value.
     *
     * @param salesTaxRateId new value for {@value #ATTRIBUTE_NAME_SALES_TAX_RATE_ID} attribute.
     */
    protected void setSalesTaxRateId(final Integer salesTaxRateId) {
        this.salesTaxRateId = salesTaxRateId;
    }

    // ----------------------------------------------------------------------------- stateProvinceId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    protected Integer getStateProvinceId() {
        return stateProvinceId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param stateProvinceId new value for {@value #ATTRIBUTE_NAME_STATE_PROVINCE_ID} attribute.
     */
    protected void setStateProvinceId(final Integer stateProvinceId) {
        this.stateProvinceId = stateProvinceId;
    }

    // ------------------------------------------------------------------------------------- taxType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_TYPE} attribute.
     */
    public Integer getTaxType() {
        return taxType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_TYPE} attribute with the specified
     * value.
     *
     * @param taxType new value for {@value #ATTRIBUTE_NAME_TAX_TYPE} attribute.
     */
    public void setTaxType(final Integer taxType) {
        this.taxType = taxType;
    }

    // ------------------------------------------------------------------------------------- taxRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute with the specified
     * value.
     *
     * @param taxRate new value for {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    public void setTaxRate(final BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    // ---------------------------------------------------------------------------------------- name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setName(final String name) {
        this.name = name;
    }

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
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
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SALES_TAX_RATE_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Primary key for SalesTaxRate records.")
    private Integer salesTaxRateId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STATE_PROVINCE_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "State, province, or country/region the sales tax applies to.")
    private Integer stateProvinceId;

    // ---------------------------------------------------------------------------------------------
    // CK_SalesTaxRate_TaxType: ([TaxType]>=(1) AND [TaxType]<=(3))
    @Max(ATTRIBUTE_MAX_TAX_TYPE)
    @Min(ATTRIBUTE_MIN_TAX_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_TYPE, nullable = false, insertable = true, updatable = true,
            comment = "1 = Tax applied to retail transactions, 2 = Tax applied to wholesale "
                      + "transactions, 3 = Tax applied to all sales (retail and wholesale) "
                      + "transactions.")
    private Integer taxType;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(SqlServerConstants.SMALLMONEY_MAX)
    @DecimalMin(SqlServerConstants.SMALLMONEY_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_RATE, nullable = false, insertable = true, updatable = true, precision = SqlServerConstants.SMALLMONEY_PRECISION, scale = SqlServerConstants.SMALLMONEY_SCALE,
            comment = "Tax rate amount.")
    @MsDefault(name = "DF_SalesTaxRate_TaxRate", value = "((0.00))")
    private BigDecimal taxRate;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME,
            comment = "Tax rate description.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_SalesTaxRate_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_SalesTaxRate_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

