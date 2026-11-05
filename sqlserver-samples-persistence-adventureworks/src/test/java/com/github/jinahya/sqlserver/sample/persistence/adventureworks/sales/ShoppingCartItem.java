package com.github.jinahya.sqlserver.sample.persistence.adventureworks.sales;

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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedShoppingCartItem;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.ShoppingCartItem} table.
 */
@Entity(name = "SalesShoppingCartItem")
@Table(schema = MappedShoppingCartItem.TABLE_SCHEMA,
       name = MappedShoppingCartItem.TABLE_NAME,
       comment = "Contains online customer orders until the order is submitted or cancelled.",
       check = {
               @CheckConstraint(
                       name = "CK_ShoppingCartItem_Quantity",
                       constraint = "([Quantity]>=(1))"
               )
       }
)
public class ShoppingCartItem
        extends MappedShoppingCartItem {

    /**
     * The name of the attribute of the {@code FK_ShoppingCartItem_Product_ProductID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ShoppingCartItem() {
        super();
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ShoppingCartItem_Product_ProductID} foreign
     * key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ShoppingCartItem_Product_ProductID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = true,
                updatable = true
    )
    private Product product;
}

