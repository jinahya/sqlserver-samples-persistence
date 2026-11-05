package com.github.jinahya.sqlserver.sample.persistence.wideworldimporters.mapped.warehouse;

/*-
 * #%L
 * sqlserver-samples-persistence-wideworldimporters
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
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsTemporal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code Warehouse.PackageTypes} table.
 * <p>
 * The table is system-versioned: SQL Server writes its period columns, which are mapped read-only, and keeps every
 * former version of a row in {@code Warehouse.PackageTypes_Archive}.
 * <p>
 * The {@value #COLUMN_NAME_LAST_EDITED_BY} column is mapped read-only, as a foreign key; the extending entity owns the
 * writable mapping, through an association's {@link jakarta.persistence.JoinColumn @JoinColumn}.
 *
 * <table class="striped">
 * <caption>Columns of {@code Warehouse.PackageTypes}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PackageTypeID}</th>
 *     <td>Numeric ID used for reference to a package type within the database</td></tr>
 * <tr><th scope="row">{@code PackageTypeName}</th>
 *     <td>Full name of package types that stock items can be purchased in or sold in</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Warehouse.PackageTypes}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Definition</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PackageTypeID}</th><td>{@code (NEXT VALUE FOR [Sequences].[PackageTypeID])}</td></tr>
 * </tbody>
 * </table>
 *
 * @see <a href="https://learn.microsoft.com/en-us/sql/t-sql/functions/next-value-for-transact-sql">NEXT VALUE FOR</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPackageType.TABLE_SCHEMA,
        table = MappedPackageType.TABLE_NAME,
        value = "Ways that stock items can be packaged (ie: each, box, carton, pallet, kg, etc."
)
@MsTemporal(
        historyTable = "Warehouse.PackageTypes_Archive",
        periodStart = "ValidFrom",
        periodEnd = "ValidTo"
)
public abstract class MappedPackageType {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "PackageTypes";

    // ------------------------------------------------------------------------------- PackageTypeID

    /**
     * The name of the {@code int, NEXT VALUE FOR Sequences.PackageTypeID, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_ID = "PackageTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_ID = "packageTypeId";

    // ----------------------------------------------------------------------------- PackageTypeName

    /**
     * The name of the {@code nvarchar(50), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PACKAGE_TYPE_NAME = "PackageTypeName";

    /**
     * The length of the {@value #COLUMN_NAME_PACKAGE_TYPE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PACKAGE_TYPE_NAME = 50;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PACKAGE_TYPE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PACKAGE_TYPE_NAME = "packageTypeName";

    // -------------------------------------------------------------------------------- LastEditedBy

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_EDITED_BY = "LastEditedBy";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_LAST_EDITED_BY} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LAST_EDITED_BY = "lastEditedBy";

    // ----------------------------------------------------------------------------------- ValidFrom

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW START, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_FROM = "ValidFrom";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_FROM} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_FROM = "validFrom";

    // ------------------------------------------------------------------------------------- ValidTo

    /**
     * The name of the {@code datetime2, GENERATED ALWAYS AS ROW END, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VALID_TO = "ValidTo";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VALID_TO} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VALID_TO = "validTo";

    // -------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedPackageType() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

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
        if (!(obj instanceof MappedPackageType that)) {
            return false;
        }
        return getPackageTypeId() != null
               && getPackageTypeId().equals(that.getPackageTypeId());
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
        return MappedPackageType.class.hashCode();
    }

    // ------------------------------------------------------------------------------- packageTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    public Integer getPackageTypeId() {
        return packageTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute with the specified value.
     *
     * @param packageTypeId new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_ID} attribute.
     */
    protected void setPackageTypeId(final Integer packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    // ----------------------------------------------------------------------------- packageTypeName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute.
     */
    public String getPackageTypeName() {
        return packageTypeName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute with the specified value.
     *
     * @param packageTypeName new value for {@value #ATTRIBUTE_NAME_PACKAGE_TYPE_NAME} attribute.
     */
    public void setPackageTypeName(final String packageTypeName) {
        this.packageTypeName = packageTypeName;
    }

    // -------------------------------------------------------------------------------- lastEditedBy

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected Integer getLastEditedBy() {
        return lastEditedBy;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute with the specified value. The
     * attribute is read-only; the extending entity calls this to keep it in step with the association which writes the
     * column.
     *
     * @param lastEditedBy new value for {@value #ATTRIBUTE_NAME_LAST_EDITED_BY} attribute.
     */
    protected void setLastEditedBy(final Integer lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    // ----------------------------------------------------------------------------------- validFrom

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_FROM} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validFrom new value for {@value #ATTRIBUTE_NAME_VALID_FROM} attribute.
     */
    protected void setValidFrom(final LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    // ------------------------------------------------------------------------------------- validTo

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    public LocalDateTime getValidTo() {
        return validTo;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VALID_TO} attribute with the specified value. The database
     * writes the column; this is for the provider and the extending class only.
     *
     * @param validTo new value for {@value #ATTRIBUTE_NAME_VALID_TO} attribute.
     */
    protected void setValidTo(final LocalDateTime validTo) {
        this.validTo = validTo;
    }

    // ---------------------------------------------------------------------------------------------
    // not updatable: an identifier column; Jakarta Persistence does not change the identifier of an entity
    @Id
    @SequenceGenerator(name = "WideWorldImporters.Sequences.PackageTypeID", schema = "Sequences",
                       sequenceName = "PackageTypeID",
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WideWorldImporters.Sequences.PackageTypeID")
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_ID, nullable = false, insertable = true, updatable = false,
            comment = "Numeric ID used for reference to a package type within the database")
    @MsDefault(name = "DF_Warehouse_PackageTypes_PackageTypeID", value = "(NEXT VALUE FOR [Sequences].[PackageTypeID])")
    private Integer packageTypeId;

    // ---------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_PACKAGE_TYPE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_PACKAGE_TYPE_NAME, nullable = false, insertable = true, updatable = true, length = COLUMN_LENGTH_PACKAGE_TYPE_NAME,
            comment = "Full name of package types that stock items can be purchased in or sold in")
    private String packageTypeName;

    // ---------------------------------------------------------------------------------------------
    // read-only: the extending entity owns the writable mapping of this column; see the class documentation
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_EDITED_BY, nullable = false, insertable = false, updatable = false)
    private Integer lastEditedBy;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_FROM, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validFrom;

    // ---------------------------------------------------------------------------------------------
    // read-only: a GENERATED ALWAYS period column; SQL Server sets it, and rejects a value for it in an INSERT
    // (error 13536) or an UPDATE (error 13537)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VALID_TO, nullable = false, insertable = false, updatable = false)
    private LocalDateTime validTo;
}

