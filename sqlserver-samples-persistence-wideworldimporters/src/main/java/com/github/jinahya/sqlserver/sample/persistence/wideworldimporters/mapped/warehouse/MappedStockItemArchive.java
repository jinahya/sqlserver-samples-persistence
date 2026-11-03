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
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockItems_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query fills.
 * <p>
 * Read-only: SQL Server rejects an INSERT (error 13559) and an UPDATE (error 13561) of a temporal history table; only
 * the system versioning of {@code Warehouse.StockItems} writes it.
 */
@MappedSuperclass
public abstract class MappedStockItemArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockItems_Archive";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // ------------------------------------------------------------------------------- StockItemName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_NAME = "StockItemName";

    /**
     * The length of the {@value #COLUMN_NAME_STOCK_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STOCK_ITEM_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_NAME = "stockItemName";

    // ---------------------------------------------------------------------------------- SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // ------------------------------------------------------------------------------------- ColorID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLOR_ID = "ColorID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLOR_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLOR_ID = "colorId";

    // ------------------------------------------------------------------------------- UnitPackageID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PACKAGE_ID = "UnitPackageID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PACKAGE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PACKAGE_ID = "unitPackageId";

    // ------------------------------------------------------------------------------ OuterPackageID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OUTER_PACKAGE_ID = "OuterPackageID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OUTER_PACKAGE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_OUTER_PACKAGE_ID = "outerPackageId";

    // --------------------------------------------------------------------------------------- Brand

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BRAND = "Brand";

    /**
     * The length of the {@value #COLUMN_NAME_BRAND} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BRAND = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BRAND} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BRAND = "brand";

    // ---------------------------------------------------------------------------------------- Size

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIZE = "Size";

    /**
     * The length of the {@value #COLUMN_NAME_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SIZE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIZE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE = "size";

    // -------------------------------------------------------------------------------- LeadTimeDays

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEAD_TIME_DAYS = "LeadTimeDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEAD_TIME_DAYS = "leadTimeDays";

    // ---------------------------------------------------------------------------- QuantityPerOuter

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_PER_OUTER = "QuantityPerOuter";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_PER_OUTER} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_PER_OUTER = "quantityPerOuter";

    // ------------------------------------------------------------------------------ IsChillerStock

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_CHILLER_STOCK = "IsChillerStock";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_CHILLER_STOCK} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_CHILLER_STOCK = "isChillerStock";

    // ------------------------------------------------------------------------------------- Barcode

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BARCODE = "Barcode";

    /**
     * The length of the {@value #COLUMN_NAME_BARCODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BARCODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BARCODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BARCODE = "barcode";

    // ------------------------------------------------------------------------------------- TaxRate

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
     * The minimum value of the {@value #ATTRIBUTE_NAME_TAX_RATE} attribute, the minimum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TAX_RATE = "-999999999999999.999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TAX_RATE} attribute, the maximum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TAX_RATE = "999999999999999.999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_RATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_RATE = "taxRate";

    // ----------------------------------------------------------------------------------- UnitPrice

    /**
     * The name of the {@code decimal(18, 2), NOT NULL} column. The value is {@value}.
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
     * The minimum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ---------------------------------------------------------------------- RecommendedRetailPrice

    /**
     * The name of the {@code decimal(18, 2)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RECOMMENDED_RETAIL_PRICE = "RecommendedRetailPrice";

    /**
     * The precision of the {@value #COLUMN_NAME_RECOMMENDED_RETAIL_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_RECOMMENDED_RETAIL_PRICE = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_RECOMMENDED_RETAIL_PRICE} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_RECOMMENDED_RETAIL_PRICE = 2;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute, the minimum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_RECOMMENDED_RETAIL_PRICE = "-9999999999999999.99";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute, the maximum of the
     * {@code decimal(18, 2)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_RECOMMENDED_RETAIL_PRICE = "9999999999999999.99";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECOMMENDED_RETAIL_PRICE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE = "recommendedRetailPrice";

    // ------------------------------------------------------------------------ TypicalWeightPerUnit

    /**
     * The name of the {@code decimal(18, 3), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT = "TypicalWeightPerUnit";

    /**
     * The precision of the {@value #COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT} column. The value is {@value}.
     */
    public static final int COLUMN_PRECISION_TYPICAL_WEIGHT_PER_UNIT = 18;

    /**
     * The scale of the {@value #COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT} column. The value is {@value}.
     */
    public static final int COLUMN_SCALE_TYPICAL_WEIGHT_PER_UNIT = 3;

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute, the minimum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MIN_TYPICAL_WEIGHT_PER_UNIT = "-999999999999999.999";

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute, the maximum of the
     * {@code decimal(18, 3)} type. The value is {@value}.
     */
    public static final String ATTRIBUTE_DECIMAL_MAX_TYPICAL_WEIGHT_PER_UNIT = "999999999999999.999";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT = "typicalWeightPerUnit";

    // --------------------------------------------------------------------------- MarketingComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MARKETING_COMMENTS = "MarketingComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MARKETING_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MARKETING_COMMENTS = "marketingComments";

    // ---------------------------------------------------------------------------- InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

    // --------------------------------------------------------------------------------------- Photo

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHOTO = "Photo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHOTO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHOTO = "photo";

    // -------------------------------------------------------------------------------- CustomFields

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOM_FIELDS = "CustomFields";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOM_FIELDS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOM_FIELDS = "customFields";

    // ---------------------------------------------------------------------------------------- Tags

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAGS = "Tags";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAGS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAGS = "tags";

    // ------------------------------------------------------------------------------- SearchDetails

    /**
     * The name of the {@code nvarchar(max), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SEARCH_DETAILS = "SearchDetails";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SEARCH_DETAILS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SEARCH_DETAILS = "searchDetails";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ----------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStockItemArchive() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "stockItemId=" + stockItemId
               + ",stockItemName=" + stockItemName
               + ",supplierId=" + supplierId
               + ",colorId=" + colorId
               + ",unitPackageId=" + unitPackageId
               + ",outerPackageId=" + outerPackageId
               + ",brand=" + brand
               + ",size=" + size
               + ",leadTimeDays=" + leadTimeDays
               + ",quantityPerOuter=" + quantityPerOuter
               + ",isChillerStock=" + isChillerStock
               + ",barcode=" + barcode
               + ",taxRate=" + taxRate
               + ",unitPrice=" + unitPrice
               + ",recommendedRetailPrice=" + recommendedRetailPrice
               + ",typicalWeightPerUnit=" + typicalWeightPerUnit
               + ",marketingComments=" + marketingComments
               + ",internalComments=" + internalComments
               + ",photo=" + (this.photo == null ? null : this.photo.length + " bytes")
               + ",customFields=" + customFields
               + ",tags=" + tags
               + ",searchDetails=" + searchDetails
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
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

    // ------------------------------------------------------------------------------- stockItemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute.
     */
    public String getStockItemName() {
        return stockItemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute with the specified value.
     *
     * @param stockItemName new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute.
     */
    protected void setStockItemName(final String stockItemName) {
        this.stockItemName = stockItemName;
    }

    // ---------------------------------------------------------------------------------- supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(final Integer supplierId) {
        this.supplierId = supplierId;
    }

    // ------------------------------------------------------------------------------------- colorId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute.
     */
    public Integer getColorId() {
        return colorId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute with the specified value.
     *
     * @param colorId new value for {@value #ATTRIBUTE_NAME_COLOR_ID} attribute.
     */
    protected void setColorId(final Integer colorId) {
        this.colorId = colorId;
    }

    // ------------------------------------------------------------------------------- unitPackageId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute.
     */
    public Integer getUnitPackageId() {
        return unitPackageId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute with the specified value.
     *
     * @param unitPackageId new value for {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute.
     */
    protected void setUnitPackageId(final Integer unitPackageId) {
        this.unitPackageId = unitPackageId;
    }

    // ------------------------------------------------------------------------------ outerPackageId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute.
     */
    public Integer getOuterPackageId() {
        return outerPackageId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute with the specified value.
     *
     * @param outerPackageId new value for {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute.
     */
    protected void setOuterPackageId(final Integer outerPackageId) {
        this.outerPackageId = outerPackageId;
    }

    // --------------------------------------------------------------------------------------- brand

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BRAND} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BRAND} attribute.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BRAND} attribute with the specified value.
     *
     * @param brand new value for {@value #ATTRIBUTE_NAME_BRAND} attribute.
     */
    protected void setBrand(final String brand) {
        this.brand = brand;
    }

    // ---------------------------------------------------------------------------------------- size

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SIZE} attribute.
     */
    public String getSize() {
        return size;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SIZE} attribute with the specified value.
     *
     * @param size new value for {@value #ATTRIBUTE_NAME_SIZE} attribute.
     */
    protected void setSize(final String size) {
        this.size = size;
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

    // ---------------------------------------------------------------------------- quantityPerOuter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute.
     */
    public Integer getQuantityPerOuter() {
        return quantityPerOuter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute with the specified value.
     *
     * @param quantityPerOuter new value for {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute.
     */
    protected void setQuantityPerOuter(final Integer quantityPerOuter) {
        this.quantityPerOuter = quantityPerOuter;
    }

    // ------------------------------------------------------------------------------ isChillerStock

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute.
     */
    public Boolean getIsChillerStock() {
        return isChillerStock;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute with the specified value.
     *
     * @param isChillerStock new value for {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute.
     */
    protected void setIsChillerStock(final Boolean isChillerStock) {
        this.isChillerStock = isChillerStock;
    }

    // ------------------------------------------------------------------------------------- barcode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BARCODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BARCODE} attribute.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BARCODE} attribute with the specified value.
     *
     * @param barcode new value for {@value #ATTRIBUTE_NAME_BARCODE} attribute.
     */
    protected void setBarcode(final String barcode) {
        this.barcode = barcode;
    }

    // ------------------------------------------------------------------------------------- taxRate

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
    protected void setTaxRate(final BigDecimal taxRate) {
        this.taxRate = taxRate;
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
    protected void setUnitPrice(final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ---------------------------------------------------------------------- recommendedRetailPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute.
     */
    public BigDecimal getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute with the specified value.
     *
     * @param recommendedRetailPrice new value for {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute.
     */
    protected void setRecommendedRetailPrice(final BigDecimal recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    // ------------------------------------------------------------------------ typicalWeightPerUnit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute.
     */
    public BigDecimal getTypicalWeightPerUnit() {
        return typicalWeightPerUnit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute with the specified value.
     *
     * @param typicalWeightPerUnit new value for {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute.
     */
    protected void setTypicalWeightPerUnit(final BigDecimal typicalWeightPerUnit) {
        this.typicalWeightPerUnit = typicalWeightPerUnit;
    }

    // --------------------------------------------------------------------------- marketingComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute.
     */
    public String getMarketingComments() {
        return marketingComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute with the specified value.
     *
     * @param marketingComments new value for {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute.
     */
    protected void setMarketingComments(final String marketingComments) {
        this.marketingComments = marketingComments;
    }

    // ---------------------------------------------------------------------------- internalComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    public String getInternalComments() {
        return internalComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute with the specified value.
     *
     * @param internalComments new value for {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    protected void setInternalComments(final String internalComments) {
        this.internalComments = internalComments;
    }

    // --------------------------------------------------------------------------------------- photo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute with the specified value.
     *
     * @param photo new value for {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     */
    protected void setPhoto(final byte[] photo) {
        this.photo = photo;
    }

    // -------------------------------------------------------------------------------- customFields

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     */
    public String getCustomFields() {
        return customFields;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute with the specified value.
     *
     * @param customFields new value for {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     */
    protected void setCustomFields(final String customFields) {
        this.customFields = customFields;
    }

    // ---------------------------------------------------------------------------------------- tags

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAGS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAGS} attribute.
     */
    public String getTags() {
        return tags;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAGS} attribute with the specified value.
     *
     * @param tags new value for {@value #ATTRIBUTE_NAME_TAGS} attribute.
     */
    protected void setTags(final String tags) {
        this.tags = tags;
    }

    // ------------------------------------------------------------------------------- searchDetails

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute.
     */
    public String getSearchDetails() {
        return searchDetails;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute with the specified value.
     *
     * @param searchDetails new value for {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute.
     */
    protected void setSearchDetails(final String searchDetails) {
        this.searchDetails = searchDetails;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ----------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // ------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = false, updatable = false)
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_STOCK_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_NAME, nullable = false, insertable = false, updatable = false, length = COLUMN_LENGTH_STOCK_ITEM_NAME)
    private String stockItemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID, nullable = false, insertable = false, updatable = false)
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COLOR_ID, nullable = true, insertable = false, updatable = false)
    private Integer colorId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PACKAGE_ID, nullable = false, insertable = false, updatable = false)
    private Integer unitPackageId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_OUTER_PACKAGE_ID, nullable = false, insertable = false, updatable = false)
    private Integer outerPackageId;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BRAND)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BRAND, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_BRAND)
    private String brand;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_SIZE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIZE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_SIZE)
    private String size;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS, nullable = false, insertable = false, updatable = false)
    private Integer leadTimeDays;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_PER_OUTER, nullable = false, insertable = false, updatable = false)
    private Integer quantityPerOuter;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_CHILLER_STOCK, nullable = false, insertable = false, updatable = false)
    private Boolean isChillerStock;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Size(max = COLUMN_LENGTH_BARCODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BARCODE, nullable = true, insertable = false, updatable = false, length = COLUMN_LENGTH_BARCODE)
    private String barcode;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TAX_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TAX_RATE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_RATE, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_TAX_RATE, scale = COLUMN_SCALE_TAX_RATE)
    private BigDecimal taxRate;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_RECOMMENDED_RETAIL_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_RECOMMENDED_RETAIL_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RECOMMENDED_RETAIL_PRICE, nullable = true, insertable = false, updatable = false, precision = COLUMN_PRECISION_RECOMMENDED_RETAIL_PRICE, scale = COLUMN_SCALE_RECOMMENDED_RETAIL_PRICE)
    private BigDecimal recommendedRetailPrice;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TYPICAL_WEIGHT_PER_UNIT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TYPICAL_WEIGHT_PER_UNIT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT, nullable = false, insertable = false, updatable = false, precision = COLUMN_PRECISION_TYPICAL_WEIGHT_PER_UNIT, scale = COLUMN_SCALE_TYPICAL_WEIGHT_PER_UNIT)
    private BigDecimal typicalWeightPerUnit;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 126 bytes (63 characters)
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MARKETING_COMMENTS, nullable = true, insertable = false, updatable = false)
    private String marketingComments;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS, nullable = true, insertable = false, updatable = false)
    private String internalComments;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHOTO, nullable = true, insertable = false, updatable = false)
    private byte[] photo;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 200 bytes (100 characters)
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOM_FIELDS, nullable = true, insertable = false, updatable = false)
    private String customFields;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 70 bytes (35 characters)
    // read-only: a temporal history table; see the class documentation
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TAGS, nullable = true, insertable = false, updatable = false)
    private String tags;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 208 bytes (104 characters)
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SEARCH_DETAILS, nullable = false, insertable = false, updatable = false)
    private String searchDetails;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a temporal history table; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

