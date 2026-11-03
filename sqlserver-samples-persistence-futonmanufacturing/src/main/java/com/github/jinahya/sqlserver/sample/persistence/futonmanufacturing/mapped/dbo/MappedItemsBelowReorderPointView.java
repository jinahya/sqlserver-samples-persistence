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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.vw_ItemsBelowReorderPoint} view.
 * <p>
 * Identifier: the view declares no key, and no identifier column of it is unique.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server takes no whole row of this view in an INSERT or an UPDATE, so no column of it is insertable or
 * updatable; an INSERT or an UPDATE of a whole row affects more than one of its base tables (error 4405); some of its
 * columns are derived or constant (error 4406); one of its columns is a computed column of its base table (error 271).
 */
@MappedSuperclass
public abstract class MappedItemsBelowReorderPointView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vw_ItemsBelowReorderPoint";

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

    // -------------------------------------------------------------------------------- ReorderPoint

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REORDER_POINT = "ReorderPoint";

    /**
     * The precision of the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_REORDER_POINT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_REORDER_POINT = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_REORDER_POINT = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_REORDER_POINT = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REORDER_POINT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REORDER_POINT = "reorderPoint";

    // --------------------------------------------------------------------------------- SafetyStock

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SAFETY_STOCK = "SafetyStock";

    /**
     * The precision of the {@value #COLUMN_NAME_SAFETY_STOCK} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SAFETY_STOCK = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_SAFETY_STOCK} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SAFETY_STOCK = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SAFETY_STOCK = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SAFETY_STOCK = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SAFETY_STOCK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SAFETY_STOCK = "safetyStock";

    // ---------------------------------------------------------------------------- ShortageQuantity

    /**
     * The name of the {@code decimal(20, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHORTAGE_QUANTITY = "ShortageQuantity";

    /**
     * The precision of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_SHORTAGE_QUANTITY = 20;

    /**
     * The scale of the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_SHORTAGE_QUANTITY = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute, the minimum of the
     * {@code decimal(20, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_SHORTAGE_QUANTITY = "-999999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute, the maximum of the
     * {@code decimal(20, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_SHORTAGE_QUANTITY = "999999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHORTAGE_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHORTAGE_QUANTITY = "shortageQuantity";

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

    // -------------------------------------------------------------------------------- LeadTimeDays

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEAD_TIME_DAYS = "LeadTimeDays";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_LEAD_TIME_DAYS = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_LEAD_TIME_DAYS = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEAD_TIME_DAYS = "leadTimeDays";

    // --------------------------------------------------------------------------- PreferredSupplier

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PREFERRED_SUPPLIER = "PreferredSupplier";

    /**
     * The length of the {@value #COLUMN_NAME_PREFERRED_SUPPLIER} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PREFERRED_SUPPLIER = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PREFERRED_SUPPLIER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PREFERRED_SUPPLIER = "preferredSupplier";

    // ------------------------------------------------------------------------------ PreferredPrice

    /**
     * The name of the {@code decimal(18, 4)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PREFERRED_PRICE = "PreferredPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_PREFERRED_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_PREFERRED_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_PREFERRED_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_PREFERRED_PRICE = 4;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PREFERRED_PRICE} attribute, the minimum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_PREFERRED_PRICE = "-99999999999999.9999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PREFERRED_PRICE} attribute, the maximum of the
     * {@code decimal(18, 4)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_PREFERRED_PRICE = "99999999999999.9999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PREFERRED_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PREFERRED_PRICE = "preferredPrice";

    // ----------------------------------------------------------------------------- ExpectedArrival

    /**
     * The name of the {@code datetime} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_EXPECTED_ARRIVAL = "ExpectedArrival";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_EXPECTED_ARRIVAL} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_EXPECTED_ARRIVAL = "expectedArrival";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedItemsBelowReorderPointView() {
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
               + ",quantityAvailable=" + quantityAvailable
               + ",reorderPoint=" + reorderPoint
               + ",safetyStock=" + safetyStock
               + ",shortageQuantity=" + shortageQuantity
               + ",unitCode=" + unitCode
               + ",leadTimeDays=" + leadTimeDays
               + ",preferredSupplier=" + preferredSupplier
               + ",preferredPrice=" + preferredPrice
               + ",expectedArrival=" + expectedArrival
               + "}";
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

    // -------------------------------------------------------------------------------- reorderPoint

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    public BigDecimal getReorderPoint() {
        return reorderPoint;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute with the specified value.
     *
     * @param reorderPoint new value for {@value #ATTRIBUTE_NAME_REORDER_POINT} attribute.
     */
    protected void setReorderPoint(final BigDecimal reorderPoint) {
        this.reorderPoint = reorderPoint;
    }

    // --------------------------------------------------------------------------------- safetyStock

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute.
     */
    public BigDecimal getSafetyStock() {
        return safetyStock;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute with the specified value.
     *
     * @param safetyStock new value for {@value #ATTRIBUTE_NAME_SAFETY_STOCK} attribute.
     */
    protected void setSafetyStock(final BigDecimal safetyStock) {
        this.safetyStock = safetyStock;
    }

    // ---------------------------------------------------------------------------- shortageQuantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     */
    public BigDecimal getShortageQuantity() {
        return shortageQuantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute with the specified value.
     *
     * @param shortageQuantity new value for {@value #ATTRIBUTE_NAME_SHORTAGE_QUANTITY} attribute.
     */
    protected void setShortageQuantity(final BigDecimal shortageQuantity) {
        this.shortageQuantity = shortageQuantity;
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

    // -------------------------------------------------------------------------------- leadTimeDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute with the specified value.
     *
     * @param leadTimeDays new value for {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    protected void setLeadTimeDays(final Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    // --------------------------------------------------------------------------- preferredSupplier

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute.
     */
    public String getPreferredSupplier() {
        return preferredSupplier;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute with the specified value.
     *
     * @param preferredSupplier new value for {@value #ATTRIBUTE_NAME_PREFERRED_SUPPLIER} attribute.
     */
    protected void setPreferredSupplier(final String preferredSupplier) {
        this.preferredSupplier = preferredSupplier;
    }

    // ------------------------------------------------------------------------------ preferredPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PREFERRED_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PREFERRED_PRICE} attribute.
     */
    public BigDecimal getPreferredPrice() {
        return preferredPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PREFERRED_PRICE} attribute with the specified value.
     *
     * @param preferredPrice new value for {@value #ATTRIBUTE_NAME_PREFERRED_PRICE} attribute.
     */
    protected void setPreferredPrice(final BigDecimal preferredPrice) {
        this.preferredPrice = preferredPrice;
    }

    // ----------------------------------------------------------------------------- expectedArrival

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_EXPECTED_ARRIVAL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_EXPECTED_ARRIVAL} attribute.
     */
    public LocalDateTime getExpectedArrival() {
        return expectedArrival;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_EXPECTED_ARRIVAL} attribute with the specified value.
     *
     * @param expectedArrival new value for {@value #ATTRIBUTE_NAME_EXPECTED_ARRIVAL} attribute.
     */
    protected void setExpectedArrival(final LocalDateTime expectedArrival) {
        this.expectedArrival = expectedArrival;
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
    @Basic(optional = false)
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
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_QUANTITY_AVAILABLE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_QUANTITY_AVAILABLE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_QUANTITY_AVAILABLE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_QUANTITY_AVAILABLE, scale = COLUMN_SCALE_QUANTITY_AVAILABLE)
    private BigDecimal quantityAvailable;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_REORDER_POINT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_REORDER_POINT)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_REORDER_POINT, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_REORDER_POINT, scale = COLUMN_SCALE_REORDER_POINT)
    private BigDecimal reorderPoint;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SAFETY_STOCK)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SAFETY_STOCK)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SAFETY_STOCK, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SAFETY_STOCK, scale = COLUMN_SCALE_SAFETY_STOCK)
    private BigDecimal safetyStock;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_SHORTAGE_QUANTITY)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_SHORTAGE_QUANTITY)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SHORTAGE_QUANTITY, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_SHORTAGE_QUANTITY, scale = COLUMN_SCALE_SHORTAGE_QUANTITY)
    private BigDecimal shortageQuantity;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_UNIT_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_CODE, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_UNIT_CODE)
    private String unitCode;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Max(ATTRIBUTE_MAX_LEAD_TIME_DAYS)
    @Min(ATTRIBUTE_MIN_LEAD_TIME_DAYS)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS, nullable = true, insertable = false, updatable = false)
    private Integer leadTimeDays;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Size(max = COLUMN_LENGTH_PREFERRED_SUPPLIER)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PREFERRED_SUPPLIER, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_PREFERRED_SUPPLIER)
    private String preferredSupplier;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_PREFERRED_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_PREFERRED_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PREFERRED_PRICE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_PREFERRED_PRICE, scale = COLUMN_SCALE_PREFERRED_PRICE)
    private BigDecimal preferredPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: SQL Server does not write this view; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_EXPECTED_ARRIVAL, nullable = true, insertable = false, updatable = false)
    private LocalDateTime expectedArrival;
}

