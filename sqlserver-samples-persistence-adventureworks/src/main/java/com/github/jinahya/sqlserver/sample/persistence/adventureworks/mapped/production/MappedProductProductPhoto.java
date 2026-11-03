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
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductProductPhoto}
 * table.
 * <p>
 * The key is a {@link ProductProductPhotoId} embedded with {@link EmbeddedId @EmbeddedId}, whose
 * fields map the columns of the key; an entity which extends this class derives each of its
 * attributes from an association with {@link jakarta.persistence.MapsId @MapsId}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Production.ProductProductPhoto}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code ProductPhotoID}</th>
 *     <td>Product photo identification number. Foreign key to
 *     ProductPhoto.ProductPhotoID.</td></tr>
 * <tr><th scope="row">{@code Primary}</th>
 *     <td>0 = Photo is not the principal image. 1 = Photo is the principal image.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Production.ProductProductPhoto}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code Primary}</th><td>{@code ((0))}</td></tr>
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
        schema = MappedProductProductPhoto.TABLE_SCHEMA,
        table = MappedProductProductPhoto.TABLE_NAME,
        value = "Cross-reference table mapping products and product photos."
)
public abstract class MappedProductProductPhoto {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductProductPhoto";

    // ------------------------------------------------------------------------------------------ id

    /**
     * The name of the attribute which embeds the key. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column -- a path
     * into the {@link EmbeddedId @EmbeddedId}, which is where the column actually lives. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PRODUCT_ID = "id.productId";

    // ------------------------------------------------------------------------------ ProductPhotoID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_PHOTO_ID = "ProductPhotoID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_PHOTO_ID} column -- a
     * path into the {@link EmbeddedId @EmbeddedId}, which is where the column actually lives. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_PATH_ID_PRODUCT_PHOTO_ID = "id.productPhotoId";

    // ------------------------------------------------------------------------------------- Primary

    /**
     * The name of the {@code Flag (bit), DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRIMARY = "\"Primary\"";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRIMARY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRIMARY = "primary";

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
    protected MappedProductProductPhoto() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "id=" + id
               + ",primary=" + primary
               + ",modifiedDate=" + modifiedDate
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
        if (!(obj instanceof MappedProductProductPhoto that)) {
            return false;
        }
        final var id = getId();
        return id != null
               && id.getProductId() != null
               && id.getProductPhotoId() != null
               && id.equals(that.getId());
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
        return MappedProductProductPhoto.class.hashCode();
    }

    // ------------------------------------------------------------------------------------------ id

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. The method is
     *         {@code protected}, not package-private, since a proxy is a subclass in the package of
     *         the entity, and overrides only what that package sees.
     */
    protected ProductProductPhotoId getId() {
        return id;
    }

    // ------------------------------------------------------------------------------------- primary

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRIMARY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRIMARY} attribute.
     */
    public Boolean getPrimary() {
        return primary;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRIMARY} attribute with the specified
     * value.
     *
     * @param primary new value for {@value #ATTRIBUTE_NAME_PRIMARY} attribute.
     */
    public void setPrimary(final Boolean primary) {
        this.primary = primary;
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
    // not updatable: the identifier; Jakarta Persistence does not change the identifier of an
    // entity
    @Valid
    @NotNull
    @EmbeddedId
    private ProductProductPhotoId id = new ProductProductPhotoId();

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PRIMARY, nullable = false, insertable = true, updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_PRIMARY,
                          value = "0 = Photo is not the principal image. 1 = Photo is the "
                                  + "principal image.")
    @MsDefault(name = "DF_ProductProductPhoto_Primary", value = "((0))")
    private Boolean primary;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductProductPhoto_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

