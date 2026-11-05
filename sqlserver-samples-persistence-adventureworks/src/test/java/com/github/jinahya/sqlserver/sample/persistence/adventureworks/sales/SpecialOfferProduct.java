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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSpecialOffer;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.MappedSpecialOfferProduct;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales.SpecialOfferProductId;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.production.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * An entity which maps the {@code Sales.SpecialOfferProduct} table.
 */
@Entity(name = "SalesSpecialOfferProduct")
@Table(schema = MappedSpecialOfferProduct.TABLE_SCHEMA,
       name = MappedSpecialOfferProduct.TABLE_NAME,
       comment = "Cross-reference table mapping products to special offer discounts.",
       uniqueConstraints = {
               @UniqueConstraint(
                       name = "AK_SpecialOfferProduct_rowguid",
                       columnNames = {
                               SpecialOfferProduct.COLUMN_NAME_ROWGUID
                       }
               )
       }
)
@IdClass(SpecialOfferProductId.class)
public class SpecialOfferProduct
        extends MappedSpecialOfferProduct {

    /**
     * The name of the attribute of the {@code FK_SpecialOfferProduct_Product_ProductID} foreign
     * key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_SpecialOfferProduct_SpecialOffer_SpecialOfferID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER = "specialOffer";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code specialOffer} and {@code product}.
     *
     * @param specialOffer the {@code SpecialOffer} to reference.
     * @param product      the {@code Product} to reference.
     * @return a new instance which references {@code specialOffer} and {@code product}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static SpecialOfferProduct of(final SpecialOffer specialOffer,
                                         final Product product) {
        Objects.requireNonNull(specialOffer, "specialOffer is null");
        Objects.requireNonNull(product, "product is null");
        final var instance = new SpecialOfferProduct();
        instance.setSpecialOffer(specialOffer);
        instance.setProduct(product);
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SpecialOfferProduct() {
        super();
    }

    // ------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_SpecialOfferProduct_Product_ProductID}
     * foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_SpecialOfferProduct_Product_ProductID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    private void setProduct(final Product product) {
        this.product = product;
        setProductId(Optional.ofNullable(product).map(Product::getProductId).orElse(null));
    }

    // -------------------------------------------------------------------------------- specialOffer

    /**
     * Returns the {@code SpecialOffer} which the
     * {@code FK_SpecialOfferProduct_SpecialOffer_SpecialOfferID} foreign key references.
     *
     * @return the {@code SpecialOffer} referenced; {@code null} when none is.
     */
    public SpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    /**
     * Replaces the {@code SpecialOffer} which the
     * {@code FK_SpecialOfferProduct_SpecialOffer_SpecialOfferID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param specialOffer the {@code SpecialOffer} to reference; {@code null} for none.
     */
    private void setSpecialOffer(final SpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
        setSpecialOfferId(Optional.ofNullable(specialOffer).map(SpecialOffer::getSpecialOfferId)
                                  .orElse(null));
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

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SPECIAL_OFFER_ID,
                referencedColumnName = MappedSpecialOffer.COLUMN_NAME_SPECIAL_OFFER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SpecialOffer specialOffer;
}

