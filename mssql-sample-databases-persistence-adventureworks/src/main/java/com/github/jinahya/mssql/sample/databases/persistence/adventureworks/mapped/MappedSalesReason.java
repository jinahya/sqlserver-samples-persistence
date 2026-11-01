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
 * A mapped superclass which holds the mappings of the {@code Sales.SalesReason} table.
 */
@MappedSuperclass
public abstract class MappedSalesReason {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Sales";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SalesReason";

    // --------------------------------------------------------------------------------------------------- SalesReasonID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SALES_REASON_ID = "SalesReasonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SALES_REASON_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SALES_REASON_ID = "salesReasonId";

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

    // ------------------------------------------------------------------------------------------------------ ReasonType

    /**
     * The name of the {@code Name (nvarchar)(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REASON_TYPE = "ReasonType";

    /**
     * The length of the {@value #COLUMN_NAME_REASON_TYPE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REASON_TYPE = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REASON_TYPE} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_REASON_TYPE = "reasonType";

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
    protected MappedSalesReason() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "salesReasonId=" + salesReasonId
               + ",name=" + name
               + ",reasonType=" + reasonType
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
        if (!(obj instanceof MappedSalesReason that)) {
            return false;
        }
        return Objects.equals(salesReasonId, that.salesReasonId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(salesReasonId);
    }

    // --------------------------------------------------------------------------------------------------- salesReasonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SALES_REASON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SALES_REASON_ID} attribute.
     */
    @Nullable
    public Integer getSalesReasonId() {
        return salesReasonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SALES_REASON_ID} attribute with the specified value.
     *
     * @param salesReasonId new value for {@value #ATTRIBUTE_NAME_SALES_REASON_ID} attribute.
     */
    protected void setSalesReasonId(@Nullable final Integer salesReasonId) {
        this.salesReasonId = salesReasonId;
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

    // ------------------------------------------------------------------------------------------------------ reasonType

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REASON_TYPE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REASON_TYPE} attribute.
     */
    @Nonnull
    public String getReasonType() {
        return reasonType;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REASON_TYPE} attribute with the specified value.
     *
     * @param reasonType new value for {@value #ATTRIBUTE_NAME_REASON_TYPE} attribute.
     */
    public void setReasonType(@Nonnull final String reasonType) {
        this.reasonType = reasonType;
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
    @Column(name = COLUMN_NAME_SALES_REASON_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer salesReasonId;

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
    @Size(max = COLUMN_LENGTH_REASON_TYPE)
    @NotNull
    @Column(name = COLUMN_NAME_REASON_TYPE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REASON_TYPE
    )
    private String reasonType;

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

