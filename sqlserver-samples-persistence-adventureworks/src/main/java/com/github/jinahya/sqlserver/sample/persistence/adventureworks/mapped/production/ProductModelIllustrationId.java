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

import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/**
 * The identifier class of the {@code Production.ProductModelIllustration} table, whose rows are
 * identified by {@code ProductModelID}, {@code IllustrationID}.
 * <p>
 * {@link MappedProductModelIllustration} embeds this class with
 * {@link jakarta.persistence.EmbeddedId @EmbeddedId}, so the fields of this class map the columns
 * of the key.
 */
@Embeddable
public class ProductModelIllustrationId {

    /**
     * The name of the attribute which maps the
     * {@value MappedProductModelIllustration#COLUMN_NAME_PRODUCT_MODEL_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    /**
     * The name of the attribute which maps the
     * {@value MappedProductModelIllustration#COLUMN_NAME_ILLUSTRATION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ILLUSTRATION_ID = "illustrationId";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    public ProductModelIllustrationId() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",illustrationId=" + illustrationId
               + "}";
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductModelIllustrationId that)) {
            return false;
        }
        return Objects.equals(getProductModelId(), that.getProductModelId()) &&
               Objects.equals(getIllustrationId(), that.getIllustrationId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getProductModelId(), getIllustrationId());
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

    // ------------------------------------------------------------------------------ illustrationId

    /**
     * Returns current value of the {@code illustrationId} attribute.
     *
     * @return current value of the {@code illustrationId} attribute.
     */
    public Integer getIllustrationId() {
        return illustrationId;
    }

    /**
     * Replaces current value of the {@code illustrationId} attribute with the specified value.
     *
     * @param illustrationId new value for the {@code illustrationId} attribute.
     */
    public void setIllustrationId(final Integer illustrationId) {
        this.illustrationId = illustrationId;
    }

    // ---------------------------------------------------------------------------------------------

    /**
     * The value of the {@code productModelId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Column(name = MappedProductModelIllustration.COLUMN_NAME_PRODUCT_MODEL_ID, nullable = false,
            insertable = true, updatable = false,
            comment = "Primary key. Foreign key to ProductModel.ProductModelID.")
    private Integer productModelId;

    /**
     * The value of the {@code illustrationId} attribute.
     */
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Column(name = MappedProductModelIllustration.COLUMN_NAME_ILLUSTRATION_ID, nullable = false,
            insertable = true, updatable = false,
            comment = "Primary key. Foreign key to Illustration.IllustrationID.")
    private Integer illustrationId;
}

