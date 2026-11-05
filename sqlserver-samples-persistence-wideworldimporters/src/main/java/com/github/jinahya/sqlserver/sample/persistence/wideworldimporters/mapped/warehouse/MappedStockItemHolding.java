package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse;

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
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockItemHoldings} table.
 * <p>
 * The {@value #COLUMN_NAME_LAST_EDITED_BY} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Warehouse.StockItemHoldings}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemID}</th>
 *     <td>ID of the stock item that this holding relates to (this table holds non-temporal columns
 *     for stock)</td></tr>
 * <tr><th scope="row">{@code QuantityOnHand}</th>
 *     <td>Quantity currently on hand (if tracked)</td></tr>
 * <tr><th scope="row">{@code BinLocation}</th>
 *     <td>Bin location (ie location of this stock item within the depot)</td></tr>
 * <tr><th scope="row">{@code LastStocktakeQuantity}</th>
 *     <td>Quantity at last stocktake (if tracked)</td></tr>
 * <tr><th scope="row">{@code LastCostPrice}</th>
 *     <td>Unit cost price the last time this stock item was purchased</td></tr>
 * <tr><th scope="row">{@code ReorderLevel}</th>
 *     <td>Quantity below which reordering should take place</td></tr>
 * <tr><th scope="row">{@code TargetStockLevel}</th><td>Typical quantity ordered</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Warehouse.StockItemHoldings}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code LastEditedWhen}</th><td>{@code (sysdatetime())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/sysdatetime-transact-sql">SYSDATETIME</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStockItemHolding.TABLE_SCHEMA,
        table = MappedStockItemHolding.TABLE_NAME,
        value = "Non-temporal attributes for stock items"
)
public abstract class MappedStockItemHolding {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockItemHoldings";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // ------------------------------------------------------------------------------ QuantityOnHand

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_ON_HAND = "QuantityOnHand";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_ON_HAND = "quantityOnHand";

    // --------------------------------------------------------------------------------- BinLocation

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIN_LOCATION = "BinLocation";

