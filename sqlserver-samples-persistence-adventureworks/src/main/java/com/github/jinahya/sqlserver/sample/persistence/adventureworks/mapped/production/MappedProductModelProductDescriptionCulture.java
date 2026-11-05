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

import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import com.github.jinahya.sqlserver.sample.persistence.common.mapped.MappedWithEmbeddedId;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the
 * {@code Production.ProductModelProductDescriptionCulture} table.
 * <p>
 * The key is a {@link ProductModelProductDescriptionCultureId} embedded with
 * {@link EmbeddedId @EmbeddedId}, whose fields map the columns of the key; an entity which extends
 * this class derives each of its attributes from an association with
 * {@link jakarta.persistence.MapsId @MapsId}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.ProductModelProductDescriptionCulture}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductModelID}</th>
 *     <td>Primary key. Foreign key to ProductModel.ProductModelID.</td></tr>
 * <tr><th scope="row">{@code ProductDescriptionID}</th>
 *     <td>Primary key. Foreign key to ProductDescription.ProductDescriptionID.</td></tr>
 * <tr><th scope="row">{@code CultureID}</th>
 *     <td>Culture identification number. Foreign key to Culture.CultureID.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.ProductModelProductDescriptionCulture}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ModifiedDate}</th><td>{@code (getdate())}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a
 *         href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedProductModelProductDescriptionCulture.TABLE_SCHEMA,
        table = MappedProductModelProductDescriptionCulture.TABLE_NAME,
        value = "Cross-reference table mapping product descriptions and the language the "
                + "description is written in."
)
public abstract class MappedProductModelProductDescriptionCulture
        extends MappedWithEmbeddedId<ProductModelProductDescriptionCultureId> {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductModelProductDescriptionCulture";

    // ------------------------------------------------------------------------------ ProductModelID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_MODEL_ID = "ProductModelID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column -- a
     * path into the {@link EmbeddedId @EmbeddedId}, which is where the column actually lives. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PRODUCT_MODEL_ID = "id_.productModelId";

    // ------------------------------------------------------------------------ ProductDescriptionID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_DESCRIPTION_ID = "ProductDescriptionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_DESCRIPTION_ID} column
     * -- a path into the {@link EmbeddedId @EmbeddedId}, which is where the column actually lives.
     * The value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PRODUCT_DESCRIPTION_ID = "id_.productDescriptionId";

    // ----------------------------------------------------------------------------------- CultureID

    /**
     * The name of the {@code nchar(6), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CULTURE_ID = "CultureID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CULTURE_ID} column -- a path
     * into the {@link EmbeddedId @EmbeddedId}, which is where the column actually lives. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_CULTURE_ID = "id_.cultureId";

    /**
     * The length of the {@value #COLUMN_NAME_CULTURE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CULTURE_ID = 6;

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
    protected MappedProductModelProductDescriptionCulture() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "modifiedDate=" + modifiedDate
               + "}";
    }

    /**
     * {@inheritDoc}
     *
     * @param obj {@inheritDoc}
     * @return {@inheritDoc}
     * @implSpec Equality is by the identifier, once each of its attributes is assigned; an
     *         instance whose identifier is not yet assigned, which
     *         {@link jakarta.persistence.MapsId @MapsId} does on persist, equals only itself.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MappedProductModelProductDescriptionCulture that)) {
            return false;
        }
        final var id = getId_();
        return id != null
               && id.getProductModelId() != null
               && id.getProductDescriptionId() != null
               && id.getCultureId() != null
               && id.equals(that.getId_());
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
        return MappedProductModelProductDescriptionCulture.class.hashCode();
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
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductModelProductDescriptionCulture_ModifiedDate",
               value = "(getdate())")
    private LocalDateTime modifiedDate;
}

