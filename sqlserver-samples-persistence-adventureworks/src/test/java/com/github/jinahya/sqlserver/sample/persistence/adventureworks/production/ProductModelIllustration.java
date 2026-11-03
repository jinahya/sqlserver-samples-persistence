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
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production.MappedProductModelIllustrationId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * An entity which maps the {@code Production.ProductModelIllustration} table.
 */
@Entity(name = "ProductionProductModelIllustration")
@Table(schema = MappedProductModelIllustration.TABLE_SCHEMA, name = MappedProductModelIllustration.TABLE_NAME)
@IdClass(MappedProductModelIllustrationId.class)
public class ProductModelIllustration extends MappedProductModelIllustration {

    /**
     * The name of the attribute of the {@code FK_ProductModelIllustration_ProductModel_ProductModelID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL = "productModel";

    /**
     * The name of the attribute of the {@code FK_ProductModelIllustration_Illustration_IllustrationID} foreign key. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ILLUSTRATION = "illustration";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected ProductModelIllustration() {
        super();
    }

    // -------------------------------------------------------------------------------- productModel

    /**
     * Returns the {@code ProductModel} which the {@code FK_ProductModelIllustration_ProductModel_ProductModelID}
     * foreign key references.
     *
     * @return the {@code ProductModel} referenced; {@code null} when none is.
     */
    public ProductModel getProductModel() {
        return productModel;
    }

    /**
     * Replaces the {@code ProductModel} which the {@code FK_ProductModelIllustration_ProductModel_ProductModelID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param productModel the {@code ProductModel} to reference; {@code null} for none.
     */
    public void setProductModel(final ProductModel productModel) {
        this.productModel = productModel;
        setProductModelId(productModel == null ? null : productModel.getProductModelId());
    }

    // -------------------------------------------------------------------------------- illustration

    /**
     * Returns the {@code Illustration} which the {@code FK_ProductModelIllustration_Illustration_IllustrationID}
     * foreign key references.
     *
     * @return the {@code Illustration} referenced; {@code null} when none is.
     */
    public Illustration getIllustration() {
        return illustration;
    }

    /**
     * Replaces the {@code Illustration} which the {@code FK_ProductModelIllustration_Illustration_IllustrationID}
     * foreign key references, and the value of the column it consists of.
     *
     * @param illustration the {@code Illustration} to reference; {@code null} for none.
     */
    public void setIllustration(final Illustration illustration) {
        this.illustration = illustration;
        setIllustrationId(illustration == null ? null : illustration.getIllustrationId());
    }

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_PRODUCT_MODEL_ID,
                referencedColumnName = MappedProductModel.COLUMN_NAME_PRODUCT_MODEL_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private ProductModel productModel;

    // ---------------------------------------------------------------------------------------------
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_ILLUSTRATION_ID,
                referencedColumnName = MappedIllustration.COLUMN_NAME_ILLUSTRATION_ID,
                nullable = false,
                insertable = false,
                updatable = false
    )
    private Illustration illustration;
}

