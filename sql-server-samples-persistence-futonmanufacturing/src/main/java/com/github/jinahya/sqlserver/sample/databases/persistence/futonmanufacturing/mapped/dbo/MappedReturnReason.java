package com.github.jinahya.sqlserver.sample.databases.persistence.futonmanufacturing.mapped.dbo;

/*-
 * #%L
 * sql-server-samples-persistence-futonmanufacturing
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
 * A mapped superclass which holds the mappings of the {@code dbo.ReturnReason} table.
 */
@MappedSuperclass
public abstract class MappedReturnReason {

    /**
     * The name of the schema of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_SCHEMA = _Constants.TABLE_SCHEMA;

    /**
     * The name of the table to which this class maps. The value is {@value}.
     */
    public static final String TABLE_NAME = "ReturnReason";

    // -------------------------------------------------------------------------------------------------- ReturnReasonID

    /**
     * The name of the {@code int, IDENTITY, NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_RETURN_REASON_ID = "ReturnReasonID";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_RETURN_REASON_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RETURN_REASON_ID = "returnReasonId";

    // ------------------------------------------------------------------------------------------------------ ReasonCode

    /**
     * The name of the {@code nvarchar(20), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REASON_CODE = "ReasonCode";

    /**
     * The length of the {@value #COLUMN_NAME_REASON_CODE} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REASON_CODE = 20;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REASON_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REASON_CODE = "reasonCode";

    // ----------------------------------------------------------------------------------------------- ReasonDescription

    /**
     * The name of the {@code nvarchar(255), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_REASON_DESCRIPTION = "ReasonDescription";

    /**
     * The length of the {@value #COLUMN_NAME_REASON_DESCRIPTION} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_REASON_DESCRIPTION = 255;

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_REASON_DESCRIPTION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_REASON_DESCRIPTION = "reasonDescription";

    // -------------------------------------------------------------------------------------------------------- IsActive

    /**
     * The name of the {@code bit, DEFAULT ((1)), NOT NULL} column. The value is {@value}.
     */
    public static final String COLUMN_NAME_IS_ACTIVE = "IsActive";

    /**
     * The name of the attribute which maps the {@value #COLUMN_NAME_IS_ACTIVE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_IS_ACTIVE = "isActive";

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    /**
     * Creates a new instance.
     */
    protected MappedReturnReason() {
        super();
    }

    // ------------------------------------------------------------------------------------------------ java.lang.Object

    @Override
    public String toString() {
        return super.toString() + "{"
               + "returnReasonId=" + returnReasonId
               + ",reasonCode=" + reasonCode
               + ",reasonDescription=" + reasonDescription
               + ",isActive=" + isActive
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
        if (!(obj instanceof MappedReturnReason that)) {
            return false;
        }
        return Objects.equals(returnReasonId, that.returnReasonId);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implSpec The hash is over the identifier, consistent with {@link #equals(Object)}.
     */
    @Override
    public final int hashCode() {
        return Objects.hash(returnReasonId);
    }

    // -------------------------------------------------------------------------------------------------- returnReasonId

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute.
     */
    public Integer getReturnReasonId() {
        return returnReasonId;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute with the specified value.
     *
     * @param returnReasonId new value for {@value #ATTRIBUTE_NAME_RETURN_REASON_ID} attribute.
     */
    protected void setReturnReasonId(final Integer returnReasonId) {
        this.returnReasonId = returnReasonId;
    }

    // ------------------------------------------------------------------------------------------------------ reasonCode

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REASON_CODE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REASON_CODE} attribute.
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REASON_CODE} attribute with the specified value.
     *
     * @param reasonCode new value for {@value #ATTRIBUTE_NAME_REASON_CODE} attribute.
     */
    public void setReasonCode(final String reasonCode) {
        this.reasonCode = reasonCode;
    }

    // ----------------------------------------------------------------------------------------------- reasonDescription

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute.
     */
    public String getReasonDescription() {
        return reasonDescription;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute with the specified value.
     *
     * @param reasonDescription new value for {@value #ATTRIBUTE_NAME_REASON_DESCRIPTION} attribute.
     */
    public void setReasonDescription(final String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    // -------------------------------------------------------------------------------------------------------- isActive

    /**
     * Returns current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     *
     * @return current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public Boolean getIsActive() {
        return isActive;
    }

    /**
     * Replaces current value of {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute with the specified value.
     *
     * @param isActive new value for {@value #ATTRIBUTE_NAME_IS_ACTIVE} attribute.
     */
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // not updatable: an identity column, which SQL Server does not update (error 8102); and the identifier, which
    // the provider leaves out of an INSERT, as it is @GeneratedValue(strategy = IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = COLUMN_NAME_RETURN_REASON_ID,
            nullable = false,
            insertable = true,
            updatable = false
    )
    private Integer returnReasonId;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REASON_CODE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REASON_CODE,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REASON_CODE
    )
    private String reasonCode;

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = COLUMN_LENGTH_REASON_DESCRIPTION)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REASON_DESCRIPTION,
            nullable = false,
            insertable = true,
            updatable = true,
            length = COLUMN_LENGTH_REASON_DESCRIPTION
    )
    private String reasonDescription;

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_IS_ACTIVE,
            nullable = false,
            insertable = true,
            updatable = true
    )
    private Boolean isActive;
}

