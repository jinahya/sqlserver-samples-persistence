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
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductCategory} table.
 */
@MappedSuperclass
public abstract class MappedProductCategory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductCategory";

    // ----------------------------------------------------------------------------------------------- ProductCategoryID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_CATEGORY_ID = "ProductCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID = "productCategoryId";

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

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductCategory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productCategoryId=" + productCategoryId
               + ",name=" + name
               + ",rowguid=" + rowguid
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
        if (!(obj instanceof MappedProductCategory that)) {
            return false;
        }
        return Objects.equals(productCategoryId, that.productCategoryId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productCategoryId);
    }

    // ----------------------------------------------------------------------------------------------- productCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute.
     */
    @Nullable
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute with the specified value.
     *
     * @param productCategoryId new value for {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute.
     */
    protected void setProductCategoryId(@Nullable final Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
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
    public void setName(@Nonnull final String name) {
        this.name = name;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    @Nonnull
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(@Nonnull final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    @Nonnull
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(@Nonnull final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_CATEGORY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productCategoryId;

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
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

