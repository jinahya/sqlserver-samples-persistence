package com.github.jinahya.sqlserver.sample.persistence.adventureworks.mapped.person;

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

import com.github.jinahya.persistence.more.converter.javautil.Base64StringAttributeConverter;
import com.github.jinahya.sqlserver.sample.persistence.common.SqlServerConstants;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDefault;
import com.github.jinahya.sqlserver.sample.persistence.common.annotation.MsDescription;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.Password} table.
 *
 * <table class="striped">
 * <caption>Columns of {@code Person.Password}</caption>
 * <thead>
 * <tr><th scope="col">Column</th><th scope="col">Description</th></tr>
 * </thead>
 * <tbody>
 * <tr><th scope="row">{@code PasswordHash}</th><td>Password for the e-mail account.</td></tr>
 * <tr><th scope="row">{@code PasswordSalt}</th>
 *     <td>Random value concatenated with the password string before the password is
 *     hashed.</td></tr>
 * <tr><th scope="row">{@code rowguid}</th>
 *     <td>ROWGUIDCOL number uniquely identifying the record. Used to support a merge replication
 *     sample.</td></tr>
 * <tr><th scope="row">{@code ModifiedDate}</th>
 *     <td>Date and time the record was last updated.</td></tr>
 * </tbody>
 * </table>
 *
 * <table class="striped">
 * <caption>Defaults of {@code Person.Password}</caption>
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
 * @see <a href="https://learn.microsoft
 *         .com/en-us/sql/t-sql/functions/getdate-transact-sql">GETDATE</a>
 */
@MappedSuperclass
@MsDescription(
        schema = MappedPassword.TABLE_SCHEMA,
        table = MappedPassword.TABLE_NAME,
        value = "One way hashed authentication information"
)
public abstract class MappedPassword {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Password";

    // ---------------------------------------------------------------------------- BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // -------------------------------------------------------------------------------- PasswordHash

    /**
     * The name of the {@code varchar(128), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PASSWORD_HASH = "PasswordHash";

    /**
     * The length of the {@value #COLUMN_NAME_PASSWORD_HASH} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PASSWORD_HASH = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PASSWORD_HASH} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PASSWORD_HASH = "passwordHash";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PASSWORD_HASH} attribute, the most bytes
     * whose padded {@link java.util.Base64} text fits the {@value #COLUMN_NAME_PASSWORD_HASH}
     * column. The value is {@value}.
     */
    private static final int SIZE_MAX_PASSWORD_HASH = COLUMN_LENGTH_PASSWORD_HASH / 4 * 3;

    // -------------------------------------------------------------------------------- PasswordSalt

    /**
     * The name of the {@code varchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PASSWORD_SALT = "PasswordSalt";

    /**
     * The length of the {@value #COLUMN_NAME_PASSWORD_SALT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PASSWORD_SALT = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PASSWORD_SALT} column. The
     * value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PASSWORD_SALT = "passwordSalt";

    /**
     * The maximum size of the {@value #ATTRIBUTE_NAME_PASSWORD_SALT} attribute, the most bytes
     * whose padded {@link java.util.Base64} text fits the {@value #COLUMN_NAME_PASSWORD_SALT}
     * column. The value is {@value}.
     */
    private static final int SIZE_MAX_PASSWORD_SALT = COLUMN_LENGTH_PASSWORD_SALT / 4 * 3;

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
    protected MappedPassword() {
        super();
    }

    // ---------------------------------------------------------------------------- java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               // + ",passwordHash=" + passwordHash // a credential, which a log should not hold
               // + ",passwordSalt=" + passwordSalt // a credential, which a log should not hold
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
        if (!(obj instanceof MappedPassword that)) {
            return false;
        }
        return Objects.equals(businessEntityId, that.businessEntityId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(businessEntityId);
    }

    // ---------------------------------------------------------------------------- businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the
     * specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    protected void setBusinessEntityId(final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
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
    @Max(SqlServerConstants.INT_MAX)
    @Min(SqlServerConstants.INT_MIN)
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID, nullable = false, insertable = true,
            updatable = false)
    private Integer businessEntityId;

    // ---------------------------------------------------------------------------------------------
    // the column holds the padded Base64 text of the bytes
    @Size(max = SIZE_MAX_PASSWORD_HASH)
    @NotNull
    @Basic(optional = false)
    @Convert(converter = Base64StringAttributeConverter.OfBasic.class)
    @Column(name = COLUMN_NAME_PASSWORD_HASH, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_PASSWORD_HASH)
    @MsDescription.Column(name = COLUMN_NAME_PASSWORD_HASH,
                          value = "Password for the e-mail account.")
    private byte[] passwordHash;

    // ---------------------------------------------------------------------------------------------
    // the column holds the padded Base64 text of the bytes
    @Size(max = SIZE_MAX_PASSWORD_SALT)
    @NotNull
    @Basic(optional = false)
    @Convert(converter = Base64StringAttributeConverter.OfBasic.class)
    @Column(name = COLUMN_NAME_PASSWORD_SALT, nullable = false, insertable = true, updatable = true,
            length = COLUMN_LENGTH_PASSWORD_SALT)
    @MsDescription.Column(name = COLUMN_NAME_PASSWORD_SALT,
                          value = "Random value concatenated with the password string before "
                                  + "the password is hashed.")
    private byte[] passwordSalt;

    // ---------------------------------------------------------------------------------------------
    // read-only; the database's DEFAULT (newid()) fills it on an INSERT, and nothing in the
    // database changes it
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ROWGUID, nullable = false, insertable = false, updatable = false)
    @MsDescription.Column(name = COLUMN_NAME_ROWGUID,
                          value = "ROWGUIDCOL number uniquely identifying the record. Used to "
                                  + "support a merge replication sample.")
    @MsDefault(name = "DF_Password_rowguid", value = "(newid())")
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
    @MsDefault(name = "DF_Password_ModifiedDate", value = "(getdate())")
    private LocalDateTime modifiedDate;

    // ---------------------------------------------------------------------------------------------
    public void setPassword(final byte[] passwordBytes, final byte[] saltBytes) {
        Objects.requireNonNull(passwordBytes, "passwordBytes is null");
        Objects.requireNonNull(saltBytes, "saltBytes is null");
        this.passwordHash = passwordBytes.clone();
        this.passwordSalt = saltBytes.clone();
    }

    private void setPassword(final byte[] passwordBytes, final SecureRandom random) {
        Objects.requireNonNull(passwordBytes, "passwordBytes is null");
        Objects.requireNonNull(random, "random is null");
        final var saltBytes = new byte[SIZE_MAX_PASSWORD_SALT];
        random.nextBytes(saltBytes);
        setPassword(passwordBytes, saltBytes);
    }

    public void setPassword(final byte[] passwordBytes) {
        Objects.requireNonNull(passwordBytes, "passwordBytes is null");
        final SecureRandom random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (final NoSuchAlgorithmException nsae) {
            throw new RuntimeException("no strong SecureRandom", nsae);
        }
        setPassword(passwordBytes, random);
    }

    private void setPassword(final SecureRandom random) {
        Objects.requireNonNull(random, "random is null");
        final var passwordBytes = new byte[SIZE_MAX_PASSWORD_HASH];
        random.nextBytes(passwordBytes);
        setPassword(passwordBytes, random);
    }

    public void setPassword() {
        final SecureRandom random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (final NoSuchAlgorithmException nsae) {
            throw new RuntimeException("no strong SecureRandom", nsae);
        }
        setPassword(random);
    }
}

