package com.github.jinahya.sqlserver.sample.databases.persistence.adventureworks.mapped.purchasing;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.ProductVendor} table.
 * <p>
 * The {@value #COLUMN_NAME_UNIT_MEASURE_CODE} column is mapped read-only, as a foreign key; the extending entity owns
 * the writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedProductVendor {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductVendor";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ------------------------------------------------------------------------------------------------- AverageLeadTime

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVERAGE_LEAD_TIME = "AverageLeadTime";

    /**
     * The minimum value of the {@value #COLUMN_NAME_AVERAGE_LEAD_TIME} column, as the
     * {@code CK_ProductVendor_AverageLeadTime} check constraint ({@code [AverageLeadTime]>=(1)}) requires. The value is
     * {@value}.
     */
    public static final int COLUMN_MIN_AVERAGE_LEAD_TIME = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_AVERAGE_LEAD_TIME} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_AVERAGE_LEAD_TIME = Integer.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVERAGE_LEAD_TIME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVERAGE_LEAD_TIME = "averageLeadTime";

    // --------------------------------------------------------------------------------------------------- StandardPrice

    /**
     * The name of the {@code money, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_PRICE = "StandardPrice";

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_STANDARD_PRICE} column, as the
     * {@code CK_ProductVendor_StandardPrice} check constraint ({@code [StandardPrice]>(0.00)}) requires. The value is
     * {@value}.
     */
    public static final String COLUMN_MIN_STANDARD_PRICE_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_PRICE = "standardPrice";

    // ------------------------------------------------------------------------------------------------- LastReceiptCost

    /**
     * The name of the {@code money} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_RECEIPT_COST = "LastReceiptCost";

    /**
     * The exclusive minimum value of the {@value #COLUMN_NAME_LAST_RECEIPT_COST} column, as the
     * {@code CK_ProductVendor_LastReceiptCost} check constraint ({@code [LastReceiptCost]>(0.00)}) requires. The value
     * is {@value}.
     */
    public static final String COLUMN_MIN_LAST_RECEIPT_COST_EXCLUSIVE = "0.00";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_RECEIPT_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_RECEIPT_COST = "lastReceiptCost";

    // ------------------------------------------------------------------------------------------------- LastReceiptDate

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_RECEIPT_DATE = "LastReceiptDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_RECEIPT_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_RECEIPT_DATE = "lastReceiptDate";

    // ----------------------------------------------------------------------------------------------------- MinOrderQty

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MIN_ORDER_QTY = "MinOrderQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_MIN_ORDER_QTY} column, as the {@code CK_ProductVendor_MinOrderQty}
     * check constraint ({@code [MinOrderQty]>=(1)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_MIN_ORDER_QTY = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MIN_ORDER_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MIN_ORDER_QTY = Integer.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MIN_ORDER_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MIN_ORDER_QTY = "minOrderQty";

    // ----------------------------------------------------------------------------------------------------- MaxOrderQty

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MAX_ORDER_QTY = "MaxOrderQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_MAX_ORDER_QTY} column, as the {@code CK_ProductVendor_MaxOrderQty}
     * check constraint ({@code [MaxOrderQty]>=(1)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_MAX_ORDER_QTY = +1;

    /**
     * The maximum value of the {@value #COLUMN_NAME_MAX_ORDER_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_MAX_ORDER_QTY = Integer.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MAX_ORDER_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MAX_ORDER_QTY = "maxOrderQty";

    // ------------------------------------------------------------------------------------------------------ OnOrderQty

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ON_ORDER_QTY = "OnOrderQty";

    /**
     * The minimum value of the {@value #COLUMN_NAME_ON_ORDER_QTY} column, as the {@code CK_ProductVendor_OnOrderQty}
     * check constraint ({@code [OnOrderQty]>=(0)}) requires. The value is {@value}.
     */
    public static final int COLUMN_MIN_ON_ORDER_QTY = 0;

    /**
     * The maximum value of the {@value #COLUMN_NAME_ON_ORDER_QTY} column. The value is {@value}.
     */
    public static final int COLUMN_MAX_ON_ORDER_QTY = Integer.MAX_VALUE;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ON_ORDER_QTY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ON_ORDER_QTY = "onOrderQty";

    // ------------------------------------------------------------------------------------------------- UnitMeasureCode

    /**
     * The name of the {@code nchar(3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_MEASURE_CODE = "UnitMeasureCode";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_MEASURE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_MEASURE_CODE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_MEASURE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_MEASURE_CODE = "unitMeasureCode";

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
    protected MappedProductVendor() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",businessEntityId=" + businessEntityId
               + ",averageLeadTime=" + averageLeadTime
               + ",standardPrice=" + standardPrice
               + ",lastReceiptCost=" + lastReceiptCost
               + ",lastReceiptDate=" + lastReceiptDate
               + ",minOrderQty=" + minOrderQty
               + ",maxOrderQty=" + maxOrderQty
               + ",onOrderQty=" + onOrderQty
               + ",unitMeasureCode=" + unitMeasureCode
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
        if (!(obj instanceof MappedProductVendor that)) {
            return false;
        }
        return Objects.equals(productId, that.productId) &&
               Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productId, businessEntityId);
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public void setProductId(final Integer productId) {
        this.productId = productId;
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

    // ------------------------------------------------------------------------------------------------- averageLeadTime

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVERAGE_LEAD_TIME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVERAGE_LEAD_TIME} attribute.
     */
    public Integer getAverageLeadTime() {
        return averageLeadTime;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVERAGE_LEAD_TIME} attribute with the specified value.
     *
     * @param averageLeadTime new value for {@value #ATTRIBUTE_NAME_AVERAGE_LEAD_TIME} attribute.
     */
    public void setAverageLeadTime(final Integer averageLeadTime) {
        this.averageLeadTime = averageLeadTime;
    }

    // --------------------------------------------------------------------------------------------------- standardPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_PRICE} attribute.
     */
    public BigDecimal getStandardPrice() {
        return standardPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_PRICE} attribute with the specified value.
     *
     * @param standardPrice new value for {@value #ATTRIBUTE_NAME_STANDARD_PRICE} attribute.
     */
    public void setStandardPrice(final BigDecimal standardPrice) {
        this.standardPrice = standardPrice;
    }

    // ------------------------------------------------------------------------------------------------- lastReceiptCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_COST} attribute.
     */
    public BigDecimal getLastReceiptCost() {
        return lastReceiptCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_COST} attribute with the specified value.
     *
     * @param lastReceiptCost new value for {@value #ATTRIBUTE_NAME_LAST_RECEIPT_COST} attribute.
     */
    public void setLastReceiptCost(final BigDecimal lastReceiptCost) {
        this.lastReceiptCost = lastReceiptCost;
    }

    // ------------------------------------------------------------------------------------------------- lastReceiptDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute.
     */
    public LocalDateTime getLastReceiptDate() {
        return lastReceiptDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute with the specified value.
     *
     * @param lastReceiptDate new value for {@value #ATTRIBUTE_NAME_LAST_RECEIPT_DATE} attribute.
     */
    public void setLastReceiptDate(final LocalDateTime lastReceiptDate) {
        this.lastReceiptDate = lastReceiptDate;
    }

    // ----------------------------------------------------------------------------------------------------- minOrderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MIN_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MIN_ORDER_QTY} attribute.
     */
    public Integer getMinOrderQty() {
        return minOrderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MIN_ORDER_QTY} attribute with the specified value.
     *
     * @param minOrderQty new value for {@value #ATTRIBUTE_NAME_MIN_ORDER_QTY} attribute.
     */
    public void setMinOrderQty(final Integer minOrderQty) {
        this.minOrderQty = minOrderQty;
    }

    // ----------------------------------------------------------------------------------------------------- maxOrderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MAX_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MAX_ORDER_QTY} attribute.
     */
    public Integer getMaxOrderQty() {
        return maxOrderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MAX_ORDER_QTY} attribute with the specified value.
     *
     * @param maxOrderQty new value for {@value #ATTRIBUTE_NAME_MAX_ORDER_QTY} attribute.
     */
    public void setMaxOrderQty(final Integer maxOrderQty) {
        this.maxOrderQty = maxOrderQty;
    }

    // ------------------------------------------------------------------------------------------------------ onOrderQty

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ON_ORDER_QTY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ON_ORDER_QTY} attribute.
     */
    public Integer getOnOrderQty() {
        return onOrderQty;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ON_ORDER_QTY} attribute with the specified value.
     *
     * @param onOrderQty new value for {@value #ATTRIBUTE_NAME_ON_ORDER_QTY} attribute.
     */
    public void setOnOrderQty(final Integer onOrderQty) {
        this.onOrderQty = onOrderQty;
    }

    // ------------------------------------------------------------------------------------------------- unitMeasureCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute.
     */
    public String getUnitMeasureCode() {
        return unitMeasureCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param unitMeasureCode new value for {@value #ATTRIBUTE_NAME_UNIT_MEASURE_CODE} attribute.
     */
    protected void setUnitMeasureCode(final String unitMeasureCode) {
        this.unitMeasureCode = unitMeasureCode;
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
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productId;

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
    // CK_ProductVendor_AverageLeadTime: ([AverageLeadTime]>=(1))
    @Max(COLUMN_MAX_AVERAGE_LEAD_TIME)
    @Min(COLUMN_MIN_AVERAGE_LEAD_TIME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AVERAGE_LEAD_TIME,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer averageLeadTime;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_ProductVendor_StandardPrice: ([StandardPrice]>(0.00))
    @DecimalMin(value = COLUMN_MIN_STANDARD_PRICE_EXCLUSIVE, inclusive = false)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STANDARD_PRICE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private BigDecimal standardPrice;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_ProductVendor_LastReceiptCost: ([LastReceiptCost]>(0.00))
    @DecimalMin(value = COLUMN_MIN_LAST_RECEIPT_COST_EXCLUSIVE, inclusive = false)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_RECEIPT_COST,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private BigDecimal lastReceiptCost;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_RECEIPT_DATE,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime lastReceiptDate;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_ProductVendor_MinOrderQty: ([MinOrderQty]>=(1))
    @Max(COLUMN_MAX_MIN_ORDER_QTY)
    @Min(COLUMN_MIN_MIN_ORDER_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MIN_ORDER_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer minOrderQty;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_ProductVendor_MaxOrderQty: ([MaxOrderQty]>=(1))
    @Max(COLUMN_MAX_MAX_ORDER_QTY)
    @Min(COLUMN_MIN_MAX_ORDER_QTY)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MAX_ORDER_QTY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer maxOrderQty;

    // -----------------------------------------------------------------------------------------------------------------
    // CK_ProductVendor_OnOrderQty: ([OnOrderQty]>=(0))
    @Max(COLUMN_MAX_ON_ORDER_QTY)
    @Min(COLUMN_MIN_ON_ORDER_QTY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ON_ORDER_QTY,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer onOrderQty;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_MEASURE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_MEASURE_CODE,
            nullable = false,
            insertable = false,
            updatable = false,
            length = COLUMN_LENGTH_UNIT_MEASURE_CODE
    )
    private String unitMeasureCode;

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

