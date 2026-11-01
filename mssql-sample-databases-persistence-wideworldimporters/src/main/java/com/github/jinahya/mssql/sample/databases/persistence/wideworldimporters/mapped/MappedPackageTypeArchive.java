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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.PackageTypes_Archive} table.
 * <p>
 * Identifier: a history table of a system-versioned temporal table, which has no key.
 * <p>
 * As nothing identifies a row, no entity can extend this class; a plain class does, which a native query
 * fills.
 */
@MappedSuperclass
public abstract class MappedPackageTypeArchive {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Warehouse";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PackageTypes_Archive";

    // --------------------------------------------------------------------------------------------------- PackageTypeID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_ID = "PackageTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_ID = "packageTypeId";

    // ------------------------------------------------------------------------------------------------- PackageTypeName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_NAME = "PackageTypeName";

    /**
     * The length of the {@value #COLUMN_NAME_PACKAGE_TYPE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PACKAGE_TYPE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_NAME} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_NAME = "packageTypeName";

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
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // --------------------------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, NOT NULL} column. The value is {@value}.
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
    protected MappedPackageTypeArchive() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "packageTypeId=" + packageTypeId
               + ",packageTypeName=" + packageTypeName
               + ",lastEditedBy=" + lastEditedBy
               + ",validFrom=" + validFrom
               + ",validTo=" + validTo
               + "}";
    }

    // --------------------------------------------------------------------------------------------------- packageTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    @Nonnull
    public Integer getPackageTypeId() {
        return packageTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute with the specified value.
     *
     * @param packageTypeId new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected void setPackageTypeId(@Nonnull final Integer packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    // ------------------------------------------------------------------------------------------------- packageTypeName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute.
     */
    @Nonnull
    public String getPackageTypeName() {
        return packageTypeName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute with the specified value.
     *
     * @param packageTypeName new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute.
     */
    protected void setPackageTypeName(@Nonnull final String packageTypeName) {
        this.packageTypeName = packageTypeName;
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value.
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
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(@Nonnull final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_ID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer packageTypeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_PACKAGE_TYPE_NAME)
    @NotNull
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PACKAGE_TYPE_NAME
    )
    private String packageTypeName;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_LAST_EDITED_BY,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Integer lastEditedBy;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VALID_FROM,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime validFrom;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Column(name = COLUMN_NAME_VALID_TO,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private LocalDateTime validTo;
}

