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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
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
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.Inventory} table.
 * <p>
 * The {@value #COLUMN_NAME_ITEM_ID}, {@value #COLUMN_NAME_WAREHOUSE_ID} columns are mapped read-only, as a foreign key;
 * the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.Inventory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code QuantityOnHand}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code QuantityAllocated}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code LastUpdated}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code dbo.Inventory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code QuantityAvailable}</th>
 *     <td>{@code ([QuantityOnHand]-[QuantityAllocated])}</td><td>{@code true}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
public abstract class MappedInventory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Inventory";

    // --------------------------------------------------------------------------------- InventoryID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_ID = "InventoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVENTORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVENTORY_ID = "inventoryId";

    // -------------------------------------------------------------------------------------- ItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_ID = "ItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_ID = "itemId";

    // --------------------------------------------------------------------------------- WarehouseID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_ID = "WarehouseID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_ID = "warehouseId";

    // ------------------------------------------------------------------------------ QuantityOnHand

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_ON_HAND = "QuantityOnHand";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_ON_HAND = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_ON_HAND = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_ON_HAND = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_ON_HAND = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_ON_HAND} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_ON_HAND = "quantityOnHand";

    // --------------------------------------------------------------------------- QuantityAllocated

    /**
     * The name of the {@code decimal(18, 2), DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_ALLOCATED = "QuantityAllocated";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_ALLOCATED} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_ALLOCATED = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_ALLOCATED} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_ALLOCATED = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_ALLOCATED} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_ALLOCATED = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_ALLOCATED} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_ALLOCATED = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_ALLOCATED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_ALLOCATED = "quantityAllocated";

    // --------------------------------------------------------------------------- QuantityAvailable

    /**
     * The name of the {@code decimal(19, 2), computed as ([QuantityOnHand]-[QuantityAllocated])} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_AVAILABLE = "QuantityAvailable";

    /**
     * The precision of the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_QUANTITY_AVAILABLE = 19;

    /**
     * The scale of the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_QUANTITY_AVAILABLE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute, the minimum of the
     * {@code decimal(19, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_QUANTITY_AVAILABLE = "-99999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute, the maximum of the
     * {@code decimal(19, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_QUANTITY_AVAILABLE = "99999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_AVAILABLE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_AVAILABLE = "quantityAvailable";

    // ------------------------------------------------------------------------------- LastCountDate

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_COUNT_DATE = "LastCountDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_COUNT_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_COUNT_DATE = "lastCountDate";

    // --------------------------------------------------------------------------------- LastUpdated

    /**
     * The name of the {@code datetime2, DEFAULT (getdate())} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_UPDATED = "LastUpdated";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_UPDATED} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_UPDATED = "lastUpdated";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "inventoryId=" + inventoryId
               + ",itemId=" + itemId
               + ",warehouseId=" + warehouseId
               + ",quantityOnHand=" + quantityOnHand
               + ",quantityAllocated=" + quantityAllocated
               + ",quantityAvailable=" + quantityAvailable
               + ",lastCountDate=" + lastCountDate
               + ",lastUpdated=" + lastUpdated
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
        if (!(obj instanceof MappedInventory that)) {
            return false;
        }
        return getInventoryId() != null
               && getInventoryId().equals(that.getInventoryId());
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
        return MappedInventory.class.hashCode();
    }

    // --------------------------------------------------------------------------------- inventoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     */
    public Integer getInventoryId() {
        return inventoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute with the specified value.
     *
     * @param inventoryId new value for {@value #ATTRIBUTE_NAME_INVENTORY_ID} attribute.
     */
    protected void setInventoryId(final Integer inventoryId) {
        this.inventoryId = inventoryId;
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

    // --------------------------------------------------------------------------------- warehouseId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected Integer getWarehouseId() {
        return warehouseId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param warehouseId new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_ID} attribute.
     */
    protected void setWarehouseId(final Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    // ------------------------------------------------------------------------------ quantityOnHand

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     */
    public BigDecimal getQuantityOnHand() {
        return quantityOnHand;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute with the specified value.
     *
     * @param quantityOnHand new value for {@value #ATTRIBUTE_NAME_QUANTITY_ON_HAND} attribute.
     */
    public void setQuantityOnHand(final BigDecimal quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    // --------------------------------------------------------------------------- quantityAllocated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_ALLOCATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_ALLOCATED} attribute.
     */
    public BigDecimal getQuantityAllocated() {
        return quantityAllocated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_ALLOCATED} attribute with the specified value.
     *
     * @param quantityAllocated new value for {@value #ATTRIBUTE_NAME_QUANTITY_ALLOCATED} attribute.
     */
    public void setQuantityAllocated(final BigDecimal quantityAllocated) {
        this.quantityAllocated = quantityAllocated;
    }

    // --------------------------------------------------------------------------- quantityAvailable

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     */
    public BigDecimal getQuantityAvailable() {
        return quantityAvailable;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
     *
     * @param quantityAvailable new value for {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     */
    protected void setQuantityAvailable(final BigDecimal quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    // ------------------------------------------------------------------------------- lastCountDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_COUNT_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_COUNT_DATE} attribute.
     */
    public LocalDateTime getLastCountDate() {
        return lastCountDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_COUNT_DATE} attribute with the specified value.
     *
     * @param lastCountDate new value for {@value #ATTRIBUTE_NAME_LAST_COUNT_DATE} attribute.
     */
    public void setLastCountDate(final LocalDateTime lastCountDate) {
        this.lastCountDate = lastCountDate;
    }

    // --------------------------------------------------------------------------------- lastUpdated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute.
     */
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute with the specified value.
     *
     * @param lastUpdated new value for {@value #ATTRIBUTE_NAME_LAST_UPDATED} attribute.
     */
    public void setLastUpdated(final LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_INVENTORY_ID, nullable = false, insertable = false, updatable = false)
    private Integer inventoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer itemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_ID, nullable = false, insertable = false, updatable = false)
    private Integer warehouseId;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_ON_HAND)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_ON_HAND)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_ON_HAND, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_ON_HAND, scale = COLUMN_SCALE_QUANTITY_ON_HAND)
    @MsDefault("((0))")
    private BigDecimal quantityOnHand;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_ALLOCATED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_ALLOCATED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_ALLOCATED, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_QUANTITY_ALLOCATED, scale = COLUMN_SCALE_QUANTITY_ALLOCATED)
    @MsDefault("((0))")
    private BigDecimal quantityAllocated;

    // ---------------------------------------------------------------------------------------------
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_AVAILABLE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_AVAILABLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_AVAILABLE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_AVAILABLE, scale = COLUMN_SCALE_QUANTITY_AVAILABLE)
    @MsComputedColumn(value = "([QuantityOnHand]-[QuantityAllocated])", persisted = true)
    private BigDecimal quantityAvailable;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_COUNT_DATE, nullable = true, insertable = true, updatable = true)
    private LocalDateTime lastCountDate;

    // ---------------------------------------------------------------------------------------------
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_UPDATED, nullable = true, insertable = true, updatable = true)
    @MsDefault("(getdate())")
    private LocalDateTime lastUpdated;
}

