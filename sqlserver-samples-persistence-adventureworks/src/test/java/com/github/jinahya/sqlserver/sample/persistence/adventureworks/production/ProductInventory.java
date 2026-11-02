package com.github.jinahya.sqlserver.sample.persistence.adventureworks.production;

/*-
 * #%L
 * sqlserver-samples-persistence-adventureworks
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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedLocation;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductInventory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductInventoryId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.ProductInventory} table.
 */
@Entity(name = "ProductionProductInventory")
@Table(schema = MappedProductInventory.TABLE_SCHEMA, name = MappedProductInventory.TABLE_NAME)
@IdClass(MappedProductInventoryId.class)
public class ProductInventory extends MappedProductInventory {

    /**
     * The name of the attribute of the {@code FK_ProductInventory_Location_LocationID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    /**
     * The name of the attribute of the {@code FK_ProductInventory_Product_ProductID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductInventory() {
        super();
    }

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns the {@code Location} which the {@code FK_ProductInventory_Location_LocationID} foreign key references.
     *
     * @return the {@code Location} referenced; {@code null} when none is.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Replaces the {@code Location} which the {@code FK_ProductInventory_Location_LocationID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param location the {@code Location} to reference; {@code null} for none.
     */
    public void setLocation(final Location location) {
        this.location = location;
        setLocationId(location == null || location.getLocationId() == null
                      ? null : location.getLocationId().intValue());
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductInventory_Product_ProductID} foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ProductInventory_Product_ProductID} foreign key references, and
     * the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                referencedColumnName = MappedLocation.COLUMN_NAME_LOCATION_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Location location;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;
}

