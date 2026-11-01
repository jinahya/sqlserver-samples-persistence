package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockItems_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedStockItemArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Warehouse";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockItems_Archive";

    // ----------------------------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_ID = "StockItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_ID = "stockItemId";

    // --------------------------------------------------------------------------------------------------- StockItemName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_STOCK_ITEM_NAME = "StockItemName";

    /**
     * The length of the {@value #COLUMN_NAME_STOCK_ITEM_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_STOCK_ITEM_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_STOCK_ITEM_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_STOCK_ITEM_NAME = "stockItemName";

    // ------------------------------------------------------------------------------------------------------ SupplierID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_ID = "SupplierID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_ID = "supplierId";

    // --------------------------------------------------------------------------------------------------------- ColorID

    /**
     * The name of the {@code int} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_COLOR_ID = "ColorID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_COLOR_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_COLOR_ID = "colorId";

    // --------------------------------------------------------------------------------------------------- UnitPackageID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_UNIT_PACKAGE_ID = "UnitPackageID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PACKAGE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PACKAGE_ID = "unitPackageId";

    // -------------------------------------------------------------------------------------------------- OuterPackageID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_OUTER_PACKAGE_ID = "OuterPackageID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_OUTER_PACKAGE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_OUTER_PACKAGE_ID = "outerPackageId";

    // ----------------------------------------------------------------------------------------------------------- Brand

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BRAND = "Brand";

    /**
     * The length of the {@value #COLUMN_NAME_BRAND} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BRAND = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BRAND} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BRAND = "brand";

    // ------------------------------------------------------------------------------------------------------------ Size

    /**
     * The name of the {@code nvarchar(20)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SIZE = "Size";

    /**
     * The length of the {@value #COLUMN_NAME_SIZE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SIZE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SIZE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SIZE = "size";

    // ---------------------------------------------------------------------------------------------------- LeadTimeDays

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LEAD_TIME_DAYS = "LeadTimeDays";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LEAD_TIME_DAYS = "leadTimeDays";

    // ------------------------------------------------------------------------------------------------ QuantityPerOuter

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY_PER_OUTER = "QuantityPerOuter";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY_PER_OUTER} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY_PER_OUTER = "quantityPerOuter";

    // -------------------------------------------------------------------------------------------------- IsChillerStock

    /**
     * The name of the {@code bit, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_CHILLER_STOCK = "IsChillerStock";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_CHILLER_STOCK} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_CHILLER_STOCK = "isChillerStock";

    // --------------------------------------------------------------------------------------------------------- Barcode

    /**
     * The name of the {@code nvarchar(50)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BARCODE = "Barcode";

    /**
     * The length of the {@value #COLUMN_NAME_BARCODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_BARCODE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BARCODE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BARCODE = "barcode";

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAX_RATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAX_RATE = "taxRate";

    // ------------------------------------------------------------------------------------------------------- UnitPrice

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_UNIT_PRICE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_UNIT_PRICE = "unitPrice";

    // ------------------------------------------------------------------------------------------ RecommendedRetailPrice

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_RECOMMENDED_RETAIL_PRICE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE = "recommendedRetailPrice";

    // -------------------------------------------------------------------------------------------- TypicalWeightPerUnit

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT = "typicalWeightPerUnit";

    // ----------------------------------------------------------------------------------------------- MarketingComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MARKETING_COMMENTS = "MarketingComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MARKETING_COMMENTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MARKETING_COMMENTS = "marketingComments";

    // ------------------------------------------------------------------------------------------------ InternalComments

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INTERNAL_COMMENTS = "InternalComments";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_INTERNAL_COMMENTS = "internalComments";

    // ----------------------------------------------------------------------------------------------------------- Photo

    /**
     * The name of the {@code varbinary(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PHOTO = "Photo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PHOTO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHOTO = "photo";

    // ---------------------------------------------------------------------------------------------------- CustomFields

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CUSTOM_FIELDS = "CustomFields";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CUSTOM_FIELDS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CUSTOM_FIELDS = "customFields";

    // ------------------------------------------------------------------------------------------------------------ Tags

    /**
     * The name of the {@code nvarchar(max)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TAGS = "Tags";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAGS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAGS = "tags";

    // --------------------------------------------------------------------------------------------------- SearchDetails

    /**
     * The name of the {@code nvarchar(max), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SEARCH_DETAILS = "SearchDetails";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SEARCH_DETAILS} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SEARCH_DETAILS = "searchDetails";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedStockItemArchive() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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

    // ----------------------------------------------------------------------------------------------------- stockItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    @Nonnull
    public Integer getStockItemId() {
        return stockItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute with the specified value.
     *
     * @param stockItemId new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_ID} attribute.
     */
    protected void setStockItemId(@Nonnull final Integer stockItemId) {
        this.stockItemId = stockItemId;
    }

    // --------------------------------------------------------------------------------------------------- stockItemName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute.
     */
    @Nonnull
    public String getStockItemName() {
        return stockItemName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute with the specified value.
     *
     * @param stockItemName new value for {@value #ATTRIBUTE_NAME_STOCK_ITEM_NAME} attribute.
     */
    protected void setStockItemName(@Nonnull final String stockItemName) {
        this.stockItemName = stockItemName;
    }

    // ------------------------------------------------------------------------------------------------------ supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    @Nonnull
    public Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value.
     *
     * @param supplierId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected void setSupplierId(@Nonnull final Integer supplierId) {
        this.supplierId = supplierId;
    }

    // --------------------------------------------------------------------------------------------------------- colorId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute.
     */
    @Nullable
    public Integer getColorId() {
        return colorId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute with the specified value.
     *
     * @param colorId new value for {@value #ATTRIBUTE_NAME_COLOR_ID} attribute.
     */
    protected void setColorId(@Nullable final Integer colorId) {
        this.colorId = colorId;
    }

    // --------------------------------------------------------------------------------------------------- unitPackageId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute.
     */
    @Nonnull
    public Integer getUnitPackageId() {
        return unitPackageId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute with the specified value.
     *
     * @param unitPackageId new value for {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute.
     */
    protected void setUnitPackageId(@Nonnull final Integer unitPackageId) {
        this.unitPackageId = unitPackageId;
    }

    // -------------------------------------------------------------------------------------------------- outerPackageId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute.
     */
    @Nonnull
    public Integer getOuterPackageId() {
        return outerPackageId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute with the specified value.
     *
     * @param outerPackageId new value for {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute.
     */
    protected void setOuterPackageId(@Nonnull final Integer outerPackageId) {
        this.outerPackageId = outerPackageId;
    }

    // ----------------------------------------------------------------------------------------------------------- brand

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BRAND} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BRAND} attribute.
     */
    @Nullable
    public String getBrand() {
        return brand;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BRAND} attribute with the specified value.
     *
     * @param brand new value for {@value #ATTRIBUTE_NAME_BRAND} attribute.
     */
    protected void setBrand(@Nullable final String brand) {
        this.brand = brand;
    }

    // ------------------------------------------------------------------------------------------------------------ size

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SIZE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SIZE} attribute.
     */
    @Nullable
    public String getSize() {
        return size;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SIZE} attribute with the specified value.
     *
     * @param size new value for {@value #ATTRIBUTE_NAME_SIZE} attribute.
     */
    protected void setSize(@Nullable final String size) {
        this.size = size;
    }

    // ---------------------------------------------------------------------------------------------------- leadTimeDays

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    @Nonnull
    public Integer getLeadTimeDays() {
        return leadTimeDays;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute with the specified value.
     *
     * @param leadTimeDays new value for {@value #ATTRIBUTE_NAME_LEAD_TIME_DAYS} attribute.
     */
    protected void setLeadTimeDays(@Nonnull final Integer leadTimeDays) {
        this.leadTimeDays = leadTimeDays;
    }

    // ------------------------------------------------------------------------------------------------ quantityPerOuter

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute.
     */
    @Nonnull
    public Integer getQuantityPerOuter() {
        return quantityPerOuter;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute with the specified value.
     *
     * @param quantityPerOuter new value for {@value #ATTRIBUTE_NAME_QUANTITY_PER_OUTER} attribute.
     */
    protected void setQuantityPerOuter(@Nonnull final Integer quantityPerOuter) {
        this.quantityPerOuter = quantityPerOuter;
    }

    // -------------------------------------------------------------------------------------------------- isChillerStock

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute.
     */
    @Nonnull
    public Boolean getIsChillerStock() {
        return isChillerStock;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute with the specified value.
     *
     * @param isChillerStock new value for {@value #ATTRIBUTE_NAME_IS_CHILLER_STOCK} attribute.
     */
    protected void setIsChillerStock(@Nonnull final Boolean isChillerStock) {
        this.isChillerStock = isChillerStock;
    }

    // --------------------------------------------------------------------------------------------------------- barcode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BARCODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BARCODE} attribute.
     */
    @Nullable
    public String getBarcode() {
        return barcode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BARCODE} attribute with the specified value.
     *
     * @param barcode new value for {@value #ATTRIBUTE_NAME_BARCODE} attribute.
     */
    protected void setBarcode(@Nullable final String barcode) {
        this.barcode = barcode;
    }

    // --------------------------------------------------------------------------------------------------------- taxRate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    @Nonnull
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAX_RATE} attribute with the specified value.
     *
     * @param taxRate new value for {@value #ATTRIBUTE_NAME_TAX_RATE} attribute.
     */
    protected void setTaxRate(@Nonnull final BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    // ------------------------------------------------------------------------------------------------------- unitPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    @Nonnull
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute with the specified value.
     *
     * @param unitPrice new value for {@value #ATTRIBUTE_NAME_UNIT_PRICE} attribute.
     */
    protected void setUnitPrice(@Nonnull final BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    // ------------------------------------------------------------------------------------------ recommendedRetailPrice

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute.
     */
    @Nullable
    public BigDecimal getRecommendedRetailPrice() {
        return recommendedRetailPrice;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute with the specified value.
     *
     * @param recommendedRetailPrice new value for {@value #ATTRIBUTE_NAME_RECOMMENDED_RETAIL_PRICE} attribute.
     */
    protected void setRecommendedRetailPrice(@Nullable final BigDecimal recommendedRetailPrice) {
        this.recommendedRetailPrice = recommendedRetailPrice;
    }

    // -------------------------------------------------------------------------------------------- typicalWeightPerUnit

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute.
     */
    @Nonnull
    public BigDecimal getTypicalWeightPerUnit() {
        return typicalWeightPerUnit;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute with the specified value.
     *
     * @param typicalWeightPerUnit new value for {@value #ATTRIBUTE_NAME_TYPICAL_WEIGHT_PER_UNIT} attribute.
     */
    protected void setTypicalWeightPerUnit(@Nonnull final BigDecimal typicalWeightPerUnit) {
        this.typicalWeightPerUnit = typicalWeightPerUnit;
    }

    // ----------------------------------------------------------------------------------------------- marketingComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute.
     */
    @Nullable
    public String getMarketingComments() {
        return marketingComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute with the specified value.
     *
     * @param marketingComments new value for {@value #ATTRIBUTE_NAME_MARKETING_COMMENTS} attribute.
     */
    protected void setMarketingComments(@Nullable final String marketingComments) {
        this.marketingComments = marketingComments;
    }

    // ------------------------------------------------------------------------------------------------ internalComments

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    @Nullable
    public String getInternalComments() {
        return internalComments;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute with the specified value.
     *
     * @param internalComments new value for {@value #ATTRIBUTE_NAME_INTERNAL_COMMENTS} attribute.
     */
    protected void setInternalComments(@Nullable final String internalComments) {
        this.internalComments = internalComments;
    }

    // ----------------------------------------------------------------------------------------------------------- photo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     */
    @Nullable
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PHOTO} attribute with the specified value.
     *
     * @param photo new value for {@value #ATTRIBUTE_NAME_PHOTO} attribute.
     */
    protected void setPhoto(@Nullable final byte[] photo) {
        this.photo = photo;
    }

    // ---------------------------------------------------------------------------------------------------- customFields

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     */
    @Nullable
    public String getCustomFields() {
        return customFields;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute with the specified value.
     *
     * @param customFields new value for {@value #ATTRIBUTE_NAME_CUSTOM_FIELDS} attribute.
     */
    protected void setCustomFields(@Nullable final String customFields) {
        this.customFields = customFields;
    }

    // ------------------------------------------------------------------------------------------------------------ tags

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TAGS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TAGS} attribute.
     */
    @Nullable
    public String getTags() {
        return tags;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAGS} attribute with the specified value.
     *
     * @param tags new value for {@value #ATTRIBUTE_NAME_TAGS} attribute.
     */
    protected void setTags(@Nullable final String tags) {
        this.tags = tags;
    }

    // --------------------------------------------------------------------------------------------------- searchDetails

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute.
     */
    @Nonnull
    public String getSearchDetails() {
        return searchDetails;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute with the specified value.
     *
     * @param searchDetails new value for {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute.
     */
    protected void setSearchDetails(@Nonnull final String searchDetails) {
        this.searchDetails = searchDetails;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    @Nonnull
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(@Nonnull final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    @Nonnull
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(@Nonnull final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // --------------------------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    @Nonnull
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(@Nonnull final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer stockItemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_STOCK_ITEM_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_STOCK_ITEM_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_STOCK_ITEM_NAME
    )
    private String stockItemName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SUPPLIER_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer supplierId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_COLOR_ID,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private Integer colorId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_UNIT_PACKAGE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer unitPackageId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_OUTER_PACKAGE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer outerPackageId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_BRAND)
    @Column(name = COLUMN_NAME_BRAND,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_BRAND
    )
    private String brand;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_SIZE)
    @Column(name = COLUMN_NAME_SIZE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SIZE
    )
    private String size;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer leadTimeDays;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY_PER_OUTER,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer quantityPerOuter;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_IS_CHILLER_STOCK,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isChillerStock;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Size(max = COLUMN_LENGTH_BARCODE)
    @Column(name = COLUMN_NAME_BARCODE,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_BARCODE
    )
    private String barcode;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_TAX_RATE - COLUMN_SCALE_TAX_RATE, fraction = COLUMN_SCALE_TAX_RATE)
    @NotNull
    @Column(name = COLUMN_NAME_TAX_RATE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TAX_RATE,
            scale = COLUMN_SCALE_TAX_RATE
    )
    private BigDecimal taxRate;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_UNIT_PRICE - COLUMN_SCALE_UNIT_PRICE, fraction = COLUMN_SCALE_UNIT_PRICE)
    @NotNull
    @Column(name = COLUMN_NAME_UNIT_PRICE,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_UNIT_PRICE,
            scale = COLUMN_SCALE_UNIT_PRICE
    )
    private BigDecimal unitPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Digits(integer = COLUMN_PRECISION_RECOMMENDED_RETAIL_PRICE - COLUMN_SCALE_RECOMMENDED_RETAIL_PRICE, fraction = COLUMN_SCALE_RECOMMENDED_RETAIL_PRICE)
    @Column(name = COLUMN_NAME_RECOMMENDED_RETAIL_PRICE,
            nullable = true,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_RECOMMENDED_RETAIL_PRICE,
            scale = COLUMN_SCALE_RECOMMENDED_RETAIL_PRICE
    )
    private BigDecimal recommendedRetailPrice;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Digits(integer = COLUMN_PRECISION_TYPICAL_WEIGHT_PER_UNIT - COLUMN_SCALE_TYPICAL_WEIGHT_PER_UNIT, fraction = COLUMN_SCALE_TYPICAL_WEIGHT_PER_UNIT)
    @NotNull
    @Column(name = COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT,
            nullable = false,
            insertable = true,
            updatable = true,
            precision = COLUMN_PRECISION_TYPICAL_WEIGHT_PER_UNIT,
            scale = COLUMN_SCALE_TYPICAL_WEIGHT_PER_UNIT
    )
    private BigDecimal typicalWeightPerUnit;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_MARKETING_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String marketingComments;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String internalComments;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_PHOTO,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private byte[] photo;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_CUSTOM_FIELDS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String customFields;

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Column(name = COLUMN_NAME_TAGS,
            nullable = true,
            insertable = true,
            updatable = true
    )
    private String tags;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_SEARCH_DETAILS,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private String searchDetails;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime validTo;
}

