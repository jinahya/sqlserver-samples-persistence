package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped;

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

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.vProductAndDescription} view.
 * <p>
 * Identifier: the view declares no key; ProductID, CultureID are unique, and not null, in every row it returns.
 */
@MappedSuperclass
public abstract class MappedProductAndDescriptionView {

    /**
     * The name of the schema of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the view to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "vProductAndDescription";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ------------------------------------------------------------------------------------------------------------ Name

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_NAME = "Name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    // ---------------------------------------------------------------------------------------------------- ProductModel

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL = "ProductModel";

    /**
     * The length of the {@value #COLUMN_NAME_PRODUCT_MODEL} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PRODUCT_MODEL = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL = "productModel";

    // ------------------------------------------------------------------------------------------------------- CultureID

    /**
     * The name of the {@code nchar(6), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CULTURE_ID = "CultureID";

    /**
     * The length of the {@value #COLUMN_NAME_CULTURE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CULTURE_ID = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CULTURE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_CULTURE_ID = "cultureId";

    // ----------------------------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(400), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 400;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductAndDescriptionView() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",name=" + name
               + ",productModel=" + productModel
               + ",cultureId=" + cultureId
               + ",description=" + description
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
        if (!(obj instanceof MappedProductAndDescriptionView that)) {
            return false;
        }
        return Objects.equals(productId, that.productId) &&
               Objects.equals(cultureId, that.cultureId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productId, cultureId);
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    @Nonnull
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(@Nonnull final Integer productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    @Nonnull
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    protected void setName(@Nonnull final String name) {
        this.name = name;
    }

    // ---------------------------------------------------------------------------------------------------- productModel

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL} attribute.
     */
    @Nonnull
    public String getProductModel() {
        return productModel;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL} attribute with the specified value.
     *
     * @param productModel new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL} attribute.
     */
    protected void setProductModel(@Nonnull final String productModel) {
        this.productModel = productModel;
    }

    // ------------------------------------------------------------------------------------------------------- cultureId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute.
     */
    @Nonnull
    public String getCultureId() {
        return cultureId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute with the specified value.
     *
     * @param cultureId new value for {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute.
     */
    protected void setCultureId(@Nonnull final String cultureId) {
        this.cultureId = cultureId;
    }

    // ----------------------------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    @Nonnull
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    protected void setDescription(@Nonnull final String description) {
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_NAME
    )
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_PRODUCT_MODEL)
    @NotNull
    @Column(name = COLUMN_NAME_PRODUCT_MODEL,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PRODUCT_MODEL
    )
    private String productModel;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_CULTURE_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CULTURE_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_CULTURE_ID
    )
    private String cultureId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @NotNull
    @Column(name = COLUMN_NAME_DESCRIPTION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DESCRIPTION
    )
    private String description;
}

