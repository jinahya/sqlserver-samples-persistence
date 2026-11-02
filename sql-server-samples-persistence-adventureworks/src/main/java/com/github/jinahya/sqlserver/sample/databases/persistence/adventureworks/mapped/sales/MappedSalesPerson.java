package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.sales;

/*-
 * #%L
 * sql-server-samples-persistence-adventureworks
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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesPerson} table.
 * <p>
 * The {@value #COLUMN_NAME_TERRITORY_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSalesPerson {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesPerson";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ----------------------------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // ------------------------------------------------------------------------------------------------------ SalesQuota

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_QUOTA = "SalesQuota";

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_SALES_QUOTA} column, as the
     * {@code CK_SalesPerson_SalesQuota} check constraint ({@code [SalesQuota]>(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_SALES_QUOTA_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_QUOTA} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_QUOTA = "salesQuota";

    // ----------------------------------------------------------------------------------------------------------- Bonus

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BONUS = "Bonus";

    /**
     * The minimum value of the {@value #COLUMN_NAME_BONUS} column, as the {@code CK_SalesPerson_Bonus} check constraint
     * ({@code [Bonus]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_BONUS = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BONUS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BONUS = "bonus";

    // --------------------------------------------------------------------------------------------------- CommissionPct

    /**
     * The name of the {@code smallmoney, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMISSION_PCT = "CommissionPct";

    /**
     * The minimum value of the {@value #COLUMN_NAME_COMMISSION_PCT} column, as the {@code CK_SalesPerson_CommissionPct}
     * check constraint ({@code [CommissionPct]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_COMMISSION_PCT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMISSION_PCT = "commissionPct";

    // -------------------------------------------------------------------------------------------------------- SalesYTD

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_YTD = "SalesYTD";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SALES_YTD} column, as the {@code CK_SalesPerson_SalesYTD} check
     * constraint ({@code [SalesYTD]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_SALES_YTD = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_YTD} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_YTD = "salesYtd";

    // --------------------------------------------------------------------------------------------------- SalesLastYear

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_LAST_YEAR = "SalesLastYear";

    /**
     * The minimum value of the {@value #COLUMN_NAME_SALES_LAST_YEAR} column, as the
     * {@code CK_SalesPerson_SalesLastYear} check constraint ({@code [SalesLastYear]>=(0.00)}) requires. The value is
     * {@value}.
     */
    public static final String COLUMN_MIN_SALES_LAST_YEAR = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_LAST_YEAR} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_LAST_YEAR = "salesLastYear";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSalesPerson() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",territoryId=" + territoryId
               + ",salesQuota=" + salesQuota
               + ",bonus=" + bonus
               + ",commissionPct=" + commissionPct
               + ",salesYtd=" + salesYtd
               + ",salesLastYear=" + salesLastYear
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedSalesPerson that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
    }

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ----------------------------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ------------------------------------------------------------------------------------------------------ salesQuota

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute.
     */
    public BigDecimal getSalesQuota() {
        return salesQuota;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute with the specified value.
     *
     * @param salesQuota new value for {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute.
     */
    public void setSalesQuota(final BigDecimal salesQuota) {
        this.salesQuota = salesQuota;
    }

    // ----------------------------------------------------------------------------------------------------------- bonus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BONUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BONUS} attribute.
     */
    public BigDecimal getBonus() {
        return bonus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BONUS} attribute with the specified value.
     *
     * @param bonus new value for {@value #ATTRIBUTE_NAME_BONUS} attribute.
     */
    public void setBonus(final BigDecimal bonus) {
        this.bonus = bonus;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute with the specified value.
     *
     * @param commissionPct new value for {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    public void setCommissionPct(final BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    // -------------------------------------------------------------------------------------------------------- salesYtd

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     */
    public BigDecimal getSalesYtd() {
        return salesYtd;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute with the specified value.
     *
     * @param salesYtd new value for {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     */
    public void setSalesYtd(final BigDecimal salesYtd) {
        this.salesYtd = salesYtd;
    }

    // --------------------------------------------------------------------------------------------------- salesLastYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     */
    public BigDecimal getSalesLastYear() {
        return salesLastYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute with the specified value.
     *
     * @param salesLastYear new value for {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     */
    public void setSalesLastYear(final BigDecimal salesLastYear) {
        this.salesLastYear = salesLastYear;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

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

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer territoryId;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesPerson_SalesQuota: ([SalesQuota]>(0.00))
    @DecimalMin(value = COLUMN_MIN_SALES_QUOTA_EXCLUSIVE, inclusive = false)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_QUOTA,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesQuota;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesPerson_Bonus: ([Bonus]>=(0.00))
    @DecimalMin(COLUMN_MIN_BONUS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BONUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal bonus;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesPerson_CommissionPct: ([CommissionPct]>=(0.00))
    @DecimalMin(COLUMN_MIN_COMMISSION_PCT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_COMMISSION_PCT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal commissionPct;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesPerson_SalesYTD: ([SalesYTD]>=(0.00))
    @DecimalMin(COLUMN_MIN_SALES_YTD)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_YTD,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesYtd;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesPerson_SalesLastYear: ([SalesLastYear]>=(0.00))
    @DecimalMin(COLUMN_MIN_SALES_LAST_YEAR)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_LAST_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesLastYear;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

