package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.production;

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

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * The identifier class of the {@code Production.ProductModelProductDescriptionCulture} table, whose
 * rows are identified by {@code ProductModelID}, {@code ProductDescriptionID}, {@code CultureID}.
 * <p>
 * {@link MappedProductModelProductDescriptionCulture} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class ProductModelProductDescriptionCultureId {

    /**
     * The name of the attribute which maps the
     * {@value MappedProductModelProductDescriptionCulture#COLUMN_NAME_PRODUCT_MODEL_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductModelProductDescriptionCulture#COLUMN_NAME_PRODUCT_DESCRIPTION_ID}
     * column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID = "productDescriptionId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductModelProductDescriptionCulture#COLUMN_NAME_CULTURE_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CULTURE_ID = "cultureId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductModelProductDescriptionCultureId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",productDescriptionId=" + productDescriptionId
               + ",cultureId=" + cultureId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductModelProductDescriptionCultureId that)) {
            return false;
        }
        return Objects.equals(getProductModelId(), that.getProductModelId()) &&
               Objects.equals(getProductDescriptionId(), that.getProductDescriptionId()) &&
               Objects.equals(getCultureId(), that.getCultureId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getProductModelId(), getProductDescriptionId(), getCultureId());
    }

    // ------------------------------------------------------------------------------ productModelId

    /**
     * Returns current value of the {@code productModelId} attribute.
     *
     * @return current value of the {@code productModelId} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of the {@code productModelId} attribute with the specified value.
     *
     * @param productModelId new value for the {@code productModelId} attribute.
     */
    public void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // ------------------------------------------------------------------------ productDescriptionId

    /**
     * Returns current value of the {@code productDescriptionId} attribute.
     *
     * @return current value of the {@code productDescriptionId} attribute.
     */
    public Integer getProductDescriptionId() {
        return productDescriptionId;
    }

    /**
     * Replaces current value of the {@code productDescriptionId} attribute with the specified
     * value.
     *
     * @param productDescriptionId new value for the {@code productDescriptionId} attribute.
     */
    public void setProductDescriptionId(final Integer productDescriptionId) {
        this.productDescriptionId = productDescriptionId;
    }

    // ----------------------------------------------------------------------------------- cultureId

    /**
     * Returns current value of the {@code cultureId} attribute.
     *
     * @return current value of the {@code cultureId} attribute.
     */
    public String getCultureId() {
        return cultureId;
    }

    /**
     * Replaces current value of the {@code cultureId} attribute with the specified value.
     *
     * @param cultureId new value for the {@code cultureId} attribute.
     */
    public void setCultureId(final String cultureId) {
        this.cultureId = cultureId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code productModelId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedProductModelProductDescriptionCulture.COLUMN_NAME_PRODUCT_MODEL_ID,
            nullable = false, insertable = true, updatable = false,
            comment = "Primary key. Foreign key to ProductModel.ProductModelID.")
    private Integer productModelId;

    /**
     * The value of the {@code productDescriptionId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedProductModelProductDescriptionCulture.COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
            nullable = false, insertable = true, updatable = false,
            comment = "Primary key. Foreign key to ProductDescription.ProductDescriptionID.")
    private Integer productDescriptionId;

    /**
     * The value of the {@code cultureId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Size(max = MappedProductModelProductDescriptionCulture.COLUMN_LENGTH_CULTURE_ID)
    @NotNull
    @Column(name = MappedProductModelProductDescriptionCulture.COLUMN_NAME_CULTURE_ID,
            nullable = false, insertable = true, updatable = false,
            length = MappedProductModelProductDescriptionCulture.COLUMN_LENGTH_CULTURE_ID,
            comment = "Culture identification number. Foreign key to Culture.CultureID.")
    private String cultureId;
}

