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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductSubcategory} table.
 * <p>
 * The {@value #COLUMN_NAME_PRODUCT_CATEGORY_ID} column is mapped read-only, as a foreign key; the
 * extending entity owns the writable mapping, through an association's
 * {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.ProductSubcategory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductSubcategoryID}</th>
 *     <td>Primary key for ProductSubcategory records.</td></tr>
 * <tr><th scope="row">{@code ProductCategoryID}</th>
 *     <td>Product category identification number. Foreign key to
 *     ProductCategory.ProductCategoryID.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>Subcategory description.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.ProductSubcategory}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
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
        schema = MappedProductSubcategory.TABLE_SCHEMA,
        table = MappedProductSubcategory.TABLE_NAME,
        value = "Product subcategories. See ProductCategory table."
)
public abstract class MappedProductSubcategory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductSubcategory";

    // ------------------------------------------------------------------------ ProductSubcategoryID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_SUBCATEGORY_ID = "ProductSubcategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_SUBCATEGORY_ID} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID = "productSubcategoryId";

    // --------------------------------------------------------------------------- ProductCategoryID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_CATEGORY_ID = "ProductCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_CATEGORY_ID} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID = "productCategoryId";

    // ---------------------------------------------------------------------------------------- Name

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
    protected MappedProductSubcategory() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productSubcategoryId=" + productSubcategoryId
               + ",productCategoryId=" + productCategoryId
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
     * @implSpec Equality is by the identifier, once it is assigned; an instance whose identifier is
     *         not yet assigned, which persist does, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductSubcategory that)) {
            return false;
        }
        return getProductSubcategoryId() != null
               && getProductSubcategoryId().equals(that.getProductSubcategoryId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is the same for every instance, so that it does not change when
     *         persist assigns the identifier; consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return MappedProductSubcategory.class.hashCode();
    }

    // ------------------------------------------------------------------------ productSubcategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute.
     */
    public Integer getProductSubcategoryId() {
        return productSubcategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID} attribute with the
     * specified value.
     *
     * @param productSubcategoryId new value for {@value #ATTRIBUTE_NAME_PRODUCT_SUBCATEGORY_ID}
     *                             attribute.
     */
    protected void setProductSubcategoryId(final Integer productSubcategoryId) {
        this.productSubcategoryId = productSubcategoryId;
    }

    // --------------------------------------------------------------------------- productCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute.
     */
    protected Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID} attribute with the
     * specified value. The attribute is read-only; the extending entity calls this to keep it in
     * step with the association which writes the column.
     *
     * @param productCategoryId new value for {@value #ATTRIBUTE_NAME_PRODUCT_CATEGORY_ID}
     *                          attribute.
     */
    protected void setProductCategoryId(final Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    // ---------------------------------------------------------------------------------------- name

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_NAME} attribute with the specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setName(final String name) {
        this.name = name;
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
    // read-only: an identity column, which SQL Server assigns on an INSERT and does not update
    // (error 8102)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_PRODUCT_SUBCATEGORY_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Primary key for ProductSubcategory records.")
    private Integer productSubcategoryId;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class
    // documentation
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRODUCT_CATEGORY_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Product category identification number. Foreign key to "
                      + "ProductCategory.ProductCategoryID.")
    private Integer productCategoryId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME,
            comment = "Subcategory description.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_ProductSubcategory_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductSubcategory_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

