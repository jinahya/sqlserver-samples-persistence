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
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
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
    public static final String TABLE_SCHEMA = "Sales";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ----------------------------------------------------------------------------------------------------- TerritoryID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TERRITORY_ID = "TerritoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TERRITORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TERRITORY_ID = "territoryId";

    // ------------------------------------------------------------------------------------------------------ SalesQuota

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_QUOTA = "SalesQuota";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_QUOTA} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_QUOTA = "salesQuota";

    // ----------------------------------------------------------------------------------------------------------- Bonus

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BONUS = "Bonus";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BONUS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BONUS = "bonus";

    // --------------------------------------------------------------------------------------------------- CommissionPct

    /**
     * The name of the {@code smallmoney, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COMMISSION_PCT = "CommissionPct";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COMMISSION_PCT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COMMISSION_PCT = "commissionPct";

    // -------------------------------------------------------------------------------------------------------- SalesYTD

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_YTD = "SalesYTD";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_YTD} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_YTD = "salesYtd";

    // --------------------------------------------------------------------------------------------------- SalesLastYear

    /**
     * The name of the {@code money, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_LAST_YEAR = "SalesLastYear";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_LAST_YEAR} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_LAST_YEAR = "salesLastYear";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    @Nonnull
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(@Nonnull final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ----------------------------------------------------------------------------------------------------- territoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    @Nullable
    public Integer getTerritoryId() {
        return territoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param territoryId new value for {@value #ATTRIBUTE_NAME_TERRITORY_ID} attribute.
     */
    protected void setTerritoryId(@Nullable final Integer territoryId) {
        this.territoryId = territoryId;
    }

    // ------------------------------------------------------------------------------------------------------ salesQuota

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute.
     */
    @Nullable
    public BigDecimal getSalesQuota() {
        return salesQuota;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute with the specified value.
     *
     * @param salesQuota new value for {@value #ATTRIBUTE_NAME_SALES_QUOTA} attribute.
     */
    public void setSalesQuota(@Nullable final BigDecimal salesQuota) {
        this.salesQuota = salesQuota;
    }

    // ----------------------------------------------------------------------------------------------------------- bonus

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BONUS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BONUS} attribute.
     */
    @Nonnull
    public BigDecimal getBonus() {
        return bonus;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BONUS} attribute with the specified value.
     *
     * @param bonus new value for {@value #ATTRIBUTE_NAME_BONUS} attribute.
     */
    public void setBonus(@Nonnull final BigDecimal bonus) {
        this.bonus = bonus;
    }

    // --------------------------------------------------------------------------------------------------- commissionPct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    @Nonnull
    public BigDecimal getCommissionPct() {
        return commissionPct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute with the specified value.
     *
     * @param commissionPct new value for {@value #ATTRIBUTE_NAME_COMMISSION_PCT} attribute.
     */
    public void setCommissionPct(@Nonnull final BigDecimal commissionPct) {
        this.commissionPct = commissionPct;
    }

    // -------------------------------------------------------------------------------------------------------- salesYtd

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     */
    @Nonnull
    public BigDecimal getSalesYtd() {
        return salesYtd;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_YTD} attribute with the specified value.
     *
     * @param salesYtd new value for {@value #ATTRIBUTE_NAME_SALES_YTD} attribute.
     */
    public void setSalesYtd(@Nonnull final BigDecimal salesYtd) {
        this.salesYtd = salesYtd;
    }

    // --------------------------------------------------------------------------------------------------- salesLastYear

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     */
    @Nonnull
    public BigDecimal getSalesLastYear() {
        return salesLastYear;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute with the specified value.
     *
     * @param salesLastYear new value for {@value #ATTRIBUTE_NAME_SALES_LAST_YEAR} attribute.
     */
    public void setSalesLastYear(@Nonnull final BigDecimal salesLastYear) {
        this.salesLastYear = salesLastYear;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    @Nonnull
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(@Nonnull final UUID rowguid) {
        this.rowguid = rowguid;
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
    @Nonnull
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
    @Nullable
    @Column(name = COLUMN_NAME_TERRITORY_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer territoryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_SALES_QUOTA,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesQuota;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_BONUS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal bonus;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_COMMISSION_PCT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal commissionPct;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SALES_YTD,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesYtd;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SALES_LAST_YEAR,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesLastYear;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

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

