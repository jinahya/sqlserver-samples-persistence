package com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.production;

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

import com.github.jinahya.mssql.sample.databases.persistence.adventureworks.mapped.UuidStringAttributeConverter;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Production.ProductInventory} table.
 */
@MappedSuperclass
public abstract class MappedProductInventory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Production";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ProductInventory";

    // ------------------------------------------------------------------------------------------------------- ProductID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PRODUCT_ID = "ProductID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PRODUCT_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PRODUCT_ID = "productId";

    // ------------------------------------------------------------------------------------------------------ LocationID

    /**
     * The name of the {@code smallint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LOCATION_ID = "LocationID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LOCATION_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION_ID = "locationId";

    // ----------------------------------------------------------------------------------------------------------- Shelf

    /**
     * The name of the {@code nvarchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SHELF = "Shelf";

    /**
     * The length of the {@value #COLUMN_NAME_SHELF} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SHELF = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SHELF} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_SHELF = "shelf";

    // ------------------------------------------------------------------------------------------------------------- Bin

    /**
     * The name of the {@code tinyint, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BIN = "Bin";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BIN} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BIN = "bin";

    // -------------------------------------------------------------------------------------------------------- Quantity

    /**
     * The name of the {@code smallint, DEFAULT ((0)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_QUANTITY = "Quantity";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_QUANTITY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_QUANTITY = "quantity";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

    // ---------------------------------------------------------------------------------------------------- ModifiedDate

    /**
     * The name of the {@code datetime, DEFAULT (getdate()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_MODIFIED_DATE = "ModifiedDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_MODIFIED_DATE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MODIFIED_DATE = "modifiedDate";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedProductInventory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

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
        return Objects.equals(productId, that.productId) &&
               Objects.equals(locationId, that.locationId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(productId, locationId);
    }

    // ------------------------------------------------------------------------------------------------------- productId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute with the specified value.
     *
     * @param productId new value for {@value #ATTRIBUTE_NAME_PRODUCT_ID} attribute.
     */
    public void setProductId(final Integer productId) {
        this.productId = productId;
    }

    // ------------------------------------------------------------------------------------------------------ locationId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    public Short getLocationId() {
        return locationId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute with the specified value.
     *
     * @param locationId new value for {@value #ATTRIBUTE_NAME_LOCATION_ID} attribute.
     */
    public void setLocationId(final Short locationId) {
        this.locationId = locationId;
    }

    // ----------------------------------------------------------------------------------------------------------- shelf

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

    // ------------------------------------------------------------------------------------------------------------- bin

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BIN} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BIN} attribute.
     */
    public Short getBin() {
        return bin;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BIN} attribute with the specified value.
     *
     * @param bin new value for {@value #ATTRIBUTE_NAME_BIN} attribute.
     */
    public void setBin(final Short bin) {
        this.bin = bin;
    }

    // -------------------------------------------------------------------------------------------------------- quantity

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public Short getQuantity() {
        return quantity;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_QUANTITY} attribute with the specified value.
     *
     * @param quantity new value for {@value #ATTRIBUTE_NAME_QUANTITY} attribute.
     */
    public void setQuantity(final Short quantity) {
        this.quantity = quantity;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(final UUID rowguid) {
        this.rowguid = rowguid;
    }

    // ---------------------------------------------------------------------------------------------------- modifiedDate

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

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_PRODUCT_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer productId;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_LOCATION_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Short locationId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_SHELF)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_SHELF,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SHELF
    )
    private String shelf;

    // -----------------------------------------------------------------------------------------------------------------
    @Max(255)
    @Min(0)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_BIN,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short bin;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_QUANTITY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Short quantity;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

    // -----------------------------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE,
            nullable = false,
            insertable = false,
            updatable = true
    )
    private LocalDateTime modifiedDate;
}

