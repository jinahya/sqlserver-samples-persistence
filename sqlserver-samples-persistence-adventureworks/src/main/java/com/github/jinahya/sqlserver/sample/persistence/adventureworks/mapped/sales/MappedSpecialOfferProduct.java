package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.sales;

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
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Sales.SpecialOfferProduct} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Sales.SpecialOfferProduct}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SpecialOfferID}</th>
 *     <td>Primary key for SpecialOfferProduct records.</td></tr>
 * <tr><th scope="row">{@code ProductID}</th>
 *     <td>Product identification number. Foreign key to Product.ProductID.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Sales.SpecialOfferProduct}</caption>
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
        schema = MappedSpecialOfferProduct.TABLE_SCHEMA,
        table = MappedSpecialOfferProduct.TABLE_NAME,
        value = "Cross-reference table mapping products to special offer discounts."
)
public abstract class MappedSpecialOfferProduct {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SpecialOfferProduct";

    // ------------------------------------------------------------------------------ SpecialOfferID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SPECIAL_OFFER_ID = "SpecialOfferID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_SPECIAL_OFFER_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_SPECIAL_OFFER_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SPECIAL_OFFER_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SPECIAL_OFFER_ID = "specialOfferId";

    // ----------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The minimum value of the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute, the minimum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MIN_PRODUCT_ID = SqlServerConstants.INT_MIN;

    /**
     * The maximum value of the {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute, the maximum of the
     * {@code int} type. The value is {@value}.
     */
    public static final int ATTRIBUTE_MAX_PRODUCT_ID = SqlServerConstants.INT_MAX;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

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
    protected MappedSpecialOfferProduct() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "specialOfferId=" + specialOfferId
               + ",productId=" + productId
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
        if (!(obj instanceof MappedSpecialOfferProduct that)) {
            return false;
        }
        return Objects.equals(getSpecialOfferId(), that.getSpecialOfferId()) &&
               Objects.equals(getProductId(), that.getProductId());
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(getSpecialOfferId(), getProductId());
    }

    // ------------------------------------------------------------------------------ specialOfferId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. It is {@code public}, since
     *         {@code SalesOrderDetail} references the key with a composite foreign key.
     */
    public Integer getSpecialOfferId() {
        return specialOfferId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute with the
     * specified value.
     *
     * @param specialOfferId new value for {@value #ATTRIBUTE_NAME_SPECIAL_OFFER_ID} attribute.
     */
    protected void setSpecialOfferId(final Integer specialOfferId) {
        this.specialOfferId = specialOfferId;
    }

    // ----------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     * @implNote {@link #equals(Object)} and {@link #hashCode()} read the identifier through
     *         this method, not the field, so that a Hibernate proxy, whose own fields are unset,
     *         answers with the identifier of the row it stands for. It is {@code public}, since
     *         {@code SalesOrderDetail} references the key with a composite foreign key.
     */
    public Integer getProductId() {
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
    @Max(ATTRIBUTE_MAX_SPECIAL_OFFER_ID)
    @Min(ATTRIBUTE_MIN_SPECIAL_OFFER_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_SPECIAL_OFFER_ID, nullable = false, insertable = true,
            updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_SPECIAL_OFFER_ID,
                          value = "Primary key for SpecialOfferProduct records.")
    private Integer specialOfferId;

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of
    // an entity
    @Max(ATTRIBUTE_MAX_PRODUCT_ID)
    @Min(ATTRIBUTE_MIN_PRODUCT_ID)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_ID, nullable = false, insertable = true, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_PRODUCT_ID,
                          value = "Product identification number. Foreign key to "
                                  + "Product.ProductID.")
    private Integer productId;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ROWGUID,
                          value = "ROWGUIDCOL number uniquely identifying the record. Used to "
                                  + "support a merge replication sample.")
    @MsDefault(name = "DF_SpecialOfferProduct_rowguid", value = "(newid())")
    private UUID rowguid;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true)
    @MsDescription.Column(name = COLUMN_NAME_MODIFIED_DATE,
                          value = "Date and time the record was last updated.")
    @MsDefault(name = "DF_SpecialOfferProduct_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

