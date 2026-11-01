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
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * A mapped superclass which holds the mappings of the {@code Person.Password} table.
 */
@MappedSuperclass
public abstract class MappedPassword {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = "Person";

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "Password";

    // ------------------------------------------------------------------------------------------------ BusinessEntityID

    /**
     * The name of the {@code int, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_BUSINESS_ENTITY_ID = "BusinessEntityID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_BUSINESS_ENTITY_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_BUSINESS_ENTITY_ID = "businessEntityId";

    // ---------------------------------------------------------------------------------------------------- PasswordHash

    /**
     * The name of the {@code varchar(128), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PASSWORD_HASH = "PasswordHash";

    /**
     * The length of the {@value #COLUMN_NAME_PASSWORD_HASH} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PASSWORD_HASH = 128;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PASSWORD_HASH} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PASSWORD_HASH = "passwordHash";

    // ---------------------------------------------------------------------------------------------------- PasswordSalt

    /**
     * The name of the {@code varchar(10), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_PASSWORD_SALT = "PasswordSalt";

    /**
     * The length of the {@value #COLUMN_NAME_PASSWORD_SALT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_PASSWORD_SALT = 10;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_PASSWORD_SALT} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_PASSWORD_SALT = "passwordSalt";

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * The name of the {@code uniqueidentifier, DEFAULT (newid()), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_ROWGUID = "rowguid";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_ROWGUID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_ROWGUID = "rowguid";

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
    protected MappedPassword() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "businessEntityId=" + businessEntityId
               + ",passwordHash=" + passwordHash
               + ",passwordSalt=" + passwordSalt
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

    // ------------------------------------------------------------------------------------------------ businessEntityId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    @Nonnull
    public Integer getBusinessEntityId() {
        return businessEntityId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute with the specified value.
     *
     * @param businessEntityId new value for {@value #ATTRIBUTE_NAME_BUSINESS_ENTITY_ID} attribute.
     */
    public void setBusinessEntityId(@Nonnull final Integer businessEntityId) {
        this.businessEntityId = businessEntityId;
    }

    // ---------------------------------------------------------------------------------------------------- passwordHash

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PASSWORD_HASH} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PASSWORD_HASH} attribute.
     */
    @Nonnull
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PASSWORD_HASH} attribute with the specified value.
     *
     * @param passwordHash new value for {@value #ATTRIBUTE_NAME_PASSWORD_HASH} attribute.
     */
    public void setPasswordHash(@Nonnull final String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // ---------------------------------------------------------------------------------------------------- passwordSalt

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_PASSWORD_SALT} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_PASSWORD_SALT} attribute.
     */
    @Nonnull
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_PASSWORD_SALT} attribute with the specified value.
     *
     * @param passwordSalt new value for {@value #ATTRIBUTE_NAME_PASSWORD_SALT} attribute.
     */
    public void setPasswordSalt(@Nonnull final String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    // --------------------------------------------------------------------------------------------------------- rowguid

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    @Nonnull
    public UUID getRowguid() {
        return rowguid;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_ROWGUID} attribute with the specified value.
     *
     * @param rowguid new value for {@value #ATTRIBUTE_NAME_ROWGUID} attribute.
     */
    public void setRowguid(@Nonnull final UUID rowguid) {
        this.rowguid = rowguid;
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
    @Nonnull
    @NotNull
    @Id
    @Column(name = COLUMN_NAME_BUSINESS_ENTITY_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer businessEntityId;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_PASSWORD_HASH)
    @NotNull
    @Column(name = COLUMN_NAME_PASSWORD_HASH,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PASSWORD_HASH
    )
    private String passwordHash;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @Size(max = COLUMN_LENGTH_PASSWORD_SALT)
    @NotNull
    @Column(name = COLUMN_NAME_PASSWORD_SALT,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_PASSWORD_SALT
    )
    private String passwordSalt;

    // -----------------------------------------------------------------------------------------------------------------
    @Nonnull
    @NotNull
    @Convert(converter = UuidStringAttributeConverter.class)
    @Column(name = COLUMN_NAME_ROWGUID,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private UUID rowguid;

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

