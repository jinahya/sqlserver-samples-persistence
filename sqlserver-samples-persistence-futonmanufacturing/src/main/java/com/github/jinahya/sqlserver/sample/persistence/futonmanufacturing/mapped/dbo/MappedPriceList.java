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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.PriceList} table.
 * <p>
 * The {@value #COLUMN_NAME_SALES_CHANNEL_ID} column is mapped read-only, as a foreign key; the extending entity owns
 * the writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.PriceList}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code IsActive}</th><td>{@code ((1))}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedPriceList {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PriceList";

    // --------------------------------------------------------------------------------- PriceListID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRICE_LIST_ID = "PriceListID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRICE_LIST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRICE_LIST_ID = "priceListId";

    // ------------------------------------------------------------------------------- PriceListCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRICE_LIST_CODE = "PriceListCode";

    /**
     * The length of the {@value #COLUMN_NAME_PRICE_LIST_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRICE_LIST_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRICE_LIST_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRICE_LIST_CODE = "priceListCode";

    // ------------------------------------------------------------------------------- PriceListName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRICE_LIST_NAME = "PriceListName";

    /**
     * The length of the {@value #COLUMN_NAME_PRICE_LIST_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRICE_LIST_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRICE_LIST_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRICE_LIST_NAME = "priceListName";

    // ------------------------------------------------------------------------------ SalesChannelID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_CHANNEL_ID = "SalesChannelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_CHANNEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_CHANNEL_ID = "salesChannelId";

    // ------------------------------------------------------------------------------- EffectiveDate

    /**
     * The name of the {@code date, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EFFECTIVE_DATE = "EffectiveDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EFFECTIVE_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EFFECTIVE_DATE = "effectiveDate";

    // ------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code date} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ------------------------------------------------------------------------------------ IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPriceList() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "priceListId=" + priceListId
               + ",priceListCode=" + priceListCode
               + ",priceListName=" + priceListName
               + ",salesChannelId=" + salesChannelId
               + ",effectiveDate=" + effectiveDate
               + ",endDate=" + endDate
               + ",isActive=" + isActive
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
        if (!(obj instanceof MappedPriceList that)) {
            return false;
        }
        return Objects.equals(priceListId, that.priceListId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(priceListId);
    }

    // --------------------------------------------------------------------------------- priceListId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute.
     */
    public Integer getPriceListId() {
        return priceListId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute with the specified value.
     *
     * @param priceListId new value for {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute.
     */
    protected void setPriceListId(final Integer priceListId) {
        this.priceListId = priceListId;
    }

    // ------------------------------------------------------------------------------- priceListCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_CODE} attribute.
     */
    public String getPriceListCode() {
        return priceListCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_CODE} attribute with the specified value.
     *
     * @param priceListCode new value for {@value #ATTRIBUTE_NAME_PRICE_LIST_CODE} attribute.
     */
    public void setPriceListCode(final String priceListCode) {
        this.priceListCode = priceListCode;
    }

    // ------------------------------------------------------------------------------- priceListName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_NAME} attribute.
     */
    public String getPriceListName() {
        return priceListName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_NAME} attribute with the specified value.
     *
     * @param priceListName new value for {@value #ATTRIBUTE_NAME_PRICE_LIST_NAME} attribute.
     */
    public void setPriceListName(final String priceListName) {
        this.priceListName = priceListName;
    }

    // ------------------------------------------------------------------------------ salesChannelId

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

    // ------------------------------------------------------------------------------- effectiveDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute.
     */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute with the specified value.
     *
     * @param effectiveDate new value for {@value #ATTRIBUTE_NAME_EFFECTIVE_DATE} attribute.
     */
    public void setEffectiveDate(final LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    // ------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    // ------------------------------------------------------------------------------------ isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRICE_LIST_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer priceListId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRICE_LIST_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRICE_LIST_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRICE_LIST_CODE
    )
    private String priceListCode;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PRICE_LIST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRICE_LIST_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRICE_LIST_NAME
    )
    private String priceListName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SALES_CHANNEL_ID,
            nullable = true,
            insertable = false,
            updatable = false
    )
    private Integer salesChannelId;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_EFFECTIVE_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDate effectiveDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDate endDate;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    @MsDefault("((1))")
    private Boolean isActive;
}

