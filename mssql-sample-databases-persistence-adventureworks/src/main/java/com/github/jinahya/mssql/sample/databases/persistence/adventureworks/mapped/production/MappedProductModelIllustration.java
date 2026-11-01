package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production;

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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductModelIllustration} table.
 */
@MappedSuperclass
public abstract class MappedProductModelIllustration {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductModelIllustration";

    // -------------------------------------------------------------------------------------------------- ProductModelID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    // -------------------------------------------------------------------------------------------------- IllustrationID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ILLUSTRATION_ID = "IllustrationID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ILLUSTRATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ILLUSTRATION_ID = "illustrationId";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductModelIllustration() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",illustrationId=" + illustrationId
               + ",modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier alone.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductModelIllustration that)) {
            return false;
        }
        return Objects.equals(productModelId, that.productModelId) &&
               Objects.equals(illustrationId, that.illustrationId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productModelId, illustrationId);
    }

    // -------------------------------------------------------------------------------------------------- productModelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the specified value.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // -------------------------------------------------------------------------------------------------- illustrationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute.
     */
    public Integer getIllustrationId() {
        return illustrationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute with the specified value.
     *
     * @param illustrationId new value for {@value #ATTRIBUTE_NAME_ILLUSTRATION_ID} attribute.
     */
    public void setIllustrationId(final Integer illustrationId) {
        this.illustrationId = illustrationId;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productModelId;

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_ILLUSTRATION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer illustrationId;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

