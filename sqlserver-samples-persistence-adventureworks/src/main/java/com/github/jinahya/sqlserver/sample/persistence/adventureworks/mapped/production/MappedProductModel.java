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

import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.ManuInstructionsStringAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.ProductDescriptionStringAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import io.github.jinahya.sqlserver.xml.schema.binding.b0fd0e.ProductDescription;
import io.github.jinahya.sqlserver.xml.schema.binding.b73cae.Root;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductModel} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.ProductModel}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductModelID}</th>
 *     <td>Primary key for ProductModel records.</td></tr>
 * <tr><th scope="row">{@code Name}</th><td>Product model description.</td></tr>
 * <tr><th scope="row">{@code CatalogDescription}</th>
 *     <td>Detailed product catalog information in xml format.</td></tr>
 * <tr><th scope="row">{@code Instructions}</th>
 *     <td>Manufacturing instructions in xml format.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.ProductModel}</caption>
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
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedProductModel.TABLE_SCHEMA,
        table = MappedProductModel.TABLE_NAME,
        value = "Product model classification."
)
public abstract class MappedProductModel {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductModel";

    // ------------------------------------------------------------------------------ ProductModelID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

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

    // -------------------------------------------------------------------------- CatalogDescription

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CATALOG_DESCRIPTION = "CatalogDescription";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CATALOG_DESCRIPTION} column.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CATALOG_DESCRIPTION = "catalogDescription";

    // -------------------------------------------------------------------------------- Instructions

    /**
     * The name of the {@code xml} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_INSTRUCTIONS = "Instructions";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_INSTRUCTIONS} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_INSTRUCTIONS = "instructions";

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
    protected MappedProductModel() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "productModelId=" + productModelId
               + ",name=" + name
               + ",catalogDescription=" + catalogDescription
               + ",instructions=" + instructions
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
        if (!(obj instanceof MappedProductModel that)) {
            return false;
        }
        return getProductModelId() != null
               && getProductModelId().equals(that.getProductModelId());
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
        return MappedProductModel.class.hashCode();
    }

    // ------------------------------------------------------------------------------ productModelId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public Integer getProductModelId() {
        return productModelId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the
     * specified value.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    protected void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
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

    // -------------------------------------------------------------------------- catalogDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CATALOG_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CATALOG_DESCRIPTION} attribute.
     */
    public ProductDescription getCatalogDescription() {
        return catalogDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CATALOG_DESCRIPTION} attribute with the
     * specified value.
     *
     * @param catalogDescription new value for {@value #ATTRIBUTE_NAME_CATALOG_DESCRIPTION}
     *                           attribute.
     */
    public void setCatalogDescription(final ProductDescription catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    // -------------------------------------------------------------------------------- instructions

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute.
     */
    public Root getInstructions() {
        return instructions;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute with the specified
     * value.
     *
     * @param instructions new value for {@value #ATTRIBUTE_NAME_INSTRUCTIONS} attribute.
     */
    public void setInstructions(final Root instructions) {
        this.instructions = instructions;
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
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID, nullable = false, insertable = false,
            updatable = false,
            comment = "Primary key for ProductModel records.")
    private Integer productModelId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_NAME,
            comment = "Product model description.")
    private String name;

    // ---------------------------------------------------------------------------------------------
    // immutable to Hibernate ORM: the description's xsd:any content is DOM elements, which equal
    // no copy of themselves, so a value would never equal the snapshot of it, and every flush
    // would update the row; the value is replaced, not changed in place
    @Valid
    @Convert(converter = ProductDescriptionStringAttributeConverter.class)
    @org.hibernate.annotations.Mutability(org.hibernate.type.descriptor.java.Immutability.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_CATALOG_DESCRIPTION, nullable = true, insertable = true,
            updatable = true,
            comment = "Detailed product catalog information in xml format.")
    private ProductDescription catalogDescription;

    // ---------------------------------------------------------------------------------------------
    @Valid
    @Convert(converter = ManuInstructionsStringAttributeConverter.class)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_INSTRUCTIONS, nullable = true, insertable = true, updatable = true,
            comment = "Manufacturing instructions in xml format.")
    private Root instructions;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false,
            comment = "ROWGUIDCOL number uniquely identifying the record. Used to support a merge "
                      + "replication sample.")
    @MsDefault(name = "DF_ProductModel_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductModel_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

