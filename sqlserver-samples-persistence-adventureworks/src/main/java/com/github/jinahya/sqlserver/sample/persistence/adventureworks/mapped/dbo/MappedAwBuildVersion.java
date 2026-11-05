package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.dbo;

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
import com.github.jinahya.sqlserver.sample.persistence.common.mapped.MappedWithIdentityId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.AWBuildVersion} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code dbo.AWBuildVersion}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code SystemInformationID}</th>
 *     <td>Primary key for AWBuildVersion records.</td></tr>
 * <tr><th scope="row">{@code Database Version}</th>
 *     <td>Version number of the database in 9.yy.mm.dd.00 format.</td></tr>
 * <tr><th scope="row">{@code VersionDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code dbo.AWBuildVersion}</caption>
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
@AttributeOverride(name = MappedWithIdentityId.ATTRIBUTE_NAME_ID_,
                   column = @Column(name = MappedAwBuildVersion.COLUMN_NAME_SYSTEM_INFORMATION_ID,
                                    nullable = false, insertable = false, updatable = false,
                                    comment = "Primary key for AWBuildVersion records.")
)
@MappedSuperclass
@MsDescription(
        schema = MappedAwBuildVersion.TABLE_SCHEMA,
        table = MappedAwBuildVersion.TABLE_NAME,
        value = "Current version number of the AdventureWorks 2025 sample database. "
)
public abstract class MappedAwBuildVersion
        extends MappedWithIdentityId {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "AWBuildVersion";

    // ------------------------------------------------------------------------- SystemInformationID

    /**
     * The name of the {@code tinyint, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_SYSTEM_INFORMATION_ID = "SystemInformationID";

    // ---------------------------------------------------------------------------- Database Version

    /**
     * The name of the {@code nvarchar(25), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DATABASE_VERSION = "\"Database Version\"";

    /**
     * The length of the {@value #COLUMN_NAME_DATABASE_VERSION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DATABASE_VERSION = 25;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DATABASE_VERSION} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DATABASE_VERSION = "databaseVersion";

    // --------------------------------------------------------------------------------- VersionDate

    /**
     * The name of the {@code datetime, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_VERSION_DATE = "VersionDate";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_VERSION_DATE} column. The value
     * is {@value}.
     */
    public static final String ATTRIBUTE_NAME_VERSION_DATE = "versionDate";

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
    protected MappedAwBuildVersion() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "systemInformationId=" + getSystemInformationId()
               + ",databaseVersion=" + databaseVersion
               + ",versionDate=" + versionDate
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
        if (!(obj instanceof MappedAwBuildVersion that)) {
            return false;
        }
        return getId_() != null
               && getId_().equals(that.getId_());
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
        return MappedAwBuildVersion.class.hashCode();
    }

    // ------------------------------------------------------------------------- systemInformationId

    /**
     * Returns current value of the identifier, which maps the
     * {@value #COLUMN_NAME_SYSTEM_INFORMATION_ID} column.
     *
     * @return current value of the identifier.
     */
    public Long getSystemInformationId() {
        return getId_();
    }

    // ----------------------------------------------------------------------------- databaseVersion

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DATABASE_VERSION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DATABASE_VERSION} attribute.
     */
    public String getDatabaseVersion() {
        return databaseVersion;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DATABASE_VERSION} attribute with the
     * specified value.
     *
     * @param databaseVersion new value for {@value #ATTRIBUTE_NAME_DATABASE_VERSION} attribute.
     */
    public void setDatabaseVersion(final String databaseVersion) {
        this.databaseVersion = databaseVersion;
    }

    // --------------------------------------------------------------------------------- versionDate

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_VERSION_DATE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_VERSION_DATE} attribute.
     */
    public LocalDateTime getVersionDate() {
        return versionDate;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_VERSION_DATE} attribute with the specified
     * value.
     *
     * @param versionDate new value for {@value #ATTRIBUTE_NAME_VERSION_DATE} attribute.
     */
    public void setVersionDate(final LocalDateTime versionDate) {
        this.versionDate = versionDate;
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
    @Size(max = COLUMN_LENGTH_DATABASE_VERSION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DATABASE_VERSION, nullable = false, insertable = true,
            updatable = true, length = COLUMN_LENGTH_DATABASE_VERSION,
            comment = "Version number of the database in 9.yy.mm.dd.00 format.")
    private String databaseVersion;

    // ---------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_VERSION_DATE, nullable = false, insertable = true, updatable = true,
            comment = "Date and time the record was last updated.")
    private LocalDateTime versionDate;

    // ---------------------------------------------------------------------------------------------
    // the database's DEFAULT (getdate()) fills it on an INSERT; the date and time the record was
    // last updated,
    // which nothing in the database restamps, so an UPDATE writes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_MODIFIED_DATE, nullable = false, insertable = false,
            updatable = true,
            comment = "Date and time the record was last updated.")
    @MsDefault(name = "DF_AWBuildVersion_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;
}

