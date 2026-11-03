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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;

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

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.PriceListDetail} table.
 * <p>
 * The {@value #COLUMN_NAME_PRICE_LIST_ID}, {@value #COLUMN_NAME_ITEM_ID} columns are mapped read-only, as a foreign
 * key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.PriceListDetail}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code MinimumQuantity}</th><td>{@code ((1))}</td></tr>
 * </tbody>
 * </table>
 */
@MappedSuperclass
public abstract class MappedPriceListDetail {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PriceListDetail";

    // --------------------------------------------------------------------------- PriceListDetailID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRICE_LIST_DETAIL_ID = "PriceListDetailID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRICE_LIST_DETAIL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRICE_LIST_DETAIL_ID = "priceListDetailId";

    // --------------------------------------------------------------------------------- PriceListID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRICE_LIST_ID = "PriceListID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRICE_LIST_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRICE_LIST_ID = "priceListId";

    // -------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // ----------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, the minimum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE = "-99999999999999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, the maximum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE = "99999999999999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ----------------------------------------------------------------------------- MinimumQuantity

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((1))} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MINIMUM_QUANTITY = "MinimumQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_MINIMUM_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_MINIMUM_QUANTITY = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_MINIMUM_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_MINIMUM_QUANTITY = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_MINIMUM_QUANTITY} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_MINIMUM_QUANTITY = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_MINIMUM_QUANTITY} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_MINIMUM_QUANTITY = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MINIMUM_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MINIMUM_QUANTITY = "minimumQuantity";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPriceListDetail() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "priceListDetailId=" + priceListDetailId
               + ",priceListId=" + priceListId
               + ",itemId=" + itemId
               + ",unitPrice=" + unitPrice
               + ",minimumQuantity=" + minimumQuantity
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
        if (!(obj instanceof MappedPriceListDetail that)) {
            return false;
        }
        return Objects.equals(priceListDetailId, that.priceListDetailId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(priceListDetailId);
    }

    // --------------------------------------------------------------------------- priceListDetailId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_DETAIL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_DETAIL_ID} attribute.
     */
    public Integer getPriceListDetailId() {
        return priceListDetailId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_DETAIL_ID} attribute with the specified value.
     *
     * @param priceListDetailId new value for {@value #ATTRIBUTE_NAME_PRICE_LIST_DETAIL_ID} attribute.
     */
    protected void setPriceListDetailId(final Integer priceListDetailId) {
        this.priceListDetailId = priceListDetailId;
    }

    // --------------------------------------------------------------------------------- priceListId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute.
     */
    protected Integer getPriceListId() {
        return priceListId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param priceListId new value for {@value #ATTRIBUTE_NAME_PRICE_LIST_ID} attribute.
     */
    protected void setPriceListId(final Integer priceListId) {
        this.priceListId = priceListId;
    }

    // -------------------------------------------------------------------------------------- itemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected Integer getItemId() {
        return itemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param itemId new value for {@value #ATTRIBUTE_NAME_ITEM_ID} attribute.
     */
    protected void setItemId(final Integer itemId) {
        this.itemId = itemId;
    }

    // ----------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    public void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ----------------------------------------------------------------------------- minimumQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MINIMUM_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MINIMUM_QUANTITY} attribute.
     */
    public BigDecimal getMinimumQuantity() {
        return minimumQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MINIMUM_QUANTITY} attribute with the specified value.
     *
     * @param minimumQuantity new value for {@value #ATTRIBUTE_NAME_MINIMUM_QUANTITY} attribute.
     */
    public void setMinimumQuantity(final BigDecimal minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRICE_LIST_DETAIL_ID, nullable = false, insertable = true, updatable = false)
    private Integer priceListDetailId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRICE_LIST_ID, nullable = false, insertable = false, updatable = false)
    private Integer priceListId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_MINIMUM_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_MINIMUM_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MINIMUM_QUANTITY, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_MINIMUM_QUANTITY, scale = COLUMN_SCALE_MINIMUM_QUANTITY)
    @MsDefault("((1))")
    private BigDecimal minimumQuantity;
}

