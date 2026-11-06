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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedCulture;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductDescription;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModel;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModelProductDescriptionCulture;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.ProductModelProductDescriptionCultureId;
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
 * An entity which maps the {@code Production.ProductModelProductDescriptionCulture} table.
 * <p>
 * Each attribute of the embedded {@link ProductModelProductDescriptionCultureId} is derived, with
 * {@link MapsId @MapsId}, from the association which references the same column.
 */
@Entity(name = "ProductionProductModelProductDescriptionCulture")
@Table(schema = MappedProductModelProductDescriptionCulture.TABLE_SCHEMA,
       name = MappedProductModelProductDescriptionCulture.TABLE_NAME,
       comment = "Cross-reference table mapping product descriptions and the language the "
                 + "description is written in.")
public class ProductModelProductDescriptionCulture
        extends MappedProductModelProductDescriptionCulture {

    /**
     * The name of the attribute of the
     * {@code FK_ProductModelProductDescriptionCulture_ProductDescription_ProductDescriptionID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_DESCRIPTION = "productDescription";

    /**
     * The name of the attribute of the
     * {@code FK_ProductModelProductDescriptionCulture_Culture_CultureID} foreign key. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CULTURE = "culture";

    /**
     * The name of the attribute of the
     * {@code FK_ProductModelProductDescriptionCulture_ProductModel_ProductModelID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL = "productModel";

    // ---------------------------------------------------------------------- STATIC_FACTORY_METHODS

    /**
     * Creates a new instance which references {@code productModel}, {@code productDescription} and
     * {@code culture}.
     *
     * @param productModel       the {@code ProductModel} to reference.
     * @param productDescription the {@code ProductDescription} to reference.
     * @param culture            the {@code Culture} to reference.
     * @return a new instance which references {@code productModel}, {@code productDescription} and
     *         {@code culture}.
     * @throws NullPointerException if any of the arguments is {@code null}.
     */
    public static ProductModelProductDescriptionCulture of(final ProductModel productModel,
                                                           final ProductDescription productDescription,
                                                           final Culture culture) {
        Objects.requireNonNull(productModel, "productModel is null");
        Objects.requireNonNull(productDescription, "productDescription is null");
        Objects.requireNonNull(culture, "culture is null");
        final var instance = new ProductModelProductDescriptionCulture();
        instance.productModel = productModel;
        instance.productDescription = productDescription;
        instance.culture = culture;
        return instance;
    }

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductModelProductDescriptionCulture() {
        super();
    }

    // ----------------------------------------------------------------------------------------- id_

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID_} attribute.
     */
    @Override
    protected ProductModelProductDescriptionCultureId getId_() {
        return id_;
    }

    // -------------------------------------------------------------------------- productDescription

    /**
     * Returns the {@code ProductDescription} which the
     * {@code FK_ProductModelProductDescriptionCulture_ProductDescription_ProductDescriptionID}
     * foreign key references.
     *
     * @return the {@code ProductDescription} referenced; {@code null} when none is.
     */
    public ProductDescription getProductDescription() {
        return productDescription;
    }

    // ------------------------------------------------------------------------------------- culture

    /**
     * Returns the {@code Culture} which the
     * {@code FK_ProductModelProductDescriptionCulture_Culture_CultureID} foreign key references.
     *
     * @return the {@code Culture} referenced; {@code null} when none is.
     */
    public Culture getCulture() {
        return culture;
    }

    // -------------------------------------------------------------------------------- productModel

    /**
     * Returns the {@code ProductModel} which the
     * {@code FK_ProductModelProductDescriptionCulture_ProductModel_ProductModelID} foreign key
     * references.
     *
     * @return the {@code ProductModel} referenced; {@code null} when none is.
     */
    public ProductModel getProductModel() {
        return productModel;
    }

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @EmbeddedId
    private ProductModelProductDescriptionCultureId id_;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductModelProductDescriptionCultureId.ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
                referencedColumnName = MappedProductDescription.COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private ProductDescription productDescription;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductModelProductDescriptionCultureId.ATTRIBUTE_NAME_CULTURE_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CULTURE_ID,
                referencedColumnName = MappedCulture.COLUMN_NAME_CULTURE_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private Culture culture;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @NotNull
    @MapsId(ProductModelProductDescriptionCultureId.ATTRIBUTE_NAME_PRODUCT_MODEL_ID)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_MODEL_ID,
                referencedColumnName = MappedProductModel.COLUMN_NAME_PRODUCT_MODEL_ID,
                nullable = false,
                insertable = true,
                updatable = false
    )
    private ProductModel productModel;
}

