package com.github.jinahya.mssql.sample.databases.persistence.adventureworks;

/*-
 * #%L
 * mssql-sample-databases-persistence-adventureworks
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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedProduct;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedSpecialOffer;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedSpecialOfferProduct;
import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.MappedSpecialOfferProductId;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Sales.SpecialOfferProduct} table.
 */
@Entity
@Table(schema = MappedSpecialOfferProduct.TABLE_SCHEMA, name = MappedSpecialOfferProduct.TABLE_NAME)
@IdClass(MappedSpecialOfferProductId.class)
public class SpecialOfferProduct extends MappedSpecialOfferProduct {

    /**
     * The name of the attribute of the {@code FK_SpecialOfferProduct_Product_ProductID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT = "product";

    /**
     * The name of the attribute of the {@code FK_SpecialOfferProduct_SpecialOffer_SpecialOfferID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER = "specialOffer";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected SpecialOfferProduct() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- product

    /**
     * Returns the {@code Product} which the {@code FK_SpecialOfferProduct_Product_ProductID} foreign key references.
     *
     * @return the {@code Product} referenced; {@code null} when none is.
     */
    @Nullable
    public Product getProduct() {
        return product;
    }

    /**
     * Replaces the {@code Product} which the {@code FK_SpecialOfferProduct_Product_ProductID} foreign key references,
     * and the
     * value of the column it consists of.
     *
     * @param product the {@code Product} to reference; {@code null} for none.
     */
    public void setProduct(@Nullable final Product product) {
        this.product = product;
        setProductId(product == null ? null : product.getProductId());
    }

    // ---------------------------------------------------------------------------------------------------- specialOffer

    /**
     * Returns the {@code SpecialOffer} which the {@code FK_SpecialOfferProduct_SpecialOffer_SpecialOfferID} foreign
     * key references.
     *
     * @return the {@code SpecialOffer} referenced; {@code null} when none is.
     */
    @Nullable
    public SpecialOffer getSpecialOffer() {
        return specialOffer;
    }

    /**
     * Replaces the {@code SpecialOffer} which the {@code FK_SpecialOfferProduct_SpecialOffer_SpecialOfferID} foreign
     * key references, and the
     * value of the column it consists of.
     *
     * @param specialOffer the {@code SpecialOffer} to reference; {@code null} for none.
     */
    public void setSpecialOffer(@Nullable final SpecialOffer specialOffer) {
        this.specialOffer = specialOffer;
        setSpecialOfferId(specialOffer == null ? null : specialOffer.getSpecialOfferId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_ID,
                referencedColumnName = MappedProduct.COLUMN_NAME_PRODUCT_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Product product;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SPECIAL_OFFER_ID,
                referencedColumnName = MappedSpecialOffer.COLUMN_NAME_SPECIAL_OFFER_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private SpecialOffer specialOffer;
}

