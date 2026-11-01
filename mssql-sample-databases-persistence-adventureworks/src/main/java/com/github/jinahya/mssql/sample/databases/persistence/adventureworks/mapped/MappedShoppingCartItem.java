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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.ShoppingCartItem} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_ID} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedShoppingCartItem {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ShoppingCartItem";

    // ---------------------------------------------------------------------------------------------- ShoppingCartItemID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHOPPING_CART_ITEM_ID = "ShoppingCartItemID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHOPPING_CART_ITEM_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHOPPING_CART_ITEM_ID = "shoppingCartItemId";

    // -------------------------------------------------------------------------------------------------- ShoppingCartID

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHOPPING_CART_ID = "ShoppingCartID";

    /**
     * The length of the {@value #COLUMN_NAME_SHOPPING_CART_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SHOPPING_CART_ID = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHOPPING_CART_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHOPPING_CART_ID = "shoppingCartId";

    // -------------------------------------------------------------------------------------------------------- Quantity

    /**
     * The name of the {@code int, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

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

    // ----------------------------------------------------------------------------------------------------- DateCreated

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DATE_CREATED = "DateCreated";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DATE_CREATED} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_DATE_CREATED = "dateCreated";

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
    protected MappedShoppingCartItem() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "shoppingCartItemId=" + shoppingCartItemId
               + ",shoppingCartId=" + shoppingCartId
               + ",quantity=" + quantity
               + ",productId=" + productId
               + ",dateCreated=" + dateCreated
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
        if (!(obj instanceof MappedShoppingCartItem that)) {
            return false;
        }
        return Objects.equals(shoppingCartItemId, that.shoppingCartItemId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(shoppingCartItemId);
    }

    // ---------------------------------------------------------------------------------------------- shoppingCartItemId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHOPPING_CART_ITEM_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHOPPING_CART_ITEM_ID} attribute.
     */
    @Nullable
    public Integer getShoppingCartItemId() {
        return shoppingCartItemId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHOPPING_CART_ITEM_ID} attribute with the specified value.
     *
     * @param shoppingCartItemId new value for {@value #ATTRIBUTE_NAME_SHOPPING_CART_ITEM_ID} attribute.
     */
    protected void setShoppingCartItemId(@Nullable final Integer shoppingCartItemId) {
        this.shoppingCartItemId = shoppingCartItemId;
    }

    // -------------------------------------------------------------------------------------------------- shoppingCartId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHOPPING_CART_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHOPPING_CART_ID} attribute.
     */
    @Nonnull
    public String getShoppingCartId() {
        return shoppingCartId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHOPPING_CART_ID} attribute with the specified value.
     *
     * @param shoppingCartId new value for {@value #ATTRIBUTE_NAME_SHOPPING_CART_ID} attribute.
     */
    public void setShoppingCartId(@Nonnull final String shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    @Nonnull
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(@Nonnull final Integer quantity) {
        this.quantity = quantity;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value. The attribute
     * is read-only; the extending entity calls this to keep it in step with the association which writes the column.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(@Nonnull final Integer productId) {
        this.productId = productId;
    }

    // ----------------------------------------------------------------------------------------------------- dateCreated

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DATE_CREATED} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DATE_CREATED} attribute.
     */
    @Nonnull
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DATE_CREATED} attribute with the specified value.
     *
     * @param dateCreated new value for {@value #ATTRIBUTE_NAME_DATE_CREATED} attribute.
     */
    public void setDateCreated(@Nonnull final LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
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
    @Column(name = COLUMN_NAME_SHOPPING_CART_ITEM_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer shoppingCartItemId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_SHOPPING_CART_ID)
    @NotNull
    @Column(name = COLUMN_NAME_SHOPPING_CART_ID,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHOPPING_CART_ID
    )
    private String shoppingCartId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer quantity;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_DATE_CREATED,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime dateCreated;

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

