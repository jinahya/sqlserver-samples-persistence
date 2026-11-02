package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.sales;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.OrderLines} table.
 * <p>
 * The {@value #COLUMN_NAME_ORDER_ID}, {@value #COLUMN_NAME_STOCK_ITEM_ID}, {@value #COLUMN_NAME_PACKAGE_TYPE_ID},
 * {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedOrderLine {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "OrderLines";

    // ----------------------------------------------------------------------------------------------------- OrderLineID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.OrderLineID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_LINE_ID = "OrderLineID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_LINE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_LINE_ID = "orderLineId";

    // --------------------------------------------------------------------------------------------------------- OrderID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ORDER_ID = "OrderID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ORDER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ORDER_ID = "orderId";

    // ----------------------------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // ----------------------------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // --------------------------------------------------------------------------------------------------- PackageTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_ID = "PackageTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_ID = "packageTypeId";

    // -------------------------------------------------------------------------------------------------------- Quantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ------------------------------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PRICE = "UnitPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_UNIT_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_UNIT_PRICE = 2;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // --------------------------------------------------------------------------------------------------------- TaxRate

    /**
     * The name of the {@code decimal(18, 3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAX_RATE = "TaxRate";

    /**
     * The precision of the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TAX_RATE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TAX_RATE = 3;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_RATE = "taxRate";

    // -------------------------------------------------------------------------------------------------- PickedQuantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICKED_QUANTITY = "PickedQuantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICKED_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKED_QUANTITY = "pickedQuantity";

    // -------------------------------------------------------------------------------------------- PickingCompletedWhen

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PICKING_COMPLETED_WHEN = "PickingCompletedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PICKING_COMPLETED_WHEN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN = "pickingCompletedWhen";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // -------------------------------------------------------------------------------------------------- LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedOrderLine() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "orderLineId=" + orderLineId
               + ",orderId=" + orderId
               + ",stockItemId=" + stockItemId
               + ",description=" + description
               + ",packageTypeId=" + packageTypeId
               + ",quantity=" + quantity
               + ",unitPrice=" + unitPrice
               + ",taxRate=" + taxRate
               + ",pickedQuantity=" + pickedQuantity
               + ",pickingCompletedWhen=" + pickingCompletedWhen
               + ",lastEditedBy=" + lastEditedBy
               + ",lastEditedWhen=" + lastEditedWhen
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
        if (!(obj instanceof MappedOrderLine that)) {
            return false;
        }
        return Objects.equals(orderLineId, that.orderLineId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(orderLineId);
    }

    // ----------------------------------------------------------------------------------------------------- orderLineId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_LINE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_LINE_ID} attribute.
     */
    public Integer getOrderLineId() {
        return orderLineId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_LINE_ID} attribute with the specified value.
     *
     * @param orderLineId new value for {@value #ATTRIBUTE_NAME_ORDER_LINE_ID} attribute.
     */
    protected void setOrderLineId(final Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    // --------------------------------------------------------------------------------------------------------- orderId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ORDER_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param orderId new value for {@value #ATTRIBUTE_NAME_ORDER_ID} attribute.
     */
    protected void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    // ----------------------------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    public Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(final Integer stockItemId) {
        this.stockItemId = stockItemId;
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

    // --------------------------------------------------------------------------------------------------- packageTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    public Integer getPackageTypeId() {
        return packageTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param packageTypeId new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected void setPackageTypeId(final Integer packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

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

    // --------------------------------------------------------------------------------------------------------- taxRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute with the specified value.
     *
     * @param taxRate new value for {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    public void setTaxRate(final BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    // -------------------------------------------------------------------------------------------------- pickedQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICKED_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICKED_QUANTITY} attribute.
     */
    public Integer getPickedQuantity() {
        return pickedQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICKED_QUANTITY} attribute with the specified value.
     *
     * @param pickedQuantity new value for {@value #ATTRIBUTE_NAME_PICKED_QUANTITY} attribute.
     */
    public void setPickedQuantity(final Integer pickedQuantity) {
        this.pickedQuantity = pickedQuantity;
    }

    // -------------------------------------------------------------------------------------------- pickingCompletedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     */
    public LocalDateTime getPickingCompletedWhen() {
        return pickingCompletedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute with the specified value.
     *
     * @param pickingCompletedWhen new value for {@value #ATTRIBUTE_NAME_PICKING_COMPLETED_WHEN} attribute.
     */
    public void setPickingCompletedWhen(final LocalDateTime pickingCompletedWhen) {
        this.pickingCompletedWhen = pickingCompletedWhen;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // -------------------------------------------------------------------------------------------------- lastEditedWhen

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public LocalDateTime getLastEditedWhen() {
        return lastEditedWhen;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute with the specified value.
     *
     * @param lastEditedWhen new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_WHEN} attribute.
     */
    public void setLastEditedWhen(final LocalDateTime lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.OrderLineID", schema = "Sequences",
                       sequenceName = "OrderLineID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.OrderLineID")
    @Column(name = COLUMN_NAME_ORDER_LINE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer orderLineId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ORDER_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer orderId;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer stockItemId;

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
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer packageTypeId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Digits(integer = COLUMN_PRECISION_TAX_RATE - COLUMN_SCALE_TAX_RATE, fraction = COLUMN_SCALE_TAX_RATE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_RATE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TAX_RATE,
            scale = COLUMN_SCALE_TAX_RATE
    )
    private BigDecimal taxRate;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PICKED_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer pickedQuantity;

    // -----------------------------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PICKING_COMPLETED_WHEN,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private LocalDateTime pickingCompletedWhen;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime lastEditedWhen;
}

