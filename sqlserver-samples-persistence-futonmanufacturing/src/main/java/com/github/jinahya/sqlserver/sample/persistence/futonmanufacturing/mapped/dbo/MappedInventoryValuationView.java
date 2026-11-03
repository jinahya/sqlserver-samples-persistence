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
 * A mapped superclass which holds the mappings of the {@code dbo.vw_InventoryValuation} view.
 * <p>
 * Identifier: the view declares no key; ItemCode is unique, and not null, in every row it returns.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is updatable, nor
 * insertable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); some of its
 * columns are derived or constant (error 4406); one of its columns is a computed column of its base table (error 271).
 */
@MappedSuperclass
public abstract class MappedInventoryValuationView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_InventoryValuation";

    // ------------------------------------------------------------------------------- WarehouseCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_CODE = "WarehouseCode";

    /**
     * The length of the {@value #COLUMN_NAME_WAREHOUSE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WAREHOUSE_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_CODE = "warehouseCode";

    // ------------------------------------------------------------------------------- WarehouseName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_WAREHOUSE_NAME = "WarehouseName";

    /**
     * The length of the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_WAREHOUSE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_WAREHOUSE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_WAREHOUSE_NAME = "warehouseName";

    // ------------------------------------------------------------------------------------ ItemType

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_TYPE = "ItemType";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_TYPE = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_TYPE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_TYPE = "itemType";

    // ------------------------------------------------------------------------------------ ItemCode

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_CODE = "ItemCode";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_CODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_CODE = "itemCode";

    // ------------------------------------------------------------------------------------ ItemName

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ITEM_NAME = "ItemName";

    /**
     * The length of the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ITEM_NAME = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ITEM_NAME = "itemName";

    // ------------------------------------------------------------------------------ QuantityOnHand

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
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
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
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
     * The name of the {@code decimal(19, 2)} column. The value is {@value}.
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

    // ------------------------------------------------------------------------------------ UnitCode

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_CODE = "UnitCode";

    /**
     * The length of the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_UNIT_CODE = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_CODE = "unitCode";

    // -------------------------------------------------------------------------------- StandardCost

    /**
     * The name of the {@code decimal(18, 4), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STANDARD_COST = "StandardCost";

    /**
     * The precision of the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_STANDARD_COST = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_STANDARD_COST = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute, the minimum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_STANDARD_COST = "-99999999999999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute, the maximum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_STANDARD_COST = "99999999999999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STANDARD_COST} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STANDARD_COST = "standardCost";

    // ------------------------------------------------------------------------------ InventoryValue

    /**
     * The name of the {@code decimal(37, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INVENTORY_VALUE = "InventoryValue";

    /**
     * The precision of the {@value #COLUMN_NAME_INVENTORY_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_INVENTORY_VALUE = 37;

    /**
     * The scale of the {@value #COLUMN_NAME_INVENTORY_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_INVENTORY_VALUE = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute, the minimum of the
     * {@code decimal(37, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_INVENTORY_VALUE = "-9999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute, the maximum of the
     * {@code decimal(37, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_INVENTORY_VALUE = "9999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INVENTORY_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INVENTORY_VALUE = "inventoryValue";

    // ------------------------------------------------------------------------------ AvailableValue

    /**
     * The name of the {@code decimal(38, 6)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_AVAILABLE_VALUE = "AvailableValue";

    /**
     * The precision of the {@value #COLUMN_NAME_AVAILABLE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_AVAILABLE_VALUE = 38;

    /**
     * The scale of the {@value #COLUMN_NAME_AVAILABLE_VALUE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_AVAILABLE_VALUE = 6;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_AVAILABLE_VALUE} attribute, the minimum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_AVAILABLE_VALUE = "-99999999999999999999999999999999.999999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_AVAILABLE_VALUE} attribute, the maximum of the
     * {@code decimal(38, 6)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_AVAILABLE_VALUE = "99999999999999999999999999999999.999999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_AVAILABLE_VALUE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_AVAILABLE_VALUE = "availableValue";

    // ------------------------------------------------------------------------------- LastCountDate

    /**
     * The name of the {@code datetime2} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_COUNT_DATE = "LastCountDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_COUNT_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_COUNT_DATE = "lastCountDate";

    // ------------------------------------------------------------------------------ DaysSinceCount

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DAYS_SINCE_COUNT = "DaysSinceCount";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_DAYS_SINCE_COUNT} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_DAYS_SINCE_COUNT = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_DAYS_SINCE_COUNT} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_DAYS_SINCE_COUNT = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DAYS_SINCE_COUNT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DAYS_SINCE_COUNT = "daysSinceCount";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedInventoryValuationView() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "warehouseCode=" + warehouseCode
               + ",warehouseName=" + warehouseName
               + ",itemType=" + itemType
               + ",itemCode=" + itemCode
               + ",itemName=" + itemName
               + ",quantityOnHand=" + quantityOnHand
               + ",quantityAllocated=" + quantityAllocated
               + ",quantityAvailable=" + quantityAvailable
               + ",unitCode=" + unitCode
               + ",standardCost=" + standardCost
               + ",inventoryValue=" + inventoryValue
               + ",availableValue=" + availableValue
               + ",lastCountDate=" + lastCountDate
               + ",daysSinceCount=" + daysSinceCount
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
        if (!(obj instanceof MappedInventoryValuationView that)) {
            return false;
        }
        return Objects.equals(itemCode, that.itemCode);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(itemCode);
    }

    // ------------------------------------------------------------------------------- warehouseCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute.
     */
    public String getWarehouseCode() {
        return warehouseCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute with the specified value.
     *
     * @param warehouseCode new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_CODE} attribute.
     */
    protected void setWarehouseCode(final String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    // ------------------------------------------------------------------------------- warehouseName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute with the specified value.
     *
     * @param warehouseName new value for {@value #ATTRIBUTE_NAME_WAREHOUSE_NAME} attribute.
     */
    protected void setWarehouseName(final String warehouseName) {
        this.warehouseName = warehouseName;
    }

    // ------------------------------------------------------------------------------------ itemType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute with the specified value.
     *
     * @param itemType new value for {@value #ATTRIBUTE_NAME_ITEM_TYPE} attribute.
     */
    protected void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    // ------------------------------------------------------------------------------------ itemCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute with the specified value.
     *
     * @param itemCode new value for {@value #ATTRIBUTE_NAME_ITEM_CODE} attribute.
     */
    protected void setItemCode(final String itemCode) {
        this.itemCode = itemCode;
    }

    // ------------------------------------------------------------------------------------ itemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute with the specified value.
     *
     * @param itemName new value for {@value #ATTRIBUTE_NAME_ITEM_NAME} attribute.
     */
    protected void setItemName(final String itemName) {
        this.itemName = itemName;
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
    protected void setQuantityOnHand(final BigDecimal quantityOnHand) {
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
    protected void setQuantityAllocated(final BigDecimal quantityAllocated) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute with the specified value.
     *
     * @param quantityAvailable new value for {@value #ATTRIBUTE_NAME_QUANTITY_AVAILABLE} attribute.
     */
    protected void setQuantityAvailable(final BigDecimal quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    // ------------------------------------------------------------------------------------ unitCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute with the specified value.
     *
     * @param unitCode new value for {@value #ATTRIBUTE_NAME_UNIT_CODE} attribute.
     */
    protected void setUnitCode(final String unitCode) {
        this.unitCode = unitCode;
    }

    // -------------------------------------------------------------------------------- standardCost

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    public BigDecimal getStandardCost() {
        return standardCost;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute with the specified value.
     *
     * @param standardCost new value for {@value #ATTRIBUTE_NAME_STANDARD_COST} attribute.
     */
    protected void setStandardCost(final BigDecimal standardCost) {
        this.standardCost = standardCost;
    }

    // ------------------------------------------------------------------------------ inventoryValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute.
     */
    public BigDecimal getInventoryValue() {
        return inventoryValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute with the specified value.
     *
     * @param inventoryValue new value for {@value #ATTRIBUTE_NAME_INVENTORY_VALUE} attribute.
     */
    protected void setInventoryValue(final BigDecimal inventoryValue) {
        this.inventoryValue = inventoryValue;
    }

    // ------------------------------------------------------------------------------ availableValue

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_AVAILABLE_VALUE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_AVAILABLE_VALUE} attribute.
     */
    public BigDecimal getAvailableValue() {
        return availableValue;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_AVAILABLE_VALUE} attribute with the specified value.
     *
     * @param availableValue new value for {@value #ATTRIBUTE_NAME_AVAILABLE_VALUE} attribute.
     */
    protected void setAvailableValue(final BigDecimal availableValue) {
        this.availableValue = availableValue;
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
    protected void setLastCountDate(final LocalDateTime lastCountDate) {
        this.lastCountDate = lastCountDate;
    }

    // ------------------------------------------------------------------------------ daysSinceCount

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_COUNT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_COUNT} attribute.
     */
    public Integer getDaysSinceCount() {
        return daysSinceCount;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DAYS_SINCE_COUNT} attribute with the specified value.
     *
     * @param daysSinceCount new value for {@value #ATTRIBUTE_NAME_DAYS_SINCE_COUNT} attribute.
     */
    protected void setDaysSinceCount(final Integer daysSinceCount) {
        this.daysSinceCount = daysSinceCount;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WAREHOUSE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WAREHOUSE_CODE)
    private String warehouseCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_WAREHOUSE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_WAREHOUSE_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_WAREHOUSE_NAME)
    private String warehouseName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_TYPE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_TYPE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_TYPE)
    private String itemType;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_CODE)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ITEM_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_CODE)
    private String itemCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ITEM_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_ITEM_NAME)
    private String itemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_ON_HAND)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_ON_HAND)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_ON_HAND, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_ON_HAND, scale = COLUMN_SCALE_QUANTITY_ON_HAND)
    private BigDecimal quantityOnHand;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_ALLOCATED)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_ALLOCATED)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_ALLOCATED, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_ALLOCATED, scale = COLUMN_SCALE_QUANTITY_ALLOCATED)
    private BigDecimal quantityAllocated;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_AVAILABLE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_AVAILABLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_AVAILABLE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_AVAILABLE, scale = COLUMN_SCALE_QUANTITY_AVAILABLE)
    private BigDecimal quantityAvailable;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_UNIT_CODE)
    private String unitCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_STANDARD_COST)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_STANDARD_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STANDARD_COST, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_STANDARD_COST, scale = COLUMN_SCALE_STANDARD_COST)
    private BigDecimal standardCost;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_INVENTORY_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_INVENTORY_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INVENTORY_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_INVENTORY_VALUE, scale = COLUMN_SCALE_INVENTORY_VALUE)
    private BigDecimal inventoryValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_AVAILABLE_VALUE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_AVAILABLE_VALUE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_AVAILABLE_VALUE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_AVAILABLE_VALUE, scale = COLUMN_SCALE_AVAILABLE_VALUE)
    private BigDecimal availableValue;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LAST_COUNT_DATE, nullable = true, insertable = false, updatable = false)
    private LocalDateTime lastCountDate;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_DAYS_SINCE_COUNT)
    @Min(ATTRIBUTE_MIN_DAYS_SINCE_COUNT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DAYS_SINCE_COUNT, nullable = true, insertable = false, updatable = false)
    private Integer daysSinceCount;
}