    /**
     * The length of the {@value #COLUMN_NAME_BIN_LOCATION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BIN_LOCATION = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIN_LOCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIN_LOCATION = "binLocation";

    // ----------------------------------------------------------------------- LastStocktakeQuantity

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_STOCKTAKE_QUANTITY = "LastStocktakeQuantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_STOCKTAKE_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_STOCKTAKE_QUANTITY = "lastStocktakeQuantity";

    // ------------------------------------------------------------------------------- LastCostPrice

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_COST_PRICE = "LastCostPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_LAST_COST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_LAST_COST_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_LAST_COST_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_LAST_COST_PRICE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LAST_COST_PRICE} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_LAST_COST_PRICE = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LAST_COST_PRICE} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_LAST_COST_PRICE = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_COST_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_COST_PRICE = "lastCostPrice";

    // -------------------------------------------------------------------------------- ReorderLevel

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REORDER_LEVEL = "ReorderLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REORDER_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REORDER_LEVEL = "reorderLevel";

    // ---------------------------------------------------------------------------- TargetStockLevel

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TARGET_STOCK_LEVEL = "TargetStockLevel";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TARGET_STOCK_LEVEL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TARGET_STOCK_LEVEL = "targetStockLevel";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------ LastEditedWhen

    /**
     * The name of the {@code datetime2, DEFAULT (sysdatetime()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_WHEN = "LastEditedWhen";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_WHEN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_WHEN = "lastEditedWhen";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStockItemHolding() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stockItemId=" + stockItemId
               + ",quantityOnHand=" + quantityOnHand
               + ",binLocation=" + binLocation
               + ",lastStocktakeQuantity=" + lastStocktakeQuantity
               + ",lastCostPrice=" + lastCostPrice
               + ",reorderLevel=" + reorderLevel
               + ",targetStockLevel=" + targetStockLevel
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
        if (!(obj instanceof MappedStockItemHolding that)) {
            return false;
        }
        return Objects.equals(getStockItemId(), that.getStockItemId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getStockItemId());
    }

    // --------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    public Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(final Integer stockItemId) {
        this.stockItemId = stockItemId;
    }

    // ------------------------------------------------------------------------------ quantityOnHand

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     */
    public Integer getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute with the specified value.
     *
     * @param quantityOnHand new value for {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     */
    public void setQuantityOnHand(final Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    // --------------------------------------------------------------------------------- binLocation

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIN_LOCATION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIN_LOCATION} attribute.
     */
    public String getBinLocation() {
        return binLocation;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIN_LOCATION} attribute with the specified value.
     *
     * @param binLocation new value for {@value #ATTRIBUTE_NAME_BIN_LOCATION} attribute.
     */
    public void setBinLocation(final String binLocation) {
        this.binLocation = binLocation;
    }

    // ----------------------------------------------------------------------- lastStocktakeQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_STOCKTAKE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_STOCKTAKE_QUANTITY} attribute.
     */
    public Integer getLastStocktakeQuantity() {
        return lastStocktakeQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_STOCKTAKE_QUANTITY} attribute with the specified value.
     *
     * @param lastStocktakeQuantity new value for {@value #ATTRIBUTE_NAME_LAST_STOCKTAKE_QUANTITY} attribute.
     */
    public void setLastStocktakeQuantity(final Integer lastStocktakeQuantity) {
        this.lastStocktakeQuantity = lastStocktakeQuantity;
    }

    // ------------------------------------------------------------------------------- lastCostPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_COST_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_COST_PRICE} attribute.
     */
    public BigDecimal getLastCostPrice() {
        return lastCostPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_COST_PRICE} attribute with the specified value.
     *
     * @param lastCostPrice new value for {@value #ATTRIBUTE_NAME_LAST_COST_PRICE} attribute.
     */
    public void setLastCostPrice(final BigDecimal lastCostPrice) {
        this.lastCostPrice = lastCostPrice;
    }

    // -------------------------------------------------------------------------------- reorderLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REORDER_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REORDER_LEVEL} attribute.
     */
    public Integer getReorderLevel() {
        return reorderLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REORDER_LEVEL} attribute with the specified value.
     *
     * @param reorderLevel new value for {@value #ATTRIBUTE_NAME_REORDER_LEVEL} attribute.
     */
    public void setReorderLevel(final Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    // ---------------------------------------------------------------------------- targetStockLevel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TARGET_STOCK_LEVEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TARGET_STOCK_LEVEL} attribute.
     */
    public Integer getTargetStockLevel() {
        return targetStockLevel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TARGET_STOCK_LEVEL} attribute with the specified value.
     *
     * @param targetStockLevel new value for {@value #ATTRIBUTE_NAME_TARGET_STOCK_LEVEL} attribute.
     */
    public void setTargetStockLevel(final Integer targetStockLevel) {
        this.targetStockLevel = targetStockLevel;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
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

    // ------------------------------------------------------------------------------ lastEditedWhen

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

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = true, updatable = false,
            comment = "ID of the stock item that this holding relates to (this table holds "
                      + "non-temporal columns for stock)")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_ON_HAND, nullable = false, insertable = true, updatable = true,
            comment = "Quantity currently on hand (if tracked)")
    private Integer quantityOnHand;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BIN_LOCATION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BIN_LOCATION, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_BIN_LOCATION,
            comment = "Bin location (ie location of this stock item within the depot)")
    private String binLocation;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_STOCKTAKE_QUANTITY, nullable = false, insertable = true, updatable = true,
            comment = "Quantity at last stocktake (if tracked)")
    private Integer lastStocktakeQuantity;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_LAST_COST_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_LAST_COST_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_COST_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_LAST_COST_PRICE, scale = COLUMN_SCALE_LAST_COST_PRICE,
            comment = "Unit cost price the last time this stock item was purchased")
    private BigDecimal lastCostPrice;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REORDER_LEVEL, nullable = false, insertable = true, updatable = true,
            comment = "Quantity below which reordering should take place")
    private Integer reorderLevel;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TARGET_STOCK_LEVEL, nullable = false, insertable = true, updatable = true,
            comment = "Typical quantity ordered")
    private Integer targetStockLevel;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (sysdatetime()) fills it on an INSERT; the date and time the record
    // was last updated, which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_WHEN, nullable = false, insertable = false, updatable = true)
    @MsDefault(name = "DF_Warehouse_StockItemHoldings_LastEditedWhen", value = "(sysdatetime())")
    private LocalDateTime lastEditedWhen;
}

