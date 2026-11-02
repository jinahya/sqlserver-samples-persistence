package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.sales;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SalesPersonQuotaHistory} table.
 */
@MappedSuperclass
public abstract class MappedSalesPersonQuotaHistory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesPersonQuotaHistory";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------------------------------- QuotaDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUOTA_DATE = "QuotaDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUOTA_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUOTA_DATE = "quotaDate";

    // ------------------------------------------------------------------------------------------------------ SalesQuota

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_QUOTA = "SalesQuota";

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_SALES_QUOTA} column, as the
     * {@code CK_SalesPersonQuotaHistory_SalesQuota} check constraint ({@code [SalesQuota]>(0.00)}) requires. The value
     * is {@value}.
     */
    public static final String COLUMN_MIN_SALES_QUOTA_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_QUOTA} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_QUOTA = "salesQuota";

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
    protected MappedSalesPersonQuotaHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",quotaDate=" + quotaDate
               + ",salesQuota=" + salesQuota
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
        if (!(obj instanceof MappedSalesPersonQuotaHistory that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId) &&
               Objects.equals(quotaDate, that.quotaDate);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId, quotaDate);
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

    // ------------------------------------------------------------------------------------------------------- quotaDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUOTA_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUOTA_DATE} attribute.
     */
    public LocalDateTime getQuotaDate() {
        return quotaDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUOTA_DATE} attribute with the specified value.
     *
     * @param quotaDate new value for {@value #ATTRIBUTE_NAME_QUOTA_DATE} attribute.
     */
    public void setQuotaDate(final LocalDateTime quotaDate) {
        this.quotaDate = quotaDate;
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
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_QUOTA_DATE,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private LocalDateTime quotaDate;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SalesPersonQuotaHistory_SalesQuota: ([SalesQuota]>(0.00))
    @DecimalMin(value = COLUMN_MIN_SALES_QUOTA_EXCLUSIVE, inclusive = false)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SALES_QUOTA,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal salesQuota;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
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

