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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SpecialOffer} table.
 */
@MappedSuperclass
public abstract class MappedSpecialOffer {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SpecialOffer";

    // -------------------------------------------------------------------------------------------------- SpecialOfferID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIAL_OFFER_ID = "SpecialOfferID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIAL_OFFER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER_ID = "specialOfferId";

    // ----------------------------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ----------------------------------------------------------------------------------------------------- DiscountPct

    /**
     * The name of the {@code smallmoney, DEFAULT ((0.00)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DISCOUNT_PCT = "DiscountPct";

    /**
     * The minimum value of the {@value #COLUMN_NAME_DISCOUNT_PCT} column, as the {@code CK_SpecialOffer_DiscountPct}
     * check constraint ({@code [DiscountPct]>=(0.00)}) requires. The value is {@value}.
     */
    public static final String COLUMN_MIN_DISCOUNT_PCT = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DISCOUNT_PCT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISCOUNT_PCT = "discountPct";

    // ------------------------------------------------------------------------------------------------------------ Type

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TYPE = "Type";

    /**
     * The length of the {@value #COLUMN_NAME_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TYPE = "type";

    // -------------------------------------------------------------------------------------------------------- Category

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CATEGORY = "Category";

    /**
     * The length of the {@value #COLUMN_NAME_CATEGORY} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CATEGORY = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CATEGORY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CATEGORY = "category";

    // ------------------------------------------------------------------------------------------------------- StartDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_START_DATE = "StartDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_START_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_START_DATE = "startDate";

    // --------------------------------------------------------------------------------------------------------- EndDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_END_DATE = "EndDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_END_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_END_DATE = "endDate";

    // ---------------------------------------------------------------------------------------------------------- MinQty

    /**
     * The name of the {@code int, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIN_QTY = "MinQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_MIN_QTY} column, as the {@code CK_SpecialOffer_MinQty} check
     * constraint ({@code [MinQty]>=(0)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_MIN_QTY = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MIN_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MIN_QTY = Integer.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIN_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIN_QTY = "minQty";

    // ---------------------------------------------------------------------------------------------------------- MaxQty

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MAX_QTY = "MaxQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_MAX_QTY} column, as the {@code CK_SpecialOffer_MaxQty} check
     * constraint ({@code [MaxQty]>=(0)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_MAX_QTY = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MAX_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MAX_QTY = Integer.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAX_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAX_QTY = "maxQty";

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
    protected MappedSpecialOffer() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "specialOfferId=" + specialOfferId
               + ",description=" + description
               + ",discountPct=" + discountPct
               + ",type=" + type
               + ",category=" + category
               + ",startDate=" + startDate
               + ",endDate=" + endDate
               + ",minQty=" + minQty
               + ",maxQty=" + maxQty
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
        if (!(obj instanceof MappedSpecialOffer that)) {
            return false;
        }
        return Objects.equals(specialOfferId, that.specialOfferId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(specialOfferId);
    }

    // ------------------------------------------------------------------------------------------------------ VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_END_DATE} attribute is not before the
     * {@value #ATTRIBUTE_NAME_START_DATE} attribute, as the table's {@code CK_SpecialOffer_EndDate} constraint
     * requires.
     * {@snippet lang = "sql":
     * ([EndDate]>=[StartDate])
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_END_DATE} attribute is not before the
     *         {@value #ATTRIBUTE_NAME_START_DATE} attribute, or if either attribute is {@code null}; {@code false}
     *         otherwise.
     */
    @AssertTrue(message = "endDate must not be before startDate")
    protected boolean isEndDateValid() {
        if (endDate == null || startDate == null) {
            return true;
        }
        return !endDate.isBefore(startDate);
    }

    // -------------------------------------------------------------------------------------------------- specialOfferId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    public Integer getSpecialOfferId() {
        return specialOfferId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute with the specified value.
     *
     * @param specialOfferId new value for {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    protected void setSpecialOfferId(final Integer specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    // ----------------------------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // ----------------------------------------------------------------------------------------------------- discountPct

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PCT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PCT} attribute.
     */
    public BigDecimal getDiscountPct() {
        return discountPct;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DISCOUNT_PCT} attribute with the specified value.
     *
     * @param discountPct new value for {@value #ATTRIBUTE_NAME_DISCOUNT_PCT} attribute.
     */
    public void setDiscountPct(final BigDecimal discountPct) {
        this.discountPct = discountPct;
    }

    // ------------------------------------------------------------------------------------------------------------ type

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TYPE} attribute.
     */
    public String getType() {
        return type;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TYPE} attribute with the specified value.
     *
     * @param type new value for {@value #ATTRIBUTE_NAME_TYPE} attribute.
     */
    public void setType(final String type) {
        this.type = type;
    }

    // -------------------------------------------------------------------------------------------------------- category

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CATEGORY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CATEGORY} attribute.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CATEGORY} attribute with the specified value.
     *
     * @param category new value for {@value #ATTRIBUTE_NAME_CATEGORY} attribute.
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    // ------------------------------------------------------------------------------------------------------- startDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_START_DATE} attribute with the specified value.
     *
     * @param startDate new value for {@value #ATTRIBUTE_NAME_START_DATE} attribute.
     */
    public void setStartDate(final LocalDateTime startDate) {
        this.startDate = startDate;
    }

    // --------------------------------------------------------------------------------------------------------- endDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_END_DATE} attribute with the specified value.
     *
     * @param endDate new value for {@value #ATTRIBUTE_NAME_END_DATE} attribute.
     */
    public void setEndDate(final LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // ---------------------------------------------------------------------------------------------------------- minQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIN_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIN_QTY} attribute.
     */
    public Integer getMinQty() {
        return minQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_QTY} attribute with the specified value.
     *
     * @param minQty new value for {@value #ATTRIBUTE_NAME_MIN_QTY} attribute.
     */
    public void setMinQty(final Integer minQty) {
        this.minQty = minQty;
    }

    // ---------------------------------------------------------------------------------------------------------- maxQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAX_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAX_QTY} attribute.
     */
    public Integer getMaxQty() {
        return maxQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_QTY} attribute with the specified value.
     *
     * @param maxQty new value for {@value #ATTRIBUTE_NAME_MAX_QTY} attribute.
     */
    public void setMaxQty(final Integer maxQty) {
        this.maxQty = maxQty;
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
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_SPECIAL_OFFER_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer specialOfferId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DESCRIPTION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DESCRIPTION
    )
    private String description;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SpecialOffer_DiscountPct: ([DiscountPct]>=(0.00))
    @DecimalMin(COLUMN_MIN_DISCOUNT_PCT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DISCOUNT_PCT,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal discountPct;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TYPE
    )
    private String type;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_CATEGORY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CATEGORY,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_CATEGORY
    )
    private String category;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_START_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime startDate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_END_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime endDate;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SpecialOffer_MinQty: ([MinQty]>=(0))
    @Max(COLUMN_MAX_MIN_QTY)
    @Min(COLUMN_MIN_MIN_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MIN_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer minQty;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_SpecialOffer_MaxQty: ([MaxQty]>=(0))
    @Max(COLUMN_MAX_MAX_QTY)
    @Min(COLUMN_MIN_MAX_QTY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MAX_QTY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer maxQty;

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

