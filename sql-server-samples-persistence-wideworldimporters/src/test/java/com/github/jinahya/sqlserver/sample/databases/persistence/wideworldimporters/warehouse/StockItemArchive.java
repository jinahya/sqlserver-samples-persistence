package com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.warehouse;

/*-
 * #%L
 * sql-server-samples-persistence-wideworldimporters
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

import com.github.jinahya.sqlserver.sample.databases.persistence.wideworldimporters.mapped.warehouse.MappedStockItemArchive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A plain class which holds a row of the {@code Warehouse.StockItems_Archive} table, which no entity can map: a history
 * table of a system-versioned temporal table, which has no key.
 * <p>
 * The public constructor takes every column, in the order the table declares them, for a
 * {@link jakarta.persistence.ConstructorResult @ConstructorResult} or a row mapper.
 */
public class StockItemArchive extends MappedStockItemArchive {

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new empty instance.
     */
    protected StockItemArchive() {
        super();
    }

    /**
     * Creates a new instance of a row.
     *
     * @param stockItemId            the value of the {@value #COLUMN_NAME_STOCK_ITEM_ID} column.
     * @param stockItemName          the value of the {@value #COLUMN_NAME_STOCK_ITEM_NAME} column.
     * @param supplierId             the value of the {@value #COLUMN_NAME_SUPPLIER_ID} column.
     * @param colorId                the value of the {@value #COLUMN_NAME_COLOR_ID} column.
     * @param unitPackageId          the value of the {@value #COLUMN_NAME_UNIT_PACKAGE_ID} column.
     * @param outerPackageId         the value of the {@value #COLUMN_NAME_OUTER_PACKAGE_ID} column.
     * @param brand                  the value of the {@value #COLUMN_NAME_BRAND} column.
     * @param size                   the value of the {@value #COLUMN_NAME_SIZE} column.
     * @param leadTimeDays           the value of the {@value #COLUMN_NAME_LEAD_TIME_DAYS} column.
     * @param quantityPerOuter       the value of the {@value #COLUMN_NAME_QUANTITY_PER_OUTER} column.
     * @param isChillerStock         the value of the {@value #COLUMN_NAME_IS_CHILLER_STOCK} column.
     * @param barcode                the value of the {@value #COLUMN_NAME_BARCODE} column.
     * @param taxRate                the value of the {@value #COLUMN_NAME_TAX_RATE} column.
     * @param unitPrice              the value of the {@value #COLUMN_NAME_UNIT_PRICE} column.
     * @param recommendedRetailPrice the value of the {@value #COLUMN_NAME_RECOMMENDED_RETAIL_PRICE} column.
     * @param typicalWeightPerUnit   the value of the {@value #COLUMN_NAME_TYPICAL_WEIGHT_PER_UNIT} column.
     * @param marketingComments      the value of the {@value #COLUMN_NAME_MARKETING_COMMENTS} column.
     * @param internalComments       the value of the {@value #COLUMN_NAME_INTERNAL_COMMENTS} column.
     * @param photo                  the value of the {@value #COLUMN_NAME_PHOTO} column.
     * @param customFields           the value of the {@value #COLUMN_NAME_CUSTOM_FIELDS} column.
     * @param tags                   the value of the {@value #COLUMN_NAME_TAGS} column.
     * @param searchDetails          the value of the {@value #COLUMN_NAME_SEARCH_DETAILS} column.
     * @param lastEditedBy           the value of the {@value #COLUMN_NAME_LAST_EDITED_BY} column.
     * @param validFrom              the value of the {@value #COLUMN_NAME_VALID_FROM} column.
     * @param validTo                the value of the {@value #COLUMN_NAME_VALID_TO} column.
     */
    @SuppressWarnings({"java:S107"}) // Methods should not have too many parameters
    public StockItemArchive(final Integer stockItemId,
                            final String stockItemName,
                            final Integer supplierId,
                            final Integer colorId,
                            final Integer unitPackageId,
                            final Integer outerPackageId,
                            final String brand,
                            final String size,
                            final Integer leadTimeDays,
                            final Integer quantityPerOuter,
                            final Boolean isChillerStock,
                            final String barcode,
                            final BigDecimal taxRate,
                            final BigDecimal unitPrice,
                            final BigDecimal recommendedRetailPrice,
                            final BigDecimal typicalWeightPerUnit,
                            final String marketingComments,
                            final String internalComments,
                            final byte[] photo,
                            final String customFields,
                            final String tags,
                            final String searchDetails,
                            final Integer lastEditedBy,
                            final LocalDateTime validFrom,
                            final LocalDateTime validTo) {
        super();
        setStockItemId(stockItemId);
        setStockItemName(stockItemName);
        setSupplierId(supplierId);
        setColorId(colorId);
        setUnitPackageId(unitPackageId);
        setOuterPackageId(outerPackageId);
        setBrand(brand);
        setSize(size);
        setLeadTimeDays(leadTimeDays);
        setQuantityPerOuter(quantityPerOuter);
        setIsChillerStock(isChillerStock);
        setBarcode(barcode);
        setTaxRate(taxRate);
        setUnitPrice(unitPrice);
        setRecommendedRetailPrice(recommendedRetailPrice);
        setTypicalWeightPerUnit(typicalWeightPerUnit);
        setMarketingComments(marketingComments);
        setInternalComments(internalComments);
        setPhoto(photo);
        setCustomFields(customFields);
        setTags(tags);
        setSearchDetails(searchDetails);
        setLastEditedBy(lastEditedBy);
        setValidFrom(validFrom);
        setValidTo(validTo);
    }
}

