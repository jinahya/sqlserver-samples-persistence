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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductInventory} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.ProductInventory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code LocationID}</th>
 *     <td>Inventory location identification number. Foreign key to Location.LocationID.</td></tr>
 * <tr><th scope="row">{@code Shelf}</th>
 *     <td>Storage compartment within an inventory location.</td></tr>
 * <tr><th scope="row">{@code Bin}</th>
 *     <td>Storage container on a shelf in an inventory location.</td></tr>
 * <tr><th scope="row">{@code Quantity}</th>
 *     <td>Quantity of products in the inventory location.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.ProductInventory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code Quantity}</th><td>{@code ((0))}</td></tr>
 * <tr><th scope="row">{@code rowguid}</th><td>{@code (newid())}</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft.com/en-us/sql/t-sql/functions/newid-transact-sql">NEWID</a>
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedProductInventory.TABLE_SCHEMA,
        table = MappedProductInventory.TABLE_NAME,
        value = "Product inventory information."
)
public abstract class MappedProductInventory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductInventory";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ---------------------------------------------------------------------------------- LocationID

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LocationID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // --------------------------------------------------------------------------------------- Shelf

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHELF = "Shelf";

    /**
     * The length of the {@value #COLUMN_NAME_SHELF} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SHELF = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHELF} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHELF = "shelf";

    // ----------------------------------------------------------------------------------------- Bin

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIN = "Bin";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_BIN} attribute, as the
     * {@code CK_ProductInventory_Bin} check constraint ({@code [Bin]>=(0)}) requires. The value is
     * {@value}.
     */
    public static final int ATTRIBUTE_MIN_BIN = 0;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_BIN} attribute, as the
     * {@code CK_ProductInventory_Bin} check constraint ({@code [Bin]<=(100)}) requires. The value
     * is {@value}.
     */
    public static final int ATTRIBUTE_MAX_BIN = +100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIN} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIN = "bin";

    // ------------------------------------------------------------------------------------ Quantity

    /**
     * The name of the {@code smallint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is
     * {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductInventory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productId=" + productId
               + ",locationId=" + locationId
               + ",shelf=" + shelf
               + ",bin=" + bin
               + ",quantity=" + quantity
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
        if (!(obj instanceof MappedProductInventory that)) {
            return false;
        }
        return Objects.equals(getProductId(), that.getProductId()) &&
               Objects.equals(getLocationId(), that.getLocationId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getProductId(), getLocationId());
    }

    // ---------------------------------------------------------------------------------- VALIDATION

    /**
     * Checks whether the {@value #ATTRIBUTE_NAME_SHELF} attribute is {@code null}, a single letter,
     * or {@code N/A}, as the table's {@code CK_ProductInventory_Shelf} constraint requires.
     * {@snippet lang = "sql":
     * ([Shelf] like '[A-Za-z]' OR [Shelf]='N/A')
     *}
     *
     * @return {@code true} if the {@value #ATTRIBUTE_NAME_SHELF} attribute is {@code null}, a
     *         single letter from {@code A} to {@code Z} or from {@code a} to {@code z}, or
     *         {@code N/A}; {@code false} otherwise.
     * @implNote This method compares with {@code N/A} as it is, while the database compares
     *         under the collation of the column.
     */
    @AssertTrue(message = "shelf must be a single letter or N/A")
    protected boolean isShelfValid() {
        if (shelf == null) {
            return true;
        }
        return shelf.matches("[A-Za-z]") || shelf.equals("N/A");
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified
     * value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    protected void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ---------------------------------------------------------------------------------- locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected Long getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified
     * value.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    protected void setLocationId(final Long locationId) {
        this.locationId = locationId;
    }

    // --------------------------------------------------------------------------------------- shelf

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SHELF} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SHELF} attribute.
     */
    public String getShelf() {
        return shelf;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SHELF} attribute with the specified value.
     *
     * @param shelf new value for {@value #ATTRIBUTE_NAME_SHELF} attribute.
     */
    public void setShelf(final String shelf) {
        this.shelf = shelf;
    }

    // ----------------------------------------------------------------------------------------- bin

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIN} attribute.
     */
    public Integer getBin() {
        return bin;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIN} attribute with the specified value.
     *
     * @param bin new value for {@value #ATTRIBUTE_NAME_BIN} attribute.
     */
    public void setBin(final Integer bin) {
        this.bin = bin;
    }

    // ------------------------------------------------------------------------------------ quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified
     * value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    // ------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    // -------------------------------------------------------------------------------- modifiedDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified
     * value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false,
            comment = "Product identification number. Foreign key to Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_LOCATION_ID, nullable = false, insertable = true, updatable = false,
            comment = "Inventory location identification number. Foreign key to "
                      + "Location.LocationID. ")
    private Long locationId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SHELF)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHELF, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_SHELF,
            comment = "Storage compartment within an inventory location.")
    private String shelf;

    // ---------------------------------------------------------------------------------------------
    // CK_ProductInventory_Bin: ([Bin]>=(0) AND [Bin]<=(100))
    @Max(ATTRIBUTE_MAX_BIN)
    @Min(ATTRIBUTE_MIN_BIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BIN, nullable = false, insertable = true, updatable = true,
            comment = "Storage container on a shelf in an inventory location.")
    private Integer bin;

    // ---------------------------------------------------------------------------------------------
    @Max(SqlServerConstants.SMALLINT_MAX)
    @Min(SqlServerConstants.SMALLINT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY, nullable = false, insertable = true, updatable = true,
            comment = "Quantity of products in the inventory location.")
    @MsDefault(name = "DF_ProductInventory_Quantity", value = "((0))")
    private Integer quantity;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_ProductInventory_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductInventory_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

