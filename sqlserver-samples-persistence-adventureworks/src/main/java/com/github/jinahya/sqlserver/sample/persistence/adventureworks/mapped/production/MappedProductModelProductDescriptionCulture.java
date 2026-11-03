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
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductModelProductDescriptionCulture} table.
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
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedProductModelProductDescriptionCulture.TABLE_SCHEMA,
        table = MappedProductModelProductDescriptionCulture.TABLE_NAME,
        value = "Cross-reference table mapping product descriptions and the language the "
                + "description is written in."
)
public abstract class MappedProductModelProductDescriptionCulture {

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
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_MODEL_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_MODEL_ID = "productModelId";

    // ------------------------------------------------------------------------ ProductDescriptionID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_DESCRIPTION_ID = "ProductDescriptionID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_DESCRIPTION_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID = "productDescriptionId";

    // ----------------------------------------------------------------------------------- CultureID

    /**
     * The name of the {@code nchar(6), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_CULTURE_ID = "CultureID";

    /**
     * The length of the {@value #COLUMN_NAME_CULTURE_ID} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_CULTURE_ID = 6;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_CULTURE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CULTURE_ID = "cultureId";

    // -------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
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
               + "productModelId=" + productModelId
               + ",productDescriptionId=" + productDescriptionId
               + ",cultureId=" + cultureId
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
        if (!(obj instanceof MappedProductModelProductDescriptionCulture that)) {
            return false;
        }
        return Objects.equals(productModelId, that.productModelId) &&
               Objects.equals(productDescriptionId, that.productDescriptionId) &&
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
        return Objects.hash(productModelId, productDescriptionId, cultureId);
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute with the specified value.
     *
     * @param productModelId new value for {@value #ATTRIBUTE_NAME_PRODUCT_MODEL_ID} attribute.
     */
    public void setProductModelId(final Integer productModelId) {
        this.productModelId = productModelId;
    }

    // ------------------------------------------------------------------------ productDescriptionId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID} attribute.
     */
    public Integer getProductDescriptionId() {
        return productDescriptionId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID} attribute with the specified value.
     *
     * @param productDescriptionId new value for {@value #ATTRIBUTE_NAME_PRODUCT_DESCRIPTION_ID} attribute.
     */
    public void setProductDescriptionId(final Integer productDescriptionId) {
        this.productDescriptionId = productDescriptionId;
    }

    // ----------------------------------------------------------------------------------- cultureId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute.
     */
    public String getCultureId() {
        return cultureId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute with the specified value.
     *
     * @param cultureId new value for {@value #ATTRIBUTE_NAME_CULTURE_ID} attribute.
     */
    public void setCultureId(final String cultureId) {
        this.cultureId = cultureId;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute with the specified value.
     *
     * @param modifiedDate new value for {@value #ATTRIBUTE_NAME_MODIFIED_DATE} attribute.
     */
    public void setModifiedDate(final LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_MODEL_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_MODEL_ID,
                          value = "Primary key. Foreign key to ProductModel.ProductModelID.")
    private Integer productModelId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_DESCRIPTION_ID,
                          value = "Primary key. Foreign key to "
                                  + "ProductDescription.ProductDescriptionID.")
    private Integer productDescriptionId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Size(max = COLUMN_LENGTH_CULTURE_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_CULTURE_ID,
            nullable = false,
            insertable = true,
            updatable = false,
            length = COLUMN_LENGTH_CULTURE_ID
    )
    @MsDescription.Column(name = COLUMN_NAME_CULTURE_ID,
                          value = "Culture identification number. Foreign key to "
                                  + "Culture.CultureID.")
    private String cultureId;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_ProductModelProductDescriptionCulture_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

