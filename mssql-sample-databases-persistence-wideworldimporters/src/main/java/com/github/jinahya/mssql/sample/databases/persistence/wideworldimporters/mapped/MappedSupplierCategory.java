package com.github.jinahya.mssql.sample.databases.persistence.wideworldimporters.mapped;

/*-
 * #%L
 * mssql-sample-databases-persistence-wideworldimporters
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
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code Purchasing.SupplierCategories} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and
 * keeps every former version of a row in {@code Purchasing.SupplierCategories_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_LAST_EDITED_BY} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 */
@MappedSuperclass
public abstract class MappedSupplierCategory {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Purchasing";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "SupplierCategories";

    // ---------------------------------------------------------------------------------------------- SupplierCategoryID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.SupplierCategoryID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_CATEGORY_ID = "SupplierCategoryID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID = "supplierCategoryId";

    // -------------------------------------------------------------------------------------------- SupplierCategoryName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SUPPLIER_CATEGORY_NAME = "SupplierCategoryName";

    /**
     * The length of the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_SUPPLIER_CATEGORY_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_SUPPLIER_CATEGORY_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME = "supplierCategoryName";

    // ---------------------------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ------------------------------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedSupplierCategory() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "supplierCategoryId=" + supplierCategoryId
               + ",supplierCategoryName=" + supplierCategoryName
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
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
        if (!(obj instanceof MappedSupplierCategory that)) {
            return false;
        }
        return Objects.equals(supplierCategoryId, that.supplierCategoryId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(supplierCategoryId);
    }

    // ---------------------------------------------------------------------------------------------- supplierCategoryId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute.
     */
    @Nullable
    public Integer getSupplierCategoryId() {
        return supplierCategoryId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute with the specified value.
     *
     * @param supplierCategoryId new value for {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_ID} attribute.
     */
    protected void setSupplierCategoryId(@Nullable final Integer supplierCategoryId) {
        this.supplierCategoryId = supplierCategoryId;
    }

    // -------------------------------------------------------------------------------------------- supplierCategoryName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute.
     */
    @Nonnull
    public String getSupplierCategoryName() {
        return supplierCategoryName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute with the specified value.
     *
     * @param supplierCategoryName new value for {@value #ATTRIBUTE_NAME_SUPPLIER_CATEGORY_NAME} attribute.
     */
    public void setSupplierCategoryName(@Nonnull final String supplierCategoryName) {
        this.supplierCategoryName = supplierCategoryName;
    }

    // ---------------------------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    @Nonnull
    public Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(@Nonnull final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ------------------------------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    @Nonnull
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(@Nonnull final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // --------------------------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    @Nonnull
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(@Nonnull final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nullable
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.SupplierCategoryID", schema = "Sequences", sequenceName = "SupplierCategoryID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.SupplierCategoryID")
    @Column(name = COLUMN_NAME_SUPPLIER_CATEGORY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer supplierCategoryId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_SUPPLIER_CATEGORY_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_SUPPLIER_CATEGORY_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_SUPPLIER_CATEGORY_NAME
    )
    private String supplierCategoryName;

    // -----------------------------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime validTo;
}

