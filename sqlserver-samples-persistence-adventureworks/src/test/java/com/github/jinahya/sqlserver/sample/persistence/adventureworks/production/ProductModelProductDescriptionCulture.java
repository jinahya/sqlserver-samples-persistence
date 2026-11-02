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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModelProductDescriptionCultureId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.ProductModelProductDescriptionCulture} table.
 */
@Entity(name = "ProductionProductModelProductDescriptionCulture")
@Table(schema = MappedProductModelProductDescriptionCulture.TABLE_SCHEMA,
       name = MappedProductModelProductDescriptionCulture.TABLE_NAME)
@IdClass(MappedProductModelProductDescriptionCultureId.class)
public class ProductModelProductDescriptionCulture extends MappedProductModelProductDescriptionCulture {

    /**
     * The name of the attribute of the
     * {@code FK_ProductModelProductDescriptionCulture_ProductDescription_ProductDescriptionID} foreign key. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_DESCRIPTION = "productDescription";

    /**
     * The name of the attribute of the {@code FK_ProductModelProductDescriptionCulture_Culture_CultureID} foreign key.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CULTURE = "culture";

    /**
     * The name of the attribute of the {@code FK_ProductModelProductDescriptionCulture_ProductModel_ProductModelID}
     * foreign key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL = "productModel";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductModelProductDescriptionCulture() {
        super();
    }

    // ---------------------------------------------------------------------------------------------- productDescription

    /**
     * Returns the {@code ProductDescription} which the
     * {@code FK_ProductModelProductDescriptionCulture_ProductDescription_ProductDescriptionID} foreign key references.
     *
     * @return the {@code ProductDescription} referenced; {@code null} when none is.
     */
    public ProductDescription getProductDescription() {
        return productDescription;
    }

    /**
     * Replaces the {@code ProductDescription} which the
     * {@code FK_ProductModelProductDescriptionCulture_ProductDescription_ProductDescriptionID} foreign key references,
     * and the value of the column it consists of.
     *
     * @param productDescription the {@code ProductDescription} to reference; {@code null} for none.
     */
    public void setProductDescription(final ProductDescription productDescription) {
        this.productDescription = productDescription;
        setProductDescriptionId(productDescription == null ? null : productDescription.getProductDescriptionId());
    }

    // --------------------------------------------------------------------------------------------------------- culture

    /**
     * Returns the {@code Culture} which the {@code FK_ProductModelProductDescriptionCulture_Culture_CultureID} foreign
     * key references.
     *
     * @return the {@code Culture} referenced; {@code null} when none is.
     */
    public Culture getCulture() {
        return culture;
    }

    /**
     * Replaces the {@code Culture} which the {@code FK_ProductModelProductDescriptionCulture_Culture_CultureID} foreign
     * key references, and the value of the column it consists of.
     *
     * @param culture the {@code Culture} to reference; {@code null} for none.
     */
    public void setCulture(final Culture culture) {
        this.culture = culture;
        setCultureId(culture == null ? null : culture.getCultureId());
    }

    // ---------------------------------------------------------------------------------------------------- productModel

    /**
     * Returns the {@code ProductModel} which the
     * {@code FK_ProductModelProductDescriptionCulture_ProductModel_ProductModelID} foreign key references.
     *
     * @return the {@code ProductModel} referenced; {@code null} when none is.
     */
    public ProductModel getProductModel() {
        return productModel;
    }

    /**
     * Replaces the {@code ProductModel} which the
     * {@code FK_ProductModelProductDescriptionCulture_ProductModel_ProductModelID} foreign key references, and the
     * value of the column it consists of.
     *
     * @param productModel the {@code ProductModel} to reference; {@code null} for none.
     */
    public void setProductModel(final ProductModel productModel) {
        this.productModel = productModel;
        setProductModelId(productModel == null ? null : productModel.getProductModelId());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
                referencedColumnName = MappedProductDescription.COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private ProductDescription productDescription;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_CULTURE_ID,
                referencedColumnName = MappedCulture.COLUMN_NAME_CULTURE_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Culture culture;

    // -----------------------------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_MODEL_ID,
                referencedColumnName = MappedProductModel.COLUMN_NAME_PRODUCT_MODEL_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private ProductModel productModel;
}

