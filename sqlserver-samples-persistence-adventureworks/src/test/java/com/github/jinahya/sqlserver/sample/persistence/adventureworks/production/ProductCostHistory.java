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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductCostHistory;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductCostHistoryId;
import jakarta.persistence.CheckConstraint;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Production.ProductCostHistory} table.
 */
@Entity(name = "ProductionProductCostHistory")
@Table(schema = MappedProductCostHistory.TABLE_SCHEMA,
       name = MappedProductCostHistory.TABLE_NAME,
       comment = "Changes in the cost of a product over time.",
       check = {
               @CheckConstraint(
                       name = "CK_ProductCostHistory_EndDate",
                       constraint = "([EndDate]>=[StartDate] OR [EndDate] IS NULL)"
               ),
               @CheckConstraint(
                       name = "CK_ProductCostHistory_StandardCost",
                       constraint = "([StandardCost]>=(0.00))"
               )
       }
)
@IdClass(ProductCostHistoryId.class)
public class ProductCostHistory
        extends MappedProductCostHistory {

    /**
     * The name of the attribute of the {@code FK_ProductCostHistory_Product_ProductID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code product}, with {@code startDate}.
     *
     * @param product   the {@code Product} to reference.
     * @param startDate the value of the {@value #COLUMN_NAME_START_DATE} column.
     * @return a new instance which references {@code product}, with {@code startDate}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static ProductCostHistory of(final Product product,
                                        final LocalDateTime startDate) {
        Objects.requireNonNull(product, "product is null");
        Objects.requireNonNull(startDate, "startDate is null");
        final var instance = new ProductCostHistory();
        instance.setProduct(product);
        instance.setStartDate(startDate);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductCostHistory() {
        super();
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_ProductCostHistory_Product_ProductID} foreign
     * key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_ProductCostHistory_Product_ProductID}
     * foreign key references, and the value of the column it consists of.
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
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;
}

