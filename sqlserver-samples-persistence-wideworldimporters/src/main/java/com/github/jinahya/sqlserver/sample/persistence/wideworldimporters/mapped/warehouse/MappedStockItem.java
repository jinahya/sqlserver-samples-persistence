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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsComputedColumn;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.StockItems} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Warehouse.StockItems_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_SUPPLIER_ID}, {@value #COLUMN_NAME_COLOR_ID}, {@value #COLUMN_NAME_UNIT_PACKAGE_ID},
 * {@value #COLUMN_NAME_OUTER_PACKAGE_ID}, {@value #COLUMN_NAME_LAST_EDITED_BY} columns are mapped read-only, as a
 * foreign key; the extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Warehouse.StockItems}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemID}</th>
 *     <td>Numeric ID used for reference to a stock item within the database</td></tr>
 * <tr><th scope="row">{@code StockItemName}</th>
 *     <td>Full name of a stock item (but not a full description)</td></tr>
 * <tr><th scope="row">{@code SupplierID}</th><td>Usual supplier for this stock item</td></tr>
 * <tr><th scope="row">{@code ColorID}</th><td>Color (optional) for this stock item</td></tr>
 * <tr><th scope="row">{@code UnitPackageID}</th>
 *     <td>Usual package for selling units of this stock item</td></tr>
 * <tr><th scope="row">{@code OuterPackageID}</th>
 *     <td>Usual package for selling outers of this stock item (ie cartons, boxes, etc.)</td></tr>
 * <tr><th scope="row">{@code Brand}</th>
 *     <td>Brand for the stock item (if the item is branded)</td></tr>
 * <tr><th scope="row">{@code Size}</th><td>Size of this item (eg: 100mm)</td></tr>
 * <tr><th scope="row">{@code LeadTimeDays}</th>
 *     <td>Number of days typically taken from order to receipt of this stock item</td></tr>
 * <tr><th scope="row">{@code QuantityPerOuter}</th>
 *     <td>Quantity of the stock item in an outer package</td></tr>
 * <tr><th scope="row">{@code IsChillerStock}</th>
 *     <td>Does this stock item need to be in a chiller?</td></tr>
 * <tr><th scope="row">{@code Barcode}</th><td>Barcode for this stock item</td></tr>
 * <tr><th scope="row">{@code TaxRate}</th><td>Tax rate to be applied</td></tr>
 * <tr><th scope="row">{@code UnitPrice}</th>
 *     <td>Selling price (ex-tax) for one unit of this product</td></tr>
 * <tr><th scope="row">{@code RecommendedRetailPrice}</th>
 *     <td>Recommended retail price for this stock item</td></tr>
 * <tr><th scope="row">{@code TypicalWeightPerUnit}</th>
 *     <td>Typical weight for one unit of this product (packaged)</td></tr>
 * <tr><th scope="row">{@code MarketingComments}</th>
 *     <td>Marketing comments for this stock item (shared outside the organization)</td></tr>
 * <tr><th scope="row">{@code InternalComments}</th>
 *     <td>Internal comments (not exposed outside organization)</td></tr>
 * <tr><th scope="row">{@code Photo}</th><td>Photo of the product</td></tr>
 * <tr><th scope="row">{@code CustomFields}</th><td>Custom fields added by system users</td></tr>
 * <tr><th scope="row">{@code Tags}</th>
 *     <td>Advertising tags associated with this stock item (JSON array retrieved from
 *     CustomFields)</td></tr>
 * <tr><th scope="row">{@code SearchDetails}</th>
 *     <td>Combination of columns used by full text search</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Warehouse.StockItems}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code StockItemID}</th><td>{@code (NEXT VALUE FOR [Sequences].[StockItemID])}</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Computed columns of {@code Warehouse.StockItems}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th><th scope="col">Persisted</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code Tags}</th>
 *     <td>{@code (json_query([CustomFields],N'$.Tags'))}</td><td>{@code false}</td></tr>
 * <tr><th scope="row">{@code SearchDetails}</th>
 *     <td>{@code (concat([StockItemName],N' ',[MarketingComments]))}</td><td>{@code false}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/json-query-transact-sql">JSON_QUERY</a>
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/concat-transact-sql">CONCAT</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedStockItem.TABLE_SCHEMA,
        table = MappedStockItem.TABLE_NAME,
        value = "Main entity table for stock items"
)
@MsTemporal(
        historyTable = "Warehouse.StockItems_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
public abstract class MappedStockItem {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "StockItems";

    // --------------------------------------------------------------------------------- StockItemID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.StockItemID, NOT NULL} column. The value is {@value}.
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
     * The name of the {@code nvarchar(max), computed as (json_query([CustomFields],N'$.Tags'))} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_TAGS = "Tags";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TAGS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TAGS = "tags";

    // ------------------------------------------------------------------------------- SearchDetails

    /**
     * The name of the {@code nvarchar(max), computed as (concat([StockItemName],N' ',[MarketingComments])), NOT NULL}
     * column. The value is {@value}.
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
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
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
    protected MappedStockItem() {
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
        if (!(obj instanceof MappedStockItem that)) {
            return false;
        }
        return getStockItemId() != null
               && getStockItemId().equals(that.getStockItemId());
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
        return MappedStockItem.class.hashCode();
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
    public void setStockItemName(final String stockItemName) {
        this.stockItemName = stockItemName;
    }

    // ---------------------------------------------------------------------------------- supplierId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute.
     */
    protected Integer getSupplierId() {
        return supplierId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
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
    protected Integer getColorId() {
        return colorId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_COLOR_ID} attribute with the specified value. The attribute is
     * read-only; the extending entity calls this to keep it in step with the association which writes the column.
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
    protected Integer getUnitPackageId() {
        return unitPackageId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_UNIT_PACKAGE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
    protected Integer getOuterPackageId() {
        return outerPackageId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_OUTER_PACKAGE_ID} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
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
    public void setBrand(final String brand) {
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
    public void setSize(final String size) {
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
    public void setLeadTimeDays(final Integer leadTimeDays) {
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
    public void setQuantityPerOuter(final Integer quantityPerOuter) {
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
    public void setIsChillerStock(final Boolean isChillerStock) {
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
    public void setBarcode(final String barcode) {
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
    public void setTaxRate(final BigDecimal taxRate) {
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
    public void setUnitPrice(final BigDecimal unitPrice) {
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
    public void setRecommendedRetailPrice(final BigDecimal recommendedRetailPrice) {
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
    public void setTypicalWeightPerUnit(final BigDecimal typicalWeightPerUnit) {
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
    public void setMarketingComments(final String marketingComments) {
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
    public void setInternalComments(final String internalComments) {
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
    public void setPhoto(final byte[] photo) {
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
    public void setCustomFields(final String customFields) {
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_TAGS} attribute with the specified value. The database writes
     * the column; this is for the provider and the extending class only.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_SEARCH_DETAILS} attribute with the specified value. The
     * database writes the column; this is for the provider and the extending class only.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.StockItemID", schema = "Sequences",
                       sequenceName = "StockItemID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.StockItemID")
    @Column(name = COLUMN_NAME_STOCK_ITEM_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_ID,
                          value = "Numeric ID used for reference to a stock item within the "
                                  + "database")
    @MsDefault(name = "DF_Warehouse_StockItems_StockItemID", value = "(NEXT VALUE FOR [Sequences].[StockItemID])")
    private Integer stockItemId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_STOCK_ITEM_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_STOCK_ITEM_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_STOCK_ITEM_NAME)
    @MsDescription.Column(name = COLUMN_NAME_STOCK_ITEM_NAME,
                          value = "Full name of a stock item (but not a full description)")
    private String stockItemName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SUPPLIER_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SUPPLIER_ID,
                          value = "Usual supplier for this stock item")
    private Integer supplierId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_COLOR_ID, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_COLOR_ID,
                          value = "Color (optional) for this stock item")
    private Integer colorId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PACKAGE_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_UNIT_PACKAGE_ID,
                          value = "Usual package for selling units of this stock item")
    private Integer unitPackageId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_OUTER_PACKAGE_ID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_OUTER_PACKAGE_ID,
                          value = "Usual package for selling outers of this stock item (ie "
                                  + "cartons, boxes, etc.)")
    private Integer outerPackageId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BRAND)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BRAND, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BRAND)
    @MsDescription.Column(name = COLUMN_NAME_BRAND,
                          value = "Brand for the stock item (if the item is branded)")
    private String brand;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SIZE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_SIZE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_SIZE)
    @MsDescription.Column(name = COLUMN_NAME_SIZE, value = "Size of this item (eg: 100mm)")
    private String size;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LEAD_TIME_DAYS, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_LEAD_TIME_DAYS,
                          value = "Number of days typically taken from order to receipt of this "
                                  + "stock item")
    private Integer leadTimeDays;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY_PER_OUTER, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_QUANTITY_PER_OUTER,
                          value = "Quantity of the stock item in an outer package")
    private Integer quantityPerOuter;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_CHILLER_STOCK, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_IS_CHILLER_STOCK,
                          value = "Does this stock item need to be in a chiller?")
    private Boolean isChillerStock;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_BARCODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_BARCODE, nullable = true, insertable = true, updatable = true, length = COLUMN_LENGTH_BARCODE)
    @MsDescription.Column(name = COLUMN_NAME_BARCODE, value = "Barcode for this stock item")
    private String barcode;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TAX_RATE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TAX_RATE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TAX_RATE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_TAX_RATE, scale = COLUMN_SCALE_TAX_RATE)
    @MsDescription.Column(name = COLUMN_NAME_TAX_RATE, value = "Tax rate to be applied")
    private BigDecimal taxRate;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_UNIT_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_UNIT_PRICE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_UNIT_PRICE, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_UNIT_PRICE, scale = COLUMN_SCALE_UNIT_PRICE)
    @MsDescription.Column(name = COLUMN_NAME_UNIT_PRICE,
                          value = "Selling price (ex-tax) for one unit of this product")
    private BigDecimal unitPrice;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_RECOMMENDED_RETAIL_PRICE)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_RECOMMENDED_RETAIL_PRICE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RECOMMENDED_RETAIL_PRICE, nullable = true, insertable = true, updatable = true, precision = COLUMN_PRECISION_RECOMMENDED_RETAIL_PRICE, scale = COLUMN_SCALE_RECOMMENDED_RETAIL_PRICE)
    @MsDescription.Column(name = COLUMN_NAME_RECOMMENDED_RETAIL_PRICE,
                          value = "Recommended retail price for this stock item")
    private BigDecimal recommendedRetailPrice;

    // ---------------------------------------------------------------------------------------------
    @DecimalMax(ATTRIBUTE_DECIMAL_MAX_TYPICAL_WEIGHT_PER_UNIT)
    @DecimalMin(ATTRIBUTE_DECIMAL_MIN_TYPICAL_WEIGHT_PER_UNIT)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT, nullable = false, insertable = true, updatable = true, precision = COLUMN_PRECISION_TYPICAL_WEIGHT_PER_UNIT, scale = COLUMN_SCALE_TYPICAL_WEIGHT_PER_UNIT)
    @MsDescription.Column(name = COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT,
                          value = "Typical weight for one unit of this product (packaged)")
    private BigDecimal typicalWeightPerUnit;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 126 bytes (63 characters)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_MARKETING_COMMENTS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MARKETING_COMMENTS,
                          value = "Marketing comments for this stock item (shared outside the "
                                  + "organization)")
    private String marketingComments;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INTERNAL_COMMENTS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_INTERNAL_COMMENTS,
                          value = "Internal comments (not exposed outside organization)")
    private String internalComments;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: NULL; no row has a value
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_PHOTO, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_PHOTO, value = "Photo of the product")
    private byte[] photo;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 220 bytes (110 characters)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CUSTOM_FIELDS, nullable = true, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_CUSTOM_FIELDS,
                          value = "Custom fields added by system users")
    private String customFields;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 90 bytes (45 characters)
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_TAGS, nullable = true, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_TAGS,
                          value = "Advertising tags associated with this stock item (JSON array "
                                  + "retrieved from CustomFields)")
    @MsComputedColumn("(json_query([CustomFields],N'$.Tags'))")
    private String tags;

    // ---------------------------------------------------------------------------------------------
    // DATA.MAX: 208 bytes (104 characters)
    // read-only: a computed column; SQL Server derives it, and rejects a value for it (error 271)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SEARCH_DETAILS, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SEARCH_DETAILS,
                          value = "Combination of columns used by full text search")
    @MsComputedColumn("(concat([StockItemName],N' ',[MarketingComments]))")
    private String searchDetails;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

