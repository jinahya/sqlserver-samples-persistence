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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductInventoryId;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Production.ProductInventory} table.
 */
@Entity(name = "ProductionProductInventory")
@Table(schema = MappedProductInventory.TABLE_SCHEMA,
       name = MappedProductInventory.TABLE_NAME,
       comment = "Product inventory information.",
       check = {
               @CheckConstraint(
                       name = "CK_ProductInventory_Bin",
                       constraint = "([Bin]>=(0) AND [Bin]<=(100))"
               ),
               @CheckConstraint(
                       name = "CK_ProductInventory_Shelf",
                       constraint = "([Shelf] like '[A-Za-z]' OR [Shelf]='N/A')"
               )
       }
)
@IdClass(ProductInventoryId.class)
public class ProductInventory
        extends MappedProductInventory {

    /**
     * The name of the attribute of the {@code FK_ProductInventory_Location_LocationID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    /**
     * The name of the attribute of the {@code FK_ProductInventory_Product_ProductID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code product} and {@code location}.
     *
     * @param product  the {@code Product} to reference.
     * @param location the {@code Location} to reference.
     * @return a new instance which references {@code product} and {@code location}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static ProductInventory of(final Product product,
                                      final Location location) {
        Objects.requireNonNull(product, "product is null");
        Objects.requireNonNull(location, "location is null");
        final var instance = new ProductInventory();
        instance.setProduct(product);
        instance.setLocation(location);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductInventory() {
        super();
    }

    // ------------------------------------------------------------------------------------ location

    /**
     * Returns the {@code Location} which the {@code FK_ProductInventory_Location_LocationID}
     * foreign key references.
     *
     * @return the {@code Location} referenced; {@code null} when none is.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Replaces the {@code Location} which the {@code FK_ProductInventory_Location_LocationID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param location the {@code Location} to reference; {@code null} for none.
     */
    private void setLocation(final Location location) {
        this.location = location;
        setLocationId(
                Optional.ofNullable(location).map(MappedLocation::getLocationId).orElse(null));
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductInventory_Product_ProductID} foreign
     * key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ProductInventory_Product_ProductID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    private void setProduct(final Product product) {
        this.product = product;
        setProductId(Optional.ofNullable(product).map(Product::getProductId).orElse(null));
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_LOCATION_ID,
                referencedColumnName = MappedLocation.COLUMN_NAME_LOCATION_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Location location;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;
}

