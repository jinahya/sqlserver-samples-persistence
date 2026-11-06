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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedIllustration;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModel;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModelIllustration;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductModelIllustrationId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * An entity which maps the {@code Production.ProductModelIllustration} table.
 * <p>
 * Each attribute of the embedded {@link ProductModelIllustrationId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "ProductionProductModelIllustration")
@Table(schema = MappedProductModelIllustration.TABLE_SCHEMA,
       name = MappedProductModelIllustration.TABLE_NAME,
       comment = "Cross-reference table mapping product models and illustrations.")
public class ProductModelIllustration
        extends MappedProductModelIllustration {

    /**
     * The name of the attribute of the
     * {@code FK_ProductModelIllustration_ProductModel_ProductModelID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL = "productModel";

    /**
     * The name of the attribute of the
     * {@code FK_ProductModelIllustration_Illustration_IllustrationID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ILLUSTRATION = "illustration";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code productModel} and {@code illustration}.
     *
     * @param productModel the {@code ProductModel} to reference.
     * @param illustration the {@code Illustration} to reference.
     * @return a new instance which references {@code productModel} and {@code illustration}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static ProductModelIllustration of(final ProductModel productModel,
                                              final Illustration illustration) {
        Objects.requireNonNull(productModel, "productModel is null");
        Objects.requireNonNull(illustration, "illustration is null");
        final var instance = new ProductModelIllustration();
        instance.productModel = productModel;
        instance.illustration = illustration;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductModelIllustration() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected ProductModelIllustrationId getId_() {
        return id_;
    }

    // -------------------------------------------------------------------------------- productModel

    /**
     * Returns the {@code ProductModel} which the
     * {@code FK_ProductModelIllustration_ProductModel_ProductModelID} foreign key references.
     *
     * @return the {@code ProductModel} referenced; {@code null} when none is.
     */
    public ProductModel getProductModel() {
        return productModel;
    }

    // -------------------------------------------------------------------------------- illustration

    /**
     * Returns the {@code Illustration} which the
     * {@code FK_ProductModelIllustration_Illustration_IllustrationID} foreign key references.
     *
     * @return the {@code Illustration} referenced; {@code null} when none is.
     */
    public Illustration getIllustration() {
        return illustration;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private ProductModelIllustrationId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductModelIllustrationId.ATTRIBUTE_NAME_PRODUCT_MODEL_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_MODEL_ID,
                referencedColumnName = MappedProductModel.COLUMN_NAME_PRODUCT_MODEL_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private ProductModel productModel;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductModelIllustrationId.ATTRIBUTE_NAME_ILLUSTRATION_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ILLUSTRATION_ID,
                referencedColumnName = MappedIllustration.COLUMN_NAME_ILLUSTRATION_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Illustration illustration;
}

