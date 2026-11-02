package com.github.jinahya.mssql.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * mssql-sample-databases-persistence-futonmanufacturing
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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * A mapped superclass which holds the mappings of the {@code dbo.TransactionType} table.
 */
@MappedSuperclass
public abstract class MappedTransactionType {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "TransactionType";

    // ----------------------------------------------------------------------------------------------- TransactionTypeID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TRANSACTION_TYPE_ID = "TransactionTypeID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TRANSACTION_TYPE_ID} column. The value is
     * {@value}.
     */
    public static final String ATTRIBUTE_NAME_TRANSACTION_TYPE_ID = "transactionTypeId";

    // -------------------------------------------------------------------------------------------------------- TypeCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TYPE_CODE = "TypeCode";

    /**
     * The length of the {@value #COLUMN_NAME_TYPE_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TYPE_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TYPE_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TYPE_CODE = "typeCode";

    // -------------------------------------------------------------------------------------------------------- TypeName

    /**
     * The name of the {@code nvarchar(100), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_TYPE_NAME = "TypeName";

    /**
     * The length of the {@value #COLUMN_NAME_TYPE_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_TYPE_NAME = 100;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_TYPE_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_TYPE_NAME = "typeName";

    // ----------------------------------------------------------------------------------------------------- Description

    /**
     * The name of the {@code nvarchar(255)} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_DESCRIPTION = "Description";

    /**
     * The length of the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DESCRIPTION = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedTransactionType() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "transactionTypeId=" + transactionTypeId
               + ",typeCode=" + typeCode
               + ",typeName=" + typeName
               + ",description=" + description
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
        if (!(obj instanceof MappedTransactionType that)) {
            return false;
        }
        return Objects.equals(transactionTypeId, that.transactionTypeId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(transactionTypeId);
    }

    // ----------------------------------------------------------------------------------------------- transactionTypeId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute with the specified value.
     *
     * @param transactionTypeId new value for {@value #ATTRIBUTE_NAME_TRANSACTION_TYPE_ID} attribute.
     */
    protected void setTransactionTypeId(final Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    // -------------------------------------------------------------------------------------------------------- typeCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TYPE_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TYPE_CODE} attribute.
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TYPE_CODE} attribute with the specified value.
     *
     * @param typeCode new value for {@value #ATTRIBUTE_NAME_TYPE_CODE} attribute.
     */
    public void setTypeCode(final String typeCode) {
        this.typeCode = typeCode;
    }

    // -------------------------------------------------------------------------------------------------------- typeName

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_TYPE_NAME} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_TYPE_NAME} attribute.
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_TYPE_NAME} attribute with the specified value.
     *
     * @param typeName new value for {@value #ATTRIBUTE_NAME_TYPE_NAME} attribute.
     */
    public void setTypeName(final String typeName) {
        this.typeName = typeName;
    }

    // ----------------------------------------------------------------------------------------------------- description

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute with the specified value.
     *
     * @param description new value for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_TRANSACTION_TYPE_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer transactionTypeId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TYPE_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TYPE_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TYPE_CODE
    )
    private String typeCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_TYPE_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TYPE_NAME,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_TYPE_NAME
    )
    private String typeName;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION,
            nullable = true,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_DESCRIPTION
    )
    private String description;
}

